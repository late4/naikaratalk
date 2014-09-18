package com.naikara_talk.action;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.service.PointControlListMoveService;
import com.naikara_talk.service.PointControlListSearchService;
import com.naikara_talk.sessiondata.SessionPointControl;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>マスタ保守<br>
 * <b>クラス名称       :</b>ポイント管理マスタメンテナンス(一覧)<br>
 * <b>クラス概要       :</b>ポイント管理マスタメンテナンス(一覧)登録選択Action<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/01 TECS 新規作成
 */
public class PointControlListMoveAction extends PointControlListActionSupport {

    private static final long serialVersionUID = 1L;

    /**
     * 登録/選択ボタンの処理。<br>
     * <br>
     * 登録/選択ボタンの処理。<br>
     * <br>
     * @param なし<br>
     * @return String SUCCESS or NEXTPAGE<br>
     * @exception NaiException
     */
    public String requestService() throws NaiException {

        // 開始ログ
        log.info(NaikaraStringUtil.unionProcesslog("Start"));

        //Modelクラス設定
        setupModel();
        //Serviceクラス生成
        PointControlListMoveService service = new PointControlListMoveService();

        int chkResult = service.nextPageRequest(this.model, this.getSelect_rdl(), this.getHasSearchFlg());

        //エラーの場合、メッセージ設定
        try {
            switch (chkResult) {
            case PointControlListMoveService.ERR_NO_SELECT:

                this.addActionMessage(getMessage("EN0015", new String[] { "一覧部の左の" + "選択" }));

                PointControlListSearchService searchService = new PointControlListSearchService();

                //検索前チェック
                chkResult = searchService.checkPreSelect(model);

                //エラーの場合、メッセージ設定
                switch (chkResult) {
                case PointControlListSearchService.ERR_PROS_BTN_MISMATCH:
                    return SUCCESS;
                case PointControlListSearchService.ERR_ZERO_DATA:
                    return SUCCESS;
                case PointControlListSearchService.ERR_MAXOVER_DATA:
                    return SUCCESS;
                }
                // 表示データの取得
                this.model.setResultList(searchService.selectList(this.model));
                // 選択したラジオボタンをクリアする
                this.select_rdl = NaikaraTalkConstants.BRANK;
                this.hasSearchFlg = NaikaraTalkConstants.TRUE;

                return SUCCESS;

            case PointControlListMoveService.ERR_LIST_BACK:
                this.addActionMessage(getMessage("EN0015", new String[] { "一覧部の左の" + "選択" }));
                return SUCCESS;
            }

            // 戻る用に必要な情報を取得/格納
            this.modelToSessionPointControl();

        } catch (Exception e) {
            throw new NaiException(e);
        }

        // ヘッダの戻るリンク用
        setCurrentActionName(NaikaraTalkConstants.POINT_CONTROL_LIST_SEARCH);

        // 詳細画面遷移
        return NEXTPAGE;
    }

    /**
     * Model値 To Session<br>
     * <br>
     * Model値・SessionPointControl値をSessionPointControlにセット。<br>
     * <br>
     * @param なし<br>
     * @return なし<br>
     * @exception Exception
     */
    private void modelToSessionPointControl() throws Exception {

        // 戻る用に必要な情報を格納
        SessionPointControl sessionData = new SessionPointControl();

        if ((SessionPointControl) SessionDataUtil.getSessionData(SessionPointControl.class.toString()) != null) {
            // 戻る用に必要な情報を取得
            String searchPointCd = ((SessionPointControl) SessionDataUtil.getSessionData(SessionPointControl.class
                    .toString())).getSearchPointCd();
            String searchFeeKbn = ((SessionPointControl) SessionDataUtil.getSessionData(SessionPointControl.class
                    .toString())).getSearchFeeKbn();

            sessionData.setSearchPointCd(searchPointCd); //検索Key：ポイントコード
            sessionData.setSearchFeeKbn(searchFeeKbn); //検索Key：通常月謝区分
        }

        sessionData.setReturnOnFlg(true); // 戻る判定Onフラグ
        sessionData.setProcessKbn(this.model.getProcessKbn()); //処理区分
        sessionData.setPointCd(this.model.getPointCd()); //ポイントコード
        sessionData.setFeeKbn(this.model.getFeeKbn()); //通常月謝区分
        sessionData.setSearchPointCdKey(this.getSelect_rdl()); //検索Key：選択された明細のKey-商品コード
        sessionData.setHasSearchFlg(this.getHasSearchFlg()); // 検索判断フラグ
        SessionDataUtil.setSessionData(sessionData);

    }

}
