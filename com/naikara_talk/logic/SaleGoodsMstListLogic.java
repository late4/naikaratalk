package com.naikara_talk.logic;

import java.sql.Connection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dao.SaleGoodsMstDao;
import com.naikara_talk.dbutil.OrderCondition;
import com.naikara_talk.dto.CodeManagMstDto;
import com.naikara_talk.dto.GoodsMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.model.SaleGoodsMstListModel;
import com.naikara_talk.mstcache.CodeManagMstCache;
import com.naikara_talk.sessiondata.SessionUser;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>販売商品マスタメンテナンス(一覧)。<br>
 * <b>クラス名称　　　:</b>販売商品マスタメンテナンスLogicクラス。<br>
 * <b>クラス概要　　　:</b>販売商品マスタメンテナンスLogic。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/06/18 TECS 新規作成。
 */
public class SaleGoodsMstListLogic {

    // コネクション変数
    public Connection conn = null;

    // コンストラクタ
    public SaleGoodsMstListLogic(Connection con) {
        this.conn = con;
    }


    /**
     * 権限チェック。<br>
     * <br>
     * @param SaleGoodsMstListModel
     * @return int:rturnCd
     * @throws NaiException
     */
    public int checkRole(SaleGoodsMstListModel model) throws NaiException {

        int rturnCd = SaleGoodsMstListModel.CHECK_OK;

        // ロール(権限) の取得処理
        String role = NaikaraTalkConstants.BRANK;
        SessionUser SessionUserData = ((SessionUser)SessionDataUtil.getSessionData(SessionUser.class.toString()));
        if (SessionUserData != null) {
            role = SessionUserData.getRole();    // ログイン中のユーザロール
        }

        if (!StringUtils.equals(SaleGoodsMstListModel.PROS_KBN_REF, model.getProcessKbn()) &&
                StringUtils.equals(SessionUser.ROLE_STAFF, role)) {
            // 「スタッフ」の場合は[照会]のみ
            rturnCd = SaleGoodsMstListModel.ERR_NO_UPD_ROLE;
        }

        return rturnCd;
    }


    /**
     * 検索データ件数取得。<br>
     * <br>
     * @param GoodsMstDto
     *            画面のパラメータ
     * @return int:DataCount
     * @throws NaiException
     */
    public int getRowCount(GoodsMstDto dto) throws NaiException {
        int dataCount = 0;

        // SaleGoodsMstDaoクラスの生成
        SaleGoodsMstDao dao = new SaleGoodsMstDao(this.conn);

        // 並び順:商品コード の昇順
        OrderCondition orderBy = new OrderCondition();
        orderBy.addCondition(SaleGoodsMstDao.COND_GOODS_CD, OrderCondition.ASC);

        // 一覧データを取得する
        List<GoodsMstDto> list = dao.search(dto, orderBy);

        // 戻り値へ値の設定
        if (0 < list.size()) {
            // 1件分
            if (NaikaraTalkConstants.RETURN_CD_DATA_YES == list.get(0).getReturnCode()) {
                // 一覧のデータ件数を取得する
                dataCount = list.size();
            }
        }

        // 戻り値
        return dataCount;

    }


    /**
     * 検索処理。<br>
     * <br>
     * @param GoodsMstDto
     *            画面のパラメータ
     * @return List<GoodsMstDto>
     * @throws Exception
     */
    public List<GoodsMstDto> selectList(GoodsMstDto dto) throws NaiException {

        // SaleGoodsMstDaoクラスの生成
        SaleGoodsMstDao dao = new SaleGoodsMstDao(this.conn);

        // 並び順:商品コード の昇順
        OrderCondition orderBy = new OrderCondition();
        orderBy.addCondition(SaleGoodsMstDao.COND_GOODS_CD, OrderCondition.ASC);

        // 一覧データを取得する
        List<GoodsMstDto> list = dao.search(dto, orderBy);

        // 戻り値
        return list;

    }


    /**
     * 検索条件の設定。<br>
     * <br>
     * @param SaleGoodsMstListModel
     * @return ConditionMapper:conditions
     * @throws NaiException
     */
/*
    private ConditionMapper SetConditions(SaleGoodsMstListModel model) throws NaiException {

        // 検索条件の作成
        ConditionMapper  conditions = new ConditionMapper();

        StringBuffer work = new StringBuffer();

        // 商品コードを入力されている場合
        if (!StringUtils.isEmpty(model.getGoodsCd())) {
            work.setLength(0);
            work.append(NaikaraTalkConstants.OPERATOR_PERCENT).append(model.getGoodsCd());
            work.append(NaikaraTalkConstants.OPERATOR_PERCENT);

            conditions.addCondition(SaleGoodsMstDao.COND_GOODS_CD,
                ConditionMapper.OPERATOR_LIKE, work.toString());
        }

        // 商品名を入力されている場合
        if (!StringUtils.isEmpty(model.getGoodsNm())) {
            work.setLength(0);
            work.append(NaikaraTalkConstants.OPERATOR_PERCENT).append(model.getGoodsNm());
            work.append(NaikaraTalkConstants.OPERATOR_PERCENT);

            conditions.addCondition(SaleGoodsMstDao.COND_GOODS_NM,
                ConditionMapper.OPERATOR_LIKE, work.toString());
        }

        // 利用状態＝利用可(のコード) の場合
        if (StringUtils.equals(NaikaraTalkConstants.USE_KBN_OK, model.getUseKbn())) {
            conditions.addCondition(SaleGoodsMstDao.COND_USE_STR_DT,
                ConditionMapper.OPERATOR_LESS_EQUAL, DateUtil.getSysDate());

            conditions.addCondition(SaleGoodsMstDao.COND_USE_END_DT,
                ConditionMapper.OPERATOR_LARGE_EQUAL, DateUtil.getSysDate());
        }

        // 利用状態＝利用不可(のコード) の場合
        if (StringUtils.equals(NaikaraTalkConstants.USE_KBN_NG, model.getUseKbn())) {
            conditions.addCondition(SaleGoodsMstDao.COND_USE_END_DT,
                ConditionMapper.OPERATOR_LESS_THAN , DateUtil.getSysDate());

            conditions.addCondition(SaleGoodsMstDao.COND_USE_STR_DT,
                    ConditionMapper.OPERATOR_LARGER_THAN , DateUtil.getSysDate());
        }

        // 戻り値
        return conditions;

    }
*/


    /**
     * コード管理マスタキャッシュからデータ取得処理。<br>
     * <br>
     * @param String
     *            汎用コード
     * @return LinkedHashMap<String, String>
     * @throws NaiException
	 */
    public LinkedHashMap<String, String> selectCodeMst(String category) throws NaiException {

       	LinkedHashMap<String, String> hashMap=new LinkedHashMap<String, String>();

       	CodeManagMstCache cache=CodeManagMstCache.getInstance();
       	LinkedHashMap<String, CodeManagMstDto> list=cache.getList(category);

       	Iterator<String> iter = list.keySet().iterator();
       	while (iter.hasNext()) {
	       	Object key = iter.next();
	       	CodeManagMstDto dto = list.get(key);
	       	hashMap.put(dto.getManagerCd(),dto.getManagerNm());
       	}

        return hashMap;
    }


}
