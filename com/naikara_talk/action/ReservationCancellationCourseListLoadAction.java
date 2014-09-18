package com.naikara_talk.action;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.sessiondata.SessionReservationCancellationCourse;
import com.naikara_talk.util.DateUtil;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>お客様(個人)_予約処理<br>
 * <b>クラス名称       :</b>予約申込ページ初期化Actionクラス<br>
 * <b>クラス概要       :</b>受講者がレッスンを予約登録、取消ができる。<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/18 TECS 新規作成
 * <b>                 :</b>2014/04/22 TECS 希望時刻(To)の初期値変更対応
 */
public class ReservationCancellationCourseListLoadAction extends ReservationCancellationCourseListActionSupport {

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

        // 2014/04/22 Del Start 希望日をテキストからプルダウンへ変更対応
        // 希望日：システム日付
        //this.hopeDt_txt = NaikaraStringUtil.converToYYYY_MM_DD(DateUtil.getSysDate());
        this.hopeDt_sel = NaikaraStringUtil.converToYYYY_MM_DD(DateUtil.getSysDate());
        // 2014/04/22 Del End   希望日をテキストからプルダウンへ変更対応

        // 予約状況：全て
        this.reservationKbn_rdl = NaikaraTalkConstants.CHOICE_ALL_ZERO;
        // 性別：全て
        this.sexKbn_rdl = NaikaraTalkConstants.CHOICE_ALL_ZERO;

        // 2014/04/22 Add Start 希望時刻(To)の初期値変更対応
        this.hopeTimeFr_sel = NaikaraTalkConstants.PROCESS_TIME_KBN_1;
        this.hopeTimeTo_sel = NaikaraTalkConstants.PROCESS_TIME_KBN_36;
        // 2014/04/22 Add End   希望時刻(To)の初期値変更対応


        // 初期データ取得
        try {
            initRadio();
            initSelect();
        } catch (Exception e) {
            throw new NaiException(e);
        }

        // セッションをクリーンアップ
        if ((SessionReservationCancellationCourse) SessionDataUtil
                .getSessionData(SessionReservationCancellationCourse.class.toString()) != null) {
            SessionDataUtil.clearSessionData(SessionReservationCancellationCourse.class.toString());
        }

        // 画面を返す
        return SUCCESS;
    }
}
