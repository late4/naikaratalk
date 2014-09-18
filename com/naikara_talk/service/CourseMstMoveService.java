package com.naikara_talk.service;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.model.CourseMstModel;
import com.naikara_talk.util.DateUtil;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>マスタ保守<br>
 * <b>クラス名称       :</b>コースマスタメンテナンス(単票)<br>
 * <b>クラス概要       :</b>コースマスタメンテナンス(単票)講師支払比率の設定Service<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/08/13 TECS 新規作成
 */
public class CourseMstMoveService implements ActionService {

    /** ドロップダウンリストの必須チェック　(大分類) */
    public static final int ERR_NOT_SELECT_COURSELARGE_SEL = 1;

    /** ドロップダウンリストの必須チェック　(中分類) */
    public static final int ERR_NOT_SELECT_COURSEMEDIUM_SEL = 2;

    /** ドロップダウンリストの必須チェック　(小分類) */
    public static final int ERR_NOT_SELECT_COURSESMALL_SEL = 3;

    /** チェック：｢提供期間：開始日｣　＞　｢提供期間：終了日｣　の場合 */
    public static final int ERR_INTEGRITY_DT = 4;

    /**
     * 単票へ画面遷移する制御処理<br>
     * <br>
     * 単票へ画面遷移する制御処理<br>
     * <br>
     * @param TeacherMstModel<br>
     * @return int <br>
     * @exception なし
     */
    public int nextPageRequest(CourseMstModel model) {

        // 大分類のコード値＝”0000”の場合
        if (StringUtils.equals(NaikaraTalkConstants.CHOICE_ALL_ZERO, model.getBigClassificationCd())) {
            return ERR_NOT_SELECT_COURSELARGE_SEL;
        }
        // 中分類のコード値＝”0000”の場合
        if (StringUtils.equals(NaikaraTalkConstants.CHOICE_ALL_ZERO, model.getMiddleClassificationCd())) {
            return ERR_NOT_SELECT_COURSEMEDIUM_SEL;
        }
        // 小分類のコード値＝”0000”の場合
        if (StringUtils.equals(NaikaraTalkConstants.CHOICE_ALL_ZERO, model.getSmallClassificationCd())) {
            return ERR_NOT_SELECT_COURSESMALL_SEL;
        }
        // ｢提供期間：開始日｣　＞　｢提供期間：終了日｣　の場合
        if (DateUtil.dateCompare2(NaikaraStringUtil.converToYYYYMMDD(model.getUseStrDt()),
                NaikaraStringUtil.converToYYYYMMDD(model.getUseEndDt()))) {
            return ERR_INTEGRITY_DT;
        }
        return CourseMstModel.PROCESS_NORMAL;
    }
}
