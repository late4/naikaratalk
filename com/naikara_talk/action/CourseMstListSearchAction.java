package com.naikara_talk.action;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.service.CourseMstListSearchService;
import com.naikara_talk.sessiondata.SessionCourseMst;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>マスタ保守<br>
 * <b>クラス名称       :</b>コースマスタメンテナンス(一覧)<br>
 * <b>クラス概要       :</b>コースマスタメンテナンス(一覧)検索Action<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/08/02 TECS 新規作成
 */
public class CourseMstListSearchAction extends CourseMstListActionSupport {

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
        CourseMstListSearchService service = new CourseMstListSearchService();

        // 戻る判定Onフラグ
        boolean returnOnFlg = false;

        if ((SessionCourseMst) SessionDataUtil.getSessionData(SessionCourseMst.class.toString()) != null) {

            // 戻る判定Onフラグ
            returnOnFlg = ((SessionCourseMst) SessionDataUtil.getSessionData(SessionCourseMst.class.toString()))
                    .getReturnOnFlg();

            // 検索判断フラグ
            this.hasSearchFlg = ((SessionCourseMst) SessionDataUtil.getSessionData(SessionCourseMst.class.toString()))
                    .getHasSearchFlg();
        }

        // 検索前チェック
        if (!returnOnFlg) {
            int chkResult;
            try {
                chkResult = service.checkPreSelect(model);
                // エラーの場合、メッセージ設定
                switch (chkResult) {
                case CourseMstListSearchService.ERR_PROS_BTN_MISMATCH:
                    this.addActionMessage(getMessage("EN0036", new String[] { "新規", "登録／選択ボタン" }));
                    return SUCCESS;
                case CourseMstListSearchService.ERR_NO_AUTH:
                    this.addActionMessage(getMessage("EN0059", new String[] { "" }));
                    return SUCCESS;
                case CourseMstListSearchService.ERR_ZERO_DATA:
                    this.addActionMessage(getMessage("EN0020", new String[] { "コースマスタ", "" }));
                    return SUCCESS;
                case CourseMstListSearchService.ERR_MAXOVER_DATA:
                    this.addActionMessage(getMessage("EN0023", new String[] { "201" }));
                    return SUCCESS;
                }
            } catch (Exception e) {
                throw new NaiException(e);
            }
        }

        // ヘッダ部の戻るボタン押下の場合は値の入れ替え処理
        this.SessionCourseMstToModelBefore();

        if (!returnOnFlg || StringUtils.equals(NaikaraTalkConstants.TRUE, this.hasSearchFlg)) {
            // 表示データの取得
            this.model.setResultList(service.selectList(this.model));
            if (NaikaraTalkConstants.RETURN_CD_DATA_NO == this.model.getResultList().get(0).getReturnCode()) {
                this.model.getResultList().clear();
            }
            this.hasSearchFlg = Boolean.toString(Boolean.TRUE);
        }

        // 選択したラジオボタンをクリアする
        this.select_rdl = NaikaraTalkConstants.BRANK;

        // ヘッダ部の戻るボタン押下の場合は値の入れ替え処理
        this.SessionCourseMstToModelAfter();

        // 戻る用に必要な情報を格納
        this.modelToSessionCourseMst();

        // メッセージの設定
        if (!StringUtils.isEmpty(this.message)) {
            this.addActionMessage(this.message);
        }

        return SUCCESS;
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

        // 戻る用に必要な情報を取得
        SessionCourseMst sessionData = new SessionCourseMst();
        sessionData.setReturnOnFlg(false);                                                      // 戻る判定Onフラグ=Offとする
        sessionData.setSearchBigClassificationCd(this.model.getBigClassificationCd());          // 検索Key：大分類
        sessionData.setSearchMiddleClassificationCd(this.model.getMiddleClassificationCd());    // 検索Key：中分類
        sessionData.setSearchSmallClassificationCd(this.model.getSmallClassificationCd());      // 検索Key：小分類
        sessionData.setSearchCourseJnm(this.model.getCourseJnm());                              // 検索Key：コース名
        sessionData.setSearchKeyword1(this.model.getKeyword1());                                // 検索Key：キーワード1
        sessionData.setSearchKeyword2(this.model.getKeyword2());                                // 検索Key：キーワード2
        sessionData.setSearchKeyword3(this.model.getKeyword3());                                // 検索Key：キーワード3
        sessionData.setSearchKeyword4(this.model.getKeyword4());                                // 検索Key：キーワード4
        sessionData.setSearchKeyword5(this.model.getKeyword5());                                // 検索Key：キーワード5
        SessionDataUtil.setSessionData(sessionData);

    }

    /**
     * 検索する前 Session値 To Model<br>
     * <br>
     * SessionCourseMst値をModelにセット。<br>
     * <br>
     * @param なし<br>
     * @return なし<br>
     * @exception なし
     */
    private void SessionCourseMstToModelBefore() {

        if ((SessionCourseMst) SessionDataUtil.getSessionData(SessionCourseMst.class.toString()) != null) {

            boolean returnOnFlg = ((SessionCourseMst) SessionDataUtil.getSessionData(SessionCourseMst.class.toString()))
                    .getReturnOnFlg(); // 戻る判定Onフラグ

            if (returnOnFlg == true) {
                // 「戻る」の場合(getReturnOnFlgにtrueが設定されるのは、コースマスタメンテナンス登録選択Actionクラスのみ)
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

                this.model.setBigClassificationCd(searchBigClassificationCd);           // 検索Key：大分類
                this.model.setMiddleClassificationCd(searchMiddleClassificationCd);     // 検索Key：中分類
                this.model.setSmallClassificationCd(searchSmallClassificationCd);       // 検索Key：小分類
                this.model.setCourseJnm(searchCourseJnm);                               // 検索Key：コース名
                this.model.setKeyword1(searchKeyword1);                                 // 検索Key：キーワード1
                this.model.setKeyword2(searchKeyword2);                                 // 検索Key：キーワード2
                this.model.setKeyword3(searchKeyword3);                                 // 検索Key：キーワード3
                this.model.setKeyword4(searchKeyword4);                                 // 検索Key：キーワード4
                this.model.setKeyword5(searchKeyword5);                                 // 検索Key：キーワード5
            }
        }

    }

    /**
     * 検索の後 Session値 To Model<br>
     * <br>
     * SessionCourseMst値をModelにセット。<br>
     * <br>
     * @param なし<br>
     * @return なし<br>
     * @exception なし
     */
    private void SessionCourseMstToModelAfter() {

        if ((SessionCourseMst) SessionDataUtil.getSessionData(SessionCourseMst.class.toString()) != null) {
            boolean returnOnFlg = ((SessionCourseMst) SessionDataUtil.getSessionData(SessionCourseMst.class.toString()))
                    .getReturnOnFlg(); // 戻る判定Onフラグ

            if (returnOnFlg == true) {
                // 「戻る」の場合(getReturnOnFlgにtrueが設定されるのは、ポイント管理マスタメンテナンス登録選択Actionクラスのみ)
                String processKbn = ((SessionCourseMst) SessionDataUtil.getSessionData(SessionCourseMst.class
                        .toString())).getProcessKbn();                                                      // 処理区分
                String bigClassificationCd = ((SessionCourseMst) SessionDataUtil.getSessionData(SessionCourseMst.class
                        .toString())).getBigClassificationCd();                                             // 大分類
                String middleClassificationCd = ((SessionCourseMst) SessionDataUtil
                        .getSessionData(SessionCourseMst.class.toString())).getMiddleClassificationCd();    // 中分類
                String smallClassificationCd = ((SessionCourseMst) SessionDataUtil
                        .getSessionData(SessionCourseMst.class.toString())).getSmallClassificationCd();     // 小分類
                String courseJnm = ((SessionCourseMst) SessionDataUtil
                        .getSessionData(SessionCourseMst.class.toString())).getCourseJnm();                 // コース名
                String keyword1 = ((SessionCourseMst) SessionDataUtil.getSessionData(SessionCourseMst.class.toString()))
                        .getKeyword1();                                                                     // キーワード1
                String keyword2 = ((SessionCourseMst) SessionDataUtil.getSessionData(SessionCourseMst.class.toString()))
                        .getKeyword2();                                                                     // キーワード2
                String keyword3 = ((SessionCourseMst) SessionDataUtil.getSessionData(SessionCourseMst.class.toString()))
                        .getKeyword3();                                                                     // キーワード3
                String keyword4 = ((SessionCourseMst) SessionDataUtil.getSessionData(SessionCourseMst.class.toString()))
                        .getKeyword4();                                                                     // キーワード4
                String keyword5 = ((SessionCourseMst) SessionDataUtil.getSessionData(SessionCourseMst.class.toString()))
                        .getKeyword5();                                                                     // キーワード5

                String select_rdl = ((SessionCourseMst) SessionDataUtil.getSessionData(SessionCourseMst.class
                        .toString())).getSearchCourseCdKey();                                               // 検索Key：選択された明細のKey-コースコード

                this.processKbn_rdl = processKbn;                                                           // 処理区分
                this.courseLarge_sel = bigClassificationCd;                                                 // 大分類
                this.courseMedium_sel = middleClassificationCd;                                             // 中分類
                this.courseSmall_sel = smallClassificationCd;                                               // 小分類
                this.courseName_txt = courseJnm;                                                            // コース名
                this.keyword1_txt = keyword1;                                                               // キーワード1
                this.keyword2_txt = keyword2;                                                               // キーワード2
                this.keyword3_txt = keyword3;                                                               // キーワード3
                this.keyword4_txt = keyword4;                                                               // キーワード4
                this.keyword5_txt = keyword5;                                                               // キーワード5
                this.select_rdl = select_rdl;                                                               // 一覧から選択された明細データ(jsp)

            }

            // SessionCourseMstのクリア
            SessionDataUtil.clearSessionData(SessionCourseMst.class.toString());

        }
    }
}
