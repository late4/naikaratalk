package com.naikara_talk.action;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>マスタ保守<br>
 * <b>クラス名称　　　:</b>受講者マスタメンテナンス【単票】月謝購入情報Actionクラス。<br>
 * <b>クラス概要　　　:</b>受講者マスタメンテナンス【単票】月謝購入情報Action。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b> 2013/07/30 TECS 新規作成。
 */
public class StudentMstToStudentMonthlyFeePurchaseInformationMoveAction extends StudentMstActionSupport {

    private static final long serialVersionUID = 1L;

    /**
     * 月謝購入情報ボタンの処理。<br>
     * <br>
     * @param なし
     * @return String
     * @throws NaiException
     */
    public String requestService() throws NaiException {

        // 開始ログ
        log.info(NaikaraStringUtil.unionProcesslog("Start"));

        // Modelクラス設定
        setupModel();

        // 戻る用に必要な情報を取得/格納
        try {

            modelToSessionStudentMst();

        } catch (Exception e) {
            throw new NaiException(e);
        }

        // ヘッダの戻るリンク用
        setCurrentActionName(NaikaraTalkConstants.STUDENT_MST_LOAD);

        // 詳細画面遷移
        return NEXTPAGE;
    }

}
