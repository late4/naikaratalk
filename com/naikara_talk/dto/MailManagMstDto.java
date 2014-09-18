package com.naikara_talk.dto;

import java.sql.Timestamp;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称:</b>共通部品 DTOクラス<br>
 * <b>クラス名称　　　:</b>メール管理マスタクラス<br>
 * <b>クラス概要　　　:</b>メール管理マスタDTO<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/06/03 TECS 新規作成
 */
public class MailManagMstDto extends AbstractDto {

    private String mailPatternCd;    // メールパターンコード
    private String sentMailAddress;  // 送信者アドレス
    private String address;          // 宛先
    private String cc;               // CC
    private String bcc;              // BCC
    private String subjectTitle;     // 件名
    private String text1;            // 本文１
    private String text2;            // 本文２
    private String text3;            // 本文３
    private String text4;            // 本文４
    private String text5;            // 本文５
    private String text6;            // 本文６
    private String text7;            // 本文７
    private String text8;            // 本文８
    private String text9;            // 本文９
    private String text10;            // 本文１０
    private String text11;            // 本文１１
    private String text12;            // 本文１２
    private String text13;            // 本文１３
    private String text14;            // 本文１４
    private String text15;            // 本文１５
    private String text16;            // 本文１６
    private String text17;            // 本文１７
    private String text18;            // 本文１８
    private String text19;            // 本文１９
    private String text20;            // 本文２０
    private String text21;            // 本文２１
    private String text22;            // 本文２２
    private String text23;            // 本文２３
    private String text24;            // 本文２４
    private String text25;            // 本文２５
    private String text26;            // 本文２６
    private String text27;            // 本文２７
    private String text28;            // 本文２８
    private String text29;            // 本文２９
    private String text30;            // 本文３０
    private String text31;            // 本文３０
    private String text32;            // 本文３０
    private String text33;            // 本文３０
    private String text34;            // 本文３０
    private String text35;            // 本文３０
    private int recordVerNo;          // レコードバージョン番号
    private Timestamp insertDttm;     // 登録日時
    private String insertCd;          // 登録者コード
    private Timestamp updateDttm;     // 更新日時
    private String updateCd;          // 更新者コード
    private int returnCode;           // リターンコード

    /**
     * メールパターンコード取得<br>
     * <br>
     * メールパターンコードを戻り値で返却する<br>
     * <br>
     * @return mailPatternCd
     */
    public String getMailPatternCd() {
        return mailPatternCd;
    }

    /**
     * メールパターンコード設定<br>
     * <br>
     * メールパターンコードを引数で設定する<br>
     * <br>
     * @param mailPatternCd
     */
    public void setMailPatternCd(String mailPatternCd) {
        this.mailPatternCd = mailPatternCd;
    }

    /**
     * 送信者アドレス取得<br>
     * <br>
     * 送信者アドレスを戻り値で返却する<br>
     * <br>
     * @return sentMailAddress
     */
    public String getSentMailAddress() {
        return sentMailAddress;
    }

    /**
     * 送信者アドレス設定<br>
     * <br>
     * 送信者アドレスを引数で設定する<br>
     * <br>
     * @param sentMailAddress
     */
    public void setSentMailAddress(String sentMailAddress) {
        this.sentMailAddress = sentMailAddress;
    }

    /**
     * 宛先取得<br>
     * <br>
     * 宛先を戻り値で返却する<br>
     * <br>
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * 宛先設定<br>
     * <br>
     * 宛先を引数で設定する<br>
     * <br>
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * CC取得<br>
     * <br>
     * CCを戻り値で返却する<br>
     * <br>
     * @return cc
     */
    public String getCc() {
        return cc;
    }

    /**
     * CC設定<br>
     * <br>
     * CCを引数で設定する<br>
     * <br>
     * @param cc
     */
    public void setCc(String cc) {
        this.cc = cc;
    }

    /**
     * BCC取得<br>
     * <br>
     * BCCを戻り値で返却する<br>
     * <br>
     * @return bcc
     */
    public String getBcc() {
        return bcc;
    }

    /**
     * BCC設定<br>
     * <br>
     * BCCを引数で設定する<br>
     * <br>
     * @param bcc
     */
    public void setBcc(String bcc) {
        this.bcc = bcc;
    }

    /**
     * 件名取得<br>
     * <br>
     * 件名を戻り値で返却する<br>
     * <br>
     * @return subjectTitle
     */
    public String getSubjectTitle() {
        return subjectTitle;
    }

    /**
     * 件名設定<br>
     * <br>
     * 件名を引数で設定する<br>
     * <br>
     * @param subjectTitle
     */
    public void setSubjectTitle(String subjectTitle) {
        this.subjectTitle = subjectTitle;
    }

    /**
     * 本文１取得<br>
     * <br>
     * 本文１を戻り値で返却する<br>
     * <br>
     * @return text1
     */
    public String getText1() {
        return text1;
    }

    /**
     * 本文１設定<br>
     * <br>
     * 本文１を引数で設定する<br>
     * <br>
     * @param text1
     */
    public void setText1(String text1) {
        this.text1 = text1;
    }

    /**
     * 本文２取得<br>
     * <br>
     * 本文２を戻り値で返却する<br>
     * <br>
     * @return text2
     */
    public String getText2() {
        return text2;
    }

    /**
     * 本文２設定<br>
     * <br>
     * 本文２を引数で設定する<br>
     * <br>
     * @param text2
     */
    public void setText2(String text2) {
        this.text2 = text2;
    }

    /**
     * 本文３取得<br>
     * <br>
     * 本文３を戻り値で返却する<br>
     * <br>
     * @return text3
     */
    public String getText3() {
        return text3;
    }

    /**
     * 本文３設定<br>
     * <br>
     * 本文３を引数で設定する<br>
     * <br>
     * @param text3
     */
    public void setText3(String text3) {
        this.text3 = text3;
    }

    /**
     * 本文４取得<br>
     * <br>
     * 本文４を戻り値で返却する<br>
     * <br>
     * @return text4
     */
    public String getText4() {
        return text4;
    }

    /**
     * 本文４設定<br>
     * <br>
     * 本文４を引数で設定する<br>
     * <br>
     * @param text4
     */
    public void setText4(String text4) {
        this.text4 = text4;
    }

    /**
     * 本文５取得<br>
     * <br>
     * 本文５を戻り値で返却する<br>
     * <br>
     * @return text5
     */
    public String getText5() {
        return text5;
    }

    /**
     * 本文５設定<br>
     * <br>
     * 本文５を引数で設定する<br>
     * <br>
     * @param text5
     */
    public void setText5(String text5) {
        this.text5 = text5;
    }

    /**
     * 本文６取得<br>
     * <br>
     * 本文６を戻り値で返却する<br>
     * <br>
     * @return text6
     */
    public String getText6() {
        return text6;
    }

    /**
     * 本文６設定<br>
     * <br>
     * 本文６を引数で設定する<br>
     * <br>
     * @param text6
     */
    public void setText6(String text6) {
        this.text6 = text6;
    }

    /**
     * 本文７取得<br>
     * <br>
     * 本文７を戻り値で返却する<br>
     * <br>
     * @return text7
     */
    public String getText7() {
        return text7;
    }

    /**
     * 本文７設定<br>
     * <br>
     * 本文７を引数で設定する<br>
     * <br>
     * @param text7
     */
    public void setText7(String text7) {
        this.text7 = text7;
    }

    /**
     * 本文８取得<br>
     * <br>
     * 本文８を戻り値で返却する<br>
     * <br>
     * @return text8
     */
    public String getText8() {
        return text8;
    }

    /**
     * 本文８設定<br>
     * <br>
     * 本文８を引数で設定する<br>
     * <br>
     * @param text8
     */
    public void setText8(String text8) {
        this.text8 = text8;
    }

    /**
     * 本文９取得<br>
     * <br>
     * 本文９を戻り値で返却する<br>
     * <br>
     * @return text9
     */
    public String getText9() {
        return text9;
    }

    /**
     * 本文９設定<br>
     * <br>
     * 本文９を引数で設定する<br>
     * <br>
     * @param text9
     */
    public void setText9(String text9) {
        this.text9 = text9;
    }

    /**
     * 本文１０取得<br>
     * <br>
     * 本文１０を戻り値で返却する<br>
     * <br>
     * @return text10
     */
    public String getText10() {
        return text10;
    }

    /**
     * 本文１０設定<br>
     * <br>
     * 本文１０を引数で設定する<br>
     * <br>
     * @param text10
     */
    public void setText10(String text10) {
        this.text10 = text10;
    }

    /**
     * 本文１１取得<br>
     * <br>
     * 本文１１を戻り値で返却する<br>
     * <br>
     * @return text11
     */
    public String getText11() {
        return text11;
    }

    /**
     * 本文１１設定<br>
     * <br>
     * 本文１１を引数で設定する<br>
     * <br>
     * @param text11
     */
    public void setText11(String text11) {
        this.text11 = text11;
    }

    /**
     * 本文１２取得<br>
     * <br>
     * 本文１２を戻り値で返却する<br>
     * <br>
     * @return text12
     */
    public String getText12() {
        return text12;
    }

    /**
     * 本文１２設定<br>
     * <br>
     * 本文１２を引数で設定する<br>
     * <br>
     * @param text12
     */
    public void setText12(String text12) {
        this.text12 = text12;
    }

    /**
     * 本文１３取得<br>
     * <br>
     * 本文１３を戻り値で返却する<br>
     * <br>
     * @return text13
     */
    public String getText13() {
        return text13;
    }

    /**
     * 本文１３設定<br>
     * <br>
     * 本文１３を引数で設定する<br>
     * <br>
     * @param text13
     */
    public void setText13(String text13) {
        this.text13 = text13;
    }

    /**
     * 本文１４取得<br>
     * <br>
     * 本文１４を戻り値で返却する<br>
     * <br>
     * @return text14
     */
    public String getText14() {
        return text14;
    }

    /**
     * 本文１４設定<br>
     * <br>
     * 本文１４を引数で設定する<br>
     * <br>
     * @param text14
     */
    public void setText14(String text14) {
        this.text14 = text14;
    }

    /**
     * 本文１５取得<br>
     * <br>
     * 本文１５を戻り値で返却する<br>
     * <br>
     * @return text15
     */
    public String getText15() {
        return text15;
    }

    /**
     * 本文１５設定<br>
     * <br>
     * 本文１５を引数で設定する<br>
     * <br>
     * @param text15
     */
    public void setText15(String text15) {
        this.text15 = text15;
    }

    /**
     * 本文１６取得<br>
     * <br>
     * 本文１６を戻り値で返却する<br>
     * <br>
     * @return text16
     */
    public String getText16() {
        return text16;
    }

    /**
     * 本文１６設定<br>
     * <br>
     * 本文１６を引数で設定する<br>
     * <br>
     * @param text16
     */
    public void setText16(String text16) {
        this.text16 = text16;
    }

    /**
     * 本文１７取得<br>
     * <br>
     * 本文１７を戻り値で返却する<br>
     * <br>
     * @return text17
     */
    public String getText17() {
        return text17;
    }

    /**
     * 本文１７設定<br>
     * <br>
     * 本文１７を引数で設定する<br>
     * <br>
     * @param text17
     */
    public void setText17(String text17) {
        this.text17 = text17;
    }

    /**
     * 本文１８取得<br>
     * <br>
     * 本文１８を戻り値で返却する<br>
     * <br>
     * @return text18
     */
    public String getText18() {
        return text18;
    }

    /**
     * 本文１８設定<br>
     * <br>
     * 本文１８を引数で設定する<br>
     * <br>
     * @param text18
     */
    public void setText18(String text18) {
        this.text18 = text18;
    }

    /**
     * 本文１９取得<br>
     * <br>
     * 本文１９を戻り値で返却する<br>
     * <br>
     * @return text19
     */
    public String getText19() {
        return text19;
    }

    /**
     * 本文１９設定<br>
     * <br>
     * 本文１９を引数で設定する<br>
     * <br>
     * @param text19
     */
    public void setText19(String text19) {
        this.text19 = text19;
    }

    /**
     * 本文２０取得<br>
     * <br>
     * 本文２０を戻り値で返却する<br>
     * <br>
     * @return text20
     */
    public String getText20() {
        return text20;
    }

    /**
     * 本文２０設定<br>
     * <br>
     * 本文２０を引数で設定する<br>
     * <br>
     * @param text20
     */
    public void setText20(String text20) {
        this.text20 = text20;
    }

    /**
     * 本文２１取得<br>
     * <br>
     * 本文２１を戻り値で返却する<br>
     * <br>
     * @return text21
     */
    public String getText21() {
        return text21;
    }

    /**
     * 本文２１設定<br>
     * <br>
     * 本文２１を引数で設定する<br>
     * <br>
     * @param text21
     */
    public void setText21(String text21) {
        this.text21 = text21;
    }

    /**
     * 本文２２取得<br>
     * <br>
     * 本文２２を戻り値で返却する<br>
     * <br>
     * @return text22
     */
    public String getText22() {
        return text22;
    }

    /**
     * 本文２２設定<br>
     * <br>
     * 本文２２を引数で設定する<br>
     * <br>
     * @param text22
     */
    public void setText22(String text22) {
        this.text22 = text22;
    }

    /**
     * 本文２３取得<br>
     * <br>
     * 本文２３を戻り値で返却する<br>
     * <br>
     * @return text23
     */
    public String getText23() {
        return text23;
    }

    /**
     * 本文２３設定<br>
     * <br>
     * 本文２３を引数で設定する<br>
     * <br>
     * @param text23
     */
    public void setText23(String text23) {
        this.text23 = text23;
    }

    /**
     * 本文２４取得<br>
     * <br>
     * 本文２４を戻り値で返却する<br>
     * <br>
     * @return text24
     */
    public String getText24() {
        return text24;
    }

    /**
     * 本文２４設定<br>
     * <br>
     * 本文２４を引数で設定する<br>
     * <br>
     * @param text24
     */
    public void setText24(String text24) {
        this.text24 = text24;
    }

    /**
     * 本文２５取得<br>
     * <br>
     * 本文２５を戻り値で返却する<br>
     * <br>
     * @return text25
     */
    public String getText25() {
        return text25;
    }

    /**
     * 本文２５設定<br>
     * <br>
     * 本文２５を引数で設定する<br>
     * <br>
     * @param text25
     */
    public void setText25(String text25) {
        this.text25 = text25;
    }

    /**
     * 本文２６取得<br>
     * <br>
     * 本文２６を戻り値で返却する<br>
     * <br>
     * @return text26
     */
    public String getText26() {
        return text26;
    }

    /**
     * 本文２６設定<br>
     * <br>
     * 本文２６を引数で設定する<br>
     * <br>
     * @param text26
     */
    public void setText26(String text26) {
        this.text26 = text26;
    }

    /**
     * 本文２７取得<br>
     * <br>
     * 本文２７を戻り値で返却する<br>
     * <br>
     * @return text27
     */
    public String getText27() {
        return text27;
    }

    /**
     * 本文２７設定<br>
     * <br>
     * 本文２７を引数で設定する<br>
     * <br>
     * @param text27
     */
    public void setText27(String text27) {
        this.text27 = text27;
    }

    /**
     * 本文２８取得<br>
     * <br>
     * 本文２８を戻り値で返却する<br>
     * <br>
     * @return text28
     */
    public String getText28() {
        return text28;
    }

    /**
     * 本文２８設定<br>
     * <br>
     * 本文２８を引数で設定する<br>
     * <br>
     * @param text28
     */
    public void setText28(String text28) {
        this.text28 = text28;
    }

    /**
     * 本文２９取得<br>
     * <br>
     * 本文２９を戻り値で返却する<br>
     * <br>
     * @return text29
     */
    public String getText29() {
        return text29;
    }

    /**
     * 本文２９設定<br>
     * <br>
     * 本文２９を引数で設定する<br>
     * <br>
     * @param text29
     */
    public void setText29(String text29) {
        this.text29 = text29;
    }

    /**
     * 本文３０取得<br>
     * <br>
     * 本文３０を戻り値で返却する<br>
     * <br>
     * @return text30
     */
    public String getText30() {
        return text30;
    }

    /**
     * 本文３０設定<br>
     * <br>
     * 本文３０を引数で設定する<br>
     * <br>
     * @param text30
     */
    public void setText30(String text30) {
        this.text30 = text30;
    }

    /**
     * 本文３１取得<br>
     * <br>
     * 本文３１を戻り値で返却する<br>
     * <br>
     * @return text31
     */
    public String getText31() {
        return text31;
    }

    /**
     * 本文３１設定<br>
     * <br>
     * 本文３１を引数で設定する<br>
     * <br>
     * @param text31
     */
    public void setText31(String text31) {
        this.text31 = text31;
    }

    /**
     * 本文３２取得<br>
     * <br>
     * 本文３２を戻り値で返却する<br>
     * <br>
     * @return text32
     */
    public String getText32() {
        return text32;
    }

    /**
     * 本文３２設定<br>
     * <br>
     * 本文３２を引数で設定する<br>
     * <br>
     * @param text32
     */
    public void setText32(String text32) {
        this.text32 = text32;
    }

    /**
     * 本文３３取得<br>
     * <br>
     * 本文３３を戻り値で返却する<br>
     * <br>
     * @return text33
     */
    public String getText33() {
        return text33;
    }

    /**
     * 本文３３設定<br>
     * <br>
     * 本文３３を引数で設定する<br>
     * <br>
     * @param text33
     */
    public void setText33(String text33) {
        this.text33 = text33;
    }

    /**
     * 本文３４取得<br>
     * <br>
     * 本文３４を戻り値で返却する<br>
     * <br>
     * @return text34
     */
    public String getText34() {
        return text34;
    }

    /**
     * 本文３４設定<br>
     * <br>
     * 本文３４を引数で設定する<br>
     * <br>
     * @param text34
     */
    public void setText34(String text34) {
        this.text34 = text34;
    }

    /**
     * 本文３５取得<br>
     * <br>
     * 本文３５を戻り値で返却する<br>
     * <br>
     * @return text35
     */
    public String getText35() {
        return text35;
    }

    /**
     * 本文３５設定<br>
     * <br>
     * 本文３５を引数で設定する<br>
     * <br>
     * @param text35
     */
    public void setText35(String text35) {
        this.text35 = text35;
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

}
