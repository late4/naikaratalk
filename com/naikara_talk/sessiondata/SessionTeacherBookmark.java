package com.naikara_talk.sessiondata;


/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>お客様(個人)_予約処理<br>
 * <b>クラス名称　　　:</b>ブックマーク済の講師一覧（Pop）のセッション情報クラス。<br>
 * <b>クラス概要　　　:</b>予約スケジュールの戻る用のセッション情報。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b> 2013/07/19 TECS 新規作成。
 */
public class SessionTeacherBookmark implements SessionData {

    /** ヘッダの戻るリンク用のSessionKey */
    private static final String HEADER_RETURN_KEY = "TeacherBookmarkList";

    /** 受講者ID */
    private String studentId;

    /** 一覧から選択された明細データ */
    private String selected;

    /**
     * このセッションデータのキーを返却する
     */
    public static String getKey() {
        return HEADER_RETURN_KEY;
    }

    /**
     * @return studentId
     */
    public String getStudentId() {
        return studentId;
    }

    /**
     * @param studentId セットする studentId
     */
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    /**
     * @return selected
     */
    public String getSelected() {
        return selected;
    }

    /**
     * @param selected セットする selected
     */
    public void setSelected(String selected) {
        this.selected = selected;
    }

}
