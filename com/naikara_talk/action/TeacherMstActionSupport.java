package com.naikara_talk.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dto.TeacherCourseDto;
import com.naikara_talk.dto.TeacherRateMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.model.TeacherMstModel;
import com.naikara_talk.service.TeacherMstLoadService;
import com.naikara_talk.sessiondata.SessionUserMstTeacherMst;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>マスタ保守<br>
 * <b>クラス名称       :</b>講師マスタメンテナンス(単票)<br>
 * <b>クラス概要       :</b>講師マスタメンテナンス(単票)共通Action<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/26 TECS 新規作成
 */
public abstract class TeacherMstActionSupport extends NaikaraActionSupport {

    private static final long serialVersionUID = 1L;

    // 画面表示のタイトル
    protected String title = "講師マスタメンテナンス";

    // Help画面名
    protected String helpPageId = "HelpTeacherMst.html";

    /**
     * 性別、国籍、母国語、滞在国、時差地域Noの再取得<br>
     * <br>
     * チェックエラーの場合、性別、国籍、母国語、滞在国、時差地域Noの再取得。<br>
     * <br>
     * @param なし<br>
     * @return なし<br>
     * @exception なし
     */
    @Override
    public void validate() {

        // チェックエラーの場合、性別、国籍、母国語、滞在国、時差地域Noの再取得。
        try {
            initRadio();
            SessionUserMstTeacherMst sessionData = null;

            // 講師マスタメンテナンスのセッション情報の存在判断
            if ((SessionUserMstTeacherMst) SessionDataUtil.getSessionData(SessionUserMstTeacherMst.class.toString()) != null) {
                sessionData = (SessionUserMstTeacherMst) SessionDataUtil.getSessionData(SessionUserMstTeacherMst.class
                        .toString());
            } else {
                sessionData = new SessionUserMstTeacherMst();
            }
            // 講師画像の選択判断
            if (this.teacher_fil != null) {
                // 講師画像はセッションに存在判断
                if (sessionData.getImgPhoto() != null) {
                    File file = sessionData.getImgPhoto();
                    file.delete();
                }
                InputStream is = new FileInputStream(this.teacher_fil);

                byte[] bytes = new byte[is.available()];
                is.read(bytes, 0, is.available());

                // 選択された講師画像をセッションに格納
                sessionData.setBytImgPhoto(bytes);

                is.close();

                // 講師画像Fileはセッションに格納
                sessionData.setImgPhoto(this.teacher_fil);
                // 講師画像名はセッションに格納
                sessionData.setImgPhotoNm(this.teacher_filFileName);
            } else {

                // 講師画像はセッションに存在判断
                if (sessionData.getBytImgPhoto() != null) {
                    File file = sessionData.getImgPhoto();
                    byte[] bytes = sessionData.getBytImgPhoto();
                    try {
                        OutputStream os = new FileOutputStream(file);
                        if (bytes != null) {
                            os.write(bytes);
                        }
                        os.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * サービスの呼び出しの実装。
     */
    abstract protected String requestService() throws NaiException;

    /**
     * 性別、国籍、母国語、滞在国、時差地域Noを取得する。<br>
     * <br>
     * 性別、国籍、母国語、滞在国、時差地域Noを取得する。<br>
     * <br>
     * @param なし<br>
     * @return なし<br>
     * @exception Exception
     */
    public void initRadio() throws Exception {

        TeacherMstLoadService service = new TeacherMstLoadService();
        // 性別を取得する
        this.sex_rdll = service.selectCodeMst(NaikaraTalkConstants.CODE_CATEGORY_GENDER);
        // 国籍を取得する
        this.nationaliy_sell = service.selectCodeMst(NaikaraTalkConstants.CODE_CATEGORY_COUNTRY);
        // 母国語を取得する
        this.tongue_sell = service.selectCodeMst(NaikaraTalkConstants.CODE_CATEGORY_NATIVE_LANG);
        // 滞在国を取得する
        this.residence_sell = service.selectCodeMst(NaikaraTalkConstants.CODE_CATEGORY_COUNTRY);
        // 時差地域Noを取得する
        this.regionNo_sell = service.selectCodeMst(NaikaraTalkConstants.CODE_CATEGORY_AREA_NO);
        // 外国送金関係銀行手数料区分を取得する
        this.oversea_rdll = service.selectCodeMst(NaikaraTalkConstants.CODE_CATEGORY_OTHER_REMITTANCE_FEE_KBN);
    }

    /**
     * 画面のパラメータをモデルにセット。<br>
     * <br>
     * 画面のパラメータをモデルにセット。<br>
     * <br>
     * @param なし<br>
     * @return なし<br>
     * @exception なし
     */
    public void setupModel() {

        // 利用者ID
        this.model.setUserId(this.userId);
        // 講師マスタ．利用者ID
        this.model.setUserIdT(this.userIdT);
        // 名前(姓)
        this.model.setFamilyJnm(this.nameFamiy_txt);
        // 名前(名)
        this.model.setFirstJnm(this.nameFirst_txt);
        // フリガナ(姓)
        this.model.setFamilyKnm(this.furiganaFamiy_txt);
        // フリガナ(名)
        this.model.setFirstKnm(this.furiganaFirst_txt);
        // ローマ字(姓)
        this.model.setFamilyRomaji(this.romajiFamiy_txt);
        // ローマ字(名)
        this.model.setFirstRomaji(this.romajiFirst_txt);
        // ニックネーム
        this.model.setNickAnm(this.nickName_txt);
        // 電話番号1
        this.model.setTel1(this.telephone1_txt);
        // 電話番号2
        this.model.setTel2(this.telephone2_txt);
        // 生年月日：年
        this.model.setBirthYyyy(this.birthdayYy_txt);
        // 生年月日：月
        this.model.setBirthMm(this.birthdayMm_txt);
        // 生年月日：日
        this.model.setBirthDd(this.birthdayDd_txt);
        // 郵便番号
        this.model.setZipCd(this.zipCode_txt);
        // 住所(市区町村)
        this.model.setAddressCity(this.address1_txt);
        // 住所(ﾋﾞﾙ、ﾏﾝｼｮﾝ名 等)
        this.model.setAddressOthers(this.address2_txt);
        // 性別区分分
        this.model.setGenderKbn(this.sex_rdl);
        // 国籍コード
        this.model.setNationalityCd(this.nationaliy_sel);
        // 母国語コード
        this.model.setNativeLangCd(this.tongue_sel);
        // メールアドレス1
        this.model.setMailAddress1(this.mailAdd1_txt);
        // メールアドレス2
        this.model.setMailAddress2(this.mailAdd2_txt);
        // メールアドレス3
        this.model.setMailAddress3(this.mailAdd3_txt);
        // 滞在国コード
        this.model.setCountryCd(this.residence_sel);
        // 時差地域NOコード
        this.model.setAreaNoCd(this.regionNo_sel);
        // 勤務拠点
        this.model.setCityTown(this.sites_txt);
        // 契約日
        this.model.setContractDt(this.contract_txt);
        // 契約開始日
        this.model.setContractStartDt(this.contractStart_txt);
        // 契約終了日
        this.model.setContractEndDt(this.contractEnd_txt);
        // 講師支払比率
        this.model.setTeacherRateMstDtoList(this.teacherRateMstDtoList);
        // 国内銀行向け送金の場合：銀行名
        this.model.setBankJpnBankNm(this.bankName_txt);
        // 国内銀行向け送金の場合：支店名
        this.model.setBankJpnBranchNm(this.bankbramchNm_txt);
        // 国内銀行向け送金の場合：預金種別
        this.model.setBankJpnTypeOfAccount(this.bankType_txt);
        // 国内銀行向け送金の場合：口座名義人（フリガナ）
        this.model.setBankJpnAccountHoldersKnm(this.bankReceiptFuri_txt);
        // 国内銀行向け送金の場合：口座名義人
        this.model.setBankJpnAccountHoldersNm(this.bankReceiptNm_txt);
        // 国内銀行向け送金の場合：口座番号
        this.model.setBankJpnAccountNumber(this.bankAccountNo_txt);
        // 国内銀行向け送金の場合：追加情報
        this.model.setBankJpnAdditionalInfo(this.bankAdd_txt);
        // 国内ゆうちょ銀行向け送金の場合:店番
        this.model.setJpnPbankBranchNm(this.yuchoShopNo_txt);
        // 国内ゆうちょ銀行向け送金の場合:預金種別
        this.model.setJpnPbankTypeOfAccount(this.yuchoType_txt);
        // 国内ゆうちょ銀行向け送金の場合:口座名義人（フリガナ）
        this.model.setJpnPbankAccountHoldersKnm(this.yuchoReceiptFuri_txt);
        // 国内ゆうちょ銀行向け送金の場合:口座名義人
        this.model.setJpnPbankAccountHoldersNm(this.yuchoReceiptNm_txt);
        // 国内ゆうちょ銀行向け送金の場合：口座番号
        this.model.setJpnPbankAccountNumber(this.yuchoAccountNo_txt);
        // 国内ゆうちょ銀行向け送金の場合：追加情報
        this.model.setJpnPbankAdditionalInfo(this.yuchoAdd_txt);
        // 海外送金の場合:口座名義人
        this.model.setOverseaAccountHNm(this.overseaAccountName_txt);
        // 海外送金の場合:口座名義人登録住所
        this.model.setOverseaAccountHRAddress1(this.overseaAccountAddress1_txt);
        // 海外送金の場合:口座名義人登録住所（上記住所欄が一杯のとき）
        this.model.setOverseaAccountHRAddress2(this.overseaAccountAddress2_txt);
        // 海外送金の場合:口座番号/IBAN（ヨーロッパ）
        this.model.setOverseaAccountNumberIban(this.overseaAccountNumber_txt);
        // 海外送金の場合:ABAナンバー/SWIFTコード/BIC　Code　等
        this.model.setOverseaAbanoSwiftcdBiccd(this.overseaCode1_txt);
        // 海外送金の場合:等
        this.model.setOverseaEtc(this.overseaCode2_txt);
        // 海外送金の場合:銀行名
        this.model.setOverseaBankNm(this.overseaBankName_txt);
        // 海外送金の場合:支店名
        this.model.setOverseaBranchNm(this.overseaBranchName_txt);
        // 海外送金の場合:支店住所
        this.model.setOverseaBranchAddress1(this.overseaBranchAddress1_txt);
        // 海外送金の場合:支店住所（上記住所欄が一杯のとき）
        this.model.setOverseaBranchAddress2(this.overseaBranchAddress2_txt);
        // 海外送金の場合:支店が所在する国名
        this.model.setOverseaCountryBranchExists(this.overseaBranchCountry_txt);
        // 外国送金関係銀行手数料区分
        this.model.setOtherRemittanceFeeKbn(this.oversea_rdl);
        // 海外送金の場合:追加情報
        this.model.setOverseaAdditionalInfo(this.overseaAdd_txt);
        // 海外ペイパル送金の場合:PayPalアドレス
        this.model.setOverseaPlPaypalAddress(this.paypalAccount_txt);
        // 海外ペイパル送金の場合:追加情報
        this.model.setOverseaPlAdditionalInfo(this.paypalAdd_txt);
        // セールスポイント(スクール記入)
        this.model.setSellingPoint(this.sellingPoint_txa);
        // 受講生へのアピールポイント
        this.model.setSelfRecommendation(this.appealPoint_txa);
        // 講師画像：ファイル名(登録用)
        this.model.setTeacherFileName(this.teacher_fileName);

        if ((SessionUserMstTeacherMst) SessionDataUtil.getSessionData(SessionUserMstTeacherMst.class.toString()) != null) {
            SessionUserMstTeacherMst sessionData = (SessionUserMstTeacherMst) SessionDataUtil
                    .getSessionData(SessionUserMstTeacherMst.class.toString());
            // 講師画像
            this.model.setImgPhoto(sessionData.getImgPhoto());
            // 講師画像名
            this.model.setImgPhotoNm(sessionData.getImgPhotoNm());
        } else {
            // 講師画像
            this.model.setImgPhoto(this.teacher_fil);
            // 講師画像名
            this.model.setImgPhotoNm(this.teacher_filFileName);
        }
        // 講師画像削除
        this.model.setImgPhotoChkn(this.imgPhoto_chkn);

        // スクール側からのコメント(講師公開)
        this.model.setEvaluationFromSchoolCmt1(this.schoolEvaluationOpen_txa);
        // スクール側からのコメント(講師非公開)
        this.model.setEvaluationFromSchoolCmt2(this.schoolEvaluationClose_txa);
        // 最新の受講生から講師へのコメント
        this.model.setLatestEvaluationFromStudentCmt(this.studentEvaluation_txa);
        // 備考(講師は見えません)
        this.model.setRemarkT(this.remarks_txa);
        // レコードバージョン番号
        String recVerNoU = StringUtils.isEmpty(this.recordVerNoU) ? "0" : this.recordVerNoU;
        this.model.setRecordVerNoU(Integer.parseInt(recVerNoU));
        // 利用開始日
        this.model.setUseStartDt(this.useStartDt);
        // 利用終了日
        this.model.setUseEndDt(this.useEndDt);

        // レコードバージョン番号
        String recVerNoT = StringUtils.isEmpty(this.recordVerNoT) ? "0" : this.recordVerNoT;
        this.model.setRecordVerNoT(Integer.parseInt(recVerNoT));
        // 処理区分(前画面の引き継ぎ情報)
        this.model.setProcessKbn_rdl(this.processKbn_rdl);
        // 処理判定
        this.model.setProcessDifference(this.processDifference);
        // 講師別コースマスタ(+コースマスタ)
        this.model.setTeacherCourseDtoList(this.teacherCourseDtoList);
    }

    /** メッセージ */
    protected String message;

    /** 処理区分(前画面の引き継ぎ情報) */
    protected String processKbn_rdl;

    /** 画面表示処理区分 */
    protected String processKbn_txt;

    /** 処理判定 */
    protected String processDifference;

    /** 説明コメント */
    protected String comment;

    /** 利用者ID */
    protected String userId;

    /** 講師マスタ．利用者ID */
    protected String userIdT;

    /** 名前(姓) */
    protected String nameFamiy_txt;

    /** 名前(名) */
    protected String nameFirst_txt;

    /** フリガナ(姓) */
    protected String furiganaFamiy_txt;

    /** フリガナ(名) */
    protected String furiganaFirst_txt;

    /** ローマ字(姓) */
    protected String romajiFamiy_txt;

    /** ローマ字(名) */
    protected String romajiFirst_txt;

    /** ニックネーム */
    protected String nickName_txt;

    /** 電話番号1 */
    protected String telephone1_txt;

    /** 電話番号2 */
    protected String telephone2_txt;

    /** 生年月日：年 */
    protected String birthdayYy_txt;

    /** 生年月日：月 */
    protected String birthdayMm_txt;

    /** 生年月日：日 */
    protected String birthdayDd_txt;

    /** 郵便番号 */
    protected String zipCode_txt;

    /** 住所(市区町村) */
    protected String address1_txt;

    /** 住所(ﾋﾞﾙ、ﾏﾝｼｮﾝ名 等) */
    protected String address2_txt;

    /** 性別区分分 */
    protected String sex_rdl;
    protected Map<String, String> sex_rdll = new LinkedHashMap<String, String>();

    /** 国籍コード */
    protected String nationaliy_sel;
    protected Map<String, String> nationaliy_sell = new LinkedHashMap<String, String>();

    /** 母国語コード */
    protected String tongue_sel;
    protected Map<String, String> tongue_sell = new LinkedHashMap<String, String>();

    /** メールアドレス1 */
    protected String mailAdd1_txt;

    /** メールアドレス2 */
    protected String mailAdd2_txt;

    /** メールアドレス3 */
    protected String mailAdd3_txt;

    /** 滞在国コード */
    protected String residence_sel;
    protected Map<String, String> residence_sell = new LinkedHashMap<String, String>();

    /** 時差地域NOコード */
    protected String regionNo_sel;
    protected Map<String, String> regionNo_sell = new LinkedHashMap<String, String>();

    /** 勤務拠点 */
    protected String sites_txt;

    /** 契約日 */
    protected String contract_txt;

    /** 契約開始日 */
    protected String contractStart_txt;

    /** 契約終了日 */
    protected String contractEnd_txt;

    /** 講師支払比率 */
    protected List<TeacherRateMstDto> teacherRateMstDtoList;

    /** 国内銀行向け送金の場合：銀行名 */
    protected String bankName_txt;

    /** 国内銀行向け送金の場合：支店名 */
    protected String bankbramchNm_txt;

    /** 国内銀行向け送金の場合：預金種別 */
    protected String bankType_txt;

    /** 国内銀行向け送金の場合：口座名義人（フリガナ） */
    protected String bankReceiptFuri_txt;

    /** 国内銀行向け送金の場合：口座名義人 */
    protected String bankReceiptNm_txt;

    /** 国内銀行向け送金の場合：口座番号 */
    protected String bankAccountNo_txt;

    /** 国内銀行向け送金の場合：追加情報 */
    protected String bankAdd_txt;

    /** 国内ゆうちょ銀行向け送金の場合:店番 */
    protected String yuchoShopNo_txt;

    /** 国内ゆうちょ銀行向け送金の場合:預金種別 */
    protected String yuchoType_txt;

    /** 国内ゆうちょ銀行向け送金の場合:口座名義人（フリガナ） */
    protected String yuchoReceiptFuri_txt;

    /** 国内ゆうちょ銀行向け送金の場合:口座名義人 */
    protected String yuchoReceiptNm_txt;

    /** 国内ゆうちょ銀行向け送金の場合：口座番号 */
    protected String yuchoAccountNo_txt;

    /** 国内ゆうちょ銀行向け送金の場合：追加情報 */
    protected String yuchoAdd_txt;

    /** 海外送金の場合:口座名義人 */
    protected String overseaAccountName_txt;

    /** 海外送金の場合:口座名義人登録住所 */
    protected String overseaAccountAddress1_txt;

    /** 海外送金の場合:口座名義人登録住所（上記住所欄が一杯のとき） */
    protected String overseaAccountAddress2_txt;

    /** 海外送金の場合:口座番号/IBAN（ヨーロッパ） */
    protected String overseaAccountNumber_txt;

    /** 海外送金の場合:ABAナンバー/SWIFTコード/BIC　Code　等 */
    protected String overseaCode1_txt;

    /** 海外送金の場合:等 */
    protected String overseaCode2_txt;

    /** 海外送金の場合:銀行名 */
    protected String overseaBankName_txt;

    /** 海外送金の場合:支店名 */
    protected String overseaBranchName_txt;

    /** 海外送金の場合:支店住所 */
    protected String overseaBranchAddress1_txt;

    /** 海外送金の場合:支店住所（上記住所欄が一杯のとき） */
    protected String overseaBranchAddress2_txt;

    /** 海外送金の場合:支店が所在する国名 */
    protected String overseaBranchCountry_txt;

    /** 外国送金関係銀行手数料区分 */
    protected String oversea_rdl;
    protected Map<String, String> oversea_rdll = new LinkedHashMap<String, String>();

    /** 海外送金の場合:追加情報 */
    protected String overseaAdd_txt;

    /** 海外ペイパル送金の場合:PayPalアドレス */
    protected String paypalAccount_txt;

    /** 海外ペイパル送金の場合:追加情報 */
    protected String paypalAdd_txt;

    /** 講師別コースマスタ(+コースマスタ) */
    protected List<TeacherCourseDto> teacherCourseDtoList;

    /** セールスポイント(スクール記入) */
    protected String sellingPoint_txa;

    /** 受講生へのアピールポイント */
    protected String appealPoint_txa;

    /** 講師画像：ファイル名(登録用) */
    protected String teacher_fileName;

    /** 講師画像 */
    protected File teacher_fil;

    /** 講師画像名 */
    protected String teacher_filFileName;

    /** 講師画像削除 */
    protected String imgPhoto_chkn;

    /** スクール側からのコメント(講師公開) */
    protected String schoolEvaluationOpen_txa;

    /** スクール側からのコメント(講師非公開) */
    protected String schoolEvaluationClose_txa;

    /** 最新の受講生から講師へのコメント */
    protected String studentEvaluation_txa;

    /** 備考(講師は見えません) */
    protected String remarks_txa;

    /** レコードバージョン番号 */
    protected String recordVerNoU;

    /** 講師レコードバージョン番号 */
    protected String recordVerNoT;

    /** 利用開始日 */
    protected String useStartDt;

    /** 利用終了日 */
    protected String useEndDt;

    /** 契約期間：開始日 が設定されていない */
    protected String error_contractStart_empty;

    /** 契約期間：終了日 が設定されていない */
    protected String error_contractEnd_empty;

    /** 契約日 が日付ではない */
    protected String error_contract_notDate;

    /** 契約期間：開始日 が日付ではない */
    protected String error_contractStart_notDate;

    /** 契約期間：終了日 が日付ではない */
    protected String error_contractEnd_notDate;

    /** ｢契約日｣　＞　｢契約期間：開始日｣　の場合 */
    protected String err_integrity_dt;

    /** ｢契約期間：開始日｣　＞　｢契約期間：終了日｣　の場合 */
    protected String err_integrity_date;

    /** 処理結果 */
    protected TeacherMstModel model = new TeacherMstModel();

    /**
     * @return helpPageId
     */
    public String getHelpPageId() {
        return helpPageId;
    }

    /**
     * @param helpPageId
     *            セットする helpPageId
     */
    public void setHelpPageId(String helpPageId) {
        this.helpPageId = helpPageId;
    }

    /**
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title
     *            セットする title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message
     *            セットする message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return processKbn_rdl
     */
    public String getProcessKbn_rdl() {
        return processKbn_rdl;
    }

    /**
     * @param processKbn_rdl
     *            セットする processKbn_rdl
     */
    public void setProcessKbn_rdl(String processKbn_rdl) {
        this.processKbn_rdl = processKbn_rdl;
    }

    /**
     * @return processKbn_txt
     */
    public String getProcessKbn_txt() {
        return processKbn_txt;
    }

    /**
     * @param processKbn_txt
     *            セットする processKbn_txt
     */
    public void setProcessKbn_txt(String processKbn_txt) {
        this.processKbn_txt = processKbn_txt;
    }

    /**
     * @return processDifference
     */
    public String getProcessDifference() {
        return processDifference;
    }

    /**
     * @param processDifference セットする processDifference
     */
    public void setProcessDifference(String processDifference) {
        this.processDifference = processDifference;
    }

    /**
     * @return comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * @param comment
     *            セットする comment
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * @return userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId セットする userId
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return userIdT
     */
    public String getUserIdT() {
        return userIdT;
    }

    /**
     * @param userIdT セットする userIdT
     */
    public void setUserIdT(String userIdT) {
        this.userIdT = userIdT;
    }

    /**
     * @return nameFamiy_txt
     */
    public String getNameFamiy_txt() {
        return nameFamiy_txt;
    }

    /**
     * @param nameFamiy_txt セットする nameFamiy_txt
     */
    public void setNameFamiy_txt(String nameFamiy_txt) {
        this.nameFamiy_txt = nameFamiy_txt;
    }

    /**
     * @return nameFirst_txt
     */
    public String getNameFirst_txt() {
        return nameFirst_txt;
    }

    /**
     * @param nameFirst_txt セットする nameFirst_txt
     */
    public void setNameFirst_txt(String nameFirst_txt) {
        this.nameFirst_txt = nameFirst_txt;
    }

    /**
     * @return furiganaFamiy_txt
     */
    public String getFuriganaFamiy_txt() {
        return furiganaFamiy_txt;
    }

    /**
     * @param furiganaFamiy_txt セットする furiganaFamiy_txt
     */
    public void setFuriganaFamiy_txt(String furiganaFamiy_txt) {
        this.furiganaFamiy_txt = furiganaFamiy_txt;
    }

    /**
     * @return furiganaFirst_txt
     */
    public String getFuriganaFirst_txt() {
        return furiganaFirst_txt;
    }

    /**
     * @param furiganaFirst_txt セットする furiganaFirst_txt
     */
    public void setFuriganaFirst_txt(String furiganaFirst_txt) {
        this.furiganaFirst_txt = furiganaFirst_txt;
    }

    /**
     * @return romajiFamiy_txt
     */
    public String getRomajiFamiy_txt() {
        return romajiFamiy_txt;
    }

    /**
     * @param romajiFamiy_txt セットする romajiFamiy_txt
     */
    public void setRomajiFamiy_txt(String romajiFamiy_txt) {
        this.romajiFamiy_txt = romajiFamiy_txt;
    }

    /**
     * @return romajiFirst_txt
     */
    public String getRomajiFirst_txt() {
        return romajiFirst_txt;
    }

    /**
     * @param romajiFirst_txt セットする romajiFirst_txt
     */
    public void setRomajiFirst_txt(String romajiFirst_txt) {
        this.romajiFirst_txt = romajiFirst_txt;
    }

    /**
     * @return nickName_txt
     */
    public String getNickName_txt() {
        return nickName_txt;
    }

    /**
     * @param nickName_txt セットする nickName_txt
     */
    public void setNickName_txt(String nickName_txt) {
        this.nickName_txt = nickName_txt;
    }

    /**
     * @return telephone1_txt
     */
    public String getTelephone1_txt() {
        return telephone1_txt;
    }

    /**
     * @param telephone1_txt セットする telephone1_txt
     */
    public void setTelephone1_txt(String telephone1_txt) {
        this.telephone1_txt = telephone1_txt;
    }

    /**
     * @return telephone2_txt
     */
    public String getTelephone2_txt() {
        return telephone2_txt;
    }

    /**
     * @param telephone2_txt セットする telephone2_txt
     */
    public void setTelephone2_txt(String telephone2_txt) {
        this.telephone2_txt = telephone2_txt;
    }

    /**
     * @return birthdayYy_txt
     */
    public String getBirthdayYy_txt() {
        return birthdayYy_txt;
    }

    /**
     * @param birthdayYy_txt セットする birthdayYy_txt
     */
    public void setBirthdayYy_txt(String birthdayYy_txt) {
        this.birthdayYy_txt = birthdayYy_txt;
    }

    /**
     * @return birthdayMm_txt
     */
    public String getBirthdayMm_txt() {
        return birthdayMm_txt;
    }

    /**
     * @param birthdayMm_txt セットする birthdayMm_txt
     */
    public void setBirthdayMm_txt(String birthdayMm_txt) {
        this.birthdayMm_txt = birthdayMm_txt;
    }

    /**
     * @return birthdayDd_txt
     */
    public String getBirthdayDd_txt() {
        return birthdayDd_txt;
    }

    /**
     * @param birthdayDd_txt セットする birthdayDd_txt
     */
    public void setBirthdayDd_txt(String birthdayDd_txt) {
        this.birthdayDd_txt = birthdayDd_txt;
    }

    /**
     * @return zipCode_txt
     */
    public String getZipCode_txt() {
        return zipCode_txt;
    }

    /**
     * @param zipCode_txt セットする zipCode_txt
     */
    public void setZipCode_txt(String zipCode_txt) {
        this.zipCode_txt = zipCode_txt;
    }

    /**
     * @return address1_txt
     */
    public String getAddress1_txt() {
        return address1_txt;
    }

    /**
     * @param address1_txt セットする address1_txt
     */
    public void setAddress1_txt(String address1_txt) {
        this.address1_txt = address1_txt;
    }

    /**
     * @return address2_txt
     */
    public String getAddress2_txt() {
        return address2_txt;
    }

    /**
     * @param address2_txt セットする address2_txt
     */
    public void setAddress2_txt(String address2_txt) {
        this.address2_txt = address2_txt;
    }

    /**
     * @return sex_rdl
     */
    public String getSex_rdl() {
        return sex_rdl;
    }

    /**
     * @param sex_rdl セットする sex_rdl
     */
    public void setSex_rdl(String sex_rdl) {
        this.sex_rdl = sex_rdl;
    }

    /**
     * @return sex_rdll
     */
    public Map<String, String> getSex_rdll() {
        return sex_rdll;
    }

    /**
     * @param sex_rdll セットする sex_rdll
     */
    public void setSex_rdll(Map<String, String> sex_rdll) {
        this.sex_rdll = sex_rdll;
    }

    /**
     * @return nationaliy_sel
     */
    public String getNationaliy_sel() {
        return nationaliy_sel;
    }

    /**
     * @param nationaliy_sel セットする nationaliy_sel
     */
    public void setNationaliy_sel(String nationaliy_sel) {
        this.nationaliy_sel = nationaliy_sel;
    }

    /**
     * @return nationaliy_sell
     */
    public Map<String, String> getNationaliy_sell() {
        return nationaliy_sell;
    }

    /**
     * @param nationaliy_sell セットする nationaliy_sell
     */
    public void setNationaliy_sell(Map<String, String> nationaliy_sell) {
        this.nationaliy_sell = nationaliy_sell;
    }

    /**
     * @return tongue_sel
     */
    public String getTongue_sel() {
        return tongue_sel;
    }

    /**
     * @param tongue_sel セットする tongue_sel
     */
    public void setTongue_sel(String tongue_sel) {
        this.tongue_sel = tongue_sel;
    }

    /**
     * @return tongue_sell
     */
    public Map<String, String> getTongue_sell() {
        return tongue_sell;
    }

    /**
     * @param tongue_sell セットする tongue_sell
     */
    public void setTongue_sell(Map<String, String> tongue_sell) {
        this.tongue_sell = tongue_sell;
    }

    /**
     * @return mailAdd1_txt
     */
    public String getMailAdd1_txt() {
        return mailAdd1_txt;
    }

    /**
     * @param mailAdd1_txt セットする mailAdd1_txt
     */
    public void setMailAdd1_txt(String mailAdd1_txt) {
        this.mailAdd1_txt = mailAdd1_txt;
    }

    /**
     * @return mailAdd2_txt
     */
    public String getMailAdd2_txt() {
        return mailAdd2_txt;
    }

    /**
     * @param mailAdd2_txt セットする mailAdd2_txt
     */
    public void setMailAdd2_txt(String mailAdd2_txt) {
        this.mailAdd2_txt = mailAdd2_txt;
    }

    /**
     * @return mailAdd3_txt
     */
    public String getMailAdd3_txt() {
        return mailAdd3_txt;
    }

    /**
     * @param mailAdd3_txt セットする mailAdd3_txt
     */
    public void setMailAdd3_txt(String mailAdd3_txt) {
        this.mailAdd3_txt = mailAdd3_txt;
    }

    /**
     * @return residence_sel
     */
    public String getResidence_sel() {
        return residence_sel;
    }

    /**
     * @param residence_sel セットする residence_sel
     */
    public void setResidence_sel(String residence_sel) {
        this.residence_sel = residence_sel;
    }

    /**
     * @return residence_sell
     */
    public Map<String, String> getResidence_sell() {
        return residence_sell;
    }

    /**
     * @param residence_sell セットする residence_sell
     */
    public void setResidence_sell(Map<String, String> residence_sell) {
        this.residence_sell = residence_sell;
    }

    /**
     * @return regionNo_sel
     */
    public String getRegionNo_sel() {
        return regionNo_sel;
    }

    /**
     * @param regionNo_sel セットする regionNo_sel
     */
    public void setRegionNo_sel(String regionNo_sel) {
        this.regionNo_sel = regionNo_sel;
    }

    /**
     * @return regionNo_sell
     */
    public Map<String, String> getRegionNo_sell() {
        return regionNo_sell;
    }

    /**
     * @param regionNo_sell セットする regionNo_sell
     */
    public void setRegionNo_sell(Map<String, String> regionNo_sell) {
        this.regionNo_sell = regionNo_sell;
    }

    /**
     * @return sites_txt
     */
    public String getSites_txt() {
        return sites_txt;
    }

    /**
     * @param sites_txt セットする sites_txt
     */
    public void setSites_txt(String sites_txt) {
        this.sites_txt = sites_txt;
    }

    /**
     * @return contract_txt
     */
    public String getContract_txt() {
        return contract_txt;
    }

    /**
     * @param contract_txt セットする contract_txt
     */
    public void setContract_txt(String contract_txt) {
        this.contract_txt = contract_txt;
    }

    /**
     * @return contractStart_txt
     */
    public String getContractStart_txt() {
        return contractStart_txt;
    }

    /**
     * @param contractStart_txt セットする contractStart_txt
     */
    public void setContractStart_txt(String contractStart_txt) {
        this.contractStart_txt = contractStart_txt;
    }

    /**
     * @return contractEnd_txt
     */
    public String getContractEnd_txt() {
        return contractEnd_txt;
    }

    /**
     * @param contractEnd_txt セットする contractEnd_txt
     */
    public void setContractEnd_txt(String contractEnd_txt) {
        this.contractEnd_txt = contractEnd_txt;
    }

    /**
     * @return teacherRateMstDtoList
     */
    public List<TeacherRateMstDto> getTeacherRateMstDtoList() {
        if (null == this.teacherRateMstDtoList) {
            this.teacherRateMstDtoList = new ArrayList<TeacherRateMstDto>();
        }
        return teacherRateMstDtoList;
    }

    /**
     * @param teacherRateMstDtoList セットする teacherRateMstDtoList
     */
    public void setTeacherRateMstDtoList(List<TeacherRateMstDto> teacherRateMstDtoList) {
        this.teacherRateMstDtoList = teacherRateMstDtoList;
    }

    /**
     * @return bankName_txt
     */
    public String getBankName_txt() {
        return bankName_txt;
    }

    /**
     * @param bankName_txt セットする bankName_txt
     */
    public void setBankName_txt(String bankName_txt) {
        this.bankName_txt = bankName_txt;
    }

    /**
     * @return bankbramchNm_txt
     */
    public String getBankbramchNm_txt() {
        return bankbramchNm_txt;
    }

    /**
     * @param bankbramchNm_txt セットする bankbramchNm_txt
     */
    public void setBankbramchNm_txt(String bankbramchNm_txt) {
        this.bankbramchNm_txt = bankbramchNm_txt;
    }

    /**
     * @return bankType_txt
     */
    public String getBankType_txt() {
        return bankType_txt;
    }

    /**
     * @param bankType_txt セットする bankType_txt
     */
    public void setBankType_txt(String bankType_txt) {
        this.bankType_txt = bankType_txt;
    }

    /**
     * @return bankReceiptFuri_txt
     */
    public String getBankReceiptFuri_txt() {
        return bankReceiptFuri_txt;
    }

    /**
     * @param bankReceiptFuri_txt セットする bankReceiptFuri_txt
     */
    public void setBankReceiptFuri_txt(String bankReceiptFuri_txt) {
        this.bankReceiptFuri_txt = bankReceiptFuri_txt;
    }

    /**
     * @return bankReceiptNm_txt
     */
    public String getBankReceiptNm_txt() {
        return bankReceiptNm_txt;
    }

    /**
     * @param bankReceiptNm_txt セットする bankReceiptNm_txt
     */
    public void setBankReceiptNm_txt(String bankReceiptNm_txt) {
        this.bankReceiptNm_txt = bankReceiptNm_txt;
    }

    /**
     * @return bankAccountNo_txt
     */
    public String getBankAccountNo_txt() {
        return bankAccountNo_txt;
    }

    /**
     * @param bankAccountNo_txt セットする bankAccountNo_txt
     */
    public void setBankAccountNo_txt(String bankAccountNo_txt) {
        this.bankAccountNo_txt = bankAccountNo_txt;
    }

    /**
     * @return bankAdd_txt
     */
    public String getBankAdd_txt() {
        return bankAdd_txt;
    }

    /**
     * @param bankAdd_txt セットする bankAdd_txt
     */
    public void setBankAdd_txt(String bankAdd_txt) {
        this.bankAdd_txt = bankAdd_txt;
    }

    /**
     * @return yuchoShopNo_txt
     */
    public String getYuchoShopNo_txt() {
        return yuchoShopNo_txt;
    }

    /**
     * @param yuchoShopNo_txt セットする yuchoShopNo_txt
     */
    public void setYuchoShopNo_txt(String yuchoShopNo_txt) {
        this.yuchoShopNo_txt = yuchoShopNo_txt;
    }

    /**
     * @return yuchoType_txt
     */
    public String getYuchoType_txt() {
        return yuchoType_txt;
    }

    /**
     * @param yuchoType_txt セットする yuchoType_txt
     */
    public void setYuchoType_txt(String yuchoType_txt) {
        this.yuchoType_txt = yuchoType_txt;
    }

    /**
     * @return yuchoReceiptFuri_txt
     */
    public String getYuchoReceiptFuri_txt() {
        return yuchoReceiptFuri_txt;
    }

    /**
     * @param yuchoReceiptFuri_txt セットする yuchoReceiptFuri_txt
     */
    public void setYuchoReceiptFuri_txt(String yuchoReceiptFuri_txt) {
        this.yuchoReceiptFuri_txt = yuchoReceiptFuri_txt;
    }

    /**
     * @return yuchoReceiptNm_txt
     */
    public String getYuchoReceiptNm_txt() {
        return yuchoReceiptNm_txt;
    }

    /**
     * @param yuchoReceiptNm_txt セットする yuchoReceiptNm_txt
     */
    public void setYuchoReceiptNm_txt(String yuchoReceiptNm_txt) {
        this.yuchoReceiptNm_txt = yuchoReceiptNm_txt;
    }

    /**
     * @return yuchoAccountNo_txt
     */
    public String getYuchoAccountNo_txt() {
        return yuchoAccountNo_txt;
    }

    /**
     * @param yuchoAccountNo_txt セットする yuchoAccountNo_txt
     */
    public void setYuchoAccountNo_txt(String yuchoAccountNo_txt) {
        this.yuchoAccountNo_txt = yuchoAccountNo_txt;
    }

    /**
     * @return yuchoAdd_txt
     */
    public String getYuchoAdd_txt() {
        return yuchoAdd_txt;
    }

    /**
     * @param yuchoAdd_txt セットする yuchoAdd_txt
     */
    public void setYuchoAdd_txt(String yuchoAdd_txt) {
        this.yuchoAdd_txt = yuchoAdd_txt;
    }

    /**
     * @return overseaAccountName_txt
     */
    public String getOverseaAccountName_txt() {
        return overseaAccountName_txt;
    }

    /**
     * @param overseaAccountName_txt セットする overseaAccountName_txt
     */
    public void setOverseaAccountName_txt(String overseaAccountName_txt) {
        this.overseaAccountName_txt = overseaAccountName_txt;
    }

    /**
     * @return overseaAccountAddress1_txt
     */
    public String getOverseaAccountAddress1_txt() {
        return overseaAccountAddress1_txt;
    }

    /**
     * @param overseaAccountAddress1_txt セットする overseaAccountAddress1_txt
     */
    public void setOverseaAccountAddress1_txt(String overseaAccountAddress1_txt) {
        this.overseaAccountAddress1_txt = overseaAccountAddress1_txt;
    }

    /**
     * @return overseaAccountAddress2_txt
     */
    public String getOverseaAccountAddress2_txt() {
        return overseaAccountAddress2_txt;
    }

    /**
     * @param overseaAccountAddress2_txt セットする overseaAccountAddress2_txt
     */
    public void setOverseaAccountAddress2_txt(String overseaAccountAddress2_txt) {
        this.overseaAccountAddress2_txt = overseaAccountAddress2_txt;
    }

    /**
     * @return overseaAccountNumber_txt
     */
    public String getOverseaAccountNumber_txt() {
        return overseaAccountNumber_txt;
    }

    /**
     * @param overseaAccountNumber_txt セットする overseaAccountNumber_txt
     */
    public void setOverseaAccountNumber_txt(String overseaAccountNumber_txt) {
        this.overseaAccountNumber_txt = overseaAccountNumber_txt;
    }

    /**
     * @return overseaCode1_txt
     */
    public String getOverseaCode1_txt() {
        return overseaCode1_txt;
    }

    /**
     * @param overseaCode1_txt セットする overseaCode1_txt
     */
    public void setOverseaCode1_txt(String overseaCode1_txt) {
        this.overseaCode1_txt = overseaCode1_txt;
    }

    /**
     * @return overseaCode2_txt
     */
    public String getOverseaCode2_txt() {
        return overseaCode2_txt;
    }

    /**
     * @param overseaCode2_txt セットする overseaCode2_txt
     */
    public void setOverseaCode2_txt(String overseaCode2_txt) {
        this.overseaCode2_txt = overseaCode2_txt;
    }

    /**
     * @return overseaBankName_txt
     */
    public String getOverseaBankName_txt() {
        return overseaBankName_txt;
    }

    /**
     * @param overseaBankName_txt セットする overseaBankName_txt
     */
    public void setOverseaBankName_txt(String overseaBankName_txt) {
        this.overseaBankName_txt = overseaBankName_txt;
    }

    /**
     * @return overseaBranchName_txt
     */
    public String getOverseaBranchName_txt() {
        return overseaBranchName_txt;
    }

    /**
     * @param overseaBranchName_txt セットする overseaBranchName_txt
     */
    public void setOverseaBranchName_txt(String overseaBranchName_txt) {
        this.overseaBranchName_txt = overseaBranchName_txt;
    }

    /**
     * @return overseaBranchAddress1_txt
     */
    public String getOverseaBranchAddress1_txt() {
        return overseaBranchAddress1_txt;
    }

    /**
     * @param overseaBranchAddress1_txt セットする overseaBranchAddress1_txt
     */
    public void setOverseaBranchAddress1_txt(String overseaBranchAddress1_txt) {
        this.overseaBranchAddress1_txt = overseaBranchAddress1_txt;
    }

    /**
     * @return overseaBranchAddress2_txt
     */
    public String getOverseaBranchAddress2_txt() {
        return overseaBranchAddress2_txt;
    }

    /**
     * @param overseaBranchAddress2_txt セットする overseaBranchAddress2_txt
     */
    public void setOverseaBranchAddress2_txt(String overseaBranchAddress2_txt) {
        this.overseaBranchAddress2_txt = overseaBranchAddress2_txt;
    }

    /**
     * @return overseaBranchCountry_txt
     */
    public String getOverseaBranchCountry_txt() {
        return overseaBranchCountry_txt;
    }

    /**
     * @param overseaBranchCountry_txt セットする overseaBranchCountry_txt
     */
    public void setOverseaBranchCountry_txt(String overseaBranchCountry_txt) {
        this.overseaBranchCountry_txt = overseaBranchCountry_txt;
    }

    /**
     * @return oversea_rdl
     */
    public String getOversea_rdl() {
        return oversea_rdl;
    }

    /**
     * @param oversea_rdl セットする oversea_rdl
     */
    public void setOversea_rdl(String oversea_rdl) {
        this.oversea_rdl = oversea_rdl;
    }

    /**
     * @return oversea_rdll
     */
    public Map<String, String> getOversea_rdll() {
        return oversea_rdll;
    }

    /**
     * @param oversea_rdll セットする oversea_rdll
     */
    public void setOversea_rdll(Map<String, String> oversea_rdll) {
        this.oversea_rdll = oversea_rdll;
    }

    /**
     * @return overseaAdd_txt
     */
    public String getOverseaAdd_txt() {
        return overseaAdd_txt;
    }

    /**
     * @param overseaAdd_txt セットする overseaAdd_txt
     */
    public void setOverseaAdd_txt(String overseaAdd_txt) {
        this.overseaAdd_txt = overseaAdd_txt;
    }

    /**
     * @return paypalAccount_txt
     */
    public String getPaypalAccount_txt() {
        return paypalAccount_txt;
    }

    /**
     * @param paypalAccount_txt セットする paypalAccount_txt
     */
    public void setPaypalAccount_txt(String paypalAccount_txt) {
        this.paypalAccount_txt = paypalAccount_txt;
    }

    /**
     * @return paypalAdd_txt
     */
    public String getPaypalAdd_txt() {
        return paypalAdd_txt;
    }

    /**
     * @param paypalAdd_txt セットする paypalAdd_txt
     */
    public void setPaypalAdd_txt(String paypalAdd_txt) {
        this.paypalAdd_txt = paypalAdd_txt;
    }

    /**
     * @return teacherCourseDtoList
     */
    public List<TeacherCourseDto> getTeacherCourseDtoList() {
        if (this.teacherCourseDtoList == null) {
            this.teacherCourseDtoList = new ArrayList<TeacherCourseDto>();
        }
        return teacherCourseDtoList;
    }

    /**
     * @param teacherCourseDtoList セットする teacherCourseDtoList
     */
    public void setTeacherCourseDtoList(List<TeacherCourseDto> teacherCourseDtoList) {
        this.teacherCourseDtoList = teacherCourseDtoList;
    }

    /**
     * @return sellingPoint_txa
     */
    public String getSellingPoint_txa() {
        return sellingPoint_txa;
    }

    /**
     * @param sellingPoint_txa セットする sellingPoint_txa
     */
    public void setSellingPoint_txa(String sellingPoint_txa) {
        this.sellingPoint_txa = sellingPoint_txa;
    }

    /**
     * @return appealPoint_txa
     */
    public String getAppealPoint_txa() {
        return appealPoint_txa;
    }

    /**
     * @param appealPoint_txa セットする appealPoint_txa
     */
    public void setAppealPoint_txa(String appealPoint_txa) {
        this.appealPoint_txa = appealPoint_txa;
    }

    /**
     * @return teacher_fileName
     */
    public String getTeacher_fileName() {
        return teacher_fileName;
    }

    /**
     * @param teacher_fileName セットする teacher_fileName
     */
    public void setTeacher_fileName(String teacher_fileName) {
        this.teacher_fileName = teacher_fileName;
    }

    /**
     * @return teacher_filFileName
     */
    public String getTeacher_filFileName() {
        return teacher_filFileName;
    }

    /**
     * @param teacher_filFileName セットする teacher_filFileName
     */
    public void setTeacher_filFileName(String teacher_filFileName) {
        this.teacher_filFileName = teacher_filFileName;
    }

    /**
     * @return imgPhoto_chkn
     */
    public String getImgPhoto_chkn() {
        return imgPhoto_chkn;
    }

    /**
     * @param imgPhoto_chkn セットする imgPhoto_chkn
     */
    public void setImgPhoto_chkn(String imgPhoto_chkn) {
        this.imgPhoto_chkn = imgPhoto_chkn;
    }

    /**
     * @return teacher_fil
     */
    public File getTeacher_fil() {
        return teacher_fil;
    }

    /**
     * @param teacher_fil セットする teacher_fil
     */
    public void setTeacher_fil(File teacher_fil) {
        this.teacher_fil = teacher_fil;
    }

    /**
     * @return schoolEvaluationOpen_txa
     */
    public String getSchoolEvaluationOpen_txa() {
        return schoolEvaluationOpen_txa;
    }

    /**
     * @param schoolEvaluationOpen_txa セットする schoolEvaluationOpen_txa
     */
    public void setSchoolEvaluationOpen_txa(String schoolEvaluationOpen_txa) {
        this.schoolEvaluationOpen_txa = schoolEvaluationOpen_txa;
    }

    /**
     * @return schoolEvaluationClose_txa
     */
    public String getSchoolEvaluationClose_txa() {
        return schoolEvaluationClose_txa;
    }

    /**
     * @param schoolEvaluationClose_txa セットする schoolEvaluationClose_txa
     */
    public void setSchoolEvaluationClose_txa(String schoolEvaluationClose_txa) {
        this.schoolEvaluationClose_txa = schoolEvaluationClose_txa;
    }

    /**
     * @return studentEvaluation_txa
     */
    public String getStudentEvaluation_txa() {
        return studentEvaluation_txa;
    }

    /**
     * @param studentEvaluation_txa セットする studentEvaluation_txa
     */
    public void setStudentEvaluation_txa(String studentEvaluation_txa) {
        this.studentEvaluation_txa = studentEvaluation_txa;
    }

    /**
     * @return remarks_txa
     */
    public String getRemarks_txa() {
        return remarks_txa;
    }

    /**
     * @param remarks_txa セットする remarks_txa
     */
    public void setRemarks_txa(String remarks_txa) {
        this.remarks_txa = remarks_txa;
    }

    /**
     * @return recordVerNoU
     */
    public String getRecordVerNoU() {
        return recordVerNoU;
    }

    /**
     * @param recordVerNoU セットする recordVerNoU
     */
    public void setRecordVerNoU(String recordVerNoU) {
        this.recordVerNoU = recordVerNoU;
    }

    /**
     * @return recordVerNoT
     */
    public String getRecordVerNoT() {
        return recordVerNoT;
    }

    /**
     * @param recordVerNoT セットする recordVerNoT
     */
    public void setRecordVerNoT(String recordVerNoT) {
        this.recordVerNoT = recordVerNoT;
    }

    /**
     * @return useStartDt
     */
    public String getUseStartDt() {
        return useStartDt;
    }

    /**
     * @param useStartDt セットする useStartDt
     */
    public void setUseStartDt(String useStartDt) {
        this.useStartDt = useStartDt;
    }

    /**
     * @return useEndDt
     */
    public String getUseEndDt() {
        return useEndDt;
    }

    /**
     * @param useEndDt セットする useEndDt
     */
    public void setUseEndDt(String useEndDt) {
        this.useEndDt = useEndDt;
    }

    /**
     * @return error_contractStart_empty
     */
    public String getError_contractStart_empty() {
        return error_contractStart_empty;
    }

    /**
     * @param error_contractStart_empty セットする error_contractStart_empty
     */
    public void setError_contractStart_empty(String error_contractStart_empty) {
        this.error_contractStart_empty = error_contractStart_empty;
    }

    /**
     * @return error_contractEnd_empty
     */
    public String getError_contractEnd_empty() {
        return error_contractEnd_empty;
    }

    /**
     * @param error_contractEnd_empty セットする error_contractEnd_empty
     */
    public void setError_contractEnd_empty(String error_contractEnd_empty) {
        this.error_contractEnd_empty = error_contractEnd_empty;
    }

    /**
     * @return error_contract_notDate
     */
    public String getError_contract_notDate() {
        return error_contract_notDate;
    }

    /**
     * @param error_contract_notDate セットする error_contract_notDate
     */
    public void setError_contract_notDate(String error_contract_notDate) {
        this.error_contract_notDate = error_contract_notDate;
    }

    /**
     * @return error_contractStart_notDate
     */
    public String getError_contractStart_notDate() {
        return error_contractStart_notDate;
    }

    /**
     * @param error_contractStart_notDate セットする error_contractStart_notDate
     */
    public void setError_contractStart_notDate(String error_contractStart_notDate) {
        this.error_contractStart_notDate = error_contractStart_notDate;
    }

    /**
     * @return error_contractEnd_notDate
     */
    public String getError_contractEnd_notDate() {
        return error_contractEnd_notDate;
    }

    /**
     * @param error_contractEnd_notDate セットする error_contractEnd_notDate
     */
    public void setError_contractEnd_notDate(String error_contractEnd_notDate) {
        this.error_contractEnd_notDate = error_contractEnd_notDate;
    }

    /**
     * @return err_integrity_dt
     */
    public String getErr_integrity_dt() {
        return err_integrity_dt;
    }

    /**
     * @param err_integrity_dt セットする err_integrity_dt
     */
    public void setErr_integrity_dt(String err_integrity_dt) {
        this.err_integrity_dt = err_integrity_dt;
    }

    /**
     * @return err_integrity_date
     */
    public String getErr_integrity_date() {
        return err_integrity_date;
    }

    /**
     * @param err_integrity_date セットする err_integrity_date
     */
    public void setErr_integrity_date(String err_integrity_date) {
        this.err_integrity_date = err_integrity_date;
    }

    /**
     * @return model
     */
    public TeacherMstModel getModel() {
        return model;
    }

    /**
     * @param model
     *            セットする model
     */
    public void setModel(TeacherMstModel model) {
        this.model = model;
    }

}
