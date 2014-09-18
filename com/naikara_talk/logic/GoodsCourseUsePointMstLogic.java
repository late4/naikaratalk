package com.naikara_talk.logic;

import java.sql.Connection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dao.CourseUsePointMstDao;
import com.naikara_talk.dbutil.ConditionMapper;
import com.naikara_talk.dbutil.OrderCondition;
import com.naikara_talk.dto.CodeManagMstDto;
import com.naikara_talk.dto.CourseUsePointMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.mstcache.CodeManagMstCache;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>マスタ保守<br>
 * <b>クラス名称       :</b>コースマスタメンテナンス(コース利用ポイント)クラス<br>
 * <b>クラス概要       :</b>コースマスタメンテナンス(コース利用ポイント)Logic<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/08/18 TECS 新規作成
 */
public class GoodsCourseUsePointMstLogic {

    // コネクション変数
    public Connection conn = null;

    // コンストラクタ
    public GoodsCourseUsePointMstLogic(Connection con) {
        this.conn = con;
    }

    /**
     * 初期表示の検索処理。<br>
     * <br>
     * 初期表示の検索処理。<br>
     * <br>
     * @param CourseUsePointMstDto 画面のパラメータ<br>
     * @return List<CourseUsePointMstDto><br>
     * @exception NaiException
     */

    public List<CourseUsePointMstDto> select(CourseUsePointMstDto dto) throws NaiException {
        CourseUsePointMstDao dao = new CourseUsePointMstDao(this.conn);

        // 並び順:利用ポイント開始日 の昇順
        OrderCondition orderBy = new OrderCondition();
        orderBy.addCondition(CourseUsePointMstDao.COND_USE_POINT_STR_DT, OrderCondition.ASC);

        return dao.search(setConditions(dto), orderBy);

    }

    /**
     * データの存在チェック。<br>
     * <br>
     * データの存在チェック。<br>
     * <br>
     * @param CourseUsePointMstDto 画面のパラメータ<br>
     * @return int<br>
     * @exception NaiException
     */
    public int getExist(CourseUsePointMstDto dto) throws NaiException {

        CourseUsePointMstDao dao = new CourseUsePointMstDao(this.conn);

        // 並び順:利用ポイント開始日 の昇順
        OrderCondition orderBy = new OrderCondition();
        orderBy.addCondition(CourseUsePointMstDao.COND_USE_POINT_STR_DT, OrderCondition.ASC);

        List<CourseUsePointMstDto> resultDto = dao.search(setConditions(dto), orderBy);

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
     * @param CourseUsePointMstDto 画面のパラメータ<br>
     * @return ConditionMapper:conditions 検索条件<br>
     * @exception NaiException
     */
    private ConditionMapper setConditions(CourseUsePointMstDto dto) throws NaiException {

        // 検索条件の作成
        ConditionMapper conditions = new ConditionMapper();

        // 利用者ID
        if (!StringUtils.isEmpty(dto.getCourseCd())) {
            conditions.addCondition(CourseUsePointMstDao.COND_COURSE_CD, ConditionMapper.OPERATOR_EQUAL,
                    dto.getCourseCd());
        }

        // 戻り値
        return conditions;

    }

}
