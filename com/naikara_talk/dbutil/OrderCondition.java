package com.naikara_talk.dbutil;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称:</b>データベースユーティリティ<br>
 * <b>クラス名称　　　:</b><br>
 * <b>クラス概要　　　:</b>このクラスはSQL文の ORDER BY 句を構築します。<br>
 * ソート条件を設定したこのクラスのインスタンスをDAOのパラメタとして渡してください。
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author nos!!@mira
 * <b>変更履歴　　　　:</b>2013/06/03 MIRA 新規作成
 */
public class OrderCondition {

	private static final String ASC_STR = " asc";
	private static final String DESC_STR = " desc";

	/** ソートオーダー　昇順 */
	public static final int ASC = 0;
	/** ソートオーダー　降順 */
	public static final int DESC = 1;

	private List<String> orderCondition = new ArrayList<String>();

	/**
	 * ソート条件を追加します。
	 *
	 * @param item ソート対象のカラム名　DAOで定義している定数を使用してください。
	 * @param order ソート方向 このクラスで規定しているソートオーダーを使用してください
	 */
	public void addCondition(String item, int order){

		if (!StringUtils.isEmpty(item)){
			StringBuilder sb = new StringBuilder();
			sb.append(item);
			if (ASC == order) {
				sb.append(ASC_STR);
			} else if (DESC == order) {
				sb.append(DESC_STR);
			}
			orderCondition.add(sb.toString());
		}
	}

	/**
	 * 　ORDER BY 句文字列を返却する。
	 * @return
	 */
	public String getOrderString(){

		StringBuilder sb = new StringBuilder();

		if(!orderCondition.isEmpty()){


			sb.append(" ORDER BY ");

			for(int i = 0; i < this.orderCondition.size(); i++) {

				if(i > 0){
					sb.append(", ");
				}

				sb.append(this.orderCondition.get(i));
			}
		}

		return sb.toString();
	}

}
