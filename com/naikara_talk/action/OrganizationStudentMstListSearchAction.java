package com.naikara_talk.action;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.service.OrganizationStudentMstListSearchService;
import com.naikara_talk.sessiondata.SessionOrganizationStudentMst;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>組織_初期処理<br>
 * <b>クラス名称       :</b>システム受講者登録(一覧)検索Actionクラス。<br>
 * <b>クラス概要       :</b>組織の社員情報(受講者)の新規アカウント(一覧)。<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/11 TECS 新規作成
 */
public class OrganizationStudentMstListSearchAction extends OrganizationStudentMstListActionSupport {

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
        OrganizationStudentMstListSearchService service = new OrganizationStudentMstListSearchService();

        // 戻る判定Onフラグ
        boolean returnOnFlg = false;

        if ((SessionOrganizationStudentMst) SessionDataUtil.getSessionData(SessionOrganizationStudentMst.class
                .toString()) != null) {

            // 戻る判定Onフラグ
            returnOnFlg = ((SessionOrganizationStudentMst) SessionDataUtil
                    .getSessionData(SessionOrganizationStudentMst.class.toString())).getReturnOnFlg();

            // 検索判断フラグ
            this.hasSearchFlg = ((SessionOrganizationStudentMst) SessionDataUtil
                    .getSessionData(SessionOrganizationStudentMst.class.toString())).getHasSearchFlg();
        }

        // 検索前チェック
        if (!returnOnFlg) {
            int chkResult;
            try {
                chkResult = service.checkPreSelect(model);
                // エラーの場合、メッセージ設定
                switch (chkResult) {
                case OrganizationStudentMstListSearchService.ERR_PROS_BTN_MISMATCH:
                    this.addActionMessage(getMessage("EB0036", new String[] { "新規", "登録／選択ボタン" }));
                    return SUCCESS;
                case OrganizationStudentMstListSearchService.ERR_ZERO_DATA:
                    this.addActionMessage(getMessage("EB0020", new String[] {}));
                    return SUCCESS;
                case OrganizationStudentMstListSearchService.ERR_MAXOVER_DATA:
                    this.addActionMessage(getMessage("EB0023", new String[] { "101" }));
                    return SUCCESS;
                }
            } catch (Exception e) {
                throw new NaiException(e);
            }
        }
        try {

            // ヘッダ部の戻るボタン押下の場合は値の入れ替え処理
            this.SessionOrganizationStudentMstToModelBefore();

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
            this.SessionOrganizationStudentMstToModelAfter();

            // 戻る用に必要な情報を格納
            this.modelToSessionOrganizationStudentMst();

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
     * Model値・SessionOrganizationStudentMst値をSessionOrganizationStudentMstにセット。<br>
     * <br>
     * @param なし<br>
     * @return なし<br>
     * @exception Exception
     */
    private void modelToSessionOrganizationStudentMst() throws Exception {

        // 戻る用に必要な情報を取得
        SessionOrganizationStudentMst sessionData = null;
        if ((SessionOrganizationStudentMst) SessionDataUtil.getSessionData(SessionOrganizationStudentMst.class
                .toString()) != null) {
            sessionData = (SessionOrganizationStudentMst) SessionDataUtil
                    .getSessionData(SessionOrganizationStudentMst.class.toString());
        } else {
            sessionData = new SessionOrganizationStudentMst();
        }
        // 戻る判定Onフラグ=Offとする
        sessionData.setReturnOnFlg(false);

        sessionData.setSearchStudentPosition(this.model.getStudentPosition());              // 検索Key：受講者所属部署
        sessionData.setSearchPositionOrganizationId(this.model.getPositionOrganizationId());// 検索Key：所属組織内
        sessionData.setSearchStudentId(this.model.getStudentId());                          // 検索Key：受講者ID
        sessionData.setSearchStudentNm(this.model.getStudentNm());                          // 検索Key：受講者名(フリガナ)
        sessionData.setSearchUseKbn(this.model.getUseKbn());                                // 検索Key：利用状態
        sessionData.setSearchOrganizationId(this.model.getOrganizationId());                // 検索Key：組織ID
        sessionData.setSearchOrganizationNm(this.model.getOrganizationNm());                // 検索Key：組織名
        SessionDataUtil.setSessionData(sessionData);

    }

    /**
     * 検索する前 Session値 To Model<br>
     * <br>
     * SessionOrganizationStudentMst値をModelにセット。<br>
     * <br>
     * @param なし<br>
     * @return なし<br>
     * @exception Exception
     */
    private void SessionOrganizationStudentMstToModelBefore() throws Exception {

        if ((SessionOrganizationStudentMst) SessionDataUtil.getSessionData(SessionOrganizationStudentMst.class
                .toString()) != null) {

            boolean returnOnFlg = ((SessionOrganizationStudentMst) SessionDataUtil
                    .getSessionData(SessionOrganizationStudentMst.class.toString())).getReturnOnFlg(); // 戻る判定Onフラグ

            if (returnOnFlg == true) {
                // 「戻る」の場合(getReturnOnFlgにtrueが設定されるのは、システム受講者登録選択Actionクラスのみ)
                String searchStudentPosition = ((SessionOrganizationStudentMst) SessionDataUtil
                        .getSessionData(SessionOrganizationStudentMst.class.toString())).getSearchStudentPosition();
                String searchPositionOrganizationId = ((SessionOrganizationStudentMst) SessionDataUtil
                        .getSessionData(SessionOrganizationStudentMst.class.toString()))
                        .getSearchPositionOrganizationId();
                String searchStudentId = ((SessionOrganizationStudentMst) SessionDataUtil
                        .getSessionData(SessionOrganizationStudentMst.class.toString())).getSearchStudentId();
                String searchStudentNm = ((SessionOrganizationStudentMst) SessionDataUtil
                        .getSessionData(SessionOrganizationStudentMst.class.toString())).getSearchStudentNm();
                String searchUseKbn = ((SessionOrganizationStudentMst) SessionDataUtil
                        .getSessionData(SessionOrganizationStudentMst.class.toString())).getSearchUseKbn();
                String searchOrganizationId = ((SessionOrganizationStudentMst) SessionDataUtil
                        .getSessionData(SessionOrganizationStudentMst.class.toString())).getSearchOrganizationId();
                String searchOrganizationNm = ((SessionOrganizationStudentMst) SessionDataUtil
                        .getSessionData(SessionOrganizationStudentMst.class.toString())).getSearchOrganizationNm();

                this.model.setStudentPosition(searchStudentPosition);                 // 検索Key：受講者所属部署
                this.model.setPositionOrganizationId(searchPositionOrganizationId);   // 検索Key：所属組織内ID
                this.model.setStudentId(searchStudentId);                             // 検索Key：受講者ID
                this.model.setStudentNm(searchStudentNm);                             // 検索Key：受講者名(フリガナ)
                this.model.setUseKbn(searchUseKbn);                                   // 検索Key：利用状態
                this.model.setOrganizationId(searchOrganizationId);                   // 検索Key：組織ID
                this.model.setOrganizationNm(searchOrganizationNm);                   // 検索Key：組織名

            }
        }

    }

    /**
     * 検索の後 Session値 To Model<br>
     * <br>
     * SessionOrganizationStudentMst値をModelにセット。<br>
     * <br>
     * @param なし<br>
     * @return なし<br>
     * @exception Exception
     */
    private void SessionOrganizationStudentMstToModelAfter() throws Exception {

        if ((SessionOrganizationStudentMst) SessionDataUtil.getSessionData(SessionOrganizationStudentMst.class
                .toString()) != null) {
            boolean returnOnFlg = ((SessionOrganizationStudentMst) SessionDataUtil
                    .getSessionData(SessionOrganizationStudentMst.class.toString())).getReturnOnFlg(); // 戻る判定Onフラグ

            if (returnOnFlg == true) {
                // 「戻る」の場合(getReturnOnFlgにtrueが設定されるのは、ポイント管理マスタメンテナンス登録選択Actionクラスのみ)
                String processKbn = ((SessionOrganizationStudentMst) SessionDataUtil
                        .getSessionData(SessionOrganizationStudentMst.class.toString())).getProcessKbn();             // 処理区分

                String studentPosition = ((SessionOrganizationStudentMst) SessionDataUtil
                        .getSessionData(SessionOrganizationStudentMst.class.toString())).getStudentPosition();        // 受講者所属部署

                String positionOrganizationId = ((SessionOrganizationStudentMst) SessionDataUtil
                        .getSessionData(SessionOrganizationStudentMst.class.toString())).getPositionOrganizationId(); // 所属組織内ID

                String studentId = ((SessionOrganizationStudentMst) SessionDataUtil
                        .getSessionData(SessionOrganizationStudentMst.class.toString())).getStudentId();              // 受講者ID

                String studentNm = ((SessionOrganizationStudentMst) SessionDataUtil
                        .getSessionData(SessionOrganizationStudentMst.class.toString())).getStudentNm();              // 受講者名(フリガナ)

                String useKbn = ((SessionOrganizationStudentMst) SessionDataUtil
                        .getSessionData(SessionOrganizationStudentMst.class.toString())).getUseKbn();                 // 利用状態

                String organizationId = ((SessionOrganizationStudentMst) SessionDataUtil
                        .getSessionData(SessionOrganizationStudentMst.class.toString())).getOrganizationId();         // 組織ID

                String organizationNm = ((SessionOrganizationStudentMst) SessionDataUtil
                        .getSessionData(SessionOrganizationStudentMst.class.toString())).getOrganizationNm();         // 組織名

                String select_rdl = ((SessionOrganizationStudentMst) SessionDataUtil
                        .getSessionData(SessionOrganizationStudentMst.class.toString())).getSearchStudentIdKey();     // 検索Key：選択された明細のKey-受講者ID

                this.processKbn_rdl = processKbn;                            // 処理区分
                this.studentPosition_txt = studentPosition;                  // 検索Key：受講者所属部署
                this.positionOrganizationId_txt = positionOrganizationId;    // 検索Key：所属組織内ID
                this.studentId_txt = studentId;                              // 検索Key：受講者ID
                this.studentNm_txt = studentNm;                              // 検索Key：受講者名(フリガナ)
                this.useKbn_rdl = useKbn;                                    // 検索Key：利用状態
                this.organizationId = organizationId;                          // 検索Key：組織ID
                this.organizationNm_txt = organizationNm;                      // 検索Key：組織名
                this.select_rdl = select_rdl;
                this.organizationId = ((SessionOrganizationStudentMst) SessionDataUtil
                        .getSessionData(SessionOrganizationStudentMst.class.toString())).getOrganizationId();
                this.organizationNm_txt = ((SessionOrganizationStudentMst) SessionDataUtil
                        .getSessionData(SessionOrganizationStudentMst.class.toString())).getOrganizationNm();

            }
            // sessionSessionOrganizationStudentMstのクリア
            SessionDataUtil.clearSessionData(SessionOrganizationStudentMst.class.toString());

        }
    }
}
