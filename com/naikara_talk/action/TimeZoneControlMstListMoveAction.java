package com.naikara_talk.action;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.service.TimeZoneControlMstListMoveService;
import com.naikara_talk.service.TimeZoneControlMstListSearchService;
import com.naikara_talk.sessiondata.SessionTimeManagMst;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>マスタ保守<br>
 * <b>クラス名称　　　:</b>時差管理マスタメンテナンス【一覧】登録選択Actionクラス。<br>
 * <b>クラス概要　　　:</b>時差管理マスタメンテナンス【一覧】登録選択Action。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b> 2013/07/09 TECS 新規作成。
 */
public class TimeZoneControlMstListMoveAction extends TimeZoneControlMstListActionSupport {

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

        // Modelクラス設定
        setupModel();

        // Serviceクラス生成
        TimeZoneControlMstListMoveService service = new TimeZoneControlMstListMoveService();

        int chkResult = service.nextPageRequest(this.model, this.getSelect_rdl(), this.getHasSearchFlg());

        // エラーの場合、メッセージ設定
        try {
            switch (chkResult) {
            case TimeZoneControlMstListMoveService.ERR_NO_SELECT:

                this.addActionMessage(getMessage("EN0015", new String[] { "一覧部の左の" + "選択" }));
                TimeZoneControlMstListSearchService searchService = new TimeZoneControlMstListSearchService();

                // 検索前チェック
                chkResult = searchService.checkPreSelect(model);

                // エラーの場合、メッセージ設定
                switch (chkResult) {
                case TimeZoneControlMstListSearchService.ERR_PROS_BTN_MISMATCH:
                    return SUCCESS;
                case TimeZoneControlMstListSearchService.ERR_ZERO_DATA:
                    return SUCCESS;
                case TimeZoneControlMstListSearchService.ERR_MAXOVER_DATA:
                    return SUCCESS;
                }
                // 一覧データを取得
                this.reSearchList();

                return SUCCESS;

            case TimeZoneControlMstListMoveService.ERR_LIST_BACK:
                this.addActionMessage(getMessage("EN0015", new String[] { "一覧部の左の" + "選択" }));
                return SUCCESS;

            case TimeZoneControlMstListMoveService.ERR_NO_AUTH:
                this.addActionMessage(getMessage("EN0059", new String[] { "" }));
                if (StringUtils.equals(this.getHasSearchFlg(), NaikaraTalkConstants.TRUE)) {
                    this.reSearchList();
                }
                return SUCCESS;
            }

            // 戻る用に必要な情報を取得/格納
            this.modelToSessionTimeManagMst();

        } catch (Exception e) {
            throw new NaiException(e);
        }

        // ヘッダの戻るリンク用
        setCurrentActionName(NaikaraTalkConstants.TIME_ZONE_CONTROL_MST_LIST_SEARCH);

        // 詳細画面遷移
        return NEXTPAGE;
    }

    /**
     * Model値・modelToSessionTimeManagMst値をmodelToSessionTimeManagMstにセット。<br>
     * <br>
     * @throws Exception
     */
    private void modelToSessionTimeManagMst() throws Exception {

        // 戻る用に必要な情報を格納
        SessionTimeManagMst sessionData = new SessionTimeManagMst();

        if ((SessionTimeManagMst) SessionDataUtil.getSessionData(SessionTimeManagMst.class.toString()) != null) {
            // 戻る用に必要な情報を取得

            String searchCountryCd = ((SessionTimeManagMst) SessionDataUtil.getSessionData(SessionTimeManagMst.class
                    .toString())).getSearchCountryCd_sel();
            String searchAreaNoCd = ((SessionTimeManagMst) SessionDataUtil.getSessionData(SessionTimeManagMst.class
                    .toString())).getSearchAreaNoCd_sel();

            sessionData.setSearchCountryCd_sel(searchCountryCd);                           // 検索Key：国コード
            sessionData.setSearchAreaNoCd_sel(searchAreaNoCd);                             // 検索Key：時差管理No
        }

        sessionData.setReturnOnFlg(true);                                                  // 戻る判定Onフラグ
        sessionData.setProcessKbn_rdl(this.model.getProcessKbn_rdl());                     // 処理区分
        sessionData.setCountryCd_sel(this.model.getCountryCd_sel());                       // 検索Key：国コード
        sessionData.setAreaNoCd_sel(this.model.getAreaNoCd_sel());                         // 検索Key：時差管理No
        sessionData.setSearchPrimaryKey(this.getSelect_rdl());                             // 検索Key：選択された明細のKey-国コード&時差管理No
        sessionData.setHasSearchFlg(this.getHasSearchFlg());                               // 検索判断フラグ
        SessionDataUtil.setSessionData(sessionData);

    }

    /**
     * 一覧データの取得処理。<br>
     * <br>
     * @param なし
     * @return なし
     * @throws NaiException
     */
    private void reSearchList() throws Exception {

        TimeZoneControlMstListSearchService searchServicel = new TimeZoneControlMstListSearchService();
        // 表示データの取得
        this.model.setResultList(searchServicel.selectList(this.model));
        // 選択したラジオボタンをクリアする
        this.select_rdl = NaikaraTalkConstants.BRANK;
        // チェックエラー場合、検索判断フラグ
        this.hasSearchFlg = NaikaraTalkConstants.TRUE;
    }

}
