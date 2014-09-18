package com.naikara_talk.logic;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.naikara_talk.dto.AgeCalculateDto;
import com.naikara_talk.exception.NaiException;


/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称:</b>共通部品 Logicクラス<br>
 * <b>クラス名称　　　:</b>年齢の算出処理クラス<br>
 * <b>クラス概要　　　:</b>年齢の算出処理Logic<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/05/24 TECS 新規作成
 */
public class AgeCalculateLogic {

	/** 未成年/成人区分　未成年　*/
	public static final int ADULT_KBN_MINORITY = 0;

	/** 未成年/成人区分　成年　*/
	public static final int ADULT_KBN_ADULT = 1;

	/**
	 * 年齢の算出処理<br>
	 * <br>
	 * 年齢の算出処理を行う<br>
	 * 戻り値：年齢、成人区別DTO
	 * <br>
	 * @param birthDay
	 * @param sysDate
	 * @return AgeAdultDictDto
	 * @throws NaiException
	 */
	public AgeCalculateDto ageCalculate(String birthDay,String sysDate) throws NaiException{

		int workAge;//計算用
		AgeCalculateDto dto=null; //年齢、成人区別


		//引数のパタメータチェック
		//誕生日のyyyyMMdd書式チェック
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
		sdf.setLenient(false);
		Date dBitrhDay;
		try {
			dBitrhDay=sdf.parse(birthDay);
		} catch (Exception e) {
			dto=new AgeCalculateDto();
			dto.setReturnCode(1);
			return dto;
		}

		//システム日付のyyyyMMdd書式チェック
		Date dSysDate;
		try {
			dSysDate=sdf.parse(sysDate);
		} catch (Exception e) {
			dto=new AgeCalculateDto();
			dto.setReturnCode(1);
			return dto;
		}

		//誕生日の未来日チェック
		//引数の年月日をカレンダー型に変換
		Calendar birth = Calendar.getInstance();
		birth.setTime(dBitrhDay);
		Calendar specified = Calendar.getInstance();
		specified.setTime(dSysDate);
		if(birth.compareTo(specified)>0){
			dto=new AgeCalculateDto();
			dto.setReturnCode(1);
			return dto;
		}


		//年齢の計算
		dto=new AgeCalculateDto();
		workAge=specified.get(Calendar.YEAR) - birth.get(Calendar.YEAR);
		if (specified.get(Calendar.MONTH) == birth.get(Calendar.MONTH)) {
			if (specified.get(Calendar.DAY_OF_MONTH) < birth.get(Calendar.DAY_OF_MONTH)) {
				workAge--;
			}
		} else if (specified.get(Calendar.MONTH) < birth.get(Calendar.MONTH)) {
			workAge--;
		}
		dto.setAge(workAge);

		//成人区別の設定
		if(workAge<20){
			dto.setAdult(ADULT_KBN_MINORITY); //未成年
		}else{
			dto.setAdult(ADULT_KBN_ADULT); //成人
		}

		dto.setReturnCode(0);
		return dto;
	}
}
