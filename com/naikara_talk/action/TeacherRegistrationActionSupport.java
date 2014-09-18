package com.naikara_talk.action;

import java.io.File;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.model.TeacherRegistrationModel;
import com.naikara_talk.service.TeacherRegistrationLoadService;
import com.naikara_talk.sessiondata.SessionUser;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>講師初期登録ページ。<br>
 * <b>クラス名称       :</b>講師初期登録ページ初期処理Actionクラス。<br>
 * <b>クラス概要       :</b>講師情報の登録・修正処理を行うことができる<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/01 TECS 新規作成
 */
public abstract class TeacherRegistrationActionSupport extends NaikaraActionSupport {
    private static final long serialVersionUID = 1L;

    // 画面表示のタイトル
    protected String title = "Teacher Registration";

    // Help画面名
    protected String helpPageId = "HelpTeacherRegistration.html";

    /**
     * 画面の初期値を保持<br>
     * <br>
     *
     * @param なし<br>
     * @return なし <br>
     * @exception なし
     */
    public void validate() {
        try {
            // 滞在国と時差地域No.の初期取得。
            initSelect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.validate();
    }

    /**
     * 滞在国と時差地域No.等を取得する。<br>
     * <br>
     * @param なし
     * @return なし
     * @throws Exception
     */
    public void initSelect() throws Exception {

        // 前画面の情報
        this.model.setUserId(((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString())).getUserId());
        TeacherRegistrationLoadService service = new TeacherRegistrationLoadService();
        // 滞在国を取得する
        this.countryCd_sellE = service.selectCodeMst(NaikaraTalkConstants.CODE_CATEGORY_COUNTRY_T);
        this.countryCd_sellJ = service.selectCodeMst(NaikaraTalkConstants.CODE_CATEGORY_COUNTRY);
        Iterator<String> iterCountry = countryCd_sellE.keySet().iterator();
        while (iterCountry.hasNext()) {
            Object key = iterCountry.next();
            this.countryCd_sell.put(key.toString(),
                    NaikaraStringUtil.unionString2(countryCd_sellE.get(key), countryCd_sellJ.get(key)));
        }

        // 時差地域No.を取得する
        this.countryCd_sellE = service.selectCodeMst(NaikaraTalkConstants.CODE_CATEGORY_AREA_NO_T);
        this.areaNoCd_sellJ = service.selectCodeMst(NaikaraTalkConstants.CODE_CATEGORY_AREA_NO);
        Iterator<String> iterArea = countryCd_sellE.keySet().iterator();
        while (iterArea.hasNext()) {
            Object key = iterArea.next();
            this.areaNoCd_sell.put(key.toString(),
                    NaikaraStringUtil.unionString2(countryCd_sellE.get(key), areaNoCd_sellJ.get(key)));
        }
        // 講師単位のコースリストの取得
        this.model.setTeacherCourseListDto(service.selectTeacherCourse(this.model));
        // 現行支払設定期間等の取得
        this.model.setTeacherRateMstDto(service.selectTeacherRate(this.model));
    }

    /**
     * 画面のパラメータをモデルにセット。<br>
     * <br>
     * @param なし
     * @return なし
     */
    public void setupModel() {
        /** 利用者ID */
        this.model.setUserId(this.userId);
        /** 電話番号（自宅/携帯） */
        this.model.setTel1(this.tel1_txt);
        /** 電話番号（緊急連絡先） */
        this.model.setTel2(this.tel2_txt);
        /** 郵便番号 */
        this.model.setZipCd(this.zipCd_txt);
        /** 住所 */
        this.model.setAddressCity(this.addressCity_txt);
        /** 住所(ﾋﾞﾙ、ﾏﾝｼｮﾝ名 等) */
        this.model.setAddressOthers(this.addressOthers_txt);
        /** メールアドレス１ */
        this.model.setMailAddress1(this.mailAddress1_txt);
        /** メールアドレス２ */
        this.model.setMailAddress2(this.mailAddress2_txt);
        /** メールアドレス３ */
        this.model.setMailAddress3(this.mailAddress3_txt);
        /** 滞在国 */
        this.model.setCountry(this.countryCd_sel);
        /** 時差地域No */
        this.model.setAreaNo(this.areaNoCd_sel);
        /** 勤務都市名 */
        this.model.setCityTown(this.cityTown_txt);
        /** 受講生へのアピールポイント */
        this.model.setSelfRecommendation(this.selfRecommendation_txa);
        /** 講師画像名 */
        this.model.setImgPhotoNm(this.imgPhoto_filFileName);
        /** 講師画像：ファイル名 */
        this.model.setImgPhoto(this.imgPhoto_fil);

        // レコードバージョン番号
        String userMstRecordVerNo = StringUtils.isEmpty(this.userMstRecordVerNo) ? "0" : this.userMstRecordVerNo;
        // レコードバージョン番号
        String teacherMstRecordVerNo = StringUtils.isEmpty(this.teacherMstRecordVerNo) ? "0"
                : this.teacherMstRecordVerNo;
        this.model.setUserMstRecordVerNo(Integer.parseInt(userMstRecordVerNo));
        this.model.setTeacherMstRecordVerNo(Integer.parseInt(teacherMstRecordVerNo));
    }

    /** メッセージ */
    protected String message;

    /** 検索結果 */
    protected TeacherRegistrationModel model = new TeacherRegistrationModel();

    /** 利用者ID */
    protected String userId;
    /** 名前（苗字） */
    protected String familyJnm;
    /** 名前（名前） */
    protected String firstJnm;
    /** フリガナ(姓) */
    protected String familyKnm;
    /** フリガナ(名） */
    protected String firstKnm;
    /** ローマ字(姓) */
    protected String familyRomaji;
    /** ローマ字(名) */
    protected String firstRomaji;
    /** ニックネーム  */
    protected String nickAnm;
    /** 電話番号（自宅/携帯） */
    protected String tel1_txt;
    /** 電話番号（緊急連絡先） */
    protected String tel2_txt;
    /** 生年月日 */
    protected String birthYyyyMmDd;
    /** 郵便番号 */
    protected String zipCd_txt;
    /** 住所 */
    protected String addressCity_txt;
    /** 住所(ﾋﾞﾙ、ﾏﾝｼｮﾝ名 等) */
    protected String addressOthers_txt;
    /** 性別 */
    protected String gender;
    /** 国籍 */
    protected String nationality;
    /** 母国語 */
    protected String nativeLang;
    /** メールアドレス１ */
    protected String mailAddress1_txt;
    /** メールアドレス２ */
    protected String mailAddress2_txt;
    /** メールアドレス３ */
    protected String mailAddress3_txt;
    /** 滞在国 */
    protected String countryCd_sel;
    protected Map<String, String> countryCd_sell = new LinkedHashMap<String, String>();
    protected Map<String, String> countryCd_sellE = new LinkedHashMap<String, String>();
    protected Map<String, String> countryCd_sellJ = new LinkedHashMap<String, String>();
    /** 時差地域No. */
    protected String areaNoCd_sel;
    protected Map<String, String> areaNoCd_sell = new LinkedHashMap<String, String>();
    protected Map<String, String> areaNoCd_sellE = new LinkedHashMap<String, String>();
    protected Map<String, String> areaNoCd_sellJ = new LinkedHashMap<String, String>();
    /** 勤務都市名 */
    protected String cityTown_txt;
    /** 契約日 */
    protected String contractDt;
    /** 契約期間：開始日 */
    protected String contractStartDt;
    /** 契約期間：終了日 */
    protected String contractEndDt;
    /** 銀行名 */
    protected String bankJpnBankNm;
    /** 支店名 */
    protected String bankJpnBranchNm;
    /** 支店名 */
    protected String bankJpnTypeOfAccount;
    /** 支店名 */
    protected String bankJpnAccountHoldersKnm;
    /** 支店名 */
    protected String bankJpnAccountHoldersNm;
    /** 支店名 */
    protected String bankJpnAccountNumber;
    /** 支店名 */
    protected String bankJpnAdditionalInfo;
    /** 店番 */
    protected String jpnPbankBranchNm;
    /** 預金種別 */
    protected String jpnPbankTypeOfAccount;
    /** 口座名義人(フリガナ) */
    protected String jpnPbankAccountHoldersKnm;
    /** 口座名義人 */
    protected String jpnPbankAccountHoldersNm;
    /** 口座番号 */
    protected String jpnPbankAccountNumber;
    /** 追加情報 */
    protected String jpnPbankAdditionalInfo;
    /** 口座名義人 */
    protected String overseaAccountHNm;
    /** 口座名義人登録住所 */
    protected String overseaAccountHRAddress1;
    /** 口座名義人登録住所上記住所欄が一杯のとき */
    protected String overseaAccountHRAddress2;
    /** 口座番号/IBAN(ヨーロッパ */
    protected String overseaAccountNumberIban;
    /** ABAナンバー/SWIFTコード/BIC Code */
    protected String overseaAbanoSwiftcdBiccd;
    /** 等 */
    protected String overseaEtc;
    /** 銀行名 */
    protected String overseaBankNm;
    /** 支店名 */
    protected String overseaBranchNm;
    /** 支店住所 */
    protected String overseaBranchAddress1;
    /** 支店住所上記住所欄が一杯のとき */
    protected String overseaBranchAddress2;
    /** 支店が所在する国名 */
    protected String overseaCountryBranchExists;
    /** その他の送金手数料 */
    protected String otherRemittanceFee;
    /** 追加情報 */
    protected String overseaAdditionalInfo;
    /** PayPalアドレス */
    protected String overseaPlPaypalAddress;
    /** 追加情報 */
    protected String overseaPlAdditionalInfo;
    /** コースコード */
    protected String courseCd;
    /** コース名 */
    protected String courseNm;
    /** セールスポイント(スクール記入) */
    protected String sellingPoint;
    /** 受講生へのアピールポイント */
    protected String selfRecommendation_txa;
    /** 講師画像：ファイル名(表示) */
    protected String imgPhoto_filFileName;
    /** 講師画像：ファイル名 */
    protected File imgPhoto_fil;
    /** スクール側から講師への評価へのコメント */
    protected String evaluationFromSchoolCmt1;
    /** 最新の受講生から講師へのコメント */
    protected String latestEvaluationFromStudentCmt;
    /** レコードバージョン番号 */
    protected String teacherMstRecordVerNo;
    /** レコードバージョン番号 */
    protected String userMstRecordVerNo;

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
     * @return countryCd_sel
     */
    public String getCountryCd_sel() {
        return countryCd_sel;
    }

    /**
     * @param countryCd_sel セットする countryCd_sel
     */
    public void setCountryCd_sel(String countryCd_sel) {
        this.countryCd_sel = countryCd_sel;
    }

    /**
     * @return countryCd_sell
     */
    public Map<String, String> getCountryCd_sell() {
        return countryCd_sell;
    }

    /**
     * @param countryCd_sell セットする countryCd_sell
     */
    public void setCountryCd_sell(Map<String, String> countryCd_sell) {
        this.countryCd_sell = countryCd_sell;
    }

    /**
     * @return areaNoCd_sel
     */
    public String getAreaNoCd_sel() {
        return areaNoCd_sel;
    }

    /**
     * @param areaNoCd_sel セットする areaNoCd_sel
     */
    public void setAreaNoCd_sel(String areaNoCd_sel) {
        this.areaNoCd_sel = areaNoCd_sel;
    }

    /**
     * @return areaNoCd_sell
     */
    public Map<String, String> getAreaNoCd_sell() {
        return areaNoCd_sell;
    }

    /**
     * @param areaNoCd_sell セットする areaNoCd_sell
     */
    public void setAreaNoCd_sell(Map<String, String> areaNoCd_sell) {
        this.areaNoCd_sell = areaNoCd_sell;
    }

    /**
     * @return model
     */
    public TeacherRegistrationModel getModel() {
        return model;
    }

    /**
     * @param model セットする model
     */
    public void setModel(TeacherRegistrationModel model) {
        this.model = model;
    }

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
     * @return cityTown_txt
     */
    public String getCityTown_txt() {
        return cityTown_txt;
    }

    /**
     * @param cityTown_txt セットする cityTown_txt
     */
    public void setCityTown_txt(String cityTown_txt) {
        this.cityTown_txt = cityTown_txt;
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
     * @return courseCd
     */
    public String getCourseCd() {
        return courseCd;
    }

    /**
     * @param courseCd セットする courseCd
     */
    public void setCourseCd(String courseCd) {
        this.courseCd = courseCd;
    }

    /**
     * @return courseNm
     */
    public String getCourseNm() {
        return courseNm;
    }

    /**
     * @param courseNm セットする courseNm
     */
    public void setCourseNm(String courseNm) {
        this.courseNm = courseNm;
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
     * @return selfRecommendation_txa
     */
    public String getSelfRecommendation_txa() {
        return selfRecommendation_txa;
    }

    /**
     * @param selfRecommendation_txa セットする selfRecommendation_txa
     */
    public void setSelfRecommendation_txa(String selfRecommendation_txa) {
        this.selfRecommendation_txa = selfRecommendation_txa;
    }

    /**
     * @return imgPhoto_filFileName
     */
    public String getImgPhoto_filFileName() {
        return imgPhoto_filFileName;
    }

    /**
     * @param imgPhoto_filFileName セットする imgPhoto_filFileName
     */
    public void setImgPhoto_filFileName(String imgPhoto_filFileName) {
        this.imgPhoto_filFileName = imgPhoto_filFileName;
    }

    /**
     * @return imgPhoto_fil
     */
    public File getImgPhoto_fil() {
        return imgPhoto_fil;
    }

    /**
     * @param imgPhoto_fil セットする imgPhoto_fil
     */
    public void setImgPhoto_fil(File imgPhoto_fil) {
        this.imgPhoto_fil = imgPhoto_fil;
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
    public String getTeacherMstRecordVerNo() {
        return teacherMstRecordVerNo;
    }

    /**
     * @param teacherMstRecordVerNo セットする teacherMstRecordVerNo
     */
    public void setTeacherMstRecordVerNo(String teacherMstRecordVerNo) {
        this.teacherMstRecordVerNo = teacherMstRecordVerNo;
    }

    /**
     * @return userMstRecordVerNo
     */
    public String getUserMstRecordVerNo() {
        return userMstRecordVerNo;
    }

    /**
     * @param userMstRecordVerNo セットする userMstRecordVerNo
     */
    public void setUserMstRecordVerNo(String userMstRecordVerNo) {
        this.userMstRecordVerNo = userMstRecordVerNo;
    }

    /**
     * @return serialversionuid
     */
    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}
