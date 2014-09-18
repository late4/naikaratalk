package com.naikara_talk.mstcache;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import com.naikara_talk.dao.SaleGoodsMstImgDao;
import com.naikara_talk.dto.GoodsMstImgDto;
import com.naikara_talk.dbutil.ConditionMapper;
import com.naikara_talk.dbutil.DbUtil;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.util.DateUtil;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称:</b>共通部品 DTOクラス<br>
 * <b>クラス名称　　　:</b>販売商品マスタのサンプル画像キャッシュクラス<br>
 * <b>クラス概要　　　:</b>販売商品マスタのサンプル画像cache<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 *
 * @author TECS <b>変更履歴　　　　:</b>
 *  2013/06/25 TECS 新規作成
 */
public class GoodsMstImgCache {

    private static GoodsMstImgCache instance;
    private static Date today;
    private static LinkedHashMap<String, GoodsMstImgDto> map;

    /**
     * プライベートなコンストラクタ
     */
    private GoodsMstImgCache() throws NaiException {
        init();
    }

    public static GoodsMstImgCache getInstance() throws NaiException {
        if (instance == null) {

            instance = new GoodsMstImgCache();

        }
        return instance;
    }

    /**
     * 販売商品マスタを読み込んでこのインスタンスを初期化します。
     */
    private void init() throws NaiException {

        // 業務日付で有効な商品を検索
        ConditionMapper conditions = new ConditionMapper();

        Connection conn = null;

        try {
            conn = DbUtil.getConnection();

            SaleGoodsMstImgDao tmDao = new SaleGoodsMstImgDao(conn);

            // 提供開始日
            if (today != null) {
                conditions.addCondition(SaleGoodsMstImgDao.COND_USE_STR_DT,
                    ConditionMapper.OPERATOR_LESS_EQUAL, DateUtil.toString(today,
                    DateUtil.DATE_FORMAT_yyyyMMdd));

                    conditions.addCondition(SaleGoodsMstImgDao.COND_USE_END_DT,
                    ConditionMapper.OPERATOR_LARGE_EQUAL, DateUtil.toString(today,
                    DateUtil.DATE_FORMAT_yyyyMMdd));
            }

            map = new LinkedHashMap<String, GoodsMstImgDto>();

            // mapに変換する
            for (GoodsMstImgDto dto : tmDao.search(conditions)) {
                map.put(dto.getGoodsCd(), dto);
            }

        } catch (SQLException e) {
            throw new NaiException(e);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new NaiException(e);
            }
        }

    }

    /**
     * データベースの変更などで内容が変わった際に販売商品マスタを読み直します。
     */
    public void reload() throws NaiException {
        init();
    }

    /**
     * 販売商品マスタを返却する
     *
     * @param goodsCd
     *            販売商品マスタの商品コード
     * @return 販売商品マスタの１レコード
     * @throws Exception
     */
    public GoodsMstImgDto get(String goodsCd) throws SQLException {


/*         if (!DateUtil.dateEquals(DateUtil.getOperationDate(), today)) {
        	 init();
         }
*/
    	return map.get(goodsCd);
    }

    /**
     * 読み込み済みのすべての販売商品リスト(商品コード、サンプル画像名、サンプル画像)を返却する
     *
     * @param List
     *            <GoodsMstDto> 販売商品マスタの商品コード
     * @return 販売商品マスタの１レコード
     * @throws Exception
     *
     */
    public List<GoodsMstImgDto> getList() throws SQLException {

/*         if (!DateUtil.dateEquals(DateUtil.getOperationDate(), today)) {
        	 init();
         }

*/
    	return new ArrayList<GoodsMstImgDto>(map.values());
    }

}
