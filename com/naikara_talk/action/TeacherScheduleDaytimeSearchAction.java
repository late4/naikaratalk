package com.naikara_talk.action;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.util.NaikaraStringUtil;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>講師スケジュール<br>
 * <b>クラス名称       :</b>予約スケジュール検索(12：00 - 18：00)Actionクラス<br>
 * <b>クラス概要       :</b>講師の予約スケジュールを表示して、レッスン予約の登録／取消ができる。<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/25 TECS 新規作成
 * <b>                 :</b>2014/06/02 TECS レッスン予約に関する「応相談」対応
 */
public class TeacherScheduleDaytimeSearchAction extends TeacherScheduleActionSupport {

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

        this.setupModel();

        try {

            this.displayFlg2 = !this.displayFlg2;

            // 表示データの取得
            this.load();

            // 2014/06/02 レッスン予約に関する「応相談」対応 Add Start
            // 画面の選択値の保持
            this.listSelSet();
            // 2014/06/02 レッスン予約に関する「応相談」対応 Add End

        } catch (Exception e) {
            throw new NaiException(e);
        }

        // 画面を返す
        return SUCCESS;
    }
}
