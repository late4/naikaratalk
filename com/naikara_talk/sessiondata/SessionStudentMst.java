package com.naikara_talk.sessiondata;

import java.util.ArrayList;
import java.util.List;

import com.naikara_talk.dto.PointOwnershipTrnDto;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>マスタ保守。<br>
 * <b>クラス名称　　　:</b>受講者マスタメンテナンス【単票】のセッション情報クラス。<br>
 * <b>クラス概要　　　:</b>受講者マスタメンテナンス【単票】の戻る用のセッション情報。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b> 2013/07/30 TECS 新規作成。
 */

public class SessionStudentMst implements SessionData {

    /** ヘッダの戻るリンク用のSessionKey */
    private static final String HEADER_RETURN_KEY = "StudentMst";

    /** 戻る判定Onフラグ */
    private boolean returnOnFlg;

    /** 処理区分 */
    private String processKbn_rdl;

    /** お客様区分 */
    private String customerKbn_rdl;

    /** 画面表示処理区分 */
    private String processKbn_txt;

    /** コメン */
    private String comment;

    /** 受講者ID */
    private String studentId_lbl;

    /** 名前(姓) */
    private String familyJnm_txt;

    /** 名前(名) */
    private String firstJnm_txt;

    /** フリガナ(姓) */
    private String familyKnm_txt;

    /** フリガナ(名) */
    private String firstKnm_txt;

    /** ローマ字(姓) */
    private String familyRomaji_txt;

    /** ローマ字(名) */
    private String firstRomaji_txt;

    /** ニックネーム */
    private String nickNm_txt;

    /** 電話番号1 */
    private String tel1_txt;

    /** 電話番号2 */
    private String tel2_txt;

    /** 生年月日：年 */
    private String birthYyyy_txt;

    /** 生年月日：月 */
    private String birthMm_txt;

    /** 生年月日：日 */
    private String birthDd_txt;

    /** 郵便番号 */
    private String zipCd_txt;

    /** 住所(地域)コード */
    private String addressAreaCd_sel;

    /** 住所(都道府県)コード */
    private String addressPrefectureCd_sel;

    /** 住所(市区町村 等) */
    private String addressCity_txt;

    /** 住所(ﾋﾞﾙ、ﾏﾝｼｮﾝ名 等) */
    private String addressOthers_txt;

    /** 性別区分 */
    private String genderKbn_rdl;

    /** メールアドレス1 */
    private String mailAddress1_txt;

    /** メールアドレス2 */
    private String mailAddress2_txt;

    /** メールアドレス3 */
    private String mailAddress3_txt;

    /** パスワード */
    private String password_txt;

    /** ログイン回数 */
    private String loginNum_lbl;

    /** 最終ログイン日 */
    private String endLoginDt_lbl;

    /** 職業コード */
    private String occupationCd_sel;

    /** 組織名 */
    private String organizationNm_txt;

    /** 組織ID */
    private String organizationId_lbl;

    /** 受講者所属部署 */
    private String studentPosition_lbl;

    /** 所属組織内ID */
    private String positionOrganizationId_lbl;

    /** 自己負担率 */
    private String burdenNum_lbl;

    /** ポイント残高 */
    private String balancePoint_lbl;

    /** 有効終了日 */
    private String effectiveEndDt_lbl;

    /** 利用開始日 */
    private String useStrDt_txt;

    /** 利用終了日 */
    private String useEndDt_txt;

    /** 利用停止フラグ */
    private String useStopFlg;

    /** 利用動機フラグ１ */
    private boolean useMotiveFlg_chk1;
    private String useMotive_lbl1;

    /** 利用動機フラグ2 */
    private boolean useMotiveFlg_chk2;
    private String useMotive_lbl2;

    /** 利用動機フラグ3 */
    private boolean useMotiveFlg_chk3;
    private String useMotive_lbl3;


//2013/09/25-Del-Start
    /** 利用動機フラグ4 */
//    private boolean useMotiveFlg_chk4;
//    private String useMotive_lbl4;
//2013/09/25-Del-End


    /** 利用動機フラグ5 */
    private boolean useMotiveFlg_chk5;
    private String useMotive_lbl5;

    /** 利用動機テキスト */
    private String useMotive_txt;

    /** 達成目標 */
    private String achievement_txt;

    /** 利用規約に同意する：チェックフラグ */
    private boolean useAgreement_chk;

    /** 個人情報の同意：チェックフラグ */
    private boolean individualAgreement_chk;

    /** ｽｸｰﾙからのｺﾒﾝﾄ(生徒公開) */
    private String schoolCmt_txa;

    /** 備考(生徒非公開) */
    private String remark_txa;

    /** 保護者の同意書の入手フラグ */
    private String consentDocumentAcquisitionFlg_rdl;

    /** 保護者：名前(姓) */
    private String guardianFamilyJnm_txt;

    /** 保護者：名前(名） */
    private String guardianFirstJnm_txt;

    /** 保護者：フリガナ(姓) */
    private String guardianFamilyKnm_txt;

    /** 保護者：フリガナ(名) */
    private String guardianFirstKnm_txt;

    /** あなたとの続柄 */
    private String guardianFamilyRelationship_txt;

    /** 保護者：電話番号1 */
    private String guardianTel1_txt;

    /** 保護者：電話番号2 */
    private String guardianTel2_txt;

    /** 保護者：生年月日：年 */
    private String guardianBirthYyyy_txt;

    /** 保護者：生年月日：月 */
    private String guardianBirthMm_txt;

    /** 保護者：生年月日：日 */
    private String guardianBirthDd_txt;

    /** 保護者：郵便番号 */
    private String guardianZipCd_txt;

    /** 保護者：住所(地域)コード */
    private String guardianAddressAreaCd_sel;

    /** 保護者：住所(都道府県)コード */
    private String guardianAddressPrefectureCd_sel;

    /** 保護者：住所(市区町村 等) */
    private String guardianAddressCity_txt;

    /** 保護者：住所(ﾋﾞﾙ、ﾏﾝｼｮﾝ名 等) */
    private String guardianAddressOthers_txt;

    /** 保護者：性別区分 */
    private String guardianGenderKbn_rdl;

    /** 保護者：メールアドレス1 */
    private String guardianMailAddress1_txt;

    /** 保護者：メールアドレス2 */
    private String guardianMailAddress2_txt;

    /** 保護者：メールアドレス3 */
    private String guardianMailAddress3_txt;

    /** ポイント購入済フラグ */
    private String pointPurchaseFlg;

    /** DM不要フラグ */
    private boolean dmNoNeedFlg_chk;

    /** その他フラグ１ */
    private boolean other1Flg_chk;

    /** その他フラグ２ */
    private boolean other2Flg_chk;

    /** その他フラグ３ */
    private boolean other3Flg_chk;

    /** その他フラグ４ */
    private boolean other4Flg_chk;

    /** 排他用レコードバージョン */
    private int recordVerNo;

    /** 検索key */
    private String primaryKey;

    /** 組織ID */
    private String organizationId;

    /** 月謝購入区分 */
    private String feeKbn;

    /** 受講者別月謝購入情報 リターンリスト */
    private List<PointOwnershipTrnDto> monthlyReturnList = new ArrayList<PointOwnershipTrnDto>();

    /** 特別無償ポイント設定 リターンリスト */
    private List<PointOwnershipTrnDto> specialFreeReturnList = new ArrayList<PointOwnershipTrnDto>();

    /** 一時保存の特別無償ポイント設定 リターンリスト */
    private List<PointOwnershipTrnDto> tempSpecialFreeReturnList = new ArrayList<PointOwnershipTrnDto>();

    /** ポイント残高 */
    private String purchasePoint;

    /** 最長のポイント有効終了日 */
    private String pointDffectEndDt;

    /** パスワード表示フラグ */
    private String pwdDispFlg;

    /** 受講者IDリスト */
    private List<String> studentIdLst = new ArrayList<String>();

    /** メールアドレス1リスト */
    private List<String> mailAddrLst = new ArrayList<String>();

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
     * @return recordVerNo
     */
    public int getRecordVerNo() {
        return recordVerNo;
    }

    /**
     * @param recordVerNo セットする recordVerNo
     */
    public void setRecordVerNo(int recordVerNo) {
        this.recordVerNo = recordVerNo;
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
     * @return organizationId
     */
    public String getOrganizationId() {
        return organizationId;
    }

    /**
     * @param organizationId セットする organizationId
     */
    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
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
     * @return tempSpecialFreeReturnList
     */
    public List<PointOwnershipTrnDto> getTempSpecialFreeReturnList() {
        return tempSpecialFreeReturnList;
    }

    /**
     * @param tempSpecialFreeReturnList セットする tempSpecialFreeReturnList
     */
    public void setTempSpecialFreeReturnList(List<PointOwnershipTrnDto> tempSpecialFreeReturnList) {
        this.tempSpecialFreeReturnList = tempSpecialFreeReturnList;
    }

    /**
     * @return purchasePoint
     */
    public String getPurchasePoint() {
        return purchasePoint;
    }

    /**
     * @param purchasePoint セットする purchasePoint
     */
    public void setPurchasePoint(String purchasePoint) {
        this.purchasePoint = purchasePoint;
    }

    /**
     * @return pointDffectEndDt
     */
    public String getPointDffectEndDt() {
        return pointDffectEndDt;
    }

    /**
     * @param pointDffectEndDt セットする pointDffectEndDt
     */
    public void setPointDffectEndDt(String pointDffectEndDt) {
        this.pointDffectEndDt = pointDffectEndDt;
    }

    /**
     * @return headerReturnKey
     */
    public static String getHeaderReturnKey() {
        return HEADER_RETURN_KEY;
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
     * @return studentIdLst
     */
    public List<String> getStudentIdLst() {
        return studentIdLst;
    }

    /**
     * @param studentIdLst セットする studentIdLst
     */
    public void setStudentIdLst(List<String> studentIdLst) {
        this.studentIdLst = studentIdLst;
    }

    /**
     * @return mailAddrLst
     */
    public List<String> getMailAddrLst() {
        return mailAddrLst;
    }

    /**
     * @param mailAddrLst セットする mailAddrLst
     */
    public void setMailAddrLst(List<String> mailAddrLst) {
        this.mailAddrLst = mailAddrLst;
    }

}
