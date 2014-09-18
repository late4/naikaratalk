package com.naikara_talk.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.naikara_talk.dbutil.ConditionMapper;
import com.naikara_talk.dbutil.OrderCondition;
import com.naikara_talk.dbutil.QueryCondition;
import com.naikara_talk.dto.CodeManagMstDto;
import com.naikara_talk.dto.GoodsMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.mstcache.CodeManagMstCache;
import com.naikara_talk.sessiondata.SessionUser;
import com.naikara_talk.util.DateUtil;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>販売商品マスタデータアクセス。<br>
 * <b>クラス名称　　　:</b>販売商品マスタDaoクラス。<br>
 * <b>クラス概要　　　:</b>販売商品マスタの取得、登録、更新を行う。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/06/18 TECS 新規作成。
 *                     </b>2014/02/18 TECS 改造 項目追加「講師への購入メール連絡付き区分」対応
 */
public class SaleGoodsMstDao extends AbstractDao {

    /** 商品コード　条件項目/並び順　*/
    public static final String COND_GOODS_CD = "GOODS_CD";
    /** 商品名　条件項目　*/
    public static final String COND_GOODS_NM = "GOODS_NM";
    /** 提供開始日　条件項目　*/
    public static final String COND_USE_STR_DT = "USE_STR_DT";
    /** 提供終了日　条件項目　*/
    public static final String COND_USE_END_DT = "USE_END_DT";

    // コネクション変数
    public Connection conn = null;

    // コンストラクタ
    public SaleGoodsMstDao(Connection con) {
        this.conn = con;
    }

    /**
     * 検索処理。<br>
     * <br>
     * @param SaleGoodsMstDto
     *            画面のパラメータ
     * @return GoodsMstDto 検索結果
     * @throws NaiException
     */
    public ArrayList<GoodsMstDto> search(ConditionMapper conditions, OrderCondition OrderBy) throws NaiException {

        // 戻り値とリターンコードの初期化
        ArrayList<GoodsMstDto> list = null;  // DTOリスト
        GoodsMstDto retDto = null;           // DTO

        ResultSet res = null;

        StringBuffer sb = new StringBuffer();

        // SQL文作成
        sb.append(" select ");
        sb.append("  GOODS_CD ");
        sb.append(" ,GOODS_NM ");
        sb.append(" ,EXPLANATION ");
        sb.append(" ,SALE_KBN ");
        sb.append(" ,IMG_PHOTO ");
        sb.append(" ,IMG_PHOTO_NM ");
        sb.append(" ,PRODUCT_KBN ");
        sb.append(" ,GOODS_FILE ");
        sb.append(" ,GOODS_FILE_NM ");
        sb.append(" ,SALES_PRICE ");
        sb.append(" ,USE_POINT ");
        sb.append(" ,PURCHASE_DESCRIPTION ");
        sb.append(" ,PURCHASE_URL ");
        sb.append(" ,USE_STR_DT ");
        sb.append(" ,USE_END_DT ");

        // 2014.02.18 Add 項目追加「講師への購入メール連絡付き区分」対応
        sb.append(" ,TEACHER_CONTACT_KBN ");  // 講師への購入メール連絡付き区分

        sb.append(" ,USE_COURSE_MEMO ");
        sb.append(" ,REMARK ");
        sb.append(" ,RECORD_VER_NO ");
        sb.append(" from ");
        sb.append(" GOODS_MST ");

        // 抽出条件の設定
        if (!StringUtils.isEmpty(conditions.getConditionString())) {
            sb.append("where ");
            sb.append(conditions.getConditionString());
        }
        // 並び順の設定
        if (!StringUtils.isEmpty(OrderBy.getOrderString())) {
            sb.append(OrderBy.getOrderString());
        }

        try {

            PreparedStatement ps = conn.prepareStatement(sb.toString());

            // パラメタの設定
            int i = 0;
            for (QueryCondition cond : conditions.getConditions()) {
                for (String val : cond.getValues()) {
                    ps.setString(i + 1, val);
                    i++;
                }
            }

            // SQL文を実行
            res = ps.executeQuery();

            // コード管理マスタのキャッシュ読み込み
            CodeManagMstCache cache = CodeManagMstCache.getInstance();
            LinkedHashMap<String, CodeManagMstDto> saleKbnList = cache
                    .getList(NaikaraTalkConstants.CODE_CATEGORY_SALE_KBN);
            LinkedHashMap<String, CodeManagMstDto> productList = cache
                    .getList(NaikaraTalkConstants.CODE_CATEGORY_PRODUCT_KBN);

            // 戻りdto作成
            list = new ArrayList<GoodsMstDto>();
            while (res.next()) {
                retDto = new GoodsMstDto();

                retDto.setGoodsCd(res.getString("GOODS_CD"));                                           // 商品コード
                retDto.setGoodsNm(res.getString("GOODS_NM"));                                           // 商品名
                retDto.setExplanation(res.getString("EXPLANATION"));                                    // 詳細説明
                retDto.setSaleKbn(res.getString("SALE_KBN"));                                           // 受取方法区分

                retDto.setSaleKbnNm(saleKbnList.get(res.getString("SALE_KBN")).getManagerNm());

                retDto.setImgPhotoNm(res.getString("IMG_PHOTO_NM"));                                    // サンプル画像名
                retDto.setProductKbn(res.getString("PRODUCT_KBN"));                                     // 商品形態区分

                retDto.setProductKbnNm(productList.get(res.getString("PRODUCT_KBN")).getManagerNm());

                retDto.setGoodsFileNm(res.getString("GOODS_FILE_NM"));                                  // 商品ファイル名
                retDto.setGoodsFile(res.getBlob("GOODS_FILE"));                                         // 商品ファイル
                retDto.setSalesPrice(res.getBigDecimal("SALES_PRICE"));                                 // 販売価格
                retDto.setUsePoint(res.getBigDecimal("USE_POINT"));                                     // 利用ポイント
                retDto.setPurchaseDescription(res.getString("PURCHASE_DESCRIPTION"));                   // 購入先説明
                retDto.setPurchaseUrl(res.getString("PURCHASE_URL"));                                   // 購入先URL
                retDto.setUseStrDt(NaikaraStringUtil.converToYYYY_MM_DD(res.getString("USE_STR_DT")));  // 提供開始日
                retDto.setUseEndDt(NaikaraStringUtil.converToYYYY_MM_DD(res.getString("USE_END_DT")));  // 提供終了日

                // 2014.02.18 Add 項目追加「講師への購入メール連絡付き区分」対応
                retDto.setTeacherContactKbn(res.getString("TEACHER_CONTACT_KBN"));                      // 講師への購入メール連絡付き区分

                retDto.setUseCourseMemo(res.getString("USE_COURSE_MEMO"));                              // 利用コースメモ
                retDto.setRemark(res.getString("REMARK"));                                              // 備考
                retDto.setRecordVerNo(res.getInt("RECORD_VER_NO"));                                     // レコードバージョン番号
                retDto.setReturnCode(NaikaraTalkConstants.RETURN_CD_DATA_YES);

                list.add(retDto);
            }

            if (list.size() < 1) {
                list = new ArrayList<GoodsMstDto>();
                retDto = new GoodsMstDto();
                retDto.setReturnCode(NaikaraTalkConstants.RETURN_CD_DATA_NO);
                list.add(retDto);
                return list;
            }

            ps.close();

            // 戻りdto
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
     * 検索処理(販売商品マスタメンテナンス【一覧】用 Or有り)。<br>
     * <br>
     * @param SaleGoodsMstDto
     *            画面のパラメータ
     * @return GoodsMstDto 検索結果
     * @throws NaiException
     */
    public ArrayList<GoodsMstDto> search(GoodsMstDto dto, OrderCondition OrderBy) throws NaiException {

        // 戻り値とリターンコードの初期化
        ArrayList<GoodsMstDto> list = null;  // DTOリスト
        GoodsMstDto retDto = null;           // DTO

        ResultSet res = null;

        StringBuffer sb = new StringBuffer();

        // SQL文作成
        sb.append(" select ");
        sb.append("  GOODS_CD ");
        sb.append(" ,GOODS_NM ");
        sb.append(" ,EXPLANATION ");
        sb.append(" ,SALE_KBN ");
        sb.append(" ,IMG_PHOTO ");
        sb.append(" ,IMG_PHOTO_NM ");
        sb.append(" ,PRODUCT_KBN ");
        sb.append(" ,GOODS_FILE ");
        sb.append(" ,GOODS_FILE_NM ");
        sb.append(" ,SALES_PRICE ");
        sb.append(" ,USE_POINT ");
        sb.append(" ,PURCHASE_DESCRIPTION ");
        sb.append(" ,PURCHASE_URL ");
        sb.append(" ,USE_STR_DT ");
        sb.append(" ,USE_END_DT ");
        sb.append(" ,USE_COURSE_MEMO ");
        sb.append(" ,REMARK ");
        sb.append(" ,RECORD_VER_NO ");
        sb.append(" from ");
        sb.append(" GOODS_MST ");
        sb.append(" where 1=1 ");

        // 商品コードを入力されている場合<曖昧検索>
        if (!StringUtils.isEmpty(dto.getGoodsCd())) {
            sb.append(" and ");
            sb.append("  GOODS_CD like ? ");
        }

        // 商品名を入力されている場合<曖昧検索>
        if (!StringUtils.isEmpty(dto.getGoodsNm())) {
            sb.append(" and ");
            sb.append("  GOODS_NM like ? ");
        }

        // 利用状態＝利用可(のコード) の場合
        if (StringUtils.equals(NaikaraTalkConstants.USE_KBN_OK, dto.getUseKbn())) {
            sb.append(" and ");
            sb.append("  USE_STR_DT <= ").append(" ? ");
            sb.append(" and ");
            sb.append("  USE_END_DT >= ").append(" ? ");
        }

        // 利用状態＝利用不可(のコード) の場合
        if (StringUtils.equals(NaikaraTalkConstants.USE_KBN_NG, dto.getUseKbn())) {
            sb.append(" and ");
            sb.append(" (");
            sb.append("  USE_STR_DT > ").append(" ? ");
            sb.append(" or ");
            sb.append("  USE_END_DT < ").append(" ? ");
            sb.append(") ");
        }

        // 並び順の設定
        if (!StringUtils.isEmpty(OrderBy.getOrderString())) {
            sb.append(OrderBy.getOrderString());
        }

        try {

            PreparedStatement ps = conn.prepareStatement(sb.toString());

            // パラメタの設定
            StringBuffer work = new StringBuffer();

            int i = 0;
            if (!StringUtils.isEmpty(dto.getGoodsCd())) {
                i++;
                work.setLength(0);
                work.append(NaikaraTalkConstants.OPERATOR_PERCENT).append(dto.getGoodsCd());  // 商品コード
                work.append(NaikaraTalkConstants.OPERATOR_PERCENT);

                ps.setString(i, work.toString());
            }

            if (!StringUtils.isEmpty(dto.getGoodsNm())) {
                i++;
                work.setLength(0);
                work.append(NaikaraTalkConstants.OPERATOR_PERCENT).append(dto.getGoodsNm());  // 商品名
                work.append(NaikaraTalkConstants.OPERATOR_PERCENT);

                ps.setString(i, work.toString());
            }

            if (!StringUtils.isEmpty(dto.getUseKbn())) {
                i++;
                ps.setString(i, DateUtil.getSysDate());      // 提供開始日
                i++;
                ps.setString(i, DateUtil.getSysDate());      // 提供終了日
            }

            // SQL文を実行
            res = ps.executeQuery();

            // コード管理マスタのキャッシュ読み込み
            CodeManagMstCache cache = CodeManagMstCache.getInstance();
            LinkedHashMap<String, CodeManagMstDto> saleKbnList = cache
                    .getList(NaikaraTalkConstants.CODE_CATEGORY_SALE_KBN);
            LinkedHashMap<String, CodeManagMstDto> productList = cache
                    .getList(NaikaraTalkConstants.CODE_CATEGORY_PRODUCT_KBN);

            // 戻りdto作成
            list = new ArrayList<GoodsMstDto>();
            while (res.next()) {
                retDto = new GoodsMstDto();

                retDto.setGoodsCd(res.getString("GOODS_CD"));                                           // 商品コード
                retDto.setGoodsNm(res.getString("GOODS_NM"));                                           // 商品名
                retDto.setExplanation(res.getString("EXPLANATION"));                                    // 詳細説明
                retDto.setSaleKbn(res.getString("SALE_KBN"));                                           // 受取方法区分

                retDto.setSaleKbnNm(saleKbnList.get(res.getString("SALE_KBN")).getManagerNm());

                retDto.setImgPhotoNm(res.getString("IMG_PHOTO_NM"));                                    // サンプル画像名
                retDto.setProductKbn(res.getString("PRODUCT_KBN"));                                     // 商品形態区分

                retDto.setProductKbnNm(productList.get(res.getString("PRODUCT_KBN")).getManagerNm());

                retDto.setGoodsFileNm(res.getString("GOODS_FILE_NM"));                                  // 商品ファイル名
                retDto.setSalesPrice(res.getBigDecimal("SALES_PRICE"));                                 // 販売価格
                retDto.setUsePoint(res.getBigDecimal("USE_POINT"));                                     // 利用ポイント
                retDto.setPurchaseDescription(res.getString("PURCHASE_DESCRIPTION"));                   // 購入先説明
                retDto.setPurchaseUrl(res.getString("PURCHASE_URL"));                                   // 購入先URL
                retDto.setUseStrDt(NaikaraStringUtil.converToYYYY_MM_DD(res.getString("USE_STR_DT")));  // 提供開始日
                retDto.setUseEndDt(NaikaraStringUtil.converToYYYY_MM_DD(res.getString("USE_END_DT")));  // 提供終了日
                retDto.setUseCourseMemo(res.getString("USE_COURSE_MEMO"));                              // 利用コースメモ
                retDto.setRemark(res.getString("REMARK"));                                              // 備考
                retDto.setRecordVerNo(res.getInt("RECORD_VER_NO"));                                     // レコードバージョン番号
                retDto.setReturnCode(NaikaraTalkConstants.RETURN_CD_DATA_YES);

                list.add(retDto);
            }

            if (list.size() < 1) {
                list = new ArrayList<GoodsMstDto>();
                retDto = new GoodsMstDto();
                retDto.setReturnCode(NaikaraTalkConstants.RETURN_CD_DATA_NO);
                list.add(retDto);
                return list;
            }

            ps.close();

            // 戻りdto
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
     * 登録処理。<br>
     * <br>
     * @param GoodsMstDto
     *            画面のパラメータ
     * @return なし
     * @throws NaiException
     * @throws IOException
     */
    public int insert(GoodsMstDto dto) throws NaiException, IOException {

        // この画面を開いたロールを取得します(この段階に到達した時点でユーザ情報は存在している)
        String userId = ((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString())).getUserId();

        int insertRowCount = NaikaraTalkConstants.RETURN_CD_ERR_INS; // 追加データ件数

        StringBuffer sb = new StringBuffer();

        // SQL文作成
        sb.append(" insert into GOODS_MST");
        sb.append(" ( ");
        sb.append("  GOODS_CD ");
        sb.append(" ,GOODS_NM ");
        sb.append(" ,EXPLANATION ");
        sb.append(" ,SALE_KBN ");
        sb.append(" ,IMG_PHOTO_NM ");
        sb.append(" ,IMG_PHOTO ");
        sb.append(" ,PRODUCT_KBN ");
        sb.append(" ,GOODS_FILE_NM ");
        sb.append(" ,GOODS_FILE ");
        sb.append(" ,SALES_PRICE ");
        sb.append(" ,USE_POINT ");
        sb.append(" ,PURCHASE_DESCRIPTION ");
        sb.append(" ,PURCHASE_URL ");
        sb.append(" ,USE_STR_DT ");
        sb.append(" ,USE_END_DT ");

        // 2014.02.18 Add 項目追加「講師への購入メール連絡付き区分」対応
        sb.append(" ,TEACHER_CONTACT_KBN ");

        sb.append(" ,USE_COURSE_MEMO ");
        sb.append(" ,REMARK ");
        sb.append(" ,RECORD_VER_NO ");
        sb.append(" ,INSERT_DTTM ");
        sb.append(" ,INSERT_CD ");
        sb.append(" ,UPDATE_DTTM ");
        sb.append(" ,UPDATE_CD ");
        sb.append(" ) ");
        sb.append(" VALUES ");
        sb.append(" ( ");
        sb.append("  ? ");          // 01.商品コード
        sb.append(" ,? ");          // 02.商品名
        sb.append(" ,? ");          // 03.詳細説明
        sb.append(" ,? ");          // 04.受取方法
        sb.append(" ,? ");          // 05.サンプル画像名
        sb.append(" ,? ");          // 06.サンプル画像
        sb.append(" ,? ");          // 07.商品形態
        sb.append(" ,? ");          // 08.商品ファイル名
        sb.append(" ,? ");          // 09.商品ファイル
        sb.append(" ,? ");          // 10.販売価格
        sb.append(" ,? ");          // 11.利用ポイント
        sb.append(" ,? ");          // 12.購入先説明
        sb.append(" ,? ");          // 13.購入先URL
        sb.append(" ,? ");          // 14.提供開始日
        sb.append(" ,? ");          // 15.提供終了日

        // 2014.02.18 Add 項目追加「講師への購入メール連絡付き区分」対応
        sb.append(" ,? ");          // XX.講師への購入メール連絡付き区分

        sb.append(" ,? ");          // 16.利用コースメモ
        sb.append(" ,? ");          // 17.備考
        sb.append(" ,0 ");          // --.レコードバージョン番号
        sb.append(" ,sysdate() ");  // --.登録日時
        sb.append(" ,? ");          // 18.登録者コード
        sb.append(" ,sysdate() ");  // --.更新日時
        sb.append(" ,? ");          // 19.更新者コード
        sb.append(" ) ");

        // BLOG用
        FileInputStream fis1 = null;
        FileInputStream fis2 = null;

        PreparedStatement ps = null;

        try {

            ps = conn.prepareStatement(sb.toString());

            // 商品コード
            ps.setString(1, dto.getGoodsCd());
            // 商品名
            ps.setString(2, dto.getGoodsNm());
            // 詳細説明
            ps.setString(3, dto.getExplanation());
            // 受取方法
            ps.setString(4, dto.getSaleKbn());

            // サンプル画像
            if (dto.getImgPhotoPage() == null) {
                ps.setString(5, NaikaraTalkConstants.IMG_NODATA);  // サンプル画像名
                try {
                    fis1 = new FileInputStream(new File(ServletActionContext.getServletContext().getRealPath(
                            NaikaraTalkConstants.IMG_NODATA_PATH), NaikaraTalkConstants.IMG_NODATA));
                    ps.setBinaryStream(6, fis1, fis1.available());
                } catch (IOException se) {
                    throw new NaiException(se);
                }
            } else {
                ps.setString(5, dto.getImgPhotoNm());              // サンプル画像名
                try {
                    fis1 = new FileInputStream(dto.getImgPhotoPage());
                    ps.setBinaryStream(6, fis1, fis1.available());
                } catch (IOException se) {
                    throw new NaiException(se);
                }
            }

            // 商品形態
            ps.setString(7, dto.getProductKbn());
            // 商品ファイル名
            ps.setString(8, dto.getGoodsFileNm());

            // 商品ファイル
            if (dto.getGoodsFilePage() == null) {
                ps.setNull(9, java.sql.Types.BLOB);
            } else {
                fis2 = new FileInputStream(dto.getGoodsFilePage());
                ps.setBinaryStream(9, fis2, fis2.available());
            }

            // 販売価格
            ps.setBigDecimal(10, NaikaraStringUtil.delComma(dto.getSalesPrice()));
            // 利用ポイント
            ps.setBigDecimal(11, NaikaraStringUtil.delComma(dto.getSalesPrice()));
            // 購入先説明
            ps.setString(12, dto.getPurchaseDescription());
            // 購入先URL
            ps.setString(13, dto.getPurchaseUrl());
            // 提供開始日
            ps.setString(14, NaikaraStringUtil.converToYYYYMMDD(dto.getUseStrDt()));
            // 提供終了日
            ps.setString(15, NaikaraStringUtil.converToYYYYMMDD(dto.getUseEndDt()));


            // 2014.02.18 Add 項目追加「講師への購入メール連絡付き区分」対応
            ps.setString(16, dto.getTeacherContactKbn());

            // 利用コースメモ
            //ps.setString(16, dto.getUseCourseMemo());
            ps.setString(17, dto.getUseCourseMemo());

            // 備考
            //ps.setString(17, dto.getRemark());
            ps.setString(18, dto.getRemark());

            // 登録者コード
            //ps.setString(18, userId);
            ps.setString(19, userId);

            // 更新者コード
            //ps.setString(19, userId);
            ps.setString(20, userId);

            // SQL文を実行
            insertRowCount = ps.executeUpdate();

            return insertRowCount;

        } catch (SQLException se) {
            throw new NaiException(se);
        } finally {
            try {
                if (fis1 != null) {
                    fis1.close();
                }
                if (fis2 != null) {
                    fis2.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException se) {
                throw new NaiException(se);
            }
        }
    }

    /**
     * 更新処理。<br>
     * <br>
     * @param SaleGoodsMstDto
     *            画面のパラメータ
     * @return int 更新レコード数
     * @throws NaiException
     * @throws IOException
     */
    public int update(GoodsMstDto dto) throws NaiException, IOException {

        // この画面を開いたロールを取得します(この段階に到達した時点でユーザ情報は存在している)
        String userId = ((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString())).getUserId();

        int updatedRowCount = NaikaraTalkConstants.RETURN_CD_ERR_UPD; // 更新データ件数

        StringBuffer sb = new StringBuffer();

        // SQL文作成
        sb.append(" update GOODS_MST");
        sb.append(" set ");
        sb.append(" GOODS_NM = ?");
        sb.append(" ,EXPLANATION = ?");
        sb.append(" ,SALE_KBN = ?");
        if (StringUtils.equals(NaikaraTalkConstants.TRUE, dto.getImgPhoto_chkn()) || dto.getImgPhotoPage() != null) {
            sb.append(",IMG_PHOTO_NM = ? ");
            sb.append(",IMG_PHOTO = ? ");
        }

        sb.append(" ,PRODUCT_KBN = ?");
        if (!(StringUtils.equals("false", dto.getGoodsFile_chkn()) && dto.getGoodsFilePage() == null)) {
            sb.append(" ,GOODS_FILE_NM = ?");
            sb.append(" ,GOODS_FILE = ?");
        }
        sb.append(" ,SALES_PRICE = ?");
        sb.append(" ,USE_POINT = ?");
        sb.append(" ,PURCHASE_DESCRIPTION = ?");
        sb.append(" ,PURCHASE_URL = ?");
        sb.append(" ,USE_STR_DT = ?");
        sb.append(" ,USE_END_DT = ?");

        // 2014.02.18 Add 項目追加「講師への購入メール連絡付き区分」対応
        sb.append(" ,TEACHER_CONTACT_KBN = ?");

        sb.append(" ,USE_COURSE_MEMO = ?");
        sb.append(" ,REMARK = ?");
        sb.append(" ,RECORD_VER_NO = RECORD_VER_NO + 1");
        sb.append(" ,UPDATE_DTTM = sysdate()");
        sb.append(" ,UPDATE_CD = ?");
        sb.append(" where ");
        sb.append(" GOODS_CD = ? ");
        sb.append(" and RECORD_VER_NO = ?");

        // BLOG用
        FileInputStream fis1 = null;
        FileInputStream fis2 = null;

        PreparedStatement ps = null;
        try {

            ps = conn.prepareStatement(sb.toString());

            int index = 1;
            // 商品名
            ps.setString(index++, dto.getGoodsNm());
            // 詳細説明
            ps.setString(index++, dto.getExplanation());
            // 受取方法
            ps.setString(index++, dto.getSaleKbn());

            // サンプル画像、サンプル画像名
            // サンプル画像を｢削除｣がチェックされている場合
            if (dto.getImgPhotoPage() != null) {
                ps.setString(index++, dto.getImgPhotoNm());
                try {
                    fis1 = new FileInputStream(dto.getImgPhotoPage());
                    ps.setBinaryStream(index++, fis1, fis1.available());
                } catch (IOException se) {
                    throw new NaiException(se);
                }
            }
            if (StringUtils.equals(NaikaraTalkConstants.TRUE, dto.getImgPhoto_chkn()) && dto.getImgPhotoPage() == null) {
                ps.setString(index++, NaikaraTalkConstants.IMG_NODATA);
                try {
                    fis1 = new FileInputStream(new File(ServletActionContext.getServletContext().getRealPath(
                            NaikaraTalkConstants.IMG_NODATA_PATH), NaikaraTalkConstants.IMG_NODATA));
                    ps.setBinaryStream(index++, fis1, fis1.available());
                } catch (IOException se) {
                    throw new NaiException(se);
                }
            }

            // 商品形態
            ps.setString(index++, dto.getProductKbn());

            // 商品ファイル、商品ファイル名
            // 商品ファイルを｢削除｣がチェックされている場合
            if (StringUtils.equals("true", dto.getGoodsFile_chkn()) && dto.getGoodsFilePage() == null) {
                ps.setString(index++, "");
                ps.setNull(index++, java.sql.Types.BLOB);
            }
            if (StringUtils.equals("false", dto.getGoodsFile_chkn()) && dto.getGoodsFilePage() != null) {
                ps.setString(index++, dto.getGoodsFileNm());
                fis2 = new FileInputStream(dto.getGoodsFilePage());
                ps.setBinaryStream(index++, fis2, fis2.available());
            }

            // 販売価格
            ps.setBigDecimal(index++, NaikaraStringUtil.delComma(dto.getSalesPrice()));
            // 利用ポイント
            ps.setBigDecimal(index++, NaikaraStringUtil.delComma(dto.getSalesPrice()));
            // 購入先説明
            ps.setString(index++, dto.getPurchaseDescription());
            // 購入先URL
            ps.setString(index++, dto.getPurchaseUrl());
            // 提供開始日
            ps.setString(index++, NaikaraStringUtil.converToYYYYMMDD(dto.getUseStrDt()));
            // 提供終了日
            ps.setString(index++, NaikaraStringUtil.converToYYYYMMDD(dto.getUseEndDt()));


            // 2014.02.18 Add 項目追加「講師への購入メール連絡付き区分」対応
            // 講師への購入メール連絡付き区分
            ps.setString(index++, dto.getTeacherContactKbn());


            // 利用コースメモ
            ps.setString(index++, dto.getUseCourseMemo());
            // 備考
            ps.setString(index++, dto.getRemark());
            // 更新者コード
            ps.setString(index++, userId);
            // 商品コード
            ps.setString(index++, dto.getGoodsCd());
            // レコードバージョン番号
            ps.setInt(index++, dto.getRecordVerNo());

            // SQL文を実行
            updatedRowCount = ps.executeUpdate();

            return updatedRowCount;

        } catch (SQLException se) {
            throw new NaiException(se);
        } finally {
            try {
                if (fis1 != null) {
                    fis1.close();
                }
                if (fis2 != null) {
                    fis2.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException se) {
                throw new NaiException(se);
            }
        }
    }

}
