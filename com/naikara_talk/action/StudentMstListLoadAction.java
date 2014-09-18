package com.naikara_talk.action;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.model.StudentMstListModel;
import com.naikara_talk.service.StudentMstListLoadService;
import com.naikara_talk.sessiondata.SessionStudentListMst;
import com.naikara_talk.sessiondata.SessionStudentMst;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称:</b>マスタ保守<br>
 * <b>クラス名称　　　:</b>受講者マスタメンテナンス【一覧】初期処理Actionクラス。<br>
 * <b>クラス概要　　　:</b>受講者マスタメンテナンス【一覧】初期処理Action。<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/30 TECS 新規作成
 */
public class StudentMstListLoadAction extends StudentMstListActionSupport {

    private static final long serialVersionUID = 1L;

    /**
     * サービスの呼び出しの実装。<br>
     * <br>
     * サービスの呼び出しの実装<br>
     * <br>
     * @param なし <br>
     * @return String <br>
     * @exception NaiException
     */
    protected String requestService() throws NaiException {

        // 開始ログ
        log.info(NaikaraStringUtil.unionProcesslog("Start"));

        // 利用状態の初期取得。
        try {
            initRadio();
            initSelect();
        } catch (Exception e) {
            throw new NaiException(e);
        }

        StudentMstListLoadService service = new StudentMstListLoadService();

        StudentMstListModel model = service.load();

        // 処理区分は「照会」を選択する
        this.setProcessKbn_rdl(model.getProcessKbn_rdl());

        // お客様区分は「個人」を選択する
        this.setCustomerKbn_rdl(model.getCustomerKbn_rdl());

        // 条件キー項目５の初期化選択する
        this.setItemNm5_sel(StudentMstListModel.CONDITION_INIT_VALUE_5);

        // 条件キー条件1の初期化選択する
        this.setItemCondn1_rdl(StudentMstListModel.CONDITION_KBN_NONE);

        // 条件キー条件２の初期化選択する
        this.setItemCondn2_rdl(StudentMstListModel.CONDITION_KBN_NONE);

        // 条件キー条件３の初期化選択する
        this.setItemCondn3_rdl(StudentMstListModel.CONDITION_KBN_NONE);

        // 条件キー条件４の初期化選択する
        this.setItemCondn4_rdl(StudentMstListModel.CONDITION_KBN_NONE);

        // 条件キー条件５の初期化選択する
        this.setItemCondn5_rdl(StudentMstListModel.CONDITION_KBN_ALLEQU);

        // 条件キー５の初期化選択する
        this.setItemNm5_sel(StudentMstListModel.ITEMNM_SEL_INIT_VALUE);

        // 条件キーFrom５の初期化選択する
        this.setItemFrom5_txt(StudentMstListModel.FROM_INIT_VALUE_5);

        if (!StringUtils.isEmpty(this.message)) {
            this.addActionMessage(this.message);
        }

        // クリアセッション
        if (((SessionStudentListMst) SessionDataUtil.getSessionData(SessionStudentListMst.class.toString())) != null) {
            SessionDataUtil.clearSessionData(SessionStudentListMst.class.toString());
        }

        if (((SessionStudentMst) SessionDataUtil.getSessionData(SessionStudentMst.class.toString())) != null) {
            SessionDataUtil.clearSessionData(SessionStudentMst.class.toString());
        }

        // 画面を返す
        return SUCCESS;
    }
}
