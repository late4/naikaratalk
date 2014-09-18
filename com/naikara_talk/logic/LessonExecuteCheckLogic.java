package com.naikara_talk.logic;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dao.CourceMstDaoForLessonService;
import com.naikara_talk.dao.LessonCommentTrnForLessonServiceDao;
import com.naikara_talk.dao.LessonReservationPerformanceTrnDao;
import com.naikara_talk.dbutil.ConditionMapper;
import com.naikara_talk.dbutil.OrderCondition;
import com.naikara_talk.dto.CourseMstDto;
import com.naikara_talk.dto.LessonCommentTrnDto;
import com.naikara_talk.dto.LessonReservationPerformanceTrnDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.lessonservice.util.NaikaraLessonConstants;
import com.naikara_talk.util.DateUtil;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称 :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>レッスン画面<br>
 * <b>クラス名称 :</b>レッスン画面<br>
 * <b>クラス概要 :</b>レッスン画面Logic<br>
 * <br>
 * <b>著作権 :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 *
 * @author MIRA <b>変更履歴 :</b>2013/XX/XX MIRA 新規作成
 *                           </b>2014/01/15 TECS FlashからTokBoxへの変更対応
 */
public class LessonExecuteCheckLogic {

	// コネクション変数
	public Connection conn = null;

	// コンストラクタ
	public LessonExecuteCheckLogic(Connection con) {
		this.conn = con;
	}

	/**
	 * 予約番号でレッスンを取得します。
	 * @param reservationNo 予約番号
	 * @return レッスン予実
	 * @throws NaiException
	 */
	public List<LessonReservationPerformanceTrnDto> getLessonByReservationNo(String reservationNo ) throws NaiException{

		List<LessonReservationPerformanceTrnDto> list = new ArrayList<LessonReservationPerformanceTrnDto>();

		LessonReservationPerformanceTrnDao dao = new LessonReservationPerformanceTrnDao(conn);

		ConditionMapper conditions = new ConditionMapper();

		// 条件設定：予約番号
		conditions.addCondition(LessonReservationPerformanceTrnDao.RESERVATION_NO
								, ConditionMapper.OPERATOR_EQUAL
								, reservationNo);

		// 条件設定：状態区分
		conditions.addCondition(LessonReservationPerformanceTrnDao.COND_STATE_KBN
								, new String[]{NaikaraTalkConstants.LESSON_STATE_KBN_RESERVATION,NaikaraTalkConstants.LESSON_STATE_KBN_START});


		// 検索実行
	    OrderCondition orderBy = new OrderCondition();
		for (LessonReservationPerformanceTrnDto dto : dao.search(conditions, orderBy)){

			// TODO:どうなるのか混沌としているがとりあえず後続処理に迷惑かけないように。
			if(dto.getReturnCode() != NaikaraTalkConstants.RETURN_CD_DATA_NO) {
				list.add(dto);
			}

		}

		return list;

	}

	/**
	 * 予約番号でレッスンを取得します。(生徒用ステータス更新用)
	 * @param reservationNo 予約番号
	 * @return レッスン予実
	 * @throws NaiException
	 */
	public List<LessonReservationPerformanceTrnDto> getLessonByReservationNoStudent(String reservationNo ) throws NaiException{

		List<LessonReservationPerformanceTrnDto> list = new ArrayList<LessonReservationPerformanceTrnDto>();

		LessonReservationPerformanceTrnDao dao = new LessonReservationPerformanceTrnDao(conn);

		ConditionMapper conditions = new ConditionMapper();

		// 条件設定：予約番号
		conditions.addCondition(LessonReservationPerformanceTrnDao.RESERVATION_NO
								, ConditionMapper.OPERATOR_EQUAL
								, reservationNo);

		// 条件設定：状態区分
		conditions.addCondition(LessonReservationPerformanceTrnDao.COND_STATE_KBN
								, new String[]{NaikaraTalkConstants.LESSON_STATE_KBN_RESERVATION,NaikaraTalkConstants.LESSON_STATE_KBN_START,NaikaraTalkConstants.LESSON_STATE_KBN_END});

		// 検索実行
	    OrderCondition orderBy = new OrderCondition();
		for (LessonReservationPerformanceTrnDto dto : dao.search(conditions, orderBy)){

			// TODO:どうなるのか混沌としているがとりあえず後続処理に迷惑かけないように。
			if(dto.getReturnCode() != NaikaraTalkConstants.RETURN_CD_DATA_NO) {
				list.add(dto);
			}

		}

		return list;

	}
	/**
	 * 時刻名文字列から時間を生成して、時間レンジを計算する。
	 * @param dateStr	レッスン日付
	 * @param timeSpanStr	レッスン開始終了の期間文字列 HH:MM-HH:MM
	 * @param date	現在時刻
	 * @return 範囲内＝０　範囲外　超過時間（msec）
	 * @throws NaiException
	 */
	public long isTimeToStart(String dateStr, String timeSpanStr, Date date) throws NaiException{

		String[] tArr = timeSpanStr.split("-");

		if(tArr.length == 2 ){

			// 開始時刻文字列を作成
			String startTime = tArr[0].replace(":", "") + "00";
			// 終了時刻文字列を作成
			String endTime = tArr[1].replace(":", "") + "00";

			Date  startDate = DateUtil.toDate( dateStr + startTime, DateUtil.DATE_FORMAT_yyyyMMddHHmmss);
			Date  endDate = DateUtil.toDate( dateStr + endTime, DateUtil.DATE_FORMAT_yyyyMMddHHmmss);

			if((startDate.getTime() - date.getTime()) <= NaikaraTalkConstants.LESSON_START_OFFSET_MSEC){

				if ((endDate.getTime() - date.getTime()) >= NaikaraTalkConstants.LESSON_END_OFFSET_MSEC) {
					return 0;
				} else {
					return (date.getTime() - endDate.getTime());
				}
			}
			return (startDate.getTime() - date.getTime());
		} else {
			// レッスン書式文字列がおかしいので例外
			throw new NaiException("レッスン時刻書式が無効");
		}
	}

	/**
	 * コースコードでコースマスタを取得します。
	 * @param courseCd コースコード
	 * @return コースマスタ
	 * @throws NaiException
	 */
	public List<CourseMstDto> getCourceMst(String courseCd ) throws NaiException{

		List<CourseMstDto> list = new ArrayList<CourseMstDto>();

		CourceMstDaoForLessonService dao = new CourceMstDaoForLessonService(conn);

		ConditionMapper conditions = new ConditionMapper();

		// 条件設定：コースコード
		conditions.addCondition(CourceMstDaoForLessonService.COND_COURSE_CD
								, ConditionMapper.OPERATOR_EQUAL
								, courseCd);

		// 検索実行
	    OrderCondition orderBy = new OrderCondition();
		for (CourseMstDto dto : dao.search(conditions, orderBy)){

			// TODO:どうなるのか混沌としているがとりあえず後続処理に迷惑かけないように。
			if(dto.getReturnCode() != NaikaraTalkConstants.RETURN_CD_DATA_NO) {
				list.add(dto);
			}

		}

		return list;

	}


	/**
	 * 引継ぎコメントを検索します。
	 * 同一受講者、同一コースで状態区分が終了状態で
	 * もっとも最近のレッスンを取り出す
	 * TODO:インデックスをつけないとまずい。
	 * @param strudentId 受講者ID
	 * @param courseCd コースコード
	 * @return 引継ぎコメント
	 * @throws NaiException
	 */
	public List<LessonCommentTrnDto> getCommentTo(String strudentId, String courseCd) throws NaiException{

		// コメントテーブル
		List<LessonCommentTrnDto> result = new ArrayList<LessonCommentTrnDto>();

		LessonReservationPerformanceTrnDao dao = new LessonReservationPerformanceTrnDao(conn);

		ConditionMapper conditions = new ConditionMapper();


		// 条件設定：レッスン状態区分(終了) TODO:状態区分を定数登録する。
		conditions.addCondition(LessonReservationPerformanceTrnDao.COND_STATE_KBN
								, ConditionMapper.OPERATOR_EQUAL
								, "3");
		// 条件設定：受講者ID
		conditions.addCondition(LessonReservationPerformanceTrnDao.STUDENT_ID
								, ConditionMapper.OPERATOR_EQUAL
								, strudentId);

		// 条件設定：コースコード
		conditions.addCondition(LessonReservationPerformanceTrnDao.COND_COURSE_CD
								, ConditionMapper.OPERATOR_EQUAL
								, courseCd);

		// ソート設定
	    OrderCondition orderBy = new OrderCondition();
	    // 日付時刻で降順ソート　一レコード目を使用する前提
	    orderBy.addCondition(LessonReservationPerformanceTrnDao.LESSON_DT, OrderCondition.DESC);
	    orderBy.addCondition(LessonReservationPerformanceTrnDao.LESSON_TM_NM, OrderCondition.DESC);

		// 検索実行
	    // TODO:どうなるのか混沌としているがとりあえず後続処理に迷惑かけないように。
		// レッスン予実
	    LessonReservationPerformanceTrnDto rdto = dao.search(conditions, orderBy).get(0);

	    if ((rdto.getReturnCode() != NaikaraTalkConstants.RETURN_CD_DATA_NO) && (rdto != null)) {


	    	LessonCommentTrnForLessonServiceDao cdao = new LessonCommentTrnForLessonServiceDao(conn);

	    	ConditionMapper mapper = new ConditionMapper();
	    	mapper.addCondition(LessonCommentTrnForLessonServiceDao.RESERVATION_NO
	    						,ConditionMapper.OPERATOR_EQUAL
	    						,rdto.getReservationNo());

	    	mapper.addCondition(LessonCommentTrnForLessonServiceDao.CMT_INPUTS_KBN
					,ConditionMapper.OPERATOR_EQUAL
					,"T");

	    	ArrayList<LessonCommentTrnDto> dtoList = cdao.search(mapper, new OrderCondition());
	    	for(LessonCommentTrnDto dto: dtoList) {
	    		result.add(dto);
	    	}
		}
		return result;
	}


	/**
	 * レッスンコメントテーブルを検索します。
	 *
	 * TODO:インデックスをつけないとまずい。
	 * @param sreservationNo 予約番号
	 * @return 引継ぎコメント
	 * @throws NaiException
	 */
	private List<LessonCommentTrnDto> getLessonComntNextTeacher(String reservationNo) throws NaiException{

		// コメントテーブル
		List<LessonCommentTrnDto> result = new ArrayList<LessonCommentTrnDto>();

		LessonCommentTrnForLessonServiceDao dao = new LessonCommentTrnForLessonServiceDao(conn);

		ConditionMapper conditions = new ConditionMapper();


		// 条件設定：予約番号。
		conditions.addCondition(LessonCommentTrnForLessonServiceDao.RESERVATION_NO
								, ConditionMapper.OPERATOR_EQUAL
								, reservationNo);
		// 条件設定：登録者 TODO : 定数化する。
		conditions.addCondition(LessonCommentTrnForLessonServiceDao.CMT_INPUTS_KBN
								, ConditionMapper.OPERATOR_EQUAL
								, "T");

		OrderCondition orderBy = new OrderCondition();

	    // TODO:どうなるのか混沌としているがとりあえず後続処理に迷惑かけないように。
		LessonCommentTrnDto dto = dao.search(conditions, orderBy).get(0);
		if( dto.getReturnCode() != NaikaraTalkConstants.RETURN_CD_DATA_NO){
			result.add( dto);
		}

		return result;

	}

	/**
	 * 講師レッスンのステータスを設定する
	 * この処理は、制御上の更新で特定の更新者が特定の項目を更新するため
	 * 意図的な排他制御を必要としません。
	 * waitによって制御されます。
	 * レコードバージョン番号はDAOによって更新されます。
	 *
	 * @param role
	 * @param bdto
	 * @return
	 * @throws NaiException
	 */
	public void setTeacherLessonStatus(Map<String, String> map) throws NaiException {

		// daoのインスタンス
		LessonReservationPerformanceTrnDao dao = new LessonReservationPerformanceTrnDao(conn);
		// updateのパラメタ用のDTO
		LessonReservationPerformanceTrnDto dto = new LessonReservationPerformanceTrnDto();

		// TODO :ここでタイムスタンプを採ることはどうかと？
		Timestamp timestamp = DateUtil.getSysTimestamp();

		// レッスンの状態を記録
		if(StringUtils.equalsIgnoreCase(NaikaraLessonConstants.EVENT_OPEN, map.get("event"))){
			// レッスン区分設定なし
			// 講師入室
			dto.setTeacherInDttm(timestamp);

    	} else if(StringUtils.equalsIgnoreCase(NaikaraLessonConstants.EVENT_START, map.get("event"))){

    		// レッスン区分の設定
    		// 予定レッスン開始時刻文字列を作成する
    		String[] tArr = map.get("lessonTmNm").split("-");
			String dateStr = map.get("lessonDt") + tArr[0].replace(":", "") + "00";

			// 現在時刻文字列を構築する
			Date  startDate = DateUtil.toDate( dateStr, DateUtil.DATE_FORMAT_yyyyMMddHHmmss);

			if(timestamp.after(startDate)){
				// 遅刻に設定する
	    		dto.setTeacherLessonKbn(NaikaraLessonConstants.LESSON_KBN_LATE);
			}else{
				// 正常に設定する
	    		dto.setTeacherLessonKbn(NaikaraLessonConstants.LESSON_KBN_NOMAL);
			}

			// 状態区分を開始にする
			dto.setStateKbn(NaikaraLessonConstants.STGATE_WORKING);
    		// 講師スタート時刻設定
    		dto.setTeacherStartDttm(timestamp);

    		// レッスンスタート時刻設定
    		dto.setLessonStartDttm(timestamp);

    	} else if(StringUtils.equalsIgnoreCase(NaikaraLessonConstants.EVENT_END, map.get("event"))){

    		// 講師のEND
    		// レッスン区分の設定
    		// 予定レッスン終了時刻文字列を作成する
    		String[] tArr = map.get("lessonTmNm").split("-");
			String dateStr = map.get("lessonDt") + tArr[1].replace(":", "") + "00";

			// 現在時刻文字列を構築する
			Date  endDate = DateUtil.toDate( dateStr, DateUtil.DATE_FORMAT_yyyyMMddHHmmss);

			// 一旦テーブルから前回設定の
			List<LessonReservationPerformanceTrnDto> list = this.getLessonByReservationNo(map.get("reservationNo"));
    		String kbn = "";
    		for(LessonReservationPerformanceTrnDto ldto : list){
    			kbn = ldto.getTeacherLessonKbn();
    		}
			if(timestamp.after(endDate)){
				// 正常終了の場合
				// 元の値を設定する
//	    		dto.setTeacherLessonKbn(kbn);
				if (StringUtils.equals(kbn, NaikaraLessonConstants.LESSON_KBN_LEAVE)) {
		    		dto.setTeacherLessonKbn(NaikaraLessonConstants.LESSON_KBN_NOMAL);
				} else {
		    		dto.setTeacherLessonKbn(kbn);
				}
			}else{
//				// 早退の場合
//				if(StringUtils.equals(kbn, NaikaraLessonConstants.LESSON_KBN_LATE)){
//					// 遅刻&早退に設定する
//					dto.setTeacherLessonKbn(NaikaraLessonConstants.LESSON_KBN_LATE_LEAVE);
//				}else{
//					// 早退に設定する
//					dto.setTeacherLessonKbn(NaikaraLessonConstants.LESSON_KBN_LEAVE);
//
//				}
				// 早退の場合
				if(StringUtils.equals(kbn, NaikaraLessonConstants.LESSON_KBN_LATE)){
					// 遅刻&早退に設定する
					dto.setTeacherLessonKbn(NaikaraLessonConstants.LESSON_KBN_LATE_LEAVE);
				} else if (StringUtils.equals(kbn, NaikaraLessonConstants.LESSON_KBN_LATE_LEAVE)) {
		    		dto.setTeacherLessonKbn(kbn);
				} else  {
					// 早退に設定する
					dto.setTeacherLessonKbn(NaikaraLessonConstants.LESSON_KBN_LEAVE);

				}
			}

			// 状態区分を終了にする
			dto.setStateKbn(NaikaraLessonConstants.STGATE_DONE);

    		// 講師終了時刻設定
    		dto.setTeacherEndDttm(timestamp);

    		// レッスン終了時刻を設定
    		dto.setLessonEndDttm(timestamp);

    	} else if(StringUtils.equalsIgnoreCase(NaikaraLessonConstants.EVENT_NEXT, map.get("event"))){
    		// 講師NEXT
			// レッスン区分設定なし
    		dto.setTeacherNextDttm(timestamp);

			// 2014/01/15 FlashからTokboxに変更対応 start
			// 状態区分を終了にする
			dto.setStateKbn(NaikaraLessonConstants.STGATE_DONE);
			// 2014/01/15 FlashからTokboxに変更対応 end
    	}

    	// 更新対象の検索条件
    	ConditionMapper uConditions = new ConditionMapper();
    	uConditions.addCondition(LessonReservationPerformanceTrnDao.RESERVATION_NO, ConditionMapper.OPERATOR_EQUAL, map.get("reservationNo"));

    	////////////////////////////////////////////////////////
    	// レッスン予実テーブルの更新
    	////////////////////////////////////////////////////////

    	// 更新者の設定セッションからのユーザID取得
    	dto.setUpdateCd(map.get("userId"));

    	try {
    		dao.updateStatus(dto, uConditions);


    	} catch (Exception e) {
    		throw new NaiException(e);
    	}

	}

	/**
	 * 受講者レッスンのステータスを設定する
	 * この処理は、制御上の更新で特定の更新者が特定の項目を更新するため
	 * 意図的な排他制御を必要としません。
	 * waitによって制御されます。
	 * レコードバージョン番号はDAOによって更新されます。
	 *
	 * @param role
	 * @param bdto
	 * @return
	 * @throws NaiException
	 */
	public void setStudentLessonStatus(Map<String, String> map) throws NaiException {

		// daoのインスタンス
		LessonReservationPerformanceTrnDao dao = new LessonReservationPerformanceTrnDao(conn);
		// updateのパラメタ用のDTO
		LessonReservationPerformanceTrnDto dto = new LessonReservationPerformanceTrnDto();

		// ここでタイムスタンプを採ることはどうかと？
		Timestamp timestamp = DateUtil.getSysTimestamp();

		// レッスンの状態を記録
		if(StringUtils.equalsIgnoreCase(NaikaraLessonConstants.EVENT_OPEN, map.get("event"))){
			// レッスン区分設定なし
			// 生徒入室
			dto.setStudentInDttm(timestamp);

    	} else if(StringUtils.equalsIgnoreCase(NaikaraLessonConstants.EVENT_START, map.get("event"))){

    		// レッスン区分の設定
    		// 予定レッスン開始時刻文字列を作成する
    		String[] tArr = map.get("lessonTmNm").split("-");
    		String dateStr = map.get("lessonDt") + tArr[0].replace(":", "") + "00";


    		// 現在時刻文字列を構築する
    		Date  startDate = DateUtil.toDate( dateStr, DateUtil.DATE_FORMAT_yyyyMMddHHmmss);

    		if(timestamp.after(startDate)){
    			// 遅刻に設定する
    			dto.setStudentLessonKbn(NaikaraLessonConstants.LESSON_KBN_LATE);
    		}else{
    			// 正常に設定する
    			dto.setStudentLessonKbn(NaikaraLessonConstants.LESSON_KBN_NOMAL);
    		}

			// 状態区分を開始にする
			dto.setStateKbn(NaikaraLessonConstants.STGATE_WORKING);

    		// 生徒スタート時刻設定
    		dto.setStudentStartDttm(timestamp);

    	} else if(StringUtils.equalsIgnoreCase(NaikaraLessonConstants.EVENT_END, map.get("event"))){

    		// 受講者のEND
    		// レッスン区分の設定
    		// 予定レッスン終了時刻文字列を作成する
    		String[] tArr = map.get("lessonTmNm").split("-");
			String dateStr = map.get("lessonDt") + tArr[1].replace(":", "") + "00";

			// 現在時刻文字列を構築する
			Date  endDate = DateUtil.toDate( dateStr, DateUtil.DATE_FORMAT_yyyyMMddHHmmss);

			// 一旦テーブルから前回設定の
			List<LessonReservationPerformanceTrnDto> list = this.getLessonByReservationNoStudent(map.get("reservationNo"));
    		String kbn = "";
    		for(LessonReservationPerformanceTrnDto ldto : list){
    			kbn = ldto.getStudentLessonKbn();
    		}
			if(timestamp.after(endDate)){
				// 正常終了の場合
				// 元の値を設定する
				if (StringUtils.equals(kbn, NaikaraLessonConstants.LESSON_KBN_LEAVE)) {
		    		dto.setStudentLessonKbn(NaikaraLessonConstants.LESSON_KBN_NOMAL);
				} else {
		    		dto.setStudentLessonKbn(kbn);
				}
			}else{
				// 早退の場合
				if(StringUtils.equals(kbn, NaikaraLessonConstants.LESSON_KBN_LATE)){
					// 遅刻&早退に設定する
					dto.setStudentLessonKbn(NaikaraLessonConstants.LESSON_KBN_LATE_LEAVE);
				} else if (StringUtils.equals(kbn, NaikaraLessonConstants.LESSON_KBN_LATE_LEAVE)) {
		    		dto.setStudentLessonKbn(kbn);
				} else  {
					// 早退に設定する
					dto.setStudentLessonKbn(NaikaraLessonConstants.LESSON_KBN_LEAVE);

				}
			}

//			// 状態区分を終了にする
//			dto.setStateKbn(NaikaraLessonConstants.STGATE_DONE);

			// 生徒の終了時刻設定
    		dto.setStudentEndDttm(timestamp);

    	}

    	// 更新対象の検索条件
    	ConditionMapper uConditions = new ConditionMapper();
    	uConditions.addCondition(LessonReservationPerformanceTrnDao.RESERVATION_NO, ConditionMapper.OPERATOR_EQUAL, map.get("reservationNo"));

    	////////////////////////////////////////////////////////
    	// レッスン予実テーブルの更新
    	////////////////////////////////////////////////////////

    	// 更新者の設定セッションからのユーザID取得
    	dto.setUpdateCd(map.get("userId"));

    	try {
    		dao.updateStatus(dto, uConditions);


    	} catch (Exception e) {
    		throw new NaiException(e);
    	}

	}

}
