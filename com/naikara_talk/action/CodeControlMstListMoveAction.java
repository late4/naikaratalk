package com.naikara_talk.action;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dto.CodeClassMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.model.CodeControlMstListModel;
import com.naikara_talk.service.CodeControlMstListMoveService;
import com.naikara_talk.sessiondata.SessionCodeControlMst;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>コード管理マスタメンテナンス(一覧)。<br>
 * <b>クラス名称　　　:</b>コード管理マスタメンテナンス登録/選択Actionクラス。<br>
 * <b>クラス概要　　　:</b>コード管理マスタメンテナンス登録/選択Action。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>
 * 2013/07/16 TECS 新規作成。
 */
public class CodeControlMstListMoveAction extends CodeControlMstListActionSupport {

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

        // CodeControlMstListMoveServiceクラス生成
        CodeControlMstListMoveService service = new CodeControlMstListMoveService();

        // 次画面へ遷移する前のチェック
        int chkResult = service.nextPageRequest(this.model, this.getSearchPressCdCategory(), this.getSelect_rdl(), this.getHasSearchFlg());

        // コード種別の検索ボタン押下時の選択値を隠し項目へ設定
        this.searchPressCdCategory = this.cdCategory_sel;

        // エラー発生時に、選択された値を保持するために設定
        this.defaultCdCategory = this.cdCategory_sel;

        //エラーの場合、メッセージ設定
        try {

        	SessionCodeControlMst sessionData = new SessionCodeControlMst();
            sessionData = (SessionCodeControlMst)SessionDataUtil.getSessionData(SessionCodeControlMst.class.toString());
            if ( sessionData != null ) {
                // ◆エラーが発生した場合でも、一覧が表示されている状態であれば表示する
                this.hasSearchFlg = NaikaraStringUtil.nvlString(sessionData.getHasSearchFlg()).toString();            // ログイン中の顧客区分(受講者の場合のみ設定される)

                if (StringUtils.equals(NaikaraTalkConstants.FALSE, this.hasSearchFlg)) {
                    // 表示データの取得と設定
                    this.model.setResultList(service.selectList(this.model));
                }
            }

            switch (chkResult) {
            case CodeControlMstListModel.ERR_NO_SELECT:
                // 一覧の選択なしエラー
                this.addActionMessage(getMessage("EN0015", new String[] { "一覧部の左の選択" }));
                return SUCCESS;
            case CodeControlMstListModel.ERR_LIST_BACK:
                // 一覧の選択なしエラー
                this.addActionMessage(getMessage("EN0015", new String[] { "一覧部の左の選択" }));
                return SUCCESS;
            case CodeControlMstListModel.ERR_MANAGER_NO_INS:
                // 汎用コード登録不可フラグ＝”1”(登録不可)の場合
                String cdCategoryJnm = this.getSearchPressCdCategory();    // 名称が取得できない場合はコード値を設定
                cdCategoryJnm = this.setCdCategoryJnm(cdCategoryJnm, this.cdCategory_sel);
                this.addActionMessage(getMessage("EN0037", new String[] { cdCategoryJnm }));
                return SUCCESS;
            case CodeControlMstListModel.ERR_MANAGER_NO_DEL:
                // システム削除不可フラグ＝”1”(削除不可)の場合
                this.addActionMessage(getMessage("EN0022", new String[] { "システム" }));
                return SUCCESS;
            case CodeControlMstListModel.ERR_NO_DEL_ROLE:
                // 「管理者」の場合は[削除]権限なしエラー
                this.addActionMessage(getMessage("EN0059", new String[] {}));
                return SUCCESS;
            case CodeControlMstListModel.ERR_NO_UPD_ROLE:
                // 「スタッフ」の場合は[照会]のみエラー
                this.addActionMessage(getMessage("EN0059", new String[] {}));
                return SUCCESS;
            }


            // 正常の場合
            // 戻る用に必要な情報を取得/格納
            this.modelToSessionCodeControlMst();

        } catch (Exception e) {
            throw new NaiException(e);
        }

        // ヘッダの戻るリンク用
        setCurrentActionName(NaikaraTalkConstants.CODE_CONTROL_MST_LIST_SEARCH);

        // 詳細画面遷移
        return NEXTPAGE;
    }


    /**
     * 選択されているコード種別名を取得。<br>
     * <br>
     * @param cdCategoryJnm
     * @param nowCdCategorySelected
     * @return cdCategoryJnm
     * @throws Exception
     */
    private String setCdCategoryJnm(String cdCategoryJnm, String nowCdCategorySelected) throws Exception {

        if (cdCategoryJnm == null || StringUtils.isEmpty(cdCategoryJnm)) {
            // 検索ボタン押下せず、遷移する場合
            cdCategoryJnm = nowCdCategorySelected;
        }
        List<CodeClassMstDto> list = this.getCdCategoryList();
        CodeClassMstDto dto = new CodeClassMstDto();
        for(int i = 0, n = list.size(); i < n; i++) {
            dto = list.get(i);
            if (StringUtils.equals(cdCategoryJnm, dto.getCdCategory())) {
                // メッセージに表示するコード種別名の取得
                cdCategoryJnm = dto.getCdCategoryJnm();
            }
        }
        return cdCategoryJnm;
    }


    /**
     * Model値・SessionCodeControlMst値をSessionCodeControlMstに設定。<br>
     * <br>
     * @throws Exception
     */
    private void modelToSessionCodeControlMst() throws Exception {

        // 戻る用に必要な情報を格納
        SessionCodeControlMst sessionData = new SessionCodeControlMst();

        if ((SessionCodeControlMst) SessionDataUtil.getSessionData(SessionCodeControlMst.class.toString()) != null) {
            // 戻る用に必要な情報を取得
            String searchCdCategory = ((SessionCodeControlMst) SessionDataUtil.getSessionData(SessionCodeControlMst.class
                    .toString())).getSearchCdCategorySelected();
            String searchManagerCd = ((SessionCodeControlMst) SessionDataUtil.getSessionData(SessionCodeControlMst.class
                    .toString())).getSearchManagerCd();
            String searchManagerNm = ((SessionCodeControlMst) SessionDataUtil.getSessionData(SessionCodeControlMst.class
                    .toString())).getSearchManagerNm();

            sessionData.setSearchCdCategorySelected(searchCdCategory);            // <検索ボタン押下時に保持>検索Key：コード種別(選択されたコード)
            sessionData.setSearchManagerCd(searchManagerCd);                      // <検索ボタン押下時に保持>検索Key：汎用コード
            sessionData.setSearchManagerNm(searchManagerNm);                      // <検索ボタン押下時に保持>検索Key：汎用フィールド

        }

        sessionData.setReturnOnFlg(true);                                         // 画面遷移前に保持:戻る判定Onフラグ
        sessionData.setProcessKbn(this.model.getProcessKbn());                    // 画面遷移前に保持:処理区分
        sessionData.setCdCategorySelected(this.model.getCdCategorySelected());    // 画面遷移前に保持:コード種別(選択されたコード)
        sessionData.setManagerCd(this.model.getManagerCd());                      // 画面遷移前に保持:汎用コード
        sessionData.setManagerNm(this.model.getManagerNm());                      // 画面遷移前に保持:汎用フィールド
        sessionData.setSearchManagerCdKey(this.getSelect_rdl());                  // 画面遷移前に保持:選択された明細のKey-汎用コード

        this.setHasSearchFlg(NaikaraTalkConstants.TRUE);           // クリア(一覧なし)
        if (this.model.getResultList() != null ) {
            if (this.model.getResultList().size() > 0) {
                this.setHasSearchFlg(NaikaraTalkConstants.FALSE);  // 初期状態ではない(一覧あり)
            }
        }
        sessionData.setHasSearchFlg(this.getHasSearchFlg());                      // 画面遷移前に保持:画面状態フラグ

        SessionDataUtil.setSessionData(sessionData);

    }

}
