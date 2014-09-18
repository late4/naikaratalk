package com.naikara_talk.logic;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.naikara_talk.dto.PointExpirationDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.util.NaikaraTalkConstants;


/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称:</b>共通部品 Logicクラス<br>
 * <b>クラス名称　　　:</b>ポイントの有効期限算出処理クラス<br>
 * <b>クラス概要　　　:</b>ポイントの有効期限算出処理Logic<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/05/24 TECS 新規作成
 */
public class PointExpirationLogic {

	/**
	 * 指定日のnヶ月加算処理<br>
	 * <br>
	 * 指定日のnヶ月加算処理を行う（有効期限終了計算日で返す）<br>
	 * <br>
	 * @param calcDate
	 * @param expMonth
	 * @return PointExpirationDto
	 * @throws NaiException
	 */
	public PointExpirationDto monthCalculation(String calcDate,int expMonth) throws NaiException{

		PointExpirationDto peDto = new PointExpirationDto();

    	Calendar calendar = Calendar.getInstance();
    	SimpleDateFormat sdfYMD = new SimpleDateFormat("yyyyMMdd");
    	SimpleDateFormat sdfD = new SimpleDateFormat("dd");
        Date newDate = new Date();
        int beforeDay = 0;
        int afterDay = 0;

        try {
    		// 指定日をparseメソッドでDate型に変換
        	newDate = sdfYMD.parse(calcDate);

        // 変換できなかった場合例外がスロー呼び出し元にエラーを返す
   	    } catch (ParseException e) {
   	        peDto.setReturnCode(1);
   	        return peDto;
   	    }

    	// setLenientメソッドでfalseを設定（日付の妥当性、存在チェックを行う）
        calendar.setLenient(false);

   	    // setメソッドで指定日を設定
        calendar.setTime(newDate);

        // 指定日の日(DD)を取得
        beforeDay = Integer.parseInt(sdfD.format(calendar.getTime()).toString());

        // 指定日の nヶ月後を設定
        calendar.add(Calendar.MONTH, expMonth);

       	// nヶ月後の日(DD)を取得
   	    afterDay = Integer.parseInt(sdfD.format(calendar.getTime()).toString());

   	    // 指定日の日(DD)が nヶ月後に存在しない場合
   	    if(beforeDay > afterDay) {
   	    	calendar.add(Calendar.DAY_OF_MONTH, NaikaraTalkConstants.EFFECTIVE_END_CALC_DT);
   	    }

		peDto.setAge(sdfYMD.format(calendar.getTime()).toString());
		peDto.setReturnCode(0);
		return peDto;
	}

	/**
	 * ポイントの有効期限算出処理<br>
	 * <br>
	 * ポイントの有効期限算出処理を行う<br>
	 * <br>
	 * @param systemDate
	 * @param expMonth
	 * @return PointExpirationDto
	 * @throws NaiException
	 */
	public PointExpirationDto pointExpiration(String sysDate,int expMonth) throws NaiException{

		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
		PointExpirationDto dto = new PointExpirationDto();
		Date expDate = new Date();

		sdf.setLenient(false);

		// 指定日-1日を返す
		dto = this.monthCalculation(sysDate, expMonth);
		if(dto.getReturnCode()==NaikaraTalkConstants.RETURN_CD_DATA_YES) {
			try {
				expDate = sdf.parse(dto.getAge());
			} catch (ParseException e) {
				dto.setReturnCode(1);
				return dto;
			}
		} else {
			return dto;
		}

		try {
			expDate = sdf.parse(dto.getAge());
		} catch (ParseException e) {
			dto.setReturnCode(1);
			return dto;
		}
		// setメソッドで指定日を設定
        calendar.setTime(expDate);
        // nヶ月後の-1を設定
        calendar.add(Calendar.DAY_OF_MONTH, -1);

		sdf = new SimpleDateFormat("yyyy/MM/dd");
		dto.setAge(sdf.format(calendar.getTime()).toString());
		dto.setReturnCode(0);
		return dto;
	}

/* 変更確認前の為、退避（充当日が存在しない場合、存在する月末日-1を返す。）

		//引数のパタメータチェック
		//ポイント有効期限のnullチェック
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
		sdf.setLenient(false);
		Date workDate;
		try {
			workDate = sdf.parse(sysDate);
		} catch (Exception e) {
			dto=new PointExpirationDto();
			dto.setReturnCode(1);
			return dto;
		}

		//有効期限(月数)の数値チェック
		if(expMonth<1){
			dto=new PointExpirationDto();
			dto.setReturnCode(1);
			return dto;
		}


		//ポイント有効期限の算出処理
		//期間FROM
		Calendar fromDate = Calendar.getInstance();
		fromDate.setTime(workDate);
		//期間TO
		Calendar toDate = Calendar.getInstance();

		//有効期限の月数を、年と月にわける
		int year=0;
		while(expMonth>12){
			year++;
			expMonth=expMonth-12;
		}
		//年を跨ぐ場合の年月算出
		if(fromDate.get(Calendar.MONTH) + expMonth>11){//Calendarの月は0始まり
			year++;
			expMonth=expMonth-12;
		}

		//年月
		year=year + fromDate.get(Calendar.YEAR);
		expMonth=expMonth + fromDate.get(Calendar.MONTH)-1;//Calendarの月は0始まり


		int day;
		//期間FROMが1日の場合、期間TOに末日を設定する
		if(fromDate.get(Calendar.DATE)==1){
			toDate.set(year, expMonth, 1);
			day=toDate.getActualMaximum(Calendar.DATE);
			toDate.clear();
			toDate.set(year, expMonth, day);
		}
		//期間FROMが2～31日の場合、期間TOに1～30日を設定する
		//但し、期間TOの対応日が無い場合は、末日を設定する
		//閏年はCalendarクラスが自動判断する
		if(fromDate.get(Calendar.DATE)>=2
				&& fromDate.get(Calendar.DATE)<=31){
			day=fromDate.get(Calendar.DATE)-1;
			toDate.set(year, expMonth+1, day);
			//対応日無しの場合
			toDate.setLenient(false);
			try{
				toDate.getTime();
			}catch(IllegalArgumentException e){
				toDate.clear();
				toDate.set(year, expMonth+1, 1);
				day=toDate.getActualMaximum(Calendar.DATE);
				toDate.clear();
				toDate.set(year, expMonth+1, day);
			}
		}

		sdf=new SimpleDateFormat("yyyy/MM/dd");
		expDate=sdf.format(toDate.getTime());

		dto.setAge(expDate);
		dto.setReturnCode(0);
		return dto;
	}
*/
}
