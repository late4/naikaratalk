package com.naikara_talk.logic;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedHashMap;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dao.LessonSumTimeDao;
import com.naikara_talk.dto.CodeManagMstDto;
import com.naikara_talk.dto.LocSumTimeDto;
import com.naikara_talk.dto.TimeManagMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.mstcache.CodeManagMstCache;
import com.naikara_talk.util.NaikaraTalkConstants;




/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称:</b>共通部品 DAOクラス<br>
 * <b>クラス名称　　　:</b>レッスン時刻単位の時差計算処理クラス<br>
 * <b>クラス概要　　　:</b>レッスン時刻単位の時差計算処理DAO<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/05/27 TECS 新規作成
 */
public class LessonSumTimeLogic {

	// コネクション変数
	public Connection conn = null;

	// コンストラクタ
	public LessonSumTimeLogic(Connection con) {
		this.conn = con;
	}

	/**
	 * レッスン時刻単位の時差計算処理<br>
	 * <br>
	 * 講師IDで指定のローカル・サマータイムリストを時差管理マスタと汎用フィールドから算出する<br>
	 * <br>
	 * @param userID
	 * @return ArrayList<LocSumTimeDto>
	 * @throws NaiException
	 */
	public ArrayList<LocSumTimeDto> getLocSumTime(String userID) throws NaiException {
		return this.getLocSumTime(userID, null);
	}


	/**
	 * レッスン時刻単位の時差計算処理<br>
	 * <br>
	 * 講師IDとレッスン時刻で指定のローカル・サマータイムを時差管理マスタから算出する<br>
	 * <br>
	 * @param userID
	 * @param lessonTime
	 * @return ArrayList<LocSumTimeDto>
	 * @throws NaiException
	 */
	public ArrayList<LocSumTimeDto> getLocSumTime(String userID,String lessonTime) throws NaiException {
		//戻り値とリターンコードの初期化
		ArrayList<LocSumTimeDto> list = null; //ローカル・サマータイムリスト
		LocSumTimeDto dto = null; //ローカル・サマータイムDTO

		//引数のパラメータチェック
		//講師IDの必須チェック
		if(userID==null || "".equals(userID)){
			list = new ArrayList<LocSumTimeDto>();
			dto = new LocSumTimeDto();
			dto.setReturnCode(1);
			list.add(dto);
			return list;
		}

		//オプション引数：レッスン時刻の書式
		if(lessonTime != null && !"".equals(lessonTime)){
			String strTime[] = lessonTime.split("-");
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");//時間の書式
			sdf.setLenient(false);
			for(int i=0;i < strTime.length;i++){
				try {
					sdf.parse(strTime[i]);
				} catch (Exception e) {
					list = new ArrayList<LocSumTimeDto>();
					dto = new LocSumTimeDto();
					dto.setReturnCode(1);
					list.add(dto);
					return list;
				}
			}
		}

		TimeManagMstDto tmmDto = new TimeManagMstDto();    // 時差管理マスタDTO
		LessonSumTimeDao dao = new LessonSumTimeDao(this.conn);
		tmmDto = dao.getLocSumTime(userID);

		if(tmmDto.getTimeMarkKbn()==null || "".equals(tmmDto.getTimeMarkKbn())){
			list = new ArrayList<LocSumTimeDto>();
			dto = new LocSumTimeDto();
			dto.setReturnCode(2);
			list.add(dto);
			return list;
		}

		//ローカル・サマータイムの計算処理
		String locTimeMark=tmmDto.getTimeMarkKbn();       // 時間（符号）
		String sumTimeMaek=tmmDto.getSumTimeMarkKbn();    // サマータイム(時間)(符号)
		int locTimeMinutes=tmmDto.getTimeMinutes();       // 時間(分)
		int sumTimeMinutes=tmmDto.getSumTimeMinutes();    // サマータイム(時間)(分)

		list = new ArrayList<LocSumTimeDto>();
		//dto = new LocSumTimeDto();
		String work;

		//オプション引数：レッスン時刻指定の場合、１件
		if(lessonTime != null && !"".equals(lessonTime)){
			dto = new LocSumTimeDto();
			String strTime[] = lessonTime.split("-");

			work=getDiffTime(strTime[0],strTime[1],locTimeMark,locTimeMinutes);
			dto.setLocalTimeFromTo(work);

			work=getDiffTime(strTime[0],strTime[1],sumTimeMaek,sumTimeMinutes);
			dto.setSumTimeFromTo(work);

			dto.setReturnCode(0);

			list.add(dto);

		//オプション引数：レッスン時刻未指定の場合、汎用フィールドから全件
		}else{
			CodeManagMstCache cache=CodeManagMstCache.getInstance();
			CodeManagMstDto codeDto;

			LinkedHashMap<String, CodeManagMstDto> codeList =cache.getList(NaikaraTalkConstants.CODE_CATEGORY_LESSON_TM_S);



			Iterator<String> iter = codeList.keySet().iterator();
	       	while (iter.hasNext()) {
		       	Object key = iter.next();
		       	codeDto = codeList.get(key);

		       	dto = new LocSumTimeDto();
		       	String sTime=codeDto.getManagerNm();
				String strTime[] = sTime.split("-");
				work=getDiffTime(strTime[0],strTime[1],locTimeMark,locTimeMinutes);
				dto.setLocalTimeFromTo(work);

				work=getDiffTime(strTime[0],strTime[1],sumTimeMaek,sumTimeMinutes);
				dto.setSumTimeFromTo(work);

				dto.setReturnCode(0);

				list.add(dto);
	       	}

		}
		return list;
	}


	/**
	 * 時差計算<br>
	 * <br>
	 * 指定時間から、時差を計算して戻り値として返却する<br>
	 * <br>
	 * @param startTime
	 * @param endTime
	 * @param timeMark
	 * @param timeMinutes
	 * @return String:diffTimeFromTo(HH:mm-HH:mm)
	 */
	private String getDiffTime(String startTime,String endTime,String timeMark,int timeMinutes){

		String diffTimeFromTo;
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");//時間の書式
		sdf.setLenient(true);

		//開始時間
		String strFromHHmm[] = startTime.split(":");
		Calendar calFrom = Calendar.getInstance();
		calFrom.set(Calendar.HOUR_OF_DAY, Integer.parseInt(strFromHHmm[0]));
		calFrom.set(Calendar.MINUTE, Integer.parseInt(strFromHHmm[1]));

		//終了時間
		String strToHHmm[] = endTime.split(":");
		Calendar calTo = Calendar.getInstance();
		calTo.set(Calendar.HOUR_OF_DAY, Integer.parseInt(strToHHmm[0]));
		calTo.set(Calendar.MINUTE, Integer.parseInt(strToHHmm[1]));

		StringBuffer sb = new StringBuffer();

		//正負号付で現地ローカル時間を算出する(0:+、1:-)　NaikaraTalkConstants.SIGN_PLUS
		// if("0".equals(timeMark)){
		if (StringUtils.equals(NaikaraTalkConstants.SIGN_PLUS, timeMark)) {
			// 符号 + の場合
			calFrom.add(Calendar.MINUTE, (timeMinutes));
			calTo.add(Calendar.MINUTE, (timeMinutes));
			sb =sb.append(sdf.format(calFrom.getTime())).append("-").append(sdf.format(calTo.getTime()));
		}else if (StringUtils.equals(NaikaraTalkConstants.SIGN_MINUS, timeMark)) {
			// 符号 - の場合
			calFrom.add(Calendar.MINUTE, -1*(timeMinutes));
			calTo.add(Calendar.MINUTE, -1*(timeMinutes));
			sb =sb.append(sdf.format(calFrom.getTime())).append("-").append(sdf.format(calTo.getTime()));
		} else {
			// 設定なしの場合
			sb =sb.append(NaikaraTalkConstants.SUMMER_TIME_NO);
		}

		diffTimeFromTo=sb.toString();

		return diffTimeFromTo;
	}

}


