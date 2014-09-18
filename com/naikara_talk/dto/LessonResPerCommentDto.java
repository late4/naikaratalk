package com.naikara_talk.dto;

import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.Timestamp;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称:</b>共通部品 DTOクラス<br>
 * <b>クラス名称　　　:</b>レッスン予実、レッスンコメントテーブルクラス<br>
 * <b>クラス概要　　　:</b>レッスン予実、レッスンコメント、テーブルDTO<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/06/05 TECS 新規作成
 */
public class LessonResPerCommentDto extends AbstractDto {

    private String reservationNo;            // 予約No
    private String stateKbn;                 // 状態区分
    private String studentId;                // 受講者ID
    private String studentNickNm;            // 受講者ニックネーム
    private String teacherId;                // 担当講師ID
    private String teacherNickNm;            // 担当講師ニックネーム
    private String lessonDt;                 // レッスン日
    private String lessonTmCd;               // レッスン時刻コード
    private String lessonTmNm;               // レッスン時刻名
    private String bigClassificationJcd;     // 大分類コード
    private String bigClassificationJnm;     // 大分類名
    private String middleClassificationJcd;  // 中分類コード
    private String middleClassificationJnm;  // 中分類名
    private String smallClassificationJcd;   // 小分類コード
    private String smallClassificationJnm;   // 小分類名
    private String bigClassificationEcd;     // 大分類コード(英語)
    private String bigClassificationEnm;     // 大分類名(英語)
    private String middleClassificationEcd;  // 中分類コード(英語)
    private String middleClassificationEnm;  // 中分類名(英語)
    private String smallClassificationEcd;   // 小分類コード(英語)
    private String smallClassificationEnm;   // 小分類名(英語)
    private String courseCd;                 // コースコード
    private String courseJnm;                // コース名
    private String courseEnm;                // コース名(英語)
    private BigDecimal paymentUsePoint;      // 有償利用ポイント
    private BigDecimal freeUsePoint;         // 無償利用ポイント
    private BigDecimal usePointSum;          // 利用ポイント合計
    private String mailKbn;                  // 添付メール送付済区分
    private String mailDt;                   // 添付メール送付日

    // 2014/01/15 FlashからTokboxに変更対応 start
    //private String roomNo;                   // 教室No
    private String tokboxSessionId;          // Tokbox用セッションID
    private String tokboxTokenId;            // Tokbox用トークンID
    private Blob fileDuringLessons;        // レッスンファイル
    // 2014/01/15 FlashからTokboxに変更対応 end

    private String teacherSessionId;         // 講師セッションID
    private String studentSessionId;         // 受講者セッションID
    private Timestamp studentInDttm;         // 受講者入室時刻
    private Timestamp studentStartDttm;      // 受講者START押下時刻
    private Timestamp studentEndDttm;        // 受講者END押下時刻
    private String studentLessonKbn;         // 受講者レッスン区分
    private Timestamp teacherInDttm;         // 講師入室時刻
    private Timestamp teacherStartDttm;      // 講師START押下時刻
    private Timestamp teacherEndDttm;        // 講師END押下時刻
    private Timestamp teacherNextDttm;       // 講師NEXT押下時刻
    private String teacherLessonKbn;         // 講師レッスン区分
    private Timestamp lessonStartDttm;       // レッスンSTART時刻
    private Timestamp lessonEndDttm;         // レッスンEND時刻
    private int recordVerNo;                 // レコードバージョン番号
    private Timestamp insertDttm;            // 登録日時
    private String insertCd;                 // 登録者コード
    private Timestamp updateDttm;            // 更新日時
    private String updateCd;                 // 更新者コード
    private int returnCode;                  // リターンコード

    private String tSelfEvaluation1Kbn;      // 講師：講師のレッスン自己分析１区分
    private String tSelfEvaluation1Enm;      // 講師：講師のレッスン自己分析１
    private String tSelfEvaluation2Kbn;      // 講師：講師のレッスン自己分析２区分
    private String tSelfEvaluation2Enm;      // 講師：講師のレッスン自己分析２
    private String tPositiveCmt;             // 講師：受講者への前向きコメント
    private String tOnSchoolCmt;             // 講師：講師からスクールへのコメント
    private String tRecommendedLevelKbn;     // 講師：推奨レベル区分
    private String tRecommendedLevelEnm;     // 講師：推奨レベル
    private String tNextTeacherCmt;          // 講師：受講者を引継ぎするコメント
    private String cEvaluationKbn;           // 受講者：講師への評価区分
    private String cEvaluationJnm;           // 受講者：講師への評価
    private String cOnTeacherCmt;            // 受講者：レッスンに対する講師への意見コメント
    private String sOnTeacherCmt;            // スクール：スクール側から講師へのコメント
    private int recordVerNoLCT;              // レッスンコメントテーブル レコードバージョン番号
    private int recordVerNoForTeacher;       // レコードバージョン番号(講師)
    private int recordVerNoForStudent;       // レコードバージョン番号(受講者)
    private int recordVerNoForSchool;        // レコードバージョン番号(スクール)

    private String tPositiveCmtCut;          // 講師：受講者への前向きコメントの上37バイト

    /**
     * レコードバージョン番号(スクール)取得<br>
     * <br>
     * レコードバージョン番号(スクール)を戻り値で返却する<br>
     * <br>
     * @return recordVerNoForSchool
     */
    public int getRecordVerNoForSchool() {
        return recordVerNoForSchool;
    }

    /**
     * レコードバージョン番号(スクール)設定<br>
     * <br>
     * レコードバージョン番号(スクール)を引数で設定する<br>
     * <br>
     * @param recordVerNoForSchool
     */
    public void setRecordVerNoForSchool(int recordVerNoForSchool) {
        this.recordVerNoForSchool = recordVerNoForSchool;
    }

    /**
     * レコードバージョン番号(受講者)取得<br>
     * <br>
     * レコードバージョン番号(受講者)を戻り値で返却する<br>
     * <br>
     * @return recordVerNoForStudent
     */
    public int getRecordVerNoForStudent() {
        return recordVerNoForStudent;
    }

    /**
     * レコードバージョン番号(受講者)設定<br>
     * <br>
     * レコードバージョン番号(受講者)を引数で設定する<br>
     * <br>
     * @param recordVerNoForStudent
     */
    public void setRecordVerNoForStudent(int recordVerNoForStudent) {
        this.recordVerNoForStudent = recordVerNoForStudent;
    }

    /**
     * レコードバージョン番号(講師)取得<br>
     * <br>
     * レコードバージョン番号(講師)を戻り値で返却する<br>
     * <br>
     * @return recordVerNoForTeacher
     */
    public int getRecordVerNoForTeacher() {
        return recordVerNoForTeacher;
    }

    /**
     * レコードバージョン番号(講師)設定<br>
     * <br>
     * レコードバージョン番号(講師)を引数で設定する<br>
     * <br>
     * @param recordVerNoForTeacher
     */
    public void setRecordVerNoForTeacher(int recordVerNoForTeacher) {
        this.recordVerNoForTeacher = recordVerNoForTeacher;
    }

    /**
     * 予約No取得<br>
     * <br>
     * 予約Noを戻り値で返却する<br>
     * <br>
     * @return reservationNo
     */
    public String getReservationNo() {
        return reservationNo;
    }

    /**
     * 予約No設定<br>
     * <br>
     * 予約Noを引数で設定する<br>
     * <br>
     * @param reservationNo
     */
    public void setReservationNo(String reservationNo) {
        this.reservationNo = reservationNo;
    }

    /**
     * 状態区分取得<br>
     * <br>
     * 状態区分を戻り値で返却する<br>
     * <br>
     * @return stateKbn
     */
    public String getStateKbn() {
        return stateKbn;
    }

    /**
     * 状態区分設定<br>
     * <br>
     * 状態区分を引数で設定する<br>
     * <br>
     * @param stateKbn
     */
    public void setStateKbn(String stateKbn) {
        this.stateKbn = stateKbn;
    }

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
     * 受講者ニックネーム取得<br>
     * <br>
     * 受講者ニックネームを戻り値で返却する<br>
     * <br>
     * @return studentNickNm
     */
    public String getStudentNickNm() {
        return studentNickNm;
    }

    /**
     * 受講者ニックネーム設定<br>
     * <br>
     * 受講者ニックネームを引数で設定する<br>
     * <br>
     * @param studentNickNm
     */
    public void setStudentNickNm(String studentNickNm) {
        this.studentNickNm = studentNickNm;
    }

    /**
     * 担当講師ID取得<br>
     * <br>
     * 担当講師IDを戻り値で返却する<br>
     * <br>
     * @return teacherId
     */
    public String getTeacherId() {
        return teacherId;
    }

    /**
     * 担当講師ID設定<br>
     * <br>
     * 担当講師IDを引数で設定する<br>
     * <br>
     * @param teacherId
     */
    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    /**
     * 担当講師ニックネーム取得<br>
     * <br>
     * 担当講師ニックネームを戻り値で返却する<br>
     * <br>
     * @return teacherNickNm
     */
    public String getTeacherNickNm() {
        return teacherNickNm;
    }

    /**
     * 担当講師ニックネーム設定<br>
     * <br>
     * 担当講師ニックネームを引数で設定する<br>
     * <br>
     * @param teacherNickNm
     */
    public void setTeacherNickNm(String teacherNickNm) {
        this.teacherNickNm = teacherNickNm;
    }

    /**
     * レッスン日取得<br>
     * <br>
     * レッスン日を戻り値で返却する<br>
     * <br>
     * @return lessonDt
     */
    public String getLessonDt() {
        return lessonDt;
    }

    /**
     * レッスン日設定<br>
     * <br>
     * レッスン日を引数で設定する<br>
     * <br>
     * @param lessonDt
     */
    public void setLessonDt(String lessonDt) {
        this.lessonDt = lessonDt;
    }

    /**
     * レッスン時刻コード取得<br>
     * <br>
     * レッスン時刻コードを戻り値で返却する<br>
     * <br>
     * @return lessonTmCd
     */
    public String getLessonTmCd() {
        return lessonTmCd;
    }

    /**
     * レッスン時刻コード設定<br>
     * <br>
     * レッスン時刻コードを引数で設定する<br>
     * <br>
     * @param lessonTmCd
     */
    public void setLessonTmCd(String lessonTmCd) {
        this.lessonTmCd = lessonTmCd;
    }

    /**
     * レッスン時刻名取得<br>
     * <br>
     * レッスン時刻名を戻り値で返却する<br>
     * <br>
     * @return lessonTmNm
     */
    public String getLessonTmNm() {
        return lessonTmNm;
    }

    /**
     * レッスン時刻名設定<br>
     * <br>
     * レッスン時刻名を引数で設定する<br>
     * <br>
     * @param lessonTmNm
     */
    public void setLessonTmNm(String lessonTmNm) {
        this.lessonTmNm = lessonTmNm;
    }

    /**
     * 大分類コード取得<br>
     * <br>
     * 大分類コードを戻り値で返却する<br>
     * <br>
     * @return bigClassificationJcd
     */
    public String getBigClassificationJcd() {
        return bigClassificationJcd;
    }

    /**
     * 大分類コード設定<br>
     * <br>
     * 大分類コードを引数で設定する<br>
     * <br>
     * @param bigClassificationJcd
     */
    public void setBigClassificationJcd(String bigClassificationJcd) {
        this.bigClassificationJcd = bigClassificationJcd;
    }

    /**
     * 大分類名取得<br>
     * <br>
     * 大分類名を戻り値で返却する<br>
     * <br>
     * @return bigClassificationJnm
     */
    public String getBigClassificationJnm() {
        return bigClassificationJnm;
    }

    /**
     * 大分類名設定<br>
     * <br>
     * 大分類名を引数で設定する<br>
     * <br>
     * @param bigClassificationJnm
     */
    public void setBigClassificationJnm(String bigClassificationJnm) {
        this.bigClassificationJnm = bigClassificationJnm;
    }

    /**
     * 中分類コード取得<br>
     * <br>
     * 中分類コードを戻り値で返却する<br>
     * <br>
     * @return middleClassificationJcd
     */
    public String getMiddleClassificationJcd() {
        return middleClassificationJcd;
    }

    /**
     * 中分類コード設定<br>
     * <br>
     * 中分類コードを引数で設定する<br>
     * <br>
     * @param middleClassificationJcd
     */
    public void setMiddleClassificationJcd(String middleClassificationJcd) {
        this.middleClassificationJcd = middleClassificationJcd;
    }

    /**
     * 中分類名取得<br>
     * <br>
     * 中分類名を戻り値で返却する<br>
     * <br>
     * @return middleClassificationJnm
     */
    public String getMiddleClassificationJnm() {
        return middleClassificationJnm;
    }

    /**
     * 中分類名設定<br>
     * <br>
     * 中分類名を引数で設定する<br>
     * <br>
     * @param middleClassificationJnm
     */
    public void setMiddleClassificationJnm(String middleClassificationJnm) {
        this.middleClassificationJnm = middleClassificationJnm;
    }

    /**
     * 小分類コード取得<br>
     * <br>
     * 小分類コードを戻り値で返却する<br>
     * <br>
     * @return smallClassificationJcd
     */
    public String getSmallClassificationJcd() {
        return smallClassificationJcd;
    }

    /**
     * 小分類コード設定<br>
     * <br>
     * 小分類コードを引数で設定する<br>
     * <br>
     * @param smallClassificationJcd
     */
    public void setSmallClassificationJcd(String smallClassificationJcd) {
        this.smallClassificationJcd = smallClassificationJcd;
    }

    /**
     * 小分類名取得<br>
     * <br>
     * 小分類名を戻り値で返却する<br>
     * <br>
     * @return smallClassificationJnm
     */
    public String getSmallClassificationJnm() {
        return smallClassificationJnm;
    }

    /**
     * 小分類名設定<br>
     * <br>
     * 小分類名を引数で設定する<br>
     * <br>
     * @param smallClassificationJnm
     */
    public void setSmallClassificationJnm(String smallClassificationJnm) {
        this.smallClassificationJnm = smallClassificationJnm;
    }

    /**
     * 大分類コード(英語)取得<br>
     * <br>
     * 大分類コード(英語)を戻り値で返却する<br>
     * <br>
     * @return bigClassificationEcd
     */
    public String getBigClassificationEcd() {
        return bigClassificationEcd;
    }

    /**
     * 大分類コード(英語)設定<br>
     * <br>
     * 大分類コード(英語)を引数で設定する<br>
     * <br>
     * @param bigClassificationEcd
     */
    public void setBigClassificationEcd(String bigClassificationEcd) {
        this.bigClassificationEcd = bigClassificationEcd;
    }

    /**
     * 大分類名(英語)取得<br>
     * <br>
     * 大分類名(英語)を戻り値で返却する<br>
     * <br>
     * @return bigClassificationEnm
     */
    public String getBigClassificationEnm() {
        return bigClassificationEnm;
    }

    /**
     * 大分類名(英語)設定<br>
     * <br>
     * 大分類名(英語)を引数で設定する<br>
     * <br>
     * @param bigClassificationEnm
     */
    public void setBigClassificationEnm(String bigClassificationEnm) {
        this.bigClassificationEnm = bigClassificationEnm;
    }

    /**
     * 中分類コード(英語)取得<br>
     * <br>
     * 中分類コード(英語)を戻り値で返却する<br>
     * <br>
     * @return middleClassificationEcd
     */
    public String getMiddleClassificationEcd() {
        return middleClassificationEcd;
    }

    /**
     * 中分類コード(英語)設定<br>
     * <br>
     * 中分類コード(英語)を引数で設定する<br>
     * <br>
     * @param middleClassificationEcd
     */
    public void setMiddleClassificationEcd(String middleClassificationEcd) {
        this.middleClassificationEcd = middleClassificationEcd;
    }

    /**
     * 中分類名(英語)取得<br>
     * <br>
     * 中分類名(英語)を戻り値で返却する<br>
     * <br>
     * @return middleClassificationEnm
     */
    public String getMiddleClassificationEnm() {
        return middleClassificationEnm;
    }

    /**
     * 中分類名(英語)設定<br>
     * <br>
     * 中分類名(英語)を引数で設定する<br>
     * <br>
     * @param middleClassificationEnm
     */
    public void setMiddleClassificationEnm(String middleClassificationEnm) {
        this.middleClassificationEnm = middleClassificationEnm;
    }

    /**
     * 小分類コード(英語)取得<br>
     * <br>
     * 小分類コード(英語)を戻り値で返却する<br>
     * <br>
     * @return smallClassificationEcd
     */
    public String getSmallClassificationEcd() {
        return smallClassificationEcd;
    }

    /**
     * 小分類コード(英語)設定<br>
     * <br>
     * 小分類コード(英語)を引数で設定する<br>
     * <br>
     * @param smallClassificationEcd
     */
    public void setSmallClassificationEcd(String smallClassificationEcd) {
        this.smallClassificationEcd = smallClassificationEcd;
    }

    /**
     * 小分類名(英語)取得<br>
     * <br>
     * 小分類名(英語)を戻り値で返却する<br>
     * <br>
     * @return smallClassificationEnm
     */
    public String getSmallClassificationEnm() {
        return smallClassificationEnm;
    }

    /**
     * 小分類名(英語)設定<br>
     * <br>
     * 小分類名(英語)を引数で設定する<br>
     * <br>
     * @param smallClassificationEnm
     */
    public void setSmallClassificationEnm(String smallClassificationEnm) {
        this.smallClassificationEnm = smallClassificationEnm;
    }

    /**
     * コースコード取得<br>
     * <br>
     * コースコードを戻り値で返却する<br>
     * <br>
     * @return courseCd
     */
    public String getCourseCd() {
        return courseCd;
    }

    /**
     * コースコード設定<br>
     * <br>
     * コースコードを引数で設定する<br>
     * <br>
     * @param courseCd
     */
    public void setCourseCd(String courseCd) {
        this.courseCd = courseCd;
    }

    /**
     * コース名取得<br>
     * <br>
     * コース名を戻り値で返却する<br>
     * <br>
     * @return courseJnm
     */
    public String getCourseJnm() {
        return courseJnm;
    }

    /**
     * コース名設定<br>
     * <br>
     * コース名を引数で設定する<br>
     * <br>
     * @param courseJnm
     */
    public void setCourseJnm(String courseJnm) {
        this.courseJnm = courseJnm;
    }

    /**
     * コース名(英語)取得<br>
     * <br>
     * コース名(英語)を戻り値で返却する<br>
     * <br>
     * @return courseEnm
     */
    public String getCourseEnm() {
        return courseEnm;
    }

    /**
     * コース名(英語)設定<br>
     * <br>
     * コース名(英語)を引数で設定する<br>
     * <br>
     * @param courseEnm
     */
    public void setCourseEnm(String courseEnm) {
        this.courseEnm = courseEnm;
    }

    /**
     * 有償利用ポイント取得<br>
     * <br>
     * 有償利用ポイントを戻り値で返却する<br>
     * <br>
     * @return paymentUsePoint
     */
    public BigDecimal getPaymentUsePoint() {
        return paymentUsePoint;
    }

    /**
     * 有償利用ポイント設定<br>
     * <br>
     * 有償利用ポイントを引数で設定する<br>
     * <br>
     * @param paymentUsePoint
     */
    public void setPaymentUsePoint(BigDecimal paymentUsePoint) {
        this.paymentUsePoint = paymentUsePoint;
    }

    /**
     * 無償利用ポイント取得<br>
     * <br>
     * 無償利用ポイントを戻り値で返却する<br>
     * <br>
     * @return freeUsePoint
     */
    public BigDecimal getFreeUsePoint() {
        return freeUsePoint;
    }

    /**
     * 無償利用ポイント設定<br>
     * <br>
     * 無償利用ポイントを引数で設定する<br>
     * <br>
     * @param freeUsePoint
     */
    public void setFreeUsePoint(BigDecimal freeUsePoint) {
        this.freeUsePoint = freeUsePoint;
    }

    /**
     * 利用ポイント合計取得<br>
     * <br>
     * 利用ポイント合計を戻り値で返却する<br>
     * <br>
     * @return usePointSum
     */
    public BigDecimal getUsePointSum() {
        return usePointSum;
    }

    /**
     * 利用ポイント合計設定<br>
     * <br>
     * 利用ポイント合計を引数で設定する<br>
     * <br>
     * @param usePointSum
     */
    public void setUsePointSum(BigDecimal usePointSum) {
        this.usePointSum = usePointSum;
    }

    /**
     * 添付メール送付済区分取得<br>
     * <br>
     * 添付メール送付済区分を戻り値で返却する<br>
     * <br>
     * @return mailKbn
     */
    public String getMailKbn() {
        return mailKbn;
    }

    /**
     * 添付メール送付済区分設定<br>
     * <br>
     * 添付メール送付済区分を引数で設定する<br>
     * <br>
     * @param mailKbn
     */
    public void setMailKbn(String mailKbn) {
        this.mailKbn = mailKbn;
    }

    /**
     * 添付メール送付日取得<br>
     * <br>
     * 添付メール送付日を戻り値で返却する<br>
     * <br>
     * @return mailDt
     */
    public String getMailDt() {
        return mailDt;
    }

    /**
     * 添付メール送付日設定<br>
     * <br>
     * 添付メール送付日を引数で設定する<br>
     * <br>
     * @param mailDt
     */
    public void setMailDt(String mailDt) {
        this.mailDt = mailDt;
    }

    // 2014/01/15 FlashからTokboxに変更対応 start
//    /**
//     * 教室No取得<br>
//     * <br>
//     * 教室Noを戻り値で返却する<br>
//     * <br>
//     * @return roomNo
//     */
//    public String getRoomNo() {
//        return roomNo;
//    }
//
//    /**
//     * 教室No設定<br>
//     * <br>
//     * 教室Noを引数で設定する<br>
//     * <br>
//     * @param roomNo
//     */
//    public void setRoomNo(String roomNo) {
//        this.roomNo = roomNo;
//    }

    /**
     * Tokbox用セッションID取得<br>
     * <br>
     * Tokbox用セッションIDを戻り値で返却する<br>
     * <br>
     * @return tokboxSessionId
     */
    public String getTokboxSessionId() {
        return tokboxSessionId;
    }

    /**
     * Tokbox用セッションID設定<br>
     * <br>
     * Tokbox用セッションIDを引数で設定する<br>
     * <br>
     * @param tokboxSessionId
     */
    public void setTokboxSessionId(String tokboxSessionId) {
        this.tokboxSessionId = tokboxSessionId;
    }

    /**
     * Tokbox用トークンID取得<br>
     * <br>
     * Tokbox用トークンIDを戻り値で返却する<br>
     * <br>
     * @return tokboxTokenId
     */
    public String getTokboxTokenId() {
        return tokboxTokenId;
    }

    /**
     * Tokbox用トークンID設定<br>
     * <br>
     * Tokbox用トークンIDを引数で設定する<br>
     * <br>
     * @param tokboxTokenId
     */
    public void setTokboxTokenId(String tokboxTokenId) {
        this.tokboxTokenId = tokboxTokenId;
    }

    /**
     * レッスンファイル取得<br>
     * <br>
     * レッスンファイルを戻り値で返却する<br>
     * <br>
     * @return fileDuringLessons
     */
    public Blob getFileDuringLessons() {
        return fileDuringLessons;
    }

    /**
     * レッスンファイル設定<br>
     * <br>
     * レッスンファイルを引数で設定する<br>
     * <br>
     * @param fileDuringLessons
     */
    public void setFileDuringLessons(Blob fileDuringLessons) {
        this.fileDuringLessons = fileDuringLessons;
    }
    // 2014/01/15 FlashからTokboxに変更対応 end

    /**
     * 講師セッションID取得<br>
     * <br>
     * 講師セッションIDを戻り値で返却する<br>
     * <br>
     * @return teacherSessionId
     */
    public String getTeacherSessionId() {
        return teacherSessionId;
    }

    /**
     * 講師セッションID設定<br>
     * <br>
     * 講師セッションIDを引数で設定する<br>
     * <br>
     * @param teacherSessionId
     */
    public void setTeacherSessionId(String teacherSessionId) {
        this.teacherSessionId = teacherSessionId;
    }

    /**
     * 受講者セッションID取得<br>
     * <br>
     * 受講者セッションIDを戻り値で返却する<br>
     * <br>
     * @return studentSessionId
     */
    public String getStudentSessionId() {
        return studentSessionId;
    }

    /**
     * 受講者セッションID設定<br>
     * <br>
     * 受講者セッションIDを引数で設定する<br>
     * <br>
     * @param studentSessionId
     */
    public void setStudentSessionId(String studentSessionId) {
        this.studentSessionId = studentSessionId;
    }

    /**
     * 受講者入室時刻取得<br>
     * <br>
     * 受講者入室時刻を戻り値で返却する<br>
     * <br>
     * @return studentInDttm
     */
    public Timestamp getStudentInDttm() {
        return studentInDttm;
    }

    /**
     * 受講者入室時刻設定<br>
     * <br>
     * 受講者入室時刻を引数で設定する<br>
     * <br>
     * @param studentInDttm
     */
    public void setStudentInDttm(Timestamp studentInDttm) {
        this.studentInDttm = studentInDttm;
    }

    /**
     * 受講者START押下時刻取得<br>
     * <br>
     * 受講者START押下時刻を戻り値で返却する<br>
     * <br>
     * @return studentStartDttm
     */
    public Timestamp getStudentStartDttm() {
        return studentStartDttm;
    }

    /**
     * 受講者START押下時刻設定<br>
     * <br>
     * 受講者START押下時刻を引数で設定する<br>
     * <br>
     * @param studentStartDttm
     */
    public void setStudentStartDttm(Timestamp studentStartDttm) {
        this.studentStartDttm = studentStartDttm;
    }

    /**
     * 受講者END押下時刻取得<br>
     * <br>
     * 受講者END押下時刻を戻り値で返却する<br>
     * <br>
     * @return studentEndDttm
     */
    public Timestamp getStudentEndDttm() {
        return studentEndDttm;
    }

    /**
     * 受講者END押下時刻設定<br>
     * <br>
     * 受講者END押下時刻を引数で設定する<br>
     * <br>
     * @param studentEndDttm
     */
    public void setStudentEndDttm(Timestamp studentEndDttm) {
        this.studentEndDttm = studentEndDttm;
    }

    /**
     * 受講者レッスン区分取得<br>
     * <br>
     * 受講者レッスン区分を戻り値で返却する<br>
     * <br>
     * @return studentLessonKbn
     */
    public String getStudentLessonKbn() {
        return studentLessonKbn;
    }

    /**
     * 受講者レッスン区分設定<br>
     * <br>
     * 受講者レッスン区分を引数で設定する<br>
     * <br>
     * @param studentLessonKbn
     */
    public void setStudentLessonKbn(String studentLessonKbn) {
        this.studentLessonKbn = studentLessonKbn;
    }

    /**
     * 講師入室時刻取得<br>
     * <br>
     * 講師入室時刻を戻り値で返却する<br>
     * <br>
     * @return teacherInDttm
     */
    public Timestamp getTeacherInDttm() {
        return teacherInDttm;
    }

    /**
     * 講師入室時刻設定<br>
     * <br>
     * 講師入室時刻を引数で設定する<br>
     * <br>
     * @param teacherInDttm
     */
    public void setTeacherInDttm(Timestamp teacherInDttm) {
        this.teacherInDttm = teacherInDttm;
    }

    /**
     * 講師START押下時刻取得<br>
     * <br>
     * 講師START押下時刻を戻り値で返却する<br>
     * <br>
     * @return teacherStartDttm
     */
    public Timestamp getTeacherStartDttm() {
        return teacherStartDttm;
    }

    /**
     * 講師START押下時刻設定<br>
     * <br>
     * 講師START押下時刻を引数で設定する<br>
     * <br>
     * @param teacherStartDttm
     */
    public void setTeacherStartDttm(Timestamp teacherStartDttm) {
        this.teacherStartDttm = teacherStartDttm;
    }

    /**
     * 講師END押下時刻取得<br>
     * <br>
     * 講師END押下時刻を戻り値で返却する<br>
     * <br>
     * @return teacherEndDttm
     */
    public Timestamp getTeacherEndDttm() {
        return teacherEndDttm;
    }

    /**
     * 講師END押下時刻設定<br>
     * <br>
     * 講師END押下時刻を引数で設定する<br>
     * <br>
     * @param teacherEndDttm
     */
    public void setTeacherEndDttm(Timestamp teacherEndDttm) {
        this.teacherEndDttm = teacherEndDttm;
    }

    /**
     * 講師NEXT押下時刻取得<br>
     * <br>
     * 講師NEXT押下時刻を戻り値で返却する<br>
     * <br>
     * @return teacherNextDttm
     */
    public Timestamp getTeacherNextDttm() {
        return teacherNextDttm;
    }

    /**
     * 講師NEXT押下時刻設定<br>
     * <br>
     * 講師NEXT押下時刻を引数で設定する<br>
     * <br>
     * @param teacherNextDttm
     */
    public void setTeacherNextDttm(Timestamp teacherNextDttm) {
        this.teacherNextDttm = teacherNextDttm;
    }

    /**
     * 講師レッスン区分取得<br>
     * <br>
     * 講師レッスン区分を戻り値で返却する<br>
     * <br>
     * @return teacherLessonKbn
     */
    public String getTeacherLessonKbn() {
        return teacherLessonKbn;
    }

    /**
     * 講師レッスン区分設定<br>
     * <br>
     * 講師レッスン区分を引数で設定する<br>
     * <br>
     * @param teacherLessonKbn
     */
    public void setTeacherLessonKbn(String teacherLessonKbn) {
        this.teacherLessonKbn = teacherLessonKbn;
    }

    /**
     * レッスンSTART時刻取得<br>
     * <br>
     * レッスンSTART時刻を戻り値で返却する<br>
     * <br>
     * @return lessonStartDttm
     */
    public Timestamp getLessonStartDttm() {
        return lessonStartDttm;
    }

    /**
     * レッスンSTART時刻設定<br>
     * <br>
     * レッスンSTART時刻を引数で設定する<br>
     * <br>
     * @param lessonStartDttm
     */
    public void setLessonStartDttm(Timestamp lessonStartDttm) {
        this.lessonStartDttm = lessonStartDttm;
    }

    /**
     * レッスンEND時刻取得<br>
     * <br>
     * レッスンEND時刻を戻り値で返却する<br>
     * <br>
     * @return lessonEndDttm
     */
    public Timestamp getLessonEndDttm() {
        return lessonEndDttm;
    }

    /**
     * レッスンEND時刻設定<br>
     * <br>
     * レッスンEND時刻を引数で設定する<br>
     * <br>
     * @param lessonEndDttm
     */
    public void setLessonEndDttm(Timestamp lessonEndDttm) {
        this.lessonEndDttm = lessonEndDttm;
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

    /**
     * 講師：講師のレッスン自己分析１区分取得<br>
     * <br>
     * 講師：講師のレッスン自己分析１区分を戻り値で返却する<br>
     * <br>
     * @return tSelfEvaluation1Kbn
     */
    public String getTSelfEvaluation1Kbn() {
        return tSelfEvaluation1Kbn;
    }

    /**
     * 講師：講師のレッスン自己分析１区分設定<br>
     * <br>
     * 講師：講師のレッスン自己分析１区分を引数で設定する<br>
     * <br>
     * @param tSelfEvaluation1Kbn
     */
    public void setTSelfEvaluation1Kbn(String tSelfEvaluation1Kbn) {
        this.tSelfEvaluation1Kbn = tSelfEvaluation1Kbn;
    }

    /**
     * 講師：講師のレッスン自己分析１取得<br>
     * <br>
     * 講師：講師のレッスン自己分析１を戻り値で返却する<br>
     * <br>
     * @return tSelfEvaluation1Enm
     */
    public String getTSelfEvaluation1Enm() {
        return tSelfEvaluation1Enm;
    }

    /**
     * 講師：講師のレッスン自己分析１設定<br>
     * <br>
     * 講師：講師のレッスン自己分析１を引数で設定する<br>
     * <br>
     * @param tSelfEvaluation1Enm
     */
    public void setTSelfEvaluation1Enm(String tSelfEvaluation1Enm) {
        this.tSelfEvaluation1Enm = tSelfEvaluation1Enm;
    }

    /**
     * 講師：講師のレッスン自己分析２区分取得<br>
     * <br>
     * 講師：講師のレッスン自己分析２区分を戻り値で返却する<br>
     * <br>
     * @return tSelfEvaluation2Kbn
     */
    public String getTSelfEvaluation2Kbn() {
        return tSelfEvaluation2Kbn;
    }

    /**
     * 講師：講師のレッスン自己分析２区分設定<br>
     * <br>
     * 講師：講師のレッスン自己分析２区分を引数で設定する<br>
     * <br>
     * @param tSelfEvaluation2Kbn
     */
    public void setTSelfEvaluation2Kbn(String tSelfEvaluation2Kbn) {
        this.tSelfEvaluation2Kbn = tSelfEvaluation2Kbn;
    }

    /**
     * 講師：講師のレッスン自己分析２取得<br>
     * <br>
     * 講師：講師のレッスン自己分析２を戻り値で返却する<br>
     * <br>
     * @return tSelfEvaluation2Enm
     */
    public String getTSelfEvaluation2Enm() {
        return tSelfEvaluation2Enm;
    }

    /**
     * 講師：講師のレッスン自己分析２設定<br>
     * <br>
     * 講師：講師のレッスン自己分析２を引数で設定する<br>
     * <br>
     * @param tSelfEvaluation2Enm
     */
    public void setTSelfEvaluation2Enm(String tSelfEvaluation2Enm) {
        this.tSelfEvaluation2Enm = tSelfEvaluation2Enm;
    }

    /**
     * 講師：受講者への前向きコメント取得<br>
     * <br>
     * 講師：受講者への前向きコメントを戻り値で返却する<br>
     * <br>
     * @return tPositiveCmt
     */
    public String getTPositiveCmt() {
        return tPositiveCmt;
    }

    /**
     * 講師：受講者への前向きコメント設定<br>
     * <br>
     * 講師：受講者への前向きコメントを引数で設定する<br>
     * <br>
     * @param tPositiveCmt
     */
    public void setTPositiveCmt(String tPositiveCmt) {
        this.tPositiveCmt = tPositiveCmt;
    }

    /**
     * 講師：講師からスクールへのコメント取得<br>
     * <br>
     * 講師：講師からスクールへのコメントを戻り値で返却する<br>
     * <br>
     * @return tOnSchoolCmt
     */
    public String getTOnSchoolCmt() {
        return tOnSchoolCmt;
    }

    /**
     * 講師：講師からスクールへのコメント設定<br>
     * <br>
     * 講師：講師からスクールへのコメントを引数で設定する<br>
     * <br>
     * @param tOnSchoolCmt
     */
    public void setTOnSchoolCmt(String tOnSchoolCmt) {
        this.tOnSchoolCmt = tOnSchoolCmt;
    }

    /**
     * 講師：推奨レベル区分取得<br>
     * <br>
     * 講師：推奨レベル区分を戻り値で返却する<br>
     * <br>
     * @return tRecommendedLevelKbn
     */
    public String getTRecommendedLevelKbn() {
        return tRecommendedLevelKbn;
    }

    /**
     * 講師：推奨レベル区分設定<br>
     * <br>
     * 講師：推奨レベル区分を引数で設定する<br>
     * <br>
     * @param tRecommendedLevelKbn
     */
    public void setTRecommendedLevelKbn(String tRecommendedLevelKbn) {
        this.tRecommendedLevelKbn = tRecommendedLevelKbn;
    }

    /**
     * 講師：推奨レベル取得<br>
     * <br>
     * 講師：推奨レベルを戻り値で返却する<br>
     * <br>
     * @return tRecommendedLevelEnm
     */
    public String getTRecommendedLevelEnm() {
        return tRecommendedLevelEnm;
    }

    /**
     * 講師：推奨レベル設定<br>
     * <br>
     * 講師：推奨レベルを引数で設定する<br>
     * <br>
     * @param tRecommendedLevelEnm
     */
    public void setTRecommendedLevelEnm(String tRecommendedLevelEnm) {
        this.tRecommendedLevelEnm = tRecommendedLevelEnm;
    }

    /**
     * 講師：受講者を引継ぎするコメント取得<br>
     * <br>
     * 講師：受講者を引継ぎするコメントを戻り値で返却する<br>
     * <br>
     * @return tNextTeacherCmt
     */
    public String getTNextTeacherCmt() {
        return tNextTeacherCmt;
    }

    /**
     * 講師：受講者を引継ぎするコメント設定<br>
     * <br>
     * 講師：受講者を引継ぎするコメントを引数で設定する<br>
     * <br>
     * @param tNextTeacherCmt
     */
    public void setTNextTeacherCmt(String tNextTeacherCmt) {
        this.tNextTeacherCmt = tNextTeacherCmt;
    }

    /**
     * 受講者：講師への評価区分取得<br>
     * <br>
     * 受講者：講師への評価区分を戻り値で返却する<br>
     * <br>
     * @return cEvaluationKbn
     */
    public String getCEvaluationKbn() {
        return cEvaluationKbn;
    }

    /**
     * 受講者：講師への評価区分設定<br>
     * <br>
     * 受講者：講師への評価区分を引数で設定する<br>
     * <br>
     * @param cEvaluationKbn
     */
    public void setCEvaluationKbn(String cEvaluationKbn) {
        this.cEvaluationKbn = cEvaluationKbn;
    }

    /**
     * 受講者：講師への評価取得<br>
     * <br>
     * 受講者：講師への評価を戻り値で返却する<br>
     * <br>
     * @return cEvaluationJnm
     */
    public String getCEvaluationJnm() {
        return cEvaluationJnm;
    }

    /**
     * 受講者：講師への評価設定<br>
     * <br>
     * 受講者：講師への評価を引数で設定する<br>
     * <br>
     * @param cEvaluationJnm
     */
    public void setCEvaluationJnm(String cEvaluationJnm) {
        this.cEvaluationJnm = cEvaluationJnm;
    }

    /**
     * 受講者：レッスンに対する講師への意見コメント取得<br>
     * <br>
     * 受講者：レッスンに対する講師への意見コメントを戻り値で返却する<br>
     * <br>
     * @return cOnTeacherCmt
     */
    public String getCOnTeacherCmt() {
        return cOnTeacherCmt;
    }

    /**
     * 受講者：レッスンに対する講師への意見コメント設定<br>
     * <br>
     * 受講者：レッスンに対する講師への意見コメントを引数で設定する<br>
     * <br>
     * @param cOnTeacherCmt
     */
    public void setCOnTeacherCmt(String cOnTeacherCmt) {
        this.cOnTeacherCmt = cOnTeacherCmt;
    }

    /**
     * スクール：スクール側から講師へのコメント取得<br>
     * <br>
     * スクール：スクール側から講師へのコメントを戻り値で返却する<br>
     * <br>
     * @return sOnTeacherCmt
     */
    public String getSOnTeacherCmt() {
        return sOnTeacherCmt;
    }

    /**
     * スクール：スクール側から講師へのコメント設定<br>
     * <br>
     * スクール：スクール側から講師へのコメントを引数で設定する<br>
     * <br>
     * @param sOnTeacherCmt
     */
    public void setSOnTeacherCmt(String sOnTeacherCmt) {
        this.sOnTeacherCmt = sOnTeacherCmt;
    }

    /**
     * レッスンコメントテーブル レコードバージョン番号取得<br>
     * <br>
     * レッスンコメントテーブル レコードバージョン番号を戻り値で返却する<br>
     * <br>
     * @return recordVerNoLCT
     */
    public int getRecordVerNoLCT() {
        return recordVerNoLCT;
    }

    /**
     * レッスンコメントテーブル レコードバージョン番号設定<br>
     * <br>
     * レッスンコメントテーブル レコードバージョン番号で設定する<br>
     * <br>
     * @param recordVerNoLCT
     */
    public void setRecordVerNoLCT(int recordVerNoLCT) {
        this.recordVerNoLCT = recordVerNoLCT;
    }

    /**
     * 講師：受講者への前向きコメントの上37バイト取得<br>
     * <br>
     * 講師：受講者への前向きコメントの上37バイトを戻り値で返却する<br>
     * <br>
     * @return cOnTeacherCmt
     */
    public String gettPositiveCmtCut() {
        return tPositiveCmtCut;
    }

    /**
     * 講師：受講者への前向きコメントの上37バイト設定<br>
     * <br>
     * 講師：受講者への前向きコメントの上37バイトを引数で設定する<br>
     * <br>
     * @param cOnTeacherCmt
     */
    public void settPositiveCmtCut(String tPositiveCmtCut) {
        this.tPositiveCmtCut = tPositiveCmtCut;
    }

}
