package com.naikara_talk.sessiondata;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称 :</b>カメラ起動確認テスト画面<br>
 * <b>クラス名称　　　:</b>カメラ起動確認テスト画面のセッション情報クラス。<br>
 * <b>クラス概要　　　:</b>カメラ起動確認テスト画面のセッション情報。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 *
 * @author TECS <b>変更履歴　　　　:</b> 2014/01/15 TECS 新規作成。
 */
public class SessionLessonTrial implements SessionData {

	/** TOKBOXのAPIキー */
	private String tokboxApiKey;

	/** TOKBOXセッションID */
	private String tokboxSessionId;

	/** TOKBOXトークンID */
	private String tokboxTokenId;

	/** TOKBOXのSecretキー */
	private String apiSecret;

    /**
     * @return tokboxApiKey
     */
    public String getTokboxApiKey() {
        return tokboxApiKey;
    }

    /**
     * @param tokboxApiKey セットする tokboxApiKey
     */
    public void setTokboxApiKey(String tokboxApiKey) {
        this.tokboxApiKey = tokboxApiKey;
    }

    /**
     * @return tokboxSessionId
     */
    public String getTokboxSessionId() {
        return tokboxSessionId;
    }

    /**
     * @param tokboxSessionId セットする tokboxSessionId
     */
    public void setTokboxSessionId(String tokboxSessionId) {
        this.tokboxSessionId = tokboxSessionId;
    }

    /**
     * @return tokboxTokenId
     */
    public String getTokboxTokenId() {
        return tokboxTokenId;
    }

    /**
     * @param tokboxTokenId セットする tokboxTokenId
     */
    public void setTokboxTokenId(String tokboxTokenId) {
        this.tokboxTokenId = tokboxTokenId;
    }

	/**
	 * @return apiSecret
	 */
	public String getApiSecret() {
		return apiSecret;
	}

	/**
	 * @param apiSecret セットする apiSecret
	 */
	public void setApiSecret(String apiSecret) {
		this.apiSecret = apiSecret;
	}
}