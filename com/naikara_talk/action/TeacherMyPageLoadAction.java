package com.naikara_talk.action;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.service.TeacherMyPageLoadService;
import com.naikara_talk.sessiondata.SessionUser;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>講師用個別マイページ。<br>
 * <b>クラス名称       :</b>講師用個別マイページ初期処理Actionクラス。<br>
 * <b>クラス概要       :</b>講師が下記の情報照会ができる<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/09 TECS 新規作成
 */
public class TeacherMyPageLoadAction extends TeacherMyPageActionSupport {

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

        // 表示データを取得する
        TeacherMyPageLoadService service = new TeacherMyPageLoadService();
        // 前画面の情報
        this.model.setUserId(((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString())).getUserId());
        // 講師予定予約テーブルデータの取得処理
        try {
            this.model = service.selectTeacherMst(this.model);
            this.model = service.select(this.model);
        } catch (Exception e) {
            throw new NaiException(e);
        }
        /** スクール側からお知らせ */
        this.osiraseJ = model.getOsiraseJ();
        /** スクール側からお知らせ */
        this.osiraseE = model.getOsiraseE();
        /** 利用者ID */
        this.userId = model.getUserId();
        /** ニックネーム  */
        this.nickAnm = model.getNickAnm();
        /** 問合せメールアドレス(講師用個別マイページ用) */
        this.mailAdd = model.getMailAdd();
        if (!StringUtils.isEmpty(this.message)) {
            this.addActionMessage(this.message);
        }
        // ヘッダの戻るリンク用
        setCurrentActionName(NaikaraTalkConstants.TEACHER_MY_PAGE_LOAD);
        return SUCCESS;
    }
}
