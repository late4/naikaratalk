package com.naikara_talk.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.StrutsStatics;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称:</b>共通部品 interceptorクラス<br>
 * <b>クラス名称　　　:</b>JSPファイルのキャッシュの削除クラス<br>
 * <b>クラス概要　　　:</b>JSPファイルのキャッシュの削除(ヘッダ部[戻る]対応)<br>
 * <br>
 * <b>著作権　　　　　:</b>Copyright (C) 2013 NAI, Inc. All rights reserved.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/06/12 TECS 新規作成
 */
public class RequestInterceptor extends AbstractInterceptor {

    private static final long serialVersionUID = 1L;

    @Override
    public String intercept(ActionInvocation invocation) throws Exception {

        ActionContext ac = invocation.getInvocationContext();

        // リクエストを取得する
        HttpServletRequest request = (HttpServletRequest) ac
                .get(StrutsStatics.HTTP_REQUEST);

        // レスポンスを取得する
        HttpServletResponse response = (HttpServletResponse) ac
                .get(StrutsStatics.HTTP_RESPONSE);

        // キャッシュを消去する
        response.setHeader("Pragma", "cache");
        response.setHeader("Cache-Control", "cache");
//        response.setHeader("Pragma", "No-cache");
//        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Cache-Control", "no-store");
        response.setDateHeader("Expires", 0);

        // ヘッダ部の戻るボタンの処理
        String uri = request.getRequestURI();
        int index1 = uri.lastIndexOf("/") > 0 ? uri.lastIndexOf("/") + 1 : 0;
        uri = uri.substring(index1);
        int index2 = uri.lastIndexOf(".") > 0 ? uri.lastIndexOf(".") : uri
                .length();
        uri = uri.substring(0, index2);
        int index3 = uri.lastIndexOf("!") > 0 ? uri.lastIndexOf("!") : uri
                .length();
        uri = uri.substring(0, index3);
        request.setAttribute("currentAction", uri);

        // invoke action
        String result = invocation.invoke();

        return result;
    }
}
