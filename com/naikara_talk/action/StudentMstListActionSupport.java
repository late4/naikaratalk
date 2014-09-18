package com.naikara_talk.action;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.naikara_talk.dto.CodeManagMstDto;
import com.naikara_talk.dto.StuMPoiOwnTDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.model.StudentMstListModel;
import com.naikara_talk.mstcache.CodeManagMstCache;
import com.naikara_talk.service.StudentMstListLoadService;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>マスタ保守<br>
 * <b>クラス名称　　　:</b>受講者マスタメンテナンス【一覧】共通Actionクラス。<br>
 * <b>クラス概要　　　:</b>受講者マスタメンテナンス【一覧】共通Action。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/07/30 TECS 新規作成。
 */
public abstract class StudentMstListActionSupport extends NaikaraActionSupport {

    private static final long serialVersionUID = 1L;

    // 画面表示のタイトル
    protected String title = "受講者マスタメンテナンス";

    // Help画面名
    protected String helpPageId = "HelpStudentMstList.html";

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
            initRadio();
            initSelect();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * サービスの呼び出しの実装。
     */
    abstract protected String requestService() throws NaiException;

    /**
     * ラジオボタンを取得する。<br>
     * <br>
     * @param なし
     * @return void
     * @throws Exception
     */
    public void initRadio() throws Exception {

        StudentMstListLoadService service = new StudentMstListLoadService();
        // お客様区分を取得する
        this.customerKbn_rdll = service.selectCodeMst(NaikaraTalkConstants.CODE_CATEGORY_CUSTOMER_KBN);
    }

    /**
     * ドロップダウンを取得する。<br>
     * <br>
     * @param なし
     * @return void
     * @throws Exception
     */
    public void initSelect() throws Exception {

        CodeManagMstCache cache = CodeManagMstCache.getInstance();
        // 条件キー条件１～５のリストを取得する
        this.itemNm_sell = cache.getList(NaikaraTalkConstants.CODE_CATEGORY_STUDENT_MST_SEARCH);

    }

    /**
     * 画面のパラメータをモデルにセット。<br>
     * <br>
     * @param なし
     * @return なし
     */
    public void setupModel() {
        this.model.setProcessKbn_rdl(this.processKbn_rdl);
        this.model.setCustomerKbn_rdl(this.customerKbn_rdl);
        this.model.setItemNm1_sel(this.itemNm1_sel);
        this.model.setItemNm2_sel(this.itemNm2_sel);
        this.model.setItemNm3_sel(this.itemNm3_sel);
        this.model.setItemNm4_sel(this.itemNm4_sel);
        this.model.setItemNm5_sel(this.itemNm5_sel);
        this.model.setItemCondn1_rdl(this.itemCondn1_rdl);
        this.model.setItemCondn2_rdl(this.itemCondn2_rdl);
        this.model.setItemCondn3_rdl(this.itemCondn3_rdl);
        this.model.setItemCondn4_rdl(this.itemCondn4_rdl);
        this.model.setItemCondn5_rdl(this.itemCondn5_rdl);
        this.model.setItemFrom1_txt(this.itemFrom1_txt);
        this.model.setItemFrom2_txt(this.itemFrom2_txt);
        this.model.setItemFrom3_txt(this.itemFrom3_txt);
        this.model.setItemFrom4_txt(this.itemFrom4_txt);
        this.model.setItemFrom5_txt(this.itemFrom5_txt);
        this.model.setItemTo1_txt(this.itemTo1_txt);
        this.model.setItemTo2_txt(this.itemTo2_txt);
        this.model.setItemTo3_txt(this.itemTo3_txt);
        this.model.setItemTo4_txt(this.itemTo4_txt);
        this.model.setItemTo5_txt(this.itemTo5_txt);
    }

    /** メッセージ */
    protected String message;

    /** 処理区分 */
    protected String processKbn_rdl;

    /** お客様区分 */
    protected String customerKbn_rdl;
    protected Map<String, String> customerKbn_rdll = new LinkedHashMap<String, String>();;

    /** 項目名1 */
    protected String itemNm1_sel;

    /** 項目条件1 */
    protected String itemCondn1_rdl;

    /** 項目1from */
    protected String itemFrom1_txt;

    /** 項目1to */
    protected String itemTo1_txt;

    /** 項目名2 */
    protected String itemNm2_sel;

    /** 項目条件2 */
    protected String itemCondn2_rdl;

    /** 項目2from */
    protected String itemFrom2_txt;

    /** 項目2to */
    protected String itemTo2_txt;

    /** 項目名3 */
    protected String itemNm3_sel;

    /** 項目条件3 */
    protected String itemCondn3_rdl;

    /** 項目3from */
    protected String itemFrom3_txt;

    /** 項目3to */
    protected String itemTo3_txt;

    /** 項目名4 */
    protected String itemNm4_sel;

    /** 項目条件4 */
    protected String itemCondn4_rdl;

    /** 項目4from */
    protected String itemFrom4_txt;

    /** 項目4to */
    protected String itemTo4_txt;

    /** 項目名5 */
    protected String itemNm5_sel;

    /** 項目条件5 */
    protected String itemCondn5_rdl;

    /** 項目5from */
    protected String itemFrom5_txt;

    /** 項目5to */
    protected String itemTo5_txt;

    /** 検索結果 */
    protected StudentMstListModel model = new StudentMstListModel();

    /** 一覧から選択された明細データ(jsp) */
    protected String select_rdl;

    /** チェックエラー場合、検索判断フラグ */
    protected String hasSearchFlg;

    /** セレクトボックスリスト */
    protected LinkedHashMap<String, CodeManagMstDto> itemNm_sell = new LinkedHashMap<String, CodeManagMstDto>();

    /** メール チェックボックスリスト*/
    protected String[] mailCheck_lst = {};

    /** メッセージID：EN0015 */
    protected String errorMsg_EN0015;

    /** 検索結果一覧 */
    protected List<StuMPoiOwnTDto> resultList;

    /**
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title セットする title
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
     * @return processKbn_rdl
     */
    public String getProcessKbn_rdl() {
        return processKbn_rdl;
    }

    /**
     * @param processKbn_rdl セットする processKbn_rdl
     */
    public void setProcessKbn_rdl(String processKbn_rdl) {
        this.processKbn_rdl = processKbn_rdl;
    }

    /**
     * @return customerKbn_rdl
     */
    public String getCustomerKbn_rdl() {
        return customerKbn_rdl;
    }

    /**
     * @param customerKbn_rdl セットする customerKbn_rdl
     */
    public void setCustomerKbn_rdl(String customerKbn_rdl) {
        this.customerKbn_rdl = customerKbn_rdl;
    }

    /**
     * @return customerKbn_rdll
     */
    public Map<String, String> getCustomerKbn_rdll() {
        return customerKbn_rdll;
    }

    /**
     * @param customerKbn_rdll セットする customerKbn_rdll
     */
    public void setCustomerKbn_rdll(Map<String, String> customerKbn_rdll) {
        this.customerKbn_rdll = customerKbn_rdll;
    }

    /**
     * @return itemNm1_sel
     */
    public String getItemNm1_sel() {
        return itemNm1_sel;
    }

    /**
     * @param itemNm1_sel セットする itemNm1_sel
     */
    public void setItemNm1_sel(String itemNm1_sel) {
        this.itemNm1_sel = itemNm1_sel;
    }

    /**
     * @return itemCondn1_rdl
     */
    public String getItemCondn1_rdl() {
        return itemCondn1_rdl;
    }

    /**
     * @param itemCondn1_rdl セットする itemCondn1_rdl
     */
    public void setItemCondn1_rdl(String itemCondn1_rdl) {
        this.itemCondn1_rdl = itemCondn1_rdl;
    }

    /**
     * @return itemFrom1_txt
     */
    public String getItemFrom1_txt() {
        return itemFrom1_txt;
    }

    /**
     * @param itemFrom1_txt セットする itemFrom1_txt
     */
    public void setItemFrom1_txt(String itemFrom1_txt) {
        this.itemFrom1_txt = itemFrom1_txt;
    }

    /**
     * @return itemTo1_txt
     */
    public String getItemTo1_txt() {
        return itemTo1_txt;
    }

    /**
     * @param itemTo1_txt セットする itemTo1_txt
     */
    public void setItemTo1_txt(String itemTo1_txt) {
        this.itemTo1_txt = itemTo1_txt;
    }

    /**
     * @return itemNm2_sel
     */
    public String getItemNm2_sel() {
        return itemNm2_sel;
    }

    /**
     * @param itemNm2_sel セットする itemNm2_sel
     */
    public void setItemNm2_sel(String itemNm2_sel) {
        this.itemNm2_sel = itemNm2_sel;
    }

    /**
     * @return itemCondn2_rdl
     */
    public String getItemCondn2_rdl() {
        return itemCondn2_rdl;
    }

    /**
     * @param itemCondn2_rdl セットする itemCondn2_rdl
     */
    public void setItemCondn2_rdl(String itemCondn2_rdl) {
        this.itemCondn2_rdl = itemCondn2_rdl;
    }

    /**
     * @return itemFrom2_txt
     */
    public String getItemFrom2_txt() {
        return itemFrom2_txt;
    }

    /**
     * @param itemFrom2_txt セットする itemFrom2_txt
     */
    public void setItemFrom2_txt(String itemFrom2_txt) {
        this.itemFrom2_txt = itemFrom2_txt;
    }

    /**
     * @return itemTo2_txt
     */
    public String getItemTo2_txt() {
        return itemTo2_txt;
    }

    /**
     * @param itemTo2_txt セットする itemTo2_txt
     */
    public void setItemTo2_txt(String itemTo2_txt) {
        this.itemTo2_txt = itemTo2_txt;
    }

    /**
     * @return itemNm3_sel
     */
    public String getItemNm3_sel() {
        return itemNm3_sel;
    }

    /**
     * @param itemNm3_sel セットする itemNm3_sel
     */
    public void setItemNm3_sel(String itemNm3_sel) {
        this.itemNm3_sel = itemNm3_sel;
    }

    /**
     * @return itemCondn3_rdl
     */
    public String getItemCondn3_rdl() {
        return itemCondn3_rdl;
    }

    /**
     * @param itemCondn3_rdl セットする itemCondn3_rdl
     */
    public void setItemCondn3_rdl(String itemCondn3_rdl) {
        this.itemCondn3_rdl = itemCondn3_rdl;
    }

    /**
     * @return itemFrom3_txt
     */
    public String getItemFrom3_txt() {
        return itemFrom3_txt;
    }

    /**
     * @param itemFrom3_txt セットする itemFrom3_txt
     */
    public void setItemFrom3_txt(String itemFrom3_txt) {
        this.itemFrom3_txt = itemFrom3_txt;
    }

    /**
     * @return itemTo3_txt
     */
    public String getItemTo3_txt() {
        return itemTo3_txt;
    }

    /**
     * @param itemTo3_txt セットする itemTo3_txt
     */
    public void setItemTo3_txt(String itemTo3_txt) {
        this.itemTo3_txt = itemTo3_txt;
    }

    /**
     * @return itemNm4_sel
     */
    public String getItemNm4_sel() {
        return itemNm4_sel;
    }

    /**
     * @param itemNm4_sel セットする itemNm4_sel
     */
    public void setItemNm4_sel(String itemNm4_sel) {
        this.itemNm4_sel = itemNm4_sel;
    }

    /**
     * @return itemCondn4_rdl
     */
    public String getItemCondn4_rdl() {
        return itemCondn4_rdl;
    }

    /**
     * @param itemCondn4_rdl セットする itemCondn4_rdl
     */
    public void setItemCondn4_rdl(String itemCondn4_rdl) {
        this.itemCondn4_rdl = itemCondn4_rdl;
    }

    /**
     * @return itemFrom4_txt
     */
    public String getItemFrom4_txt() {
        return itemFrom4_txt;
    }

    /**
     * @param itemFrom4_txt セットする itemFrom4_txt
     */
    public void setItemFrom4_txt(String itemFrom4_txt) {
        this.itemFrom4_txt = itemFrom4_txt;
    }

    /**
     * @return itemTo4_txt
     */
    public String getItemTo4_txt() {
        return itemTo4_txt;
    }

    /**
     * @param itemTo4_txt セットする itemTo4_txt
     */
    public void setItemTo4_txt(String itemTo4_txt) {
        this.itemTo4_txt = itemTo4_txt;
    }

    /**
     * @return itemNm5_sel
     */
    public String getItemNm5_sel() {
        return itemNm5_sel;
    }

    /**
     * @param itemNm5_sel セットする itemNm5_sel
     */
    public void setItemNm5_sel(String itemNm5_sel) {
        this.itemNm5_sel = itemNm5_sel;
    }

    /**
     * @return itemCondn5_rdl
     */
    public String getItemCondn5_rdl() {
        return itemCondn5_rdl;
    }

    /**
     * @param itemCondn5_rdl セットする itemCondn5_rdl
     */
    public void setItemCondn5_rdl(String itemCondn5_rdl) {
        this.itemCondn5_rdl = itemCondn5_rdl;
    }

    /**
     * @return itemFrom5_txt
     */
    public String getItemFrom5_txt() {
        return itemFrom5_txt;
    }

    /**
     * @param itemFrom5_txt セットする itemFrom5_txt
     */
    public void setItemFrom5_txt(String itemFrom5_txt) {
        this.itemFrom5_txt = itemFrom5_txt;
    }

    /**
     * @return itemTo5_txt
     */
    public String getItemTo5_txt() {
        return itemTo5_txt;
    }

    /**
     * @param itemTo5_txt セットする itemTo5_txt
     */
    public void setItemTo5_txt(String itemTo5_txt) {
        this.itemTo5_txt = itemTo5_txt;
    }

    /**
     * @return model
     */
    public StudentMstListModel getModel() {
        return model;
    }

    /**
     * @param model セットする model
     */
    public void setModel(StudentMstListModel model) {
        this.model = model;
    }

    /**
     * @return select_rdl
     */
    public String getSelect_rdl() {
        return select_rdl;
    }

    /**
     * @param select_rdl セットする select_rdl
     */
    public void setSelect_rdl(String select_rdl) {
        this.select_rdl = select_rdl;
    }

    /**
     * @return hasSearchFlg
     */
    public String getHasSearchFlg() {
        return hasSearchFlg;
    }

    /**
     * @param hasSearchFlg セットする hasSearchFlg
     */
    public void setHasSearchFlg(String hasSearchFlg) {
        this.hasSearchFlg = hasSearchFlg;
    }

    /**
     * @return itemNm_sell
     */
    public LinkedHashMap<String, CodeManagMstDto> getItemNm_sell() {
        return itemNm_sell;
    }

    /**
     * @param itemNm_sell セットする itemNm_sell
     */
    public void setItemNm_sell(LinkedHashMap<String, CodeManagMstDto> itemNm_sell) {
        this.itemNm_sell = itemNm_sell;
    }

    /**
     * @return errorMsg_EN0015
     */
    public String getErrorMsg_EN0015() {
        return errorMsg_EN0015;
    }

    /**
     * @param errorMsg_EN0015 セットする errorMsg_EN0015
     */
    public void setErrorMsg_EN0015(String errorMsg_EN0015) {
        this.errorMsg_EN0015 = errorMsg_EN0015;
    }

    /**
     * @return mailCheck_lst
     */
    public String[] getMailCheck_lst() {
        return mailCheck_lst;
    }

    /**
     * @param mailCheck_lst セットする mailCheck_lst
     */
    public void setMailCheck_lst(String[] mailCheck_lst) {
        this.mailCheck_lst = mailCheck_lst;
    }

    /**
     * @return resultList
     */
    public List<StuMPoiOwnTDto> getResultList() {
        return resultList;
    }

    /**
     * @param resultList セットする resultList
     */
    public void setResultList(List<StuMPoiOwnTDto> resultList) {
        this.resultList = resultList;
    }

}
