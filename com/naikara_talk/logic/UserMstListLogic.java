package com.naikara_talk.logic;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dao.UserMstDao;
import com.naikara_talk.dbutil.ConditionMapper;
import com.naikara_talk.dbutil.OrderCondition;
import com.naikara_talk.dto.CodeManagMstDto;
import com.naikara_talk.dto.UserMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.mstcache.CodeManagMstCache;
import com.naikara_talk.util.DateUtil;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>マスタ保守。<br>
 * <b>クラス名称　　　:</b>利用者マスタメンテナンスLogicクラス。<br>
 * <b>クラス概要　　　:</b>利用者マスタメンテナンスLogic。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/07/04 TECS 新規作成。
 */
public class UserMstListLogic {

	// コネクション変数
	public Connection conn = null;

	// コンストラクタ
	public UserMstListLogic(Connection con) {
		this.conn = con;
	}

    /**
     * 検索データ件数取得<br>
     * <br>
     * 検索データ件数取得を行う<br>
     * <br>
     * @param dto 検索条件<br>
     * @return DataCount 件数<br>
     * @exception NaiException
     */
    public int getRowCount(UserMstDto dto) throws NaiException {

        // DAOの初期化
        UserMstDao dao = new UserMstDao(this.conn);

        // データ件数を取得
        int dataCount = dao.rowCount(NaikaraTalkConstants.USER_MST,
                setConditions(dto), dto);

        // 戻り値
        return dataCount;
    }

    /**
     * 利用者リスト取得<br>
     * <br>
     * 利用者リスト取得を行う<br>
     * <br>
     * @param dto 検索条件<br>
     * @return list 検索結果<br>
     * @exception Exception
     */
    public List<UserMstDto> selectList(UserMstDto dto) throws Exception {

        // 検索結果の初期化
        List<UserMstDto> list = new ArrayList<UserMstDto>();

        // DAOの初期化
        UserMstDao dao = new UserMstDao(this.conn);

        // 並び順:利用者ID の昇順
        OrderCondition orderBy = new OrderCondition();
        orderBy.addCondition(UserMstDao.COND_USER_ID, OrderCondition.ASC);

        // 一覧データを取得する
        list = dao.searchWithKnm(setConditions(dto), orderBy, dto);

        // 戻り値
        return list;
    }

    /**
     * 検索条件設定<br>
     * <br>
     * 検索条件を設定<br>
     * <br>
     * @param dto 検索条件<br>
     * @return conditions 設定後の検索条件<br>
     * @exception NaiException
     */
    private ConditionMapper setConditions(UserMstDto dto) throws NaiException {

        // 検索条件の初期化
        ConditionMapper conditions = new ConditionMapper();

        StringBuffer sb = new StringBuffer();
        sb.append(NaikaraTalkConstants.OPERATOR_PERCENT)
                .append(dto.getUserId())
                .append(NaikaraTalkConstants.OPERATOR_PERCENT);

        // 利用者IDが入力されている場合
        if (!StringUtils.isEmpty(dto.getUserId())) {

            conditions.addCondition(UserMstDao.COND_USER_ID,
                    ConditionMapper.OPERATOR_LIKE, sb.toString());
        }

        // 利用状態＝利用可(のコード) の場合
        if (StringUtils.equals(NaikaraTalkConstants.USE_KBN_OK,
                dto.getRiyouKbn())) {

            conditions.addCondition(UserMstDao.COND_USE_START_DT,
                    ConditionMapper.OPERATOR_LESS_EQUAL, DateUtil.getSysDate());

            conditions
                    .addCondition(UserMstDao.COND_USE_END_DT,
                            ConditionMapper.OPERATOR_LARGE_EQUAL,
                            DateUtil.getSysDate());
        }

        // 利用状態＝利用不可(のコード) の場合
        if (StringUtils.equals(NaikaraTalkConstants.USE_KBN_NG,
                dto.getRiyouKbn())) {

            conditions.addCondition(UserMstDao.COND_USE_END_DT,
                    ConditionMapper.OPERATOR_LESS_THAN, DateUtil.getSysDate());
        }

        // 戻り値
        return conditions;
    }

    /**
     * コード取得<br>
     * <br>
     * コード管理マスタキャッシュからデータ取得処理<br>
     * <br>
     * @param category 汎用コード<br>
     * @return hashMap 取得結果<br>
     * @exception NaiException
     */
    public LinkedHashMap<String, String> selectCodeMst(String category)
            throws NaiException {

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