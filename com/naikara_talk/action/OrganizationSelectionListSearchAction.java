package com.naikara_talk.action;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.service.OrganizationSelectionListSearchService;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>法人_初期処理<br>
 * <b>クラス名称       :</b>組織マイページ(組織 選択)検索Action。<br>
 * <b>クラス概要       :</b>組織マイページ(組織 選択)。<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/08/01 TECS 新規作成
 */
public class OrganizationSelectionListSearchAction extends OrganizationSelectionListActionSupport {

    private static final long serialVersionUID = 1L;

    /**
     * 検索処理。<br>
     * <br>
     * 検索処理。<br>
     * <br>
     * @param なし<br>
     * @return String SUCCESS<br>
     * @exception NaiException
     */
    public String requestService() throws NaiException {

        // 開始ログ
        log.info(NaikaraStringUtil.unionProcesslog("Start"));

        // Modelクラス設定
        setupModel();
        // Serviceクラス生成
        OrganizationSelectionListSearchService service = new OrganizationSelectionListSearchService();

        int chkResult;
        try {
            chkResult = service.checkPreSelect(model);
            // エラーの場合、メッセージ設定
            switch (chkResult) {
            case OrganizationSelectionListSearchService.ERR_ZERO_DATA:
                this.addActionMessage(getMessage("EN0020", new String[] { "組織マスタ", "" }));
                return SUCCESS;
            case OrganizationSelectionListSearchService.ERR_MAXOVER_DATA:
                this.addActionMessage(getMessage("EN0023", new String[] { "101" }));
                return SUCCESS;
            }
        } catch (Exception e) {
            throw new NaiException(e);
        }
        try {

            this.model.setResultList(service.selectList(this.model));

        } catch (Exception e) {
            throw new NaiException(e);
        }

        // 選択したラジオボタンをクリアする
        this.select_rdl = NaikaraTalkConstants.BRANK;

        return SUCCESS;
    }

}
