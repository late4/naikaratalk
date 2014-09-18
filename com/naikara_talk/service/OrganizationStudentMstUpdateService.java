package com.naikara_talk.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedHashMap;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dbutil.DbUtil;
import com.naikara_talk.dto.OrderNumberDto;
import com.naikara_talk.dto.OrganizationMstDto;
import com.naikara_talk.dto.StudentMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.logic.OrderNumbersMstLogic;
import com.naikara_talk.logic.OrganizationStudentMstLogic;
import com.naikara_talk.logic.PasswordChkLogic;
import com.naikara_talk.model.OrganizationStudentMstModel;
import com.naikara_talk.sessiondata.SessionUser;
import com.naikara_talk.util.DateUtil;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>組織_初期処理<br>
 * <b>クラス名称　　　:</b>システム受講者登録(単票)登録更新Serviceクラス。<br>
 * <b>クラス概要　　　:</b>組織の社員情報(受講者)の新規アカウント(単票)。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/07/15 TECS 新規作成。
 */
public class OrganizationStudentMstUpdateService implements ActionService {

    /** 利用停止状態：”0”：利用可 */
    public static final String USE_STOP_FLG_0 = "0";
    /** 利用停止状態：”0”：利用停止 */
    public static final String USE_STOP_FLG_1 = "1";
    /** 更新前チェック：パスワードの複雑性のチェックエラー */
    public static final int ERR_CHECK_1 = 1;
    /** 更新前チェック：パスワードとパスワード確認の同一チェックエラー */
    public static final int ERR_CHECK_2 = 2;
    /** 更新前チェック：自己負担率の下限チェックエラー */
    public static final int ERR_CHECK_3 = 3;
    /** 更新前チェック：自己負担率の上限チェックエラー */
    public static final int ERR_CHECK_4 = 4;
    /** 更新前チェック：利用停止状態と利用停止日のチェックエラー */
    public static final int ERR_CHECK_5 = 5;
    /** 更新前チェック：利用停止状態と利用停止日のチェックエラー */
    public static final int ERR_CHECK_6 = 6;
    /** 更新前チェック：利用停止日≠日付の場合のチェックエラー */
    public static final int ERR_CHECK_7 = 7;
    /** 更新前チェック：組織マスタより契約開始日の最小値と契約終了日の最大値存在なしエラー */
    public static final int ERR_CHECK_8 = 8;
    /** 更新前チェック：利用停止日、契約開始日、契約終了日の比較エラー */
    public static final int ERR_CHECK_9 = 9;
    /** 更新前チェック：レッスン予約のデータ存在チェックエラー */
    public static final int ERR_CHECK_10 = 10;
    /** 更新前チェック：ポイント所有テーブルへのデータ存在チェックエラー */
    public static final int ERR_CHECK_11 = 11;
    /** エラーなし */
    public static final int OK_CHECK = 0;

    /**
     * 登録のチェック。<br>
     * <br>
     * 登録のチェック。<br>
     * <br>
     * @param OrganizationStudentMstModel<br>
     * @return int チェック結果<br>
     * @exception NaiException
     */
    public int insertCheck(OrganizationStudentMstModel model) throws NaiException {

        // 関連チェック
        // パスワードの複雑性のチェック
        PasswordChkLogic passwordChkLogic = new PasswordChkLogic();
        int checkResult = passwordChkLogic.passwordCheck(model.getPassword_txt());
        if (checkResult == -1) {
            return ERR_CHECK_1;
        }

        // パスワードとパスワード確認の同一チェック
        if (!StringUtils.equals(model.getPassword_txt(), model.getPassworCon_txt())) {
            return ERR_CHECK_2;
        }

        // 自己負担率の下限チェック
        if (model.getBurdenNum() < 0) {
            return ERR_CHECK_3;
        }
        // 自己負担率の上限チェック
        if (model.getBurdenNum() > 100) {
            return ERR_CHECK_4;
        }
        return OK_CHECK;
    }

    /**
     * 更新のチェック。<br>
     * <br>
     * 更新のチェック。<br>
     * <br>
     * @param OrganizationStudentMstModel<br>
     * @return int チェック結果<br>
     * @exception NaiException
     */
    public int updateCheck(OrganizationStudentMstModel model) throws NaiException {

        // 関連チェック
        // 関連チェック
        // パスワードの複雑性のチェック
        PasswordChkLogic passwordChkLogic = new PasswordChkLogic();
        int checkResult = passwordChkLogic.passwordCheck(model.getPassword_txt());
        if (checkResult == -1) {
            return ERR_CHECK_1;
        }

        // パスワードとパスワード確認の同一チェック
        if (!StringUtils.equals(model.getPassword_txt(), model.getPassworCon_txt())) {
            return ERR_CHECK_2;
        }

        // 自己負担率の下限チェック
        if (model.getBurdenNum() < 0) {
            return ERR_CHECK_3;
        }
        // 自己負担率の上限チェック
        if (model.getBurdenNum() > 100) {
            return ERR_CHECK_4;
        }
        // 利用停止状態と利用停止日のチェック
        if (StringUtils.equals(NaikaraTalkConstants.FALSE, model.getUseStopFlg())
                && !StringUtils.isEmpty(model.getUseEndDt())) {
            return ERR_CHECK_5;
        }
        if (StringUtils.equals(NaikaraTalkConstants.TRUE, model.getUseStopFlg())
                && StringUtils.isEmpty(model.getUseEndDt())) {
            return ERR_CHECK_6;
        }

        Connection conn = null;
        try {
            conn = DbUtil.getConnection();
            // 組織マスタより契約開始日の最小値と契約終了日の最大値を取得する
            OrganizationStudentMstLogic logic = new OrganizationStudentMstLogic(conn);
            // DTOの初期化
            StudentMstDto prmDto = new StudentMstDto();
            OrganizationMstDto ormDto = new OrganizationMstDto();
            // Model値をDTOにセット
            prmDto = this.modelToStudentDto(model, prmDto);
            ormDto.setOrganizationId(model.getOrganizationId());
            OrganizationMstDto organizationMstDto = logic.selectOrganizationMst(ormDto);

            // データが存在しない場合
            if (StringUtils.equals(NaikaraTalkConstants.TRUE, model.getUseStopFlg())
                    && StringUtils.isEmpty(organizationMstDto.getContractStrDt())) {
                return ERR_CHECK_8;
            }

            // サーバーのシステム日付≦画面の｢利用停止日｣ 且つ 組織マスタ．契約開始日 ≦ 画面の｢利用停止日｣
            // 且つ 画面の｢利用停止日｣ ≦組織マスタ．契約終了日 を除く場合は、エラーメッセージ情報を設定する
            if (StringUtils.equals(NaikaraTalkConstants.TRUE, model.getUseStopFlg())
                    && !(DateUtil.dateCompare3(NaikaraStringUtil.converToYYYYMMDD(model.getUseEndDt()),
                            DateUtil.getSysDate())
                            && DateUtil.dateCompare3(NaikaraStringUtil.converToYYYYMMDD(model.getUseEndDt()),
                                    organizationMstDto.getContractStrDt()) && DateUtil.dateCompare3(
                            organizationMstDto.getContractEndDt(),
                            NaikaraStringUtil.converToYYYYMMDD(model.getUseEndDt())))) {
                return ERR_CHECK_9;
            }

            // レッスン予約のデータ存在チェック、データが存在する場合
            if (StringUtils.equals(NaikaraTalkConstants.TRUE, model.getUseStopFlg())
                    && logic.getRowCountLesson(prmDto) > 0) {
                return ERR_CHECK_10;
            }

            return OK_CHECK;
        } catch (SQLException e) {
            throw new NaiException(e);
        } catch (Exception e) {
            throw new NaiException(e);
        } finally {
            try {
                conn.close();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * 削除のチェック。<br>
     * <br>
     * 削除のチェック。<br>
     * <br>
     * @param OrganizationStudentMstModel<br>
     * @return int チェック結果<br>
     * @exception NaiException
     */
    public int deleteCheck(OrganizationStudentMstModel model) throws NaiException {

        Connection conn = null;
        try {
            conn = DbUtil.getConnection();
            // 組織マスタより契約開始日の最小値と契約終了日の最大値を取得する
            OrganizationStudentMstLogic logic = new OrganizationStudentMstLogic(conn);
            // DTOの初期化
            StudentMstDto prmDto = new StudentMstDto();
            prmDto = this.modelToDto(model);
            // ポイント所有テーブルへのデータ存在チェック (処理区分[削除]の場合)、データが存在する場合
            if (logic.getRowCountPoint(prmDto) > 0) {
                return ERR_CHECK_11;
            }

            return OK_CHECK;
        } catch (SQLException e) {
            throw new NaiException(e);
        } catch (Exception e) {
            throw new NaiException(e);
        } finally {
            try {
                conn.close();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * 登録処理。<br>
     * <br>
     * 登録処理。<br>
     * <br>
     * @param OrganizationStudentMstModel 画面のパラメータ<br>
     * @return なし<br>
     * @exception NaiException
     */
    public int insert(OrganizationStudentMstModel model) throws NaiException {
        Connection conn = null;
        try {
            conn = DbUtil.getConnection();

            OrganizationStudentMstLogic organizationStudentMstLogic = new OrganizationStudentMstLogic(conn);
            // DTOの初期化
            StudentMstDto prmDto = new StudentMstDto();
            OrganizationMstDto ormDto = new OrganizationMstDto();
            // Model値をDTOにセット
            prmDto = this.modelToDto(model);
            // 受講者IDの自動採番処理
            OrderNumbersMstLogic orderNumbersMstLogic = new OrderNumbersMstLogic();
            OrderNumberDto orderNumberDto = orderNumbersMstLogic.getOrderNumber(NaikaraTalkConstants.ORDER_NUMBERS_CU,
                    DateUtil.getSysDate());
            model.setStudentId(orderNumberDto.getOrderNumber());
            prmDto.setStudentId(orderNumberDto.getOrderNumber());
            // 組織マスタより契約終了日の最大値を取得する
            OrganizationStudentMstLogic logic = new OrganizationStudentMstLogic(conn);
            ormDto.setOrganizationId(model.getOrganizationId());
            OrganizationMstDto organizationMstDto = logic.selectOrganizationMst(ormDto);
            prmDto.setUseEndDt(organizationMstDto.getContractEndDt());
            // 利用停止状態：OFFの場合は、”0”：利用可
            prmDto.setUseStopFlg(USE_STOP_FLG_0);
            // 登録実行
            int result = organizationStudentMstLogic.insert(prmDto);

            // コミット
            conn.commit();
            return result;

        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
                throw new NaiException(e1);
            }
            throw new NaiException(e);
        } finally {
            try {
                conn.close();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * 更新処理。<br>
     * <br>
     * 更新処理。<br>
     * <br>
     * @param OrganizationStudentMstModel 画面のパラメータ<br>
     * @return int<br>
     * @exception NaiException
     */
    public int update(OrganizationStudentMstModel model) throws NaiException {

        Connection conn = null;
        int cnt = 0;
        try {

            conn = DbUtil.getConnection();

            OrganizationStudentMstLogic organizationStudentMstLogic = new OrganizationStudentMstLogic(conn);
            // DTOの初期化
            StudentMstDto prmDto = new StudentMstDto();
            // Model値をDTOにセット
            prmDto = this.modelToStudentDto(model, prmDto);
            StudentMstDto resDto = organizationStudentMstLogic.select(prmDto);
            resDto = this.modelToStudentDto(model, resDto);
            OrganizationMstDto ormDto = new OrganizationMstDto();
            ormDto.setOrganizationId(model.getOrganizationId());
            // 利用停止状態：ONの場合は、画面入力した利用停止日を設定する
            // 利用停止状態：OFFの場合は、該当する組織マスタの契約終了日の最大値を設定する
            if (StringUtils.equals(NaikaraTalkConstants.TRUE, model.getUseStopFlg())) {
                // 利用停止状態：ONの場合は、”1”：停止
                resDto.setUseStopFlg(USE_STOP_FLG_1);
            } else {
                // 組織マスタより契約終了日の最大値を取得する
                OrganizationStudentMstLogic logic = new OrganizationStudentMstLogic(conn);
                OrganizationMstDto organizationMstDto = logic.selectOrganizationMst(ormDto);
                resDto.setUseEndDt(organizationMstDto.getContractEndDt());
                // 利用停止状態：OFFの場合は、”0”：利用可
                resDto.setUseStopFlg(USE_STOP_FLG_0);
            }
            // 更新実行
            cnt = organizationStudentMstLogic.update(resDto);

            // コミット
            conn.commit();

        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
                throw new NaiException(e1);
            }
            throw new NaiException(e);
        } finally {
            try {
                conn.close();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return cnt;
    }

    /**
     * 削除処理。<br>
     * <br>
     * 削除処理。<br>
     * <br>
     * @param OrganizationStudentMstModel 画面のパラメータ<br>
     * @return int<br>
     * @exception NaiException
     */
    public int delete(OrganizationStudentMstModel model) throws NaiException {

        Connection conn = null;
        int cnt = 0;
        try {

            conn = DbUtil.getConnection();
            OrganizationStudentMstLogic organizationStudentMstLogic = new OrganizationStudentMstLogic(conn);
            // DTOの初期化
            StudentMstDto prmDto = new StudentMstDto();
            // Model値をDTOにセット
            prmDto = this.modelToDto(model);

            // 更新実行
            cnt = organizationStudentMstLogic.delete(prmDto);

            // コミット
            conn.commit();

        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
                throw new NaiException(e1);
            }
            throw new NaiException(e);
        } finally {
            try {
                conn.close();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return cnt;
    }

    /**
     * データの存在チェック。<br>
     * <br>
     * データの存在チェック。<br>
     * <br>
     * @param OrganizationStudentMstModel 画面のパラメータ<br>
     * @return boolean<br>
     * @exception NaiException
     */
    public int isExists(OrganizationStudentMstModel model) throws NaiException {

        Connection conn = null;
        try {
            conn = DbUtil.getConnection();

            OrganizationStudentMstLogic organizationStudentMstLogic = new OrganizationStudentMstLogic(conn);
            // DTOの初期化
            StudentMstDto prmDto = new StudentMstDto();
            // Model値をDTOにセット
            prmDto = this.modelToDto(model);

            // 検索実行
            return organizationStudentMstLogic.isExists(prmDto);
        } catch (SQLException e) {
            throw new NaiException(e);
        } catch (Exception e) {
            throw new NaiException(e);
        } finally {
            try {
                conn.close();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }

    }

    /**
     * データの存在チェック。<br>
     * <br>
     * データの存在チェック。<br>
     * <br>
     * @param OrganizationStudentMstModel 画面のパラメータ<br>
     * @return boolean<br>
     * @exception NaiException
     */
    public int getExists(OrganizationStudentMstModel model) throws NaiException {

        Connection conn = null;
        try {
            conn = DbUtil.getConnection();
            OrganizationStudentMstLogic organizationStudentMstLogic = new OrganizationStudentMstLogic(conn);
            // DTOの初期化
            StudentMstDto prmDto = new StudentMstDto();
            // Model値をDTOにセット
            prmDto = this.modelToStudentDto(model, prmDto);

            // 検索実行
            return organizationStudentMstLogic.isExists(prmDto);
        } catch (SQLException e) {
            throw new NaiException(e);
        } catch (Exception e) {
            throw new NaiException(e);
        } finally {
            try {
                conn.close();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }

    }

    /**
     * コード管理マスタからデータ取得処理。<br>
     * <br>
     * コード管理マスタからデータ取得処理。<br>
     * <br>
     * @param category 汎用コード<br>
     * @return LinkedHashMap<String, String><br>
     * @exception NaiException
     */
    public LinkedHashMap<String, String> selectCodeMst(String category) throws NaiException {

        Connection conn = null;
        try {
            conn = DbUtil.getConnection();
            OrganizationStudentMstLogic organizationStudentMstLogic = new OrganizationStudentMstLogic(conn);
            // コード管理マスタ検索
            return organizationStudentMstLogic.selectCodeMst(category);
        } catch (SQLException e) {
            throw new NaiException(e);
        } catch (Exception e) {
            throw new NaiException(e);
        } finally {
            try {
                conn.close();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }

    }

    /**
     * Model値をDTOにセット。<br>
     * <br>
     * Model値をDTOにセット。<br>
     * <br>
     * @param OrganizationStudentMstModel<br>
     * @return StudentMstDto<br>
     * @exception NaiException
     */
    private StudentMstDto modelToDto(OrganizationStudentMstModel model) throws NaiException {

        // DTOの初期化
        StudentMstDto prmDto = new StudentMstDto();
        // パスワード
        prmDto.setPassword(model.getPassword_txt());
        // 受講者所属部署
        prmDto.setStudentPosition(model.getStudentPosition());
        // 所属組織内ID
        prmDto.setPositionOrganizationId(model.getPositionOrganizationId());
        // 名前(姓)
        prmDto.setFamilyJnm(model.getFamilyJnm());
        // 名前(名)
        prmDto.setFirstJnm(model.getFirstJnm());
        // 自己負担率
        prmDto.setBurdenNum(model.getBurdenNum());
        // 利用停止フラグ
        if (StringUtils.isEmpty(model.getUseStopFlg())) {
            prmDto.setUseStopFlg(NaikaraTalkConstants.USE_KBN_OK);
        } else {
            prmDto.setUseStopFlg(model.getUseStopFlg());
        }
        // 利用終了日
        prmDto.setUseEndDt(NaikaraStringUtil.converToYYYYMMDD(model.getUseEndDt()));
        // 組織ID
        prmDto.setOrganizationId(model.getOrganizationId());
        // 組織名
        prmDto.setOrganizationNm(model.getOrganizationNm_txt());
        // 受講者ID
        prmDto.setStudentId(model.getStudentId());
        // 顧客区分
        prmDto.setCustomerKbn(NaikaraTalkConstants.CUSTOMER_KBN_ORGANIZATION);
        // 利用開始日
        prmDto.setUseStrDt(DateUtil.getSysDate());
        // 利用動機フラグ１
        prmDto.setUseMotiveFlg1(NaikaraTalkConstants.USE_MOTIVE_FLG_NO);
        // 利用動機フラグ２
        prmDto.setUseMotiveFlg2(NaikaraTalkConstants.USE_MOTIVE_FLG_NO);
        // 利用動機フラグ３
        prmDto.setUseMotiveFlg3(NaikaraTalkConstants.USE_MOTIVE_FLG_NO);
        // 利用動機フラグ４
        prmDto.setUseMotiveFlg4(NaikaraTalkConstants.USE_MOTIVE_FLG_NO);
        // 利用動機フラグ５
        prmDto.setUseMotiveFlg5(NaikaraTalkConstants.USE_MOTIVE_FLG_NO);
        // 利用規約に同意する：チェックフラグ
        prmDto.setUseAgreementFlg(NaikaraTalkConstants.USE_AGREEMENT_FLG_YES);
        // 個人情報の同意：チェックフラグ
        prmDto.setIndividualAgreementFlg(NaikaraTalkConstants.INDIVIDUAL_AGREEMENT_FLG_YES);
        // 保護者の同意書の入手フラグ
        prmDto.setConsentDocumentAcquisitionFlg(NaikaraTalkConstants.PARENTAL_CONSENT_FLG_YES);
        // ポイント購入済フラグ
        prmDto.setPointPurchaseFlg(NaikaraTalkConstants.POINT_PURCHASE_FLG_YES);
        // DM不要フラグ
        prmDto.setDmNoNeedFlg(NaikaraTalkConstants.DM_NO_NEED_FLG_YES);
        // その他フラグ１
        prmDto.setOther1Flg(NaikaraTalkConstants.OTHER_FLG_OFF);
        // その他フラグ２
        prmDto.setOther2Flg(NaikaraTalkConstants.OTHER_FLG_OFF);
        // その他フラグ３
        prmDto.setOther3Flg(NaikaraTalkConstants.OTHER_FLG_OFF);
        // その他フラグ４
        prmDto.setOther4Flg(NaikaraTalkConstants.OTHER_FLG_OFF);
        // 登録者コード
        prmDto.setInsertCd(((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString())).getUserId());
        // 更新者コード
        prmDto.setUpdateCd(((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString())).getUserId());
        // レコードバージョン番号
        prmDto.setRecordVerNo(model.getRecordVerNo());
        return prmDto;

    }

    /**
     * Model値をDTOにセット。<br>
     * <br>
     * Model値をDTOにセット。<br>
     * <br>
     * @param OrganizationStudentMstModel<br>
     * @return StudentMstDto<br>
     * @exception NaiException
     */
    private StudentMstDto modelToStudentDto(OrganizationStudentMstModel model, StudentMstDto prmDto)
            throws NaiException {

        // パスワード
        prmDto.setPassword(model.getPassword_txt());
        // 受講者所属部署
        prmDto.setStudentPosition(model.getStudentPosition());
        // 所属組織内ID
        prmDto.setPositionOrganizationId(model.getPositionOrganizationId());
        // 名前(姓)
        prmDto.setFamilyJnm(model.getFamilyJnm());
        // 名前(名)
        prmDto.setFirstJnm(model.getFirstJnm());
        // 自己負担率
        prmDto.setBurdenNum(model.getBurdenNum());
        // 利用停止フラグ
        prmDto.setUseStopFlg(StringUtils.equals(NaikaraTalkConstants.TRUE, model.getUseStopFlg()) ? "1" : "0");
        // 利用終了日
        prmDto.setUseEndDt(NaikaraStringUtil.converToYYYYMMDD(model.getUseEndDt()));
        // 組織ID
        prmDto.setOrganizationId(model.getOrganizationId());
        // 組織名
        prmDto.setOrganizationNm(model.getOrganizationNm_txt());
        // 受講者ID
        prmDto.setStudentId(model.getStudentId());
        // 登録者コード
        prmDto.setInsertCd(((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString())).getUserId());
        // 更新者コード
        prmDto.setUpdateCd(((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString())).getUserId());
        // レコードバージョン番号
        prmDto.setRecordVerNo(model.getRecordVerNo());
        return prmDto;

    }
}
