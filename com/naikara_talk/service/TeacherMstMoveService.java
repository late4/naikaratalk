package com.naikara_talk.service;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.model.TeacherMstModel;
import com.naikara_talk.util.DateUtil;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>マスタ保守<br>
 * <b>クラス名称       :</b>講師マスタメンテナンス(単票)<br>
 * <b>クラス概要       :</b>講師マスタメンテナンス(単票)講師支払比率の設定Service<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/08/01 TECS 新規作成
 */
public class TeacherMstMoveService implements ActionService {

    /** チェック：｢契約日｣　＞　｢契約期間：開始日｣　の場合 */
    public static final int ERR_INTEGRITY_DT = 1;

    /** チェック：｢契約期間：開始日｣　＞　｢契約期間：終了日｣ */
    public static final int ERR_INTEGRITY_DATE = 2;

    /**
     * 単票へ画面遷移する制御処理<br>
     * <br>
     * 単票へ画面遷移する制御処理<br>
     * <br>
     * @param TeacherMstModel<br>
     * @return int <br>
     * @exception なし
     */
    public int nextPageRequest(TeacherMstModel model) {

        // ｢契約日｣と｢契約期間：開始日｣が両方入力されている場合は、大小チェックを行う
        if (!StringUtils.isEmpty(model.getContractDt())) {
            // ｢契約日｣　＞　｢契約期間：開始日｣　の場合
            if (DateUtil.dateCompare1(model.getContractDt(), model.getContractStartDt())) {
                return ERR_INTEGRITY_DT;
            }
        }

        // ｢契約期間：開始日｣　＞　｢契約期間：終了日｣　の場合
        if (DateUtil.dateCompare1(model.getContractStartDt(), model.getContractEndDt())) {
            return ERR_INTEGRITY_DATE;
        }

        return TeacherMstModel.PROCESS_NORMAL;
    }
}
