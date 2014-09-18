package com.naikara_talk.action;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.model.PurchaseGoodsListModel;
import com.naikara_talk.logic.CodeManagMstLogic;
import com.naikara_talk.sessiondata.SessionPurchaseGoods;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>お客様利用画面<br>
 * <b>クラス名称       :</b>商品購入ページ（一覧）<br>
 * <b>クラス概要       :</b>商品購入ページ（一覧）ActionSupport<br>
 * <br>
 * <b>著作権           :</b>Copyright (C) 2013 NAI, Inc. All rights reserved.
 * @author TECS
 * <b>変更履歴         :</b>2013/08/09 TECS 新規作成
 *                     :</b>2014/02/18 TECS 商品購入ページからの呼出追加に伴う修正
 */
public abstract class PurchaseGoodsListActionSupport extends NaikaraActionSupport {

	private static final long serialVersionUID = 1L;

	// Help画面名
	protected String helpPageId = "HelpPurchaseGoods.html";

	/** メッセージ用使用テーブル名 */
	protected static final String GOODS_MST_JNM = "販売商品マスタ";


	/**
	 * チェック。<br>
	 * <br>
	 * コースコードを戻り値で返却する<br>
	 * <br>
	 * @param なし
	 * @return なし
	 */
	@Override
	public void validate() {

		// チェックエラーの場合、利用状態の再取得。
		try {
			// 初期表示用のデータ取得処理
			this.initCond();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * サービスの呼び出しの実装。
	 */
	abstract protected String requestService() throws NaiException;


	/**
	 * 検索条件値の取得<br>
	 * <br>
	 * 検索条件値を取得する<br>
	 * <br>
	 * @param なし<br>
	 * @return なし<br>
	 * @exception Exception
	 */
	public void initCond() throws Exception {

		CodeManagMstLogic logic = new CodeManagMstLogic();

		// コース名：大分類コード
		this.setBigClassificationCd_sell(logic.selectCdMst(NaikaraTalkConstants.CODE_CATEGORY_BIG_CLASSIFICATION));

		// コース名：中分類コード
		this.setMiddleClassificationCd_sell(logic.selectCdMst(NaikaraTalkConstants.CODE_CATEGORY_MIDDLE_CLASSIFICATION));

		// コース名：小分類コード
		this.setSmallClassificationCd_sell(logic.selectCdMst(NaikaraTalkConstants.CODE_CATEGORY_SMALL_CLASSIFICATION));

		// 商品形態（ 先頭に｢全て｣を追加 ）
		LinkedHashMap<String, String> hashMap = new LinkedHashMap<String, String>();
		LinkedHashMap<String, String> list = logic.selectCdMst(NaikaraTalkConstants.CODE_CATEGORY_PRODUCT_KBN);
		hashMap.put(NaikaraTalkConstants.CHOICE_ALL_ZERO, NaikaraTalkConstants.CHOICE_ALL);
		Iterator<String> iter = list.keySet().iterator();
		while (iter.hasNext()) {
			Object key = iter.next();
			String value = list.get(key);
			hashMap.put(key.toString(), value);
		}
		this.setProductKbn_rdll(hashMap);

		// 受取方法（ 先頭に｢全て｣を追加 ）
		hashMap = new LinkedHashMap<String, String>();
		list = logic.selectCdMst(NaikaraTalkConstants.CODE_CATEGORY_SALE_KBN);
		hashMap.put(NaikaraTalkConstants.CHOICE_ALL_ZERO, NaikaraTalkConstants.CHOICE_ALL);
		iter = list.keySet().iterator();
		while (iter.hasNext()) {
			Object key = iter.next();
			String value = list.get(key);
			hashMap.put(key.toString(), value);
		}
		this.setSaleKbn_rdll(hashMap);


		//2014.02.18 Add Start 商品購入ページからの呼出追加に伴う修正
		hashMap = new LinkedHashMap<String, String>();
		list = logic.selectCdMst(NaikaraTalkConstants.CODE_CATEGORY_T_CONTACT_KBN);
		iter = list.keySet().iterator();
		while (iter.hasNext()) {
			Object key = iter.next();
			String value = list.get(key);
			hashMap.put(key.toString(), value);
		}
		this.setTMailKbn_rdll(hashMap);
		//2014.02.18 Add End   商品購入ページからの呼出追加に伴う修正

	}



	/**
	 * SessionPurchaseGoods値を画面項目(ActionSupport)にセット。<br>
	 * 又は、再表示
	 * <br>
	 * @throws Exception
	 */
	public void SessionPurchaseGoodsToModelAs() throws Exception {

		boolean returnOnFlg = false;  // 戻る判定Onフラグ

		if ((SessionPurchaseGoods) SessionDataUtil.getSessionData(SessionPurchaseGoods.class.toString()) != null) {
			// 戻る判定Onフラグ
			returnOnFlg = ((SessionPurchaseGoods) SessionDataUtil.getSessionData
					(SessionPurchaseGoods.class.toString())).getReturnOnFlg();
		}

		// returnOnFlg に true が設定されるのは、確認Actionクラス(ConfirmationAction)のみ
		if (returnOnFlg) {
			// コース名：大分類コード
			this.setBigClassificationCd_sel(((SessionPurchaseGoods) SessionDataUtil.getSessionData
					(SessionPurchaseGoods.class.toString())).getBigClassificationCdD());
			this.setDefaultBigClassificationCd(((SessionPurchaseGoods) SessionDataUtil.getSessionData
					(SessionPurchaseGoods.class.toString())).getBigClassificationCdD());

			// コース名：中分類コード
			this.setMiddleClassificationCd_sel(((SessionPurchaseGoods) SessionDataUtil.getSessionData
					(SessionPurchaseGoods.class.toString())).getMiddleClassificationCdD());
			this.setDefaultMiddleClassificationCd(((SessionPurchaseGoods) SessionDataUtil.getSessionData
					(SessionPurchaseGoods.class.toString())).getMiddleClassificationCdD());

			// コース名：小分類コード
			this.setSmallClassificationCd_sel(((SessionPurchaseGoods) SessionDataUtil.getSessionData
					(SessionPurchaseGoods.class.toString())).getSmallClassificationCdD());
			this.setDefaultSmallClassificationCd(((SessionPurchaseGoods) SessionDataUtil.getSessionData
					(SessionPurchaseGoods.class.toString())).getSmallClassificationCdD());

			// コース名
			this.setCourseName_txt(((SessionPurchaseGoods) SessionDataUtil.getSessionData
					(SessionPurchaseGoods.class.toString())).getCourseJnmD());

			// 商品名
			this.setGoodsName_txt(((SessionPurchaseGoods) SessionDataUtil.getSessionData
					(SessionPurchaseGoods.class.toString())).getGoodsNmD());

			// 商品形態
			this.setProductKbn_rdl(((SessionPurchaseGoods) SessionDataUtil.getSessionData
					(SessionPurchaseGoods.class.toString())).getProductKbnD());

			// 受取方法
			this.setSaleKbn_rdl(((SessionPurchaseGoods) SessionDataUtil.getSessionData
					(SessionPurchaseGoods.class.toString())).getSaleKbnD());

			//2014.02.18 Add Start 商品購入ページからの呼出追加に伴う修正

			this.setTMailKbn_rdl(((SessionPurchaseGoods) SessionDataUtil.getSessionData
					(SessionPurchaseGoods.class.toString())).getTMailKbnD());
			//2014.02.18 Add End   商品購入ページからの呼出追加に伴う修正

			// 一覧選択済み明細
			this.setSelect_chk(((SessionPurchaseGoods) SessionDataUtil.getSessionData
					(SessionPurchaseGoods.class.toString())).getSelectChk());

		} else {
			// 入力条件再表示
			// コース名：大分類コード
			this.setBigClassificationCd_sel(this.bigClassificationCd_sel);
			this.setDefaultBigClassificationCd(this.bigClassificationCd_sel);

			// コース名：中分類コード
			this.setMiddleClassificationCd_sel(this.middleClassificationCd_sel);
			this.setDefaultMiddleClassificationCd(this.middleClassificationCd_sel);

			// コース名：小分類コード
			this.setSmallClassificationCd_sel(this.smallClassificationCd_sel);
			this.setDefaultSmallClassificationCd(this.smallClassificationCd_sel);

			// コース名
			this.setCourseName_txt(this.courseName_txt);

			// 商品名
			this.setGoodsName_txt(this.goodsName_txt);

			// 商品形態
			this.setProductKbn_rdl(this.productKbn_rdl);

			// 受取方法
			this.setSaleKbn_rdl(this.saleKbn_rdl);

			//2014.02.18 Add Start 商品購入ページからの呼出追加に伴う修正
			this.setTMailKbn_rdl(this.tMailKbn_rdl);
			//2014.02.18 Add End   商品購入ページからの呼出追加に伴う修正

		}

	}


	/**
	 * SessionPurchaseGoods値をModelにセット<br>
	 * 又は、画面項目値をModelにセット<br>
	 * <br>
	 * @throws Exception
	 */
	public PurchaseGoodsListModel SessionPurchaseGoodsToModel() throws Exception {

		boolean returnOnFlg = false;  // 戻る判定Onフラグ

		PurchaseGoodsListModel pglModel = new PurchaseGoodsListModel();

		if ((SessionPurchaseGoods) SessionDataUtil.getSessionData(SessionPurchaseGoods.class.toString()) != null) {
			// 戻る判定Onフラグ
			returnOnFlg = ((SessionPurchaseGoods) SessionDataUtil.getSessionData
					(SessionPurchaseGoods.class.toString())).getReturnOnFlg();
		}

		// returnOnFlg に true が設定されるのは、確認Actionクラス(MoveAction)のみ
		if (returnOnFlg) {
			// コース名：大分類コード
			pglModel.setBigClassificationCdS(((SessionPurchaseGoods) SessionDataUtil.getSessionData
					(SessionPurchaseGoods.class.toString())).getBigClassificationCdS());

			// コース名：中分類コード
			pglModel.setMiddleClassificationCdS(((SessionPurchaseGoods) SessionDataUtil.getSessionData
					(SessionPurchaseGoods.class.toString())).getMiddleClassificationCdS());

			// コース名：小分類コード
			pglModel.setSmallClassificationCdS(((SessionPurchaseGoods) SessionDataUtil.getSessionData
					(SessionPurchaseGoods.class.toString())).getSmallClassificationCdS());

			// コース名
			pglModel.setCourseJnmS(((SessionPurchaseGoods) SessionDataUtil.getSessionData
					(SessionPurchaseGoods.class.toString())).getCourseJnmS());

			// 商品名
			pglModel.setGoodsNmS(((SessionPurchaseGoods) SessionDataUtil.getSessionData
					(SessionPurchaseGoods.class.toString())).getGoodsNmS());

			// 商品形態
			pglModel.setProductKbnS(((SessionPurchaseGoods) SessionDataUtil.getSessionData
					(SessionPurchaseGoods.class.toString())).getProductKbnS());

			// 受取方法
			pglModel.setSaleKbnS(((SessionPurchaseGoods) SessionDataUtil.getSessionData
					(SessionPurchaseGoods.class.toString())).getSaleKbnS());

			//2014.02.18 Add Start 商品購入ページからの呼出追加に伴う修正
			// 講師への商品購入確認通知メール
			pglModel.setTMailKbnS(((SessionPurchaseGoods) SessionDataUtil.getSessionData
					(SessionPurchaseGoods.class.toString())).getTMailKbnS());
			//2014.02.18 Add End   商品購入ページからの呼出追加に伴う修正

		} else {
			// コース名：大分類コード
			pglModel.setBigClassificationCdS(this.bigClassificationCd_sel);

			// コース名：中分類コード
			pglModel.setMiddleClassificationCdS(this.middleClassificationCd_sel);

			// コース名：小分類コード
			pglModel.setSmallClassificationCdS(this.smallClassificationCd_sel);

			// コース名
			pglModel.setCourseJnmS(this.courseName_txt);

			// 商品名
			pglModel.setGoodsNmS(this.goodsName_txt);

			// 商品形態
			pglModel.setProductKbnS(this.productKbn_rdl);

			// 受取方法
			pglModel.setSaleKbnS(this.saleKbn_rdl);

			//2014.02.18 Add Start 商品購入ページからの呼出追加に伴う修正
			// 講師への商品購入確認通知メール
			pglModel.setTMailKbnS(this.tMailKbn_rdl);
			//2014.02.18 Add End   商品購入ページからの呼出追加に伴う修正

		}

		return pglModel;

	}



	/** メッセージ */
	protected String message;

	/** コース名：大分類コード(選択条件) name */
	protected String bigClassificationCd_sel;

	/** コース名：大分類コード(選択条件) list,listKey,listValue */
	protected Map<String, String> bigClassificationCd_sell = new LinkedHashMap<String, String>();

	/** コース名：大分類コード(選択条件) value 初期値 */
	protected String defaultBigClassificationCd;

	/** コース名：中分類コード(選択条件) */
	protected String middleClassificationCd_sel;

	/** コース名：中分類コード(選択条件) list,listKey,listValue */
	protected Map<String, String> middleClassificationCd_sell = new LinkedHashMap<String, String>();

	/** コース名：中分類コード(選択条件) value 初期値 */
	protected String defaultMiddleClassificationCd;

	/** コース名：小分類コード(選択条件) */
	protected String smallClassificationCd_sel;

	/** コース名：小分類コード(選択条件) list,listKey,listValue */
	protected Map<String, String> smallClassificationCd_sell = new LinkedHashMap<String, String>();

	/** コース名：小分類コード(選択条件) value 初期値 */
	protected String defaultSmallClassificationCd;

	/** コース名(選択条件) */
	protected String courseName_txt;

	/** 商品名(選択条件) */
	protected String goodsName_txt;

	/** 商品形態(選択条件) */
	protected String productKbn_rdl;
	protected Map<String, String> productKbn_rdll = new LinkedHashMap<String, String>();

	/** 受取方法(選択条件) */
	protected String saleKbn_rdl;
	protected Map<String, String> saleKbn_rdll = new LinkedHashMap<String, String>();

	//2014.02.18 Add Start 商品購入ページからの呼出追加に伴う修正
	/** 講師への商品購入確認通知メール(選択条件) */
	protected String tMailKbn_rdl;
	protected Map<String, String> tMailKbn_rdll = new LinkedHashMap<String, String>();

	/** winOpenTeacherList押下判定フラグ */
	protected String winOpenTeacherListClickFlg;
	//2014.02.18 Add End   商品購入ページからの呼出追加に伴う修正

	/** 一覧から選択された明細 */
	protected String[] select_chk;

	/** 商品購入一覧 */
	protected PurchaseGoodsListModel model = new PurchaseGoodsListModel();

	//2014.02.18 Add Start 商品購入ページからの呼出追加に伴う修正
	/** 商品の選択された明細の選択状態の維持用 */
	protected String[] checkValue;
	//2014.02.18 Add End   商品購入ページからの呼出追加に伴う修正

	/**
	* @return helpPageId
	*/
	public String getHelpPageId() {
		return helpPageId;
	}

	/**
	* @param helpPageId セットする helpPageId
	*/
	public void setHelpPageId(String helpPageId) {
		this.helpPageId = helpPageId;
	}

	/**
	* @return message
	*/
	public String getMessage() {
		return message;
	}

	/**
	* @param message セットする message
	*/
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return

	 */
	public String getBigClassificationCd_sel() {
		return bigClassificationCd_sel;
	}

	/**
	 * @param bigClassificationCd_sel セットする bigClassificationCd_sel
	 */
	public void setBigClassificationCd_sel(String bigClassificationCd_sel) {
		this.bigClassificationCd_sel = bigClassificationCd_sel;
	}

	/**
	 * @return bigClassificationCd_sell
	 */
	public Map<String, String> getBigClassificationCd_sell() {
		return bigClassificationCd_sell;
	}

	/**
	 * @param bigClassificationCd_sell セットする bigClassificationCd_sell
	 */
	public void setBigClassificationCd_sell(Map<String, String> bigClassificationCd_sell) {
		this.bigClassificationCd_sell = bigClassificationCd_sell;
	}

	/**
	 * @return defaultBigClassificationCd
	 */
	public String getDefaultBigClassificationCd() {
		return defaultBigClassificationCd;
	}

	/**
	 * @param defaultBigClassificationCd セットする defaultBigClassificationCd
	 */
	public void setDefaultBigClassificationCd(String defaultBigClassificationCd) {
		this.defaultBigClassificationCd = defaultBigClassificationCd;
	}

	/**
	 * @return middleClassificationCd_sel
	 */
	public String getMiddleClassificationCd_sel() {
		return middleClassificationCd_sel;
	}

	/**
	 * @param middleClassificationCd_sel セットする middleClassificationCd_sel
	 */
	public void setMiddleClassificationCd_sel(String middleClassificationCd_sel) {
		this.middleClassificationCd_sel = middleClassificationCd_sel;
	}

	/**
	 * @return middleClassificationCd_sell
	 */
	public Map<String, String> getMiddleClassificationCd_sell() {
		return middleClassificationCd_sell;
	}

	/**
	 * @param middleClassificationCd_sell セットする middleClassificationCd_sell
	 */
	public void setMiddleClassificationCd_sell(Map<String, String> middleClassificationCd_sell) {
		this.middleClassificationCd_sell = middleClassificationCd_sell;
	}

	/**
	 * @return defaultMiddleClassificationCd
	 */
	public String getDefaultMiddleClassificationCd() {
		return defaultMiddleClassificationCd;
	}

	/**
	 * @param defaultMiddleClassificationCd セットする defaultMiddleClassificationCd
	 */
	public void setDefaultMiddleClassificationCd(String defaultMiddleClassificationCd) {
		this.defaultMiddleClassificationCd = defaultMiddleClassificationCd;
	}

	/**
	 * @return smallClassificationCd_sel
	 */
	public String getSmallClassificationCd_sel() {
		return smallClassificationCd_sel;
	}

	/**
	 * @param smallClassificationCd_sel セットする smallClassificationCd_sel
	 */
	public void setSmallClassificationCd_sel(String smallClassificationCd_sel) {
		this.smallClassificationCd_sel = smallClassificationCd_sel;
	}

	/**
	 * @return smallClassificationCd_sell
	 */
	public Map<String, String> getSmallClassificationCd_sell() {
		return smallClassificationCd_sell;
	}

	/**
	 * @param smallClassificationCd_sell セットする smallClassificationCd_sell
	 */
	public void setSmallClassificationCd_sell(Map<String, String> smallClassificationCd_sell) {
		this.smallClassificationCd_sell = smallClassificationCd_sell;
	}

	/**
	 * @return defaultSmallClassificationCd
	 */
	public String getDefaultSmallClassificationCd() {
		return defaultSmallClassificationCd;
	}

	/**
	 * @param defaultSmallClassificationCd セットする defaultSmallClassificationCd
	 */
	public void setDefaultSmallClassificationCd(String defaultSmallClassificationCd) {
		this.defaultSmallClassificationCd = defaultSmallClassificationCd;
	}

	/**
	 * @return courseName_txt
	 */
	public String getCourseName_txt() {
		return courseName_txt;
	}

	/**
	 * @param courseName_txt セットする courseName_txt
	 */
	public void setCourseName_txt(String courseName_txt) {
		this.courseName_txt = courseName_txt;
	}

	/**
	 * @return goodsName_txt
	 */
	public String getGoodsName_txt() {
		return goodsName_txt;
	}

	/**
	 * @param goodsName_txt セットする goodsName_txt
	 */
	public void setGoodsName_txt(String goodsName_txt) {
		this.goodsName_txt = goodsName_txt;
	}

	/**
	 * @return productKbn_rdl
	 */
	public String getProductKbn_rdl() {
		return productKbn_rdl;
	}

	/**
	 * @param productKbn_rdl セットする productKbn_rdl
	 */
	public void setProductKbn_rdl(String productKbn_rdl) {
		this.productKbn_rdl = productKbn_rdl;
	}

	/**
	 * @return productKbn_rdll
	 */
	public Map<String, String> getProductKbn_rdll() {
		return productKbn_rdll;
	}

	/**
	 * @param productKbn_rdll セットする productKbn_rdll
	 */
	public void setProductKbn_rdll(Map<String, String> productKbn_rdll) {
		this.productKbn_rdll = productKbn_rdll;
	}

	/**
	 * @return saleKbn_rdl
	 */
	public String getSaleKbn_rdl() {
		return saleKbn_rdl;
	}

	/**
	 * @param saleKbn_rdl セットする saleKbn_rdl
	 */
	public void setSaleKbn_rdl(String saleKbn_rdl) {
		this.saleKbn_rdl = saleKbn_rdl;
	}

    //2014.02.18 Add Start 商品購入ページからの呼出追加に伴う修正
	/**
	 * @return tMailKbn_rdl
	 */
	public String getTMailKbn_rdl() {
		return tMailKbn_rdl;
	}

	/**
	 * @param tMailKbn_rdl セットする tMailKbn_rdl
	 */
	public void setTMailKbn_rdl(String tMailKbn_rdl) {
		this.tMailKbn_rdl = tMailKbn_rdl;
	}
    //2014.02.18 Add End   商品購入ページからの呼出追加に伴う修正

	/**
	 * @return saleKbn_rdll
	 */
	public Map<String, String> getSaleKbn_rdll() {
		return saleKbn_rdll;
	}

	/**
	 * @param saleKbn_rdll セットする saleKbn_rdll
	 */
	public void setSaleKbn_rdll(Map<String, String> saleKbn_rdll) {
		this.saleKbn_rdll = saleKbn_rdll;
	}


	//2014.02.18 Add Start 商品購入ページからの呼出追加に伴う修正
	/**
	 * @return tMailKbn_rdll
	 */
	public Map<String, String> getTMailKbn_rdll() {
		return tMailKbn_rdll;
	}

	/**
	 * @param tMailKbn_rdll セットする tMailKbn_rdll
	 */
	public void setTMailKbn_rdll(Map<String, String> tMailKbn_rdll) {
		this.tMailKbn_rdll = tMailKbn_rdll;
	}


	/**
	 * @return winOpenTeacherListClickFlg
	 */
	public String getWinOpenTeacherListClickFlg() {
		return winOpenTeacherListClickFlg;
	}

	/**
	 * @param winOpenTeacherListClickFlg セットする winOpenTeacherListClickFlg
	 */
	public void setWinOpenTeacherListClickFlg(String winOpenTeacherListClickFlg) {
		this.winOpenTeacherListClickFlg = winOpenTeacherListClickFlg;
	}
	//2014.02.18 Add End   商品購入ページからの呼出追加に伴う修正


	/**
	 * @return model
	 */
	public PurchaseGoodsListModel getModel() {
		return model;
	}

	/**
	 * @param model セットする model
	 */
	public void setModel(PurchaseGoodsListModel model) {
		this.model = model;
	}

	/**
	 * @return select_chk
	 */
	public String[] getSelect_chk() {
		setCheckValue(select_chk);  // 選択があれば、その対象を選択した状態とする(Check ON)
		return select_chk;
	}

	/**
	 * @param select_chk セットする select_chk
	 */
	public void setSelect_chk(String[] select_chk) {
		this.select_chk = select_chk;
	}



	/**
	 * @return checkValue(name=select_chk の value値)
	 */
	public String[] getCheckValue() {
		return checkValue;
}

	/**
	 * @param checkValue(name=select_chk の value値) セットする checkValue
	 */
	public void setCheckValue(String[] checkValue) {
		this.checkValue = checkValue;
	}


}