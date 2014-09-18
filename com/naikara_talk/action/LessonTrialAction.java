package com.naikara_talk.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.mstcache.CodeManagMstCache;
import com.naikara_talk.opentok_beta.ArchiveHelper;
import com.naikara_talk.opentok.api.OpenTokSDK;
import com.naikara_talk.opentok.api.constants.RoleConstants;
import com.naikara_talk.opentok.exception.OpenTokException;
import com.naikara_talk.sessiondata.SessionLessonTrial;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称 :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>カメラ起動確認テスト画面<br>
 * <b>クラス名称 :</b>カメラ起動確認テスト<br>
 * <b>クラス概要 :</b>カメラ起動確認テストAction<br>
 * <br>
 * <b>著作権 :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 *
 * @author TECS <b>変更履歴 :</b>2014/01/15 TECS 新規作成
 */
public class LessonTrialAction extends NaikaraActionSupport {

	private static final long serialVersionUID = 1L;

	/**
	 * カメラテストボタンを押下する
	 *
	 * @return
	 */
	@Override
	protected String requestService() throws NaiException {

		// TOKBOXのAPIキー
		String apiKey = null;
		// TOKBOXのSecretキー
		String apiSecret = null;
		// TOKBOXセッションID
		String tokboxSessionId = null;

		// 初期表示する場合
		if (this.getCommand() == null || "".equals(this.getCommand())) {
			log.info(NaikaraStringUtil.unionProcesslog("TOKBOX info get"));
			// TOKBOX情報を取得し、セッションに設定する
			Map<String, String> tokboxInfo = this.getTokboxInfo();
			// TOKBOXのAPIキー
			apiKey = tokboxInfo.get("apiKey");
			// TOKBOXのSecretキー
			apiSecret = tokboxInfo.get("apiSecret");
			// TOKBOXセッションID
			tokboxSessionId = tokboxInfo.get("tokboxSessionId");

			// 各種のパラメータを作成する
			this.setParameter(tokboxInfo);
		} else {
			// 新しいTOKBOX情報を取らず、セッションから取得する
			// TOKBOXのAPIキー
			apiKey = ((SessionLessonTrial)SessionDataUtil.getSessionData(SessionLessonTrial.class.toString())).getTokboxApiKey();
			// TOKBOXのSecretキー
			apiSecret = ((SessionLessonTrial)SessionDataUtil.getSessionData(SessionLessonTrial.class.toString())).getApiSecret();
			// TOKBOXセッションID
			tokboxSessionId = ((SessionLessonTrial)SessionDataUtil.getSessionData(SessionLessonTrial.class.toString())).getTokboxSessionId();
		}

		if (apiKey != null && !"".equals(apiKey) && apiSecret != null
				&& !"".equals(apiSecret) && tokboxSessionId != null
				&& !"".equals(tokboxSessionId)) {

			// Startボタンを押下する場合
			if ("start".equals(this.getCommand())) {
				// 開始ログ
				log.info(NaikaraStringUtil.unionProcesslog("LessonTrial Recording Start"));

				ArchiveHelper helper = new ArchiveHelper(apiKey, apiSecret);
				String name = "Foo";
				helper.createClient(this.getCommand(), tokboxSessionId, name);
				helper.startArchiving(tokboxSessionId, name);
			}
			// Stopボタンを押下する場合
			if ("stop".equals(this.getCommand())) {
				// 開始ログ
				log.info(NaikaraStringUtil.unionProcesslog("LessonTrial Recording Stop"));

				ArchiveHelper helper = new ArchiveHelper(apiKey, apiSecret);
				helper.createClient(this.getCommand(), tokboxSessionId,
						archiveId);
				helper.stopArchiving(tokboxSessionId, archiveId);
			}
			// Playボタンを押下する場合
			if ("list".equals(this.getCommand())) {
				// 開始ログ
				log.info(NaikaraStringUtil.unionProcesslog("LessonTrial Recording Play"));

				ArchiveHelper helper = new ArchiveHelper(apiKey, apiSecret);
				helper.createClient(this.getCommand(), tokboxSessionId,
						archiveId);
				JSONObject response = helper.listArchive(tokboxSessionId,
						archiveId);
				try {
					if (response != null) {
						if (response.getString("url") != null) {
							HttpServletResponse res = ServletActionContext.getResponse();
							res.reset();
							res.setContentType("text/html;charset=utf-8");
							PrintWriter pw = res.getWriter();
							pw.print(response);
							pw.flush();
							pw.close();
						}
					}
				} catch (JSONException e) {
					throw new NaiException();
				} catch (IOException e) {
					throw new NaiException();
				}
			}
		}

		// 自分自身に遷移する
		return "success";
	}

	/**
	 * tokbox情報を取得する
	 *
	 * @param なし
	 * @return tokbox情報Map
	 * @throws NaiException
	 */
	private Map<String, String> getTokboxInfo() throws NaiException {

		Map<String, String> map = new HashMap<String, String>();

		// TOKBOXのapiKeyとapiSecretを取得する
		CodeManagMstCache cache = CodeManagMstCache.getInstance();
		String apiKey = cache.decode(
				NaikaraTalkConstants.CODE_CATEGORY_TOXBOX_INFO,
				NaikaraTalkConstants.TOXBOX_APIKEY);
		String apiSecret = cache.decode(
				NaikaraTalkConstants.CODE_CATEGORY_TOXBOX_INFO,
				NaikaraTalkConstants.TOXBOX_APISECRET);
		try {
			OpenTokSDK sdk = new OpenTokSDK(Integer.parseInt(apiKey), apiSecret);
			String tokboxSessionId = sdk.create_session().session_id;
			String tokboxTokenId = sdk.generate_token(tokboxSessionId,
					RoleConstants.PUBLISHER);

			map.put("apiKey", apiKey);
			map.put("apiSecret", apiSecret);
			map.put("tokboxSessionId", tokboxSessionId);
			map.put("tokboxTokenId", tokboxTokenId);
		} catch (OpenTokException e) {
			throw new NaiException(e);
		}
		return map;
	}

	/**
	 * 各種のパラメータを作成する
	 *
	 * @param なし
	 * @return tokbox情報Map
	 * @throws NaiException
	 */
	private void setParameter(Map<String, String> map) throws NaiException {

		// パラメータをセッションに渡す
		SessionLessonTrial sessionData = new SessionLessonTrial();
		// TOKBOXのAPIキー
		sessionData.setTokboxApiKey(map.get("apiKey"));
		// TOKBOXのSecretキー
		sessionData.setApiSecret(map.get("apiSecret"));
		// TOKBOXセッションID
		sessionData.setTokboxSessionId(map.get("tokboxSessionId"));
		// TOKBOXトークンID
		sessionData.setTokboxTokenId(map.get("tokboxTokenId"));
		// 内容をセッションに反映する
		SessionDataUtil.setSessionData(sessionData);

		// パラメータを画面に渡す
		// TOKBOXのAPIキー
		this.setApiKey(map.get("apiKey"));
		// TOKBOXセッションID
		this.setTokboxSessionId(map.get("tokboxSessionId"));
		// TOKBOXトークンID
		this.setTokboxTokenId(map.get("tokboxTokenId"));
	}

	private String apiKey;
	private String tokboxSessionId;
	private String tokboxTokenId;
	private String archiveId;
	private String command;

	public void setArchiveId(String archiveId) {
		this.archiveId = archiveId;
	}

	public String getArchiveId() {
		return archiveId;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public String getCommand() {
		return command;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setTokboxSessionId(String tokboxSessionId) {
		this.tokboxSessionId = tokboxSessionId;
	}

	public String getTokboxSessionId() {
		return tokboxSessionId;
	}

	public void setTokboxTokenId(String tokboxTokenId) {
		this.tokboxTokenId = tokboxTokenId;
	}

	public String getTokboxTokenId() {
		return tokboxTokenId;
	}
}
