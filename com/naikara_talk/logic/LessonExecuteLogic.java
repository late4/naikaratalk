package com.naikara_talk.logic;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dao.LessonReservationPerformanceTrnDao;
import com.naikara_talk.dbutil.ConditionMapper;
import com.naikara_talk.dbutil.OrderCondition;
import com.naikara_talk.dto.LessonReservationPerformanceTrnDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.mstcache.CodeManagMstCache;
import com.naikara_talk.opentok.api.OpenTokSDK;
import com.naikara_talk.opentok.api.constants.RoleConstants;
import com.naikara_talk.opentok.exception.OpenTokException;
import com.naikara_talk.sessiondata.SessionUser;
import com.naikara_talk.util.DateUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称 :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>受講者、講師<br>
 * <b>クラス名称 :</b>レッスン提供<br>
 * <b>クラス概要 :</b>レッスン提供の際に接続の準備をします。<br>
 * <br>
 * <b>著作権 :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 *
 * @author nos <b>変更履歴 :</b>2013/XX/XX MIRA 新規作成
 *                          </b>2014/01/15 TECS FlashからTokboxに変更対応
 *                          </b>2014/04/22 TECS レッスンファイルのコンテンツタイプの追加対応(pdf⇒pdf、txt)
 */
public class LessonExecuteLogic {

	// コネクション変数
	public Connection conn = null;

	// コンストラクタ
	public LessonExecuteLogic(Connection con) {
		this.conn = con;
	}

	/**
	 * 現在時刻で開始可能なレッスンを取得します。(講師用)
	 * @param userId
	 * @param date
	 * @return
	 * @throws NaiException
	 */
	public List<LessonReservationPerformanceTrnDto> getLessonForTeacher(String userId, Date date) throws NaiException{

		return getExecutableLesson(userId, LessonReservationPerformanceTrnDao.TEACHER_ID, date);

	}


	/**
	 * 現在時刻で開始可能なレッスンを取得します。(生徒用)
	 * @param userId
	 * @param date
	 * @return
	 * @throws NaiException
	 */
	public List<LessonReservationPerformanceTrnDto> getLessonForStudent(String userId, Date date) throws NaiException{

		return getExecutableLesson(userId, LessonReservationPerformanceTrnDao.STUDENT_ID, date);

	}

	/**
	 * 現在時刻で開始可能なレッスンを取得します。
	 * @param userId
	 * @param item 処理対象となるカラム
	 * @param date 現在時刻
	 * @return
	 * @throws NaiException
	 */
	private List<LessonReservationPerformanceTrnDto> getExecutableLesson(String userId, String item, Date date) throws NaiException{

		List<LessonReservationPerformanceTrnDto> list = new ArrayList<LessonReservationPerformanceTrnDto>();
		LessonReservationPerformanceTrnDao dao = new LessonReservationPerformanceTrnDao(conn);

		ConditionMapper conditions = new ConditionMapper();

		// 条件設定：状態区分
		conditions.addCondition(LessonReservationPerformanceTrnDao.COND_STATE_KBN
								, new String[]{NaikaraTalkConstants.LESSON_STATE_KBN_RESERVATION,NaikaraTalkConstants.LESSON_STATE_KBN_START});

		// 条件設定：ユーザ（カラムはロールで切り替え）
		conditions.addCondition(item
								, ConditionMapper.OPERATOR_EQUAL
								, userId);

		// 条件設定：レッスン日
		conditions.addCondition(LessonReservationPerformanceTrnDao.LESSON_DT
								, ConditionMapper.OPERATOR_EQUAL
								, DateUtil.toString(date,DateUtil.DATE_FORMAT_yyyyMMdd));


		// 検索実行と開始時刻チェック
	    OrderCondition orderBy = new OrderCondition();
		for (LessonReservationPerformanceTrnDto dto : dao.search(conditions, orderBy)){

			if( dto.getReturnCode() == NaikaraTalkConstants.RETURN_CD_DATA_YES ) {
				if(isTimeToStart(dto.getLessonDt(), dto.getLessonTmNm(), date) == 0) {
					list.add(dto);
				}
			}
		}

		return list;
	}

//	/**
//	 * 現在時刻で開始可能なレッスンを取得します。
//	 * @param userId
//	 * @param item 処理対象となるカラム
//	 * @param date 現在時刻
//	 * @return
//	 * @throws NaiException
//	 */
//	private List<LessonReservationPerformanceTrnDto> getLesson(String userId, String item, Date date) throws NaiException{
//
//		List<LessonReservationPerformanceTrnDto> list = new ArrayList<LessonReservationPerformanceTrnDto>();
//
//		LessonReservationPerformanceTrnDao dao = new LessonReservationPerformanceTrnDao();
//
//		ConditionMapper conditions = new ConditionMapper();
//
//		// 条件設定：ユーザ（カラムはロールで切り替え）
//		conditions.addCondition(item
//								, ConditionMapper.OPERATOR_EQUAL
//								, userId);
//
//		// 条件設定：レッスン日
//		conditions.addCondition(LessonReservationPerformanceTrnDao.LESSON_DT
//								, ConditionMapper.OPERATOR_EQUAL
//								, DateUtil.toString(date,DateUtil.DATE_FORMAT_yyyyMMdd));
//
//
//		// 検索実行と開始時刻チェック
//	    OrderCondition orderBy = new OrderCondition();
//		for (LessonReservationPerformanceTrnDto dto : dao.search(conditions, orderBy)){
//
//			if(dto.getReturnCode() != 2 && isTimeToStart(dto.getLessonDt(), dto.getLessonTmNm(), date) == 0) {
//				list.add(dto);
//			}
//
//		}
//
//		return list;
//
//	}


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

		// 検索実行と開始時刻チェック
	    OrderCondition orderBy = new OrderCondition();
		for (LessonReservationPerformanceTrnDto dto : dao.search(conditions, orderBy)){

			// TODO:どうなるのか混沌としているがとりあえず後続処理に迷惑かけないように。
			if(dto.getReturnCode() !=NaikaraTalkConstants.RETURN_CD_DATA_NO ) {
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
	 * レッスン開始準備を行う
	 * セッションIDの設定。
	 * 教室Noの設定
	 * @param role
	 * @param bdto
	 * @return
	 * @throws NaiException
	 */
	public LessonReservationPerformanceTrnDto standby(
			LessonReservationPerformanceTrnDto dto) throws NaiException {
		// daoのインスタンス
		LessonReservationPerformanceTrnDao dao = new LessonReservationPerformanceTrnDao(conn);

		// updateのパラメタ用のDTO
		LessonReservationPerformanceTrnDto pDto = new LessonReservationPerformanceTrnDto();

		// リトライ処理
		boolean complete = false;
		int retryCnt = 0;
		// 更新が成功するかリトライ回数を超えるまで
		while  (!complete) {

			// 更新対象の検索条件
			ConditionMapper uConditions = new ConditionMapper();
			uConditions.addCondition(LessonReservationPerformanceTrnDao.RESERVATION_NO, ConditionMapper.OPERATOR_EQUAL, dto.getReservationNo());
			uConditions.addCondition(LessonReservationPerformanceTrnDao.RECORD_VER_NO, ConditionMapper.OPERATOR_EQUAL, dto.getRecordVerNo());

			////////////////////////////////////////////////////////
			// レッスン予実テーブルの更新
			////////////////////////////////////////////////////////
			// roomNo設定

			// 2014/01/15 FlashからTokboxに変更対応 start
			// 有無のチェック
			//if (StringUtils.isEmpty(dto.getRoomNo())){
			//	// 存在しなければroomNoを構築
			//	// TODO:roomNoの構築ロジックを作成　現状は予約番号を使用
			//	pDto.setRoomNo(dto.getReservationNo());
			//}

			// TOKBOX関連情報の有無のチェック
			if (StringUtils.isEmpty(dto.getTokboxSessionId()) || StringUtils.isEmpty(dto.getTokboxTokenId())){
				// TOKBOXのapiKeyとapiSecretを取得する
				CodeManagMstCache cache = CodeManagMstCache.getInstance();
				int apiKey = Integer.parseInt(cache.decode(NaikaraTalkConstants.CODE_CATEGORY_TOXBOX_INFO, NaikaraTalkConstants.TOXBOX_APIKEY));
				String apiSecret = cache.decode(NaikaraTalkConstants.CODE_CATEGORY_TOXBOX_INFO, NaikaraTalkConstants.TOXBOX_APISECRET);

				try {
					OpenTokSDK sdk = new OpenTokSDK(apiKey, apiSecret);
					String tokboxSessionId = sdk.create_session().session_id;
					String tokboxTokenId = sdk.generate_token(tokboxSessionId,RoleConstants.PUBLISHER);

					pDto.setTokboxSessionId(tokboxSessionId);
					pDto.setTokboxTokenId(tokboxTokenId);
				} catch (OpenTokException e) {
					throw new NaiException(e);
				}
			}
			// 2014/01/15 FlashからTokboxに変更対応 end

			// セッションIDの設定
			pDto.setTeacherSessionId(dto.getTeacherSessionId());
			pDto.setStudentSessionId(dto.getStudentSessionId());

			// 2014/01/15 FlashからTokboxに変更対応 start
			// レコードバージョン番号の設定
			//pDto.setRecordVerNo(dto.getRecordVerNo() + 1);
			pDto.setRecordVerNo(dto.getRecordVerNo());
			// 2014/01/15 FlashからTokboxに変更対応 end

			// 更新者の設定セッションからのユーザID取得
			pDto.setUpdateCd(
					((SessionUser)SessionDataUtil.getSessionData(SessionUser.class.toString())).getUserId());

			//更新があったら脱出
//			UserTransaction trn = DbUtil.getUserTransaction();
			try {
//				trn.begin();
				if ( dao.update(pDto, uConditions) > 0 ){
					complete = true;
				}
//				trn.commit();
			} catch (Exception e) {
				throw new NaiException(e);
			}

			// リトライ回数を超えたら例外にする。
			if (++retryCnt >= NaikaraTalkConstants.UPDATE_RETRY_COUNT) {
				NaiException e = new NaiException();
				e.setMsg("リトライオーバー");
				throw e;
			}

			// 再度検索の条件設定
			ConditionMapper sCconditions = new ConditionMapper();
			sCconditions.addCondition(LessonReservationPerformanceTrnDao.RESERVATION_NO, ConditionMapper.OPERATOR_EQUAL, dto.getReservationNo());

			// プライマリキーの検索なので1件目を処理
		    OrderCondition orderBy = new OrderCondition();
			dto = dao.search(sCconditions, orderBy).get(0);

		}

		return dto;

	}

	// 2014/01/15 FlashからTokboxに変更対応 start
	/**
	 * PDFファイルをアップロードする（受講者、講師）
	 * @param 更新前のレッスン予実テーブルDTO
	 * @return 更新結果
	 * @throws NaiException
	 */
	public int uploadLessonPdf(LessonReservationPerformanceTrnDto dto) throws NaiException {

		int updatedRowCount = NaikaraTalkConstants.RETURN_CD_ERR_UPD; // 更新データ件数

		// daoのインスタンス
		LessonReservationPerformanceTrnDao dao = new LessonReservationPerformanceTrnDao(conn);

		// updateのパラメタ用のDTO
		LessonReservationPerformanceTrnDto pDto = new LessonReservationPerformanceTrnDto();

		// 検索条件
		ConditionMapper conditions = new ConditionMapper();
		conditions.addCondition(LessonReservationPerformanceTrnDao.RESERVATION_NO, ConditionMapper.OPERATOR_EQUAL, dto.getReservationNo());

		////////////////////////////////////////////////////////
		// レッスン予実テーブルの検索
		////////////////////////////////////////////////////////
		OrderCondition orderCondition = new OrderCondition();
		ArrayList<LessonReservationPerformanceTrnDto> searchDto = dao.search(conditions, orderCondition);

		// レコードバージョン番号の設定
		if (NaikaraTalkConstants.RETURN_CD_DATA_YES == searchDto.get(0).getReturnCode()) {
			pDto.setRecordVerNo(searchDto.get(0).getRecordVerNo());
		} else {
			return updatedRowCount;
		}

		////////////////////////////////////////////////////////
		// レッスン予実テーブルの更新
		////////////////////////////////////////////////////////

		// レッスンファイルの設定
		pDto.setLessonPDF(dto.getLessonPDF());

		// 2014/04/22 Upd Start ファイルタイプの追加対応(pdf⇒pdf、txt)
		// レッスンファイルのコンテンツタイプの設定
		pDto.setLessonFileContentType(dto.getLessonFileContentType());
		// 2014/04/22 Upd End   ファイルタイプの追加対応(pdf⇒pdf、txt)

		// 更新者の設定セッションからのユーザID取得
		pDto.setUpdateCd(((SessionUser)SessionDataUtil.getSessionData(SessionUser.class.toString())).getUserId());

		try {
			// レッスン予実テーブルの更新
			updatedRowCount = dao.update(pDto, conditions);
		} catch (Exception e) {
			throw new NaiException(e);
		}

		return updatedRowCount;
	}

	/**
	 * レッスン情報を取得する（受講者、講師）
	 * @param 検索前のレッスン予実テーブルDTO
	 * @return 検索後のレッスン予実テーブルDTO
	 * @throws NaiException
	 */
	public LessonReservationPerformanceTrnDto getLessonInfoByReservationNo(String reservationNo) throws NaiException {

		// daoのインスタンス
		LessonReservationPerformanceTrnDao dao = new LessonReservationPerformanceTrnDao(conn);

		// 検索条件
		ConditionMapper conditions = new ConditionMapper();
		conditions.addCondition(LessonReservationPerformanceTrnDao.RESERVATION_NO, ConditionMapper.OPERATOR_EQUAL, reservationNo);

		OrderCondition orderCondition = new OrderCondition();

		ArrayList<LessonReservationPerformanceTrnDto> searchDto = dao.search(conditions, orderCondition);

		LessonReservationPerformanceTrnDto dtoResult = null;

        // 設定
        if (NaikaraTalkConstants.RETURN_CD_DATA_YES == searchDto.get(0).getReturnCode()) {
            dtoResult = searchDto.get(0);
        }

        return dtoResult;
	}
	// 2014/01/15 FlashからTokboxに変更対応 end
}
