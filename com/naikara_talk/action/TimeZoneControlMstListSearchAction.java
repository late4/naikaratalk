package com.naikara_talk.action;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.service.TimeZoneControlMstListSearchService;
import com.naikara_talk.sessiondata.SessionTimeManagMst;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>マスタ保守<br>
 * <b>クラス名称　　　:</b>時差管理マスタメンテナンス【一覧】検索Actionクラス。<br>
 * <b>クラス概要　　　:</b>時差管理マスタメンテナンス【一覧】検索Action。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:2013/07/09 TECS 新規作成。
 */
public class TimeZoneControlMstListSearchAction extends TimeZoneControlMstListActionSupport {

    private static final long serialVersionUID = 1L;

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

        // Modelクラス設定
        setupModel();

        // Serviceクラス生成
        TimeZoneControlMstListSearchService service = new TimeZoneControlMstListSearchService();

        // 戻る判定Onフラグ
        boolean returnOnFlg = false;

        if ((SessionTimeManagMst) SessionDataUtil.getSessionData(SessionTimeManagMst.class.toString()) != null) {

            returnOnFlg = ((SessionTimeManagMst) SessionDataUtil.getSessionData(SessionTimeManagMst.class.toString()))
                    .getReturnOnFlg();

            // チェックエラー場合、検索判断フラグ
            this.hasSearchFlg = ((SessionTimeManagMst) SessionDataUtil.getSessionData(SessionTimeManagMst.class
                    .toString())).getHasSearchFlg();
        }

        if (!returnOnFlg) {

            // 検索前チェック
            int chkResult;

            try {
                chkResult = service.checkPreSelect(model);
                // エラーの場合、メッセージ設定
                switch (chkResult) {
                case TimeZoneControlMstListSearchService.ERR_PROS_BTN_MISMATCH:
                    this.addActionMessage(getMessage("EN0036", new String[] { "新規", "登録／選択ボタン" }));
                    return SUCCESS;
                case TimeZoneControlMstListSearchService.ERR_ZERO_DATA:
                    this.addActionMessage(getMessage("EN0020", new String[] { "時差管理マスタ", "" }));
                    return SUCCESS;
                case TimeZoneControlMstListSearchService.ERR_MAXOVER_DATA:
                    this.addActionMessage(getMessage("EN0023", new String[] { "101" }));
                    return SUCCESS;
                case TimeZoneControlMstListSearchService.ERR_NO_AUTH:
                    this.addActionMessage(getMessage("EN0059", new String[] { "" }));
                    return SUCCESS;
                }

            } catch (Exception e) {
                throw new NaiException(e);
            }
        }

        try {

            // ヘッダ部の戻るボタン押下の場合は値の入れ替え処理
            this.SessionTimeZoneControlMstToModelBefore();

            if (!returnOnFlg || StringUtils.equals(NaikaraTalkConstants.TRUE, this.hasSearchFlg)) {

                // 検索結果
                this.model.setResultList(service.selectList(this.model));

                if (NaikaraTalkConstants.RETURN_CD_DATA_NO == this.model.getResultList().get(0).getReturnCode()) {
                    this.model.getResultList().clear();
                }

                // チェックエラー場合、検索判断フラグ
                this.hasSearchFlg = Boolean.toString(Boolean.TRUE);
            }

        } catch (Exception e) {

            throw new NaiException(e);

        }

        // 選択したラジオボタンをクリアする
        this.select_rdl = NaikaraTalkConstants.BRANK;

        try {
            // ヘッダ部の戻るボタン押下の場合は値の入れ替え処理
            this.SessionTimeManagMstToModelAfter();

            // 戻る用に必要な情報を格納
            this.modelToSessionTimeManagMst();

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
     * SessionTimeManagMst値をModelにセット。<br>
     * <br>
     * @throws Exception
     */
    private void SessionTimeZoneControlMstToModelBefore() throws Exception {

        if ((SessionTimeManagMst) SessionDataUtil.getSessionData(SessionTimeManagMst.class.toString()) != null) {
            boolean returnOnFlg = ((SessionTimeManagMst) SessionDataUtil.getSessionData(SessionTimeManagMst.class
                    .toString())).getReturnOnFlg();                                  // 戻る判定Onフラグ

            if (returnOnFlg == true) {
                // 「戻る」の場合(getReturnOnFlgにtrueが設定されるのは、時差管理マスタメンテナンス登録選択Actionクラスのみ)
                String searchCountryCd = ((SessionTimeManagMst) SessionDataUtil
                        .getSessionData(SessionTimeManagMst.class.toString())).getSearchCountryCd_sel();
                String searchAreaNoCd = ((SessionTimeManagMst) SessionDataUtil.getSessionData(SessionTimeManagMst.class
                        .toString())).getSearchAreaNoCd_sel();

                this.model.setCountryCd_sel(searchCountryCd);                         // 検索Key：国コード
                this.model.setAreaNoCd_sel(searchAreaNoCd);                           // 検索Key：時差地域No
            }

        }

    }

    /**
     * SessionTimeManagMst値をModelにセット。<br>
     * <br>
     * @throws Exception
     */
    private void SessionTimeManagMstToModelAfter() throws Exception {

        if ((SessionTimeManagMst) SessionDataUtil.getSessionData(SessionTimeManagMst.class.toString()) != null) {
            boolean returnOnFlg = ((SessionTimeManagMst) SessionDataUtil.getSessionData(SessionTimeManagMst.class
                    .toString())).getReturnOnFlg();                                    // 戻る判定Onフラグ

            if (returnOnFlg == true) {
                // 「戻る」の場合(getReturnOnFlgにtrueが設定されるのは、時差管理マスタメンテナンス登録選択Actionクラスのみ)
                String processKbn = ((SessionTimeManagMst) SessionDataUtil.getSessionData(SessionTimeManagMst.class
                        .toString())).getProcessKbn_rdl();                             // 処理区分
                String countryCd = ((SessionTimeManagMst) SessionDataUtil.getSessionData(SessionTimeManagMst.class
                        .toString())).getCountryCd_sel();                              // 国コード

                String areaNoNm = ((SessionTimeManagMst) SessionDataUtil.getSessionData(SessionTimeManagMst.class
                        .toString())).getAreaNoCd_sel();                               // 時差地域No

                String select_rdl = ((SessionTimeManagMst) SessionDataUtil.getSessionData(SessionTimeManagMst.class
                        .toString())).getSearchPrimaryKey();                           // 検索Key：選択された明細のKey-国コード&時差管理No

                this.processKbn_rdl = processKbn;                                      // 処理区分
                this.countryCd_sel = countryCd;                                        // 検索Key：国コード
                this.areaNoCd_sel = areaNoNm;                                          // 検索Key：時差地域No
                this.select_rdl = select_rdl;                                          // 検索Key：選択された明細のKey-国コード&時差管理No

            }

            // sessionSessionTimeManagMstのクリア
            SessionDataUtil.clearSessionData(SessionTimeManagMst.class.toString());

        }

    }

    /**
     * Model値・SessionTimeManagMst値をSessionTimeManagMstにセット。<br>
     * <br>
     * @throws Exception
     */
    private void modelToSessionTimeManagMst() throws Exception {

        // 戻る用に必要な情報を取得
        SessionTimeManagMst sessionData = new SessionTimeManagMst();
        sessionData.setReturnOnFlg(false);                                              // 戻る判定Onフラグ=Offとする
        sessionData.setSearchCountryCd_sel(this.model.getCountryCd_sel());              // 検索Key：国コード
        sessionData.setSearchAreaNoCd_sel(this.model.getAreaNoCd_sel());                // 検索Key：時差地域No

        SessionDataUtil.setSessionData(sessionData);

    }
}
