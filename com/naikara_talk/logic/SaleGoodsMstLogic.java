package com.naikara_talk.logic;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dao.SaleGoodsMstDao;
import com.naikara_talk.dao.TableCountDao;
import com.naikara_talk.dbutil.ConditionMapper;
import com.naikara_talk.dbutil.OrderCondition;
import com.naikara_talk.dto.CodeManagMstDto;
import com.naikara_talk.dto.GoodsMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.mstcache.CodeManagMstCache;
import com.naikara_talk.util.NaikaraTalkConstants;


/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>販売商品マスタメンテナンス(単票)。<br>
 * <b>クラス名称　　　:</b>販売商品マスタメンテナンスLogicクラス。<br>
 * <b>クラス概要　　　:</b>販売商品マスタメンテナンスLogic。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/06/18 TECS 新規作成。
 */
public class SaleGoodsMstLogic {

    // コネクション変数
    public Connection conn = null;

    // コンストラクタ
    public SaleGoodsMstLogic(Connection con) {
        this.conn = con;
    }


    /**
     * データの存在チェック。<br>
     * <br>
     * @param GoodsMstDto
     *            画面のパラメータ
     * @return int
     * @throws NaiException
     */
    public int Exists(GoodsMstDto dto) throws NaiException {

        //TableCountDaoクラスのオブジェクトを生成
        TableCountDao dao = new TableCountDao(this.conn);

        // データ件数の取得処理
        return dao.rowCount(NaikaraTalkConstants.GOODS_MST, SetConditions(dto));

    }


    /**
     * 初期表示の検索処理。<br>
     * <br>
     * @param GoodsMstDto
     *            画面のパラメータ
     * @return GoodsMstDto
     * @throws NaiException
     */
    public GoodsMstDto select(GoodsMstDto dto) throws NaiException {

        SaleGoodsMstDao dao = new SaleGoodsMstDao(this.conn);
        GoodsMstDto dtoResult = new GoodsMstDto();

        // 並び順:商品コード の昇順
        OrderCondition orderBy = new OrderCondition();
        orderBy.addCondition(SaleGoodsMstDao.COND_GOODS_CD, OrderCondition.ASC);

        // データ取得処理
        ArrayList<GoodsMstDto> resultDto = dao.search(SetConditions(dto), orderBy);


        // 戻り値へ値の設定
        if (0 < resultDto.size()) {
            // 1件分
            dtoResult = resultDto.get(0);
        }

        return dtoResult;

    }


    /**
     * 登録処理。<br>
     * <br>
     * @param GoodsMstDto
     *            画面のパラメータ
     * @return 返却値
     * @throws NaiException
     */
    public int insert(GoodsMstDto dto) throws NaiException {
        SaleGoodsMstDao dao = new SaleGoodsMstDao(this.conn);
        try {
        	return dao.insert(dto);
        } catch (Exception e) {
            throw new NaiException(e);
        }
    }



	/**
	 * 更新処理。<br>
	 * <br>
     * @param GoodsMstDto
     *            画面のパラメータ
     * @return int
     * @throws NaiException
	 */
    public int update(GoodsMstDto dto) throws NaiException {
        SaleGoodsMstDao dao = new SaleGoodsMstDao(this.conn);
        try {
            return dao.update(dto);
        } catch (Exception e) {
            throw new NaiException(e);
        }
    }


	/**
	 * コード管理マスタキャッシュからデータ取得処理。<br>
	 * <br>
     * @param String
     *            汎用コード
     * @return LinkedHashMap<String, String>
     * @throws NaiException
	 */
    public LinkedHashMap<String, String> selectCodeMst(String category) throws NaiException {

       	LinkedHashMap<String, String> hashMap=new LinkedHashMap<String, String>();

       	CodeManagMstCache cache=CodeManagMstCache.getInstance();
       	LinkedHashMap<String, CodeManagMstDto> list=cache.getList(category);

       	Iterator<String> iter = list.keySet().iterator();
       	while (iter.hasNext()) {
	       	Object key = iter.next();
	       	CodeManagMstDto dto = list.get(key);
	       	hashMap.put(dto.getManagerCd(),dto.getManagerNm());
       	}

        return hashMap;
    }

    /**
     * 検索条件の設定。<br>
     * <br>
     * @param GoodsMstDto
     * @return ConditionMapper:conditions
     * @throws NaiException
     */
    private ConditionMapper SetConditions(GoodsMstDto dto) throws NaiException {

        // 検索条件の作成
        ConditionMapper  conditions = new ConditionMapper();

        // 商品コードを入力されている場合
        if (!StringUtils.isEmpty(dto.getGoodsCd())) {
            conditions.addCondition(SaleGoodsMstDao.COND_GOODS_CD,
                 ConditionMapper.OPERATOR_EQUAL, dto.getGoodsCd());
        }

        // 戻り値
        return conditions;
    }


}
