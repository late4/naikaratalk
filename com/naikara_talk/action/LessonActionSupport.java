package com.naikara_talk.action;

import java.io.File;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.model.LessonLauncherModel;

/**
 * <b>システム名称 :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>レッスン画面起動<br>
 * <b>クラス名称 :</b>レッスンPDF<br>
 * <b>クラス概要 :</b>レッスンPDF共通Action<br>
 * <br>
 * <b>著作権 :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 *
 * @author TECS <b>変更履歴 :</b>2014/01/15 TECS 新規作成
 *                          :</b>2014/03/28 TECS 別画面でPDFファイルを開く 処理追加対応
 */
public abstract class LessonActionSupport extends NaikaraActionSupport {

	private static final long serialVersionUID = 1L;

	public static final String CONST_JST = "(JST)";
	public static final String CONST_LOCAL = "(Local)";


	/** 予約No */
	protected String reservationNo;

	/** 予約No（評価画面用） */
	protected String reservationNo_hid;

	/** レッスンファイル */
	protected File fileDuringLessons;                 // Select File(ファイルを選ぶ)
	protected String fileDuringLessonsContentType;
//	protected String fileDuringLessonsFileName;


	/** チャット履歴 */
	////protected String chatHistory = "<div><span class='tcr'>[TECS.teacher] </span>aaaa<br></div><div><span class='tcr'>[TECS.teacher] </span>xxx<br></div>";                     // チャット履歴（chat Boad）
	////protected String hidChatHistory;                  // チャット履歴（chat Boad）
	protected String chatHistoryUpload;               // チャット履歴（Upload:アップロード）
	protected String chatHistoryDownload;             // チャット履歴（View File:ファイルを見る）
	protected String chatHistoryMyPage;               // チャット履歴（My Page:マイページ）
	protected String chatHistoryDownloadOther;        // チャット履歴（別画面でPDFファイルを開く）

	/** 別画面でPDFファイルを開くボタン */
	////protected String popLessonFileFlg;                // 別画面の表示の有無判定


	/** メッセージ */
	protected String message;

	/** 処理結果 */
	protected LessonLauncherModel model = new LessonLauncherModel();

	/**
	 * サービスの呼び出しの実装。
	 */
	abstract protected String requestService() throws NaiException;

	/**
	 * @return reservationNo
	 */
	public String getReservationNo() {
		return reservationNo;
	}

	/**
	 * @param reservationNo
	 *            セットする 予約No
	 */
	public void setReservationNo(String reservationNo) {
		this.reservationNo = reservationNo;
	}

	/**
	 * @return reservationNo_hid
	 */
	public String getReservationNo_hid() {
		return reservationNo_hid;
	}

	/**
	 * @param reservationNo_hid
	 *            セットする 予約No
	 */
	public void setReservationNo_hid(String reservationNo_hid) {
		this.reservationNo_hid = reservationNo_hid;
	}

	/**
	 * @return fileDuringLessons
	 */
	public File getFileDuringLessons() {
		return fileDuringLessons;
	}

	/**
	 * @param fileDuringLessons
	 *            セットする Select File(ファイルを選ぶ)
	 */
	public void setFileDuringLessons(File fileDuringLessons) {
		this.fileDuringLessons = fileDuringLessons;
	}

	/**
	 * @return message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 *            セットする メッセージ
	 */
	public void setMessage(String message) {
		this.message = message;
	}


	// 2014/03/28 別画面でPDFファイルを開く 処理追加対応 使用していないため削除 Del Start
	/**
	 * @return fileDuringLessonsFileName
	 */
//	public String getFileDuringLessonsFileName() {
//		return this.fileDuringLessonsFileName;
//	}

	/**
	 * @param fileDuringLessonsFileName
	 *            セットする fileDuringLessonsFileName
	 */
//	public void setFileDuringLessonsFileName(
//			String fileDuringLessonsFileName) {
//		this.fileDuringLessonsFileName = fileDuringLessonsFileName;
//	}
	// 2014/03/28 別画面でPDFファイルを開く 処理追加対応 End
	// 2014/03/28 別画面でPDFファイルを開く 処理追加対応 使用していないため削除 Del End


	/**
	 * @return fileDuringLessonsContentType
	 */
	public String getFileDuringLessonsContentType() {
		return this.fileDuringLessonsContentType;
	}

	/**
	 * @param fileDuringLessonsContentType
	 *            セットする ファイルタイプ
	 */
	public void setFileDuringLessonsContentType(
			String fileDuringLessonsContentType) {
		this.fileDuringLessonsContentType = fileDuringLessonsContentType;
	}

	/**
	 * @return chatHistoryUpload
	 */
	public String getChatHistoryUpload() {
		return this.chatHistoryUpload;
	}

	/**
	 * @param chatHistoryUpload
	 *            セットする チャット履歴（Upload:アップロード）
	 */
	public void setChatHistoryUpload(String chatHistoryUpload) {
		this.chatHistoryUpload = chatHistoryUpload;
	}

	/**
	 * @return chatHistoryDownload
	 */
	public String getChatHistoryDownload() {
		return this.chatHistoryDownload;
	}

	/**
	 * @param chatHistoryDownload
	 *            セットする チャット履歴（View File:ファイルを見る）
	 */
	public void setChatHistoryDownload(String chatHistoryDownload) {
		this.chatHistoryDownload = chatHistoryDownload;
	}

	/**
	 * @return chatHistoryMyPage
	 */
	public String getChatHistoryMyPage() {
		return this.chatHistoryMyPage;
	}

	/**
	 * @param chatHistoryMyPage
	 *            セットする チャット履歴（My Page:マイページ）
	 */
	public void setChatHistoryMyPage(String chatHistoryMyPage) {
		this.chatHistoryMyPage = chatHistoryMyPage;
	}


    // 2014/03/28 別画面でPDFファイルを開く 処理追加対応 Add Start
    /**
     * 処理結果取得<br>
     * <br>
     * 処理結果取得を行う<br>
     * <br>
     * @param なし<br>
     * @return model 処理結果<br>
     * @exception なし
     */
    public LessonLauncherModel getModel() {
        return model;
    }


    /**
     * 処理結果設定<br>
     * <br>
     * 処理結果設定を行う<br>
     * <br>
     * @param model 処理結果<br>
     * @return なし<br>
     * @exception なし
     */
    public void setModel(LessonLauncherModel model) {
        this.model = model;
    }

    /**
     * チャット履歴（chat Boad）取得<br>
     * <br>
     * チャット履歴（chat Boad）取得を行う<br>
     * <br>
     * @param なし<br>
     * @return hidChatHistory チャット履歴（chat Boad）<br>
     * @exception なし
     */
//	public String getHidChatHistory() {
//		return this.hidChatHistory;
//	}

    /**
     * チャット履歴（chat Boad）設定<br>
     * <br>
     * チャット履歴（chat Boad）設定を行う<br>
     * <br>
     * @param hidChatHistory チャット履歴（chat Boad）<br>
     * @return なし<br>
     * @exception なし
     */
//	public void setHidChatHistory(String hidChatHistory) {
//		this.hidChatHistory = hidChatHistory;
//	}

    /**
     * チャット履歴（別画面でPDFファイルを開く）取得<br>
     * <br>
     * チャット履歴（別画面でPDFファイルを開く）取得を行う<br>
     * <br>
     * @param なし<br>
     * @return chatHistoryDownloadOther チャット履歴（別画面でPDFファイルを開く）<br>
     * @exception なし
     */
    public String getChatHistoryDownloadOther() {
        return this.chatHistoryDownloadOther;
    }

    /**
     * チャット履歴（別画面でPDFファイルを開く）設定<br>
     * <br>
     * チャット履歴（別画面でPDFファイルを開く）設定を行う<br>
     * <br>
     * @param chatHistoryDownloadOther チャット履歴（別画面でPDFファイルを開く）<br>
     * @return なし<br>
     * @exception なし
     */
    public void setChatHistoryDownloadOther(String chatHistoryDownloadOther) {
        this.chatHistoryDownloadOther = chatHistoryDownloadOther;
    }

// 2014/03/28 別画面でPDFファイルを開く 処理追加対応 Add End


}
