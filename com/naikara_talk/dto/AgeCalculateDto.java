package com.naikara_talk.dto;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称:</b>共通部品 DTOクラス<br>
 * <b>クラス名称　　　:</b>年齢成人区分クラス<br>
 * <b>クラス概要　　　:</b>年齢成人区分DTO<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/05/30 TECS 新規作成
 */
public class AgeCalculateDto extends AbstractDto {

    private int age;        // 年齢
    private int adult;      // 成人区別(0:未成年、1:成人)
    private int returnCode; // リターンコード

    private String birth;   // 生年月日

    /**
     * 年齢取得<br>
     * <br>
     * 年齢を戻り値で返却する<br>
     * <br>
     * @return age
     */
    public int getAge() {
        return age;
    }

    /**
     * 年齢設定<br>
     * <br>
     * 年齢を引数で設定する<br>
     * <br>
     * @param age
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * 成人区別取得<br>
     * <br>
     * 成人区別を戻り値で返却する<br>
     * <br>
     * @return adult
     */
    public int getAdult() {
        return adult;
    }

    /**
     * 成人区別設定<br>
     * <br>
     * 成人区別を引数で設定する<br>
     * <br>
     * @param adult
     */
    public void setAdult(int adult) {
        this.adult = adult;
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
     * リターンコード取得<br>
     * <br>
     * リターンコードを戻り値で返却する<br>
     * <br>
     * @return birth
     */
    public String getBirth() {
        return birth;
    }

    /**
     * リターンコード設定<br>
     * <br>
     * リターンコードを引数で設定する<br>
     * <br>
     * @param birth
     */
    public void setBirth(String birth) {
        this.birth = birth;
    }

}
