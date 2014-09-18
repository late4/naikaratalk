package com.naikara_talk.action;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.manager.util.SessionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.interceptor.validation.SkipValidation;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.model.UserInfo;
import com.naikara_talk.service.MessageMstService;
import com.naikara_talk.sessiondata.SessionUser;
import com.naikara_talk.util.ExecJudgment;
import com.naikara_talk.util.ExecutionJudge;
import com.naikara_talk.util.SessionDataUtil;
import com.opensymphony.xwork2.ActionSupport;

/**
 * NaikaraTalkシステムのAction規定クラスです。
 * Struts.xmlから呼び出されるexecuteメソッドの処理のテンプレートです。
 * Actionクラスはこの基底クラスを継承し、JSPとのインターフェースとなる表示データの編集と
 * サービスクラスの呼び出しを行います。
 *
 * @author nos
 *
 */
public abstract class NaikaraActionSupport extends ActionSupport implements
        ServletRequestAware, ServletResponseAware, SessionAware {

	private static final long serialVersionUID = 1L;
	protected Logger log = Logger.getLogger(this.getClass());
	protected Logger actionLog = Logger.getLogger("action");

	public static final String NOT_LOGIN = "notlogin";
	public static final String ACCESS_DENIED = "access_denied";

	/** リクエスト */
    protected HttpServletRequest request;
    /** レスポンス */
    protected HttpServletResponse response;
    /** セッション */
    protected Map<String, Object> session;
    /** 画面遷移用 */
    public static final String NEXTPAGE = "next";

    /** 実行判定定数 : 実行可能 */
	public static final int JUDGE_EXECUTABLE = 0;
    /** 実行判定定数 : ログインが必要な場合でログインしていない  */
	public static final int JUDGE_NOT_LOGIN = 1;
    /** 実行判定定数  : 実行許可なし */
	public static final int JUDGE_DENIED = 2;

    /**
     * Action実行テンプレート
     *
     */
	public String execute() throws Exception {

		int executable = this.isExecute();

//        setUserInfo();

		if ( JUDGE_EXECUTABLE == executable ){

			try {
				// service実行
        		return requestService();

			} catch (NaiException e) {
				// エラーメッセージを設定する TODO:
				log.error(e.getMsg(),e);
				return "fail";
			} catch (Exception e) {
				log.error(e.getMessage(), e);
				throw new Exception(e);
			}

		}else if (JUDGE_NOT_LOGIN == executable ) {

			return notLogin();

		} else {

			return denied();

		}


	}

	private int isExecute() throws NaiException {

		// 権限なしで初期化
		int ret = JUDGE_DENIED;

		ExecutionJudge judge = ExecutionJudge.getInstance();

		String role = null;
		String userId = null;
		SessionUser user = (SessionUser)SessionDataUtil.getSessionData(SessionUser.class.toString());
		if (user != null && StringUtils.isNotEmpty(user.getRole())) {
			role = user.getRole();
			userId = user.getUserId();
		} else {
			// ログインしていないのでユーザのロールをゲストにする
			role = SessionUser.ROLE_GUEST;
			userId = "GUEST";
			// ログインなしに設定
			ret = JUDGE_NOT_LOGIN;
		}

		// 実行判定
		if (judge.judge(this.getClass().getName(), role)) {
			// 実行可能に設定
			ret = JUDGE_EXECUTABLE;
		}

		// アクションログの出力
		actionLog.info(this.getClass().getName() + ", USER_ID=" + userId + ", ROLE=" + role +  ", RESULT=" + ret);

		return ret;
	}

	/** 共通メッセージ変数	*/
	private String commonMsg = "";

	public String getCommonMsg(){
		return this.commonMsg;
	}

	public void setCommonMsg(String msg){
		this.commonMsg = msg;
	}

    /**
     *  サービスの呼び出しの実装。
     * @throws Exception
     */
    abstract protected String requestService() throws NaiException;


	/** セッションタイムアウトまたはログインしていない場合の遷移先URL */
    private String alert_url;

	/**
	 * セッションタイムアウト遷移先URLを取得する
	 *
	 */
    public String getAlert_url() {
		return alert_url;
	}

	/**
	 * セッションタイムアウト遷移先URLを設定する
	 *
	 */
	public void setAlert_url(String alert_url) {
		this.alert_url = alert_url;
	}

	/**
     *  ログインしていない場合の対応。
     *  特別な処理が必要な場合オーバーライトしてください。
     */
    protected String notLogin() throws NaiException {

//		Properties configuration = new Properties();
//
//    	// プロパティのallert_URLを取得します
//    	try {
//    		// プロパティファイルから情報の読み込み
//    		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("naikarapram.properties");
//    		configuration.load(inputStream);
//    		setAlert_url(String.valueOf(configuration.getProperty("lessonLauncher_alert_url")));
//    	} catch (IOException e) {
//    		e.printStackTrace();
//    		throw new NaiException(e);
//    	}

    	return NOT_LOGIN;
    }


	/**
     *  実行許可がない場合の対応。
     *  特別な処理が必要な場合オーバーライトしてください。
     */
    protected String denied() throws NaiException {

//		Properties configuration = new Properties();
//
//		// プロパティのallert_URLを取得します
//    	try {
//    		// プロパティファイルから情報の読み込み
//    		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("naikarapram.properties");
//    		configuration.load(inputStream);
//    		setAlert_url(String.valueOf(configuration.getProperty("lessonLauncher_alert_url")));
//    	} catch (IOException e) {
//    		e.printStackTrace();
//    		throw new NaiException(e);
//    	}

    	return ACCESS_DENIED;
    }


    /**
     * 画面のActionNameをセッションに設定
     *
     * @param String
     *            アクション名
     */
    @SuppressWarnings("unchecked")
    @SkipValidation
    protected void setCurrentActionName(String action) {
        List<String> actionList = new ArrayList<String>();
        // sessionからactionlistを取得する
        if (this.session.containsKey("actionList")) {
            actionList = (List<String>) this.session.get("actionList");
        }
        // actionをsessionに格納する
        actionList.add(action);
        this.session.put("actionList", actionList);

    }

    /**
     * ヘッダ部の「戻る」リンク用
     *
     * @throws Exception

     */
    @SuppressWarnings("unchecked")
    @SkipValidation
    public String returnPrePage() throws Exception {
        ArrayList<String> actionList = null;
        if (this.session.containsKey("actionList")) {
            // sessionからactionlistを取得する
            actionList = (ArrayList<String>) this.session.get("actionList");
            if (actionList != null && actionList.size() > 0) {
                // 前画面のactionを取得する
                String action = actionList.get(actionList.size() - 1);
                // sessionから前画面のactionを削除する
                actionList.remove(actionList.size() - 1);

                // 前画面をRedirect
                response.sendRedirect(action + ".action");
            }
        } else {
            // セッションの無効化
            request.getSession().invalidate();
            // 未ログイン画面へ遷移
            return NOT_LOGIN;
        }
        return SUCCESS;
    }


    /**
     * 更新成功すると前画面へ遷移させるので
     * 不要となった1つ前の画面Actionを削除する
     * (ヘッダ部の「戻る」リンク用 対策)
     * @throws Exception

     */
    @SuppressWarnings("unchecked")
    @SkipValidation
    public void removeLatestActionList() throws Exception {
        ArrayList<String> actionList = null;
        if (this.session.containsKey("actionList")) {
            // sessionからactionlistを取得する
            actionList = (ArrayList<String>) this.session.get("actionList");
            if (actionList != null && actionList.size() > 0) {
                // sessionから前画面のactionを削除する
                actionList.remove(actionList.size() - 1);
            }
        }
    }

    /**
     * メッセージ取得
     *
     * @param String
     *            メッセージID
     * @param String
     *            [] メッセージパラメータ
     * @return String メッセージ内容
     * @throws Exception
     */
    public String getMessage(String messageId, String[] params)
            throws Exception {
        MessageMstService service = new MessageMstService();
        return service.getMessage(messageId, params);
    }

    /**
     * Session設定
     */
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    /**
     * ServletRequest設定
     */
    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }

    /**
     * ServletResponse設定
     */
    public void setServletResponse(HttpServletResponse response) {
        this.response = response;
    }



}
