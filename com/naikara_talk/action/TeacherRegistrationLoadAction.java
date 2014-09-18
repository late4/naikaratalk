package com.naikara_talk.action;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.service.TeacherRegistrationLoadService;
import com.naikara_talk.util.NaikaraStringUtil;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>講師初期登録ページ。<br>
 * <b>クラス名称       :</b>講師初期登録ページ初期処理Actionクラス。<br>
 * <b>クラス概要       :</b>講師情報の登録・修正処理を行うことができる<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/01 TECS 新規作成
 */
public class TeacherRegistrationLoadAction extends TeacherRegistrationActionSupport {

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
        TeacherRegistrationLoadService service = new TeacherRegistrationLoadService();

        try {
            // 滞在国と時差地域No.等の初期取得。
            initSelect();
            // 対象データの取得
            this.model = service.select(this.model);
            // データが存在しない場合
            if (this.model.getUserId() == null) {
                this.message = getMessage("ET0020", new String[] { "", "" });
                return "previous";
            }
            // ｢契約日｣＝Nullの場合
            if (this.model.getContractDt() == null) {
                this.message = getMessage("ET0020", new String[] { "", "" });
                return "previous";
            }

        } catch (Exception e) {
            throw new NaiException(e);
        }
        /** 利用者ID */
        this.userId = model.getUserId();
        /** 名前（苗字） */
        this.familyJnm = model.getFamilyJnm();
        /** 名前（名前） */
        this.firstJnm = model.getFirstJnm();
        /** フリガナ(姓) */
        this.familyKnm = model.getFamilyKnm();
        /** フリガナ(名） */
        this.firstKnm = model.getFirstKnm();
        /** ローマ字(姓) */
        this.familyRomaji = model.getFamilyRomaji();
        /** ローマ字(名) */
        this.firstRomaji = model.getFirstRomaji();
        /** ニックネーム  */
        this.nickAnm = model.getNickAnm();
        /** 電話番号（自宅/携帯） */
        this.tel1_txt = model.getTel1();
        /** 電話番号（緊急連絡先） */
        this.tel2_txt = model.getTel2();
        /** 生年月日 */
        this.birthYyyyMmDd = model.getBirthYyyyMmDd();
        /** 郵便番号 */
        this.zipCd_txt = model.getZipCd();
        /** 住所 */
        this.addressCity_txt = model.getAddressCity();
        /** 住所(ﾋﾞﾙ、ﾏﾝｼｮﾝ名 等) */
        this.addressOthers_txt = model.getAddressOthers();
        /** 性別 */
        this.gender = model.getGender();
        /** 国籍 */
        this.nationality = model.getNationality();
        /** 母国語 */
        this.nativeLang = model.getNativeLang();
        /** メールアドレス１ */
        this.mailAddress1_txt = model.getMailAddress1();
        /** メールアドレス２ */
        this.mailAddress2_txt = model.getMailAddress2();
        /** メールアドレス３ */
        this.mailAddress3_txt = model.getMailAddress3();
        /** 滞在国 */
        this.countryCd_sel = model.getCountry();
        /** 時差地域No */
        this.areaNoCd_sel = model.getAreaNo();
        /** 勤務都市名 */
        this.cityTown_txt = model.getCityTown();
        /** 契約日 */
        this.contractDt = model.getContractDt();
        /** 契約期間：開始日 */
        this.contractStartDt = model.getContractStartDt();
        /** 契約期間：終了日 */
        this.contractEndDt = model.getContractEndDt();
        /** 銀行名 */
        this.bankJpnBankNm = model.getBankJpnBankNm();
        /** 支店名 */
        this.bankJpnBranchNm = model.getBankJpnBranchNm();
        /** 支店名 */
        this.bankJpnTypeOfAccount = model.getBankJpnTypeOfAccount();
        /** 支店名 */
        this.bankJpnAccountHoldersKnm = model.getBankJpnAccountHoldersKnm();
        /** 支店名 */
        this.bankJpnAccountHoldersNm = model.getBankJpnAccountHoldersNm();
        /** 支店名 */
        this.bankJpnAccountNumber = model.getBankJpnAccountNumber();
        /** 支店名 */
        this.bankJpnAdditionalInfo = model.getBankJpnAdditionalInfo();
        /** 店番 */
        this.jpnPbankBranchNm = model.getJpnPbankBranchNm();
        /** 預金種別 */
        this.jpnPbankTypeOfAccount = model.getJpnPbankTypeOfAccount();
        /** 口座名義人(フリガナ) */
        this.jpnPbankAccountHoldersKnm = model.getJpnPbankAccountHoldersKnm();
        /** 口座名義人 */
        this.jpnPbankAccountHoldersNm = model.getJpnPbankAccountHoldersNm();
        /** 口座番号 */
        this.jpnPbankAccountNumber = model.getJpnPbankAccountNumber();
        /** 追加情報 */
        this.jpnPbankAdditionalInfo = model.getJpnPbankAdditionalInfo();
        /** 口座名義人 */
        this.overseaAccountHNm = model.getOverseaAccountHNm();
        /** 口座名義人登録住所 */
        this.overseaAccountHRAddress1 = model.getOverseaAccountHRAddress1();
        /** 口座名義人登録住所上記住所欄が一杯のとき */
        this.overseaAccountHRAddress2 = model.getOverseaAccountHRAddress2();
        /** 口座番号/IBAN(ヨーロッパ */
        this.overseaAccountNumberIban = model.getOverseaAccountNumberIban();
        /** ABAナンバー/SWIFTコード/BIC Code */
        this.overseaAbanoSwiftcdBiccd = model.getOverseaAbanoSwiftcdBiccd();
        /** 等 */
        this.overseaEtc = model.getOverseaEtc();
        /** 銀行名 */
        this.overseaBankNm = model.getOverseaBankNm();
        /** 支店名 */
        this.overseaBranchNm = model.getOverseaBranchNm();
        /** 支店住所 */
        this.overseaBranchAddress1 = model.getOverseaBranchAddress1();
        /** 支店住所上記住所欄が一杯のとき */
        this.overseaBranchAddress2 = model.getOverseaBranchAddress2();
        /** 支店が所在する国名 */
        this.overseaCountryBranchExists = model.getOverseaCountryBranchExists();
        /** その他の送金手数料 */
        this.otherRemittanceFee = model.getOtherRemittanceFee();
        /** 追加情報 */
        this.overseaAdditionalInfo = model.getOverseaAdditionalInfo();
        /** PayPalアドレス */
        this.overseaPlPaypalAddress = model.getOverseaPlPaypalAddress();
        /** 追加情報 */
        this.overseaPlAdditionalInfo = model.getOverseaPlAdditionalInfo();
        /** セールスポイント(スクール記入) */
        this.sellingPoint = model.getSellingPoint();
        /** 受講生へのアピールポイント */
        this.selfRecommendation_txa = model.getSelfRecommendation().replaceAll("\n", "\r\n");
        /** 講師画像：ファイル名(表示) */
        this.imgPhoto_filFileName = model.getImgPhotoNm();
        /** スクール側から講師への評価へのコメント */
        this.evaluationFromSchoolCmt1 = model.getEvaluationFromSchoolCmt1();
        /** 最新の受講生から講師へのコメント */
        this.latestEvaluationFromStudentCmt = model.getLatestEvaluationFromStudentCmt();
        /** レコードバージョン番号 */
        this.teacherMstRecordVerNo = String.valueOf(model.getTeacherMstRecordVerNo());
        /** レコードバージョン番号 */
        this.userMstRecordVerNo = String.valueOf(model.getUserMstRecordVerNo());

        if (!StringUtils.isEmpty(this.message)) {
            this.addActionMessage(this.message);
        }

        return SUCCESS;
    }
}
