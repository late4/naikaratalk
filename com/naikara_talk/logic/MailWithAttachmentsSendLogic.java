package com.naikara_talk.logic;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;

import com.naikara_talk.dao.StudentMstDao;
import com.naikara_talk.dao.TeacherMstDao;
import com.naikara_talk.dao.UserMstDao;
import com.naikara_talk.dbutil.ConditionMapper;
import com.naikara_talk.dbutil.OrderCondition;
import com.naikara_talk.dto.CodeManagMstDto;
import com.naikara_talk.dto.StudentMstDto;
import com.naikara_talk.dto.TeacherMstDto;
import com.naikara_talk.dto.UserMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.mstcache.CodeManagMstCache;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>お客様(個人)_初期登録。<br>
 * <b>クラス名称　　　:</b>マイページLogicクラス。<br>
 * <b>クラス概要　　　:</b>マイページLogic。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/08/02 TECS 新規作成。
 */
public class MailWithAttachmentsSendLogic {

    public Connection conn = null;

    public MailWithAttachmentsSendLogic(Connection con) {

        this.conn = con;
    }

    /**
     * 初期表示処理<br>
     * <br>
     * 初期表示を処理<br>
     * <br>
     * @param TeacherMstDto 画面のパラメータ<br>
     * @return TeacherMstDto<br>
     * @exception NaiException
     */
    public TeacherMstDto select(TeacherMstDto dto) throws NaiException {

        // DAOの初期化
        TeacherMstDao dao = new TeacherMstDao(this.conn);

        // 並び順:利用者ID の昇順
        OrderCondition orderBy = new OrderCondition();
        orderBy.addCondition(TeacherMstDao.COND_USER_ID, OrderCondition.ASC);

        // 検索を行う
        ArrayList<TeacherMstDto> resultDto = (ArrayList<TeacherMstDto>) dao
                .search(setConditions(dto), orderBy);

        // DTOの初期化
        TeacherMstDto dtoResult = new TeacherMstDto();

        // データありの場合
        if (resultDto.get(0).getReturnCode() != NaikaraTalkConstants.RETURN_CD_DATA_NO) {

            dtoResult = resultDto.get(0);
        }

        return dtoResult;
    }

    /**
     * 受講者処理<br>
     * <br>
     * 受講者を処理<br>
     * <br>
     * @param StudentMstDto 画面のパラメータ<br>
     * @return StudentMstDto<br>
     * @exception NaiException
     */
    public StudentMstDto selectStudent(StudentMstDto dto) throws NaiException {

        // DAOの初期化
        StudentMstDao dao = new StudentMstDao(this.conn);

        // 並び順:受講者ID の昇順
        OrderCondition orderBy = new OrderCondition();
        orderBy.addCondition(StudentMstDao.COND_STUDENT_ID, OrderCondition.ASC);

        // 検索を行う
        ArrayList<StudentMstDto> resultDto = (ArrayList<StudentMstDto>) dao
                .search(setStudentConditions(dto), orderBy);

        // DTOの初期化
        StudentMstDto dtoResult = new StudentMstDto();

        // データありの場合
        if (resultDto.get(0).getReturnCode() != NaikaraTalkConstants.RETURN_CD_DATA_NO) {

            dtoResult = resultDto.get(0);
        }

        return dtoResult;
    }

    /**
     * 利用者処理<br>
     * <br>
     * 利用者を処理<br>
     * <br>
     * @param UserMstDto 画面のパラメータ<br>
     * @return UserMstDto<br>
     * @exception NaiException
     */
    public UserMstDto selectUser(UserMstDto dto) throws NaiException {

        // DAOの初期化
        UserMstDao dao = new UserMstDao(this.conn);

        // 並び順:利用者ID の昇順
        OrderCondition orderBy = new OrderCondition();
        orderBy.addCondition(UserMstDao.COND_USER_ID, OrderCondition.ASC);

        // 検索を行う
        ArrayList<UserMstDto> resultDto = (ArrayList<UserMstDto>) dao.search(
                setUserConditions(dto), orderBy);

        // DTOの初期化
        UserMstDto dtoResult = new UserMstDto();

        // データありの場合
        if (resultDto.get(0).getReturnCode() != NaikaraTalkConstants.RETURN_CD_DATA_NO) {

            dtoResult = resultDto.get(0);
        }

        return dtoResult;
    }

    /**
     * 検索条件設定<br>
     * <br>
     * 検索条件を設定<br>
     * <br>
     * @param TeacherMstDto 画面のパラメータ<br>
     * @return ConditionMapper 検索条件<br>
     * @exception NaiException
     */
    private ConditionMapper setConditions(TeacherMstDto dto)
            throws NaiException {

        // 検索条件の初期化
        ConditionMapper conditions = new ConditionMapper();

        // 利用者ID
        conditions.addCondition(TeacherMstDao.COND_USER_ID,
                ConditionMapper.OPERATOR_EQUAL, dto.getUserId());

        return conditions;
    }

    /**
     * 受講者検索条件設定<br>
     * <br>
     * 受講者検索条件を設定<br>
     * <br>
     * @param StudentMstDto 画面のパラメータ<br>
     * @return ConditionMapper 検索条件<br>
     * @exception NaiException
     */
    private ConditionMapper setStudentConditions(StudentMstDto dto)
            throws NaiException {

        // 検索条件の初期化
        ConditionMapper conditions = new ConditionMapper();

        // 受講者ID
        conditions.addCondition(StudentMstDao.COND_STUDENT_ID,
                ConditionMapper.OPERATOR_EQUAL, dto.getStudentId());

        return conditions;
    }

    /**
     * 利用者検索条件設定<br>
     * <br>
     * 利用者検索条件を設定<br>
     * <br>
     * @param UserMstDto 画面のパラメータ<br>
     * @return ConditionMapper 検索条件<br>
     * @exception NaiException
     */
    private ConditionMapper setUserConditions(UserMstDto dto)
            throws NaiException {

        // 検索条件の初期化
        ConditionMapper conditions = new ConditionMapper();

        // 利用者ID
        conditions.addCondition(UserMstDao.COND_USER_ID,
                ConditionMapper.OPERATOR_EQUAL, dto.getUserId());

        return conditions;
    }

    /**
     * コード取得<br>
     * <br>
     * コード管理マスタキャッシュからデータ取得処理<br>
     * <br>
     * @param String 汎用コード<br>
     * @return LinkedHashMap 取得結果<br>
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