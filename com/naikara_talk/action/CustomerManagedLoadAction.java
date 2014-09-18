package com.naikara_talk.action;

import java.math.BigDecimal;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.service.CustomerManagedLoadService;
import com.naikara_talk.sessiondata.SessionCustomerManaged;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>お客様管理ページ詳細<br>
 * <b>クラス名称　　　:</b>お客様管理ページ詳細クラス。<br>
 * <b>クラス概要　　　:</b>お客様管理ページ詳細初期処理Action。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/08/20 TECS 新規作成。
 */

public class CustomerManagedLoadAction extends CustomerManagedActionSupport{

	private static final long serialVersionUID = 1L;

	/** レイアウト初期 */
	private static int LYOUT_INIT = 35;
	/** 名前のbyte */
	private static int NAME_BYTE_LENGTH = 30;
	/** 組織のbyte */
	private static int ORG_BYTE_LENGTH = 94;
	/** レイアウトマイナス数 */
	private static int LYOUTM = 5;

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
			// 顧客区分と対象期間の初期取得
			initRadio();
		} catch (Exception e) {
			throw new NaiException(e);
		}

		// 前画面の情報が引継
		if ((SessionCustomerManaged) SessionDataUtil.getSessionData(SessionCustomerManaged.class.toString()) != null) {
            // 戻る用に必要な情報を取得
            String searchObjectPeriod = ((SessionCustomerManaged) SessionDataUtil.getSessionData(SessionCustomerManaged.class
                    .toString())).getSearchObjectPeriod();
            String searchPeriodFrom = ((SessionCustomerManaged) SessionDataUtil.getSessionData(SessionCustomerManaged.class
                    .toString())).getSearchPeriodFrom();
            String searchPeriodTo = ((SessionCustomerManaged) SessionDataUtil.getSessionData(SessionCustomerManaged.class
                    .toString())).getSearchPeriodTo();

            this.objectPeriod_rdl = searchObjectPeriod;            // 検索Key：対象期間
            this.periodFrom_txt = searchPeriodFrom;                // 検索Key：期間指定 from
            this.periodTo_txt = searchPeriodTo;                    // 検索Key：期間指定 to

        }

		String split[] = this.select_rdl.split("#");
		this.studentId = split[0];                                   // 受講者ID
		this.familyFirstJnm = split[1];                              // 受講者名(漢字)
		this.familyFirstKnm = split[2];                              // 受講者名(フリガナ)
		this.familyFirstRomaji = split[3];                           // 受講者名(ローマ字)
		this.nikeNm = split[4];                                      // 受講者名(ニックネーム)
		this.mailAddress1 = split[5];                                // メールアドレス
		this.organizationJnm = split[6];                             // 組織名
		this.freeUsePoint = new BigDecimal(split[7]);                // 利用無償ポイント(合計)
		this.compensationUsePoint = new BigDecimal(split[8]);        // 利用有償ポイント(合計)
		this.goodsPurchaseYen = new BigDecimal(split[9]);            // ポイント外商品購入
		this.costomerKbn = split[10];                                // 顧客区分

		int lyout = LYOUT_INIT;
		if (familyFirstJnm.getBytes().length > NAME_BYTE_LENGTH || familyFirstKnm.getBytes().length > NAME_BYTE_LENGTH) {
			lyout = lyout - LYOUTM;
		}
		if (familyFirstRomaji.getBytes().length > NAME_BYTE_LENGTH || nikeNm.getBytes().length > NAME_BYTE_LENGTH) {
			lyout = lyout - LYOUTM;
		}
		if (organizationJnm.getBytes().length > ORG_BYTE_LENGTH) {
			lyout = lyout - LYOUTM;
		}
		this.request.setAttribute("lyout", lyout);

		// 顧客区分名の取得
		this.costomerKbnNm = this.costomerKbn_rdll.get(this.costomerKbn);
		// 対象期間名の取得
		this.objectPeriodNm = this.objectPeriod_rdll.get(this.objectPeriod_rdl);

		// 画面のパラメータをモデルにセット
		setupModel();

		CustomerManagedLoadService service = new CustomerManagedLoadService();

		this.periodFrom_txt = NaikaraStringUtil.converToYYYY_MM_DD(model.getPeriodFrom());              // 検索Key：期間指定 from
        this.periodTo_txt = NaikaraStringUtil.converToYYYY_MM_DD(model.getPeriodTo());                  // 検索Key：期間指定 to

		// 画面一覧のlistを設定
		this.model.setResultList(service.selectList(model));

		// 画面を返す
		return SUCCESS;
	}

}
