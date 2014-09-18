package com.naikara_talk.action;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.service.OrganizationContractMstListSearchService;
import com.naikara_talk.sessiondata.SessionOrganizationContractMstList;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>組織契約情報登録一覧。<br>
 * <b>クラス名称       :</b>組織契約情報登録一覧検索処理Actionクラス。<br>
 * <b>クラス概要       :</b>組織契約情報登録一覧の情報照会ができる<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/08/01 TECS 新規作成
 */
public class OrganizationContractMstListSearchAction extends OrganizationContractMstListActionSupport {

    private static final long serialVersionUID = 1L;

    /**
     * 画面の初期表示。<br>
     * <br>
     *
     * @param なし<br>
     * @return String SUCCESS <br>
     * @exception NaiException
     */
    public String requestService() throws NaiException {

        // 開始ログ
        log.info(NaikaraStringUtil.unionProcesslog("Start"));

        // 表示データを取得する
        OrganizationContractMstListSearchService service = new OrganizationContractMstListSearchService();

        // 戻る判定Onフラグ
        boolean returnOnFlg = false;

        if ((SessionOrganizationContractMstList) SessionDataUtil
                .getSessionData(SessionOrganizationContractMstList.class.toString()) != null) {

            // 戻る判定Onフラグ
            returnOnFlg = ((SessionOrganizationContractMstList) SessionDataUtil
                    .getSessionData(SessionOrganizationContractMstList.class.toString())).getReturnOnFlg();

            // 検索判断フラグ
            this.hasSearchFlg = ((SessionOrganizationContractMstList) SessionDataUtil
                    .getSessionData(SessionOrganizationContractMstList.class.toString())).getHasSearchFlg();
        }

        // 画面のデータをmodelにセットする
        setupModel();

        // 検索前チェック
        if (!returnOnFlg) {
            int chkResult;
            try {
                chkResult = service.errorCheck(model);
                // エラーの場合、メッセージ設定
                switch (chkResult) {
                case OrganizationContractMstListSearchService.ERR_PROCESS_KBN:
                    this.addActionMessage(getMessage("EN0036", new String[] { "新規", "登録／選択ボタン" }));
                    return SUCCESS;
                case OrganizationContractMstListSearchService.ERR_GET_DATA_ZERO:
                    this.addActionMessage(getMessage("EN0020", new String[] { "組織マスタ", "" }));
                    return SUCCESS;
                }

                if (chkResult > OrganizationContractMstListSearchService.ERR_GET_DATE_HUNDRED) {
                    this.addActionMessage(getMessage("EN0023", new String[] { "201" }));
                    return SUCCESS;
                }
            } catch (Exception e) {
                throw new NaiException(e);
            }
        }

        try {

            // ヘッダ部の戻るボタン押下の場合は値の入れ替え処理
            this.SessionOrganizationContractMstListToModelBefore();

            if (!returnOnFlg || StringUtils.equals(NaikaraTalkConstants.TRUE, this.hasSearchFlg)) {
                // 表示データの取得
                this.model = service.select(this.model);
                this.hasSearchFlg = Boolean.toString(Boolean.TRUE);
            }

            // 選択したラジオボタンをクリアする
            this.select_rdl = NaikaraTalkConstants.BRANK;

            // ヘッダ部の戻るボタン押下の場合は値の入れ替え処理
            this.SessionOrganizationContractMstListToModelAfter();

            // 戻る用に必要な情報を格納
            this.modelToSessionOrganizationContractMstList();

        } catch (Exception e1) {
            throw new NaiException(e1);
        }

        // メッセージの設定
        if (!StringUtils.isEmpty(this.message)) {
            this.addActionMessage(this.message);
        }

        return SUCCESS;
    }

    /**
     * Model値 To Session<br>
     * <br>
     * Model値・SessionOrganizationContractMstList値をSessionOrganizationContractMstListにセット。<br>
     * <br>
     * @param なし<br>
     * @return なし<br>
     * @exception Exception
     */
    private void modelToSessionOrganizationContractMstList() throws Exception {

        // 戻る用に必要な情報を取得
        SessionOrganizationContractMstList sessionData = new SessionOrganizationContractMstList();
        // 戻る判定Onフラグ=Offとする
        sessionData.setReturnOnFlg(false);
        // 検索Key：処理区分
        sessionData.setSearchProcessKbn(this.model.getProcessKbn_rdl());
        // 検索Key：組織ID
        sessionData.setSearchOrganizationId(this.model.getOrganizationId());
        // 検索Key：組織名
        sessionData.setSearchOrganizationJnm(this.model.getOrganizationJnm());
        SessionDataUtil.setSessionData(sessionData);

    }

    /**
     * 検索する前 Session値 To Model<br>
     * <br>
     * SessionOrganizationContractMstList値をModelにセット。<br>
     * <br>
     * @param なし<br>
     * @return なし<br>
     * @exception Exception
     */
    private void SessionOrganizationContractMstListToModelBefore() throws Exception {

        if ((SessionOrganizationContractMstList) SessionDataUtil
                .getSessionData(SessionOrganizationContractMstList.class.toString()) != null) {

            boolean returnOnFlg = ((SessionOrganizationContractMstList) SessionDataUtil
                    .getSessionData(SessionOrganizationContractMstList.class.toString())).getReturnOnFlg(); // 戻る判定Onフラグ

            if (returnOnFlg) {
                // 「戻る」の場合(getReturnOnFlgにtrueが設定されるのは、ポイント管理マスタメンテナンス登録選択Actionクラスのみ)
                String searchOrganizationId = ((SessionOrganizationContractMstList) SessionDataUtil
                        .getSessionData(SessionOrganizationContractMstList.class.toString())).getSearchOrganizationId();
                String searchOrganizationJnm = ((SessionOrganizationContractMstList) SessionDataUtil
                        .getSessionData(SessionOrganizationContractMstList.class.toString()))
                        .getSearchOrganizationJnm();
                String searchProcessKbn = ((SessionOrganizationContractMstList) SessionDataUtil
                        .getSessionData(SessionOrganizationContractMstList.class.toString())).getSearchProcessKbn();

                // 検索Key：処理区分
                this.model.setProcessKbn_rdl(searchProcessKbn);
                // 検索Key：組織ID
                this.model.setOrganizationId(searchOrganizationId);
                // 検索Key：組織名
                this.model.setOrganizationJnm(searchOrganizationJnm);
            }
        }

    }

    /**
     * 検索の後 Session値 To Model<br>
     * <br>
     * SessionOrganizationContractMstList値をModelにセット。<br>
     * <br>
     * @param なし<br>
     * @return なし<br>
     * @exception Exception
     */
    private void SessionOrganizationContractMstListToModelAfter() throws Exception {

        if ((SessionOrganizationContractMstList) SessionDataUtil
                .getSessionData(SessionOrganizationContractMstList.class.toString()) != null) {
            // 戻る判定Onフラグ
            boolean returnOnFlg = ((SessionOrganizationContractMstList) SessionDataUtil
                    .getSessionData(SessionOrganizationContractMstList.class.toString())).getReturnOnFlg();

            if (returnOnFlg) {
                // 「戻る」の場合(getReturnOnFlgにtrueが設定されるのは、ポイント管理マスタメンテナンス登録選択Actionクラスのみ)
                // 処理区分
                String processKbn = ((SessionOrganizationContractMstList) SessionDataUtil
                        .getSessionData(SessionOrganizationContractMstList.class.toString())).getProcessKbn();

                // 組織ID
                String organizationId = ((SessionOrganizationContractMstList) SessionDataUtil
                        .getSessionData(SessionOrganizationContractMstList.class.toString())).getOrganizationId();

                // 組織名
                String organizationJnm = ((SessionOrganizationContractMstList) SessionDataUtil
                        .getSessionData(SessionOrganizationContractMstList.class.toString())).getOrganizationJnm();

                // 検索Key：選択された明細のKey-組織ID
                String select_rdl = ((SessionOrganizationContractMstList) SessionDataUtil
                        .getSessionData(SessionOrganizationContractMstList.class.toString()))
                        .getSearchOrganizationIdKey();

                // 処理区分
                this.processKbn_rdl = processKbn;
                // 検索Key：組織ID
                this.organizationId_txt = organizationId;
                // 検索Key：組織名
                this.organizationJnm_txt = organizationJnm;
                this.select_rdl = select_rdl;

            }

            // sessionSessionOrganizationContractMstListのクリア
            SessionDataUtil.clearSessionData(SessionOrganizationContractMstList.class.toString());

        }
    }
}
