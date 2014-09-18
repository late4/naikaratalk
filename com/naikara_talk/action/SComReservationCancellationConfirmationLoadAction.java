package com.naikara_talk.action;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dto.SchResTLesResPerTDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.service.SComReservationCancellationConfirmationLoadService;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>お客様(個人)_予約処理<br>
 * <b>クラス名称       :</b>予約／取消確認ページ初期化Actionクラス<br>
 * <b>クラス概要       :</b>受講者が指定した講師の予約スケジュールを表示して、レッスン予約の登録／取消ができる。<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/08/02 TECS 新規作成
 */
public class SComReservationCancellationConfirmationLoadAction extends
        SComReservationCancellationConfirmationActionSupport {

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

        this.load();

        this.setupModel();

        SComReservationCancellationConfirmationLoadService service = new SComReservationCancellationConfirmationLoadService();

        service.setDefaultValue(this.model);

        // ポイント合計の設定
        this.setPointSum();

        // 画面を返す
        return SUCCESS;
    }

    /**
     * 画面のパラメータの取得。<br>
     * <br>
     * 画面のパラメータの取得を行う。<br>
     * <br>
     * @param なし<br>
     * @return なし<br>
     * @throws なし
     */
    private void load() {

        if (this.schResTLesResPerTDtoList != null && this.course_sel != null) {

            int i = 0;

            for (SchResTLesResPerTDto dto : this.schResTLesResPerTDtoList) {

                // 隠し項目：予約No＝””の場合
                if (StringUtils.isEmpty(dto.getReservationNo())
                        || StringUtils.equals(NaikaraTalkConstants.RESERVATION_NO_NULL, dto.getReservationNo())) {

                    if (i < this.course_sel.length) {

                        String[] selected = this.course_sel[i].substring(1, this.course_sel[i].length() - 1).split(
                                String.valueOf(NaikaraTalkConstants.COMMA));

                        // コースコードの取得
                        dto.setCourseCd(selected[0]);
                        // コース名の取得
                        dto.setCourseNm(selected[1]);

                        i++;
                    }
                }
            }
        }
    }

}
