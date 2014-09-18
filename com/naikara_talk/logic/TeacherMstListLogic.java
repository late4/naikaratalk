package com.naikara_talk.logic;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dao.UserMstTeacherMstDao;
import com.naikara_talk.dbutil.ConditionMapper;
import com.naikara_talk.dbutil.OrderCondition;
import com.naikara_talk.dto.CodeManagMstDto;
import com.naikara_talk.dto.UserMstTeacherMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.mstcache.CodeManagMstCache;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>マスタ保守<br>
 * <b>クラス名称       :</b>講師マスタメンテナンス(一覧)<br>
 * <b>クラス概要       :</b>講師マスタメンテナンス(一覧)Logic<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/22 TECS 新規作成
 */
public class TeacherMstListLogic {

    // コネクション変数
    public Connection conn = null;

    // コンストラクタ
    public TeacherMstListLogic(Connection con) {
        this.conn = con;
    }

    /**
     * 検索データ件数取得。<br>
     * <br>
     * 検索データ件数取得。<br>
     * <br>
     * @param UserMstTeacherMstDto<br>
     * @return int:dataCount<br>
     * @exception NaiException
     */
    public int getRowCount(UserMstTeacherMstDto dto) throws NaiException {

        // 初期化処理
        UserMstTeacherMstDao dao = new UserMstTeacherMstDao(this.conn);

        // 一覧のデータ件数を取得する
        int dataCount = dao.rowCount(setConditions(dto), dto);

        // 戻り値
        return dataCount;

    }

    /**
     * 検索処理<br>
     * <br>
     * 検索処理<br>
     * <br>
     * @param UserMstTeacherMstDto 画面のパラメータ<br>
     * @return List<UserMstTeacherMstDto> 一覧データ <br>
     * @exception NaiException
     */
    public List<UserMstTeacherMstDto> selectList(UserMstTeacherMstDto dto) throws NaiException {

        // 初期化処理
        List<UserMstTeacherMstDto> list = new ArrayList<UserMstTeacherMstDto>();
        UserMstTeacherMstDao dao = new UserMstTeacherMstDao(this.conn);

        // 並び順:名前(姓)の昇順、名前(名)の昇順
        OrderCondition orderBy = new OrderCondition();
        orderBy.addCondition(UserMstTeacherMstDao.COND_FAMILY_JNM, OrderCondition.ASC);
        orderBy.addCondition(UserMstTeacherMstDao.COND_FIRST_JNM, OrderCondition.ASC);

        // 一覧データを取得する
        list = dao.search(setConditions(dto), orderBy, dto);

        // 戻り値
        return list;

    }

    /**
     * 検索条件の設定。<br>
     * <br>
     * 検索条件の設定。<br>
     * <br>
     * @param UserMstTeacherMstDto 画面のパラメータ<br>
     * @return ConditionMapper:conditions 検索条件<br>
     * @exception NaiException
     */
    private ConditionMapper setConditions(UserMstTeacherMstDto dto) throws NaiException {

        // 検索条件の作成
        ConditionMapper conditions = new ConditionMapper();

        // 講師IDを入力されている場合
        if (!StringUtils.isEmpty(dto.getUserId())) {
            StringBuffer work = new StringBuffer();
            work.append(NaikaraTalkConstants.OPERATOR_PERCENT).append(dto.getUserId())
                    .append(NaikaraTalkConstants.OPERATOR_PERCENT);
            conditions.addCondition(UserMstTeacherMstDao.COND_USER_ID, ConditionMapper.OPERATOR_LIKE, work.toString());
        }
        // 種別区分
        conditions.addCondition(UserMstTeacherMstDao.COND_CLASSIFICATION_KBN, ConditionMapper.OPERATOR_EQUAL,
                NaikaraTalkConstants.AUTHORITY_T);

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
