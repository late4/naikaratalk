package com.naikara_talk.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dbutil.DbUtil;
import com.naikara_talk.dto.CourseMstDto;
import com.naikara_talk.dto.LessonCommentTrnDto;
import com.naikara_talk.dto.LessonReservationPerformanceTrnDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.logic.LessonExecuteCheckLogic;
import com.naikara_talk.logic.LessonExecuteLogic;
import com.naikara_talk.logic.LessonSumTimeLogic;
import com.naikara_talk.model.LessonLauncherModel;
import com.naikara_talk.sessiondata.SessionUser;
import com.naikara_talk.util.DateUtil;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * レッスン開始前の準備をします。
 *
 * @author nos
 *
 */
public class LessonLauncherService implements ActionService {

	public static final String ONTIME = "ONTIME";
	public static final String TO_FAST = "TO_FAST";
	public static final String TO_LATE = "TO_LATE";

	// public static final String ROLE_TEACHER = "TEACHER";
	// public static final String ROLE_STUDENT = "STUDENT";

	// private String role;
	// private String userId;

	/**
	 * レッスンの実行判定を行います。
	 *
	 * @param userId
	 *            予約番号
	 * @param role
	 *            講師or生徒
	 * @return 受講可能なレッスン
	 * @throws NaiException
	 */
	public List<LessonLauncherModel> getExecutableLesson(
			LessonLauncherModel model) throws NaiException {

		// レッスン予約レコード
		List<LessonLauncherModel> listModel = new ArrayList<LessonLauncherModel>();
		// レッスン予実テーブルのDTO
		List<LessonReservationPerformanceTrnDto> listDto;

		// 現在時刻とユーザIDでレッスンを検索します。
		Connection conn = null;
		try {

			conn = DbUtil.getConnection();

			LessonExecuteLogic lessonLogic = new LessonExecuteLogic(conn);

			if (StringUtils.equals(model.getRole(), SessionUser.ROLE_TEACHER)) {
				listDto = lessonLogic.getLessonForTeacher(model.getTeacherId(),
						DateUtil.getOperationDate());
			} else {
				listDto = lessonLogic.getLessonForStudent(model.getStudentId(),
						DateUtil.getOperationDate());
			}

			// 取れたリストの最初の1件を対象とする。取れてなければ蛻のリストが返却されます。
			if (!listDto.isEmpty()) {
				// 最初の1件を取り出す
				LessonReservationPerformanceTrnDto bdto = listDto.get(0);

				if (!StringUtils.isEmpty(model.getTeacherSessionId())) {
					bdto.setTeacherSessionId(model.getTeacherSessionId());
				} else if ((!StringUtils.isEmpty(model.getStudentSessionId()))) {
					bdto.setStudentSessionId(model.getStudentSessionId());
				}

				LessonReservationPerformanceTrnDto dto = lessonLogic
						.standby(bdto);

				LessonLauncherModel rModel = new LessonLauncherModel();

				rModel.setReservationNo(dto.getReservationNo());
				rModel.setTeacherId(dto.getTeacherId());
				rModel.setStudentId(dto.getStudentId());

				// 2014/01/15 FlashからTokboxに変更対応 start
				// rModel.setRoomNo(dto.getRoomNo());
				// 2014/01/15 FlashからTokboxに変更対応 end

				rModel.setTeacherSessionId(dto.getTeacherSessionId());
				rModel.setStudentSessionId(dto.getStudentSessionId());

				listModel.add(rModel);

				conn.commit();
			}
		} catch (SQLException e) {
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					throw new NaiException(e1);
				}
			}
			throw new NaiException(e);
		} finally {
			try {
				conn.close();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

		return listModel;
	}

	/**
	 * 講師用レッスン関連の表示情報その他を取得
	 *
	 * @param sessionId
	 * @param reservationNo
	 * @param userId
	 * @return
	 * @throws NaiException
	 */
	public Object getLessonInfoTeacher(Object param) throws NaiException {

		// 返却用のマップ
		Map<String, Object> resp = new HashMap<String, Object>();

		// パラメタを展開したマップ
		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) param;

		// 予約Noでレッスン情報を取得
		resp = getLessonInfo(map.get("reservationNo"));
		// 引継ぎコメントの取得
		resp.putAll(getComment(resp.get("studentId"), resp.get("courseCd")));

		// 2014/01/15 FlashからTokboxに変更対応 start
		// コース説明と添付付き有無フラグの取得
		resp.putAll(getCourseInfo(resp.get("courseCd")));
		// 2014/01/15 FlashからTokboxに変更対応 end

		return resp;
	}

	/**
	 * 受講者用レッスン関連の表示情報その他を取得
	 *
	 * @param sessionId
	 * @param reservationNo
	 * @param userId
	 * @return
	 * @throws NaiException
	 */
	public Object getLessonInfoStudent(Object param) throws NaiException {

		// 返却用のマップ
		Map<String, Object> resp = new HashMap<String, Object>();

		// パラメタを展開したマップ
		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) param;

		// 予約Noでレッスン情報を取得
		resp = getLessonInfo(map.get("reservationNo"));

		// コース説明と添付付き有無フラグの取得
		resp.putAll(getCourseInfo(resp.get("courseCd")));

		return resp;
	}

	/**
	 * コースの情報を取得する。
	 *
	 * @throws NaiException
	 */
	private Map<String, Object> getCourseInfo(Object courseCd)
			throws NaiException {

		HashMap<String, Object> map = new HashMap<String, Object>();

		Connection conn = null;

		try {
			conn = DbUtil.getConnection();

			// レッスン予実テーブルを検索
			LessonExecuteCheckLogic logic = new LessonExecuteCheckLogic(conn);

			List<CourseMstDto> list = logic.getCourceMst((String) courseCd);
			if (list.get(0).getReturnCode() == NaikaraTalkConstants.RETURN_CD_DATA_YES) {
				CourseMstDto dto = list.get(0);
				// 簡易説明
				map.put("courseSimpleExplanation", dto.getSimpleExplanation());
				// 2014/01/15 FlashからTokboxに変更対応 start
				// 添付付き有無フラグ
				map.put("attachmentFlg", dto.getAttachmentFlg());
				// 2014/01/15 FlashからTokboxに変更対応 end
			}

		} catch (SQLException e) {
			throw new NaiException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					throw new NaiException(e);
				}
			}
		}
		return map;
	}

	/**
	 * 引継ぎコメントを取得する。
	 *
	 * @param string
	 * @throws NaiException
	 */
	private Map<String, String> getComment(Object userId, Object courseCd)
			throws NaiException {

		HashMap<String, String> map = new HashMap<String, String>();

		Connection conn = null;

		try {
			conn = DbUtil.getConnection();

			// レッスン予実テーブルを検索
			LessonExecuteCheckLogic logic = new LessonExecuteCheckLogic(conn);

			// 1件しか返却されないので
			for (LessonCommentTrnDto dto : logic.getCommentTo((String) userId,
					(String) courseCd)) {

				// 引継ぎコメント
				map.put("commentTo", dto.getTNextTeacherCmt());
			}

		} catch (SQLException e) {
			throw new NaiException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					throw new NaiException(e);
				}
			}

		}

		return map;
	}

	/**
	 * レッスン関連の表示情報その他を取得
	 *
	 * @param sessionId
	 * @param reservationNo
	 * @param userId
	 * @return
	 * @throws NaiException
	 */
	private Map<String, Object> getLessonInfo(String reservationNo)
			throws NaiException {

		// 返却用のマップ
		Map<String, Object> resp = new HashMap<String, Object>();

		Connection conn = null;

		try {
			conn = DbUtil.getConnection();

			// レッスン予実テーブルを検索
			LessonExecuteCheckLogic logic = new LessonExecuteCheckLogic(conn);

			List<LessonReservationPerformanceTrnDto> list = logic
					.getLessonByReservationNo(reservationNo);
			// if(list.size() > 0) {

			// レッスン予約情報を取得(プライマリで検索なので最初の1レコード前提)
			LessonReservationPerformanceTrnDto lDto;

			if (list.get(0).getReturnCode() == NaikaraTalkConstants.RETURN_CD_DATA_YES) {
				lDto = list.get(0);
				resp.put("reservationNo", lDto.getReservationNo()); // 予約No
				resp.put("studentId", lDto.getStudentId()); // 受講者ID
				resp.put("studentNickNm", lDto.getStudentNickNm()); // 受講者ニックネーム
				resp.put("teacherId", lDto.getTeacherId()); // 担当講師ID
				resp.put("teacherNickNm", lDto.getTeacherNickNm()); // 担当講師ニックネーム
				resp.put("lessonDt", lDto.getLessonDt()); // レッスン日
				resp.put("lessonTmCd", lDto.getLessonTmCd()); // レッスン時刻コード
				resp.put("lessonTmNm", lDto.getLessonTmNm()); // レッスン時刻名
				// ステータス設定用の日付群　とりあえず全部採っておく
				resp.put("studentInDttm", DateUtil.toString(
						lDto.getStudentInDttm(),
						DateUtil.DATE_FORMAT_yyyyMMddHHmmssSSS));
				resp.put("studentStartDttm", DateUtil.toString(
						lDto.getStudentStartDttm(),
						DateUtil.DATE_FORMAT_yyyyMMddHHmmssSSS));
				resp.put("studentEndDttm", DateUtil.toString(
						lDto.getStudentEndDttm(),
						DateUtil.DATE_FORMAT_yyyyMMddHHmmssSSS));
				resp.put("teacherInDttm", DateUtil.toString(
						lDto.getTeacherInDttm(),
						DateUtil.DATE_FORMAT_yyyyMMddHHmmssSSS));
				resp.put("teacherStartDttm", DateUtil.toString(
						lDto.getTeacherStartDttm(),
						DateUtil.DATE_FORMAT_yyyyMMddHHmmssSSS));
				resp.put("teacherEndDttm()", DateUtil.toString(
						lDto.getTeacherEndDttm(),
						DateUtil.DATE_FORMAT_yyyyMMddHHmmssSSS));
				resp.put("teacherNextDttm", DateUtil.toString(
						lDto.getTeacherNextDttm(),
						DateUtil.DATE_FORMAT_yyyyMMddHHmmssSSS));
				resp.put("lessonStartDttm", DateUtil.toString(
						lDto.getLessonStartDttm(),
						DateUtil.DATE_FORMAT_yyyyMMddHHmmssSSS));
				resp.put("lessonEndDttm", DateUtil.toString(
						lDto.getLessonEndDttm(),
						DateUtil.DATE_FORMAT_yyyyMMddHHmmssSSS));

				// end of ステータス設定用の日付群　とりあえず全部採っておく
				LessonSumTimeLogic sumtimeLogic = new LessonSumTimeLogic(conn);
				if (sumtimeLogic.getLocSumTime(lDto.getTeacherId() // レッスン時刻名(現地時刻)
						, lDto.getLessonTmNm()).get(0).getReturnCode() == NaikaraTalkConstants.RETURN_CD_DATA_YES) {
					// うまく取れたときだけ表示
					resp.put(
							"localLessonTmNm",
							sumtimeLogic
									.getLocSumTime(lDto.getTeacherId(),
											lDto.getLessonTmNm()).get(0)
									.getLocalTimeFromTo());
				}

				resp.put("bigClassificationJcd", lDto.getBigClassificationJcd()); // 大分類コード
				resp.put("bigClassificationJnm", lDto.getBigClassificationJnm()); // 大分類名
				resp.put("middleClassificationJcd",
						lDto.getMiddleClassificationJcd()); // 中分類コード
				resp.put("middleClassificationJnm",
						lDto.getMiddleClassificationJnm()); // 中分類名
				resp.put("smallClassificationJcd",
						lDto.getSmallClassificationJcd()); // 小分類コード
				resp.put("smallClassificationJnm",
						lDto.getSmallClassificationJnm()); // 小分類名
				resp.put("bigClassificationEcd", lDto.getBigClassificationEcd()); // 大分類コード(英語)
				resp.put("bigClassificationEnm", lDto.getBigClassificationEnm()); // 大分類名(英語)
				resp.put("middleClassificationEcd",
						lDto.getMiddleClassificationEcd()); // 中分類コード(英語)
				resp.put("middleClassificationEnm",
						lDto.getMiddleClassificationEnm()); // 中分類名(英語)
				resp.put("smallClassificationEcd",
						lDto.getSmallClassificationEcd()); // 小分類コード(英語)
				resp.put("smallClassificationEnm",
						lDto.getSmallClassificationEnm()); // 小分類名(英語)
				resp.put("courseCd", lDto.getCourseCd()); // コースコード
				resp.put("courseJnm", lDto.getCourseJnm()); // コース名
				resp.put("courseEnm", lDto.getCourseEnm()); // コース名(英語)

				// 2014/01/15 FlashからTokboxに変更対応 start
				// resp.put("roomNo",lDto.getRoomNo()); //教室No
				resp.put("tokboxSessionId", lDto.getTokboxSessionId());
				resp.put("tokboxTokenId", lDto.getTokboxTokenId());
				resp.put("lessonPDF", lDto.getFileDuringLessons());
				// 2014/01/15 FlashからTokboxに変更対応 end

				resp.put("serverTime", DateUtil.toString(
						DateUtil.getOperationDate(),
						DateUtil.DATE_FORMAT_yyyyMMddHHmmss)); // サーバ時刻を設定

			} else {
				resp.put("message", "");
			}
		} catch (SQLException e) {
			throw new NaiException(e);
		} finally {
			try {
				conn.close();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

		return resp;
	}

	/**
	 * レッスンステータス更新受講者用
	 *
	 * @param prm
	 * @throws NaiException
	 */
	public void setStatusStudent(Object prm) throws NaiException {

		Connection conn = null;
		try {
			conn = DbUtil.getConnection();

			// パラメタを展開したマップ
			@SuppressWarnings("unchecked")
			Map<String, String> map = (Map<String, String>) prm;
			LessonExecuteCheckLogic logic = new LessonExecuteCheckLogic(conn);

			logic.setStudentLessonStatus(map);
			conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {

			}

			throw new NaiException(e);
		} catch (NaiException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
			}

			throw e;
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					throw new NaiException(e);
				}
			}
		}
	}

	/**
	 * レッスンステータス更新講師用
	 *
	 * @param prm
	 * @throws NaiException
	 */
	public void setStatusTeacher(Object prm) throws NaiException {

		Connection conn = null;
		try {
			conn = DbUtil.getConnection();

			// パラメタを展開したマップ
			@SuppressWarnings("unchecked")
			Map<String, String> map = (Map<String, String>) prm;
			LessonExecuteCheckLogic logic = new LessonExecuteCheckLogic(conn);

			logic.setTeacherLessonStatus(map);

			conn.commit();

		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
			}

			throw new NaiException(e);
		} catch (NaiException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {

			}

			throw e;
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {

					throw new NaiException(e);
				}
			}
		}
	}

	/**
	 * 認証を行う
	 *
	 * @description クライアント接続時に自動的に呼ばれる
	 */
	public boolean appConnect(Object[] params) throws NaiException {

		@SuppressWarnings("unchecked")
		Map<String, String> prm = (Map<String, String>) params[0];

		return checkUser(prm.get("reservationNo"), prm.get("userId"),
				prm.get("sessionId"), prm.get("role"));
	}

	/**
	 * 接続チェックを行う
	 *
	 * @param reservationNo
	 * @param userId
	 * @param sessionId
	 * @param role
	 * @return
	 */
	public boolean checkUser(String reservationNo, String userId,
			String sessionId, String role) throws NaiException {

		Connection conn = null;

		try {

			conn = DbUtil.getConnection();

			// レッスン予実テーブルを検索
			LessonExecuteCheckLogic logic = new LessonExecuteCheckLogic(conn);

			List<LessonReservationPerformanceTrnDto> list = logic
					.getLessonByReservationNo(reservationNo);

			if (list.size() > 0) {
				// レッスン予約情報を取得(プライマリで検索なので最初の1レコード前提)
				LessonReservationPerformanceTrnDto dto = list.get(0);

				// 現在時刻がレンジ内で終了していないこと
				if (logic.isTimeToStart(dto.getLessonDt(), dto.getLessonTmNm(),
						DateUtil.getOperationDate()) == 0) {

					// セッションID、ユーザIDが一致
					if (StringUtils.equals(role, SessionUser.ROLE_STUDENT)) {
						// 生徒のユーザID　セッションIDが一致
						if (StringUtils.equals(userId, dto.getStudentId())
								&& StringUtils.equals(sessionId,
										dto.getStudentSessionId())) {

							return true;
						}

					} else if (StringUtils.equals(role,
							SessionUser.ROLE_TEACHER)) {
						// 講師のユーザID　セッションIDが一致
						if (StringUtils.equals(userId, dto.getTeacherId())
								&& StringUtils.equals(sessionId,
										dto.getTeacherSessionId())) {

							return true;
						}
					}
				}
			}
		} catch (SQLException e) {
			throw new NaiException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
		// 外に流れてきたのは全部失敗
		// 接続失敗
		return false;
	}
}
