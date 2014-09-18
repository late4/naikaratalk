package com.naikara_talk.action;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.service.PointControlListSearchService;
import com.naikara_talk.sessiondata.SessionPointControl;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>マスタ保守<br>
 * <b>クラス名称       :</b>ポイント管理マスタメンテナンス(一覧)<br>
 * <b>クラス概要       :</b>ポイント管理マスタメンテナンス(一覧)検索Action<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/01 TECS 新規作成
 */
public class PointControlListSearchAction extends PointControlListActionSupport {

    private static final long serialVersionUID = 1L;

    /**
     * 検索処理。<br>
     * <br>
     * 検索処理。<br>
     * <br>
     * @param なし<br>
     * @return String SUCCESS<br>
     * @exception NaiException
     */
    public String requestService() throws NaiException {

        // 開始ログ
        log.info(NaikaraStringUtil.unionProcesslog("Start"));

        // Modelクラス設定
        setupModel();
        // Serviceクラス生成
        PointControlListSearchService service = new PointControlListSearchService();

        // 戻る判定Onフラグ
        boolean returnOnFlg = false;

        if ((SessionPointControl) SessionDataUtil.getSessionData(SessionPointControl.class.toString()) != null) {

            // 戻る判定Onフラグ
            returnOnFlg = ((SessionPointControl) SessionDataUtil.getSessionData(SessionPointControl.class.toString()))
                    .getReturnOnFlg();

            // 検索判断フラグ
            this.hasSearchFlg = ((SessionPointControl) SessionDataUtil.getSessionData(SessionPointControl.class
                    .toString())).getHasSearchFlg();
        }

        // 検索前チェック
        if (!returnOnFlg) {
            int chkResult;
            try {
                chkResult = service.checkPreSelect(model);
                // エラーの場合、メッセージ設定
                switch (chkResult) {
                case PointControlListSearchService.ERR_PROS_BTN_MISMATCH:
                    this.addActionMessage(getMessage("EN0036", new String[] { "新規", "登録／選択ボタン" }));
                    return SUCCESS;
                case PointControlListSearchService.ERR_ZERO_DATA:
                    this.addActionMessage(getMessage("EN0020", new String[] { "ポイント管理マスタ", "" }));
                    return SUCCESS;
                case PointControlListSearchService.ERR_MAXOVER_DATA:
                    this.addActionMessage(getMessage("EN0023", new String[] { "101" }));
                    return SUCCESS;
                }
            } catch (Exception e) {
                throw new NaiException(e);
            }
        }
        try {

            // ヘッダ部の戻るボタン押下の場合は値の入れ替え処理
            this.SessionPointControlToModelBefore();

            if (!returnOnFlg || StringUtils.equals(NaikaraTalkConstants.TRUE, this.hasSearchFlg)) {
                // 表示データの取得
                this.model.setResultList(service.selectList(this.model));
                if (NaikaraTalkConstants.RETURN_CD_DATA_NO == this.model.getResultList().get(0).getReturnCode()) {
                    this.model.getResultList().clear();
                }
                this.hasSearchFlg = Boolean.toString(Boolean.TRUE);
            }

        } catch (Exception e) {
            throw new NaiException(e);
        }

        // 選択したラジオボタンをクリアする
        this.select_rdl = NaikaraTalkConstants.BRANK;

        try {
            // ヘッダ部の戻るボタン押下の場合は値の入れ替え処理
            this.SessionPointControlToModelAfter();

            // 戻る用に必要な情報を格納
            this.modelToSessionPointControl();

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
     * Model値 To Session<br>
     * <br>
     * Model値・SessionPointControl値をSessionPointControlにセット。<br>
     * <br>
     * @param なし<br>
     * @return なし<br>
     * @exception Exception
     */
    private void modelToSessionPointControl() throws Exception {

        // 戻る用に必要な情報を取得
        SessionPointControl sessionData = new SessionPointControl();
        sessionData.setReturnOnFlg(false); // 戻る判定Onフラグ=Offとする
        sessionData.setSearchPointCd(this.model.getPointCd()); // 検索Key：ポイントコード
        sessionData.setSearchFeeKbn(this.model.getFeeKbn()); // 検索Key：通常月謝区分
        SessionDataUtil.setSessionData(sessionData);

    }

    /**
     * 検索する前 Session値 To Model<br>
     * <br>
     * SessionPointControl値をModelにセット。<br>
     * <br>
     * @param なし<br>
     * @return なし<br>
     * @exception Exception
     */
    private void SessionPointControlToModelBefore() throws Exception {

        if ((SessionPointControl) SessionDataUtil.getSessionData(SessionPointControl.class.toString()) != null) {

            boolean returnOnFlg = ((SessionPointControl) SessionDataUtil.getSessionData(SessionPointControl.class
                    .toString())).getReturnOnFlg(); // 戻る判定Onフラグ

            if (returnOnFlg == true) {
                // 「戻る」の場合(getReturnOnFlgにtrueが設定されるのは、ポイント管理マスタメンテナンス登録選択Actionクラスのみ)
                String searchPointCd = ((SessionPointControl) SessionDataUtil.getSessionData(SessionPointControl.class
                        .toString())).getSearchPointCd();
                String searchFeeKbn = ((SessionPointControl) SessionDataUtil.getSessionData(SessionPointControl.class
                        .toString())).getSearchFeeKbn();

                this.model.setPointCd(searchPointCd); // 検索Key：ポイントコード
                this.model.setFeeKbn(searchFeeKbn); // 検索Key：通常月謝区分
            }
        }

    }

    /**
     * 検索の後 Session値 To Model<br>
     * <br>
     * SessionPointControl値をModelにセット。<br>
     * <br>
     * @param なし<br>
     * @return なし<br>
     * @exception Exception
     */
    private void SessionPointControlToModelAfter() throws Exception {

        if ((SessionPointControl) SessionDataUtil.getSessionData(SessionPointControl.class.toString()) != null) {
            boolean returnOnFlg = ((SessionPointControl) SessionDataUtil.getSessionData(SessionPointControl.class
                    .toString())).getReturnOnFlg(); // 戻る判定Onフラグ

            if (returnOnFlg == true) {
                // 「戻る」の場合(getReturnOnFlgにtrueが設定されるのは、ポイント管理マスタメンテナンス登録選択Actionクラスのみ)
                String processKbn = ((SessionPointControl) SessionDataUtil.getSessionData(SessionPointControl.class
                        .toString())).getProcessKbn(); // 処理区分

                String pointCd = ((SessionPointControl) SessionDataUtil.getSessionData(SessionPointControl.class
                        .toString())).getPointCd(); // ポイントコード

                String feeKbn = ((SessionPointControl) SessionDataUtil.getSessionData(SessionPointControl.class
                        .toString())).getFeeKbn(); // 通常月謝区分

                String select_rdl = ((SessionPointControl) SessionDataUtil.getSessionData(SessionPointControl.class
                        .toString())).getSearchPointCdKey(); // 検索Key：選択された明細のKey-商品コード

                this.processKbn_rdl = processKbn; // 処理区分
                this.pointCode_txt = pointCd; // 検索Key：ポイントコード
                this.feeKbn_rdl = feeKbn; // 検索Key：通常月謝区分
                this.select_rdl = select_rdl;

            }

            // sessionSessionPointControlのクリア
            SessionDataUtil.clearSessionData(SessionPointControl.class.toString());

        }
    }
}
