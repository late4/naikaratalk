package com.naikara_talk.service;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.batch.SendMailBatch;
import com.naikara_talk.dbutil.DbUtil;
import com.naikara_talk.dto.AgeCalculateDto;
import com.naikara_talk.dto.CodeManagMstDto;
import com.naikara_talk.dto.PointExpirationDto;
import com.naikara_talk.dto.PointOwnershipTrnDto;
import com.naikara_talk.dto.SmailAccountHistoryTrnDto;
import com.naikara_talk.dto.StudentMstDto;
import com.naikara_talk.dto.SmailAccountHistoryDetailsTrnDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.logic.AccountLogic;
import com.naikara_talk.logic.AgeCalculateLogic;
import com.naikara_talk.logic.OrderNumbersMstLogic;
import com.naikara_talk.logic.PasswordChkLogic;
import com.naikara_talk.logic.PointExpirationLogic;
import com.naikara_talk.logic.SmailAccountHistoryLogic;
import com.naikara_talk.model.AccountModel;
import com.naikara_talk.mstcache.CodeManagMstCache;
import com.naikara_talk.sessiondata.SessionAccount;
import com.naikara_talk.sessiondata.SessionUser;
import com.naikara_talk.util.DateUtil;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>お客様_初期処理。<br>
 * <b>クラス名称　　　:</b>アカウント取得処理登録更新Serviceクラス。<br>
 * <b>クラス概要　　　:</b>アカウント取得処理登録更新Service。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/08/08 TECS 新規作成。
 *                         2013/01/03 TECS スクールのメール送信・受信履歴照会に伴う対応
 */
public class AccountUpdateService implements ActionService {

    /** 住所(都道府県)未選択 */
    public static final int PREFECTURE_NOT_SELECT = 1;

    /** 生年月日月不正 */
    public static final int BIRTH_MM = 2;

    /** 生年月日日不正 */
    public static final int BIRTH_DD = 3;

    /** 生年月日未来日 */
    public static final int BIRTH_YYYYMMDD_FUTURE = 4;

    /** パスワード複雑性 */
    public static final int PASSWORD_CHK = 5;

    /** パスワード異なる */
    public static final int PASSWORD_CONFIRM = 6;

    /** 保護者お名前(姓)未入力 */
    public static final int GUARD_FAMILY_JNM_NOT_INPUT = 7;

    /** 保護者お名前(名)未入力 */
    public static final int GUARD_FIRST_JNM_NOT_INPUT = 8;

    /** 保護者フリガナ(姓)未入力 */
    public static final int GUARD_FAMILY_KNM_NOT_INPUT = 9;

    /** 保護者フリガナ(名)未入力 */
    public static final int GUARD_FIRST_KNM_NOT_INPUT = 10;

    /** あなたとの続柄未入力 */
    public static final int GUARD_RELAT_NOT_INPUT = 11;

    /** 保護者電話番号未入力 */
    public static final int GUARD_TEL1_NOT_INPUT = 12;

    /** 保護者生年月日年未入力 */
    public static final int GUARD_BIRTH_YY_NOT_INPUT = 13;

    /** 保護者生年月日月未入力 */
    public static final int GUARD_BIRTH_MM_NOT_INPUT = 14;

    /** 保護者生年月日日未入力 */
    public static final int GUARD_BIRTH_DD_NOT_INPUT = 15;

    /** 保護者生年月日月不正 */
    public static final int GUARD_BIRTH_MM = 16;

    /** 保護者生年月日日不正 */
    public static final int GUARD_BIRTH_DD = 17;

    /** 保護者郵便番号未入力 */
    public static final int GUARD_ZIP_CD_NOT_INPUT = 18;

    /** 保護者住所(都道府県)未選択 */
    public static final int GUARD_PREFECTURE_NOT_SELECT = 19;

    /** 保護者住所(市区町村 等)未入力 */
    public static final int GUARD_CITY_NOT_INPUT = 20;

    /** 保護者性別未選択 */
    public static final int GUARD_GENDER_NOT_SELECT = 21;

    /** 保護者メールアドレス1未入力 */
    public static final int GUARD_MAIL_ADD1_NOT_INPUT = 22;

    /** メールアドレス重複 */
    public static final int MAIL_ADD_REPEAT = 23;

    /** メールアドレス1DB重複 */
    public static final int MAIL_ADD1_DB_REPEAT = 24;

    /** メールアドレス2DB重複 */
    public static final int MAIL_ADD2_DB_REPEAT = 25;

    /** メールアドレス3DB重複 */
    public static final int MAIL_ADD3_DB_REPEAT = 26;

    /** 受講者所属部署未入力 */
    public static final int STUDENT_POSITION_NOT_INPUT = 27;

    /** ご利用規約に同意する未選択 */
    public static final int USE_AGREE_NOT_SELECT = 28;

    /** 個人情報の同意未選択 */
    public static final int INDIV_AGREE_NOT_SELECT = 29;

    /** 生年月日未来日 */
    public static final int GUARD_BIRTH_YYYYMMDD_FUTURE = 30;

    /** 全てＯＫ */
    public static final int ALL_OK = 0;

    /** ゼロ */
    private static final String ZERO = "0";


    /**
     * 登録、更新のチェック<br>
     * <br>
     * 登録、更新のチェックを行う<br>
     * <br>
     * @param model 画面パラメータ<br>
     * @return int 結果フラグ<br>
     * @exception NaiException
     */
    public int errorCheck(AccountModel model) throws NaiException {

        String userId = NaikaraTalkConstants.BRANK;

        // ユーザIDを取得
        if ((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString()) != null) {

            userId = ((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString())).getUserId();
        }

        // 法人の場合
        if (!StringUtils.isEmpty(userId)
                && StringUtils.equals(model.getCustomerKbn(), NaikaraTalkConstants.CUSTOMER_KBN_ORGANIZATION)) {

            // 受講者所属部署未入力
            if (StringUtils.isEmpty(model.getStudentPosi_txt())) {

                return STUDENT_POSITION_NOT_INPUT;
            }
        }

        // 住所(都道府県)未選択
        if (StringUtils.equals(model.getPrefecture_sel(), NaikaraTalkConstants.CHOICE_ALL_ZERO)) {

            return PREFECTURE_NOT_SELECT;
        }

        StringBuffer sb = new StringBuffer();
        sb.append(model.getBirthYy_txt());
        if (model.getBirthMm_txt().length() == 1) {
            sb.append(ZERO);
        }
        sb.append(model.getBirthMm_txt());
        if (model.getBirthDd_txt().length() == 1) {
            sb.append(ZERO);
        }
        sb.append(model.getBirthDd_txt());

        // 生年月日String(yyyyMMdd)
        String birthStr = NaikaraStringUtil
                .converToYYYYMMDD(NaikaraStringUtil.unionString2(
                        NaikaraStringUtil.unionString2(model.getBirthYy_txt(), model.getBirthMm_txt()),
                        model.getBirthDd_txt()));

        // 生年月日Date(yyyyMMdd)
        Date birthDate;
        try {
            birthDate = new SimpleDateFormat(DateUtil.DATE_FORMAT_yyyyMMdd).parse(birthStr);
        } catch (ParseException e) {
            throw new NaiException(e);
        }

        // 生年月日Calendar(yyyyMMdd)
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(birthDate);

        int year = Integer.parseInt(birthStr.substring(0, 4));
        int month = Integer.parseInt(birthStr.substring(4, 6));
        int date = Integer.parseInt(birthStr.substring(6, 8));

        // 生年月日不正
        if (year != calendar.get(Calendar.YEAR) || month != calendar.get(Calendar.MONTH) + 1
                || date != calendar.get(Calendar.DAY_OF_MONTH)) {

            // 生年月日月不正
            if (month > 12 || month < 1) {

                return BIRTH_MM;
            }

            int temp = Integer.MIN_VALUE;

            boolean flg = (0 == year % 4 && (year % 100 != 0 || year % 400 == 0));

            switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                temp = 31;
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                temp = 30;
                break;
            case 2:
                if (flg) {
                    temp = 29;
                } else {
                    temp = 28;
                }
                break;
            }

            // 生年月日日不正
            if (date > temp || date < 1) {

                return BIRTH_DD;
            }
        }

        // 生年月日未来日
        if (DateUtil.dateCompare2(sb.toString(), DateUtil.getSysDate())) {

            return BIRTH_YYYYMMDD_FUTURE;
        }

        // ロジックの初期化
        PasswordChkLogic passwordLogic = new PasswordChkLogic();

        // パスワード複雑性チェック
        int ret = passwordLogic.passwordCheck(model.getPassword_txt());

        // パスワード複雑性
        if (ret != 0) {

            return PASSWORD_CHK;
        }

        // パスワード異なる
        if (!StringUtils.equals(model.getPassword_txt(), model.getPasswordConf_txt())) {

            return PASSWORD_CONFIRM;
        }

        // ロジックの初期化
        AgeCalculateLogic ageLogic = new AgeCalculateLogic();

        // 年齢の算出処理
        AgeCalculateDto ageDto = ageLogic.ageCalculate(sb.toString(), DateUtil.getSysDate());

        // 未成年の場合
        if (ageDto.getAdult() == AgeCalculateLogic.ADULT_KBN_MINORITY) {

            // 保護者お名前(姓)未入力
            if (StringUtils.isEmpty(model.getGuardFamilyJnm_txt())) {

                return GUARD_FAMILY_JNM_NOT_INPUT;
            }

            // 保護者お名前(名)未入力
            if (StringUtils.isEmpty(model.getGuardFirstJnm_txt())) {

                return GUARD_FIRST_JNM_NOT_INPUT;
            }

            // 保護者フリガナ(姓)未入力
            if (StringUtils.isEmpty(model.getGuardFamilyKnm_txt())) {

                return GUARD_FAMILY_KNM_NOT_INPUT;
            }

            // 保護者フリガナ(名)未入力
            if (StringUtils.isEmpty(model.getGuardFirstKnm_txt())) {

                return GUARD_FIRST_KNM_NOT_INPUT;
            }

            // あなたとの続柄未入力
            if (StringUtils.isEmpty(model.getGuardRelat_txt())) {

                return GUARD_RELAT_NOT_INPUT;
            }

            // 保護者電話番号未入力
            if (StringUtils.isEmpty(model.getGuardTel1_txt())) {

                return GUARD_TEL1_NOT_INPUT;
            }

            // 保護者生年月日年未入力
            if (StringUtils.isEmpty(model.getGuardBirthYy_txt())) {

                return GUARD_BIRTH_YY_NOT_INPUT;
            }

            // 保護者生年月日月未入力
            if (StringUtils.isEmpty(model.getGuardBirthMm_txt())) {

                return GUARD_BIRTH_MM_NOT_INPUT;
            }

            // 保護者生年月日日未入力
            if (StringUtils.isEmpty(model.getGuardBirthDd_txt())) {

                return GUARD_BIRTH_DD_NOT_INPUT;
            }

            StringBuffer sbg = new StringBuffer();
            sbg.append(model.getGuardBirthYy_txt());
            if (model.getGuardBirthMm_txt().length() == 1) {
                sb.append(ZERO);
            }
            sbg.append(model.getGuardBirthMm_txt());
            if (model.getGuardBirthDd_txt().length() == 1) {
                sb.append(ZERO);
            }
            sbg.append(model.getGuardBirthDd_txt());
            // 保護者生年月日String(yyyyMMdd)
            String guardBirthStr = NaikaraStringUtil.converToYYYYMMDD(NaikaraStringUtil.unionString2(
                    NaikaraStringUtil.unionString2(model.getGuardBirthYy_txt(), model.getGuardBirthMm_txt()),
                    model.getGuardBirthDd_txt()));

            // 保護者生年月日Date(yyyyMMdd)
            Date guardBirthDate = new Date();
            try {
                guardBirthDate = new SimpleDateFormat(DateUtil.DATE_FORMAT_yyyyMMdd).parse(guardBirthStr);
            } catch (ParseException e) {
                throw new NaiException(e);
            }

            // 保護者生年月日Calendar(yyyyMMdd)
            Calendar guardCalendar = Calendar.getInstance();
            guardCalendar.setTime(guardBirthDate);

            int guardYear = Integer.parseInt(guardBirthStr.substring(0, 4));
            int guardMonth = Integer.parseInt(guardBirthStr.substring(4, 6));
            int guardDate = Integer.parseInt(guardBirthStr.substring(6, 8));

            // 保護者生年月日不正
            if (guardYear != guardCalendar.get(Calendar.YEAR) || guardMonth != guardCalendar.get(Calendar.MONTH) + 1
                    || guardDate != guardCalendar.get(Calendar.DAY_OF_MONTH)) {

                // 保護者生年月日月不正
                if (guardMonth > 12 || guardMonth < 1) {

                    return GUARD_BIRTH_MM;
                }

                int temp = Integer.MIN_VALUE;

                boolean flg = (0 == guardYear % 4 && (guardYear % 100 != 0 || guardYear % 400 == 0));

                switch (guardMonth) {
                case 1:
                case 3:
                case 5:
                case 7:
                case 8:
                case 10:
                case 12:
                    temp = 31;
                    break;
                case 4:
                case 6:
                case 9:
                case 11:
                    temp = 30;
                    break;
                case 2:
                    if (flg) {
                        temp = 29;
                    } else {
                        temp = 28;
                    }
                    break;
                }

                // 保護者生年月日日不正
                if (guardDate > temp || guardDate < 1) {

                    return GUARD_BIRTH_DD;
                }
            }
            // 保護者生年月日未来日
            if (DateUtil.dateCompare2(sbg.toString(), DateUtil.getSysDate())) {

                return GUARD_BIRTH_YYYYMMDD_FUTURE;
            }

            // 保護者郵便番号未入力
            if (StringUtils.isEmpty(model.getGuardZipCd_txt())) {

                return GUARD_ZIP_CD_NOT_INPUT;
            }

            // 保護者住所(都道府県)未選択
            if (StringUtils.equals(model.getGuardprefecture_sel(), NaikaraTalkConstants.CHOICE_ALL_ZERO)) {

                return GUARD_PREFECTURE_NOT_SELECT;
            }

            // 保護者住所(市区町村 等)未入力
            if (StringUtils.isEmpty(model.getGuardCity_txt())) {

                return GUARD_CITY_NOT_INPUT;
            }

            // 保護者性別未選択
            if (StringUtils.isEmpty(model.getGuardGender_rdl())) {

                return GUARD_GENDER_NOT_SELECT;
            }

            // 保護者メールアドレス1未入力
            if (StringUtils.isEmpty(model.getGuardMailAdd1_txt())) {

                return GUARD_MAIL_ADD1_NOT_INPUT;
            }
        }

        if (!StringUtils.isEmpty(model.getMailAdd2_txt()) && !StringUtils.isEmpty(model.getMailAdd3_txt())) {

            // メールアドレス重複
            if (StringUtils.equals(model.getMailAdd1_txt(), model.getMailAdd2_txt())
                    || StringUtils.equals(model.getMailAdd1_txt(), model.getMailAdd3_txt())
                    || StringUtils.equals(model.getMailAdd2_txt(), model.getMailAdd3_txt())) {

                return MAIL_ADD_REPEAT;
            }
        }

        if (!StringUtils.isEmpty(model.getMailAdd2_txt())) {

            // メールアドレス重複
            if (StringUtils.equals(model.getMailAdd1_txt(), model.getMailAdd2_txt())) {

                return MAIL_ADD_REPEAT;
            }
        }

        if (!StringUtils.isEmpty(model.getMailAdd3_txt())) {

            // メールアドレス重複
            if (StringUtils.equals(model.getMailAdd1_txt(), model.getMailAdd3_txt())) {

                return MAIL_ADD_REPEAT;
            }
        }

        Connection conn = null;
        try {
            conn = DbUtil.getConnection();

            // ロジックの初期化
            AccountLogic accountLogic = new AccountLogic(conn);

            // メール重複チェック処理
            int mailCheck = accountLogic.count(model.getMailAdd1_txt(), DateUtil.getSysDate(), userId);

            // メールアドレス1DB重複
            if (mailCheck == NaikaraTalkConstants.RETURN_CD_DATA_ERR) {

                return MAIL_ADD1_DB_REPEAT;
            }

            if (!StringUtils.isEmpty(model.getMailAdd2_txt())) {

                // メール重複チェック処理
                mailCheck = accountLogic.count(model.getMailAdd2_txt(), DateUtil.getSysDate(), userId);

                // メールアドレス2DB重複
                if (mailCheck == NaikaraTalkConstants.RETURN_CD_DATA_ERR) {

                    return MAIL_ADD2_DB_REPEAT;
                }
            }

            if (!StringUtils.isEmpty(model.getMailAdd3_txt())) {

                // メール重複チェック処理
                mailCheck = accountLogic.count(model.getMailAdd3_txt(), DateUtil.getSysDate(), userId);

                // メールアドレス3DB重複
                if (mailCheck == NaikaraTalkConstants.RETURN_CD_DATA_ERR) {

                    return MAIL_ADD3_DB_REPEAT;
                }
            }

            conn.commit();

            // ご利用規約に同意する未選択
            if (!model.getUseAgreeFlg_chk()) {

                return USE_AGREE_NOT_SELECT;
            }

            // 個人情報の同意未選択
            if (!model.getIndivAgreeFlg_chk()) {

                return INDIV_AGREE_NOT_SELECT;
            }

            return ALL_OK;

        } catch (SQLException se) {
            se.printStackTrace();
            throw new NaiException(se);
        } finally {
            try {
                conn.close();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * 登録処理<br>
     * <br>
     * 登録処理を行う<br>
     * <br>
     * @param model 画面パラメータ<br>
     * @return なし<br>
     * @exception NaiException
     */
    public void insert(AccountModel model) throws NaiException {

        Connection conn = null;
        try {
            conn = DbUtil.getConnection();

            // 採番マスタの自動採番処理
            OrderNumbersMstLogic orderNumbersMstLogic = new OrderNumbersMstLogic();

            // ロジックの初期化
            AccountLogic accountLogic = new AccountLogic(conn);

            StringBuffer sb = new StringBuffer();
            sb.append(model.getBirthYy_txt());
            if (model.getBirthMm_txt().length() == 1) {
                sb.append(ZERO);
            }
            sb.append(model.getBirthMm_txt());
            if (model.getBirthDd_txt().length() == 1) {
                sb.append(ZERO);
            }
            sb.append(model.getBirthDd_txt());

            // ロジックの初期化
            AgeCalculateLogic ageLogic = new AgeCalculateLogic();

            // 年齢の算出処理
            AgeCalculateDto ageDto = ageLogic.ageCalculate(sb.toString(), DateUtil.getSysDate());

            // **************************************************
            // 受講者追加
            // **************************************************

            // Model値をDTOにセット
            StudentMstDto studentDto = this.modelToDtoStudent(new StudentMstDto(), model);

            // 受講者ID
            studentDto.setStudentId(orderNumbersMstLogic.getOrderNumber(NaikaraTalkConstants.ORDER_NUMBERS_CU,
                    DateUtil.getSysDate()).getOrderNumber());

            // 登録者コード
            studentDto.setInsertCd(studentDto.getStudentId());

            // 更新者コード
            studentDto.setUpdateCd(studentDto.getStudentId());

            // 受講者登録を実行
            accountLogic.insertStudent(studentDto);

            // **************************************************
            // ポイント追加
            // **************************************************

            model.setStudentId_lbl(studentDto.getStudentId());

            // Model値をDTOにセット
            PointOwnershipTrnDto pointDto = this.modelToDtoPoint(model);

            // 所有ID
            pointDto.setOwnershipId(orderNumbersMstLogic.getOrderNumber(NaikaraTalkConstants.ORDER_NUMBERS_OW,
                    DateUtil.getSysDate()).getOrderNumber());

            // 登録者コード
            pointDto.setInsertCd(studentDto.getStudentId());

            // 更新者コード
            pointDto.setUpdateCd(studentDto.getStudentId());

            // ポイント登録を実行
            accountLogic.insertPoint(pointDto);

            // コネクションをコミット
            conn.commit();

            Connection connMail = null;
            try {
                connMail = DbUtil.getConnection();

                // **************************************************
                // メール送信
                // **************************************************

                SendMailBatch sendMailBatch = new SendMailBatch(connMail);

                // メールパターンコード
                String mailPatternCd = NaikaraTalkConstants.MAIL_PATTERN_CODE_AGC;
                // 送信者
                String sendFrom = NaikaraTalkConstants.BRANK;
                // 送信者名
                String sendFromNm = NaikaraTalkConstants.BRANK;
                // 宛先IDリスト
                List<String> sendIdList = new ArrayList<String>();
                sendIdList.add(studentDto.getStudentId());
                // 宛先アドレスリスト
                List<String> sendToList = new ArrayList<String>();
                sendToList.add(studentDto.getMailAddress1());
                // CC
                String cc = NaikaraTalkConstants.BRANK;
                // 未成年の場合
                if (ageDto.getAdult() == AgeCalculateLogic.ADULT_KBN_MINORITY) {
                    cc = studentDto.getGuardianMailAddress1();
                }
                // BCC
                String bcc = NaikaraTalkConstants.BRANK;
                // 件名リスト
                List<String> subjectTitleList = new ArrayList<String>();
                // 本文リスト
                List<List<String>> mailTextList = new ArrayList<List<String>>();
                List<String> textList0 = new ArrayList<String>();
                textList0.add(NaikaraStringUtil.unionName(studentDto.getFamilyJnm(), studentDto.getFirstJnm()));
                mailTextList.add(textList0);
                List<String> textList1_6 = new ArrayList<String>();
                textList1_6.add(NaikaraTalkConstants.BRANK);
                mailTextList.add(textList1_6);
                mailTextList.add(textList1_6);
                mailTextList.add(textList1_6);
                mailTextList.add(textList1_6);
                mailTextList.add(textList1_6);
                mailTextList.add(textList1_6);
                List<String> textList7 = new ArrayList<String>();
                textList7.add(studentDto.getStudentId());
                mailTextList.add(textList7);
                List<String> textList8 = new ArrayList<String>();
                textList8.add(studentDto.getPassword());
                mailTextList.add(textList8);
                List<String> textList9_11 = new ArrayList<String>();
                textList9_11.add(NaikaraTalkConstants.BRANK);
                mailTextList.add(textList9_11);
                mailTextList.add(textList9_11);
                mailTextList.add(textList9_11);
                List<String> textList12 = new ArrayList<String>();
                textList12.add(NaikaraStringUtil.addZenkakuComma(String.valueOf(pointDto.getBalancePoint())));
                mailTextList.add(textList12);
                List<String> textList13_34 = new ArrayList<String>();
                textList13_34.add(NaikaraTalkConstants.BRANK);
                mailTextList.add(textList13_34);
                mailTextList.add(textList13_34);
                mailTextList.add(textList13_34);
                mailTextList.add(textList13_34);
                mailTextList.add(textList13_34);
                mailTextList.add(textList13_34);
                mailTextList.add(textList13_34);
                mailTextList.add(textList13_34);
                mailTextList.add(textList13_34);
                mailTextList.add(textList13_34);
                mailTextList.add(textList13_34);
                mailTextList.add(textList13_34);
                mailTextList.add(textList13_34);
                mailTextList.add(textList13_34);
                mailTextList.add(textList13_34);
                mailTextList.add(textList13_34);
                mailTextList.add(textList13_34);
                mailTextList.add(textList13_34);
                mailTextList.add(textList13_34);
                mailTextList.add(textList13_34);
                mailTextList.add(textList13_34);
                mailTextList.add(textList13_34);
                // 添付
                String file = NaikaraTalkConstants.BRANK;
                // 予約No
                String reservationNo = NaikaraTalkConstants.BRANK;

                try {

                    sendMailBatch.sendMail(mailPatternCd, sendFrom, sendFromNm, sendIdList, sendToList, cc, bcc,
                            subjectTitleList, mailTextList, file, reservationNo);

                } catch (Exception se) {

                    connMail.rollback();

                    throw new NaiException(se);

                }

                // 未成年の場合
                if (ageDto.getAdult() == AgeCalculateLogic.ADULT_KBN_MINORITY) {

                    CodeManagMstCache cache = CodeManagMstCache.getInstance();

                    LinkedHashMap<String, CodeManagMstDto> addressList = cache
                            .getList(NaikaraTalkConstants.CODE_CATEGORY_STATE);

                    // メールパターンコード
                    mailPatternCd = NaikaraTalkConstants.MAIL_PATTERN_CODE_AGS;
                    // 送信者
                    sendFrom = NaikaraTalkConstants.BRANK;
                    // 送信者名
                    sendFromNm = NaikaraTalkConstants.BRANK;
                    // 宛先IDリスト
                    sendIdList = new ArrayList<String>();
                    sendIdList.add(NaikaraTalkConstants.BRANK);
                    // 宛先アドレスリスト
                    sendToList = new ArrayList<String>();
                    sendToList.add(NaikaraTalkConstants.BRANK);
                    // CC
                    cc = NaikaraTalkConstants.BRANK;
                    // BCC
                    bcc = NaikaraTalkConstants.BRANK;
                    // 件名リスト
                    subjectTitleList = new ArrayList<String>();
                    // 本文リスト
                    mailTextList = new ArrayList<List<String>>();
                    List<String> textList0_2 = new ArrayList<String>();
                    textList0_2.add(NaikaraTalkConstants.BRANK);
                    mailTextList.add(textList0_2);
                    mailTextList.add(textList0_2);
                    mailTextList.add(textList0_2);
                    List<String> textList3 = new ArrayList<String>();
                    textList3.add(studentDto.getStudentId());
                    mailTextList.add(textList3);
                    List<String> textList4 = new ArrayList<String>();
                    textList4.add(NaikaraStringUtil.unionName(studentDto.getFamilyJnm(), studentDto.getFirstJnm()));
                    mailTextList.add(textList4);
                    List<String> textList5 = new ArrayList<String>();
                    textList5.add(NaikaraStringUtil.converToYYYY_MM_DD(sb.toString()));
                    mailTextList.add(textList5);
                    List<String> textList6 = new ArrayList<String>();
                    textList6.add(NaikaraTalkConstants.BRANK);
                    mailTextList.add(textList6);
                    textList7 = new ArrayList<String>();
                    textList7.add(NaikaraStringUtil.unionName(studentDto.getGuardianFamilyJnm(),
                            studentDto.getGuardianFirstJnm()));
                    mailTextList.add(textList7);
                    textList8 = new ArrayList<String>();
                    textList8.add(studentDto.getGuardianTel1());
                    mailTextList.add(textList8);
                    List<String> textList9 = new ArrayList<String>();
                    textList9.add(studentDto.getGuardianZipCd());
                    mailTextList.add(textList9);
                    List<String> textList10 = new ArrayList<String>();
                    textList10.add(NaikaraStringUtil.unionName(
                            addressList.get(studentDto.getGuardianAddressPrefectureCd()).getManagerNm(),
                            studentDto.getGuardianAddressCity()));
                    mailTextList.add(textList10);
                    List<String> textList11 = new ArrayList<String>();
                    textList11.add(studentDto.getGuardianAddressOthers());
                    mailTextList.add(textList11);
                    textList12 = new ArrayList<String>();
                    textList12.add(studentDto.getGuardianMailAddress1());
                    mailTextList.add(textList12);
                    // 添付
                    file = NaikaraTalkConstants.BRANK;
                    // 予約No
                    reservationNo = NaikaraTalkConstants.BRANK;

                    try {

                        // 未成年の場合
                        sendMailBatch.sendMail(mailPatternCd, sendFrom, sendFromNm, sendIdList, sendToList, cc, bcc,
                                subjectTitleList, mailTextList, file, reservationNo);

                        // 2014/01/03-スクールのメール送信・受信履歴照会に伴う対応-Start
                        // 共通：スクールのメール送信・受信履歴データ作成Logicクラスの生成
                        SmailAccountHistoryLogic sAMHiLogic = new SmailAccountHistoryLogic(connMail);

                        // スクールメール・アカウント変更履歴テーブルの更新処理
                        sAMHiLogic.smailActHistoryInsertMain(NaikaraTalkConstants.MAIL_PATTERN_ACCOUNT_MINOR,
                        		studentDto.getStudentId(), studentDto.getStudentId());
                        // 2014/01/03-スクールのメール送信・受信履歴照会に伴う対応-End

                        connMail.commit();

                    } catch (Exception se) {

                        connMail.rollback();

                        throw new NaiException(se);

                    }
                }

            } catch (SQLException se) {
                connMail.rollback();
                throw new NaiException(se);
            } finally {
                try {
                    connMail.close();
                } catch (Exception e) {
                    throw new NaiException(e);
                }
            }
        } catch (SQLException se) {
            try {
                conn.rollback();
            } catch (SQLException e) {
                throw new NaiException(e);
            }
            throw new NaiException(se);
        } finally {
            try {
                conn.close();
            } catch (Exception e) {
                throw new NaiException(e);
            }
        }
    }

    /**
     * 更新処理<br>
     * <br>
     * 更新処理を行う<br>
     * <br>
     * @param model 画面パラメータ<br>
     * @return cnt 更新結果<br>
     * @exception NaiException
     */
    public int update(AccountModel model) throws NaiException {

        int cnt = 0;

        Connection conn = null;
        try {
            // コネクションを取得
            conn = DbUtil.getConnection();

            // ロジックの初期化
            AccountLogic accountLogic = new AccountLogic(conn);

            // DTOの初期化
            StudentMstDto prmDto = new StudentMstDto();

            prmDto.setStudentId(model.getStudentId_lbl());

            StudentMstDto srcDto = accountLogic.select(prmDto);

            // Model値をDTOにセット
            prmDto = this.modelToDtoStudent(srcDto, model);

            prmDto.setPointPurchaseFlg(srcDto.getPointPurchaseFlg());

            prmDto.setUseStopFlg(srcDto.getUseStopFlg());

            prmDto.setUseStrDt(srcDto.getUseStrDt());

            prmDto.setUseEndDt(srcDto.getUseEndDt());

            String userId = NaikaraTalkConstants.BRANK;

            // ユーザIDを取得
            if ((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString()) != null) {
                userId = ((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString())).getUserId();
            }

            // 登録者コード
            prmDto.setInsertCd(userId);

            // 更新者コード
            prmDto.setUpdateCd(userId);

            // 更新を実行
            cnt = accountLogic.update(prmDto);


            // 2014/01/03-スクールのメール送信・受信履歴照会に伴う対応-Start

            // ###############################################################
            // SmailAccountHistoryDetailsTrnDtoクラスの生成
            SmailAccountHistoryDetailsTrnDto prmSAHDDto = new SmailAccountHistoryDetailsTrnDto();

            // 変更発生内容をSmailAccountHistoryDetailsTrnDtoへ格納
            prmSAHDDto = this.modelToDtoAHistoryDetails(userId, model, prmSAHDDto);

            // 変更箇所がある場合は更新処理を行う
            if (prmSAHDDto.getChangeYesFlg() == true) {
                // SmailAccountHistoryTrnDtoクラスの生成
                SmailAccountHistoryTrnDto prmSAHDto = new SmailAccountHistoryTrnDto();

                // 共通：スクールのメール送信・受信履歴データ作成Logicクラスの生成
                SmailAccountHistoryLogic sAMHiLogic = new SmailAccountHistoryLogic(conn);

                // 共通：変更発生内容をSmailAccountHistoryTrnDtoへ格納
                prmSAHDto = sAMHiLogic.prmToDtoAHistory(NaikaraTalkConstants.MAIL_PATTERN_ACCOUNT_UPD,
                		userId, model.getStudentId_lbl(), prmSAHDto);

                // スクールメール・アカウント変更履歴テーブル、スクールメール・アカウント変更履歴明細テーブルの更新処理
                cnt = sAMHiLogic.smailAccountHistoryAndDInsert(prmSAHDDto, prmSAHDto);
            }
            // 2014/01/03-スクールのメール送信・受信履歴照会に伴う対応-End


            // コネクションをコミット
            conn.commit();

        } catch (SQLException se) {
            se.printStackTrace();
            throw new NaiException(se);
        } finally {
            try {
                conn.close();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }

        return cnt;
    }

    /**
     * コード取得<br>
     * <br>
     * コード管理マスタからデータ取得処理<br>
     * <br>
     * @param category 汎用コード<br>
     * @return LinkedHashMap 取得結果<br>
     * @exception NaiException
     */
    public LinkedHashMap<String, String> selectCodeMst(String category) throws NaiException {

        Connection conn = null;
        try {
            conn = DbUtil.getConnection();

            // ロジックの初期化
            AccountLogic accountLogic = new AccountLogic(conn);

            // コード管理マスタを検索
            LinkedHashMap<String, String> codeMap = accountLogic.selectCodeMst(category);

            conn.commit();
            return codeMap;

        } catch (SQLException se) {
            se.printStackTrace();
            throw new NaiException(se);
        } finally {
            try {
                conn.close();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * Model値をセット(受講者)<br>
     * <br>
     * Model値をDTOにセット(受講者)<br>
     * <br>
     * @param model 画面パラメータ<br>
     * @return prmDto 変換後結果<br>
     * @exception NaiException
     */
    private StudentMstDto modelToDtoStudent(StudentMstDto prmDto, AccountModel model) throws NaiException {

        String userId = NaikaraTalkConstants.BRANK;

        // ユーザIDを取得
        if ((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString()) != null) {

            userId = ((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString())).getUserId();
        }

        StringBuffer sb = new StringBuffer();
        sb.append(model.getBirthYy_txt());
        if (model.getBirthMm_txt().length() == 1) {
            sb.append(ZERO);
        }
        sb.append(model.getBirthMm_txt());
        if (model.getBirthDd_txt().length() == 1) {
            sb.append(ZERO);
        }
        sb.append(model.getBirthDd_txt());

        // ロジックの初期化
        AgeCalculateLogic ageLogic = new AgeCalculateLogic();

        // 年齢の算出処理
        AgeCalculateDto ageDto = ageLogic.ageCalculate(sb.toString(), DateUtil.getSysDate());

        // 受講者ID
        prmDto.setStudentId(model.getStudentId_lbl());
        // 受講者所属部署
        prmDto.setStudentPosition(model.getStudentPosi_txt());
        // パスワード
        prmDto.setPassword(model.getPassword_txt());
        // パスワード確認
        prmDto.setPassword(model.getPasswordConf_txt());
        // お名前(姓)
        prmDto.setFamilyJnm(model.getFamilyJnm_txt());
        // お名前(名)
        prmDto.setFirstJnm(model.getFirstJnm_txt());
        // フリガナ(姓)
        prmDto.setFamilyKnm(model.getFamilyKnm_txt());
        // フリガナ(名)
        prmDto.setFirstKnm(model.getFirstKnm_txt());
        // ローマ字(姓)
        prmDto.setFamilyRomaji(model.getFamilyRomaji_txt());
        // ローマ字(名)
        prmDto.setFirstRomaji(model.getFirstRomaji_txt());
        // ニックネーム
        prmDto.setNickNm(model.getNickNm_txt());
        // 電話番号
        prmDto.setTel1(model.getTel1_txt());
        // 携帯電話
        prmDto.setTel2(model.getTel2_txt());
        // 生年月日年
        prmDto.setBirthYyyy(model.getBirthYy_txt());
        // 生年月日月
        prmDto.setBirthMm(model.getBirthMm_txt());
        // 生年月日日
        prmDto.setBirthDd(model.getBirthDd_txt());
        // 郵便番号
        prmDto.setZipCd(model.getZipCd_txt());
        // 住所(地域)
        prmDto.setAddressAreaCd(model.getArea_sel());
        // 住所(都道府県)
        prmDto.setAddressPrefectureCd(model.getPrefecture_sel());
        // 住所(市区町村 等)
        prmDto.setAddressCity(model.getCity_txt());
        // 住所(ビル、マンション名)
        prmDto.setAddressOthers(model.getOther_txt());
        // 性別
        prmDto.setGenderKbn(model.getGender_rdl());
        // メールアドレス1
        prmDto.setMailAddress1(model.getMailAdd1_txt());
        // メールアドレス2
        prmDto.setMailAddress2(model.getMailAdd2_txt());
        // メールアドレス3
        prmDto.setMailAddress3(model.getMailAdd3_txt());
        // ご職業
        prmDto.setOccupationCd(model.getOccupa_sel());
        // ご利用規約に同意する
        prmDto.setUseAgreementFlg(model.getUseAgreeFlg_chk() ? NaikaraTalkConstants.USE_AGREEMENT_FLG_YES
                : NaikaraTalkConstants.USE_AGREEMENT_FLG_NO);
        // ご利用の動機1
        prmDto.setUseMotiveFlg1(model.getMotiveFlg1_chk() ? NaikaraTalkConstants.USE_MOTIVE_FLG_YES
                : NaikaraTalkConstants.USE_MOTIVE_FLG_NO);
        // ご利用の動機2
        prmDto.setUseMotiveFlg2(model.getMotiveFlg2_chk() ? NaikaraTalkConstants.USE_MOTIVE_FLG_YES
                : NaikaraTalkConstants.USE_MOTIVE_FLG_NO);
        // ご利用の動機3
        prmDto.setUseMotiveFlg3(model.getMotiveFlg3_chk() ? NaikaraTalkConstants.USE_MOTIVE_FLG_YES
                : NaikaraTalkConstants.USE_MOTIVE_FLG_NO);


        // ご利用の動機4
//        prmDto.setUseMotiveFlg4(model.getMotiveFlg4_chk() ? NaikaraTalkConstants.USE_MOTIVE_FLG_YES
//                : NaikaraTalkConstants.USE_MOTIVE_FLG_NO);
        prmDto.setUseMotiveFlg4("");


        // ご利用の動機5
        prmDto.setUseMotiveFlg5(model.getMotiveFlg5_chk() ? NaikaraTalkConstants.USE_MOTIVE_FLG_YES
                : NaikaraTalkConstants.USE_MOTIVE_FLG_NO);
        // ご利用の動機備考
        prmDto.setUseMotive(model.getMotive_txt());
        // 達成したい目標があればご記入ください
        prmDto.setAchievement(model.getAchieve_txt());
        // 個人情報の同意
        prmDto.setIndividualAgreementFlg(model.getIndivAgreeFlg_chk() ? NaikaraTalkConstants.INDIVIDUAL_AGREEMENT_FLG_YES
                : NaikaraTalkConstants.INDIVIDUAL_AGREEMENT_FLG_NO);
        // 保護者お名前(姓)
        prmDto.setGuardianFamilyJnm(model.getGuardFamilyJnm_txt());
        // 保護者お名前(名)
        prmDto.setGuardianFirstJnm(model.getGuardFirstJnm_txt());
        // 保護者フリガナ(姓)
        prmDto.setGuardianFamilyKnm(model.getGuardFamilyKnm_txt());
        // 保護者フリガナ(名)
        prmDto.setGuardianFirstKnm(model.getGuardFirstKnm_txt());
        // あなたとの続柄
        prmDto.setGuardianFamilyRelationship(model.getGuardRelat_txt());
        // 保護者電話番号
        prmDto.setGuardianTel1(model.getGuardTel1_txt());
        // 保護者携帯電話
        prmDto.setGuardianTel2(model.getGuardTel2_txt());
        // 保護者生年月日年
        prmDto.setGuardianBirthYyyy(model.getGuardBirthYy_txt());
        // 保護者生年月日月
        prmDto.setGuardianBirthMm(model.getGuardBirthMm_txt());
        // 保護者生年月日日
        prmDto.setGuardianBirthDd(model.getGuardBirthDd_txt());
        // 保護者郵便番号
        prmDto.setGuardianZipCd(model.getGuardZipCd_txt());
        // 保護者住所(地域)
        prmDto.setGuardianAddressAreaCd(model.getGuardArea_sel());
        // 保護者住所(都道府県)
        prmDto.setGuardianAddressPrefectureCd(model.getGuardprefecture_sel());
        // 保護者住所(市区町村 等)
        prmDto.setGuardianAddressCity(model.getGuardCity_txt());
        // 保護者住所(ビル、マンション名 等)
        prmDto.setGuardianAddressOthers(model.getGuardOther_txt());
        // 保護者性別
        prmDto.setGuardianGenderKbn(model.getGuardGender_rdl());
        // 保護者メールアドレス1
        prmDto.setGuardianMailAddress1(model.getGuardMailAdd1_txt());
        // 保護者メールアドレス2
        prmDto.setGuardianMailAddress2(model.getGuardMailAdd2_txt());
        // 保護者メールアドレス3
        prmDto.setGuardianMailAddress3(model.getGuardMailAdd3_txt());
        // 組織名
        prmDto.setOrganizationNm(model.getOrganizationNm());
        // 組織ID
        prmDto.setOrganizationId(model.getOrganizationId());
        // 所属組織内ID
        prmDto.setPositionOrganizationId(model.getPositionOrganizationId());
        // 自己負担率
        prmDto.setBurdenNum(model.getBurdenNum());
        // ログイン回数
        prmDto.setLoginNum(model.getLoginNum());
        // 最終ログイン日
        prmDto.setEndLoginDt(model.getEndLoginDt());
        // 備考(生徒非公開)
        prmDto.setRemark(model.getRemark());
        // ｽｸｰﾙからのｺﾒﾝﾄ(生徒公開)
        prmDto.setSchoolCmt(model.getSchoolCmt());
        // 排他用レコードバージョン
        prmDto.setRecordVerNo(model.getRecordVerNo());

        if (StringUtils.isEmpty(userId)) {

            // 新規の場合

            // 顧客区分
            prmDto.setCustomerKbn(NaikaraTalkConstants.CUSTOMER_KBN_PERSON);
            // ログイン回数
            prmDto.setLoginNum(0);
            // 利用開始日
            prmDto.setUseStrDt(DateUtil.getSysDate());
            // 利用終了日
            prmDto.setUseEndDt(NaikaraStringUtil.delSlash(NaikaraTalkConstants.MAX_END_DT));
            // 利用停止フラグ
            prmDto.setUseStopFlg(NaikaraTalkConstants.USE_KBN_OK);

            // 未成年の場合
            if (ageDto.getAdult() == AgeCalculateLogic.ADULT_KBN_MINORITY) {

                // 保護者の同意書の入手フラグ
                prmDto.setConsentDocumentAcquisitionFlg(NaikaraTalkConstants.PARENTAL_CONSENT_FLG_NO);

            } else {

                // 保護者の同意書の入手フラグ
                prmDto.setConsentDocumentAcquisitionFlg(NaikaraTalkConstants.PARENTAL_CONSENT_FLG_YES);
            }

            // ポイント購入済フラグ
            prmDto.setPointPurchaseFlg(NaikaraTalkConstants.POINT_PURCHASE_FLG_NO);
            // DM不要フラグ
            prmDto.setDmNoNeedFlg(NaikaraTalkConstants.DM_NO_NEED_FLG_YES);
            // その他フラグ１
            prmDto.setOther1Flg(NaikaraTalkConstants.OTHER_FLG_OFF);
            // その他フラグ２
            prmDto.setOther2Flg(NaikaraTalkConstants.OTHER_FLG_OFF);
            // その他フラグ３
            prmDto.setOther3Flg(NaikaraTalkConstants.OTHER_FLG_OFF);
            // その他フラグ４
            prmDto.setOther4Flg(NaikaraTalkConstants.OTHER_FLG_OFF);

        } else {

            // 修正の場合

            // 顧客区分
            prmDto.setCustomerKbn(model.getCustomerKbn());

            // 未成年の場合
            if (ageDto.getAdult() == AgeCalculateLogic.ADULT_KBN_MINORITY) {

                // 保護者の同意書の入手フラグ
                prmDto.setConsentDocumentAcquisitionFlg(NaikaraTalkConstants.PARENTAL_CONSENT_FLG_NO);

            } else {

                // 保護者の同意書の入手フラグ
                prmDto.setConsentDocumentAcquisitionFlg(NaikaraTalkConstants.PARENTAL_CONSENT_FLG_YES);
            }
        }

        return prmDto;
    }

    /**
     * Model値をセット(ポイント)<br>
     * <br>
     * Model値をDTOにセット(ポイント)<br>
     * <br>
     * @param model 画面パラメータ<br>
     * @return prmDto 変換後結果<br>
     * @exception NaiException
     */
    private PointOwnershipTrnDto modelToDtoPoint(AccountModel model) throws NaiException {

        CodeManagMstCache cache = CodeManagMstCache.getInstance();

        LinkedHashMap<String, CodeManagMstDto> dateList = cache
                .getList(NaikaraTalkConstants.CODE_CATEGORY_JOIN_POINT_PERIOD);

        LinkedHashMap<String, CodeManagMstDto> pointList = cache.getList(NaikaraTalkConstants.CODE_CATEGORY_JOIN_POINT);

        int expMonth = Integer.parseInt(dateList.get(NaikaraTalkConstants.JOIN_POINT_PERIOD).getManagerNm());

        PointExpirationLogic logic = new PointExpirationLogic();

        PointExpirationDto dto = logic.pointExpiration(DateUtil.getSysDate(), expMonth);

        // DTOの初期化
        PointOwnershipTrnDto prmDto = new PointOwnershipTrnDto();

        // 有償無償区分
        prmDto.setCompensationFreeKbn(NaikaraTalkConstants.COMPENSATION_FREE_KBN_NO_ENROLLMENT);
        // 受講者ID
        prmDto.setStudentId(model.getStudentId_lbl());
        // 有効開始日
        prmDto.setEffectiveStrDt(DateUtil.getSysDate());
        // 有効終了日
        prmDto.setEffectiveEndDt(NaikaraStringUtil.delSlash(dto.getAge()));
        // 通常月謝区分
        prmDto.setFeeKbn(NaikaraTalkConstants.FEE_KBN_NORMAL);
        // 購入金額(税込)
        prmDto.setPurchaseYen(new BigDecimal(0));
        // 購入／無償ポイント
        prmDto.setPurchaseFreePoint(new BigDecimal(pointList.get(NaikaraTalkConstants.JOIN_POIN).getManagerNm()));
        // 残高ポイント
        prmDto.setBalancePoint(new BigDecimal(pointList.get(NaikaraTalkConstants.JOIN_POIN).getManagerNm()));
        // 購入日
        prmDto.setPurchaseDt(DateUtil.getSysDate());
        // 購入時刻
        prmDto.setPurchaseTm(DateUtil.getSysTime());
        // 月謝停止フラグ
        prmDto.setEndFlg(NaikaraTalkConstants.END_FLG_NO);
        // ポイントコード
        prmDto.setPointCd(NaikaraTalkConstants.POINT_INIT_CD);
        // 画面システム作成区分
        prmDto.setScreenSystemKbn(NaikaraTalkConstants.SCREEN_SYSTEM_KBN_SCREEN);
        // レコードバージョン番号
        prmDto.setRecordVerNo(0);

        return prmDto;
    }

    /**
     * データの存在チェック<br>
     * <br>
     * データの存在チェックを行う<br>
     * <br>
     * @param model 画面パラメータ<br>
     * @return int チェック結果<br>
     * @exception NaiException
     */
    public int isExists(AccountModel model) throws NaiException {

        Connection conn = null;
        try {
            conn = DbUtil.getConnection();

            // ロジックの初期化
            AccountLogic accountLogic = new AccountLogic(conn);

            // Model値をDTOにセット
            StudentMstDto prmDto = new StudentMstDto();
            prmDto.setStudentId(model.getStudentId_lbl());

            // 検索を実行
            int existsFlg = accountLogic.isExists(prmDto);

            conn.commit();
            return existsFlg;

        } catch (SQLException se) {
            se.printStackTrace();
            throw new NaiException(se);
        } finally {
            try {
                conn.close();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    // 2014/01/03-スクールのメール送信・受信履歴照会に伴う対応-Start
    /**
     * 変更箇所のModel値をセット(スクールメール・アカウント変更履歴明細テーブル)<br>
     * <br>
     * 変更箇所のModel値をDTOにセット(スクールメール・アカウント変更履歴明細テーブル)<br>
     * <br>
     * @param userId ログイン者コード<br>
     * @param model 画面パラメータ<br>
     * @param prmDto 更新内容の格納先<br>
     * @return prmDto 変換後結果<br>
     * @exception NaiException
     */
    private SmailAccountHistoryDetailsTrnDto modelToDtoAHistoryDetails(String userId, AccountModel model, SmailAccountHistoryDetailsTrnDto prmDto) throws NaiException {

        // 初期値の設定
        boolean changeYesFlg = false;
        prmDto.setChangeYesFlg(changeYesFlg);


        // 初期表示時に保持した情報の取得
        SessionAccount seAct = (SessionAccount) SessionDataUtil.getSessionData(SessionAccount.class.toString());
        if (seAct == null) {
            return prmDto;
        }

        // 初期表示時の受講者ID ≠ 画面に表示している受講者IDの場合は処理しない
        if (!StringUtils.equals((String)NaikaraStringUtil.nvlString(seAct.getStudentId()),
        		(String)NaikaraStringUtil.nvlString(model.getStudentId_lbl()))) {
            return prmDto;
        }

        // コード管理マスタの名称取得用
        CodeManagMstCache cache = CodeManagMstCache.getInstance();

        // 受講者ID
        prmDto.setStudentId(model.getStudentId_lbl());

        // 名前(姓):必須
        if (!StringUtils.equals(seAct.getFamilyJnm().trim(), model.getFamilyJnm_txt().trim())) {
            prmDto.setBFamilyJnm(seAct.getFamilyJnm());       // 変更前：名前(姓)
            prmDto.setAFamilyJnm(model.getFamilyJnm_txt());   // 変更後：名前(姓)
            changeYesFlg = true;
        }

        // 名前(名):必須
        if (!StringUtils.equals(seAct.getFirstJnm().trim(), model.getFirstJnm_txt().trim())) {
            prmDto.setBFirstJnm(seAct.getFirstJnm());
            prmDto.setAFirstJnm(model.getFirstJnm_txt());
            changeYesFlg = true;
        }

        // フリガナ(姓):必須
        if (!StringUtils.equals(seAct.getFamilyKnm().trim(), model.getFamilyKnm_txt().trim())) {
            prmDto.setBFamilyKnm(seAct.getFamilyKnm());
            prmDto.setAFamilyKnm(model.getFamilyKnm_txt());
            changeYesFlg = true;
        }

        // フリガナ(名):必須
        if (!StringUtils.equals(seAct.getFirstKnm().trim(), model.getFirstKnm_txt().trim())) {
            prmDto.setBFirstKnm(seAct.getFirstKnm());
            prmDto.setAFirstKnm(model.getFirstKnm_txt());
            changeYesFlg = true;
        }

        // ローマ字(姓)
        if (!StringUtils.equals(NaikaraStringUtil.nvlString(seAct.getFamilyRomaji()).toString().trim(),
        		NaikaraStringUtil.nvlString(model.getFamilyRomaji_txt()).toString().trim())) {
            prmDto.setBFamilyRomaji(seAct.getFamilyRomaji());
            prmDto.setAFamilyRomaji(model.getFamilyRomaji_txt());
            changeYesFlg = true;
        }

        // ローマ字(名)
        if (!StringUtils.equals(NaikaraStringUtil.nvlString(seAct.getFirstRomaji()).toString().trim(),
        		NaikaraStringUtil.nvlString(model.getFirstRomaji_txt()).toString().trim())) {
            prmDto.setBFirstRomaji(seAct.getFirstRomaji());
            prmDto.setAFirstRomaji(model.getFirstRomaji_txt());
            changeYesFlg = true;
        }

        // ニックネーム:必須
        if (!StringUtils.equals(seAct.getNickNm().trim(), model.getNickNm_txt().trim())) {
            prmDto.setBNickNm(seAct.getNickNm());
            prmDto.setANickNm(model.getNickNm_txt());
            changeYesFlg = true;
        }

        // パスワード:必須
        if (!StringUtils.equals(seAct.getPassword().trim(), model.getPassword_txt().trim())) {
            prmDto.setBPassword(seAct.getPassword());
            prmDto.setAPassword(model.getPassword_txt());
            changeYesFlg = true;
        }

        // 電話番号1:必須
        if (!StringUtils.equals(seAct.getTel1().trim(), model.getTel1_txt().trim())) {
            prmDto.setBTel1(seAct.getTel1());
            prmDto.setATel1(model.getTel1_txt());
            changeYesFlg = true;
        }

        // 電話番号2(携帯電話)
        if (!StringUtils.equals(NaikaraStringUtil.nvlString(seAct.getTel2()).toString().trim(),
        		NaikaraStringUtil.nvlString(model.getTel2_txt()).toString().trim())) {
            prmDto.setBTel2(seAct.getTel2());
            prmDto.setATel2(model.getTel2_txt());
            changeYesFlg = true;
        }

        // 生年月日:必須
        String bBirthYyyy = NaikaraStringUtil.nvlString(seAct.getBirthYyyy()).toString();    // 生年月日:年
        String bBirthMm = NaikaraStringUtil.nvlString(seAct.getBirthMm()).toString();        // 生年月日:月
        String bBirthDd = NaikaraStringUtil.nvlString(seAct.getBirthDd()).toString();        // 生年月日:日
        String bBirth = NaikaraStringUtil.converToYYYYMMDD(NaikaraStringUtil.unionString2(bBirthYyyy,
                NaikaraStringUtil.unionString2(bBirthMm, bBirthDd)));
        String aBirthYyyy = NaikaraStringUtil.nvlString(model.getBirthYy_txt()).toString();  // 生年月日:年
        String aBirthMm = NaikaraStringUtil.nvlString(model.getBirthMm_txt()).toString();    // 生年月日:月
        String aBirthDd = NaikaraStringUtil.nvlString(model.getBirthDd_txt()).toString();    // 生年月日:日
        String aBirth = NaikaraStringUtil.converToYYYYMMDD(NaikaraStringUtil.unionString2(aBirthYyyy,
                NaikaraStringUtil.unionString2(aBirthMm, aBirthDd)));
        if (!StringUtils.equals(bBirth, aBirth)) {
            prmDto.setBBirthDt(NaikaraStringUtil.converToYYYY_MM_DD(bBirth));
            prmDto.setABirthDt(NaikaraStringUtil.converToYYYY_MM_DD(aBirth));
            changeYesFlg = true;
        }

        // 郵便番号:必須
        if (!StringUtils.equals(seAct.getZipCd().trim(), model.getZipCd_txt().trim())) {
            prmDto.setBZipCd(seAct.getZipCd());
            prmDto.setAZipCd(model.getZipCd_txt());
            changeYesFlg = true;
        }

        // 住所(地域)コード・住所(地域)名
        if (!StringUtils.equals(NaikaraStringUtil.nvlString(seAct.getAddressAreaCd()).toString().trim(),
        		NaikaraStringUtil.nvlString(model.getArea_sel()).toString().trim())) {
            if (StringUtils.equals(NaikaraTalkConstants.CHOICE_ALL_ZERO, seAct.getAddressAreaCd())) {
                prmDto.setBAddressAreaCd(NaikaraTalkConstants.BRANK);  // 住所(地域)コード
                prmDto.setBAddressAreaJnm(NaikaraTalkConstants.BRANK); // 住所(地域)名
            } else {
                prmDto.setBAddressAreaCd(seAct.getAddressAreaCd());    // 住所(地域)コード
                prmDto.setBAddressAreaJnm(cache.decode(NaikaraTalkConstants.CODE_CATEGORY_REGION,
                		seAct.getAddressAreaCd()));                    // 住所(地域)名
            }

            if (StringUtils.equals(NaikaraTalkConstants.CHOICE_ALL_ZERO, model.getArea_sel())) {
                prmDto.setAAddressAreaCd(NaikaraTalkConstants.BRANK);  // 住所(地域)コード
                prmDto.setAAddressAreaJnm(NaikaraTalkConstants.BRANK); // 住所(地域)名
            } else {
                prmDto.setAAddressAreaCd(model.getArea_sel());         // 住所(地域)コード
                prmDto.setAAddressAreaJnm(cache.decode(NaikaraTalkConstants.CODE_CATEGORY_REGION,
                		model.getArea_sel()));                         // 住所(地域)名
            }

            changeYesFlg = true;
        }

        // 住所(都道府県)コード・住所(都道府県)名:必須
        if (!StringUtils.equals(seAct.getAddressPrefectureCd().trim(), model.getPrefecture_sel().trim())) {
            // 住所(地域)コード
            prmDto.setBAddressPrefectureCd(seAct.getAddressPrefectureCd());
            prmDto.setAAddressPrefectureCd(model.getPrefecture_sel());
            // 住所(都道府県)名
            prmDto.setBAddressPrefectureJnm(cache.decode(NaikaraTalkConstants.CODE_CATEGORY_STATE,
            		seAct.getAddressPrefectureCd()));
            prmDto.setAAddressPrefectureJnm(cache.decode(NaikaraTalkConstants.CODE_CATEGORY_STATE,
            		model.getPrefecture_sel()));
            changeYesFlg = true;
        }

        // 住所(市区町村 等):必須
        if (!StringUtils.equals(seAct.getAddressCity().trim(), model.getCity_txt().trim())) {
            prmDto.setBAddressCity(seAct.getAddressCity());
            prmDto.setAAddressCity(model.getCity_txt());
            changeYesFlg = true;
        }

        // 住所(ﾋﾞﾙ、ﾏﾝｼｮﾝ名 等)
        if (!StringUtils.equals(NaikaraStringUtil.nvlString(seAct.getAddressOthers()).toString().trim(),
        		NaikaraStringUtil.nvlString(model.getOther_txt()).toString().trim())) {
            prmDto.setBAddressOthers(seAct.getAddressOthers());
            prmDto.setAAddressOthers(model.getOther_txt());
            changeYesFlg = true;
        }

        // 性別区分・性別名:必須
        if (!StringUtils.equals(seAct.getGenderKbn().trim(), model.getGender_rdl().trim())) {
            // 性別区分
            prmDto.setBGenderKbn(seAct.getGenderKbn());
            prmDto.setAGenderKbn(model.getGender_rdl());
            // 性別名
            prmDto.setBGenderJnm(cache.decode(NaikaraTalkConstants.CODE_CATEGORY_GENDER,
            		seAct.getGenderKbn()));
            prmDto.setAGenderJnm(cache.decode(NaikaraTalkConstants.CODE_CATEGORY_GENDER,
            		model.getGender_rdl()));
            changeYesFlg = true;
        }

        // メールアドレス1:必須
        if (!StringUtils.equals(seAct.getMailAddress1().trim(), model.getMailAdd1_txt().trim())) {
            prmDto.setBMailAddress1(seAct.getMailAddress1());
            prmDto.setAMailAddress1(model.getMailAdd1_txt());
            changeYesFlg = true;
        }

        // メールアドレス2
        if (!StringUtils.equals(NaikaraStringUtil.nvlString(seAct.getMailAddress2()).toString().trim(),
        		NaikaraStringUtil.nvlString(model.getMailAdd2_txt()).toString().trim())) {
            prmDto.setBMailAddress2(seAct.getMailAddress2());
            prmDto.setAMailAddress2(model.getMailAdd2_txt());
            changeYesFlg = true;
        }

        // メールアドレス3
        if (!StringUtils.equals(NaikaraStringUtil.nvlString(seAct.getMailAddress3()).toString().trim(),
        		NaikaraStringUtil.nvlString(model.getMailAdd3_txt()).toString().trim())) {
            prmDto.setBMailAddress3(seAct.getMailAddress3());
            prmDto.setAMailAddress3(model.getMailAdd3_txt());
            changeYesFlg = true;
        }

        // ◆◆◆保護者情報◆◆◆
        // 保護者：名前(姓)
        if (!StringUtils.equals(NaikaraStringUtil.nvlString(seAct.getGuardianFamilyJnm()).toString().trim(),
        		NaikaraStringUtil.nvlString(model.getGuardFamilyJnm_txt()).toString().trim())) {
            prmDto.setBGuardianFamilyJnm(seAct.getGuardianFamilyJnm());
            prmDto.setAGuardianFamilyJnm(model.getGuardFamilyJnm_txt());
            changeYesFlg = true;
        }

        // 保護者：名前(名）
        if (!StringUtils.equals(NaikaraStringUtil.nvlString(seAct.getGuardianFirstJnm()).toString().trim(),
        		NaikaraStringUtil.nvlString(model.getGuardFirstJnm_txt()).toString().trim())) {
            prmDto.setBGuardianFirstJnm(seAct.getGuardianFirstJnm());
            prmDto.setAGuardianFirstJnm(model.getGuardFirstJnm_txt());
            changeYesFlg = true;
        }

        // 保護者：フリガナ(姓)
        if (!StringUtils.equals(NaikaraStringUtil.nvlString(seAct.getGuardianFamilyKnm()).toString().trim(),
        		NaikaraStringUtil.nvlString(model.getGuardFamilyKnm_txt()).toString().trim())) {
            prmDto.setBGuardianFamilyKnm(seAct.getGuardianFamilyKnm());
            prmDto.setAGuardianFamilyKnm(model.getGuardFamilyKnm_txt());
            changeYesFlg = true;
        }

        // 保護者：フリガナ(名）
        if (!StringUtils.equals(NaikaraStringUtil.nvlString(seAct.getGuardianFirstKnm()).toString().trim(),
        		NaikaraStringUtil.nvlString(model.getGuardFirstKnm_txt()).toString().trim())) {
            prmDto.setBGuardianFirstKnm(seAct.getGuardianFirstKnm());
            prmDto.setAGuardianFirstKnm(model.getGuardFirstKnm_txt());
            changeYesFlg = true;
        }

        // あなたとの続柄
        if (!StringUtils.equals(NaikaraStringUtil.nvlString(seAct.getGuardianFamilyRelationship()).toString().trim(),
        		NaikaraStringUtil.nvlString(model.getGuardRelat_txt()).toString().trim())) {
            prmDto.setBGuardianFamilyRelationship(seAct.getGuardianFamilyRelationship());
            prmDto.setAGuardianFamilyRelationship(model.getGuardRelat_txt());
            changeYesFlg = true;
        }

        // 保護者：電話番号1
        if (!StringUtils.equals(NaikaraStringUtil.nvlString(seAct.getGuardianTel1()).toString().trim(),
        		NaikaraStringUtil.nvlString(model.getGuardTel1_txt()).toString().trim())) {
            prmDto.setBGuardianTel1(seAct.getGuardianTel1());
            prmDto.setAGuardianTel1(model.getGuardTel1_txt());
            changeYesFlg = true;
        }

        // 保護者：電話番号2(携帯電話)
        if (!StringUtils.equals(NaikaraStringUtil.nvlString(seAct.getGuardianTel2()).toString().trim(),
        		NaikaraStringUtil.nvlString(model.getGuardTel2_txt()).toString().trim())) {
            prmDto.setBGuardianTel2(seAct.getGuardianTel2());
            prmDto.setAGuardianTel2(model.getGuardTel2_txt());
            changeYesFlg = true;
        }

        // 保護者：生年月日
        bBirthYyyy = NaikaraTalkConstants.BRANK;
        bBirthMm = NaikaraTalkConstants.BRANK;
        bBirthDd = NaikaraTalkConstants.BRANK;
        bBirth = NaikaraTalkConstants.BRANK;
        aBirthYyyy = NaikaraTalkConstants.BRANK;
        aBirthMm = NaikaraTalkConstants.BRANK;
        aBirthDd = NaikaraTalkConstants.BRANK;
        aBirth = NaikaraTalkConstants.BRANK;
        bBirthYyyy = NaikaraStringUtil.nvlString(seAct.getGuardianBirthYyyy()).toString(); // 生年月日:年
        bBirthMm = NaikaraStringUtil.nvlString(seAct.getGuardianBirthMm()).toString();     // 生年月日:月
        bBirthDd = NaikaraStringUtil.nvlString(seAct.getGuardianBirthDd()).toString();     // 生年月日:日
        bBirth = NaikaraStringUtil.converToYYYYMMDD(NaikaraStringUtil.unionString2(bBirthYyyy,
                NaikaraStringUtil.unionString2(bBirthMm, bBirthDd)));
        aBirthYyyy = NaikaraStringUtil.nvlString(model.getGuardBirthYy_txt()).toString();  // 生年月日:年
        aBirthMm = NaikaraStringUtil.nvlString(model.getGuardBirthMm_txt()).toString();    // 生年月日:月
        aBirthDd = NaikaraStringUtil.nvlString(model.getGuardBirthDd_txt()).toString();    // 生年月日:日
        aBirth = NaikaraStringUtil.converToYYYYMMDD(NaikaraStringUtil.unionString2(aBirthYyyy,
                NaikaraStringUtil.unionString2(aBirthMm, aBirthDd)));
        if (!StringUtils.equals(bBirth, aBirth)) {
            prmDto.setBGuardianBirthDt(NaikaraStringUtil.converToYYYY_MM_DD(bBirth));
            prmDto.setAGuardianBirthDt(NaikaraStringUtil.converToYYYY_MM_DD(aBirth));
            changeYesFlg = true;
        }

        // 保護者：郵便番号
        if (!StringUtils.equals(NaikaraStringUtil.nvlString(seAct.getGuardianZipCd()).toString().trim(),
        		NaikaraStringUtil.nvlString(model.getGuardZipCd_txt()).toString().trim())) {
            prmDto.setBGuardianZipCd(seAct.getGuardianZipCd());
            prmDto.setAGuardianZipCd(model.getGuardZipCd_txt());
            changeYesFlg = true;
        }

        // 保護者：住所(地域)コード・保護者：住所(地域)名
        if (!StringUtils.equals(NaikaraStringUtil.nvlString(seAct.getGuardianAddressAreaCd()).toString().trim(),
        		NaikaraStringUtil.nvlString(model.getGuardArea_sel()).toString().trim())) {

            if (StringUtils.equals(NaikaraTalkConstants.CHOICE_ALL_ZERO, seAct.getGuardianAddressAreaCd())) {
                prmDto.setBGuardianAddressAreaCd(NaikaraTalkConstants.BRANK);          // 保護者：住所(地域)コード
                prmDto.setBGuardianAddressAreaJnm(NaikaraTalkConstants.BRANK);         // 保護者：住所(地域)名
            } else {
                prmDto.setBGuardianAddressAreaCd(seAct.getGuardianAddressAreaCd());    // 保護者：住所(地域)コード
                prmDto.setBGuardianAddressAreaJnm(cache.decode(NaikaraTalkConstants.CODE_CATEGORY_REGION,
                		seAct.getGuardianAddressAreaCd()));                            // 保護者：住所(地域)名
            }

            if (StringUtils.equals(NaikaraTalkConstants.CHOICE_ALL_ZERO, model.getGuardArea_sel())) {
                prmDto.setAGuardianAddressAreaCd(NaikaraTalkConstants.BRANK);          // 保護者：住所(地域)コード
                prmDto.setAGuardianAddressAreaJnm(NaikaraTalkConstants.BRANK);         // 保護者：住所(地域)名
            } else {
                prmDto.setAGuardianAddressAreaCd(model.getGuardArea_sel());            // 保護者：住所(地域)コード
                prmDto.setAGuardianAddressAreaJnm(cache.decode(NaikaraTalkConstants.CODE_CATEGORY_REGION,
                		model.getGuardArea_sel()));                                    // 保護者：住所(地域)名
            }

            changeYesFlg = true;
        }

        // 保護者：住所(都道府県)コード・保護者：住所(都道府県)名
        if (!StringUtils.equals(NaikaraStringUtil.nvlString(seAct.getGuardianAddressPrefectureCd()).toString().trim(),
        		NaikaraStringUtil.nvlString(model.getGuardprefecture_sel()).toString().trim())) {

            if (StringUtils.equals(NaikaraTalkConstants.CHOICE_ALL_ZERO, seAct.getGuardianAddressPrefectureCd())) {
                prmDto.setBGuardianAddressPrefectureCd(NaikaraTalkConstants.BRANK);                // 保護者：住所(都道府県)コード
                prmDto.setBGuardianAddressPrefectureJnm(NaikaraTalkConstants.BRANK);               // 保護者：住所(都道府県)名
            } else {
                prmDto.setBGuardianAddressPrefectureCd(seAct.getGuardianAddressPrefectureCd());    // 保護者：住所(都道府県)コード
                prmDto.setBGuardianAddressPrefectureJnm(cache.decode(NaikaraTalkConstants.CODE_CATEGORY_STATE,
                		seAct.getGuardianAddressPrefectureCd()));                                  // 保護者：住所(都道府県)名
            }

            if (StringUtils.equals(NaikaraTalkConstants.CHOICE_ALL_ZERO, model.getGuardprefecture_sel())) {
                prmDto.setAGuardianAddressPrefectureCd(NaikaraTalkConstants.BRANK);                // 保護者：住所(都道府県)コード
                prmDto.setAGuardianAddressPrefectureJnm(NaikaraTalkConstants.BRANK);               // 保護者：住所(都道府県)名
            } else {
                prmDto.setAGuardianAddressPrefectureCd(model.getGuardprefecture_sel());            // 保護者：住所(都道府県)コード
                prmDto.setAGuardianAddressPrefectureJnm(cache.decode(NaikaraTalkConstants.CODE_CATEGORY_STATE,
                		model.getGuardprefecture_sel()));                                          // 保護者：住所(都道府県)名
            }

            changeYesFlg = true;
        }

        // 保護者：住所(市区町村 等)
        if (!StringUtils.equals(NaikaraStringUtil.nvlString(seAct.getGuardianAddressCity()).toString().trim(),
        		NaikaraStringUtil.nvlString(model.getGuardCity_txt()).toString().trim())) {
            prmDto.setBGuardianAddressCity(seAct.getGuardianAddressCity());
            prmDto.setAGuardianAddressCity(model.getGuardCity_txt());
            changeYesFlg = true;
        }

        // 保護者：住所(ﾋﾞﾙ、ﾏﾝｼｮﾝ名 等)
        if (!StringUtils.equals(NaikaraStringUtil.nvlString(seAct.getGuardianAddressOthers()).toString().trim(),
        		NaikaraStringUtil.nvlString(model.getGuardOther_txt()).toString().trim())) {
            prmDto.setBGuardianAddressOthers(seAct.getGuardianAddressOthers());
            prmDto.setAGuardianAddressOthers(model.getGuardOther_txt());
            changeYesFlg = true;
        }

        // 保護者：性別区分・保護者：性別名
        if (!StringUtils.equals(NaikaraStringUtil.nvlString(seAct.getGuardianGenderKbn()).toString().trim(),
        		NaikaraStringUtil.nvlString(model.getGuardGender_rdl()).toString().trim())) {
            // 保護者：性別区分
            prmDto.setBGuardianGenderKbn(seAct.getGuardianGenderKbn());
            prmDto.setAGuardianGenderKbn(model.getGuardGender_rdl());
            // 保護者：性別名
            prmDto.setBGuardianGenderJnm(cache.decode(NaikaraTalkConstants.CODE_CATEGORY_GENDER,
            		seAct.getGuardianGenderKbn()));
            prmDto.setAGuardianGenderJnm(cache.decode(NaikaraTalkConstants.CODE_CATEGORY_GENDER,
            		model.getGuardGender_rdl()));
            changeYesFlg = true;
        }

        // 保護者：メールアドレス1
        if (!StringUtils.equals(NaikaraStringUtil.nvlString(seAct.getGuardianMailAddress1()).toString().trim(),
        		NaikaraStringUtil.nvlString(model.getGuardMailAdd1_txt()).toString().trim())) {
            prmDto.setBGuardianMailAddress1(seAct.getGuardianMailAddress1());
            prmDto.setAGuardianMailAddress1(model.getGuardMailAdd1_txt());
            changeYesFlg = true;
        }


        // 保護者：メールアドレス2
        if (!StringUtils.equals(NaikaraStringUtil.nvlString(seAct.getGuardianMailAddress2()).toString().trim(),
        		NaikaraStringUtil.nvlString(model.getGuardMailAdd2_txt()).toString().trim())) {
            prmDto.setBGuardianMailAddress2(seAct.getGuardianMailAddress2());
            prmDto.setAGuardianMailAddress2(model.getGuardMailAdd2_txt());
            changeYesFlg = true;
        }

        // 保護者：メールアドレス3
        if (!StringUtils.equals(NaikaraStringUtil.nvlString(seAct.getGuardianMailAddress3()).toString().trim(),
        		NaikaraStringUtil.nvlString(model.getGuardMailAdd3_txt()).toString().trim())) {
            prmDto.setBGuardianMailAddress3(seAct.getGuardianMailAddress3());
            prmDto.setAGuardianMailAddress3(model.getGuardMailAdd3_txt());
            changeYesFlg = true;
        }


        // 登録者コード
        prmDto.setInsertCd(userId);
        // 更新者コード
        prmDto.setUpdateCd(userId);

        // 排他用レコードバージョン
        prmDto.setRecordVerNo(NaikaraTalkConstants.INS_REC_VER_NO);

        // 変更発生の有無
        prmDto.setChangeYesFlg(changeYesFlg);

        // 返却
        return prmDto;
    }

    // 2014/01/03-スクールのメール送信・受信履歴照会に伴う対応-End


}