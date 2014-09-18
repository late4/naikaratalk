package com.naikara_talk.action;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.service.StudentMyPageLoadService;
import com.naikara_talk.sessiondata.SessionUser;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>お客様(個人)_初期処理<br>
 * <b>クラス名称       :</b>受講者用マイページ初期化Actionクラス<br>
 * <b>クラス概要       :</b>受講者の情報を表示。<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/08/16 TECS 新規作成
 */
public class StudentMyPageLoadAction extends StudentMyPageActionSupport {

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

        // お知らせの初期取得。
        try {
            initRadio();
        } catch (Exception e) {
            throw new NaiException(e);
        }

        if (!StringUtils.isEmpty(this.message)) {
            String[] messages = this.message.split(NaikaraTalkConstants.NEW_LINE_CODE_WIN);
            for (String msg : messages) {
                this.addActionMessage(msg);
            }
        }

        try {
            this.load();
            return SUCCESS;
        } catch (Exception e) {
            throw new NaiException(e);
        }

    }

    /**
    * 初期処理、表示データの取得。<br>
    * <br>
    * 初期処理、表示データの取得。<br>
    * <br>
    * @param なし<br>
    * @return なし <br>
    * @exception Exception
    */
    private void load() throws Exception {

        // 表示データを取得する
        StudentMyPageLoadService service = new StudentMyPageLoadService();

        SessionUser sessionUserData = ((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString()));

        // 受講者ID
        this.studentId = sessionUserData.getUserId();
        // ニックネーム
        this.nickNm = sessionUserData.getUserNm();
        this.model.setStudentId(this.studentId);
        // 現在予約中のレッスン
        this.model.setLessonResultList(service.selectLesson(this.model));
        // 購入済みの商品一覧
        this.model.setGoodResultList(service.selectGood(this.model));
        // 講師からのコメント
        this.model.setLesResultList(service.selectLes(this.model));
        // スクールからのコメント
        model = service.selectStu(this.model);
        // スクールからのコメント
        this.schoolCmt = model.getSchoolCmt();
        // メールアドレス
        this.mailAddress = model.getMailAddress();
        // 性別区分
        this.genderKbn = model.getGenderKbn();
        // ポイント購入済フラグ
        this.pointPurchaseFlg = model.getPointPurchaseFlg();
        // 保護者の同意書の入手フラグ
        this.consentDocumentAcquisitionFlg = model.getConsentDocumentAcquisitionFlg();
        // 顧客区分
        this.customerKbn = model.getCustomerKbn();
        // DMの履歴一覧
        this.model.setDmResultList(service.selectDm(this.model));
        // 購入毎のポイント残高
        this.model.setPointResultList(service.selectPoint(this.model));
        model = service.selectBalancePoint(this.model);
        // ポイント残高合計
        this.balancePoint = model.getBalancePoint();
        model = service.selectAge(this.model);
        // 成人区分
        this.adult = model.getAdult();

    }
}
