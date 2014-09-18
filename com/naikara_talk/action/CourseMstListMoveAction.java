package com.naikara_talk.action;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.service.CourseMstListMoveService;
import com.naikara_talk.service.CourseMstListSearchService;
import com.naikara_talk.sessiondata.SessionCourseMst;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>マスタ保守<br>
 * <b>クラス名称       :</b>コースマスタメンテナンス(一覧)<br>
 * <b>クラス概要       :</b>コースマスタメンテナンス(一覧)登録選択Action<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/08/05 TECS 新規作成
 */
public class CourseMstListMoveAction extends CourseMstListActionSupport {

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

        // Modelクラス設定
        setupModel();
        // Serviceクラス生成
        CourseMstListMoveService service = new CourseMstListMoveService();

        int chkResult = service.nextPageRequest(this.model, this.getSelect_rdl(), this.getHasSearchFlg());

        // エラーの場合、メッセージ設定
        try {
            switch (chkResult) {
            case CourseMstListMoveService.ERR_NO_SELECT:

                this.addActionMessage(getMessage("EN0015", new String[] { "一覧部の左の選択" }));

                CourseMstListSearchService searchService = new CourseMstListSearchService();

                // 検索前チェック
                chkResult = searchService.checkPreSelect(model);

                // エラーの場合、メッセージ設定
                switch (chkResult) {
                case CourseMstListSearchService.ERR_PROS_BTN_MISMATCH:
                    return SUCCESS;
                case CourseMstListSearchService.ERR_ZERO_DATA:
                    return SUCCESS;
                case CourseMstListSearchService.ERR_MAXOVER_DATA:
                    return SUCCESS;
                }
                // 表示データの取得
                this.model.setResultList(searchService.selectList(this.model));
                // 選択したラジオボタンをクリアする
                this.select_rdl = NaikaraTalkConstants.BRANK;
                this.hasSearchFlg = NaikaraTalkConstants.TRUE;

                return SUCCESS;

            case CourseMstListMoveService.ERR_LIST_BACK:
                this.addActionMessage(getMessage("EN0015", new String[] { "一覧部の左の選択" }));
                return SUCCESS;

            case CourseMstListMoveService.ERR_NO_AUTH:
                this.addActionMessage(getMessage("EN0059", new String[] { "" }));
                return SUCCESS;
            }

            // 戻る用に必要な情報を取得/格納
            this.modelToSessionCourseMst();

        } catch (Exception e) {
            throw new NaiException(e);
        }

        // ヘッダの戻るリンク用
        setCurrentActionName(NaikaraTalkConstants.COURSE_MST_LIST_SEARCH);

        // 詳細画面遷移
        return NEXTPAGE;
    }

    /**
     * Model値 To Session<br>
     * <br>
     * Model値・SessionCourseMst値をSessionCourseMstにセット。<br>
     * <br>
     * @param なし<br>
     * @return なし<br>
     * @exception なし
     */
    private void modelToSessionCourseMst() {

        // 戻る用に必要な情報を格納
        SessionCourseMst sessionData = new SessionCourseMst();

        if ((SessionCourseMst) SessionDataUtil.getSessionData(SessionCourseMst.class.toString()) != null) {
            // 戻る用に必要な情報を取得
            String searchBigClassificationCd = ((SessionCourseMst) SessionDataUtil
                    .getSessionData(SessionCourseMst.class.toString())).getSearchBigClassificationCd();
            String searchMiddleClassificationCd = ((SessionCourseMst) SessionDataUtil
                    .getSessionData(SessionCourseMst.class.toString())).getSearchMiddleClassificationCd();
            String searchSmallClassificationCd = ((SessionCourseMst) SessionDataUtil
                    .getSessionData(SessionCourseMst.class.toString())).getSearchSmallClassificationCd();
            String searchCourseJnm = ((SessionCourseMst) SessionDataUtil.getSessionData(SessionCourseMst.class
                    .toString())).getSearchCourseJnm();
            String searchKeyword1 = ((SessionCourseMst) SessionDataUtil.getSessionData(SessionCourseMst.class
                    .toString())).getSearchKeyword1();
            String searchKeyword2 = ((SessionCourseMst) SessionDataUtil.getSessionData(SessionCourseMst.class
                    .toString())).getSearchKeyword2();
            String searchKeyword3 = ((SessionCourseMst) SessionDataUtil.getSessionData(SessionCourseMst.class
                    .toString())).getSearchKeyword3();
            String searchKeyword4 = ((SessionCourseMst) SessionDataUtil.getSessionData(SessionCourseMst.class
                    .toString())).getSearchKeyword4();
            String searchKeyword5 = ((SessionCourseMst) SessionDataUtil.getSessionData(SessionCourseMst.class
                    .toString())).getSearchKeyword5();

            sessionData.setSearchBigClassificationCd(searchBigClassificationCd);        // 検索Key：大分類
            sessionData.setSearchMiddleClassificationCd(searchMiddleClassificationCd);  // 検索Key：中分類
            sessionData.setSearchSmallClassificationCd(searchSmallClassificationCd);    // 検索Key：小分類
            sessionData.setSearchCourseJnm(searchCourseJnm);                            // 検索Key：コース名
            sessionData.setSearchKeyword1(searchKeyword1);                              // 検索Key：キーワード1
            sessionData.setSearchKeyword2(searchKeyword2);                              // 検索Key：キーワード2
            sessionData.setSearchKeyword3(searchKeyword3);                              // 検索Key：キーワード3
            sessionData.setSearchKeyword4(searchKeyword4);                              // 検索Key：キーワード4
            sessionData.setSearchKeyword5(searchKeyword5);                              // 検索Key：キーワード5
        }

        sessionData.setReturnOnFlg(true);                                               // 戻る判定Onフラグ
        sessionData.setProcessKbn(this.model.getProcessKbn());                          // 処理区分
        sessionData.setBigClassificationCd(this.model.getBigClassificationCd());        // 検索Key：大分類
        sessionData.setMiddleClassificationCd(this.model.getMiddleClassificationCd());  // 検索Key：中分類
        sessionData.setSmallClassificationCd(this.model.getSmallClassificationCd());    // 検索Key：小分類
        sessionData.setCourseJnm(this.model.getCourseJnm());                            // 検索Key：コース名
        sessionData.setKeyword1(this.model.getKeyword1());                              // 検索Key：キーワード1
        sessionData.setKeyword2(this.model.getKeyword2());                              // 検索Key：キーワード2
        sessionData.setKeyword3(this.model.getKeyword3());                              // 検索Key：キーワード3
        sessionData.setKeyword4(this.model.getKeyword4());                              // 検索Key：キーワード4
        sessionData.setKeyword5(this.model.getKeyword5());                              // 検索Key：キーワード5
        sessionData.setSearchCourseCdKey(this.getSelect_rdl());                         // 検索Key：選択された明細のKey-コースコード
        sessionData.setHasSearchFlg(this.getHasSearchFlg());                            // 検索判断フラグ
        SessionDataUtil.setSessionData(sessionData);

    }

}
