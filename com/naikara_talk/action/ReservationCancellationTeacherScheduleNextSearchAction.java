package com.naikara_talk.action;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.service.ReservationCancellationTeacherScheduleSearchService;
import com.naikara_talk.util.NaikaraStringUtil;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>お客様(個人)_予約処理<br>
 * <b>クラス名称       :</b>予約スケジュール検索(次へ２週間)Actionクラス<br>
 * <b>クラス概要       :</b>受講者が指定した講師の予約スケジュールを表示して、レッスン予約の登録／取消ができる。<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/25 TECS 新規作成
 */
public class ReservationCancellationTeacherScheduleNextSearchAction extends
        ReservationCancellationTeacherScheduleActionSupport {

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
            // 初期化
            ReservationCancellationTeacherScheduleSearchService service = new ReservationCancellationTeacherScheduleSearchService();

            // チェック
            String params = service.check(this.model);
            if (!StringUtils.isEmpty(params)) {
                // メッセージ情報を設定する
                this.addActionMessage(getMessage("EC0049", new String[] { params, "コース名選択へ" }));
            } else {
                // 次へ２週間
                this.dayCnt = 14;
            }

            // 表示データの取得
            this.load();

        } catch (Exception e) {
            throw new NaiException(e);
        }

        // 画面を返す
        return SUCCESS;
    }
}
