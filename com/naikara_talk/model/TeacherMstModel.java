package com.naikara_talk.model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.naikara_talk.dto.TeacherCourseDto;
import com.naikara_talk.dto.TeacherRateMstDto;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>マスタ保守<br>
 * <b>クラス名称       :</b>ポイント管理マスタメンテナンス(単票)<br>
 * <b>クラス概要       :</b>ポイント管理マスタメンテナンス(単票)Model<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/01 TECS 新規作成
 */
public class TeacherMstModel implements Model {

    private static final long serialVersionUID = 1L;

    /** 正常 */
    public static final int PROCESS_NORMAL = 0;

    /** 利用者ID */
    private String userId;

    /** 名前(姓) */
    private String familyJnm;

    /** 名前(名) */
    private String firstJnm;

    /** フリガナ(姓) */
    private String familyKnm;

    /** フリガナ(名) */
    private String firstKnm;

    /** ローマ字(姓) */
    private String familyRomaji;

    /** ローマ字(名) */
    private String firstRomaji;

    /** ニックネーム */
    private String nickAnm;

    /** 電話番号1 */
    private String tel1;

    /** 電話番号2 */
    private String tel2;

    /** 生年月日：年 */
    private String birthYyyy;

    /** 生年月日：月 */
    private String birthMm;

    /** 生年月日：日 */
    private String birthDd;

    /** 郵便番号 */
    private String zipCd;

    /** 住所(市区町村) */
    private String addressCity;

    /** 住所(ﾋﾞﾙ、ﾏﾝｼｮﾝ名 等) */
    private String addressOthers;

    /** 性別区分分 */
    private String genderKbn;

    /** 国籍コード */
    private String nationalityCd;

    /** 母国語コード */
    private String nativeLangCd;

    /** メールアドレス1 */
    private String mailAddress1;

    /** メールアドレス2 */
    private String mailAddress2;

    /** メールアドレス3 */
    private String mailAddress3;

    /** 滞在国コード */
    private String countryCd;

    /** 時差地域NOコード */
    private String areaNoCd;

    /** 勤務拠点 */
    private String cityTown;

    /** 契約日 */
    private String contractDt;

    /** 契約開始日 */
    private String contractStartDt;

    /** 契約終了日 */
    private String contractEndDt;

    /** 講師支払比率 */
    private List<TeacherRateMstDto> teacherRateMstDtoList;

    /** 削除の講師支払比率 */
    private List<TeacherRateMstDto> delTeacherRateMstDtoList;

    /** 国内銀行向け送金の場合：銀行名 */
    private String bankJpnBankNm;

    /** 国内銀行向け送金の場合：支店名 */
    private String bankJpnBranchNm;

    /** 国内銀行向け送金の場合：預金種別 */
    private String bankJpnTypeOfAccount;

    /** 国内銀行向け送金の場合：口座名義人（フリガナ） */
    private String bankJpnAccountHoldersKnm;

    /** 国内銀行向け送金の場合：口座名義人 */
    private String bankJpnAccountHoldersNm;

    /** 国内銀行向け送金の場合：口座番号 */
    private String bankJpnAccountNumber;

    /** 国内銀行向け送金の場合：追加情報 */
    private String bankJpnAdditionalInfo;

    /** 国内ゆうちょ銀行向け送金の場合:店番 */
    private String jpnPbankBranchNm;

    /** 国内ゆうちょ銀行向け送金の場合:預金種別 */
    private String jpnPbankTypeOfAccount;

    /** 国内ゆうちょ銀行向け送金の場合:口座名義人（フリガナ） */
    private String jpnPbankAccountHoldersKnm;

    /** 国内ゆうちょ銀行向け送金の場合:口座名義人 */
    private String jpnPbankAccountHoldersNm;

    /** 国内ゆうちょ銀行向け送金の場合：口座番号 */
    private String jpnPbankAccountNumber;

    /** 国内ゆうちょ銀行向け送金の場合：追加情報 */
    private String jpnPbankAdditionalInfo;

    /** 海外送金の場合:口座名義人 */
    private String overseaAccountHNm;

    /** 海外送金の場合:口座名義人登録住所 */
    private String overseaAccountHRAddress1;

    /** 海外送金の場合:口座名義人登録住所（上記住所欄が一杯のとき） */
    private String overseaAccountHRAddress2;

    /** 海外送金の場合:口座番号/IBAN（ヨーロッパ） */
    private String overseaAccountNumberIban;

    /** 海外送金の場合:ABAナンバー/SWIFTコード/BIC　Code　等 */
    private String overseaAbanoSwiftcdBiccd;

    /** 海外送金の場合:等 */
    private String overseaEtc;

    /** 海外送金の場合:銀行名 */
    private String overseaBankNm;

    /** 海外送金の場合:支店名 */
    private String overseaBranchNm;

    /** 海外送金の場合:支店住所 */
    private String overseaBranchAddress1;

    /** 海外送金の場合:支店住所（上記住所欄が一杯のとき） */
    private String overseaBranchAddress2;

    /** 海外送金の場合:支店が所在する国名 */
    private String overseaCountryBranchExists;

    /** 外国送金関係銀行手数料区分 */
    private String otherRemittanceFeeKbn;

    /** 海外送金の場合:追加情報 */
    private String overseaAdditionalInfo;

    /** 海外ペイパル送金の場合:PayPalアドレス */
    private String overseaPlPaypalAddress;

    /** 海外ペイパル送金の場合:追加情報 */
    private String overseaPlAdditionalInfo;

    /** セールスポイント(スクール記入) */
    private String sellingPoint;

    /** 受講生へのアピールポイント */
    private String selfRecommendation;

    /** 講師画像：ファイル名(登録用) */
    private String teacherFileName;

    /** 講師画像名 */
    private String imgPhotoNm;

    /** 講師画像 */
    private File imgPhoto;

    /** 講師画像削除 */
    private String imgPhotoChkn;

    /** スクール側からのコメント(講師公開) */
    private String evaluationFromSchoolCmt1;

    /** スクール側からのコメント(講師非公開) */
    private String evaluationFromSchoolCmt2;

    /** 最新の受講生から講師へのコメント */
    private String latestEvaluationFromStudentCmt;

    /** 備考(講師は見えません) */
    private String remarkT;

    /** 利用開始日 */
    private String useStartDt;

    /** 利用終了日 */
    private String useEndDt;

    /** 講師利用者ID */
    private String userIdT;

    /** 利用者レコードバージョン番号 */
    private int recordVerNoU;

    /** 講師レコードバージョン番号 */
    private int recordVerNoT;

    /** 講師別コースマスタ(+コースマスタ) */
    private List<TeacherCourseDto> teacherCourseDtoList;

    /** 処理区分(前画面情報) */
    private String processKbn_rdl;

    /** 画面表示処理区分 */
    private String processKbn_txt;

    /** 処理判定 */
    private String processDifference;

    /** チェック メッセージ */
    private String checkMessage;

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
     * @return birthYyyy
     */
    public String getBirthYyyy() {
        return birthYyyy;
    }

    /**
     * @param birthYyyy セットする birthYyyy
     */
    public void setBirthYyyy(String birthYyyy) {
        this.birthYyyy = birthYyyy;
    }

    /**
     * @return birthMm
     */
    public String getBirthMm() {
        return birthMm;
    }

    /**
     * @param birthMm セットする birthMm
     */
    public void setBirthMm(String birthMm) {
        this.birthMm = birthMm;
    }

    /**
     * @return birthDd
     */
    public String getBirthDd() {
        return birthDd;
    }

    /**
     * @param birthDd セットする birthDd
     */
    public void setBirthDd(String birthDd) {
        this.birthDd = birthDd;
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
     * @return genderKbn
     */
    public String getGenderKbn() {
        return genderKbn;
    }

    /**
     * @param genderKbn セットする genderKbn
     */
    public void setGenderKbn(String genderKbn) {
        this.genderKbn = genderKbn;
    }

    /**
     * @return nationalityCd
     */
    public String getNationalityCd() {
        return nationalityCd;
    }

    /**
     * @param nationalityCd セットする nationalityCd
     */
    public void setNationalityCd(String nationalityCd) {
        this.nationalityCd = nationalityCd;
    }

    /**
     * @return nativeLangCd
     */
    public String getNativeLangCd() {
        return nativeLangCd;
    }

    /**
     * @param nativeLangCd セットする nativeLangCd
     */
    public void setNativeLangCd(String nativeLangCd) {
        this.nativeLangCd = nativeLangCd;
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
     * @return countryCd
     */
    public String getCountryCd() {
        return countryCd;
    }

    /**
     * @param countryCd セットする countryCd
     */
    public void setCountryCd(String countryCd) {
        this.countryCd = countryCd;
    }

    /**
     * @return areaNoCd
     */
    public String getAreaNoCd() {
        return areaNoCd;
    }

    /**
     * @param areaNoCd セットする areaNoCd
     */
    public void setAreaNoCd(String areaNoCd) {
        this.areaNoCd = areaNoCd;
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
     * @return teacherRateMstDtoList
     */
    public List<TeacherRateMstDto> getTeacherRateMstDtoList() {
        if (this.teacherRateMstDtoList == null) {
            this.teacherRateMstDtoList = new ArrayList<TeacherRateMstDto>();
        }
        return teacherRateMstDtoList;
    }

    /**
     * @param teacherRateMstDtoList セットする teacherRateMstDtoList
     */
    public void setTeacherRateMstDtoList(List<TeacherRateMstDto> teacherRateMstDtoList) {
        this.teacherRateMstDtoList = teacherRateMstDtoList;
    }

    /**
     * @return delTeacherRateMstDtoList
     */
    public List<TeacherRateMstDto> getDelTeacherRateMstDtoList() {
        if (this.delTeacherRateMstDtoList == null) {
            this.delTeacherRateMstDtoList = new ArrayList<TeacherRateMstDto>();
        }
        return delTeacherRateMstDtoList;
    }

    /**
     * @param delTeacherRateMstDtoList セットする delTeacherRateMstDtoList
     */
    public void setDelTeacherRateMstDtoList(List<TeacherRateMstDto> delTeacherRateMstDtoList) {
        this.delTeacherRateMstDtoList = delTeacherRateMstDtoList;
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
     * @return otherRemittanceFeeKbn
     */
    public String getOtherRemittanceFeeKbn() {
        return otherRemittanceFeeKbn;
    }

    /**
     * @param otherRemittanceFeeKbn セットする otherRemittanceFeeKbn
     */
    public void setOtherRemittanceFeeKbn(String otherRemittanceFeeKbn) {
        this.otherRemittanceFeeKbn = otherRemittanceFeeKbn;
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
     * @return teacherFileName
     */
    public String getTeacherFileName() {
        return teacherFileName;
    }

    /**
     * @param teacherFileName セットする teacherFileName
     */
    public void setTeacherFileName(String teacherFileName) {
        this.teacherFileName = teacherFileName;
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
     * @return imgPhotoChkn
     */
    public String getImgPhotoChkn() {
        return imgPhotoChkn;
    }

    /**
     * @param imgPhotoChkn セットする imgPhotoChkn
     */
    public void setImgPhotoChkn(String imgPhotoChkn) {
        this.imgPhotoChkn = imgPhotoChkn;
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
     * @return evaluationFromSchoolCmt2
     */
    public String getEvaluationFromSchoolCmt2() {
        return evaluationFromSchoolCmt2;
    }

    /**
     * @param evaluationFromSchoolCmt2 セットする evaluationFromSchoolCmt2
     */
    public void setEvaluationFromSchoolCmt2(String evaluationFromSchoolCmt2) {
        this.evaluationFromSchoolCmt2 = evaluationFromSchoolCmt2;
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
     * @return remarkT
     */
    public String getRemarkT() {
        return remarkT;
    }

    /**
     * @param remarkT セットする remarkT
     */
    public void setRemarkT(String remarkT) {
        this.remarkT = remarkT;
    }

    /**
     * @return useStartDt
     */
    public String getUseStartDt() {
        return useStartDt;
    }

    /**
     * @param useStartDt セットする useStartDt
     */
    public void setUseStartDt(String useStartDt) {
        this.useStartDt = useStartDt;
    }

    /**
     * @return useEndDt
     */
    public String getUseEndDt() {
        return useEndDt;
    }

    /**
     * @param useEndDt セットする useEndDt
     */
    public void setUseEndDt(String useEndDt) {
        this.useEndDt = useEndDt;
    }

    /**
     * @return userIdT
     */
    public String getUserIdT() {
        return userIdT;
    }

    /**
     * @param userIdT セットする userIdT
     */
    public void setUserIdT(String userIdT) {
        this.userIdT = userIdT;
    }

    /**
     * @return recordVerNoU
     */
    public int getRecordVerNoU() {
        return recordVerNoU;
    }

    /**
     * @param recordVerNoU セットする recordVerNoU
     */
    public void setRecordVerNoU(int recordVerNoU) {
        this.recordVerNoU = recordVerNoU;
    }

    /**
     * @return recordVerNoT
     */
    public int getRecordVerNoT() {
        return recordVerNoT;
    }

    /**
     * @param recordVerNoT セットする recordVerNoT
     */
    public void setRecordVerNoT(int recordVerNoT) {
        this.recordVerNoT = recordVerNoT;
    }

    /**
     * @return teacherCourseDtoList
     */
    public List<TeacherCourseDto> getTeacherCourseDtoList() {
        if (null == this.teacherCourseDtoList) {
            this.teacherCourseDtoList = new ArrayList<TeacherCourseDto>();
        }
        return teacherCourseDtoList;
    }

    /**
     * @param teacherCourseDtoList セットする teacherCourseDtoList
     */
    public void setTeacherCourseDtoList(List<TeacherCourseDto> teacherCourseDtoList) {
        this.teacherCourseDtoList = teacherCourseDtoList;
    }

    /**
     * @return processKbn_rdl
     */
    public String getProcessKbn_rdl() {
        return processKbn_rdl;
    }

    /**
     * @param processKbn_rdl
     *            セットする processKbn_rdl
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
     * @param processKbn_txt
     *            セットする processKbn_txt
     */
    public void setProcessKbn_txt(String processKbn_txt) {
        this.processKbn_txt = processKbn_txt;
    }

    /**
     * @return processKbn_rdl
     */
    public String getProcessKbn_Rdl() {
        return processKbn_rdl;
    }

    /**
     * @param processKbn_rdl
     *            セットする processKbn_rdl
     */
    public void setProcessKbn_Rdl(String processKbn_rdl) {
        this.processKbn_rdl = processKbn_rdl;
    }

    /**
     * @return processKbn_txt
     */
    public String getProcessKbn_Txt() {
        return processKbn_txt;
    }

    /**
     * @param processKbn_txt
     *            セットする processKbn_txt
     */
    public void setProcessKbn_Txt(String processKbn_txt) {
        this.processKbn_txt = processKbn_txt;
    }

    /**
     * @return processDifference
     */
    public String getProcessDifference() {
        return processDifference;
    }

    /**
     * @param processDifference セットする processDifference
     */
    public void setProcessDifference(String processDifference) {
        this.processDifference = processDifference;
    }

    /**
     * @return checkMessage
     */
    public String getCheckMessage() {
        return checkMessage;
    }

    /**
     * @param checkMessage セットする checkMessage
     */
    public void setCheckMessage(String checkMessage) {
        this.checkMessage = checkMessage;
    }

}