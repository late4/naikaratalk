package com.naikara_talk.dto;

import java.sql.Timestamp;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称:</b>マスタ保守<br>
 * <b>クラス名称　　　:</b>受講者マスタ（受講者マスタ【単票】）クラス<br>
 * <b>クラス概要　　　:</b>受講者（受講者マスタ【単票】）マスタDTO<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/07/30 TECS 新規作成
 */
public class StuMPoiOwnTDto extends AbstractDto {

    private String studentId;                                          // 受講者ID
    private String familyJnm;                                          // 名前(姓)
    private String firstJnm;                                           // 名前(名)
    private String familyKnm;                                          // フリガナ(姓)
    private String firstKnm;                                           // フリガナ(名)
    private String familyRomaji;                                       // ローマ字(姓)
    private String firstRomaji;                                        // ローマ字(名)
    private String nickNm;                                             // ニックネーム
    private String password;                                           // パスワード
    private String tel1;                                               // 電話番号1
    private String tel2;                                               // 電話番号2
    private String birthYyyy;                                          // 生年月日：年
    private String birthMm;                                            // 生年月日：月
    private String birthDd;                                            // 生年月日：日
    private String zipCd;                                              // 郵便番号
    private String addressAreaCd;                                      // 住所(地域)コード
    private String addressPrefectureCd;                                // 住所(都道府県)コード
    private String addressCity;                                        // 住所(市区町村 等)
    private String addressOthers;                                      // 住所(ﾋﾞﾙ、ﾏﾝｼｮﾝ名 等)
    private String genderKbn;                                          // 性別区分
    private String mailAddress1;                                       // メールアドレス1
    private String mailAddress2;                                       // メールアドレス2
    private String mailAddress3;                                       // メールアドレス3
    private int loginNum;                                              // ログイン回数
    private String endLoginDt;                                         // 最終ログイン日
    private String occupationCd;                                       // 職業コード
    private String customerKbn;                                        // 顧客区分
    private String organizationNm;                                     // 組織名
    private String useStrDt;                                           // 利用開始日
    private String useEndDt;                                           // 利用終了日
    private String useStopFlg;                                         // 利用停止フラグ
    private String useMotiveFlg1;                                      // 利用動機フラグ１
    private String useMotiveFlg2;                                      // 利用動機フラグ２
    private String useMotiveFlg3;                                      // 利用動機フラグ３
    private String useMotiveFlg4;                                      // 利用動機フラグ４
    private String useMotiveFlg5;                                      // 利用動機フラグ５
    private String useMotive;                                          // 利用動機テキスト
    private String achievement;                                        // 達成目標
    private String useAgreementFlg;                                    // 利用規約に同意する：チェックフラグ
    private String individualAgreementFlg;                             // 個人情報の同意：チェックフラグ
    private String schoolCmt;                                          // ｽｸｰﾙからのｺﾒﾝﾄ(生徒公開)
    private String remark;                                             // 備考(生徒非公開)
    private String organizationId;                                     // 組織ID
    private String positionOrganizationId;                             // 所属組織内ID
    private String studentPosition;                                    // 受講者所属部署
    private int burdenNum;                                             // 自己負担率
    private String consentDocumentAcquisitionFlg;                      // 保護者の同意書の入手フラグ
    private String pointPurchaseFlg;                                   // ポイント購入済フラグ
    private int dmNoNeedFlg;                                           // DM不要フラグ
    private int other1Flg;                                             // その他フラグ１
    private int other2Flg;                                             // その他フラグ２
    private int other3Flg;                                             // その他フラグ３
    private int other4Flg;                                             // その他フラグ４
    private String guardianFamilyJnm;                                  // 保護者：名前(姓)
    private String guardianFirstJnm;                                   // 保護者：名前(名）
    private String guardianFamilyKnm;                                  // 保護者：フリガナ(姓)
    private String guardianFirstKnm;                                   // 保護者：フリガナ(名）
    private String guardianFamilyRelationship;                         // あなたとの続柄
    private String guardianTel1;                                       // 保護者：電話番号1
    private String guardianTel2;                                       // 保護者：電話番号2
    private String guardianBirthYyyy;                                  // 保護者：生年月日：年
    private String guardianBirthMm;                                    // 保護者：生年月日：月
    private String guardianBirthDd;                                    // 保護者：生年月日：日
    private String guardianZipCd;                                      // 保護者：郵便番号
    private String guardianAddressAreaCd;                              // 保護者：住所(地域)コード
    private String guardianAddressPrefectureCd;                        // 保護者：住所(都道府県)コード
    private String guardianAddressCity;                                // 保護者：住所(市区町村 等)
    private String guardianAddressOthers;                              // 保護者：住所(ﾋﾞﾙ、ﾏﾝｼｮﾝ名 等)
    private String guardianGenderKbn;                                  // 保護者：性別区分
    private String guardianMailAddress1;                               // 保護者：メールアドレス1
    private String guardianMailAddress2;                               // 保護者：メールアドレス2
    private String guardianMailAddress3;                               // 保護者：メールアドレス3
    private int recordVerNo;                                           // レコードバージョン番号
    private Timestamp insertDttm;                                      // 登録日時
    private String insertCd;                                           // 登録者コード
    private Timestamp updateDttm;                                      // 更新日時
    private String updateCd;                                           // 更新者コード
    private int returnCode;                                            // リターンコー
    private String effectiveEndDt;                                     // 有効終了日
    private String balancePoint;                                        // ポイント残高
    private String addressPrefectureNm;                                // 住所(都道府県)名
    private String genderKbnNm;                                        // 性別区分(名)
    private String occupationNm;                                       // 職業名
    private String itemNm1_sel;                                        // 検索条件：項目名1
    private String itemNm2_sel;                                        // 検索条件：項目名2
    private String itemNm3_sel;                                        // 検索条件：項目名3
    private String itemNm4_sel;                                        // 検索条件：項目名4
    private String itemNm5_sel;                                        // 検索条件：項目名5
    private String itemCondn1_rdl;                                     // 検索条件：項目条件1
    private String itemCondn2_rdl;                                     // 検索条件：項目条件2
    private String itemCondn3_rdl;                                     // 検索条件：項目条件3
    private String itemCondn4_rdl;                                     // 検索条件：項目条件4
    private String itemCondn5_rdl;                                     // 検索条件：項目条件5
    private String itemFrom1_txt;                                      // 検索条件：項目1from
    private String itemFrom2_txt;                                      // 検索条件：項目2from
    private String itemFrom3_txt;                                      // 検索条件：項目3from
    private String itemFrom4_txt;                                      // 検索条件：項目4from
    private String itemFrom5_txt;                                      // 検索条件：項目5from
    private String itemTo1_txt;                                        // 検索条件：項目1to
    private String itemTo2_txt;                                        // 検索条件：項目2to
    private String itemTo3_txt;                                        // 検索条件：項目3to
    private String itemTo4_txt;                                        // 検索条件：項目4to
    private String itemTo5_txt;                                        // 検索条件：項目5to

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
     * 電話番号2取得<br>
     * <br>
     * 電話番号2を戻り値で返却する<br>
     * <br>
     * @return tel2
     */
    public String getTel2() {
        return tel2;
    }

    /**
     * 電話番号2設定<br>
     * <br>
     * 電話番号2を引数で設定する<br>
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
     * ログイン回数取得<br>
     * <br>
     * ログイン回数を戻り値で返却する<br>
     * <br>
     * @return loginNum
     */
    public int getLoginNum() {
        return loginNum;
    }

    /**
     * ログイン回数設定<br>
     * <br>
     * ログイン回数を引数で設定する<br>
     * <br>
     * @param loginNum
     */
    public void setLoginNum(int loginNum) {
        this.loginNum = loginNum;
    }

    /**
     * 最終ログイン日取得<br>
     * <br>
     * 最終ログイン日を戻り値で返却する<br>
     * <br>
     * @return endLoginDt
     */
    public String getEndLoginDt() {
        return endLoginDt;
    }

    /**
     * 最終ログイン日設定<br>
     * <br>
     * 最終ログイン日を引数で設定する<br>
     * <br>
     * @param endLoginDt
     */
    public void setEndLoginDt(String endLoginDt) {
        this.endLoginDt = endLoginDt;
    }

    /**
     * 職業コード取得<br>
     * <br>
     * 職業コードを戻り値で返却する<br>
     * <br>
     * @return occupationCd
     */
    public String getOccupationCd() {
        return occupationCd;
    }

    /**
     * 職業コード設定<br>
     * <br>
     * 職業コードを引数で設定する<br>
     * <br>
     * @param occupationCd
     */
    public void setOccupationCd(String occupationCd) {
        this.occupationCd = occupationCd;
    }

    /**
     * 顧客区分取得<br>
     * <br>
     * 顧客区分を戻り値で返却する<br>
     * <br>
     * @return customerKbn
     */
    public String getCustomerKbn() {
        return customerKbn;
    }

    /**
     * 顧客区分設定<br>
     * <br>
     * 顧客区分を引数で設定する<br>
     * <br>
     * @param customerKbn
     */
    public void setCustomerKbn(String customerKbn) {
        this.customerKbn = customerKbn;
    }

    /**
     * 組織名取得<br>
     * <br>
     * 組織名を戻り値で返却する<br>
     * <br>
     * @return organizationNm
     */
    public String getOrganizationNm() {
        return organizationNm;
    }

    /**
     * 組織名設定<br>
     * <br>
     * 組織名を引数で設定する<br>
     * <br>
     * @param organizationNm
     */
    public void setOrganizationNm(String organizationNm) {
        this.organizationNm = organizationNm;
    }

    /**
     * 利用開始日取得<br>
     * <br>
     * 利用開始日を戻り値で返却する<br>
     * <br>
     * @return useStrDt
     */
    public String getUseStrDt() {
        return useStrDt;
    }

    /**
     * 利用開始日設定<br>
     * <br>
     * 利用開始日を引数で設定する<br>
     * <br>
     * @param useStrDt
     */
    public void setUseStrDt(String useStrDt) {
        this.useStrDt = useStrDt;
    }

    /**
     * 利用終了日取得<br>
     * <br>
     * 利用終了日を戻り値で返却する<br>
     * <br>
     * @return useEndDt
     */
    public String getUseEndDt() {
        return useEndDt;
    }

    /**
     * 利用終了日設定<br>
     * <br>
     * 利用終了日を引数で設定する<br>
     * <br>
     * @param useEndDt
     */
    public void setUseEndDt(String useEndDt) {
        this.useEndDt = useEndDt;
    }

    /**
     * 利用停止フラグ取得<br>
     * <br>
     * 利用停止フラグを戻り値で返却する<br>
     * <br>
     * @return useStopFlg
     */
    public String getUseStopFlg() {
        return useStopFlg;
    }

    /**
     * 利用停止フラグ設定<br>
     * <br>
     * 利用停止フラグを引数で設定する<br>
     * <br>
     * @param useStopFlg
     */
    public void setUseStopFlg(String useStopFlg) {
        this.useStopFlg = useStopFlg;
    }

    /**
     * 利用動機フラグ１取得<br>
     * <br>
     * 利用動機フラグ１を戻り値で返却する<br>
     * <br>
     * @return useMotiveFlg1
     */
    public String getUseMotiveFlg1() {
        return useMotiveFlg1;
    }

    /**
     * 利用動機フラグ１設定<br>
     * <br>
     * 利用動機フラグ１を引数で設定する<br>
     * <br>
     * @param useMotiveFlg1
     */
    public void setUseMotiveFlg1(String useMotiveFlg1) {
        this.useMotiveFlg1 = useMotiveFlg1;
    }

    /**
     * 利用動機フラグ２取得<br>
     * <br>
     * 利用動機フラグ２を戻り値で返却する<br>
     * <br>
     * @return useMotiveFlg2
     */
    public String getUseMotiveFlg2() {
        return useMotiveFlg2;
    }

    /**
     * 利用動機フラグ２設定<br>
     * <br>
     * 利用動機フラグ２を引数で設定する<br>
     * <br>
     * @param useMotiveFlg2
     */
    public void setUseMotiveFlg2(String useMotiveFlg2) {
        this.useMotiveFlg2 = useMotiveFlg2;
    }

    /**
     * 利用動機フラグ３取得<br>
     * <br>
     * 利用動機フラグ３を戻り値で返却する<br>
     * <br>
     * @return useMotiveFlg3
     */
    public String getUseMotiveFlg3() {
        return useMotiveFlg3;
    }

    /**
     * 利用動機フラグ３設定<br>
     * <br>
     * 利用動機フラグ３を引数で設定する<br>
     * <br>
     * @param useMotiveFlg3
     */
    public void setUseMotiveFlg3(String useMotiveFlg3) {
        this.useMotiveFlg3 = useMotiveFlg3;
    }

    /**
     * 利用動機フラグ４取得<br>
     * <br>
     * 利用動機フラグ４を戻り値で返却する<br>
     * <br>
     * @return useMotiveFlg4
     */
    public String getUseMotiveFlg4() {
        return useMotiveFlg4;
    }

    /**
     * 利用動機フラグ４設定<br>
     * <br>
     * 利用動機フラグ４を引数で設定する<br>
     * <br>
     * @param useMotiveFlg4
     */
    public void setUseMotiveFlg4(String useMotiveFlg4) {
        this.useMotiveFlg4 = useMotiveFlg4;
    }

    /**
     * 利用動機フラグ５取得<br>
     * <br>
     * 利用動機フラグ５を戻り値で返却する<br>
     * <br>
     * @return useMotiveFlg5
     */
    public String getUseMotiveFlg5() {
        return useMotiveFlg5;
    }

    /**
     * 利用動機フラグ５設定<br>
     * <br>
     * 利用動機フラグ５を引数で設定する<br>
     * <br>
     * @param useMotiveFlg5
     */
    public void setUseMotiveFlg5(String useMotiveFlg5) {
        this.useMotiveFlg5 = useMotiveFlg5;
    }

    /**
     * 利用動機テキスト取得<br>
     * <br>
     * 利用動機テキストを戻り値で返却する<br>
     * <br>
     * @return useMotive
     */
    public String getUseMotive() {
        return useMotive;
    }

    /**
     * 利用動機テキスト設定<br>
     * <br>
     * 利用動機テキストを引数で設定する<br>
     * <br>
     * @param useMotive
     */
    public void setUseMotive(String useMotive) {
        this.useMotive = useMotive;
    }

    /**
     * 達成目標取得<br>
     * <br>
     * 達成目標を戻り値で返却する<br>
     * <br>
     * @return achievement
     */
    public String getAchievement() {
        return achievement;
    }

    /**
     * 達成目標設定<br>
     * <br>
     * 達成目標を引数で設定する<br>
     * <br>
     * @param achievement
     */
    public void setAchievement(String achievement) {
        this.achievement = achievement;
    }

    /**
     * 利用規約に同意する：チェックフラグ取得<br>
     * <br>
     * 利用規約に同意する：チェックフラグを戻り値で返却する<br>
     * <br>
     * @return useAgreementFlg
     */
    public String getUseAgreementFlg() {
        return useAgreementFlg;
    }

    /**
     * 利用規約に同意する：チェックフラグ設定<br>
     * <br>
     * 利用規約に同意する：チェックフラグを引数で設定する<br>
     * <br>
     * @param useAgreementFlg
     */
    public void setUseAgreementFlg(String useAgreementFlg) {
        this.useAgreementFlg = useAgreementFlg;
    }

    /**
     * 個人情報の同意：チェックフラグ取得<br>
     * <br>
     * 個人情報の同意：チェックフラグを戻り値で返却する<br>
     * <br>
     * @return individualAgreementFlg
     */
    public String getIndividualAgreementFlg() {
        return individualAgreementFlg;
    }

    /**
     * 個人情報の同意：チェックフラグ設定<br>
     * <br>
     * 個人情報の同意：チェックフラグを引数で設定する<br>
     * <br>
     * @param individualAgreementFlg
     */
    public void setIndividualAgreementFlg(String individualAgreementFlg) {
        this.individualAgreementFlg = individualAgreementFlg;
    }

    /**
     * ｽｸｰﾙからのｺﾒﾝﾄ(生徒公開)取得<br>
     * <br>
     * ｽｸｰﾙからのｺﾒﾝﾄ(生徒公開)を戻り値で返却する<br>
     * <br>
     * @return schoolCmt
     */
    public String getSchoolCmt() {
        return schoolCmt;
    }

    /**
     * ｽｸｰﾙからのｺﾒﾝﾄ(生徒公開)設定<br>
     * <br>
     * ｽｸｰﾙからのｺﾒﾝﾄ(生徒公開)を引数で設定する<br>
     * <br>
     * @param schoolCmt
     */
    public void setSchoolCmt(String schoolCmt) {
        this.schoolCmt = schoolCmt;
    }

    /**
     * 備考(生徒非公開)取得<br>
     * <br>
     * 備考(生徒非公開)を戻り値で返却する<br>
     * <br>
     * @return remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 備考(生徒非公開)設定<br>
     * <br>
     * 備考(生徒非公開)を引数で設定する<br>
     * <br>
     * @param remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

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
     * 所属組織内ID取得<br>
     * <br>
     * 所属組織内IDを戻り値で返却する<br>
     * <br>
     * @return positionOrganizationId
     */
    public String getPositionOrganizationId() {
        return positionOrganizationId;
    }

    /**
     * 所属組織内ID設定<br>
     * <br>
     * 所属組織内IDを引数で設定する<br>
     * <br>
     * @param positionOrganizationId
     */
    public void setPositionOrganizationId(String positionOrganizationId) {
        this.positionOrganizationId = positionOrganizationId;
    }

    /**
     * 受講者所属部署取得<br>
     * <br>
     * 受講者所属部署を戻り値で返却する<br>
     * <br>
     * @return studentPosition
     */
    public String getStudentPosition() {
        return studentPosition;
    }

    /**
     * 受講者所属部署設定<br>
     * <br>
     * 受講者所属部署を引数で設定する<br>
     * <br>
     * @param studentPosition
     */
    public void setStudentPosition(String studentPosition) {
        this.studentPosition = studentPosition;
    }

    /**
     * 自己負担率取得<br>
     * <br>
     * 自己負担率を戻り値で返却する<br>
     * <br>
     * @return burdenNum
     */
    public int getBurdenNum() {
        return burdenNum;
    }

    /**
     * 自己負担率設定<br>
     * <br>
     * 自己負担率を引数で設定する<br>
     * <br>
     * @param burdenNum
     */
    public void setBurdenNum(int burdenNum) {
        this.burdenNum = burdenNum;
    }

    /**
     * 保護者の同意書の入手フラグ取得<br>
     * <br>
     * 保護者の同意書の入手フラグを戻り値で返却する<br>
     * <br>
     * @return consentDocumentAcquisitionFlg
     */
    public String getConsentDocumentAcquisitionFlg() {
        return consentDocumentAcquisitionFlg;
    }

    /**
     * 保護者の同意書の入手フラグ設定<br>
     * <br>
     * 保護者の同意書の入手フラグを引数で設定する<br>
     * <br>
     * @param consentDocumentAcquisitionFlg
     */
    public void setConsentDocumentAcquisitionFlg(String consentDocumentAcquisitionFlg) {
        this.consentDocumentAcquisitionFlg = consentDocumentAcquisitionFlg;
    }

    /**
     * ポイント購入済フラグ取得<br>
     * <br>
     * ポイント購入済フラグを戻り値で返却する<br>
     * <br>
     * @return pointPurchaseFlg
     */
    public String getPointPurchaseFlg() {
        return pointPurchaseFlg;
    }

    /**
     * ポイント購入済フラグ設定<br>
     * <br>
     * ポイント購入済フラグを引数で設定する<br>
     * <br>
     * @param pointPurchaseFlg
     */
    public void setPointPurchaseFlg(String pointPurchaseFlg) {
        this.pointPurchaseFlg = pointPurchaseFlg;
    }

    /**
     * DM不要フラグ取得<br>
     * <br>
     * DM不要フラグを戻り値で返却する<br>
     * <br>
     * @return dmNoNeedFlg
     */
    public int getDmNoNeedFlg() {
        return dmNoNeedFlg;
    }

    /**
     * DM不要フラグ設定<br>
     * <br>
     * DM不要フラグを引数で設定する<br>
     * <br>
     * @param dmNoNeedFlg
     */
    public void setDmNoNeedFlg(int dmNoNeedFlg) {
        this.dmNoNeedFlg = dmNoNeedFlg;
    }

    /**
     * その他フラグ１取得<br>
     * <br>
     * その他フラグ１を戻り値で返却する<br>
     * <br>
     * @return other1Flg
     */
    public int getOther1Flg() {
        return other1Flg;
    }

    /**
     * その他フラグ１設定<br>
     * <br>
     * その他フラグ１を引数で設定する<br>
     * <br>
     * @param other1Flg
     */
    public void setOther1Flg(int other1Flg) {
        this.other1Flg = other1Flg;
    }

    /**
     * その他フラグ２取得<br>
     * <br>
     * その他フラグ２を戻り値で返却する<br>
     * <br>
     * @return other2Flg
     */
    public int getOther2Flg() {
        return other2Flg;
    }

    /**
     * その他フラグ２設定<br>
     * <br>
     * その他フラグ２を引数で設定する<br>
     * <br>
     * @param other2Flg
     */
    public void setOther2Flg(int other2Flg) {
        this.other2Flg = other2Flg;
    }

    /**
     * その他フラグ３取得<br>
     * <br>
     * その他フラグ３を戻り値で返却する<br>
     * <br>
     * @return other3Flg
     */
    public int getOther3Flg() {
        return other3Flg;
    }

    /**
     * その他フラグ３設定<br>
     * <br>
     * その他フラグ３を引数で設定する<br>
     * <br>
     * @param other3Flg
     */
    public void setOther3Flg(int other3Flg) {
        this.other3Flg = other3Flg;
    }

    /**
     * その他フラグ４取得<br>
     * <br>
     * その他フラグ４を戻り値で返却する<br>
     * <br>
     * @return other4Flg
     */
    public int getOther4Flg() {
        return other4Flg;
    }

    /**
     * その他フラグ４設定<br>
     * <br>
     * その他フラグ４を引数で設定する<br>
     * <br>
     * @param other4Flg
     */
    public void setOther4Flg(int other4Flg) {
        this.other4Flg = other4Flg;
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
     * 保護者：電話番号2取得<br>
     * <br>
     * 保護者：電話番号2を戻り値で返却する<br>
     * <br>
     * @return guardianTel2
     */
    public String getGuardianTel2() {
        return guardianTel2;
    }

    /**
     * 保護者：電話番号2設定<br>
     * <br>
     * 保護者：電話番号2を引数で設定する<br>
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
     * 有効終了日取得<br>
     * <br>
     * 有効終了日を戻り値で返却する<br>
     * <br>
     * @return effectiveEndDt
     */
    public String getEffectiveEndDt() {
        return effectiveEndDt;
    }

    /**
     * 有効終了日設定<br>
     * <br>
     * 有効終了日を引数で設定する<br>
     * <br>
     * @param effectiveEndDt
     */
    public void setEffectiveEndDt(String effectiveEndDt) {
        this.effectiveEndDt = effectiveEndDt;
    }

    /**
     * ポイント残高取得<br>
     * <br>
     * ポイント残高を戻り値で返却する<br>
     * <br>
     * @return balancePoint
     */
    public String getBalancePoint() {
        return balancePoint;
    }

    /**
     * ポイント残高設定<br>
     * <br>
     * ポイント残高を引数で設定する<br>
     * <br>
     * @param balancePoint
     */
    public void setBalancePoint(String balancePoint) {
        this.balancePoint = balancePoint;
    }

    /**
     * 住所(都道府県)名取得<br>
     * <br>
     * 住所(都道府県)名を戻り値で返却する<br>
     * <br>
     * @return addressPrefectureNm
     */
    public String getAddressPrefectureNm() {
        return addressPrefectureNm;
    }

    /**
     * 住所(都道府県)名設定<br>
     * <br>
     * 住所(都道府県)名を引数で設定する<br>
     * <br>
     * @param addressPrefectureNm
     */
    public void setAddressPrefectureNm(String addressPrefectureNm) {
        this.addressPrefectureNm = addressPrefectureNm;
    }

    /**
     * 性別区分(名)取得<br>
     * <br>
     * 性別区分(名)を戻り値で返却する<br>
     * <br>
     * @return genderKbnNm
     */
    public String getGenderKbnNm() {
        return genderKbnNm;
    }

    /**
     * 性別区分(名)設定<br>
     * <br>
     * 性別区分(名)を引数で設定する<br>
     * <br>
     * @param genderKbnNm
     */
    public void setGenderKbnNm(String genderKbnNm) {
        this.genderKbnNm = genderKbnNm;
    }

    /**
     * 職業名取得<br>
     * <br>
     * 職業名を戻り値で返却する<br>
     * <br>
     * @return occupationNm
     */
    public String getOccupationNm() {
        return occupationNm;
    }

    /**
     * 職業名設定<br>
     * <br>
     * 職業名を引数で設定する<br>
     * <br>
     * @param occupationNm
     */
    public void setOccupationNm(String occupationNm) {
        this.occupationNm = occupationNm;
    }

    /**
         * itemNm1_sel取得<br>
         * <br>
         * itemNm1_selを戻り値で返却する<br>
         * <br>
         * @return itemNm1_sel
         */
    public String getItemNm1_sel() {
        return itemNm1_sel;
    }

    /**
         * itemNm1_sel設定<br>
         * <br>
         * itemNm1_selを引数で設定する<br>
         * <br>
         * @param itemNm1_sel
         */
    public void setItemNm1_sel(String itemNm1_sel) {
        this.itemNm1_sel = itemNm1_sel;
    }

    /**
         * itemNm2_sel取得<br>
         * <br>
         * itemNm2_selを戻り値で返却する<br>
         * <br>
         * @return itemNm2_sel
         */
    public String getItemNm2_sel() {
        return itemNm2_sel;
    }

    /**
         * itemNm2_sel設定<br>
         * <br>
         * itemNm2_selを引数で設定する<br>
         * <br>
         * @param itemNm2_sel
         */
    public void setItemNm2_sel(String itemNm2_sel) {
        this.itemNm2_sel = itemNm2_sel;
    }

    /**
         * itemNm3_sel取得<br>
         * <br>
         * itemNm3_selを戻り値で返却する<br>
         * <br>
         * @return itemNm3_sel
         */
    public String getItemNm3_sel() {
        return itemNm3_sel;
    }

    /**
         * itemNm3_sel設定<br>
         * <br>
         * itemNm3_selを引数で設定する<br>
         * <br>
         * @param itemNm3_sel
         */
    public void setItemNm3_sel(String itemNm3_sel) {
        this.itemNm3_sel = itemNm3_sel;
    }

    /**
         * itemNm4_sel取得<br>
         * <br>
         * itemNm4_selを戻り値で返却する<br>
         * <br>
         * @return itemNm4_sel
         */
    public String getItemNm4_sel() {
        return itemNm4_sel;
    }

    /**
         * itemNm4_sel設定<br>
         * <br>
         * itemNm4_selを引数で設定する<br>
         * <br>
         * @param itemNm4_sel
         */
    public void setItemNm4_sel(String itemNm4_sel) {
        this.itemNm4_sel = itemNm4_sel;
    }

    /**
         * itemNm5_sel取得<br>
         * <br>
         * itemNm5_selを戻り値で返却する<br>
         * <br>
         * @return itemNm5_sel
         */
    public String getItemNm5_sel() {
        return itemNm5_sel;
    }

    /**
         * itemNm5_sel設定<br>
         * <br>
         * itemNm5_selを引数で設定する<br>
         * <br>
         * @param itemNm5_sel
         */
    public void setItemNm5_sel(String itemNm5_sel) {
        this.itemNm5_sel = itemNm5_sel;
    }

    /**
         * itemCondn1_rdl取得<br>
         * <br>
         * itemCondn1_rdlを戻り値で返却する<br>
         * <br>
         * @return itemCondn1_rdl
         */
    public String getItemCondn1_rdl() {
        return itemCondn1_rdl;
    }

    /**
         * itemCondn1_rdl設定<br>
         * <br>
         * itemCondn1_rdlを引数で設定する<br>
         * <br>
         * @param itemCondn1_rdl
         */
    public void setItemCondn1_rdl(String itemCondn1_rdl) {
        this.itemCondn1_rdl = itemCondn1_rdl;
    }

    /**
         * itemCondn2_rdl取得<br>
         * <br>
         * itemCondn2_rdlを戻り値で返却する<br>
         * <br>
         * @return itemCondn2_rdl
         */
    public String getItemCondn2_rdl() {
        return itemCondn2_rdl;
    }

    /**
         * itemCondn2_rdl設定<br>
         * <br>
         * itemCondn2_rdlを引数で設定する<br>
         * <br>
         * @param itemCondn2_rdl
         */
    public void setItemCondn2_rdl(String itemCondn2_rdl) {
        this.itemCondn2_rdl = itemCondn2_rdl;
    }

    /**
         * itemCondn3_rdl取得<br>
         * <br>
         * itemCondn3_rdlを戻り値で返却する<br>
         * <br>
         * @return itemCondn3_rdl
         */
    public String getItemCondn3_rdl() {
        return itemCondn3_rdl;
    }

    /**
         * itemCondn3_rdl設定<br>
         * <br>
         * itemCondn3_rdlを引数で設定する<br>
         * <br>
         * @param itemCondn3_rdl
         */
    public void setItemCondn3_rdl(String itemCondn3_rdl) {
        this.itemCondn3_rdl = itemCondn3_rdl;
    }

    /**
         * itemCondn4_rdl取得<br>
         * <br>
         * itemCondn4_rdlを戻り値で返却する<br>
         * <br>
         * @return itemCondn4_rdl
         */
    public String getItemCondn4_rdl() {
        return itemCondn4_rdl;
    }

    /**
         * itemCondn4_rdl設定<br>
         * <br>
         * itemCondn4_rdlを引数で設定する<br>
         * <br>
         * @param itemCondn4_rdl
         */
    public void setItemCondn4_rdl(String itemCondn4_rdl) {
        this.itemCondn4_rdl = itemCondn4_rdl;
    }

    /**
         * itemCondn5_rdl取得<br>
         * <br>
         * itemCondn5_rdlを戻り値で返却する<br>
         * <br>
         * @return itemCondn5_rdl
         */
    public String getItemCondn5_rdl() {
        return itemCondn5_rdl;
    }

    /**
         * itemCondn5_rdl設定<br>
         * <br>
         * itemCondn5_rdlを引数で設定する<br>
         * <br>
         * @param itemCondn5_rdl
         */
    public void setItemCondn5_rdl(String itemCondn5_rdl) {
        this.itemCondn5_rdl = itemCondn5_rdl;
    }

    /**
         * itemFrom1_txt取得<br>
         * <br>
         * itemFrom1_txtを戻り値で返却する<br>
         * <br>
         * @return itemFrom1_txt
         */
    public String getItemFrom1_txt() {
        return itemFrom1_txt;
    }

    /**
         * itemFrom1_txt設定<br>
         * <br>
         * itemFrom1_txtを引数で設定する<br>
         * <br>
         * @param itemFrom1_txt
         */
    public void setItemFrom1_txt(String itemFrom1_txt) {
        this.itemFrom1_txt = itemFrom1_txt;
    }

    /**
         * itemFrom2_txt取得<br>
         * <br>
         * itemFrom2_txtを戻り値で返却する<br>
         * <br>
         * @return itemFrom2_txt
         */
    public String getItemFrom2_txt() {
        return itemFrom2_txt;
    }

    /**
         * itemFrom2_txt設定<br>
         * <br>
         * itemFrom2_txtを引数で設定する<br>
         * <br>
         * @param itemFrom2_txt
         */
    public void setItemFrom2_txt(String itemFrom2_txt) {
        this.itemFrom2_txt = itemFrom2_txt;
    }

    /**
         * itemFrom3_txt取得<br>
         * <br>
         * itemFrom3_txtを戻り値で返却する<br>
         * <br>
         * @return itemFrom3_txt
         */
    public String getItemFrom3_txt() {
        return itemFrom3_txt;
    }

    /**
         * itemFrom3_txt設定<br>
         * <br>
         * itemFrom3_txtを引数で設定する<br>
         * <br>
         * @param itemFrom3_txt
         */
    public void setItemFrom3_txt(String itemFrom3_txt) {
        this.itemFrom3_txt = itemFrom3_txt;
    }

    /**
         * itemFrom4_txt取得<br>
         * <br>
         * itemFrom4_txtを戻り値で返却する<br>
         * <br>
         * @return itemFrom4_txt
         */
    public String getItemFrom4_txt() {
        return itemFrom4_txt;
    }

    /**
         * itemFrom4_txt設定<br>
         * <br>
         * itemFrom4_txtを引数で設定する<br>
         * <br>
         * @param itemFrom4_txt
         */
    public void setItemFrom4_txt(String itemFrom4_txt) {
        this.itemFrom4_txt = itemFrom4_txt;
    }

    /**
         * itemFrom5_txt取得<br>
         * <br>
         * itemFrom5_txtを戻り値で返却する<br>
         * <br>
         * @return itemFrom5_txt
         */
    public String getItemFrom5_txt() {
        return itemFrom5_txt;
    }

    /**
         * itemFrom5_txt設定<br>
         * <br>
         * itemFrom5_txtを引数で設定する<br>
         * <br>
         * @param itemFrom5_txt
         */
    public void setItemFrom5_txt(String itemFrom5_txt) {
        this.itemFrom5_txt = itemFrom5_txt;
    }

    /**
         * itemTo1_txt取得<br>
         * <br>
         * itemTo1_txtを戻り値で返却する<br>
         * <br>
         * @return itemTo1_txt
         */
    public String getItemTo1_txt() {
        return itemTo1_txt;
    }

    /**
         * itemTo1_txt設定<br>
         * <br>
         * itemTo1_txtを引数で設定する<br>
         * <br>
         * @param itemTo1_txt
         */
    public void setItemTo1_txt(String itemTo1_txt) {
        this.itemTo1_txt = itemTo1_txt;
    }

    /**
         * itemTo2_txt取得<br>
         * <br>
         * itemTo2_txtを戻り値で返却する<br>
         * <br>
         * @return itemTo2_txt
         */
    public String getItemTo2_txt() {
        return itemTo2_txt;
    }

    /**
         * itemTo2_txt設定<br>
         * <br>
         * itemTo2_txtを引数で設定する<br>
         * <br>
         * @param itemTo2_txt
         */
    public void setItemTo2_txt(String itemTo2_txt) {
        this.itemTo2_txt = itemTo2_txt;
    }

    /**
         * itemTo3_txt取得<br>
         * <br>
         * itemTo3_txtを戻り値で返却する<br>
         * <br>
         * @return itemTo3_txt
         */
    public String getItemTo3_txt() {
        return itemTo3_txt;
    }

    /**
         * itemTo3_txt設定<br>
         * <br>
         * itemTo3_txtを引数で設定する<br>
         * <br>
         * @param itemTo3_txt
         */
    public void setItemTo3_txt(String itemTo3_txt) {
        this.itemTo3_txt = itemTo3_txt;
    }

    /**
         * itemTo4_txt取得<br>
         * <br>
         * itemTo4_txtを戻り値で返却する<br>
         * <br>
         * @return itemTo4_txt
         */
    public String getItemTo4_txt() {
        return itemTo4_txt;
    }

    /**
         * itemTo4_txt設定<br>
         * <br>
         * itemTo4_txtを引数で設定する<br>
         * <br>
         * @param itemTo4_txt
         */
    public void setItemTo4_txt(String itemTo4_txt) {
        this.itemTo4_txt = itemTo4_txt;
    }

    /**
         * itemTo5_txt取得<br>
         * <br>
         * itemTo5_txtを戻り値で返却する<br>
         * <br>
         * @return itemTo5_txt
         */
    public String getItemTo5_txt() {
        return itemTo5_txt;
    }

    /**
         * itemTo5_txt設定<br>
         * <br>
         * itemTo5_txtを引数で設定する<br>
         * <br>
         * @param itemTo5_txt
         */
    public void setItemTo5_txt(String itemTo5_txt) {
        this.itemTo5_txt = itemTo5_txt;
    }

}
