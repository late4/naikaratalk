package com.naikara_talk.model;

import java.math.BigDecimal;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>マスタ保守<br>
 * <b>クラス名称　　　:</b>組織契約情報登録Modelクラス。<br>
 * <b>クラス概要　　　:</b>組織契約情報登録Model。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b> 2013/08/01 TECS 新規作成。
 */
public class OrganizationContractMstModel implements Model {

    private static final long serialVersionUID = 1L;

    /** 処理区分 */
    private String processKbn_rdl;
    /** 組織ID */
    private String organizationId;
    /** 連番 */
    private int consSeq;
    /** パスワード */
    private String password;
    /** パスワード確認 */
    private String passwordCheck;
    /** 組織名 */
    private String organizationJnm;
    /** 組織名(フリガナ) */
    private String organizationKnm;
    /** 組織名(ローマ字) */
    private String organizationRomaji;
    /** 電話番号 */
    private String tel;
    /** 郵便番号 */
    private String zipCd;
    /** 住所(地域)コード */
    private String addressAreaCd;
    /** 住所(都道府県)コード */
    private String addressPrefectureCd;
    /** 住所(市区町村) */
    private String addressCity;
    /** 住所(ﾋﾞﾙ、ﾏﾝｼｮﾝ名等) */
    private String addressOthers;
    /** 責任者名(姓) */
    private String managFamilyJnm;
    /** 責任者名(名) */
    private String managFirstJnm;
    /** 責任者名フリガナ(姓) */
    private String managFamilyKnm;
    /** 責任者名フリガナ(名) */
    private String managFirstKnm;
    /** 責任者名ローマ字(姓) */
    private String managFamilyRomaji;
    /** 責任者名ローマ字(名) */
    private String managFirstRomaji;
    /** 責任者所属 */
    private String managPosition;
    /** 責任者性別区分 */
    private String managGenderKbn;
    /** 責任者メールアドレス1 */
    private String managMailAddress1;
    /** 責任者メールアドレス2 */
    private String managMailAddress2;
    /** 責任者メールアドレス3 */
    private String managMailAddress3;
    /** 契約開始日 */
    private String contractStrDt;
    /** 契約終了日 */
    private String contractEndDt;
    /** 仮ポイント数 */
    private BigDecimal tempPointNum;
    /** 未割振ポイント(残高) */
    private BigDecimal balancePointNum;
    /** 請求先住所区分 */
    private String requestAddressKbn;
    /** 備考 */
    private String remarks;
    /** 請求先情報：組織名 */
    private String requestOrganizationJnm;
    /** 請求先情報：組織名(カナ) */
    private String requestOrganizationKnm;
    /** 請求先情報：組織名(ローマ字) */
    private String requestOrganizationRnm;
    /** 請求先情報：電話番号 */
    private String requestTel;
    /** 請求先情報：郵便番号 */
    private String requestZipCd;
    /** 請求先情報：住所(地域)コード */
    private String requestAddressAreaCd;
    /** 請求先情報：住所(都道府県)コード */
    private String requestAddressPrefectureCd;
    /** 請求先情報：住所(市区町村) */
    private String requestAddressCity;
    /** 請求先情報：住所(ﾋﾞﾙ、ﾏﾝｼｮﾝ名等) */
    private String requestAddressOthers;
    /** 請求先情報：責任者名(姓) */
    private String requestManagFamilyJnm;
    /** 請求先情報：責任者名(名) */
    private String requestManagFirstJnm;
    /** 請求先情報：責任者名フリガナ(姓) */
    private String requestManagFamilyKnm;
    /** 請求先情報：責任者名フリガナ(名) */
    private String requestManagFirstKnm;
    /** 請求先情報：責任者名ローマ字(姓) */
    private String requestManagFamilyRomaji;
    /** 請求先情報：責任者名ローマ字(名) */
    private String requestManagFirstRomaji;
    /** 請求先情報：責任者所属 */
    private String requestManagPosition;
    /** 請求先情報：支払サイト区分 */
    private String requestPaymentSiteKbn;
    /** レコードバージョン番号 */
    private int recordVerNo;
    /** リターンコード */
    private int returnCode;
    /** 更新者コード */
    private String updateCd;

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
     * @return consSeq
     */
    public int getConsSeq() {
        return consSeq;
    }

    /**
     * @param consSeq セットする consSeq
     */
    public void setConsSeq(int consSeq) {
        this.consSeq = consSeq;
    }

    /**
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password セットする password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return organizationJnm
     */
    public String getOrganizationJnm() {
        return organizationJnm;
    }

    /**
     * @param organizationJnm セットする organizationJnm
     */
    public void setOrganizationJnm(String organizationJnm) {
        this.organizationJnm = organizationJnm;
    }

    /**
     * @return organizationKnm
     */
    public String getOrganizationKnm() {
        return organizationKnm;
    }

    /**
     * @param organizationKnm セットする organizationKnm
     */
    public void setOrganizationKnm(String organizationKnm) {
        this.organizationKnm = organizationKnm;
    }

    /**
     * @return organizationRomaji
     */
    public String getOrganizationRomaji() {
        return organizationRomaji;
    }

    /**
     * @param organizationRomaji セットする organizationRomaji
     */
    public void setOrganizationRomaji(String organizationRomaji) {
        this.organizationRomaji = organizationRomaji;
    }

    /**
     * @return tel
     */
    public String getTel() {
        return tel;
    }

    /**
     * @param tel セットする tel
     */
    public void setTel(String tel) {
        this.tel = tel;
    }

    /**
     * @return zipCd
     */
    public String getZipCd() {
        return zipCd;
    }

    /**
     * @param zipCd セットする zipCd
     */
    public void setZipCd(String zipCd) {
        this.zipCd = zipCd;
    }

    /**
     * @return addressAreaCd
     */
    public String getAddressAreaCd() {
        return addressAreaCd;
    }

    /**
     * @param addressAreaCd セットする addressAreaCd
     */
    public void setAddressAreaCd(String addressAreaCd) {
        this.addressAreaCd = addressAreaCd;
    }

    /**
     * @return addressPrefectureCd
     */
    public String getAddressPrefectureCd() {
        return addressPrefectureCd;
    }

    /**
     * @param addressPrefectureCd セットする addressPrefectureCd
     */
    public void setAddressPrefectureCd(String addressPrefectureCd) {
        this.addressPrefectureCd = addressPrefectureCd;
    }

    /**
     * @return addressCity
     */
    public String getAddressCity() {
        return addressCity;
    }

    /**
     * @param addressCity セットする addressCity
     */
    public void setAddressCity(String addressCity) {
        this.addressCity = addressCity;
    }

    /**
     * @return addressOthers
     */
    public String getAddressOthers() {
        return addressOthers;
    }

    /**
     * @param addressOthers セットする addressOthers
     */
    public void setAddressOthers(String addressOthers) {
        this.addressOthers = addressOthers;
    }

    /**
     * @return managFamilyJnm
     */
    public String getManagFamilyJnm() {
        return managFamilyJnm;
    }

    /**
     * @param managFamilyJnm セットする managFamilyJnm
     */
    public void setManagFamilyJnm(String managFamilyJnm) {
        this.managFamilyJnm = managFamilyJnm;
    }

    /**
     * @return managFirstJnm
     */
    public String getManagFirstJnm() {
        return managFirstJnm;
    }

    /**
     * @param managFirstJnm セットする managFirstJnm
     */
    public void setManagFirstJnm(String managFirstJnm) {
        this.managFirstJnm = managFirstJnm;
    }

    /**
     * @return managFamilyKnm
     */
    public String getManagFamilyKnm() {
        return managFamilyKnm;
    }

    /**
     * @param managFamilyKnm セットする managFamilyKnm
     */
    public void setManagFamilyKnm(String managFamilyKnm) {
        this.managFamilyKnm = managFamilyKnm;
    }

    /**
     * @return managFirstKnm
     */
    public String getManagFirstKnm() {
        return managFirstKnm;
    }

    /**
     * @param managFirstKnm セットする managFirstKnm
     */
    public void setManagFirstKnm(String managFirstKnm) {
        this.managFirstKnm = managFirstKnm;
    }

    /**
     * @return managFamilyRomaji
     */
    public String getManagFamilyRomaji() {
        return managFamilyRomaji;
    }

    /**
     * @param managFamilyRomaji セットする managFamilyRomaji
     */
    public void setManagFamilyRomaji(String managFamilyRomaji) {
        this.managFamilyRomaji = managFamilyRomaji;
    }

    /**
     * @return managFirstRomaji
     */
    public String getManagFirstRomaji() {
        return managFirstRomaji;
    }

    /**
     * @param managFirstRomaji セットする managFirstRomaji
     */
    public void setManagFirstRomaji(String managFirstRomaji) {
        this.managFirstRomaji = managFirstRomaji;
    }

    /**
     * @return managPosition
     */
    public String getManagPosition() {
        return managPosition;
    }

    /**
     * @param managPosition セットする managPosition
     */
    public void setManagPosition(String managPosition) {
        this.managPosition = managPosition;
    }

    /**
     * @return managGenderKbn
     */
    public String getManagGenderKbn() {
        return managGenderKbn;
    }

    /**
     * @param managGenderKbn セットする managGenderKbn
     */
    public void setManagGenderKbn(String managGenderKbn) {
        this.managGenderKbn = managGenderKbn;
    }

    /**
     * @return managMailAddress1
     */
    public String getManagMailAddress1() {
        return managMailAddress1;
    }

    /**
     * @param managMailAddress1 セットする managMailAddress1
     */
    public void setManagMailAddress1(String managMailAddress1) {
        this.managMailAddress1 = managMailAddress1;
    }

    /**
     * @return managMailAddress2
     */
    public String getManagMailAddress2() {
        return managMailAddress2;
    }

    /**
     * @param managMailAddress2 セットする managMailAddress2
     */
    public void setManagMailAddress2(String managMailAddress2) {
        this.managMailAddress2 = managMailAddress2;
    }

    /**
     * @return managMailAddress3
     */
    public String getManagMailAddress3() {
        return managMailAddress3;
    }

    /**
     * @param managMailAddress3 セットする managMailAddress3
     */
    public void setManagMailAddress3(String managMailAddress3) {
        this.managMailAddress3 = managMailAddress3;
    }

    /**
     * @return contractStrDt
     */
    public String getContractStrDt() {
        return contractStrDt;
    }

    /**
     * @param contractStrDt セットする contractStrDt
     */
    public void setContractStrDt(String contractStrDt) {
        this.contractStrDt = contractStrDt;
    }

    /**
     * @return contractEndDt
     */
    public String getContractEndDt() {
        return contractEndDt;
    }

    /**
     * @param contractEndDt セットする contractEndDt
     */
    public void setContractEndDt(String contractEndDt) {
        this.contractEndDt = contractEndDt;
    }

    /**
     * @return tempPointNum
     */
    public BigDecimal getTempPointNum() {
        return tempPointNum;
    }

    /**
     * @param tempPointNum セットする tempPointNum
     */
    public void setTempPointNum(BigDecimal tempPointNum) {
        this.tempPointNum = tempPointNum;
    }

    /**
     * @return balancePointNum
     */
    public BigDecimal getBalancePointNum() {
        return balancePointNum;
    }

    /**
     * @param balancePointNum セットする balancePointNum
     */
    public void setBalancePointNum(BigDecimal balancePointNum) {
        this.balancePointNum = balancePointNum;
    }

    /**
     * @return requestAddressKbn
     */
    public String getRequestAddressKbn() {
        return requestAddressKbn;
    }

    /**
     * @param requestAddressKbn セットする requestAddressKbn
     */
    public void setRequestAddressKbn(String requestAddressKbn) {
        this.requestAddressKbn = requestAddressKbn;
    }

    /**
     * @return remarks
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * @param remarks セットする remarks
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    /**
     * @return requestOrganizationJnm
     */
    public String getRequestOrganizationJnm() {
        return requestOrganizationJnm;
    }

    /**
     * @param requestOrganizationJnm セットする requestOrganizationJnm
     */
    public void setRequestOrganizationJnm(String requestOrganizationJnm) {
        this.requestOrganizationJnm = requestOrganizationJnm;
    }

    /**
     * @return requestOrganizationKnm
     */
    public String getRequestOrganizationKnm() {
        return requestOrganizationKnm;
    }

    /**
     * @param requestOrganizationKnm セットする requestOrganizationKnm
     */
    public void setRequestOrganizationKnm(String requestOrganizationKnm) {
        this.requestOrganizationKnm = requestOrganizationKnm;
    }

    /**
     * @return requestOrganizationRnm
     */
    public String getRequestOrganizationRnm() {
        return requestOrganizationRnm;
    }

    /**
     * @param requestOrganizationRnm セットする requestOrganizationRnm
     */
    public void setRequestOrganizationRnm(String requestOrganizationRnm) {
        this.requestOrganizationRnm = requestOrganizationRnm;
    }

    /**
     * @return requestTel
     */
    public String getRequestTel() {
        return requestTel;
    }

    /**
     * @param requestTel セットする requestTel
     */
    public void setRequestTel(String requestTel) {
        this.requestTel = requestTel;
    }

    /**
     * @return requestZipCd
     */
    public String getRequestZipCd() {
        return requestZipCd;
    }

    /**
     * @param requestZipCd セットする requestZipCd
     */
    public void setRequestZipCd(String requestZipCd) {
        this.requestZipCd = requestZipCd;
    }

    /**
     * @return requestAddressAreaCd
     */
    public String getRequestAddressAreaCd() {
        return requestAddressAreaCd;
    }

    /**
     * @param requestAddressAreaCd セットする requestAddressAreaCd
     */
    public void setRequestAddressAreaCd(String requestAddressAreaCd) {
        this.requestAddressAreaCd = requestAddressAreaCd;
    }

    /**
     * @return requestAddressPrefectureCd
     */
    public String getRequestAddressPrefectureCd() {
        return requestAddressPrefectureCd;
    }

    /**
     * @param requestAddressPrefectureCd セットする requestAddressPrefectureCd
     */
    public void setRequestAddressPrefectureCd(String requestAddressPrefectureCd) {
        this.requestAddressPrefectureCd = requestAddressPrefectureCd;
    }

    /**
     * @return requestAddressCity
     */
    public String getRequestAddressCity() {
        return requestAddressCity;
    }

    /**
     * @param requestAddressCity セットする requestAddressCity
     */
    public void setRequestAddressCity(String requestAddressCity) {
        this.requestAddressCity = requestAddressCity;
    }

    /**
     * @return requestAddressOthers
     */
    public String getRequestAddressOthers() {
        return requestAddressOthers;
    }

    /**
     * @param requestAddressOthers セットする requestAddressOthers
     */
    public void setRequestAddressOthers(String requestAddressOthers) {
        this.requestAddressOthers = requestAddressOthers;
    }

    /**
     * @return requestManagFamilyJnm
     */
    public String getRequestManagFamilyJnm() {
        return requestManagFamilyJnm;
    }

    /**
     * @param requestManagFamilyJnm セットする requestManagFamilyJnm
     */
    public void setRequestManagFamilyJnm(String requestManagFamilyJnm) {
        this.requestManagFamilyJnm = requestManagFamilyJnm;
    }

    /**
     * @return requestManagFirstJnm
     */
    public String getRequestManagFirstJnm() {
        return requestManagFirstJnm;
    }

    /**
     * @param requestManagFirstJnm セットする requestManagFirstJnm
     */
    public void setRequestManagFirstJnm(String requestManagFirstJnm) {
        this.requestManagFirstJnm = requestManagFirstJnm;
    }

    /**
     * @return requestManagFamilyKnm
     */
    public String getRequestManagFamilyKnm() {
        return requestManagFamilyKnm;
    }

    /**
     * @param requestManagFamilyKnm セットする requestManagFamilyKnm
     */
    public void setRequestManagFamilyKnm(String requestManagFamilyKnm) {
        this.requestManagFamilyKnm = requestManagFamilyKnm;
    }

    /**
     * @return requestManagFirstKnm
     */
    public String getRequestManagFirstKnm() {
        return requestManagFirstKnm;
    }

    /**
     * @param requestManagFirstKnm セットする requestManagFirstKnm
     */
    public void setRequestManagFirstKnm(String requestManagFirstKnm) {
        this.requestManagFirstKnm = requestManagFirstKnm;
    }

    /**
     * @return requestManagFamilyRomaji
     */
    public String getRequestManagFamilyRomaji() {
        return requestManagFamilyRomaji;
    }

    /**
     * @param requestManagFamilyRomaji セットする requestManagFamilyRomaji
     */
    public void setRequestManagFamilyRomaji(String requestManagFamilyRomaji) {
        this.requestManagFamilyRomaji = requestManagFamilyRomaji;
    }

    /**
     * @return requestManagFirstRomaji
     */
    public String getRequestManagFirstRomaji() {
        return requestManagFirstRomaji;
    }

    /**
     * @param requestManagFirstRomaji セットする requestManagFirstRomaji
     */
    public void setRequestManagFirstRomaji(String requestManagFirstRomaji) {
        this.requestManagFirstRomaji = requestManagFirstRomaji;
    }

    /**
     * @return requestManagPosition
     */
    public String getRequestManagPosition() {
        return requestManagPosition;
    }

    /**
     * @param requestManagPosition セットする requestManagPosition
     */
    public void setRequestManagPosition(String requestManagPosition) {
        this.requestManagPosition = requestManagPosition;
    }

    /**
     * @return requestPaymentSiteKbn
     */
    public String getRequestPaymentSiteKbn() {
        return requestPaymentSiteKbn;
    }

    /**
     * @param requestPaymentSiteKbn セットする requestPaymentSiteKbn
     */
    public void setRequestPaymentSiteKbn(String requestPaymentSiteKbn) {
        this.requestPaymentSiteKbn = requestPaymentSiteKbn;
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
     * @return returnCode
     */
    public int getReturnCode() {
        return returnCode;
    }

    /**
     * @param returnCode セットする returnCode
     */
    public void setReturnCode(int returnCode) {
        this.returnCode = returnCode;
    }

    /**
     * @return updateCd
     */
    public String getUpdateCd() {
        return updateCd;
    }

    /**
     * @param updateCd セットする updateCd
     */
    public void setUpdateCd(String updateCd) {
        this.updateCd = updateCd;
    }

    /**
     * @return passwordCheck
     */
    public String getPasswordCheck() {
        return passwordCheck;
    }

    /**
     * @param passwordCheck セットする passwordCheck
     */
    public void setPasswordCheck(String passwordCheck) {
        this.passwordCheck = passwordCheck;
    }

}