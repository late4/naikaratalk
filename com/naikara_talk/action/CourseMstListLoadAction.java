package com.naikara_talk.action;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.model.CourseMstListModel;
import com.naikara_talk.service.CourseMstListLoadService;
import com.naikara_talk.sessiondata.SessionCourseMst;
import com.naikara_talk.sessiondata.SessionCourseMstGoodsMst;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>マスタ保守<br>
 * <b>クラス名称       :</b>コースマスタメンテナンス(一覧)<br>
 * <b>クラス概要       :</b>コースマスタメンテナンス(一覧)初期処理Action<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/08/02 TECS 新規作成
 */
public class CourseMstListLoadAction extends CourseMstListActionSupport {

    private static final long serialVersionUID = 1L;

    /**
     * 画面の初期表示。<br>
     * <br>
     * 画面の初期表示。<br>
     * <br>
     * @param なし<br>
     * @return String SUCCESS <br>
     * @exception NaiException
     */
    public String requestService() throws NaiException {

        // 開始ログ
        log.info(NaikaraStringUtil.unionProcesslog("Start"));

        // 大分類、中分類、小分類の初期取得。
        try {
            initRadio();
        } catch (Exception e) {
            throw new NaiException(e);
        }

        CourseMstListLoadService service = new CourseMstListLoadService();

        CourseMstListModel model = service.load();

        // 処理区分は「照会」を選択する
        this.setProcessKbn_rdl(model.getProcessKbn());

        if (!StringUtils.isEmpty(this.message)) {
            this.addActionMessage(this.message);
        }

        // SessionCourseMstのクリア
        if ((SessionCourseMst) SessionDataUtil.getSessionData(SessionCourseMst.class.toString()) != null) {
            SessionDataUtil.clearSessionData(SessionCourseMst.class.toString());
        }

        // SessionCourseMstGoodsMstのクリア
        if ((SessionCourseMstGoodsMst) SessionDataUtil.getSessionData(SessionCourseMstGoodsMst.class.toString()) != null) {
            SessionDataUtil.clearSessionData(SessionCourseMstGoodsMst.class.toString());
        }

        // 画面を返す
        return SUCCESS;

    }
}