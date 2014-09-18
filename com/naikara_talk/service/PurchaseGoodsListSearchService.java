package com.naikara_talk.service;

import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dbutil.DbUtil;
import com.naikara_talk.dto.PurchaseGoodsDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.logic.PurchaseGoodsListLogic;
import com.naikara_talk.model.PurchaseGoodsListModel;
import com.naikara_talk.model.GoodsListModel;
import com.naikara_talk.mstcache.CodeManagMstCache;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.sessiondata.SessionPurchaseGoods;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>お客様利用画面<br>
 * <b>クラス名称       :</b>商品購入ページ一覧<br>
 * <b>クラス概要       :</b>商品購入ページ一覧検索処理Service<br>
 * <br>
 * <b>著作権           :</b>Copyright (C) 2013 NAI, Inc. All rights reserved.
 * @author TECS
 * <b>変更履歴         :</b>2013/08/09 TECS 新規作成
 *                     :</b>2014/02/18 TECS 商品購入ページからの呼出追加に伴う修正
 */
public class PurchaseGoodsListSearchService implements ActionService {

	/* 一覧 ZERO件 */
	private static final int LIST_ZERO_CNT = 0;

	/* 一覧の表示上限 */
	public static final int LIST_MAX_CNT = 200;

	/**
	 * 検索前チェック処理<br>
	 * <br>
	 * @param PurchaseGoodsListModel
	 * @return int
	 * @exception Exception
	 */
	public int checkPreSelect(PurchaseGoodsListModel pglMdl) throws Exception {

		Connection conn = null;

		try {
			conn = DbUtil.getConnection();    // DBの接続

			// PurchaseGoodsListLogicクラスの生成
			PurchaseGoodsListLogic logic = new PurchaseGoodsListLogic(conn);

			// データ件数取得処理
			int count = logic.getRowCount(pglMdl);

			if (count == LIST_ZERO_CNT) {
				// データ件数＝Zero件の場合
				return PurchaseGoodsListModel.ERR_ZERO_DATA;
			} else if (count > LIST_MAX_CNT) {
				// データ件数が最大件数以上の場合
				return PurchaseGoodsListModel.ERR_MAXOVER_DATA;
			}

			// 正常
			return PurchaseGoodsListModel.CHECK_OK;

			} catch ( SQLException se ) {
				se.printStackTrace();
				throw new NaiException(se);
			} finally {
				try {
					if (!conn.isClosed()) {
						conn.close();
					}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

	}

	/**
	 * 画面データの取得、初回表示の場合。<br>
	 * <br>
	 * @param PurchaseGoodsListModel
	 *        画面のパラメータ
	 * @return List<GoodsListModel>
	 * @throws Exception
	 */
	public List<GoodsListModel> selectList(PurchaseGoodsListModel pglMdl) throws Exception {

		Connection conn = null;
		try {
			conn = DbUtil.getConnection();    // DBの接続

			// CodeControlMstListLogicクラスの生成
			PurchaseGoodsListLogic logic = new  PurchaseGoodsListLogic(conn);

			List<GoodsListModel> rtnModel = dtoToModel(logic.search(pglMdl));

			// データの取得＆リターン
			return rtnModel;

		} catch ( SQLException se ) {
			se.printStackTrace();
			throw new NaiException(se);
		} finally {
			try {
				conn.close();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

	}

	/**
	 * (コード管理マスタメンテナンス【一覧】)検索条件の設定。<br>
	 * <br>
	 * @param CdManagMstCdClassMstDto
	 * @return ConditionMapper:conditions
	 * @throws NaiException
	*/
	private List<GoodsListModel> dtoToModel(List<PurchaseGoodsDto> dto) throws NaiException {

		List<GoodsListModel> model = new ArrayList<GoodsListModel>();
		GoodsListModel editModel = new GoodsListModel();

		CodeManagMstCache cache = CodeManagMstCache.getInstance();

		// 大分類コード
		String strBigClassCd = "";

		// 中分類コード
		String strMidClassCd = "";

		// 小分類コード
		String strSmlClassCd = "";

		// コース名
		String strCourseJnm = "";

		// Session goodsCd get
		String[] goodsCd = {""};
		boolean rtnOnFlg = false;
		if ((SessionPurchaseGoods) SessionDataUtil.getSessionData(SessionPurchaseGoods.class.toString()) != null) {
			goodsCd = ((SessionPurchaseGoods) SessionDataUtil.getSessionData(SessionPurchaseGoods.class.toString())).getSelectChk();
			rtnOnFlg = ((SessionPurchaseGoods) SessionDataUtil.getSessionData(SessionPurchaseGoods.class.toString())).getReturnOnFlg();
		}

		for (int i = 0; i < dto.size(); i++) {

			editModel = new GoodsListModel();

			// 選択の活性／非活性制御
			if (StringUtils.equals(dto.get(i).getSaleKbn(), NaikaraTalkConstants.SALE_KBN_OUTSIDE)) {
				editModel.setCheckboxFlg(false);
			} else {
				editModel.setCheckboxFlg(true);
			}

			// 商品コード
			editModel.setGoodsCd(dto.get(i).getGoodsCd());

			// 商品名
			editModel.setGoodsNm(dto.get(i).getGoodsNm());

			// 商品形態：CODE_CATEGORY_PRODUCT_KBN
			editModel.setProductKbn(cache.decode(NaikaraTalkConstants.CODE_CATEGORY_PRODUCT_KBN, dto.get(i).getProductKbn()));

			// 利用ポイント
			editModel.setStrUsePoint(NaikaraStringUtil.addComma(dto.get(i).getUsePoint().toString()));

			// 受取方法：CODE_CATEGORY_SALE_KBN
			editModel.setSaleKbn(cache.decode(NaikaraTalkConstants.CODE_CATEGORY_SALE_KBN, dto.get(i).getSaleKbn()));

			// 2014/02/18 Add Start 商品購入ページからの呼出追加に伴う修正
			// 講師への商品購入確認通知メール(表示用)：
			editModel.setTMailKbn(cache.decode(NaikaraTalkConstants.CODE_CATEGORY_T_CONTACT_KBN, dto.get(i).getTMailKbn()));
			// 2014/02/18 Add End   商品購入ページからの呼出追加に伴う修正


			// 備考
			editModel.setPurchaseDescription(dto.get(i).getPurchaseDescription());

			// URL
			editModel.setPurchaseUrl(dto.get(i).getPurchaseUrl());


			// 2014/02/18 Add Start 商品購入ページからの呼出追加に伴う修正
			editModel.setTContactKbn(dto.get(i).getTContactKbn());
			editModel.setTeacherId(NaikaraTalkConstants.BRANK);       // 初期値-なし
			editModel.setTeacherNickNm(NaikaraTalkConstants.BRANK);   // 初期値-なし
			editModel.setTeacherChk(false);                           // 初期値-未選択
			// 2014/02/18 Add End   商品購入ページからの呼出追加に伴う修正


			// コース名（大分類）：CODE_CATEGORY_BIG_CLASSIFICATION
			strBigClassCd = cache.decode(NaikaraTalkConstants.CODE_CATEGORY_BIG_CLASSIFICATION, dto.get(i).getBigClassificationCd());

			// コース名（中分類）：CODE_CATEGORY_MIDDLE_CLASSIFICATION
			strMidClassCd = cache.decode(NaikaraTalkConstants.CODE_CATEGORY_MIDDLE_CLASSIFICATION, dto.get(i).getMiddleClassificationCd());

			// コース名（小分類）：CODE_CATEGORY_SMALL_CLASSIFICATION
			strSmlClassCd = cache.decode(NaikaraTalkConstants.CODE_CATEGORY_SMALL_CLASSIFICATION, dto.get(i).getSmallClassificationCd());

			// コース名
			strCourseJnm = dto.get(i).getCourseJnm();
			editModel.setCourseJnm(NaikaraStringUtil.unionString4(strBigClassCd, strMidClassCd, strSmlClassCd, strCourseJnm));

			// 商品コード+コースコード
			StringBuffer sb = new StringBuffer();
			sb.append(dto.get(i).getGoodsCd());
			sb.append(NaikaraTalkConstants.HALF_SPACE);
			sb.append(dto.get(i).getCourseCd());
			editModel.setGoodsCdcourseCd(sb.toString());

			model.add(editModel);
		}

		// 戻り値
		return model;

	}

}
