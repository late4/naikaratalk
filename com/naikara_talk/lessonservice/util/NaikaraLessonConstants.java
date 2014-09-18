package com.naikara_talk.lessonservice.util;

public class NaikaraLessonConstants {

	/** レッスン状況　： 予約 */
	public static final String STGATE_RESERVE 		= "1";
	/** レッスン状況　： レッスン中 */
	public static final String STGATE_WORKING 		= "2";
	/** レッスン状況　： レッスン終了 */
	public static final String STGATE_DONE			= "3";

	/** レッスンイベント　： 入室 */
	public static final String EVENT_OPEN 	= "OPEN";
	/** レッスンイベント ： 開始 */
	public static final String EVENT_START	= "START";
	/** レッスンイベント ： 終了 */
	public static final String EVENT_END	= "END";
	/** レッスンイベント ： NEXTボタン押下 */
	public static final String EVENT_NEXT	= "NEXT";

	/** レッスン区分 正常 */
	public static final String LESSON_KBN_NOMAL	= "0";
	/** レッスン区分 遅刻 */
	public static final String LESSON_KBN_LATE	= "1";
	/** レッスン区分 早退 */
	public static final String LESSON_KBN_LEAVE	= "2";
	/** レッスン区分 早退・遅刻 */
	public static final String LESSON_KBN_LATE_LEAVE	= "3";
	/** レッスン区分 欠席 （アプリから使用しない）*/
	public static final String EVENT_ABSENT	= "4";

}
