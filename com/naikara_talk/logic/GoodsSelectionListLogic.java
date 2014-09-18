package com.naikara_talk.logic;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dao.SaleGoodsMstDao;
import com.naikara_talk.dao.TableCountDao;
import com.naikara_talk.dbutil.ConditionMapper;
import com.naikara_talk.dbutil.OrderCondition;
import com.naikara_talk.dto.CodeManagMstDto;
import com.naikara_talk.dto.GoodsMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.mstcache.CodeManagMstCache;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>マスタ保守<br>
 * <b>クラス名称       :</b>コースマスタメンテナンス(商品選択)<br>
 * <b>クラス概要       :</b>コースマスタメンテナンス(商品選択)Logic<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/08/05 TECS 新規作成
 */
public class GoodsSelectionListLogic {

    // コネクション変数
    public Connection conn = null;

    // コンストラクタ
    public GoodsSelectionListLogic(Connection con) {
        this.conn = con;
    }

    /**
     * 検索データ件数取得。<br>
     * <br>
     * @param GoodsMstDto:dto
     * @return int:dataCount
     * @throws NaiException
     */
    public int getRowCount(GoodsMstDto dto) throws NaiException {

        // 初期化処理
        TableCountDao dao = new TableCountDao(this.conn);

        // 一覧のデータ件数を取得する
        int dataCount = dao.rowCount(NaikaraTalkConstants.GOODS_MST, setConditions(dto));

        // 戻り値
        return dataCount;

    }

    /**
     * 検索処理。<br>
     * <br>
     * @param GoodsMstDto 画面のパラメータ
     * @return List<GoodsMstDto>
     * @throws Exception
     */
    public List<GoodsMstDto> selectList(GoodsMstDto dto) throws Exception {

        // 初期化処理
        List<GoodsMstDto> list = new ArrayList<GoodsMstDto>();
        SaleGoodsMstDao dao = new SaleGoodsMstDao(this.conn);

        // 並び順:商品コード の昇順
        OrderCondition orderBy = new OrderCondition();
        orderBy.addCondition(SaleGoodsMstDao.COND_GOODS_CD, OrderCondition.ASC);

        // 一覧データを取得する
        list = dao.search(setConditions(dto), orderBy);

        // 戻り値
        return list;

    }

    /**
     * 検索条件の設定。<br>
     * <br>
      * @param GoodsMstDto
      * @return ConditionMapper:conditions
      * @throws NaiException
     */
    private ConditionMapper setConditions(GoodsMstDto dto) throws NaiException {

        // 検索条件の作成
        ConditionMapper conditions = new ConditionMapper();

        // 商品コードを入力されている場合
        if (!StringUtils.isEmpty(dto.getGoodsCd())) {
            conditions.addCondition(SaleGoodsMstDao.COND_GOODS_CD, ConditionMapper.OPERATOR_LIKE,
                    "%" + dto.getGoodsCd() + "%");
        }

        // 商品名を入力されている場合
        if (!StringUtils.isEmpty(dto.getGoodsNm())) {
            conditions.addCondition(SaleGoodsMstDao.COND_GOODS_NM, ConditionMapper.OPERATOR_LIKE,
                    "%" + dto.getGoodsNm() + "%");
        }
        // 提供開始日
        conditions.addCondition(SaleGoodsMstDao.COND_USE_STR_DT, ConditionMapper.OPERATOR_LESS_EQUAL,
                NaikaraStringUtil.converToYYYYMMDD(dto.getUseEndDt()));

        // 提供終了日
        conditions.addCondition(SaleGoodsMstDao.COND_USE_END_DT, ConditionMapper.OPERATOR_LARGE_EQUAL,
                NaikaraStringUtil.converToYYYYMMDD(dto.getUseStrDt()));

        // 戻り値
        return conditions;

    }

    /**
     * コード管理マスタキャッシュからデータ取得処理。<br>
     * <br>
     * コード管理マスタキャッシュからデータ取得処理。<br>
     * <br>
     * @param String 汎用コード<br>
     * @return LinkedHashMap<String, String><br>
     * @exception NaiException
     */
    public LinkedHashMap<String, String> selectCodeMst(String category) throws NaiException {

        LinkedHashMap<String, String> hashMap = new LinkedHashMap<String, String>();

        CodeManagMstCache cache = CodeManagMstCache.getInstance();
        LinkedHashMap<String, CodeManagMstDto> list = cache.getList(category);

        Iterator<String> iter = list.keySet().iterator();
        while (iter.hasNext()) {
            Object key = iter.next();
            CodeManagMstDto dto = list.get(key);
            hashMap.put(dto.getManagerCd(), dto.getManagerNm());
        }

        return hashMap;
    }

}
