package com.naikara_talk.logic;

import java.sql.Connection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import com.naikara_talk.dao.TeacherCourseDao;
import com.naikara_talk.dao.TeacherMstDao;
import com.naikara_talk.dao.TeacherRateMstDao;
import com.naikara_talk.dao.UserMstDao;
import com.naikara_talk.dao.UserMstTeacherMstDao;
import com.naikara_talk.dbutil.ConditionMapper;
import com.naikara_talk.dbutil.OrderCondition;
import com.naikara_talk.dto.CodeManagMstDto;
import com.naikara_talk.dto.TeacherCourseDto;
import com.naikara_talk.dto.TeacherMstDto;
import com.naikara_talk.dto.TeacherRateMstDto;
import com.naikara_talk.dto.UserMstDto;
import com.naikara_talk.dto.UserMstTeacherMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.mstcache.CodeManagMstCache;
import com.naikara_talk.util.DateUtil;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>講師初期登録ページ。<br>
 * <b>クラス名称       :</b>講師初期登録ページ初期処理Logicクラス。<br>
 * <b>クラス概要       :</b>講師初期登録ページ初期処理Logic。<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/01 TECS 新規作成
 */
public class TeacherRegistrationLogic {

    // コネクション変数
    public Connection conn = null;

    // コンストラクタ
    public TeacherRegistrationLogic(Connection con) {
        this.conn = con;
    }

    /**
     * コード管理マスタキャッシュからデータ取得処理。<br>
     * <br>
     * @param category String
     * @return hashMap LinkedHashMap<String, String>
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
     * 利用者マスタ、講師マスタから対象データの取得処理。<br>
     * <br>
     * @param dto UserMstTeacherMstDto
     * @return dtoResult UserMstTeacherMstDto
     * @throws NaiException
     */
    public UserMstTeacherMstDto select(UserMstTeacherMstDto dto) throws NaiException {

        UserMstTeacherMstDao dao = new UserMstTeacherMstDao(this.conn);

        // 検索条件の作成
        ConditionMapper conditions = new ConditionMapper();
        conditions.addCondition(UserMstTeacherMstDao.COND_USER_ID, ConditionMapper.OPERATOR_EQUAL, dto.getUserId());
        // 並び順
        OrderCondition orderBy = new OrderCondition();

        // 利用者マスタ、講師マスタから対象データの取得
        List<UserMstTeacherMstDto> resultDtoList = dao.search(conditions, orderBy);

        UserMstTeacherMstDto dtoResult = new UserMstTeacherMstDto();
        // 設定
        if (NaikaraTalkConstants.RETURN_CD_DATA_YES == resultDtoList.get(0).getReturnCode()) {
            dtoResult = resultDtoList.get(0);
        }
        return dtoResult;

    }

    /**
     * 利用者マスタ対象データの取得処理。<br>
     * <br>
     * @param dto UserMstDto
     * @return dtoResult UserMstDto
     * @throws NaiException
     */
    public UserMstDto selectUserInfo(UserMstDto dto) throws NaiException {

        UserMstDao dao = new UserMstDao(this.conn);

        // 検索条件の作成
        ConditionMapper conditions = new ConditionMapper();
        conditions.addCondition(UserMstDao.COND_USER_ID, ConditionMapper.OPERATOR_EQUAL, dto.getUserId());
        // 並び順
        OrderCondition orderBy = new OrderCondition();

        // 利用者マスタ、講師マスタから対象データの取得
        List<UserMstDto> resultDtoList = dao.search(conditions, orderBy);

        UserMstDto dtoResult = new UserMstDto();
        // 設定
        if (NaikaraTalkConstants.RETURN_CD_DATA_YES == resultDtoList.get(0).getReturnCode()) {
            dtoResult = resultDtoList.get(0);
        }
        return dtoResult;

    }

    /**
     * 利用者マスタ対象データの取得処理。<br>
     * <br>
     * @param dto TeacherMstDto
     * @return dtoResult TeacherMstDto
     * @throws NaiException
     */
    public TeacherMstDto selectTeacherInfo(TeacherMstDto dto) throws NaiException {

        TeacherMstDao dao = new TeacherMstDao(this.conn);

        // 検索条件の作成
        ConditionMapper conditions = new ConditionMapper();
        conditions.addCondition(TeacherMstDao.COND_USER_ID, ConditionMapper.OPERATOR_EQUAL, dto.getUserId());
        // 並び順
        OrderCondition orderBy = new OrderCondition();

        // 利用者マスタ、講師マスタから対象データの取得
        List<TeacherMstDto> resultDtoList = dao.search(conditions, orderBy);

        TeacherMstDto dtoResult = new TeacherMstDto();
        // 設定
        if (NaikaraTalkConstants.RETURN_CD_DATA_YES == resultDtoList.get(0).getReturnCode()) {
            dtoResult = resultDtoList.get(0);
        }

        return dtoResult;
    }

    /**
     * 講師単位のコースリスト取得。<br>
     * <br>
     * @param dto UserMstTeacherMstDto
     * @return teacherCourseListDtoList TeacherCourseListDtoのリスト
     * @throws NaiException
     */
    public List<TeacherCourseDto> selectTeacherCourse(UserMstTeacherMstDto dto) throws NaiException {

        TeacherCourseDao teacherCourseDao = new TeacherCourseDao(this.conn);

        // 検索条件の作成
        ConditionMapper conditions = new ConditionMapper();
        conditions.addCondition(TeacherCourseDao.COND_USER_ID, ConditionMapper.OPERATOR_EQUAL, dto.getUserId());
        // 並び順
        OrderCondition orderBy = new OrderCondition();

        // 講師単位のコースリスト取得
        List<TeacherCourseDto> teacherCourseListDtoList = teacherCourseDao.search(conditions, orderBy);

        return teacherCourseListDtoList;

    }

    /**
     * 講師別支払比率マスタリスト取得。<br>
     * <br>
     * @param dto UserMstTeacherMstDto
     * @return teacherRateMstDtoList TeacherRateMstDtoのリスト
     * @throws NaiException
     */
    public List<TeacherRateMstDto> selectTeacherRate(UserMstTeacherMstDto dto) throws NaiException {

        TeacherRateMstDao teacherRateMstDao = new TeacherRateMstDao(this.conn);

        // 検索条件の作成
        ConditionMapper conditions = new ConditionMapper();
        conditions.addCondition(TeacherRateMstDao.COND_USER_ID, ConditionMapper.OPERATOR_EQUAL, dto.getUserId());
        conditions.addCondition(TeacherRateMstDao.COND_START_DT, ConditionMapper.OPERATOR_LESS_EQUAL,
                DateUtil.getSysDate());
        conditions.addCondition(TeacherRateMstDao.COND_END_DT, ConditionMapper.OPERATOR_LARGE_EQUAL,
                DateUtil.getSysDate());
        // 並び順
        OrderCondition orderBy = new OrderCondition();

        // 講師単位のコースリスト取得
        List<TeacherRateMstDto> teacherRateMstDtoList = teacherRateMstDao.searchTeacherRate(conditions, orderBy);

        return teacherRateMstDtoList;

    }

    /**
     * 利用者マスタ更新処理。<br>
     * <br>
     * @param dto UserMstDto
     * @return updatedRowCount int
     * @throws NaiException
     */
    public int updateUserMstDto(UserMstDto dto) throws NaiException {
        UserMstDao dao = new UserMstDao(this.conn);
        return dao.update(dto);

    }

    /**
     * 講師マスタ更新処理。<br>
     * <br>
     * @param dto TeacherMstDto
     * @return updatedRowCount int
     * @throws NaiException
     */
    public int updateTeacherMstDto(TeacherMstDto dto) throws NaiException {
        TeacherMstDao dao = new TeacherMstDao(this.conn);
        try {
            return dao.update(dto);
        } catch (Exception e) {
            throw new NaiException(e);
        }
    }
}
