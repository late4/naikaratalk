package com.naikara_talk.sessiondata;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称 :</b>お客様(個人)_予約処理<br>
 * <b>クラス名称　　　:</b>予約スケジュールのセッション情報クラス。<br>
 * <b>クラス概要　　　:</b>コース名選択ページの戻る用のセッション情報。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b> 2013/07/25 TECS 新規作成。
 */
public class SessionReservationCancellationTeacherSchedule implements SessionData {

    /** ヘッダの戻るリンク用のSessionKey */
    private static final String HEADER_RETURN_KEY = "ReservationCancellationTeacherSchedule";

    /** 講師ID */
    private String teacherId;

    /** 日数 */
    private int dayCnt;

    /** 朝・午前のレッスンから選択されたチェックBOX */
    private String[] details0;

    /** 朝・午前のレッスンから選択されたチェックBOX */
    private String[] details1;

    /** 午後・夕方のレッスンから選択されたチェックBOX */
    private String[] details2;

    /** 夕方・夜のレッスンから選択されたチェックBOX */
    private String[] details3;

    /** 深夜・早朝のレッスン表示フラグ */
    private boolean displayFlg0;

    /** 朝・午前のレッスン表示フラグ */
    private boolean displayFlg1;

    /** 午後・夕方のレッスン表示フラグ */
    private boolean displayFlg2;

    /** 夕方・夜のレッスン表示フラグ */
    private boolean displayFlg3;

    /**
     * このセッションデータのキーを返却する
     */
    public static String getKey() {
        return HEADER_RETURN_KEY;
    }

    /**
     * @return teacherId
     */
    public String getTeacherId() {
        return teacherId;
    }

    /**
     * @param teacherId セットする teacherId
     */
    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    /**
     * @return dayCnt
     */
    public int getDayCnt() {
        return dayCnt;
    }

    /**
     * @param dayCnt セットする dayCnt
     */
    public void setDayCnt(int dayCnt) {
        this.dayCnt = dayCnt;
    }

    /**
     * @return details0
     */
    public String[] getDetails0() {
        return details0;
    }

    /**
     * @param details0 セットする details0
     */
    public void setDetails0(String[] details0) {
        this.details0 = details0;
    }

    /**
     * @return details1
     */
    public String[] getDetails1() {
        return details1;
    }

    /**
     * @param details1 セットする details1
     */
    public void setDetails1(String[] details1) {
        this.details1 = details1;
    }

    /**
     * @return details2
     */
    public String[] getDetails2() {
        return details2;
    }

    /**
     * @param details2 セットする details2
     */
    public void setDetails2(String[] details2) {
        this.details2 = details2;
    }

    /**
     * @return details3
     */
    public String[] getDetails3() {
        return details3;
    }

    /**
     * @param details3 セットする details3
     */
    public void setDetails3(String[] details3) {
        this.details3 = details3;
    }

    /**
     * @return displayFlg0
     */
    public boolean getDisplayFlg0() {
        return displayFlg0;
    }

    /**
     * @param displayFlg0 セットする displayFlg0
     */
    public void setDisplayFlg0(boolean displayFlg0) {
        this.displayFlg0 = displayFlg0;
    }

    /**
     * @return displayFlg1
     */
    public boolean getDisplayFlg1() {
        return displayFlg1;
    }

    /**
     * @param displayFlg1 セットする displayFlg1
     */
    public void setDisplayFlg1(boolean displayFlg1) {
        this.displayFlg1 = displayFlg1;
    }

    /**
     * @return displayFlg2
     */
    public boolean getDisplayFlg2() {
        return displayFlg2;
    }

    /**
     * @param displayFlg2 セットする displayFlg2
     */
    public void setDisplayFlg2(boolean displayFlg2) {
        this.displayFlg2 = displayFlg2;
    }

    /**
     * @return displayFlg3
     */
    public boolean getDisplayFlg3() {
        return displayFlg3;
    }

    /**
     * @param displayFlg3 セットする displayFlg3
     */
    public void setDisplayFlg3(boolean displayFlg3) {
        this.displayFlg3 = displayFlg3;
    }

}
