package com.naikara_talk.dto;

import java.sql.Timestamp;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称:</b>共通部品 DTOクラス<br>
 * <b>クラス名称　　　:</b>利用者マスタクラス<br>
 * <b>クラス概要　　　:</b>利用者マスタDTO<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/06/03 TECS 新規作成。
 */
public class UserMstDto extends AbstractDto {

    private String userId;              // 利用者ID
    private String password;            // パスワード
    private String familyJnm;           // 名前(姓)
    private String firstJnm;            // 名前(名)
    private String familyKnm;           // フリガナ(姓)
    private String firstKnm;            // フリガナ(名）
    private String familyRomaji;        // ローマ字(姓)
    private String firstRomaji;         // ローマ字(名)
    private String tel1;                // 電話番号1
    private String tel2;                // 電話番号2
    private String birthYyyy;           // 生年月日：年
    private String birthMm;             // 生年月日：月
    private String birthDd;             // 生年月日：日
    private String zipCd;               // 郵便番号
    private String addressAreaCd;       // 住所(地域)コード
    private String addressPrefectureCd; // 住所(都道府県)コード
    private String addressCity;         // 住所(市区町村)
    private String addressOthers;       // 住所(ﾋﾞﾙ、ﾏﾝｼｮﾝ名 等)
    private String genderKbn;           // 性別区分
    private String mailAddress1;        // メールアドレス1
    private String mailAddress2;        // メールアドレス2
    private String mailAddress3;        // メールアドレス3
    private String useStartDt;          // 利用開始日
    private String useEndDt;            // 利用終了日
    private String cityTown;            // 勤務拠点
    private String classificationKbn;   // 種別区分
    private String remark;              // 備考
    private int recordVerNo;            // レコードバージョン番号
    private Timestamp insertDttm;       // 登録日時
    private String insertCd;            // 登録者コード
    private Timestamp updateDttm;       // 更新日時
    private String updateCd;            // 更新者コード
    private int returnCode;             // リターンコード

    // 画面処理必要項目
    private String riyouKbn;            // 利用状態
    private String userKnm;             // 利用者名(フリガナ)(姓)と(名）
    private String userNm;              // 利用者名(フリガナ)
    private String authority;           // 変換後種別

    /**
     * 利用状態取得<br>
     * <br>
     * 利用状態取得を行う<br>
     * <br>
     * @param なし<br>
     * @return riyouKbn 利用状態<br>
     * @exception なし
     */
    public String getRiyouKbn() {
        return riyouKbn;
    }

    /**
     * 利用状態設定<br>
     * <br>
     * 利用状態設定を行う<br>
     * <br>
     * @param riyouKbn 利用状態<br>
     * @return なし<br>
     * @exception なし
     */
    public void setRiyouKbn(String riyouKbn) {
        this.riyouKbn = riyouKbn;
    }

    /**
     * 変換後種別取得<br>
     * <br>
     * 変換後種別取得を行う<br>
     * <br>
     * @param なし<br>
     * @return authority 変換後種別<br>
     * @exception なし
     */
    public String getAuthority() {
        return authority;
    }

    /**
     * 変換後種別設定<br>
     * <br>
     * 変換後種別設定を行う<br>
     * <br>
     * @param authority 変換後種別<br>
     * @return なし<br>
     * @exception なし
     */
    public void setAuthority(String authority) {
        this.authority = authority;
    }

    /**
     * 利用者名(フリガナ)取得<br>
     * <br>
     * 利用者名(フリガナ)取得を行う<br>
     * <br>
     * @param なし<br>
     * @return userNm 利用者名(フリガナ)<br>
     * @exception なし
     */
    public String getUserNm() {
        return userNm;
    }

    /**
     * 利用者名(フリガナ)設定<br>
     * <br>
     * 利用者名(フリガナ)設定を行う<br>
     * <br>
     * @param userNm 利用者名(フリガナ)<br>
     * @return なし<br>
     * @exception なし
     */
    public void setUserNm(String userNm) {
        this.userNm = userNm;
    }

    /**
     * 利用者名(フリガナ)(姓)と(名）取得<br>
     * <br>
     * 利用者名(フリガナ)(姓)と(名）取得を行う<br>
     * <br>
     * @param なし<br>
     * @return userKnm 利用者名(フリガナ)(姓)と(名）<br>
     * @exception なし
     */
    public String getUserKnm() {
        return userKnm;
    }

    /**
     * 利用者名(フリガナ)(姓)と(名）設定<br>
     * <br>
     * 利用者名(フリガナ)(姓)と(名）設定を行う<br>
     * <br>
     * @param userKnm 利用者名(フリガナ)(姓)と(名）<br>
     * @return なし<br>
     * @exception なし
     */
    public void setUserKnm(String userKnm) {
        this.userKnm = userKnm;
    }

    /**
     * 利用者ID取得<br>
     * <br>
     * 利用者IDを戻り値で返却する<br>
     * <br>
     * @param なし<br>
     * @return userId 利用者ID<br>
     * @exception なし
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 利用者ID設定<br>
     * <br>
     * 利用者IDを引数で設定する<br>
     * <br>
     * @param userId 利用者ID<br>
     * @return なし<br>
     * @exception なし
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * パスワード取得<br>
     * <br>
     * パスワードを戻り値で返却する<br>
     * <br>
     * @param なし<br>
     * @return password パスワード<br>
     * @exception なし
     */
    public String getPassword() {
        return password;
    }

    /**
     * パスワード設定<br>
     * <br>
     * パスワードを引数で設定する<br>
     * <br>
     * @param password パスワード<br>
     * @return なし<br>
     * @exception なし
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 名前(姓)取得<br>
     * <br>
     * 名前(姓)を戻り値で返却する<br>
     * <br>
     * @param なし<br>
     * @return familyJnm 名前(姓)<br>
     * @exception なし
     */
    public String getFamilyJnm() {
        return familyJnm;
    }

    /**
     * 名前(姓)設定<br>
     * <br>
     * 名前(姓)を引数で設定する<br>
     * <br>
     * @param familyJnm 名前(姓)<br>
     * @return なし<br>
     * @exception なし
     */
    public void setFamilyJnm(String familyJnm) {
        this.familyJnm = familyJnm;
    }

    /**
     * 名前(名)取得<br>
     * <br>
     * 名前(名)を戻り値で返却する<br>
     * <br>
     * @param なし<br>
     * @return firstJnm 名前(名)<br>
     * @exception なし
     */
    public String getFirstJnm() {
        return firstJnm;
    }

    /**
     * 名前(名)設定<br>
     * <br>
     * 名前(名)を引数で設定する<br>
     * <br>
     * @param firstJnm 名前(名)<br>
     * @return なし<br>
     * @exception なし
     */
    public void setFirstJnm(String firstJnm) {
        this.firstJnm = firstJnm;
    }

    /**
     * フリガナ(姓)取得<br>
     * <br>
     * フリガナ(姓)を戻り値で返却する<br>
     * <br>
     * @param なし<br>
     * @return familyKnm フリガナ(姓)<br>
     * @exception なし
     */
    public String getFamilyKnm() {
        return familyKnm;
    }

    /**
     * フリガナ(姓)設定<br>
     * <br>
     * フリガナ(姓)を引数で設定する<br>
     * <br>
     * @param familyKnm フリガナ(姓)<br>
     * @return なし<br>
     * @exception なし
     */
    public void setFamilyKnm(String familyKnm) {
        this.familyKnm = familyKnm;
    }

    /**
     * フリガナ(名）取得<br>
     * <br>
     * フリガナ(名）を戻り値で返却する<br>
     * <br>
     * @param なし<br>
     * @return firstKnm フリガナ(名）<br>
     * @exception なし
     */
    public String getFirstKnm() {
        return firstKnm;
    }

    /**
     * フリガナ(名）設定<br>
     * <br>
     * フリガナ(名）を引数で設定する<br>
     * <br>
     * @param firstKnm フリガナ(名）<br>
     * @return なし<br>
     * @exception なし
     */
    public void setFirstKnm(String firstKnm) {
        this.firstKnm = firstKnm;
    }

    /**
     * ローマ字(姓)取得<br>
     * <br>
     * ローマ字(姓)を戻り値で返却する<br>
     * <br>
     * @param なし<br>
     * @return familyRomaji ローマ字(姓)<br>
     * @exception なし
     */
    public String getFamilyRomaji() {
        return familyRomaji;
    }

    /**
     * ローマ字(姓)設定<br>
     * <br>
     * ローマ字(姓)を引数で設定する<br>
     * <br>
     * @param familyRomaji ローマ字(姓)<br>
     * @return なし<br>
     * @exception なし
     */
    public void setFamilyRomaji(String familyRomaji) {
        this.familyRomaji = familyRomaji;
    }

    /**
     * ローマ字(名)取得<br>
     * <br>
     * ローマ字(名)を戻り値で返却する<br>
     * <br>
     * @param なし<br>
     * @return firstRomaji ローマ字(名)<br>
     * @exception なし
     */
    public String getFirstRomaji() {
        return firstRomaji;
    }

    /**
     * ローマ字(名)設定<br>
     * <br>
     * ローマ字(名)を引数で設定する<br>
     * <br>
     * @param firstRomaji ローマ字(名)<br>
     * @return なし<br>
     * @exception なし
     */
    public void setFirstRomaji(String firstRomaji) {
        this.firstRomaji = firstRomaji;
    }

    /**
     * 電話番号1取得<br>
     * <br>
     * 電話番号1を戻り値で返却する<br>
     * <br>
     * @param なし<br>
     * @return tel1 電話番号1<br>
     * @exception なし
     */
    public String getTel1() {
        return tel1;
    }

    /**
     * 電話番号1設定<br>
     * <br>
     * 電話番号1を引数で設定する<br>
     * <br>
     * @param tel1 電話番号1<br>
     * @return なし<br>
     * @exception なし
     */
    public void setTel1(String tel1) {
        this.tel1 = tel1;
    }

    /**
     * 電話番号2取得<br>
     * <br>
     * 電話番号2を戻り値で返却する<br>
     * <br>
     * @param なし<br>
     * @return tel2 電話番号2<br>
     * @exception なし
     */
    public String getTel2() {
        return tel2;
    }

    /**
     * 電話番号2設定<br>
     * <br>
     * 電話番号2を引数で設定する<br>
     * <br>
     * @param tel2 電話番号2<br>
     * @return なし<br>
     * @exception なし
     */
    public void setTel2(String tel2) {
        this.tel2 = tel2;
    }

    /**
     * 生年月日：年取得<br>
     * <br>
     * 生年月日：年を戻り値で返却する<br>
     * <br>
     * @param なし<br>
     * @return birthYyyy 生年月日：年<br>
     * @exception なし
     */
    public String getBirthYyyy() {
        return birthYyyy;
    }

    /**
     * 生年月日：年設定<br>
     * <br>
     * 生年月日：年を引数で設定する<br>
     * <br>
     * @param birthYyyy 生年月日：年<br>
     * @return なし<br>
     * @exception なし
     */
    public void setBirthYyyy(String birthYyyy) {
        this.birthYyyy = birthYyyy;
    }

    /**
     * 生年月日：月取得<br>
     * <br>
     * 生年月日：月を戻り値で返却する<br>
     * <br>
     * @param なし<br>
     * @return birthMm 生年月日：月<br>
     * @exception なし
     */
    public String getBirthMm() {
        return birthMm;
    }

    /**
     * 生年月日：月設定<br>
     * <br>
     * 生年月日：月を引数で設定する<br>
     * <br>
     * @param birthMm 生年月日：月<br>
     * @return なし<br>
     * @exception なし
     */
    public void setBirthMm(String birthMm) {
        this.birthMm = birthMm;
    }

    /**
     * 生年月日：日取得<br>
     * <br>
     * 生年月日：日を戻り値で返却する<br>
     * <br>
     * @param なし<br>
     * @return birthDd 生年月日：日<br>
     * @exception なし
     */
    public String getBirthDd() {
        return birthDd;
    }

    /**
     * 生年月日：日設定<br>
     * <br>
     * 生年月日：日を引数で設定する<br>
     * <br>
     * @param birthDd 生年月日：日<br>
     * @return なし<br>
     * @exception なし
     */
    public void setBirthDd(String birthDd) {
        this.birthDd = birthDd;
    }

    /**
     * 郵便番号取得<br>
     * <br>
     * 郵便番号を戻り値で返却する<br>
     * <br>
     * @param なし<br>
     * @return zipCd 郵便番号<br>
     * @exception なし
     */
    public String getZipCd() {
        return zipCd;
    }

    /**
     * 郵便番号設定<br>
     * <br>
     * 郵便番号を引数で設定する<br>
     * <br>
     * @param zipCd 郵便番号<br>
     * @return なし<br>
     * @exception なし
     */
    public void setZipCd(String zipCd) {
        this.zipCd = zipCd;
    }

    /**
     * 住所(地域)コード取得<br>
     * <br>
     * 住所(地域)コードを戻り値で返却する<br>
     * <br>
     * @param なし<br>
     * @return addressAreaCd 住所(地域)コード<br>
     * @exception なし
     */
    public String getAddressAreaCd() {
        return addressAreaCd;
    }

    /**
     * 住所(地域)コード設定<br>
     * <br>
     * 住所(地域)コードを引数で設定する<br>
     * <br>
     * @param addressAreaCd 住所(地域)コード<br>
     * @return なし<br>
     * @exception なし
     */
    public void setAddressAreaCd(String addressAreaCd) {
        this.addressAreaCd = addressAreaCd;
    }

    /**
     * 住所(都道府県)コード取得<br>
     * <br>
     * 住所(都道府県)コードを戻り値で返却する<br>
     * <br>
     * @param なし<br>
     * @return addressPrefectureCd 住所(都道府県)コード<br>
     * @exception なし
     */
    public String getAddressPrefectureCd() {
        return addressPrefectureCd;
    }

    /**
     * 住所(都道府県)コード設定<br>
     * <br>
     * 住所(都道府県)コードを引数で設定する<br>
     * <br>
     * @param addressPrefectureCd 住所(都道府県)コード<br>
     * @return なし<br>
     * @exception なし
     */
    public void setAddressPrefectureCd(String addressPrefectureCd) {
        this.addressPrefectureCd = addressPrefectureCd;
    }

    /**
     * 住所(市区町村)取得<br>
     * <br>
     * 住所(市区町村)を戻り値で返却する<br>
     * <br>
     * @param なし<br>
     * @return addressCity 住所(市区町村)<br>
     * @exception なし
     */
    public String getAddressCity() {
        return addressCity;
    }

    /**
     * 住所(市区町村)設定<br>
     * <br>
     * 住所(市区町村)を引数で設定する<br>
     * <br>
     * @param addressCity 住所(市区町村)<br>
     * @return なし<br>
     * @exception なし
     */
    public void setAddressCity(String addressCity) {
        this.addressCity = addressCity;
    }

    /**
     * 住所(ﾋﾞﾙ、ﾏﾝｼｮﾝ名 等)取得<br>
     * <br>
     * 住所(ﾋﾞﾙ、ﾏﾝｼｮﾝ名 等)を戻り値で返却する<br>
     * <br>
     * @param なし<br>
     * @return addressOthers 住所(ﾋﾞﾙ、ﾏﾝｼｮﾝ名 等)<br>
     * @exception なし
     */
    public String getAddressOthers() {
        return addressOthers;
    }

    /**
     * 住所(ﾋﾞﾙ、ﾏﾝｼｮﾝ名 等)設定<br>
     * <br>
     * 住所(ﾋﾞﾙ、ﾏﾝｼｮﾝ名 等)を引数で設定する<br>
     * <br>
     * @param addressOthers 住所(ﾋﾞﾙ、ﾏﾝｼｮﾝ名 等)<br>
     * @return なし<br>
     * @exception なし
     */
    public void setAddressOthers(String addressOthers) {
        this.addressOthers = addressOthers;
    }

    /**
     * 性別区分取得<br>
     * <br>
     * 性別区分を戻り値で返却する<br>
     * <br>
     * @param なし<br>
     * @return genderKbn 性別区分<br>
     * @exception なし
     */
    public String getGenderKbn() {
        return genderKbn;
    }

    /**
     * 性別区分設定<br>
     * <br>
     * 性別区分を引数で設定する<br>
     * <br>
     * @param genderKbn 性別区分<br>
     * @return なし<br>
     * @exception なし
     */
    public void setGenderKbn(String genderKbn) {
        this.genderKbn = genderKbn;
    }

    /**
     * メールアドレス1取得<br>
     * <br>
     * メールアドレス1を戻り値で返却する<br>
     * <br>
     * @param なし<br>
     * @return mailAddress1 メールアドレス1<br>
     * @exception なし
     */
    public String getMailAddress1() {
        return mailAddress1;
    }

    /**
     * メールアドレス1設定<br>
     * <br>
     * メールアドレス1を引数で設定する<br>
     * <br>
     * @param mailAddress1 メールアドレス1<br>
     * @return なし<br>
     * @exception なし
     */
    public void setMailAddress1(String mailAddress1) {
        this.mailAddress1 = mailAddress1;
    }

    /**
     * メールアドレス2取得<br>
     * <br>
     * メールアドレス2を戻り値で返却する<br>
     * <br>
     * @param なし<br>
     * @return mailAddress2 メールアドレス2<br>
     * @exception なし
     */
    public String getMailAddress2() {
        return mailAddress2;
    }

    /**
     * メールアドレス2設定<br>
     * <br>
     * メールアドレス2を引数で設定する<br>
     * <br>
     * @param mailAddress2 メールアドレス2<br>
     * @return なし<br>
     * @exception なし
     */
    public void setMailAddress2(String mailAddress2) {
        this.mailAddress2 = mailAddress2;
    }

    /**
     * メールアドレス3取得<br>
     * <br>
     * メールアドレス3を戻り値で返却する<br>
     * <br>
     * @param なし<br>
     * @return mailAddress3 メールアドレス3<br>
     * @exception なし
     */
    public String getMailAddress3() {
        return mailAddress3;
    }

    /**
     * メールアドレス3設定<br>
     * <br>
     * メールアドレス3を引数で設定する<br>
     * <br>
     * @param mailAddress3 メールアドレス3<br>
     * @return なし<br>
     * @exception なし
     */
    public void setMailAddress3(String mailAddress3) {
        this.mailAddress3 = mailAddress3;
    }

    /**
     * 利用開始日取得<br>
     * <br>
     * 利用開始日を戻り値で返却する<br>
     * <br>
     * @param なし<br>
     * @return useStartDt 利用開始日<br>
     * @exception なし
     */
    public String getUseStartDt() {
        return useStartDt;
    }

    /**
     * 利用開始日設定<br>
     * <br>
     * 利用開始日を引数で設定する<br>
     * <br>
     * @param useStartDt 利用開始日<br>
     * @return なし<br>
     * @exception なし
     */
    public void setUseStartDt(String useStartDt) {
        this.useStartDt = useStartDt;
    }

    /**
     * 利用終了日取得<br>
     * <br>
     * 利用終了日を戻り値で返却する<br>
     * <br>
     * @param なし<br>
     * @return useEndDt 利用終了日<br>
     * @exception なし
     */
    public String getUseEndDt() {
        return useEndDt;
    }

    /**
     * 利用終了日設定<br>
     * <br>
     * 利用終了日を引数で設定する<br>
     * <br>
     * @param useEndDt 利用終了日<br>
     * @return なし<br>
     * @exception なし
     */
    public void setUseEndDt(String useEndDt) {
        this.useEndDt = useEndDt;
    }

    /**
     * 勤務拠点取得<br>
     * <br>
     * 勤務拠点を戻り値で返却する<br>
     * <br>
     * @param なし<br>
     * @return cityTown 勤務拠点<br>
     * @exception なし
     */
    public String getCityTown() {
        return cityTown;
    }

    /**
     * 勤務拠点設定<br>
     * <br>
     * 勤務拠点を引数で設定する<br>
     * <br>
     * @param cityTown 勤務拠点<br>
     * @return なし<br>
     * @exception なし
     */
    public void setCityTown(String cityTown) {
        this.cityTown = cityTown;
    }

    /**
     * 種別区分取得<br>
     * <br>
     * 種別区分を戻り値で返却する<br>
     * <br>
     * @param なし<br>
     * @return classificationKbn 種別区分<br>
     * @exception なし
     */
    public String getClassificationKbn() {
        return classificationKbn;
    }

    /**
     * 種別区分設定<br>
     * <br>
     * 種別区分を引数で設定する<br>
     * <br>
     * @param classificationKbn 種別区分<br>
     * @return なし<br>
     * @exception なし
     */
    public void setClassificationKbn(String classificationKbn) {
        this.classificationKbn = classificationKbn;
    }

    /**
     * 備考取得<br>
     * <br>
     * 備考を戻り値で返却する<br>
     * <br>
     * @param なし<br>
     * @return remark 備考<br>
     * @exception なし
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 備考設定<br>
     * <br>
     * 備考を引数で設定する<br>
     * <br>
     * @param remark 備考<br>
     * @return なし<br>
     * @exception なし
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * レコードバージョン番号取得<br>
     * <br>
     * レコードバージョン番号を戻り値で返却する<br>
     * <br>
     * @param なし<br>
     * @return recordVerNo レコードバージョン番号<br>
     * @exception なし
     */
    public int getRecordVerNo() {
        return recordVerNo;
    }

    /**
     * レコードバージョン番号設定<br>
     * <br>
     * レコードバージョン番号を引数で設定する<br>
     * <br>
     * @param recordVerNo レコードバージョン番号<br>
     * @return なし<br>
     * @exception なし
     */
    public void setRecordVerNo(int recordVerNo) {
        this.recordVerNo = recordVerNo;
    }

    /**
     * 登録日時取得<br>
     * <br>
     * 登録日時を戻り値で返却する<br>
     * <br>
     * @param なし<br>
     * @return insertDttm 登録日時<br>
     * @exception なし
     */
    public Timestamp getInsertDttm() {
        return insertDttm;
    }

    /**
     * 登録日時設定<br>
     * <br>
     * 登録日時を引数で設定する<br>
     * <br>
     * @param insertDttm 登録日時<br>
     * @return なし<br>
     * @exception なし
     */
    public void setInsertDttm(Timestamp insertDttm) {
        this.insertDttm = insertDttm;
    }

    /**
     * 登録者コード取得<br>
     * <br>
     * 登録者コードを戻り値で返却する<br>
     * <br>
     * @param なし<br>
     * @return insertCd 登録者コード<br>
     * @exception なし
     */
    public String getInsertCd() {
        return insertCd;
    }

    /**
     * 登録者コード設定<br>
     * <br>
     * 登録者コードを引数で設定する<br>
     * <br>
     * @param insertCd 登録者コード<br>
     * @return なし<br>
     * @exception なし
     */
    public void setInsertCd(String insertCd) {
        this.insertCd = insertCd;
    }

    /**
     * 更新日時取得<br>
     * <br>
     * 更新日時を戻り値で返却する<br>
     * <br>
     * @param なし<br>
     * @return updateDttm 更新日時<br>
     * @exception なし
     */
    public Timestamp getUpdateDttm() {
        return updateDttm;
    }

    /**
     * 更新日時設定<br>
     * <br>
     * 更新日時を引数で設定する<br>
     * <br>
     * @param updateDttm 更新日時<br>
     * @return なし<br>
     * @exception なし
     */
    public void setUpdateDttm(Timestamp updateDttm) {
        this.updateDttm = updateDttm;
    }

    /**
     * 更新者コード取得<br>
     * <br>
     * 更新者コードを戻り値で返却する<br>
     * <br>
     * @param なし<br>
     * @return updateCd 更新者コード<br>
     * @exception なし
     */
    public String getUpdateCd() {
        return updateCd;
    }

    /**
     * 更新者コード設定<br>
     * <br>
     * 更新者コードを引数で設定する<br>
     * <br>
     * @param updateCd 更新者コード<br>
     * @return なし<br>
     * @exception なし
     */
    public void setUpdateCd(String updateCd) {
        this.updateCd = updateCd;
    }

    /**
     * リターンコード取得<br>
     * <br>
     * リターンコードを戻り値で返却する<br>
     * <br>
     * @param なし<br>
     * @return returnCode リターンコード<br>
     * @exception なし
     */
    public int getReturnCode() {
        return returnCode;
    }

    /**
     * リターンコード設定<br>
     * <br>
     * リターンコードを引数で設定する<br>
     * <br>
     * @param returnCode リターンコード<br>
     * @return なし<br>
     * @exception なし
     */
    public void setReturnCode(int returnCode) {
        this.returnCode = returnCode;
    }
}