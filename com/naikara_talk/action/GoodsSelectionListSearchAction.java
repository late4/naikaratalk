package com.naikara_talk.action;

import java.util.ArrayList;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.service.GoodsSelectionListSearchService;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>マスタ保守<br>
 * <b>クラス名称       :</b>コースマスタメンテナンス(商品選択)<br>
 * <b>クラス概要       :</b>コースマスタメンテナンス(商品選択)検索Action<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/08/05 TECS 新規作成
 */
public class GoodsSelectionListSearchAction extends GoodsSelectionListActionSupport {

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
        GoodsSelectionListSearchService service = new GoodsSelectionListSearchService();

        int chkResult;
        try {
            chkResult = service.checkPreSelect(model);
            // エラーの場合、メッセージ設定
            switch (chkResult) {
            case GoodsSelectionListSearchService.ERR_ZERO_DATA:
                this.addActionMessage(getMessage("EN0020", new String[] { "販売商品マスタ", "" }));
                return SUCCESS;
            case GoodsSelectionListSearchService.ERR_MAXOVER_DATA:
                this.addActionMessage(getMessage("EN0023", new String[] { "101" }));
                return SUCCESS;
            }
        } catch (Exception e) {
            throw new NaiException(e);
        }
        // 表示データの取得
        try {
            this.model.setResultList(service.selectList(this.model));
            this.select_chk_list = new ArrayList<String>();
            this.searchFlg = NaikaraTalkConstants.TRUE;
            this.error_noSelect = getMessage("EN0015", new String[] { "一覧部の左の選択" });
            this.error_gtFive = getMessage("EN0040", new String[] { "選択のチェック", "6" });
            this.error_gtOne = getMessage("EN0040", new String[] { "選択のチェック", "2" });
        } catch (Exception e) {
            throw new NaiException(e);
        }

        return SUCCESS;
    }
}
