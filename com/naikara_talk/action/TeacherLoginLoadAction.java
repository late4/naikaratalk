package com.naikara_talk.action;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.util.NaikaraStringUtil;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>初期処理<br>
 * <b>クラス名称　　　:</b>サービス提供ページ初期処理Actionクラス。<br>
 * <b>クラス概要　　　:</b>講師認証の初期表示処理。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/07/02 TECS 新規作成。
 */
public class TeacherLoginLoadAction extends TeacherLoginActionSupport {

    private static final long serialVersionUID = 1L;

    /**
     * 画面の初期表示<br>
     * <br>
     * 画面項目の初期処理を行う<br>
     * <br>
     * @param なし<br>
     * @return SUCCESS 画面遷移フラグ<br>
     * @exception NaiException
     */
    public String requestService() throws NaiException {

        // 開始ログ
        log.info(NaikaraStringUtil.unionProcesslog("Start"));
        // 画面を返す
        return SUCCESS;

    }

}
