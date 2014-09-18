package com.naikara_talk.dto;

import java.sql.Timestamp;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称:</b>共通部品 DTOクラス<br>
 * <b>クラス名称　　　:</b>講師予定予約テーブルクラス<br>
 * <b>クラス概要　　　:</b>講師予定予約テーブルDTO<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/07/01 TECS 新規作成
 * <b>　　　　　　　　:</b>2014/06/02 TECS レッスン予約に関する「応相談」対応
 */
public class ScheduleReservationTrnDto extends AbstractDto{

	private String teacherId;       //講師ID
	private String lessonDt;        //レッスン日
	private String lessonTmCd;      //レッスン時刻コード
	private String reservationKbn;  //予約区分(予約状況)
	private String reservationNo;   //予約No
	private String studentId;       //受講者ID
	private String courseCd;        //コースコード

	// 2014/06/02 レッスン予約に関する「応相談」対応 Add Sta
	private Timestamp requestedDttm;            // 応相談予約登録日時
	private Timestamp appRequestDttm;           // 応相談予約回答日時
	private Timestamp appRequestAutoCxlDttm;    // 応相談予約自動キャンセル処理日時
	// 2014/06/02 レッスン予約に関する「応相談」対応 Add End

	private int recordVerNo;        //レコードバージョン番号
	private Timestamp insertDttm;   //登録日時
	private String insertCd;        //登録者コード
	private Timestamp updateDttm;   //更新日時
	private String updateCd;        //更新者コード
	private int returnCode;         //リターンコード

	private String lessonDtTo;        //レッスン日(To)

	// 2014/06/02 レッスン予約に関する「応相談」対応 Add Start
	private String defaultSelValue;   // ドロップダウンの選択値
	private String disabledFlg;       // ドロップダウンの使用可/不可
	private String reservationState;  // 予約区分(予約状況)から判断した状態
	// 2014/06/02 レッスン予約に関する「応相談」対応 Add End


	/**
	 * 講師ID取得<br>
	 * <br>
	 * 講師IDを戻り値で返却する<br>
	 * <br>
	 * @return teacherId
	 */
	public String getTeacherId(){
		return teacherId;
	}

	/**
	 * 講師ID設定<br>
	 * <br>
	 * 講師IDを引数で設定する<br>
	 * <br>
	 * @param teacherId
	 */
	public void setTeacherId(String teacherId){
		this.teacherId = teacherId;
	}

	/**
	 * レッスン日取得<br>
	 * <br>
	 * レッスン日を戻り値で返却する<br>
	 * <br>
	 * @return lessonDt
	 */
	public String getLessonDt(){
		return lessonDt;
	}

	/**
	 * レッスン日設定<br>
	 * <br>
	 * レッスン日を引数で設定する<br>
	 * <br>
	 * @param lessonDt
	 */
	public void setLessonDt(String lessonDt){
		this.lessonDt = lessonDt;
	}

	/**
	 * レッスン時刻コード取得<br>
	 * <br>
	 * レッスン時刻コードを戻り値で返却する<br>
	 * <br>
	 * @return lessonTmCd
	 */
	public String getLessonTmCd(){
		return lessonTmCd;
	}

	/**
	 * レッスン時刻コード設定<br>
	 * <br>
	 * レッスン時刻コードを引数で設定する<br>
	 * <br>
	 * @param lessonTmCd
	 */
	public void setLessonTmCd(String lessonTmCd){
		this.lessonTmCd = lessonTmCd;
	}

	/**
	 * 予約区分(予約状況)取得<br>
	 * <br>
	 * 予約区分(予約状況)を戻り値で返却する<br>
	 * <br>
	 * @return reservationKbn
	 */
	public String getReservationKbn(){
		return reservationKbn;
	}

	/**
	 * 予約区分(予約状況)設定<br>
	 * <br>
	 * 予約区分(予約状況)を引数で設定する<br>
	 * <br>
	 * @param reservationKbn
	 */
	public void setReservationKbn(String reservationKbn){
		this.reservationKbn = reservationKbn;
	}

	/**
	 * 予約No取得<br>
	 * <br>
	 * 予約Noを戻り値で返却する<br>
	 * <br>
	 * @return reservationNo
	 */
	public String getReservationNo(){
		return reservationNo;
	}

	/**
	 * 予約No設定<br>
	 * <br>
	 * 予約Noを引数で設定する<br>
	 * <br>
	 * @param reservationNo
	 */
	public void setReservationNo(String reservationNo){
		this.reservationNo = reservationNo;
	}

	/**
	 * 受講者ID取得<br>
	 * <br>
	 * 受講者IDを戻り値で返却する<br>
	 * <br>
	 * @return studentId
	 */
	public String getStudentId(){
		return studentId;
	}

	/**
	 * 受講者ID設定<br>
	 * <br>
	 * 受講者IDを引数で設定する<br>
	 * <br>
	 * @param studentId
	 */
	public void setStudentId(String studentId){
		this.studentId = studentId;
	}

	/**
	 * コースコード取得<br>
	 * <br>
	 * コースコードを戻り値で返却する<br>
	 * <br>
	 * @return courseCd
	 */
	public String getCourseCd(){
		return courseCd;
	}

	/**
	 * コースコード設定<br>
	 * <br>
	 * コースコードを引数で設定する<br>
	 * <br>
	 * @param courseCd
	 */
	public void setCourseCd(String courseCd){
		this.courseCd = courseCd;
	}


	// 2014/06/02 レッスン予約に関する「応相談」対応 Add Start
	/**
	 * 応相談予約登録日時 取得<br>
	 * <br>
	 * 応相談予約登録日時を戻り値で返却する<br>
	 * <br>
	 * @return requestedDttm
	 */
	public Timestamp getRequestedDttm(){
		return requestedDttm;
	}

	/**
	 * 応相談予約登録日時 設定<br>
	 * <br>
	 * 応相談予約登録日時 を引数で設定する<br>
	 * <br>
	 * @param requestedDttm
	 */
	public void setRequestedDttm(Timestamp requestedDttm){
		this.requestedDttm = requestedDttm;
	}

	/**
	 * 応相談予約回答日時 取得<br>
	 * <br>
	 * 応相談予約回答日時を戻り値で返却する<br>
	 * <br>
	 * @return appRequestDttm
	 */
	public Timestamp getAppRequestDttm(){
		return appRequestDttm;
	}

	/**
	 * 応相談予約回答日時 設定<br>
	 * <br>
	 * 応相談予約回答日時 を引数で設定する<br>
	 * <br>
	 * @param appRequestDttm
	 */
	public void setAppRequestDttm(Timestamp appRequestDttm){
		this.appRequestDttm = appRequestDttm;
	}

	/**
	 * 応相談予約自動キャンセル処理日時 取得<br>
	 * <br>
	 * 応相談予約自動キャンセル処理日時を戻り値で返却する<br>
	 * <br>
	 * @return appRequestAutoCxlDttm
	 */
	public Timestamp getAppRequestAutoCxlDttm(){
		return appRequestAutoCxlDttm;
	}

	/**
	 * 応相談予約自動キャンセル処理日時 設定<br>
	 * <br>
	 * 応相談予約自動キャンセル処理日時 を引数で設定する<br>
	 * <br>
	 * @param appRequestAutoCxlDttm
	 */
	public void setAppRequestAutoCxlDttm(Timestamp appRequestAutoCxlDttm){
		this.appRequestAutoCxlDttm = appRequestAutoCxlDttm;
	}
	// 2014/06/02 レッスン予約に関する「応相談」対応 Add End


	/**
	 * レコードバージョン番号取得<br>
	 * <br>
	 * レコードバージョン番号を戻り値で返却する<br>
	 * <br>
	 * @return recordVerNo
	 */
	public int getRecordVerNo(){
		return recordVerNo;
	}

	/**
	 * レコードバージョン番号設定<br>
	 * <br>
	 * レコードバージョン番号を引数で設定する<br>
	 * <br>
	 * @param recordVerNo
	 */
	public void setRecordVerNo(int recordVerNo){
		this.recordVerNo = recordVerNo;
	}

	/**
	 * 登録日時取得<br>
	 * <br>
	 * 登録日時を戻り値で返却する<br>
	 * <br>
	 * @return insertDttm
	 */
	public Timestamp getInsertDttm(){
		return insertDttm;
	}

	/**
	 * 登録日時設定<br>
	 * <br>
	 * 登録日時を引数で設定する<br>
	 * <br>
	 * @param insertDttm
	 */
	public void setInsertDttm(Timestamp insertDttm){
		this.insertDttm = insertDttm;
	}

	/**
	 * 登録者コード取得<br>
	 * <br>
	 * 登録者コードを戻り値で返却する<br>
	 * <br>
	 * @return insertCd
	 */
	public String getInsertCd(){
		return insertCd;
	}

	/**
	 * 登録者コード設定<br>
	 * <br>
	 * 登録者コードを引数で設定する<br>
	 * <br>
	 * @param insertCd
	 */
	public void setInsertCd(String insertCd){
		this.insertCd = insertCd;
	}

	/**
	 * 更新日時取得<br>
	 * <br>
	 * 更新日時を戻り値で返却する<br>
	 * <br>
	 * @return updateDttm
	 */
	public Timestamp getUpdateDttm(){
		return updateDttm;
	}

	/**
	 * 更新日時設定<br>
	 * <br>
	 * 更新日時を引数で設定する<br>
	 * <br>
	 * @param updateDttm
	 */
	public void setUpdateDttm(Timestamp updateDttm){
		this.updateDttm = updateDttm;
	}

	/**
	 * 更新者コード取得<br>
	 * <br>
	 * 更新者コードを戻り値で返却する<br>
	 * <br>
	 * @return updateCd
	 */
	public String getUpdateCd(){
		return updateCd;
	}

	/**
	 * 更新者コード設定<br>
	 * <br>
	 * 更新者コードを引数で設定する<br>
	 * <br>
	 * @param updateCd
	 */
	public void setUpdateCd(String updateCd){
		this.updateCd = updateCd;
	}

	/**
	 * リターンコード取得<br>
	 * <br>
	 * リターンコードを戻り値で返却する<br>
	 * <br>
	 * @return returnCode
	 */
	public int getReturnCode(){
		return returnCode;
	}

	/**
	 * リターンコード設定<br>
	 * <br>
	 * リターンコードを引数で設定する<br>
	 * <br>
	 * @param returnCode
	 */
	public void setReturnCode(int returnCode){
		this.returnCode = returnCode;
	}




	/**
	 * レッスン日取得<br>
	 * <br>
	 * レッスン日を戻り値で返却する<br>
	 * <br>
	 * @return lessonDtTo
	 */
	public String getLessonDtTo(){
		return lessonDtTo;
	}

	/**
	 * レッスン日設定<br>
	 * <br>
	 * レッスン日を引数で設定する<br>
	 * <br>
	 * @param LessonDtTo
	 */
	public void setLessonDtTo(String lessonDtTo){
		this.lessonDtTo = lessonDtTo;
	}

	// 2014/06/02 レッスン予約に関する「応相談」対応 Add Start
	/**
	 * 予約区分(予約状況)の選択値(初期値) 取得<br>
	 * <br>
	 * 予約区分(予約状況)の選択値を戻り値で返却する<br>
	 * <br>
	 * @return defaultSelValue
	 */
    public String getDefaultSelValue() {
        return defaultSelValue;
    }

	/**
	 * 予約区分(予約状況)の選択値(初期値)設定<br>
	 * <br>
	 * 予約区分(予約状況)の選択値(初期値)を引数で設定する<br>
	 * <br>
	 * @param defaultSelValue
	 */
    public void setDefaultSelValue(String defaultSelValue) {
        this.defaultSelValue = defaultSelValue;
    }

	/**
	 * 予約区分(予約状況)のドロップダウンの使用可/不可 取得<br>
	 * <br>
	 * 予約区分(予約状況)のドロップダウンの使用可/不可を戻り値で返却する<br>
	 * <br>
	 * @return defaultSelValue
	 */
    public String getDisabledFlg() {
        return disabledFlg;
    }

	/**
	 * 予約区分(予約状況)のドロップダウンの使用可/不可設定<br>
	 * <br>
	 * 予約区分(予約状況)のドロップダウンの使用可/不可を引数で設定する<br>
	 * <br>
	 * @param defaultSelValue
	 */
    public void setDisabledFlg(String disabledFlg) {
        this.disabledFlg = disabledFlg;
    }

	/**
	 * 予約区分(予約状況)から判定した予約状態 取得<br>
	 * <br>
	 * 予約区分(予約状況)から判定した予約状態を戻り値で返却する<br>
	 * <br>
	 * @return reservationState
	 */
    public String getReservationState() {
        return reservationState;
    }

	/**
	 * 予約区分(予約状況)から判定した予約状態 設定<br>
	 * <br>
	 * 予約区分(予約状況)から判定した予約状態を引数で設定する<br>
	 * <br>
	 * @param reservationState
	 */
    public void setReservationState(String reservationState) {
        this.reservationState = reservationState;
    }
    // 2014/06/02 レッスン予約に関する「応相談」対応 Add End


}
