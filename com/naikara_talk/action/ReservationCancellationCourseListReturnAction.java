package com.naikara_talk.action;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.sessiondata.SessionReservationCancellationCourse;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>お客様(個人)_予約処理<br>
 * <b>クラス名称       :</b>予約申込ページ戻るActionクラス<br>
 * <b>クラス概要       :</b>受講者がレッスンを予約登録、取消ができる。<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/18 TECS 新規作成
 * <b>                 :</b>2014/04/22 TECS 希望日をテキストからプルダウンへ変更対応
 */
public class ReservationCancellationCourseListReturnAction extends ReservationCancellationCourseListActionSupport {

    private static final long serialVersionUID = 1L;

    /**
     * 必須入力の項目の取得<br>
     * <br>
     * 必須入力の項目の取得を行う。<br>
     * <br>
     * @param なし<br>
     * @return SUCCESS 画面遷移フラグ<br>
     * @exception NaiException
     */
    public String requestService() throws NaiException {

        // 開始ログ
        log.info(NaikaraStringUtil.unionProcesslog("Start"));

        // 希望日
        String searchHopeDt = ((SessionReservationCancellationCourse) SessionDataUtil
                .getSessionData(SessionReservationCancellationCourse.class.toString())).getSearchHopeDt();
        // 希望時刻(From)
        String searchHopeTimeFr = ((SessionReservationCancellationCourse) SessionDataUtil
                .getSessionData(SessionReservationCancellationCourse.class.toString())).getSearchHopeTimeFr();
        // 希望時刻(To)
        String searchHopeTimeTo = ((SessionReservationCancellationCourse) SessionDataUtil
                .getSessionData(SessionReservationCancellationCourse.class.toString())).getSearchHopeTimeTo();

        // 2014/04/22 Upd Start 希望日をテキストからプルダウンへ変更対応
        // 検索Key：希望日
        //this.hopeDt_txt = searchHopeDt;
        this.hopeDt_sel = searchHopeDt;
        // 2014/04/22 Upd End   希望日をテキストからプルダウンへ変更対応

        // 検索Key：希望時刻(From)
        this.hopeTimeFr_sel = searchHopeTimeFr;
        // 検索Key：希望時刻(To)
        this.hopeTimeTo_sel = searchHopeTimeTo;

        return NEXTPAGE;
    }
}