package com.naikara_talk.action;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.sessiondata.SessionTeacher;
import com.naikara_talk.sessiondata.SessionTeacherListBtnControl;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

//import org.apache.commons.lang3.StringUtils;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>お客様(個人)_予約処理<br>
 * <b>クラス名称       :</b>講師一覧（Pop）Actionクラス<br>
 * <b>クラス概要       :</b>登録済みの講師情報の検索処理を行い。<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/19 TECS 新規作成
 *                     :</b>2014/02/18 TECS 商品購入ページからの呼出追加に伴う修正
 */
public class TeacherListLoadAction extends TeacherListActionSupport {

    private static final long serialVersionUID = 1L;

    /**
     * 画面の初期表示。<br>
     * <br>
     * 画面の初期表示を設定する。<br>
     * <br>
     * @param なし<br>
     * @return String<br>
     * @throws NaiException
     */
    public String requestService() throws NaiException {

        // 開始ログ
        log.info(NaikaraStringUtil.unionProcesslog("Start"));

        // 初期データ設定
        // 性別：全て
        this.sexKbn_rdl = NaikaraTalkConstants.CHOICE_ALL_ZERO;

        // 初期データ取得
        try {
            initRadio();
        } catch (Exception e) {
            throw new NaiException(e);
        }

        // セッションをクリーンアップ
        if ((SessionTeacher) SessionDataUtil.getSessionData(SessionTeacher.class.toString()) != null) {
            SessionDataUtil.clearSessionData(SessionTeacher.class.toString());
        }

        // 2014/02/18 Add Start 商品購入ページからの呼出追加に伴う修正
        // 呼出元名の取得
        String reqHeaderInfo = request.getHeader("referer");
        String [] headerInfo;
        String callNm = NaikaraTalkConstants.BRANK;
        if (reqHeaderInfo != null) {
            headerInfo = reqHeaderInfo.split("[/]");
            callNm = headerInfo[headerInfo.length-1];
        }

        StringBuffer sbTeacher = new StringBuffer();
        sbTeacher.append(".*").append(NaikaraTalkConstants.NM_TEACHER_LIST).append(".*");        // 曖昧検索

        StringBuffer sbPGoods = new StringBuffer();
        sbPGoods.append(".*").append(NaikaraTalkConstants.NM_PURCHASE_GOODS_LIST).append(".*");  // 曖昧検索


        this.hasSearchTcontactFlg = NaikaraTalkConstants.FALSE;         // 初期化
        if ((SessionTeacherListBtnControl) SessionDataUtil.getSessionData(SessionTeacherListBtnControl.class.toString()) != null) {
            if (callNm.matches(sbTeacher.toString())) {
                // 自画面の再呼出の場合はSession情報の内容はそのまま保持
                this.hasSearchTcontactFlg = ((SessionTeacherListBtnControl) SessionDataUtil.getSessionData(SessionTeacherListBtnControl.class.toString()))
                        .getHasSearchTcontactFlg();
            } else {
                // 別画面の場合
                if (callNm.matches(sbPGoods.toString())) {
                    // 購入商品ページ 部分一致
                    this.hasSearchTcontactFlg = NaikaraTalkConstants.TRUE;      // 部分一致の場合のみ値設定
                }
            }
        } else {
            if (callNm.matches(sbPGoods.toString())) {
                // 購入商品ページ 部分一致
                this.hasSearchTcontactFlg = NaikaraTalkConstants.TRUE;          // 部分一致の場合のみ値設定
            }
        }

        // ボタン制御用に必要な情報を格納
        SessionDataUtil.clearSessionData(SessionTeacherListBtnControl.class.toString());
        SessionTeacherListBtnControl sessionData = new SessionTeacherListBtnControl();
        sessionData.setHasSearchTcontactFlg(this.hasSearchTcontactFlg);
        SessionDataUtil.setSessionData(sessionData);
        // 2014/02/18 Add End 商品購入ページからの呼出追加に伴う修正


        // 画面を返す
        return SUCCESS;
    }
}
