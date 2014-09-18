package com.naikara_talk.service;

import java.io.InputStream;
import java.sql.Connection;
import java.util.List;

import com.naikara_talk.dbutil.DbUtil;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.logic.BillDownloadLogic;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>会社側_管理帳票<br>
 * <b>クラス名称       :</b>請求書のダウンロードServiceクラス<br>
 * <b>クラス概要       :</b>請求書のダウンロードCSV作成Service<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author MIRA
 * <b>変更履歴         :</b>2013/09/08 MIRA 新規作成
 */
public class BillDownloadService implements ActionService {

    /**
	 * DBからZIPファイルを取得<br>
	 * <br>
	 * 請求書格納テーブルからデータを取得する<br>
     * <br>
     * @param String<br>
     * @return InputStream<br>
     * @throws NaiException
     * @exception なし
     */
    public InputStream getApplications(String sBatchMonthly)
            throws NaiException {

		Connection conn = null;
		InputStream is = null;
		try {
			// コネクションの設定
			conn = DbUtil.getConnection();

			/** 初期化処理 **/
			BillDownloadLogic logic = new BillDownloadLogic(conn);
			is = logic.getApplications(sBatchMonthly);

        } catch (Exception e) {
			try {
				is = null;
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				conn.close();
			} catch (Exception e1) {
				e1.printStackTrace();
				System.out.println("予期せぬエラーが発生しました。");
			}
		}
        return is;
    }

    /**
	 * DBからZIPファイルを取得<br>
	 * <br>
	 * 請求書格納テーブルからデータを取得する<br>
     * <br>
     * @param String<br>
     * @return InputStream<br>
     * @throws NaiException
     * @exception なし
     */
    public List<String> getBillIssueYyyymm()
            throws NaiException {

		Connection conn = null;
		List<String> yyyymmList = null;
		try {
			// コネクションの設定
			conn = DbUtil.getConnection();

			/** 初期化処理 **/
			BillDownloadLogic logic = new BillDownloadLogic(conn);
			yyyymmList = logic.getBillIssueYyyymm();

        } catch (Exception e) {
			try {
				yyyymmList = null;
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				conn.close();
			} catch (Exception e1) {
				e1.printStackTrace();
				System.out.println("予期せぬエラーが発生しました。");
			}
		}
        return yyyymmList;
    }


}
