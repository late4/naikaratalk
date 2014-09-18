package com.naikara_talk.action;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dto.CodeClassMstDto;
import com.naikara_talk.dto.CodeManagMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.logic.CodeControlMstListLogic;
import com.naikara_talk.model.CodeControlMstListModel;
import com.naikara_talk.mstcache.CodeManagMstCache;
import com.naikara_talk.service.CodeControlMstUpdateService;
import com.naikara_talk.sessiondata.SessionCodeControlMst;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;


/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>コード管理マスタメンテナンス(単票)。<br>
 * <b>クラス名称　　　:</b>コード管理マスタメンテナンス登録・更新・削除Actionクラス。<br>
 * <b>クラス概要　　　:</b>コード管理マスタメンテナンス登録・更新・削除Action。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/07/16 TECS 新規作成。
 */
public class CodeControlMstUpdateAction extends CodeControlMstActionSupport {

    private static final long serialVersionUID = 1L;

    /** 入力チェックエラー */
    private static final int ERR_CHECK_NG = -1;

    /** 排他エラー */
    private static final int ERR_CHECK_EXCLUSION = -2;

    /** データ存在エラー */
    private static final int ERR_DATA_EXISTENCE = -3;

    /** データ存在しないエラー */
    private static final int ERR_DATA_NO_EXISTENCE = -4;

    /** 追加処理(正常) */
    private static final int INSERT_OK = 1;

    /** 更新処理(正常) */
    private static final int UPDATE_OK = 2;

    /** 削除処理(正常) */
    private static final int DELETE_OK = 3;


    /**
     * 登録/更新/削除処理。<br>
     * <br>
     * @param なし
     * @return String
     * @throws NaiException
     */
    public String requestService() throws NaiException {

        // 開始ログ
        log.info(NaikaraStringUtil.unionProcesslog("Start"));

        // 画面遷移先
        String transitionScreen = SUCCESS;    // 初期値の設定

        try {
            // コード種別名(ドロップダウンリスト)の初期データの取得処理
            this.initSelectList();
        } catch (Exception e) {
            throw new NaiException(e);
        }

        // 画面のパラメータをモデルに設定
        this.setupModel();

        // エラー発生時には選択された値を保持させるために設定
        this.defaultCdCategory = this.model.getCdCategorySelected();

        // 処理区分＝新規の場合('0':'新規','1':'修正','2':'修正','3':'照会')
        if (StringUtils.equals(CodeControlMstListModel.PROS_KBN_ADD, this.processKbn_rdl)) {
            try {
                // 新規処理を実行
                int rtn = this.insert();
                switch(rtn){
                case ERR_CHECK_NG:
                    // 入力チェックエラー
                    transitionScreen = SUCCESS;
                    break;
                case ERR_DATA_EXISTENCE:
                    // データ存在エラー
                    transitionScreen = SUCCESS;
                    break;
                case INSERT_OK:
                    // 追加処理(正常)
                    transitionScreen = NEXTPAGE;
                    break;
                }
            } catch (Exception e) {
                throw new NaiException(e);
            }
        }

        // 処理区分＝修正の場合('0':'新規','1':'修正','2':'修正','3':'照会')
        if (StringUtils.equals(CodeControlMstListModel.PROS_KBN_UPD, this.processKbn_rdl)) {
            try {
                // 更新処理を実行
                int rtn = this.update();
                switch(rtn){
                case ERR_CHECK_NG:
                    // 入力チェックエラー
                    transitionScreen = SUCCESS;
                    break;
                case ERR_DATA_NO_EXISTENCE:
                    // 更新データなしエラー
                    transitionScreen = INPUT;
                    break;
                case ERR_CHECK_EXCLUSION:
                    // 排他エラー
                    transitionScreen = INPUT;
                    break;
                case UPDATE_OK:
                    // 更新処理(正常)
                    transitionScreen = NEXTPAGE;
                    break;
                }

            } catch (Exception e) {
                throw new NaiException(e);
            }
        }

        // 処理区分＝削除の場合('0':'新規','1':'修正','2':'修正','3':'照会')
        if (StringUtils.equals(CodeControlMstListModel.PROS_KBN_DEL, this.processKbn_rdl)) {
            try {
                // 削除処理を実行
                int rtn = this.delete();
                switch(rtn){
                case ERR_CHECK_NG:
                    // 入力チェックエラー
                    transitionScreen = SUCCESS;
                    break;
                case ERR_DATA_NO_EXISTENCE:
                    // 更新データなしエラー
                    transitionScreen = INPUT;
                    break;
                case ERR_CHECK_EXCLUSION:
                    // 排他エラー
                    transitionScreen = INPUT;
                    break;
                case DELETE_OK:
                    // 削除処理(正常)
                    transitionScreen = NEXTPAGE;
                    break;
                }

            } catch (Exception e) {
                throw new NaiException(e);
            }
        }

        if (transitionScreen == NEXTPAGE) {
            // 正常の場合 MoveActionで登録した画面遷移を削除
            try {
                removeLatestActionList();
            } catch (Exception e) {
                throw new NaiException(e);
            }

            //キャッシュの内容のリフレッシュ
            CodeManagMstCache cache = CodeManagMstCache.getInstance();
            cache.reload();

            //戻る用のsession情報の初期化
            //SessionCodeControlMstのクリア
            SessionDataUtil.clearSessionData(SessionCodeControlMst.class.toString());
        }


        // 一覧画面を戻る
        return transitionScreen;

    }

    /**
     * 登録処理。<br>
     * <br>
     * @param なし
     * @return int
     * @throws Exception
     */
    private int insert() throws Exception {

        // 入力チェック
        if (this.errorCheck()) {
            return ERR_CHECK_NG;
        }

        // CodeControlMstUpdateServiceクラスの生成
        CodeControlMstUpdateService service = new CodeControlMstUpdateService();

        // CodeManagMstDtoクラスの生成
        CodeManagMstDto dto = new CodeManagMstDto();

        // データの取得処理
        dto = service.select(this.model);

        if (dto != null) {
            if (dto.getReturnCode() == NaikaraTalkConstants.RETURN_CD_DATA_YES) {
                // データが存在する場合
                this.addActionMessage(getMessage(
                    "EN0019", new String[]
                            { CodeControlMstListActionSupport.CODE_MANAG_MST_TBL_JNM, "" }));
                return ERR_DATA_EXISTENCE;
            }
        }

        // 新規処理
        service.insert(this.model);

        // 登録完了メッセージの設定
        this.message = getMessage("IN0010", new String[]
                { CodeControlMstListActionSupport.CODE_MANAG_MST_TBL_JNM, "" });

        return INSERT_OK;

    }

    /**
     * 更新処理。<br>
     * <br>
     * @param なし
     * @return int
     * @throws Exception
     */
    private int update() throws Exception {

        // 入力チェック
        if (this.errorCheck()) {
            return ERR_CHECK_NG;
        }

        // CodeControlMstUpdateServiceクラスの生成
        CodeControlMstUpdateService service = new CodeControlMstUpdateService();

        // CodeManagMstDtoクラスの生成
        CodeManagMstDto dto = new CodeManagMstDto();

        // データの取得処理
        dto = service.select(this.model);

        if (dto != null) {
            if (dto.getReturnCode() == NaikaraTalkConstants.RETURN_CD_DATA_NO) {
                // データが存在しない場合
                this.addActionMessage(getMessage(
                    "EN0020", new String[]
                            { CodeControlMstListActionSupport.CODE_MANAG_MST_TBL_JNM, "" }));
                return ERR_DATA_NO_EXISTENCE;
            }
        }

        // 更新処理
        int cnt = service.update(this.model);

        if (cnt == NaikaraTalkConstants.RETURN_CD_ERR_NO_UPD) {
            // 排他エラーメッセージの設定
            String msg = getMessage("ES0014", new String[] { "", "" });
            this.addActionMessage(msg);
            return ERR_CHECK_EXCLUSION;
        }

        // 更新完了メッセージの設定
        this.message = getMessage("IN0011", new String[]
                { CodeControlMstListActionSupport.CODE_MANAG_MST_TBL_JNM, "" });

        // 一覧画面を戻る
        return UPDATE_OK;
    }


    /**
     * 削除処理。<br>
     * <br>
     * @param なし
     * @return int
     * @throws Exception
     */
    private int delete() throws Exception {

        // 入力チェック
        if (this.errorCheck()) {
            return ERR_CHECK_NG;
        }

        // CodeControlMstUpdateServiceクラスの生成
        CodeControlMstUpdateService service = new CodeControlMstUpdateService();

        // CodeManagMstDtoクラスの生成
        CodeManagMstDto dto = new CodeManagMstDto();

        // データの取得処理
        dto = service.select(this.model);

        if (dto != null) {
            if (dto.getReturnCode() == NaikaraTalkConstants.RETURN_CD_DATA_NO) {
                // データが存在しない場合
                this.addActionMessage(getMessage(
                    "EN0020", new String[]
                            { CodeControlMstListActionSupport.CODE_MANAG_MST_TBL_JNM, "" }));
                return ERR_DATA_NO_EXISTENCE;
            }
        }

        // 削除処理
        int cnt = service.delete(this.model);

        if (cnt == NaikaraTalkConstants.RETURN_CD_ERR_NO_UPD) {
            // 排他エラーメッセージの設定
            String msg = getMessage("ES0014", new String[] { "", "" });
            this.addActionMessage(msg);
            return ERR_CHECK_EXCLUSION;
        }

        // 削除完了メッセージの設定
        this.message = getMessage("IN0012", new String[]
                { CodeControlMstListActionSupport.CODE_MANAG_MST_TBL_JNM, "" });

        // 一覧画面を戻る
        return DELETE_OK;
    }


    /**
     * 登録/更新前チェック。<br>
     * <br>
     * @param なし
     * @return boolean
     * @throws Exception
     */
    private boolean errorCheck() throws Exception {

        CodeControlMstUpdateService service = new CodeControlMstUpdateService();

        // 入力チェック
        int chkResult=service.errorCheck(model, this.processKbn_rdl);

        switch(chkResult){
        case CodeControlMstUpdateService.ERR_REQUIRED_CD_CATEGORY:
            // コード種別(ドロップダウンリスト)の必須チェックエラーの場合
            this.addActionMessage(getMessage("EN0001", new String[] {"コード種別"}));
            return true;
        case CodeControlMstUpdateService.ERR_BYTE_LENGTH_MANAGER_NM:
            // 汎用フィールドの桁数チェックエラーの場合
            int len = 30;
            try {
                len = model.getManagerNmMaxLength() / 3;
                len = len * 2;
                if (len > 30) {
                    len = 253;
                }
            } catch (Exception e) {
            }
            this.addActionMessage(getMessage("EN0004", new String[]
                {"汎用フィールド", String.valueOf(len)}));
            return true;
        case CodeControlMstUpdateService.ERR_ORDER_BY_MIN:
            // 並び順の下限値チェックエラーの場合
            int min = CodeControlMstListLogic.ORDER_BY_INIT +1;
            StringBuffer work = new StringBuffer();
            work.append(String.valueOf(min)).append("以上");
            this.addActionMessage(getMessage("EN0007", new String[]
                {"並び順", work.toString()}));
            return true;
        case CodeControlMstListModel.ERR_MANAGER_NO_INS:
            // 汎用コード登録不可フラグ＝”1”(登録不可)の場合
            String cdCategoryJnm = model.getCdCategorySelected();    // 名称が取得できない場合はコード値を設定
            cdCategoryJnm = this.setCdCategoryJnm(cdCategoryJnm, this.cdCategory_sel);
            this.addActionMessage(getMessage("EN0037", new String[] { cdCategoryJnm }));
            return true;
        case CodeControlMstListModel.ERR_MANAGER_NO_DEL:
            // システム削除不可フラグ＝”1”(削除不可)の場合
            this.addActionMessage(getMessage("EN0022", new String[] { "システム" }));
            return true;
        }

        return false;
    }

    /**
     * データの取得処理。<br>
     * <br>
     * @param cdCategoryJnm
     * @param nowCdCategorySelected
     * @return cdCategoryJnm
     * @throws Exception
     */
    private String setCdCategoryJnm(String cdCategoryJnm, String nowCdCategorySelected) throws Exception {

        if (cdCategoryJnm == null || StringUtils.isEmpty(cdCategoryJnm)) {
            // 検索ボタン押下せず、遷移する場合
            cdCategoryJnm = nowCdCategorySelected;
        }
        List<CodeClassMstDto> list = this.getCdCategoryList();
        CodeClassMstDto dto = new CodeClassMstDto();
        for(int i = 0, n = list.size(); i < n; i++) {
            dto = list.get(i);
            if (StringUtils.equals(cdCategoryJnm, dto.getCdCategory())) {
                // メッセージに表示するコード種別名の取得
                cdCategoryJnm = dto.getCdCategoryJnm();
            }
        }
        return cdCategoryJnm;
    }




}
