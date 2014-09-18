package com.naikara_talk.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dbutil.DbUtil;
import com.naikara_talk.dto.PaymentTrnDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.logic.PaymentManagementListDirectionsLogic;
import com.naikara_talk.model.PaymentManagementListDirectionsModel;
import com.naikara_talk.sessiondata.SessionCsvFileDelete;
import com.naikara_talk.sessiondata.SessionUser;
import com.naikara_talk.util.CsvUtil;
import com.naikara_talk.util.DateUtil;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>会社側_管理帳票<br>
 * <b>クラス名称       :</b>講師毎の支払管理表Serviceクラス<br>
 * <b>クラス概要       :</b>講師毎の支払管理表CSV作成Service<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/08 TECS 新規作成
 */
public class PaymentManagementListDirectionsCsvCreateService implements ActionService {

    /** 一覧 ZERO件 */
    private static final int LIST_ZERO_CNT = 0;

    /** 検索前チェック：データ件数ZERO件 */
    public static final int ERR_ZERO_DATA = 1;

    /** CSV Title */
    private final String title = "支払年月";
    /** ファイル名 */
    private final String fileNamePart = "講師毎の支払管理表";
    /** 下線 */
    private final String underline = "_";
    /** .csv */
    private final String csvEnding = ".csv";

    private final String header1 = "発生年月";
    private final String header2 = "支払対象区分名";
    private final String header3 = "講師ID";
    private final String header4 = "講師名";
    private final String header5 = "講師名(フリガナ)";
    private final String header6 = "ローマ字名";
    private final String header7 = "ニックネーム";
    private final String header8 = "レッスン回数(回)";
    private final String header9 = "バッチ計算支払予定額";
    private final String header10 = "源泉額(円)";
    private final String header11 = "調整額(円)";
    private final String header12 = "支払予定額：調整後金額(円)";
    private final String header13 = "電話番号(自宅/携帯)";
    private final String header14 = "電話番号(緊急連絡先)";
    private final String header15 = "住所";
    private final String header16 = "住所（上記住所欄が一杯のとき）";
    private final String header17 = "国籍";
    private final String header18 = "母国語";
    private final String header19 = "メールアドレス1";
    private final String header20 = "メールアドレス2";
    private final String header21 = "メールアドレス3";
    private final String header22 = "滞在国";
    private final String header23 = "時差地域No.";
    private final String header24 = "勤務都市名";
    private final String header25 = "契約日";
    private final String header26 = "契約開始日";
    private final String header27 = "契約終了日";
    private final String header28 = "◆国内銀行向け送金の場合";
    private final String header29 = "銀行名";
    private final String header30 = "支店名";
    private final String header31 = "預金種別";
    private final String header32 = "口座名義人（フリガナ）";
    private final String header33 = "口座名義人";
    private final String header34 = "口座番号";
    private final String header35 = "追加情報";
    private final String header36 = "◆国内ゆうちょ銀行向け送金の場合";
    private final String header37 = "店番";
    private final String header38 = "預金種別";
    private final String header39 = "口座名義人（フリガナ）";
    private final String header40 = "口座名義人";
    private final String header41 = "口座番号";
    private final String header42 = "追加情報";
    private final String header43 = "◆海外送金の場合";
    private final String header44 = "口座名義人";
    private final String header45 = "口座名義人登録住所";
    private final String header46 = "口座名義人登録住所（上記住所欄が一杯のとき）";
    private final String header47 = "口座番号/IBAN（ヨーロッパ）";
    private final String header48 = "ABAナンバー/SWIFTコード/BIC Code　等<1>";
    private final String header49 = "ABAナンバー/SWIFTコード/BIC Code　等<2>";
    private final String header50 = "銀行名";
    private final String header51 = "支店名";
    private final String header52 = "支店住所";
    private final String header53 = "支店住所（上記住所欄が一杯のとき）";
    private final String header54 = "支店が所在する国名";
    private final String header55 = "その他の送金手数料";
    private final String header56 = "追加情報";
    private final String header57 = "◆海外ペイパル送金の場合";
    private final String header58 = "PayPalアドレス";
    private final String header59 = "追加情報";
    private final String header60 = "講師マスタの備考(講師は見えません)";

    /**
     * 検索前チェック処理<br>
     * <br>
     * 検索前チェック処理<br>
     * <br>
     * @param PaymentManagementListDirectionsModel<br>
     * @return int チェック結果<br>
     * @exception NaiException
     */
    public int checkPreCreate(PaymentManagementListDirectionsModel model) throws NaiException {
        // 入力チェック - DBアクセスありチェック
        // 共通部品：支払集計テーブルのデータ件数取得処理
        int count = getRowCount(model);
        if (count == LIST_ZERO_CNT) {
            return ERR_ZERO_DATA;
        }

        // 正常
        return PaymentManagementListDirectionsModel.CHECK_OK;

    }

    /**
     * 検索データ件数取得。<br>
     * <br>
     * 検索データ件数取得。<br>
     * <br>
     * @param PaymentManagementListDirectionsModel 画面のパラメータ<br>
     * @return int 検索データ件数<br>
     * @exception NaiException
     */
    private int getRowCount(PaymentManagementListDirectionsModel model) throws NaiException {

        Connection conn = null;
        try {
            conn = DbUtil.getConnection();
            // 初期化処理
            PaymentManagementListDirectionsLogic paymentManagementListDirectionsLogic = new PaymentManagementListDirectionsLogic(
                    conn);

            // DTOの初期化
            PaymentTrnDto dto = new PaymentTrnDto();

            // Model値をDTOにセット
            dto.setPaymentPlanYyyyMm(model.getPayment_txt());

            // データの取得件数＆リターン
            return paymentManagementListDirectionsLogic.getRowCount(dto);
        } catch (SQLException e) {
            throw new NaiException(e);
        } catch (NaiException e) {
            throw new NaiException(e);
        } finally {
            try {
                conn.close();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * データの取得。<br>
     * <br>
     * 支払集計テーブル、利用者マスタ、講師マスタのデータを取得する。<br>
     * <br>
     * @param PaymentManagementListDirectionsModel 画面のパラメータ<br>
     * @return List<PaymentTrnDto><br>
     * @exception NaiException
     */
    public List<PaymentTrnDto> selectList(PaymentManagementListDirectionsModel model) throws NaiException {

        Connection conn = null;
        try {
            conn = DbUtil.getConnection();
            // 初期化処理
            PaymentManagementListDirectionsLogic paymentManagementListDirectionsLogic = new PaymentManagementListDirectionsLogic(
                    conn);

            // DTOの初期化
            PaymentTrnDto dto = new PaymentTrnDto();

            // Model値をDTOにセット
            dto.setPaymentPlanYyyyMm(model.getPayment_txt());

            // データの取得＆リターン
            return paymentManagementListDirectionsLogic.select(dto);
        } catch (SQLException e) {
            throw new NaiException(e);
        } catch (NaiException e) {
            throw new NaiException(e);
        } finally {
            try {
                conn.close();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * コード管理マスタからデータ取得処理。<br>
     * <br>
     * コード管理マスタからデータ取得処理。<br>
     * <br>
     * @param category  汎用コード<br>
     * @return LinkedHashMap<String, String><br>
     * @exception NaiException
     */
    public LinkedHashMap<String, String> selectCodeMst(String category) throws NaiException {

        Connection conn = null;
        try {
            conn = DbUtil.getConnection();

            PaymentManagementListDirectionsLogic paymentManagementListDirectionsLogic = new PaymentManagementListDirectionsLogic(
                    conn);

            // コード管理マスタ検索
            return paymentManagementListDirectionsLogic.selectCodeMst(category);
        } catch (SQLException e) {
            throw new NaiException(e);
        } catch (NaiException e) {
            throw new NaiException(e);
        } finally {
            try {
                conn.close();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * CSV作成処理。<br>
     * <br>
     * CSV作成処理。<br>
     * <br>
     * @param PaymentManagementListDirectionsModel<br>
     * @return PaymentManagementListDirectionsModel<br>
     * @throws NaiException
     * @exception なし
     */
    public PaymentManagementListDirectionsModel csvCreate(PaymentManagementListDirectionsModel model)
            throws NaiException {

        // 支払管理表CSVファイルの格納先（サーバー）
        String csvFilePath;
        csvFilePath = this.selectCodeMst(NaikaraTalkConstants.CODE_CATEGORY_PAYMENT_CSV_OUTPUT).get(
                NaikaraTalkConstants.PAYMENT_CSV_OUTPUT);

        // 表示データの取得
        List<PaymentTrnDto> resultList = this.selectList(model);
        // Title 作成
        List<String> termsList = new ArrayList<String>();
        termsList.add(this.title);
        termsList.add(NaikaraStringUtil.converToYYYY_Year_MM_Month(NaikaraStringUtil.converToYYYY_MM(model
                .getPayment_txt())));
        // CSV ヘッダ部 作成
        List<String> headerList = new ArrayList<String>();
        headerList.add(this.header1);   // 発生年月
        headerList.add(this.header2);   // 支払対象区分名
        headerList.add(this.header3);   // 講師ID
        headerList.add(this.header4);   // 講師名
        headerList.add(this.header5);   // 講師名(フリガナ)
        headerList.add(this.header6);   // ローマ字名
        headerList.add(this.header7);   // ニックネーム
        headerList.add(this.header8);   // レッスン回数(回)
        headerList.add(this.header9);   // バッチ計算支払予定額
        headerList.add(this.header10);  // 源泉額(円)
        headerList.add(this.header11);  // 調整額(円)
        headerList.add(this.header12);  // 支払予定額：調整後金額(円)
        headerList.add(this.header13);  // 電話番号(自宅/携帯)
        headerList.add(this.header14);  // 電話番号(緊急連絡先)
        headerList.add(this.header15);  // 住所
        headerList.add(this.header16);  // 住所（上記住所欄が一杯のとき）
        headerList.add(this.header17);  // 国籍
        headerList.add(this.header18);  // 母国語
        headerList.add(this.header19);  // メールアドレス1
        headerList.add(this.header20);  // メールアドレス2
        headerList.add(this.header21);  // メールアドレス3
        headerList.add(this.header22);  // 滞在国
        headerList.add(this.header23);  // 時差地域No.
        headerList.add(this.header24);  // 勤務都市名
        headerList.add(this.header25);  // 契約日
        headerList.add(this.header26);  // 契約開始日
        headerList.add(this.header27);  // 契約終了日
        headerList.add(this.header28);  // ◆国内銀行向け送金の場合
        headerList.add(this.header29);  // 銀行名
        headerList.add(this.header30);  // 支店名
        headerList.add(this.header31);  // 預金種別
        headerList.add(this.header32);  // 口座名義人（フリガナ）
        headerList.add(this.header33);  // 口座名義人
        headerList.add(this.header34);  // 口座番号
        headerList.add(this.header35);  // 追加情報
        headerList.add(this.header36);  // ◆国内ゆうちょ銀行向け送金の場合
        headerList.add(this.header37);  // 店番
        headerList.add(this.header38);  // 預金種別
        headerList.add(this.header39);  // 口座名義人（フリガナ）
        headerList.add(this.header40);  // 口座名義人
        headerList.add(this.header41);  // 口座番号
        headerList.add(this.header42);  // 追加情報
        headerList.add(this.header43);  // ◆海外送金の場合
        headerList.add(this.header44);  // 口座名義人
        headerList.add(this.header45);  // 口座名義人登録住所
        headerList.add(this.header46);  // 口座名義人登録住所（上記住所欄が一杯のとき）
        headerList.add(this.header47);  // 口座番号/IBAN（ヨーロッパ）
        headerList.add(this.header48);  // ABAナンバー/SWIFTコード/BIC Code　等<1>
        headerList.add(this.header49);  // ABAナンバー/SWIFTコード/BIC Code　等<2>
        headerList.add(this.header50);  // 銀行名
        headerList.add(this.header51);  // 支店名
        headerList.add(this.header52);  // 支店住所
        headerList.add(this.header53);  // 支店住所（上記住所欄が一杯のとき）
        headerList.add(this.header54);  // 支店が所在する国名
        headerList.add(this.header55);  // その他の送金手数料
        headerList.add(this.header56);  // 追加情報
        headerList.add(this.header57);  // ◆海外ペイパル送金の場合
        headerList.add(this.header58);  // PayPalアドレス
        headerList.add(this.header59);  // 追加情報
        headerList.add(this.header60);  // 講師マスタの備考(講師は見えません)

        List<List<String>> list = new ArrayList<List<String>>();
        list.add(termsList);
        list.add(headerList);
        // CSV明細部 作成
        for (PaymentTrnDto paymentTrnDto : resultList) {
            List<String> contentsList = new ArrayList<String>();
            contentsList.add(NaikaraStringUtil.converToYYYY_MM(paymentTrnDto.getDataYyyyMm()));                         // 発生年月
            contentsList.add(this.selectCodeMst(NaikaraTalkConstants.CODE_CATEGORY_PAYMENT_KBN).get(
                    paymentTrnDto.getPaymentNm()));                                                                     // 支払対象区分名
            contentsList.add(paymentTrnDto.getUserId());                                                                // 講師ID
            contentsList.add(NaikaraStringUtil.unionName(paymentTrnDto.getFamilyJnmU(), paymentTrnDto.getFirstJnmU())); // 講師名
            contentsList.add(NaikaraStringUtil.unionName(paymentTrnDto.getFamilyKnm(), paymentTrnDto.getFirstKnm()));   // 講師名(フリガナ)
            contentsList.add(NaikaraStringUtil.unionName(paymentTrnDto.getFamilyRomaji(),
                    paymentTrnDto.getFirstRomaji()));                                                                   // ローマ字名
            contentsList.add(paymentTrnDto.getNickAnm());                                                               // ニックネーム
            contentsList.add(String.valueOf(paymentTrnDto.getLessonNum()));                                             // レッスン回数(回)
            contentsList.add(String.valueOf(paymentTrnDto.getBatPaymentYen()));                                         // バッチ計算支払予定額
            contentsList.add(String.valueOf(paymentTrnDto.getSourceYen()));                                             // 源泉額(円)
            contentsList.add(String.valueOf(paymentTrnDto.getAdjustmentYen()));                                         // 調整額(円)
            contentsList.add(String.valueOf(paymentTrnDto.getEndPaymentYen()));                                         // 支払予定額：調整後金額(円)
            contentsList.add(paymentTrnDto.getTel1());                                                                  // 電話番号(自宅/携帯)
            contentsList.add(paymentTrnDto.getTel2());                                                                  // 電話番号(緊急連絡先)
            contentsList.add(paymentTrnDto.getAddressCity());                                                           // 住所
            contentsList.add(paymentTrnDto.getAddressOthers());                                                         // 住所（上記住所欄が一杯のとき）
            contentsList.add(this.selectCodeMst(NaikaraTalkConstants.CODE_CATEGORY_COUNTRY).get(
                    paymentTrnDto.getNationalityCd()));                                                                 // 国籍
            contentsList.add(this.selectCodeMst(NaikaraTalkConstants.CODE_CATEGORY_NATIVE_LANG).get(
                    paymentTrnDto.getNativeLangCd()));                                                                  // 母国語
            contentsList.add(paymentTrnDto.getMailAddress1());                                                          // メールアドレス1
            contentsList.add(paymentTrnDto.getMailAddress2());                                                          // メールアドレス2
            contentsList.add(paymentTrnDto.getMailAddress3());                                                          // メールアドレス3
            contentsList.add(this.selectCodeMst(NaikaraTalkConstants.CODE_CATEGORY_COUNTRY).get(
                    paymentTrnDto.getCountryCd()));                                                                     // 滞在国
            contentsList.add(this.selectCodeMst(NaikaraTalkConstants.CODE_CATEGORY_AREA_NO).get(
                    paymentTrnDto.getAreaNoCd()));                                                                      // 時差地域No.
            contentsList.add(paymentTrnDto.getCityTown());                                                              // 勤務都市名
            contentsList.add(NaikaraStringUtil.converToYYYY_MM_DD(paymentTrnDto.getContractDt()));                      // 契約日
            contentsList.add(NaikaraStringUtil.converToYYYY_MM_DD(paymentTrnDto.getContractStartDt()));                 // 契約開始日
            contentsList.add(NaikaraStringUtil.converToYYYY_MM_DD(paymentTrnDto.getContractEndDt()));                   // 契約終了日
            contentsList.add(NaikaraTalkConstants.BRANK);                                                               // ◆国内銀行向け送金の場合
            contentsList.add(paymentTrnDto.getBankJpnBankNm());                                                         // 銀行名
            contentsList.add(paymentTrnDto.getBankJpnBranchNm());                                                       // 支店名
            contentsList.add(paymentTrnDto.getBankJpnTypeOfAccount());                                                  // 預金種別
            contentsList.add(paymentTrnDto.getBankJpnAccountHoldersKnm());                                              // 口座名義人（フリガナ）
            contentsList.add(paymentTrnDto.getBankJpnAccountHoldersNm());                                               // 口座名義人
            contentsList.add(paymentTrnDto.getBankJpnAccountNumber());                                                  // 口座番号
            contentsList.add(paymentTrnDto.getBankJpnAdditionalInfo());                                                 // 追加情報
            contentsList.add(NaikaraTalkConstants.BRANK);                                                               // ◆国内ゆうちょ銀行向け送金の場合
            contentsList.add(paymentTrnDto.getJpnPbankBranchNm());                                                      // 店番
            contentsList.add(paymentTrnDto.getJpnPbankTypeOfAccount());                                                 // 預金種別
            contentsList.add(paymentTrnDto.getJpnPbankAccountHoldersKnm());                                             // 口座名義人（フリガナ）
            contentsList.add(paymentTrnDto.getJpnPbankAccountHoldersNm());                                              // 口座名義人
            contentsList.add(paymentTrnDto.getJpnPbankAccountNumber());                                                 // 口座番号
            contentsList.add(paymentTrnDto.getJpnPbankAdditionalInfo());                                                // 追加情報
            contentsList.add(NaikaraTalkConstants.BRANK);                                                               // ◆海外送金の場合
            contentsList.add(paymentTrnDto.getOverseaAccountHNm());                                                     // 口座名義人
            contentsList.add(paymentTrnDto.getOverseaAccountHRAddress1());                                              // 口座名義人登録住所
            contentsList.add(paymentTrnDto.getOverseaAccountHRAddress2());                                              // 口座名義人登録住所（上記住所欄が一杯のとき）
            contentsList.add(paymentTrnDto.getOverseaAccountNumberIban());                                              // 口座番号/IBAN（ヨーロッパ）
            contentsList.add(paymentTrnDto.getOverseaAbanoSwiftcdBiccd());                                              // ABAナンバー/SWIFTコード/BICCode　等<1>
            contentsList.add(paymentTrnDto.getOverseaEtc());                                                            // ABAナンバー/SWIFTコード/BICCode　等<2>
            contentsList.add(paymentTrnDto.getOverseaBankNm());                                                         // 銀行名
            contentsList.add(paymentTrnDto.getOverseaBranchNm());                                                       // 支店名
            contentsList.add(paymentTrnDto.getOverseaBranchAddress1());                                                 // 支店住所
            contentsList.add(paymentTrnDto.getOverseaBranchAddress2());                                                 // 支店住所（上記住所欄が一杯のとき）
            contentsList.add(paymentTrnDto.getOverseaCountryBranchExists());                                            // 支店が所在する国名
            if (!StringUtils.isEmpty(paymentTrnDto.getOtherRemittanceFeeKbn())) {
                contentsList.add(this.selectCodeMst(NaikaraTalkConstants.CODE_CATEGORY_OTHER_REMITTANCE_FEE_KBN).get(
                        paymentTrnDto.getOtherRemittanceFeeKbn()));                                                     // その他の送金手数料
            }
            contentsList.add(paymentTrnDto.getOverseaAdditionalInfo());                                                 // 追加情報
            contentsList.add(NaikaraTalkConstants.BRANK);                                                               // ◆海外ペイパル送金の場合
            contentsList.add(paymentTrnDto.getOverseaPlPaypalAddress());                                                // PayPalアドレス
            contentsList.add(paymentTrnDto.getOverseaPlAdditionalInfo());                                               // 追加情報
            contentsList.add(paymentTrnDto.getRemarkT());                                                               // 講師マスタの備考(講師は見えません)

            list.add(contentsList);
        }

        // ファイル名
        StringBuffer csvName = new StringBuffer();
        csvName.append(fileNamePart).append(underline)
                .append(((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString())).getUserId())
                .append(underline).append(DateUtil.getSysDateTimeNoSplit()).append(csvEnding);

        StringBuffer csvPath = new StringBuffer();
        csvPath.append(csvFilePath.replace("\\", NaikaraTalkConstants.DATE_SEPARATOR))
                .append(NaikaraTalkConstants.DATE_SEPARATOR).append(csvName.toString());
        model.setFileName(csvName.toString());

        // CSV作成
        CsvUtil.createCsvFile(list, csvPath.toString());

        model.setCerateFlg(NaikaraTalkConstants.TRUE);
        this.createSessionData();
        return model;
    }

    /**
     * SessionCsvFileDelete作成(yyyy/MM)。<br>
     * <br>
     * SessionCsvFileDelete作成(yyyy/MM)。<br>
     * <br>
     * @param なし <br>
     * @return なし <br>
     * @exception なし
     */
    private void createSessionData() {
        SessionCsvFileDelete sessionData = new SessionCsvFileDelete();
        sessionData.setUserId(((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString())).getUserId());
        List<String> cdCategory = new ArrayList<String>();
        List<String> managerCd = new ArrayList<String>();
        cdCategory.add(NaikaraTalkConstants.CODE_CATEGORY_PAYMENT_CSV_OUTPUT);
        managerCd.add(NaikaraTalkConstants.PAYMENT_CSV_OUTPUT);
        sessionData.setCdCategory(cdCategory);
        sessionData.setManagerCd(managerCd);
        SessionDataUtil.setSessionData(sessionData);

    }
}
