package com.naikara_talk.logic;

import java.sql.Connection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import com.naikara_talk.dao.LessonCommentTrnDao;
import com.naikara_talk.dao.TeacherBookmarkDao;
import com.naikara_talk.dao.TeacherMstDao;
import com.naikara_talk.dbutil.ConditionMapper;
import com.naikara_talk.dbutil.OrderCondition;
import com.naikara_talk.dto.CodeManagMstDto;
import com.naikara_talk.dto.LessonCommentTrnDto;
import com.naikara_talk.dto.TeacherBookmarkMstDto;
import com.naikara_talk.dto.TeacherMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.mstcache.CodeManagMstCache;
import com.naikara_talk.sessiondata.SessionUser;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>お客様(個人)_受講処理<br>
 * <b>クラス名称       :</b>講師評価画面<br>
 * <b>クラス概要       :</b>講師評価画面Logic<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/10 TECS 新規作成
 */
public class TeacherEvaluationLogic {

    // コネクション変数
    public Connection conn = null;

    // コンストラクタ
    public TeacherEvaluationLogic(Connection con) {
        this.conn = con;
    }

    /**
     * データの存在チェック。<br>
     * <br>
     * データの存在チェック。<br>
     * <br>
     * @param LessonCommentTrnDto 画面のパラメータ<br>
     * @return boolean<br>
     * @exception NaiException
     */
    public int getExist(LessonCommentTrnDto dto) throws NaiException {

        LessonCommentTrnDao dao = new LessonCommentTrnDao(this.conn);

        OrderCondition orderBy = new OrderCondition();

        List<LessonCommentTrnDto> resultDto = dao.search(SetConditionsLessonCommentTrn(dto), orderBy);

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
     * レッスンコメントテーブル検索処理<br>
     * <br>
     * レッスンコメントテーブル検索処理<br>
     * <br>
     * @param LessonCommentTrnDto <br>
     * @return LessonCommentTrnDto <br>
     * @exception NaiException
     */
    public LessonCommentTrnDto searchLessonCommentTrn(LessonCommentTrnDto dto) throws NaiException {

        LessonCommentTrnDao dao = new LessonCommentTrnDao(this.conn);

        LessonCommentTrnDto updateDto = null;

        OrderCondition orderBy = new OrderCondition();

        List<LessonCommentTrnDto> resultDtoList = dao.search(SetConditionsLessonCommentTrn(dto), orderBy);
        if (NaikaraTalkConstants.RETURN_CD_DATA_YES == resultDtoList.get(0).getReturnCode()) {
            updateDto = resultDtoList.get(0);
        }
        return updateDto;
    }

    /**
     * レッスンコメントテーブル登録処理<br>
     * <br>
     * レッスンコメントテーブル登録処理<br>
     * <br>
     * @param LessonCommentTrnDto <br>
     * @return int <br>
     * @exception NaiException
     */
    public int insertLessonCommentTrn(LessonCommentTrnDto dto) throws NaiException {
        LessonCommentTrnDao dao = new LessonCommentTrnDao(this.conn);
        return dao.insert(dto);
    }

    /**
     * レッスンコメントテーブル更新処理<br>
     * <br>
     * レッスンコメントテーブル更新処理<br>
     * <br>
     * @param LessonCommentTrnDto <br>
     * @return int <br>
     * @exception NaiException
     */
    public int updateLessonCommentTrn(LessonCommentTrnDto dto) throws NaiException {
        LessonCommentTrnDao dao = new LessonCommentTrnDao(this.conn);
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

        List<TeacherMstDto> resultDtoList = dao.search(SetConditionsTeacherMst(dto), orderBy);

        if (NaikaraTalkConstants.RETURN_CD_DATA_YES == resultDtoList.get(0).getReturnCode()) {
            updateDto = resultDtoList.get(0);
        }
        return updateDto;
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
        String updateCd = ((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString())).getUserId();
        dto.setUpdateCd(updateCd);
        return dao.update(dto);
    }

    /**
     * 受講者別講師ブックマークテーブル登録処理<br>
     * <br>
     * 受講者別講師ブックマークテーブル登録処理<br>
     * <br>
     * @param TeacherBookmarkMstDto <br>
     * @return int <br>
     * @exception NaiException
     */
    public int insertTeacherBookmark(String studentId, String userId) throws NaiException {
        TeacherBookmarkDao dao = new TeacherBookmarkDao(this.conn);
        String sessionID = ((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString())).getUserId();
        TeacherBookmarkMstDto dto = new TeacherBookmarkMstDto();
        dto.setStudentId(studentId);
        dto.setUserId(userId);
        dto.setInsertCd(sessionID);
        dto.setUpdateCd(sessionID);
        return dao.insert(dto);
    }

    /**
     * レッスンコメントテーブル検索条件の設定。<br>
     * <br>
     * レッスンコメントテーブル検索条件の設定。<br>
     * <br>
     * @param LessonCommentTrnDto 画面のパラメータ<br>
     * @return ConditionMapper:conditions 検索条件<br>
     * @exception NaiException
     */
    private ConditionMapper SetConditionsLessonCommentTrn(LessonCommentTrnDto dto) throws NaiException {

        // 検索条件の作成
        ConditionMapper conditions = new ConditionMapper();
        // 予約番号
        conditions.addCondition(LessonCommentTrnDao.RESERVATION_NO, ConditionMapper.OPERATOR_EQUAL,
                dto.getReservationNo());
        // コメント入力者区分
        conditions.addCondition(LessonCommentTrnDao.CMT_INPUTS_KBN, ConditionMapper.OPERATOR_EQUAL,
                dto.getCmtInputsKbn());
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
    private ConditionMapper SetConditionsTeacherMst(TeacherMstDto dto) throws NaiException {

        // 検索条件の作成
        ConditionMapper conditions = new ConditionMapper();
        // 利用者ID　
        conditions.addCondition(TeacherMstDao.COND_USER_ID, ConditionMapper.OPERATOR_EQUAL, dto.getUserId());
        // 戻り値
        return conditions;
    }
}
