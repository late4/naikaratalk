package com.naikara_talk.logic;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;

import com.naikara_talk.dao.OrganizationMstDao;
import com.naikara_talk.dao.StudentMstDao;
import com.naikara_talk.dbutil.ConditionMapper;
import com.naikara_talk.dbutil.OrderCondition;
import com.naikara_talk.dto.CodeManagMstDto;
import com.naikara_talk.dto.OrganizationMstDto;
import com.naikara_talk.dto.StudentMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.mstcache.CodeManagMstCache;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>お客様(個人)_初期登録<br>
 * <b>クラス名称       :</b>問合せ画面Logicクラス。<br>
 * <b>クラス概要       :</b>メール送信をおこなう。<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/30 TECS 新規作成
 */
public class ContactMailSendLogic {

    // コネクション変数
    public Connection conn = null;

    // コンストラクタ
    public ContactMailSendLogic(Connection con) {
        this.conn = con;
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
    public StudentMstDto selectStudent(StudentMstDto dto) throws NaiException {

        StudentMstDao dao = new StudentMstDao(this.conn);

        // 並び順:受講者ＩＤ の昇順
        OrderCondition orderBy = new OrderCondition();
        orderBy.addCondition(StudentMstDao.COND_STUDENT_ID, OrderCondition.ASC);
        ArrayList<StudentMstDto> resultDto = (ArrayList<StudentMstDto>) dao.search(setStudentConditions(dto), orderBy);
        StudentMstDto dtoResult = new StudentMstDto();
        // 設定
        if (NaikaraTalkConstants.RETURN_CD_DATA_YES < resultDto.size()) {
            dtoResult = resultDto.get(0);
        }

        return dtoResult;

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
    public OrganizationMstDto selectOrganization(OrganizationMstDto dto) throws NaiException {

        OrganizationMstDao dao = new OrganizationMstDao(this.conn);

        // 並び順:受講者ＩＤ の昇順
        OrderCondition orderBy = new OrderCondition();
        orderBy.addCondition(OrganizationMstDao.COND_ORGANIZATION_ID, OrderCondition.ASC);
        ArrayList<OrganizationMstDto> resultDto = (ArrayList<OrganizationMstDto>) dao.search(
                setOrganizationConditions(dto), orderBy);
        OrganizationMstDto dtoResult = new OrganizationMstDto();
        // 設定
        if (0 < resultDto.size()) {
            dtoResult = resultDto.get(0);
        }

        return dtoResult;

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
    private ConditionMapper setStudentConditions(StudentMstDto dto) throws NaiException {

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
     * @param StudentMstDto 画面のパラメータ<br>
     * @return ConditionMapper:conditions 検索条件<br>
     * @exception NaiException
     */
    private ConditionMapper setOrganizationConditions(OrganizationMstDto dto) throws NaiException {

        // 検索条件の作成
        ConditionMapper conditions = new ConditionMapper();

        // 組織ID
        conditions.addCondition(OrganizationMstDao.COND_ORGANIZATION_ID, ConditionMapper.OPERATOR_EQUAL,
                dto.getOrganizationId());

        // 連番
        conditions.addCondition(OrganizationMstDao.COND_CONS_SEQ, ConditionMapper.OPERATOR_EQUAL, dto.getConsSeq());

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

    /**
     * データの存在チェック<br>
     * <br>
     * データの存在チェックを行う<br>
     * <br>
     * @param dto チェックデータ<br>
     * @return boolean チェック結果<br>
     * @exception NaiException
     */
    public int studentExists(StudentMstDto dto) throws NaiException {

        // DAOの初期化
        StudentMstDao dao = new StudentMstDao(this.conn);

        // 並び順:受講者ＩＤ の昇順
        OrderCondition orderBy = new OrderCondition();
        orderBy.addCondition(StudentMstDao.COND_STUDENT_ID, OrderCondition.ASC);

        // 検索を行う
        ArrayList<StudentMstDto> resultDto = (ArrayList<StudentMstDto>) dao.search(setStudentConditions(dto), orderBy);

        return resultDto.get(0).getReturnCode();

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
    public int organizationExists(OrganizationMstDto dto) throws NaiException {

        // DAOの初期化
        OrganizationMstDao dao = new OrganizationMstDao(this.conn);

        // 並び順:組織ＩＤ の昇順
        OrderCondition orderBy = new OrderCondition();
        orderBy.addCondition(OrganizationMstDao.COND_ORGANIZATION_ID, OrderCondition.ASC);

        // 検索を行う
        ArrayList<OrganizationMstDto> resultDto = (ArrayList<OrganizationMstDto>) dao.search(
                setOrganizationConditions(dto), orderBy);

        return resultDto.get(0).getReturnCode();

    }

}
