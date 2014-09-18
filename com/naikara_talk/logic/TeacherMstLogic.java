package com.naikara_talk.logic;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import com.naikara_talk.dao.CourceMstDao;
import com.naikara_talk.dao.ScheduleReservationTrnDao;
//import com.naikara_talk.dao.TableCountDao;
import com.naikara_talk.dao.TeacherCourseDao;
import com.naikara_talk.dao.TeacherCourseMstDao;
import com.naikara_talk.dao.TeacherMstDao;
import com.naikara_talk.dao.TeacherRateMstDao;
import com.naikara_talk.dao.UserMstDao;
import com.naikara_talk.dao.UserMstTeacherMstDao;
import com.naikara_talk.dbutil.ConditionMapper;
import com.naikara_talk.dbutil.OrderCondition;
import com.naikara_talk.dto.CodeManagMstDto;
import com.naikara_talk.dto.CourseMstDto;
import com.naikara_talk.dto.ScheduleReservationTrnDto;
import com.naikara_talk.dto.TeacherCourseDto;
import com.naikara_talk.dto.TeacherCourseMstDto;
import com.naikara_talk.dto.TeacherMstDto;
import com.naikara_talk.dto.TeacherRateMstDto;
import com.naikara_talk.dto.UserMstDto;
import com.naikara_talk.dto.UserMstTeacherMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.mstcache.CodeManagMstCache;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>マスタ保守<br>
 * <b>クラス名称       :</b>講師マスタメンテナンス(単票)<br>
 * <b>クラス概要       :</b>講師マスタメンテナンス(単票)Logic<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/26 TECS 新規作成
 */
public class TeacherMstLogic {

    // コネクション変数
    public Connection conn = null;

    // コンストラクタ
    public TeacherMstLogic(Connection con) {
        this.conn = con;
    }

    /**
     * データの存在チェック。<br>
     * <br>
     * データの存在チェック。<br>
     * <br>
     * @param UserMstTeacherMstDto 画面のパラメータ<br>
     * @return int<br>
     * @exception NaiException
     */
    public int getExist(UserMstTeacherMstDto dto) throws NaiException {

        UserMstTeacherMstDao dao = new UserMstTeacherMstDao(this.conn);

        OrderCondition orderBy = new OrderCondition();

        List<UserMstTeacherMstDto> resultDto = dao.search(setConditions(dto), orderBy, dto);

        return resultDto.get(0).getReturnCode();

    }

    /**
     * コードの存在チェック。<br>
     * <br>
     * コードの存在チェック。<br>
     * <br>
     * @param CourseMstDto 画面のパラメータ<br>
     * @return int<br>
     * @exception NaiException
     */
    public int getExist(CourseMstDto dto) throws NaiException {

        CourceMstDao dao = new CourceMstDao(this.conn);

        OrderCondition orderBy = new OrderCondition();

        // 検索条件の作成
        ConditionMapper conditions = new ConditionMapper();

        // コースコード
        conditions.addCondition(CourceMstDao.COND_COURSE_CD, ConditionMapper.OPERATOR_EQUAL, dto.getCourseCd());

        // 2014/01/16 日付範囲指定の変更対応 Start
/*
        // 提供開始日
        conditions.addCondition(CourceMstDao.COND_USE_STR_DT, ConditionMapper.OPERATOR_LESS_EQUAL,
                NaikaraStringUtil.converToYYYYMMDD(dto.getUseStrDt()));
        // 提供終了日
        conditions.addCondition(CourceMstDao.COND_USE_END_DT, ConditionMapper.OPERATOR_LARGE_EQUAL,
                NaikaraStringUtil.converToYYYYMMDD(dto.getUseEndDt()));
*/
        // 2014/01/16 日付範囲指定の変更対応 End


        // 2014/01/16 日付範囲指定の変更対応 Start
        // List<CourseMstDto> resultDto = dao.search(conditions, orderBy);
        CourseMstDto newDto = new CourseMstDto();
        List<CourseMstDto> resultDto = dao.search(conditions, orderBy, newDto
        		, NaikaraStringUtil.converToYYYYMMDD(dto.getUseStrDt())
        		, NaikaraStringUtil.converToYYYYMMDD(dto.getUseEndDt()));
        // 2014/01/16 日付範囲指定の変更対応 End

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
     * 初期表示の検索処理。<br>
     * <br>
     * 初期表示の検索処理。<br>
     * <br>
     * @param UserMstTeacherMstDto 画面のパラメータ<br>
     * @return UserMstTeacherMstDto<br>
     * @exception NaiException
     */
    public UserMstTeacherMstDto select(UserMstTeacherMstDto dto) throws NaiException {

        UserMstTeacherMstDao dao = new UserMstTeacherMstDao(this.conn);

        OrderCondition orderBy = new OrderCondition();

        List<UserMstTeacherMstDto> resultDto = dao.search(setConditions(dto), orderBy);

        UserMstTeacherMstDto dtoResult = new UserMstTeacherMstDto();

        // 設定
        if (NaikaraTalkConstants.RETURN_CD_DATA_YES == resultDto.get(0).getReturnCode()) {
            dtoResult = resultDto.get(0);
        }

        return dtoResult;

    }

    /**
     * 利用者マスタの検索処理<br>
     * <br>
     * 利用者マスタの検索処理<br>
     * <br>
     * @param dto 検索条件<br>
     * @return dtoResult 検索結果<br>
     * @exception NaiException
     */
    public UserMstDto searchUserMst(UserMstDto dto) throws NaiException {

        // DAOの初期化
        UserMstDao dao = new UserMstDao(this.conn);

        OrderCondition orderBy = new OrderCondition();
        // 検索条件の初期化
        ConditionMapper conditions = new ConditionMapper();

        // 検索条件を設定
        conditions.addCondition(UserMstDao.COND_USER_ID, ConditionMapper.OPERATOR_EQUAL, dto.getUserId());

        // 検索を行う
        List<UserMstDto> resultDto = dao.search(conditions, orderBy);

        // DTOの初期化
        UserMstDto dtoResult = null;

        // データありの場合
        if (resultDto.get(0).getReturnCode() != NaikaraTalkConstants.RETURN_CD_DATA_NO) {

            dtoResult = resultDto.get(0);
        }

        return dtoResult;
    }

    /**
     * 利用者マスタの更新処理<br>
     * <br>
     * 利用者マスタの更新処理を行う<br>
     * <br>
     * @param dto 更新データ<br>
     * @return int 更新結果<br>
     * @exception NaiException
     */
    public int updateUserMst(UserMstDto dto) throws NaiException {

        // DAOの初期化
        UserMstDao dao = new UserMstDao(this.conn);

        // 更新を行う
        return dao.update(dto);
    }

    /**
     * 講師マスタ検索処理<br>
     * <br>
     * 講師マスタ検索処理<br>
     * <br>
     * @param TeacherMstDto <br>
     * @return TeacherMstDto <br>
     * @exception NaiException
     */
    public TeacherMstDto searchTeacherMst(TeacherMstDto dto) throws NaiException {

        TeacherMstDao dao = new TeacherMstDao(this.conn);

        TeacherMstDto updateDto = null;

        OrderCondition orderBy = new OrderCondition();

        List<TeacherMstDto> resultDtoList = dao.search(setConditionsTeacherMst(dto), orderBy);

        if (NaikaraTalkConstants.RETURN_CD_DATA_YES == resultDtoList.get(0).getReturnCode()) {
            updateDto = resultDtoList.get(0);
        }
        return updateDto;
    }

    /**
     * 講師マスタ登録処理<br>
     * <br>
     * 講師マスタ登録処理<br>
     * <br>
     * @param TeacherMstDto <br>
     * @return int <br>
     * @exception NaiException
     */
    public int insertTeacherMst(TeacherMstDto dto) throws NaiException {
        TeacherMstDao dao = new TeacherMstDao(this.conn);
        return dao.insert(dto);
    }

    /**
     * 講師マスタ更新処理<br>
     * <br>
     * 講師マスタ更新処理<br>
     * <br>
     * @param TeacherMstDto <br>
     * @return int <br>
     * @exception NaiException
     */
    public int updateTeacherMst(TeacherMstDto dto) throws NaiException {
        TeacherMstDao dao = new TeacherMstDao(this.conn);
        return dao.update(dto);
    }

    /**
     * 講師支払比率の登録処理<br>
     * <br>
     * 講師支払比率の登録処理<br>
     * <br>
     * @param TeacherRateMstDto <br>
     * @return int <br>
     * @exception NaiException
     */
    public int insertTeacherRateMst(TeacherRateMstDto dto) throws NaiException {
        TeacherRateMstDao dao = new TeacherRateMstDao(this.conn);
        return dao.insert(dto);
    }

    /**
     * 講師支払比率データの存在チェック。<br>
     * <br>
     * 講師支払比率データの存在チェック。<br>
     * <br>
     * @param TeacherRateMstDto 画面のパラメータ<br>
     * @return int<br>
     * @exception NaiException
     */
    public int getExistTeacherRateMst(TeacherRateMstDto dto) throws NaiException {

        TeacherRateMstDao dao = new TeacherRateMstDao(this.conn);

        OrderCondition orderBy = new OrderCondition();

        ConditionMapper conditions = new ConditionMapper();
        // 講師ID
        conditions.addCondition(TeacherRateMstDao.COND_USER_ID, ConditionMapper.OPERATOR_EQUAL, dto.getUserId());
        // 適用期間：開始日
        conditions.addCondition(TeacherRateMstDao.COND_START_DT, ConditionMapper.OPERATOR_EQUAL, dto.getStartDt());

        List<TeacherRateMstDto> resultDto = dao.searchTeacherRateMst(conditions, orderBy);

        return resultDto.get(0).getReturnCode();

    }

    /**
     * 講師支払比率の更新処理<br>
     * <br>
     * 講師支払比率の更新処理<br>
     * <br>
     * @param TeacherRateMstDto <br>
     * @return int <br>
     * @exception NaiException
     */
    public int updateTeacherRateMst(TeacherRateMstDto dto) throws NaiException {
        TeacherRateMstDao dao = new TeacherRateMstDao(this.conn);
        return dao.update(dto);
    }

    /**
     * 講師支払比率の削除処理<br>
     * <br>
     * 講師支払比率の削除処理<br>
     * <br>
     * @param TeacherRateMstDto <br>
     * @return int <br>
     * @exception NaiException
     */
    public int deleteTeacherRateMst(TeacherRateMstDto dto) throws NaiException {
        TeacherRateMstDao dao = new TeacherRateMstDao(this.conn);

        ConditionMapper conditions = new ConditionMapper();
        // 講師ID
        conditions.addCondition(TeacherRateMstDao.COND_USER_ID, ConditionMapper.OPERATOR_EQUAL, dto.getUserId());
        // 適用期間：開始日
        conditions.addCondition(TeacherRateMstDao.COND_START_DT, ConditionMapper.OPERATOR_EQUAL,
                NaikaraStringUtil.converToYYYYMMDD(dto.getStartDt()));

        return dao.delete(conditions);
    }

    /**
     * 講師別コースマスタの登録処理<br>
     * <br>
     * 講師別コースマスタの登録処理<br>
     * <br>
     * @param TeacherCourseMstDto <br>
     * @return int <br>
     * @exception NaiException
     */
    public int insertTeacherCourseMst(TeacherCourseMstDto dto) throws NaiException {
        TeacherCourseMstDao dao = new TeacherCourseMstDao(this.conn);
        return dao.insert(dto);
    }

    /**
     * 講師別コースマスタの更新処理<br>
     * <br>
     * 講師別コースマスタの更新処理<br>
     * <br>
     * @param TeacherCourseMstDto <br>
     * @return int <br>
     * @exception NaiException
     */
    public int updateTeacherCourseMst(TeacherCourseMstDto dto) throws NaiException {
        TeacherCourseMstDao dao = new TeacherCourseMstDao(this.conn);
        return dao.update(dto);
    }

    /**
     * 講師別コースマスタの削除処理<br>
     * <br>
     * 講師別コースマスタの削除処理<br>
     * <br>
     * @param TeacherCourseMstDto <br>
     * @return int <br>
     * @exception NaiException
     */
    public int deleteTeacherCourseMst(TeacherCourseMstDto dto) throws NaiException {
        TeacherCourseMstDao dao = new TeacherCourseMstDao(this.conn);

        ConditionMapper conditions = new ConditionMapper();
        // 講師ID
        conditions.addCondition(TeacherCourseMstDao.COND_USER_ID, ConditionMapper.OPERATOR_EQUAL, dto.getUserId());
        // コースコード
        conditions.addCondition(TeacherCourseMstDao.COND_COURSE_CD, ConditionMapper.OPERATOR_EQUAL, dto.getCourseCd());

        return dao.delete(conditions);
    }

    /**
     * 講師別コースマスタ(+コースマスタ)取得処理。<br>
     * <br>
     * 講師別コースマスタ(+コースマスタ)取得処理。<br>
     * <br>
     * @param TeacherCourseDto 画面のパラメータ<br>
     * @return List<TeacherCourseDto><br>
     * @exception NaiException
     */
    public List<TeacherCourseDto> getTeacherCourseDtos(TeacherCourseDto dto) throws NaiException {

        List<TeacherCourseDto> modelList = new ArrayList<TeacherCourseDto>();

        TeacherCourseDao dao = new TeacherCourseDao(this.conn);

        OrderCondition orderBy = new OrderCondition();
        ConditionMapper conditions = new ConditionMapper();
        conditions.addCondition(TeacherCourseDao.COND_USER_ID, ConditionMapper.OPERATOR_EQUAL, dto.getUserId());

        modelList = dao.search(conditions, orderBy);

        return modelList;
    }

    /**
     * 講師別コースマスタ(+コースマスタ)データの存在チェック。<br>
     * <br>
     * 講師別コースマスタ(+コースマスタ)データの存在チェック。<br>
     * <br>
     * @param TeacherCourseMstDto 画面のパラメータ<br>
     * @return int<br>
     * @exception NaiException
     */
    public int getExistTeacherCourseDto(TeacherCourseMstDto dto) throws NaiException {

        TeacherCourseMstDao dao = new TeacherCourseMstDao(this.conn);

        ConditionMapper conditions = new ConditionMapper();
        // 講師ID
        conditions.addCondition(TeacherCourseMstDao.COND_USER_ID, ConditionMapper.OPERATOR_EQUAL, dto.getUserId());
        // コースコード
        conditions.addCondition(TeacherCourseMstDao.COND_COURSE_CD, ConditionMapper.OPERATOR_EQUAL, dto.getCourseCd());

        List<TeacherCourseMstDto> resultList = dao.search(conditions, new OrderCondition());

        return resultList.get(0).getReturnCode();

    }

    /**
     * 初期表示検索条件の設定。<br>
     * <br>
     * 初期表示検索条件の設定。<br>
     * <br>
     * @param UserMstTeacherMstDto 画面のパラメータ<br>
     * @return ConditionMapper:conditions 検索条件<br>
     * @exception NaiException
     */
    private ConditionMapper setConditions(UserMstTeacherMstDto dto) throws NaiException {

        // 検索条件の作成
        ConditionMapper conditions = new ConditionMapper();

        // 講師ID
        conditions.addCondition(UserMstTeacherMstDao.COND_USER_ID, ConditionMapper.OPERATOR_EQUAL, dto.getUserId());

        // 種別区分
        conditions.addCondition(UserMstTeacherMstDao.COND_CLASSIFICATION_KBN, ConditionMapper.OPERATOR_EQUAL,
                NaikaraTalkConstants.AUTHORITY_T);

        // 戻り値
        return conditions;

    }

    /**
     * 講師マスタ検索条件の設定。<br>
     * <br>
     * 講師マスタ検索条件の設定。<br>
     * <br>
     * @param TeacherMstDto 画面のパラメータ<br>
     * @return ConditionMapper:conditions 検索条件<br>
     * @exception NaiException
     */
    private ConditionMapper setConditionsTeacherMst(TeacherMstDto dto) throws NaiException {

        // 検索条件の作成
        ConditionMapper conditions = new ConditionMapper();
        // 利用者ID　
        conditions.addCondition(TeacherMstDao.COND_USER_ID, ConditionMapper.OPERATOR_EQUAL, dto.getUserId());
        // 戻り値
        return conditions;
    }

    /**
     * 講師予定予約テーブルの検索データ件数取得。<br>
     * <br>
     * @param ScheduleReservationTrnDto
     * @return int:DataCount
     * @throws NaiException
     */
    public int selectScheduleReservationTrnMstCnt(ScheduleReservationTrnDto dto) throws NaiException {

        // 初期化処理
        ScheduleReservationTrnDao dao = new ScheduleReservationTrnDao(this.conn);

        // 一覧のデータ件数を取得する
        int DataCount = dao.rowCount(dto.getTeacherId(), dto.getLessonDt(), dto.getLessonDtTo());

        // 戻り値
        return DataCount;

    }


}
