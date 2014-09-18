package com.naikara_talk.action;

import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dto.PointOwnershipTrnDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.model.StudentMonthlyFeePurchaseInformationModel;
import com.naikara_talk.model.StudentMstListModel;
import com.naikara_talk.model.StudentMstModel;
import com.naikara_talk.model.StudentSpecialFreePointMstModel;
import com.naikara_talk.service.StudentMonthlyFeePurchaseInformationLoadService;
import com.naikara_talk.service.StudentMstLoadService;
import com.naikara_talk.service.StudentSpecialFreePointMstLoadService;
import com.naikara_talk.sessiondata.SessionStudentMst;
import com.naikara_talk.util.DateUtil;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>>マスタ保守。<br>
 * <b>クラス名称　　　:</b>受講者マスタメンテナンス【単票】初期処理Actionクラス。<br>
 * <b>クラス概要　　　:</b>受講者マスタメンテナンス【単票】初期処理Action。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/07/30 TECS 新規作成。
 */
public class StudentMstLoadAction extends StudentMstActionSupport {

    private static final long serialVersionUID = 1L;

    /**
     * 画面の初期表示。<br>
     * <br>
     * @param なし
     * @return String
     * @throws NaiException
     */
    public String requestService() throws NaiException {

        // 開始ログ
        log.info(NaikaraStringUtil.unionProcesslog("Start"));

        // 戻る判定Onフラグ
        this.returnOnFlg = false;

        // 非初期ロードの場合
        if (((SessionStudentMst) SessionDataUtil.getSessionData(SessionStudentMst.class.toString())) != null) {
            returnOnFlg = ((SessionStudentMst) SessionDataUtil.getSessionData(SessionStudentMst.class.toString()))
                    .isReturnOnFlg();
            if (returnOnFlg) {

                this.sessionStudentMstToModel();
                ((SessionStudentMst) SessionDataUtil.getSessionData(SessionStudentMst.class.toString()))
                        .setReturnOnFlg(false);

                // 画面を返す
                return SUCCESS;
            }

        }

        // 処理区分＝新規の場合
        if (StringUtils.equals(StudentMstListModel.PROS_KBN_ADD, this.processKbn_rdl)) {
            // 前画面からのPrimaryKeyをクリア
            this.primaryKey = null;
        }

        // Modelクラス設定
        setupModel();

        StudentMstLoadService service = new StudentMstLoadService();

        StudentMstModel workModel = service.initLoad(model);

        // 前画面から処理区分を画面にセット
        this.processKbn_rdl = workModel.getProcessKbn_rdl();
        this.processKbn_txt = workModel.getProcessKbn_txt();

        // コードマスタリストの初期取得。
        try {
            initSelect();
            initRadio();
            initCheckbox();
        } catch (Exception e) {
            throw new NaiException(e);
        }

        // ******************************
        // 新規・修正・照会の処理
        // ******************************

        // 処理区分＝新規の場合('0':'新規','1':'修正','2':'照会')

        if (StringUtils.equals(StudentMstListModel.PROS_KBN_ADD, this.processKbn_rdl)) {
            // 画面の初期表示値設定
            this.useAgreement_chk = true;
            this.individualAgreement_chk = true;
            this.feeKbn = NaikaraTalkConstants.FALSE;
            this.customerKbn_rdl = NaikaraTalkConstants.CUSTOMER_KBN_PERSON;
            this.useStrDt_txt = NaikaraStringUtil.converToYYYY_MM_DD(DateUtil.getSysDate());
            this.useEndDt_txt = NaikaraTalkConstants.MAX_END_DT;
            this.guardianGenderKbn_rdl = NaikaraTalkConstants.GENDER_MALE;
            this.feeKbn = StudentMstLoadService.FEEKBN_NASI;
            return SUCCESS;
        }

        // 処理区分＝照会の場合('0':'新規','1':'修正','2':'照会')
        if (StringUtils.equals(StudentMstListModel.PROS_KBN_REF, this.processKbn_rdl)) {
            this.setComment(NaikaraTalkConstants.PROCESSKBN_REF_COMMENT);
        }

        // 処理区分＝修正、照会の場合('0':'新規','1':'修正','2':'照会')
        if (StringUtils.equals(StudentMstListModel.PROS_KBN_UPD, this.processKbn_rdl)
                || StringUtils.equals(StudentMstListModel.PROS_KBN_REF, this.processKbn_rdl)) {

            try {

                // データが存在しない場合
                if (NaikaraTalkConstants.RETURN_CD_DATA_NO == service.getExist(model)) {
                    this.message = getMessage("EN0020", new String[] { "受講者マスタ", "" });
                    removeLatestActionList();
                    // 前画面(一覧)へ戻り、エラーメッセージを表示
                    return ERROR;
                } else {
                    // データが存在する場合

                    // 表示データの取得処理
                    this.load();
                    SessionStudentMst sessionData;
                    if (((SessionStudentMst) SessionDataUtil.getSessionData(SessionStudentMst.class.toString())) != null) {
                        sessionData = ((SessionStudentMst) SessionDataUtil.getSessionData(SessionStudentMst.class
                                .toString()));
                        sessionData.setMonthlyReturnList(this.monthlyReturnList);
                        sessionData.setSpecialFreeReturnList(this.specialFreeReturnList);
                        SessionDataUtil.setSessionData(sessionData);
                    } else {
                        sessionData = new SessionStudentMst();
                        sessionData.setMonthlyReturnList(this.monthlyReturnList);
                        sessionData.setSpecialFreeReturnList(this.specialFreeReturnList);
                        SessionDataUtil.setSessionData(sessionData);
                    }
                    return SUCCESS;
                }

            } catch (Exception e) {
                throw new NaiException(e);
            }
        }

        // 画面を返す
        return SUCCESS;

    }

    /**
     * 初期処理、表示データの取得。<br>
     * <br>
     * @param なし
     * @return なし
     * @throws Exception
     */
    private void load() throws Exception {

        // 表示データを取得する
        StudentMstLoadService service = new StudentMstLoadService();

        // 前画面の情報
        this.model.setProcessKbn_rdl(this.processKbn_rdl);
        this.model.setProcessKbn_txt(this.processKbn_txt);

        model = service.select(this.model);

        /** 受講者ID */
        this.studentId_lbl = this.model.getStudentId_lbl();

        /** 名前(姓) */
        this.familyJnm_txt = this.model.getFamilyJnm_txt();

        /** 名前(名) */
        this.firstJnm_txt = this.model.getFirstJnm_txt();

        /** フリガナ(姓) */
        this.familyKnm_txt = this.model.getFamilyKnm_txt();

        /** フリガナ(名) */
        this.firstKnm_txt = this.model.getFirstKnm_txt();

        /** ローマ字(姓) */
        this.familyRomaji_txt = this.model.getFamilyRomaji_txt();

        /** ローマ字(名) */
        this.firstRomaji_txt = this.model.getFirstRomaji_txt();

        /** ニックネーム */
        this.nickNm_txt = this.model.getNickNm_txt();

        /** 電話番号1 */
        this.tel1_txt = this.model.getTel1_txt();

        /** 電話番号2 */
        this.tel2_txt = this.model.getTel2_txt();

        /** 生年月日：年 */
        this.birthYyyy_txt = this.model.getBirthYyyy_txt();

        /** 生年月日：月 */
        this.birthMm_txt = this.model.getBirthMm_txt();

        /** 生年月日：日 */
        this.birthDd_txt = this.model.getBirthDd_txt();

        /** 郵便番号 */
        this.zipCd_txt = this.model.getZipCd_txt();

        /** 住所(地域)コード */
        this.addressAreaCd_sel = this.model.getAddressAreaCd_sel();

        /** 住所(都道府県)コード */
        this.addressPrefectureCd_sel = this.model.getAddressPrefectureCd_sel();

        /** 住所(市区町村 等) */
        this.addressCity_txt = this.model.getAddressCity_txt();

        /** 住所(ﾋﾞﾙ、ﾏﾝｼｮﾝ名 等) */
        this.addressOthers_txt = this.model.getAddressOthers_txt();

        /** 性別区分 */
        this.genderKbn_rdl = this.model.getGenderKbn_rdl();

        /** メールアドレス1 */
        this.mailAddress1_txt = this.model.getMailAddress1_txt();

        /** メールアドレス2 */
        this.mailAddress2_txt = this.model.getMailAddress2_txt();

        /** メールアドレス3 */
        this.mailAddress3_txt = this.model.getMailAddress3_txt();

        /** パスワード */
        this.password_txt = this.model.getPassword_txt();

        /** ログイン回数 */
        this.loginNum_lbl = this.model.getLoginNum_lbl();

        /** 最終ログイン日 */
        this.endLoginDt_lbl = NaikaraStringUtil.converToYYYY_MM_DD(this.model.getEndLoginDt_lbl());

        /** 職業コード */
        this.occupationCd_sel = this.model.getOccupationCd_sel();

        /** 顧客区分 */
        this.customerKbn_rdl = this.model.getCustomerKbn_rdl();

        /** 組織名 */
        this.organizationNm_txt = this.model.getOrganizationNm_txt();

        /** 組織ID */
        this.organizationId_lbl = this.model.getOrganizationId_lbl();

        /** 受講者所属部署 */
        this.studentPosition_lbl = this.model.getStudentPosition_lbl();

        /** 所属組織内ID */
        this.positionOrganizationId_lbl = this.model.getPositionOrganizationId_lbl();

        /** 自己負担率 */
        this.burdenNum_lbl = this.model.getBurdenNum_lbl();

        /** 利用開始日 */
        this.useStrDt_txt = NaikaraStringUtil.converToYYYY_MM_DD(this.model.getUseStrDt_txt());

        /** 利用終了日 */
        this.useEndDt_txt = NaikaraStringUtil.converToYYYY_MM_DD(this.model.getUseEndDt_txt());

        /** 利用停止フラグ */
        this.useStopFlg = this.model.getUseStopFlg();

        /** 利用動機フラグ１ */
        this.useMotiveFlg_chk1 = this.model.isUseMotiveFlg_chk1();

        /** 利用動機フラグ2 */
        this.useMotiveFlg_chk2 = this.model.isUseMotiveFlg_chk2();

        /** 利用動機フラグ3 */
        this.useMotiveFlg_chk3 = this.model.isUseMotiveFlg_chk3();

/*2013/09/25-Del-Start
        /** 利用動機フラグ4 */
//        this.useMotiveFlg_chk4 = this.model.isUseMotiveFlg_chk4();
/*2013/09/25-Del-End

        /** 利用動機フラグ5 */
        this.useMotiveFlg_chk5 = this.model.isUseMotiveFlg_chk5();

        /** 利用動機テキスト */
        this.useMotive_txt = this.model.getUseMotive_txt();

        /** 達成目標 */
        this.achievement_txt = this.model.getAchievement_txt();

        /** 利用規約に同意する：チェックフラグ */
        this.useAgreement_chk = this.model.isUseAgreement_chk();

        /** 個人情報の同意：チェックフラグ */
        this.individualAgreement_chk = this.model.isIndividualAgreement_chk();

        /** ｽｸｰﾙからのｺﾒﾝﾄ(生徒公開) */
        this.schoolCmt_txa = this.model.getSchoolCmt_txa();

        /** 備考(生徒非公開) */
        this.remark_txa = this.model.getRemark_txa();

        /** 保護者の同意書の入手フラグ */
        this.consentDocumentAcquisitionFlg_rdl = this.model.getConsentDocumentAcquisitionFlg_rdl();

        /** 保護者：名前(姓) */
        this.guardianFamilyJnm_txt = this.model.getGuardianFamilyJnm_txt();

        /** 保護者：名前(名） */
        this.guardianFirstJnm_txt = this.model.getGuardianFirstJnm_txt();

        /** 保護者：フリガナ(姓) */
        this.guardianFamilyKnm_txt = this.model.getGuardianFamilyKnm_txt();

        /** 保護者：フリガナ(名) */
        this.guardianFirstKnm_txt = this.model.getGuardianFirstKnm_txt();

        /** あなたとの続柄 */
        this.guardianFamilyRelationship_txt = this.model.getGuardianFamilyRelationship_txt();

        /** 保護者：電話番号1 */
        this.guardianTel1_txt = this.model.getGuardianTel1_txt();

        /** 保護者：電話番号2 */
        this.guardianTel2_txt = this.model.getGuardianTel2_txt();

        /** 保護者：生年月日：年 */
        this.guardianBirthYyyy_txt = this.model.getGuardianBirthYyyy_txt();

        /** 保護者：生年月日：月 */
        this.guardianBirthMm_txt = this.model.getGuardianBirthMm_txt();

        /** 保護者：生年月日：日 */
        this.guardianBirthDd_txt = this.model.getGuardianBirthDd_txt();

        /** 保護者：郵便番号 */
        this.guardianZipCd_txt = this.model.getGuardianZipCd_txt();

        /** 保護者：住所(地域)コード */
        this.guardianAddressAreaCd_sel = this.model.getGuardianAddressAreaCd_sel();

        /** 保護者：住所(都道府県)コード */
        this.guardianAddressPrefectureCd_sel = this.model.getGuardianAddressPrefectureCd_sel();

        /** 保護者：住所(市区町村 等) */
        this.guardianAddressCity_txt = this.model.getGuardianAddressCity_txt();

        /** 保護者：住所(ﾋﾞﾙ、ﾏﾝｼｮﾝ名 等) */
        this.guardianAddressOthers_txt = this.model.getGuardianAddressOthers_txt();

        /** 保護者：性別区分 */
        this.guardianGenderKbn_rdl = this.model.getGuardianGenderKbn_rdl();

        /** 保護者：メールアドレス1 */
        this.guardianMailAddress1_txt = this.model.getGuardianMailAddress1_txt();

        /** 保護者：メールアドレス2 */
        this.guardianMailAddress2_txt = this.model.getGuardianMailAddress2_txt();

        /** 保護者：メールアドレス3 */
        this.guardianMailAddress3_txt = this.model.getGuardianMailAddress3_txt();

        /** ポイント購入済フラグ */
        this.pointPurchaseFlg = this.model.getPointPurchaseFlg();

        /** DM不要フラグ */
        this.dmNoNeedFlg_chk = this.model.isDmNoNeedFlg_chk();

        /** その他フラグ１ */
        this.other1Flg_chk = this.model.isOther1Flg_chk();

        /** その他フラグ２ */
        this.other2Flg_chk = this.model.isOther2Flg_chk();

        /** その他フラグ３ */
        this.other3Flg_chk = this.model.isOther3Flg_chk();

        /** その他フラグ４ */
        this.other4Flg_chk = this.model.isOther4Flg_chk();

        /** 排他用レコードバージョン */
        this.recordVerNo = String.valueOf(model.getRecordVerNo());

        /** 月謝購入区分 */
        this.setFeeKbn(service.selectFeeKbn(this.model));

        /** 登録者コード */
        String insertUserRole = service.selectRole(this.model.getInsert_cd());

        /** 更新者コード */
        String updateUserRole = service.selectRole(this.model.getUpdate_cd());

        /** パスワード表示フラグ */
        if ((StringUtils.equals(insertUserRole, NaikaraTalkConstants.AUTHORITY_A)
                || StringUtils.equals(insertUserRole, NaikaraTalkConstants.AUTHORITY_M) || StringUtils.equals(
                insertUserRole, NaikaraTalkConstants.AUTHORITY_S))
                && (StringUtils.equals(updateUserRole, NaikaraTalkConstants.AUTHORITY_A)
                        || StringUtils.equals(updateUserRole, NaikaraTalkConstants.AUTHORITY_M) || StringUtils.equals(
                        updateUserRole, NaikaraTalkConstants.AUTHORITY_S))) {
            this.pwdDispFlg = NaikaraTalkConstants.TRUE;
        }
        /** 受講者別月謝購入情報 リターンリスト */
        StudentMonthlyFeePurchaseInformationLoadService studentMonthlyFeePurchaseInformationLoadService = new StudentMonthlyFeePurchaseInformationLoadService();
        StudentMonthlyFeePurchaseInformationModel studentMonthlyFeePurchaseInformationModel = new StudentMonthlyFeePurchaseInformationModel();
        studentMonthlyFeePurchaseInformationModel.setStudentId(model.getStudentId_lbl());
        this.monthlyReturnList = studentMonthlyFeePurchaseInformationLoadService
                .selectList(studentMonthlyFeePurchaseInformationModel);

        if (monthlyReturnList.get(0).getReturnCode() == NaikaraTalkConstants.RETURN_CD_DATA_NO) {
            this.monthlyReturnList = new ArrayList<PointOwnershipTrnDto>();
        }

        /** 特別無償ポイント設定 リターンリスト */
        StudentSpecialFreePointMstLoadService studentSpecialFreePointMstLoadService = new StudentSpecialFreePointMstLoadService();
        StudentSpecialFreePointMstModel studentSpecialFreePointMstModel = new StudentSpecialFreePointMstModel();
        studentSpecialFreePointMstModel.setStudentId(model.getStudentId_lbl());
        this.specialFreeReturnList = studentSpecialFreePointMstLoadService.selectList(studentSpecialFreePointMstModel);

    }
}
