package com.naikara_talk.logic;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;

import com.naikara_talk.dao.LessonReservationPerformanceTrnDao;
import com.naikara_talk.dao.OrganizationMstDao;
import com.naikara_talk.dao.PointOwnershipTrnDao;
import com.naikara_talk.dao.StudentMstDao;
import com.naikara_talk.dao.TableCountDao;
import com.naikara_talk.dbutil.ConditionMapper;
import com.naikara_talk.dbutil.OrderCondition;
import com.naikara_talk.dto.CodeManagMstDto;
import com.naikara_talk.dto.OrganizationMstDto;
import com.naikara_talk.dto.StudentMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.mstcache.CodeManagMstCache;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>組織_初期処理。<br>
 * <b>クラス名称　　　:</b>システム受講者登録(単票)Logicクラス。<br>
 * <b>クラス概要　　　:</b>組織の社員情報(受講者)の新規アカウント(単票)。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/07/15 TECS 新規作成。
 */
public class OrganizationStudentMstLogic {

    // コネクション変数
    public Connection conn = null;

    // コンストラクタ
    public OrganizationStudentMstLogic(Connection con) {
        this.conn = con;
    }

    /**
     * 検索データ件数取得。<br>
     * <br>
     * 検索データ件数取得。<br>
     * <br>
     * @param StudentMstDto:dto
     * @return int:DataCount
     * @throws NaiException
     */
    public int getRowCountLesson(StudentMstDto dto) throws NaiException {

        // 初期化処理
        TableCountDao dao = new TableCountDao(this.conn);

        // 一覧のデータ件数を取得する
        int DataCount = dao.rowCount(NaikaraTalkConstants.LESSON_RESERVATION_PERFORMANCE_TRN, SetLessonConditions(dto));

        // 戻り値
        return DataCount;

    }

    /**
     * 検索データ件数取得。<br>
     * <br>
     * 検索データ件数取得。<br>
     * <br>
     * @param StudentMstDto:dto
     * @return int:DataCount
     * @throws NaiException
     */
    public int getRowCountPoint(StudentMstDto dto) throws NaiException {

        // 初期化処理
        TableCountDao dao = new TableCountDao(this.conn);

        // 一覧のデータ件数を取得する
        int DataCount = dao.rowCount(NaikaraTalkConstants.POINT_OWNERSHIP_TRN, SetPointConditions(dto));

        // 戻り値
        return DataCount;

    }

    /**
     * 初期表示の検索処理。<br>
     * <br>
     * 初期表示の検索処理。<br>
     * <br>
     * @param StudentMstDto 画面のパラメータ<br>
     * @return StudentMstDto<br>
     * @exception NaiException
     */
    public StudentMstDto select(StudentMstDto dto) throws NaiException {

        StudentMstDao dao = new StudentMstDao(this.conn);

        // 並び順:受講者ＩＤ の昇順
        OrderCondition orderBy = new OrderCondition();
        orderBy.addCondition(StudentMstDao.COND_STUDENT_ID, OrderCondition.ASC);
        ArrayList<StudentMstDto> resultDto = (ArrayList<StudentMstDto>) dao.searchWithKnm(SetConditions(dto), orderBy,
                new StudentMstDto());
        StudentMstDto dtoResult = new StudentMstDto();

        // データありの場合
        if (resultDto.get(0).getReturnCode() == NaikaraTalkConstants.RETURN_CD_DATA_YES) {

            dtoResult = resultDto.get(0);
        }

        return dtoResult;

    }

    /**
     * 登録処理。<br>
     * <br>
     * 登録処理。<br>
     * <br>
     * @param StudentMstDto 画面のパラメータ<br>
     * @return int<br>
     * @exception NaiException
     */
    public int insert(StudentMstDto dto) throws NaiException {
        StudentMstDao dao = new StudentMstDao(this.conn);
        return dao.insert(dto);
    }

    /**
     * 更新処理。<br>
     * <br>
     * 更新処理。<br>
     * <br>
     * @param StudentMstDto 画面のパラメータ<br>
     * @return int<br>
     * @exception NaiException
     */
    public int update(StudentMstDto dto) throws NaiException {
        StudentMstDao dao = new StudentMstDao(this.conn);
        return dao.update(dto);
    }

    /**
     * 削除処理。<br>
     * <br>
     * 削除処理。<br>
     * <br>
     * @param StudentMstDto 画面のパラメータ<br>
     * @return int<br>
     * @exception NaiException
     */
    public int delete(StudentMstDto dto) throws NaiException {
        StudentMstDao dao = new StudentMstDao(this.conn);
        return dao.delete(dto);
    }

    /**
     * データの存在チェック<br>
     * <br>
     * データの存在チェックを行う<br>
     * <br>
     * @param dto チェックデータ<br>
     * @return boolean チェック結果<br>
     * @exception NaiException
     */
    public int isExists(StudentMstDto dto) throws NaiException {

        // DAOの初期化
        StudentMstDao dao = new StudentMstDao(this.conn);

        // 並び順:受講者ＩＤ の昇順
        OrderCondition orderBy = new OrderCondition();
        orderBy.addCondition(StudentMstDao.COND_STUDENT_ID, OrderCondition.ASC);

        // 検索を行う
        ArrayList<StudentMstDto> resultDto = (ArrayList<StudentMstDto>) dao.searchWithKnm(SetConditions(dto), orderBy,
                new StudentMstDto());

        return resultDto.get(0).getReturnCode();

    }

    /**
     * 組織マスタの検索。<br>
     * <br>
     * 組織マスタの検索。<br>
     * <br>
     * @param OrganizationMstDto
     * @return OrganizationMstDto
     * @throws NaiException
     */
    public OrganizationMstDto selectOrganizationMst(OrganizationMstDto dto) throws NaiException {

        OrganizationMstDao dao = new OrganizationMstDao(this.conn);

        OrderCondition orderCondition = new OrderCondition();
        ArrayList<OrganizationMstDto> resultDto = dao.searchOrganizationMst(SetOrganizationMstConditions(dto),
                orderCondition);
        OrganizationMstDto dtoResult = new OrganizationMstDto();
        // 設定
        if (0 < resultDto.size()) {
            dtoResult = resultDto.get(0);
        }

        return dtoResult;

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
     * @param StudentMstDto 画面のパラメータ<br>
     * @return ConditionMapper:conditions 検索条件<br>
     * @exception NaiException
     */
    private ConditionMapper SetConditions(StudentMstDto dto) throws NaiException {

        // 検索条件の作成
        ConditionMapper conditions = new ConditionMapper();

        // 受講者ID
        conditions.addCondition(StudentMstDao.COND_STUDENT_ID, ConditionMapper.OPERATOR_EQUAL, dto.getStudentId());

        // 戻り値
        return conditions;

    }

    /**
     * 検索条件の設定。<br>
     * <br>
     * 検索条件の設定。<br>
     * <br>
     * @param OrganizationMstDto 画面のパラメータ<br>
     * @return ConditionMapper:conditions 検索条件<br>
     * @exception NaiException
     */
    private ConditionMapper SetOrganizationMstConditions(OrganizationMstDto dto) throws NaiException {

        // 検索条件の作成
        ConditionMapper conditions = new ConditionMapper();

        // 組織ID
        conditions.addCondition(OrganizationMstDao.COND_ORGANIZATION_ID, ConditionMapper.OPERATOR_EQUAL,
                dto.getOrganizationId());

        // 戻り値
        return conditions;

    }

    /**
     * 検索条件の設定。<br>
     * <br>
     * 検索条件の設定。<br>
     * <br>
     * @param StudentMstDto 画面のパラメータ<br>
     * @return ConditionMapper:conditions 検索条件<br>
     * @exception NaiException
     */
    private ConditionMapper SetLessonConditions(StudentMstDto dto) throws NaiException {

        // 検索条件の作成
        ConditionMapper conditions = new ConditionMapper();

        // 受講者ID
        conditions.addCondition(LessonReservationPerformanceTrnDao.COND_STUDENT_ID, ConditionMapper.OPERATOR_EQUAL,
                dto.getStudentId());
        // 状態区分
        conditions.addCondition(LessonReservationPerformanceTrnDao.COND_STATE_KBN, ConditionMapper.OPERATOR_EQUAL,
                NaikaraTalkConstants.STATE_KBN);
        // レッスン日
        conditions.addCondition(LessonReservationPerformanceTrnDao.COND_LESSON_DT,
                ConditionMapper.OPERATOR_LARGER_THAN, dto.getUseEndDt());

        // 戻り値
        return conditions;

    }

    /**
     * 検索条件の設定。<br>
     * <br>
     * 検索条件の設定。<br>
     * <br>
     * @param StudentMstDto 画面のパラメータ<br>
     * @return ConditionMapper:conditions 検索条件<br>
     * @exception NaiException
     */
    private ConditionMapper SetPointConditions(StudentMstDto dto) throws NaiException {

        // 検索条件の作成
        ConditionMapper conditions = new ConditionMapper();

        // 受講者ID
        conditions.addCondition(PointOwnershipTrnDao.COND_STUDENT_ID, ConditionMapper.OPERATOR_EQUAL,
                dto.getStudentId());

        // 戻り値
        return conditions;

    }

}
