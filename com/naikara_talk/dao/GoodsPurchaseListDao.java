package com.naikara_talk.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.naikara_talk.dto.GoodsPurchaseListDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称:</b>共通部品 DAOクラス<br>
 * <b>クラス名称　　　:</b>商品購入テーブル、販売商品マスタのデータ取得クラス<br>
 * <b>クラス概要　　　:</b>商品購入テーブル、販売商品マスタのデータ取得DAO<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/06/14 TECS 新規作成
 */
public class GoodsPurchaseListDao extends AbstractDao {

    // コネクション変数
    public Connection conn = null;

    // コンストラクタ
    public GoodsPurchaseListDao(Connection con) {
        this.conn = con;
    }

    /**
     * 商品購入テーブル、販売商品マスタのデータ取得<br>
     * <br>
     * 商品購入リストを戻り値で返却する<br>
     * <br>
     * @param studentId
     * @param goodsCd
     * @param purchaseDt
     * @return ArrayList<GoodsPurchaseListDto>
     * @throws NaiException
     */
    public ArrayList<GoodsPurchaseListDto> search(String studentId, String goodsCd, String purchaseDt)
            throws NaiException {

        // 戻り値とリターンコードの初期化
        ArrayList<GoodsPurchaseListDto> list = null;  // DTOリスト
        GoodsPurchaseListDto dto = null;              // DTO

        ResultSet res = null;

        StringBuffer sb = new StringBuffer();

        // 取得処理
        sb.append(" SELECT ");
        sb.append("   GPT.PURCHASE_ID ");
        sb.append("  ,GPT.PAYMENT_METHOD_KBN ");
        sb.append("  ,GPT.STUDENT_ID ");
        sb.append("  ,GPT.PURCHASE_DT ");
        sb.append("  ,GPT.PURCHASE_TM ");
        sb.append("  ,GPT.GOODS_CD ");
        sb.append("  ,GPT.GOODS_NM ");
        sb.append("  ,GPT.SALE_KBN ");
        sb.append("  ,GPT.SALE_NM ");
        sb.append("  ,GPT.PURCHASE_YEN ");
        sb.append("  ,GPT.PAYMENT_USE_POINT ");
        sb.append("  ,GPT.FREE_USE_POINT ");
        sb.append("  ,GMS.EXPLANATION ");
        sb.append("  ,GMS.IMG_PHOTO_NM ");
        sb.append("  ,GMS.IMG_PHOTO ");
        sb.append("  ,GMS.PRODUCT_KBN ");
        sb.append("  ,GMS.GOODS_FILE_NM ");
        sb.append("  ,GMS.GOODS_FILE ");
        sb.append("  ,GMS.SALES_PRICE ");
        sb.append("  ,GMS.USE_POINT ");
        sb.append("  ,GMS.PURCHASE_DESCRIPTION ");
        sb.append("  ,GMS.PURCHASE_URL ");
        sb.append("  ,GMS.USE_STR_DT ");
        sb.append("  ,GMS.USE_END_DT ");
        sb.append("  ,GMS.USE_COURSE_MEMO ");
        sb.append("  ,GMS.REMARK ");
        sb.append(" FROM ");
        sb.append("   GOODS_PURCHASE_TRN AS GPT ");
        sb.append("  ,GOODS_MST          AS GMS ");
        sb.append(" WHERE GPT.STUDENT_ID  = ? ");
        sb.append("   AND GPT.PURCHASE_DT = ? ");
        sb.append("   AND GPT.GOODS_CD    = ? ");
        sb.append("   AND GPT.GOODS_CD    = GMS.GOODS_CD ");
        sb.append(" ORDER BY GPT.GOODS_CD ");

        try {

            PreparedStatement ps = conn.prepareStatement(sb.toString());
            ps.setString(1, studentId);
            ps.setString(2, purchaseDt);
            ps.setString(3, goodsCd);
            res = ps.executeQuery();

            list = new ArrayList<GoodsPurchaseListDto>();
            while (res.next()) {

                dto = new GoodsPurchaseListDto();

                dto.setPurchaseId(res.getString("GPT.PURCHASE_ID"));
                dto.setPaymentMethodKbn(res.getString("GPT.PAYMENT_METHOD_KBN"));
                dto.setStudentId(res.getString("GPT.STUDENT_ID"));
                dto.setPurchaseDt(res.getString("GPT.PURCHASE_DT"));
                dto.setPurchaseTm(res.getString("GPT.PURCHASE_TM"));
                dto.setGoodsCd(res.getString("GPT.GOODS_CD"));
                dto.setGoodsNm(res.getString("GPT.GOODS_NM"));
                dto.setSaleKbn(res.getString("GPT.SALE_KBN"));
                dto.setSaleNm(res.getString("GPT.SALE_NM"));
                dto.setPurchaseYen(res.getBigDecimal("GPT.PURCHASE_YEN"));
                dto.setPaymentUsePoint(res.getBigDecimal("GPT.PAYMENT_USE_POINT"));
                dto.setFreeUsePoint(res.getBigDecimal("GPT.FREE_USE_POINT"));
                dto.setExplanation(res.getString("GMS.EXPLANATION"));
                dto.setImgPhotoNm(res.getString("GMS.IMG_PHOTO_NM"));
                dto.setImgPhoto(res.getBlob("GMS.IMG_PHOTO"));
                dto.setProductKbn(res.getString("GMS.PRODUCT_KBN"));
                dto.setGoodsFileNm(res.getString("GMS.GOODS_FILE_NM"));
                dto.setGoodsFile(res.getBlob("GMS.GOODS_FILE"));
                dto.setSalesPrice(res.getBigDecimal("GMS.SALES_PRICE"));
                dto.setUsePoint(res.getBigDecimal("GMS.USE_POINT"));
                dto.setPurchaseDescription(res.getString("GMS.PURCHASE_DESCRIPTION"));
                dto.setPurchaseUrl(res.getString("GMS.PURCHASE_URL"));
                dto.setUseStrDt(res.getString("GMS.USE_STR_DT"));
                dto.setUseEndDt(res.getString("GMS.USE_END_DT"));
                dto.setUseCourseMemo(res.getString("GMS.USE_COURSE_MEMO"));
                dto.setRemark(res.getString("GMS.REMARK"));

                dto.setReturnCode(NaikaraTalkConstants.RETURN_CD_DATA_YES);

                list.add(dto);
            }

            if (list.size() < 1) {
                list = new ArrayList<GoodsPurchaseListDto>();
                dto = new GoodsPurchaseListDto();
                dto.setReturnCode(NaikaraTalkConstants.RETURN_CD_DATA_NO);
                list.add(dto);
                return list;
            }

            return list;

        } catch (SQLException se) {
            throw new NaiException(se);
        } finally {
            try {
                if (res != null) {
                    res.close();
                }
            } catch (SQLException se) {
                throw new NaiException(se);
            }
        }
    }

    /**
     * 商品購入テーブル、販売商品マスタのデータ取得<br>
     * <br>
     * 商品購入リストを戻り値で返却する<br>
     * <br>
     * @param studentId
     * @param goodsCd
     * @param purchaseDt
     * @return ArrayList<GoodsPurchaseListDto>
     * @throws NaiException
     */
    public ArrayList<GoodsPurchaseListDto> searchNew(String studentId, String purchaseDt, String purchaseDtNext,
            String saleKbn) throws NaiException {

        // 戻り値とリターンコードの初期化
        ArrayList<GoodsPurchaseListDto> list = null;  // DTOリスト
        GoodsPurchaseListDto dto = null;              // DTO

        ResultSet res = null;

        StringBuffer sb = new StringBuffer();

        // 取得処理
        sb.append(" SELECT ");
        sb.append("   GPT.PURCHASE_DT ");
        sb.append("  ,GPT.GOODS_NM ");
        sb.append("  ,GMS.GOODS_CD ");
        sb.append("  ,GMS.GOODS_FILE ");
        sb.append(" FROM ");
        sb.append("   GOODS_PURCHASE_TRN AS GPT ");
        sb.append("  ,GOODS_MST          AS GMS ");
        sb.append(" WHERE GPT.STUDENT_ID  = ? ");
        sb.append("   AND GPT.PURCHASE_DT <= ? ");
        sb.append("   AND GPT.PURCHASE_DT > ? ");
        sb.append("   AND GPT.GOODS_CD    = GMS.GOODS_CD ");
        sb.append("   AND GPT.SALE_KBN    = ? ");
        sb.append(" ORDER BY GPT.PURCHASE_DT DESC ");

        try {

            PreparedStatement ps = conn.prepareStatement(sb.toString());
            ps.setString(1, studentId);
            ps.setString(2, purchaseDt);
            ps.setString(3, purchaseDtNext);
            ps.setString(4, saleKbn);
            res = ps.executeQuery();

            list = new ArrayList<GoodsPurchaseListDto>();
            while (res.next()) {

                dto = new GoodsPurchaseListDto();

                dto.setPurchaseDt(res.getString("GPT.PURCHASE_DT"));
                dto.setGoodsCd(res.getString("GMS.GOODS_CD"));
                dto.setGoodsNm(res.getString("GPT.GOODS_NM"));
                dto.setGoodsFile(res.getBlob("GMS.GOODS_FILE"));
                dto.setReturnCode(NaikaraTalkConstants.RETURN_CD_DATA_YES);

                list.add(dto);
            }

            if (list.size() < 1) {
                list = new ArrayList<GoodsPurchaseListDto>();
                dto = new GoodsPurchaseListDto();
                dto.setReturnCode(NaikaraTalkConstants.RETURN_CD_DATA_NO);
                list.add(dto);
                return list;
            }

            return list;

        } catch (SQLException se) {
            throw new NaiException(se);
        } finally {
            try {
                if (res != null) {
                    res.close();
                }
            } catch (SQLException se) {
                throw new NaiException(se);
            }
        }
    }

}
