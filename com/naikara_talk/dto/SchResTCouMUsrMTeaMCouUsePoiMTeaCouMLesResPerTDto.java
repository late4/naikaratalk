package com.naikara_talk.dto;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>共通部品 DTOクラス<br>
 * <b>クラス名称       :</b>予約申込ページDTOクラス<br>
 * <b>クラス概要       :</b>受講者がレッスンを予約登録、取消ができる。<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/18 TECS 新規作成
 */
public class SchResTCouMUsrMTeaMCouUsePoiMTeaCouMLesResPerTDto extends AbstractDto {

    private String lessonDt;                 // レッスン日
    private String LessonTmCdFr;             // レッスン時刻コード(From)
    private String LessonTmCdTo;             // レッスン時刻コード(To)
    private String genderKbn;                // 性別区分
    private String bigClassificationCd;      // 大分類コード
    private String middleClassificationCd;   // 中分類コード
    private String smallClassificationCd;    // 小分類コード
    private String teacherId;                // 講師ID
    private String lessonTmCd;               // レッスン時刻コード
    private String lessonTm;                 // レッスン時刻
    private String nickAnm;                  // 講師名(ニックネーム）
    private String sellingPoint;             // セールスポイント
    private String courseJnm;                // コース名
    private String courseEnm;                // コース名(英語名)
    private String coursePoint;              // コースポイント
    private String reservationKbn;           // 予約区分
    private String reservationNo;            // 予約No
    private String studentId;                // 受講者ID
    private String courseCd;                 // コースコード
    private int recordVerNo1;             // レコードバージョン番号1
    private int recordVerNo2;             // レコードバージョン番号2
    private int returnCode;                  // リターンコード

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
     * レッスン時刻コード(From)取得<br>
     * <br>
     * レッスン時刻コード(From)を戻り値で返却する<br>
     * <br>
     * @return LessonTmCdFr
     */
    public String getLessonTmCdFr() {
        return LessonTmCdFr;
    }

    /**
     * レッスン時刻コード(From)設定<br>
     * <br>
     * レッスン時刻コード(From)を引数で設定する<br>
     * <br>
     * @param LessonTmCdFr
     */
    public void setLessonTmCdFr(String lessonTmCdFr) {
        LessonTmCdFr = lessonTmCdFr;
    }

    /**
     * レッスン時刻コード(To)取得<br>
     * <br>
     * レッスン時刻コード(To)を戻り値で返却する<br>
     * <br>
     * @return LessonTmCdTo
     */
    public String getLessonTmCdTo() {
        return LessonTmCdTo;
    }

    /**
     * レッスン時刻コード(To)取得<br>
     * <br>
     * レッスン時刻コード(To)を戻り値で返却する<br>
     * <br>
     * @return LessonTmCdTo
     */
    public void setLessonTmCdTo(String lessonTmCdTo) {
        LessonTmCdTo = lessonTmCdTo;
    }

    /**
     * 性別区分取得<br>
     * <br>
     * 性別区分を戻り値で返却する<br>
     * <br>
     * @return genderKbn
     */
    public String getGenderKbn() {
        return genderKbn;
    }

    /**
     * 性別区分設定<br>
     * <br>
     * 性別区分を引数で設定する<br>
     * <br>
     * @param genderKbn
     */
    public void setGenderKbn(String genderKbn) {
        this.genderKbn = genderKbn;
    }

    /**
     * 大分類コード取得<br>
     * <br>
     * 大分類コードを戻り値で返却する<br>
     * <br>
     * @return bigClassificationCd
     */
    public String getBigClassificationCd() {
        return bigClassificationCd;
    }

    /**
     * 大分類コード設定<br>
     * <br>
     * 大分類コードを引数で設定する<br>
     * <br>
     * @param bigClassificationCd
     */
    public void setBigClassificationCd(String bigClassificationCd) {
        this.bigClassificationCd = bigClassificationCd;
    }

    /**
     * 中分類コード取得<br>
     * <br>
     * 中分類コードを戻り値で返却する<br>
     * <br>
     * @return middleClassificationCd
     */
    public String getMiddleClassificationCd() {
        return middleClassificationCd;
    }

    /**
     * 中分類コード設定<br>
     * <br>
     * 中分類コードを引数で設定する<br>
     * <br>
     * @param middleClassificationCd
     */
    public void setMiddleClassificationCd(String middleClassificationCd) {
        this.middleClassificationCd = middleClassificationCd;
    }

    /**
     * 小分類コード取得<br>
     * <br>
     * 小分類コードを戻り値で返却する<br>
     * <br>
     * @return smallClassificationCd
     */
    public String getSmallClassificationCd() {
        return smallClassificationCd;
    }

    /**
     * 小分類コード設定<br>
     * <br>
     * 小分類コードを引数で設定する<br>
     * <br>
     * @param smallClassificationCd
     */
    public void setSmallClassificationCd(String smallClassificationCd) {
        this.smallClassificationCd = smallClassificationCd;
    }

    /**
     * 講師ID取得<br>
     * <br>
     * 講師IDを戻り値で返却する<br>
     * <br>
     * @return teacherId
     */
    public String getTeacherId() {
        return teacherId;
    }

    /**
     * 講師ID設定<br>
     * <br>
     * 講師IDを引数で設定する<br>
     * <br>
     * @param teacherId
     */
    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
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
     * レッスン時刻取得<br>
     * <br>
     * レッスン時刻を戻り値で返却する<br>
     * <br>
     * @return lessonTm
     */
    public String getLessonTm() {
        return lessonTm;
    }

    /**
     * レッスン時刻設定<br>
     * <br>
     * レッスン時刻を引数で設定する<br>
     * <br>
     * @param lessonTm
     */
    public void setLessonTm(String lessonTm) {
        this.lessonTm = lessonTm;
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
     * セールスポイント取得<br>
     * <br>
     * セールスポイントを戻り値で返却する<br>
     * <br>
     * @return sellingPoint
     */
    public String getSellingPoint() {
        return sellingPoint;
    }

    /**
     * セールスポイント設定<br>
     * <br>
     * セールスポイントを引数で設定する<br>
     * <br>
     * @param sellingPoint
     */
    public void setSellingPoint(String sellingPoint) {
        this.sellingPoint = sellingPoint;
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
     * コース名(英語名)取得<br>
     * <br>
     * コース名(英語名)を戻り値で返却する<br>
     * <br>
     * @return courseEnm
     */
    public String getCourseEnm() {
        return courseEnm;
    }

    /**
     * コース名(英語名)設定<br>
     * <br>
     * コース名(英語名)を引数で設定する<br>
     * <br>
     * @param courseEnm
     */
    public void setCourseEnm(String courseEnm) {
        this.courseEnm = courseEnm;
    }

    /**
     * コースポイント取得<br>
     * <br>
     * コースポイントを戻り値で返却する<br>
     * <br>
     * @return coursePoint
     */
    public String getCoursePoint() {
        return coursePoint;
    }

    /**
     * コースポイント設定<br>
     * <br>
     * コースポイントを引数で設定する<br>
     * <br>
     * @param coursePoint
     */
    public void setCoursePoint(String coursePoint) {
        this.coursePoint = coursePoint;
    }

    /**
     * 予約区分取得<br>
     * <br>
     * 予約区分を戻り値で返却する<br>
     * <br>
     * @return reservationKbn
     */
    public String getReservationKbn() {
        return reservationKbn;
    }

    /**
     * 予約区分設定<br>
     * <br>
     * 予約区分を引数で設定する<br>
     * <br>
     * @param reservationKbn
     */
    public void setReservationKbn(String reservationKbn) {
        this.reservationKbn = reservationKbn;
    }

    /**
     * 予約No取得<br>
     * <br>
     * 予約Noを戻り値で返却する<br>
     * <br>
     * @return returnCode
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
     * レコードバージョン番号1取得<br>
     * <br>
     * レコードバージョン番号1を戻り値で返却する<br>
     * <br>
     * @return recordVerNo1
     */
    public int getRecordVerNo1() {
        return recordVerNo1;
    }

    /**
     * レコードバージョン番号1設定<br>
     * <br>
     * レコードバージョン番号1を引数で設定する<br>
     * <br>
     * @param recordVerNo1
     */
    public void setRecordVerNo1(int recordVerNo1) {
        this.recordVerNo1 = recordVerNo1;
    }

    /**
     * レコードバージョン番号2取得<br>
     * <br>
     * レコードバージョン番号2を戻り値で返却する<br>
     * <br>
     * @return recordVerNo2
     */
    public int getRecordVerNo2() {
        return recordVerNo2;
    }

    /**
     * レコードバージョン番号2設定<br>
     * <br>
     * レコードバージョン番号2を引数で設定する<br>
     * <br>
     * @param recordVerNo2
     */
    public void setRecordVerNo2(int recordVerNo2) {
        this.recordVerNo2 = recordVerNo2;
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
