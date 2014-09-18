package com.naikara_talk.action;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.service.TeacherMstMoveService;
import com.naikara_talk.sessiondata.SessionUserMstTeacherMst;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>マスタ保守<br>
 * <b>クラス名称       :</b>講師マスタメンテナンス(単票)<br>
 * <b>クラス概要       :</b>講師マスタメンテナンス(単票)講師支払比率の設定Action<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/30 TECS 新規作成
 */
public class TeacherMstMoveAction extends TeacherMstActionSupport {

    private static final long serialVersionUID = 1L;

    /**
     * 講師支払比率の設定ボタンの処理。<br>
     * <br>
     * 講師支払比率の設定ボタンの処理。<br>
     * <br>
     * @param なし<br>
     * @return String <br>
     * @exception NaiException
     */
    public String requestService() throws NaiException {

        // 開始ログ
        log.info(NaikaraStringUtil.unionProcesslog("Start"));

        // Modelクラス設定
        setupModel();
        // Serviceクラス生成
        TeacherMstMoveService service = new TeacherMstMoveService();

        int chkResult = service.nextPageRequest(this.model);

        try {
            switch (chkResult) {
            case TeacherMstMoveService.ERR_INTEGRITY_DT:
                this.addActionMessage(getMessage("EN0011", new String[] { "契約日", "契約期間：開始日" }));
                return SUCCESS;
            case TeacherMstMoveService.ERR_INTEGRITY_DATE:
                this.addActionMessage(getMessage("EN0011", new String[] { "契約期間：開始日", "契約期間：終了日" }));
                return SUCCESS;
            }
            // 戻る用に必要な情報を取得/格納
            this.actionToSession();
        } catch (Exception e) {
            throw new NaiException(e);
        }

        // ヘッダの戻るリンク用
        setCurrentActionName(NaikaraTalkConstants.TEACHER_MST_LOAD);

        // 詳細画面遷移
        return NEXTPAGE;
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
        sessionData.setReturnOnFlg(true); // 戻る判定Onフラグ

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
        sessionData.setContractDt(NaikaraStringUtil.converToYYYYMMDD(this.contract_txt));
        // 契約開始日
        sessionData.setContractStartDt(NaikaraStringUtil.converToYYYYMMDD(this.contractStart_txt));
        // 契約終了日
        sessionData.setContractEndDt(NaikaraStringUtil.converToYYYYMMDD(this.contractEnd_txt));
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
        // レコードバージョン番号
        String recVerNoU = StringUtils.isEmpty(this.recordVerNoU) ? "0" : this.recordVerNoU;
        sessionData.setRecordVerNoU(Integer.parseInt(recVerNoU));
        // 利用開始日
        sessionData.setUseStartDt(this.useStartDt);
        // 利用終了日
        sessionData.setUseEndDt(this.useEndDt);

        // レコードバージョン番号
        String recVerNoT = StringUtils.isEmpty(this.recordVerNoT) ? "0" : this.recordVerNoT;
        sessionData.setRecordVerNoT(Integer.parseInt(recVerNoT));
        // 処理区分(前画面の引き継ぎ情報)
        sessionData.setProcessKbn_rdl(this.processKbn_rdl);
        // 講師支払比率
        sessionData.setResultList(this.teacherRateMstDtoList);
        // 講師別コースマスタ(+コースマスタ)
        sessionData.setTeacherCourseDtoList(this.teacherCourseDtoList);

        SessionDataUtil.setSessionData(sessionData);
    }
}
