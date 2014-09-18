package com.naikara_talk.dto;

import java.sql.Timestamp;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称:</b>共通部品 DTOクラス<br>
 * <b>クラス名称　　　:</b>レッスンコメントテーブルクラス<br>
 * <b>クラス概要　　　:</b>レッスンコメントテーブルDTO<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/06/03 TECS 新規作成
 */
public class LessonCommentTrnDto extends AbstractDto {

    private String reservationNo;         // 予約No
    private String cmtInputsKbn;          // コメント入力者区分
    private String tSelfEvaluation1Kbn;   // 講師：講師のレッスン自己分析１区分
    private String tSelfEvaluation1Enm;   // 講師：講師のレッスン自己分析１
    private String tSelfEvaluation2Kbn;   // 講師：講師のレッスン自己分析２区分
    private String tSelfEvaluation2Enm;   // 講師：講師のレッスン自己分析２
    private String tPositiveCmt;          // 講師：受講者への前向きコメント
    private String tOnSchoolCmt;          // 講師：講師からスクールへのコメント
    private String tRecommendedLevelKbn;  // 講師：推奨レベル区分
    private String tRecommendedLevelEnm;  // 講師：推奨レベル
    private String tNextTeacherCmt;       // 講師：受講者を引継ぎするコメント
    private String cEvaluationKbn;        // 受講者：講師への評価区分
    private String cEvaluationJnm;        // 受講者：講師への評価
    private String cOnTeacherCmt;         // 受講者：レッスンに対する講師への意見コメント
    private String sOnTeacherCmt;         // スクール：スクール側から講師へのコメント
    private int recordVerNo;              // レコードバージョン番号
    private Timestamp insertDttm;         // 登録日時
    private String insertCd;              // 登録者コード
    private Timestamp updateDttm;         // 更新日時
    private String updateCd;              // 更新者コード
    private int returnCode;               // リターンコード

    private int recordVerNoForTeacher;    // レコードバージョン番号(講師)
    private int recordVerNoForStudent;    // レコードバージョン番号(受講者)
    private int recordVerNoForSchool;     // レコードバージョン番号(スクール)

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
     * コメント入力者区分取得<br>
     * <br>
     * コメント入力者区分を戻り値で返却する<br>
     * <br>
     * @return cmtInputsKbn
     */
    public String getCmtInputsKbn() {
        return cmtInputsKbn;
    }

    /**
     * コメント入力者区分設定<br>
     * <br>
     * コメント入力者区分を引数で設定する<br>
     * <br>
     * @param cmtInputsKbn
     */
    public void setCmtInputsKbn(String cmtInputsKbn) {
        this.cmtInputsKbn = cmtInputsKbn;
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
