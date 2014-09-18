package com.naikara_talk.service;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.SQLException;

import com.naikara_talk.dbutil.DbUtil;
import com.naikara_talk.dto.LessonReservationPerformanceTrnDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.logic.LessonExecuteLogic;
import com.naikara_talk.model.LessonLauncherModel;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>レッスン画面起動<br>
 * <b>クラス名称       :</b>レッスン受講者からPDFアップロード<br>
 * <b>クラス概要       :</b>レッスン受講者からPDFアップロードService<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2014/01/15 TECS 新規作成
 *                     :</b>2014/04/22 TECS ファイルサイズの最大サイズチェック対応(1MB⇒5MB)
 *                     :</b>2014/04/22 TECS レッスンファイルのコンテンツタイプの追加対応(pdf⇒pdf、txt)
 */
public class LessonPdfUploadService implements ActionService {

	/**
	 * PDFファイルをアップロードする（受講者）
	 *
	 * @param model レッスン開始処理モデル
	 * @return アップロードの実行結果
	 * @throws NaiException
	 */
	public int uploadLessonPdf(LessonLauncherModel model) throws NaiException{

		int updatedRowCount = NaikaraTalkConstants.RETURN_CD_ERR_UPD; // 更新データ件数

		// 現在時刻とユーザIDでレッスンを検索します。
		Connection conn = null;
		try {
			conn = DbUtil.getConnection();

			LessonExecuteLogic lessonLogic = new LessonExecuteLogic(conn);

			LessonReservationPerformanceTrnDto dto = new LessonReservationPerformanceTrnDto();
			// 予約番号の設定
			dto.setReservationNo(model.getReservationNo());
			// レッスンファイルの設定
			dto.setLessonPDF(model.getFileDuringLessons());

			// 2014/04/22 Add Start ファイルタイプの追加対応(pdf⇒pdf、txt)
			// レッスンファイルのコンテンツタイプの設定
			dto.setLessonFileContentType(model.getLessonFileContentType());
			// 2014/04/22 Add End ファイルタイプの追加対応(pdf⇒pdf、txt)

			updatedRowCount = lessonLogic.uploadLessonPdf(dto);
            if (NaikaraTalkConstants.RETURN_CD_ERR_UPD == updatedRowCount) {
                // ロールバック
                try {
                    conn.rollback();
                } catch (Exception e1) {
                    e1.printStackTrace();
                    throw new NaiException(e1);
                }
                return updatedRowCount;
            }

            // コミット
			conn.commit();
		} catch (SQLException e) {
			if(conn != null){
				try {
					conn.rollback();
				} catch (SQLException sqle) {
					throw new NaiException(sqle);
				}
			}
			throw new NaiException(e);
		} finally {
            try {
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

		return updatedRowCount;
	}

	/**
	 * PDFファイルサイズチェック
	 *
	 * @param ファイルインスタンス
	 * @return チェック結果（false：範囲内、true：範囲外）
	 * @throws NaiException
	 */
	public boolean isSizeOver(File file) throws NaiException, IOException{
		FileInputStream fiStream = new FileInputStream(file);

		// 2014/04/22 Upd Start ファイルサイズの最大サイズチェック対応(1MB⇒5MB)
		// if (fiStream.available() > 1 * 1024 * 1024) { // available:推定バイト数
			// 1MB以上の場合
		if (fiStream.available() > 5 * 1024 * 1024) { // available:推定バイト数
			// 5MB以上の場合
		// 2014/04/22 Upd End   ファイルサイズの最大サイズチェック対応(1MB⇒5MB)

			return true;
		} else {
			return false;
		}
	}

	/**
	 * PDFファイルタイプチェック
	 *
	 * @param ファイルインスタンス
	 * @param ファイルタイプ
	 * @return チェック結果（false：指定ファイルタイプ、true：指定ファイルタイプ以外）
	 * @throws NaiException
	 */
	public boolean isWrongType(File file, String fileDuringLessonsContentType) throws NaiException, IOException{

		InputStream in = new FileInputStream(file);
		BufferedInputStream bis = new BufferedInputStream(in);
		String fileType = URLConnection.guessContentTypeFromStream(bis);  // 入力ストリームの種類の取得

		if (fileType == null || NaikaraTalkConstants.BRANK.equals(fileType)) {

			// text/plain
			// 2014/04/22 Upd Start ファイルタイプのチェック対応(pdf⇒pdf、txt)
			//if (!"application/pdf".equals(fileDuringLessonsContentType)) {
			if (!NaikaraTalkConstants.CONTENT_TYPE_PDF.equals(fileDuringLessonsContentType)
					&& !NaikaraTalkConstants.CONTENT_TYPE_TXT.equals(fileDuringLessonsContentType)) {
			// 2014/04/22 Upd End ファイルタイプのチェック対応(pdf⇒pdf、txt)

				return true;
			}
		} else {
			// 2014/04/22 Upd Start ファイルタイプのチェック対応(pdf⇒pdf、txt)
			//if (!"application/pdf".equals(fileType)) {
			if (!NaikaraTalkConstants.CONTENT_TYPE_PDF.equals(fileType)
					&& !NaikaraTalkConstants.CONTENT_TYPE_TXT.equals(fileType)) {
			// 2014/04/22 Upd End ファイルタイプのチェック対応(pdf⇒pdf、txt)

				return true;
			}
		}

		return false;
	}
}
