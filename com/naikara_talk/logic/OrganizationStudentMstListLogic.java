package com.naikara_talk.logic;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dao.StudentMstDao;
import com.naikara_talk.dao.UserMstDao;
import com.naikara_talk.dbutil.ConditionMapper;
import com.naikara_talk.dbutil.OrderCondition;
import com.naikara_talk.dto.CodeManagMstDto;
import com.naikara_talk.dto.StudentMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.mstcache.CodeManagMstCache;
import com.naikara_talk.util.DateUtil;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>>組織_初期処理<br>
 * <b>クラス名称       :</b>システム受講者登録(一覧)Logicクラス。<br>
 * <b>クラス概要       :</b>組織の社員情報(受講者)の新規アカウント(一覧)。<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/11 TECS 新規作成
 */
public class OrganizationStudentMstListLogic {

    // コネクション変数
    public Connection conn = null;

    // コンストラクタ
    public OrganizationStudentMstListLogic(Connection con) {
        this.conn = con;
    }

    /**
     * 検索データ件数取得。<br>
     * <br>
     * 検索データ件数取得。<br>
     * <br>
     * @param StudentMstDto:dto<br>
     * @return int:DataCount<br>
     * @exception NaiException
     */
    public int getRowCount(StudentMstDto dto) throws NaiException {

        // DAOの初期化
        StudentMstDao dao = new StudentMstDao(this.conn);

        // 一覧のデータ件数を取得する
        int DataCount = dao.rowCount(NaikaraTalkConstants.STUDENT_MST, SetConditions(dto), dto);

        // 戻り値
        return DataCount;

    }

    /**
     * 検索処理<br>
     * <br>
     * 検索処理<br>
     * <br>
     * @param StudentMstDto 画面のパラメータ<br>
     * @return List<StudentMstDto> 一覧データ <br>
     * @exception Exception
     */
    public List<StudentMstDto> selectList(StudentMstDto dto) throws Exception {

        // 初期化処理
        List<StudentMstDto> list = new ArrayList<StudentMstDto>();
        StudentMstDao dao = new StudentMstDao(this.conn);

        // 並び順:受講者所属部署の昇順、フリガナ(姓)の昇順、フリガナ(名)の昇順、受講者ＩＤの昇順
        OrderCondition orderBy = new OrderCondition();
        orderBy.addCondition(StudentMstDao.COND_STUDENT_POSITION, OrderCondition.ASC);
        orderBy.addCondition(StudentMstDao.COND_FAMILY_KNM, OrderCondition.ASC);
        orderBy.addCondition(StudentMstDao.COND_FIRST_KNM, OrderCondition.ASC);
        orderBy.addCondition(StudentMstDao.COND_STUDENT_ID, OrderCondition.ASC);

        // 一覧データを取得する
        list = dao.searchWithKnm(SetConditions(dto), orderBy, dto);

        // 戻り値
        return list;

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
        StringBuffer work = new StringBuffer();

        // 受講者所属部署入力されている場合
        if (!StringUtils.isEmpty(dto.getStudentPosition())) {
            work.setLength(0);
            work.append(NaikaraTalkConstants.OPERATOR_PERCENT).append(dto.getStudentPosition())
                    .append(NaikaraTalkConstants.OPERATOR_PERCENT);
            conditions
                    .addCondition(StudentMstDao.COND_STUDENT_POSITION, ConditionMapper.OPERATOR_LIKE, work.toString());
        }

        // 所属組織内ID入力されている場合
        if (!StringUtils.isEmpty(dto.getPositionOrganizationId())) {
            work.setLength(0);
            work.append(NaikaraTalkConstants.OPERATOR_PERCENT).append(dto.getPositionOrganizationId())
                    .append(NaikaraTalkConstants.OPERATOR_PERCENT);
            conditions.addCondition(StudentMstDao.COND_POSITION_ORGANIZATION_ID, ConditionMapper.OPERATOR_LIKE,
                    work.toString());
        }

        // 受講者ID入力されている場合
        if (!StringUtils.isEmpty(dto.getStudentId())) {
            work.setLength(0);
            work.append(NaikaraTalkConstants.OPERATOR_PERCENT).append(dto.getStudentId())
                    .append(NaikaraTalkConstants.OPERATOR_PERCENT);
            conditions.addCondition(StudentMstDao.COND_STUDENT_ID, ConditionMapper.OPERATOR_LIKE, work.toString());
        }

        // 組織ＩＤ
        conditions.addCondition(StudentMstDao.COND_ORGANIZATION_ID, ConditionMapper.OPERATOR_EQUAL,
                dto.getOrganizationId());

        // ｢利用状態｣＝利用可 の場合
        if (StringUtils.equals(NaikaraTalkConstants.USE_KBN_OK, dto.getUseKbn())) {
            conditions.addCondition(StudentMstDao.COND_USE_STR_DT, ConditionMapper.OPERATOR_LESS_EQUAL,
                    DateUtil.getSysDate());
            conditions.addCondition(StudentMstDao.COND_USE_END_DT, ConditionMapper.OPERATOR_LARGE_EQUAL,
                    DateUtil.getSysDate());

            //現時点ではまだ利用可能な人が表示されなくなるので、削除
            //conditions.addCondition(StudentMstDao.COND_USE_STOP_FLG, ConditionMapper.OPERATOR_EQUAL, "0");


        }

        // ｢利用状態｣＝利用不可 の場合
        if (StringUtils.equals(NaikaraTalkConstants.USE_KBN_NG, dto.getUseKbn())) {
            conditions.addCondition(StudentMstDao.COND_USE_END_DT,
                    ConditionMapper.OPERATOR_LESS_THAN, DateUtil.getSysDate());

            //conditions.addCondition(StudentMstDao.COND_USE_STOP_FLG, ConditionMapper.OPERATOR_EQUAL,
            //        NaikaraTalkConstants.USE_KBN_NG);

        }

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

}
