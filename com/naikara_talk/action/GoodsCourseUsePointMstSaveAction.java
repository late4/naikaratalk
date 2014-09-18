package com.naikara_talk.action;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.service.GoodsCourseUsePointMstSaveService;
import com.naikara_talk.sessiondata.SessionCourseMstGoodsMst;
import com.naikara_talk.util.DateComparatorUtil;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>マスタ保守<br>
 * <b>クラス名称       :</b>コースマスタメンテナンス(コース利用ポイント)<br>
 * <b>クラス概要       :</b>コースマスタメンテナンス(コース利用ポイント)保存Action<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/08/13 TECS 新規作成
 */
public class GoodsCourseUsePointMstSaveAction extends GoodsCourseUsePointMstActionSupport {

    private static final long serialVersionUID = 1L;

    /**
     * 保存ボタンの処理。<br>
     * <br>
     * 保存ボタンの処理。<br>
     * <br>
     * @param なし<br>
     * @return String SUCCESS or NEXTPAGE<br>
     * @exception NaiException
     */
    public String requestService() throws NaiException {

        // 開始ログ
        log.info(NaikaraStringUtil.unionProcesslog("Start"));

        // Modelクラス設定
        setupModel();
        // Serviceクラス生成
        GoodsCourseUsePointMstSaveService service = new GoodsCourseUsePointMstSaveService();

        List<String> chkResult = service.nextPageRequest(this.model);

        // エラーの場合、メッセージ設定
        try {
            if (StringUtils.equals(GoodsCourseUsePointMstSaveService.ERR_INTEGRITY_DT, chkResult.get(0))) {
                this.addActionMessage(getMessage("EN0034", new String[] { "コースマスタメンテナンス画面の提供期間と利用ポイント期間の不一致" }));
                return INPUT;
            } else if (StringUtils.equals(DateComparatorUtil.ERR_OVERLAP, chkResult.get(0))) {
                StringBuffer sb = new StringBuffer();
                sb.append(chkResult.get(1)).append("~").append(chkResult.get(2)).append("(No.")
                        .append(chkResult.get(3)).append("、No.").append(chkResult.get(4)).append(")");
                this.addActionMessage(getMessage("EN0018", new String[] { sb.toString() }));
                return INPUT;
            } else if (StringUtils.equals(DateComparatorUtil.ERR_MISSING, chkResult.get(0))) {
                StringBuffer sb = new StringBuffer();
                sb.append(chkResult.get(1)).append("~").append(chkResult.get(2));
                this.addActionMessage(getMessage("EN0034", new String[] { sb.toString() }));
                return INPUT;
            }

        } catch (Exception e) {
            throw new NaiException(e);
        }

        SessionCourseMstGoodsMst sessionData = (SessionCourseMstGoodsMst) SessionDataUtil
                .getSessionData(SessionCourseMstGoodsMst.class.toString());
        sessionData.getCourseUsePointMstList().clear();
        sessionData.getCourseUsePointMstList().addAll(sessionData.getTempCourseUsePointMstList());
        sessionData.getTempCourseUsePointMstList().clear();

        // 正常の場合 MoveActionで登録した画面遷移を削除
        try {
            removeLatestActionList();
        } catch (Exception e) {
            throw new NaiException(e);
        }
        // 詳細画面遷移
        return NEXTPAGE;
    }
}
