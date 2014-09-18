package com.naikara_talk.logic;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import com.naikara_talk.dao.HolidayMstDao;
import com.naikara_talk.dto.HolidayListDto;
import com.naikara_talk.dto.HolidayMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.util.NaikaraTalkConstants;


/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称:</b>共通部品 LOGICクラス<br>
 * <b>クラス名称　　　:</b>祝日判定処理クラス<br>
 * <b>クラス概要　　　:</b>祝日判定処理LOGIC<br>
 * <br>
 * <b>著作権　　　　　:</b>Copyright (C) 2013 NAI, Inc. All rights reserved.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/05/27 TECS 新規作成
 */
public class HolidayListGetLogic {

	// コネクション変数
	public Connection conn = null;

	// コンストラクタ
	public HolidayListGetLogic(Connection con) {
		this.conn = con;
	}

	/**
	 * 祝日、曜日の取得<br>
	 * <br>
	 * 指定期間の祝日と曜日を取得して、リストで返却する<br>
	 * <br>
	 * @param baseDay
	 * @param addDays
	 * @param countNum
	 * @return ArrayList<HolidayListDto>
	 * @throws NaiException
	 */
	public ArrayList<HolidayListDto> chkHoliday(String baseDay,int addDays,int countNum) throws NaiException {


		//戻り値とリターンコードの初期化
		ArrayList<HolidayListDto> list = null; //祝日リスト
		HolidayListDto dto=null;


		//引数のパラメータチェック
		//基点日の必須チェック
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
		sdf.setLenient(false);
		Date workDate;
		try {
			workDate = sdf.parse(baseDay);
		} catch (Exception e) {
			list = new ArrayList<HolidayListDto>();
			dto = new HolidayListDto();
			dto.setReturnCode(1);
			list.add(dto);
			return list;
		}


		//増加日数の０以上チェック
		if(!(addDays>=0)){
			list = new ArrayList<HolidayListDto>();
			dto = new HolidayListDto();
			dto.setReturnCode(1);
			list.add(dto);
			return list;
		}
		//算出する数の０以上チェック
		if(!(countNum>=1)){
			list = new ArrayList<HolidayListDto>();
			dto = new HolidayListDto();
			dto.setReturnCode(1);
			list.add(dto);
			return list;
		}

		//日付と曜日の算出処理
		SimpleDateFormat weeks = new SimpleDateFormat ("EEE",Locale.ENGLISH);//英語表記の曜日書式

		list = new ArrayList<HolidayListDto>();

		Calendar workCal = Calendar.getInstance();
		workCal.setTime(workDate);
		Date lastDay = workDate;//期間の最終日
		sdf = new SimpleDateFormat("yyyy/MM/dd");
		for(int i=0;countNum>i;i++){
			//曜日を算出してリストに追加
			dto=new HolidayListDto();
			dto.setDay(sdf.format(workCal.getTime()));
			lastDay=workCal.getTime();
			dto.setDayOfWeek(weeks.format(workCal.getTime()));
			dto.setHoliDay(0);
			dto.setReturnCode(0);
			list.add(dto);

			//増加日数を加算
			workCal.add(Calendar.DATE, addDays);
		}


		//祝日データ取得
		HolidayMstDao hmDao = new HolidayMstDao(this.conn);
		ArrayList<HolidayMstDto> mstList=hmDao.search(workDate,lastDay);

		//取得データに、祝日が存在する場合は、リストに設定する
		String workDay;
		for(int i=0;mstList.size()>i;i++){
			HolidayMstDto mstDto=mstList.get(i);

			if (mstDto.getReturnCode() == NaikaraTalkConstants.RETURN_CD_DATA_YES) {
				// データが存在する場合
				for(int j=0;list.size()>j;j++){

					dto=list.get(j);
					StringBuffer sb = new StringBuffer();
					sb = sb.append(dto.getDay().substring(0, 4))
							.append(dto.getDay().substring(5, 7))
							.append(dto.getDay().substring(8, 10));
					workDay=sb.toString();

					if(mstDto.getHolidayDt().equals(workDay)){
						dto.setHoliDay(1);
						list.set(j, dto);
						break;
					}

				}

			}

		}
		return list;
	}
}
