package com.naikara_talk.action;

import java.util.LinkedHashMap;
import java.util.List;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.service.BillDownloadService;
import com.naikara_talk.util.NaikaraStringUtil;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>会社側_管理帳票<br>
 * <b>クラス名称       :</b>請求書のダウンロードActionクラス<br>
 * <b>クラス概要       :</b>請求書のダウンロード初期処理Action<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author MIRA
 * <b>変更履歴         :</b>2013/09/08 MIRA 新規作成
 */
public class BillDownloadLoadAction extends BillDownloadActionSupport {

    private static final long serialVersionUID = 1L;

     /**
     * 画面の初期表示。<br>
     * <br>
     * 画面の初期表示。<br>
     * <br>
     * @param なし<br>
     * @return String SUCCESS <br>
     * @exception NaiException
     */
    public String requestService() throws NaiException {

        // 開始ログ
        log.info(NaikaraStringUtil.unionProcesslog("Start"));
		this.buttonFlg = "false";
        // 請求年月の設定
        this.yyyymmList = new LinkedHashMap<String, String>();
		BillDownloadService servis = new BillDownloadService();
		List<String> list = servis.getBillIssueYyyymm();
		for (int i = 0; list != null && list.size() > i ; i++){
	        this.yyyymmList.put(list.get(i), list.get(i));
			this.buttonFlg = "true";
		}

        // 画面を返す
        return SUCCESS;

    }
}
