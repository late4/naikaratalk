package com.naikara_talk.action;

import java.util.List;

import com.naikara_talk.dto.StudentMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.service.StudentSelectionListSearchService;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>お客様(個人)_初期登録。<br>
 * <b>クラス名称　　　:</b>マイページ(お客様_個人)検索Actionクラス。<br>
 * <b>クラス概要　　　:</b>マイページ(お客様_個人)検索Action。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/07/31 TECS 新規作成。
 */
public class StudentSelectionListSearchAction extends StudentSelectionListActionSupport {

    private static final long serialVersionUID = 1L;

    /**
     * 初期処理<br>
     * <br>
     * 画面項目の初期処理を行う<br>
     * <br>
     * @param なし<br>
     * @return SUCCESS 画面遷移フラグ<br>
     * @exception NaiException
     */
    public String requestService() throws NaiException {

        // 開始ログ
        log.info(NaikaraStringUtil.unionProcesslog("Start"));

        // 画面のパラメータセット
        setupModel();

        // サービスの初期化
        StudentSelectionListSearchService service = new StudentSelectionListSearchService();

        int chkResult;

        try {
            //選択ボタンの表示/非表示制御
            this.hasSearchFlg = Boolean.toString(Boolean.FALSE);

            // データ有無をチェック
            chkResult = service.checkPreSelect(model);

            switch (chkResult) {

            case StudentSelectionListSearchService.ERR_ZERO_DATA:

                this.addActionMessage(getMessage("EN0020", new String[] {
                        "受講者マスタ", "" }));

                return SUCCESS;

            case StudentSelectionListSearchService.ERR_MAXOVER_DATA:

                this.addActionMessage(getMessage("EN0023",
                        new String[] { "101" }));

                return SUCCESS;

            }

        } catch (Exception e) {

            throw new NaiException(e);

        }

        try {

            List<StudentMstDto> data = service.selectList(this.model);

            // 検索結果
            this.model.setResultList(data);

            for (int i = 0, n = data.size(); i < n; i++) {
                String studentId = data.get(i).getStudentId();
                if (!(studentId == null || "".equals(studentId))) {
                    this.hasSearchFlg = Boolean.toString(Boolean.TRUE);
                }
                break;
            }

        } catch (Exception e) {

            throw new NaiException(e);

        }

        // 一覧から選択された明細データ(jsp)
        this.select_rdl = NaikaraTalkConstants.BRANK;

        return SUCCESS;
    }
}