package com.naikara_talk.action;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>入金管理ページ<br>
 * <b>クラス名称　　　:</b>入金管理ページクラス。<br>
 * <b>クラス概要　　　:</b>入金管理ページSearchAction。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/08/09 TECS 新規作成。
 */

import java.util.List;

import com.naikara_talk.dto.SalesManagedListDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.service.CsvFileDeleteService;
import com.naikara_talk.service.SalesManagedListMoveService;
import com.naikara_talk.service.SalesManagedListSearchService;
import com.naikara_talk.sessiondata.SessionSalesManaged;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

public class SalesManagedListMoveAction  extends SalesManagedListActionSupport{

	private static final long serialVersionUID = 1L;

	/**
	 * 画面の初期表示。<br>
	 * <br>
	 *
	 * @param なし
	 * @return String
	 * @throws NaiException
	 */
	public String requestService() throws NaiException {

		// 開始ログ
		log.info(NaikaraStringUtil.unionProcesslog("Start"));

        try {
        	// 顧客区分の初期取得
			initRadio();
		} catch (Exception e) {
			throw new NaiException(e);
		}

		// 画面のパラメータをモデルにセット
		setupModel();

		SalesManagedListSearchService searchService = new SalesManagedListSearchService();

        // 検索前チェック
        int chkResult;
        try {
        	chkResult = searchService.checkPreSelect(model);
            //エラーの場合、メッセージ設定
        	switch (chkResult) {
        	case SalesManagedListSearchService.OBJECT_YYYYMM_NULL:
        		// 対象年月が必須チェック
        		this.addActionMessage(getMessage("EN0001",new String[] { "対象年月" }));
                return SUCCESS;
        	case SalesManagedListSearchService.OBJECT_YYYYMM_DATE:
        		// 対象年月の日付チェック
        		this.addActionMessage(getMessage("EN0035", new String[] { "対象年月" }));
                return SUCCESS;
            }
        } catch (Exception e) {
            throw new NaiException(e);
        }

        SalesManagedListMoveService moveService = new SalesManagedListMoveService();

        if (!moveService.checkNull(model)) {

        	// 一覧部の「選択」が必須入力チェック エラーの場合

        	// 件数のチェック
            try {
            	chkResult = searchService.checkDetaCount(model);
                //エラーの場合、メッセージ設定
            	switch (chkResult) {
                case SalesManagedListSearchService.LIST_ZERO_CNT:
                	// 取得したデータ件数がZero件の場合
                    this.addActionMessage(getMessage("EN0020", new String[] { "売上", "" }));
                    return SUCCESS;
                }

            	// 画面一覧のデータ取得処理
        		List<SalesManagedListDto> list = searchService.search(model);

        		// 画面一覧のlistを設定
        		this.model.setResultList(list);

        		// 一覧部の「選択」が必須入力チェック エラーのメッセージ情報を設定する
        		this.addActionMessage(getMessage("EN0015", new String[] {"一覧部の左の選択", "" }));

            } catch (Exception e) {
                throw new NaiException(e);
            }

    		return SUCCESS;

        } else {

        	// 一覧部の「選択」が必須入力チェック 正常の場合

        	try {
    			// 戻る用に必要な情報を取得/格納
    	        this.modelToSessionPaymentManaged();

    			// ヘッダの戻るリンク用
    	        setCurrentActionName(NaikaraTalkConstants.SALES_MANAGED_LIST_SEARCH);

            } catch (Exception e) {
                throw new NaiException(e);
            }
        }

        // 過去に作成したCSVファイルの削除処理
        CsvFileDeleteService.removeCsvFile();

		// 入金管理ページ【詳細】画面へ遷移する
		return NEXTPAGE;

	}

	/**
	 * 戻る用に必要な情報を取得/格納。
	 * Model値・SessionPaymentManaged値をSessionPaymentManagedにセット。
	 * @param なし
	 * @throws Exception
	 */
	private void modelToSessionPaymentManaged() throws Exception {

        // 戻る用に必要な情報を格納
		SessionSalesManaged sessionData = new SessionSalesManaged();

		sessionData.setSelect_rdl(select_rdl);                                             // 選択
        sessionData.setObjectYyyyMm_txt(objectYyyyMm_txt);                                 // 対象年月
        sessionData.setCostomerKbn_rdl(costomerKbn_rdl);                                   // 顧客区分
        sessionData.setStudentOrganizationId_txt(studentOrganizationId_txt);               // 受講者ID／組織ID
        sessionData.setOrganizationJnm_txt(organizationJnm_txt);                           // 組織名
        sessionData.setFamilyFirstKum_txt(familyFirstKum_txt);                             // 受講者名(フリガナ)
        sessionData.setReturnOnFlg(true);                                                  // 戻る判定Onフラグ
        SessionDataUtil.setSessionData(sessionData);

    }

}
