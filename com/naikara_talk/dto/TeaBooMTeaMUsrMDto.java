package com.naikara_talk.dto;

import java.util.List;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称:</b>共通部品 DTOクラス<br>
 * <b>クラス名称　　　:</b>受講者別講師ブックマークテーブル、講師マスタ、利用者マスタクラス<br>
 * <b>クラス概要　　　:</b>受講者別講師ブックマークテーブル、講師マスタ、利用者マスタDTO<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/08/13 TECS 新規作成
 */
public class TeaBooMTeaMUsrMDto extends AbstractDto {

    private String studentId;                               // 受講者ID
    private String userId;                                  // 講師ID (利用者ID)
    private String nickAnm;                                 // 講師名(ニックネーム）
    private String genderKbnNm;                             // 性別区分名
    private String sellingPoint;                            // セールスポイント(スクール記入)
    private String selfRecommendation;                      // 受講生へのアピールポイント
    private int recordVerNo;                                // レコードバージョン番号
    private List<TeacherCourseDto> teacherCourseDtoList;    // 講師別コースマスタ(+コースマスタ)DTOリスト
    private int returnCode;                                 // リターンコード

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
     * 講師ID (利用者ID)取得<br>
     * <br>
     * 講師ID (利用者ID)を戻り値で返却する<br>
     * <br>
     * @return userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 講師ID (利用者ID)設定<br>
     * <br>
     * 講師ID (利用者ID)を引数で設定する<br>
     * <br>
     * @param userId
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 講師名(ニックネーム）取得<br>
     * <br>
     * 講師名(ニックネーム）を戻り値で返却する<br>
     * <br>
     * @return nickAnm
     */
    public String getNickAnm() {
        return nickAnm;
    }

    /**
     * 講師名(ニックネーム）設定<br>
     * <br>
     * 講師名(ニックネーム）を引数で設定する<br>
     * <br>
     * @param nickAnm
     */
    public void setNickAnm(String nickAnm) {
        this.nickAnm = nickAnm;
    }

    /**
     * 性別区分名取得<br>
     * <br>
     * 性別区分名を戻り値で返却する<br>
     * <br>
     * @return genderKbnNm
     */
    public String getGenderKbnNm() {
        return genderKbnNm;
    }

    /**
     * 性別区分名設定<br>
     * <br>
     * 性別区分名を引数で設定する<br>
     * <br>
     * @param genderKbnNm
     */
    public void setGenderKbnNm(String genderKbnNm) {
        this.genderKbnNm = genderKbnNm;
    }

    /**
     * セールスポイント(スクール記入)取得<br>
     * <br>
     * セールスポイント(スクール記入)を戻り値で返却する<br>
     * <br>
     * @return sellingPoint
     */
    public String getSellingPoint() {
        return sellingPoint;
    }

    /**
     * セールスポイント(スクール記入)設定<br>
     * <br>
     * セールスポイント(スクール記入)を引数で設定する<br>
     * <br>
     * @param sellingPoint
     */
    public void setSellingPoint(String sellingPoint) {
        this.sellingPoint = sellingPoint;
    }

    /**
     * 受講生へのアピールポイント取得<br>
     * <br>
     * 受講生へのアピールポイントを戻り値で返却する<br>
     * <br>
     * @return selfRecommendation
     */
    public String getSelfRecommendation() {
        return selfRecommendation;
    }

    /**
     * 受講生へのアピールポイント設定<br>
     * <br>
     * 受講生へのアピールポイントを引数で設定する<br>
     * <br>
     * @param selfRecommendation
     */
    public void setSelfRecommendation(String selfRecommendation) {
        this.selfRecommendation = selfRecommendation;
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
     * 講師別コースマスタ(+コースマスタ)DTOリスト取得<br>
     * <br>
     * 講師別コースマスタ(+コースマスタ)DTOリストを戻り値で返却する<br>
     * <br>
     * @return teacherCourseDtoList
     */
    public List<TeacherCourseDto> getTeacherCourseDtoList() {
        return teacherCourseDtoList;
    }

    /**
     * 講師別コースマスタ(+コースマスタ)DTOリスト設定<br>
     * <br>
     * 講師別コースマスタ(+コースマスタ)DTOリストを引数で設定する<br>
     * <br>
     * @param teacherCourseDtoList
     */
    public void setTeacherCourseDtoList(List<TeacherCourseDto> teacherCourseDtoList) {
        this.teacherCourseDtoList = teacherCourseDtoList;
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
