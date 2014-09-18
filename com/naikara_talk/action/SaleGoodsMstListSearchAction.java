package com.naikara_talk.action;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.model.SaleGoodsMstListModel;
import com.naikara_talk.service.SaleGoodsMstListSearchService;
import com.naikara_talk.sessiondata.SessionSaleGoodsMst;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>販売商品マスタメンテナンス(一覧)。<br>
 * <b>クラス名称　　　:</b>販売商品マスタメンテナンス検索Actionクラス。<br>
 * <b>クラス概要　　　:</b>販売商品マスタメンテナンス検索Action。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>
 * 2013/06/18 TECS 新規作成。
 */
public class SaleGoodsMstListSearchAction extends SaleGoodsMstListActionSupport {

    private static final long serialVersionUID = 1L;

    private static boolean returnOnFlg = false;                        // 戻る判定Onフラグ

    private static String searchGoodsCdKey;                            // 検索Key：選択された明細のKey-商品コード

    /**
     * 検索処理。<br>
     * <br>
     * @param なし
     * @return String
     * @throws NaiException
     */
    public String requestService() throws NaiException {

        // 開始ログ
        log.info(NaikaraStringUtil.unionProcesslog("Start"));

        //Modelクラス設定
        this.setupModel();

        //Serviceクラス生成
        SaleGoodsMstListSearchService service = new SaleGoodsMstListSearchService();

        try {

            // ヘッダ部の戻るボタン押下の場合は値の入れ替え処理
            this.SessionSaleGoodsMstToModelBefore();

            // ヘッダ部の戻るボタン押下の場合は画面へ値の設定処理
            this.SessionSaleGoodsMstToModelAfter();

            int chkResult;

            //検索前チェック
            chkResult = service.checkPreSelect(model, returnOnFlg, this.hasSearchFlg);

            // エラーの場合、メッセージ設定
            switch (chkResult) {
            case SaleGoodsMstListModel.ERR_PROS_BTN_MISMATCH:
                // 処理区分と押下されたボタンのチェックエラー
                this.addActionMessage(getMessage("EN0036", new String[] { "新規", "登録／選択ボタン" }));
                return SUCCESS;
            case SaleGoodsMstListModel.ERR_NO_UPD_ROLE:
                // 「スタッフ」の場合は[照会]を除きエラー
                this.addActionMessage(getMessage("EN0059", new String[] {}));
                return SUCCESS;
            case SaleGoodsMstListModel.ERR_ZERO_DATA:
                // 対象データZero件エラー
                this.addActionMessage(getMessage("EN0020", new String[]
                        { SaleGoodsMstListActionSupport.GOODS_MST_TBL_JNM, "" }));
                return SUCCESS;
            case SaleGoodsMstListModel.ERR_MAXOVER_DATA:
                // 対象データMax件以上エラー
                int MaxCnt = SaleGoodsMstListSearchService.LIST_MAX_CNT + 1;
                this.addActionMessage(getMessage("EN0023", new String[] { String.valueOf(MaxCnt) }));
                return SUCCESS;
            }

            if (!returnOnFlg || StringUtils.equals(NaikaraTalkConstants.FALSE, this.hasSearchFlg)) {

                // 戻る判定Onフラグ(returnOnFlg) = false 又は 画面状態フラグ == (初期状態ではない) の場合

                // 表示データの取得と設定
                this.model.setResultList(service.selectList(this.model));
                this.hasSearchFlg = Boolean.toString(Boolean.FALSE);
            } else {
                // クリア
                this.hasSearchFlg = NaikaraTalkConstants.TRUE;
            }

        } catch (Exception e) {
            throw new NaiException(e);
        }

        if (returnOnFlg && StringUtils.equals(NaikaraTalkConstants.FALSE, this.hasSearchFlg)) {
            // 戻る判定Onフラグ(returnOnFlg) = true 且つ 画面状態フラグ(hasSearchFlg) == (初期状態ではない) の場合
            this.select_rdl = searchGoodsCdKey;
        } else {
            // 一覧の選択したラジオボタンをクリアする
            this.select_rdl = NaikaraTalkConstants.BRANK;
        }

        try {
            // 戻る用に必要な情報を格納
            this.modelToSessionSaleGoodsMst();
        } catch (Exception e) {
            throw new NaiException(e);
        }

        // メッセージの設定
        if (!StringUtils.isEmpty(this.message)) {
            this.addActionMessage(this.message);
        }

        return SUCCESS;
    }


    /**
     * SessionSaleGoodsMst値をModelにセット。<br>
     * <br>
     * @throws Exception
     */
    private void SessionSaleGoodsMstToModelBefore() throws NaiException {

        if ((SessionSaleGoodsMst) SessionDataUtil.getSessionData(SessionSaleGoodsMst.class.toString()) != null) {
            this.hasSearchFlg = ((SessionSaleGoodsMst) SessionDataUtil.getSessionData(SessionSaleGoodsMst.class
                    .toString())).getHasSearchFlg();               // 検索判断フラグ

            returnOnFlg = ((SessionSaleGoodsMst) SessionDataUtil.getSessionData(SessionSaleGoodsMst.class
                    .toString())).getReturnOnFlg();                //戻る判定Onフラグ

            if (returnOnFlg == true) {
                // 「戻る」の場合(getReturnOnFlgにtrueが設定されるのは、販売商品マスタメンテナンス登録選択Actionクラスのみ)
                String processKbn = ((SessionSaleGoodsMst) SessionDataUtil.getSessionData(SessionSaleGoodsMst.class
                        .toString())).getProcessKbn();
                String searchGoodsCd = ((SessionSaleGoodsMst) SessionDataUtil.getSessionData(SessionSaleGoodsMst.class
                        .toString())).getSearchGoodsCd();
                String searchGoodsNm = ((SessionSaleGoodsMst) SessionDataUtil.getSessionData(SessionSaleGoodsMst.class
                        .toString())).getSearchGoodsNm();
                String searchUseKbn = ((SessionSaleGoodsMst) SessionDataUtil.getSessionData(SessionSaleGoodsMst.class
                        .toString())).getSearchUseKbn();

                this.model.setProcessKbn(processKbn);  // 検索：処理区分
                this.model.setGoodsCd(searchGoodsCd);  // 検索Key<検索ボタン押下時>：商品コード
                this.model.setGoodsNm(searchGoodsNm);  // 検索Key<検索ボタン押下時>：商品名
                this.model.setUseKbn(searchUseKbn);    // 検索Key<検索ボタン押下時>：利用状態

            }

        }

    }

    /**
     * SessionSaleGoodsMst値を画面にセット。<br>
     * <br>
     * @throws Exception
     */
    private void SessionSaleGoodsMstToModelAfter() throws NaiException {

        if ((SessionSaleGoodsMst) SessionDataUtil.getSessionData(SessionSaleGoodsMst.class.toString()) != null) {
            this.hasSearchFlg = ((SessionSaleGoodsMst) SessionDataUtil.getSessionData(SessionSaleGoodsMst.class
                    .toString())).getHasSearchFlg();               // 検索判断フラグ

            returnOnFlg = ((SessionSaleGoodsMst) SessionDataUtil.getSessionData(SessionSaleGoodsMst.class
                    .toString())).getReturnOnFlg();                //戻る判定Onフラグ

            if (returnOnFlg == true) {
                // 「戻る」の場合(getReturnOnFlgにtrueが設定されるのは、コード管理マスタメンテナンス登録/選択Actionクラスのみ)
                String processKbn = ((SessionSaleGoodsMst) SessionDataUtil.getSessionData(SessionSaleGoodsMst.class
                        .toString())).getProcessKbn();             //処理区分
                String goodsCd = ((SessionSaleGoodsMst) SessionDataUtil.getSessionData(SessionSaleGoodsMst.class
                        .toString())).getGoodsCd();                //商品コード

                String goodsNm = ((SessionSaleGoodsMst) SessionDataUtil.getSessionData(SessionSaleGoodsMst.class
                        .toString())).getGoodsNm();                //商品名

                String useKbn = ((SessionSaleGoodsMst) SessionDataUtil.getSessionData(SessionSaleGoodsMst.class
                        .toString())).getUseKbn();                 //利用状態

                searchGoodsCdKey = ((SessionSaleGoodsMst) SessionDataUtil.getSessionData(SessionSaleGoodsMst.class
                        .toString())).getSearchGoodsCdKey();       //検索Key：選択された明細のKey-商品コード

                this.processKbn_rdl = processKbn;    // 処理区分
                this.goodsCd_txt = goodsCd;          // 画面遷移前に保持した値：商品コード
                this.goodsNm_txt = goodsNm;          // 画面遷移前に保持した値：商品名
                this.useKbn_rdl = useKbn;            // 画面遷移前に保持した値：利用状態
                this.select_rdl = searchGoodsCdKey;  // 画面遷移前に保持した値：選択された明細のKey-商品コード

            }

            //SessionSaleGoodsMstのクリア
            SessionDataUtil.clearSessionData(SessionSaleGoodsMst.class.toString());

        }

    }

    /**
     * Model値・SessionSaleGoodsMst値をSessionSaleGoodsMstにセット。<br>
     * <br>
     * @throws Exception
     */
    private void modelToSessionSaleGoodsMst() throws NaiException {

        this.setHasSearchFlg(this.hasSearchFlg);

        // 戻る用に必要な情報を取得
        SessionSaleGoodsMst sessionData = new SessionSaleGoodsMst();
        sessionData.setReturnOnFlg(false);                      //戻る判定Onフラグ=Offとする
        sessionData.setSearchGoodsCd(this.model.getGoodsCd());  // 検索Key：商品コード
        sessionData.setSearchGoodsNm(this.model.getGoodsNm());  // 検索Key：商品名
        sessionData.setSearchUseKbn(this.model.getUseKbn());    // 検索Key：利用状態
        sessionData.setHasSearchFlg(this.getHasSearchFlg());    // 検索判断フラグ
        SessionDataUtil.setSessionData(sessionData);

    }

}
