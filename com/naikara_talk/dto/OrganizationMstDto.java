package com.naikara_talk.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称:</b>共通部品 DTOクラス<br>
 * <b>クラス名称　　　:</b>組織マスタクラス<br>
 * <b>クラス概要　　　:</b>組織マスタDTO<br>
 * <br>
 * <b>著作権　　　　　:</b>Copyright (C) 2013 NAI, Inc. All rights reserved.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/07/03 TECS 新規作成
 */
public class OrganizationMstDto extends AbstractDto {

    private String organizationId;             // 組織ID
    private int consSeq;                       // 連番
    private String password;                   // パスワード
    private String organizationJnm;            // 組織名
    private String organizationKnm;            // 組織名(フリガナ)
    private String organizationRomaji;         // 組織名(ローマ字)
    private String tel;                        // 電話番号
    private String zipCd;                      // 郵便番号
    private String addressAreaCd;              // 住所(地域)コード
    private String addressPrefectureCd;        // 住所(都道府県)コード
    private String addressCity;                // 住所(市区町村)
    private String addressOthers;              // 住所(ﾋﾞﾙ、ﾏﾝｼｮﾝ名 等)
    private String managFamilyJnm;             // 責任者名(姓)
    private String managFirstJnm;              // 責任者名(名)
    private String managFamilyKnm;             // 責任者名フリガナ(姓)
    private String managFirstKnm;              // 責任者名フリガナ(名)
    private String managFamilyRomaji;          // 責任者名ローマ字(姓)
    private String managFirstRomaji;           // 責任者名ローマ字(名)
    private String managPosition;              // 責任者所属
    private String managGenderKbn;             // 責任者性別区分
    private String managMailAddress1;          // 責任者メールアドレス1
    private String managMailAddress2;          // 責任者メールアドレス2
    private String managMailAddress3;          // 責任者メールアドレス3
    private String contractStrDt;              // 契約開始日
    private String contractEndDt;              // 契約終了日
    private BigDecimal tempPointNum;           // 仮ポイント数
    private BigDecimal balancePointNum;        // 未割振ポイント(残高)
    private String requestAddressKbn;          // 請求先住所区分
    private String remarks;                    // 備考
    private String requestOrganizationJnm;     // 請求先情報：組織名
    private String requestOrganizationKnm;     // 請求先情報：組織名(カナ)
    private String requestOrganizationRnm;     // 請求先情報：組織名(ローマ字)
    private String requestTel;                 // 請求先情報：電話番号
    private String requestZipCd;               // 請求先情報：郵便番号
    private String requestAddressAreaCd;       // 請求先情報：住所(地域)コード
    private String requestAddressPrefectureCd; // 請求先情報：住所(都道府県)コード
    private String requestAddressCity;         // 請求先情報：住所(市区町村)
    private String requestAddressOthers;       // 請求先情報：住所(ﾋﾞﾙ、ﾏﾝｼｮﾝ名 等)
    private String requestManagFamilyJnm;      // 請求先情報：責任者名(姓)
    private String requestManagFirstJnm;       // 請求先情報：責任者名(名)
    private String requestManagFamilyKnm;      // 請求先情報：責任者名フリガナ(姓)
    private String requestManagFirstKnm;       // 請求先情報：責任者名フリガナ(名)
    private String requestManagFamilyRomaji;   // 請求先情報：責任者名ローマ字(姓)
    private String requestManagFirstRomaji;    // 請求先情報：責任者名ローマ字(名)
    private String requestManagPosition;       // 請求先情報：責任者所属
    private String requestPaymentSiteKbn;      // 請求先情報：支払サイト区分
    private int recordVerNo;                   // レコードバージョン番号
    private Timestamp insertDttm;              // 登録日時
    private String insertCd;                   // 登録者コード
    private Timestamp updateDttm;              // 更新日時
    private String updateCd;                   // 更新者コード
    private int returnCode;                    // リターンコード

    // 画面処理必要項目
    private String codeCategoryContact;            // お問合せの目的
    private String subject;                        // 件名
    private String contactText;                    // ご意見・ご要望・お問合せ内容
    private String managGenderKbnNm;               // 責任者性別区分名
    private String managKnm;                       // 組織責任者名(フリガナ)
    private String managJnm;                       // 組織責任者名

    /**
     * 組織ID取得<br>
     * <br>
     * 組織IDを戻り値で返却する<br>
     * <br>
     * @return organizationId
     */
    public String getOrganizationId() {
        return organizationId;
    }

    /**
     * 組織ID設定<br>
     * <br>
     * 組織IDを引数で設定する<br>
     * <br>
     * @param organizationId
     */
    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    /**
     * 連番取得<br>
     * <br>
     * 連番を戻り値で返却する<br>
     * <br>
     * @return consSeq
     */
    public int getConsSeq() {
        return consSeq;
    }

    /**
     * 連番設定<br>
     * <br>
     * 連番を引数で設定する<br>
     * <br>
     * @param consSeq
     */
    public void setConsSeq(int consSeq) {
        this.consSeq = consSeq;
    }

    /**
     * パスワード取得<br>
     * <br>
     * パスワードを戻り値で返却する<br>
     * <br>
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * パスワード設定<br>
     * <br>
     * パスワードを引数で設定する<br>
     * <br>
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 組織名取得<br>
     * <br>
     * 組織名を戻り値で返却する<br>
     * <br>
     * @return organizationJnm
     */
    public String getOrganizationJnm() {
        return organizationJnm;
    }

    /**
     * 組織名設定<br>
     * <br>
     * 組織名を引数で設定する<br>
     * <br>
     * @param organizationJnm
     */
    public void setOrganizationJnm(String organizationJnm) {
        this.organizationJnm = organizationJnm;
    }

    /**
     * 組織名(フリガナ)取得<br>
     * <br>
     * 組織名(フリガナ)を戻り値で返却する<br>
     * <br>
     * @return organizationKnm
     */
    public String getOrganizationKnm() {
        return organizationKnm;
    }

    /**
     * 組織名(フリガナ)設定<br>
     * <br>
     * 組織名(フリガナ)を引数で設定する<br>
     * <br>
     * @param organizationKnm
     */
    public void setOrganizationKnm(String organizationKnm) {
        this.organizationKnm = organizationKnm;
    }

    /**
     * 組織名(ローマ字)取得<br>
     * <br>
     * 組織名(ローマ字)を戻り値で返却する<br>
     * <br>
     * @return organizationRomaji
     */
    public String getOrganizationRomaji() {
        return organizationRomaji;
    }

    /**
     * 組織名(ローマ字)設定<br>
     * <br>
     * 組織名(ローマ字)を引数で設定する<br>
     * <br>
     * @param organizationRomaji
     */
    public void setOrganizationRomaji(String organizationRomaji) {
        this.organizationRomaji = organizationRomaji;
    }

    /**
     * 電話番号取得<br>
     * <br>
     * 電話番号を戻り値で返却する<br>
     * <br>
     * @return tel
     */
    public String getTel() {
        return tel;
    }

    /**
     * 電話番号設定<br>
     * <br>
     * 電話番号を引数で設定する<br>
     * <br>
     * @param tel
     */
    public void setTel(String tel) {
        this.tel = tel;
    }

    /**
     * 郵便番号取得<br>
     * <br>
     * 郵便番号を戻り値で返却する<br>
     * <br>
     * @return zipCd
     */
    public String getZipCd() {
        return zipCd;
    }

    /**
     * 郵便番号設定<br>
     * <br>
     * 郵便番号を引数で設定する<br>
     * <br>
     * @param zipCd
     */
    public void setZipCd(String zipCd) {
        this.zipCd = zipCd;
    }

    /**
     * 住所(地域)コード取得<br>
     * <br>
     * 住所(地域)コードを戻り値で返却する<br>
     * <br>
     * @return addressAreaCd
     */
    public String getAddressAreaCd() {
        return addressAreaCd;
    }

    /**
     * 住所(地域)コード設定<br>
     * <br>
     * 住所(地域)コードを引数で設定する<br>
     * <br>
     * @param addressAreaCd
     */
    public void setAddressAreaCd(String addressAreaCd) {
        this.addressAreaCd = addressAreaCd;
    }

    /**
     * 住所(都道府県)コード取得<br>
     * <br>
     * 住所(都道府県)コードを戻り値で返却する<br>
     * <br>
     * @return addressPrefectureCd
     */
    public String getAddressPrefectureCd() {
        return addressPrefectureCd;
    }

    /**
     * 住所(都道府県)コード設定<br>
     * <br>
     * 住所(都道府県)コードを引数で設定する<br>
     * <br>
     * @param addressPrefectureCd
     */
    public void setAddressPrefectureCd(String addressPrefectureCd) {
        this.addressPrefectureCd = addressPrefectureCd;
    }

    /**
     * 住所(市区町村)取得<br>
     * <br>
     * 住所(市区町村)を戻り値で返却する<br>
     * <br>
     * @return addressCity
     */
    public String getAddressCity() {
        return addressCity;
    }

    /**
     * 住所(市区町村)設定<br>
     * <br>
     * 住所(市区町村)を引数で設定する<br>
     * <br>
     * @param addressCity
     */
    public void setAddressCity(String addressCity) {
        this.addressCity = addressCity;
    }

    /**
     * 住所(ﾋﾞﾙ、ﾏﾝｼｮﾝ名 等)取得<br>
     * <br>
     * 住所(ﾋﾞﾙ、ﾏﾝｼｮﾝ名 等)を戻り値で返却する<br>
     * <br>
     * @return addressOthers
     */
    public String getAddressOthers() {
        return addressOthers;
    }

    /**
     * 住所(ﾋﾞﾙ、ﾏﾝｼｮﾝ名 等)設定<br>
     * <br>
     * 住所(ﾋﾞﾙ、ﾏﾝｼｮﾝ名 等)を引数で設定する<br>
     * <br>
     * @param addressOthers
     */
    public void setAddressOthers(String addressOthers) {
        this.addressOthers = addressOthers;
    }

    /**
     * 責任者名(姓)取得<br>
     * <br>
     * 責任者名(姓)を戻り値で返却する<br>
     * <br>
     * @return managFamilyJnm
     */
    public String getManagFamilyJnm() {
        return managFamilyJnm;
    }

    /**
     * 責任者名(姓)設定<br>
     * <br>
     * 責任者名(姓)を引数で設定する<br>
     * <br>
     * @param managFamilyJnm
     */
    public void setManagFamilyJnm(String managFamilyJnm) {
        this.managFamilyJnm = managFamilyJnm;
    }

    /**
     * 責任者名(名)取得<br>
     * <br>
     * 責任者名(名)を戻り値で返却する<br>
     * <br>
     * @return managFirstJnm
     */
    public String getManagFirstJnm() {
        return managFirstJnm;
    }

    /**
     * 責任者名(名)設定<br>
     * <br>
     * 責任者名(名)を引数で設定する<br>
     * <br>
     * @param managFirstJnm
     */
    public void setManagFirstJnm(String managFirstJnm) {
        this.managFirstJnm = managFirstJnm;
    }

    /**
     * 責任者名フリガナ(姓)取得<br>
     * <br>
     * 責任者名フリガナ(姓)を戻り値で返却する<br>
     * <br>
     * @return managFamilyKnm
     */
    public String getManagFamilyKnm() {
        return managFamilyKnm;
    }

    /**
     * 責任者名フリガナ(姓)設定<br>
     * <br>
     * 責任者名フリガナ(姓)を引数で設定する<br>
     * <br>
     * @param managFamilyKnm
     */
    public void setManagFamilyKnm(String managFamilyKnm) {
        this.managFamilyKnm = managFamilyKnm;
    }

    /**
     * 責任者名フリガナ(名)取得<br>
     * <br>
     * 責任者名フリガナ(名)を戻り値で返却する<br>
     * <br>
     * @return managFirstKnm
     */
    public String getManagFirstKnm() {
        return managFirstKnm;
    }

    /**
     * 責任者名フリガナ(名)設定<br>
     * <br>
     * 責任者名フリガナ(名)を引数で設定する<br>
     * <br>
     * @param managFirstKnm
     */
    public void setManagFirstKnm(String managFirstKnm) {
        this.managFirstKnm = managFirstKnm;
    }

    /**
     * 責任者名ローマ字(姓)取得<br>
     * <br>
     * 責任者名ローマ字(姓)を戻り値で返却する<br>
     * <br>
     * @return managFamilyRomaji
     */
    public String getManagFamilyRomaji() {
        return managFamilyRomaji;
    }

    /**
     * 責任者名ローマ字(姓)設定<br>
     * <br>
     * 責任者名ローマ字(姓)を引数で設定する<br>
     * <br>
     * @param managFamilyRomaji
     */
    public void setManagFamilyRomaji(String managFamilyRomaji) {
        this.managFamilyRomaji = managFamilyRomaji;
    }

    /**
     * 責任者名ローマ字(名)取得<br>
     * <br>
     * 責任者名ローマ字(名)を戻り値で返却する<br>
     * <br>
     * @return managFirstRomaji
     */
    public String getManagFirstRomaji() {
        return managFirstRomaji;
    }

    /**
     * 責任者名ローマ字(名)設定<br>
     * <br>
     * 責任者名ローマ字(名)を引数で設定する<br>
     * <br>
     * @param managFirstRomaji
     */
    public void setManagFirstRomaji(String managFirstRomaji) {
        this.managFirstRomaji = managFirstRomaji;
    }

    /**
     * 責任者所属取得<br>
     * <br>
     * 責任者所属を戻り値で返却する<br>
     * <br>
     * @return managPosition
     */
    public String getManagPosition() {
        return managPosition;
    }

    /**
     * 責任者所属設定<br>
     * <br>
     * 責任者所属を引数で設定する<br>
     * <br>
     * @param managPosition
     */
    public void setManagPosition(String managPosition) {
        this.managPosition = managPosition;
    }

    /**
     * 責任者性別区分取得<br>
     * <br>
     * 責任者性別区分を戻り値で返却する<br>
     * <br>
     * @return managGenderKbn
     */
    public String getManagGenderKbn() {
        return managGenderKbn;
    }

    /**
     * 責任者性別区分設定<br>
     * <br>
     * 責任者性別区分を引数で設定する<br>
     * <br>
     * @param managGenderKbn
     */
    public void setManagGenderKbn(String managGenderKbn) {
        this.managGenderKbn = managGenderKbn;
    }

    /**
     * 責任者メールアドレス1取得<br>
     * <br>
     * 責任者メールアドレス1を戻り値で返却する<br>
     * <br>
     * @return managMailAddress1
     */
    public String getManagMailAddress1() {
        return managMailAddress1;
    }

    /**
     * 責任者メールアドレス1設定<br>
     * <br>
     * 責任者メールアドレス1を引数で設定する<br>
     * <br>
     * @param managMailAddress1
     */
    public void setManagMailAddress1(String managMailAddress1) {
        this.managMailAddress1 = managMailAddress1;
    }

    /**
     * 責任者メールアドレス2取得<br>
     * <br>
     * 責任者メールアドレス2を戻り値で返却する<br>
     * <br>
     * @return managMailAddress2
     */
    public String getManagMailAddress2() {
        return managMailAddress2;
    }

    /**
     * 責任者メールアドレス2設定<br>
     * <br>
     * 責任者メールアドレス2を引数で設定する<br>
     * <br>
     * @param managMailAddress2
     */
    public void setManagMailAddress2(String managMailAddress2) {
        this.managMailAddress2 = managMailAddress2;
    }

    /**
     * 責任者メールアドレス3取得<br>
     * <br>
     * 責任者メールアドレス3を戻り値で返却する<br>
     * <br>
     * @return managMailAddress3
     */
    public String getManagMailAddress3() {
        return managMailAddress3;
    }

    /**
     * 責任者メールアドレス3設定<br>
     * <br>
     * 責任者メールアドレス3を引数で設定する<br>
     * <br>
     * @param managMailAddress3
     */
    public void setManagMailAddress3(String managMailAddress3) {
        this.managMailAddress3 = managMailAddress3;
    }

    /**
     * 契約開始日取得<br>
     * <br>
     * 契約開始日を戻り値で返却する<br>
     * <br>
     * @return contractStrDt
     */
    public String getContractStrDt() {
        return contractStrDt;
    }

    /**
     * 契約開始日設定<br>
     * <br>
     * 契約開始日を引数で設定する<br>
     * <br>
     * @param contractStrDt
     */
    public void setContractStrDt(String contractStrDt) {
        this.contractStrDt = contractStrDt;
    }

    /**
     * 契約終了日取得<br>
     * <br>
     * 契約終了日を戻り値で返却する<br>
     * <br>
     * @return contractEndDt
     */
    public String getContractEndDt() {
        return contractEndDt;
    }

    /**
     * 契約終了日設定<br>
     * <br>
     * 契約終了日を引数で設定する<br>
     * <br>
     * @param contractEndDt
     */
    public void setContractEndDt(String contractEndDt) {
        this.contractEndDt = contractEndDt;
    }

    /**
     * 仮ポイント数取得<br>
     * <br>
     * 仮ポイント数を戻り値で返却する<br>
     * <br>
     * @return tempPointNum
     */
    public BigDecimal getTempPointNum() {
        return tempPointNum;
    }

    /**
     * 仮ポイント数設定<br>
     * <br>
     * 仮ポイント数を引数で設定する<br>
     * <br>
     * @param tempPointNum
     */
    public void setTempPointNum(BigDecimal tempPointNum) {
        this.tempPointNum = tempPointNum;
    }

    /**
     * 未割振ポイント(残高)取得<br>
     * <br>
     * 未割振ポイント(残高)を戻り値で返却する<br>
     * <br>
     * @return balancePointNum
     */
    public BigDecimal getBalancePointNum() {
        return balancePointNum;
    }

    /**
     * 未割振ポイント(残高)設定<br>
     * <br>
     * 未割振ポイント(残高)を引数で設定する<br>
     * <br>
     * @param balancePointNum
     */
    public void setBalancePointNum(BigDecimal balancePointNum) {
        this.balancePointNum = balancePointNum;
    }

    /**
     * 請求先住所区分取得<br>
     * <br>
     * 請求先住所区分を戻り値で返却する<br>
     * <br>
     * @return requestAddressKbn
     */
    public String getRequestAddressKbn() {
        return requestAddressKbn;
    }

    /**
     * 請求先住所区分設定<br>
     * <br>
     * 請求先住所区分を引数で設定する<br>
     * <br>
     * @param requestAddressKbn
     */
    public void setRequestAddressKbn(String requestAddressKbn) {
        this.requestAddressKbn = requestAddressKbn;
    }

    /**
     * 備考取得<br>
     * <br>
     * 備考を戻り値で返却する<br>
     * <br>
     * @return remarks
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * 備考設定<br>
     * <br>
     * 備考を引数で設定する<br>
     * <br>
     * @param remarks
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    /**
     * 請求先情報：組織名取得<br>
     * <br>
     * 請求先情報：組織名を戻り値で返却する<br>
     * <br>
     * @return requestOrganizationJnm
     */
    public String getRequestOrganizationJnm() {
        return requestOrganizationJnm;
    }

    /**
     * 請求先情報：組織名設定<br>
     * <br>
     * 請求先情報：組織名を引数で設定する<br>
     * <br>
     * @param requestOrganizationJnm
     */
    public void setRequestOrganizationJnm(String requestOrganizationJnm) {
        this.requestOrganizationJnm = requestOrganizationJnm;
    }

    /**
     * 請求先情報：組織名(カナ)取得<br>
     * <br>
     * 請求先情報：組織名(カナ)を戻り値で返却する<br>
     * <br>
     * @return requestOrganizationKnm
     */
    public String getRequestOrganizationKnm() {
        return requestOrganizationKnm;
    }

    /**
     * 請求先情報：組織名(カナ)設定<br>
     * <br>
     * 請求先情報：組織名(カナ)を引数で設定する<br>
     * <br>
     * @param requestOrganizationKnm
     */
    public void setRequestOrganizationKnm(String requestOrganizationKnm) {
        this.requestOrganizationKnm = requestOrganizationKnm;
    }

    /**
     * 請求先情報：組織名(ローマ字)取得<br>
     * <br>
     * 請求先情報：組織名(ローマ字)を戻り値で返却する<br>
     * <br>
     * @return requestOrganizationRnm
     */
    public String getRequestOrganizationRnm() {
        return requestOrganizationRnm;
    }

    /**
     * 請求先情報：組織名(ローマ字)設定<br>
     * <br>
     * 請求先情報：組織名(ローマ字)を引数で設定する<br>
     * <br>
     * @param requestOrganizationRnm
     */
    public void setRequestOrganizationRnm(String requestOrganizationRnm) {
        this.requestOrganizationRnm = requestOrganizationRnm;
    }

    /**
     * 請求先情報：電話番号取得<br>
     * <br>
     * 請求先情報：電話番号を戻り値で返却する<br>
     * <br>
     * @return requestTel
     */
    public String getRequestTel() {
        return requestTel;
    }

    /**
     * 請求先情報：電話番号設定<br>
     * <br>
     * 請求先情報：電話番号を引数で設定する<br>
     * <br>
     * @param requestTel
     */
    public void setRequestTel(String requestTel) {
        this.requestTel = requestTel;
    }

    /**
     * 請求先情報：郵便番号取得<br>
     * <br>
     * 請求先情報：郵便番号を戻り値で返却する<br>
     * <br>
     * @return requestZipCd
     */
    public String getRequestZipCd() {
        return requestZipCd;
    }

    /**
     * 請求先情報：郵便番号設定<br>
     * <br>
     * 請求先情報：郵便番号を引数で設定する<br>
     * <br>
     * @param requestZipCd
     */
    public void setRequestZipCd(String requestZipCd) {
        this.requestZipCd = requestZipCd;
    }

    /**
     * 請求先情報：住所(地域)コード取得<br>
     * <br>
     * 請求先情報：住所(地域)コードを戻り値で返却する<br>
     * <br>
     * @return requestAddressAreaCd
     */
    public String getRequestAddressAreaCd() {
        return requestAddressAreaCd;
    }

    /**
     * 請求先情報：住所(地域)コード設定<br>
     * <br>
     * 請求先情報：住所(地域)コードを引数で設定する<br>
     * <br>
     * @param requestAddressAreaCd
     */
    public void setRequestAddressAreaCd(String requestAddressAreaCd) {
        this.requestAddressAreaCd = requestAddressAreaCd;
    }

    /**
     * 請求先情報：住所(都道府県)コード取得<br>
     * <br>
     * 請求先情報：住所(都道府県)コードを戻り値で返却する<br>
     * <br>
     * @return requestAddressPrefectureCd
     */
    public String getRequestAddressPrefectureCd() {
        return requestAddressPrefectureCd;
    }

    /**
     * 請求先情報：住所(都道府県)コード設定<br>
     * <br>
     * 請求先情報：住所(都道府県)コードを引数で設定する<br>
     * <br>
     * @param requestAddressPrefectureCd
     */
    public void setRequestAddressPrefectureCd(String requestAddressPrefectureCd) {
        this.requestAddressPrefectureCd = requestAddressPrefectureCd;
    }

    /**
     * 請求先情報：住所(市区町村)取得<br>
     * <br>
     * 請求先情報：住所(市区町村)を戻り値で返却する<br>
     * <br>
     * @return requestAddressCity
     */
    public String getRequestAddressCity() {
        return requestAddressCity;
    }

    /**
     * 請求先情報：住所(市区町村)設定<br>
     * <br>
     * 請求先情報：住所(市区町村)を引数で設定する<br>
     * <br>
     * @param requestAddressCity
     */
    public void setRequestAddressCity(String requestAddressCity) {
        this.requestAddressCity = requestAddressCity;
    }

    /**
     * 請求先情報：住所(ﾋﾞﾙ、ﾏﾝｼｮﾝ名 等)取得<br>
     * <br>
     * 請求先情報：住所(ﾋﾞﾙ、ﾏﾝｼｮﾝ名 等)を戻り値で返却する<br>
     * <br>
     * @return requestAddressOthers
     */
    public String getRequestAddressOthers() {
        return requestAddressOthers;
    }

    /**
     * 請求先情報：住所(ﾋﾞﾙ、ﾏﾝｼｮﾝ名 等)設定<br>
     * <br>
     * 請求先情報：住所(ﾋﾞﾙ、ﾏﾝｼｮﾝ名 等)を引数で設定する<br>
     * <br>
     * @param requestAddressOthers
     */
    public void setRequestAddressOthers(String requestAddressOthers) {
        this.requestAddressOthers = requestAddressOthers;
    }

    /**
     * 請求先情報：責任者名(姓)取得<br>
     * <br>
     * 請求先情報：責任者名(姓)を戻り値で返却する<br>
     * <br>
     * @return requestManagFamilyJnm
     */
    public String getRequestManagFamilyJnm() {
        return requestManagFamilyJnm;
    }

    /**
     * 請求先情報：責任者名(姓)設定<br>
     * <br>
     * 請求先情報：責任者名(姓)を引数で設定する<br>
     * <br>
     * @param requestManagFamilyJnm
     */
    public void setRequestManagFamilyJnm(String requestManagFamilyJnm) {
        this.requestManagFamilyJnm = requestManagFamilyJnm;
    }

    /**
     * 請求先情報：責任者名(名)取得<br>
     * <br>
     * 請求先情報：責任者名(名)を戻り値で返却する<br>
     * <br>
     * @return requestManagFirstJnm
     */
    public String getRequestManagFirstJnm() {
        return requestManagFirstJnm;
    }

    /**
     * 請求先情報：責任者名(名)設定<br>
     * <br>
     * 請求先情報：責任者名(名)を引数で設定する<br>
     * <br>
     * @param requestManagFirstJnm
     */
    public void setRequestManagFirstJnm(String requestManagFirstJnm) {
        this.requestManagFirstJnm = requestManagFirstJnm;
    }

    /**
     * 請求先情報：責任者名フリガナ(姓)取得<br>
     * <br>
     * 請求先情報：責任者名フリガナ(姓)を戻り値で返却する<br>
     * <br>
     * @return requestManagFamilyKnm
     */
    public String getRequestManagFamilyKnm() {
        return requestManagFamilyKnm;
    }

    /**
     * 請求先情報：責任者名フリガナ(姓)設定<br>
     * <br>
     * 請求先情報：責任者名フリガナ(姓)を引数で設定する<br>
     * <br>
     * @param requestManagFamilyKnm
     */
    public void setRequestManagFamilyKnm(String requestManagFamilyKnm) {
        this.requestManagFamilyKnm = requestManagFamilyKnm;
    }

    /**
     * 請求先情報：責任者名フリガナ(名)取得<br>
     * <br>
     * 請求先情報：責任者名フリガナ(名)を戻り値で返却する<br>
     * <br>
     * @return requestManagFirstKnm
     */
    public String getRequestManagFirstKnm() {
        return requestManagFirstKnm;
    }

    /**
     * 請求先情報：責任者名フリガナ(名)設定<br>
     * <br>
     * 請求先情報：責任者名フリガナ(名)を引数で設定する<br>
     * <br>
     * @param requestManagFirstKnm
     */
    public void setRequestManagFirstKnm(String requestManagFirstKnm) {
        this.requestManagFirstKnm = requestManagFirstKnm;
    }

    /**
     * 請求先情報：責任者名ローマ字(姓)取得<br>
     * <br>
     * 請求先情報：責任者名ローマ字(姓)を戻り値で返却する<br>
     * <br>
     * @return requestManagFamilyRomaji
     */
    public String getRequestManagFamilyRomaji() {
        return requestManagFamilyRomaji;
    }

    /**
     * 請求先情報：責任者名ローマ字(姓)設定<br>
     * <br>
     * 請求先情報：責任者名ローマ字(姓)を引数で設定する<br>
     * <br>
     * @param requestManagFamilyRomaji
     */
    public void setRequestManagFamilyRomaji(String requestManagFamilyRomaji) {
        this.requestManagFamilyRomaji = requestManagFamilyRomaji;
    }

    /**
     * 請求先情報：責任者名ローマ字(名)取得<br>
     * <br>
     * 請求先情報：責任者名ローマ字(名)を戻り値で返却する<br>
     * <br>
     * @return requestManagFirstRomaji
     */
    public String getRequestManagFirstRomaji() {
        return requestManagFirstRomaji;
    }

    /**
     * 請求先情報：責任者名ローマ字(名)設定<br>
     * <br>
     * 請求先情報：責任者名ローマ字(名)を引数で設定する<br>
     * <br>
     * @param requestManagFirstRomaji
     */
    public void setRequestManagFirstRomaji(String requestManagFirstRomaji) {
        this.requestManagFirstRomaji = requestManagFirstRomaji;
    }

    /**
     * 請求先情報：責任者所属取得<br>
     * <br>
     * 請求先情報：責任者所属を戻り値で返却する<br>
     * <br>
     * @return requestManagPosition
     */
    public String getRequestManagPosition() {
        return requestManagPosition;
    }

    /**
     * 請求先情報：責任者所属設定<br>
     * <br>
     * 請求先情報：責任者所属を引数で設定する<br>
     * <br>
     * @param requestManagPosition
     */
    public void setRequestManagPosition(String requestManagPosition) {
        this.requestManagPosition = requestManagPosition;
    }

    /**
     * 請求先情報：支払サイト区分取得<br>
     * <br>
     * 請求先情報：支払サイト区分を戻り値で返却する<br>
     * <br>
     * @return requestPaymentSiteKbn
     */
    public String getRequestPaymentSiteKbn() {
        return requestPaymentSiteKbn;
    }

    /**
     * 請求先情報：支払サイト区分設定<br>
     * <br>
     * 請求先情報：支払サイト区分を引数で設定する<br>
     * <br>
     * @param requestPaymentSiteKbn
     */
    public void setRequestPaymentSiteKbn(String requestPaymentSiteKbn) {
        this.requestPaymentSiteKbn = requestPaymentSiteKbn;
    }

    /**
     * レコードバージョン番号取得<br>
     * <br>
     * レコードバージョン番号を戻り値で返却する<br>
     * <br>
     * @return recordVerNo
     */
    public int getRecordVerNo() {
        return recordVerNo;
    }

    /**
     * レコードバージョン番号設定<br>
     * <br>
     * レコードバージョン番号を引数で設定する<br>
     * <br>
     * @param recordVerNo
     */
    public void setRecordVerNo(int recordVerNo) {
        this.recordVerNo = recordVerNo;
    }

    /**
     * 登録日時取得<br>
     * <br>
     * 登録日時を戻り値で返却する<br>
     * <br>
     * @return insertDttm
     */
    public Timestamp getInsertDttm() {
        return insertDttm;
    }

    /**
     * 登録日時設定<br>
     * <br>
     * 登録日時を引数で設定する<br>
     * <br>
     * @param insertDttm
     */
    public void setInsertDttm(Timestamp insertDttm) {
        this.insertDttm = insertDttm;
    }

    /**
     * 登録者コード取得<br>
     * <br>
     * 登録者コードを戻り値で返却する<br>
     * <br>
     * @return insertCd
     */
    public String getInsertCd() {
        return insertCd;
    }

    /**
     * 登録者コード設定<br>
     * <br>
     * 登録者コードを引数で設定する<br>
     * <br>
     * @param insertCd
     */
    public void setInsertCd(String insertCd) {
        this.insertCd = insertCd;
    }

    /**
     * 更新日時取得<br>
     * <br>
     * 更新日時を戻り値で返却する<br>
     * <br>
     * @return updateDttm
     */
    public Timestamp getUpdateDttm() {
        return updateDttm;
    }

    /**
     * 更新日時設定<br>
     * <br>
     * 更新日時を引数で設定する<br>
     * <br>
     * @param updateDttm
     */
    public void setUpdateDttm(Timestamp updateDttm) {
        this.updateDttm = updateDttm;
    }

    /**
     * 更新者コード取得<br>
     * <br>
     * 更新者コードを戻り値で返却する<br>
     * <br>
     * @return updateCd
     */
    public String getUpdateCd() {
        return updateCd;
    }

    /**
     * 更新者コード設定<br>
     * <br>
     * 更新者コードを引数で設定する<br>
     * <br>
     * @param updateCd
     */
    public void setUpdateCd(String updateCd) {
        this.updateCd = updateCd;
    }

    /**
     * リターンコード取得<br>
     * <br>
     * リターンコードを戻り値で返却する<br>
     * <br>
     * @return returnCode
     */
    public int getReturnCode() {
        return returnCode;
    }

    /**
     * リターンコード設定<br>
     * <br>
     * リターンコードを引数で設定する<br>
     * <br>
     * @param returnCode
     */
    public void setReturnCode(int returnCode) {
        this.returnCode = returnCode;
    }

    /**
     * お問合せの目的取得<br>
     * <br>
     * お問合せの目的を戻り値で返却する<br>
     * <br>
     * @return codeCategoryContact
     */
    public String getCodeCategoryContact() {
        return codeCategoryContact;
    }

    /**
     * お問合せの目的設定<br>
     * <br>
     * お問合せの目的を引数で設定する<br>
     * <br>
     * @param codeCategoryContact
     */
    public void setCodeCategoryContact(String codeCategoryContact) {
        this.codeCategoryContact = codeCategoryContact;
    }

    /**
     * 件名取得<br>
     * <br>
     * 件名を戻り値で返却する<br>
     * <br>
     * @return subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * 件名設定<br>
     * <br>
     * 件名を引数で設定する<br>
     * <br>
     * @param subject
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * ご意見・ご要望・お問合せ内容取得<br>
     * <br>
     * ご意見・ご要望・お問合せ内容を戻り値で返却する<br>
     * <br>
     * @return contactText
     */
    public String getContactText() {
        return contactText;
    }

    /**
     * ご意見・ご要望・お問合せ内容設定<br>
     * <br>
     * ご意見・ご要望・お問合せ内容を引数で設定する<br>
     * <br>
     * @param contactText
     */
    public void setContactText(String contactText) {
        this.contactText = contactText;
    }

    /**
     * 責任者性別区分名取得<br>
     * <br>
     * 責任者性別区分名を戻り値で返却する<br>
     * <br>
     * @return contactText
     */
    public String getManagGenderKbnNm() {
        return managGenderKbnNm;
    }

    /**
     * 責任者性別区分名設定<br>
     * <br>
     * 責任者性別区分名を引数で設定する<br>
     * <br>
     * @param contactText
     */
    public void setManagGenderKbnNm(String managGenderKbnNm) {
        this.managGenderKbnNm = managGenderKbnNm;
    }

    /**
     * 組織責任者名(フリガナ)取得<br>
     * <br>
     * 組織責任者名(フリガナ)を戻り値で返却する<br>
     * <br>
     * @return managKnm
     */
    public String getManagKnm() {
        return managKnm;
    }

    /**
     * 組織責任者名(フリガナ)設定<br>
     * <br>
     * 組織責任者名(フリガナ)を引数で設定する<br>
     * <br>
     * @param managKnm
     */
    public void setManagKnm(String managKnm) {
        this.managKnm = managKnm;
    }

    /**
     * 組織責任者名取得<br>
     * <br>
     * 組織責任者名を戻り値で返却する<br>
     * <br>
     * @return managKnm
     */
    public String getManagJnm() {
        return managJnm;
    }

    /**
     * 組織責任者名設定<br>
     * <br>
     * 組織責任者名を引数で設定する<br>
     * <br>
     * @param managKnm
     */
    public void setManagJnm(String managJnm) {
        this.managJnm = managJnm;
    }

}
