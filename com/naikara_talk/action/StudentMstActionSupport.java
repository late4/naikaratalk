package com.naikara_talk.action;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dto.CodeManagMstDto;
import com.naikara_talk.dto.PointOwnershipTrnDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.model.StudentMstModel;
import com.naikara_talk.mstcache.CodeManagMstCache;
import com.naikara_talk.service.StudentMstLoadService;
import com.naikara_talk.sessiondata.SessionStudentMst;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>マスタ保守<br>
 * <b>クラス名称　　　:</b>受講者マスタメンテナンス【単票】の共通Actionクラス。<br>
 * <b>クラス概要　　　:</b>受講者マスタメンテナンス【単票】の共通Action<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/07/09 TECS 新規作成。
 */
public abstract class StudentMstActionSupport extends NaikaraActionSupport {

    private static final long serialVersionUID = 1L;

    // 画面表示のタイトル
    protected String title = "受講者マスタメンテナンス";

    // Help画面名
    protected String helpPageId = "HelpStudentMst.html";

    /**
     * チェック。<br>
     * <br>
     * コースコードを戻り値で返却する<br>
     * <br>
     * @param なし
     * @return なし
     */
    public void validate() {

        // チェックエラーの場合、国コード、時差地域NO、時間（符号）、サマータイム(時間)(符号)、サマータイムフラグの初期取得。
        try {
            initSelect();
            initRadio();
            initCheckbox();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * サービスの呼び出しの実装。
     */
    abstract protected String requestService() throws NaiException;

    /**
     * ドロップダウンリストを取得する。<br>
     * <br>
     * @param なし
     * @return void
     * @throws Exception
     */
    public void initSelect() throws Exception {

        StudentMstLoadService service = new StudentMstLoadService();
        // 住所(地域)コードを取得する
        this.areaCd_sell = service.selectCodeMst(NaikaraTalkConstants.CODE_CATEGORY_REGION);
        // 住所(都道府県)コードを取得する
        this.prefectureCd_sell = service.selectCodeMst(NaikaraTalkConstants.CODE_CATEGORY_STATE);
        // 職業コードを取得する
        this.occupationCd_sell = service.selectCodeMst(NaikaraTalkConstants.CODE_CATEGORY_OCCUPATION);
    }

    /**
     * ラジオボタンを取得する。<br>
     * <br>
     * @param なし
     * @return void
     * @throws Exception
     */
    public void initRadio() throws Exception {

        StudentMstLoadService service = new StudentMstLoadService();
        // 性別区分
        this.genderKbn_rdll = service.selectCodeMst(NaikaraTalkConstants.CODE_CATEGORY_GENDER);
        // お客様区分
        this.customerKbn_rdll = service.selectCodeMst(NaikaraTalkConstants.CODE_CATEGORY_CUSTOMER_KBN);
        // 保護者の同意書の入手フラグ
        this.consentDocumentAcquisitionFlg_rdll = service
                .selectCodeMst(NaikaraTalkConstants.CODE_CATEGORY_PARENTAL_CONSENT);

    }

    /**
     * チェックボックスを取得する。<br>
     * <br>
     * @param なし
     * @return void
     * @throws Exception
     */
    public void initCheckbox() throws Exception {

        CodeManagMstCache cache = CodeManagMstCache.getInstance();

        // 利用動機リストを取得
        LinkedHashMap<String, CodeManagMstDto> useMotivationList = cache
                .getList(NaikaraTalkConstants.CODE_CATEGORY_USE_MOTIVATION);

        // 利用動機：学業
        this.useMotive_lbl1 = useMotivationList.get(NaikaraTalkConstants.USE_MOTIVATION_1).getManagerNm();

        // 利用動機：仕事
        this.useMotive_lbl2 = useMotivationList.get(NaikaraTalkConstants.USE_MOTIVATION_2).getManagerNm();

        // 利用動機：趣味
        this.useMotive_lbl3 = useMotivationList.get(NaikaraTalkConstants.USE_MOTIVATION_3).getManagerNm();

        // 2013/09/25-Del-Start
        // 利用動機：○○○
        //this.useMotive_lbl4 = useMotivationList.get(NaikaraTalkConstants.USE_MOTIVATION_4).getManagerNm();
        // 2013/09/25-Del-End

        // 利用動機：その他
        this.useMotive_lbl5 = useMotivationList.get(NaikaraTalkConstants.USE_MOTIVATION_5).getManagerNm();

    }

    /**
     * 画面のパラメータをモデルにセット。<br>
     * <br>
     * @param なし
     * @return なし
     */
    public void setupModel() {

        /** search PrimaryKey */
        this.model.setPrimaryKey(this.primaryKey);
        if (!StringUtils.isEmpty(this.primaryKey)) {
            String searchKeys = this.primaryKey.substring(1, this.primaryKey.length() - 1);

            /** 受講者ID */
            this.model.setStudentId_lbl(searchKeys.split(",")[0].toString().trim());
            this.studentId_lbl = searchKeys.split(",")[0].trim();

            /** 有効終了日 */
            this.model.setEffectiveEndDt_lbl(searchKeys.split(",")[1].toString().trim());
            this.effectiveEndDt_lbl = searchKeys.split(",")[1].trim();

            /** ポイント残高 */
            this.model.setBalancePoint_lbl(searchKeys.split(",")[2].toString().trim());
            this.balancePoint_lbl = searchKeys.split(",")[2].trim();
        } else {

            /** 受講者ID */
            this.model.setStudentId_lbl(this.studentId_lbl);

            /** ポイント残高 */
            this.model.setBalancePoint_lbl(this.balancePoint_lbl);

            /** 有効終了日 */
            this.model.setEffectiveEndDt_lbl(this.effectiveEndDt_lbl);
        }

        /** 処理区分(前画面の引き継ぎ情報) */
        this.model.setProcessKbn_rdl(this.processKbn_rdl);

        /** 画面表示処理区分 */
        this.model.setProcessKbn_txt(this.processKbn_txt);

        /** コメン */
        this.model.setComment(this.comment);

        /** 名前(姓) */
        this.model.setFamilyJnm_txt(this.familyJnm_txt);

        /** 名前(名) */
        this.model.setFirstJnm_txt(this.firstJnm_txt);

        /** フリガナ(姓) */
        this.model.setFamilyKnm_txt(this.familyKnm_txt);

        /** フリガナ(名) */
        this.model.setFirstKnm_txt(this.firstKnm_txt);

        /** ローマ字(姓) */
        this.model.setFamilyRomaji_txt(this.familyRomaji_txt);

        /** ローマ字(名) */
        this.model.setFirstRomaji_txt(this.firstRomaji_txt);

        /** ニックネーム */
        this.model.setNickNm_txt(this.nickNm_txt);

        /** 電話番号1 */
        this.model.setTel1_txt(this.tel1_txt);

        /** 電話番号2 */
        this.model.setTel2_txt(this.tel2_txt);

        /** 生年月日：年 */
        this.model.setBirthYyyy_txt(this.birthYyyy_txt);

        /** 生年月日：月 */
        this.model.setBirthMm_txt(this.birthMm_txt);

        /** 生年月日：日 */
        this.model.setBirthDd_txt(this.birthDd_txt);

        /** 郵便番号 */
        this.model.setZipCd_txt(this.zipCd_txt);

        /** 住所(地域)コード */
        this.model.setAddressAreaCd_sel(this.addressAreaCd_sel);

        /** 住所(都道府県)コード */
        this.model.setAddressPrefectureCd_sel(this.addressPrefectureCd_sel);

        /** 住所(市区町村 等) */
        this.model.setAddressCity_txt(this.addressCity_txt);

        /** 住所(ﾋﾞﾙ、ﾏﾝｼｮﾝ名 等) */
        this.model.setAddressOthers_txt(this.addressOthers_txt);

        /** 性別区分 */
        this.model.setGenderKbn_rdl(this.genderKbn_rdl);

        /** メールアドレス1 */
        this.model.setMailAddress1_txt(this.mailAddress1_txt);

        /** メールアドレス2 */
        this.model.setMailAddress2_txt(this.mailAddress2_txt);

        /** メールアドレス3 */
        this.model.setMailAddress3_txt(this.mailAddress3_txt);

        /** パスワード */
        this.model.setPassword_txt(this.password_txt);

        /** ログイン回数 */
        this.model.setLoginNum_lbl(this.loginNum_lbl);

        /** 最終ログイン日 */
        this.model.setEndLoginDt_lbl(this.endLoginDt_lbl);

        /** 職業コード */
        this.model.setOccupationCd_sel(this.occupationCd_sel);

        /** 顧客区分 */
        this.model.setCustomerKbn_rdl(this.customerKbn_rdl);

        /** 組織名 */
        this.model.setOrganizationNm_txt(this.organizationNm_txt);

        /** 組織ID */
        this.model.setOrganizationId_lbl(this.organizationId_lbl);

        /** 受講者所属部署 */
        this.model.setStudentPosition_lbl(this.studentPosition_lbl);

        /** 所属組織内ID */
        this.model.setPositionOrganizationId_lbl(this.positionOrganizationId_lbl);

        /** 自己負担率 */
        this.model.setBurdenNum_lbl(this.burdenNum_lbl);

        /** 利用開始日 */
        this.model.setUseStrDt_txt(this.useStrDt_txt);

        /** 利用終了日 */
        this.model.setUseEndDt_txt(this.useEndDt_txt);

        /** 利用停止フラグ */
        this.model.setUseStopFlg(this.useStopFlg);

        /** 利用動機フラグ１ */
        this.model.setUseMotiveFlg_chk1(this.useMotiveFlg_chk1);
        this.model.setUseMotive_lbl1(this.useMotive_lbl1);

        /** 利用動機フラグ2 */
        this.model.setUseMotiveFlg_chk2(this.useMotiveFlg_chk2);
        this.model.setUseMotive_lbl2(this.useMotive_lbl2);

        /** 利用動機フラグ3 */
        this.model.setUseMotiveFlg_chk3(this.useMotiveFlg_chk3);
        this.model.setUseMotive_lbl3(this.useMotive_lbl3);

//2013/09/25-Del-Start
        /** 利用動機フラグ4 */
//        this.model.setUseMotiveFlg_chk4(this.useMotiveFlg_chk4);
//        this.model.setUseMotive_lbl4(this.useMotive_lbl4);
//2013/09/25-Del-End


        /** 利用動機フラグ5 */
        this.model.setUseMotiveFlg_chk5(this.useMotiveFlg_chk5);
        this.model.setUseMotive_lbl5(this.useMotive_lbl5);

        /** 利用動機テキスト */
        this.model.setUseMotive_txt(this.useMotive_txt);

        /** 達成目標 */
        this.model.setAchievement_txt(this.achievement_txt);

        /** 利用規約に同意する：チェックフラグ */
        this.model.setUseAgreement_chk(this.useAgreement_chk);

        /** 個人情報の同意：チェックフラグ */
        this.model.setIndividualAgreement_chk(this.individualAgreement_chk);

        /** ｽｸｰﾙからのｺﾒﾝﾄ(生徒公開) */
        this.model.setSchoolCmt_txa(this.schoolCmt_txa);

        /** 備考(生徒非公開) */
        this.model.setRemark_txa(this.remark_txa);

        /** 保護者の同意書の入手フラグ */
        this.model.setConsentDocumentAcquisitionFlg_rdl(this.consentDocumentAcquisitionFlg_rdl);

        /** 保護者：名前(姓) */
        this.model.setGuardianFamilyJnm_txt(this.guardianFamilyJnm_txt);

        /** 保護者：名前(名） */
        this.model.setGuardianFirstJnm_txt(this.guardianFirstJnm_txt);

        /** 保護者：フリガナ(姓) */
        this.model.setGuardianFamilyKnm_txt(this.guardianFamilyKnm_txt);

        /** 保護者：フリガナ(名) */
        this.model.setGuardianFirstKnm_txt(this.guardianFirstKnm_txt);

        /** あなたとの続柄 */
        this.model.setGuardianFamilyRelationship_txt(this.guardianFamilyRelationship_txt);

        /** 保護者：電話番号1 */
        this.model.setGuardianTel1_txt(this.guardianTel1_txt);

        /** 保護者：電話番号2 */
        this.model.setGuardianTel2_txt(this.guardianTel2_txt);

        /** 保護者：生年月日：年 */
        this.model.setGuardianBirthYyyy_txt(this.guardianBirthYyyy_txt);

        /** 保護者：生年月日：月 */
        this.model.setGuardianBirthMm_txt(this.guardianBirthMm_txt);

        /** 保護者：生年月日：日 */
        this.model.setGuardianBirthDd_txt(this.guardianBirthDd_txt);

        /** 保護者：郵便番号 */
        this.model.setGuardianZipCd_txt(this.guardianZipCd_txt);

        /** 保護者：住所(地域)コード */
        this.model.setGuardianAddressAreaCd_sel(this.guardianAddressAreaCd_sel);

        /** 保護者：住所(都道府県)コード */
        this.model.setGuardianAddressPrefectureCd_sel(this.guardianAddressPrefectureCd_sel);

        /** 保護者：住所(市区町村 等) */
        this.model.setGuardianAddressCity_txt(this.guardianAddressCity_txt);

        /** 保護者：住所(ﾋﾞﾙ、ﾏﾝｼｮﾝ名 等) */
        this.model.setGuardianAddressOthers_txt(this.guardianAddressOthers_txt);

        /** 保護者：性別区分 */
        this.model.setGuardianGenderKbn_rdl(this.guardianGenderKbn_rdl);

        /** 保護者：メールアドレス1 */
        this.model.setGuardianMailAddress1_txt(this.guardianMailAddress1_txt);

        /** 保護者：メールアドレス2 */
        this.model.setGuardianMailAddress2_txt(this.guardianMailAddress2_txt);

        /** 保護者：メールアドレス3 */
        this.model.setGuardianMailAddress3_txt(this.guardianMailAddress3_txt);

        /** ポイント購入済フラグ */
        this.model.setPointPurchaseFlg(this.pointPurchaseFlg);

        /** DM不要フラグ */
        this.model.setDmNoNeedFlg_chk(this.dmNoNeedFlg_chk);

        /** その他フラグ１ */
        this.model.setOther1Flg_chk(this.other1Flg_chk);

        /** その他フラグ２ */
        this.model.setOther2Flg_chk(this.other2Flg_chk);

        /** その他フラグ３ */
        this.model.setOther3Flg_chk(this.other3Flg_chk);

        /** その他フラグ４ */
        this.model.setOther4Flg_chk(this.other4Flg_chk);

        /** 排他用レコードバージョン */
        String recVerNo = StringUtils.isEmpty(this.recordVerNo) ? "0" : this.recordVerNo;
        this.model.setRecordVerNo(Integer.parseInt(recVerNo));

        /** 受講者別月謝購入情報リスト */
        this.model.setMonthlyReturnList(this.monthlyReturnList);

        /** 特別無償ポイント設定リスト */
        this.model.setSpecialFreeReturnList(this.specialFreeReturnList);

        /** 月謝購入区分 */
        this.model.setFeeKbn(this.feeKbn);

        /** パスワード表示フラグ */
        this.model.setPwdDispFlg(this.pwdDispFlg);

    }

    /**
     * Model値・modelToSessionStudentMst値をmodelToSessionStudentMstにセット。<br>
     * <br>
     * @throws Exception
     */
    public void modelToSessionStudentMst() {

        // 戻る用に必要な情報を格納
        SessionStudentMst sessionData;
        if ((SessionStudentMst) SessionDataUtil.getSessionData(SessionStudentMst.class.toString()) != null) {
            sessionData = (SessionStudentMst) SessionDataUtil.getSessionData(SessionStudentMst.class.toString());
        } else {
            sessionData = new SessionStudentMst();
        }
        sessionData.setReturnOnFlg(true);                                                                         // 戻る判定Onフラグ
        sessionData.setProcessKbn_rdl(this.model.getProcessKbn_rdl());                                            // 処理区分
        sessionData.setProcessKbn_txt(this.model.getProcessKbn_txt());                                            // 処理区分名
        sessionData.setStudentId_lbl(this.model.getStudentId_lbl());                                              // 受講者ID
        sessionData.setFamilyJnm_txt(this.model.getFamilyJnm_txt());                                              // 名前(姓)
        sessionData.setFirstJnm_txt(this.model.getFirstJnm_txt());                                                // 名前(名)
        sessionData.setFamilyKnm_txt(this.model.getFamilyKnm_txt());                                              // フリガナ(姓)
        sessionData.setFirstKnm_txt(this.model.getFirstKnm_txt());                                                // フリガナ(名)
        sessionData.setFamilyRomaji_txt(this.model.getFamilyRomaji_txt());                                        // ローマ字(姓)
        sessionData.setFirstRomaji_txt(this.model.getFirstRomaji_txt());                                          // ローマ字(名)
        sessionData.setNickNm_txt(this.model.getNickNm_txt());                                                    // ニックネーム
        sessionData.setPassword_txt(this.model.getPassword_txt());                                                // パスワード
        sessionData.setTel1_txt(this.model.getTel1_txt());                                                        // 電話番号1
        sessionData.setTel2_txt(this.model.getTel2_txt());                                                        // 電話番号2
        sessionData.setBirthYyyy_txt(this.model.getBirthYyyy_txt());                                              // 生年月日：年
        sessionData.setBirthMm_txt(this.model.getBirthMm_txt());                                                  // 生年月日：月
        sessionData.setBirthDd_txt(this.model.getBirthDd_txt());                                                  // 生年月日：日
        sessionData.setZipCd_txt(this.model.getZipCd_txt());                                                      // 郵便番号
        sessionData.setAddressAreaCd_sel(this.model.getAddressAreaCd_sel());                                      // 住所(地域)コード
        sessionData.setAddressPrefectureCd_sel(this.model.getAddressPrefectureCd_sel());                          // 住所(都道府県)コード
        sessionData.setAddressCity_txt(this.model.getAddressCity_txt());                                          // 住所(市区町村等)
        sessionData.setAddressOthers_txt(this.model.getAddressOthers_txt());                                      // 住所(ﾋﾞﾙ、ﾏﾝｼｮﾝ名等)
        sessionData.setGenderKbn_rdl(this.model.getGenderKbn_rdl());                                              // 性別区分
        sessionData.setMailAddress1_txt(this.model.getMailAddress1_txt());                                        // メールアドレス1
        sessionData.setMailAddress2_txt(this.model.getMailAddress2_txt());                                        // メールアドレス2
        sessionData.setMailAddress3_txt(this.model.getMailAddress3_txt());                                        // メールアドレス3
        sessionData.setLoginNum_lbl(this.model.getLoginNum_lbl());                                                // ログイン回数
        sessionData.setEndLoginDt_lbl(this.model.getEndLoginDt_lbl());                                            // 最終ログイン日
        sessionData.setOccupationCd_sel(this.model.getOccupationCd_sel());                                        // 職業コード
        sessionData.setCustomerKbn_rdl(this.model.getCustomerKbn_rdl());                                          // 顧客区分
        sessionData.setOrganizationNm_txt(this.model.getOrganizationNm_txt());                                    // 組織名
        sessionData.setUseStrDt_txt(this.model.getUseStrDt_txt());                                                // 利用開始日
        sessionData.setUseEndDt_txt(this.model.getUseEndDt_txt());                                                // 利用終了日
        sessionData.setUseStopFlg(this.model.getUseStopFlg());                                                    // 利用停止フラグ
        sessionData.setUseMotiveFlg_chk1(this.model.isUseMotiveFlg_chk1());                                       // 利用動機フラグ１
        sessionData.setUseMotive_lbl1(this.model.getUseMotive_lbl1());                                            // 利用動機フラグ１(名)
        sessionData.setUseMotiveFlg_chk2(this.model.isUseMotiveFlg_chk2());                                       // 利用動機フラグ２
        sessionData.setUseMotive_lbl2(this.model.getUseMotive_lbl2());                                            // 利用動機フラグ２(名)
        sessionData.setUseMotiveFlg_chk3(this.model.isUseMotiveFlg_chk3());                                       // 利用動機フラグ３
        sessionData.setUseMotive_lbl3(this.model.getUseMotive_lbl3());                                            // 利用動機フラグ３(名)

//2013/09/25-Del-Start
//        sessionData.setUseMotiveFlg_chk4(this.model.isUseMotiveFlg_chk4());                                       // 利用動機フラグ４
//        sessionData.setUseMotive_lbl4(this.model.getUseMotive_lbl4());                                            // 利用動機フラグ４(名)
//2013/09/25-Del-End


        sessionData.setUseMotiveFlg_chk5(this.model.isUseMotiveFlg_chk5());                                       // 利用動機フラグ５
        sessionData.setUseMotive_lbl5(this.model.getUseMotive_lbl5());                                            // 利用動機フラグ５(名)
        sessionData.setUseMotive_txt(this.model.getUseMotive_txt());                                              // 利用動機テキスト
        sessionData.setAchievement_txt(this.model.getAchievement_txt());                                          // 達成目標
        sessionData.setUseAgreement_chk(this.model.isUseAgreement_chk());                                         // 利用規約に同意する：チェックフラ
        sessionData.setIndividualAgreement_chk(this.model.isIndividualAgreement_chk());                           // 個人情報の同意：チェックフラグ
        sessionData.setSchoolCmt_txa(this.model.getSchoolCmt_txa());                                              // ｽｸｰﾙからのｺﾒﾝﾄ(生徒公開)
        sessionData.setRemark_txa(this.model.getRemark_txa());                                                    // 備考(生徒非公開)
        sessionData.setOrganizationId_lbl(this.model.getOrganizationId_lbl());                                        // 組織ID
        sessionData.setPositionOrganizationId_lbl(this.model.getPositionOrganizationId_lbl());                    // 所属組織内ID
        sessionData.setStudentPosition_lbl(this.model.getStudentPosition_lbl());                                  // 受講者所属部署
        sessionData.setBurdenNum_lbl(this.model.getBurdenNum_lbl());                                              // 自己負担率
        sessionData.setConsentDocumentAcquisitionFlg_rdl(this.model.getConsentDocumentAcquisitionFlg_rdl());      // 保護者の同意書の入手フラグ
        sessionData.setPointPurchaseFlg(this.model.getPointPurchaseFlg());                                        // ポイント購入済フラグ
        sessionData.setDmNoNeedFlg_chk(this.model.isDmNoNeedFlg_chk());                                           // DM不要フラグ
        sessionData.setOther1Flg_chk(this.model.isOther1Flg_chk());                                               // その他フラグ１
        sessionData.setOther2Flg_chk(this.model.isOther2Flg_chk());                                               // その他フラグ２
        sessionData.setOther3Flg_chk(this.model.isOther3Flg_chk());                                               // その他フラグ３
        sessionData.setOther4Flg_chk(this.model.isOther4Flg_chk());                                               // その他フラグ４
        sessionData.setGuardianFamilyJnm_txt(this.model.getGuardianFamilyJnm_txt());                              // 保護者：名前(姓)
        sessionData.setGuardianFirstJnm_txt(this.model.getGuardianFirstJnm_txt());                                // 保護者：名前(名）
        sessionData.setGuardianFamilyKnm_txt(this.model.getGuardianFamilyKnm_txt());                              // 保護者：フリガナ(姓)
        sessionData.setGuardianFirstKnm_txt(this.model.getGuardianFirstKnm_txt());                                // 保護者：フリガナ(名）
        sessionData.setGuardianFamilyRelationship_txt(this.model.getGuardianFamilyRelationship_txt());            // あなたとの続柄
        sessionData.setGuardianTel1_txt(this.model.getGuardianTel1_txt());                                        // 保護者：電話番号1
        sessionData.setGuardianTel2_txt(this.model.getGuardianTel2_txt());                                        // 保護者：電話番号2
        sessionData.setGuardianBirthYyyy_txt(this.model.getGuardianBirthYyyy_txt());                              // 保護者：生年月日：年
        sessionData.setGuardianBirthMm_txt(this.model.getGuardianBirthMm_txt());                                  // 保護者：生年月日：月
        sessionData.setGuardianBirthDd_txt(this.model.getGuardianBirthDd_txt());                                  // 保護者：生年月日：日
        sessionData.setGuardianZipCd_txt(this.model.getGuardianZipCd_txt());                                      // 保護者：郵便番号
        sessionData.setGuardianAddressAreaCd_sel(this.model.getGuardianAddressAreaCd_sel());                      // 保護者：住所(地域)コード
        sessionData.setGuardianAddressPrefectureCd_sel(this.model.getGuardianAddressPrefectureCd_sel());          // 保護者：住所(都道府県)コード
        sessionData.setGuardianAddressCity_txt(this.model.getGuardianAddressCity_txt());                          // 保護者：住所(市区町村等)
        sessionData.setGuardianAddressOthers_txt(this.model.getGuardianAddressOthers_txt());                      // 保護者：住所(ﾋﾞﾙ、ﾏﾝｼｮﾝ名等)
        sessionData.setGuardianGenderKbn_rdl(this.model.getGuardianGenderKbn_rdl());                              // 保護者：性別区分
        sessionData.setGuardianMailAddress1_txt(this.model.getGuardianMailAddress1_txt());                        // 保護者：メールアドレス1
        sessionData.setGuardianMailAddress2_txt(this.model.getGuardianMailAddress2_txt());                        // 保護者：メールアドレス2
        sessionData.setGuardianMailAddress3_txt(this.model.getGuardianMailAddress3_txt());                        // 保護者：メールアドレス3
        sessionData.setRecordVerNo(Integer.valueOf(this.model.getRecordVerNo()));                                 // レコードバージョン番号
        sessionData.setPurchasePoint(this.model.getBalancePoint_lbl());                                           // ポイント残高
        sessionData.setPointDffectEndDt(this.model.getEffectiveEndDt_lbl());                                      // 最長のポイント有効終了日
        sessionData.setReturnOnFlg(true);                                                                         // 戻る判定Onフラグ
        sessionData.setPwdDispFlg(this.model.getPwdDispFlg());                                                    // パスワード表示フラグ
        sessionData.setFeeKbn(this.model.getFeeKbn());                                                            // 月謝購入区分

        SessionDataUtil.setSessionData(sessionData);

    }

    /**
     * Model値・sessionStudentMstToModel値をsessionStudentMstToModelにセット。<br>
     * <br>
     * @throws Exception
     */
    public void sessionStudentMstToModel() {

        // 戻る用に必要な情報を格納

        SessionStudentMst sessionData = (SessionStudentMst) SessionDataUtil.getSessionData(SessionStudentMst.class
                .toString());

        this.setProcessKbn_rdl(sessionData.getProcessKbn_rdl());                                            // 処理区分
        this.setProcessKbn_txt(sessionData.getProcessKbn_txt());                                            // 処理区分名
        this.setStudentId_lbl(sessionData.getStudentId_lbl());                                              // 受講者ID
        this.setFamilyJnm_txt(sessionData.getFamilyJnm_txt());                                              // 名前(姓)
        this.setFirstJnm_txt(sessionData.getFirstJnm_txt());                                                // 名前(名)
        this.setFamilyKnm_txt(sessionData.getFamilyKnm_txt());                                              // フリガナ(姓)
        this.setFirstKnm_txt(sessionData.getFirstKnm_txt());                                                // フリガナ(名)
        this.setFamilyRomaji_txt(sessionData.getFamilyRomaji_txt());                                        // ローマ字(姓)
        this.setFirstRomaji_txt(sessionData.getFirstRomaji_txt());                                          // ローマ字(名)
        this.setNickNm_txt(sessionData.getNickNm_txt());                                                    // ニックネーム
        this.setPassword_txt(sessionData.getPassword_txt());                                                // パスワード
        this.setTel1_txt(sessionData.getTel1_txt());                                                        // 電話番号1
        this.setTel2_txt(sessionData.getTel2_txt());                                                        // 電話番号2
        this.setBirthYyyy_txt(sessionData.getBirthYyyy_txt());                                              // 生年月日：年
        this.setBirthMm_txt(sessionData.getBirthMm_txt());                                                  // 生年月日：月
        this.setBirthDd_txt(sessionData.getBirthDd_txt());                                                  // 生年月日：日
        this.setZipCd_txt(sessionData.getZipCd_txt());                                                      // 郵便番号
        this.setAddressAreaCd_sel(sessionData.getAddressAreaCd_sel());                                      // 住所(地域)コード
        this.setAddressPrefectureCd_sel(sessionData.getAddressPrefectureCd_sel());                          // 住所(都道府県)コード
        this.setAddressCity_txt(sessionData.getAddressCity_txt());                                          // 住所(市区町村等)
        this.setAddressOthers_txt(sessionData.getAddressOthers_txt());                                      // 住所(ﾋﾞﾙ、ﾏﾝｼｮﾝ名等)
        this.setGenderKbn_rdl(sessionData.getGenderKbn_rdl());                                              // 性別区分
        this.setMailAddress1_txt(sessionData.getMailAddress1_txt());                                        // メールアドレス1
        this.setMailAddress2_txt(sessionData.getMailAddress2_txt());                                        // メールアドレス2
        this.setMailAddress3_txt(sessionData.getMailAddress3_txt());                                        // メールアドレス3
        this.setLoginNum_lbl(sessionData.getLoginNum_lbl());                                                // ログイン回数
        this.setEndLoginDt_lbl(sessionData.getEndLoginDt_lbl());                                            // 最終ログイン日
        this.setOccupationCd_sel(sessionData.getOccupationCd_sel());                                        // 職業コード
        this.setCustomerKbn_rdl(sessionData.getCustomerKbn_rdl());                                          // 顧客区分
        this.setOrganizationNm_txt(sessionData.getOrganizationNm_txt());                                    // 組織名
        this.setUseStrDt_txt(sessionData.getUseStrDt_txt());                                                // 利用開始日
        this.setUseEndDt_txt(sessionData.getUseEndDt_txt());                                                // 利用終了日
        this.setUseStopFlg(sessionData.getUseStopFlg());                                                    // 利用停止フラグ
        this.setUseMotiveFlg_chk1(sessionData.isUseMotiveFlg_chk1());                                       // 利用動機フラグ１
        this.setUseMotive_lbl1(sessionData.getUseMotive_lbl1());                                            // 利用動機フラグ１(名)
        this.setUseMotiveFlg_chk2(sessionData.isUseMotiveFlg_chk2());                                       // 利用動機フラグ２
        this.setUseMotive_lbl2(sessionData.getUseMotive_lbl2());                                            // 利用動機フラグ２(名)
        this.setUseMotiveFlg_chk3(sessionData.isUseMotiveFlg_chk3());                                       // 利用動機フラグ３
        this.setUseMotive_lbl3(sessionData.getUseMotive_lbl3());                                            // 利用動機フラグ３(名)

//2013/09/25-Del-Start
//        this.setUseMotiveFlg_chk4(sessionData.isUseMotiveFlg_chk4());                                       // 利用動機フラグ４
//        this.setUseMotive_lbl4(sessionData.getUseMotive_lbl4());                                            // 利用動機フラグ４(名)
//2013/09/25-Del-End


        this.setUseMotiveFlg_chk5(sessionData.isUseMotiveFlg_chk5());                                       // 利用動機フラグ５
        this.setUseMotive_lbl5(sessionData.getUseMotive_lbl5());                                            // 利用動機フラグ５(名)
        this.setUseMotive_txt(sessionData.getUseMotive_txt());                                              // 利用動機テキスト
        this.setAchievement_txt(sessionData.getAchievement_txt());                                          // 達成目標
        this.setUseAgreement_chk(sessionData.isUseAgreement_chk());                                         // 利用規約に同意する：チェックフラ
        this.setIndividualAgreement_chk(sessionData.isIndividualAgreement_chk());                           // 個人情報の同意：チェックフラグ
        this.setSchoolCmt_txa(sessionData.getSchoolCmt_txa());                                              // ｽｸｰﾙからのｺﾒﾝﾄ(生徒公開)
        this.setRemark_txa(sessionData.getRemark_txa());                                                    // 備考(生徒非公開)
        this.setOrganizationId_lbl(sessionData.getOrganizationId());                                        // 組織ID
        this.setPositionOrganizationId_lbl(sessionData.getPositionOrganizationId_lbl());                    // 所属組織内ID
        this.setStudentPosition_lbl(sessionData.getStudentPosition_lbl());                                  // 受講者所属部署
        this.setBurdenNum_lbl(sessionData.getBurdenNum_lbl());                                              // 自己負担率
        this.setConsentDocumentAcquisitionFlg_rdl(sessionData.getConsentDocumentAcquisitionFlg_rdl());      // 保護者の同意書の入手フラグ
        this.setPointPurchaseFlg(sessionData.getPointPurchaseFlg());                                        // ポイント購入済フラグ
        this.setDmNoNeedFlg_chk(sessionData.isDmNoNeedFlg_chk());                                           // DM不要フラグ
        this.setOther1Flg_chk(sessionData.isOther1Flg_chk());                                               // その他フラグ１
        this.setOther2Flg_chk(sessionData.isOther2Flg_chk());                                               // その他フラグ２
        this.setOther3Flg_chk(sessionData.isOther3Flg_chk());                                               // その他フラグ３
        this.setOther4Flg_chk(sessionData.isOther4Flg_chk());                                               // その他フラグ４
        this.setGuardianFamilyJnm_txt(sessionData.getGuardianFamilyJnm_txt());                              // 保護者：名前(姓)
        this.setGuardianFirstJnm_txt(sessionData.getGuardianFirstJnm_txt());                                // 保護者：名前(名）
        this.setGuardianFamilyKnm_txt(sessionData.getGuardianFamilyKnm_txt());                              // 保護者：フリガナ(姓)
        this.setGuardianFirstKnm_txt(sessionData.getGuardianFirstKnm_txt());                                // 保護者：フリガナ(名）
        this.setGuardianFamilyRelationship_txt(sessionData.getGuardianFamilyRelationship_txt());            // あなたとの続柄
        this.setGuardianTel1_txt(sessionData.getGuardianTel1_txt());                                        // 保護者：電話番号1
        this.setGuardianTel2_txt(sessionData.getGuardianTel2_txt());                                        // 保護者：電話番号2
        this.setGuardianBirthYyyy_txt(sessionData.getGuardianBirthYyyy_txt());                              // 保護者：生年月日：年
        this.setGuardianBirthMm_txt(sessionData.getGuardianBirthMm_txt());                                  // 保護者：生年月日：月
        this.setGuardianBirthDd_txt(sessionData.getGuardianBirthDd_txt());                                  // 保護者：生年月日：日
        this.setGuardianZipCd_txt(sessionData.getGuardianZipCd_txt());                                      // 保護者：郵便番号
        this.setGuardianAddressAreaCd_sel(sessionData.getGuardianAddressAreaCd_sel());                      // 保護者：住所(地域)コード
        this.setGuardianAddressPrefectureCd_sel(sessionData.getGuardianAddressPrefectureCd_sel());          // 保護者：住所(都道府県)コード
        this.setGuardianAddressCity_txt(sessionData.getGuardianAddressCity_txt());                          // 保護者：住所(市区町村等)
        this.setGuardianAddressOthers_txt(sessionData.getGuardianAddressOthers_txt());                      // 保護者：住所(ﾋﾞﾙ、ﾏﾝｼｮﾝ名等)
        this.setGuardianGenderKbn_rdl(sessionData.getGuardianGenderKbn_rdl());                              // 保護者：性別区分
        this.setGuardianMailAddress1_txt(sessionData.getGuardianMailAddress1_txt());                        // 保護者：メールアドレス1
        this.setGuardianMailAddress2_txt(sessionData.getGuardianMailAddress2_txt());                        // 保護者：メールアドレス2
        this.setGuardianMailAddress3_txt(sessionData.getGuardianMailAddress3_txt());                        // 保護者：メールアドレス3
        this.setRecordVerNo(String.valueOf(sessionData.getRecordVerNo()));                                  // レコードバージョン番号
        this.setMonthlyReturnList(sessionData.getMonthlyReturnList());                                      // 受講者別月謝購入情報リスト
        this.setSpecialFreeReturnList(sessionData.getSpecialFreeReturnList());                              // 特別無償ポイント設定リスト
        this.setBalancePoint_lbl(sessionData.getPurchasePoint());                                           // ポイント残高
        this.setEffectiveEndDt_lbl(sessionData.getPointDffectEndDt());                                      // 最長のポイント有効終了日
        this.setPwdDispFlg(sessionData.getPwdDispFlg());                                                    // パスワード表示フラグ
        this.setFeeKbn(sessionData.getFeeKbn());                                                            // 月謝購入区分

    }

    /** メッセージ */
    protected String message;

    /** 処理区分(前画面の引き継ぎ情報) */
    protected String processKbn_rdl;

    /** 画面表示処理区分 */
    protected String processKbn_txt;

    /** コメン */
    protected String comment;

    /** 受講者ID */
    protected String studentId_lbl;

    /** 名前(姓) */
    protected String familyJnm_txt;

    /** 名前(名) */
    protected String firstJnm_txt;

    /** フリガナ(姓) */
    protected String familyKnm_txt;

    /** フリガナ(名) */
    protected String firstKnm_txt;

    /** ローマ字(姓) */
    protected String familyRomaji_txt;

    /** ローマ字(名) */
    protected String firstRomaji_txt;

    /** ニックネーム */
    protected String nickNm_txt;

    /** 電話番号1 */
    protected String tel1_txt;

    /** 電話番号2 */
    protected String tel2_txt;

    /** 生年月日：年 */
    protected String birthYyyy_txt;

    /** 生年月日：月 */
    protected String birthMm_txt;

    /** 生年月日：日 */
    protected String birthDd_txt;

    /** 郵便番号 */
    protected String zipCd_txt;

    /** 住所(地域)コード */
    protected String addressAreaCd_sel;

    /** 住所(都道府県)コード */
    protected String addressPrefectureCd_sel;

    /** 住所(市区町村 等) */
    protected String addressCity_txt;

    /** 住所(ﾋﾞﾙ、ﾏﾝｼｮﾝ名 等) */
    protected String addressOthers_txt;

    /** 性別区分 */
    protected String genderKbn_rdl;

    /** メールアドレス1 */
    protected String mailAddress1_txt;

    /** メールアドレス2 */
    protected String mailAddress2_txt;

    /** メールアドレス3 */
    protected String mailAddress3_txt;

    /** パスワード */
    protected String password_txt;

    /** ログイン回数 */
    protected String loginNum_lbl;

    /** 最終ログイン日 */
    protected String endLoginDt_lbl;

    /** 職業コード */
    protected String occupationCd_sel;
    protected Map<String, String> occupationCd_sell = new LinkedHashMap<String, String>();

    /** 顧客区分 */
    protected String customerKbn_rdl;
    protected Map<String, String> customerKbn_rdll = new LinkedHashMap<String, String>();

    /** 組織名 */
    protected String organizationNm_txt;

    /** 組織ID */
    protected String organizationId_lbl;

    /** 受講者所属部署 */
    protected String studentPosition_lbl;

    /** 所属組織内ID */
    protected String positionOrganizationId_lbl;

    /** 自己負担率 */
    protected String burdenNum_lbl;

    /** 月謝購入区分 */
    protected String feeKbn;

    /** ポイント残高 */
    protected String balancePoint_lbl;

    /** 有効終了日 */
    protected String effectiveEndDt_lbl;

    /** 利用開始日 */
    protected String useStrDt_txt;

    /** 利用終了日 */
    protected String useEndDt_txt;

    /** 利用停止フラグ */
    protected String useStopFlg;

    /** 利用動機フラグ１ */
    protected boolean useMotiveFlg_chk1;
    protected String useMotive_lbl1;

    /** 利用動機フラグ2 */
    protected boolean useMotiveFlg_chk2;
    protected String useMotive_lbl2;

    /** 利用動機フラグ3 */
    protected boolean useMotiveFlg_chk3;
    protected String useMotive_lbl3;

//2013/09/25-Del-Start
    /** 利用動機フラグ4 */
//    protected boolean useMotiveFlg_chk4;
//    protected String useMotive_lbl4;
//2013/09/25-Del-End

    /** 利用動機フラグ5 */
    protected boolean useMotiveFlg_chk5;
    protected String useMotive_lbl5;

    /** 利用動機テキスト */
    protected String useMotive_txt;

    /** 達成目標 */
    protected String achievement_txt;

    /** 利用規約に同意する：チェックフラグ */
    protected boolean useAgreement_chk;

    /** 個人情報の同意：チェックフラグ */
    protected boolean individualAgreement_chk;

    /** ｽｸｰﾙからのｺﾒﾝﾄ(生徒公開) */
    protected String schoolCmt_txa;

    /** 備考(生徒非公開) */
    protected String remark_txa;

    /** 保護者の同意書の入手フラグ */
    protected String consentDocumentAcquisitionFlg_rdl;
    protected Map<String, String> consentDocumentAcquisitionFlg_rdll = new LinkedHashMap<String, String>();

    /** 保護者：名前(姓) */
    protected String guardianFamilyJnm_txt;

    /** 保護者：名前(名） */
    protected String guardianFirstJnm_txt;

    /** 保護者：フリガナ(姓) */
    protected String guardianFamilyKnm_txt;

    /** 保護者：フリガナ(名) */
    protected String guardianFirstKnm_txt;

    /** あなたとの続柄 */
    protected String guardianFamilyRelationship_txt;

    /** 保護者：電話番号1 */
    protected String guardianTel1_txt;

    /** 保護者：電話番号2 */
    protected String guardianTel2_txt;

    /** 保護者：生年月日：年 */
    protected String guardianBirthYyyy_txt;

    /** 保護者：生年月日：月 */
    protected String guardianBirthMm_txt;

    /** 保護者：生年月日：日 */
    protected String guardianBirthDd_txt;

    /** 保護者：郵便番号 */
    protected String guardianZipCd_txt;

    /** 保護者：住所(地域)コード */
    protected String guardianAddressAreaCd_sel;

    /** 保護者：住所(都道府県)コード */
    protected String guardianAddressPrefectureCd_sel;

    /** 保護者：住所(市区町村 等) */
    protected String guardianAddressCity_txt;

    /** 保護者：住所(ﾋﾞﾙ、ﾏﾝｼｮﾝ名 等) */
    protected String guardianAddressOthers_txt;

    /** 保護者：性別区分 */
    protected String guardianGenderKbn_rdl;

    /** 保護者：メールアドレス1 */
    protected String guardianMailAddress1_txt;

    /** 保護者：メールアドレス2 */
    protected String guardianMailAddress2_txt;

    /** 保護者：メールアドレス3 */
    protected String guardianMailAddress3_txt;

    /** ポイント購入済フラグ */
    protected String pointPurchaseFlg;

    /** DM不要フラグ */
    protected boolean dmNoNeedFlg_chk;

    /** その他フラグ１ */
    protected boolean other1Flg_chk;

    /** その他フラグ２ */
    protected boolean other2Flg_chk;

    /** その他フラグ３ */
    protected boolean other3Flg_chk;

    /** その他フラグ４ */
    protected boolean other4Flg_chk;

    /** (地域)コード */
    protected Map<String, String> areaCd_sell = new LinkedHashMap<String, String>();

    /** (都道府県)コード */
    protected Map<String, String> prefectureCd_sell = new LinkedHashMap<String, String>();

    /** 性別区分コード */
    protected Map<String, String> genderKbn_rdll = new LinkedHashMap<String, String>();

    /** 排他用レコードバージョン */
    protected String recordVerNo;

    /** 処理結果 */
    protected StudentMstModel model = new StudentMstModel();

    /** search Primary Key */
    protected String primaryKey;

    /** 受講者別月謝購入情報 リターンリスト */
    protected List<PointOwnershipTrnDto> monthlyReturnList = new ArrayList<PointOwnershipTrnDto>();

    /** 特別無償ポイント設定 リターンリスト */
    protected List<PointOwnershipTrnDto> specialFreeReturnList = new ArrayList<PointOwnershipTrnDto>();

    /** パスワード表示フラグ */
    protected String pwdDispFlg;

    /** 戻る判定Onフラグ */
    protected boolean returnOnFlg;

    /**
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title セットする title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return helpPageId
     */
    public String getHelpPageId() {
        return helpPageId;
    }

    /**
     * @param helpPageId セットする helpPageId
     */
    public void setHelpPageId(String helpPageId) {
        this.helpPageId = helpPageId;
    }

    /**
     * @return message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message セットする message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return processKbn_rdl
     */
    public String getProcessKbn_rdl() {
        return processKbn_rdl;
    }

    /**
     * @param processKbn_rdl セットする processKbn_rdl
     */
    public void setProcessKbn_rdl(String processKbn_rdl) {
        this.processKbn_rdl = processKbn_rdl;
    }

    /**
     * @return processKbn_txt
     */
    public String getProcessKbn_txt() {
        return processKbn_txt;
    }

    /**
     * @param processKbn_txt セットする processKbn_txt
     */
    public void setProcessKbn_txt(String processKbn_txt) {
        this.processKbn_txt = processKbn_txt;
    }

    /**
     * @return comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * @param comment セットする comment
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * @return studentId_lbl
     */
    public String getStudentId_lbl() {
        return studentId_lbl;
    }

    /**
     * @param studentId_lbl セットする studentId_lbl
     */
    public void setStudentId_lbl(String studentId_lbl) {
        this.studentId_lbl = studentId_lbl;
    }

    /**
     * @return familyJnm_txt
     */
    public String getFamilyJnm_txt() {
        return familyJnm_txt;
    }

    /**
     * @param familyJnm_txt セットする familyJnm_txt
     */
    public void setFamilyJnm_txt(String familyJnm_txt) {
        this.familyJnm_txt = familyJnm_txt;
    }

    /**
     * @return firstJnm_txt
     */
    public String getFirstJnm_txt() {
        return firstJnm_txt;
    }

    /**
     * @param firstJnm_txt セットする firstJnm_txt
     */
    public void setFirstJnm_txt(String firstJnm_txt) {
        this.firstJnm_txt = firstJnm_txt;
    }

    /**
     * @return familyKnm_txt
     */
    public String getFamilyKnm_txt() {
        return familyKnm_txt;
    }

    /**
     * @param familyKnm_txt セットする familyKnm_txt
     */
    public void setFamilyKnm_txt(String familyKnm_txt) {
        this.familyKnm_txt = familyKnm_txt;
    }

    /**
     * @return firstKnm_txt
     */
    public String getFirstKnm_txt() {
        return firstKnm_txt;
    }

    /**
     * @param firstKnm_txt セットする firstKnm_txt
     */
    public void setFirstKnm_txt(String firstKnm_txt) {
        this.firstKnm_txt = firstKnm_txt;
    }

    /**
     * @return familyRomaji_txt
     */
    public String getFamilyRomaji_txt() {
        return familyRomaji_txt;
    }

    /**
     * @param familyRomaji_txt セットする familyRomaji_txt
     */
    public void setFamilyRomaji_txt(String familyRomaji_txt) {
        this.familyRomaji_txt = familyRomaji_txt;
    }

    /**
     * @return firstRomaji_txt
     */
    public String getFirstRomaji_txt() {
        return firstRomaji_txt;
    }

    /**
     * @param firstRomaji_txt セットする firstRomaji_txt
     */
    public void setFirstRomaji_txt(String firstRomaji_txt) {
        this.firstRomaji_txt = firstRomaji_txt;
    }

    /**
     * @return nickNm_txt
     */
    public String getNickNm_txt() {
        return nickNm_txt;
    }

    /**
     * @param nickNm_txt セットする nickNm_txt
     */
    public void setNickNm_txt(String nickNm_txt) {
        this.nickNm_txt = nickNm_txt;
    }

    /**
     * @return tel1_txt
     */
    public String getTel1_txt() {
        return tel1_txt;
    }

    /**
     * @param tel1_txt セットする tel1_txt
     */
    public void setTel1_txt(String tel1_txt) {
        this.tel1_txt = tel1_txt;
    }

    /**
     * @return tel2_txt
     */
    public String getTel2_txt() {
        return tel2_txt;
    }

    /**
     * @param tel2_txt セットする tel2_txt
     */
    public void setTel2_txt(String tel2_txt) {
        this.tel2_txt = tel2_txt;
    }

    /**
     * @return birthYyyy_txt
     */
    public String getBirthYyyy_txt() {
        return birthYyyy_txt;
    }

    /**
     * @param birthYyyy_txt セットする birthYyyy_txt
     */
    public void setBirthYyyy_txt(String birthYyyy_txt) {
        this.birthYyyy_txt = birthYyyy_txt;
    }

    /**
     * @return birthMm_txt
     */
    public String getBirthMm_txt() {
        return birthMm_txt;
    }

    /**
     * @param birthMm_txt セットする birthMm_txt
     */
    public void setBirthMm_txt(String birthMm_txt) {
        this.birthMm_txt = birthMm_txt;
    }

    /**
     * @return birthDd_txt
     */
    public String getBirthDd_txt() {
        return birthDd_txt;
    }

    /**
     * @param birthDd_txt セットする birthDd_txt
     */
    public void setBirthDd_txt(String birthDd_txt) {
        this.birthDd_txt = birthDd_txt;
    }

    /**
     * @return zipCd_txt
     */
    public String getZipCd_txt() {
        return zipCd_txt;
    }

    /**
     * @param zipCd_txt セットする zipCd_txt
     */
    public void setZipCd_txt(String zipCd_txt) {
        this.zipCd_txt = zipCd_txt;
    }

    /**
     * @return addressAreaCd_sel
     */
    public String getAddressAreaCd_sel() {
        return addressAreaCd_sel;
    }

    /**
     * @param addressAreaCd_sel セットする addressAreaCd_sel
     */
    public void setAddressAreaCd_sel(String addressAreaCd_sel) {
        this.addressAreaCd_sel = addressAreaCd_sel;
    }

    /**
     * @return addressPrefectureCd_sel
     */
    public String getAddressPrefectureCd_sel() {
        return addressPrefectureCd_sel;
    }

    /**
     * @param addressPrefectureCd_sel セットする addressPrefectureCd_sel
     */
    public void setAddressPrefectureCd_sel(String addressPrefectureCd_sel) {
        this.addressPrefectureCd_sel = addressPrefectureCd_sel;
    }

    /**
     * @return addressCity_txt
     */
    public String getAddressCity_txt() {
        return addressCity_txt;
    }

    /**
     * @param addressCity_txt セットする addressCity_txt
     */
    public void setAddressCity_txt(String addressCity_txt) {
        this.addressCity_txt = addressCity_txt;
    }

    /**
     * @return addressOthers_txt
     */
    public String getAddressOthers_txt() {
        return addressOthers_txt;
    }

    /**
     * @param addressOthers_txt セットする addressOthers_txt
     */
    public void setAddressOthers_txt(String addressOthers_txt) {
        this.addressOthers_txt = addressOthers_txt;
    }

    /**
     * @return genderKbn_rdl
     */
    public String getGenderKbn_rdl() {
        return genderKbn_rdl;
    }

    /**
     * @param genderKbn_rdl セットする genderKbn_rdl
     */
    public void setGenderKbn_rdl(String genderKbn_rdl) {
        this.genderKbn_rdl = genderKbn_rdl;
    }

    /**
     * @return mailAddress1_txt
     */
    public String getMailAddress1_txt() {
        return mailAddress1_txt;
    }

    /**
     * @param mailAddress1_txt セットする mailAddress1_txt
     */
    public void setMailAddress1_txt(String mailAddress1_txt) {
        this.mailAddress1_txt = mailAddress1_txt;
    }

    /**
     * @return mailAddress2_txt
     */
    public String getMailAddress2_txt() {
        return mailAddress2_txt;
    }

    /**
     * @param mailAddress2_txt セットする mailAddress2_txt
     */
    public void setMailAddress2_txt(String mailAddress2_txt) {
        this.mailAddress2_txt = mailAddress2_txt;
    }

    /**
     * @return mailAddress3_txt
     */
    public String getMailAddress3_txt() {
        return mailAddress3_txt;
    }

    /**
     * @param mailAddress3_txt セットする mailAddress3_txt
     */
    public void setMailAddress3_txt(String mailAddress3_txt) {
        this.mailAddress3_txt = mailAddress3_txt;
    }

    /**
     * @return password_txt
     */
    public String getPassword_txt() {
        return password_txt;
    }

    /**
     * @param password_txt セットする password_txt
     */
    public void setPassword_txt(String password_txt) {
        this.password_txt = password_txt;
    }

    /**
     * @return loginNum_lbl
     */
    public String getLoginNum_lbl() {
        return loginNum_lbl;
    }

    /**
     * @param loginNum_lbl セットする loginNum_lbl
     */
    public void setLoginNum_lbl(String loginNum_lbl) {
        this.loginNum_lbl = loginNum_lbl;
    }

    /**
     * @return endLoginDt_lbl
     */
    public String getEndLoginDt_lbl() {
        return endLoginDt_lbl;
    }

    /**
     * @param endLoginDt_lbl セットする endLoginDt_lbl
     */
    public void setEndLoginDt_lbl(String endLoginDt_lbl) {
        this.endLoginDt_lbl = endLoginDt_lbl;
    }

    /**
     * @return occupationCd_sel
     */
    public String getOccupationCd_sel() {
        return occupationCd_sel;
    }

    /**
     * @param occupationCd_sel セットする occupationCd_sel
     */
    public void setOccupationCd_sel(String occupationCd_sel) {
        this.occupationCd_sel = occupationCd_sel;
    }

    /**
     * @return occupationCd_sell
     */
    public Map<String, String> getOccupationCd_sell() {
        return occupationCd_sell;
    }

    /**
     * @param occupationCd_sell セットする occupationCd_sell
     */
    public void setOccupationCd_sell(Map<String, String> occupationCd_sell) {
        this.occupationCd_sell = occupationCd_sell;
    }

    /**
     * @return customerKbn_rdl
     */
    public String getCustomerKbn_rdl() {
        return customerKbn_rdl;
    }

    /**
     * @param customerKbn_rdl セットする customerKbn_rdl
     */
    public void setCustomerKbn_rdl(String customerKbn_rdl) {
        this.customerKbn_rdl = customerKbn_rdl;
    }

    /**
     * @return customerKbn_rdll
     */
    public Map<String, String> getCustomerKbn_rdll() {
        return customerKbn_rdll;
    }

    /**
     * @param customerKbn_rdll セットする customerKbn_rdll
     */
    public void setCustomerKbn_rdll(Map<String, String> customerKbn_rdll) {
        this.customerKbn_rdll = customerKbn_rdll;
    }

    /**
     * @return organizationNm_txt
     */
    public String getOrganizationNm_txt() {
        return organizationNm_txt;
    }

    /**
     * @param organizationNm_txt セットする organizationNm_txt
     */
    public void setOrganizationNm_txt(String organizationNm_txt) {
        this.organizationNm_txt = organizationNm_txt;
    }

    /**
     * @return organizationId_lbl
     */
    public String getOrganizationId_lbl() {
        return organizationId_lbl;
    }

    /**
     * @param organizationId_lbl セットする organizationId_lbl
     */
    public void setOrganizationId_lbl(String organizationId_lbl) {
        this.organizationId_lbl = organizationId_lbl;
    }

    /**
     * @return studentPosition_lbl
     */
    public String getStudentPosition_lbl() {
        return studentPosition_lbl;
    }

    /**
     * @param studentPosition_lbl セットする studentPosition_lbl
     */
    public void setStudentPosition_lbl(String studentPosition_lbl) {
        this.studentPosition_lbl = studentPosition_lbl;
    }

    /**
     * @return positionOrganizationId_lbl
     */
    public String getPositionOrganizationId_lbl() {
        return positionOrganizationId_lbl;
    }

    /**
     * @param positionOrganizationId_lbl セットする positionOrganizationId_lbl
     */
    public void setPositionOrganizationId_lbl(String positionOrganizationId_lbl) {
        this.positionOrganizationId_lbl = positionOrganizationId_lbl;
    }

    /**
     * @return burdenNum_lbl
     */
    public String getBurdenNum_lbl() {
        return burdenNum_lbl;
    }

    /**
     * @param burdenNum_lbl セットする burdenNum_lbl
     */
    public void setBurdenNum_lbl(String burdenNum_lbl) {
        this.burdenNum_lbl = burdenNum_lbl;
    }

    /**
     * @return feeKbn
     */
    public String getFeeKbn() {
        return feeKbn;
    }

    /**
     * @param feeKbn セットする feeKbn
     */
    public void setFeeKbn(String feeKbn) {
        this.feeKbn = feeKbn;
    }

    /**
     * @return balancePoint_lbl
     */
    public String getBalancePoint_lbl() {
        return balancePoint_lbl;
    }

    /**
     * @param balancePoint_lbl セットする balancePoint_lbl
     */
    public void setBalancePoint_lbl(String balancePoint_lbl) {
        this.balancePoint_lbl = balancePoint_lbl;
    }

    /**
     * @return effectiveEndDt_lbl
     */
    public String getEffectiveEndDt_lbl() {
        return effectiveEndDt_lbl;
    }

    /**
     * @param effectiveEndDt_lbl セットする effectiveEndDt_lbl
     */
    public void setEffectiveEndDt_lbl(String effectiveEndDt_lbl) {
        this.effectiveEndDt_lbl = effectiveEndDt_lbl;
    }

    /**
     * @return useStrDt_txt
     */
    public String getUseStrDt_txt() {
        return useStrDt_txt;
    }

    /**
     * @param useStrDt_txt セットする useStrDt_txt
     */
    public void setUseStrDt_txt(String useStrDt_txt) {
        this.useStrDt_txt = useStrDt_txt;
    }

    /**
     * @return useEndDt_txt
     */
    public String getUseEndDt_txt() {
        return useEndDt_txt;
    }

    /**
     * @param useEndDt_txt セットする useEndDt_txt
     */
    public void setUseEndDt_txt(String useEndDt_txt) {
        this.useEndDt_txt = useEndDt_txt;
    }

    /**
     * @return useStopFlg
     */
    public String getUseStopFlg() {
        return useStopFlg;
    }

    /**
     * @param useStopFlg セットする useStopFlg
     */
    public void setUseStopFlg(String useStopFlg) {
        this.useStopFlg = useStopFlg;
    }

    /**
     * @return useMotiveFlg_chk1
     */
    public boolean isUseMotiveFlg_chk1() {
        return useMotiveFlg_chk1;
    }

    /**
     * @param useMotiveFlg_chk1 セットする useMotiveFlg_chk1
     */
    public void setUseMotiveFlg_chk1(boolean useMotiveFlg_chk1) {
        this.useMotiveFlg_chk1 = useMotiveFlg_chk1;
    }

    /**
     * @return useMotive_lbl1
     */
    public String getUseMotive_lbl1() {
        return useMotive_lbl1;
    }

    /**
     * @param useMotive_lbl1 セットする useMotive_lbl1
     */
    public void setUseMotive_lbl1(String useMotive_lbl1) {
        this.useMotive_lbl1 = useMotive_lbl1;
    }

    /**
     * @return useMotiveFlg_chk2
     */
    public boolean isUseMotiveFlg_chk2() {
        return useMotiveFlg_chk2;
    }

    /**
     * @param useMotiveFlg_chk2 セットする useMotiveFlg_chk2
     */
    public void setUseMotiveFlg_chk2(boolean useMotiveFlg_chk2) {
        this.useMotiveFlg_chk2 = useMotiveFlg_chk2;
    }

    /**
     * @return useMotive_lbl2
     */
    public String getUseMotive_lbl2() {
        return useMotive_lbl2;
    }

    /**
     * @param useMotive_lbl2 セットする useMotive_lbl2
     */
    public void setUseMotive_lbl2(String useMotive_lbl2) {
        this.useMotive_lbl2 = useMotive_lbl2;
    }

    /**
     * @return useMotiveFlg_chk3
     */
    public boolean isUseMotiveFlg_chk3() {
        return useMotiveFlg_chk3;
    }

    /**
     * @param useMotiveFlg_chk3 セットする useMotiveFlg_chk3
     */
    public void setUseMotiveFlg_chk3(boolean useMotiveFlg_chk3) {
        this.useMotiveFlg_chk3 = useMotiveFlg_chk3;
    }

    /**
     * @return useMotive_lbl3
     */
    public String getUseMotive_lbl3() {
        return useMotive_lbl3;
    }

    /**
     * @param useMotive_lbl3 セットする useMotive_lbl3
     */
    public void setUseMotive_lbl3(String useMotive_lbl3) {
        this.useMotive_lbl3 = useMotive_lbl3;
    }

//2013/09/25-Del-Start
    /**
     * @return useMotiveFlg_chk4
     */
/*
    public boolean isUseMotiveFlg_chk4() {
        return useMotiveFlg_chk4;
    }
*/
    /**
     * @param useMotiveFlg_chk4 セットする useMotiveFlg_chk4
     */
/*
    public void setUseMotiveFlg_chk4(boolean useMotiveFlg_chk4) {
        this.useMotiveFlg_chk4 = useMotiveFlg_chk4;
    }
*/

    /**
     * @return useMotive_lbl4
     */
/*
    public String getUseMotive_lbl4() {
        return useMotive_lbl4;
    }
*/

    /**
     * @param useMotive_lbl4 セットする useMotive_lbl4
     */
/*
    public void setUseMotive_lbl4(String useMotive_lbl4) {
        this.useMotive_lbl4 = useMotive_lbl4;
    }
*/
//2013/09/25-Del-End

    /**
     * @return useMotiveFlg_chk5
     */
    public boolean isUseMotiveFlg_chk5() {
        return useMotiveFlg_chk5;
    }

    /**
     * @param useMotiveFlg_chk5 セットする useMotiveFlg_chk5
     */
    public void setUseMotiveFlg_chk5(boolean useMotiveFlg_chk5) {
        this.useMotiveFlg_chk5 = useMotiveFlg_chk5;
    }

    /**
     * @return useMotive_lbl5
     */
    public String getUseMotive_lbl5() {
        return useMotive_lbl5;
    }

    /**
     * @param useMotive_lbl5 セットする useMotive_lbl5
     */
    public void setUseMotive_lbl5(String useMotive_lbl5) {
        this.useMotive_lbl5 = useMotive_lbl5;
    }

    /**
     * @return useMotive_txt
     */
    public String getUseMotive_txt() {
        return useMotive_txt;
    }

    /**
     * @param useMotive_txt セットする useMotive_txt
     */
    public void setUseMotive_txt(String useMotive_txt) {
        this.useMotive_txt = useMotive_txt;
    }

    /**
     * @return achievement_txt
     */
    public String getAchievement_txt() {
        return achievement_txt;
    }

    /**
     * @param achievement_txt セットする achievement_txt
     */
    public void setAchievement_txt(String achievement_txt) {
        this.achievement_txt = achievement_txt;
    }

    /**
     * @return useAgreement_chk
     */
    public boolean isUseAgreement_chk() {
        return useAgreement_chk;
    }

    /**
     * @param useAgreement_chk セットする useAgreement_chk
     */
    public void setUseAgreement_chk(boolean useAgreement_chk) {
        this.useAgreement_chk = useAgreement_chk;
    }

    /**
     * @return individualAgreement_chk
     */
    public boolean isIndividualAgreement_chk() {
        return individualAgreement_chk;
    }

    /**
     * @param individualAgreement_chk セットする individualAgreement_chk
     */
    public void setIndividualAgreement_chk(boolean individualAgreement_chk) {
        this.individualAgreement_chk = individualAgreement_chk;
    }

    /**
     * @return schoolCmt_txa
     */
    public String getSchoolCmt_txa() {
        return schoolCmt_txa;
    }

    /**
     * @param schoolCmt_txa セットする schoolCmt_txa
     */
    public void setSchoolCmt_txa(String schoolCmt_txa) {
        this.schoolCmt_txa = schoolCmt_txa;
    }

    /**
     * @return remark_txa
     */
    public String getRemark_txa() {
        return remark_txa;
    }

    /**
     * @param remark_txa セットする remark_txa
     */
    public void setRemark_txa(String remark_txa) {
        this.remark_txa = remark_txa;
    }

    /**
     * @return consentDocumentAcquisitionFlg_rdl
     */
    public String getConsentDocumentAcquisitionFlg_rdl() {
        return consentDocumentAcquisitionFlg_rdl;
    }

    /**
     * @param consentDocumentAcquisitionFlg_rdl セットする consentDocumentAcquisitionFlg_rdl
     */
    public void setConsentDocumentAcquisitionFlg_rdl(String consentDocumentAcquisitionFlg_rdl) {
        this.consentDocumentAcquisitionFlg_rdl = consentDocumentAcquisitionFlg_rdl;
    }

    /**
     * @return consentDocumentAcquisitionFlg_rdll
     */
    public Map<String, String> getConsentDocumentAcquisitionFlg_rdll() {
        return consentDocumentAcquisitionFlg_rdll;
    }

    /**
     * @param consentDocumentAcquisitionFlg_rdll セットする consentDocumentAcquisitionFlg_rdll
     */
    public void setConsentDocumentAcquisitionFlg_rdll(Map<String, String> consentDocumentAcquisitionFlg_rdll) {
        this.consentDocumentAcquisitionFlg_rdll = consentDocumentAcquisitionFlg_rdll;
    }

    /**
     * @return guardianFamilyJnm_txt
     */
    public String getGuardianFamilyJnm_txt() {
        return guardianFamilyJnm_txt;
    }

    /**
     * @param guardianFamilyJnm_txt セットする guardianFamilyJnm_txt
     */
    public void setGuardianFamilyJnm_txt(String guardianFamilyJnm_txt) {
        this.guardianFamilyJnm_txt = guardianFamilyJnm_txt;
    }

    /**
     * @return guardianFirstJnm_txt
     */
    public String getGuardianFirstJnm_txt() {
        return guardianFirstJnm_txt;
    }

    /**
     * @param guardianFirstJnm_txt セットする guardianFirstJnm_txt
     */
    public void setGuardianFirstJnm_txt(String guardianFirstJnm_txt) {
        this.guardianFirstJnm_txt = guardianFirstJnm_txt;
    }

    /**
     * @return guardianFamilyKnm_txt
     */
    public String getGuardianFamilyKnm_txt() {
        return guardianFamilyKnm_txt;
    }

    /**
     * @param guardianFamilyKnm_txt セットする guardianFamilyKnm_txt
     */
    public void setGuardianFamilyKnm_txt(String guardianFamilyKnm_txt) {
        this.guardianFamilyKnm_txt = guardianFamilyKnm_txt;
    }

    /**
     * @return guardianFirstKnm_txt
     */
    public String getGuardianFirstKnm_txt() {
        return guardianFirstKnm_txt;
    }

    /**
     * @param guardianFirstKnm_txt セットする guardianFirstKnm_txt
     */
    public void setGuardianFirstKnm_txt(String guardianFirstKnm_txt) {
        this.guardianFirstKnm_txt = guardianFirstKnm_txt;
    }

    /**
     * @return guardianFamilyRelationship_txt
     */
    public String getGuardianFamilyRelationship_txt() {
        return guardianFamilyRelationship_txt;
    }

    /**
     * @param guardianFamilyRelationship_txt セットする guardianFamilyRelationship_txt
     */
    public void setGuardianFamilyRelationship_txt(String guardianFamilyRelationship_txt) {
        this.guardianFamilyRelationship_txt = guardianFamilyRelationship_txt;
    }

    /**
     * @return guardianTel1_txt
     */
    public String getGuardianTel1_txt() {
        return guardianTel1_txt;
    }

    /**
     * @param guardianTel1_txt セットする guardianTel1_txt
     */
    public void setGuardianTel1_txt(String guardianTel1_txt) {
        this.guardianTel1_txt = guardianTel1_txt;
    }

    /**
     * @return guardianTel2_txt
     */
    public String getGuardianTel2_txt() {
        return guardianTel2_txt;
    }

    /**
     * @param guardianTel2_txt セットする guardianTel2_txt
     */
    public void setGuardianTel2_txt(String guardianTel2_txt) {
        this.guardianTel2_txt = guardianTel2_txt;
    }

    /**
     * @return guardianBirthYyyy_txt
     */
    public String getGuardianBirthYyyy_txt() {
        return guardianBirthYyyy_txt;
    }

    /**
     * @param guardianBirthYyyy_txt セットする guardianBirthYyyy_txt
     */
    public void setGuardianBirthYyyy_txt(String guardianBirthYyyy_txt) {
        this.guardianBirthYyyy_txt = guardianBirthYyyy_txt;
    }

    /**
     * @return guardianBirthMm_txt
     */
    public String getGuardianBirthMm_txt() {
        return guardianBirthMm_txt;
    }

    /**
     * @param guardianBirthMm_txt セットする guardianBirthMm_txt
     */
    public void setGuardianBirthMm_txt(String guardianBirthMm_txt) {
        this.guardianBirthMm_txt = guardianBirthMm_txt;
    }

    /**
     * @return guardianBirthDd_txt
     */
    public String getGuardianBirthDd_txt() {
        return guardianBirthDd_txt;
    }

    /**
     * @param guardianBirthDd_txt セットする guardianBirthDd_txt
     */
    public void setGuardianBirthDd_txt(String guardianBirthDd_txt) {
        this.guardianBirthDd_txt = guardianBirthDd_txt;
    }

    /**
     * @return guardianZipCd_txt
     */
    public String getGuardianZipCd_txt() {
        return guardianZipCd_txt;
    }

    /**
     * @param guardianZipCd_txt セットする guardianZipCd_txt
     */
    public void setGuardianZipCd_txt(String guardianZipCd_txt) {
        this.guardianZipCd_txt = guardianZipCd_txt;
    }

    /**
     * @return guardianAddressAreaCd_sel
     */
    public String getGuardianAddressAreaCd_sel() {
        return guardianAddressAreaCd_sel;
    }

    /**
     * @param guardianAddressAreaCd_sel セットする guardianAddressAreaCd_sel
     */
    public void setGuardianAddressAreaCd_sel(String guardianAddressAreaCd_sel) {
        this.guardianAddressAreaCd_sel = guardianAddressAreaCd_sel;
    }

    /**
     * @return guardianAddressPrefectureCd_sel
     */
    public String getGuardianAddressPrefectureCd_sel() {
        return guardianAddressPrefectureCd_sel;
    }

    /**
     * @param guardianAddressPrefectureCd_sel セットする guardianAddressPrefectureCd_sel
     */
    public void setGuardianAddressPrefectureCd_sel(String guardianAddressPrefectureCd_sel) {
        this.guardianAddressPrefectureCd_sel = guardianAddressPrefectureCd_sel;
    }

    /**
     * @return guardianAddressCity_txt
     */
    public String getGuardianAddressCity_txt() {
        return guardianAddressCity_txt;
    }

    /**
     * @param guardianAddressCity_txt セットする guardianAddressCity_txt
     */
    public void setGuardianAddressCity_txt(String guardianAddressCity_txt) {
        this.guardianAddressCity_txt = guardianAddressCity_txt;
    }

    /**
     * @return guardianAddressOthers_txt
     */
    public String getGuardianAddressOthers_txt() {
        return guardianAddressOthers_txt;
    }

    /**
     * @param guardianAddressOthers_txt セットする guardianAddressOthers_txt
     */
    public void setGuardianAddressOthers_txt(String guardianAddressOthers_txt) {
        this.guardianAddressOthers_txt = guardianAddressOthers_txt;
    }

    /**
     * @return guardianGenderKbn_rdl
     */
    public String getGuardianGenderKbn_rdl() {
        return guardianGenderKbn_rdl;
    }

    /**
     * @param guardianGenderKbn_rdl セットする guardianGenderKbn_rdl
     */
    public void setGuardianGenderKbn_rdl(String guardianGenderKbn_rdl) {
        this.guardianGenderKbn_rdl = guardianGenderKbn_rdl;
    }

    /**
     * @return guardianMailAddress1_txt
     */
    public String getGuardianMailAddress1_txt() {
        return guardianMailAddress1_txt;
    }

    /**
     * @param guardianMailAddress1_txt セットする guardianMailAddress1_txt
     */
    public void setGuardianMailAddress1_txt(String guardianMailAddress1_txt) {
        this.guardianMailAddress1_txt = guardianMailAddress1_txt;
    }

    /**
     * @return guardianMailAddress2_txt
     */
    public String getGuardianMailAddress2_txt() {
        return guardianMailAddress2_txt;
    }

    /**
     * @param guardianMailAddress2_txt セットする guardianMailAddress2_txt
     */
    public void setGuardianMailAddress2_txt(String guardianMailAddress2_txt) {
        this.guardianMailAddress2_txt = guardianMailAddress2_txt;
    }

    /**
     * @return guardianMailAddress3_txt
     */
    public String getGuardianMailAddress3_txt() {
        return guardianMailAddress3_txt;
    }

    /**
     * @param guardianMailAddress3_txt セットする guardianMailAddress3_txt
     */
    public void setGuardianMailAddress3_txt(String guardianMailAddress3_txt) {
        this.guardianMailAddress3_txt = guardianMailAddress3_txt;
    }

    /**
     * @return pointPurchaseFlg
     */
    public String getPointPurchaseFlg() {
        return pointPurchaseFlg;
    }

    /**
     * @param pointPurchaseFlg セットする pointPurchaseFlg
     */
    public void setPointPurchaseFlg(String pointPurchaseFlg) {
        this.pointPurchaseFlg = pointPurchaseFlg;
    }

    /**
     * @return dmNoNeedFlg_chk
     */
    public boolean isDmNoNeedFlg_chk() {
        return dmNoNeedFlg_chk;
    }

    /**
     * @param dmNoNeedFlg_chk セットする dmNoNeedFlg_chk
     */
    public void setDmNoNeedFlg_chk(boolean dmNoNeedFlg_chk) {
        this.dmNoNeedFlg_chk = dmNoNeedFlg_chk;
    }

    /**
     * @return other1Flg_chk
     */
    public boolean isOther1Flg_chk() {
        return other1Flg_chk;
    }

    /**
     * @param other1Flg_chk セットする other1Flg_chk
     */
    public void setOther1Flg_chk(boolean other1Flg_chk) {
        this.other1Flg_chk = other1Flg_chk;
    }

    /**
     * @return other2Flg_chk
     */
    public boolean isOther2Flg_chk() {
        return other2Flg_chk;
    }

    /**
     * @param other2Flg_chk セットする other2Flg_chk
     */
    public void setOther2Flg_chk(boolean other2Flg_chk) {
        this.other2Flg_chk = other2Flg_chk;
    }

    /**
     * @return other3Flg_chk
     */
    public boolean isOther3Flg_chk() {
        return other3Flg_chk;
    }

    /**
     * @param other3Flg_chk セットする other3Flg_chk
     */
    public void setOther3Flg_chk(boolean other3Flg_chk) {
        this.other3Flg_chk = other3Flg_chk;
    }

    /**
     * @return other4Flg_chk
     */
    public boolean isOther4Flg_chk() {
        return other4Flg_chk;
    }

    /**
     * @param other4Flg_chk セットする other4Flg_chk
     */
    public void setOther4Flg_chk(boolean other4Flg_chk) {
        this.other4Flg_chk = other4Flg_chk;
    }

    /**
     * @return areaCd_sell
     */
    public Map<String, String> getAreaCd_sell() {
        return areaCd_sell;
    }

    /**
     * @param areaCd_sell セットする areaCd_sell
     */
    public void setAreaCd_sell(Map<String, String> areaCd_sell) {
        this.areaCd_sell = areaCd_sell;
    }

    /**
     * @return prefectureCd_sell
     */
    public Map<String, String> getPrefectureCd_sell() {
        return prefectureCd_sell;
    }

    /**
     * @param prefectureCd_sell セットする prefectureCd_sell
     */
    public void setPrefectureCd_sell(Map<String, String> prefectureCd_sell) {
        this.prefectureCd_sell = prefectureCd_sell;
    }

    /**
     * @return genderKbn_rdll
     */
    public Map<String, String> getGenderKbn_rdll() {
        return genderKbn_rdll;
    }

    /**
     * @param genderKbn_rdll セットする genderKbn_rdll
     */
    public void setGenderKbn_rdll(Map<String, String> genderKbn_rdll) {
        this.genderKbn_rdll = genderKbn_rdll;
    }

    /**
     * @return recordVerNo
     */
    public String getRecordVerNo() {
        return recordVerNo;
    }

    /**
     * @param recordVerNo セットする recordVerNo
     */
    public void setRecordVerNo(String recordVerNo) {
        this.recordVerNo = recordVerNo;
    }

    /**
     * @return model
     */
    public StudentMstModel getModel() {
        return model;
    }

    /**
     * @param model セットする model
     */
    public void setModel(StudentMstModel model) {
        this.model = model;
    }

    /**
     * @return primaryKey
     */
    public String getPrimaryKey() {
        return primaryKey;
    }

    /**
     * @param primaryKey セットする primaryKey
     */
    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }

    /**
     * @return monthlyReturnList
     */
    public List<PointOwnershipTrnDto> getMonthlyReturnList() {
        return monthlyReturnList;
    }

    /**
     * @param monthlyReturnList セットする monthlyReturnList
     */
    public void setMonthlyReturnList(List<PointOwnershipTrnDto> monthlyReturnList) {
        this.monthlyReturnList = monthlyReturnList;
    }

    /**
     * @return specialFreeReturnList
     */
    public List<PointOwnershipTrnDto> getSpecialFreeReturnList() {
        return specialFreeReturnList;
    }

    /**
     * @param specialFreeReturnList セットする specialFreeReturnList
     */
    public void setSpecialFreeReturnList(List<PointOwnershipTrnDto> specialFreeReturnList) {
        this.specialFreeReturnList = specialFreeReturnList;
    }

    /**
     * @return pwdDispFlg
     */
    public String getPwdDispFlg() {
        return pwdDispFlg;
    }

    /**
     * @param pwdDispFlg セットする pwdDispFlg
     */
    public void setPwdDispFlg(String pwdDispFlg) {
        this.pwdDispFlg = pwdDispFlg;
    }

    /**
     * @return returnOnFlg
     */
    public boolean isReturnOnFlg() {
        return returnOnFlg;
    }

    /**
     * @param returnOnFlg セットする returnOnFlg
     */
    public void setReturnOnFlg(boolean returnOnFlg) {
        this.returnOnFlg = returnOnFlg;
    }

}
