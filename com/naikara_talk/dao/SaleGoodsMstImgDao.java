package com.naikara_talk.dao;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dbutil.ConditionMapper;
import com.naikara_talk.dbutil.QueryCondition;
import com.naikara_talk.dto.GoodsMstImgDto;
import com.naikara_talk.util.NaikaraTalkConstants;


/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>販売商品マスタのサンプル画像。<br>
 * <b>クラス名称　　　:</b> 販売商品マスタサンプル画像Daoクラス。<br>
 * <b>クラス概要　　　:</b>サンプル画像Dao。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/06/18 TECS 新規作成。
 */
public class SaleGoodsMstImgDao extends AbstractDao {

    /** 提供開始日　条件項目　*/
    public static final String COND_USE_STR_DT = "USE_STR_DT";
    /** 提供終了日　条件項目　*/
    public static final String COND_USE_END_DT = "USE_END_DT";

    // コネクション変数
    public Connection conn = null;

    // コンストラクタ
    public SaleGoodsMstImgDao(Connection con) {
        this.conn = con;
    }

    /**
     * 検索処理。
     *
     * @param SaleGoodsMstImgDto
     *            画面のパラメータ
     * @return List<GoodsMstDto>
     * @throws SQLException
     */
    public List<GoodsMstImgDto> search(ConditionMapper conditions) throws SQLException {

        //戻り値とリターンコードの初期化
        ArrayList<GoodsMstImgDto> list = null;  //DTOリスト
        GoodsMstImgDto retDto = null;           //DTO

        /** 画像用　*/
        Blob imgPhoto;
        byte byteData[] = null;

        ResultSet res = null;

        // 実行SQL文
        StringBuffer sb = new StringBuffer();
        sb.append(" select ");
        sb.append("  GOODS_CD ");
        sb.append(" ,GOODS_NM ");
        sb.append(" ,IMG_PHOTO_NM ");
        sb.append(" ,IMG_PHOTO ");
        sb.append(" from ");
        sb.append(" GOODS_MST ");
		// 抽出条件の設定
		if(!StringUtils.isEmpty(conditions.getConditionString())) {
			sb.append("where ");
			sb.append(conditions.getConditionString());
		}


        try {

            PreparedStatement ps = conn.prepareStatement(sb.toString());

            // パラメタの設定
            int i = 0;
            for( QueryCondition cond : conditions.getConditions()){
                for(String val : cond.getValues()){
                    ps.setString(i + 1, val);
                    i++;
                }
            }

            // SQL文実行
            res = ps.executeQuery();

            // 検索結果をdtoにセットする
            list = new ArrayList<GoodsMstImgDto>();
            while (res.next()) {
                retDto = new GoodsMstImgDto();
                retDto.setGoodsCd(res.getString("GOODS_CD"));
                retDto.setGoodsNm(res.getString("GOODS_NM"));
                retDto.setImgPhotoNm(res.getString("IMG_PHOTO_NM"));

                // Blob型からByte型へ変換して格納
                imgPhoto = res.getBlob("IMG_PHOTO");
                if (imgPhoto == null) {
                    byteData = new byte[4096];
                } else {
                    byteData = imgPhoto.getBytes(1, (int)imgPhoto.length());
                }
                retDto.setImgPhoto(byteData);

                retDto.setReturnCode(NaikaraTalkConstants.RETURN_CD_DATA_YES);

                //リストに追加
                list.add(retDto);
            }

            if(list.size()<1){
                list=new ArrayList<GoodsMstImgDto>();
	            retDto=new GoodsMstImgDto();
	            retDto.setReturnCode(NaikaraTalkConstants.RETURN_CD_DATA_NO);
	            list.add(retDto);
	            return list;
	        }

            return list;

        } catch (SQLException se) {
            throw se;
        } finally {
            if ( res != null ) {
                res.close();
            }
        }
    }

}
