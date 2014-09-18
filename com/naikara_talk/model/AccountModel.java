package com.naikara_talk.model;


/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>お客様_初期処理。<br>
 * <b>クラス名称　　　:</b>アカウント取得処理Modelクラス。<br>
 * <b>クラス概要　　　:</b>アカウント取得処理Model。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/08/08 TECS 新規作成。
 */
public class AccountModel implements Model {

    private static final long serialVersionUID = 1L;

    /** 受講者ID */
    private String studentId_lbl;
    /** 受講者所属部署 */
    private String studentPosi_txt;
    /** パスワード */
    private String password_txt;
    /** パスワード確認 */
    private String passwordConf_txt;
    /** お名前(姓) */
    private String familyJnm_txt;
    /** お名前(名) */
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
    /** 電話番号 */
    private String tel1_txt;
    /** 携帯電話 */
    private String tel2_txt;
    /** 生年月日年 */
    private String birthYy_txt;
    /** 生年月日月 */
    private String birthMm_txt;
    /** 生年月日日 */
    private String birthDd_txt;
    /** 郵便番号 */
    private String zipCd_txt;
    /** 住所(地域) */
    private String area_sel;
    /** 住所(都道府県) */
    private String prefecture_sel;
    /** 住所(市区町村 等) */
    private String city_txt;
    /** 住所(ビル、マンション名) */
    private String other_txt;
    /** 性別 */
    private String gender_rdl;
    /** メールアドレス1 */
    private String mailAdd1_txt;
    /** メールアドレス2 */
    private String mailAdd2_txt;
    /** メールアドレス3 */
    private String mailAdd3_txt;
    /** ご職業 */
    private String occupa_sel;
    /** ご利用規約に同意する */
    private boolean useAgreeFlg_chk;
    /** ご利用の動機1 */
    private boolean motiveFlg1_chk;
    /** ご利用の動機2 */
    private boolean motiveFlg2_chk;
    /** ご利用の動機3 */
    private boolean motiveFlg3_chk;
    /** ご利用の動機4 */
//    private boolean motiveFlg4_chk;
    /** ご利用の動機5 */
    private boolean motiveFlg5_chk;
    /** ご利用の動機1 */
    private String motiveFlg1_lbl;
    /** ご利用の動機2 */
    private String motiveFlg2_lbl;
    /** ご利用の動機3 */
    private String motiveFlg3_lbl;
    /** ご利用の動機4 */
//    private String motiveFlg4_lbl;
    /** ご利用の動機5 */
    private String motiveFlg5_lbl;
    /** ご利用の動機備考 */
    private String motive_txt;
    /** 達成したい目標があればご記入ください */
    private String achieve_txt;
    /** 個人情報の同意 */
    private boolean indivAgreeFlg_chk;
    /** 保護者お名前(姓) */
    private String guardFamilyJnm_txt;
    /** 保護者お名前(名) */
    private String guardFirstJnm_txt;
    /** 保護者フリガナ(姓) */
    private String guardFamilyKnm_txt;
    /** 保護者フリガナ(名) */
    private String guardFirstKnm_txt;
    /** あなたとの続柄 */
    private String guardRelat_txt;
    /** 保護者電話番号 */
    private String guardTel1_txt;
    /** 保護者携帯電話 */
    private String guardTel2_txt;
    /** 保護者生年月日年 */
    private String guardBirthYy_txt;
    /** 保護者生年月日月 */
    private String guardBirthMm_txt;
    /** 保護者生年月日日 */
    private String guardBirthDd_txt;
    /** 保護者郵便番号 */
    private String guardZipCd_txt;
    /** 保護者住所(地域) */
    private String guardArea_sel;
    /** 保護者住所(都道府県) */
    private String guardprefecture_sel;
    /** 保護者住所(市区町村 等) */
    private String guardCity_txt;
    /** 保護者住所(ビル、マンション名 等) */
    private String guardOther_txt;
    /** 保護者性別 */
    private String guardGender_rdl;
    /** 保護者メールアドレス1 */
    private String guardMailAdd1_txt;
    /** 保護者メールアドレス2 */
    private String guardMailAdd2_txt;
    /** 保護者メールアドレス3 */
    private String guardMailAdd3_txt;
    /** 顧客区分 */
    private String customerKbn;
    /** 組織名 */
    private String organizationNm;
    /** 組織ID */
    private String organizationId;
    /** 所属組織内ID */
    private String positionOrganizationId;
    /** 自己負担率 */
    private int burdenNum;
    /** ログイン回数 */
    private int loginNum;
    /** 最終ログイン日 */
    private String endLoginDt;
    /** ｽｸｰﾙからのｺﾒﾝﾄ(生徒公開) */
    private String schoolCmt;
    /** 備考(生徒非公開) */
    private String remark;
    /** 排他用レコードバージョン */
    private int recordVerNo;

    /**
     * 受講者ID取得<br>
     * <br>
     * 受講者ID取得を行う<br>
     * <br>
     * @param なし<br>
     * @return studentId_lbl 受講者ID<br>
     * @exception なし
     */
    public String getStudentId_lbl() {
        return studentId_lbl;
    }

    /**
     * 受講者所属部署取得<br>
     * <br>
     * 受講者所属部署取得を行う<br>
     * <br>
     * @param なし<br>
     * @return studentPosi_txt 受講者所属部署<br>
     * @exception なし
     */
    public String getStudentPosi_txt() {
        return studentPosi_txt;
    }

    /**
     * パスワード取得<br>
     * <br>
     * パスワード取得を行う<br>
     * <br>
     * @param なし<br>
     * @return password_txt パスワード<br>
     * @exception なし
     */
    public String getPassword_txt() {
        return password_txt;
    }

    /**
     * パスワード確認取得<br>
     * <br>
     * パスワード確認取得を行う<br>
     * <br>
     * @param なし<br>
     * @return passwordConf_txt パスワード確認<br>
     * @exception なし
     */
    public String getPasswordConf_txt() {
        return passwordConf_txt;
    }

    /**
     * お名前(姓)取得<br>
     * <br>
     * お名前(姓)取得を行う<br>
     * <br>
     * @param なし<br>
     * @return familyJnm_txt お名前(姓)<br>
     * @exception なし
     */
    public String getFamilyJnm_txt() {
        return familyJnm_txt;
    }

    /**
     * お名前(名)取得<br>
     * <br>
     * お名前(名)取得を行う<br>
     * <br>
     * @param なし<br>
     * @return firstJnm_txt お名前(名)<br>
     * @exception なし
     */
    public String getFirstJnm_txt() {
        return firstJnm_txt;
    }

    /**
     * フリガナ(姓)取得<br>
     * <br>
     * フリガナ(姓)取得を行う<br>
     * <br>
     * @param なし<br>
     * @return familyKnm_txt フリガナ(姓)<br>
     * @exception なし
     */
    public String getFamilyKnm_txt() {
        return familyKnm_txt;
    }

    /**
     * フリガナ(名)取得<br>
     * <br>
     * フリガナ(名)取得を行う<br>
     * <br>
     * @param なし<br>
     * @return firstKnm_txt フリガナ(名)<br>
     * @exception なし
     */
    public String getFirstKnm_txt() {
        return firstKnm_txt;
    }

    /**
     * ローマ字(姓)取得<br>
     * <br>
     * ローマ字(姓)取得を行う<br>
     * <br>
     * @param なし<br>
     * @return familyRomaji_txt ローマ字(姓)<br>
     * @exception なし
     */
    public String getFamilyRomaji_txt() {
        return familyRomaji_txt;
    }

    /**
     * ローマ字(名)取得<br>
     * <br>
     * ローマ字(名)取得を行う<br>
     * <br>
     * @param なし<br>
     * @return firstRomaji_txt ローマ字(名)<br>
     * @exception なし
     */
    public String getFirstRomaji_txt() {
        return firstRomaji_txt;
    }

    /**
     * ニックネーム取得<br>
     * <br>
     * ニックネーム取得を行う<br>
     * <br>
     * @param なし<br>
     * @return nickNm_txt ニックネーム<br>
     * @exception なし
     */
    public String getNickNm_txt() {
        return nickNm_txt;
    }

    /**
     * 電話番号取得<br>
     * <br>
     * 電話番号取得を行う<br>
     * <br>
     * @param なし<br>
     * @return tel1_txt 電話番号<br>
     * @exception なし
     */
    public String getTel1_txt() {
        return tel1_txt;
    }

    /**
     * 携帯電話取得<br>
     * <br>
     * 携帯電話取得を行う<br>
     * <br>
     * @param なし<br>
     * @return tel2_txt 携帯電話<br>
     * @exception なし
     */
    public String getTel2_txt() {
        return tel2_txt;
    }

    /**
     * 生年月日年取得<br>
     * <br>
     * 生年月日年取得を行う<br>
     * <br>
     * @param なし<br>
     * @return birthYy_txt 生年月日年<br>
     * @exception なし
     */
    public String getBirthYy_txt() {
        return birthYy_txt;
    }

    /**
     * 生年月日月取得<br>
     * <br>
     * 生年月日月取得を行う<br>
     * <br>
     * @param なし<br>
     * @return birthMm_txt 生年月日月<br>
     * @exception なし
     */
    public String getBirthMm_txt() {
        return birthMm_txt;
    }

    /**
     * 生年月日日取得<br>
     * <br>
     * 生年月日日取得を行う<br>
     * <br>
     * @param なし<br>
     * @return birthDd_txt 生年月日日<br>
     * @exception なし
     */
    public String getBirthDd_txt() {
        return birthDd_txt;
    }

    /**
     * 郵便番号取得<br>
     * <br>
     * 郵便番号取得を行う<br>
     * <br>
     * @param なし<br>
     * @return zipCd_txt 郵便番号<br>
     * @exception なし
     */
    public String getZipCd_txt() {
        return zipCd_txt;
    }

    /**
     * 住所(地域)取得<br>
     * <br>
     * 住所(地域)取得を行う<br>
     * <br>
     * @param なし<br>
     * @return area_sel 住所(地域)<br>
     * @exception なし
     */
    public String getArea_sel() {
        return area_sel;
    }

    /**
     * 住所(都道府県)取得<br>
     * <br>
     * 住所(都道府県)取得を行う<br>
     * <br>
     * @param なし<br>
     * @return prefecture_sel 住所(都道府県)<br>
     * @exception なし
     */
    public String getPrefecture_sel() {
        return prefecture_sel;
    }

    /**
     * 住所(市区町村 等)取得<br>
     * <br>
     * 住所(市区町村 等)取得を行う<br>
     * <br>
     * @param なし<br>
     * @return city_txt 住所(市区町村 等)<br>
     * @exception なし
     */
    public String getCity_txt() {
        return city_txt;
    }

    /**
     * 住所(ビル、マンション名)取得<br>
     * <br>
     * 住所(ビル、マンション名)取得を行う<br>
     * <br>
     * @param なし<br>
     * @return other_txt 住所(ビル、マンション名)<br>
     * @exception なし
     */
    public String getOther_txt() {
        return other_txt;
    }

    /**
     * 性別取得<br>
     * <br>
     * 性別取得を行う<br>
     * <br>
     * @param なし<br>
     * @return gender_rdl 性別<br>
     * @exception なし
     */
    public String getGender_rdl() {
        return gender_rdl;
    }

    /**
     * メールアドレス1取得<br>
     * <br>
     * メールアドレス1取得を行う<br>
     * <br>
     * @param なし<br>
     * @return mailAdd1_txt メールアドレス1<br>
     * @exception なし
     */
    public String getMailAdd1_txt() {
        return mailAdd1_txt;
    }

    /**
     * メールアドレス2取得<br>
     * <br>
     * メールアドレス2取得を行う<br>
     * <br>
     * @param なし<br>
     * @return mailAdd2_txt メールアドレス2<br>
     * @exception なし
     */
    public String getMailAdd2_txt() {
        return mailAdd2_txt;
    }

    /**
     * メールアドレス3取得<br>
     * <br>
     * メールアドレス3取得を行う<br>
     * <br>
     * @param なし<br>
     * @return mailAdd3_txt メールアドレス3<br>
     * @exception なし
     */
    public String getMailAdd3_txt() {
        return mailAdd3_txt;
    }

    /**
     * ご職業取得<br>
     * <br>
     * ご職業取得を行う<br>
     * <br>
     * @param なし<br>
     * @return occupa_sel ご職業<br>
     * @exception なし
     */
    public String getOccupa_sel() {
        return occupa_sel;
    }

    /**
     * ご利用規約に同意する取得<br>
     * <br>
     * ご利用規約に同意する取得を行う<br>
     * <br>
     * @param なし<br>
     * @return useAgreeFlg_chk ご利用規約に同意する<br>
     * @exception なし
     */
    public boolean getUseAgreeFlg_chk() {
        return useAgreeFlg_chk;
    }

    /**
     * ご利用の動機1取得<br>
     * <br>
     * ご利用の動機1取得を行う<br>
     * <br>
     * @param なし<br>
     * @return motiveFlg1_chk ご利用の動機1<br>
     * @exception なし
     */
    public boolean getMotiveFlg1_chk() {
        return motiveFlg1_chk;
    }

    /**
     * ご利用の動機2取得<br>
     * <br>
     * ご利用の動機2取得を行う<br>
     * <br>
     * @param なし<br>
     * @return motiveFlg2_chk ご利用の動機2<br>
     * @exception なし
     */
    public boolean getMotiveFlg2_chk() {
        return motiveFlg2_chk;
    }

    /**
     * ご利用の動機3取得<br>
     * <br>
     * ご利用の動機3取得を行う<br>
     * <br>
     * @param なし<br>
     * @return motiveFlg3_chk ご利用の動機3<br>
     * @exception なし
     */
    public boolean getMotiveFlg3_chk() {
        return motiveFlg3_chk;
    }

    /**
     * ご利用の動機4取得<br>
     * <br>
     * ご利用の動機4取得を行う<br>
     * <br>
     * @param なし<br>
     * @return motiveFlg4_chk ご利用の動機4<br>
     * @exception なし
     */
/*
    public boolean getMotiveFlg4_chk() {
        return motiveFlg4_chk;
    }
*/


    /**
     * ご利用の動機5取得<br>
     * <br>
     * ご利用の動機5取得を行う<br>
     * <br>
     * @param なし<br>
     * @return motiveFlg5_chk ご利用の動機5<br>
     * @exception なし
     */
    public boolean getMotiveFlg5_chk() {
        return motiveFlg5_chk;
    }

    /**
     * ご利用の動機1取得<br>
     * <br>
     * ご利用の動機1取得を行う<br>
     * <br>
     * @param なし<br>
     * @return motiveFlg1_lbl ご利用の動機1<br>
     * @exception なし
     */
    public String getMotiveFlg1_lbl() {
        return motiveFlg1_lbl;
    }

    /**
     * ご利用の動機2取得<br>
     * <br>
     * ご利用の動機2取得を行う<br>
     * <br>
     * @param なし<br>
     * @return motiveFlg2_lbl ご利用の動機2<br>
     * @exception なし
     */
    public String getMotiveFlg2_lbl() {
        return motiveFlg2_lbl;
    }

    /**
     * ご利用の動機3取得<br>
     * <br>
     * ご利用の動機3取得を行う<br>
     * <br>
     * @param なし<br>
     * @return motiveFlg3_lbl ご利用の動機3<br>
     * @exception なし
     */
    public String getMotiveFlg3_lbl() {
        return motiveFlg3_lbl;
    }

    /**
     * ご利用の動機4取得<br>
     * <br>
     * ご利用の動機4取得を行う<br>
     * <br>
     * @param なし<br>
     * @return motiveFlg4_lbl ご利用の動機4<br>
     * @exception なし
     */
/*
    public String getMotiveFlg4_lbl() {
        return motiveFlg4_lbl;
    }
*/


    /**
     * ご利用の動機5取得<br>
     * <br>
     * ご利用の動機5取得を行う<br>
     * <br>
     * @param なし<br>
     * @return motiveFlg5_lbl ご利用の動機5<br>
     * @exception なし
     */
    public String getMotiveFlg5_lbl() {
        return motiveFlg5_lbl;
    }

    /**
     * ご利用の動機備考取得<br>
     * <br>
     * ご利用の動機備考取得を行う<br>
     * <br>
     * @param なし<br>
     * @return motive_txt ご利用の動機備考<br>
     * @exception なし
     */
    public String getMotive_txt() {
        return motive_txt;
    }

    /**
     * 達成したい目標があればご記入ください取得<br>
     * <br>
     * 達成したい目標があればご記入ください取得を行う<br>
     * <br>
     * @param なし<br>
     * @return achieve_txt 達成したい目標があればご記入ください<br>
     * @exception なし
     */
    public String getAchieve_txt() {
        return achieve_txt;
    }

    /**
     * 個人情報の同意取得<br>
     * <br>
     * 個人情報の同意取得を行う<br>
     * <br>
     * @param なし<br>
     * @return indivAgreeFlg_chk 個人情報の同意<br>
     * @exception なし
     */
    public boolean getIndivAgreeFlg_chk() {
        return indivAgreeFlg_chk;
    }

    /**
     * 保護者お名前(姓)取得<br>
     * <br>
     * 保護者お名前(姓)取得を行う<br>
     * <br>
     * @param なし<br>
     * @return guardFamilyJnm_txt 保護者お名前(姓)<br>
     * @exception なし
     */
    public String getGuardFamilyJnm_txt() {
        return guardFamilyJnm_txt;
    }

    /**
     * 保護者お名前(名)取得<br>
     * <br>
     * 保護者お名前(名)取得を行う<br>
     * <br>
     * @param なし<br>
     * @return guardFirstJnm_txt 保護者お名前(名)<br>
     * @exception なし
     */
    public String getGuardFirstJnm_txt() {
        return guardFirstJnm_txt;
    }

    /**
     * 保護者フリガナ(姓)取得<br>
     * <br>
     * 保護者フリガナ(姓)取得を行う<br>
     * <br>
     * @param なし<br>
     * @return guardFamilyKnm_txt 保護者フリガナ(姓)<br>
     * @exception なし
     */
    public String getGuardFamilyKnm_txt() {
        return guardFamilyKnm_txt;
    }

    /**
     * 保護者フリガナ(名)取得<br>
     * <br>
     * 保護者フリガナ(名)取得を行う<br>
     * <br>
     * @param なし<br>
     * @return guardFirstKnm_txt 保護者フリガナ(名)<br>
     * @exception なし
     */
    public String getGuardFirstKnm_txt() {
        return guardFirstKnm_txt;
    }

    /**
     * あなたとの続柄取得<br>
     * <br>
     * あなたとの続柄取得を行う<br>
     * <br>
     * @param なし<br>
     * @return guardRelat_txt あなたとの続柄<br>
     * @exception なし
     */
    public String getGuardRelat_txt() {
        return guardRelat_txt;
    }

    /**
     * 保護者電話番号取得<br>
     * <br>
     * 保護者電話番号取得を行う<br>
     * <br>
     * @param なし<br>
     * @return guardTel1_txt 保護者電話番号<br>
     * @exception なし
     */
    public String getGuardTel1_txt() {
        return guardTel1_txt;
    }

    /**
     * 保護者携帯電話取得<br>
     * <br>
     * 保護者携帯電話取得を行う<br>
     * <br>
     * @param なし<br>
     * @return guardTel2_txt 保護者携帯電話<br>
     * @exception なし
     */
    public String getGuardTel2_txt() {
        return guardTel2_txt;
    }

    /**
     * 保護者生年月日年取得<br>
     * <br>
     * 保護者生年月日年取得を行う<br>
     * <br>
     * @param なし<br>
     * @return guardBirthYy_txt 保護者生年月日年<br>
     * @exception なし
     */
    public String getGuardBirthYy_txt() {
        return guardBirthYy_txt;
    }

    /**
     * 保護者生年月日月取得<br>
     * <br>
     * 保護者生年月日月取得を行う<br>
     * <br>
     * @param なし<br>
     * @return guardBirthMm_txt 保護者生年月日月<br>
     * @exception なし
     */
    public String getGuardBirthMm_txt() {
        return guardBirthMm_txt;
    }

    /**
     * 保護者生年月日日取得<br>
     * <br>
     * 保護者生年月日日取得を行う<br>
     * <br>
     * @param なし<br>
     * @return guardBirthDd_txt 保護者生年月日日<br>
     * @exception なし
     */
    public String getGuardBirthDd_txt() {
        return guardBirthDd_txt;
    }

    /**
     * 保護者郵便番号取得<br>
     * <br>
     * 保護者郵便番号取得を行う<br>
     * <br>
     * @param なし<br>
     * @return guardZipCd_txt 保護者郵便番号<br>
     * @exception なし
     */
    public String getGuardZipCd_txt() {
        return guardZipCd_txt;
    }

    /**
     * 保護者住所(地域)取得<br>
     * <br>
     * 保護者住所(地域)取得を行う<br>
     * <br>
     * @param なし<br>
     * @return guardArea_sel 保護者住所(地域)<br>
     * @exception なし
     */
    public String getGuardArea_sel() {
        return guardArea_sel;
    }

    /**
     * 保護者住所(都道府県)取得<br>
     * <br>
     * 保護者住所(都道府県)取得を行う<br>
     * <br>
     * @param なし<br>
     * @return guardprefecture_sel 保護者住所(都道府県)<br>
     * @exception なし
     */
    public String getGuardprefecture_sel() {
        return guardprefecture_sel;
    }

    /**
     * 保護者住所(市区町村 等)取得<br>
     * <br>
     * 保護者住所(市区町村 等)取得を行う<br>
     * <br>
     * @param なし<br>
     * @return guardCity_txt 保護者住所(市区町村 等)<br>
     * @exception なし
     */
    public String getGuardCity_txt() {
        return guardCity_txt;
    }

    /**
     * 保護者住所(ビル、マンション名 等)取得<br>
     * <br>
     * 保護者住所(ビル、マンション名 等)取得を行う<br>
     * <br>
     * @param なし<br>
     * @return guardOther_txt 保護者住所(ビル、マンション名 等)<br>
     * @exception なし
     */
    public String getGuardOther_txt() {
        return guardOther_txt;
    }

    /**
     * 保護者性別取得<br>
     * <br>
     * 保護者性別取得を行う<br>
     * <br>
     * @param なし<br>
     * @return guardGender_rdl 保護者性別<br>
     * @exception なし
     */
    public String getGuardGender_rdl() {
        return guardGender_rdl;
    }

    /**
     * 保護者メールアドレス1取得<br>
     * <br>
     * 保護者メールアドレス1取得を行う<br>
     * <br>
     * @param なし<br>
     * @return guardMailAdd1_txt 保護者メールアドレス1<br>
     * @exception なし
     */
    public String getGuardMailAdd1_txt() {
        return guardMailAdd1_txt;
    }

    /**
     * 保護者メールアドレス2取得<br>
     * <br>
     * 保護者メールアドレス2取得を行う<br>
     * <br>
     * @param なし<br>
     * @return guardMailAdd2_txt 保護者メールアドレス2<br>
     * @exception なし
     */
    public String getGuardMailAdd2_txt() {
        return guardMailAdd2_txt;
    }

    /**
     * 保護者メールアドレス3取得<br>
     * <br>
     * 保護者メールアドレス3取得を行う<br>
     * <br>
     * @param なし<br>
     * @return guardMailAdd3_txt 保護者メールアドレス3<br>
     * @exception なし
     */
    public String getGuardMailAdd3_txt() {
        return guardMailAdd3_txt;
    }

    /**
     * 顧客区分取得<br>
     * <br>
     * 顧客区分取得を行う<br>
     * <br>
     * @param なし<br>
     * @return customerKbn 顧客区分<br>
     * @exception なし
     */
    public String getCustomerKbn() {
        return customerKbn;
    }

    /**
     * 排他用レコードバージョン取得<br>
     * <br>
     * 排他用レコードバージョン取得を行う<br>
     * <br>
     * @param なし<br>
     * @return recordVerNo 排他用レコードバージョン<br>
     * @exception なし
     */
    public int getRecordVerNo() {
        return recordVerNo;
    }

    /**
     * 受講者ID設定<br>
     * <br>
     * 受講者ID設定を行う<br>
     * <br>
     * @param studentId_lbl 受講者ID<br>
     * @return なし<br>
     * @exception なし
     */
    public void setStudentId_lbl(String studentId_lbl) {
        this.studentId_lbl = studentId_lbl;
    }

    /**
     * 受講者所属部署設定<br>
     * <br>
     * 受講者所属部署設定を行う<br>
     * <br>
     * @param studentPosi_txt 受講者所属部署<br>
     * @return なし<br>
     * @exception なし
     */
    public void setStudentPosi_txt(String studentPosi_txt) {
        this.studentPosi_txt = studentPosi_txt;
    }

    /**
     * パスワード設定<br>
     * <br>
     * パスワード設定を行う<br>
     * <br>
     * @param password_txt パスワード<br>
     * @return なし<br>
     * @exception なし
     */
    public void setPassword_txt(String password_txt) {
        this.password_txt = password_txt;
    }

    /**
     * パスワード確認設定<br>
     * <br>
     * パスワード確認設定を行う<br>
     * <br>
     * @param passwordConf_txt パスワード確認<br>
     * @return なし<br>
     * @exception なし
     */
    public void setPasswordConf_txt(String passwordConf_txt) {
        this.passwordConf_txt = passwordConf_txt;
    }

    /**
     * お名前(姓)設定<br>
     * <br>
     * お名前(姓)設定を行う<br>
     * <br>
     * @param familyJnm_txt お名前(姓)<br>
     * @return なし<br>
     * @exception なし
     */
    public void setFamilyJnm_txt(String familyJnm_txt) {
        this.familyJnm_txt = familyJnm_txt;
    }

    /**
     * お名前(名)設定<br>
     * <br>
     * お名前(名)設定を行う<br>
     * <br>
     * @param firstJnm_txt お名前(名)<br>
     * @return なし<br>
     * @exception なし
     */
    public void setFirstJnm_txt(String firstJnm_txt) {
        this.firstJnm_txt = firstJnm_txt;
    }

    /**
     * フリガナ(姓)設定<br>
     * <br>
     * フリガナ(姓)設定を行う<br>
     * <br>
     * @param familyKnm_txt フリガナ(姓)<br>
     * @return なし<br>
     * @exception なし
     */
    public void setFamilyKnm_txt(String familyKnm_txt) {
        this.familyKnm_txt = familyKnm_txt;
    }

    /**
     * フリガナ(名)設定<br>
     * <br>
     * フリガナ(名)設定を行う<br>
     * <br>
     * @param firstKnm_txt フリガナ(名)<br>
     * @return なし<br>
     * @exception なし
     */
    public void setFirstKnm_txt(String firstKnm_txt) {
        this.firstKnm_txt = firstKnm_txt;
    }

    /**
     * ローマ字(姓)設定<br>
     * <br>
     * ローマ字(姓)設定を行う<br>
     * <br>
     * @param familyRomaji_txt ローマ字(姓)<br>
     * @return なし<br>
     * @exception なし
     */
    public void setFamilyRomaji_txt(String familyRomaji_txt) {
        this.familyRomaji_txt = familyRomaji_txt;
    }

    /**
     * ローマ字(名)設定<br>
     * <br>
     * ローマ字(名)設定を行う<br>
     * <br>
     * @param firstRomaji_txt ローマ字(名)<br>
     * @return なし<br>
     * @exception なし
     */
    public void setFirstRomaji_txt(String firstRomaji_txt) {
        this.firstRomaji_txt = firstRomaji_txt;
    }

    /**
     * ニックネーム設定<br>
     * <br>
     * ニックネーム設定を行う<br>
     * <br>
     * @param nickNm_txt ニックネーム<br>
     * @return なし<br>
     * @exception なし
     */
    public void setNickNm_txt(String nickNm_txt) {
        this.nickNm_txt = nickNm_txt;
    }

    /**
     * 電話番号設定<br>
     * <br>
     * 電話番号設定を行う<br>
     * <br>
     * @param tel1_txt 電話番号<br>
     * @return なし<br>
     * @exception なし
     */
    public void setTel1_txt(String tel1_txt) {
        this.tel1_txt = tel1_txt;
    }

    /**
     * 携帯電話設定<br>
     * <br>
     * 携帯電話設定を行う<br>
     * <br>
     * @param tel2_txt 携帯電話<br>
     * @return なし<br>
     * @exception なし
     */
    public void setTel2_txt(String tel2_txt) {
        this.tel2_txt = tel2_txt;
    }

    /**
     * 生年月日年設定<br>
     * <br>
     * 生年月日年設定を行う<br>
     * <br>
     * @param birthYy_txt 生年月日年<br>
     * @return なし<br>
     * @exception なし
     */
    public void setBirthYy_txt(String birthYy_txt) {
        this.birthYy_txt = birthYy_txt;
    }

    /**
     * 生年月日月設定<br>
     * <br>
     * 生年月日月設定を行う<br>
     * <br>
     * @param birthMm_txt 生年月日月<br>
     * @return なし<br>
     * @exception なし
     */
    public void setBirthMm_txt(String birthMm_txt) {
        this.birthMm_txt = birthMm_txt;
    }

    /**
     * 郵便番号設定<br>
     * <br>
     * 郵便番号設定を行う<br>
     * <br>
     * @param birthDd_txt 郵便番号<br>
     * @return なし<br>
     * @exception なし
     */
    public void setBirthDd_txt(String birthDd_txt) {
        this.birthDd_txt = birthDd_txt;
    }

    /**
     * 郵便番号設定<br>
     * <br>
     * 郵便番号設定を行う<br>
     * <br>
     * @param zipCd_txt 郵便番号<br>
     * @return なし<br>
     * @exception なし
     */
    public void setZipCd_txt(String zipCd_txt) {
        this.zipCd_txt = zipCd_txt;
    }

    /**
     * 住所(地域)設定<br>
     * <br>
     * 住所(地域)設定を行う<br>
     * <br>
     * @param area_sel 住所(地域)<br>
     * @return なし<br>
     * @exception なし
     */
    public void setArea_sel(String area_sel) {
        this.area_sel = area_sel;
    }

    /**
     * 住所(都道府県)設定<br>
     * <br>
     * 住所(都道府県)設定を行う<br>
     * <br>
     * @param prefecture_sel 住所(都道府県)<br>
     * @return なし<br>
     * @exception なし
     */
    public void setPrefecture_sel(String prefecture_sel) {
        this.prefecture_sel = prefecture_sel;
    }

    /**
     * 住所(市区町村 等)設定<br>
     * <br>
     * 住所(市区町村 等)設定を行う<br>
     * <br>
     * @param city_txt 住所(市区町村 等)<br>
     * @return なし<br>
     * @exception なし
     */
    public void setCity_txt(String city_txt) {
        this.city_txt = city_txt;
    }

    /**
     * 住所(ビル、マンション名)設定<br>
     * <br>
     * 住所(ビル、マンション名)設定を行う<br>
     * <br>
     * @param other_txt 住所(ビル、マンション名)<br>
     * @return なし<br>
     * @exception なし
     */
    public void setOther_txt(String other_txt) {
        this.other_txt = other_txt;
    }

    /**
     * 性別設定<br>
     * <br>
     * 性別設定を行う<br>
     * <br>
     * @param gender_rdl 性別<br>
     * @return なし<br>
     * @exception なし
     */
    public void setGender_rdl(String gender_rdl) {
        this.gender_rdl = gender_rdl;
    }

    /**
     * メールアドレス1設定<br>
     * <br>
     * メールアドレス1設定を行う<br>
     * <br>
     * @param mailAdd1_txt メールアドレス1<br>
     * @return なし<br>
     * @exception なし
     */
    public void setMailAdd1_txt(String mailAdd1_txt) {
        this.mailAdd1_txt = mailAdd1_txt;
    }

    /**
     * メールアドレス2設定<br>
     * <br>
     * メールアドレス2設定を行う<br>
     * <br>
     * @param mailAdd2_txt メールアドレス2<br>
     * @return なし<br>
     * @exception なし
     */
    public void setMailAdd2_txt(String mailAdd2_txt) {
        this.mailAdd2_txt = mailAdd2_txt;
    }

    /**
     * メールアドレス3設定<br>
     * <br>
     * メールアドレス3設定を行う<br>
     * <br>
     * @param mailAdd3_txt メールアドレス3<br>
     * @return なし<br>
     * @exception なし
     */
    public void setMailAdd3_txt(String mailAdd3_txt) {
        this.mailAdd3_txt = mailAdd3_txt;
    }

    /**
     * ご職業設定<br>
     * <br>
     * ご職業設定を行う<br>
     * <br>
     * @param occupa_sel ご職業<br>
     * @return なし<br>
     * @exception なし
     */
    public void setOccupa_sel(String occupa_sel) {
        this.occupa_sel = occupa_sel;
    }

    /**
     * ご利用規約に同意する設定<br>
     * <br>
     * ご利用規約に同意する設定を行う<br>
     * <br>
     * @param useAgreeFlg_chk ご利用規約に同意する<br>
     * @return なし<br>
     * @exception なし
     */
    public void setUseAgreeFlg_chk(boolean useAgreeFlg_chk) {
        this.useAgreeFlg_chk = useAgreeFlg_chk;
    }

    /**
     * ご利用の動機1設定<br>
     * <br>
     * ご利用の動機1設定を行う<br>
     * <br>
     * @param motiveFlg1_chk ご利用の動機1<br>
     * @return なし<br>
     * @exception なし
     */
    public void setMotiveFlg1_chk(boolean motiveFlg1_chk) {
        this.motiveFlg1_chk = motiveFlg1_chk;
    }

    /**
     * ご利用の動機2設定<br>
     * <br>
     * ご利用の動機2設定を行う<br>
     * <br>
     * @param motiveFlg2_chk ご利用の動機2<br>
     * @return なし<br>
     * @exception なし
     */
    public void setMotiveFlg2_chk(boolean motiveFlg2_chk) {
        this.motiveFlg2_chk = motiveFlg2_chk;
    }

    /**
     * ご利用の動機3設定<br>
     * <br>
     * ご利用の動機3設定を行う<br>
     * <br>
     * @param motiveFlg3_chk ご利用の動機3<br>
     * @return なし<br>
     * @exception なし
     */
    public void setMotiveFlg3_chk(boolean motiveFlg3_chk) {
        this.motiveFlg3_chk = motiveFlg3_chk;
    }

    /**
     * ご利用の動機4設定<br>
     * <br>
     * ご利用の動機4設定を行う<br>
     * <br>
     * @param motiveFlg4_chk ご利用の動機4<br>
     * @return なし<br>
     * @exception なし
     */
/*
    public void setMotiveFlg4_chk(boolean motiveFlg4_chk) {
        this.motiveFlg4_chk = motiveFlg4_chk;
    }
*/

    /**
     * ご利用の動機5設定<br>
     * <br>
     * ご利用の動機5設定を行う<br>
     * <br>
     * @param motiveFlg5_chk ご利用の動機5<br>
     * @return なし<br>
     * @exception なし
     */
    public void setMotiveFlg5_chk(boolean motiveFlg5_chk) {
        this.motiveFlg5_chk = motiveFlg5_chk;
    }

    /**
     * ご利用の動機1設定<br>
     * <br>
     * ご利用の動機1設定を行う<br>
     * <br>
     * @param motiveFlg1_lbl ご利用の動機1<br>
     * @return なし<br>
     * @exception なし
     */
    public void setMotiveFlg1_lbl(String motiveFlg1_lbl) {
        this.motiveFlg1_lbl = motiveFlg1_lbl;
    }

    /**
     * ご利用の動機2設定<br>
     * <br>
     * ご利用の動機2設定を行う<br>
     * <br>
     * @param motiveFlg2_lbl ご利用の動機2<br>
     * @return なし<br>
     * @exception なし
     */
    public void setMotiveFlg2_lbl(String motiveFlg2_lbl) {
        this.motiveFlg2_lbl = motiveFlg2_lbl;
    }

    /**
     * ご利用の動機3設定<br>
     * <br>
     * ご利用の動機3設定を行う<br>
     * <br>
     * @param motiveFlg3_lbl ご利用の動機3<br>
     * @return なし<br>
     * @exception なし
     */
    public void setMotiveFlg3_lbl(String motiveFlg3_lbl) {
        this.motiveFlg3_lbl = motiveFlg3_lbl;
    }

    /**
     * ご利用の動機4設定<br>
     * <br>
     * ご利用の動機4設定を行う<br>
     * <br>
     * @param motiveFlg4_lbl ご利用の動機4<br>
     * @return なし<br>
     * @exception なし
     */
/*
    public void setMotiveFlg4_lbl(String motiveFlg4_lbl) {
        this.motiveFlg4_lbl = motiveFlg4_lbl;
    }
*/


    /**
     * ご利用の動機5設定<br>
     * <br>
     * ご利用の動機5設定を行う<br>
     * <br>
     * @param motiveFlg5_lbl ご利用の動機5<br>
     * @return なし<br>
     * @exception なし
     */
    public void setMotiveFlg5_lbl(String motiveFlg5_lbl) {
        this.motiveFlg5_lbl = motiveFlg5_lbl;
    }

    /**
     * ご利用の動機備考設定<br>
     * <br>
     * ご利用の動機備考設定を行う<br>
     * <br>
     * @param motive_txt ご利用の動機備考<br>
     * @return なし<br>
     * @exception なし
     */
    public void setMotive_txt(String motive_txt) {
        this.motive_txt = motive_txt;
    }

    /**
     * 達成したい目標があればご記入ください設定<br>
     * <br>
     * 達成したい目標があればご記入ください設定を行う<br>
     * <br>
     * @param achieve_txt 達成したい目標があればご記入ください<br>
     * @return なし<br>
     * @exception なし
     */
    public void setAchieve_txt(String achieve_txt) {
        this.achieve_txt = achieve_txt;
    }

    /**
     * 個人情報の同意設定<br>
     * <br>
     * 個人情報の同意設定を行う<br>
     * <br>
     * @param indivAgreeFlg_chk 個人情報の同意<br>
     * @return なし<br>
     * @exception なし
     */
    public void setIndivAgreeFlg_chk(boolean indivAgreeFlg_chk) {
        this.indivAgreeFlg_chk = indivAgreeFlg_chk;
    }

    /**
     * 保護者お名前(姓)設定<br>
     * <br>
     * 保護者お名前(姓)設定を行う<br>
     * <br>
     * @param guardFamilyJnm_txt 保護者お名前(姓)<br>
     * @return なし<br>
     * @exception なし
     */
    public void setGuardFamilyJnm_txt(String guardFamilyJnm_txt) {
        this.guardFamilyJnm_txt = guardFamilyJnm_txt;
    }

    /**
     * 保護者お名前(名)設定<br>
     * <br>
     * 保護者お名前(名)設定を行う<br>
     * <br>
     * @param guardFirstJnm_txt 保護者お名前(名)<br>
     * @return なし<br>
     * @exception なし
     */
    public void setGuardFirstJnm_txt(String guardFirstJnm_txt) {
        this.guardFirstJnm_txt = guardFirstJnm_txt;
    }

    /**
     * 保護者フリガナ(姓)設定<br>
     * <br>
     * 保護者フリガナ(姓)設定を行う<br>
     * <br>
     * @param guardFamilyKnm_txt 保護者フリガナ(姓)<br>
     * @return なし<br>
     * @exception なし
     */
    public void setGuardFamilyKnm_txt(String guardFamilyKnm_txt) {
        this.guardFamilyKnm_txt = guardFamilyKnm_txt;
    }

    /**
     * 保護者フリガナ(名)設定<br>
     * <br>
     * 保護者フリガナ(名)設定を行う<br>
     * <br>
     * @param guardFirstKnm_txt 保護者フリガナ(名)<br>
     * @return なし<br>
     * @exception なし
     */
    public void setGuardFirstKnm_txt(String guardFirstKnm_txt) {
        this.guardFirstKnm_txt = guardFirstKnm_txt;
    }

    /**
     * あなたとの続柄設定<br>
     * <br>
     * あなたとの続柄設定を行う<br>
     * <br>
     * @param guardRelat_txt あなたとの続柄<br>
     * @return なし<br>
     * @exception なし
     */
    public void setGuardRelat_txt(String guardRelat_txt) {
        this.guardRelat_txt = guardRelat_txt;
    }

    /**
     * 保護者電話番号設定<br>
     * <br>
     * 保護者電話番号設定を行う<br>
     * <br>
     * @param guardTel1_txt 保護者電話番号<br>
     * @return なし<br>
     * @exception なし
     */
    public void setGuardTel1_txt(String guardTel1_txt) {
        this.guardTel1_txt = guardTel1_txt;
    }

    /**
     * 保護者携帯電話設定<br>
     * <br>
     * 保護者携帯電話設定を行う<br>
     * <br>
     * @param guardTel2_txt 保護者携帯電話<br>
     * @return なし<br>
     * @exception なし
     */
    public void setGuardTel2_txt(String guardTel2_txt) {
        this.guardTel2_txt = guardTel2_txt;
    }

    /**
     * 保護者生年月日年設定<br>
     * <br>
     * 保護者生年月日年設定を行う<br>
     * <br>
     * @param guardBirthYy_txt 保護者生年月日年<br>
     * @return なし<br>
     * @exception なし
     */
    public void setGuardBirthYy_txt(String guardBirthYy_txt) {
        this.guardBirthYy_txt = guardBirthYy_txt;
    }

    /**
     * 保護者生年月日月設定<br>
     * <br>
     * 保護者生年月日月設定を行う<br>
     * <br>
     * @param guardBirthMm_txt 保護者生年月日月<br>
     * @return なし<br>
     * @exception なし
     */
    public void setGuardBirthMm_txt(String guardBirthMm_txt) {
        this.guardBirthMm_txt = guardBirthMm_txt;
    }

    /**
     * 保護者生年月日日設定<br>
     * <br>
     * 保護者生年月日日設定を行う<br>
     * <br>
     * @param guardBirthDd_txt 保護者生年月日日<br>
     * @return なし<br>
     * @exception なし
     */
    public void setGuardBirthDd_txt(String guardBirthDd_txt) {
        this.guardBirthDd_txt = guardBirthDd_txt;
    }

    /**
     * 保護者郵便番号設定<br>
     * <br>
     * 保護者郵便番号設定を行う<br>
     * <br>
     * @param guardZipCd_txt 保護者郵便番号<br>
     * @return なし<br>
     * @exception なし
     */
    public void setGuardZipCd_txt(String guardZipCd_txt) {
        this.guardZipCd_txt = guardZipCd_txt;
    }

    /**
     * 保護者住所(地域)設定<br>
     * <br>
     * 保護者住所(地域)設定を行う<br>
     * <br>
     * @param guardArea_sel 保護者住所(地域)<br>
     * @return なし<br>
     * @exception なし
     */
    public void setGuardArea_sel(String guardArea_sel) {
        this.guardArea_sel = guardArea_sel;
    }

    /**
     * 保護者住所(都道府県)設定<br>
     * <br>
     * 保護者住所(都道府県)設定を行う<br>
     * <br>
     * @param guardprefecture_sel 保護者住所(都道府県)<br>
     * @return なし<br>
     * @exception なし
     */
    public void setGuardprefecture_sel(String guardprefecture_sel) {
        this.guardprefecture_sel = guardprefecture_sel;
    }

    /**
     * 保護者住所(市区町村 等)設定<br>
     * <br>
     * 保護者住所(市区町村 等)設定を行う<br>
     * <br>
     * @param guardCity_txt 保護者住所(市区町村 等)<br>
     * @return なし<br>
     * @exception なし
     */
    public void setGuardCity_txt(String guardCity_txt) {
        this.guardCity_txt = guardCity_txt;
    }

    /**
     * 保護者住所(ビル、マンション名 等)設定<br>
     * <br>
     * 保護者住所(ビル、マンション名 等)設定を行う<br>
     * <br>
     * @param guardOther_txt 保護者住所(ビル、マンション名 等)<br>
     * @return なし<br>
     * @exception なし
     */
    public void setGuardOther_txt(String guardOther_txt) {
        this.guardOther_txt = guardOther_txt;
    }

    /**
     * 保護者性別設定<br>
     * <br>
     * 保護者性別設定を行う<br>
     * <br>
     * @param guardGender_rdl 保護者性別<br>
     * @return なし<br>
     * @exception なし
     */
    public void setGuardGender_rdl(String guardGender_rdl) {
        this.guardGender_rdl = guardGender_rdl;
    }

    /**
     * 保護者メールアドレス1設定<br>
     * <br>
     * 保護者メールアドレス1設定を行う<br>
     * <br>
     * @param guardMailAdd1_txt 保護者メールアドレス1<br>
     * @return なし<br>
     * @exception なし
     */
    public void setGuardMailAdd1_txt(String guardMailAdd1_txt) {
        this.guardMailAdd1_txt = guardMailAdd1_txt;
    }

    /**
     * 保護者メールアドレス2設定<br>
     * <br>
     * 保護者メールアドレス2設定を行う<br>
     * <br>
     * @param guardMailAdd2_txt 保護者メールアドレス2<br>
     * @return なし<br>
     * @exception なし
     */
    public void setGuardMailAdd2_txt(String guardMailAdd2_txt) {
        this.guardMailAdd2_txt = guardMailAdd2_txt;
    }

    /**
     * 保護者メールアドレス3設定<br>
     * <br>
     * 保護者メールアドレス3設定を行う<br>
     * <br>
     * @param guardMailAdd3_txt 保護者メールアドレス3<br>
     * @return なし<br>
     * @exception なし
     */
    public void setGuardMailAdd3_txt(String guardMailAdd3_txt) {
        this.guardMailAdd3_txt = guardMailAdd3_txt;
    }

    /**
     * 顧客区分設定<br>
     * <br>
     * 顧客区分設定を行う<br>
     * <br>
     * @param customerKbn 顧客区分<br>
     * @return なし<br>
     * @exception なし
     */
    public void setCustomerKbn(String customerKbn) {
        this.customerKbn = customerKbn;
    }

    /**
     * 排他用レコードバージョン設定<br>
     * <br>
     * 排他用レコードバージョン設定を行う<br>
     * <br>
     * @param recordVerNo 排他用レコードバージョン<br>
     * @return なし<br>
     * @exception なし
     */
    public void setRecordVerNo(int recordVerNo) {
        this.recordVerNo = recordVerNo;
    }

    /**
     * 組織名取得<br>
     * <br>
     * 組織名取得を行う<br>
     * <br>
     * @param なし<br>
     * @return organizationNm 組織名<br>
     * @exception なし
     */
    public String getOrganizationNm() {
        return organizationNm;
    }

    /**
     * 組織名設定<br>
     * <br>
     * 組織名設定を行う<br>
     * <br>
     * @param organizationNm 組織名<br>
     * @return なし<br>
     * @exception なし
     */
    public void setOrganizationNm(String organizationNm) {
        this.organizationNm = organizationNm;
    }

    /**
     * 組織ID取得<br>
     * <br>
     * 組織ID取得を行う<br>
     * <br>
     * @param なし<br>
     * @return organizationId 組織ID<br>
     * @exception なし
     */
    public String getOrganizationId() {
        return organizationId;
    }

    /**
     * 組織ID設定<br>
     * <br>
     * 組織ID設定を行う<br>
     * <br>
     * @param organizationId 組織ID<br>
     * @return なし<br>
     * @exception なし
     */
    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    /**
     * 所属組織内ID取得<br>
     * <br>
     * 所属組織内ID取得を行う<br>
     * <br>
     * @param なし<br>
     * @return positionOrganizationId 所属組織内ID<br>
     * @exception なし
     */
    public String getPositionOrganizationId() {
        return positionOrganizationId;
    }

    /**
     * 所属組織内ID設定<br>
     * <br>
     * 所属組織内ID設定を行う<br>
     * <br>
     * @param positionOrganizationId 所属組織内ID<br>
     * @return なし<br>
     * @exception なし
     */
    public void setPositionOrganizationId(String positionOrganizationId) {
        this.positionOrganizationId = positionOrganizationId;
    }

    /**
     * 自己負担率取得<br>
     * <br>
     * 自己負担率取得を行う<br>
     * <br>
     * @param なし<br>
     * @return burdenNum 自己負担率<br>
     * @exception なし
     */
    public int getBurdenNum() {
        return burdenNum;
    }

    /**
     * 自己負担率設定<br>
     * <br>
     * 自己負担率設定を行う<br>
     * <br>
     * @param burdenNum 自己負担率<br>
     * @return なし<br>
     * @exception なし
     */
    public void setBurdenNum(int burdenNum) {
        this.burdenNum = burdenNum;
    }

    /**
     * ログイン回数取得<br>
     * <br>
     * ログイン回数取得を行う<br>
     * <br>
     * @param なし<br>
     * @return loginNum ログイン回数<br>
     * @exception なし
     */
    public int getLoginNum() {
        return loginNum;
    }

    /**
     * ログイン回数設定<br>
     * <br>
     * ログイン回数設定を行う<br>
     * <br>
     * @param loginNum ログイン回数<br>
     * @return なし<br>
     * @exception なし
     */
    public void setLoginNum(int loginNum) {
        this.loginNum = loginNum;
    }

    /**
     * 最終ログイン日取得<br>
     * <br>
     * 最終ログイン日取得を行う<br>
     * <br>
     * @param なし<br>
     * @return endLoginDt 最終ログイン日<br>
     * @exception なし
     */
    public String getEndLoginDt() {
        return endLoginDt;
    }

    /**
     * 最終ログイン日設定<br>
     * <br>
     * 最終ログイン日設定を行う<br>
     * <br>
     * @param endLoginDt 最終ログイン日<br>
     * @return なし<br>
     * @exception なし
     */
    public void setEndLoginDt(String endLoginDt) {
        this.endLoginDt = endLoginDt;
    }

    /**
     * ｽｸｰﾙからのｺﾒﾝﾄ(生徒公開)取得<br>
     * <br>
     * ｽｸｰﾙからのｺﾒﾝﾄ(生徒公開)取得を行う<br>
     * <br>
     * @param なし<br>
     * @return schoolCmt ｽｸｰﾙからのｺﾒﾝﾄ(生徒公開)<br>
     * @exception なし
     */
    public String getSchoolCmt() {
        return schoolCmt;
    }

    /**
     * ｽｸｰﾙからのｺﾒﾝﾄ(生徒公開)設定<br>
     * <br>
     * ｽｸｰﾙからのｺﾒﾝﾄ(生徒公開)設定を行う<br>
     * <br>
     * @param schoolCmt ｽｸｰﾙからのｺﾒﾝﾄ(生徒公開)<br>
     * @return なし<br>
     * @exception なし
     */
    public void setSchoolCmt(String schoolCmt) {
        this.schoolCmt = schoolCmt;
    }

    /**
     * 備考(生徒非公開)取得<br>
     * <br>
     * 備考(生徒非公開)取得を行う<br>
     * <br>
     * @param なし<br>
     * @return remark 備考(生徒非公開)<br>
     * @exception なし
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 備考(生徒非公開)設定<br>
     * <br>
     * 備考(生徒非公開)設定を行う<br>
     * <br>
     * @param remark 備考(生徒非公開)<br>
     * @return なし<br>
     * @exception なし
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }
}