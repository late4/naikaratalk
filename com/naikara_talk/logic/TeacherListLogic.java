package com.naikara_talk.logic;

import java.sql.Connection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dao.TableCountDao;
import com.naikara_talk.dao.TeacherBookmarkDao;
import com.naikara_talk.dao.TeacherCourseDao;
import com.naikara_talk.dao.UMstTMstTCMstCMstDao;
import com.naikara_talk.dao.UserMstDao;
import com.naikara_talk.dao.UserMstTeacherMstDao;
import com.naikara_talk.dbutil.ConditionMapper;
import com.naikara_talk.dbutil.OrderCondition;
import com.naikara_talk.dto.CodeManagMstDto;
import com.naikara_talk.dto.TeacherBookmarkMstDto;
import com.naikara_talk.dto.TeacherCourseDto;
import com.naikara_talk.dto.UserMstDto;
import com.naikara_talk.dto.UserMstTeacherMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.mstcache.CodeManagMstCache;
import com.naikara_talk.util.DateUtil;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>お客様(個人)_予約処理<br>
 * <b>クラス名称       :</b>講師一覧（Pop）Logicクラス<br>
 * <b>クラス概要       :</b>登録済みの講師情報の検索処理を行い。<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/19 TECS 新規作成
 * <b>                 :</b>2014/04/22 TECS 検索条件の追加(コース名)対応
 */
public class TeacherListLogic {

    // コネクション変数
    public Connection conn = null;

    // コンストラクタ
    public TeacherListLogic(Connection con) {
        this.conn = con;
    }

    /**
     * コード管理マスタキャッシュからデータ取得処理。<br>
     * <br>
     * コード管理マスタキャッシュからデータ取得処理を行う。<br>
     * <br>
     * @param String 汎用コード <br>
     * @return LinkedHashMap<String, String> ハッシュマップ <br>
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
     * 利用者マスタ、講師マスタデータ取得。<br>
     * <br>
     * 利用者マスタ、講師マスタリストを戻り値で返却する。<br>
     * <br>
     * @param dto 利用者マスタ、講師マスタデータ取得DTO<br>
     * @return List<UserMstTeacherMstDto> 利用者マスタ、講師マスタデータ取得DTOリスト<br>
     * @throws NaiException
     */
    public List<UserMstTeacherMstDto> selectUserMstTeacherMstList(UserMstTeacherMstDto dto) throws NaiException {

        // 2014/04/22 Upd Start 検索条件の追加(コース名)対応
        // 生成
        //UserMstTeacherMstDao dao = new UserMstTeacherMstDao(this.conn);
        UMstTMstTCMstCMstDao dao = new UMstTMstTCMstCMstDao(this.conn);
        // 2014/04/22 Upd End   検索条件の追加(コース名)対応

        // 並び順
        OrderCondition orderBy = new OrderCondition();
        // 講師マスタ．ニックネーム の昇順
        orderBy.addCondition(UserMstTeacherMstDao.COND_NICK_ANM, OrderCondition.ASC);
        // 講師マスタ．利用者ID の昇順
        orderBy.addCondition(UserMstTeacherMstDao.COND_USER_ID, OrderCondition.ASC);

        // データを取得する
        return dao.search(this.setConditions(dto), orderBy);

    }

    /**
     * 受講者別講師ブックマークテーブルのデータ件数取得処理。<br>
     * <br>
     * 受講者別講師ブックマークテーブルのデータ件数取得処理を行う。<br>
     * <br>
     * @param dto 受講者別講師ブックマークテーブルDTO<br>
     * @return int 件数<br>
     * @throws NaiException
     */
    public int selectTeacherBookmarkCount(TeacherBookmarkMstDto dto) throws NaiException {

        // 初期化
        TableCountDao dao = new TableCountDao(this.conn);

        // 検索条件の作成
        ConditionMapper conditions = new ConditionMapper();

        // 受講者ID
        conditions.addCondition(TeacherBookmarkDao.COND_STUDENT_ID, ConditionMapper.OPERATOR_EQUAL, dto.getStudentId());
        // 講師ID (利用者ID)
        conditions.addCondition(TeacherBookmarkDao.COND_USER_ID, ConditionMapper.OPERATOR_EQUAL, dto.getUserId());

        // データ件数取得
        return dao.rowCount(NaikaraTalkConstants.TEACHER_BOOKMARK_MST, conditions);
    }

    /**
     * 講師別コースマスタ(+コースマスタ)リスト取得。<br>
     * <br>
     * 講師別コースマスタ(+コースマスタ)リスト取得を行う。<br>
     * <br>
     * @param dto 講師別コースマスタ(+コースマスタ)DTO<br>
     * @return List<TeacherCourseDto> 講師別コースマスタ(+コースマスタ)DTOリスト<br>
     * @throws NaiException
     */
    public List<TeacherCourseDto> selectTeacherCourseDtoList(TeacherCourseDto dto) throws NaiException {

        // 初期化
        TeacherCourseDao dao = new TeacherCourseDao(this.conn);

        // 検索条件の作成
        ConditionMapper conditions = new ConditionMapper();

        // 受講者ID
        conditions.addCondition(TeacherCourseDao.COND_USER_ID, ConditionMapper.OPERATOR_EQUAL, dto.getUserId());
        // 提供開始日 ≦ サーバーシステム日付
        conditions.addCondition(TeacherCourseDao.COND_USE_STR_DT, ConditionMapper.OPERATOR_LESS_EQUAL,
                DateUtil.getSysDate());
        // 提供終了日 ≧ サーバーシステム日付 + 27日
        conditions.addCondition(TeacherCourseDao.COND_USE_END_DT, ConditionMapper.OPERATOR_LARGE_EQUAL,
                DateUtil.getSysDateAddDay(27));

        // 並び順
        OrderCondition orderBy = new OrderCondition();

        // データを取得する
        return dao.search(conditions, orderBy);
    }

    /**
     * 検索条件の設定。<br>
     * <br>
     * 検索条件の設定。<br>
     * <br>
     * @param UserMstTeacherMstDto 利用者マスタ、講師マスタデータ取得DTO<br>
     * @return ConditionMapper:conditions<br>
     * @throws NaiException
     */
    private ConditionMapper setConditions(UserMstTeacherMstDto dto) throws NaiException {

        // 検索条件の作成
        ConditionMapper conditions = new ConditionMapper();

        StringBuffer work = new StringBuffer();

        // 講師IDを入力されている場合
        if (!StringUtils.isEmpty(dto.getUserId())) {

            // 曖昧検索
            work.setLength(0);
            work.append(NaikaraTalkConstants.OPERATOR_PERCENT).append(dto.getUserId());
            work.append(NaikaraTalkConstants.OPERATOR_PERCENT);

            conditions.addCondition(UserMstTeacherMstDao.COND_USER_ID,
                ConditionMapper.OPERATOR_LIKE, work.toString());
        }

        // ニックネームを入力されている場合
        if (!StringUtils.isEmpty(dto.getNickAnm())) {

            // 曖昧検索
            work.setLength(0);
            work.append(NaikaraTalkConstants.OPERATOR_PERCENT).append(dto.getNickAnm());
            work.append(NaikaraTalkConstants.OPERATOR_PERCENT);

            conditions.addCondition(UserMstTeacherMstDao.COND_NICK_ANM,
                ConditionMapper.OPERATOR_LIKE, work.toString());
        }

        // 性別 ≠ ”0000” (全て) の場合
        if (!StringUtils.equals(NaikaraTalkConstants.CHOICE_ALL_ZERO, dto.getGenderKbn())) {
            conditions.addCondition(UserMstTeacherMstDao.COND_GENDER_KBN, ConditionMapper.OPERATOR_EQUAL,
                    dto.getGenderKbn());
        }

        // 契約終了日 ＞ サーバーのシステム日付
        conditions.addCondition(UserMstTeacherMstDao.COND_CONTRACT_END_DT, ConditionMapper.OPERATOR_LARGER_THAN,
                DateUtil.getSysDate());


        // 2014/04/22 Add Start 検索条件の追加(コース名)対応
        // 大分類 ≠ ”0000” (全て) の場合
        if (!StringUtils.equals(NaikaraTalkConstants.CHOICE_ALL_ZERO, dto.getBigClassificationCd())) {
            conditions.addCondition(UMstTMstTCMstCMstDao.COND_BIG_CLASSIFICATION_CD, ConditionMapper.OPERATOR_EQUAL,
                    dto.getBigClassificationCd());
        }
        // 中分類 ≠ ”0000” (全て) の場合
        if (!StringUtils.equals(NaikaraTalkConstants.CHOICE_ALL_ZERO, dto.getMiddleClassificationCd())) {
            conditions.addCondition(UMstTMstTCMstCMstDao.COND_MIDDLE_CLASSIFICATION_CD, ConditionMapper.OPERATOR_EQUAL,
                    dto.getMiddleClassificationCd());
        }
        // 小分類 ≠ ”0000” (全て) の場合
        if (!StringUtils.equals(NaikaraTalkConstants.CHOICE_ALL_ZERO, dto.getSmallClassificationCd())) {
            conditions.addCondition(UMstTMstTCMstCMstDao.COND_SMALL_CLASSIFICATION_CD, ConditionMapper.OPERATOR_EQUAL,
                    dto.getSmallClassificationCd());
        }

        // コース名を入力されている場合
        if (!StringUtils.isEmpty(dto.getCourseJnm())) {
            // 曖昧検索
            work.setLength(0);
            work.append(NaikaraTalkConstants.OPERATOR_PERCENT).append(dto.getCourseJnm());
            work.append(NaikaraTalkConstants.OPERATOR_PERCENT);

            conditions.addCondition(UMstTMstTCMstCMstDao.COND_COURSE_JNM,
                ConditionMapper.OPERATOR_LIKE, work.toString());
        }
        // 2014/04/22 Add End   検索条件の追加(コース名)対応


        // 戻り値
        return conditions;
    }

    /**
     * 利用者マスタのデータ件数取得処理<br>
     * <br>
     * 利用者マスタのデータ件数取得処理を行う。<br>
     * <br>
     * @param dto 利用者マスタDTO<br>
     * @return int 件数<br>
     * @throws NaiException
     */
    public int selectUserMstCount(UserMstDto dto) throws NaiException {

        // 初期化
        TableCountDao dao = new TableCountDao(this.conn);

        // 検索条件の作成
        ConditionMapper conditions = new ConditionMapper();

        // 利用者ID
        conditions.addCondition(UserMstDao.COND_USER_ID, ConditionMapper.OPERATOR_EQUAL, dto.getUserId());
        // 利用終了日-当日予約もできるので、当日稼働ならデータあり
        //conditions.addCondition(UserMstDao.COND_USE_END_DT, ConditionMapper.OPERATOR_LARGER_THAN, dto.getUseEndDt());
        conditions.addCondition(UserMstDao.COND_USE_END_DT, ConditionMapper.OPERATOR_LARGE_EQUAL, dto.getUseEndDt());

        // データ件数取得
        return dao.rowCount(NaikaraTalkConstants.USER_MST, conditions);
    }

    /**
     * 受講者別講師ブックマークテーブルのデータ件数取得処理<br>
     * <br>
     * 受講者別講師ブックマークテーブルのデータ件数取得処理を行う。<br>
     * <br>
     * @param dto 受講者別講師ブックマークテーブルDTO<br>
     * @return int 件数<br>
     * @throws NaiException
     */
    public int selectTeacherBookmarkMstCount(TeacherBookmarkMstDto dto) throws NaiException {

        // 初期化
        TableCountDao dao = new TableCountDao(this.conn);

        // 検索条件の作成
        ConditionMapper conditions = new ConditionMapper();

        // 利用者ID
        conditions.addCondition(TeacherBookmarkDao.COND_STUDENT_ID, ConditionMapper.OPERATOR_EQUAL, dto.getStudentId());

        // 講師ID⇒(隠し項目) 講師ID(登録しようとしているものが既に登録されている場合はそのデータは件数に含めない)
        conditions.addCondition(TeacherBookmarkDao.COND_USER_ID, ConditionMapper.OPERATOR_NOT_EQUAL, dto.getUserId());

        // データ件数取得
        return dao.rowCount(NaikaraTalkConstants.TEACHER_BOOKMARK_MST, conditions);
    }

    /**
     * 講師ブックマーク登録処理<br>
     * <br>
     * 受講者別講師ブックマークテーブルへ追加処理を行う。<br>
     * <br>
     * @param dto 受講者別講師ブックマークテーブルDTO<br>
     * @return なし<br>
     * @throws NaiException
     */
    public void insertTeacherBookmarkMst(TeacherBookmarkMstDto dto) throws NaiException {

        // 初期化
        TeacherBookmarkDao dao = new TeacherBookmarkDao(this.conn);

        // 講師ブックマーク登録
        dao.insert(dto);
    }
}
