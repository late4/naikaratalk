package com.naikara_talk.logic;

import java.sql.Connection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dao.TeacherRateMstDao;
import com.naikara_talk.dbutil.ConditionMapper;
import com.naikara_talk.dbutil.OrderCondition;
import com.naikara_talk.dto.CodeManagMstDto;
import com.naikara_talk.dto.TeacherRateMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.mstcache.CodeManagMstCache;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>マスタ保守<br>
 * <b>クラス名称       :</b>講師支払比率の設定クラス<br>
 * <b>クラス概要       :</b>講師支払比率の設定Logic<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/26 TECS 新規作成
 */
public class TeacherRateMstLogic {

    // コネクション変数
    public Connection conn = null;

    // コンストラクタ
    public TeacherRateMstLogic(Connection con) {
        this.conn = con;
    }

    /**
     * 初期表示の検索処理。<br>
     * <br>
     * 初期表示の検索処理。<br>
     * <br>
     * @param TeacherRateMstDto 画面のパラメータ<br>
     * @return List<TeacherRateMstDto><br>
     * @exception NaiException
     */
    public List<TeacherRateMstDto> select(TeacherRateMstDto dto) throws NaiException {

        TeacherRateMstDao dao = new TeacherRateMstDao(this.conn);

        // 並び順:適用期間：開始日 の昇順
        OrderCondition orderBy = new OrderCondition();
        orderBy.addCondition(TeacherRateMstDao.COND_START_DT, OrderCondition.ASC);

        return dao.searchTeacherRateMst(setConditions(dto), orderBy);

    }

    /**
     * データの存在チェック。<br>
     * <br>
     * データの存在チェック。<br>
     * <br>
     * @param TeacherRateMstDto 画面のパラメータ<br>
     * @return int<br>
     * @exception NaiException
     */
    public int getExist(TeacherRateMstDto dto) throws NaiException {

        TeacherRateMstDao dao = new TeacherRateMstDao(this.conn);

        // 並び順:適用期間：開始日 の昇順
        OrderCondition orderBy = new OrderCondition();
        orderBy.addCondition(TeacherRateMstDao.COND_START_DT, OrderCondition.ASC);

        List<TeacherRateMstDto> resultDto = dao.searchTeacherRateMst(setConditions(dto), orderBy);

        return resultDto.get(0).getReturnCode();

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
     * @param TeacherRateMstDto 画面のパラメータ<br>
     * @return ConditionMapper:conditions 検索条件<br>
     * @exception NaiException
     */
    private ConditionMapper setConditions(TeacherRateMstDto dto) throws NaiException {

        // 検索条件の作成
        ConditionMapper conditions = new ConditionMapper();

        // 利用者ID
        if (!StringUtils.isEmpty(dto.getUserId())) {
            conditions.addCondition(TeacherRateMstDao.COND_USER_ID, ConditionMapper.OPERATOR_EQUAL, dto.getUserId());
        }

        // 戻り値
        return conditions;

    }

}
