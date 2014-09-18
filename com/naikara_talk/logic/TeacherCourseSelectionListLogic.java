package com.naikara_talk.logic;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dao.CourceMstDao;
import com.naikara_talk.dbutil.ConditionMapper;
import com.naikara_talk.dbutil.OrderCondition;
import com.naikara_talk.dto.CodeManagMstDto;
import com.naikara_talk.dto.CourseMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.mstcache.CodeManagMstCache;
//import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>マスタ保守<br>
 * <b>クラス名称       :</b>講師マスタメンテナンス(コース 選択)<br>
 * <b>クラス概要       :</b>講師マスタメンテナンス(コース 選択)Logic<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/08/01 TECS 新規作成
 */
public class TeacherCourseSelectionListLogic {

    // コネクション変数
    public Connection conn = null;

    // コンストラクタ
    public TeacherCourseSelectionListLogic(Connection con) {
        this.conn = con;
    }

    /**
     * 検索データ件数取得。<br>
     * <br>
     * 検索データ件数取得。<br>
     * <br>
     * @param CourseMstDto<br>
     * @return int:dataCount<br>
     * @exception NaiException
     */
    public int getRowCount(CourseMstDto dto) throws NaiException {

        // 初期化処理
        CourceMstDao dao = new CourceMstDao(this.conn);

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
     * @param CourseMstDto 画面のパラメータ<br>
     * @return List<CourseMstDto> 一覧データ <br>
     * @exception NaiException
     */
    public List<CourseMstDto> selectList(CourseMstDto dto) throws NaiException {

        // 初期化処理
        List<CourseMstDto> list = new ArrayList<CourseMstDto>();
        CourceMstDao dao = new CourceMstDao(this.conn);

        // 並び順:コースコード の昇順
        OrderCondition orderBy = new OrderCondition();
        orderBy.addCondition(CourceMstDao.COND_COURSE_CD, OrderCondition.ASC);

        // 一覧データを取得する
        // 2014/01/16 日付範囲指定の変更対応 Start
        //list = dao.search(this.setConditions(dto), orderBy, dto);
        list = dao.search(this.setConditions(dto), orderBy, dto
        		, NaikaraStringUtil.converToYYYYMMDD(dto.getUseStrDt())
        		, NaikaraStringUtil.converToYYYYMMDD(dto.getUseEndDt()));
        // 2014/01/16 日付範囲指定の変更対応 End

        // 戻り値
        return list;

    }

    /**
     * 検索条件の設定。<br>
     * <br>
     * 検索条件の設定。<br>
     * <br>
     * @param CourseMstDto 画面のパラメータ<br>
     * @return ConditionMapper:conditions 検索条件<br>
     * @exception なし
     */
    private ConditionMapper setConditions(CourseMstDto dto) {

        // 検索条件の作成
        ConditionMapper conditions = new ConditionMapper();

        // 大分類コード != "0000" 場合
        if (!StringUtils.equals(NaikaraTalkConstants.CHOICE_ALL_ZERO, dto.getBigClassificationCd())) {
            conditions.addCondition(CourceMstDao.COND_BIG_CLASSIFICATION_CD, ConditionMapper.OPERATOR_EQUAL,
                    dto.getBigClassificationCd());
        }

        // 中分類コード != "0000" 場合
        if (!StringUtils.equals(NaikaraTalkConstants.CHOICE_ALL_ZERO, dto.getMiddleClassificationCd())) {
            conditions.addCondition(CourceMstDao.COND_MIDDLE_CLASSIFICATION_CD, ConditionMapper.OPERATOR_EQUAL,
                    dto.getMiddleClassificationCd());
        }

        // 小分類コード != "0000" 場合
        if (!StringUtils.equals(NaikaraTalkConstants.CHOICE_ALL_ZERO, dto.getSmallClassificationCd())) {
            conditions.addCondition(CourceMstDao.COND_SMALL_CLASSIFICATION_CD, ConditionMapper.OPERATOR_EQUAL,
                    dto.getSmallClassificationCd());
        }

        // コース名を入力されている場合
        if (!StringUtils.isEmpty(dto.getCourseJnm())) {
            StringBuffer work = new StringBuffer();
            work.append(NaikaraTalkConstants.OPERATOR_PERCENT).append(dto.getCourseJnm())
                    .append(NaikaraTalkConstants.OPERATOR_PERCENT);
            conditions.addCondition(CourceMstDao.COND_COURSE_JNM, ConditionMapper.OPERATOR_LIKE, work.toString());
        }

        // 2014/01/16 日付範囲指定の変更対応 Start
/*
        // コースマスタ.提供開始日 ≦ 契約期間：開始日
        conditions.addCondition(CourceMstDao.COND_USE_STR_DT, ConditionMapper.OPERATOR_LESS_EQUAL,
                NaikaraStringUtil.converToYYYYMMDD(dto.getUseStrDt()));
        // コースマスタ.提供終了日 ≧ 契約期間：終了日
        conditions.addCondition(CourceMstDao.COND_USE_END_DT, ConditionMapper.OPERATOR_LARGE_EQUAL,
                NaikaraStringUtil.converToYYYYMMDD(dto.getUseEndDt()));
*/
        // 2014/01/16 日付範囲指定の変更対応 End

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
