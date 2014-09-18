package com.naikara_talk.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Map;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.model.SComCoursePointModel;
import com.naikara_talk.service.SComCoursePointLoadService;
import com.naikara_talk.util.DateUtil;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>お客様(個人)_初期登録。<br>
 * <b>クラス名称       :</b>コース別ポイントActionスーパークラス。<br>
 * <b>クラス概要       :</b>コース別ポイント一覧の情報を表示。<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/08/06 TECS 新規作成
 * <b>                 :</b>2014/04/22 TECS 検索条件：希望日のコントロールをテキストからプルダウンへ変更対応
 * <b>                 :</b>2014/04/22 TECS 検索条件：キーワード検索の削除・コース名の追加対応
 */
public abstract class SComCoursePointActionSupport extends NaikaraActionSupport {

    private static final long serialVersionUID = 1L;

    // 画面表示のタイトル
    protected String title = "コース別ポイント";

    // Help画面名
    protected String helpPageId = "HelpSComCoursePoint.html";

    /**
     * サービスの呼び出しの実装。
     */
    abstract protected String requestService() throws NaiException;

    // 2014/04/22 Add Start 希望日をテキストからプルダウンへ変更対応／検索条件：コース名の追加対応
    /**
     * validateエラー時にプルダウンを再設定する<br>
     * <br>
     * プルダウンを初期化する<br>
     * <br>
     * @param なし<br>
     * @return なし<br>
     * @exception なし
     */
    @Override
    public void validate() {
        // チェックエラーの場合、初期データ再取得
        try {
            this.initSelect();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    // 2014/04/22 Add End   希望日をテキストからプルダウンへ変更対応／検索条件：コース名の追加対応

    /**
     * 検索部のパラメータをモデルにセット。<br>
     * <br>
     * 検索部のパラメータをモデルにセット。<br>
     * <br>
     * @param なし<br>
     * @return なし<br>
     * @exception なし
     */
    public void setupSearchModel() {

        // 2014/04/22 Del Start キーワード検索の削除・コース名の追加対応
        //// キーワード１
        //this.model.setKeyword1(this.serchKey1_txt);
        //// キーワード２
        //this.model.setKeyword2(this.serchKey2_txt);
        // キーワード３
        //this.model.setKeyword3(this.serchKey3_txt);
        //// キーワード４
        //this.model.setKeyword4(this.serchKey4_txt);
        //// キーワード５
        //this.model.setKeyword5(this.serchKey5_txt);
        // 2014/04/22 Del End   キーワード検索の削除・コース名の追加対応

        // 2014/04/22 Upd Start 希望日をテキストからプルダウンへ変更対応／検索条件：コース名の追加対応
        // レッスン希望日
        //this.model.setSysDate(NaikaraStringUtil.converToYYYYMMDD(this.sysDate));
        this.model.setHopeDt(NaikaraStringUtil.converToYYYYMMDD(this.hopeDt_sel));

        // コース名：大分類
        this.model.setSearchCourseLargeCd(this.courseLarge_sel);
        // コース名：中分類
        this.model.setSearchCourseMediumCd(this.courseMedium_sel);
        // コース名：小分類
        this.model.setSearchCourseSmallCd(this.courseSmall_sel);
        // コース名：コース名 ⇒ キーワード
        this.model.setSearchKeyword(this.searchKeyword_txt);
        // 2014/04/22 Upd End   希望日をテキストからプルダウンへ変更対応／検索条件：コース名の追加対応


    }

    // 2014/04/22 Add Start 希望日をテキストからプルダウンへ変更対応／検索条件：コース名の追加対応
    /**
     * セレクトボックス取得。<br>
     * <br>
     * セレクトボックスを取得する。<br>
     * <br>
     * @param なし<br>
     * @return なし<br>
     * @throws Exception
     */
    public void initSelect() throws Exception {

        // 希望日用
        LinkedHashMap<String, String> dtMap = new LinkedHashMap<String, String>();

        // 書式設定
        DateFormat df1 = new SimpleDateFormat(DateUtil.DATE_FORMAT_yyyy_MM_dd);

        // カレンダーインスタンスの取得
        Calendar calendar = Calendar.getInstance();

        String setDt = df1.format(calendar.getTime());
        dtMap.put(setDt, setDt);                       // LinkedHashMapへの追加(当日)
        for (int i = 1 ; i < 28 ; i++){
            calendar.add(Calendar.DATE, 1);            // 日付の加算(+1日)
            setDt = df1.format(calendar.getTime());
            dtMap.put(setDt, setDt);                   // LinkedHashMapへの追加
        }
        this.hopeDt_sell = dtMap;
        // 2014/04/22 Add End   希望日をテキストからプルダウンへ変更対応

        SComCoursePointLoadService service = new SComCoursePointLoadService();

        // 大分類の取得
        this.courseLarge_sell = service.selectCodeMst(NaikaraTalkConstants.CODE_CATEGORY_BIG_CLASSIFICATION);
        // 中分類の取得
        this.courseMedium_sell = service.selectCodeMst(NaikaraTalkConstants.CODE_CATEGORY_MIDDLE_CLASSIFICATION);
        // 小分類の取得
        this.courseSmall_sell = service.selectCodeMst(NaikaraTalkConstants.CODE_CATEGORY_SMALL_CLASSIFICATION);
    }
    // 2014/04/22 Add End   希望日をテキストからプルダウンへ変更対応／検索条件：コース名の追加対応



    /** メッセージ */
    protected String message;

    // 2014/04/22 Upd Start 希望日をテキストからプルダウンへ変更対応／検索条件：コース名の追加対応
    /** 希望日 */
    //protected String sysDate;
    protected String hopeDt_sel;
    protected Map<String, String> hopeDt_sell = new LinkedHashMap<String, String>();

    /** コース名：大分類 */
    protected String courseLarge_sel;
    protected Map<String, String> courseLarge_sell = new LinkedHashMap<String, String>();

    /** コース名：中分類 */
    protected String courseMedium_sel;
    protected Map<String, String> courseMedium_sell = new LinkedHashMap<String, String>();

    /** コース名：小分類 */
    protected String courseSmall_sel;
    protected Map<String, String> courseSmall_sell = new LinkedHashMap<String, String>();

    /** コース名：コース名 ⇒ searchKeyword_txt */
    protected String searchKeyword_txt;
    // 2014/04/22 Upd End   希望日をテキストからプルダウンへ変更対応／検索条件：コース名の追加対応

    // 2014/04/22 Del Start キーワード検索の削除・コース名の追加対応
    ///** キーワード１(jsp) */
    //protected String serchKey1_txt;

    ///** キーワード２(jsp) */
    //protected String serchKey2_txt;

    ///** キーワード３(jsp) */
    //protected String serchKey3_txt;

    ///** キーワード４(jsp) */
    //protected String serchKey4_txt;

    ///** キーワード５(jsp) */
    //protected String serchKey5_txt;
    // 2014/04/22 Del End   キーワード検索の削除・コース名の追加対応

    /** コースコード(jsp) */
    protected String courseCd;

    /** コース名(jsp) */
    protected String courseJnm;

    /** 説明(jsp) */
    protected String simpleExplanation;

    /** 商品等(jsp) */
    protected String goodsNm;

    /** 利用ポイント(jsp) */
    protected String usePoint;

    /** お知らせ(jsp) */
    protected String information;

    /** 検索結果 */
    protected SComCoursePointModel model = new SComCoursePointModel();

    // 2014/04/22 Upd Start 希望日をテキストからプルダウンへ変更対応
    /**
     * @return sysDate
     */
//    public String getSysDate() {
//        return sysDate;
//    }

    /**
     * @param sysDate セットする sysDate
     */
//    public void setSysDate(String sysDate) {
//        this.sysDate = sysDate;
//    }

    /**
     * @return hopeDt_sel
     */
    public String getHopeDt_sel() {
        return hopeDt_sel;
    }

    /**
     * @param hopeDt_sel セットする hopeDt_sel
     */
    public void setHopeDt_sel(String hopeDt_sel) {
        this.hopeDt_sel = hopeDt_sel;
    }

    /**
     * @return hopeDt_sell
     */
    public Map<String, String> getHopeDt_sell() {
        return hopeDt_sell;
    }

    /**
     * @param hopeDt_sell セットする hopeDt_sell
     */
    public void setHopeDt_sell(Map<String, String> hopeDt_sell) {
        this.hopeDt_sell = hopeDt_sell;
    }
    // 2014/04/22 Upd End   希望日をテキストからプルダウンへ変更対応

    // 2014/04/22 Del Start キーワード検索の削除・コース名の追加対応
    ///**
    // * @return serchKey1_txt
    // */
    //public String getSerchKey1_txt() {
    //    return serchKey1_txt;
    //}

    ///**
    // * @param serchKey1_txt セットする serchKey1_txt
    // */
    //public void setSerchKey1_txt(String serchKey1_txt) {
    //    this.serchKey1_txt = serchKey1_txt;
    //}

    ///**
    // * @return serchKey2_txt
    // */
    //public String getSerchKey2_txt() {
    //    return serchKey2_txt;
    //}

    ///**
    // * @param serchKey2_txt セットする serchKey2_txt
    // */
    //public void setSerchKey2_txt(String serchKey2_txt) {
    //    this.serchKey2_txt = serchKey2_txt;
    //}

    ///**
    // * @return serchKey3_txt
    // */
    //public String getSerchKey3_txt() {
    //    return serchKey3_txt;
    //}

    ///**
    // * @param serchKey3_txt セットする serchKey3_txt
    // */
    //public void setSerchKey3_txt(String serchKey3_txt) {
    //    this.serchKey3_txt = serchKey3_txt;
    //}

    ///**
    // * @return serchKey4_txt
    // */
    //public String getSerchKey4_txt() {
    //    return serchKey4_txt;
    //}

    ///**
    // * @param serchKey4_txt セットする serchKey4_txt
    // */
    //public void setSerchKey4_txt(String serchKey4_txt) {
    //    this.serchKey4_txt = serchKey4_txt;
    //}

    ///**
    // * @return serchKey5_txt
    // */
    //public String getSerchKey5_txt() {
    //    return serchKey5_txt;
    //}

    ///**
    // * @param serchKey5_txt セットする serchKey5_txt
    // */
    //public void setSerchKey5_txt(String serchKey5_txt) {
    //    this.serchKey5_txt = serchKey5_txt;
    //}
    // 2014/04/22 Del End キーワード検索の削除・コース名の追加対応


    // 2014/04/22 Add Start 検索条件：コース名の追加対応
    /**
     * @return courseLarge_sel
     */
    public String getCourseLarge_sel() {
        return courseLarge_sel;
    }

    /**
     * @param courseLarge_sel セットする courseLarge_sel
     */
    public void setCourseLarge_sel(String courseLarge_sel) {
        this.courseLarge_sel = courseLarge_sel;
    }

    /**
     * @return courseLarge_sell
     */
    public Map<String, String> getCourseLarge_sell() {
        return courseLarge_sell;
    }

    /**
     * @param courseLarge_sell セットする courseLarge_sell
     */
    public void setCourseLarge_sell(Map<String, String> courseLarge_sell) {
        this.courseLarge_sell = courseLarge_sell;
    }

    /**
     * @return courseMedium_sel
     */
    public String getCourseMedium_sel() {
        return courseMedium_sel;
    }

    /**
     * @param courseMedium_sel セットする courseMedium_sel
     */
    public void setCourseMedium_sel(String courseMedium_sel) {
        this.courseMedium_sel = courseMedium_sel;
    }

    /**
     * @return courseMedium_sell
     */
    public Map<String, String> getCourseMedium_sell() {
        return courseMedium_sell;
    }

    /**
     * @param courseMedium_sell セットする courseMedium_sell
     */
    public void setCourseMedium_sell(Map<String, String> courseMedium_sell) {
        this.courseMedium_sell = courseMedium_sell;
    }

    /**
     * @return courseSmall_sel
     */
    public String getCourseSmall_sel() {
        return courseSmall_sel;
    }

    /**
     * @param courseSmall_sel セットする courseSmall_sel
     */
    public void setCourseSmall_sel(String courseSmall_sel) {
        this.courseSmall_sel = courseSmall_sel;
    }

    /**
     * @return courseSmall_sell
     */
    public Map<String, String> getCourseSmall_sell() {
        return courseSmall_sell;
    }

    /**
     * @param courseSmall_sell セットする courseSmall_sell
     */
    public void setCourseSmall_sell(Map<String, String> courseSmall_sell) {
        this.courseSmall_sell = courseSmall_sell;
    }

    /**
     * @return searchKeyword_txt
     */
    public String getSearchKeyword_txt() {
        return searchKeyword_txt;
    }

    /**
     * @param searchKeyword_txt セットする searchKeyword_txt
     */
    public void setSearchKeyword_txt(String searchKeyword_txt) {
        this.searchKeyword_txt = searchKeyword_txt;
    }
    // 2014/04/22 Add End   検索条件：コース名の追加対応

    /**
     * @return courseCd
     */
    public String getCourseCd() {
        return courseCd;
    }

    /**
     * @param courseCd セットする courseCd
     */
    public void setCourseCd(String courseCd) {
        this.courseCd = courseCd;
    }

    /**
     * @return courseJnm
     */
    public String getCourseJnm() {
        return courseJnm;
    }

    /**
     * @param courseJnm セットする courseJnm
     */
    public void setCourseJnm(String courseJnm) {
        this.courseJnm = courseJnm;
    }

    /**
     * @return simpleExplanation
     */
    public String getSimpleExplanation() {
        return simpleExplanation;
    }

    /**
     * @param simpleExplanation セットする simpleExplanation
     */
    public void setSimpleExplanation(String simpleExplanation) {
        this.simpleExplanation = simpleExplanation;
    }

    /**
     * @return goodsNm
     */
    public String getGoodsNm() {
        return goodsNm;
    }

    /**
     * @param goodsNm セットする goodsNm
     */
    public void setGoodsNm(String goodsNm) {
        this.goodsNm = goodsNm;
    }

    /**
     * @return usePoint
     */
    public String getUsePoint() {
        return usePoint;
    }

    /**
     * @param usePoint セットする usePoint
     */
    public void setUsePoint(String usePoint) {
        this.usePoint = usePoint;
    }

    /**
     * @return information
     */
    public String getInformation() {
        return information;
    }

    /**
     * @param information セットする information
     */
    public void setInformation(String information) {
        this.information = information;
    }

    /**
     * @return model
     */
    public SComCoursePointModel getModel() {
        return model;
    }

    /**
     * @param model セットする model
     */
    public void setModel(SComCoursePointModel model) {
        this.model = model;
    }

    /**
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title
     *            セットする title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return helpPageId
     */
    public String getHelpPageId() {
        return helpPageId;
    }

    /**
     * @param helpPageId
     *            セットする helpPageId
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
     * @param message
     *            セットする message
     */
    public void setMessage(String message) {
        this.message = message;
    }

}
