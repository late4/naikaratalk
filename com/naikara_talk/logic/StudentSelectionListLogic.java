package com.naikara_talk.logic;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dao.StudentMstDao;
import com.naikara_talk.dbutil.ConditionMapper;
import com.naikara_talk.dbutil.OrderCondition;
import com.naikara_talk.dto.CodeManagMstDto;
import com.naikara_talk.dto.StudentMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.mstcache.CodeManagMstCache;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>お客様(個人)_初期登録。<br>
 * <b>クラス名称　　　:</b>マイページ(お客様_個人)Logicクラス。<br>
 * <b>クラス概要　　　:</b>マイページ(お客様_個人)Logic。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/07/31 TECS 新規作成。
 */
public class StudentSelectionListLogic {

    public Connection conn = null;

    public StudentSelectionListLogic(Connection con) {

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
    public int getRowCount(StudentMstDto dto) throws NaiException {

        // DAOの初期化
        StudentMstDao dao = new StudentMstDao(this.conn);

        // データ件数を取得
        int dataCount = dao.rowCount(NaikaraTalkConstants.STUDENT_MST, setConditions(dto), dto);

        // 戻り値
        return dataCount;
    }

    /**
     * 選択遷移<br>
     * <br>
     * 選択遷移の検索処理<br>
     * <br>
     * @param dto 検索条件<br>
     * @return dtoResult 検索結果<br>
     * @exception NaiException
     */
    public StudentMstDto select(StudentMstDto dto) throws NaiException {

        // DAOの初期化
        StudentMstDao dao = new StudentMstDao(this.conn);

        // 並び順:フリガナ(姓) の昇順
        // 並び順:フリガナ(名) の昇順
        OrderCondition orderBy = new OrderCondition();
        orderBy.addCondition(StudentMstDao.COND_FAMILY_KNM, OrderCondition.ASC);
        orderBy.addCondition(StudentMstDao.COND_FIRST_KNM, OrderCondition.ASC);

        // 検索を行う
        List<StudentMstDto> resultDto = dao.searchWithKnm(setConditions(dto), orderBy, new StudentMstDto());

        // DTOの初期化
        StudentMstDto dtoResult = new StudentMstDto();

        // データありの場合
        if (resultDto.get(0).getReturnCode() != NaikaraTalkConstants.RETURN_CD_DATA_NO) {

            dtoResult = resultDto.get(0);
        }

        return dtoResult;
    }

    /**
     * 受講者リスト取得<br>
     * <br>
     * 受講者リスト取得を行う<br>
     * <br>
     * @param dto 検索条件<br>
     * @return list 検索結果<br>
     * @exception Exception
     */
    public List<StudentMstDto> selectList(StudentMstDto dto) throws NaiException {

        // 検索結果の初期化
        List<StudentMstDto> list = new ArrayList<StudentMstDto>();

        // DAOの初期化
        StudentMstDao dao = new StudentMstDao(this.conn);

        // 並び順:フリガナ(姓) の昇順
        // 並び順:フリガナ(名) の昇順
        OrderCondition orderBy = new OrderCondition();
        orderBy.addCondition(StudentMstDao.COND_FAMILY_KNM, OrderCondition.ASC);
        orderBy.addCondition(StudentMstDao.COND_FIRST_KNM, OrderCondition.ASC);

        // 一覧データを取得する
        list = dao.searchWithKnm(setConditions(dto), orderBy, dto);

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
    private ConditionMapper setConditions(StudentMstDto dto) throws NaiException {

        // 検索条件の初期化
        ConditionMapper conditions = new ConditionMapper();

        // 顧客区分は「全て」ではない場合
        if (!StringUtils.isEmpty(dto.getCustomerKbn())) {

            if (!StringUtils.equals(NaikaraTalkConstants.CHOICE_ALL_ZERO, dto.getCustomerKbn())) {

                conditions.addCondition(StudentMstDao.COND_CUSTOMER_KBN, ConditionMapper.OPERATOR_EQUAL,
                        dto.getCustomerKbn());
            }
        }

        StringBuffer sb = new StringBuffer();

        // 受講者IDが入力されている場合
        if (!StringUtils.isEmpty(dto.getStudentId())) {

            sb.setLength(0);
            sb.append(NaikaraTalkConstants.OPERATOR_PERCENT).append(dto.getStudentId())
                    .append(NaikaraTalkConstants.OPERATOR_PERCENT);

            conditions.addCondition(StudentMstDao.COND_STUDENT_ID, ConditionMapper.OPERATOR_LIKE, sb.toString());
        }

        // ニックネームが入力されている場合
        if (!StringUtils.isEmpty(dto.getNickNm())) {

            sb.setLength(0);
            sb.append(NaikaraTalkConstants.OPERATOR_PERCENT).append(dto.getNickNm())
                    .append(NaikaraTalkConstants.OPERATOR_PERCENT);

            conditions.addCondition(StudentMstDao.COND_NICK_NM, ConditionMapper.OPERATOR_LIKE, sb.toString());
        }

        // 組織名が入力されている場合
        if (!StringUtils.isEmpty(dto.getOrganizationNm())) {

            sb.setLength(0);
            sb.append(NaikaraTalkConstants.OPERATOR_PERCENT).append(dto.getOrganizationNm())
                    .append(NaikaraTalkConstants.OPERATOR_PERCENT);

            conditions.addCondition(StudentMstDao.COND_ORGANIZATION_NM, ConditionMapper.OPERATOR_LIKE, sb.toString());
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