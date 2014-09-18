package com.naikara_talk.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedHashMap;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dbutil.DbUtil;
import com.naikara_talk.dto.PointOwnershipTrnDto;
import com.naikara_talk.dto.StudentMstDto;
import com.naikara_talk.dto.UserMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.logic.StudentMstLogic;
import com.naikara_talk.model.StudentMstListModel;
import com.naikara_talk.model.StudentMstModel;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>マスタ保守<br>
 * <b>クラス名称　　　:</b>受講者マスタメンテナンス【単票】初期処理Serviceクラス。<br>
 * <b>クラス概要　　　:</b>受講者マスタメンテナンス【単票】初期処理Service。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/07/30 TECS 新規作成。
 */
public class StudentMstLoadService implements ActionService {

    // 通常月謝区分：有
    public static final String FEEKBN_ARI = "有";

    // 通常月謝区分：無
    public static final String FEEKBN_NASI = "無";

    // データカウント下限
    public static int DATA_COUNT_MIN = 0;

    /**
     * 初期処理<br>
     * <br>
     * 画面項目の初期処理を行う<br>
     * <br>
     * @return StudentMstModel
     */
    public StudentMstModel initLoad(StudentMstModel model) {
        StudentMstModel workModel = new StudentMstModel();

        // 前画面から処理区分を画面にセット
        workModel.setProcessKbn_rdl(model.getProcessKbn_rdl());
        if (StringUtils.equals(StudentMstListModel.PROS_KBN_ADD, model.getProcessKbn_rdl())) {
            workModel.setProcessKbn_txt(NaikaraTalkConstants.PROCESSKBN_INS);
        } else if (StringUtils.equals(StudentMstListModel.PROS_KBN_UPD, model.getProcessKbn_rdl())) {
            workModel.setProcessKbn_txt(NaikaraTalkConstants.PROCESSKBN_UPD);
        } else {
            workModel.setProcessKbn_txt(NaikaraTalkConstants.PROCESSKBN_REF);
        }

        return workModel;
    }

    /**
     * 画面初期表示。<br>
     * <br>
     * @param StudentMstModel
     *            画面のパラメータ
     * @return なし
     * @throws NaiException
     */
    public StudentMstModel select(StudentMstModel model) throws NaiException {

        Connection conn = null;
        try {
            conn = DbUtil.getConnection();

            StudentMstLogic studentMstLogic = new StudentMstLogic(conn);
            // DTOの初期化
            StudentMstDto prmDto = new StudentMstDto();

            // Model値をDTOにセット
            prmDto = this.modelToDto(model);

            // 検索実行
            StudentMstDto resultDto = studentMstLogic.select(prmDto);

            // DTO値をModelにセット
            model = this.dtoToModel(resultDto, model);

            return model;

        } catch (SQLException se) {
            se.printStackTrace();
            throw new NaiException(se);
        } finally {
            try {
                conn.close();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }

    }

    /**
     * 画面月謝購入区分表示。<br>
     * <br>
     * @param StudentMstModel
     *            画面のパラメータ
     * @return なし
     * @throws NaiException
     */
    public String selectFeeKbn(StudentMstModel model) throws NaiException {

        Connection conn = null;
        try {
            conn = DbUtil.getConnection();

            StudentMstLogic studentMstLogic = new StudentMstLogic(conn);
            // DTOの初期化
            PointOwnershipTrnDto prmDto = new PointOwnershipTrnDto();

            // Model値をDTOにセット
            prmDto = this.modelToPointOwnershipTrnDto(model);

            // 検索実行
            int listSize = studentMstLogic.selectPointOwnershipTrnDto(prmDto);

            if (DATA_COUNT_MIN < listSize) {
                return FEEKBN_ARI;
            } else {
                return FEEKBN_NASI;
            }

        } catch (SQLException se) {
            se.printStackTrace();
            throw new NaiException(se);
        } finally {
            try {
                conn.close();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }

    }

    /**
     * パスワード表示フラグを取得。<br>
     * <br>
     * @param StudentMstModel
     *            画面のパラメータ
     * @return なし
     * @throws NaiException
     */
    public String selectRole(String userId) throws NaiException {

        Connection conn = null;
        try {
            conn = DbUtil.getConnection();

            StudentMstLogic studentMstLogic = new StudentMstLogic(conn);
            // DTOの初期化
            UserMstDto prmDto = new UserMstDto();

            // DTOにセット
            prmDto.setUserId(userId);

            // 検索実行
            prmDto = studentMstLogic.selectUserMst(prmDto);

            return prmDto.getClassificationKbn();

        } catch (SQLException se) {
            se.printStackTrace();
            throw new NaiException(se);
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
     * @param StudentMstModel
     *            画面のパラメータ
     * @return int
     * @throws NaiException
     */
    public int getExist(StudentMstModel model) throws NaiException {

        Connection conn = null;
        try {
            conn = DbUtil.getConnection();

            StudentMstLogic studentMstLogic = new StudentMstLogic(conn);
            // DTOの初期化
            StudentMstDto prmDto = new StudentMstDto();

            // Model値をDTOにセット
            prmDto = this.modelToDto(model);

            // 検索実行
            return studentMstLogic.getExist(prmDto);

        } catch (SQLException se) {
            se.printStackTrace();
            throw new NaiException(se);
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
     * @param category
     *            汎用コード
     * @return LinkedHashMap<String, String>
     * @throws NaiException
     */
    public LinkedHashMap<String, String> selectCodeMst(String category) throws NaiException {

        Connection conn = null;
        try {
            conn = DbUtil.getConnection();

            StudentMstLogic StudentMstLogic = new StudentMstLogic(conn);

            // コード管理マスタ検索
            return StudentMstLogic.selectCodeMst(category);

        } catch (SQLException se) {
            se.printStackTrace();
            throw new NaiException(se);
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
     * @param model
     *           StudentMstModel
     * @return StudentMstDto
     * @throws NaiException
     */
    private StudentMstDto modelToDto(StudentMstModel model) throws NaiException {

        // DTOの初期化
        StudentMstDto prmDto = new StudentMstDto();

        prmDto.setStudentId(model.getStudentId_lbl());                                             // 受講者ID

        return prmDto;

    }

    /**
     * Model値をDTOにセット。<br>
     * <br>
     * @param model
     *           StudentMstModel
     * @return StudentMstDto
     * @throws NaiException
     */
    private PointOwnershipTrnDto modelToPointOwnershipTrnDto(StudentMstModel model) throws NaiException {

        // DTOの初期化
        PointOwnershipTrnDto prmDto = new PointOwnershipTrnDto();

        prmDto.setStudentId(model.getStudentId_lbl());                                             // 受講者ID

        return prmDto;

    }

    /**
     * DTO値をModelにセット。<br>
     * <br>
     * @param prmDto
     *           StudentMstDto
     * @param model
     *           StudentMstModel
     * @return StudentMstModel
     * @throws NaiException
     */
    private StudentMstModel dtoToModel(StudentMstDto prmDto, StudentMstModel model) throws NaiException {

        model.setProcessKbn_rdl(model.getProcessKbn_rdl());                                        // 処理区分
        model.setProcessKbn_txt(model.getProcessKbn_txt());                                        // 処理区分名

        model.setStudentId_lbl(prmDto.getStudentId());                                             // 受講者ID
        model.setFamilyJnm_txt(prmDto.getFamilyJnm());                                             // 名前(姓)
        model.setFirstJnm_txt(prmDto.getFirstJnm());                                               // 名前(名)
        model.setFamilyKnm_txt(prmDto.getFamilyKnm());                                             // フリガナ(姓)
        model.setFirstKnm_txt(prmDto.getFirstKnm());                                               // フリガナ(名)
        model.setFamilyRomaji_txt(prmDto.getFamilyRomaji());                                       // ローマ字(姓)
        model.setFirstRomaji_txt(prmDto.getFirstRomaji());                                         // ローマ字(名)
        model.setNickNm_txt(prmDto.getNickNm());                                                   // ニックネーム
        model.setPassword_txt(prmDto.getPassword());                                               // パスワード
        model.setTel1_txt(prmDto.getTel1());                                                       // 電話番号1
        model.setTel2_txt(prmDto.getTel2());                                                       // 電話番号2
        model.setBirthYyyy_txt(prmDto.getBirthYyyy());                                             // 生年月日：年
        model.setBirthMm_txt(prmDto.getBirthMm());                                                 // 生年月日：月
        model.setBirthDd_txt(prmDto.getBirthDd());                                                 // 生年月日：日
        model.setZipCd_txt(prmDto.getZipCd());                                                     // 郵便番号
        model.setAddressAreaCd_sel(prmDto.getAddressAreaCd());                                     // 住所(地域)コード
        model.setAddressPrefectureCd_sel(prmDto.getAddressPrefectureCd());                         // 住所(都道府県)コード
        model.setAddressCity_txt(prmDto.getAddressCity());                                         // 住所(市区町村等)
        model.setAddressOthers_txt(prmDto.getAddressOthers());                                     // 住所(ﾋﾞﾙ、ﾏﾝｼｮﾝ名等)
        model.setGenderKbn_rdl(prmDto.getGenderKbn());                                             // 性別区分
        model.setMailAddress1_txt(prmDto.getMailAddress1());                                       // メールアドレス1
        model.setMailAddress2_txt(prmDto.getMailAddress2());                                       // メールアドレス2
        model.setMailAddress3_txt(prmDto.getMailAddress3());                                       // メールアドレス3
        model.setLoginNum_lbl(String.valueOf(prmDto.getLoginNum()));                               // ログイン回数
        model.setEndLoginDt_lbl(prmDto.getEndLoginDt());                                           // 最終ログイン日
        model.setOccupationCd_sel(prmDto.getOccupationCd());                                       // 職業コード
        model.setCustomerKbn_rdl(prmDto.getCustomerKbn());                                         // 顧客区分
        model.setOrganizationNm_txt(prmDto.getOrganizationNm());                                   // 組織名
        model.setUseStrDt_txt(prmDto.getUseStrDt());                                               // 利用開始日
        model.setUseEndDt_txt(prmDto.getUseEndDt());                                               // 利用終了日
        model.setUseStopFlg(prmDto.getUseStopFlg());                                               // 利用停止フラグ
        boolean useMotiveFlg1 = StringUtils.equals(prmDto.getUseMotiveFlg1(), NaikaraTalkConstants.USE_MOTIVE_FLG_NO) ? false
                : true;
        boolean useMotiveFlg2 = StringUtils.equals(prmDto.getUseMotiveFlg2(), NaikaraTalkConstants.USE_MOTIVE_FLG_NO) ? false
                : true;
        boolean useMotiveFlg3 = StringUtils.equals(prmDto.getUseMotiveFlg3(), NaikaraTalkConstants.USE_MOTIVE_FLG_NO) ? false
                : true;
        boolean useMotiveFlg4 = StringUtils.equals(prmDto.getUseMotiveFlg4(), NaikaraTalkConstants.USE_MOTIVE_FLG_NO) ? false
                : true;
        boolean useMotiveFlg5 = StringUtils.equals(prmDto.getUseMotiveFlg5(), NaikaraTalkConstants.USE_MOTIVE_FLG_NO) ? false
                : true;
        model.setUseMotiveFlg_chk1(useMotiveFlg1);                                                 // 利用動機フラグ１
        model.setUseMotiveFlg_chk2(useMotiveFlg2);                                                 // 利用動機フラグ２
        model.setUseMotiveFlg_chk3(useMotiveFlg3);                                                 // 利用動機フラグ３


//2013/09/25-Del-Start
//        model.setUseMotiveFlg_chk4(useMotiveFlg4);                                                 // 利用動機フラグ４
//2013/09/25-Del-ENd


        model.setUseMotiveFlg_chk5(useMotiveFlg5);                                                 // 利用動機フラグ５
        model.setUseMotive_txt(prmDto.getUseMotive());                                             // 利用動機テキスト
        model.setAchievement_txt(prmDto.getAchievement());                                         // 達成目標
        boolean useAgreementFlg = StringUtils.equals(prmDto.getUseAgreementFlg(),
                NaikaraTalkConstants.USE_MOTIVE_FLG_NO) ? false : true;
        model.setUseAgreement_chk(useAgreementFlg);                                                // 利用規約に同意する：チェックフラ
        boolean useIndividualAgreementFlg = StringUtils.equals(prmDto.getIndividualAgreementFlg(),
                NaikaraTalkConstants.USE_MOTIVE_FLG_NO) ? false : true;
        model.setIndividualAgreement_chk(useIndividualAgreementFlg);                               // 個人情報の同意：チェックフラグ
        model.setSchoolCmt_txa(prmDto.getSchoolCmt());                                             // ｽｸｰﾙからのｺﾒﾝﾄ(生徒公開)
        model.setRemark_txa(prmDto.getRemark());                                                   // 備考(生徒非公開)
        model.setOrganizationId_lbl(prmDto.getOrganizationId());                                       // 組織ID
        model.setPositionOrganizationId_lbl(prmDto.getPositionOrganizationId());                   // 所属組織内ID
        model.setStudentPosition_lbl(prmDto.getStudentPosition());                                 // 受講者所属部署
        model.setBurdenNum_lbl(String.valueOf(prmDto.getBurdenNum()));                             // 自己負担率
        model.setConsentDocumentAcquisitionFlg_rdl(prmDto.getConsentDocumentAcquisitionFlg());     // 保護者の同意書の入手フラグ
        model.setPointPurchaseFlg(prmDto.getPointPurchaseFlg());                                   // ポイント購入済フラグ
        boolean dmNoNeedFlg = prmDto.getDmNoNeedFlg() == NaikaraTalkConstants.DM_NO_NEED_FLG_YES ? false : true;
        boolean otherFlg1 = prmDto.getOther1Flg() == NaikaraTalkConstants.OTHER_FLG_OFF ? false : true;
        boolean otherFlg2 = prmDto.getOther2Flg() == NaikaraTalkConstants.OTHER_FLG_OFF ? false : true;
        boolean otherFlg3 = prmDto.getOther3Flg() == NaikaraTalkConstants.OTHER_FLG_OFF ? false : true;
        boolean otherFlg4 = prmDto.getOther4Flg() == NaikaraTalkConstants.OTHER_FLG_OFF ? false : true;
        model.setDmNoNeedFlg_chk(dmNoNeedFlg);                                                     // DM不要フラグ
        model.setOther1Flg_chk(otherFlg1);                                                         // その他フラグ１
        model.setOther2Flg_chk(otherFlg2);                                                         // その他フラグ２
        model.setOther3Flg_chk(otherFlg3);                                                         // その他フラグ３
        model.setOther4Flg_chk(otherFlg4);                                                         // その他フラグ４
        model.setGuardianFamilyJnm_txt(prmDto.getGuardianFamilyJnm());                             // 保護者：名前(姓)
        model.setGuardianFirstJnm_txt(prmDto.getGuardianFirstJnm());                               // 保護者：名前(名）
        model.setGuardianFamilyKnm_txt(prmDto.getGuardianFamilyKnm());                             // 保護者：フリガナ(姓)
        model.setGuardianFirstKnm_txt(prmDto.getGuardianFirstKnm());                               // 保護者：フリガナ(名）
        model.setGuardianFamilyRelationship_txt(prmDto.getGuardianFamilyRelationship());           // あなたとの続柄
        model.setGuardianTel1_txt(prmDto.getGuardianTel1());                                       // 保護者：電話番号1
        model.setGuardianTel2_txt(prmDto.getGuardianTel2());                                       // 保護者：電話番号2
        model.setGuardianBirthYyyy_txt(prmDto.getGuardianBirthYyyy());                             // 保護者：生年月日：年
        model.setGuardianBirthMm_txt(prmDto.getGuardianBirthMm());                                 // 保護者：生年月日：月
        model.setGuardianBirthDd_txt(prmDto.getGuardianBirthDd());                                 // 保護者：生年月日：日
        model.setGuardianZipCd_txt(prmDto.getGuardianZipCd());                                     // 保護者：郵便番号
        model.setGuardianAddressAreaCd_sel(prmDto.getGuardianAddressAreaCd());                     // 保護者：住所(地域)コード
        model.setGuardianAddressPrefectureCd_sel(prmDto.getGuardianAddressPrefectureCd());         // 保護者：住所(都道府県)コード
        model.setGuardianAddressCity_txt(prmDto.getGuardianAddressCity());                         // 保護者：住所(市区町村等)
        model.setGuardianAddressOthers_txt(prmDto.getGuardianAddressOthers());                     // 保護者：住所(ﾋﾞﾙ、ﾏﾝｼｮﾝ名等)
        model.setGuardianGenderKbn_rdl(prmDto.getGuardianGenderKbn());                             // 保護者：性別区分
        model.setGuardianMailAddress1_txt(prmDto.getGuardianMailAddress1());                       // 保護者：メールアドレス1
        model.setGuardianMailAddress2_txt(prmDto.getGuardianMailAddress2());                       // 保護者：メールアドレス2
        model.setGuardianMailAddress3_txt(prmDto.getGuardianMailAddress3());                       // 保護者：メールアドレス3
        model.setRecordVerNo(prmDto.getRecordVerNo());                                             // レコードバージョン番号
        model.setInsert_cd(prmDto.getInsertCd());                                                  // 登録者コード
        model.setUpdate_cd(prmDto.getUpdateCd());                                                  // 更新者コード

        return model;

    }
}
