package com.naikara_talk.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dto.PaymentManagedDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.model.PaymentManagedModel;
import com.naikara_talk.util.NaikaraStringUtil;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>売上入金・支払管理。<br>
 * <b>クラス名称　　　:</b>支払入力・修正クラス。<br>
 * <b>クラス概要　　　:</b>支払入力・修正reflectService。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 *
 * @author TECS <b>変更履歴　　　　:</b>2013/07/30 TECS 新規作成。
 */

public class PaymentManagedReflectService implements ActionService {

	/* チェック：問題なし(正常) */
    public static final int CHECK_OK = 0;
	/* チェック：文字種欄チェック error */
    public static final int ERR_HALH_NUM = 1;
    /* チェック：一覧上の｢調整額｣が全て未入力チェック error */
    public static final int ERR_ALL_NULL = 2;
    /* チェック：一覧上の｢調整額｣がマイナス入力の場合の整合性チェック error */
    public static final int ERR_MAENAD = 3;
    /* チェック：一覧上の｢調整額｣がマイナス入力の場合の整合性チェック値 */
    private static final int MAENAD_ZERO = 0;

	/**
	 * 表示データの付帯情報の取得と設定
	 * @param list 画面一覧list
	 * @param model 支払入力・修正model
	 * @return list
	 * @throws NaiException
	 */
	public List<PaymentManagedDto> getInfo(List<PaymentManagedDto> list, PaymentManagedModel model) throws NaiException {

		// 初期化処理
		ArrayList<PaymentManagedDto> returnList = new ArrayList<PaymentManagedDto>();
		PaymentManagedDto retDto;
		// 調整額
	    String[] adjustmentYen = model.getAdjustmentYen();
	    // レッスン日
	    String[] lessonDt = model.getLessonDt();
	    // レッスン時刻コード
	    String[] lessonTmCd = model.getLessonTmCd();
		// レコードバージョン番号
		String[] recordVerNoM = model.getRecordVerNoM();
		int j = 0;

		// 調整額と新支払予定額を設定
		// 画面の｢新支払予定額｣ = 画面の｢現支払予定額｣ + 画面の｢調整額｣
		for (int i = 0; i < list.size(); i++) {
			retDto = list.get(i);
			// 画面のレッスン日、レッスン時刻コードとDBのレッスン日、レッスン時刻コードが当ている場合
			if (StringUtils.equals(retDto.getLessonDt(), lessonDt[j]) && StringUtils.equals(retDto.getLessonTmCd(), lessonTmCd[j])) {

				if (StringUtils.equals("", adjustmentYen[j].trim()) || !isHalfNum(adjustmentYen[j])) {
					// 画面の調整額はnullの場合、半角数字10桁チェックエラーの、そのままで設定する
					retDto.setAdjustmentYen(adjustmentYen[j].trim());
					retDto.setNewPaymentYen(retDto.getEndPaymentYen());
				} else {
					// 正常の場合
					// コンマ削除してから追加
					retDto.setAdjustmentYen(NaikaraStringUtil.addComma(NaikaraStringUtil.delComma(adjustmentYen[j].trim())));
					retDto.setNewPaymentYen(new BigDecimal(NaikaraStringUtil.delComma(adjustmentYen[j].trim())).add(retDto.getEndPaymentYen()));
				}
				// レコードバージョン番号を設定
				retDto.setRecordVerNoM(recordVerNoM[j]);

				j ++;
			}
			returnList.add(retDto);
		}

		return returnList;

	}

	/**
	 * 文字種欄チェック、一覧上の｢調整額｣が全て未入力チェック、一覧上の｢調整額｣がマイナス入力の場合の整合性チェック
	 * @param list
	 * @return int
	 */
	public int paymentManagedCheck(List<PaymentManagedDto> list) throws NaiException {

		// 初期化処理
		PaymentManagedDto dto;
		// 調整額
		String adjustmentYen;
		// 新支払予定額
		BigDecimal newPaymentYen;
		// 全て未入力flag
		boolean allNullFlag = true;

		for (int i = 0; i < list.size(); i++) {
			dto = list.get(i);
			adjustmentYen = dto.getAdjustmentYen();
			newPaymentYen = dto.getNewPaymentYen();
			// 文字種欄チェック 半角数字10桁
			if (!isHalfNum(NaikaraStringUtil.delComma(adjustmentYen))) {
				return ERR_HALH_NUM;
			}
			// 一覧上の｢調整額｣が全て未入力判断
			if (adjustmentYen != null && !StringUtils.equals("", adjustmentYen)) {
				allNullFlag = false;
			}
			// 一覧上の｢調整額｣がマイナス入力の場合の整合性チェック
			if ( newPaymentYen.compareTo(new BigDecimal(0)) < MAENAD_ZERO) {
				return ERR_MAENAD;
			}
		}
		// 一覧上の｢調整額｣が全て未入力チェック
		if(allNullFlag) {
			return ERR_ALL_NULL;
		}
		return CHECK_OK;
	}

	/**
     * 半角数字10桁チェック
     * @param str
     * @return boolean
     * @throws NaiException
     */
    public boolean isHalfNum(String str) throws NaiException {
    	// トリムを行う場合はトリムを実行
        if (str != null) {
        	str = str.trim();
        }

        // 入力されていない場合は処理を抜ける
        if (StringUtils.isEmpty(str)) {
            return true;
        }

        // 少数入力不可
        if(str.contains(".")) {
        	return false;
        }

        // コンマ削除
        str = NaikaraStringUtil.delComma(str);

        if (str.contains("-")) {
        	// マイナスの場合11桁数以内
            if(str.length() > 11) {
            	return false;
            }
        } else if(str.length() > 10) {
        	// 10桁数以内
        	return false;
        }

        String expression = "[ -~｡-ﾟ]*";
        Pattern pattern;
        pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(str);
        if (!matcher.matches()) {
        	return false;
        }

        // 数字
        try {
        	new BigDecimal(str);

        } catch (Exception e) {
			return false;
		}

        return true;
    }

}
