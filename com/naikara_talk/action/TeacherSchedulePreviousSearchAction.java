package com.naikara_talk.action;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.model.TeacherScheduleModel;
import com.naikara_talk.service.TeacherScheduleLoadService;
import com.naikara_talk.service.TeacherScheduleSearchService;
import com.naikara_talk.util.NaikaraStringUtil;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>講師スケジュール。<br>
 * <b>クラス名称       :</b>講師スケジュール検索処理Actionクラス。<br>
 * <b>クラス概要       :</b>講師スケジュールの情報検索ができる<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/09 TECS 新規作成
 * <b>                 :</b>2014/06/02 TECS レッスン予約に関する「応相談」対応
 */
public class TeacherSchedulePreviousSearchAction extends TeacherScheduleActionSupport {

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

        // TeacherScheduleLoadServiceの生成
        TeacherScheduleLoadService service = new TeacherScheduleLoadService();

        // 2014/06/02 レッスン予約に関する「応相談」対応 Del Start
        // ※ 位置変更
        // 前画面の情報
        //this.model.setUserId(this.teacherId);
        //this.model.setDateFlg(this.dateFlg - 14);
        // 2014/06/02 レッスン予約に関する「応相談」対応 Del End

        // 画面のデータをmodelにセットする
        setupModel();

        // 講師予定予約テーブルデータの取得処理
        try {
            // 関連チェック
            if (errorCheck()) {
                // 2014/06/02 レッスン予約に関する「応相談」対応 Del Start
                //this.model.setDateFlg(this.dateFlg);
                //this.model = service.select(this.model);
                // 2014/06/02 レッスン予約に関する「応相談」対応 Del End
                return SUCCESS;
            }

            // 2014/06/02 レッスン予約に関する「応相談」対応 Del Start
            // ※ 位置変更
            // 前画面の情報
            this.model.setUserId(this.teacherId);
            this.model.setDateFlg(this.dateFlg - 14);
            // 2014/06/02 レッスン予約に関する「応相談」対応 Del End

            // 対象データの取得
            this.model = service.select(this.model);

        } catch (Exception e) {
            throw new NaiException(e);
        }

        // 画面項目への設定

        /** 時間(符号) */
        this.timeMark = model.getTimeMark();
        /** 時間(分) */
        this.timeMinutes = model.getTimeMinutes();
        /** サマータイム(時間)(符号) */
        this.sumTimeMark = model.getSumTimeMark();
        /** サマータイム(分) */
        this.sumTimeMinutes = model.getSumTimeMinutes();
        /** 備考 */
        this.remark = model.getRemark();
        /** 講師ID */
        this.teacherId = model.getTeacherId();
        /** 基点日フラグ */
        this.dateFlg = model.getDateFlg();

        return SUCCESS;
    }

    /**
     * 検索前チェック。<br>
     * <br>
     * @param なし
     * @return boolean
     * @throws Exception
     */
    private boolean errorCheck() throws Exception {

        // 検索処理のサービス呼び出し
        TeacherScheduleSearchService service = new TeacherScheduleSearchService();

        // 2014/06/02 レッスン予約に関する「応相談」対応 Upd Start
        TeacherScheduleModel nowModel = model;

        // 初期表示時の情報を設定(エラー発生時の画面表示用)
        SessionTeacherScheduleToModel();

        // 関連チェック
        //StringBuffer chkResult = service.errorCheck(model);
        StringBuffer chkResult = service.errorCheck(nowModel, model);
        // 2014/06/02 レッスン予約に関する「応相談」対応 Upd End

        if (chkResult.length() != TeacherScheduleSearchService.ERR_CHECK_ON) {
            this.addActionMessage(getMessage("ET0049",
                    new String[] { chkResult.toString(), "Update", chkResult.toString(), " 更新" }));
            return true;
        }

        return false;
    }
}
