package com.naikara_talk.logic;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dao.PaymentManagedDao;
import com.naikara_talk.dao.TableCountDao;
import com.naikara_talk.dao.TeacherRateMstDao;
import com.naikara_talk.dbutil.ConditionMapper;
import com.naikara_talk.dbutil.OrderCondition;
import com.naikara_talk.dto.PaymentManagedDto;
import com.naikara_talk.dto.TeacherRateMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.mstcache.CodeManagMstCache;
import com.naikara_talk.util.DateUtil;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>売上入金・支払管理。<br>
 * <b>クラス名称　　　:</b>支払入力・修正クラス。<br>
 * <b>クラス概要　　　:</b>支払入力・修正Logic。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/07/30 TECS 新規作成。
 *  　　　　　　　　　　　 2014/04/11 TECS 変更 updateメソッド：講師ID,対象のレッスン日の最大値より源泉税有無区分を判定して源泉税を計算するように修正
 *  　　　　　　　　　　　 2014/04/11 TECS 変更 getWithholdingTaxRateKbnメソッドの追加：上記対応に伴う追加
 */

public class PaymentManagedLogic {

	// コネクション変数
	public Connection conn = null;

	// コンストラクタ
	public PaymentManagedLogic(Connection con) {
		this.conn = con;
	}

	/**
	 * 検索データ件数取得。
	 * @param dto 支払入力・修正DTO
	 * @return DataCount
	 * @throws NaiException
	 */
	public int getRowCount(PaymentManagedDto dto) throws NaiException {

		// 初期化処理
        TableCountDao dao = new TableCountDao(this.conn);
		ConditionMapper  cm = new ConditionMapper();

		// 抽出条件 支払予定年月
		cm.addCondition(PaymentManagedDao.COND_PAYMENT_PLAN_YYYY_MM, ConditionMapper.OPERATOR_EQUAL, dto.getPaymentPlanYyyyMm());
		// 抽出条件 データ発生年月
		cm.addCondition(PaymentManagedDao.COND_DATA_YYYY_MM, ConditionMapper.OPERATOR_EQUAL, dto.getDataYyyyMm());
		// 抽出条件 講師ID(利用者ID)
		cm.addCondition(PaymentManagedDao.COND_USER_ID, ConditionMapper.OPERATOR_EQUAL, dto.getUserId());

        // 一覧のデータ件数を取得して、戻る
		return dao.rowCount(NaikaraTalkConstants.PAYMENT_DETAILS_TRN, cm);

    }

	/**
	 * 画面一覧のデータ取得処理。
	 * @param dto 支払入力・修正DTO
	 * @return list
	 * @throws NaiException
	 */
	public List<PaymentManagedDto> search(PaymentManagedDto dto) throws NaiException {

		// 初期化処理
		PaymentManagedDao dao = new PaymentManagedDao(this.conn);
		ConditionMapper  cm = new ConditionMapper();
		ArrayList<PaymentManagedDto> returnList = new ArrayList<PaymentManagedDto>();
		PaymentManagedDto getDto;
		PaymentManagedDto retDto;

		// 抽出条件 支払予定年月
		cm.addCondition(PaymentManagedDao.COND_PAYMENT_PLAN_YYYY_MM, ConditionMapper.OPERATOR_EQUAL, dto.getPaymentPlanYyyyMm());
		// 抽出条件 データ発生年月
		cm.addCondition(PaymentManagedDao.COND_DATA_YYYY_MM, ConditionMapper.OPERATOR_EQUAL, dto.getDataYyyyMm());
		// 抽出条件 講師ID(利用者ID)
		cm.addCondition(PaymentManagedDao.COND_USER_ID, ConditionMapper.OPERATOR_EQUAL, dto.getUserId());

		// 支払明細テーブルのデータ取得
		List<PaymentManagedDto> list = dao.search(cm);

		// 戻るlistを設定
		for (int i = 0; i < list.size(); i++) {
			getDto = list.get(i);
			// データなしの場合
			if (getDto.getReturnCode() == NaikaraTalkConstants.RETURN_CD_DATA_NO) {
				return null;
			}
			retDto = new PaymentManagedDto();
			// レッスン日 Dateフォマット変換 yyyyMMdd ⇒ yyyy/MM/dd
			retDto.setLessonDt(NaikaraStringUtil.converToYYYY_MM_DD(getDto.getLessonDt()));
			retDto.setLessonTmNm(getDto.getLessonTmNm());            // レッスン時刻名
			retDto.setCourseJnm(getDto.getCourseJnm());              // コース名
			retDto.setStudentId(getDto.getStudentId());              // 受講者ID
			// 受講者名 共通部品：名前の編集(名前(姓)、名前(名))
			retDto.setStudentName(NaikaraStringUtil.unionName(getDto.getFamilyJnm(), getDto.getFirstJnm()));
			retDto.setEvaluationJnm(getDto.getEvaluationJnm());      // 受講者：講師への評価
			retDto.setOnTeacherCmt(getDto.getOnTeacherCmt());        // スクール：スクールから講師へのコメント
			retDto.setEndPaymentYen(getDto.getEndPaymentYen());      // 現支払予定額  支払明細テーブルの新支払予定額
			retDto.setAdjustmentYen(String.valueOf("0"));            // 調整額 初期表示0
			retDto.setNewPaymentYen(new BigDecimal(0));              // 新支払予定額 初期表示0

			// 隠しﾌｨｰﾙﾄﾞ
			retDto.setLessonTmCd(getDto.getLessonTmCd());            // レッスン時刻コード
			retDto.setRecordVerNoM(getDto.getRecordVerNoM());        // レコードバージョン番号

			returnList.add(retDto);
		}

		return returnList;

	}

	/**
	 * 更新処理
	 * @param list 画面一覧list (支払入力・修正画面：支払予定年月、データ発生年月、講師ID (利用者ID)単位)
	 * @param dto 支払入力・修正DTO
	 * @return int
	 * @throws NaiException
	 */
	public int update(List<PaymentManagedDto> list, PaymentManagedDto dto) throws NaiException {

		// PaymentManagedDaoクラスの生成
		PaymentManagedDao dao = new PaymentManagedDao(this.conn);


		// 源泉税の計算処理
		BigDecimal sourceYen = new BigDecimal(0);
		try {
			// コード管理マスタ
			CodeManagMstCache cache = CodeManagMstCache.getInstance();

			// 源泉税率取得（コード管理マスタより）
			String strWithholdingTaxRate = cache.decode(NaikaraTalkConstants.CODE_CATEGORY_WITHHOLDING_TAX_RATE, NaikaraTalkConstants.WITHHOLDING_TAX_RATE);
			BigDecimal bigWithholdingTaxRate = new BigDecimal(strWithholdingTaxRate);

			// 2014.04.11 源泉税有無区分の取得 Add-Start
			List<TeacherRateMstDto> taxKbnList = new ArrayList<TeacherRateMstDto>();

			String userId = dto.getUserId();
			String wkLessonDt = NaikaraTalkConstants.BRANK;
			// 画面一覧listより「レッスン日」最大値の取得
			PaymentManagedDto dtoD;
			String lessonDtMax = null;
			for (int i = 0; i < list.size(); i++) {
				dtoD = list.get(i);
				wkLessonDt = NaikaraStringUtil.converToYYYYMMDD(dtoD.getLessonDt());

				if (i == 0) {
					// 初回用
					lessonDtMax = wkLessonDt;
				}

				if (wkLessonDt == null || wkLessonDt.length() != 8) {
					// (存在しない筈だが)レッスン日がNull,8桁を除く場合は次のデータへ
					continue;
				}

				// 日付の比較
				if (DateUtil.dateCompare3(wkLessonDt, lessonDtMax)) {
					// lessonDtMaxの方が過去日の場合
					lessonDtMax = wkLessonDt;
				}
			}

			// 源泉税有無区分の取得
			taxKbnList = this.getWithholdingTaxRateKbn(userId, lessonDtMax);
			if (taxKbnList.size() != 1) {
				sourceYen = BigDecimal.ZERO;
			} else {
				TeacherRateMstDto taxDto = taxKbnList.get(0);  // 講師支払比率マスタDto
				if (StringUtils.equals(taxDto.getWithholdingTaxKbn(), NaikaraTalkConstants.SOURCE_WHETHER_Y )) {
					// 源泉有無区分＝有の場合
					// 計算式：支払明細テーブル.新支払予定額の合計 × 源泉税率(0.1021)
					BigDecimal wkSourceYen = dto.getNewPaymentYenSum().multiply(bigWithholdingTaxRate);
					// 小数点以下を切り捨て
					sourceYen = wkSourceYen.setScale(0, BigDecimal.ROUND_DOWN);
					dto.setSourceYen(sourceYen);  // 源泉額(円)
				} else {
					// 源泉有無区分＝無の場合
					sourceYen = BigDecimal.ZERO;
				}
			}
			// 2014.04.11 源泉税有無区分の取得 Add-End

		} catch (Exception e1) {
			sourceYen = BigDecimal.ZERO;
		}

		// 源泉額(円) の設定
		dto.setSourceYen(sourceYen);

		// 支払予定額：調整後金額(円)の設定<支払予定額 - 源泉額>
		dto.setEndPaymentYen(dto.getNewPaymentYenSum().subtract(sourceYen));


		// 更新処理
		try {
			return dao.update(list, dto);
		} catch (Exception e) {
			return NaikaraTalkConstants.RETURN_CD_ERR_UPD;
		}
	}


	// 2014.04.11 源泉税有無区分の取得 Add-Start
	/**
	 * 源泉税有無区分の取得処理<br>
	 * <br>
	 * 源泉税有無区分の取得処理を行う<br>
	 * <br>
	 * @param userId:講師ID<br>
	 * @param lessonDtMax:最終レッスン日<br>
	 * @return string:源泉税有無区分<br>
	 * @exception NaiException
	 */
	private List<TeacherRateMstDto> getWithholdingTaxRateKbn(String userId, String lessonDtMax) throws NaiException {

		// 初期化処理
		TeacherRateMstDao dao = new TeacherRateMstDao(this.conn);
		List<TeacherRateMstDto> list = new ArrayList<TeacherRateMstDto>();

		// 検索条件の作成
		ConditionMapper conditions = new ConditionMapper();

		// 利用者ID
		conditions.addCondition(TeacherRateMstDao.COND_USER_ID, ConditionMapper.OPERATOR_EQUAL, userId);

		// 適用期間：開始日
		conditions.addCondition(TeacherRateMstDao.COND_START_DT, ConditionMapper.OPERATOR_LESS_EQUAL, lessonDtMax);

		// 適用期間：終了日
		conditions.addCondition(TeacherRateMstDao.COND_END_DT, ConditionMapper.OPERATOR_LARGE_EQUAL, lessonDtMax);

		// 並び順
		OrderCondition orderBy = new OrderCondition();

		// 一覧データを取得する
		list = dao.searchTeacherRateMst(conditions, orderBy);

		// 戻り値
		return list;
	}
	// 2014.04.11 源泉税有無区分の取得 Add-End

}
