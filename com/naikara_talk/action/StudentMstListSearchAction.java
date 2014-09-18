package com.naikara_talk.action;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.service.StudentMstListSearchService;
import com.naikara_talk.sessiondata.SessionStudentListMst;
import com.naikara_talk.sessiondata.SessionStudentMst;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>マスタ保守。<br>
 * <b>クラス名称　　　:</b>受講者マスタメンテナンス【一覧】検索Actionクラス。<br>
 * <b>クラス概要　　　:</b>受講者マスタメンテナンス【一覧】検索Action。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b> 2013/07/30 TECS 新規作成。
 */
public class StudentMstListSearchAction extends StudentMstListActionSupport {

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
        StudentMstListSearchService service = new StudentMstListSearchService();

        // 戻る判定Onフラグ
        boolean returnOnFlg = false;

        if ((SessionStudentListMst) SessionDataUtil.getSessionData(SessionStudentListMst.class.toString()) != null) {

            returnOnFlg = ((SessionStudentListMst) SessionDataUtil.getSessionData(SessionStudentListMst.class
                    .toString())).getReturnOnFlg();

            // チェックエラー場合、検索判断フラグ
            this.hasSearchFlg = ((SessionStudentListMst) SessionDataUtil.getSessionData(SessionStudentListMst.class
                    .toString())).getHasSearchFlg();
        }

        if (!returnOnFlg) {

            // 検索前チェック
            int chkResult;

            try {
                chkResult = service.checkPreSelect(model);
                // エラーの場合、メッセージ設定
                switch (chkResult) {
                case StudentMstListSearchService.ERR_PROS_BTN_MISMATCH:
                    this.addActionMessage(getMessage("EN0036", new String[] { "新規", "登録／選択ボタン" }));
                    return SUCCESS;
                case StudentMstListSearchService.ERR_ZERO_DATA:
                    this.addActionMessage(getMessage("EN0020", new String[] { "受講者マスタ", "" }));
                    return SUCCESS;
                case StudentMstListSearchService.ERR_MAXOVER_DATA:
                    this.addActionMessage(getMessage("EN0023", new String[] { "1001" }));
                    return SUCCESS;
                case StudentMstListSearchService.ERR_NO_AUTH:
                    this.addActionMessage(getMessage("EN0059", new String[] { "" }));
                    return SUCCESS;
                case StudentMstListSearchService.ERR_ITEM_1:
                    this.addActionMessage(getMessage("EN0017", new String[] { "条件キー条件の１" }));
                    return SUCCESS;
                case StudentMstListSearchService.ERR_ITEM_2:
                    this.addActionMessage(getMessage("EN0017", new String[] { "条件キー条件の２" }));
                    return SUCCESS;
                case StudentMstListSearchService.ERR_ITEM_3:
                    this.addActionMessage(getMessage("EN0017", new String[] { "条件キー条件の３" }));
                    return SUCCESS;
                case StudentMstListSearchService.ERR_ITEM_4:
                    this.addActionMessage(getMessage("EN0017", new String[] { "条件キー条件の４" }));
                    return SUCCESS;
                case StudentMstListSearchService.ERR_ITEM_5:
                    this.addActionMessage(getMessage("EN0017", new String[] { "条件キー条件の５" }));
                    return SUCCESS;
                }

            } catch (Exception e) {
                throw new NaiException(e);
            }
        }

        try {

            // ヘッダ部の戻るボタン押下の場合は値の入れ替え処理
            this.SessionStudentListMstToModelBefore();

            if (!returnOnFlg || StringUtils.equals(NaikaraTalkConstants.TRUE, this.hasSearchFlg)) {

                // 検索結果
                this.model.setResultList(service.selectList(this.model));
                if (NaikaraTalkConstants.RETURN_CD_DATA_NO == this.model.getResultList().get(0).getReturnCode()) {
                    this.model.getResultList().clear();
                }
                // メッセージID：EN0015
                this.setErrorMsg_EN0015(getMessage("EN0015", new String[] { "一覧部の「Mail」", "" }));

                this.setResultList(this.model.getResultList());

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
            this.SessionStudentListMstToModelAfter();

            // 戻る用に必要な情報を格納
            this.modelToSessionStudentListMst();

        } catch (Exception e) {
            throw new NaiException(e);
        }

        // SessionStudentMstのクリア
        if ((SessionStudentMst) SessionDataUtil.getSessionData(SessionStudentMst.class.toString()) != null) {
            SessionDataUtil.clearSessionData(SessionStudentMst.class.toString());
        }

        // メッセージの設定
        if (!StringUtils.isEmpty(this.message)) {
            this.addActionMessage(this.message);
        }

        return SUCCESS;
    }

    /**
     * SessionStudentListMst値をModelにセット。<br>
     * <br>
     * @throws Exception
     */
    private void SessionStudentListMstToModelBefore() throws Exception {

        if ((SessionStudentListMst) SessionDataUtil.getSessionData(SessionStudentListMst.class.toString()) != null) {
            boolean returnOnFlg = ((SessionStudentListMst) SessionDataUtil.getSessionData(SessionStudentListMst.class
                    .toString())).getReturnOnFlg(); // 戻る判定Onフラグ

            if (returnOnFlg == true) {
                // 「戻る」の場合(getReturnOnFlgにtrueが設定されるのは、販売商品マスタメンテナンス登録選択Actionクラスのみ)
                String searchCustomerKbn_rdl = ((SessionStudentListMst) SessionDataUtil
                        .getSessionData(SessionStudentListMst.class.toString())).getSearchCustomerKbn_rdl();
                String searchItemNm1_sel = ((SessionStudentListMst) SessionDataUtil
                        .getSessionData(SessionStudentListMst.class.toString())).getSearchItemNm1_sel();
                String searchItemNm2_sel = ((SessionStudentListMst) SessionDataUtil
                        .getSessionData(SessionStudentListMst.class.toString())).getSearchItemNm2_sel();
                String searchItemNm3_sel = ((SessionStudentListMst) SessionDataUtil
                        .getSessionData(SessionStudentListMst.class.toString())).getSearchItemNm3_sel();
                String searchItemNm4_sel = ((SessionStudentListMst) SessionDataUtil
                        .getSessionData(SessionStudentListMst.class.toString())).getSearchItemNm4_sel();
                String searchItemNm5_sel = ((SessionStudentListMst) SessionDataUtil
                        .getSessionData(SessionStudentListMst.class.toString())).getSearchItemNm5_sel();
                String searchItemCondn1_rdl = ((SessionStudentListMst) SessionDataUtil
                        .getSessionData(SessionStudentListMst.class.toString())).getSearchItemCondn1_rdl();
                String searchItemCondn2_rdl = ((SessionStudentListMst) SessionDataUtil
                        .getSessionData(SessionStudentListMst.class.toString())).getSearchItemCondn2_rdl();
                String searchItemCondn3_rdl = ((SessionStudentListMst) SessionDataUtil
                        .getSessionData(SessionStudentListMst.class.toString())).getSearchItemCondn3_rdl();
                String searchItemCondn4_rdl = ((SessionStudentListMst) SessionDataUtil
                        .getSessionData(SessionStudentListMst.class.toString())).getSearchItemCondn4_rdl();
                String searchItemCondn5_rdl = ((SessionStudentListMst) SessionDataUtil
                        .getSessionData(SessionStudentListMst.class.toString())).getSearchItemCondn5_rdl();
                String searchItemFrom1_txt = ((SessionStudentListMst) SessionDataUtil
                        .getSessionData(SessionStudentListMst.class.toString())).getSearchItemFrom1_txt();
                String searchItemFrom2_txt = ((SessionStudentListMst) SessionDataUtil
                        .getSessionData(SessionStudentListMst.class.toString())).getSearchItemFrom2_txt();
                String searchItemFrom3_txt = ((SessionStudentListMst) SessionDataUtil
                        .getSessionData(SessionStudentListMst.class.toString())).getSearchItemFrom3_txt();
                String searchItemFrom4_txt = ((SessionStudentListMst) SessionDataUtil
                        .getSessionData(SessionStudentListMst.class.toString())).getSearchItemFrom4_txt();
                String searchItemFrom5_txt = ((SessionStudentListMst) SessionDataUtil
                        .getSessionData(SessionStudentListMst.class.toString())).getSearchItemFrom5_txt();
                String searchItemTo1_txt = ((SessionStudentListMst) SessionDataUtil
                        .getSessionData(SessionStudentListMst.class.toString())).getSearchItemTo1_txt();
                String searchItemTo2_txt = ((SessionStudentListMst) SessionDataUtil
                        .getSessionData(SessionStudentListMst.class.toString())).getSearchItemTo2_txt();
                String searchItemTo3_txt = ((SessionStudentListMst) SessionDataUtil
                        .getSessionData(SessionStudentListMst.class.toString())).getSearchItemTo3_txt();
                String searchItemTo4_txt = ((SessionStudentListMst) SessionDataUtil
                        .getSessionData(SessionStudentListMst.class.toString())).getSearchItemTo4_txt();
                String searchItemTo5_txt = ((SessionStudentListMst) SessionDataUtil
                        .getSessionData(SessionStudentListMst.class.toString())).getSearchItemTo5_txt();

                this.model.setCustomerKbn_rdl(searchCustomerKbn_rdl);                            // 検索Key：お客様区分
                this.model.setItemNm1_sel(searchItemNm1_sel);                                    // 検索Key：項目名1
                this.model.setItemNm2_sel(searchItemNm2_sel);                                    // 検索Key：項目名2
                this.model.setItemNm3_sel(searchItemNm3_sel);                                    // 検索Key：項目名3
                this.model.setItemNm4_sel(searchItemNm4_sel);                                    // 検索Key：項目名4
                this.model.setItemNm5_sel(searchItemNm5_sel);                                    // 検索Key：項目名5
                this.model.setItemCondn1_rdl(searchItemCondn1_rdl);                              // 検索Key：項目条件1
                this.model.setItemCondn2_rdl(searchItemCondn2_rdl);                              // 検索Key：項目条件2
                this.model.setItemCondn3_rdl(searchItemCondn3_rdl);                              // 検索Key：項目条件3
                this.model.setItemCondn4_rdl(searchItemCondn4_rdl);                              // 検索Key：項目条件4
                this.model.setItemCondn5_rdl(searchItemCondn5_rdl);                              // 検索Key：項目条件5
                this.model.setItemFrom1_txt(searchItemFrom1_txt);                                // 検索Key：項目1from
                this.model.setItemFrom2_txt(searchItemFrom2_txt);                                // 検索Key：項目2from
                this.model.setItemFrom3_txt(searchItemFrom3_txt);                                // 検索Key：項目3from
                this.model.setItemFrom4_txt(searchItemFrom4_txt);                                // 検索Key：項目4from
                this.model.setItemFrom5_txt(searchItemFrom5_txt);                                // 検索Key：項目5from
                this.model.setItemTo1_txt(searchItemTo1_txt);                                    // 検索Key：項目1to
                this.model.setItemTo2_txt(searchItemTo2_txt);                                    // 検索Key：項目2to
                this.model.setItemTo3_txt(searchItemTo3_txt);                                    // 検索Key：項目3to
                this.model.setItemTo4_txt(searchItemTo4_txt);                                    // 検索Key：項目4to
                this.model.setItemTo5_txt(searchItemTo5_txt);                                    // 検索Key：項目5to
            }
        }
    }

    /**
     * SessionStudentListMst値をModelにセット。<br>
     * <br>
     * @throws Exception
     */
    private void SessionStudentListMstToModelAfter() throws Exception {

        if ((SessionStudentListMst) SessionDataUtil.getSessionData(SessionStudentListMst.class.toString()) != null) {
            boolean returnOnFlg = ((SessionStudentListMst) SessionDataUtil.getSessionData(SessionStudentListMst.class
                    .toString())).getReturnOnFlg(); // 戻る判定Onフラグ

            if (returnOnFlg == true) {
                // 「戻る」の場合(getReturnOnFlgにtrueが設定されるのは、販売商品マスタメンテナンス登録選択Actionクラスのみ)
                String processKbn_rdl = ((SessionStudentListMst) SessionDataUtil
                        .getSessionData(SessionStudentListMst.class.toString())).getProcessKbn_rdl();
                String customerKbn_rdl = ((SessionStudentListMst) SessionDataUtil
                        .getSessionData(SessionStudentListMst.class.toString())).getCustomerKbn_rdl();
                String itemNm1_sel = ((SessionStudentListMst) SessionDataUtil
                        .getSessionData(SessionStudentListMst.class.toString())).getItemNm1_sel();
                String itemNm2_sel = ((SessionStudentListMst) SessionDataUtil
                        .getSessionData(SessionStudentListMst.class.toString())).getItemNm2_sel();
                String itemNm3_sel = ((SessionStudentListMst) SessionDataUtil
                        .getSessionData(SessionStudentListMst.class.toString())).getItemNm3_sel();
                String itemNm4_sel = ((SessionStudentListMst) SessionDataUtil
                        .getSessionData(SessionStudentListMst.class.toString())).getItemNm4_sel();
                String itemNm5_sel = ((SessionStudentListMst) SessionDataUtil
                        .getSessionData(SessionStudentListMst.class.toString())).getItemNm5_sel();
                String itemCondn1_rdl = ((SessionStudentListMst) SessionDataUtil
                        .getSessionData(SessionStudentListMst.class.toString())).getItemCondn1_rdl();
                String itemCondn2_rdl = ((SessionStudentListMst) SessionDataUtil
                        .getSessionData(SessionStudentListMst.class.toString())).getItemCondn2_rdl();
                String itemCondn3_rdl = ((SessionStudentListMst) SessionDataUtil
                        .getSessionData(SessionStudentListMst.class.toString())).getItemCondn3_rdl();
                String itemCondn4_rdl = ((SessionStudentListMst) SessionDataUtil
                        .getSessionData(SessionStudentListMst.class.toString())).getItemCondn4_rdl();
                String itemCondn5_rdl = ((SessionStudentListMst) SessionDataUtil
                        .getSessionData(SessionStudentListMst.class.toString())).getItemCondn5_rdl();
                String itemFrom1_txt = ((SessionStudentListMst) SessionDataUtil
                        .getSessionData(SessionStudentListMst.class.toString())).getItemFrom1_txt();
                String itemFrom2_txt = ((SessionStudentListMst) SessionDataUtil
                        .getSessionData(SessionStudentListMst.class.toString())).getItemFrom2_txt();
                String itemFrom3_txt = ((SessionStudentListMst) SessionDataUtil
                        .getSessionData(SessionStudentListMst.class.toString())).getItemFrom3_txt();
                String itemFrom4_txt = ((SessionStudentListMst) SessionDataUtil
                        .getSessionData(SessionStudentListMst.class.toString())).getItemFrom4_txt();
                String itemFrom5_txt = ((SessionStudentListMst) SessionDataUtil
                        .getSessionData(SessionStudentListMst.class.toString())).getItemFrom5_txt();
                String itemTo1_txt = ((SessionStudentListMst) SessionDataUtil
                        .getSessionData(SessionStudentListMst.class.toString())).getItemTo1_txt();
                String itemTo2_txt = ((SessionStudentListMst) SessionDataUtil
                        .getSessionData(SessionStudentListMst.class.toString())).getItemTo2_txt();
                String itemTo3_txt = ((SessionStudentListMst) SessionDataUtil
                        .getSessionData(SessionStudentListMst.class.toString())).getItemTo3_txt();
                String itemTo4_txt = ((SessionStudentListMst) SessionDataUtil
                        .getSessionData(SessionStudentListMst.class.toString())).getItemTo4_txt();
                String itemTo5_txt = ((SessionStudentListMst) SessionDataUtil
                        .getSessionData(SessionStudentListMst.class.toString())).getItemTo5_txt();
                String select_rdl = ((SessionStudentListMst) SessionDataUtil.getSessionData(SessionStudentListMst.class
                        .toString())).getSelect_rdl();

                this.processKbn_rdl = processKbn_rdl;                              // 検索Key：処理区分
                this.customerKbn_rdl = customerKbn_rdl;                            // 検索Key：お客様区分
                this.itemNm1_sel = itemNm1_sel;                                    // 検索Key：項目名1
                this.itemNm2_sel = itemNm2_sel;                                    // 検索Key：項目名2
                this.itemNm3_sel = itemNm3_sel;                                    // 検索Key：項目名3
                this.itemNm4_sel = itemNm4_sel;                                    // 検索Key：項目名4
                this.itemNm5_sel = itemNm5_sel;                                    // 検索Key：項目名5
                this.itemCondn1_rdl = itemCondn1_rdl;                              // 検索Key：項目条件1
                this.itemCondn2_rdl = itemCondn2_rdl;                              // 検索Key：項目条件2
                this.itemCondn3_rdl = itemCondn3_rdl;                              // 検索Key：項目条件3
                this.itemCondn4_rdl = itemCondn4_rdl;                              // 検索Key：項目条件4
                this.itemCondn5_rdl = itemCondn5_rdl;                              // 検索Key：項目条件5
                this.itemFrom1_txt = itemFrom1_txt;                                // 検索Key：項目1from
                this.itemFrom2_txt = itemFrom2_txt;                                // 検索Key：項目2from
                this.itemFrom3_txt = itemFrom3_txt;                                // 検索Key：項目3from
                this.itemFrom4_txt = itemFrom4_txt;                                // 検索Key：項目4from
                this.itemFrom5_txt = itemFrom5_txt;                                // 検索Key：項目5from
                this.itemTo1_txt = itemTo1_txt;                                    // 検索Key：項目1to
                this.itemTo2_txt = itemTo2_txt;                                    // 検索Key：項目2to
                this.itemTo3_txt = itemTo3_txt;                                    // 検索Key：項目3to
                this.itemTo4_txt = itemTo4_txt;                                    // 検索Key：項目4to
                this.itemTo5_txt = itemTo5_txt;                                    // 検索Key：項目5to
                this.select_rdl = select_rdl;                                      // 検索Key：選択された明細のKey
            }

            // SessionStudentListMstのクリア
            SessionDataUtil.clearSessionData(SessionStudentListMst.class.toString());

        }

    }

    /**
     * Model値・SessionStudentListMst値をSessionStudentListMstにセット。<br>
     * <br>
     * @throws Exception
     */
    private void modelToSessionStudentListMst() throws Exception {

        SessionStudentListMst sessionData = new SessionStudentListMst();                             // 戻る用に必要な情報を取得
        sessionData.setReturnOnFlg(false);                                                           // 戻る判定Onフラグ=Offとする
        sessionData.setSearchProcessKbn_rdl(this.model.getProcessKbn_rdl());                         // 検索Key：処理区分
        sessionData.setSearchCustomerKbn_rdl(this.model.getCustomerKbn_rdl());                       // 検索Key：お客様区分
        sessionData.setSearchItemNm1_sel(this.model.getItemNm1_sel());                               // 検索Key：項目名1
        sessionData.setSearchItemNm2_sel(this.model.getItemNm2_sel());                               // 検索Key：項目名2
        sessionData.setSearchItemNm3_sel(this.model.getItemNm3_sel());                               // 検索Key：項目名3
        sessionData.setSearchItemNm4_sel(this.model.getItemNm4_sel());                               // 検索Key：項目名4
        sessionData.setSearchItemNm5_sel(this.model.getItemNm5_sel());                               // 検索Key：項目名5
        sessionData.setSearchItemCondn1_rdl(this.model.getItemCondn1_rdl());                         // 検索Key：項目条件1
        sessionData.setSearchItemCondn2_rdl(this.model.getItemCondn2_rdl());                         // 検索Key：項目条件2
        sessionData.setSearchItemCondn3_rdl(this.model.getItemCondn3_rdl());                         // 検索Key：項目条件3
        sessionData.setSearchItemCondn4_rdl(this.model.getItemCondn4_rdl());                         // 検索Key：項目条件4
        sessionData.setSearchItemCondn5_rdl(this.model.getItemCondn5_rdl());                         // 検索Key：項目条件5
        sessionData.setSearchItemFrom1_txt(this.model.getItemFrom1_txt());                           // 検索Key：項目1from
        sessionData.setSearchItemFrom2_txt(this.model.getItemFrom2_txt());                           // 検索Key：項目2from
        sessionData.setSearchItemFrom3_txt(this.model.getItemFrom3_txt());                           // 検索Key：項目3from
        sessionData.setSearchItemFrom4_txt(this.model.getItemFrom4_txt());                           // 検索Key：項目4from
        sessionData.setSearchItemFrom5_txt(this.model.getItemFrom5_txt());                           // 検索Key：項目5from
        sessionData.setSearchItemTo1_txt(this.model.getItemTo1_txt());                               // 検索Key：項目1to
        sessionData.setSearchItemTo2_txt(this.model.getItemTo2_txt());                               // 検索Key：項目2to
        sessionData.setSearchItemTo3_txt(this.model.getItemTo3_txt());                               // 検索Key：項目3to
        sessionData.setSearchItemTo4_txt(this.model.getItemTo4_txt());                               // 検索Key：項目4to
        sessionData.setSearchItemTo5_txt(this.model.getItemTo5_txt());                               // 検索Key：項目5to

        SessionDataUtil.setSessionData(sessionData);
    }
}
