package com.naikara_talk.model;

import java.util.List;

import com.naikara_talk.dto.UserMstTeacherMstDto;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>講師スケジュール。<br>
 * <b>クラス名称       :</b>講師スケジュールModelクラス。<br>
 * <b>クラス概要       :</b>講師スケジュールModel。<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/09 TECS 新規作成
 */
public class TeacherSelectionListModel implements Model {

    private static final long serialVersionUID = 1L;

    /** 講師ID */
    private String teacherId;
    /** 講師名(フリガナ) */
    private String teacherFurigana;
    /** 一覧リスト */
    private List<UserMstTeacherMstDto> userMstTeacherMstDto;

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
     * @return teacherFurigana
     */
    public String getTeacherFurigana() {
        return teacherFurigana;
    }

    /**
     * @param teacherFurigana セットする teacherFurigana
     */
    public void setTeacherFurigana(String teacherFurigana) {
        this.teacherFurigana = teacherFurigana;
    }

    /**
     * @return userMstTeacherMstDto
     */
    public List<UserMstTeacherMstDto> getUserMstTeacherMstDto() {
        return userMstTeacherMstDto;
    }

    /**
     * @param userMstTeacherMstDto セットする userMstTeacherMstDto
     */
    public void setUserMstTeacherMstDto(List<UserMstTeacherMstDto> userMstTeacherMstDto) {
        this.userMstTeacherMstDto = userMstTeacherMstDto;
    }

}