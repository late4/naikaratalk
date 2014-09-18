package com.naikara_talk.action;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dto.SchResTLesResPerTDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.service.ReservationCancellationCourseSelectionListLoadService;
import com.naikara_talk.sessiondata.SessionReservationCancellationCourseSelection;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>お客様(個人)_予約処理<br>
 * <b>クラス名称       :</b>コース名選択ページ初期化Actionクラス<br>
 * <b>クラス概要       :</b>受講者が指定した講師の予約スケジュールを表示して、レッスン予約の登録／取消ができる。<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/29 TECS 新規作成
 */
public class ReservationCancellationCourseSelectionListLoadAction extends
        ReservationCancellationCourseSelectionListActionSupport {

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

        try {
            // ヘッダ部の戻るボタン押下の場合は値の入れ替え処理
            this.SessionReservationCancellationCourseSelectionToModelBefore();

            this.setupModel();

            // セレクトボックス取得
            this.initSelect();

            if (!this.returnOnFlg) {

                ReservationCancellationCourseSelectionListLoadService service = new ReservationCancellationCourseSelectionListLoadService();

                // コードの名称の設定
                service.setManagerNm(this.model.getResultList());

                // 一覧明細の並び順
                service.sort(this.model.getResultList());
            }

        } catch (Exception e) {
            throw new NaiException(e);
        }

        return SUCCESS;
    }

    /**
     * Session値 To Model<br>
     * <br>
     * SessionReservationCancellationCourseSelection値をModelにセット。<br>
     * <br>
     * @param なし<br>
     * @return なし<br>
     * @exception Exception
     */
    private void SessionReservationCancellationCourseSelectionToModelBefore() throws Exception {

        if (StringUtils.isEmpty(this.teacherId)) {
            if ((SessionReservationCancellationCourseSelection) SessionDataUtil
                    .getSessionData(SessionReservationCancellationCourseSelection.class.toString()) != null) {
                // 講師ID
                String teacherId = ((SessionReservationCancellationCourseSelection) SessionDataUtil
                        .getSessionData(SessionReservationCancellationCourseSelection.class.toString())).getTeacherId();
                // 講師名
                String teacherNm = ((SessionReservationCancellationCourseSelection) SessionDataUtil
                        .getSessionData(SessionReservationCancellationCourseSelection.class.toString())).getTeacherNm();

                // コース
                String[] course = ((SessionReservationCancellationCourseSelection) SessionDataUtil
                        .getSessionData(SessionReservationCancellationCourseSelection.class.toString())).getCourse();

                // 講師予定予約テーブル、レッスン予実テーブルDTOリスト
                List<SchResTLesResPerTDto> schResTLesResPerTDtoList = ((SessionReservationCancellationCourseSelection) SessionDataUtil
                        .getSessionData(SessionReservationCancellationCourseSelection.class.toString()))
                        .getSchResTLesResPerTDtoList();

                // 講師ID
                this.teacherId = teacherId;
                // 講師名
                this.teacherNm = teacherNm;
                // 選択のコース
                this.course_sel = course;
                // 一覧のDTOリスト
                this.schResTLesResPerTDtoList = schResTLesResPerTDtoList;

                for (SchResTLesResPerTDto dto : this.schResTLesResPerTDtoList) {
                    // 利用ポイント
                    dto.setUsePoint(NaikaraTalkConstants.BRANK);
                    // 取消ポイント
                    dto.setCancelPoint(NaikaraTalkConstants.BRANK);
                }

                // 戻る判定Onフラグ
                this.returnOnFlg = true;

                // SessionReservationCancellationCourseSelectionのクリア
                SessionDataUtil.clearSessionData(SessionReservationCancellationCourseSelection.class.toString());
            }
        }
    }
}
