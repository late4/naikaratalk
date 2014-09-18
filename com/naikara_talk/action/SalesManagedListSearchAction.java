package com.naikara_talk.action;

import java.util.List;

import com.naikara_talk.dto.SalesManagedListDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.service.SalesManagedListSearchService;
import com.naikara_talk.sessiondata.SessionSalesManaged;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

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

public class SalesManagedListSearchAction extends SalesManagedListActionSupport{

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

		// 選択したラジオボタンをクリアする
		this.select_rdl = NaikaraTalkConstants.BRANK;

		// 戻る判定Onフラグ
        boolean returnOnFlg = false;
        try {
			if ((SessionSalesManaged) SessionDataUtil
					.getSessionData(SessionSalesManaged.class.toString()) != null) {

                // 戻る判定Onフラグ
                returnOnFlg = ((SessionSalesManaged) SessionDataUtil
                		.getSessionData(SessionSalesManaged.class.toString())).isReturnOnFlg();
            }

			if (returnOnFlg == true) {

				// 対象年月
				this.objectYyyyMm_txt = ((SessionSalesManaged) SessionDataUtil
						.getSessionData(SessionSalesManaged.class.toString())).getObjectYyyyMm_txt();
				// 顧客区分
				this.costomerKbn_rdl = ((SessionSalesManaged) SessionDataUtil
						.getSessionData(SessionSalesManaged.class.toString())).getCostomerKbn_rdl();
				// 受講者ID／組織ID
				this.studentOrganizationId_txt = ((SessionSalesManaged) SessionDataUtil
						.getSessionData(SessionSalesManaged.class.toString())).getStudentOrganizationId_txt();
				// 組織名
				this.organizationJnm_txt = ((SessionSalesManaged) SessionDataUtil
						.getSessionData(SessionSalesManaged.class.toString())).getOrganizationJnm_txt();
				// 受講者名(フリガナ)
				this.familyFirstKum_txt = ((SessionSalesManaged) SessionDataUtil
						.getSessionData(SessionSalesManaged.class.toString())).getFamilyFirstKum_txt();
				// 選択されたデータ
				this.select_rdl = ((SessionSalesManaged) SessionDataUtil
						.getSessionData(SessionSalesManaged.class.toString())).getSelect_rdl();

				// SessionPaymentManagedのクリア
		        SessionDataUtil.clearSessionData(SessionSalesManaged.class.toString());
			}

		} catch (Exception e) {
            throw new NaiException(e);
        }

		// 画面のパラメータをモデルにセット
		setupModel();

		SalesManagedListSearchService service = new SalesManagedListSearchService();

        // 検索前チェック
        int chkResult;
        try {
        	chkResult = service.checkPreSelect(model);
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

        // 件数のチェック
        try {
        	chkResult = service.checkDetaCount(model);
            //エラーの場合、メッセージ設定
        	switch (chkResult) {
            case SalesManagedListSearchService.LIST_ZERO_CNT:
            	// 取得したデータ件数がZero件の場合
                this.addActionMessage(getMessage("EN0020", new String[] { "売上", "" }));
                return SUCCESS;
            }
        } catch (Exception e) {
            throw new NaiException(e);
        }

		// 画面一覧のデータ(集計)取得
		List<SalesManagedListDto> list = service.search(model);

		// 画面一覧のlistを設定
		this.model.setResultList(list);
		this.setObjectYyyyMm_txt(NaikaraStringUtil.converToYYYY_MM(this.model.getObjectYyyyMm()));

        // CSV作成処理
        try {
        	// 集計の部分
        	this.fileNameCount = service.csvCreateCount(list, model).getFileName();
        	// 明細の部分
        	this.fileNameDetail = service.csvCreateDetail(list, model).getFileName();

        } catch (Exception e1) {
            throw new NaiException(e1);
        }

		// 画面を返す
		return SUCCESS;

	}

}
