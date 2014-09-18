package com.naikara_talk.action;


import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.interceptor.validation.SkipValidation;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.model.CodeControlMstListModel;
import com.naikara_talk.model.CodeControlMstModel;
import com.naikara_talk.service.CodeControlMstLoadService;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>コード管理マスタメンテナンス(単票)。<br>
 * <b>クラス名称　　　:</b>コード管理マスタメンテナンス初期処理Actionクラス。<br>
 * <b>クラス概要　　　:</b>コード管理マスタメンテナンス初期処理Action。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/07/16 TECS 新規作成。
 */
public class CodeControlMstLoadAction extends CodeControlMstActionSupport {

    private static final long serialVersionUID = 1L;


    /**
     * 画面の初期表示。<br>
     * <br>
     * @param なし
     * @return String
     * @throws NaiException
     */
    @SkipValidation
    public String requestService() throws NaiException {

        // 開始ログ
        log.info(NaikaraStringUtil.unionProcesslog("Start"));

        // 画面のパラメータをモデルに設定
        this.setupModel();

        // CodeControlMstLoadServiceクラスの生成
        CodeControlMstLoadService service = new CodeControlMstLoadService();

        // 前画面から処理区分を基にした処理区分名の判定、コード種別名をModelに設定
        CodeControlMstModel workModel= service.initLoad(model);

        // 前画面から処理区分を画面に設定
        this.processKbn_rdl = workModel.getProcessKbn_Rdl();
        this.processKbn_txt = workModel.getProcessKbn_Txt();

        // コード種別名の初期取得。
        try {
            this.initSelectList();
        } catch (Exception e) {
             throw new NaiException(e);
        }

        this.defaultCdCategory = "";    // コード種別(ドロップダウン)の選択値の初期化
        if (!StringUtils.equals(CodeControlMstListModel.PROS_KBN_ADD, this.processKbn_rdl)) {
            // 前画面から検索ボタン押下時のコード種別を画面に設定
            this.defaultCdCategory = workModel.getDefaultCdCategory();
            this.cdCategory_sel = this.defaultCdCategory;
        }

        //******************************
        //新規・修正・照会の処理
        //******************************

        // 処理区分＝新規の場合('0':'新規','1':'修正','2':'削除','3':'照会')
        if (StringUtils.equals(CodeControlMstListModel.PROS_KBN_ADD, this.processKbn_rdl)) {
            // 前画面からの汎用コードをクリア
            this.managerCd_txt = NaikaraTalkConstants.BRANK;
            return SUCCESS;
        }

        // 処理区分＝照会の場合('0':'新規','1':'修正','2':'削除','3':'照会')
        if (StringUtils.equals(CodeControlMstListModel.PROS_KBN_REF, this.processKbn_rdl)) {
            // 照会用のコメントを設定
            this.setComment(NaikaraTalkConstants.PROCESSKBN_REF_COMMENT);
        }

        // 処理区分＝修正、削除、照会の場合('0':'新規','1':'修正','2':'削除','3':'照会')
        if (StringUtils.equals(CodeControlMstListModel.PROS_KBN_UPD, this.processKbn_rdl)
                || StringUtils.equals(CodeControlMstListModel.PROS_KBN_DEL, this.processKbn_rdl)
                || StringUtils.equals(CodeControlMstListModel.PROS_KBN_REF, this.processKbn_rdl) ) {

            try {
                if (service.Exists(model) < 1) {
                    // データが存在しない場合 (データ件数 < 1 の場合)
                    StringBuffer work = new StringBuffer();
                    work.append("(コード種別=").append(model.getCdCategorySelected());
                    work.append("、汎用コード=").append(model.getManagerCd());
                    work.append(")");
                    this.message = getMessage("EN0020", new String[] { CodeControlMstListActionSupport.CODE_MANAG_MST_TBL_JNM, work.toString() });
                    // 前画面(一覧)へ戻り、エラーメッセージを表示
                    return ERROR;
                } else {
                    // データが存在する場合

                    //表示データの取得処理
                    this.load();
                    return SUCCESS;
                 }
            } catch (Exception e) {
                throw new NaiException(e);
            }
        }

        // 画面を返す
        return SUCCESS;

    }


    /**
     * 初期処理、表示データの取得。<br>
     * <br>
     * @param なし
     * @return なし
     * @throws Exception
     */
    private void load() throws Exception {

        // CodeControlMstLoadServiceクラスの設定
        CodeControlMstLoadService service = new CodeControlMstLoadService();

        // 前画面の情報をmodelへ設定
        this.formToModel();

        // データの取得処理
        model = service.select(this.model);

        // modelの情報を画面へ設定
        this.modelToForm();

    }


    /**
     * Form値をCodeControlMstModelに設定。<br>
     * <br>
     * @throws Exception
     */
    private void formToModel() throws Exception {

        // 前画面の情報をmodelへ設定
        this.model.setProcessKbn_Rdl(this.processKbn_rdl);          // 処理区分(ラジオボタン)
        this.model.setProcessKbn_Txt(this.processKbn_txt);          // 処理区分(ラベル)
        this.model.setDefaultCdCategory(this.defaultCdCategory);    // コード種別(検索ボタン押下時の選択値)
        this.model.setManagerCd(this.managerCd_txt);                // 汎用コード

    }


    /**
     * CodeControlMstModel値をFormに設定。<br>
     * <br>
     * @throws Exception
     */
    private void modelToForm() throws Exception {

        // modelの情報を画面へ設定
        this.cdCategory_sel = model.getDefaultCdCategory();           // コード種別名(jsp) name
        this.cdCategoryList = model.getCdCategoryList();              // コード種別名(jsp) list
        this.defaultCdCategory = model.getDefaultCdCategory();        // コード種別名(jsp)-value 初期値
        this.managerCd_txt = model.getManagerCd();                    // 汎用コード(jsp)
        this.managerNm_txt = model.getManagerNm();                    // 汎用フィールド(jsp)
        this.orderBy_txt = model.getOrderBy();                        // 並び順(jsp)
        this.remark_txa = model.getRemark();                          // 備考(jsp)
        this.recordVerNo = String.valueOf(model.getRecordVerNo());    // レコードバージョン番号

    }


}
