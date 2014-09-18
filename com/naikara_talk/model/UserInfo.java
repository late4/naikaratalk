package com.naikara_talk.model;

import java.io.Serializable;

public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    // ユーザID
    private String userId;

    // ユーザ名
    private String userName;

    // ユーザ区分（Nスクール限定の画面、T講師用画面、C受講者用画面、B法人用画面）
    private String userFlg;

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
     * @param userId セットする userId
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
     * @param userName セットする userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

}
