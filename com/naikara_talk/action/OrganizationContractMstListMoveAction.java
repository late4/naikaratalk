package com.naikara_talk.action;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.model.OrganizationContractMstListModel;
import com.naikara_talk.service.OrganizationContractMstListSearchService;
import com.naikara_talk.sessiondata.SessionOrganizationContractMstList;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>組織契約情報登録一覧。<br>
 * <b>クラス名称       :</b>組織契約情報登録一覧遷移Actionクラス。<br>
 * <b>クラス概要       :</b>組織契約情報登録一覧遷移を行う<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/08/01 TECS 新規作成
 */
public class OrganizationContractMstListMoveAction extends OrganizationContractMstListActionSupport {

    private static final long serialVersionUID = 1L;

    /**
     * 登録/選択ボタンの処理。<br>
     * <br>
     * @param なし
     * @return String NEXTPAGE
     * @throws NaiException
     */
    public String requestService() throws NaiException {

        // 開始ログ
        log.info(NaikaraStringUtil.unionProcesslog("Start"));

        // エラーの場合、メッセージ設定
        try {
            if (!this.processKbn_rdl.equals(OrganizationContractMstListModel.PROS_KBN_ADD)) {
                if (this.select_rdl == null) {

                    this.addActionMessage(getMessage("EN0015", new String[] { "一覧部の「選択」" }));

                    if (StringUtils.equals(NaikaraTalkConstants.TRUE, this.hasSearchFlg)) {
                        // 画面のデータをmodelにセットする
                        setupModel();

                        // 表示データを取得する
                        OrganizationContractMstListSearchService service = new OrganizationContractMstListSearchService();

                        // ヘッダ部の戻るボタン押下の場合は値の入れ替え処理
                        try {

                            // 表示データの取得
                            this.model = service.select(this.model);
                            this.hasSearchFlg = Boolean.toString(Boolean.TRUE);

                        } catch (Exception e1) {
                            throw new NaiException(e1);
                        }
                    }
                    return SUCCESS;
                }
            }

            // 戻る用に必要な情報を取得/格納
            this.modelToSessionOrganizationContractMstList();

        } catch (Exception e) {
            throw new NaiException(e);
        }

        // ヘッダの戻るリンク用
        setCurrentActionName(NaikaraTalkConstants.ORGANIZATION_CONTRACTMST_LIST_SEARCH);

        // 詳細画面遷移
        return NEXTPAGE;
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

        // 戻る用に必要な情報を格納
        SessionOrganizationContractMstList sessionData = new SessionOrganizationContractMstList();

        if ((SessionOrganizationContractMstList) SessionDataUtil
                .getSessionData(SessionOrganizationContractMstList.class.toString()) != null) {
            // 戻る用に必要な情報を取得
            String searchOrganizationId = ((SessionOrganizationContractMstList) SessionDataUtil
                    .getSessionData(SessionOrganizationContractMstList.class.toString())).getSearchOrganizationId();
            String searchOrganizationJnm = ((SessionOrganizationContractMstList) SessionDataUtil
                    .getSessionData(SessionOrganizationContractMstList.class.toString())).getSearchOrganizationJnm();
            String searchProcessKbn = ((SessionOrganizationContractMstList) SessionDataUtil
                    .getSessionData(SessionOrganizationContractMstList.class.toString())).getSearchProcessKbn();

            // 検索Key：組織ID
            sessionData.setSearchOrganizationId(searchOrganizationId);
            // 検索Key：組織名
            sessionData.setSearchOrganizationJnm(searchOrganizationJnm);
            // 検索Key：処理区分
            sessionData.setSearchProcessKbn(searchProcessKbn);
        }

        // 戻る判定Onフラグ
        sessionData.setReturnOnFlg(true);
        // 処理区分
        sessionData.setProcessKbn(this.processKbn_rdl);
        // 組織ID
        sessionData.setOrganizationId(this.organizationId_txt);
        // 組織名
        sessionData.setOrganizationJnm(this.organizationJnm_txt);
        // 検索Key：選択された明細のKey-商組織ID
        sessionData.setSearchOrganizationIdKey(this.getSelect_rdl());
        // 検索判断フラグ
        sessionData.setHasSearchFlg(this.getHasSearchFlg());
        SessionDataUtil.setSessionData(sessionData);

    }
}
