package com.naikara_talk.action;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.service.BillDownloadService;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>会社側_管理帳票<br>
 * <b>クラス名称       :</b>請求書のダウンロードActionクラス<br>
 * <b>クラス概要       :</b>請求書のダウンロードCSV出力Action<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author MIRA
 * <b>変更履歴         :</b>2013/09/08 MIRA 新規作成
 */
public class BillDownloadCsvOutputAction extends BillDownloadActionSupport {

    private static final long serialVersionUID = 1L;

    /**
     * CSV出力処理。<br>
     * <br>
     * CSV出力処理。<br>
     * <br>
     * @param なし<br>
     * @return String SUCCESS<br>
     * @exception NaiException
     */
    public String requestService() throws NaiException {
        return SUCCESS;
    }

    /**
     * CSVファイルの出力。<br>
     * <br>
     * CSVファイルの出力。<br>
     * <br>
     * @param なし<br>
     * @return InputStream<br>
     * @exception Exception
     */
    public InputStream getInputStream() {
		OutputStream out = null;
		InputStream in = null;
		String sBatchMonthly;
		BillDownloadService servis = new BillDownloadService();
		try {
			sBatchMonthly = this.getYyyymm();
			response.setContentType("application/octet-stream");
			response.setHeader(
					"Content-Disposition",
					"filename=\"" + sBatchMonthly + "_seikyusho.zip\"");
			in = servis.getApplications(sBatchMonthly);
			out = response.getOutputStream();
			byte[] buff = new byte[1024];
			int len = 0;
			while ((len = in.read(buff, 0, buff.length)) != -1) {
					out.write(buff, 0, len);
			}
		} catch (NaiException e) {
			e.printStackTrace();
		} catch (IOException e) {
			try {
				in.close();
				out.close();
			} catch (IOException e1) {
			}
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
				}
			}
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
				}
			}
		}
        return null;
    }
}