package com.naikara_talk.logic;

import java.sql.Connection;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dao.PaymentManagedListDao;
import com.naikara_talk.dao.PaymentTrnDao;
import com.naikara_talk.dao.TableCountDao;
import com.naikara_talk.dbutil.ConditionMapper;
import com.naikara_talk.dbutil.OrderCondition;
import com.naikara_talk.dto.CodeManagMstDto;
import com.naikara_talk.dto.PaymentManagedListDto;
import com.naikara_talk.dto.PaymentTrnDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.util.DateUtil;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>売上入金・支払管理。<br>
 * <b>クラス名称　　　:</b>支払管理ページクラス。<br>
 * <b>クラス概要　　　:</b>支払管理ページLOGIC。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 *
 * @author TECS <b>変更履歴　　　　:</b>2013/07/23 TECS 新規作成。
 */

public class PaymentManagedListLogic {

	/** 支払予定年月 条件項目 */
	private static final String COND_PAYMENT_PLAN_YYYY_MM = "PAYMENT_PLAN_YYYY_MM";
	/** 検索 */
    public static final String SEARCH = "0";
    /** 全選択 */
    public static final String ALLON = "1";
    /** 全解除 */
    public static final String ALLOFF = "2";
    /** 未支払 0 */
    private static final String MANAGER_CD_ZERO = "0";
    /** 支払 1 */
    private static final String MANAGER_CD_ONE = "1";

	// コネクション変数
	public Connection conn = null;

	// コンストラクタ
	public PaymentManagedListLogic(Connection con) {
		this.conn = con;
	}

    /**
	 * 検索データ件数取得。
	 * @param dto 支払管理ページDTO
	 * @return DataCount
	 * @throws NaiException
	 */
	public int getRowCount(PaymentManagedListDto dto) throws NaiException {

		// 初期化処理
        TableCountDao dao = new TableCountDao(this.conn);

		ConditionMapper  cm = new ConditionMapper();

		// 抽出条件 支払予定年月
		cm.addCondition(COND_PAYMENT_PLAN_YYYY_MM, ConditionMapper.OPERATOR_EQUAL, dto.getPaymentPlanYyyyMm());

        // 一覧のデータ件数を取得して、戻る
		return dao.rowCount(NaikaraTalkConstants.PAYMENT_TRN, cm);

    }

	/**
	 * 画面一覧のデータ取得処理。
	 * @param dto 支払管理ページDTO
	 * @param bottonFlag 0:検索 1:全選択 2:全解除
	 * @return list
	 * @throws NaiException
	 */
	public List<PaymentManagedListDto> getPaymentTrn(PaymentManagedListDto dto, String bottonFlag) throws NaiException {

		ConditionMapper  cm = new ConditionMapper();
		OrderCondition  oc = new OrderCondition();
		PaymentTrnDao dao = new PaymentTrnDao(this.conn);

		ArrayList<PaymentManagedListDto> returnList = new ArrayList<PaymentManagedListDto>();

		// 抽出条件 支払予定年月
		cm.addCondition(PaymentTrnDao.COND_PAYMENT_PLAN_YYYY_MM, ConditionMapper.OPERATOR_EQUAL, dto.getPaymentPlanYyyyMm());

		// 並び順 講師ID の昇順
		oc.addCondition(PaymentTrnDao.COND_USER_ID, OrderCondition.ASC);

		// 並び順 発生年月 の昇順
		oc.addCondition(PaymentTrnDao.COND_DATA_YYYY_MM, OrderCondition.ASC);

		// 支払集計テーブルのデータ取得
		List<PaymentTrnDto> list = dao.search(cm, oc);

		PaymentTrnDto getDto;
		PaymentManagedListDto returnDto;

		for (int i = 0; i < list.size(); i++) {
			getDto = list.get(i);
			// データなしの場合
			if (getDto.getReturnCode() == NaikaraTalkConstants.RETURN_CD_DATA_NO) {
				return null;
			}
			returnDto = new PaymentManagedListDto();

			// 取得したデータを画面表示データに設定する
			// 支払対象
			if (StringUtils.equals(bottonFlag, SEARCH)) {
				// 検索
				returnDto.setPaymentKbn(getDto.getPaymentKbn());
			} else if (StringUtils.equals(bottonFlag, ALLON)) {
				// 全選択
				returnDto.setPaymentKbn("1");
			} else if (StringUtils.equals(bottonFlag, ALLOFF)) {
				// 全解除
				returnDto.setPaymentKbn("0");
			}

			// データ発生年月
			returnDto.setDataYyyyMm(NaikaraStringUtil.converToYYYY_MM(getDto.getDataYyyyMm()));
			// 講師ID
			returnDto.setUserId(getDto.getUserId());
			// 講師名 共通部品：名前の編集(名前(姓)、名前(名))
			returnDto.setUserName(NaikaraStringUtil.unionName(getDto.getFamilyJnmP(), getDto.getFirstJnmP()));
			// レッスン回数
			returnDto.setLessonNum(getDto.getLessonNum());
			// 支払予定額(円)
			returnDto.setBatPaymentYen(getDto.getBatPaymentYen());
			// 源泉額(円)
			returnDto.setSourceYen(getDto.getSourceYen());
			// 調整額(円)
			returnDto.setAdjustmentYen(getDto.getAdjustmentYen());
			// 支払予定額(調整後金額)(円)
			returnDto.setEndPaymentYen(getDto.getEndPaymentYen());
			// レコードバージョン番号
			returnDto.setRecordVerNo(String.valueOf(getDto.getRecordVerNoP()));
			// checkbox値 base
			StringBuffer sb = new StringBuffer();
			sb.append(getDto.getDataYyyyMm()).append("_").append(getDto.getUserId()).append("_").append(getDto.getRecordVerNoP())
			.append("_").append(NaikaraStringUtil.unionName(getDto.getFamilyJnmP(), getDto.getFirstJnmP()))
			.append("_").append(getDto.getEndPaymentYen());
			returnDto.setCheckboxBaseValue(sb.toString());

			// checkbox値 add
			StringBuffer sb1 = new StringBuffer();
			sb1.append(getDto.getDataYyyyMm()).append("#").append(getDto.getUserId()).append("#").append(getDto.getRecordVerNoP())
			.append("#").append(NaikaraStringUtil.unionName(getDto.getFamilyJnmP(), getDto.getFirstJnmP()))
			.append("#").append(getDto.getEndPaymentYen());;
			returnDto.setCheckboxAddValue(sb1.toString());

			// 支払集計テーブル．データ発生年月 ＜ 現時点のシステム日付の年月 - 1ヶ月 の場合、｢修正選択｣が使用不可
			if (DateUtil.dateCompare2(addDay(NaikaraStringUtil.delSlash(DateUtil.getSysDateYM())), addDay(getDto.getDataYyyyMm()))) {
				returnDto.setRadioFlag(true);
			}
			returnList.add(returnDto);
		}

		return returnList;

	}

	/**
	 * 更新対象データの存在チェック。
	 * @param dto 支払管理ページDTO
	 * @return boolean
	 * @throws ParseException
	 * @throws NaiException
	 */
	public boolean isExist(PaymentManagedListDto dto) throws NaiException {
		ConditionMapper  cm = new ConditionMapper();
		OrderCondition  oc = new OrderCondition();
		PaymentTrnDao dao = new PaymentTrnDao(this.conn);

		// 抽出条件 支払予定年月
		cm.addCondition(PaymentTrnDao.COND_PAYMENT_PLAN_YYYY_MM, ConditionMapper.OPERATOR_EQUAL, dto.getPaymentPlanYyyyMm());
		// 抽出条件 データ発生年月
		cm.addCondition(PaymentTrnDao.COND_DATA_YYYY_MM, ConditionMapper.OPERATOR_EQUAL, dto.getDataYyyyMm());
		// 抽出条件 講師ID
		cm.addCondition(PaymentTrnDao.COND_USER_ID, ConditionMapper.OPERATOR_EQUAL, dto.getUserId());

		// 支払集計テーブルのデータ取得
		List<PaymentTrnDto> list = dao.search(cm, oc);
		PaymentTrnDto getDto;
		for (int i = 0; i < list.size(); i++) {
			getDto = list.get(i);
			// データなしの場合
			if (getDto.getReturnCode() == NaikaraTalkConstants.RETURN_CD_DATA_NO) {
				return false;
			}
		}
		return true;

	}

	/**
     * 年月変換(yyyyMM ⇒ yyyyMMdd)。<br>
     * @param String yyyyMM <br>
     * @return String yyyyMM01 <br>
     * @exception なし
     */
	private String addDay(String str) {
		StringBuffer sb = new StringBuffer();
		sb.append(str).append("01");
		return sb.toString();
	}

	/**
	 * 更新処理。
	 * @param list 「支払対象」＝ONのデータ
	 * @return int
	 * @throws NaiException
	 */
	public int update(List<PaymentManagedListDto> list, String payment_txt) throws NaiException {

		PaymentManagedListDao dao = new PaymentManagedListDao(this.conn);

		// 共通部品：指定条件のコード管理マスタの取得処理（支払対象区分）
		CodeManagMstLstLogic logic = new CodeManagMstLstLogic(this.conn);
		List<CodeManagMstDto> codeList = logic.getList(NaikaraTalkConstants.CODE_CATEGORY_PAYMENT_KBN);
		String managerCdOne = null;
		String managerCdZero = null;
		CodeManagMstDto dto;
		for (int i = 0; i < codeList.size(); i++) {
			dto = codeList.get(i);
			// 支払を取得
			if (StringUtils.equals(MANAGER_CD_ONE, dto.getManagerCd())) {
				managerCdOne = dto.getManagerNm();
			}
			// 未支払を取得
			if (StringUtils.equals(MANAGER_CD_ZERO, dto.getManagerCd())) {
				managerCdZero = dto.getManagerNm();
			}
		}

		// 更新
		try {
			return dao.update(list, managerCdOne, managerCdZero, payment_txt);
		} catch (Exception e) {
			return NaikaraTalkConstants.RETURN_CD_ERR_UPD;
		}

	}

}
