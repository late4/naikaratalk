package com.naikara_talk.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.naikara_talk.dbutil.DbUtil;
import com.naikara_talk.dto.LessonReservationPerformanceTrnDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.logic.LessonExecuteLogic;
import com.naikara_talk.model.LessonLauncherModel;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>レッスン画面起動<br>
 * <b>クラス名称 :</b>レッスン受講者からPDFダウンロード<br>
 * <b>クラス概要 :</b>レッスン受講者からPDFダウンロードService<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 *
 * @author TECS <b>変更履歴　　　　:</b>2014/01/15 TECS 新規作成。
 *                                 :</b>2014/04/22 TECS レッスンファイルのコンテンツタイプの追加対応(pdf⇒pdf、txt)
 */
public class LessonPdfDownloadService implements ActionService {

	/**
	 * ダウンロード<br>
	 * <br>
	 * PDFファイルをダウンロードする<br>
	 * <br>
	 *
	 * @param DetailDescriptionModel 画面パラメータ<br>
	 * @return DetailDescriptionModel 新画面パラメータ<br>
	 * @exception NaiException
	 */
	public LessonLauncherModel downloadLessonPdf(LessonLauncherModel model)
			throws NaiException {

		Connection conn = null;
		try {
			conn = DbUtil.getConnection();
			LessonExecuteLogic logic = new LessonExecuteLogic(conn);

			// 検索実行
			LessonReservationPerformanceTrnDto resultDto = logic.getLessonInfoByReservationNo(model.getReservationNo());

			if (resultDto == null) {
				// DTO値をModelにセット
				model.setBlobDuringLessons(null);

				// 2014/04/22 Add Start ファイルタイプの追加対応(pdf⇒pdf、txt)
				model.setLessonFileContentType(null);
				// 2014/04/22 Add End   ファイルタイプの追加対応(pdf⇒pdf、txt)
			} else {
				// DTO値をModelにセット
				model.setBlobDuringLessons(resultDto.getFileDuringLessons());

				// 2014/04/22 Add Start ファイルタイプの追加対応(pdf⇒pdf、txt)
				model.setLessonFileContentType(resultDto.getLessonFileContentType());
				// 2014/04/22 Add End   ファイルタイプの追加対応(pdf⇒pdf、txt)

			}

			return model;
		} catch (SQLException sqle) {
			throw new NaiException(sqle);
		} catch (Exception e) {
			throw new NaiException(e);
		} finally {
			try {
				conn.close();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
}
