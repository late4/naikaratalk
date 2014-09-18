package com.naikara_talk.model;

import java.io.File;
import java.util.List;

import com.naikara_talk.dto.TeacherCourseDto;
import com.naikara_talk.dto.TeacherRateMstDto;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>講師初期登録ページ。<br>
 * <b>クラス名称       :</b>講師初期登録ページ初期処理Modelクラス。<br>
 * <b>クラス概要       :</b>講師初期登録ページ初期処理Model。<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/01 TECS 新規作成
 */
public class TeacherRegistrationModel implements Model {

    private static final long serialVersionUID = 1L;

    /* チェック：問題なし(正常) */
    public static final int CHECK_OK = 0;

    /** 利用者ID */
    private String userId;
    /** 名前（苗字） */
    private String familyJnm;
    /** 名前（名前） */
    private String firstJnm;
    /** フリガナ(姓) */
    private String familyKnm;
    /** フリガナ(名） */
    private String firstKnm;
    /** ローマ字(姓) */
    private String familyRomaji;
    /** ローマ字(名) */
    private String firstRomaji;
    /** ニックネーム  */
    private String nickAnm;
    /** 電話番号（自宅/携帯） */
    private String tel1;
    /** 電話番号（緊急連絡先） */
    private String tel2;
    /** 生年月日 */
    private String birthYyyyMmDd;
    /** 郵便番号 */
    private String zipCd;
    /** 住所 */
    private String addressCity;
    /** 住所(ﾋﾞﾙ、ﾏﾝｼｮﾝ名 等) */
    private String addressOthers;
    /** 性別 */
    private String gender;
    /** 国籍 */
    private String nationality;
    /** 母国語 */
    private String nativeLang;
    /** メールアドレス１ */
    private String mailAddress1;
    /** メールアドレス２ */
    private String mailAddress2;
    /** メールアドレス３ */
    private String mailAddress3;
    /** 滞在国 */
    private String country;
    /** 時差地域No */
    private String areaNo;
    /** 勤務都市名 */
    private String cityTown;
    /** 現行支払設定期間等一覧 */
    private List<TeacherRateMstDto> teacherRateMstDto;
    /** 契約日 */
    private String contractDt;
    /** 契約期間：開始日 */
    private String contractStartDt;
    /** 契約期間：終了日 */
    private String contractEndDt;
    /** 銀行名 */
    private String bankJpnBankNm;
    /** 支店名 */
    private String bankJpnBranchNm;
    /** 支店名 */
    private String bankJpnTypeOfAccount;
    /** 支店名 */
    private String bankJpnAccountHoldersKnm;
    /** 支店名 */
    private String bankJpnAccountHoldersNm;
    /** 支店名 */
    private String bankJpnAccountNumber;
    /** 支店名 */
    private String bankJpnAdditionalInfo;
    /** 店番 */
    private String jpnPbankBranchNm;
    /** 預金種別 */
    private String jpnPbankTypeOfAccount;
    /** 口座名義人(フリガナ) */
    private String jpnPbankAccountHoldersKnm;
    /** 口座名義人 */
    private String jpnPbankAccountHoldersNm;
    /** 口座番号 */
    private String jpnPbankAccountNumber;
    /** 追加情報 */
    private String jpnPbankAdditionalInfo;
    /** 口座名義人 */
    private String overseaAccountHNm;
    /** 口座名義人登録住所 */
    private String overseaAccountHRAddress1;
    /** 口座名義人登録住所上記住所欄が一杯のとき */
    private String overseaAccountHRAddress2;
    /** 口座番号/IBAN(ヨーロッパ */
    private String overseaAccountNumberIban;
    /** ABAナンバー/SWIFTコード/BIC Code */
    private String overseaAbanoSwiftcdBiccd;
    /** 等 */
    private String overseaEtc;
    /** 銀行名 */
    private String overseaBankNm;
    /** 支店名 */
    private String overseaBranchNm;
    /** 支店住所 */
    private String overseaBranchAddress1;
    /** 支店住所上記住所欄が一杯のとき */
    private String overseaBranchAddress2;
    /** 支店が所在する国名 */
    private String overseaCountryBranchExists;
    /** その他の送金手数料 */
    private String otherRemittanceFee;
    /** 追加情報 */
    private String overseaAdditionalInfo;
    /** PayPalアドレス */
    private String overseaPlPaypalAddress;
    /** 追加情報 */
    private String overseaPlAdditionalInfo;
    /** コース検索結果一覧 */
    private List<TeacherCourseDto> teacherCourseListDto;
    /** セールスポイント(スクール記入) */
    private String sellingPoint;
    /** 受講生へのアピールポイント */
    private String selfRecommendation;
    /** 講師画像：ファイル名(表示) */
    private String imgPhotoNm;
    /** 講師画像：ファイル名 */
    private File imgPhoto;
    /** スクール側から講師への評価へのコメント */
    private String evaluationFromSchoolCmt1;
    /** 最新の受講生から講師へのコメント */
    private String latestEvaluationFromStudentCmt;
    /** レコードバージョン番号 */
    private int teacherMstRecordVerNo;
    /** レコードバージョン番号 */
    private int userMstRecordVerNo;

    /**
     * @return userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId セットする userId
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return checkOk
     */
    public static int getCheckOk() {
        return CHECK_OK;
    }

    /**
     * @return familyJnm
     */
    public String getFamilyJnm() {
        return familyJnm;
    }

    /**
     * @param familyJnm セットする familyJnm
     */
    public void setFamilyJnm(String familyJnm) {
        this.familyJnm = familyJnm;
    }

    /**
     * @return firstJnm
     */
    public String getFirstJnm() {
        return firstJnm;
    }

    /**
     * @param firstJnm セットする firstJnm
     */
    public void setFirstJnm(String firstJnm) {
        this.firstJnm = firstJnm;
    }

    /**
     * @return familyKnm
     */
    public String getFamilyKnm() {
        return familyKnm;
    }

    /**
     * @param familyKnm セットする familyKnm
     */
    public void setFamilyKnm(String familyKnm) {
        this.familyKnm = familyKnm;
    }

    /**
     * @return firstKnm
     */
    public String getFirstKnm() {
        return firstKnm;
    }

    /**
     * @param firstKnm セットする firstKnm
     */
    public void setFirstKnm(String firstKnm) {
        this.firstKnm = firstKnm;
    }

    /**
     * @return familyRomaji
     */
    public String getFamilyRomaji() {
        return familyRomaji;
    }

    /**
     * @param familyRomaji セットする familyRomaji
     */
    public void setFamilyRomaji(String familyRomaji) {
        this.familyRomaji = familyRomaji;
    }

    /**
     * @return firstRomaji
     */
    public String getFirstRomaji() {
        return firstRomaji;
    }

    /**
     * @param firstRomaji セットする firstRomaji
     */
    public void setFirstRomaji(String firstRomaji) {
        this.firstRomaji = firstRomaji;
    }

    /**
     * @return nickAnm
     */
    public String getNickAnm() {
        return nickAnm;
    }

    /**
     * @param nickAnm セットする nickAnm
     */
    public void setNickAnm(String nickAnm) {
        this.nickAnm = nickAnm;
    }

    /**
     * @return tel1
     */
    public String getTel1() {
        return tel1;
    }

    /**
     * @param tel1 セットする tel1
     */
    public void setTel1(String tel1) {
        this.tel1 = tel1;
    }

    /**
     * @return tel2
     */
    public String getTel2() {
        return tel2;
    }

    /**
     * @param tel2 セットする tel2
     */
    public void setTel2(String tel2) {
        this.tel2 = tel2;
    }

    /**
     * @return birthYyyyMmDd
     */
    public String getBirthYyyyMmDd() {
        return birthYyyyMmDd;
    }

    /**
     * @param birthYyyyMmDd セットする birthYyyyMmDd
     */
    public void setBirthYyyyMmDd(String birthYyyyMmDd) {
        this.birthYyyyMmDd = birthYyyyMmDd;
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
     * @return gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender セットする gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * @return nationality
     */
    public String getNationality() {
        return nationality;
    }

    /**
     * @param nationality セットする nationality
     */
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    /**
     * @return nativeLang
     */
    public String getNativeLang() {
        return nativeLang;
    }

    /**
     * @param nativeLang セットする nativeLang
     */
    public void setNativeLang(String nativeLang) {
        this.nativeLang = nativeLang;
    }

    /**
     * @return mailAddress1
     */
    public String getMailAddress1() {
        return mailAddress1;
    }

    /**
     * @param mailAddress1 セットする mailAddress1
     */
    public void setMailAddress1(String mailAddress1) {
        this.mailAddress1 = mailAddress1;
    }

    /**
     * @return mailAddress2
     */
    public String getMailAddress2() {
        return mailAddress2;
    }

    /**
     * @param mailAddress2 セットする mailAddress2
     */
    public void setMailAddress2(String mailAddress2) {
        this.mailAddress2 = mailAddress2;
    }

    /**
     * @return mailAddress3
     */
    public String getMailAddress3() {
        return mailAddress3;
    }

    /**
     * @param mailAddress3 セットする mailAddress3
     */
    public void setMailAddress3(String mailAddress3) {
        this.mailAddress3 = mailAddress3;
    }

    /**
     * @return country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country セットする country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * @return areaNo
     */
    public String getAreaNo() {
        return areaNo;
    }

    /**
     * @param areaNo セットする areaNo
     */
    public void setAreaNo(String areaNo) {
        this.areaNo = areaNo;
    }

    /**
     * @return cityTown
     */
    public String getCityTown() {
        return cityTown;
    }

    /**
     * @param cityTown セットする cityTown
     */
    public void setCityTown(String cityTown) {
        this.cityTown = cityTown;
    }

    /**
     * @return teacherRateMstDto
     */
    public List<TeacherRateMstDto> getTeacherRateMstDto() {
        return teacherRateMstDto;
    }

    /**
     * @param teacherRateMstDto セットする teacherRateMstDto
     */
    public void setTeacherRateMstDto(List<TeacherRateMstDto> teacherRateMstDto) {
        this.teacherRateMstDto = teacherRateMstDto;
    }

    /**
     * @return contractDt
     */
    public String getContractDt() {
        return contractDt;
    }

    /**
     * @param contractDt セットする contractDt
     */
    public void setContractDt(String contractDt) {
        this.contractDt = contractDt;
    }

    /**
     * @return contractStartDt
     */
    public String getContractStartDt() {
        return contractStartDt;
    }

    /**
     * @param contractStartDt セットする contractStartDt
     */
    public void setContractStartDt(String contractStartDt) {
        this.contractStartDt = contractStartDt;
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
     * @return bankJpnBankNm
     */
    public String getBankJpnBankNm() {
        return bankJpnBankNm;
    }

    /**
     * @param bankJpnBankNm セットする bankJpnBankNm
     */
    public void setBankJpnBankNm(String bankJpnBankNm) {
        this.bankJpnBankNm = bankJpnBankNm;
    }

    /**
     * @return bankJpnBranchNm
     */
    public String getBankJpnBranchNm() {
        return bankJpnBranchNm;
    }

    /**
     * @param bankJpnBranchNm セットする bankJpnBranchNm
     */
    public void setBankJpnBranchNm(String bankJpnBranchNm) {
        this.bankJpnBranchNm = bankJpnBranchNm;
    }

    /**
     * @return bankJpnTypeOfAccount
     */
    public String getBankJpnTypeOfAccount() {
        return bankJpnTypeOfAccount;
    }

    /**
     * @param bankJpnTypeOfAccount セットする bankJpnTypeOfAccount
     */
    public void setBankJpnTypeOfAccount(String bankJpnTypeOfAccount) {
        this.bankJpnTypeOfAccount = bankJpnTypeOfAccount;
    }

    /**
     * @return bankJpnAccountHoldersKnm
     */
    public String getBankJpnAccountHoldersKnm() {
        return bankJpnAccountHoldersKnm;
    }

    /**
     * @param bankJpnAccountHoldersKnm セットする bankJpnAccountHoldersKnm
     */
    public void setBankJpnAccountHoldersKnm(String bankJpnAccountHoldersKnm) {
        this.bankJpnAccountHoldersKnm = bankJpnAccountHoldersKnm;
    }

    /**
     * @return bankJpnAccountHoldersNm
     */
    public String getBankJpnAccountHoldersNm() {
        return bankJpnAccountHoldersNm;
    }

    /**
     * @param bankJpnAccountHoldersNm セットする bankJpnAccountHoldersNm
     */
    public void setBankJpnAccountHoldersNm(String bankJpnAccountHoldersNm) {
        this.bankJpnAccountHoldersNm = bankJpnAccountHoldersNm;
    }

    /**
     * @return bankJpnAccountNumber
     */
    public String getBankJpnAccountNumber() {
        return bankJpnAccountNumber;
    }

    /**
     * @param bankJpnAccountNumber セットする bankJpnAccountNumber
     */
    public void setBankJpnAccountNumber(String bankJpnAccountNumber) {
        this.bankJpnAccountNumber = bankJpnAccountNumber;
    }

    /**
     * @return bankJpnAdditionalInfo
     */
    public String getBankJpnAdditionalInfo() {
        return bankJpnAdditionalInfo;
    }

    /**
     * @param bankJpnAdditionalInfo セットする bankJpnAdditionalInfo
     */
    public void setBankJpnAdditionalInfo(String bankJpnAdditionalInfo) {
        this.bankJpnAdditionalInfo = bankJpnAdditionalInfo;
    }

    /**
     * @return jpnPbankBranchNm
     */
    public String getJpnPbankBranchNm() {
        return jpnPbankBranchNm;
    }

    /**
     * @param jpnPbankBranchNm セットする jpnPbankBranchNm
     */
    public void setJpnPbankBranchNm(String jpnPbankBranchNm) {
        this.jpnPbankBranchNm = jpnPbankBranchNm;
    }

    /**
     * @return jpnPbankTypeOfAccount
     */
    public String getJpnPbankTypeOfAccount() {
        return jpnPbankTypeOfAccount;
    }

    /**
     * @param jpnPbankTypeOfAccount セットする jpnPbankTypeOfAccount
     */
    public void setJpnPbankTypeOfAccount(String jpnPbankTypeOfAccount) {
        this.jpnPbankTypeOfAccount = jpnPbankTypeOfAccount;
    }

    /**
     * @return jpnPbankAccountHoldersKnm
     */
    public String getJpnPbankAccountHoldersKnm() {
        return jpnPbankAccountHoldersKnm;
    }

    /**
     * @param jpnPbankAccountHoldersKnm セットする jpnPbankAccountHoldersKnm
     */
    public void setJpnPbankAccountHoldersKnm(String jpnPbankAccountHoldersKnm) {
        this.jpnPbankAccountHoldersKnm = jpnPbankAccountHoldersKnm;
    }

    /**
     * @return jpnPbankAccountHoldersNm
     */
    public String getJpnPbankAccountHoldersNm() {
        return jpnPbankAccountHoldersNm;
    }

    /**
     * @param jpnPbankAccountHoldersNm セットする jpnPbankAccountHoldersNm
     */
    public void setJpnPbankAccountHoldersNm(String jpnPbankAccountHoldersNm) {
        this.jpnPbankAccountHoldersNm = jpnPbankAccountHoldersNm;
    }

    /**
     * @return jpnPbankAccountNumber
     */
    public String getJpnPbankAccountNumber() {
        return jpnPbankAccountNumber;
    }

    /**
     * @param jpnPbankAccountNumber セットする jpnPbankAccountNumber
     */
    public void setJpnPbankAccountNumber(String jpnPbankAccountNumber) {
        this.jpnPbankAccountNumber = jpnPbankAccountNumber;
    }

    /**
     * @return jpnPbankAdditionalInfo
     */
    public String getJpnPbankAdditionalInfo() {
        return jpnPbankAdditionalInfo;
    }

    /**
     * @param jpnPbankAdditionalInfo セットする jpnPbankAdditionalInfo
     */
    public void setJpnPbankAdditionalInfo(String jpnPbankAdditionalInfo) {
        this.jpnPbankAdditionalInfo = jpnPbankAdditionalInfo;
    }

    /**
     * @return overseaAccountHNm
     */
    public String getOverseaAccountHNm() {
        return overseaAccountHNm;
    }

    /**
     * @param overseaAccountHNm セットする overseaAccountHNm
     */
    public void setOverseaAccountHNm(String overseaAccountHNm) {
        this.overseaAccountHNm = overseaAccountHNm;
    }

    /**
     * @return overseaAccountHRAddress1
     */
    public String getOverseaAccountHRAddress1() {
        return overseaAccountHRAddress1;
    }

    /**
     * @param overseaAccountHRAddress1 セットする overseaAccountHRAddress1
     */
    public void setOverseaAccountHRAddress1(String overseaAccountHRAddress1) {
        this.overseaAccountHRAddress1 = overseaAccountHRAddress1;
    }

    /**
     * @return overseaAccountHRAddress2
     */
    public String getOverseaAccountHRAddress2() {
        return overseaAccountHRAddress2;
    }

    /**
     * @param overseaAccountHRAddress2 セットする overseaAccountHRAddress2
     */
    public void setOverseaAccountHRAddress2(String overseaAccountHRAddress2) {
        this.overseaAccountHRAddress2 = overseaAccountHRAddress2;
    }

    /**
     * @return overseaAccountNumberIban
     */
    public String getOverseaAccountNumberIban() {
        return overseaAccountNumberIban;
    }

    /**
     * @param overseaAccountNumberIban セットする overseaAccountNumberIban
     */
    public void setOverseaAccountNumberIban(String overseaAccountNumberIban) {
        this.overseaAccountNumberIban = overseaAccountNumberIban;
    }

    /**
     * @return overseaAbanoSwiftcdBiccd
     */
    public String getOverseaAbanoSwiftcdBiccd() {
        return overseaAbanoSwiftcdBiccd;
    }

    /**
     * @param overseaAbanoSwiftcdBiccd セットする overseaAbanoSwiftcdBiccd
     */
    public void setOverseaAbanoSwiftcdBiccd(String overseaAbanoSwiftcdBiccd) {
        this.overseaAbanoSwiftcdBiccd = overseaAbanoSwiftcdBiccd;
    }

    /**
     * @return overseaEtc
     */
    public String getOverseaEtc() {
        return overseaEtc;
    }

    /**
     * @param overseaEtc セットする overseaEtc
     */
    public void setOverseaEtc(String overseaEtc) {
        this.overseaEtc = overseaEtc;
    }

    /**
     * @return overseaBankNm
     */
    public String getOverseaBankNm() {
        return overseaBankNm;
    }

    /**
     * @param overseaBankNm セットする overseaBankNm
     */
    public void setOverseaBankNm(String overseaBankNm) {
        this.overseaBankNm = overseaBankNm;
    }

    /**
     * @return overseaBranchNm
     */
    public String getOverseaBranchNm() {
        return overseaBranchNm;
    }

    /**
     * @param overseaBranchNm セットする overseaBranchNm
     */
    public void setOverseaBranchNm(String overseaBranchNm) {
        this.overseaBranchNm = overseaBranchNm;
    }

    /**
     * @return overseaBranchAddress1
     */
    public String getOverseaBranchAddress1() {
        return overseaBranchAddress1;
    }

    /**
     * @param overseaBranchAddress1 セットする overseaBranchAddress1
     */
    public void setOverseaBranchAddress1(String overseaBranchAddress1) {
        this.overseaBranchAddress1 = overseaBranchAddress1;
    }

    /**
     * @return overseaBranchAddress2
     */
    public String getOverseaBranchAddress2() {
        return overseaBranchAddress2;
    }

    /**
     * @param overseaBranchAddress2 セットする overseaBranchAddress2
     */
    public void setOverseaBranchAddress2(String overseaBranchAddress2) {
        this.overseaBranchAddress2 = overseaBranchAddress2;
    }

    /**
     * @return overseaCountryBranchExists
     */
    public String getOverseaCountryBranchExists() {
        return overseaCountryBranchExists;
    }

    /**
     * @param overseaCountryBranchExists セットする overseaCountryBranchExists
     */
    public void setOverseaCountryBranchExists(String overseaCountryBranchExists) {
        this.overseaCountryBranchExists = overseaCountryBranchExists;
    }

    /**
     * @return otherRemittanceFee
     */
    public String getOtherRemittanceFee() {
        return otherRemittanceFee;
    }

    /**
     * @param otherRemittanceFee セットする otherRemittanceFee
     */
    public void setOtherRemittanceFee(String otherRemittanceFee) {
        this.otherRemittanceFee = otherRemittanceFee;
    }

    /**
     * @return overseaAdditionalInfo
     */
    public String getOverseaAdditionalInfo() {
        return overseaAdditionalInfo;
    }

    /**
     * @param overseaAdditionalInfo セットする overseaAdditionalInfo
     */
    public void setOverseaAdditionalInfo(String overseaAdditionalInfo) {
        this.overseaAdditionalInfo = overseaAdditionalInfo;
    }

    /**
     * @return overseaPlPaypalAddress
     */
    public String getOverseaPlPaypalAddress() {
        return overseaPlPaypalAddress;
    }

    /**
     * @param overseaPlPaypalAddress セットする overseaPlPaypalAddress
     */
    public void setOverseaPlPaypalAddress(String overseaPlPaypalAddress) {
        this.overseaPlPaypalAddress = overseaPlPaypalAddress;
    }

    /**
     * @return overseaPlAdditionalInfo
     */
    public String getOverseaPlAdditionalInfo() {
        return overseaPlAdditionalInfo;
    }

    /**
     * @param overseaPlAdditionalInfo セットする overseaPlAdditionalInfo
     */
    public void setOverseaPlAdditionalInfo(String overseaPlAdditionalInfo) {
        this.overseaPlAdditionalInfo = overseaPlAdditionalInfo;
    }

    /**
     * @return teacherCourseListDto
     */
    public List<TeacherCourseDto> getTeacherCourseListDto() {
        return teacherCourseListDto;
    }

    /**
     * @param list セットする teacherCourseListDto
     */
    public void setTeacherCourseListDto(List<TeacherCourseDto> list) {
        this.teacherCourseListDto = list;
    }

    /**
     * @return sellingPoint
     */
    public String getSellingPoint() {
        return sellingPoint;
    }

    /**
     * @param sellingPoint セットする sellingPoint
     */
    public void setSellingPoint(String sellingPoint) {
        this.sellingPoint = sellingPoint;
    }

    /**
     * @return selfRecommendation
     */
    public String getSelfRecommendation() {
        return selfRecommendation;
    }

    /**
     * @param selfRecommendation セットする selfRecommendation
     */
    public void setSelfRecommendation(String selfRecommendation) {
        this.selfRecommendation = selfRecommendation;
    }

    /**
     * @return imgPhotoNm
     */
    public String getImgPhotoNm() {
        return imgPhotoNm;
    }

    /**
     * @param imgPhotoNm セットする imgPhotoNm
     */
    public void setImgPhotoNm(String imgPhotoNm) {
        this.imgPhotoNm = imgPhotoNm;
    }

    /**
     * @return imgPhoto
     */
    public File getImgPhoto() {
        return imgPhoto;
    }

    /**
     * @param imgPhoto セットする imgPhoto
     */
    public void setImgPhoto(File imgPhoto) {
        this.imgPhoto = imgPhoto;
    }

    /**
     * @return evaluationFromSchoolCmt1
     */
    public String getEvaluationFromSchoolCmt1() {
        return evaluationFromSchoolCmt1;
    }

    /**
     * @param evaluationFromSchoolCmt1 セットする evaluationFromSchoolCmt1
     */
    public void setEvaluationFromSchoolCmt1(String evaluationFromSchoolCmt1) {
        this.evaluationFromSchoolCmt1 = evaluationFromSchoolCmt1;
    }

    /**
     * @return latestEvaluationFromStudentCmt
     */
    public String getLatestEvaluationFromStudentCmt() {
        return latestEvaluationFromStudentCmt;
    }

    /**
     * @param latestEvaluationFromStudentCmt セットする latestEvaluationFromStudentCmt
     */
    public void setLatestEvaluationFromStudentCmt(String latestEvaluationFromStudentCmt) {
        this.latestEvaluationFromStudentCmt = latestEvaluationFromStudentCmt;
    }

    /**
     * @return teacherMstRecordVerNo
     */
    public int getTeacherMstRecordVerNo() {
        return teacherMstRecordVerNo;
    }

    /**
     * @param teacherMstRecordVerNo セットする teacherMstRecordVerNo
     */
    public void setTeacherMstRecordVerNo(int teacherMstRecordVerNo) {
        this.teacherMstRecordVerNo = teacherMstRecordVerNo;
    }

    /**
     * @return userMstRecordVerNo
     */
    public int getUserMstRecordVerNo() {
        return userMstRecordVerNo;
    }

    /**
     * @param userMstRecordVerNo セットする userMstRecordVerNo
     */
    public void setUserMstRecordVerNo(int userMstRecordVerNo) {
        this.userMstRecordVerNo = userMstRecordVerNo;
    }

    /**
     * @return serialversionuid
     */
    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}