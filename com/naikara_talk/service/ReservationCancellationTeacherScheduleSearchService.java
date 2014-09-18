package com.naikara_talk.service;

import java.util.LinkedHashMap;

import com.naikara_talk.dto.CodeManagMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.model.ReservationCancellationTeacherScheduleModel;
import com.naikara_talk.mstcache.CodeManagMstCache;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>お客様(個人)_予約処理<br>
 * <b>クラス名称       :</b>予約スケジュール検索Serviceクラス<br>
 * <b>クラス概要       :</b>受講者が指定した講師の予約スケジュールを表示して、レッスン予約の登録／取消ができる。<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/25 TECS 新規作成
 */
public class ReservationCancellationTeacherScheduleSearchService implements ActionService {

    /**
     * チェック。<br>
     * <br>
     * チェックを行う。<br>
     * <br>
     * @param model モデル<br>
     * @return String メッセージパラメタ<br>
     * @throws NaiException
     * @exception なし
     */
    public String check(ReservationCancellationTeacherScheduleModel model) throws NaiException {
        // 初期化
        String params = NaikaraTalkConstants.BRANK;
        String[] selected = null;

        // チェック区分（日付、時刻単位のチェックBOX)がONのものが１つ以上存在するかどうかのチェック
        if (model.getDetails0() != null) {
            // 明細0チェックBOX
            selected = model.getDetails0();
        } else if (model.getDetails1() != null) {
            // 明細1チェックBOX
            selected = model.getDetails1();
        } else if (model.getDetails2() != null) {
            // 明細2チェックBOX
            selected = model.getDetails2();
        } else if (model.getDetails3() != null) {
            // 明細3チェックBOX
            selected = model.getDetails3();
        }

        if (selected != null) {

            // 汎用フィールド名の取得
            CodeManagMstCache cache = CodeManagMstCache.getInstance();
            // レッスン時刻の名称一覧取得
            LinkedHashMap<String, CodeManagMstDto> lessonTmList = cache
                    .getList(NaikaraTalkConstants.CODE_CATEGORY_LESSON_TM_S);

            // 明細チェックBOXのデータを取得
            String[] values = selected[0].substring(1).substring(0, selected[0].length() - 1)
                    .replaceAll(NaikaraTalkConstants.HALF_SPACE, NaikaraTalkConstants.BRANK)
                    .split(String.valueOf(NaikaraTalkConstants.COMMA));

            // メッセージパラメタを設定
            params = NaikaraStringUtil.unionName(NaikaraStringUtil.converToM_D(NaikaraStringUtil.delSlash(values[0])),
                    lessonTmList.get(values[1]).getManagerNm());
        }

        return params;
    }
}
