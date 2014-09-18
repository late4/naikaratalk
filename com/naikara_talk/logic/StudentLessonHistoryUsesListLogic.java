package com.naikara_talk.logic;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dao.LessonReservationPerformanceTrnDao;
import com.naikara_talk.dbutil.ConditionMapper;
import com.naikara_talk.dbutil.OrderCondition;
import com.naikara_talk.dto.CodeManagMstDto;
import com.naikara_talk.dto.LessonReservationPerformanceTrnDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.mstcache.CodeManagMstCache;
import com.naikara_talk.sessiondata.SessionUser;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>お客様(個人)_実績照会<br>
 * <b>クラス名称　　　:</b>受講者側の視点から_1-2_レッスン実績Logicクラス。<br>
 * <b>クラス概要　　　:</b>受講者側の視点から_1-2_レッスン実績Logic。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/08/05 TECS 新規作成。
 */
public class StudentLessonHistoryUsesListLogic {

    // コネクション変数
    public Connection conn = null;

    // コンストラクタ
    public StudentLessonHistoryUsesListLogic(Connection con) {
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
    public int getRowCountT(LessonReservationPerformanceTrnDto dto) throws NaiException {

        // DAOの初期化
        LessonReservationPerformanceTrnDao dao = new LessonReservationPerformanceTrnDao(this.conn);

        // データ件数を取得
        int dataCount = dao.rowCountKbn(NaikaraTalkConstants.LESSON_RESERVATION_PERFORMANCE_TRN, setConditions(dto),
                dto);

        // 戻り値
        return dataCount;
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
    public int getRowCountS(LessonReservationPerformanceTrnDto dto) throws NaiException {

        // DAOの初期化
        LessonReservationPerformanceTrnDao dao = new LessonReservationPerformanceTrnDao(this.conn);

        // データ件数を取得
        int dataCount = dao.rowCountKbn(NaikaraTalkConstants.LESSON_RESERVATION_PERFORMANCE_TRN, setConditions(dto),
                dto);

        // 戻り値
        return dataCount;
    }

    /**
     * レッスンリスト取得<br>
     * <br>
     * レッスンリスト取得を行う<br>
     * <br>
     * @param dto 検索条件<br>
     * @return list 検索結果<br>
     * @exception NaiException
     */
    public List<LessonReservationPerformanceTrnDto> selectList(LessonReservationPerformanceTrnDto dto)
            throws NaiException {

        // 検索結果の初期化
        List<LessonReservationPerformanceTrnDto> list = new ArrayList<LessonReservationPerformanceTrnDto>();

        // DAOの初期化
        LessonReservationPerformanceTrnDao dao = new LessonReservationPerformanceTrnDao(this.conn);

        // 並び順:レッスン日 の降順
        // 並び順:レッスン時刻コード の降順
        // 並び順:受講者ID の昇順
        OrderCondition orderBy = new OrderCondition();
        orderBy.addCondition(LessonReservationPerformanceTrnDao.COND_LESSON_DT, OrderCondition.DESC);
        orderBy.addCondition(LessonReservationPerformanceTrnDao.COND_LESSON_TM_CD, OrderCondition.DESC);
        orderBy.addCondition(LessonReservationPerformanceTrnDao.COND_STUDENT_ID, OrderCondition.ASC);

        // 一覧データを取得する
        list = dao.searchKbn(setConditions(dto), orderBy, dto);

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
    private ConditionMapper setConditions(LessonReservationPerformanceTrnDto dto) throws NaiException {

        // 検索条件の初期化
        ConditionMapper conditions = new ConditionMapper();

        // レッスン日
        conditions.addCondition(LessonReservationPerformanceTrnDao.COND_LESSON_DT,
                ConditionMapper.OPERATOR_LARGE_EQUAL, NaikaraStringUtil.delSlash(dto.getLessonDtFr()));

        conditions.addCondition(LessonReservationPerformanceTrnDao.COND_LESSON_DT, ConditionMapper.OPERATOR_LESS_EQUAL,
                NaikaraStringUtil.delSlash(dto.getLessonDtTo()));

        StringBuffer sb = new StringBuffer();

        // 受講者ID入力されている場合
        if (!StringUtils.isEmpty(dto.getStudentId())) {
            sb.setLength(0);
            sb.append(NaikaraTalkConstants.OPERATOR_PERCENT).append(dto.getStudentId())
                    .append(NaikaraTalkConstants.OPERATOR_PERCENT);
            conditions.addCondition(LessonReservationPerformanceTrnDao.COND_STUDENT_ID, ConditionMapper.OPERATOR_LIKE,
                    sb.toString());
        }

        // 受講者ニックネーム入力されている場合
        if (!StringUtils.isEmpty(dto.getStudentNickNm())) {
            sb.setLength(0);
            sb.append(NaikaraTalkConstants.OPERATOR_PERCENT).append(dto.getStudentNickNm())
                    .append(NaikaraTalkConstants.OPERATOR_PERCENT);
            conditions.addCondition(LessonReservationPerformanceTrnDao.COND_STUDENT_NICK_NM,
                    ConditionMapper.OPERATOR_LIKE, sb.toString());
        }

        // ロールを取得
        String role = ((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString())).getRole();

        // ユーザIDを取得
        String userId = ((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString())).getUserId();

        // ロール＝受講者の場合（レッスン管理サブメニューから遷移時のスクールの場合は別ロールとなる）
        if (StringUtils.equals(SessionUser.ROLE_STUDENT, role)) {
            conditions.addCondition(LessonReservationPerformanceTrnDao.COND_STUDENT_ID,
            		ConditionMapper.OPERATOR_EQUAL,
            		userId);
        }

        // 講師IDとニックネーム
        if (!StringUtils.equals(SessionUser.ROLE_TEACHER, role)) {

            // コース名入力されている場合
            if (!StringUtils.isEmpty(dto.getCourseJnm())) {
                sb.setLength(0);
                sb.append(NaikaraTalkConstants.OPERATOR_PERCENT).append(dto.getCourseJnm())
                        .append(NaikaraTalkConstants.OPERATOR_PERCENT);
                conditions.addCondition(LessonReservationPerformanceTrnDao.COND_COURSE_JNM,
                        ConditionMapper.OPERATOR_LIKE, sb.toString());
            }

            // 大分類コードが選択されている場合
            if (!StringUtils.equals(dto.getBigClassificationJcd(), NaikaraTalkConstants.CHOICE_ALL_ZERO)) {

                conditions.addCondition(LessonReservationPerformanceTrnDao.COND_BIG_CLASSIFICATION_JCD,
                        ConditionMapper.OPERATOR_EQUAL, dto.getBigClassificationJcd());
            }

            // 中分類コードが選択されている場合
            if (!StringUtils.equals(dto.getMiddleClassificationJcd(), NaikaraTalkConstants.CHOICE_ALL_ZERO)) {

                conditions.addCondition(LessonReservationPerformanceTrnDao.COND_MIDDLE_CLASSIFICATION_JCD,
                        ConditionMapper.OPERATOR_EQUAL, dto.getMiddleClassificationJcd());
            }

            // 小分類コードが選択されている場合
            if (!StringUtils.equals(dto.getSmallClassificationJcd(), NaikaraTalkConstants.CHOICE_ALL_ZERO)) {

                conditions.addCondition(LessonReservationPerformanceTrnDao.COND_SMALL_CLASSIFICATION_JCD,
                        ConditionMapper.OPERATOR_EQUAL, dto.getSmallClassificationJcd());
            }
        } else {

            // コース名入力されている場合
            if (!StringUtils.isEmpty(dto.getCourseEnm())) {
                sb.setLength(0);
                sb.append(NaikaraTalkConstants.OPERATOR_PERCENT).append(dto.getCourseEnm())
                        .append(NaikaraTalkConstants.OPERATOR_PERCENT);
                conditions.addCondition(LessonReservationPerformanceTrnDao.COND_COURSE_ENM,
                        ConditionMapper.OPERATOR_LIKE, sb.toString());
            }

            // 大分類コード(英語)が選択されている場合
            if (!StringUtils.equals(dto.getBigClassificationEcd(), NaikaraTalkConstants.CHOICE_ALL_ZERO)) {

                conditions.addCondition(LessonReservationPerformanceTrnDao.COND_BIG_CLASSIFICATION_ECD,
                        ConditionMapper.OPERATOR_EQUAL, dto.getBigClassificationEcd());
            }

            // 中分類コード(英語)が選択されている場合
            if (!StringUtils.equals(dto.getMiddleClassificationEcd(), NaikaraTalkConstants.CHOICE_ALL_ZERO)) {

                conditions.addCondition(LessonReservationPerformanceTrnDao.COND_MIDDLE_CLASSIFICATION_ECD,
                        ConditionMapper.OPERATOR_EQUAL, dto.getMiddleClassificationEcd());
            }

            // 小分類コード(英語)が選択されている場合
            if (!StringUtils.equals(dto.getSmallClassificationEcd(), NaikaraTalkConstants.CHOICE_ALL_ZERO)) {

                conditions.addCondition(LessonReservationPerformanceTrnDao.COND_SMALL_CLASSIFICATION_ECD,
                        ConditionMapper.OPERATOR_EQUAL, dto.getSmallClassificationEcd());
            }
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