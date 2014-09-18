package com.naikara_talk.action;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.model.PointControlListModel;
import com.naikara_talk.model.TeacherMstListModel;
import com.naikara_talk.model.TeacherMstModel;
import com.naikara_talk.service.TeacherMstLoadService;
import com.naikara_talk.sessiondata.SessionUserMstTeacherMst;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>マスタ保守<br>
 * <b>クラス名称       :</b>講師マスタメンテナンス(単票)<br>
 * <b>クラス概要       :</b>講師マスタメンテナンス(単票)初期処理Action<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/26 TECS 新規作成
 */
public class TeacherMstLoadAction extends TeacherMstActionSupport {

    private static final long serialVersionUID = 1L;

    /**
     * 画面の初期表示。<br>
     * <br>
     * 画面の初期表示。<br>
     * <br>
     * @param なし<br>
     * @return String SUCCESS <br>
     * @exception NaiException
     */
    public String requestService() throws NaiException {

        // 開始ログ
        log.info(NaikaraStringUtil.unionProcesslog("Start"));

        setupModel();
        TeacherMstLoadService service = new TeacherMstLoadService();

        // 戻る判定Onフラグ
        boolean returnOnFlg = false;

        if ((SessionUserMstTeacherMst) SessionDataUtil.getSessionData(SessionUserMstTeacherMst.class.toString()) != null) {

            // 戻る判定Onフラグ
            returnOnFlg = ((SessionUserMstTeacherMst) SessionDataUtil.getSessionData(SessionUserMstTeacherMst.class
                    .toString())).getReturnOnFlg();
        }

        // 性別、国籍、母国語、滞在国、時差地域Noの初期取得。
        try {
            initRadio();
        } catch (Exception e) {
            throw new NaiException(e);
        }

        // 検索前チェック
        if (!returnOnFlg) {
            TeacherMstModel workModel = service.initLoad(model);

            // 前画面から処理区分を画面にセット
            this.processKbn_rdl = workModel.getProcessKbn_Rdl();
            this.processKbn_txt = workModel.getProcessKbn_Txt();
            this.teacherCourseDtoList = workModel.getTeacherCourseDtoList();

            // ******************************
            // 新規・修正・照会の処理
            // ******************************

            // データが存在しない場合
            try {
                if (NaikaraTalkConstants.RETURN_CD_DATA_NO == service.getExist(model)) {
                    this.message = getMessage("EN0020", new String[] { "利用者マスタ、講師マスタ", "" });
                    removeLatestActionList();
                    // 前画面(一覧)へ戻り、エラーメッセージを表示
                    return ERROR;
                } else {
                    // データが存在する場合

                    // 表示データの取得処理
                    this.load();

                    // 処理区分＝照会の場合('1':'修正','2':'照会')
                    if (StringUtils.equals(TeacherMstListModel.PROS_KBN_REF, this.processKbn_rdl)) {
                        this.setComment(NaikaraTalkConstants.PROCESSKBN_REF_COMMENT);
                        this.processDifference = NaikaraTalkConstants.PROCESSKBN_REF;
                    }
                }
            } catch (Exception e1) {
                throw new NaiException(e1);
            }
        } else {
            this.sessionToAction();
        }
        try {
            this.actionToSession();
            this.getAlertMessage();
        } catch (Exception e) {
            throw new NaiException(e);
        }
        return SUCCESS;
    }

    /**
     * 初期処理、表示データの取得。<br>
     * <br>
     * 初期処理、表示データの取得。<br>
     * <br>
     * @param なし<br>
     * @return なし <br>
     * @exception Exception
     */
    private void load() throws Exception {

        // 表示データを取得する
        TeacherMstLoadService service = new TeacherMstLoadService();

        // 前画面の情報
        this.model.setUserId(this.userId);
        this.model.setProcessKbn_Rdl(this.processKbn_rdl);
        this.model.setProcessKbn_Txt(this.processKbn_txt);

        this.model = service.select(this.model);

        // 利用者ID
        this.userId = model.getUserId();
        // 講師マスタ．利用者ID
        this.userIdT = model.getUserIdT();
        // 処理判定
        if (StringUtils.isEmpty(this.userIdT)) {
            this.processDifference = NaikaraTalkConstants.PROCESSKBN_INS;
        } else {
            this.processDifference = NaikaraTalkConstants.PROCESSKBN_UPD;
            // 講師支払比率
            this.teacherRateMstDtoList = model.getTeacherRateMstDtoList();
        }

        // 名前(姓)
        this.nameFamiy_txt = model.getFamilyJnm();
        // 名前(名)
        this.nameFirst_txt = model.getFirstJnm();
        // フリガナ(姓)
        this.furiganaFamiy_txt = model.getFamilyKnm();
        // フリガナ(名)
        this.furiganaFirst_txt = model.getFirstKnm();
        // ローマ字(姓)
        this.romajiFamiy_txt = model.getFamilyRomaji();
        // ローマ字(名)
        this.romajiFirst_txt = model.getFirstRomaji();
        // ニックネーム
        this.nickName_txt = model.getNickAnm();
        // 電話番号1
        this.telephone1_txt = model.getTel1();
        // 電話番号2
        this.telephone2_txt = model.getTel2();
        // 生年月日：年
        this.birthdayYy_txt = model.getBirthYyyy();
        // 生年月日：月
        this.birthdayMm_txt = model.getBirthMm();
        // 生年月日：日
        this.birthdayDd_txt = model.getBirthDd();
        // 郵便番号
        this.zipCode_txt = model.getZipCd();
        // 住所(市区町村)
        this.address1_txt = model.getAddressCity();
        // 住所(ﾋﾞﾙ、ﾏﾝｼｮﾝ名 等)
        this.address2_txt = model.getAddressOthers();
        // 性別区分分
        this.sex_rdl = model.getGenderKbn();
        // 国籍コード
        this.nationaliy_sel = model.getNationalityCd();
        // 母国語コード
        this.tongue_sel = model.getNativeLangCd();
        // メールアドレス1
        this.mailAdd1_txt = model.getMailAddress1();
        // メールアドレス2
        this.mailAdd2_txt = model.getMailAddress2();
        // メールアドレス3
        this.mailAdd3_txt = model.getMailAddress3();
        // 滞在国コード
        this.residence_sel = model.getCountryCd();
        // 時差地域NOコード
        this.regionNo_sel = model.getAreaNoCd();
        // 勤務拠点
        this.sites_txt = model.getCityTown();
        // 契約日
        this.contract_txt = NaikaraStringUtil.converToYYYY_MM_DD(model.getContractDt());
        // 契約開始日
        this.contractStart_txt = NaikaraStringUtil.converToYYYY_MM_DD(model.getContractStartDt());
        // 契約終了日
        this.contractEnd_txt = NaikaraStringUtil.converToYYYY_MM_DD(model.getContractEndDt());
        // 国内銀行向け送金の場合：銀行名
        this.bankName_txt = model.getBankJpnBankNm();
        // 国内銀行向け送金の場合：支店名
        this.bankbramchNm_txt = model.getBankJpnBranchNm();
        // 国内銀行向け送金の場合：預金種別
        this.bankType_txt = model.getBankJpnTypeOfAccount();
        // 国内銀行向け送金の場合：口座名義人（フリガナ）
        this.bankReceiptFuri_txt = model.getBankJpnAccountHoldersKnm();
        // 国内銀行向け送金の場合：口座名義人
        this.bankReceiptNm_txt = model.getBankJpnAccountHoldersNm();
        // 国内銀行向け送金の場合：口座番号
        this.bankAccountNo_txt = model.getBankJpnAccountNumber();
        // 国内銀行向け送金の場合：追加情報
        this.bankAdd_txt = model.getBankJpnAdditionalInfo();
        // 国内ゆうちょ銀行向け送金の場合：店番
        this.yuchoShopNo_txt = model.getJpnPbankBranchNm();
        // 国内ゆうちょ銀行向け送金の場合：預金種別
        this.yuchoType_txt = model.getJpnPbankTypeOfAccount();
        // 国内ゆうちょ銀行向け送金の場合：口座名義人（フリガナ）
        this.yuchoReceiptFuri_txt = model.getJpnPbankAccountHoldersKnm();
        // 国内ゆうちょ銀行向け送金の場合：口座名義人
        this.yuchoReceiptNm_txt = model.getJpnPbankAccountHoldersNm();
        // 国内ゆうちょ銀行向け送金の場合：口座番号
        this.yuchoAccountNo_txt = model.getJpnPbankAccountNumber();
        // 国内ゆうちょ銀行向け送金の場合：追加情報
        this.yuchoAdd_txt = model.getJpnPbankAdditionalInfo();
        // 海外送金の場合：口座名義人
        this.overseaAccountName_txt = model.getOverseaAccountHNm();
        // 海外送金の場合：口座名義人登録住所
        this.overseaAccountAddress1_txt = model.getOverseaAccountHRAddress1();
        // 海外送金の場合：口座名義人登録住所（上記住所欄が一杯のとき）
        this.overseaAccountAddress2_txt = model.getOverseaAccountHRAddress2();
        // 海外送金の場合：口座番号/IBAN（ヨーロッパ）
        this.overseaAccountNumber_txt = model.getOverseaAccountNumberIban();
        // 海外送金の場合：ABAナンバー/SWIFTコード/BIC　Code　等
        this.overseaCode1_txt = model.getOverseaAbanoSwiftcdBiccd();
        // 海外送金の場合：等
        this.overseaCode2_txt = model.getOverseaEtc();
        // 海外送金の場合：銀行名
        this.overseaBankName_txt = model.getOverseaBankNm();
        // 海外送金の場合：支店名
        this.overseaBranchName_txt = model.getOverseaBranchNm();
        // 海外送金の場合：支店住所
        this.overseaBranchAddress1_txt = model.getOverseaBranchAddress1();
        // 海外送金の場合：支店住所（上記住所欄が一杯のとき）
        this.overseaBranchAddress2_txt = model.getOverseaBranchAddress2();
        // 海外送金の場合：支店が所在する国名
        this.overseaBranchCountry_txt = model.getOverseaCountryBranchExists();
        // 外国送金関係銀行手数料区分
        this.oversea_rdl = model.getOtherRemittanceFeeKbn();
        // 海外送金の場合：追加情報
        this.overseaAdd_txt = model.getOverseaAdditionalInfo();
        // 海外ペイパル送金の場合:PayPalアドレス
        this.paypalAccount_txt = model.getOverseaPlPaypalAddress();
        // 海外ペイパル送金の場合:追加情報
        this.paypalAdd_txt = model.getOverseaPlAdditionalInfo();
        // セールスポイント(スクール記入)
        this.sellingPoint_txa = model.getSellingPoint();
        // 受講生へのアピールポイント
        this.appealPoint_txa = model.getSelfRecommendation();

        // 講師別コースマスタ(+コースマスタ)
        this.teacherCourseDtoList = model.getTeacherCourseDtoList();

        // 講師画像：ファイル名(登録用)
        this.teacher_fileName = model.getTeacherFileName();
        // 講師画像名
        this.teacher_filFileName = model.getTeacherFileName();
        // 講師画像
        this.teacher_fil = model.getImgPhoto();
        // 講師画像削除
        this.imgPhoto_chkn = model.getImgPhotoChkn();
        // スクール側からのコメント(講師公開)
        this.schoolEvaluationOpen_txa = model.getEvaluationFromSchoolCmt1();
        // スクール側からのコメント(講師非公開)
        this.schoolEvaluationClose_txa = model.getEvaluationFromSchoolCmt2();
        // 最新の受講生から講師へのコメント
        this.studentEvaluation_txa = model.getLatestEvaluationFromStudentCmt();
        // 備考(講師は見えません)
        this.remarks_txa = model.getRemarkT();
        // 利用開始日
        this.useStartDt = model.getUseStartDt();
        // 利用終了日
        this.useEndDt = model.getUseEndDt();
        // レコードバージョン番号
        this.recordVerNoU = String.valueOf(model.getRecordVerNoU());
        // 講師レコードバージョン番号
        this.recordVerNoT = String.valueOf(model.getRecordVerNoT());
    }

    /**
     * 検索の後 Session値 To Action<br>
     * <br>
     * SessionUserMstTeacherMst値をActionにセット。<br>
     * <br>
     * @param なし<br>
     * @return なし<br>
     * @exception Exception
     */
    private void sessionToAction() {
        if ((SessionUserMstTeacherMst) SessionDataUtil.getSessionData(SessionUserMstTeacherMst.class.toString()) != null) {

            SessionUserMstTeacherMst sessionData = (SessionUserMstTeacherMst) SessionDataUtil
                    .getSessionData(SessionUserMstTeacherMst.class.toString());

            // 利用者ID
            this.userId = sessionData.getUserId();
            // 講師マスタ．利用者ID
            this.userIdT = sessionData.getUserIdT();
            // 処理判定
            if (StringUtils.isEmpty(this.userIdT)) {
                this.processDifference = NaikaraTalkConstants.PROCESSKBN_INS;
            } else {
                this.processDifference = NaikaraTalkConstants.PROCESSKBN_UPD;
            }
            // 名前(姓)
            this.nameFamiy_txt = sessionData.getFamilyJnm();
            // 名前(名)
            this.nameFirst_txt = sessionData.getFirstJnm();
            // フリガナ(姓)
            this.furiganaFamiy_txt = sessionData.getFamilyKnm();
            // フリガナ(名)
            this.furiganaFirst_txt = sessionData.getFirstKnm();
            // ローマ字(姓)
            this.romajiFamiy_txt = sessionData.getFamilyRomaji();
            // ローマ字(名)
            this.romajiFirst_txt = sessionData.getFirstRomaji();
            // ニックネーム
            this.nickName_txt = sessionData.getNickAnm();
            // 電話番号1
            this.telephone1_txt = sessionData.getTel1();
            // 電話番号2
            this.telephone2_txt = sessionData.getTel2();
            // 生年月日：年
            this.birthdayYy_txt = sessionData.getBirthYyyy();
            // 生年月日：月
            this.birthdayMm_txt = sessionData.getBirthMm();
            // 生年月日：日
            this.birthdayDd_txt = sessionData.getBirthDd();
            // 郵便番号
            this.zipCode_txt = sessionData.getZipCd();
            // 住所(市区町村)
            this.address1_txt = sessionData.getAddressCity();
            // 住所(ﾋﾞﾙ、ﾏﾝｼｮﾝ名 等)
            this.address2_txt = sessionData.getAddressOthers();
            // 性別区分分
            this.sex_rdl = sessionData.getGenderKbn();
            // 国籍コード
            this.nationaliy_sel = sessionData.getNationalityCd();
            // 母国語コード
            this.tongue_sel = sessionData.getNativeLangCd();
            // メールアドレス1
            this.mailAdd1_txt = sessionData.getMailAddress1();
            // メールアドレス2
            this.mailAdd2_txt = sessionData.getMailAddress2();
            // メールアドレス3
            this.mailAdd3_txt = sessionData.getMailAddress3();
            // 滞在国コード
            this.residence_sel = sessionData.getCountryCd();
            // 時差地域NOコード
            this.regionNo_sel = sessionData.getAreaNoCd();
            // 勤務拠点
            this.sites_txt = sessionData.getCityTown();
            // 契約日
            this.contract_txt = NaikaraStringUtil.converToYYYY_MM_DD(sessionData.getContractDt());
            // 契約開始日
            this.contractStart_txt = NaikaraStringUtil.converToYYYY_MM_DD(sessionData.getContractStartDt());
            // 契約終了日
            this.contractEnd_txt = NaikaraStringUtil.converToYYYY_MM_DD(sessionData.getContractEndDt());
            // 国内銀行向け送金の場合：銀行名
            this.bankName_txt = sessionData.getBankJpnBankNm();
            // 国内銀行向け送金の場合：支店名
            this.bankbramchNm_txt = sessionData.getBankJpnBranchNm();
            // 国内銀行向け送金の場合：預金種別
            this.bankType_txt = sessionData.getBankJpnTypeOfAccount();
            // 国内銀行向け送金の場合：口座名義人（フリガナ）
            this.bankReceiptFuri_txt = sessionData.getBankJpnAccountHoldersKnm();
            // 国内銀行向け送金の場合：口座名義人
            this.bankReceiptNm_txt = sessionData.getBankJpnAccountHoldersNm();
            // 国内銀行向け送金の場合：口座番号
            this.bankAccountNo_txt = sessionData.getBankJpnAccountNumber();
            // 国内銀行向け送金の場合：追加情報
            this.bankAdd_txt = sessionData.getBankJpnAdditionalInfo();
            // 国内ゆうちょ銀行向け送金の場合：店番
            this.yuchoShopNo_txt = sessionData.getJpnPbankBranchNm();
            // 国内ゆうちょ銀行向け送金の場合：預金種別
            this.yuchoType_txt = sessionData.getJpnPbankTypeOfAccount();
            // 国内ゆうちょ銀行向け送金の場合：口座名義人（フリガナ）
            this.yuchoReceiptFuri_txt = sessionData.getJpnPbankAccountHoldersKnm();
            // 国内ゆうちょ銀行向け送金の場合：口座名義人
            this.yuchoReceiptNm_txt = sessionData.getJpnPbankAccountHoldersNm();
            // 国内ゆうちょ銀行向け送金の場合：口座番号
            this.yuchoAccountNo_txt = sessionData.getJpnPbankAccountNumber();
            // 国内ゆうちょ銀行向け送金の場合：追加情報
            this.yuchoAdd_txt = sessionData.getJpnPbankAdditionalInfo();
            // 海外送金の場合：口座名義人
            this.overseaAccountName_txt = sessionData.getOverseaAccountHNm();
            // 海外送金の場合：口座名義人登録住所
            this.overseaAccountAddress1_txt = sessionData.getOverseaAccountHRAddress1();
            // 海外送金の場合：口座名義人登録住所（上記住所欄が一杯のとき）
            this.overseaAccountAddress2_txt = sessionData.getOverseaAccountHRAddress2();
            // 海外送金の場合：口座番号/IBAN（ヨーロッパ）
            this.overseaAccountNumber_txt = sessionData.getOverseaAccountNumberIban();
            // 海外送金の場合：ABAナンバー/SWIFTコード/BIC　Code　等
            this.overseaCode1_txt = sessionData.getOverseaAbanoSwiftcdBiccd();
            // 海外送金の場合：等
            this.overseaCode2_txt = sessionData.getOverseaEtc();
            // 海外送金の場合：銀行名
            this.overseaBankName_txt = sessionData.getOverseaBankNm();
            // 海外送金の場合：支店名
            this.overseaBranchName_txt = sessionData.getOverseaBranchNm();
            // 海外送金の場合：支店住所
            this.overseaBranchAddress1_txt = sessionData.getOverseaBranchAddress1();
            // 海外送金の場合：支店住所（上記住所欄が一杯のとき）
            this.overseaBranchAddress2_txt = sessionData.getOverseaBranchAddress2();
            // 海外送金の場合：支店が所在する国名
            this.overseaBranchCountry_txt = sessionData.getOverseaCountryBranchExists();
            // 外国送金関係銀行手数料区分
            this.oversea_rdl = sessionData.getOtherRemittanceFeeKbn();
            // 海外送金の場合：追加情報
            this.overseaAdd_txt = sessionData.getOverseaAdditionalInfo();
            // 海外ペイパル送金の場合:PayPalアドレス
            this.paypalAccount_txt = sessionData.getOverseaPlPaypalAddress();
            // 海外ペイパル送金の場合:追加情報
            this.paypalAdd_txt = sessionData.getOverseaPlAdditionalInfo();

            // 講師別コースマスタ(+コースマスタ)
            this.teacherCourseDtoList = sessionData.getTeacherCourseDtoList();

            // セールスポイント(スクール記入)
            this.sellingPoint_txa = sessionData.getSellingPoint();
            // 受講生へのアピールポイント
            this.appealPoint_txa = sessionData.getSelfRecommendation();
            // 講師画像：ファイル名(登録用)
            this.teacher_fileName = sessionData.getTeacherFileName();
            // 講師画像名
            this.teacher_filFileName = sessionData.getImgPhotoNm();
            // 講師画像
            this.teacher_fil = sessionData.getImgPhoto();
            // 講師画像削除
            this.imgPhoto_chkn = sessionData.getImgPhotoChkn();
            // スクール側からのコメント(講師公開)
            this.schoolEvaluationOpen_txa = sessionData.getEvaluationFromSchoolCmt1();
            // スクール側からのコメント(講師非公開)
            this.schoolEvaluationClose_txa = sessionData.getEvaluationFromSchoolCmt2();
            // 最新の受講生から講師へのコメント
            this.studentEvaluation_txa = sessionData.getLatestEvaluationFromStudentCmt();
            // 備考(講師は見えません)
            this.remarks_txa = sessionData.getRemarkT();
            // 利用開始日
            this.useStartDt = sessionData.getUseStartDt();
            // 利用終了日
            this.useEndDt = sessionData.getUseEndDt();
            // レコードバージョン番号
            this.recordVerNoU = String.valueOf(sessionData.getRecordVerNoU());
            // 講師レコードバージョン番号
            this.recordVerNoT = String.valueOf(sessionData.getRecordVerNoT());
            // 講師支払比率
            this.teacherRateMstDtoList = sessionData.getResultList();
            // 処理区分(前画面情報)
            this.processKbn_rdl = sessionData.getProcessKbn_rdl();
            // 画面表示処理区分
            this.processKbn_txt = sessionData.getProcessKbn_txt();

            // 処理区分＝照会の場合('1':'修正','2':'照会')
            if (StringUtils.equals(PointControlListModel.PROS_KBN_REF, this.processKbn_rdl)) {
                this.setComment(NaikaraTalkConstants.PROCESSKBN_REF_COMMENT);
            }

        }
    }

    /**
     * Action値 To Session<br>
     * <br>
     * Action値・SessionUserMstTeacherMst値をSessionUserMstTeacherMstにセット。<br>
     * <br>
     * @param なし<br>
     * @return なし<br>
     * @exception Exception
     */
    private void actionToSession() throws Exception {

        // 戻る用に必要な情報を格納
        SessionUserMstTeacherMst sessionData = null;
        if ((SessionUserMstTeacherMst) SessionDataUtil.getSessionData(SessionUserMstTeacherMst.class.toString()) != null) {
            sessionData = (SessionUserMstTeacherMst) SessionDataUtil.getSessionData(SessionUserMstTeacherMst.class
                    .toString());
        } else {
            sessionData = new SessionUserMstTeacherMst();
        }
        sessionData.setReturnOnFlg(false);

        // 利用者ID
        sessionData.setUserId(this.userId);
        // 講師マスタ．利用者ID
        sessionData.setUserIdT(this.userIdT);
        // 名前(姓)
        sessionData.setFamilyJnm(this.nameFamiy_txt);
        // 名前(名)
        sessionData.setFirstJnm(this.nameFirst_txt);
        // フリガナ(姓)
        sessionData.setFamilyKnm(this.furiganaFamiy_txt);
        // フリガナ(名)
        sessionData.setFirstKnm(this.furiganaFirst_txt);
        // ローマ字(姓)
        sessionData.setFamilyRomaji(this.romajiFamiy_txt);
        // ローマ字(名)
        sessionData.setFirstRomaji(this.romajiFirst_txt);
        // ニックネーム
        sessionData.setNickAnm(this.nickName_txt);
        // 電話番号1
        sessionData.setTel1(this.telephone1_txt);
        // 電話番号2
        sessionData.setTel2(this.telephone2_txt);
        // 生年月日：年
        sessionData.setBirthYyyy(this.birthdayYy_txt);
        // 生年月日：月
        sessionData.setBirthMm(this.birthdayMm_txt);
        // 生年月日：日
        sessionData.setBirthDd(this.birthdayDd_txt);
        // 郵便番号
        sessionData.setZipCd(this.zipCode_txt);
        // 住所(市区町村)
        sessionData.setAddressCity(this.address1_txt);
        // 住所(ﾋﾞﾙ、ﾏﾝｼｮﾝ名 等)
        sessionData.setAddressOthers(this.address2_txt);
        // 性別区分分
        sessionData.setGenderKbn(this.sex_rdl);
        // 国籍コード
        sessionData.setNationalityCd(this.nationaliy_sel);
        // 母国語コード
        sessionData.setNativeLangCd(this.tongue_sel);
        // メールアドレス1
        sessionData.setMailAddress1(this.mailAdd1_txt);
        // メールアドレス2
        sessionData.setMailAddress2(this.mailAdd2_txt);
        // メールアドレス3
        sessionData.setMailAddress3(this.mailAdd3_txt);
        // 滞在国コード
        sessionData.setCountryCd(this.residence_sel);
        // 時差地域NOコード
        sessionData.setAreaNoCd(this.regionNo_sel);
        // 勤務拠点
        sessionData.setCityTown(this.sites_txt);
        // 契約日
        sessionData.setContractDt(this.contract_txt);
        // 契約開始日
        sessionData.setContractStartDt(this.contractStart_txt);
        // 契約終了日
        sessionData.setContractEndDt(this.contractEnd_txt);
        // 国内銀行向け送金の場合：銀行名
        sessionData.setBankJpnBankNm(this.bankName_txt);
        // 国内銀行向け送金の場合：支店名
        sessionData.setBankJpnBranchNm(this.bankbramchNm_txt);
        // 国内銀行向け送金の場合：預金種別
        sessionData.setBankJpnTypeOfAccount(this.bankType_txt);
        // 国内銀行向け送金の場合：口座名義人（フリガナ）
        sessionData.setBankJpnAccountHoldersKnm(this.bankReceiptFuri_txt);
        // 国内銀行向け送金の場合：口座名義人
        sessionData.setBankJpnAccountHoldersNm(this.bankReceiptNm_txt);
        // 国内銀行向け送金の場合：口座番号
        sessionData.setBankJpnAccountNumber(this.bankAccountNo_txt);
        // 国内銀行向け送金の場合：追加情報
        sessionData.setBankJpnAdditionalInfo(this.bankAdd_txt);
        // 国内ゆうちょ銀行向け送金の場合:店番
        sessionData.setJpnPbankBranchNm(this.yuchoShopNo_txt);
        // 国内ゆうちょ銀行向け送金の場合:預金種別
        sessionData.setJpnPbankTypeOfAccount(this.yuchoType_txt);
        // 国内ゆうちょ銀行向け送金の場合:口座名義人（フリガナ）
        sessionData.setJpnPbankAccountHoldersKnm(this.yuchoReceiptFuri_txt);
        // 国内ゆうちょ銀行向け送金の場合:口座名義人
        sessionData.setJpnPbankAccountHoldersNm(this.yuchoReceiptNm_txt);
        // 国内ゆうちょ銀行向け送金の場合：口座番号
        sessionData.setJpnPbankAccountNumber(this.yuchoAccountNo_txt);
        // 国内ゆうちょ銀行向け送金の場合：追加情報
        sessionData.setJpnPbankAdditionalInfo(this.yuchoAdd_txt);
        // 海外送金の場合:口座名義人
        sessionData.setOverseaAccountHNm(this.overseaAccountName_txt);
        // 海外送金の場合:口座名義人登録住所
        sessionData.setOverseaAccountHRAddress1(this.overseaAccountAddress1_txt);
        // 海外送金の場合:口座名義人登録住所（上記住所欄が一杯のとき）
        sessionData.setOverseaAccountHRAddress2(this.overseaAccountAddress2_txt);
        // 海外送金の場合:口座番号/IBAN（ヨーロッパ）
        sessionData.setOverseaAccountNumberIban(this.overseaAccountNumber_txt);
        // 海外送金の場合:ABAナンバー/SWIFTコード/BIC　Code　等
        sessionData.setOverseaAbanoSwiftcdBiccd(this.overseaCode1_txt);
        // 海外送金の場合:等
        sessionData.setOverseaEtc(this.overseaCode2_txt);
        // 海外送金の場合:銀行名
        sessionData.setOverseaBankNm(this.overseaBankName_txt);
        // 海外送金の場合:支店名
        sessionData.setOverseaBranchNm(this.overseaBranchName_txt);
        // 海外送金の場合:支店住所
        sessionData.setOverseaBranchAddress1(this.overseaBranchAddress1_txt);
        // 海外送金の場合:支店住所（上記住所欄が一杯のとき）
        sessionData.setOverseaBranchAddress2(this.overseaBranchAddress2_txt);
        // 海外送金の場合:支店が所在する国名
        sessionData.setOverseaCountryBranchExists(this.overseaBranchCountry_txt);
        // 外国送金関係銀行手数料区分
        sessionData.setOtherRemittanceFeeKbn(this.oversea_rdl);
        // 海外送金の場合:追加情報
        sessionData.setOverseaAdditionalInfo(this.overseaAdd_txt);
        // 海外ペイパル送金の場合:PayPalアドレス
        sessionData.setOverseaPlPaypalAddress(this.paypalAccount_txt);
        // 海外ペイパル送金の場合:追加情報
        sessionData.setOverseaPlAdditionalInfo(this.paypalAdd_txt);
        // 講師別コースマスタ(+コースマスタ)
        sessionData.setTeacherCourseDtoList(this.teacherCourseDtoList);
        // セールスポイント(スクール記入)
        sessionData.setSellingPoint(this.sellingPoint_txa);
        // 受講生へのアピールポイント
        sessionData.setSelfRecommendation(this.appealPoint_txa);
        // 講師画像：ファイル名(登録用)
        sessionData.setTeacherFileName(this.teacher_fileName);
        // 講師画像名
        sessionData.setImgPhotoNm(this.teacher_filFileName);
        // 講師画像
        sessionData.setImgPhoto(this.teacher_fil);
        // 講師画像削除
        sessionData.setImgPhotoChkn(this.imgPhoto_chkn);
        // スクール側からのコメント(講師公開)
        sessionData.setEvaluationFromSchoolCmt1(this.schoolEvaluationOpen_txa);
        // スクール側からのコメント(講師非公開)
        sessionData.setEvaluationFromSchoolCmt2(this.schoolEvaluationClose_txa);
        // 最新の受講生から講師へのコメント
        sessionData.setLatestEvaluationFromStudentCmt(this.studentEvaluation_txa);
        // 備考(講師は見えません)
        sessionData.setRemarkT(this.remarks_txa);
        // 利用者レコードバージョン番号
        String recVerNoU = StringUtils.isEmpty(this.recordVerNoU) ? "0" : this.recordVerNoU;
        sessionData.setRecordVerNoU(Integer.parseInt(recVerNoU));
        // 利用開始日
        sessionData.setUseStartDt(this.useStartDt);
        // 利用終了日
        sessionData.setUseEndDt(this.useEndDt);
        // 講師レコードバージョン番号
        String recVerNoT = StringUtils.isEmpty(this.recordVerNoT) ? "0" : this.recordVerNoT;
        sessionData.setRecordVerNoT(Integer.parseInt(recVerNoT));
        // 講師支払比率
        sessionData.setResultList(this.teacherRateMstDtoList);
        // 処理区分(前画面情報)
        sessionData.setProcessKbn_rdl(this.processKbn_rdl);
        // 画面表示処理区分
        sessionData.setProcessKbn_txt(this.processKbn_txt);

        SessionDataUtil.setSessionData(sessionData);
    }

    /**
     * メッセージ情報を取得する。<br>
     * <br>
     * メッセージ情報を取得する。<br>
     * <br>
     * @param なし<br>
     * @return なし<br>
     * @exception Exception
     */
    private void getAlertMessage() throws Exception {
        // 契約期間：開始日 が設定されていない
        this.error_contractStart_empty = getMessage("EN0001", new String[] { "契約期間：開始日" });
        // 契約期間：終了日 が設定されていない
        this.error_contractEnd_empty = getMessage("EN0001", new String[] { "契約期間：終了日" });
        // 契約日 が日付ではない
        this.error_contract_notDate = getMessage("EN0010", new String[] { "契約日" });
        // 契約期間：開始日 が日付ではない
        this.error_contractStart_notDate = getMessage("EN0010", new String[] { "契約期間：開始日" });
        // 契約期間：終了日 が日付ではない
        this.error_contractEnd_notDate = getMessage("EN0010", new String[] { "契約期間：終了日" });
        // ｢契約日｣　＞　｢契約期間：開始日｣　の場合
        this.err_integrity_dt = getMessage("EN0011", new String[] { "契約日", "契約期間：開始日" });
        // ｢契約期間：開始日｣　＞　｢契約期間：終了日｣　の場合
        this.err_integrity_date = getMessage("EN0011", new String[] { "契約期間：開始日", "契約期間：終了日" });
    }
}