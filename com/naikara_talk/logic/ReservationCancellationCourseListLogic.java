package com.naikara_talk.logic;

import java.sql.Connection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dao.CourseUsePointMstDao;
import com.naikara_talk.dao.SchResTCouMUsrMTeaMCouUsePoiMTeaCouMLesResPerTDao;
import com.naikara_talk.dao.ScheduleReservationTrnDao;
import com.naikara_talk.dbutil.ConditionMapper;
import com.naikara_talk.dbutil.OrderCondition;
import com.naikara_talk.dto.CodeManagMstDto;
import com.naikara_talk.dto.CourseUsePointMstDto;
import com.naikara_talk.dto.SchResTCouMUsrMTeaMCouUsePoiMTeaCouMLesResPerTDto;
import com.naikara_talk.dto.ScheduleReservationTrnDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.mstcache.CodeManagMstCache;
import com.naikara_talk.sessiondata.SessionUser;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>お客様(個人)_予約処理<br>
 * <b>クラス名称       :</b>予約申込ページLogicクラス<br>
 * <b>クラス概要       :</b>受講者がレッスンを予約登録、取消ができる。<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/18 TECS 新規作成
 */
public class ReservationCancellationCourseListLogic {

    // コネクション変数
    public Connection conn = null;

    // コンストラクタ
    public ReservationCancellationCourseListLogic(Connection con) {
        this.conn = con;
    }

    /**
     * コード管理マスタキャッシュからデータ取得処理。<br>
     * <br>
     * コード管理マスタキャッシュからデータ取得処理を行う。<br>
     * <br>
     * @param String 汎用コード <br>
     * @return LinkedHashMap<String, String> ハッシュマップ<br>
     * @throws NaiException
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
     * 講師予定予約テーブル情報検索処理。<br>
     * <br>
     * 講師予定予約テーブル情報検索処理を行う。<br>
     * <br>
     * @param dto 予約申込ページDTO<br>
     * @return List<SchResTCouMUsrMTeaMCouUsePoiMTeaCouMLesResPerTDto> 予約申込ページDTOリスト<br>
     * @throws NaiException
     */
    public List<SchResTCouMUsrMTeaMCouUsePoiMTeaCouMLesResPerTDto> selectScheduleReservationInfoList(
            SchResTCouMUsrMTeaMCouUsePoiMTeaCouMLesResPerTDto dto) throws NaiException {

        // 初期化
        SchResTCouMUsrMTeaMCouUsePoiMTeaCouMLesResPerTDao dao = new SchResTCouMUsrMTeaMCouUsePoiMTeaCouMLesResPerTDao(
                this.conn);

        // 並び順
        OrderCondition orderBy = new OrderCondition();
        // 講師予定予約テーブル．レッスン時刻コード の昇順
        orderBy.addCondition(SchResTCouMUsrMTeaMCouUsePoiMTeaCouMLesResPerTDao.COND_SRT_LESSON_TM_CD,
                OrderCondition.ASC);
        // 講師マスタ．ニックネーム の昇順
        orderBy.addCondition(SchResTCouMUsrMTeaMCouUsePoiMTeaCouMLesResPerTDao.COND_TM_NICK_ANM, OrderCondition.ASC);
        // 講師マスタ．利用者ID の昇順
        orderBy.addCondition(SchResTCouMUsrMTeaMCouUsePoiMTeaCouMLesResPerTDao.COND_TM_USER_ID, OrderCondition.ASC);
        // コースマスタ．コースコード の昇順
        orderBy.addCondition(SchResTCouMUsrMTeaMCouUsePoiMTeaCouMLesResPerTDao.COND_CM_COURSE_CD, OrderCondition.ASC);

        // データを取得する
        return dao.search(dto, setConditions(dto), orderBy);
    }

    /**
     * 検索条件の設定。<br>
     * <br>
     * 検索条件の設定。<br>
     * <br>
     * @param SchResTCouMUsrMTeaMCouUsePoiMTeaCouMLesResPerTDao 予約申込ページDTO<br>
     * @return ConditionMapper:conditions<br>
     * @throws NaiException
     */
    private ConditionMapper setConditions(SchResTCouMUsrMTeaMCouUsePoiMTeaCouMLesResPerTDto dto) throws NaiException {

        // ユーザIDを取得
        String userId = ((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString())).getUserId();

        // 検索条件の作成
        ConditionMapper conditions = new ConditionMapper();

        // 講師予定予約テーブル．レッスン日 ＝ 画面の｢希望日｣
        conditions.addCondition(SchResTCouMUsrMTeaMCouUsePoiMTeaCouMLesResPerTDao.COND_SRT_LESSON_DT,
                ConditionMapper.OPERATOR_EQUAL, dto.getLessonDt());
        // 講師予定予約テーブル．レッスン時刻コード ≧ 画面の｢希望時刻(From)｣のコード値
        conditions.addCondition(SchResTCouMUsrMTeaMCouUsePoiMTeaCouMLesResPerTDao.COND_SRT_LESSON_TM_CD,
                ConditionMapper.OPERATOR_LARGE_EQUAL, dto.getLessonTmCdFr());
        // 講師予定予約テーブル．レッスン時刻コード ≦ 画面の｢希望時刻(To)｣のコード値
        conditions.addCondition(SchResTCouMUsrMTeaMCouUsePoiMTeaCouMLesResPerTDao.COND_SRT_LESSON_TM_CD,
                ConditionMapper.OPERATOR_LESS_EQUAL, dto.getLessonTmCdTo());
        // ※画面の｢コースコード｣ ≠ ””の場合
        if (!StringUtils.isEmpty(dto.getCourseCd())) {
            // コースマスタ．コースコード LIKE 画面の｢コースコード｣
            conditions.addCondition(SchResTCouMUsrMTeaMCouUsePoiMTeaCouMLesResPerTDao.COND_CM_COURSE_CD,
                    ConditionMapper.OPERATOR_LIKE, new StringBuffer().append(NaikaraTalkConstants.OPERATOR_PERCENT)
                            .append(dto.getCourseCd()).append(NaikaraTalkConstants.OPERATOR_PERCENT).toString());
        }
        // ※画面の｢大分類｣のコード ≠ ”0000”の場合
        if (!StringUtils.equals(dto.getBigClassificationCd(), NaikaraTalkConstants.CHOICE_ALL_ZERO)) {
            // コースマスタ．大分類 ＝ 画面の｢大分類｣のコード
            conditions.addCondition(SchResTCouMUsrMTeaMCouUsePoiMTeaCouMLesResPerTDao.COND_CM_BIG_CLASSIFICATION_CD,
                    ConditionMapper.OPERATOR_EQUAL, dto.getBigClassificationCd());
        }
        // ※画面の｢中分類｣のコード ≠ ”0000”の場合
        if (!StringUtils.equals(dto.getMiddleClassificationCd(), NaikaraTalkConstants.CHOICE_ALL_ZERO)) {
            // コースマスタ．中分類 ＝ 画面の｢中分類｣のコード
            conditions.addCondition(SchResTCouMUsrMTeaMCouUsePoiMTeaCouMLesResPerTDao.COND_CM_MIDDLE_CLASSIFICATION_CD,
                    ConditionMapper.OPERATOR_EQUAL, dto.getMiddleClassificationCd());
        }
        // ※画面の｢小分類｣のコード ≠ ”0000”の場合
        if (!StringUtils.equals(dto.getSmallClassificationCd(), NaikaraTalkConstants.CHOICE_ALL_ZERO)) {
            // コースマスタ．小分類 ＝ 画面の｢小分類｣のコード
            conditions.addCondition(SchResTCouMUsrMTeaMCouUsePoiMTeaCouMLesResPerTDao.COND_CM_SMALL_CLASSIFICATION_CD,
                    ConditionMapper.OPERATOR_EQUAL, dto.getSmallClassificationCd());
        }
        // ※画面の｢コース名｣ ≠ ””の場合
        if (!StringUtils.isEmpty(dto.getCourseJnm())) {
            // コースマスタ．コース名 LIKE 画面の｢コース名｣
            conditions.addCondition(SchResTCouMUsrMTeaMCouUsePoiMTeaCouMLesResPerTDao.COND_CM_COURSE_JNM,
                    ConditionMapper.OPERATOR_LIKE, new StringBuffer().append(NaikaraTalkConstants.OPERATOR_PERCENT)
                            .append(dto.getCourseJnm()).append(NaikaraTalkConstants.OPERATOR_PERCENT).toString());
        }
        // コースマスタ．提供開始日 ≦ 画面の｢希望日｣
        conditions.addCondition(SchResTCouMUsrMTeaMCouUsePoiMTeaCouMLesResPerTDao.COND_CM_USE_STR_DT,
                ConditionMapper.OPERATOR_LESS_EQUAL, dto.getLessonDt());
        // コースマスタ．提供終了日 ≧ 画面の｢希望日｣
        conditions.addCondition(SchResTCouMUsrMTeaMCouUsePoiMTeaCouMLesResPerTDao.COND_CM_USE_END_DT,
                ConditionMapper.OPERATOR_LARGE_EQUAL, dto.getLessonDt());
        // コース別利用ポイントマスタ．利用ポイント開始日 ≦ 画面の｢希望日｣
        conditions.addCondition(SchResTCouMUsrMTeaMCouUsePoiMTeaCouMLesResPerTDao.COND_CUPM_USE_POINT_STR_DT,
                ConditionMapper.OPERATOR_LESS_EQUAL, dto.getLessonDt());
        // コース別利用ポイントマスタ．利用ポイント終了日 ≧ 画面の｢希望日｣
        conditions.addCondition(SchResTCouMUsrMTeaMCouUsePoiMTeaCouMLesResPerTDao.COND_CUPM_USE_POINT_END_DT,
                ConditionMapper.OPERATOR_LARGE_EQUAL, dto.getLessonDt());
        // 画面の｢予約状況｣ ＝ ”1”(予約可能) の場合
        if (StringUtils.equals(dto.getReservationKbn(), NaikaraTalkConstants.RESERV_KBN_YES)) {
            // 講師予定予約テーブル．予約区分 ＝ 画面の｢予約状況｣
            conditions.addCondition(SchResTCouMUsrMTeaMCouUsePoiMTeaCouMLesResPerTDao.COND_SRT_RESERVATION_KBN,
                    ConditionMapper.OPERATOR_EQUAL, dto.getReservationKbn());
        }
        // 画面の｢予約状況｣ ＝ ”2”(予約済) の場合
        if (StringUtils.equals(dto.getReservationKbn(), NaikaraTalkConstants.RESERV_KBN_ALREADY)) {
            // 講師予定予約テーブル．予約区分 ＝ 画面の｢予約状況｣
            conditions.addCondition(SchResTCouMUsrMTeaMCouUsePoiMTeaCouMLesResPerTDao.COND_SRT_RESERVATION_KBN,
                    ConditionMapper.OPERATOR_EQUAL, dto.getReservationKbn());
            // 講師予定予約テーブル．受講者ID ＝ ログイン情報：受講者ID
            conditions.addCondition(SchResTCouMUsrMTeaMCouUsePoiMTeaCouMLesResPerTDao.COND_SRT_STUDENT_ID,
                    ConditionMapper.OPERATOR_EQUAL, userId);
        }
        // 画面の｢性別｣ ≠ ”0000”(全て) の場合
        if (!StringUtils.equals(dto.getGenderKbn(), NaikaraTalkConstants.CHOICE_ALL_ZERO)) {
            // 利用者マスタ．性別区分 ＝ 画面の｢性別｣のコード値
            conditions.addCondition(SchResTCouMUsrMTeaMCouUsePoiMTeaCouMLesResPerTDao.COND_UM_GENDER_KBN,
                    ConditionMapper.OPERATOR_EQUAL, dto.getGenderKbn());
        }

        // 戻り値
        return conditions;
    }

    /**
     * 講師予定予約テーブルのデータ取得処理。<br>
     * <br>
     * 講師予定予約テーブルのデータ取得処理を行う。<br>
     * <br>
     * @param dto 講師予定予約テーブルDTO<br>
     * @return List<ScheduleReservationTrnDto> 講師予定予約テーブルDTOリスト<br>
     * @throws NaiException
     */
    public List<ScheduleReservationTrnDto> selectScheduleReservationTrnList(ScheduleReservationTrnDto dto)
            throws NaiException {

        // 初期化
        ScheduleReservationTrnDao dao = new ScheduleReservationTrnDao(this.conn);

        // 検索条件の作成
        ConditionMapper conditions = new ConditionMapper();

        // 講師予定予約テーブル．講師ID
        conditions.addCondition(ScheduleReservationTrnDao.COND_TEACHER_ID, ConditionMapper.OPERATOR_EQUAL,
                dto.getTeacherId());
        // 講師予定予約テーブル．レッスン日
        conditions.addCondition(ScheduleReservationTrnDao.COND_LESSON_DT, ConditionMapper.OPERATOR_EQUAL,
                dto.getLessonDt());
        // 講師予定予約テーブル．レッスン時刻
        conditions.addCondition(ScheduleReservationTrnDao.COND_LESSON_TM_CD, ConditionMapper.OPERATOR_EQUAL,
                dto.getLessonTmCd());

        // 並び順
        OrderCondition orderBy = new OrderCondition();

        // データを取得する
        return dao.search(conditions, orderBy);
    }

    /**
     * 講師予定予約テーブルのデータ取得処理（選択されていない講師）。<br>
     * <br>
     * 講師予定予約テーブルのデータ取得処理を行う。<br>
     * <br>
     * @param dto 講師予定予約テーブルDTO<br>
     * @return List<ScheduleReservationTrnDto> 講師予定予約テーブルDTOリスト<br>
     * @throws NaiException
     */
    public List<ScheduleReservationTrnDto> selectScheduleReservationTrnNoSelectTList(ScheduleReservationTrnDto dto)
            throws NaiException {

        // 初期化
        ScheduleReservationTrnDao dao = new ScheduleReservationTrnDao(this.conn);

        // 検索条件の作成
        ConditionMapper conditions = new ConditionMapper();

        // 講師予定予約テーブル．講師ID
        conditions.addCondition(ScheduleReservationTrnDao.COND_TEACHER_ID, ConditionMapper.OPERATOR_NOT_EQUAL,
                dto.getTeacherId());
        // 講師予定予約テーブル．レッスン日
        conditions.addCondition(ScheduleReservationTrnDao.COND_LESSON_DT, ConditionMapper.OPERATOR_EQUAL,
                dto.getLessonDt());
        // 講師予定予約テーブル．レッスン時刻
        conditions.addCondition(ScheduleReservationTrnDao.COND_LESSON_TM_CD, ConditionMapper.OPERATOR_EQUAL,
                dto.getLessonTmCd());
        // 講師予定予約テーブル．受講者ID
        conditions.addCondition(ScheduleReservationTrnDao.COND_STUDENT_ID, ConditionMapper.OPERATOR_EQUAL,
                dto.getStudentId());

        // 並び順
        OrderCondition orderBy = new OrderCondition();

        // データを取得する
        return dao.search(conditions, orderBy);
    }

    /**
     * コース別利用ポイントマスタのデータ取得処理。<br>
     * <br>
     * コース別利用ポイントマスタのデータ取得処理を行う。<br>
     * <br>
     * @param dto コース別利用ポイントマスタDTO<br>
     * @return List<CourseUsePointMstDto> コース別利用ポイントマスタDTOリスト<br>
     * @throws NaiException
     */
    public List<CourseUsePointMstDto> selectCourseUsePointMstList(CourseUsePointMstDto dto) throws NaiException {

        // 初期化
        CourseUsePointMstDao dao = new CourseUsePointMstDao(this.conn);

        // 検索条件の作成
        ConditionMapper conditions = new ConditionMapper();

        // コース別利用ポイントマスタ．コースコード
        conditions.addCondition(CourseUsePointMstDao.COND_COURSE_CD, ConditionMapper.OPERATOR_EQUAL, dto.getCourseCd());
        // コース別利用ポイントマスタ．利用ポイント開始日
        conditions.addCondition(CourseUsePointMstDao.COND_USE_POINT_STR_DT, ConditionMapper.OPERATOR_LESS_EQUAL,
                dto.getUsePointStrDt());
        // コース別利用ポイントマスタ．利用ポイント終了日
        conditions.addCondition(CourseUsePointMstDao.COND_USE_POINT_END_DT, ConditionMapper.OPERATOR_LARGE_EQUAL,
                dto.getUsePointEndDt());

        // 並び順
        OrderCondition orderBy = new OrderCondition();

        // データを取得する
        return dao.search(conditions, orderBy);
    }

}
