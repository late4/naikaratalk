package com.naikara_talk.service;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.model.StudentSelectionListModel;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>お客様(個人)_初期登録。<br>
 * <b>クラス名称　　　:</b>マイページ(お客様_個人)登録選択Serviceクラス。<br>
 * <b>クラス概要　　　:</b>マイページ(お客様_個人)登録選択Service。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/07/31 TECS 新規作成。
 */
public class StudentSelectionListMoveService implements ActionService {

    /** チェック：一覧部の項目が選択なし */
    public static final int ERR_NO_SELECT = 1;

    /** チェック：一覧画面へ戻る */
    public static final int ERR_LIST_BACK = 2;

    /**
     * 遷移処理<br>
     * <br>
     * 次画面へ画面遷移する制御処理<br>
     * <br>
     * @param model 画面パラメータ<br>
     * @param select_rdl 選択行受講者ID<br>
     * @return int エラー有無フラグ<br>
     * @exception なし
     */
    public int nextPageRequest(StudentSelectionListModel model,
            String select_rdl) {

        // 一覧部の項目が選択されていない場合は、メッセージ情報を設定する
        if (StringUtils.isEmpty(select_rdl)) {

            return ERR_NO_SELECT;
        }

        return StudentSelectionListModel.CHECK_OK;
    }
}