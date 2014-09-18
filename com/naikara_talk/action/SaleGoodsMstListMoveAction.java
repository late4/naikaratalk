package com.naikara_talk.action;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.model.SaleGoodsMstListModel;
import com.naikara_talk.service.SaleGoodsMstListMoveService;
import com.naikara_talk.sessiondata.SessionSaleGoodsMst;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>販売商品マスタメンテナンス(一覧)。<br>
 * <b>クラス名称　　　:</b>販売商品マスタメンテナンス登録選択Actionクラス。<br>
 * <b>クラス概要　　　:</b>販売商品マスタメンテナンス登録選択Action。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>
 * 2013/06/18 TECS 新規作成。
 */
public class SaleGoodsMstListMoveAction extends SaleGoodsMstListActionSupport {

    private static final long serialVersionUID = 1L;

    /**
     * 登録/選択ボタンの処理。<br>
     * <br>
     * @param なし
     * @return String
     * @throws NaiException
     */
    public String requestService() throws NaiException {

        // 開始ログ
        log.info(NaikaraStringUtil.unionProcesslog("Start"));

        // 画面のパラメータをモデルに設定
        this.setupModel();

        //Serviceクラス生成
        SaleGoodsMstListMoveService service = new SaleGoodsMstListMoveService();

        // 次画面へ遷移する前のチェック
        int chkResult = service.nextPageRequest(this.model, this.getSelect_rdl(), this.getHasSearchFlg());

        //エラーの場合、メッセージ設定
        try {

            SessionSaleGoodsMst sessionData = new SessionSaleGoodsMst();
            sessionData = (SessionSaleGoodsMst)SessionDataUtil.getSessionData(SessionSaleGoodsMst.class.toString());
            if ( sessionData != null ) {
                // ◆エラーが発生した場合でも、一覧が表示されている状態であれば表示する
                this.hasSearchFlg = NaikaraStringUtil.nvlString(sessionData.getHasSearchFlg()).toString();            // ログイン中の顧客区分(受講者の場合のみ設定される)

                if (StringUtils.equals(NaikaraTalkConstants.FALSE, this.hasSearchFlg)) {
                    // 表示データの取得と設定
                    this.model.setResultList(service.selectList(this.model));
                }
            }

            switch (chkResult) {
            case SaleGoodsMstListModel.ERR_NO_SELECT:
                // 一覧の選択なしエラー
                //this.message = getMessage("EN0015", new String[] { "一覧部の左の選択" });
                this.addActionMessage(getMessage("EN0015", new String[] { "一覧部の左の選択" }));
                return SUCCESS;
            case SaleGoodsMstListModel.ERR_LIST_BACK:
                // 一覧の選択なしエラー
                //this.message = getMessage("EN0015", new String[] { "一覧部の左の選択" });
                this.addActionMessage(getMessage("EN0015", new String[] { "一覧部の左の選択" }));
                return SUCCESS;
            case SaleGoodsMstListModel.ERR_NO_UPD_ROLE:
                // 「スタッフ」の場合は[照会]のみエラー
                //this.message = getMessage("EN0059", new String[] {});
                this.addActionMessage(getMessage("EN0059", new String[] {}));
                return SUCCESS;
            }

            // 正常の場合
            // 戻る用に必要な情報を取得/格納
            this.modelToSessionSaleGoodsMst();

        } catch (Exception e) {
            throw new NaiException(e);
        }

        // ヘッダの戻るリンク用
        setCurrentActionName(NaikaraTalkConstants.SALE_GOODS_MST_LIST_SEARCH);

        // 詳細画面遷移
        return NEXTPAGE;
    }


    /**
     * Model値・SessionSaleGoodsMst値をSessionSaleGoodsMstにセット。<br>
     * <br>
     * @throws Exception
     */
    private void modelToSessionSaleGoodsMst() throws NaiException {

        // 戻る用に必要な情報を格納
        SessionSaleGoodsMst sessionData = new SessionSaleGoodsMst();

        if ((SessionSaleGoodsMst) SessionDataUtil.getSessionData(SessionSaleGoodsMst.class.toString()) != null) {
            // 戻る用に必要な情報を取得
            String searchGoodsCd = ((SessionSaleGoodsMst) SessionDataUtil.getSessionData(SessionSaleGoodsMst.class
                    .toString())).getSearchGoodsCd();
            String searchGoodsNm = ((SessionSaleGoodsMst) SessionDataUtil.getSessionData(SessionSaleGoodsMst.class
                    .toString())).getSearchGoodsNm();
            String searchUseKbn = ((SessionSaleGoodsMst) SessionDataUtil.getSessionData(SessionSaleGoodsMst.class
                    .toString())).getSearchUseKbn();

            sessionData.setSearchGoodsCd(searchGoodsCd);        // <検索ボタン押下時に保持>検索Key：商品コード
            sessionData.setSearchGoodsNm(searchGoodsNm);        // <検索ボタン押下時に保持>検索Key：商品名
            sessionData.setSearchUseKbn(searchUseKbn);          // <検索ボタン押下時に保持>検索Key：利用状態

        }

        sessionData.setReturnOnFlg(true);                       // 画面遷移前に保持:戻る判定Onフラグ
        sessionData.setProcessKbn(this.model.getProcessKbn());  // 画面遷移前に保持:処理区分
        sessionData.setGoodsCd(this.model.getGoodsCd());        // 商品コード
        sessionData.setGoodsNm(this.model.getGoodsNm());        // 商品名
        sessionData.setUseKbn(this.model.getUseKbn());          // 利用状態
        sessionData.setSearchGoodsCdKey(this.getSelect_rdl());  // 検索Key：選択された明細のKey-商品コード

        this.setHasSearchFlg(NaikaraTalkConstants.TRUE);           // クリア(一覧なし)
        if (this.model.getResultList() != null ) {
            if (this.model.getResultList().size() > 0) {
                this.setHasSearchFlg(NaikaraTalkConstants.FALSE);  // 初期状態ではない(一覧あり)
            }
        }
        sessionData.setHasSearchFlg(this.getHasSearchFlg());    // 画面遷移前に保持:画面状態フラグ

        SessionDataUtil.setSessionData(sessionData);

    }

}
