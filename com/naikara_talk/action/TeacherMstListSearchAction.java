package com.naikara_talk.action;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.service.TeacherMstListSearchService;
import com.naikara_talk.sessiondata.SessionTeacherMst;
import com.naikara_talk.sessiondata.SessionUserMstTeacherMst;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>マスタ保守<br>
 * <b>クラス名称       :</b>講師マスタメンテナンス(一覧)<br>
 * <b>クラス概要       :</b>講師マスタメンテナンス(一覧)検索Action<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/22 TECS 新規作成
 */
public class TeacherMstListSearchAction extends TeacherMstListActionSupport {

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
        TeacherMstListSearchService service = new TeacherMstListSearchService();

        // 戻る判定Onフラグ
        boolean returnOnFlg = false;

        if ((SessionTeacherMst) SessionDataUtil.getSessionData(SessionTeacherMst.class.toString()) != null) {

            // 戻る判定Onフラグ
            returnOnFlg = ((SessionTeacherMst) SessionDataUtil.getSessionData(SessionTeacherMst.class.toString()))
                    .getReturnOnFlg();

            // 検索判断フラグ
            this.hasSearchFlg = ((SessionTeacherMst) SessionDataUtil.getSessionData(SessionTeacherMst.class.toString()))
                    .getHasSearchFlg();
        }

        // 検索前チェック
        if (!returnOnFlg) {
            int chkResult;
            try {
                chkResult = service.checkPreSelect(model);
                // エラーの場合、メッセージ設定
                switch (chkResult) {
                case TeacherMstListSearchService.ERR_ZERO_DATA:
                    this.addActionMessage(getMessage("EN0020", new String[] { "利用者マスタ、講師マスタ", "" }));
                    return SUCCESS;
                case TeacherMstListSearchService.ERR_MAXOVER_DATA:
                    this.addActionMessage(getMessage("EN0023", new String[] { "101" }));
                    return SUCCESS;
                }
            } catch (Exception e) {
                throw new NaiException(e);
            }
        }
        try {

            // ヘッダ部の戻るボタン押下の場合は値の入れ替え処理
            this.SessionTeacherMstToModelBefore();

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
            this.SessionTeacherMstToModelAfter();

            // 戻る用に必要な情報を格納
            this.modelToSessionTeacherMst();

        } catch (Exception e) {
            throw new NaiException(e);
        }

        // SessionUserMstTeacherMstのクリア
        if ((SessionUserMstTeacherMst) SessionDataUtil.getSessionData(SessionUserMstTeacherMst.class.toString()) != null) {
            SessionDataUtil.clearSessionData(SessionUserMstTeacherMst.class.toString());
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
     * Model値・SessionTeacherMst値をSessionTeacherMstにセット。<br>
     * <br>
     * @param なし<br>
     * @return なし<br>
     * @exception Exception
     */
    private void modelToSessionTeacherMst() throws Exception {

        // 戻る用に必要な情報を取得
        SessionTeacherMst sessionData = new SessionTeacherMst();
        sessionData.setReturnOnFlg(false);                      // 戻る判定Onフラグ=Offとする
        sessionData.setSearchUserId(this.model.getUserId());    // 検索Key：講師ID
        sessionData.setSearchUserKnm(this.model.getUserKnm());  // 検索Key：講師名(フリガナ)
        SessionDataUtil.setSessionData(sessionData);

    }

    /**
     * 検索する前 Session値 To Model<br>
     * <br>
     * SessionTeacherMst値をModelにセット。<br>
     * <br>
     * @param なし<br>
     * @return なし<br>
     * @exception Exception
     */
    private void SessionTeacherMstToModelBefore() throws Exception {

        if ((SessionTeacherMst) SessionDataUtil.getSessionData(SessionTeacherMst.class.toString()) != null) {

            boolean returnOnFlg = ((SessionTeacherMst) SessionDataUtil.getSessionData(SessionTeacherMst.class
                    .toString())).getReturnOnFlg();     // 戻る判定Onフラグ

            if (returnOnFlg == true) {
                // 「戻る」の場合(getReturnOnFlgにtrueが設定されるのは、ポイント管理マスタメンテナンス登録選択Actionクラスのみ)
                String searchUserId = ((SessionTeacherMst) SessionDataUtil.getSessionData(SessionTeacherMst.class
                        .toString())).getSearchUserId();
                String searchUserKnm = ((SessionTeacherMst) SessionDataUtil.getSessionData(SessionTeacherMst.class
                        .toString())).getSearchUserKnm();

                this.model.setUserId(searchUserId);     // 検索Key：講師ID
                this.model.setUserKnm(searchUserKnm);   // 検索Key：講師名(フリガナ)
            }
        }

    }

    /**
     * 検索の後 Session値 To Model<br>
     * <br>
     * SessionTeacherMst値をModelにセット。<br>
     * <br>
     * @param なし<br>
     * @return なし<br>
     * @exception Exception
     */
    private void SessionTeacherMstToModelAfter() throws Exception {

        if ((SessionTeacherMst) SessionDataUtil.getSessionData(SessionTeacherMst.class.toString()) != null) {
            boolean returnOnFlg = ((SessionTeacherMst) SessionDataUtil.getSessionData(SessionTeacherMst.class
                    .toString())).getReturnOnFlg();                                                 // 戻る判定Onフラグ

            if (returnOnFlg == true) {
                // 「戻る」の場合(getReturnOnFlgにtrueが設定されるのは、ポイント管理マスタメンテナンス登録選択Actionクラスのみ)
                String processKbn = ((SessionTeacherMst) SessionDataUtil.getSessionData(SessionTeacherMst.class
                        .toString())).getProcessKbn();                                              // 処理区分

                String userId = ((SessionTeacherMst) SessionDataUtil.getSessionData(SessionTeacherMst.class.toString()))
                        .getUserId();                                                               // 講師ID

                String userKnm = ((SessionTeacherMst) SessionDataUtil
                        .getSessionData(SessionTeacherMst.class.toString())).getUserKnm();          // 講師名(フリガナ)

                String select_rdl = ((SessionTeacherMst) SessionDataUtil.getSessionData(SessionTeacherMst.class
                        .toString())).getSearchUserIdKey();                                         // 検索Key：選択された明細のKey-商品コード

                this.processKbn_rdl = processKbn;                                                   // 処理区分
                this.teacherId_txt = userId;                                                        // 検索Key：ポイントコード
                this.teacherNm_txt = userKnm;                                                       // 検索Key：通常月謝区分
                this.select_rdl = select_rdl;

            }

            // sessionSessionPointControlのクリア
            SessionDataUtil.clearSessionData(SessionTeacherMst.class.toString());

        }
    }
}
