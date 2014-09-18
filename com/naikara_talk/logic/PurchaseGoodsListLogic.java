package com.naikara_talk.logic;

import java.sql.Connection;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import com.naikara_talk.dao.PurchaseGoodsDao;
import com.naikara_talk.dbutil.ConditionMapper;
import com.naikara_talk.dbutil.OrderCondition;
import com.naikara_talk.dto.PurchaseGoodsDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.model.PurchaseGoodsListModel;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.DateUtil;;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>お客様利用画面<br>
 * <b>クラス名称       :</b>商品購入ページ一覧<br>
 * <b>クラス概要       :</b>商品購入ページ一覧処理Logic<br>
 * <br>
 * <b>著作権           :</b>Copyright (C) 2013 NAI, Inc. All rights reserved.
 * @author TECS
 * <b>変更履歴         :</b>2013/08/09 TECS 新規作成
 *                     :</b>2014/02/18 TECS 商品購入ページからの呼出追加に伴う修正
 *                     :</b>2014/04/22 TECS 商品一覧の並び順の変更対応(コース名、商品コード⇒商品コードのみ)
 */
public class PurchaseGoodsListLogic {

	// コネクション変数
	public Connection conn = null;

	// コンストラクタ
	public PurchaseGoodsListLogic(Connection con) {
		this.conn = con;
	}

	/** 並び順の初期値(0より大きい値) */
	public static final int ORDER_BY_INIT = 0;

	/**
	 * 検索データ件数取得。<br>
	 * <br>
	 * @param CdManagMstCdClassMstDto
	 * @return int:DataCount
	 * @throws NaiException
	 */
	public int getRowCount(PurchaseGoodsListModel pglMdl) throws NaiException {

		// 初期化処理
		PurchaseGoodsDao dao = new PurchaseGoodsDao(this.conn);

		// 一覧のデータ件数を取得する
		int rsltCnt = dao.getRowCount(setConditions(pglMdl));

		// 戻り値
		return rsltCnt;

	}


	/**
	 * 検索処理。<br>
	 * <br>
	 * @param CdManagMstCdClassMstDto
	 *			画面のパラメータ
	 * @return List<CdManagMstCdClassMstDto>
	 * @throws Exception
	 */
	public List<PurchaseGoodsDto> search(PurchaseGoodsListModel pglMdl) throws NaiException {

		// PurchaseGoodsDaoクラスの生成
		PurchaseGoodsDao dao = new PurchaseGoodsDao(this.conn);

		// 並び順：商品コード の昇順
		OrderCondition orderBy = new OrderCondition();

		// 2014/04/22 Del Start 商品一覧の並び順の変更対応(コース名、商品コード⇒商品コードのみ)
		// orderBy.addCondition(PurchaseGoodsDao.COND_COURSE_JNM, OrderCondition.ASC);  // コース名
		// 2014/04/22 Del End   商品一覧の並び順の変更対応(コース名、商品コード⇒商品コードのみ)

		orderBy.addCondition(PurchaseGoodsDao.COND_GOODS_CD, OrderCondition.ASC);    // 商品コード

		// KEYでデータを取得する
		List<PurchaseGoodsDto> list = dao.search(setConditions(pglMdl), orderBy);

		// 戻り値
		return list;

	}

	/**
	 * (コード管理マスタメンテナンス【一覧】)検索条件の設定。<br>
	 * <br>
	 * @param CdManagMstCdClassMstDto
	 * @return ConditionMapper:conditions
	 * @throws NaiException
	*/
	private ConditionMapper setConditions(PurchaseGoodsListModel pglMdl) throws NaiException {

		// ◆◆◆ 検索条件の作成 ◆◆◆

		// ConditionMapperクラスの生成
		ConditionMapper  conditions = new ConditionMapper();

		// StringBufferの生成
		StringBuffer work = new StringBuffer();

		// 大分類コード(完全一致検索)
		if (!StringUtils.equals(pglMdl.getBigClassificationCdS(), NaikaraTalkConstants.CHOICE_ALL_ZERO)) {
			conditions.addCondition(PurchaseGoodsDao.COND_BIG_CLASSIFICATION_CD,
					ConditionMapper.OPERATOR_EQUAL, pglMdl.getBigClassificationCdS());
		}

		// 中分類コード(完全一致検索)
		if (!StringUtils.equals(pglMdl.getMiddleClassificationCdS(), NaikaraTalkConstants.CHOICE_ALL_ZERO)) {
			conditions.addCondition(PurchaseGoodsDao.COND_MIDDLE_CLASSIFICATION_CD,
					ConditionMapper.OPERATOR_EQUAL, pglMdl.getMiddleClassificationCdS());
		}

		// 小分類コード(完全一致検索)
		if (!StringUtils.equals(pglMdl.getSmallClassificationCdS(), NaikaraTalkConstants.CHOICE_ALL_ZERO)) {
			conditions.addCondition(PurchaseGoodsDao.COND_SMALL_CLASSIFICATION_CD,
					ConditionMapper.OPERATOR_EQUAL, pglMdl.getSmallClassificationCdS());
		}

		// コース名(曖昧検索)
		if (!StringUtils.isEmpty(pglMdl.getCourseJnmS())) {
			work.setLength(0);
			work.append(NaikaraTalkConstants.MESSAGE_PARAM_PREFIX);
			work.append(pglMdl.getCourseJnmS()).append(NaikaraTalkConstants.MESSAGE_PARAM_PREFIX);

			conditions.addCondition(PurchaseGoodsDao.COND_COURSE_JNM,
					ConditionMapper.OPERATOR_LIKE, work.toString());
		}

		// 提供開始日(完全一致検索)
		conditions.addCondition(PurchaseGoodsDao.COND_USE_STR_DT_CM,ConditionMapper.OPERATOR_LESS_EQUAL, DateUtil.getSysDate());

		// 提供終了日(完全一致検索)
		conditions.addCondition(PurchaseGoodsDao.COND_USE_END_DT_CM,ConditionMapper.OPERATOR_LARGE_EQUAL, DateUtil.getSysDate());

		// 商品名(曖昧検索)
		if (!StringUtils.isEmpty(pglMdl.getGoodsNmS())) {
			work.setLength(0);
			work.append(NaikaraTalkConstants.MESSAGE_PARAM_PREFIX);
			work.append(pglMdl.getGoodsNmS()).append(NaikaraTalkConstants.MESSAGE_PARAM_PREFIX);

			conditions.addCondition(PurchaseGoodsDao.COND_GOODS_NM,
					ConditionMapper.OPERATOR_LIKE, work.toString());
		}

		// 受取方法区分(完全一致検索)
		if (!StringUtils.equals(pglMdl.getSaleKbnS(), NaikaraTalkConstants.CHOICE_ALL_ZERO)) {
			conditions.addCondition(PurchaseGoodsDao.COND_SALE_KBN,
					ConditionMapper.OPERATOR_EQUAL, pglMdl.getSaleKbnS());
		}

		// 商品形態区分(完全一致検索)
		if (!StringUtils.equals(pglMdl.getProductKbnS(), NaikaraTalkConstants.CHOICE_ALL_ZERO)) {
			conditions.addCondition(PurchaseGoodsDao.COND_PRODUCT_KBN,
					ConditionMapper.OPERATOR_EQUAL, pglMdl.getProductKbnS());
		}

		// 2014/02/18 Add Start 商品購入ページからの呼出追加に伴う修正
		// 講師への商品購入確認通知メール
		if (!StringUtils.equals(pglMdl.getTMailKbnS(), NaikaraTalkConstants.CHOICE_ALL_ZERO)) {
			conditions.addCondition(PurchaseGoodsDao.COND_TEACHER_CONTACT_KBN,
					ConditionMapper.OPERATOR_EQUAL, pglMdl.getTMailKbnS());
		}
		// 2014/02/18 Add End   商品購入ページからの呼出追加に伴う修正



		// 提供開始日(完全一致検索)
		conditions.addCondition(PurchaseGoodsDao.COND_USE_STR_DT_GM,ConditionMapper.OPERATOR_LESS_EQUAL, DateUtil.getSysDate());

		// 提供終了日(完全一致検索)
		conditions.addCondition(PurchaseGoodsDao.COND_USE_END_DT_GM,ConditionMapper.OPERATOR_LARGE_EQUAL, DateUtil.getSysDate());

		// 戻り値
		return conditions;

	}


}
