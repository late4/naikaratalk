package com.naikara_talk.service;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dto.SchResTLesResPerTDto;
import com.naikara_talk.model.SComReservationCancellationConfirmationModel;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>お客様(個人)_予約処理<br>
 * <b>クラス名称       :</b>予約／取消確認ページ初期化Serviceクラス<br>
 * <b>クラス概要       :</b>受講者が指定した講師の予約スケジュールを表示して、レッスン予約の登録／取消ができる。<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/08/02 TECS 新規作成
 * <b>                 :</b>2014/06/02 TECS レッスン予約に関する「応相談」対応
 */
public class SComReservationCancellationConfirmationLoadService implements ActionService {

    /**
     * 表示データの設定。<br>
     * <br>
     * 表示データの設定を行う。<br>
     * <br>
     * @param model モデル<br>
     * @return なし<br>
     * @throws なし
     */
    public void setDefaultValue(SComReservationCancellationConfirmationModel model) {

        for (SchResTLesResPerTDto dto : model.getResultList()) {

            // 遷移元：予約申込ページの場合
            if (StringUtils.equals(model.getPageId(), NaikaraTalkConstants.RESERVATION_CANCELLATION_COURSE_LIST)) {

                // 予約申込みページ(一覧部)｢予約状況｣＝”予約済”のコードの場合は、ご利用ポイントは 0
                if (StringUtils.equals(dto.getReservationKbn(), NaikaraTalkConstants.RESERV_KBN_ALREADY)) {
                    dto.setUsePoint(String.valueOf(0));

                } else if (StringUtils.equals(dto.getReservationKbn(), NaikaraTalkConstants.RESERV_KBN_YES)) {
                    // 予約申込みページ(一覧部)｢予約状況｣＝”予約可”のコードの場合は、ご取消ポイントは 0
                    dto.setCancelPoint(String.valueOf(0));
                }
            }

            // 2014/06/02 レッスン予約に関する「応相談」対応 Del Start
            //// 遷移元：コース名選択の場合
            //if (StringUtils.equals(model.getPageId(),
            //        NaikaraTalkConstants.RESERVATION_CANCELLATION_COURSE_SELECTION_LIST)) {

                // 2014/06/02 レッスン予約に関する「応相談」対応 Del Start
                //// 予約No＝””の場合は”予約可”を設定
                //if (StringUtils.isEmpty(dto.getReservationNo())
                //        || StringUtils.equals(NaikaraTalkConstants.RESERVATION_NO_NULL, dto.getReservationNo())) {
                //    dto.setReservationKbn(NaikaraTalkConstants.RESERV_KBN_YES);
                //
                //} else {
                //    // 予約No≠””場合は”予約済”を設定
                //    dto.setReservationKbn(NaikaraTalkConstants.RESERV_KBN_ALREADY);
                //}
                // 2014/06/02 レッスン予約に関する「応相談」対応 Del End

            //}
            // 2014/06/02 レッスン予約に関する「応相談」対応 Del End
        }
    }
}
