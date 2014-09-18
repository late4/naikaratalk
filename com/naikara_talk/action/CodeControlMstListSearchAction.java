package com.naikara_talk.action;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.model.CodeControlMstListModel;
import com.naikara_talk.service.CodeControlMstListSearchService;
import com.naikara_talk.sessiondata.SessionCodeControlMst;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>コード管理マスタメンテナンス(一覧)。<br>
 * <b>クラス名称　　　:</b>コード管理マスタメンテナンス検索Actionクラス。<br>
 * <b>クラス概要　　　:</b>コード管理マスタメンテナンス検索Action。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>
 * 2013/07/16 TECS 新規作成。
 */
public class CodeControlMstListSearchAction extends CodeControlMstListActionSupport {

    private static final long serialVersionUID = 1L;

    private static boolean returnOnFlg = false;                        // 戻る判定Onフラグ

    private static String searchManagerCdKey;                          // 検索Key：選択された明細のKey-汎用コード


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

        // コード種別の検索ボタン押下時の選択値を隠し項目へ設定
        this.searchPressCdCategory = this.cdCategory_sel;

        // エラー発生時に、選択された値を保持するために設定
        this.defaultCdCategory = this.cdCategory_sel;

        //画面の値をModelクラスへ設定
        this.setupModel();

        //Serviceクラス生成
        CodeControlMstListSearchService service = new CodeControlMstListSearchService();

        try {
            // ヘッダ部の戻るボタン押下の場合は値の入れ替え処理
            this.SessionCodeControlMstToModelBefore();

            // ヘッダ部の戻るボタン押下の場合は画面へ値の設定処理
            this.SessionCodeControlMstToModelAfter();

            int chkResult;

            // 検索前チェック
            chkResult = service.checkPreSelect(model, returnOnFlg, this.hasSearchFlg);
            // エラーの場合、メッセージ設定
            switch (chkResult) {
            case CodeControlMstListModel.ERR_PROS_BTN_MISMATCH:
                // 処理区分と押下されたボタンのチェックエラー
                this.addActionMessage(getMessage("EN0036", new String[] { "新規", "登録／選択ボタン" }));
                return SUCCESS;
            case CodeControlMstListModel.ERR_NO_DEL_ROLE:
                // 「管理者」の場合は[削除]権限なしエラー
                this.addActionMessage(getMessage("EN0059", new String[] {}));
                return SUCCESS;
            case CodeControlMstListModel.ERR_NO_UPD_ROLE:
                // 「スタッフ」の場合は[照会]のみエラー
                this.addActionMessage(getMessage("EN0059", new String[] {}));
                return SUCCESS;
            case CodeControlMstListModel.ERR_ZERO_DATA:
                // 対象データZero件エラー
                this.addActionMessage(getMessage("EN0020", new String[]
                        { CodeControlMstListActionSupport.CODE_MANAG_MST_TBL_JNM, "" }));
                return SUCCESS;
            case CodeControlMstListModel.ERR_MAXOVER_DATA:
                // 対象データMax件以上エラー
                int MaxCnt = CodeControlMstListSearchService.LIST_MAX_CNT + 1;
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
            this.select_rdl = searchManagerCdKey;
        } else {
            // 一覧の選択したラジオボタンをクリアする
            this.select_rdl = NaikaraTalkConstants.BRANK;
        }

        // ドロップダウンの選択値の設定
        this.defaultCdCategory = this.cdCategory_sel;


        try {
            // 戻る用に必要な情報を格納
            this.modelToSessionCodeControlMst();

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
     * SessionCodeControlMst値をModelにセット。<br>
     * <br>
     * @throws Exception
     */
    private void SessionCodeControlMstToModelBefore() throws Exception {

        if ((SessionCodeControlMst) SessionDataUtil.getSessionData(SessionCodeControlMst.class.toString()) != null) {
            this.hasSearchFlg = ((SessionCodeControlMst) SessionDataUtil.getSessionData(SessionCodeControlMst.class
                    .toString())).getHasSearchFlg();               // 検索判断フラグ

            returnOnFlg = ((SessionCodeControlMst) SessionDataUtil.getSessionData(SessionCodeControlMst.class
                    .toString())).getReturnOnFlg();    //戻る判定Onフラグ

            if (returnOnFlg == true) {
                // 「戻る」の場合(getReturnOnFlgにtrueが設定されるのは、コード管理マスタメンテナンス登録選択Actionクラスのみ)
                String processKbn = ((SessionCodeControlMst) SessionDataUtil.getSessionData(SessionCodeControlMst.class
                        .toString())).getProcessKbn();
                String searchCdCategorySelected = ((SessionCodeControlMst) SessionDataUtil.getSessionData(SessionCodeControlMst.class
                        .toString())).getSearchCdCategorySelected();
                String searchManagerCd = ((SessionCodeControlMst) SessionDataUtil.getSessionData(SessionCodeControlMst.class
                        .toString())).getSearchManagerCd();
                String searchManagerNm = ((SessionCodeControlMst) SessionDataUtil.getSessionData(SessionCodeControlMst.class
                        .toString())).getSearchManagerNm();

                this.model.setProcessKbn(processKbn);                          //検索：処理区分
                this.model.setCdCategorySelected(searchCdCategorySelected);    //検索Key<検索ボタン押下時>：コード種別名(コード)
                this.model.setManagerCd(searchManagerCd);                      //検索Key<検索ボタン押下時>：汎用コード
                this.model.setManagerNm(searchManagerNm);                      //検索Key<検索ボタン押下時>：汎用フィールド

                // コード種別の検索ボタン押下時の選択値を隠し項目へ設定
                this.searchPressCdCategory = searchCdCategorySelected;

                // エラー発生時に、選択された値を保持するために設定
                this.defaultCdCategory = searchCdCategorySelected;

            }

        }

    }

    /**
     * SessionCodeControlMst値を画面項目にセット。<br>
     * <br>
     * @throws Exception
     */
    private void SessionCodeControlMstToModelAfter() throws Exception {

        if ((SessionCodeControlMst) SessionDataUtil.getSessionData(SessionCodeControlMst.class.toString()) != null) {
            this.hasSearchFlg = ((SessionCodeControlMst) SessionDataUtil.getSessionData(SessionCodeControlMst.class
                    .toString())).getHasSearchFlg();               // 検索判断フラグ

            returnOnFlg = ((SessionCodeControlMst) SessionDataUtil.getSessionData(SessionCodeControlMst.class
                    .toString())).getReturnOnFlg();                //戻る判定Onフラグ

            if (returnOnFlg == true) {
                // 「戻る」の場合(getReturnOnFlgにtrueが設定されるのは、コード管理マスタメンテナンス登録/選択Actionクラスのみ)
                String processKbn = ((SessionCodeControlMst) SessionDataUtil.getSessionData(SessionCodeControlMst.class
                        .toString())).getProcessKbn();             //処理区分

                String cdCategory = ((SessionCodeControlMst) SessionDataUtil.getSessionData(SessionCodeControlMst.class
                        .toString())).getCdCategorySelected();     //コード種別名(コード)

                String managerCd = ((SessionCodeControlMst) SessionDataUtil.getSessionData(SessionCodeControlMst.class
                        .toString())).getManagerCd();              //汎用コード

                String managerNm = ((SessionCodeControlMst) SessionDataUtil.getSessionData(SessionCodeControlMst.class
                        .toString())).getManagerNm();              //汎用フィールド

                searchManagerCdKey = ((SessionCodeControlMst) SessionDataUtil.getSessionData(SessionCodeControlMst.class
                        .toString())).getSearchManagerCdKey();    //検索Key：選択された明細のKey-汎用コード

                this.processKbn_rdl = processKbn;        // 処理区分
                this.cdCategory_sel = cdCategory;        // 画面遷移前に保持した値：コード種別名(JSP項目名)
                this.cdCategorySelected = cdCategory;    // 画面遷移前に保持した値：コード種別名(コード)
                this.managerCd_txt = managerCd;          // 画面遷移前に保持した値：汎用コード
                this.managerNm_txt = managerNm;          // 画面遷移前に保持した値：汎用フィールド
                this.select_rdl = searchManagerCdKey;    // 画面遷移前に保持した値：選択された明細のKey-汎用コード

                this.searchPressCdCategory = cdCategory;
                this.defaultCdCategory = cdCategory;

            }

            //SessionCodeControlMstのクリア
            SessionDataUtil.clearSessionData(SessionCodeControlMst.class.toString());

        }

    }

    /**
     * Model値・SessionCodeControlMst値をSessionCodeControlMstにセット。<br>
     * <br>
     * @throws Exception
     */
    private void modelToSessionCodeControlMst() throws Exception {

        this.setHasSearchFlg(this.hasSearchFlg);

        // 戻る用に必要な情報を取得
        SessionCodeControlMst sessionData = new SessionCodeControlMst();
        sessionData.setReturnOnFlg(false);                                              // 戻る判定Onフラグ=Offとする
        sessionData.setSearchCdCategorySelected(this.model.getCdCategorySelected());    // 検索Key：コード種別名(コード)
        sessionData.setSearchManagerCd(this.model.getManagerCd());                      // 検索Key：汎用コード
        sessionData.setSearchManagerNm(this.model.getManagerNm());                      // 検索Key：汎用フィールド
        sessionData.setHasSearchFlg(this.getHasSearchFlg());                            // 検索判断フラグ
        SessionDataUtil.setSessionData(sessionData);

    }


}
