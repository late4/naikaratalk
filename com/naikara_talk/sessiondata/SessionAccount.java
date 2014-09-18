package com.naikara_talk.sessiondata;


/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>受講者機能：アカウント取得。<br>
 * <b>クラス名称　　　:</b>アカウント取得のセッション情報クラス。<br>
 * <b>クラス概要　　　:</b>アカウント取得のスクールのメール送信・受信履歴照会に伴う対応による
 * 　　　　　　　　　　　　値の変更があったか比較するために保持する情報。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>
 * 2014/01/03 TECS 新規作成。
 */

public class SessionAccount implements SessionData {

    /** ヘッダの戻るリンク用のSessionKey */
    private static final String HEADER_RETURN_KEY = "naikara_talk.sessiondata.SessionAccount";

    private String studentId;                      // 受講者ID
    private String familyJnm;                      // 名前(姓)
    private String firstJnm;                       // 名前(名)
    private String familyKnm;                      // フリガナ(姓)
    private String firstKnm;                       // フリガナ(名)
    private String familyRomaji;                   // ローマ字(姓)
    private String firstRomaji;                    // ローマ字(名)
    private String nickNm;                         // ニックネーム
    private String password;                       // パスワード
    private String tel1;                           // 電話番号1
    private String tel2;                           // 電話番号2
    private String birthYyyy;                      // 生年月日：年
    private String birthMm;                        // 生年月日：月
    private String birthDd;                        // 生年月日：日
    private String zipCd;                          // 郵便番号
    private String addressAreaCd;                  // 住所(地域)コード
    private String addressPrefectureCd;            // 住所(都道府県)コード
    private String addressCity;                    // 住所(市区町村 等)
    private String addressOthers;                  // 住所(ﾋﾞﾙ、ﾏﾝｼｮﾝ名 等)
    private String genderKbn;                      // 性別区分
    private String mailAddress1;                   // メールアドレス1
    private String mailAddress2;                   // メールアドレス2
    private String mailAddress3;                   // メールアドレス3
    private String guardianFamilyJnm;              // 保護者：名前(姓)
    private String guardianFirstJnm;               // 保護者：名前(名）
    private String guardianFamilyKnm;              // 保護者：フリガナ(姓)
    private String guardianFirstKnm;               // 保護者：フリガナ(名）
    private String guardianFamilyRelationship;     // あなたとの続柄
    private String guardianTel1;                   // 保護者：電話番号1
    private String guardianTel2;                   // 保護者：電話番号2
    private String guardianBirthYyyy;              // 保護者：生年月日：年
    private String guardianBirthMm;                // 保護者：生年月日：月
    private String guardianBirthDd;                // 保護者：生年月日：日
    private String guardianZipCd;                  // 保護者：郵便番号
    private String guardianAddressAreaCd;          // 保護者：住所(地域)コード
    private String guardianAddressPrefectureCd;    // 保護者：住所(都道府県)コード
    private String guardianAddressCity;            // 保護者：住所(市区町村 等)
    private String guardianAddressOthers;          // 保護者：住所(ﾋﾞﾙ、ﾏﾝｼｮﾝ名 等)
    private String guardianGenderKbn;              // 保護者：性別区分
    private String guardianMailAddress1;           // 保護者：メールアドレス1
    private String guardianMailAddress2;           // 保護者：メールアドレス2
    private String guardianMailAddress3;           // 保護者：メールアドレス3
    private int recordVerNo;                       // レコードバージョン番号


    /**
     * このセッションデータのキーを返却する
     */
    public static String getKey() {
        return HEADER_RETURN_KEY;
    }

    /**
     * 受講者ID取得<br>
     * <br>
     * 受講者IDを戻り値で返却する<br>
     * <br>
     * @return studentId
     */
    public String getStudentId() {
        return studentId;
    }

    /**
     * 受講者ID設定<br>
     * <br>
     * 受講者IDを引数で設定する<br>
     * <br>
     * @param studentId
     */
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    /**
     * 名前(姓)取得<br>
     * <br>
     * 名前(姓)を戻り値で返却する<br>
     * <br>
     * @return familyJnm
     */
    public String getFamilyJnm() {
        return familyJnm;
    }

    /**
     * 名前(姓)設定<br>
     * <br>
     * 名前(姓)を引数で設定する<br>
     * <br>
     * @param familyJnm
     */
    public void setFamilyJnm(String familyJnm) {
        this.familyJnm = familyJnm;
    }

    /**
     * 名前(名)取得<br>
     * <br>
     * 名前(名)を戻り値で返却する<br>
     * <br>
     * @return firstJnm
     */
    public String getFirstJnm() {
        return firstJnm;
    }

    /**
     * 名前(名)設定<br>
     * <br>
     * 名前(名)を引数で設定する<br>
     * <br>
     * @param firstJnm
     */
    public void setFirstJnm(String firstJnm) {
        this.firstJnm = firstJnm;
    }

    /**
     * フリガナ(姓)取得<br>
     * <br>
     * フリガナ(姓)を戻り値で返却する<br>
     * <br>
     * @return familyKnm
     */
    public String getFamilyKnm() {
        return familyKnm;
    }

    /**
     * フリガナ(姓)設定<br>
     * <br>
     * フリガナ(姓)を引数で設定する<br>
     * <br>
     * @param familyKnm
     */
    public void setFamilyKnm(String familyKnm) {
        this.familyKnm = familyKnm;
    }

    /**
     * フリガナ(名)取得<br>
     * <br>
     * フリガナ(名)を戻り値で返却する<br>
     * <br>
     * @return firstKnm
     */
    public String getFirstKnm() {
        return firstKnm;
    }

    /**
     * フリガナ(名)設定<br>
     * <br>
     * フリガナ(名)を引数で設定する<br>
     * <br>
     * @param firstKnm
     */
    public void setFirstKnm(String firstKnm) {
        this.firstKnm = firstKnm;
    }

    /**
     * ローマ字(姓)取得<br>
     * <br>
     * ローマ字(姓)を戻り値で返却する<br>
     * <br>
     * @return familyRomaji
     */
    public String getFamilyRomaji() {
        return familyRomaji;
    }

    /**
     * ローマ字(姓)設定<br>
     * <br>
     * ローマ字(姓)を引数で設定する<br>
     * <br>
     * @param familyRomaji
     */
    public void setFamilyRomaji(String familyRomaji) {
        this.familyRomaji = familyRomaji;
    }

    /**
     * ローマ字(名)取得<br>
     * <br>
     * ローマ字(名)を戻り値で返却する<br>
     * <br>
     * @return firstRomaji
     */
    public String getFirstRomaji() {
        return firstRomaji;
    }

    /**
     * ローマ字(名)設定<br>
     * <br>
     * ローマ字(名)を引数で設定する<br>
     * <br>
     * @param firstRomaji
     */
    public void setFirstRomaji(String firstRomaji) {
        this.firstRomaji = firstRomaji;
    }

    /**
     * ニックネーム取得<br>
     * <br>
     * ニックネームを戻り値で返却する<br>
     * <br>
     * @return nickNm
     */
    public String getNickNm() {
        return nickNm;
    }

    /**
     * ニックネーム設定<br>
     * <br>
     * ニックネームを引数で設定する<br>
     * <br>
     * @param nickNm
     */
    public void setNickNm(String nickNm) {
        this.nickNm = nickNm;
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
     * 電話番号1取得<br>
     * <br>
     * 電話番号1を戻り値で返却する<br>
     * <br>
     * @return tel1
     */
    public String getTel1() {
        return tel1;
    }

    /**
     * 電話番号1設定<br>
     * <br>
     * 電話番号1を引数で設定する<br>
     * <br>
     * @param tel1
     */
    public void setTel1(String tel1) {
        this.tel1 = tel1;
    }

    /**
     * 電話番号2(携帯電話)取得<br>
     * <br>
     * 電話番号2(携帯電話)を戻り値で返却する<br>
     * <br>
     * @return tel2
     */
    public String getTel2() {
        return tel2;
    }

    /**
     * 電話番号2(携帯電話)設定<br>
     * <br>
     * 電話番号2(携帯電話)を引数で設定する<br>
     * <br>
     * @param tel2
     */
    public void setTel2(String tel2) {
        this.tel2 = tel2;
    }

    /**
     * 生年月日：年取得<br>
     * <br>
     * 生年月日：年を戻り値で返却する<br>
     * <br>
     * @return birthYyyy
     */
    public String getBirthYyyy() {
        return birthYyyy;
    }

    /**
     * 生年月日：年設定<br>
     * <br>
     * 生年月日：年を引数で設定する<br>
     * <br>
     * @param birthYyyy
     */
    public void setBirthYyyy(String birthYyyy) {
        this.birthYyyy = birthYyyy;
    }

    /**
     * 生年月日：月取得<br>
     * <br>
     * 生年月日：月を戻り値で返却する<br>
     * <br>
     * @return birthMm
     */
    public String getBirthMm() {
        return birthMm;
    }

    /**
     * 生年月日：月設定<br>
     * <br>
     * 生年月日：月を引数で設定する<br>
     * <br>
     * @param birthMm
     */
    public void setBirthMm(String birthMm) {
        this.birthMm = birthMm;
    }

    /**
     * 生年月日：日取得<br>
     * <br>
     * 生年月日：日を戻り値で返却する<br>
     * <br>
     * @return birthDd
     */
    public String getBirthDd() {
        return birthDd;
    }

    /**
     * 生年月日：日設定<br>
     * <br>
     * 生年月日：日を引数で設定する<br>
     * <br>
     * @param birthDd
     */
    public void setBirthDd(String birthDd) {
        this.birthDd = birthDd;
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
     * 住所(市区町村 等)取得<br>
     * <br>
     * 住所(市区町村 等)を戻り値で返却する<br>
     * <br>
     * @return addressCity
     */
    public String getAddressCity() {
        return addressCity;
    }

    /**
     * 住所(市区町村 等)設定<br>
     * <br>
     * 住所(市区町村 等)を引数で設定する<br>
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
     * 性別区分取得<br>
     * <br>
     * 性別区分を戻り値で返却する<br>
     * <br>
     * @return genderKbn
     */
    public String getGenderKbn() {
        return genderKbn;
    }

    /**
     * 性別区分設定<br>
     * <br>
     * 性別区分を引数で設定する<br>
     * <br>
     * @param genderKbn
     */
    public void setGenderKbn(String genderKbn) {
        this.genderKbn = genderKbn;
    }

    /**
     * メールアドレス1取得<br>
     * <br>
     * メールアドレス1を戻り値で返却する<br>
     * <br>
     * @return mailAddress1
     */
    public String getMailAddress1() {
        return mailAddress1;
    }

    /**
     * メールアドレス1設定<br>
     * <br>
     * メールアドレス1を引数で設定する<br>
     * <br>
     * @param mailAddress1
     */
    public void setMailAddress1(String mailAddress1) {
        this.mailAddress1 = mailAddress1;
    }

    /**
     * メールアドレス2取得<br>
     * <br>
     * メールアドレス2を戻り値で返却する<br>
     * <br>
     * @return mailAddress2
     */
    public String getMailAddress2() {
        return mailAddress2;
    }

    /**
     * メールアドレス2設定<br>
     * <br>
     * メールアドレス2を引数で設定する<br>
     * <br>
     * @param mailAddress2
     */
    public void setMailAddress2(String mailAddress2) {
        this.mailAddress2 = mailAddress2;
    }

    /**
     * メールアドレス3取得<br>
     * <br>
     * メールアドレス3を戻り値で返却する<br>
     * <br>
     * @return mailAddress3
     */
    public String getMailAddress3() {
        return mailAddress3;
    }

    /**
     * メールアドレス3設定<br>
     * <br>
     * メールアドレス3を引数で設定する<br>
     * <br>
     * @param mailAddress3
     */
    public void setMailAddress3(String mailAddress3) {
        this.mailAddress3 = mailAddress3;
    }

    /**
     * 保護者：名前(姓)取得<br>
     * <br>
     * 保護者：名前(姓)を戻り値で返却する<br>
     * <br>
     * @return guardianFamilyJnm
     */
    public String getGuardianFamilyJnm() {
        return guardianFamilyJnm;
    }

    /**
     * 保護者：名前(姓)設定<br>
     * <br>
     * 保護者：名前(姓)を引数で設定する<br>
     * <br>
     * @param guardianFamilyJnm
     */
    public void setGuardianFamilyJnm(String guardianFamilyJnm) {
        this.guardianFamilyJnm = guardianFamilyJnm;
    }

    /**
     * 保護者：名前(名）取得<br>
     * <br>
     * 保護者：名前(名）を戻り値で返却する<br>
     * <br>
     * @return guardianFirstJnm
     */
    public String getGuardianFirstJnm() {
        return guardianFirstJnm;
    }

    /**
     * 保護者：名前(名）設定<br>
     * <br>
     * 保護者：名前(名）を引数で設定する<br>
     * <br>
     * @param guardianFirstJnm
     */
    public void setGuardianFirstJnm(String guardianFirstJnm) {
        this.guardianFirstJnm = guardianFirstJnm;
    }

    /**
     * 保護者：フリガナ(姓)取得<br>
     * <br>
     * 保護者：フリガナ(姓)を戻り値で返却する<br>
     * <br>
     * @return guardianFamilyKnm
     */
    public String getGuardianFamilyKnm() {
        return guardianFamilyKnm;
    }

    /**
     * 保護者：フリガナ(姓)設定<br>
     * <br>
     * 保護者：フリガナ(姓)を引数で設定する<br>
     * <br>
     * @param guardianFamilyKnm
     */
    public void setGuardianFamilyKnm(String guardianFamilyKnm) {
        this.guardianFamilyKnm = guardianFamilyKnm;
    }

    /**
     * 保護者：フリガナ(名）取得<br>
     * <br>
     * 保護者：フリガナ(名）を戻り値で返却する<br>
     * <br>
     * @return guardianFirstKnm
     */
    public String getGuardianFirstKnm() {
        return guardianFirstKnm;
    }

    /**
     * 保護者：フリガナ(名）設定<br>
     * <br>
     * 保護者：フリガナ(名）を引数で設定する<br>
     * <br>
     * @param guardianFirstKnm
     */
    public void setGuardianFirstKnm(String guardianFirstKnm) {
        this.guardianFirstKnm = guardianFirstKnm;
    }

    /**
     * あなたとの続柄取得<br>
     * <br>
     * あなたとの続柄を戻り値で返却する<br>
     * <br>
     * @return guardianFamilyRelationship
     */
    public String getGuardianFamilyRelationship() {
        return guardianFamilyRelationship;
    }

    /**
     * あなたとの続柄設定<br>
     * <br>
     * あなたとの続柄を引数で設定する<br>
     * <br>
     * @param guardianFamilyRelationship
     */
    public void setGuardianFamilyRelationship(String guardianFamilyRelationship) {
        this.guardianFamilyRelationship = guardianFamilyRelationship;
    }

    /**
     * 保護者：電話番号1取得<br>
     * <br>
     * 保護者：電話番号1を戻り値で返却する<br>
     * <br>
     * @return guardianTel1
     */
    public String getGuardianTel1() {
        return guardianTel1;
    }

    /**
     * 保護者：電話番号1設定<br>
     * <br>
     * 保護者：電話番号1を引数で設定する<br>
     * <br>
     * @param guardianTel1
     */
    public void setGuardianTel1(String guardianTel1) {
        this.guardianTel1 = guardianTel1;
    }

    /**
     * 保護者：電話番号2(携帯電話)取得<br>
     * <br>
     * 保護者：電話番号2(携帯電話)を戻り値で返却する<br>
     * <br>
     * @return guardianTel2
     */
    public String getGuardianTel2() {
        return guardianTel2;
    }

    /**
     * 保護者：電話番号2(携帯電話)設定<br>
     * <br>
     * 保護者：電話番号2(携帯電話)を引数で設定する<br>
     * <br>
     * @param guardianTel2
     */
    public void setGuardianTel2(String guardianTel2) {
        this.guardianTel2 = guardianTel2;
    }

    /**
     * 保護者：生年月日：年取得<br>
     * <br>
     * 保護者：生年月日：年を戻り値で返却する<br>
     * <br>
     * @return guardianBirthYyyy
     */
    public String getGuardianBirthYyyy() {
        return guardianBirthYyyy;
    }

    /**
     * 保護者：生年月日：年設定<br>
     * <br>
     * 保護者：生年月日：年を引数で設定する<br>
     * <br>
     * @param guardianBirthYyyy
     */
    public void setGuardianBirthYyyy(String guardianBirthYyyy) {
        this.guardianBirthYyyy = guardianBirthYyyy;
    }

    /**
     * 保護者：生年月日：月取得<br>
     * <br>
     * 保護者：生年月日：月を戻り値で返却する<br>
     * <br>
     * @return guardianBirthMm
     */
    public String getGuardianBirthMm() {
        return guardianBirthMm;
    }

    /**
     * 保護者：生年月日：月設定<br>
     * <br>
     * 保護者：生年月日：月を引数で設定する<br>
     * <br>
     * @param guardianBirthMm
     */
    public void setGuardianBirthMm(String guardianBirthMm) {
        this.guardianBirthMm = guardianBirthMm;
    }

    /**
     * 保護者：生年月日：日取得<br>
     * <br>
     * 保護者：生年月日：日を戻り値で返却する<br>
     * <br>
     * @return guardianBirthDd
     */
    public String getGuardianBirthDd() {
        return guardianBirthDd;
    }

    /**
     * 保護者：生年月日：日設定<br>
     * <br>
     * 保護者：生年月日：日を引数で設定する<br>
     * <br>
     * @param guardianBirthDd
     */
    public void setGuardianBirthDd(String guardianBirthDd) {
        this.guardianBirthDd = guardianBirthDd;
    }

    /**
     * 保護者：郵便番号取得<br>
     * <br>
     * 保護者：郵便番号を戻り値で返却する<br>
     * <br>
     * @return guardianZipCd
     */
    public String getGuardianZipCd() {
        return guardianZipCd;
    }

    /**
     * 保護者：郵便番号設定<br>
     * <br>
     * 保護者：郵便番号を引数で設定する<br>
     * <br>
     * @param guardianZipCd
     */
    public void setGuardianZipCd(String guardianZipCd) {
        this.guardianZipCd = guardianZipCd;
    }

    /**
     * 保護者：住所(地域)コード取得<br>
     * <br>
     * 保護者：住所(地域)コードを戻り値で返却する<br>
     * <br>
     * @return guardianAddressAreaCd
     */
    public String getGuardianAddressAreaCd() {
        return guardianAddressAreaCd;
    }

    /**
     * 保護者：住所(地域)コード設定<br>
     * <br>
     * 保護者：住所(地域)コードを引数で設定する<br>
     * <br>
     * @param guardianAddressAreaCd
     */
    public void setGuardianAddressAreaCd(String guardianAddressAreaCd) {
        this.guardianAddressAreaCd = guardianAddressAreaCd;
    }

    /**
     * 保護者：住所(都道府県)コード取得<br>
     * <br>
     * 保護者：住所(都道府県)コードを戻り値で返却する<br>
     * <br>
     * @return guardianAddressPrefectureCd
     */
    public String getGuardianAddressPrefectureCd() {
        return guardianAddressPrefectureCd;
    }

    /**
     * 保護者：住所(都道府県)コード設定<br>
     * <br>
     * 保護者：住所(都道府県)コードを引数で設定する<br>
     * <br>
     * @param guardianAddressPrefectureCd
     */
    public void setGuardianAddressPrefectureCd(String guardianAddressPrefectureCd) {
        this.guardianAddressPrefectureCd = guardianAddressPrefectureCd;
    }

    /**
     * 保護者：住所(市区町村 等)取得<br>
     * <br>
     * 保護者：住所(市区町村 等)を戻り値で返却する<br>
     * <br>
     * @return guardianAddressCity
     */
    public String getGuardianAddressCity() {
        return guardianAddressCity;
    }

    /**
     * 保護者：住所(市区町村 等)設定<br>
     * <br>
     * 保護者：住所(市区町村 等)を引数で設定する<br>
     * <br>
     * @param guardianAddressCity
     */
    public void setGuardianAddressCity(String guardianAddressCity) {
        this.guardianAddressCity = guardianAddressCity;
    }

    /**
     * 保護者：住所(ﾋﾞﾙ、ﾏﾝｼｮﾝ名 等)取得<br>
     * <br>
     * 保護者：住所(ﾋﾞﾙ、ﾏﾝｼｮﾝ名 等)を戻り値で返却する<br>
     * <br>
     * @return guardianAddressOthers
     */
    public String getGuardianAddressOthers() {
        return guardianAddressOthers;
    }

    /**
     * 保護者：住所(ﾋﾞﾙ、ﾏﾝｼｮﾝ名 等)設定<br>
     * <br>
     * 保護者：住所(ﾋﾞﾙ、ﾏﾝｼｮﾝ名 等)を引数で設定する<br>
     * <br>
     * @param guardianAddressOthers
     */
    public void setGuardianAddressOthers(String guardianAddressOthers) {
        this.guardianAddressOthers = guardianAddressOthers;
    }

    /**
     * 保護者：性別区分取得<br>
     * <br>
     * 保護者：性別区分を戻り値で返却する<br>
     * <br>
     * @return guardianGenderKbn
     */
    public String getGuardianGenderKbn() {
        return guardianGenderKbn;
    }

    /**
     * 保護者：性別区分設定<br>
     * <br>
     * 保護者：性別区分を引数で設定する<br>
     * <br>
     * @param guardianGenderKbn
     */
    public void setGuardianGenderKbn(String guardianGenderKbn) {
        this.guardianGenderKbn = guardianGenderKbn;
    }

    /**
     * 保護者：メールアドレス1取得<br>
     * <br>
     * 保護者：メールアドレス1を戻り値で返却する<br>
     * <br>
     * @return guardianMailAddress1
     */
    public String getGuardianMailAddress1() {
        return guardianMailAddress1;
    }

    /**
     * 保護者：メールアドレス1設定<br>
     * <br>
     * 保護者：メールアドレス1を引数で設定する<br>
     * <br>
     * @param guardianMailAddress1
     */
    public void setGuardianMailAddress1(String guardianMailAddress1) {
        this.guardianMailAddress1 = guardianMailAddress1;
    }

    /**
     * 保護者：メールアドレス2取得<br>
     * <br>
     * 保護者：メールアドレス2を戻り値で返却する<br>
     * <br>
     * @return guardianMailAddress2
     */
    public String getGuardianMailAddress2() {
        return guardianMailAddress2;
    }

    /**
     * 保護者：メールアドレス2設定<br>
     * <br>
     * 保護者：メールアドレス2を引数で設定する<br>
     * <br>
     * @param guardianMailAddress2
     */
    public void setGuardianMailAddress2(String guardianMailAddress2) {
        this.guardianMailAddress2 = guardianMailAddress2;
    }

    /**
     * 保護者：メールアドレス3取得<br>
     * <br>
     * 保護者：メールアドレス3を戻り値で返却する<br>
     * <br>
     * @return guardianMailAddress3
     */
    public String getGuardianMailAddress3() {
        return guardianMailAddress3;
    }

    /**
     * 保護者：メールアドレス3設定<br>
     * <br>
     * 保護者：メールアドレス3を引数で設定する<br>
     * <br>
     * @param guardianMailAddress3
     */
    public void setGuardianMailAddress3(String guardianMailAddress3) {
        this.guardianMailAddress3 = guardianMailAddress3;
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


}
