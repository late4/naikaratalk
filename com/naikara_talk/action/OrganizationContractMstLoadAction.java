package com.naikara_talk.action;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.model.OrganizationContractMstListModel;
import com.naikara_talk.service.OrganizationContractMstLoadService;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>組織契約情報登録。<br>
 * <b>クラス名称       :</b>組織契約情報登録初期処理Actionクラス。<br>
 * <b>クラス概要       :</b>組織契約情報登録表示を行う<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/09 TECS 新規作成
 */
public class OrganizationContractMstLoadAction extends OrganizationContractMstActionSupport {

    private static final long serialVersionUID = 1L;

    /**
     * 画面の初期表示。<br>
     * <br>
     *
     * @param なし<br>
     * @return String SUCCESS <br>
     * @exception NaiException
     */
    public String requestService() throws NaiException {

        // 開始ログ
        log.info(NaikaraStringUtil.unionProcesslog("Start"));

        // 表示データを取得する
        OrganizationContractMstLoadService service = new OrganizationContractMstLoadService();

        try {
            // 住所(地域)と住所(都道府県)等の初期取得。
            initSelect();
        } catch (Exception e) {
            throw new NaiException(e);
        }

        if (!this.processKbn_rdl.equals(OrganizationContractMstListModel.PROS_KBN_ADD)) {

            String[] values = this.select_rdl.substring(1, this.select_rdl.length() - 1)
                    .replaceAll(NaikaraTalkConstants.HALF_SPACE, NaikaraTalkConstants.BRANK)
                    .split(String.valueOf(NaikaraTalkConstants.COMMA));
            // 組織ID
            this.model.setOrganizationId(values[0]);
            // 連番
            this.model.setConsSeq(Integer.parseInt(values[1]));
            // 処理区分
            this.model.setProcessKbn_rdl(this.processKbn_rdl);
            try {

                // 表示データの取得
                this.model = service.select(this.model);
                // データが存在しない場合
                if (model.getReturnCode() == NaikaraTalkConstants.RETURN_CD_DATA_NO) {
                    this.consSeq_txt = "";
                    this.tempPointNum_txt = "";
                    this.message = getMessage("EN0020", new String[] { "組織マスタ", "" });
                    removeLatestActionList();
                    // 前画面(一覧)へ戻り、エラーメッセージを表示
                    return ERROR;
                }

            } catch (Exception e) {
                throw new NaiException(e);
            }

            // modelを画面のデータにセットする
            modelToJsp();

            if (this.processKbn_rdl.equals(OrganizationContractMstListModel.PROS_KBN_MAKE)) {
                this.consSeq_txt = "";
            }
        }

        return SUCCESS;
    }
}
