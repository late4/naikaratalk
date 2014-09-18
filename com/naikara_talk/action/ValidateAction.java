package com.naikara_talk.action;

import java.sql.SQLException;

import org.apache.struts2.interceptor.validation.SkipValidation;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.service.ValidateService;

/**
 * バリデータチェックサムプル
 *
 * @author lich
 *
 */
public class ValidateAction extends NaikaraActionSupport {

    private static final long serialVersionUID = 1L;

    @SkipValidation
    public String init() {

        this.addActionMessage("welcome!");

        return SUCCESS;
    }

    public String requestService() throws NaiException {

        ValidateService service = new ValidateService();
        // service.insert();
        this.addActionMessage("service invoke succuss!");

        return SUCCESS;
    }

    public String insert() throws SQLException, NaiException {

        this.addActionMessage("insert succuss!");

        return SUCCESS;
    }

    // userId
    private String userId;
    // userName
    private String userName;
    // ユーザ区分
    private String userFlg = "2";
    // comment
    private String comment;
    // password
    private String password;
    // number1
    private String number1;
    // number2
    private String number2;
    // zenkaku
    private String zenkaku;
    // hankaku
    private String hankaku;
    // zenkakukana
    private String zenkakukana;
    // en1
    private String en1;
    // en2
    private String en2;
    // date1
    private String date1;

    /**
     * @return userFlg
     */
    public String getUserFlg() {
        return userFlg;
    }

    /**
     * @param userFlg セットする userFlg
     */
    public void setUserFlg(String userFlg) {
        this.userFlg = userFlg;
    }

    /**
     * @return userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId
     *            セットする userId
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName
     *            セットする userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * @param comment
     *            セットする comment
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     *            セットする password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return zenkaku
     */
    public String getZenkaku() {
        return zenkaku;
    }

    /**
     * @param zenkaku
     *            セットする zenkaku
     */
    public void setZenkaku(String zenkaku) {
        this.zenkaku = zenkaku;
    }

    /**
     * @return hankaku
     */
    public String getHankaku() {
        return hankaku;
    }

    /**
     * @param hankaku
     *            セットする hankaku
     */
    public void setHankaku(String hankaku) {
        this.hankaku = hankaku;
    }

    /**
     * @return zenkakukana
     */
    public String getZenkakukana() {
        return zenkakukana;
    }

    /**
     * @param zenkakukana
     *            セットする zenkakukana
     */
    public void setZenkakukana(String zenkakukana) {
        this.zenkakukana = zenkakukana;
    }

    /**
     * @return en1
     */
    public String getEn1() {
        return en1;
    }

    /**
     * @param en1
     *            セットする en1
     */
    public void setEn1(String en1) {
        this.en1 = en1;
    }

    /**
     * @return en2
     */
    public String getEn2() {
        return en2;
    }

    /**
     * @param en2
     *            セットする en2
     */
    public void setEn2(String en2) {
        this.en2 = en2;
    }

    /**
     * @return number1
     */
    public String getNumber1() {
        return number1;
    }

    /**
     * @param number1
     *            セットする number1
     */
    public void setNumber1(String number1) {
        this.number1 = number1;
    }

    /**
     * @return number2
     */
    public String getNumber2() {
        return number2;
    }

    /**
     * @param number2
     *            セットする number2
     */
    public void setNumber2(String number2) {
        this.number2 = number2;
    }

    /**
     * @return date1
     */
    public String getDate1() {
        return date1;
    }

    /**
     * @param date1
     *            セットする date1
     */
    public void setDate1(String date1) {
        this.date1 = date1;
    }

}
