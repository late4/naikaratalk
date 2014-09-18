package com.naikara_talk.logic;

import java.sql.Connection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import com.naikara_talk.dao.PaymentTrnDao;
import com.naikara_talk.dao.TableCountDao;
import com.naikara_talk.dbutil.ConditionMapper;
import com.naikara_talk.dbutil.OrderCondition;
import com.naikara_talk.dto.CodeManagMstDto;
import com.naikara_talk.dto.PaymentTrnDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.mstcache.CodeManagMstCache;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>会社側_管理帳票<br>
 * <b>クラス名称       :</b>講師毎の支払管理表Logicクラス<br>
 * <b>クラス概要       :</b>講師毎の支払管理表Logic<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/08 TECS 新規作成
 */
public class PaymentManagementListDirectionsLogic {

    // コネクション変数
    public Connection conn = null;

    // コンストラクタ
    public PaymentManagementListDirectionsLogic(Connection con) {
        this.conn = con;
    }

    /**
     * 検索処理。<br>
     * <br>
     * 検索処理。<br>
     * <br>
     * @param PaymentTrnDto 画面のパラメータ<br>
     * @return PaymentTrnDto<br>
     * @exception NaiException
     */
    public List<PaymentTrnDto> select(PaymentTrnDto dto) throws NaiException {

        PaymentTrnDao dao = new PaymentTrnDao(this.conn);

        // 並び順:支払集計テーブル．講師IDの昇順
        OrderCondition orderBy = new OrderCondition();
        orderBy.addCondition(PaymentTrnDao.COND_USER_ID, OrderCondition.ASC);

        List<PaymentTrnDto> resultList = dao.search(SetConditions(dto), orderBy);

        return resultList;

    }

    /**
     * 検索データ件数取得。<br>
     * <br>
     * 検索データ件数取得。<br>
     * <br>
     * @param PointManagMstDto:dto<br>
     * @return int:DataCount<br>
     * @exception NaiException
     */
    public int getRowCount(PaymentTrnDto dto) throws NaiException {

        // 初期化処理
        TableCountDao dao = new TableCountDao(this.conn);

        // 一覧のデータ件数を取得する
        int DataCount = dao.rowCount(NaikaraTalkConstants.PAYMENT_TRN, SetConditions(dto));

        // 戻り値
        return DataCount;

    }

    /**
     * コード管理マスタキャッシュからデータ取得処理。<br>
     * <br>
     * コード管理マスタキャッシュからデータ取得処理。<br>
     * <br>
     * @param String 汎用コード<br>
     * @return LinkedHashMap<String, String><br>
     * @exception NaiException
     */
    public LinkedHashMap<String, String> selectCodeMst(String category) throws NaiException {

        LinkedHashMap<String, String> hashMap = new LinkedHashMap<String, String>();

        CodeManagMstCache cache = CodeManagMstCache.getInstance();
        LinkedHashMap<String, CodeManagMstDto> list = cache.getList(category);

        Iterator<String> iter = list.keySet().iterator();
        while (iter.hasNext()) {
            Object key = iter.next();
            CodeManagMstDto dto = list.get(key);
            hashMap.put(dto.getManagerCd(), dto.getManagerNm());
        }

        return hashMap;
    }

    /**
     * 検索条件の設定。<br>
     * <br>
     * 検索条件の設定。<br>
     * <br>
     * @param PaymentTrnDto 画面のパラメータ<br>
     * @return ConditionMapper:conditions 検索条件<br>
     * @exception NaiException
     */
    private ConditionMapper SetConditions(PaymentTrnDto dto) throws NaiException {

        // 検索条件の作成
        ConditionMapper conditions = new ConditionMapper();

        // 支払予定年月 = 画面の支払年月
        conditions.addCondition(PaymentTrnDao.COND_PAYMENT_PLAN_YYYY_MM, ConditionMapper.OPERATOR_EQUAL,
                NaikaraStringUtil.converToYYYYMM(dto.getPaymentPlanYyyyMm()));

        // 戻り値
        return conditions;

    }

}
