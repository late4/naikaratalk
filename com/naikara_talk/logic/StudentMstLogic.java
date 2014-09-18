package com.naikara_talk.logic;

import java.sql.Connection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dao.PointOwnershipTrnDao;
import com.naikara_talk.dao.StudentMstDao;
import com.naikara_talk.dao.UserMstDao;
import com.naikara_talk.dbutil.ConditionMapper;
import com.naikara_talk.dbutil.OrderCondition;
import com.naikara_talk.dto.CodeManagMstDto;
import com.naikara_talk.dto.PointOwnershipTrnDto;
import com.naikara_talk.dto.StudentMstDto;
import com.naikara_talk.dto.UserMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.mstcache.CodeManagMstCache;
import com.naikara_talk.service.StudentMstLoadService;
import com.naikara_talk.util.DateUtil;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>マスタ保守<br>
 * <b>クラス名称　　　:</b>受講者マスタメンテナンス【単票】Logicクラス。<br>
 * <b>クラス概要　　　:</b>受講者マスタメンテナンス【単票】Logic。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/07/30 TECS 新規作成。
 */
public class StudentMstLogic {

    // コネクション変数
    public Connection conn = null;

    // コンストラクタ
    public StudentMstLogic(Connection con) {
        this.conn = con;
    }

    /**
     * 初期表示の検索処理。<br>
     * <br>
     * @param StudentMstDto
     *            画面のパラメータ
     * @return StudentMstDto
     * @throws NaiException
     */
    public StudentMstDto select(StudentMstDto dto) throws NaiException {

        StudentMstDao dao = new StudentMstDao(this.conn);

        // 並び順
        OrderCondition orderBy = new OrderCondition();

        List<StudentMstDto> resultDto = dao.search(setConditions(dto), orderBy);

        StudentMstDto dtoResult = new StudentMstDto();

        // 設定
        if (NaikaraTalkConstants.RETURN_CD_DATA_YES == resultDto.get(0).getReturnCode()) {
            dtoResult = resultDto.get(0);
        }

        return dtoResult;

    }

    /**
     * 更新前の検索処理。<br>
     * <br>
     * @param PointOwnershipTrnDto
     *            画面のパラメータ
     * @return PointOwnershipTrnDto
     * @throws NaiException
     */
    public PointOwnershipTrnDto selectBeforUpd(PointOwnershipTrnDto dto) throws NaiException {

        PointOwnershipTrnDao dao = new PointOwnershipTrnDao(this.conn);

        // 並び順
        OrderCondition orderBy = new OrderCondition();

        List<PointOwnershipTrnDto> resultDto = dao.search(setConditionsForUpdSpe(dto), orderBy);

        PointOwnershipTrnDto dtoResult = new PointOwnershipTrnDto();

        // 設定
        if (NaikaraTalkConstants.RETURN_CD_DATA_YES == resultDto.get(0).getReturnCode()) {
            dtoResult = resultDto.get(0);
        }

        return dtoResult;

    }

    /**
     * 初期表示の検索処理。<br>
     * <br>
     * @param StudentMstDto
     *            画面のパラメータ
     * @return TimeZoneControlMstDto
     * @throws NaiException
     */
    public int selectPointOwnershipTrnDto(PointOwnershipTrnDto dto) throws NaiException {

        PointOwnershipTrnDao dao = new PointOwnershipTrnDao(this.conn);

        // 並び順:商品コード の昇順
        OrderCondition orderBy = new OrderCondition();

        List<PointOwnershipTrnDto> resultDto = dao.search(setConditionsForFeeKbn(dto), orderBy);
        if (resultDto.get(0).getReturnCode() == NaikaraTalkConstants.RETURN_CD_DATA_NO) {
            return StudentMstLoadService.DATA_COUNT_MIN;
        } else {
            return resultDto.size();
        }

    }

    /**
     * 初期表示の検索処理。<br>
     * <br>
     * @param UserMstDto
     *            画面のパラメータ
     * @return UserMstDto
     * @throws NaiException
     */
    public UserMstDto selectUserMst(UserMstDto dto) throws NaiException {

        UserMstDao dao = new UserMstDao(this.conn);

        // 並び順:商品コード の昇順
        OrderCondition orderBy = new OrderCondition();

        List<UserMstDto> resultDto = dao.search(setConditionsForUserRole(dto), orderBy);

        UserMstDto dtoResult = new UserMstDto();

        // 設定
        if (NaikaraTalkConstants.RETURN_CD_DATA_YES == resultDto.get(0).getReturnCode()) {
            dtoResult = resultDto.get(0);
        }
        return dtoResult;
    }

    /**
     * 受講者マスタ登録処理。<br>
     * <br>
     * @param StudentMstDto
     *            画面のパラメータ
     * @return なし
     * @throws NaiException
     */
    public int insert(StudentMstDto dto) throws NaiException {
        StudentMstDao dao = new StudentMstDao(this.conn);
        return dao.insert(dto);
    }

    /**
     * ポイント所有テーブル登録処理。<br>
     * <br>
     * @param PointOwnershipTrnDto
     *            画面のパラメータ
     * @return なし
     * @throws NaiException
     */
    public int insertPointOwnershipTrn(PointOwnershipTrnDto dto) throws NaiException {
        PointOwnershipTrnDao dao = new PointOwnershipTrnDao(this.conn);
        return dao.insert(dto);
    }

    /**
     * 更新処理。<br>
     * <br>
     * @param StudentMstDto
     *            画面のパラメータ
     * @return int
     * @throws NaiException
     */
    public int update(StudentMstDto dto) throws NaiException {
        StudentMstDao dao = new StudentMstDao(this.conn);
        return dao.update(dto);
    }

    /**
     * 更新処理(ポイン所有テーブル)。<br>
     * <br>
     * @param PointOwnershipTrnDto
     *            画面のパラメータ
     * @return int
     * @throws NaiException
     */
    public int update(PointOwnershipTrnDto dto) throws NaiException {
        PointOwnershipTrnDao dao = new PointOwnershipTrnDao(this.conn);
        return dao.update(dto);
    }

    /**
     * 更新処理(ポイン所有テーブル：月謝の停止)。<br>
     * <br>
     * @param PointOwnershipTrnDto
     *            画面のパラメータ
     * @return int
     * @throws NaiException
     */
    public int updateMonthly(PointOwnershipTrnDto dto) throws NaiException {
        PointOwnershipTrnDao dao = new PointOwnershipTrnDao(this.conn);
        return dao.updateMonthly(dto);
    }

    /**
     * ポイン所有テーブル削除処理。<br>
     * <br>
     * @param PointOwnershipTrnDto
     *            画面のパラメータ
     * @return なし
     * @throws NaiException
     */
    public int deleteForStuSpe(PointOwnershipTrnDto dto) throws NaiException {
        PointOwnershipTrnDao dao = new PointOwnershipTrnDao(this.conn);
        return dao.delete(dto);
    }

    /**
     * データの存在チェック。<br>
     * <br>
     * @param StudentMstDto
     *            画面のパラメータ
     * @return int
     * @throws NaiException
     */
    public int getExist(StudentMstDto dto) throws NaiException {

        StudentMstDao dao = new StudentMstDao(this.conn);

        // 並び順:国コード の昇順、時差地域No の昇順

        OrderCondition orderBy = new OrderCondition();
        orderBy.addCondition(StudentMstDao.COND_STUDENT_ID, OrderCondition.ASC);

        List<StudentMstDto> resultDto = dao.search(setConditions(dto), orderBy);

        return resultDto.get(0).getReturnCode();

    }

    /**
     * コード管理マスタキャッシュからデータ取得処理。<br>
     * <br>
     * @param String
     *            汎用コード
     * @return LinkedHashMap<String, String>
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
     * 検索条件の設定。<br>
     * <br>
      * @param StudentMstDto
      * @return ConditionMapper:conditions
      * @throws NaiException
     */
    private ConditionMapper setConditions(StudentMstDto dto) throws NaiException {

        // 検索条件の作成
        ConditionMapper conditions = new ConditionMapper();

        // 受講者IDを入力されている場合
        if (!StringUtils.isEmpty(dto.getStudentId())) {
            conditions.addCondition(StudentMstDao.COND_STUDENT_ID, ConditionMapper.OPERATOR_EQUAL, dto.getStudentId());

        } else {
            conditions.addCondition(StudentMstDao.COND_STUDENT_ID, ConditionMapper.OPERATOR_EQUAL,
                    NaikaraTalkConstants.BRANK);
        }

        // 戻り値
        return conditions;

    }

    /**
     * 検索条件の設定。<br>
     * <br>
      * @param StudentMstDto
      * @return ConditionMapper:conditions
      * @throws NaiException
     */
    private ConditionMapper setConditionsForUpdSpe(PointOwnershipTrnDto dto) throws NaiException {

        // 検索条件の作成
        ConditionMapper conditions = new ConditionMapper();

        // 所有IDを入力されている場合
        conditions.addCondition(PointOwnershipTrnDao.COND_OWNERSHIP_ID, ConditionMapper.OPERATOR_EQUAL,
                dto.getOwnershipId());

        // 有償無償区分を入力されている場合
        conditions.addCondition(PointOwnershipTrnDao.COND_COMPENSATION_FREE_KBN, ConditionMapper.OPERATOR_EQUAL,
                dto.getCompensationFreeKbn());

        // 戻り値
        return conditions;

    }

    /**
     * 検索条件の設定。<br>
     * <br>
      * @param StudentMstDto
      * @return ConditionMapper:conditions
      * @throws NaiException
     */
    private ConditionMapper setConditionsForFeeKbn(PointOwnershipTrnDto dto) throws NaiException {

        // 検索条件の作成
        ConditionMapper conditions = new ConditionMapper();

        // 受講者IDを入力されている場合
        conditions.addCondition(PointOwnershipTrnDao.COND_STUDENT_ID, ConditionMapper.OPERATOR_EQUAL,
                dto.getStudentId());

        // 有効終了日
        conditions.addCondition(PointOwnershipTrnDao.COND_EFFECTIVE_END_DT, ConditionMapper.OPERATOR_LARGE_EQUAL,
                DateUtil.getSysDate());

        // 通常月謝区 = 月謝
        conditions.addCondition(PointOwnershipTrnDao.COND_FEE_KBN, ConditionMapper.OPERATOR_EQUAL,
                NaikaraTalkConstants.FEE_KBN_MONTHLY);

        // 戻り値
        return conditions;

    }

    /**
     * 検索条件の設定。<br>
     * <br>
      * @param UserMstDto
      * @return ConditionMapper:conditions
      * @throws NaiException
     */
    private ConditionMapper setConditionsForUserRole(UserMstDto dto) throws NaiException {

        // 検索条件の作成
        ConditionMapper conditions = new ConditionMapper();

        // 利用者IDを入力されている場合
        conditions.addCondition(UserMstDao.COND_USER_ID, ConditionMapper.OPERATOR_EQUAL, dto.getUserId());

        // 戻り値
        return conditions;

    }
}
