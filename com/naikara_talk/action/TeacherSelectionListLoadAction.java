package com.naikara_talk.action;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.util.NaikaraStringUtil;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>一覧_講師選択ページ。<br>
 * <b>クラス名称       :</b>一覧_講師選択ページ初期処理Actionクラス。<br>
 * <b>クラス概要       :</b>講師一覧の情報ができる<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/09 TECS 新規作成
 */
public class TeacherSelectionListLoadAction extends TeacherSelectionListActionSupport {

    private static final long serialVersionUID = 1L;

    /**
     * 画面の初期表示。<br>
     * <br>
     *
     * @param なし<br>
     * @return String SUCCESS <br>
     * @exception NaiException
     */
    public String requestService() throws NaiException {

        // 開始ログ
        log.info(NaikaraStringUtil.unionProcesslog("Start"));

        if (!StringUtils.isEmpty(this.message)) {
            this.addActionMessage(this.message);
        }
        this.hasSearchFlg = Boolean.toString(Boolean.FALSE);
        return SUCCESS;
    }
}
