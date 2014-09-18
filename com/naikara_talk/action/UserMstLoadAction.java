package com.naikara_talk.action;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.interceptor.validation.SkipValidation;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.model.UserMstListModel;
import com.naikara_talk.model.UserMstModel;
import com.naikara_talk.service.UserMstLoadService;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>マスタ保守。<br>
 * <b>クラス名称　　　:</b>利用者マスタメンテナンス初期処理Actionクラス。<br>
 * <b>クラス概要　　　:</b>利用者マスタメンテナンス初期処理Action。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/07/04 TECS 新規作成。
 */
public class UserMstLoadAction extends UserMstActionSupport {

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
    @SkipValidation
    public String requestService() throws NaiException {

        // 開始ログ
        log.info(NaikaraStringUtil.unionProcesslog("Start"));

        // 画面のパラメータセット
        setupModel();

        // サービスの初期化
        UserMstLoadService service = new UserMstLoadService();

        // 初期値を設定
        UserMstModel workModel = service.initLoad(model);

        // 処理区分(前画面の引き継ぎ情報)
        this.processKbn_rdl = workModel.getProcessKbn_rdl();

        // 画面表示処理区分
        this.processKbn_txt = workModel.getProcessKbn_txt();

        try {

            initRadio();

        } catch (Exception e) {

            throw new NaiException(e);

        }

        // ******************************
        // 新規・修正・照会の処理
        // ******************************

        // 処理区分＝新規の場合('0':'新規','1':'修正','2':'照会')
        if (StringUtils.equals(UserMstListModel.PROS_KBN_ADD, this.processKbn_rdl)) {

            // 利用者ID
            this.userId_lbl = NaikaraTalkConstants.BRANK;

            // 利用期間開始日
            this.utilizationStart_txt = workModel.getUtilizationStart_txt();

            // 利用期間終了日
            this.utilizationEnd_txt = workModel.getUtilizationEnd_txt();

            return SUCCESS;
        }

        // 処理区分＝照会の場合('0':'新規','1':'修正','2':'照会')
        if (StringUtils.equals(UserMstListModel.PROS_KBN_REF, this.processKbn_rdl)) {

            // 処理区分コメント
            this.setProcessKbnComment(NaikaraTalkConstants.PROCESSKBN_REF_COMMENT);
        }

        // 処理区分＝修正、照会の場合('0':'新規','1':'修正','2':'照会')
        if (StringUtils.equals(UserMstListModel.PROS_KBN_UPD, this.processKbn_rdl)
                || StringUtils.equals(UserMstListModel.PROS_KBN_REF, this.processKbn_rdl)) {

            try {

                // データが存在しない場合
                if (service.getExists(model) == NaikaraTalkConstants.RETURN_CD_DATA_NO) {

                    this.message = getMessage("EN0020", new String[] { "利用者マスタ", "" });

                    removeLatestActionList();

                    // 前画面(一覧)へ戻り、エラーメッセージを表示
                    return ERROR;

                } else {

                    // データが存在する場合
                    // 表示データの取得処理
                    this.load();

                    return SUCCESS;

                }
            } catch (Exception e) {

                throw new NaiException(e);

            }
        }

        return SUCCESS;
    }

    /**
     * データ取得<br>
     * <br>
     * 初期処理、表示データの取得<br>
     * <br>
     * @param なし<br>
     * @return なし<br>
     * @exception Exception
     */
    private void load() throws Exception {

        // サービスの初期化
        UserMstLoadService service = new UserMstLoadService();

        // 利用者ID
        this.model.setUserId_lbl(this.userId_lbl);
        // 処理区分(前画面情報)
        this.model.setProcessKbn_rdl(this.processKbn_rdl);
        // 画面表示処理区分
        this.model.setProcessKbn_txt(this.processKbn_txt);

        // データを取得
        model = service.select(this.model);

        /** 利用者ID */
        this.userId_lbl = model.getUserId_lbl();
        /** パスワード */
        this.password_txt = model.getPassword_txt();
        /** 名前(姓) */
        this.nameFamiy_txt = model.getNameFamiy_txt();
        /** 名前(名) */
        this.nameFirst_txt = model.getNameFirst_txt();
        /** フリガナ(姓) */
        this.furiganaFamiy_txt = model.getFuriganaFamiy_txt();
        /** フリガナ(名) */
        this.furiganaFirst_txt = model.getFuriganaFirst_txt();
        /** ローマ字(姓) */
        this.romajiFamiy_txt = model.getRomajiFamiy_txt();
        /** ローマ字(名) */
        this.romajiFirst_txt = model.getRomajiFirst_txt();
        /** 電話番号1 */
        this.telephone1_txt = model.getTelephone1_txt();
        /** 電話番号2 */
        this.telephone2_txt = model.getTelephone2_txt();
        /** 生年月日の年 */
        this.birthdayYy_txt = model.getBirthdayYy_txt();
        /** 生年月日の月 */
        this.birthdayMm_txt = model.getBirthdayMm_txt();
        /** 生年月日の日 */
        this.birthdayDd_txt = model.getBirthdayDd_txt();
        /** 郵便番号 */
        this.zipCode_txt = model.getZipCode_txt();
        /** 住所(地域) */
        this.address1_sel = model.getAddress1_sel();
        /** 住所(都道府県) */
        this.address2_sel = model.getAddress2_sel();
        /** 住所(市区町村 等) */
        this.address3_txt = model.getAddress3_txt();
        /** 住所(ﾋﾞﾙ、ﾏﾝｼｮﾝ名 等) */
        this.address4_txt = model.getAddress4_txt();
        /** 性別 */
        this.sex_rdl = model.getSex_rdl();
        /** メールアドレス1 */
        this.mailAdd1_txt = model.getMailAdd1_txt();
        /** メールアドレス2 */
        this.mailAdd2_txt = model.getMailAdd2_txt();
        /** メールアドレス3 */
        this.mailAdd3_txt = model.getMailAdd3_txt();
        /** 利用期間開始日 */
        this.utilizationStart_txt = NaikaraStringUtil.converToYYYY_MM_DD(model.getUtilizationStart_txt());
        /** 利用期間終了日 */
        this.utilizationEnd_txt = NaikaraStringUtil.converToYYYY_MM_DD(model.getUtilizationEnd_txt());
        /** 勤務拠点 */
        this.contract_txt = model.getContract_txt();
        /** 種別 */
        this.userKbn_rdl = model.getUserKbn_rdl();
        /** 備考 */
        this.remarks_txa = model.getRemarks_txa();
        /** レコードバージョン番号 */
        this.recordVerNo = String.valueOf(model.getRecordVerNo());
        /** チェック用種別 */
        this.userKbn_chk = model.getUserKbn_chk();
    }
}