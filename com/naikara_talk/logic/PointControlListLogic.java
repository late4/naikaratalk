package com.naikara_talk.logic;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dao.PointManagMstDao;
import com.naikara_talk.dao.TableCountDao;
import com.naikara_talk.dbutil.ConditionMapper;
import com.naikara_talk.dbutil.OrderCondition;
import com.naikara_talk.dto.CodeManagMstDto;
import com.naikara_talk.dto.PointManagMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.mstcache.CodeManagMstCache;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>マスタ保守<br>
 * <b>クラス名称       :</b>ポイント管理マスタメンテナンス(一覧)<br>
 * <b>クラス概要       :</b>ポイント管理マスタメンテナンス(一覧)Logic<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/01 TECS 新規作成
 */
public class PointControlListLogic {

	// コネクション変数
	public Connection conn = null;

	// コンストラクタ
	public PointControlListLogic(Connection con) {
		this.conn = con;
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
    public int getRowCount(PointManagMstDto dto) throws NaiException {

        // 初期化処理
        TableCountDao dao = new TableCountDao(this.conn);

        // 一覧のデータ件数を取得する
        int DataCount = dao.rowCount(NaikaraTalkConstants.POINT_MANAG_MST, SetConditions(dto));

        // 戻り値
        return DataCount;

    }

    /**
     * 検索処理<br>
     * <br>
     * 検索処理<br>
     * <br>
     * @param PointManagMstDto 画面のパラメータ<br>
     * @return List<PointManagMstDto> 一覧データ <br>
     * @exception Exception
     */
    public List<PointManagMstDto> selectList(PointManagMstDto dto) throws Exception {

        // 初期化処理
        List<PointManagMstDto> list = new ArrayList<PointManagMstDto>();
        PointManagMstDao dao = new PointManagMstDao(this.conn);

        // 並び順:通常月謝区分 の昇順、金額 の昇順、有効ポイント期限 の昇順
        OrderCondition orderBy = new OrderCondition();
        orderBy.addCondition(PointManagMstDao.COND_FEE_KBN, OrderCondition.ASC);
        orderBy.addCondition(PointManagMstDao.COND_MONEY_YEN, OrderCondition.ASC);
        orderBy.addCondition(PointManagMstDao.COND_PAYMENT_POINT_TIM, OrderCondition.ASC);

        // 一覧データを取得する
        list = dao.search(SetConditions(dto), orderBy);

        // 戻り値
        return list;

    }

    /**
     * 検索条件の設定。<br>
     * <br>
     * 検索条件の設定。<br>
     * <br>
     * @param PointManagMstDto 画面のパラメータ<br>
     * @return ConditionMapper:conditions 検索条件<br>
     * @exception NaiException
     */
    private ConditionMapper SetConditions(PointManagMstDto dto) throws NaiException {

        // 検索条件の作成
        ConditionMapper conditions = new ConditionMapper();

        // 通常月謝区分==通常,月謝(のコード) の場合
        if (StringUtils.equals(NaikaraTalkConstants.FEE_KBN_NORMAL, dto.getFeeKbn())
                || StringUtils.equals(NaikaraTalkConstants.FEE_KBN_MONTHLY, dto.getFeeKbn())) {
            conditions.addCondition(PointManagMstDao.COND_FEE_KBN, ConditionMapper.OPERATOR_EQUAL, dto.getFeeKbn());
        }

        // ポイントコードを入力されている場合
        if (!StringUtils.isEmpty(dto.getPointCd())) {
            StringBuffer work = new StringBuffer();
            work.append(NaikaraTalkConstants.OPERATOR_PERCENT).append(dto.getPointCd())
                    .append(NaikaraTalkConstants.OPERATOR_PERCENT);
            conditions.addCondition(PointManagMstDao.COND_POINT_CD, ConditionMapper.OPERATOR_LIKE, work.toString());
        }

        // 戻り値
        return conditions;

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

}
