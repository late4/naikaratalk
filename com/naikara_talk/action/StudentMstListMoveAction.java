package com.naikara_talk.action;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.service.StudentMstListMoveService;
import com.naikara_talk.service.StudentMstListSearchService;
import com.naikara_talk.sessiondata.SessionStudentListMst;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>マスタ保守<br>
 * <b>クラス名称　　　:</b>受講者マスタメンテナンスス【一覧】登録選択Actionクラス。<br>
 * <b>クラス概要　　　:</b>受講者マスタメンテナンスス【一覧】登録選択Action。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b> 2013/07/09 TECS 新規作成。
 */
public class StudentMstListMoveAction extends StudentMstListActionSupport {

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
        StudentMstListMoveService service = new StudentMstListMoveService();

        int chkResult = service.nextPageRequest(this.model, this.getSelect_rdl(), this.getHasSearchFlg());

        // エラーの場合、メッセージ設定
        try {
            switch (chkResult) {
            case StudentMstListMoveService.ERR_NO_SELECT:

                this.addActionMessage(getMessage("EN0015", new String[] { "一覧部の左の選択" }));
                StudentMstListSearchService searchService = new StudentMstListSearchService();

                // 検索前チェック
                chkResult = searchService.checkPreSelect(model);

                // エラーの場合、メッセージ設定
                switch (chkResult) {
                case StudentMstListSearchService.ERR_PROS_BTN_MISMATCH:
                    return SUCCESS;
                case StudentMstListSearchService.ERR_ZERO_DATA:
                    return SUCCESS;
                case StudentMstListSearchService.ERR_MAXOVER_DATA:
                    return SUCCESS;
                }
                // 一覧データを取得
                this.reSearchList();

                return SUCCESS;

            case StudentMstListMoveService.ERR_LIST_BACK:
                this.addActionMessage(getMessage("EN0015", new String[] { "一覧部の左の選択" }));
                return SUCCESS;

            case StudentMstListMoveService.ERR_NO_AUTH:
                this.addActionMessage(getMessage("EN0059", new String[] { "" }));
                // 一覧データを取得
                if (StringUtils.equals(this.getHasSearchFlg(), NaikaraTalkConstants.TRUE)) {
                    this.reSearchList();
                }
                return SUCCESS;
            }

            // 戻る用に必要な情報を取得/格納
            this.modelToSessionStudentListMst();

        } catch (Exception e) {
            throw new NaiException(e);
        }

        // ヘッダの戻るリンク用
        setCurrentActionName(NaikaraTalkConstants.STUDENT_MST_LIST_SEARCH);

        // 詳細画面遷移
        return NEXTPAGE;
    }

    /**
     * Model値・SessionSaleGoodsMst値をSessionSaleGoodsMstにセット。<br>
     * <br>
     * @throws Exception
     */
    private void modelToSessionStudentListMst() throws Exception {

        // 戻る用に必要な情報を格納
        SessionStudentListMst sessionData = new SessionStudentListMst();

        if ((SessionStudentListMst) SessionDataUtil.getSessionData(SessionStudentListMst.class.toString()) != null) {
            // 戻る用に必要な情報を取得

            String searchCustomerKbn = ((SessionStudentListMst) SessionDataUtil
                    .getSessionData(SessionStudentListMst.class.toString())).getSearchCustomerKbn_rdl();
            String searchItemNm1 = ((SessionStudentListMst) SessionDataUtil.getSessionData(SessionStudentListMst.class
                    .toString())).getSearchItemNm1_sel();
            String searchItemNm2 = ((SessionStudentListMst) SessionDataUtil.getSessionData(SessionStudentListMst.class
                    .toString())).getSearchItemNm2_sel();
            String searchItemNm3 = ((SessionStudentListMst) SessionDataUtil.getSessionData(SessionStudentListMst.class
                    .toString())).getSearchItemNm3_sel();
            String searchItemNm4 = ((SessionStudentListMst) SessionDataUtil.getSessionData(SessionStudentListMst.class
                    .toString())).getSearchItemNm4_sel();
            String searchItemNm5 = ((SessionStudentListMst) SessionDataUtil.getSessionData(SessionStudentListMst.class
                    .toString())).getSearchItemNm5_sel();
            String searchItemCondn1 = ((SessionStudentListMst) SessionDataUtil
                    .getSessionData(SessionStudentListMst.class.toString())).getSearchItemCondn1_rdl();
            String searchItemCondn2 = ((SessionStudentListMst) SessionDataUtil
                    .getSessionData(SessionStudentListMst.class.toString())).getSearchItemCondn2_rdl();
            String searchItemCondn3 = ((SessionStudentListMst) SessionDataUtil
                    .getSessionData(SessionStudentListMst.class.toString())).getSearchItemCondn3_rdl();
            String searchItemCondn4 = ((SessionStudentListMst) SessionDataUtil
                    .getSessionData(SessionStudentListMst.class.toString())).getSearchItemCondn4_rdl();
            String searchItemCondn5 = ((SessionStudentListMst) SessionDataUtil
                    .getSessionData(SessionStudentListMst.class.toString())).getSearchItemCondn5_rdl();
            String searchItemFrom1 = ((SessionStudentListMst) SessionDataUtil
                    .getSessionData(SessionStudentListMst.class.toString())).getSearchItemFrom1_txt();
            String searchItemFrom2 = ((SessionStudentListMst) SessionDataUtil
                    .getSessionData(SessionStudentListMst.class.toString())).getSearchItemFrom2_txt();
            String searchItemFrom3 = ((SessionStudentListMst) SessionDataUtil
                    .getSessionData(SessionStudentListMst.class.toString())).getSearchItemFrom3_txt();
            String searchItemFrom4 = ((SessionStudentListMst) SessionDataUtil
                    .getSessionData(SessionStudentListMst.class.toString())).getSearchItemFrom4_txt();
            String searchItemFrom5 = ((SessionStudentListMst) SessionDataUtil
                    .getSessionData(SessionStudentListMst.class.toString())).getSearchItemFrom5_txt();
            String searchItemTo1 = ((SessionStudentListMst) SessionDataUtil.getSessionData(SessionStudentListMst.class
                    .toString())).getSearchItemTo1_txt();
            String searchItemTo2 = ((SessionStudentListMst) SessionDataUtil.getSessionData(SessionStudentListMst.class
                    .toString())).getSearchItemTo2_txt();
            String searchItemTo3 = ((SessionStudentListMst) SessionDataUtil.getSessionData(SessionStudentListMst.class
                    .toString())).getSearchItemTo3_txt();
            String searchItemTo4 = ((SessionStudentListMst) SessionDataUtil.getSessionData(SessionStudentListMst.class
                    .toString())).getSearchItemTo4_txt();
            String searchItemTo5 = ((SessionStudentListMst) SessionDataUtil.getSessionData(SessionStudentListMst.class
                    .toString())).getSearchItemTo5_txt();

            sessionData.setSearchCustomerKbn_rdl(searchCustomerKbn);                              // 検索Key：お客様区分
            sessionData.setSearchItemNm1_sel(searchItemNm1);                                      // 検索key：項目名1
            sessionData.setSearchItemNm2_sel(searchItemNm2);                                      // 検索key：項目名2
            sessionData.setSearchItemNm3_sel(searchItemNm3);                                      // 検索key：項目名3
            sessionData.setSearchItemNm4_sel(searchItemNm4);                                      // 検索key：項目名4
            sessionData.setSearchItemNm5_sel(searchItemNm5);                                      // 検索key：項目名5
            sessionData.setSearchItemCondn1_rdl(searchItemCondn1);                                // 検索key：項目key1
            sessionData.setSearchItemCondn2_rdl(searchItemCondn2);                                // 検索key：項目key2
            sessionData.setSearchItemCondn3_rdl(searchItemCondn3);                                // 検索key：項目key3
            sessionData.setSearchItemCondn4_rdl(searchItemCondn4);                                // 検索key：項目key4
            sessionData.setSearchItemCondn5_rdl(searchItemCondn5);                                // 検索key：項目key5
            sessionData.setSearchItemFrom1_txt(searchItemFrom1);                                  // 検索key：項目1from
            sessionData.setSearchItemFrom2_txt(searchItemFrom2);                                  // 検索key：項目2from
            sessionData.setSearchItemFrom3_txt(searchItemFrom3);                                  // 検索key：項目3from
            sessionData.setSearchItemFrom4_txt(searchItemFrom4);                                  // 検索key：項目4from
            sessionData.setSearchItemFrom5_txt(searchItemFrom5);                                  // 検索key：項目5from
            sessionData.setSearchItemTo1_txt(searchItemTo1);                                      // 検索key：項目1to
            sessionData.setSearchItemTo2_txt(searchItemTo2);                                      // 検索key：項目2to
            sessionData.setSearchItemTo3_txt(searchItemTo3);                                      // 検索key：項目3to
            sessionData.setSearchItemTo4_txt(searchItemTo4);                                      // 検索key：項目4to
            sessionData.setSearchItemTo5_txt(searchItemTo5);                                      // 検索key：項目5to
        }

        sessionData.setReturnOnFlg(true);                                                         // 戻る判定Onフラグ
        sessionData.setProcessKbn_rdl(this.model.getProcessKbn_rdl());                            // 処理区分
        sessionData.setCustomerKbn_rdl(this.model.getCustomerKbn_rdl());                          // 検索Key：お客様区分
        sessionData.setItemNm1_sel(this.model.getItemNm1_sel());                                  // 検索key：項目名1
        sessionData.setItemNm2_sel(this.model.getItemNm2_sel());                                  // 検索key：項目名2
        sessionData.setItemNm3_sel(this.model.getItemNm3_sel());                                  // 検索key：項目名3
        sessionData.setItemNm4_sel(this.model.getItemNm4_sel());                                  // 検索key：項目名4
        sessionData.setItemNm5_sel(this.model.getItemNm5_sel());                                  // 検索key：項目名5
        sessionData.setItemCondn1_rdl(this.model.getItemCondn1_rdl());                            // 検索key：項目key1
        sessionData.setItemCondn2_rdl(this.model.getItemCondn2_rdl());                            // 検索key：項目key2
        sessionData.setItemCondn3_rdl(this.model.getItemCondn3_rdl());                            // 検索key：項目key3
        sessionData.setItemCondn4_rdl(this.model.getItemCondn4_rdl());                            // 検索key：項目key4
        sessionData.setItemCondn5_rdl(this.model.getItemCondn5_rdl());                            // 検索key：項目key5
        sessionData.setItemFrom1_txt(this.model.getItemFrom1_txt());                              // 検索key：項目1from
        sessionData.setItemFrom2_txt(this.model.getItemFrom2_txt());                              // 検索key：項目2from
        sessionData.setItemFrom3_txt(this.model.getItemFrom3_txt());                              // 検索key：項目3from
        sessionData.setItemFrom4_txt(this.model.getItemFrom4_txt());                              // 検索key：項目4from
        sessionData.setItemFrom5_txt(this.model.getItemFrom5_txt());                              // 検索key：項目5from
        sessionData.setItemTo1_txt(this.model.getItemTo1_txt());                                  // 検索key：項目1to
        sessionData.setItemTo2_txt(this.model.getItemTo2_txt());                                  // 検索key：項目2to
        sessionData.setItemTo3_txt(this.model.getItemTo3_txt());                                  // 検索key：項目3to
        sessionData.setItemTo4_txt(this.model.getItemTo4_txt());                                  // 検索key：項目4to
        sessionData.setItemTo5_txt(this.model.getItemTo5_txt());                                  // 検索key：項目5to
        sessionData.setSelect_rdl(this.getSelect_rdl());                                          // 検索Key：選択された明細のKey
        sessionData.setHasSearchFlg(this.getHasSearchFlg());                                      // 検索判断フラグ
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

        // Serviceクラス生成
        StudentMstListSearchService searchService = new StudentMstListSearchService();

        // 表示データの取得
        this.model.setResultList(searchService.selectList(this.model));
        // 選択したラジオボタンをクリアする
        this.select_rdl = NaikaraTalkConstants.BRANK;
        this.hasSearchFlg = NaikaraTalkConstants.TRUE;

    }
}
