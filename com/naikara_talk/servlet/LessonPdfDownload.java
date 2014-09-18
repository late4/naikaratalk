package com.naikara_talk.servlet;

import java.io.IOException;
import java.sql.Blob;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.naikara_talk.model.LessonLauncherModel;
import com.naikara_talk.service.LessonPdfDownloadService;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称:</b>共通部品 Servletクラス<br>
 * <b>クラス名称　　　:</b>レッスン受講者からPDFダウンロードクラス<br>
 * <b>クラス概要　　　:</b>レッスン受講者からPDFダウンロードServlet<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 *
 * @author TECS <b>変更履歴　　　　:</b> 2014/01/15 TECS 新規作成
 *                                 :</b> 2014/04/22 TECS レッスンファイルのコンテンツタイプの追加対応(pdf⇒pdf、txt)
 */
public class LessonPdfDownload extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected Logger logs = Logger.getLogger(this.getClass());

	/** ブラウザで開く指定 */
	protected final static String INLINE = "inline";

	/** ファイル名 */
	protected final static String LESSON_FIRE = "LessonFile";

	// 2014/04/22 Del Start ファイルタイプの追加対応(pdf⇒pdf、txt)
	///** PDFコンテントタイプ */
	//protected final static String PDF_CONTENT_TYPE ="application/pdf";
	// 2014/04/22 Del end   ファイルタイプの追加対応(pdf⇒pdf、txt)


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 開始ログ
		logs.info("LessonPdfDownload-Start");

		ServletOutputStream outStream = null;

		try {

			// 予約番号の取得
			String[] reservationNo = request.getParameterValues("reservationNo");

			// レッスン受講者からPDFダウンロードService
			LessonPdfDownloadService service = new LessonPdfDownloadService();

			// レッスン開始処理モデル（検索用）
			LessonLauncherModel model = new LessonLauncherModel();

			if (reservationNo[0] != null && !NaikaraTalkConstants.BRANK.equals(reservationNo[0])) {
				// 予約番号を設定する
				model.setReservationNo(reservationNo[0]);
			} else {
				// 予約番号が取れなければ、処理終了
				return;
			}

			// レッスン開始処理モデル（結果取得用）生成
			LessonLauncherModel returnModel = new LessonLauncherModel();

			//////////////////////////////////////////////////////
			// ダウンロード対象情報を取得する
			//////////////////////////////////////////////////////
			returnModel = service.downloadLessonPdf(model);

			// ダウンロード対象が存在しない場合、なにもしない
			if (returnModel == null) {
				return;
			} else {
				if (returnModel.getBlobDuringLessons() == null) {
					return;
				}
			}

			// Blob型からByte型へ変換して格納
			byte byteData[] = null;
			Blob duringLessons = returnModel.getBlobDuringLessons();
			if (duringLessons == null) {
				byteData = new byte[4096];
			} else {
				byteData = duringLessons.getBytes(1, (int)duringLessons.length());
			}

			StringBuffer contDisp = new StringBuffer();
			contDisp.append(INLINE).append("; filename=").append(LESSON_FIRE);

			/* Header,ContentType を設定 */
			response.setHeader("Pragma", "");
		    response.addHeader("Cache-Control", "");
		    response.addHeader("Content-Disposition", contDisp.toString());

			// 2014/04/22 Upd Start ファイルタイプの追加対応(pdf⇒pdf、txt)
			//response.setContentType(PDF_CONTENT_TYPE);
	        StringBuffer contentType = new StringBuffer();
	        String wkConType = returnModel.getLessonFileContentType();
		    if (wkConType == null) {
		    	 contentType.append(NaikaraTalkConstants.CONTENT_TYPE_PDF);    // 初期値設定(PDF)
		    } else {
		        contentType.append(wkConType);                                 // TBLに設定されている値
		    }

	        StringBuffer sbconType = new StringBuffer();
	        sbconType.append(".*").append(NaikaraTalkConstants.CONTENT_TYPE_TXT).append(".*");        // 曖昧検索
		    if (contentType.toString().matches(sbconType.toString())) {
		    	// 文字コードの追加
		    	contentType.append(";");
		    	////contentType.append(NaikaraTalkConstants.CHARSET).append(NaikaraTalkConstants.UTF_8);
		    	contentType.append(NaikaraTalkConstants.CHARSET).append(NaikaraTalkConstants.WIN_31J);
		    }

		    response.setContentType(contentType.toString());
			// 2014/04/22 Upd End   ファイルタイプの追加対応(pdf⇒pdf、txt)

			response.setContentLength(byteData.length);

			/* OutputStream を取得 */
			outStream = response.getOutputStream();

			/* PDFを出力 */
			if (byteData != null) {
				if (byteData.length > 0) {
					outStream.write(byteData);
				} else {
					outStream.write(0);
				}
			}

			logs.info("LessonPdfDownload-End");

		} catch (Exception e) {
			logs.info("Exception e.getMessage=" + e.getMessage());
			logs.info("Exception e.getStackTrace=" + e.getStackTrace());
			e.printStackTrace();
		} finally {
			if (outStream != null) {
				try {
					// 閉じて、このストリームに関連するすべてのシステムリソースを解放する
					outStream.flush();
					outStream.close();
				} catch (Exception e1) {
					logs.info("e1.getMessage=" + e1.getMessage());
					e1.printStackTrace();
				}
			}
		}
	}
}
