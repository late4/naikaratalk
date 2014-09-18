package com.naikara_talk.action;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.model.CustomerManagedListModel;
import com.naikara_talk.service.CustomerManagedListSearchService;
import com.naikara_talk.sessiondata.SessionCustomerManaged;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>お客様管理ページ<br>
 * <b>クラス名称　　　:</b>お客様管理ページクラス。<br>
 * <b>クラス概要　　　:</b>お客様管理ページSearchAction。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/08/20 TECS 新規作成。
 */

public class CustomerManagedListSearchAction extends CustomerManagedListActionSupport{

	private static final long serialVersionUID = 1L;

    private static boolean returnOnFlg = false;                        // 戻る判定Onフラグ

    private static String searchKey;                                   // 検索Key：選択された明細のKey

	/**
	 * 検索処理。<br>
	 * <br>
	 *
	 * @param なし
	 * @return String
	 * @throws NaiException
	 */
	public String requestService() throws NaiException {

		// 開始ログ
		log.info(NaikaraStringUtil.unionProcesslog("Start"));

		// 画面のパラメータをモデルにセット
		this.setupModel();

		CustomerManagedListSearchService service = new CustomerManagedListSearchService();

		try {

            // ヘッダ部の戻るボタン押下の場合は値の入れ替え処理
            this.SessionCustomerManagedToModelBefore();
            // ヘッダ部の戻るボタン押下の場合は画面へ値の設定処理
            this.SessionCustomerManagedToModelAfter();

            int chkResult;

            //検索前チェック
            chkResult = service.checkPreSelect(model, returnOnFlg, this.hasSearchFlg);

            // エラーの場合、メッセージ設定
            switch (chkResult) {
            case CustomerManagedListModel.ERR_PERIOD_FROM:
                // 期間指定である場合は、開始日と終了日入力二つとも必須
            	this.addActionMessage(getMessage("EN0009", new String[] { "期間指定", "開始日" }));
                return SUCCESS;
            case CustomerManagedListModel.ERR_PERIOD_TO:
                // 期間指定である場合は、開始日と終了日入力二つとも必須
            	this.addActionMessage(getMessage("EN0009", new String[] { "期間指定", "終了日" }));
                return SUCCESS;
            case CustomerManagedListModel.ERR_OBJECT_PERIOD:
                // 期間指定ではない場合は、開始日と終了日入力できません
            	StringBuffer sb = new StringBuffer();
            	sb.append("対象期間").append(this.objectPeriod_rdll.get(this.objectPeriod_rdl));
                this.addActionMessage(getMessage("EN0002", new String[]{ sb.toString(), "対象期間:期間指定" }));
                return SUCCESS;
            case CustomerManagedListModel.ERR_FROM_TO:
                // 開始日≦終了日となるように
            	this.addActionMessage(getMessage("EN0011", new String[] { "開始日", "終了日" }));
                return SUCCESS;
            case CustomerManagedListModel.ERR_ZERO_DATA:
                // 対象データZero件エラー
                this.addActionMessage(getMessage("EN0020", new String[]{ "商品購入及びレッスン実績", "" }));
                return SUCCESS;
            case CustomerManagedListModel.ERR_MAXOVER_DATA:
                // 対象データMax件以上エラー
                this.addActionMessage(getMessage("EN0023", new String[] { "301" }));
                return SUCCESS;
            }

            if (!returnOnFlg || StringUtils.equals(NaikaraTalkConstants.FALSE, this.hasSearchFlg)) {

                // 戻る判定Onフラグ(returnOnFlg) = false 又は 画面状態フラグ == (初期状態ではない) の場合

                // 表示データの取得と設定
            	this.model.setResultList(service.selectList(this.model));
                this.hasSearchFlg = Boolean.toString(Boolean.FALSE);
            } else {
                // クリア
                this.hasSearchFlg = NaikaraTalkConstants.TRUE;
            }

        } catch (Exception e) {
            throw new NaiException(e);
        }

		if (returnOnFlg && StringUtils.equals(NaikaraTalkConstants.FALSE, this.hasSearchFlg)) {
            // 戻る判定Onフラグ(returnOnFlg) = true 且つ 画面状態フラグ(hasSearchFlg) == (初期状態ではない) の場合
            this.select_rdl = searchKey;
        } else {
            // 一覧の選択したラジオボタンをクリアする
            this.select_rdl = NaikaraTalkConstants.BRANK;
        }

		try {
            // 戻る用に必要な情報を格納
            this.modelToSessionCustomerManaged();
        } catch (Exception e) {
            throw new NaiException(e);
        }

        // メッセージの設定
        if (!StringUtils.isEmpty(this.message)) {
            this.addActionMessage(this.message);
        }

		// 画面を返す
		return SUCCESS;

	}

    /**
     * SessionCustomerManaged値をModelにセット。<br>
     * <br>
     * @throws Exception
     */
    private void SessionCustomerManagedToModelBefore() throws NaiException {

        if ((SessionCustomerManaged) SessionDataUtil.getSessionData(SessionCustomerManaged.class.toString()) != null) {
            this.hasSearchFlg = ((SessionCustomerManaged) SessionDataUtil.getSessionData(SessionCustomerManaged.class
                    .toString())).getHasSearchFlg();               // 検索判断フラグ

            returnOnFlg = ((SessionCustomerManaged) SessionDataUtil.getSessionData(SessionCustomerManaged.class
                    .toString())).getReturnOnFlg();                // 戻る判定Onフラグ

            if (returnOnFlg == true) {
                // 「戻る」の場合(getReturnOnFlgにtrueが設定されるのは、お客様管理ページ選択Actionクラスのみ)
                String searchCostomerKbn = ((SessionCustomerManaged) SessionDataUtil.getSessionData(SessionCustomerManaged.class
                        .toString())).getSearchCostomerKbn();
                String searchStudentId = ((SessionCustomerManaged) SessionDataUtil.getSessionData(SessionCustomerManaged.class
                        .toString())).getSearchStudentId();
                String searchFamilyFirstJnm = ((SessionCustomerManaged) SessionDataUtil.getSessionData(SessionCustomerManaged.class
                        .toString())).getSearchFamilyFirstJnm();
                String searchFamilyFirstKnm = ((SessionCustomerManaged) SessionDataUtil.getSessionData(SessionCustomerManaged.class
                        .toString())).getSearchFamilyFirstKnm();
                String searchFamilyFirstRomaji = ((SessionCustomerManaged) SessionDataUtil.getSessionData(SessionCustomerManaged.class
                        .toString())).getSearchFamilyFirstRomaji();
                String searchOrganizationJnm = ((SessionCustomerManaged) SessionDataUtil.getSessionData(SessionCustomerManaged.class
                        .toString())).getSearchOrganizationJnm();
                String searchNikeNm = ((SessionCustomerManaged) SessionDataUtil.getSessionData(SessionCustomerManaged.class
                        .toString())).getSearchNikeNm();
                String searchObjectPeriod = ((SessionCustomerManaged) SessionDataUtil.getSessionData(SessionCustomerManaged.class
                        .toString())).getSearchObjectPeriod();
                String searchPeriodFrom = ((SessionCustomerManaged) SessionDataUtil.getSessionData(SessionCustomerManaged.class
                        .toString())).getSearchPeriodFrom();
                String searchPeriodTo = ((SessionCustomerManaged) SessionDataUtil.getSessionData(SessionCustomerManaged.class
                        .toString())).getSearchPeriodTo();

                this.model.setCostomerKbn(searchCostomerKbn);              // 検索：顧客区分
                this.model.setStudentId(searchStudentId);                  // 検索：受講者ID
                this.model.setFamilyFirstJnm(searchFamilyFirstJnm);        // 検索：受講者名(漢字)
                this.model.setFamilyFirstKnm(searchFamilyFirstKnm);        // 検索：受講者名(フリガナ)
                this.model.setFamilyFirstRomaji(searchFamilyFirstRomaji);  // 検索：受講者名(ローマ字)
                this.model.setOrganizationJnm(searchOrganizationJnm);      // 検索：組織名
                this.model.setNikeNm(searchNikeNm);                        // 検索：受講者名(ニックネーム)
                this.model.setObjectPeriod(searchObjectPeriod);            // 検索：対象期間
                this.model.setPeriodFrom(searchPeriodFrom);                // 検索：期間指定 from
                this.model.setPeriodTo(searchPeriodTo);                    // 検索：期間指定 to

            }

        }

    }

    /**
     * SessionCustomerManaged値を画面にセット。<br>
     * <br>
     * @throws Exception
     */
    private void SessionCustomerManagedToModelAfter() throws NaiException {

        if ((SessionCustomerManaged) SessionDataUtil.getSessionData(SessionCustomerManaged.class.toString()) != null) {
            this.hasSearchFlg = ((SessionCustomerManaged) SessionDataUtil.getSessionData(SessionCustomerManaged.class
                    .toString())).getHasSearchFlg();               // 検索判断フラグ

            returnOnFlg = ((SessionCustomerManaged) SessionDataUtil.getSessionData(SessionCustomerManaged.class
                    .toString())).getReturnOnFlg();                // 戻る判定Onフラグ

            if (returnOnFlg == true) {
                // 「戻る」の場合(getReturnOnFlgにtrueが設定されるのは、コード管理マスタメンテナンス登録/選択Actionクラスのみ)
                String costomerKbn = ((SessionCustomerManaged) SessionDataUtil.getSessionData(SessionCustomerManaged.class
                        .toString())).getCostomerKbn();                   // 顧客区分
                String studentId = ((SessionCustomerManaged) SessionDataUtil.getSessionData(SessionCustomerManaged.class
                        .toString())).getStudentId();                     // 受講者ID
                String familyFirstJnm = ((SessionCustomerManaged) SessionDataUtil.getSessionData(SessionCustomerManaged.class
                        .toString())).getFamilyFirstJnm();                // 受講者名(漢字)
                String familyFirstKnm = ((SessionCustomerManaged) SessionDataUtil.getSessionData(SessionCustomerManaged.class
                        .toString())).getFamilyFirstKnm();                // 受講者名(フリガナ)
                String familyFirstRomaji = ((SessionCustomerManaged) SessionDataUtil.getSessionData(SessionCustomerManaged.class
                        .toString())).getFamilyFirstRomaji();             // 受講者名(ローマ字)
                String organizationJnm = ((SessionCustomerManaged) SessionDataUtil.getSessionData(SessionCustomerManaged.class
                        .toString())).getOrganizationJnm();               // 組織名
                String nikeNm = ((SessionCustomerManaged) SessionDataUtil.getSessionData(SessionCustomerManaged.class
                        .toString())).getNikeNm();                        // 受講者名(ニックネーム)
                String objectPeriod = ((SessionCustomerManaged) SessionDataUtil.getSessionData(SessionCustomerManaged.class
                        .toString())).getObjectPeriod();                  // 対象期間
                String periodFrom = ((SessionCustomerManaged) SessionDataUtil.getSessionData(SessionCustomerManaged.class
                        .toString())).getPeriodFrom();                    // 期間指定 from
                String periodTo = ((SessionCustomerManaged) SessionDataUtil.getSessionData(SessionCustomerManaged.class
                        .toString())).getPeriodTo();                      // 期間指定 to
                searchKey = ((SessionCustomerManaged) SessionDataUtil.getSessionData(SessionCustomerManaged.class
                        .toString())).getSearchKey();                     // 検索Key：選択された明細のKey

                this.costomerKbn_rdl = costomerKbn;                // 顧客区分
                this.studentId_txt = studentId;                    // 受講者ID
                this.familyFirstJnm_txt = familyFirstJnm;          // 受講者名(漢字)
                this.familyFirstKnm_txt = familyFirstKnm;          // 受講者名(フリガナ)
                this.familyFirstRomaji_txt = familyFirstRomaji;    // 受講者名(ローマ字)
                this.organizationJnm_txt = organizationJnm;        // 組織名
                this.nikeNm_txt = nikeNm;                          // 受講者名(ニックネーム)
                this.objectPeriod_rdl = objectPeriod;              // 対象期間
                this.periodFrom_txt = periodFrom;                  // 期間指定 from
                this.periodTo_txt = periodTo;                      // 期間指定 to
                this.select_rdl = searchKey;                       // 画面遷移前に保持した値：選択された明細のKey

            }

            // SessionCustomerManagedのクリア
            SessionDataUtil.clearSessionData(SessionCustomerManaged.class.toString());

        }

    }

    /**
     * Model値・SessionCustomerManaged値をSessionCustomerManagedにセット。<br>
     * <br>
     * @throws Exception
     */
    private void modelToSessionCustomerManaged() throws NaiException {

        this.setHasSearchFlg(this.hasSearchFlg);

        // 戻る用に必要な情報を取得
        SessionCustomerManaged sessionData = new SessionCustomerManaged();
        sessionData.setReturnOnFlg(false);                      //戻る判定Onフラグ=Offとする

        sessionData.setSearchCostomerKbn(this.model.getCostomerKbn());              // 検索Key：顧客区分
        sessionData.setSearchStudentId(this.model.getStudentId());                  // 検索Key：受講者ID
        sessionData.setSearchFamilyFirstJnm(this.model.getFamilyFirstJnm());        // 検索Key：受講者名(漢字)
        sessionData.setSearchFamilyFirstKnm(this.model.getFamilyFirstKnm());        // 検索Key：受講者名(フリガナ)
        sessionData.setSearchFamilyFirstRomaji(this.model.getFamilyFirstRomaji());  // 検索Key：受講者名(ローマ字)
        sessionData.setSearchOrganizationJnm(this.model.getOrganizationJnm());      // 検索Key：組織名
        sessionData.setSearchNikeNm(this.model.getNikeNm());                        // 検索Key：受講者名(ニックネーム)
        sessionData.setSearchObjectPeriod(this.model.getObjectPeriod());            // 検索Key：対象期間
        sessionData.setSearchPeriodFrom(this.model.getPeriodFrom());                // 検索Key：期間指定 from
        sessionData.setSearchPeriodTo(this.model.getPeriodTo());                    // 検索Key：期間指定 to
        sessionData.setHasSearchFlg(this.getHasSearchFlg());                        // 検索判断フラグ
        SessionDataUtil.setSessionData(sessionData);

    }

}
