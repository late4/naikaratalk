package com.naikara_talk.action;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.model.OrganizationContractMstModel;
import com.naikara_talk.service.OrganizationContractMstLoadService;
import com.naikara_talk.sessiondata.SessionUser;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>組織契約情報登録。<br>
 * <b>クラス名称       :</b>組織契約情報登録Actionクラス。<br>
 * <b>クラス概要       :</b>組織契約情報登録の情報ができる<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/08/01 TECS 新規作成
 */
public abstract class OrganizationContractMstActionSupport extends NaikaraActionSupport {
    private static final long serialVersionUID = 1L;

    // 画面表示のタイトル
    protected String title = "組織契約情報登録";

    // Help画面名
    protected String helpPageId = "HelpOrganizationContractMst.html";

    /**
     * サービスの呼び出しの実装。
     */
    abstract protected String requestService() throws NaiException;

    /**
     * 住所(地域)と住所(都道府県)等の初期取得。<br>
     * <br>
     * @param なし
     * @return なし
     * @throws Exception
     */
    public void initSelect() throws Exception {
        OrganizationContractMstLoadService service = new OrganizationContractMstLoadService();
        // 住所(地域)を取得する
        this.addressAreaCd_sell = service.selectCodeMst(NaikaraTalkConstants.CODE_CATEGORY_REGION);
        // 住所(都道府県)を取得する
        this.addressPrefectureCd_sell = service.selectCodeMst(NaikaraTalkConstants.CODE_CATEGORY_STATE);
        // 性別を取得する
        this.managGenderKbn_rdl = service.selectCodeMst(NaikaraTalkConstants.CODE_CATEGORY_GENDER);
        // 支払サイトを取得する
        this.requestPaymentSiteKbn_sell = service.selectCodeMst(NaikaraTalkConstants.CODE_CATEGORY_PAYMENT_SITE_KBN);
    }

    /**
     * 画面のパラメータをモデルにセット。<br>
     * <br>
     * @param なし
     * @return なし
     */
    public void setupModel() {

        this.model.setProcessKbn_rdl(this.processKbn_rdl);
        /** 組織ID */
        this.model.setOrganizationId(this.organizationId_txt);
        /** 連番 */
        if (this.consSeq_txt.equals("")) {
            this.model.setConsSeq(1);
        } else {
            this.model.setConsSeq(Integer.parseInt(this.consSeq_txt));
        }
        /** パスワード */
        this.model.setPassword(this.password_txt);
        /** パスワード確認 */
        this.model.setPasswordCheck(this.passwordCheck_txt);
        /** 組織名 */
        this.model.setOrganizationJnm(this.organizationJnm_txt);
        /** 組織名(フリガナ) */
        this.model.setOrganizationKnm(this.organizationKnm_txt);
        /** 組織名(ローマ字) */
        this.model.setOrganizationRomaji(this.organizationRomaji_txt);
        /** 電話番号 */
        this.model.setTel(this.tel_txt);
        /** 郵便番号 */
        this.model.setZipCd(this.zipCd_txt);
        /** 住所(地域)コード */
        this.model.setAddressAreaCd(this.addressAreaCd);
        /** 住所(都道府県)コード */
        this.model.setAddressPrefectureCd(this.addressPrefectureCd);
        /** 住所(市区町村) */
        this.model.setAddressCity(this.addressCity_txt);
        /** 住所(ﾋﾞﾙ、ﾏﾝｼｮﾝ名 等) */
        this.model.setAddressOthers(this.addressOthers_txt);
        /** 責任者名(姓) */
        this.model.setManagFamilyJnm(this.managFamilyJnm_txt);
        /** 責任者名(名) */
        this.model.setManagFirstJnm(this.managFirstJnm_txt);
        /** 責任者名フリガナ(姓) */
        this.model.setManagFamilyKnm(this.managFamilyKnm_txt);
        /** 責任者名フリガナ(名) */
        this.model.setManagFirstKnm(this.managFirstKnm_txt);
        /** 責任者名ローマ字(姓) */
        this.model.setManagFamilyRomaji(this.managFamilyRomaji_txt);
        /** 責任者名ローマ字(名) */
        this.model.setManagFirstRomaji(this.managFirstRomaji_txt);
        /** 責任者所属 */
        this.model.setManagPosition(this.managPosition_txt);
        /** 責任者性別区分 */
        this.model.setManagGenderKbn(this.managGenderKbn);
        /** 責任者メールアドレス1 */
        this.model.setManagMailAddress1(this.managMailAddress1_txt);
        /** 責任者メールアドレス2 */
        this.model.setManagMailAddress2(this.managMailAddress2_txt);
        /** 責任者メールアドレス3 */
        this.model.setManagMailAddress3(this.managMailAddress3_txt);
        /** 契約開始日 */
        this.model.setContractStrDt(NaikaraStringUtil.converToYYYYMMDD(this.contractStrDt_txt));
        /** 契約終了日 */
        this.model.setContractEndDt(NaikaraStringUtil.converToYYYYMMDD(this.contractEndDt_txt));
        /** 仮ポイント数 */
        this.model.setTempPointNum(new BigDecimal(NaikaraStringUtil.delComma(this.tempPointNum_txt)));
        /** 請求先住所区分 */
        if (this.requestAddressKbn == true) {
            model.setRequestAddressKbn(NaikaraTalkConstants.REQUEST_ADDRESS_KBN_ON);
        } else {
            model.setRequestAddressKbn(NaikaraTalkConstants.REQUEST_ADDRESS_KBN_OFF);
        }
        /** 備考 */
        this.model.setRemarks(this.remarks_txa);
        /** 請求先情報：組織名 */
        this.model.setRequestOrganizationJnm(this.requestOrganizationJnm_txt);
        /** 請求先情報：組織名(カナ) */
        this.model.setRequestOrganizationKnm(this.requestOrganizationKnm_txt);
        /** 請求先情報：組織名(ローマ字) */
        this.model.setRequestOrganizationRnm(this.requestOrganizationRnm_txt);
        /** 請求先情報：電話番号 */
        this.model.setRequestTel(this.requestTel_txt);
        /** 請求先情報：郵便番号 */
        this.model.setRequestZipCd(this.requestZipCd_txt);
        /** 請求先情報：住所(地域)コード */
        this.model.setRequestAddressAreaCd(this.requestAddressAreaCd);
        /** 請求先情報：住所(都道府県)コード */
        this.model.setRequestAddressPrefectureCd(this.requestAddressPrefectureCd);
        /** 請求先情報：住所(市区町村) */
        this.model.setRequestAddressCity(this.requestAddressCity_txt);
        /** 請求先情報：住所(ﾋﾞﾙ、ﾏﾝｼｮﾝ名 等) */
        this.model.setRequestAddressOthers(this.requestAddressOthers_txt);
        /** 請求先情報：責任者名(姓) */
        this.model.setRequestManagFamilyJnm(this.requestManagFamilyJnm_txt);
        /** 請求先情報：責任者名(名) */
        this.model.setRequestManagFirstJnm(this.requestManagFirstJnm_txt);
        /** 請求先情報：責任者名フリガナ(姓) */
        this.model.setRequestManagFamilyKnm(this.requestManagFamilyKnm_txt);
        /** 請求先情報：責任者名フリガナ(名) */
        this.model.setRequestManagFirstKnm(this.requestManagFirstKnm_txt);
        /** 請求先情報：責任者名ローマ字(姓) */
        this.model.setRequestManagFamilyRomaji(this.requestManagFamilyRomaji_txt);
        /** 請求先情報：責任者名ローマ字(名) */
        this.model.setRequestManagFirstRomaji(this.requestManagFirstRomaji_txt);
        /** 請求先情報：責任者所属 */
        this.model.setRequestManagPosition(this.requestManagPosition_txt);
        /** 請求先情報：支払サイト区分 */
        this.model.setRequestPaymentSiteKbn(this.requestPaymentSiteKbn);
        /** レコードバージョン番号 */
        this.model.setRecordVerNo(this.recordVerNo);
        /** 更新者コード */
        this.model
                .setUpdateCd(((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString())).getUserId());

    }

    /**
     * モデルを画面のデータにセットする。<br>
     * <br>
     * @param なし
     * @return なし
     */
    public void modelToJsp() {

        /** 組織ID */
        this.organizationId_txt = model.getOrganizationId();
        /** 連番 */
        if (model.getConsSeq() == 0) {
            this.consSeq_txt = "";
        } else {
            this.consSeq_txt = String.valueOf(model.getConsSeq());
        }
        /** パスワード */
        this.password_txt = model.getPassword();
        /** パスワード確認 */
        this.passwordCheck_txt = model.getPassword();
        /** 組織名 */
        this.organizationJnm_txt = model.getOrganizationJnm();
        /** 組織名(フリガナ) */
        this.organizationKnm_txt = model.getOrganizationKnm();
        /** 組織名(ローマ字) */
        this.organizationRomaji_txt = model.getOrganizationRomaji();
        /** 電話番号 */
        this.tel_txt = model.getTel();
        /** 郵便番号 */
        this.zipCd_txt = model.getZipCd();
        /** 住所(地域)コード */
        this.addressAreaCd = model.getAddressAreaCd();
        /** 住所(都道府県)コード */
        this.addressPrefectureCd = model.getAddressPrefectureCd();
        /** 住所(市区町村) */
        this.addressCity_txt = model.getAddressCity();
        /** 住所(ﾋﾞﾙ、ﾏﾝｼｮﾝ名 等) */
        this.addressOthers_txt = model.getAddressOthers();
        /** 責任者名(姓) */
        this.managFamilyJnm_txt = model.getManagFamilyJnm();
        /** 責任者名(名) */
        this.managFirstJnm_txt = model.getManagFirstJnm();
        /** 責任者名フリガナ(姓) */
        this.managFamilyKnm_txt = model.getManagFamilyKnm();
        /** 責任者名フリガナ(名) */
        this.managFirstKnm_txt = model.getManagFirstKnm();
        /** 責任者名ローマ字(姓) */
        this.managFamilyRomaji_txt = model.getManagFamilyRomaji();
        /** 責任者名ローマ字(名) */
        this.managFirstRomaji_txt = model.getManagFirstRomaji();
        /** 責任者所属 */
        this.managPosition_txt = model.getManagPosition();
        /** 責任者性別区分 */
        this.managGenderKbn = model.getManagGenderKbn();
        /** 責任者メールアドレス1 */
        this.managMailAddress1_txt = model.getManagMailAddress1();
        /** 責任者メールアドレス2 */
        this.managMailAddress2_txt = model.getManagMailAddress2();
        /** 責任者メールアドレス3 */
        this.managMailAddress3_txt = model.getManagMailAddress3();
        /** 契約開始日 */
        this.contractStrDt_txt = NaikaraStringUtil.converToYYYY_MM_DD(model.getContractStrDt());
        /** 契約終了日 */
        this.contractEndDt_txt = NaikaraStringUtil.converToYYYY_MM_DD(model.getContractEndDt());
        /** 仮ポイント数 */
        if (model.getTempPointNum() == null) {
            this.tempPointNum_txt = "";
        } else {
            this.tempPointNum_txt = NaikaraStringUtil.addComma(model.getTempPointNum().toString());
        }
        /** 請求先住所区分 */
        if ((NaikaraTalkConstants.REQUEST_ADDRESS_KBN_ON).equals(model.getRequestAddressKbn())) {
            this.requestAddressKbn = true;
        } else {
            this.requestAddressKbn = false;
        }
        /** 備考 */
        this.remarks_txa = model.getRemarks();
        /** 請求先情報：組織名 */
        this.requestOrganizationJnm_txt = model.getRequestOrganizationJnm();
        /** 請求先情報：組織名(カナ) */
        this.requestOrganizationKnm_txt = model.getRequestOrganizationKnm();
        /** 請求先情報：組織名(ローマ字) */
        this.requestOrganizationRnm_txt = model.getRequestOrganizationRnm();
        /** 請求先情報：電話番号 */
        this.requestTel_txt = model.getRequestTel();
        /** 請求先情報：郵便番号 */
        this.requestZipCd_txt = model.getRequestZipCd();
        /** 請求先情報：住所(地域)コード */
        this.requestAddressAreaCd = model.getRequestAddressAreaCd();
        /** 請求先情報：住所(都道府県)コード */
        this.requestAddressPrefectureCd = model.getRequestAddressPrefectureCd();
        /** 請求先情報：住所(市区町村) */
        this.requestAddressCity_txt = model.getRequestAddressCity();
        /** 請求先情報：住所(ﾋﾞﾙ、ﾏﾝｼｮﾝ名 等) */
        this.requestAddressOthers_txt = model.getRequestAddressOthers();
        /** 請求先情報：責任者名(姓) */
        this.requestManagFamilyJnm_txt = model.getRequestManagFamilyJnm();
        /** 請求先情報：責任者名(名) */
        this.requestManagFirstJnm_txt = model.getRequestManagFirstJnm();
        /** 請求先情報：責任者名フリガナ(姓) */
        this.requestManagFamilyKnm_txt = model.getRequestManagFamilyKnm();
        /** 請求先情報：責任者名フリガナ(名) */
        this.requestManagFirstKnm_txt = model.getRequestManagFirstKnm();
        /** 請求先情報：責任者名ローマ字(姓) */
        this.requestManagFamilyRomaji_txt = model.getRequestManagFamilyRomaji();
        /** 請求先情報：責任者名ローマ字(名) */
        this.requestManagFirstRomaji_txt = model.getRequestManagFirstRomaji();
        /** 請求先情報：責任者所属 */
        this.requestManagPosition_txt = model.getRequestManagPosition();
        /** 請求先情報：支払サイト区分 */
        this.requestPaymentSiteKbn = model.getRequestPaymentSiteKbn();
        /** レコードバージョン番号 */
        this.recordVerNo = model.getRecordVerNo();
    }

    /** メッセージ */
    protected String message;
    /** 検索結果 */
    protected OrganizationContractMstModel model = new OrganizationContractMstModel();
    /** 処理区分 */
    protected String processKbn_rdl;
    /** 一覧部の「選択」 */
    protected String select_rdl;
    /** 組織ID */
    protected String organizationId_txt;
    /** 連番 */
    protected String consSeq_txt;
    /** パスワード */
    protected String password_txt;
    /** パスワード確認 */
    protected String passwordCheck_txt;
    /** 組織名 */
    protected String organizationJnm_txt;
    /** 組織名(フリガナ) */
    protected String organizationKnm_txt;
    /** 組織名(ローマ字) */
    protected String organizationRomaji_txt;
    /** 電話番号 */
    protected String tel_txt;
    /** 郵便番号 */
    protected String zipCd_txt;
    /** 住所(地域)コード */
    protected String addressAreaCd;
    protected Map<String, String> addressAreaCd_sell = new LinkedHashMap<String, String>();
    /** 住所(都道府県)コード */
    protected String addressPrefectureCd;
    protected Map<String, String> addressPrefectureCd_sell = new LinkedHashMap<String, String>();
    /** 住所(市区町村) */
    protected String addressCity_txt;
    /** 住所(ﾋﾞﾙ、ﾏﾝｼｮﾝ名 等) */
    protected String addressOthers_txt;
    /** 責任者名(姓) */
    protected String managFamilyJnm_txt;
    /** 責任者名(名) */
    protected String managFirstJnm_txt;
    /** 責任者名フリガナ(姓) */
    protected String managFamilyKnm_txt;
    /** 責任者名フリガナ(名) */
    protected String managFirstKnm_txt;
    /** 責任者名ローマ字(姓) */
    protected String managFamilyRomaji_txt;
    /** 責任者名ローマ字(名) */
    protected String managFirstRomaji_txt;
    /** 責任者所属 */
    protected String managPosition_txt;
    /** 責任者性別区分 */
    protected String managGenderKbn;
    protected Map<String, String> managGenderKbn_rdl = new LinkedHashMap<String, String>();
    /** 責任者メールアドレス1 */
    protected String managMailAddress1_txt;
    /** 責任者メールアドレス2 */
    protected String managMailAddress2_txt;
    /** 責任者メールアドレス3 */
    protected String managMailAddress3_txt;
    /** 契約開始日 */
    protected String contractStrDt_txt;
    /** 契約終了日 */
    protected String contractEndDt_txt;
    /** 仮ポイント数 */
    protected String tempPointNum_txt;
    /** 請求先住所区分 */
    protected boolean requestAddressKbn;
    /** 備考 */
    protected String remarks_txa;
    /** 請求先情報：組織名 */
    protected String requestOrganizationJnm_txt;
    /** 請求先情報：組織名(カナ) */
    protected String requestOrganizationKnm_txt;
    /** 請求先情報：組織名(ローマ字) */
    protected String requestOrganizationRnm_txt;
    /** 請求先情報：電話番号 */
    protected String requestTel_txt;
    /** 請求先情報：郵便番号 */
    protected String requestZipCd_txt;
    /** 請求先情報：住所(地域)コード */
    protected String requestAddressAreaCd;
    /** 請求先情報：住所(都道府県)コード */
    protected String requestAddressPrefectureCd;
    /** 請求先情報：住所(市区町村) */
    protected String requestAddressCity_txt;
    /** 請求先情報：住所(ﾋﾞﾙ、ﾏﾝｼｮﾝ名 等) */
    protected String requestAddressOthers_txt;
    /** 請求先情報：責任者名(姓) */
    protected String requestManagFamilyJnm_txt;
    /** 請求先情報：責任者名(名) */
    protected String requestManagFirstJnm_txt;
    /** 請求先情報：責任者名フリガナ(姓) */
    protected String requestManagFamilyKnm_txt;
    /** 請求先情報：責任者名フリガナ(名) */
    protected String requestManagFirstKnm_txt;
    /** 請求先情報：責任者名ローマ字(姓) */
    protected String requestManagFamilyRomaji_txt;
    /** 請求先情報：責任者名ローマ字(名) */
    protected String requestManagFirstRomaji_txt;
    /** 請求先情報：責任者所属 */
    protected String requestManagPosition_txt;
    /** 請求先情報：支払サイト区分 */
    protected String requestPaymentSiteKbn;
    protected Map<String, String> requestPaymentSiteKbn_sell = new LinkedHashMap<String, String>();
    /** レコードバージョン番号 */
    protected int recordVerNo;

    /**
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title セットする title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return helpPageId
     */
    public String getHelpPageId() {
        return helpPageId;
    }

    /**
     * @param helpPageId セットする helpPageId
     */
    public void setHelpPageId(String helpPageId) {
        this.helpPageId = helpPageId;
    }

    /**
     * @return model
     */
    public OrganizationContractMstModel getModel() {
        return model;
    }

    /**
     * @param model セットする model
     */
    public void setModel(OrganizationContractMstModel model) {
        this.model = model;
    }

    /**
     * @return processKbn_rdl
     */
    public String getProcessKbn_rdl() {
        return processKbn_rdl;
    }

    /**
     * @param processKbn_rdl セットする processKbn_rdl
     */
    public void setProcessKbn_rdl(String processKbn_rdl) {
        this.processKbn_rdl = processKbn_rdl;
    }

    /**
     * @return select_rdl
     */
    public String getSelect_rdl() {
        return select_rdl;
    }

    /**
     * @param select_rdl セットする select_rdl
     */
    public void setSelect_rdl(String select_rdl) {
        this.select_rdl = select_rdl;
    }

    /**
     * @return organizationId_txt
     */
    public String getOrganizationId_txt() {
        return organizationId_txt;
    }

    /**
     * @param organizationId_txt セットする organizationId_txt
     */
    public void setOrganizationId_txt(String organizationId_txt) {
        this.organizationId_txt = organizationId_txt;
    }

    /**
     * @return consSeq_txt
     */
    public String getConsSeq_txt() {
        return consSeq_txt;
    }

    /**
     * @param consSeq_txt セットする consSeq_txt
     */
    public void setConsSeq_txt(String consSeq_txt) {
        this.consSeq_txt = consSeq_txt;
    }

    /**
     * @return password_txt
     */
    public String getPassword_txt() {
        return password_txt;
    }

    /**
     * @param password_txt セットする password_txt
     */
    public void setPassword_txt(String password_txt) {
        this.password_txt = password_txt;
    }

    /**
     * @return organizationJnm_txt
     */
    public String getOrganizationJnm_txt() {
        return organizationJnm_txt;
    }

    /**
     * @param organizationJnm_txt セットする organizationJnm_txt
     */
    public void setOrganizationJnm_txt(String organizationJnm_txt) {
        this.organizationJnm_txt = organizationJnm_txt;
    }

    /**
     * @return organizationKnm_txt
     */
    public String getOrganizationKnm_txt() {
        return organizationKnm_txt;
    }

    /**
     * @param organizationKnm_txt セットする organizationKnm_txt
     */
    public void setOrganizationKnm_txt(String organizationKnm_txt) {
        this.organizationKnm_txt = organizationKnm_txt;
    }

    /**
     * @return organizationRomaji_txt
     */
    public String getOrganizationRomaji_txt() {
        return organizationRomaji_txt;
    }

    /**
     * @param organizationRomaji_txt セットする organizationRomaji_txt
     */
    public void setOrganizationRomaji_txt(String organizationRomaji_txt) {
        this.organizationRomaji_txt = organizationRomaji_txt;
    }

    /**
     * @return tel_txt
     */
    public String getTel_txt() {
        return tel_txt;
    }

    /**
     * @param tel_txt セットする tel_txt
     */
    public void setTel_txt(String tel_txt) {
        this.tel_txt = tel_txt;
    }

    /**
     * @return zipCd_txt
     */
    public String getZipCd_txt() {
        return zipCd_txt;
    }

    /**
     * @param zipCd_txt セットする zipCd_txt
     */
    public void setZipCd_txt(String zipCd_txt) {
        this.zipCd_txt = zipCd_txt;
    }

    /**
     * @return addressAreaCd
     */
    public String getAddressAreaCd() {
        return addressAreaCd;
    }

    /**
     * @param addressAreaCd セットする addressAreaCd
     */
    public void setAddressAreaCd(String addressAreaCd) {
        this.addressAreaCd = addressAreaCd;
    }

    /**
     * @return addressAreaCd_sell
     */
    public Map<String, String> getAddressAreaCd_sell() {
        return addressAreaCd_sell;
    }

    /**
     * @param addressAreaCd_sell セットする addressAreaCd_sell
     */
    public void setAddressAreaCd_sell(Map<String, String> addressAreaCd_sell) {
        this.addressAreaCd_sell = addressAreaCd_sell;
    }

    /**
     * @return addressPrefectureCd
     */
    public String getAddressPrefectureCd() {
        return addressPrefectureCd;
    }

    /**
     * @param addressPrefectureCd セットする addressPrefectureCd
     */
    public void setAddressPrefectureCd(String addressPrefectureCd) {
        this.addressPrefectureCd = addressPrefectureCd;
    }

    /**
     * @return addressPrefectureCd_sell
     */
    public Map<String, String> getAddressPrefectureCd_sell() {
        return addressPrefectureCd_sell;
    }

    /**
     * @param addressPrefectureCd_sell セットする addressPrefectureCd_sell
     */
    public void setAddressPrefectureCd_sell(Map<String, String> addressPrefectureCd_sell) {
        this.addressPrefectureCd_sell = addressPrefectureCd_sell;
    }

    /**
     * @return addressCity_txt
     */
    public String getAddressCity_txt() {
        return addressCity_txt;
    }

    /**
     * @param addressCity_txt セットする addressCity_txt
     */
    public void setAddressCity_txt(String addressCity_txt) {
        this.addressCity_txt = addressCity_txt;
    }

    /**
     * @return addressOthers_txt
     */
    public String getAddressOthers_txt() {
        return addressOthers_txt;
    }

    /**
     * @param addressOthers_txt セットする addressOthers_txt
     */
    public void setAddressOthers_txt(String addressOthers_txt) {
        this.addressOthers_txt = addressOthers_txt;
    }

    /**
     * @return managFamilyJnm_txt
     */
    public String getManagFamilyJnm_txt() {
        return managFamilyJnm_txt;
    }

    /**
     * @param managFamilyJnm_txt セットする managFamilyJnm_txt
     */
    public void setManagFamilyJnm_txt(String managFamilyJnm_txt) {
        this.managFamilyJnm_txt = managFamilyJnm_txt;
    }

    /**
     * @return managFirstJnm_txt
     */
    public String getManagFirstJnm_txt() {
        return managFirstJnm_txt;
    }

    /**
     * @param managFirstJnm_txt セットする managFirstJnm_txt
     */
    public void setManagFirstJnm_txt(String managFirstJnm_txt) {
        this.managFirstJnm_txt = managFirstJnm_txt;
    }

    /**
     * @return managFamilyKnm_txt
     */
    public String getManagFamilyKnm_txt() {
        return managFamilyKnm_txt;
    }

    /**
     * @param managFamilyKnm_txt セットする managFamilyKnm_txt
     */
    public void setManagFamilyKnm_txt(String managFamilyKnm_txt) {
        this.managFamilyKnm_txt = managFamilyKnm_txt;
    }

    /**
     * @return managFirstKnm_txt
     */
    public String getManagFirstKnm_txt() {
        return managFirstKnm_txt;
    }

    /**
     * @param managFirstKnm_txt セットする managFirstKnm_txt
     */
    public void setManagFirstKnm_txt(String managFirstKnm_txt) {
        this.managFirstKnm_txt = managFirstKnm_txt;
    }

    /**
     * @return managFamilyRomaji_txt
     */
    public String getManagFamilyRomaji_txt() {
        return managFamilyRomaji_txt;
    }

    /**
     * @param managFamilyRomaji_txt セットする managFamilyRomaji_txt
     */
    public void setManagFamilyRomaji_txt(String managFamilyRomaji_txt) {
        this.managFamilyRomaji_txt = managFamilyRomaji_txt;
    }

    /**
     * @return managFirstRomaji_txt
     */
    public String getManagFirstRomaji_txt() {
        return managFirstRomaji_txt;
    }

    /**
     * @param managFirstRomaji_txt セットする managFirstRomaji_txt
     */
    public void setManagFirstRomaji_txt(String managFirstRomaji_txt) {
        this.managFirstRomaji_txt = managFirstRomaji_txt;
    }

    /**
     * @return managPosition_txt
     */
    public String getManagPosition_txt() {
        return managPosition_txt;
    }

    /**
     * @param managPosition_txt セットする managPosition_txt
     */
    public void setManagPosition_txt(String managPosition_txt) {
        this.managPosition_txt = managPosition_txt;
    }

    /**
     * @return managGenderKbn
     */
    public String getManagGenderKbn() {
        return managGenderKbn;
    }

    /**
     * @param managGenderKbn セットする managGenderKbn
     */
    public void setManagGenderKbn(String managGenderKbn) {
        this.managGenderKbn = managGenderKbn;
    }

    /**
     * @return managMailAddress1_txt
     */
    public String getManagMailAddress1_txt() {
        return managMailAddress1_txt;
    }

    /**
     * @param managMailAddress1_txt セットする managMailAddress1_txt
     */
    public void setManagMailAddress1_txt(String managMailAddress1_txt) {
        this.managMailAddress1_txt = managMailAddress1_txt;
    }

    /**
     * @return managMailAddress2_txt
     */
    public String getManagMailAddress2_txt() {
        return managMailAddress2_txt;
    }

    /**
     * @param managMailAddress2_txt セットする managMailAddress2_txt
     */
    public void setManagMailAddress2_txt(String managMailAddress2_txt) {
        this.managMailAddress2_txt = managMailAddress2_txt;
    }

    /**
     * @return managMailAddress3_txt
     */
    public String getManagMailAddress3_txt() {
        return managMailAddress3_txt;
    }

    /**
     * @param managMailAddress3_txt セットする managMailAddress3_txt
     */
    public void setManagMailAddress3_txt(String managMailAddress3_txt) {
        this.managMailAddress3_txt = managMailAddress3_txt;
    }

    /**
     * @return contractStrDt_txt
     */
    public String getContractStrDt_txt() {
        return contractStrDt_txt;
    }

    /**
     * @param contractStrDt_txt セットする contractStrDt_txt
     */
    public void setContractStrDt_txt(String contractStrDt_txt) {
        this.contractStrDt_txt = contractStrDt_txt;
    }

    /**
     * @return contractEndDt_txt
     */
    public String getContractEndDt_txt() {
        return contractEndDt_txt;
    }

    /**
     * @param contractEndDt_txt セットする contractEndDt_txt
     */
    public void setContractEndDt_txt(String contractEndDt_txt) {
        this.contractEndDt_txt = contractEndDt_txt;
    }

    /**
     * @return tempPointNum_txt
     */
    public String getTempPointNum_txt() {
        return tempPointNum_txt;
    }

    /**
     * @param tempPointNum_txt セットする tempPointNum_txt
     */
    public void setTempPointNum_txt(String tempPointNum_txt) {
        this.tempPointNum_txt = tempPointNum_txt;
    }

    /**
     * @return requestAddressKbn
     */
    public boolean getRequestAddressKbn() {
        return requestAddressKbn;
    }

    /**
     * @param requestAddressKbn セットする requestAddressKbn
     */
    public void setRequestAddressKbn(boolean requestAddressKbn) {
        this.requestAddressKbn = requestAddressKbn;
    }

    /**
     * @return remarks_txt
     */
    public String getRemarks_txa() {
        return remarks_txa;
    }

    /**
     * @param remarks_txt セットする remarks_txt
     */
    public void setRemarks_txa(String remarks_txt) {
        this.remarks_txa = remarks_txt;
    }

    /**
     * @return requestOrganizationJnm_txt
     */
    public String getRequestOrganizationJnm_txt() {
        return requestOrganizationJnm_txt;
    }

    /**
     * @param requestOrganizationJnm_txt セットする requestOrganizationJnm_txt
     */
    public void setRequestOrganizationJnm_txt(String requestOrganizationJnm_txt) {
        this.requestOrganizationJnm_txt = requestOrganizationJnm_txt;
    }

    /**
     * @return requestOrganizationKnm_txt
     */
    public String getRequestOrganizationKnm_txt() {
        return requestOrganizationKnm_txt;
    }

    /**
     * @param requestOrganizationKnm_txt セットする requestOrganizationKnm_txt
     */
    public void setRequestOrganizationKnm_txt(String requestOrganizationKnm_txt) {
        this.requestOrganizationKnm_txt = requestOrganizationKnm_txt;
    }

    /**
     * @return requestOrganizationRnm_txt
     */
    public String getRequestOrganizationRnm_txt() {
        return requestOrganizationRnm_txt;
    }

    /**
     * @param requestOrganizationRnm_txt セットする requestOrganizationRnm_txt
     */
    public void setRequestOrganizationRnm_txt(String requestOrganizationRnm_txt) {
        this.requestOrganizationRnm_txt = requestOrganizationRnm_txt;
    }

    /**
     * @return requestTel_txt
     */
    public String getRequestTel_txt() {
        return requestTel_txt;
    }

    /**
     * @param requestTel_txt セットする requestTel_txt
     */
    public void setRequestTel_txt(String requestTel_txt) {
        this.requestTel_txt = requestTel_txt;
    }

    /**
     * @return requestZipCd_txt
     */
    public String getRequestZipCd_txt() {
        return requestZipCd_txt;
    }

    /**
     * @param requestZipCd_txt セットする requestZipCd_txt
     */
    public void setRequestZipCd_txt(String requestZipCd_txt) {
        this.requestZipCd_txt = requestZipCd_txt;
    }

    /**
     * @return requestAddressAreaCd
     */
    public String getRequestAddressAreaCd() {
        return requestAddressAreaCd;
    }

    /**
     * @param requestAddressAreaCd セットする requestAddressAreaCd
     */
    public void setRequestAddressAreaCd(String requestAddressAreaCd) {
        this.requestAddressAreaCd = requestAddressAreaCd;
    }

    /**
     * @return requestAddressPrefectureCd
     */
    public String getRequestAddressPrefectureCd() {
        return requestAddressPrefectureCd;
    }

    /**
     * @param requestAddressPrefectureCd セットする requestAddressPrefectureCd
     */
    public void setRequestAddressPrefectureCd(String requestAddressPrefectureCd) {
        this.requestAddressPrefectureCd = requestAddressPrefectureCd;
    }

    /**
     * @return requestAddressCity_txt
     */
    public String getRequestAddressCity_txt() {
        return requestAddressCity_txt;
    }

    /**
     * @param requestAddressCity_txt セットする requestAddressCity_txt
     */
    public void setRequestAddressCity_txt(String requestAddressCity_txt) {
        this.requestAddressCity_txt = requestAddressCity_txt;
    }

    /**
     * @return requestAddressOthers_txt
     */
    public String getRequestAddressOthers_txt() {
        return requestAddressOthers_txt;
    }

    /**
     * @param requestAddressOthers_txt セットする requestAddressOthers_txt
     */
    public void setRequestAddressOthers_txt(String requestAddressOthers_txt) {
        this.requestAddressOthers_txt = requestAddressOthers_txt;
    }

    /**
     * @return requestManagFamilyJnm_txt
     */
    public String getRequestManagFamilyJnm_txt() {
        return requestManagFamilyJnm_txt;
    }

    /**
     * @param requestManagFamilyJnm_txt セットする requestManagFamilyJnm_txt
     */
    public void setRequestManagFamilyJnm_txt(String requestManagFamilyJnm_txt) {
        this.requestManagFamilyJnm_txt = requestManagFamilyJnm_txt;
    }

    /**
     * @return requestManagFirstJnm_txt
     */
    public String getRequestManagFirstJnm_txt() {
        return requestManagFirstJnm_txt;
    }

    /**
     * @param requestManagFirstJnm_txt セットする requestManagFirstJnm_txt
     */
    public void setRequestManagFirstJnm_txt(String requestManagFirstJnm_txt) {
        this.requestManagFirstJnm_txt = requestManagFirstJnm_txt;
    }

    /**
     * @return requestManagFamilyKnm_txt
     */
    public String getRequestManagFamilyKnm_txt() {
        return requestManagFamilyKnm_txt;
    }

    /**
     * @param requestManagFamilyKnm_txt セットする requestManagFamilyKnm_txt
     */
    public void setRequestManagFamilyKnm_txt(String requestManagFamilyKnm_txt) {
        this.requestManagFamilyKnm_txt = requestManagFamilyKnm_txt;
    }

    /**
     * @return requestManagFirstKnm_txt
     */
    public String getRequestManagFirstKnm_txt() {
        return requestManagFirstKnm_txt;
    }

    /**
     * @param requestManagFirstKnm_txt セットする requestManagFirstKnm_txt
     */
    public void setRequestManagFirstKnm_txt(String requestManagFirstKnm_txt) {
        this.requestManagFirstKnm_txt = requestManagFirstKnm_txt;
    }

    /**
     * @return requestManagFamilyRomaji_txt
     */
    public String getRequestManagFamilyRomaji_txt() {
        return requestManagFamilyRomaji_txt;
    }

    /**
     * @param requestManagFamilyRomaji_txt セットする requestManagFamilyRomaji_txt
     */
    public void setRequestManagFamilyRomaji_txt(String requestManagFamilyRomaji_txt) {
        this.requestManagFamilyRomaji_txt = requestManagFamilyRomaji_txt;
    }

    /**
     * @return requestManagFirstRomaji_txt
     */
    public String getRequestManagFirstRomaji_txt() {
        return requestManagFirstRomaji_txt;
    }

    /**
     * @param requestManagFirstRomaji_txt セットする requestManagFirstRomaji_txt
     */
    public void setRequestManagFirstRomaji_txt(String requestManagFirstRomaji_txt) {
        this.requestManagFirstRomaji_txt = requestManagFirstRomaji_txt;
    }

    /**
     * @return requestManagPosition_txt
     */
    public String getRequestManagPosition_txt() {
        return requestManagPosition_txt;
    }

    /**
     * @param requestManagPosition_txt セットする requestManagPosition_txt
     */
    public void setRequestManagPosition_txt(String requestManagPosition_txt) {
        this.requestManagPosition_txt = requestManagPosition_txt;
    }

    /**
     * @return requestPaymentSiteKbn
     */
    public String getRequestPaymentSiteKbn() {
        return requestPaymentSiteKbn;
    }

    /**
     * @param requestPaymentSiteKbn セットする requestPaymentSiteKbn
     */
    public void setRequestPaymentSiteKbn(String requestPaymentSiteKbn) {
        this.requestPaymentSiteKbn = requestPaymentSiteKbn;
    }

    /**
     * @return requestPaymentSiteKbn_sell
     */
    public Map<String, String> getRequestPaymentSiteKbn_sell() {
        return requestPaymentSiteKbn_sell;
    }

    /**
     * @param requestPaymentSiteKbn_sell セットする requestPaymentSiteKbn_sell
     */
    public void setRequestPaymentSiteKbn_sell(Map<String, String> requestPaymentSiteKbn_sell) {
        this.requestPaymentSiteKbn_sell = requestPaymentSiteKbn_sell;
    }

    /**
     * @return recordVerNo
     */
    public int getRecordVerNo() {
        return recordVerNo;
    }

    /**
     * @param recordVerNo セットする recordVerNo
     */
    public void setRecordVerNo(int recordVerNo) {
        this.recordVerNo = recordVerNo;
    }

    /**
     * @return managGenderKbn_rdl
     */
    public Map<String, String> getManagGenderKbn_rdl() {
        return managGenderKbn_rdl;
    }

    /**
     * @param managGenderKbn_rdl セットする managGenderKbn_rdl
     */
    public void setManagGenderKbn_rdl(Map<String, String> managGenderKbn_rdl) {
        this.managGenderKbn_rdl = managGenderKbn_rdl;
    }

    /**
     * @return message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message セットする message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return passwordCheck_txt
     */
    public String getPasswordCheck_txt() {
        return passwordCheck_txt;
    }

    /**
     * @param passwordCheck_txt セットする passwordCheck_txt
     */
    public void setPasswordCheck_txt(String passwordCheck_txt) {
        this.passwordCheck_txt = passwordCheck_txt;
    }

}
