package com.naikara_talk.service;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.naikara_talk.batch.SendMailBatch;
import com.naikara_talk.dao.StudentMailExistChkDao;
import com.naikara_talk.dbutil.DbUtil;
import com.naikara_talk.dto.CodeManagMstDto;
import com.naikara_talk.dto.OrderNumberDto;
import com.naikara_talk.dto.PointExpirationDto;
import com.naikara_talk.dto.PointOwnershipTrnDto;
import com.naikara_talk.dto.StudentMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.logic.AgeCalculateLogic;
import com.naikara_talk.logic.OrderNumbersMstLogic;
import com.naikara_talk.logic.PasswordChkLogic;
import com.naikara_talk.logic.PointExpirationLogic;
import com.naikara_talk.logic.StudentMstLogic;
import com.naikara_talk.model.StudentMstModel;
import com.naikara_talk.mstcache.CodeManagMstCache;
import com.naikara_talk.sessiondata.SessionUser;
import com.naikara_talk.util.DateUtil;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>マスタ保守。<br>
 * <b>クラス名称　　　:</b>受講者マスタメンテナンス【単票】登録更新Serviceクラス。<br>
 * <b>クラス概要　　　:</b>受講者マスタメンテナンス【単票】登録更新Service。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/07/30 TECS 新規作成。
 */
public class StudentMstUpdateService implements ActionService {

    protected Logger log = Logger.getLogger(this.getClass());

    /** パスワードの複雑性チェック */
    public static final int RETURN_PASS_CHECK = -1;

    /** 更新前チェック：「住所(都道府県)」のチェックエラー */
    public static final int ERR_CHK_ADDR_PREFECTURE_N0_SELECT = 1;

    /** 更新前チェック:｢性別｣ がNullの場合、チェックエラー */
    public static final int ERR_CHK_GENDER_CHECK = 2;

    /** 更新前チェック：「利用規約に同意」のチェックエラー */
    public static final int ERR_CHK_USEAGREEMENT_FLG = 3;

    /** 更新前チェック：「個人情報の同意」のチェックエラー */
    public static final int ERR_CHK_INDIVIDUAL_AGREEMENT_FLG = 4;

    /** 更新前チェック:「生年月日：月」チェックエラー */
    public static final int ERR_STUDENT_NG_MONTH = 5;

    /** 更新前チェック:「生年月日：日」チェックエラー */
    public static final int ERR_STUDENT_NG_DAY = 6;

    /** 更新前チェック： ｢生年月日｣　＞　サーバーのシステム日付のチェックエラー */
    public static final int ERR_CHK_BIRTHDATE_MORE_THAN_SYSDATE = 8;

    /** 更新前チェック： ｢利用期間：開始日｣と｢利用期間：終了日｣の整合性チェック (日付)エラー */
    public static final int ERR_CHK_INTEGRITY_DT = 9;

    /** 更新前チェック:「パスワードの複雑性」のチェックエラー */
    public static final int ERR_CHK_PASS_CHECK = 10;

    /** 更新前チェック:(未成年)の場合,「保護者：お名前(姓)」のチェックエラー */
    public static final int ERR_CHK_GETGUARDIAN_FAMILY_JNM_EMPTY = 11;

    /** 更新前チェック:(未成年)の場合,「保護者：お名前(名)」のチェックエラー */
    public static final int ERR_CHK_GETGUARDIAN_FIRST_JNM_EMPTY = 12;

    /** 更新前チェック:(未成年)の場合,「保護者：フリガナ(姓)」のチェックエラー */
    public static final int ERR_CHK_GETGUARDIAN_FAMILY_KNM_EMPTY = 13;

    /** 更新前チェック:(未成年)の場合,「保護者：フリガナ(名)」のチェックエラー */
    public static final int ERR_CHK_GETGUARDIAN_FIRST_KNM_EMPTY = 14;

    /** 更新前チェック:(未成年)の場合,「あなたとの続柄」のチェックエラー */
    public static final int ERR_CHK_GETGUARDIAN_RELATIONSHIP_EMPTY = 15;

    /** 更新前チェック:(未成年)の場合,「保護者：電話番号１」のチェックエラー */
    public static final int ERR_CHK_GETGUARDIAN_TELE1_EMPTY = 16;

    /** 更新前チェック:(未成年)の場合,「保護者：生年月日(年)」のチェックエラー */
    public static final int ERR_CHK_GETGUARDIAN_BIRTH_YYYY_EMPTY = 17;

    /** 更新前チェック:(未成年)の場合,「保護者：生年月日(月)」のチェックエラー */
    public static final int ERR_CHK_GETGUARDIAN_BIRTH_MM_EMPTY = 18;

    /** 更新前チェック:(未成年)の場合,「保護者：生年月日(日)」のチェックエラー */
    public static final int ERR_CHK_GETGUARDIAN_BIRTH_DD_EMPTY = 19;

    /** 更新前チェック:(未成年)の場合,「保護者：生年月日：月」チェックエラー */
    public static final int ERR_NG_MONTH = 20;

    /** 更新前チェック:(未成年)の場合,「保護者：生年月日：日」チェックエラー */
    public static final int ERR_NG_DAY = 21;

    /** 更新前チェック:(未成年)の場合,「保護者：生年月日」＞　サーバーのシステム日付のチェックエラー */
    public static final int ERR_CHK_GUARDIAN_BIRTHDATE_MORE_THAN_SYSDATE = 22;

    /** 更新前チェック:(未成年)の場合,「保護者：郵便番号」のチェックエラー */
    public static final int ERR_CHK_GUARDIAN_ZIPCODE_EMPTY = 23;

    /** 更新前チェック:(未成年)の場合,「保護者：住所(都道府県)」のチェックエラー */
    public static final int ERR_CHK_GUARDIAN_ADDR_PREFECTURE_N0_SELECT = 24;

    /** 更新前チェック:(未成年)の場合,「保護者：住所(市区町村 等)」のチェックエラー */
    public static final int ERR_CHK_GUARDIAN_ADDR_CITY_EMPTY = 25;

    /** 更新前チェック:(未成年)の場合,「保護者：性別」のチェックエラー */
    public static final int ERR_CHK_GUARDIAN_GENDER_N0_SELECT = 26;

    /** 更新前チェック:(未成年)の場合,「保護者：メールアドレス１」のチェックエラー */
    public static final int ERR_CHK_GUARDIAN_MAIL1_EMPTY = 27;

    /** 更新前チェック:「メールアドレス１－３」の一意チェックエラー */
    public static final int ERR_CHK_MAIL = 28;

    /** 更新前チェック: 月謝データが存在する場合 且つ 利用期間：終了日≠2999/12/31の整合性チェック */
    public static final int ERR_CHK_CHK_INTEGRITY_MONTHLY_DATE = 29;

    /** 更新前チェック:「メールアドレス１」のありチェックエラー */
    public static final int ERR_CHK_MAIL_ARI_1 = 30;

    /** 更新前チェック:「メールアドレス２」のありチェックエラー */
    public static final int ERR_CHK_MAIL_ARI_2 = 31;

    /** 更新前チェック:「メールアドレス３」のありチェックエラー */
    public static final int ERR_CHK_MAIL_ARI_3 = 32;

    /** 更新前チェック:｢月謝用の実購入日｣ がNull チェックエラー */
    public static final int ERR_CHK_BEGINNING_PURCHASEDT = 33;

    /** 更新前チェック:「保護者の同意書の入手」が選択しない チェックエラー */
    public static final int ERR_CHK_CONSENT_DOCUMENT_FLG = 34;

    /** checkbox status : checked */
    private static String ONE = "1";

    /** checkbox status : unchecked */
    private static String ZERO = "0";

    /** Data Zero */
    private static int DATA_ZERO = 0;

    /** ポイントコード */
    private static String POINT_INIT_CD = "PT0000000000";

    /** 追加 */
    private static String DATE_ADD = "1";

    /** 削除 */
    private static String DATE_DEL = "2";

    /** 問題なし */
    private static int CHECK_OK = 0;

    /**
     * 登録、更新のチェック。<br>
     * <br>
     * @param なし
     * @return boolean
     * @throws NaiException
     */
    public int errorCheck(StudentMstModel model) throws NaiException {

        // // 関連チェック
        // ｢住所(都道府県)｣ ='0000'の場合
        if (StringUtils.equals(NaikaraTalkConstants.CHOICE_ALL_ZERO, model.getAddressPrefectureCd_sel())) {
            return ERR_CHK_ADDR_PREFECTURE_N0_SELECT;
        }

        // 性別のチェック
        if (StringUtils.isEmpty(model.getGenderKbn_rdl())) {
            return ERR_CHK_GENDER_CHECK;
        }

        // ｢利用規約に同意する｣＝FALSEの場合
        if (model.isUseAgreement_chk() == false) {
            return ERR_CHK_USEAGREEMENT_FLG;
        }

        // ｢個人情報の同意｣＝FALSEの場合]
        if (model.isIndividualAgreement_chk() == false) {
            return ERR_CHK_INDIVIDUAL_AGREEMENT_FLG;
        }

        // 「保護者の同意書の入手」が選択しないの場合
        if (StringUtils.isEmpty(model.getConsentDocumentAcquisitionFlg_rdl())) {
            return ERR_CHK_CONSENT_DOCUMENT_FLG;
        }

        // 生年月日String(yyyyMMdd)
        String birthStr = NaikaraStringUtil.converToYYYYMMDD(NaikaraStringUtil.unionString2(
                NaikaraStringUtil.unionString2(model.getBirthYyyy_txt(), model.getBirthMm_txt()),
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

        // 生年月日チェック
        if (year != calendar.get(Calendar.YEAR) || month != calendar.get(Calendar.MONTH) + 1
                || date != calendar.get(Calendar.DAY_OF_MONTH)) {

            if (month > 12 || month < 1) {
                return ERR_STUDENT_NG_MONTH;
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
            if (date > temp || date < 1) {
                return ERR_STUDENT_NG_DAY;
            }
        }

        // ｢生年月日｣　＞　サーバーのシステム日付　の場合
        if (DateUtil.dateCompare2(birthStr, DateUtil.getSysDate())) {
            return ERR_CHK_BIRTHDATE_MORE_THAN_SYSDATE;
        }

        // ｢利用期間：開始日｣と｢利用期間：終了日｣の整合性チェック (日付)
        if (DateUtil.dateCompare2(NaikaraStringUtil.converToYYYYMMDD(model.getUseStrDt_txt()),
                NaikaraStringUtil.converToYYYYMMDD(model.getUseEndDt_txt()))) {
            return ERR_CHK_INTEGRITY_DT;
        }

        // パスワードの複雑性のチェック
        PasswordChkLogic passwordChkLogic = new PasswordChkLogic();
        if (passwordChkLogic.passwordCheck(model.getPassword_txt()) == RETURN_PASS_CHECK) {
            return ERR_CHK_PASS_CHECK;
        }

        // (未成年)の場合は、必須チェック
        AgeCalculateLogic ageCalculateLogic = new AgeCalculateLogic();
        if (ageCalculateLogic.ageCalculate(birthStr, DateUtil.getSysDate()).getAdult() == AgeCalculateLogic.ADULT_KBN_MINORITY) {
            // 保護者：お名前(姓)がなし
            if (StringUtils.equals(NaikaraTalkConstants.BRANK, model.getGuardianFamilyJnm_txt())) {
                return ERR_CHK_GETGUARDIAN_FAMILY_JNM_EMPTY;
            }

            // 保護者：お名前(名)がなし
            if (StringUtils.equals(NaikaraTalkConstants.BRANK, model.getGuardianFirstJnm_txt())) {
                return ERR_CHK_GETGUARDIAN_FIRST_JNM_EMPTY;
            }

            // 保護者：フリガナ(姓)がなし
            if (StringUtils.equals(NaikaraTalkConstants.BRANK, model.getGuardianFamilyKnm_txt())) {
                return ERR_CHK_GETGUARDIAN_FAMILY_KNM_EMPTY;
            }

            // 保護者：フリガナ(名)がなし
            if (StringUtils.equals(NaikaraTalkConstants.BRANK, model.getGuardianFirstKnm_txt())) {
                return ERR_CHK_GETGUARDIAN_FIRST_KNM_EMPTY;
            }
            // あなたとの続柄がなし
            if (StringUtils.equals(NaikaraTalkConstants.BRANK, model.getGuardianFamilyRelationship_txt())) {
                return ERR_CHK_GETGUARDIAN_RELATIONSHIP_EMPTY;
            }

            // 保護者：電話番号１がなし
            if (StringUtils.equals(NaikaraTalkConstants.BRANK, model.getGuardianTel1_txt())) {
                return ERR_CHK_GETGUARDIAN_TELE1_EMPTY;
            }

            // 保護者：生年月日：年がなし
            if (StringUtils.equals(NaikaraTalkConstants.BRANK, model.getGuardianBirthYyyy_txt())) {
                return ERR_CHK_GETGUARDIAN_BIRTH_YYYY_EMPTY;
            }
            // 保護者：生年月日：月がなし
            if (StringUtils.equals(NaikaraTalkConstants.BRANK, model.getGuardianBirthMm_txt())) {
                return ERR_CHK_GETGUARDIAN_BIRTH_MM_EMPTY;
            }

            // 保護者：生年月日：日がなし
            if (StringUtils.equals(NaikaraTalkConstants.BRANK, model.getGuardianBirthDd_txt())) {
                return ERR_CHK_GETGUARDIAN_BIRTH_DD_EMPTY;
            }

            // ｢保護者：生年月日｣ String(yyyyMMdd)
            String strGuardianBirthDate = NaikaraStringUtil.converToYYYYMMDD(NaikaraStringUtil.unionString2(
                    NaikaraStringUtil.unionString2(model.getGuardianBirthYyyy_txt(), model.getGuardianBirthMm_txt()),
                    model.getGuardianBirthDd_txt()));

            // ｢保護者：生年月日｣Date(yyyyMMdd)
            Date birthDate_G;
            try {
                birthDate_G = new SimpleDateFormat(DateUtil.DATE_FORMAT_yyyyMMdd).parse(strGuardianBirthDate);
            } catch (ParseException e) {
                throw new NaiException(e);
            }

            // 生年月日Calendar(yyyyMMdd)
            calendar.setTime(birthDate_G);

            year = Integer.parseInt(strGuardianBirthDate.substring(0, 4));
            month = Integer.parseInt(strGuardianBirthDate.substring(4, 6));
            date = Integer.parseInt(strGuardianBirthDate.substring(6, 8));

            // 生年月日チェック
            if (year != calendar.get(Calendar.YEAR) || month != calendar.get(Calendar.MONTH) + 1
                    || date != calendar.get(Calendar.DAY_OF_MONTH)) {

                if (month > 12 || month < 1) {
                    return ERR_NG_MONTH;
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
                if (date > temp || date < 1) {
                    return ERR_NG_DAY;
                }
            }

            // ｢保護者：生年月日｣　＞　サーバーのシステム日付　
            if (DateUtil.dateCompare2(strGuardianBirthDate, DateUtil.getSysDate())) {
                return ERR_CHK_GUARDIAN_BIRTHDATE_MORE_THAN_SYSDATE;
            }

            // ｢保護者：郵便番号｣ がなし
            if (StringUtils.equals(NaikaraTalkConstants.BRANK, model.getGuardianZipCd_txt())) {
                return ERR_CHK_GUARDIAN_ZIPCODE_EMPTY;
            }

            // ｢保護者：住所(都道府県)｣ ='0000'
            if (StringUtils.equals(NaikaraTalkConstants.CHOICE_ALL_ZERO, model.getGuardianAddressPrefectureCd_sel())) {
                return ERR_CHK_GUARDIAN_ADDR_PREFECTURE_N0_SELECT;

            }

            // 「保護者：住所(市区町村 等)」がなし
            if (StringUtils.equals(NaikaraTalkConstants.BRANK, model.getGuardianAddressCity_txt())) {
                return ERR_CHK_GUARDIAN_ADDR_CITY_EMPTY;
            }

            // 「保護者：性別」がなし
            if (StringUtils.isEmpty(model.getGuardianGenderKbn_rdl())) {
                return ERR_CHK_GUARDIAN_GENDER_N0_SELECT;
            }

            // 「保護者：メールアドレス１」がなし
            if (StringUtils.isEmpty(model.getGuardianMailAddress1_txt())) {
                return ERR_CHK_GUARDIAN_MAIL1_EMPTY;
            }
        }

        // 「メールアドレス1-3」の一意チェック
        String mail1 = model.getMailAddress1_txt();
        String mail2 = model.getMailAddress2_txt();
        String mail3 = model.getMailAddress3_txt();

        if (StringUtils.isEmpty(mail2) && (!StringUtils.isEmpty(mail3)) && (StringUtils.equals(mail1, mail3))) {
            return ERR_CHK_MAIL;
        }

        if (StringUtils.isEmpty(mail3) && (!StringUtils.isEmpty(mail2)) && (StringUtils.equals(mail1, mail2))) {
            return ERR_CHK_MAIL;
        }

        if ((!StringUtils.isEmpty(mail2))
                && (!StringUtils.isEmpty(mail3))
                && (StringUtils.equals(mail1, mail2) || StringUtils.equals(mail2, mail3) || StringUtils.equals(mail3,
                        mail1))) {
            return ERR_CHK_MAIL;
        }

        if ((!StringUtils.isEmpty(mail2))
                && (!StringUtils.isEmpty(mail3))
                && (StringUtils.equals(mail1, mail2) || StringUtils.equals(mail2, mail3) || StringUtils.equals(mail3,
                        mail1))) {
            return ERR_CHK_MAIL;
        }

        // 月謝データが存在する場合 且つ 利用期間：終了日≠2999/12/31の整合性チェック
        if (StringUtils.equals(model.getFeeKbn(), StudentMstLoadService.FEEKBN_ARI)
                && !StringUtils.equals(model.getUseEndDt_txt(), NaikaraTalkConstants.MAX_END_DT)) {
            String maxDate = NaikaraTalkConstants.BRANK;
            if (!(model.getMonthlyReturnList().size() == 0)) {
                // リストソート
                List<PointOwnershipTrnDto> list = new ArrayList<PointOwnershipTrnDto>();
                list.addAll(model.getMonthlyReturnList());
                Collections.sort(list, new ComparatorPointOwnershipTrnDto());

                maxDate = list.get(list.size() - 1).getEndDt();

            } else {
                maxDate = NaikaraTalkConstants.BRANK;
            }
            String useEndYM = NaikaraStringUtil.converToYYYY_MM(model.getUseEndDt_txt());
            if (DateUtil.dateCompare5(maxDate, useEndYM)) {
                return ERR_CHK_CHK_INTEGRITY_MONTHLY_DATE;
            }

        }

        Connection conn = null;
        try {
            conn = DbUtil.getConnection();
            StudentMailExistChkDao studentMailExistChkDao = new StudentMailExistChkDao(conn);
            // 「メールアドレス1」の一意チェック
            if (studentMailExistChkDao.count(model.getMailAddress1_txt(), DateUtil.getSysDate(),
                    model.getStudentId_lbl()) == NaikaraTalkConstants.RETURN_CD_DATA_ERR) {
                return ERR_CHK_MAIL_ARI_1;
            }

            // 「メールアドレス2」の一意チェック
            if (!StringUtils.equals(NaikaraTalkConstants.BRANK, model.getMailAddress2_txt())) {
                if (studentMailExistChkDao.count(model.getMailAddress2_txt(), DateUtil.getSysDate(),
                        model.getStudentId_lbl()) == NaikaraTalkConstants.RETURN_CD_DATA_ERR) {
                    return ERR_CHK_MAIL_ARI_2;
                }
            }

            // 「メールアドレス2」の一意チェック
            if (!StringUtils.equals(NaikaraTalkConstants.BRANK, model.getMailAddress3_txt())) {
                if (studentMailExistChkDao.count(model.getMailAddress3_txt(), DateUtil.getSysDate(),
                        model.getStudentId_lbl()) == NaikaraTalkConstants.RETURN_CD_DATA_ERR) {
                    return ERR_CHK_MAIL_ARI_3;
                }
            }

            // 更新実行(ポイント所有テーブル：月謝の停止依頼登録)
            for (int i = 0; i < model.getMonthlyReturnList().size(); i++) {

                PointOwnershipTrnDto pointOwnershipTrnDto = model.getMonthlyReturnList().get(i);

                PointOwnershipTrnDto getDto = new PointOwnershipTrnDto();
                StudentMstLogic studentMstLogic = new StudentMstLogic(conn);
                getDto = studentMstLogic.selectBeforUpd(pointOwnershipTrnDto);

                String strBeginningPurchaseDt = getDto.getBeginningPurchaseDt();
                if (StringUtils.isEmpty(strBeginningPurchaseDt)) {
                    return ERR_CHK_BEGINNING_PURCHASEDT;
                }
            }

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

        return CHECK_OK;
    }

    /**
     * 登録処理。<br>
     * <br>
     * @param StudentMstModel
     *            画面のパラメータ
     * @return なし
     * @throws NaiException
     */
    public int insert(StudentMstModel model) throws NaiException {

        Connection conn = null;
        try {
            conn = DbUtil.getConnection();

            StudentMstLogic studentMstLogic = new StudentMstLogic(conn);
            // DTOの初期化
            StudentMstDto prmDto = new StudentMstDto();

            // 受講者IDの自動採番処理
            OrderNumbersMstLogic orderNumbersMstLogic = new OrderNumbersMstLogic();
            OrderNumberDto orderNumberDto = orderNumbersMstLogic.getOrderNumber(NaikaraTalkConstants.ORDER_NUMBERS_CU,
                    DateUtil.getSysDate());
            model.setStudentId_lbl(orderNumberDto.getOrderNumber());
            // Model値をDTOにセット
            prmDto = this.modelToDto(model, prmDto);

            // 利用停止状態：OFFの場合は、”0”：利用可
            prmDto.setUseStopFlg("0");

            // 登録実行(受講者マスタ)
            int result = studentMstLogic.insert(prmDto);

            // // ログインユーザID
            String userId = ((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString())).getUserId();

            // 登録実行(ポイント所有テーブル)
            if (model.getSpecialFreeReturnList().size() > DATA_ZERO) {
                for (int i = 0; i < model.getSpecialFreeReturnList().size(); i++) {

                    PointOwnershipTrnDto pointOwnershipTrnDto = model.getSpecialFreeReturnList().get(i);

                    // 所有IDの自動採番処理
                    orderNumbersMstLogic = new OrderNumbersMstLogic();
                    orderNumberDto = orderNumbersMstLogic.getOrderNumber(NaikaraTalkConstants.ORDER_NUMBERS_OW,
                            DateUtil.getSysDate());
                    pointOwnershipTrnDto.setOwnershipId(orderNumberDto.getOrderNumber());

                    pointOwnershipTrnDto.setCompensationFreeKbn(NaikaraTalkConstants.COMPENSATION_FREE_KBN_NO_SPECIAL);
                    pointOwnershipTrnDto.setStudentId(model.getStudentId_lbl());
                    pointOwnershipTrnDto.setFeeKbn(NaikaraTalkConstants.FEE_KBN_NORMAL);
                    pointOwnershipTrnDto.setEffectiveStrDt(NaikaraStringUtil.converToYYYYMMDD(pointOwnershipTrnDto
                            .getEffectiveStrDt()));
                    pointOwnershipTrnDto.setEffectiveEndDt(NaikaraStringUtil.converToYYYYMMDD(pointOwnershipTrnDto
                            .getEffectiveEndDt()));
                    pointOwnershipTrnDto.setPurchaseYen(new BigDecimal(DATA_ZERO));
                    pointOwnershipTrnDto.setPurchaseDt(DateUtil.getSysDate());
                    pointOwnershipTrnDto.setPurchaseTm(DateUtil.getSysTime());
                    pointOwnershipTrnDto.setScreenSystemKbn(NaikaraTalkConstants.SCREEN_SYSTEM_KBN_SCREEN);
                    pointOwnershipTrnDto.setProfileId(null);
                    pointOwnershipTrnDto.setEndFlg(NaikaraTalkConstants.END_FLG_NO);    // 月謝停止フラグ 0:未処理、1:処理済
                    pointOwnershipTrnDto.setRecordVerNo(DATA_ZERO);
                    pointOwnershipTrnDto.setInsertCd(userId);
                    pointOwnershipTrnDto.setUpdateCd(userId);

                    result = studentMstLogic.insertPointOwnershipTrn(pointOwnershipTrnDto);

                }
            } else {
                PointOwnershipTrnDto pointOwnershipTrnDto = createNewPointOwnershipTrnDto(model);
                result = studentMstLogic.insertPointOwnershipTrn(pointOwnershipTrnDto);

            }

            // コミット
            conn.commit();

            // メール送信処理
            this.sendMail(model);

            // 返却
            return result;

        } catch (SQLException se) {
            log.info(se);
            se.printStackTrace();
            throw new NaiException(se);
        } finally {
            try {
                conn.close();
            } catch (Exception e1) {
                log.info(e1);
                e1.printStackTrace();
            }
        }
    }

    /**
     * 更新処理。<br>
     * <br>
     * @param StudentMstModel
     *            画面のパラメータ
     * @return int
     * @throws NaiException
     */
    public int update(StudentMstModel model) throws NaiException {

        int cnt = 0;
        Connection conn = null;
        try {
            conn = DbUtil.getConnection();

            StudentMstLogic studentMstLogic = new StudentMstLogic(conn);
            // DTOの初期化
            StudentMstDto prmDto = new StudentMstDto();
            PointOwnershipTrnDto pointOwnershipTrnDto = new PointOwnershipTrnDto();
            // Model値をDTOにセット
            prmDto = this.modelToDto(model, prmDto);
            prmDto = studentMstLogic.select(prmDto);
            prmDto = this.modelToDto(model, prmDto);
            // 更新実行 (受講マスタ)
            cnt = studentMstLogic.update(prmDto);

            // 排他
            if (cnt == NaikaraTalkConstants.RETURN_CD_ERR_NO_UPD) {
                return cnt;
            }

            // ログインユーザID
            String userId = ((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString())).getUserId();

            // 更新実行(ポイント所有テーブル：月謝の停止依頼登録)
            for (int i = 0; i < model.getMonthlyReturnList().size(); i++) {

                pointOwnershipTrnDto = model.getMonthlyReturnList().get(i);

                PointOwnershipTrnDto getDto = new PointOwnershipTrnDto();
                getDto = studentMstLogic.selectBeforUpd(pointOwnershipTrnDto);

                String strBeginningPurchaseDt = getDto.getBeginningPurchaseDt();
                String strDate = DateUtil.getDateAddDay(strBeginningPurchaseDt, -1);
                String strDay = strDate.substring(strDate.length() - 2, strDate.length());
                if (StringUtils.isEmpty(pointOwnershipTrnDto.getEndDt())) {
                    getDto.setEndDt(NaikaraTalkConstants.BRANK);
                } else {
                    getDto.setEndDt(NaikaraStringUtil.converToYYYYMM(pointOwnershipTrnDto.getEndDt()) + strDay);
                }

                getDto.setUpdateCd(userId);

                // 更新実行
                studentMstLogic.updateMonthly(getDto);
            }

            // 更新/追加実行(ポイント所有テーブル：特別無償ポイント設定)

            for (int j = 0; j < model.getSpecialFreeReturnList().size(); j++) {
                pointOwnershipTrnDto = model.getSpecialFreeReturnList().get(j);
                // 追加
                if (StringUtils.equals(DATE_ADD, pointOwnershipTrnDto.getUpdateKbn())) {

                    // 所有IDの自動採番処理
                    OrderNumbersMstLogic orderNumbersMstLogic = new OrderNumbersMstLogic();
                    OrderNumberDto orderNumberDto = orderNumbersMstLogic.getOrderNumber(
                            NaikaraTalkConstants.ORDER_NUMBERS_OW, DateUtil.getSysDate());
                    pointOwnershipTrnDto.setOwnershipId(orderNumberDto.getOrderNumber());

                    pointOwnershipTrnDto.setCompensationFreeKbn(NaikaraTalkConstants.COMPENSATION_FREE_KBN_NO_SPECIAL);
                    pointOwnershipTrnDto.setStudentId(model.getStudentId_lbl());
                    pointOwnershipTrnDto.setFeeKbn(NaikaraTalkConstants.FEE_KBN_NORMAL);
                    pointOwnershipTrnDto.setEffectiveStrDt(NaikaraStringUtil.converToYYYYMMDD(pointOwnershipTrnDto
                            .getEffectiveStrDt()));
                    pointOwnershipTrnDto.setEffectiveEndDt(NaikaraStringUtil.converToYYYYMMDD(pointOwnershipTrnDto
                            .getEffectiveEndDt()));
                    pointOwnershipTrnDto.setPurchaseYen(new BigDecimal(DATA_ZERO));
                    pointOwnershipTrnDto.setPurchaseDt(DateUtil.getSysDate());
                    pointOwnershipTrnDto.setPurchaseTm(DateUtil.getSysTime());
                    pointOwnershipTrnDto.setScreenSystemKbn(NaikaraTalkConstants.SCREEN_SYSTEM_KBN_SCREEN);
                    pointOwnershipTrnDto.setProfileId(null);
                    pointOwnershipTrnDto.setEndFlg(NaikaraTalkConstants.END_FLG_NO);    // 月謝停止フラグ 0:未処理、1:処理済
                    pointOwnershipTrnDto.setRecordVerNo(DATA_ZERO);
                    pointOwnershipTrnDto.setInsertCd(userId);
                    pointOwnershipTrnDto.setUpdateCd(userId);

                    studentMstLogic.insertPointOwnershipTrn(pointOwnershipTrnDto);
                }
                // 削除
                if (StringUtils.equals(DATE_DEL, pointOwnershipTrnDto.getUpdateKbn())) {
                    // ｢購入／無償ポイント｣≠｢ポイント残高｣ の場合は、更新処理
                    if (!StringUtils.equals(pointOwnershipTrnDto.getPurchaseFreePoint().toString(),
                            pointOwnershipTrnDto.getBalancePoint().toString())) {

                        PointOwnershipTrnDto getDto = new PointOwnershipTrnDto();

                        getDto = studentMstLogic.selectBeforUpd(pointOwnershipTrnDto);
                        getDto.setPurchaseFreePoint(new BigDecimal(Integer.valueOf(pointOwnershipTrnDto
                                .getPurchaseFreePoint().toString())
                                - Integer.valueOf(pointOwnershipTrnDto.getBalancePoint().toString())));
                        getDto.setBalancePoint(new BigDecimal("0"));
                        getDto.setUpdateCd(userId);

                        studentMstLogic.update(getDto);
                        // 削除処理
                    } else {
                        studentMstLogic.deleteForStuSpe(pointOwnershipTrnDto);
                    }

                }
            }

            // コミット
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
     * データの存在チェック。<br>
     * <br>
     * @param StudentMstModel
     *            画面のパラメータ
     * @return int
     * @throws NaiException
     */
    public int getExist(StudentMstModel model) throws NaiException {
        Connection conn = null;
        try {
            conn = DbUtil.getConnection();

            StudentMstLogic studentMstLogic = new StudentMstLogic(conn);
            // DTOの初期化
            StudentMstDto prmDto = new StudentMstDto();
            // Model値をDTOにセット
            prmDto = this.modelToDto(model, prmDto);

            // 検索実行
            return studentMstLogic.getExist(prmDto);
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
     * コード管理マスタからデータ取得処理。<br>
     * <br>
     * @param category
     *            汎用コード
     * @return LinkedHashMap<String, String>
     * @throws NaiException
     */
    public LinkedHashMap<String, String> selectCodeMst(String category) throws NaiException {
        Connection conn = null;
        try {
            conn = DbUtil.getConnection();
            StudentMstLogic studentMstLogic = new StudentMstLogic(conn);
            // コード管理マスタ検索
            return studentMstLogic.selectCodeMst(category);
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
     * Model値をDTOにセット。<br>
     * <br>
     * @param model
     *           StudentMstModel
     * @return StudentMstDto
     * @throws NaiException
     */
    private StudentMstDto modelToDto(StudentMstModel model, StudentMstDto prmDto) throws NaiException {

        prmDto.setStudentId(model.getStudentId_lbl());                                                                   // 受講者ID
        prmDto.setFamilyJnm(model.getFamilyJnm_txt());                                                                   // 名前(姓)
        prmDto.setFirstJnm(model.getFirstJnm_txt());                                                                     // 名前(名)
        prmDto.setFamilyKnm(model.getFamilyKnm_txt());                                                                   // フリガナ(姓)
        prmDto.setFirstKnm(model.getFirstKnm_txt());                                                                     // フリガナ(名)
        prmDto.setFamilyRomaji(model.getFamilyRomaji_txt());                                                             // ローマ字(姓)
        prmDto.setFirstRomaji(model.getFirstRomaji_txt());                                                               // ローマ字(名)
        prmDto.setNickNm(model.getNickNm_txt());                                                                         // ニックネーム
        prmDto.setPassword(model.getPassword_txt());                                                                     // パスワード
        prmDto.setTel1(model.getTel1_txt());                                                                             // 電話番号1
        prmDto.setTel2(model.getTel2_txt());                                                                             // 電話番号2
        prmDto.setBirthYyyy(model.getBirthYyyy_txt());                                                                   // 生年月日：年
        prmDto.setBirthMm(model.getBirthMm_txt());                                                                       // 生年月日：月
        prmDto.setBirthDd(model.getBirthDd_txt());                                                                       // 生年月日：日
        prmDto.setZipCd(model.getZipCd_txt());                                                                           // 郵便番号
        prmDto.setAddressAreaCd(model.getAddressAreaCd_sel());                                                           // 住所(地域)コード
        prmDto.setAddressPrefectureCd(model.getAddressPrefectureCd_sel());                                               // 住所(都道府県)コード
        prmDto.setAddressCity(model.getAddressCity_txt());                                                               // 住所(市区町村等)
        prmDto.setAddressOthers(model.getAddressOthers_txt());                                                           // 住所(ﾋﾞﾙ、ﾏﾝｼｮﾝ名等)
        prmDto.setGenderKbn(model.getGenderKbn_rdl());                                                                   // 性別区分
        prmDto.setMailAddress1(model.getMailAddress1_txt());                                                             // メールアドレス1
        prmDto.setMailAddress2(model.getMailAddress2_txt());                                                             // メールアドレス2
        prmDto.setMailAddress3(model.getMailAddress3_txt());                                                             // メールアドレス3
        prmDto.setOccupationCd(model.getOccupationCd_sel());                                                             // 職業コード
        prmDto.setCustomerKbn(model.getCustomerKbn_rdl());                                                               // 顧客区分
        prmDto.setOrganizationNm(model.getOrganizationNm_txt());                                                         // 組織名
        prmDto.setUseStrDt(NaikaraStringUtil.converToYYYYMMDD(model.getUseStrDt_txt()));                                 // 利用開始日
        prmDto.setUseEndDt(NaikaraStringUtil.converToYYYYMMDD(model.getUseEndDt_txt()));                                 // 利用終了日
        if (StringUtils.isEmpty(model.getUseStopFlg())) {
            prmDto.setUseStopFlg(NaikaraTalkConstants.USE_KBN_OK);                                                       // 利用停止フラグ
        } else {
            prmDto.setUseStopFlg(model.getUseStopFlg());
        }

        String useMotiveFlg1 = model.isUseMotiveFlg_chk1() == false ? NaikaraTalkConstants.USE_MOTIVE_FLG_NO
                : NaikaraTalkConstants.USE_MOTIVE_FLG_YES;
        String useMotiveFlg2 = model.isUseMotiveFlg_chk2() == false ? NaikaraTalkConstants.USE_MOTIVE_FLG_NO
                : NaikaraTalkConstants.USE_MOTIVE_FLG_YES;
        String useMotiveFlg3 = model.isUseMotiveFlg_chk3() == false ? NaikaraTalkConstants.USE_MOTIVE_FLG_NO
                : NaikaraTalkConstants.USE_MOTIVE_FLG_YES;


//2013/09/25-Upd-Start
//        String useMotiveFlg4 = model.isUseMotiveFlg_chk4() == false ? NaikaraTalkConstants.USE_MOTIVE_FLG_NO
//                : NaikaraTalkConstants.USE_MOTIVE_FLG_YES;
        String useMotiveFlg4 = NaikaraTalkConstants.BRANK;
//2013/09/25-Upd-End


        String useMotiveFlg5 = model.isUseMotiveFlg_chk5() == false ? NaikaraTalkConstants.USE_MOTIVE_FLG_NO
                : NaikaraTalkConstants.USE_MOTIVE_FLG_YES;
        prmDto.setUseMotiveFlg1(useMotiveFlg1);                                                                          // 利用動機フラグ１
        prmDto.setUseMotiveFlg2(useMotiveFlg2);                                                                          // 利用動機フラグ２
        prmDto.setUseMotiveFlg3(useMotiveFlg3);                                                                          // 利用動機フラグ３
        prmDto.setUseMotiveFlg4(useMotiveFlg4);                                                                          // 利用動機フラグ４
        prmDto.setUseMotiveFlg5(useMotiveFlg5);                                                                          // 利用動機フラグ５
        prmDto.setUseMotive(model.getUseMotive_txt());                                                                   // 利用動機テキスト
        prmDto.setAchievement(model.getAchievement_txt());                                                               // 達成目標
        String useAgreementFlg = model.isUseAgreement_chk() == true ? ONE : ZERO;
        prmDto.setUseAgreementFlg(useAgreementFlg);                                                                      // 利用規約に同意する：チェックフラ
        String useIndividualAgreementFlg = model.isIndividualAgreement_chk() == true ? ONE : ZERO;
        prmDto.setIndividualAgreementFlg(useIndividualAgreementFlg);                                                     // 個人情報の同意：チェックフラグ
        prmDto.setSchoolCmt(model.getSchoolCmt_txa());                                                                   // ｽｸｰﾙからのｺﾒﾝﾄ(生徒公開)
        prmDto.setRemark(model.getRemark_txa());                                                                         // 備考(生徒非公開)
        prmDto.setConsentDocumentAcquisitionFlg(model.getConsentDocumentAcquisitionFlg_rdl());                           // 保護者の同意書の入手フラグ
        String pointPurchaseFlg = StringUtils.isEmpty(prmDto.getPointPurchaseFlg()) ? NaikaraTalkConstants.POINT_PURCHASE_FLG_NO
                : prmDto.getPointPurchaseFlg();
        prmDto.setPointPurchaseFlg(pointPurchaseFlg);                                                                    // ポイント購入済フラグ
        int DmNoNeedFlg = model.isDmNoNeedFlg_chk() == false ? NaikaraTalkConstants.OTHER_FLG_OFF
                : NaikaraTalkConstants.OTHER_FLG_ON;
        int otherFlg1 = model.isOther1Flg_chk() == false ? NaikaraTalkConstants.OTHER_FLG_OFF
                : NaikaraTalkConstants.OTHER_FLG_ON;
        int otherFlg2 = model.isOther2Flg_chk() == false ? NaikaraTalkConstants.OTHER_FLG_OFF
                : NaikaraTalkConstants.OTHER_FLG_ON;
        int otherFlg3 = model.isOther3Flg_chk() == false ? NaikaraTalkConstants.OTHER_FLG_OFF
                : NaikaraTalkConstants.OTHER_FLG_ON;
        int otherFlg4 = model.isOther4Flg_chk() == false ? NaikaraTalkConstants.OTHER_FLG_OFF
                : NaikaraTalkConstants.OTHER_FLG_ON;
        prmDto.setDmNoNeedFlg(DmNoNeedFlg);                                                                              // DM不要フラグ
        prmDto.setOther1Flg(otherFlg1);                                                                                  // その他フラグ１
        prmDto.setOther2Flg(otherFlg2);                                                                                  // その他フラグ２
        prmDto.setOther3Flg(otherFlg3);                                                                                  // その他フラグ３
        prmDto.setOther4Flg(otherFlg4);                                                                                  // その他フラグ４
        prmDto.setGuardianFamilyJnm(model.getGuardianFamilyJnm_txt());                                                   // 保護者：名前(姓)
        prmDto.setGuardianFirstJnm(model.getGuardianFirstJnm_txt());                                                     // 保護者：名前(名）
        prmDto.setGuardianFamilyKnm(model.getGuardianFamilyKnm_txt());                                                   // 保護者：フリガナ(姓)
        prmDto.setGuardianFirstKnm(model.getGuardianFirstKnm_txt());                                                     // 保護者：フリガナ(名）
        prmDto.setGuardianFamilyRelationship(model.getGuardianFamilyRelationship_txt());                                 // あなたとの続柄
        prmDto.setGuardianTel1(model.getGuardianTel1_txt());                                                             // 保護者：電話番号1
        prmDto.setGuardianTel2(model.getGuardianTel2_txt());                                                             // 保護者：電話番号2
        prmDto.setGuardianBirthYyyy(model.getGuardianBirthYyyy_txt());                                                   // 保護者：生年月日：年
        prmDto.setGuardianBirthMm(model.getGuardianBirthMm_txt());                                                       // 保護者：生年月日：月
        prmDto.setGuardianBirthDd(model.getGuardianBirthDd_txt());                                                       // 保護者：生年月日：日
        prmDto.setGuardianZipCd(model.getGuardianZipCd_txt());                                                           // 保護者：郵便番号
        prmDto.setGuardianAddressAreaCd(model.getGuardianAddressAreaCd_sel());                                           // 保護者：住所(地域)コード
        prmDto.setGuardianAddressPrefectureCd(model.getGuardianAddressPrefectureCd_sel());                               // 保護者：住所(都道府県)コード
        prmDto.setGuardianAddressCity(model.getGuardianAddressCity_txt());                                               // 保護者：住所(市区町村等)
        prmDto.setGuardianAddressOthers(model.getGuardianAddressOthers_txt());                                           // 保護者：住所(ﾋﾞﾙ、ﾏﾝｼｮﾝ名等)
        prmDto.setGuardianGenderKbn(model.getGuardianGenderKbn_rdl());                                                   // 保護者：性別区分
        prmDto.setGuardianMailAddress1(model.getGuardianMailAddress1_txt());                                             // 保護者：メールアドレス1
        prmDto.setGuardianMailAddress2(model.getGuardianMailAddress2_txt());                                             // 保護者：メールアドレス2
        prmDto.setGuardianMailAddress3(model.getGuardianMailAddress3_txt());                                             // 保護者：メールアドレス3
        prmDto.setRecordVerNo(model.getRecordVerNo());                                                                   // レコードバージョン番号
        prmDto.setInsertCd(((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString())).getUserId());    // 登録者コード
        prmDto.setUpdateCd(((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString())).getUserId());    // 更新者コード

        return prmDto;

    }

    /**
     * Model値をDTOにセット。<br>
     * <br>
     * @param model
     *           StudentMstModel
     * @return PointOwnershipTrnDto
     * @throws NaiException
     */
    private PointOwnershipTrnDto createNewPointOwnershipTrnDto(StudentMstModel model) throws NaiException {
        // DTOの初期化
        PointOwnershipTrnDto pointOwnershipTrnDto = new PointOwnershipTrnDto();

        // 受講者IDの自動採番処理
        OrderNumbersMstLogic orderNumbersMstLogic = new OrderNumbersMstLogic();
        OrderNumberDto orderNumberDto = orderNumbersMstLogic.getOrderNumber(NaikaraTalkConstants.ORDER_NUMBERS_OW,
                DateUtil.getSysDate());
        pointOwnershipTrnDto.setOwnershipId(orderNumberDto.getOrderNumber());                                       // 所有ID

        pointOwnershipTrnDto.setCompensationFreeKbn(NaikaraTalkConstants.COMPENSATION_FREE_KBN_NO_ENROLLMENT);           // 有償無償区分
        pointOwnershipTrnDto.setStudentId(model.getStudentId_lbl());                                                     // 受講者ID
        pointOwnershipTrnDto.setEffectiveStrDt(DateUtil.getSysDate());                                                   // 有効開始日

        PointExpirationLogic logic = new PointExpirationLogic();
        // コード管理マスタのキャッシュ読み込み
        CodeManagMstCache cache = CodeManagMstCache.getInstance();
        LinkedHashMap<String, CodeManagMstDto> dateList = cache
                .getList(NaikaraTalkConstants.CODE_CATEGORY_JOIN_POINT_PERIOD);
        int expMonth = Integer.parseInt(dateList.get(NaikaraTalkConstants.JOIN_POINT_PERIOD).getManagerNm());
        // ポイントの有効期限算出
        PointExpirationDto dto = logic.pointExpiration(DateUtil.getSysDate(), expMonth);
        pointOwnershipTrnDto.setEffectiveEndDt(NaikaraStringUtil.delSlash(dto.getAge()));                                // 有効終了日


        pointOwnershipTrnDto.setFeeKbn(NaikaraTalkConstants.FEE_KBN_NORMAL);                                             // 通常月謝区分
        pointOwnershipTrnDto.setPurchaseYen(new BigDecimal(DATA_ZERO));                                                  // 購入金額(税込)
        LinkedHashMap<String, CodeManagMstDto> pointList = cache.getList(NaikaraTalkConstants.CODE_CATEGORY_JOIN_POINT);
        pointOwnershipTrnDto.setPurchaseFreePoint(new BigDecimal(pointList.get(NaikaraTalkConstants.JOIN_POIN)
                .getManagerNm()));                                                                                       // 購入／無償ポイント
        pointOwnershipTrnDto.setBalancePoint(new BigDecimal(pointList.get(NaikaraTalkConstants.JOIN_POIN)
                .getManagerNm()));                                                                                       // 残高ポイント
        pointOwnershipTrnDto.setPointAdditionReasonCd(null);                                                             // ポイント付加理由コード
        pointOwnershipTrnDto.setPurchaseDt(DateUtil.getSysDate());                                                       // 購入日
        pointOwnershipTrnDto.setPurchaseTm(DateUtil.getSysTime());                                                       // 購入時刻
        pointOwnershipTrnDto.setPointCd(POINT_INIT_CD);                                                                  // ポイントコード
        pointOwnershipTrnDto.setEndDt(null);                                                                             // 月謝停止日
        pointOwnershipTrnDto.setBeginningPurchaseDt(null);                                                               // 月謝用の実購入日
        pointOwnershipTrnDto.setBeginningPurchaseTm(null);                                                               // 月謝用の実購入時刻
        pointOwnershipTrnDto.setScreenSystemKbn(NaikaraTalkConstants.SCREEN_SYSTEM_KBN_SCREEN);                          // 画面システム作成区分
        pointOwnershipTrnDto.setProfileId(null);                                                                         // プロファイルID
        pointOwnershipTrnDto.setEndFlg(NaikaraTalkConstants.END_FLG_NO);                                                                            // 月謝停止フラグ
        pointOwnershipTrnDto.setRecordVerNo(DATA_ZERO);                                                                  // レコードバージョン番号
        pointOwnershipTrnDto.setInsertCd(((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString()))
                .getUserId());                                                                                           // 登録者コード
        pointOwnershipTrnDto.setUpdateCd(((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString()))
                .getUserId());                                                                                           // 更新者コード

        return pointOwnershipTrnDto;
    }

    /**
     * メール送信処理<br>
     * <br>
     * メール送信処理を行う。<br>
     * <br>
     * @param DirectMailSendSendModel<br>
     * @return なし <br>
     * @throws NaiException
     */
    public void sendMail(StudentMstModel model) throws NaiException {

        // 受講者 生年月日
        String strBirthDate = NaikaraStringUtil.converToYYYYMMDD(NaikaraStringUtil.unionString2(
                NaikaraStringUtil.unionString2(model.getBirthYyyy_txt(), model.getBirthMm_txt()),
                model.getBirthDd_txt()));
        // 受講者 成人フラグ
        AgeCalculateLogic ageCalculateLogic = new AgeCalculateLogic();
        int adultKbn = ageCalculateLogic.ageCalculate(strBirthDate, DateUtil.getSysDate()).getAdult();

        // メールパターンコード
        String mailPatternCd = NaikaraTalkConstants.MAIL_PATTERN_CODE_AGC;
        // 送信者
        String sendFrom = NaikaraTalkConstants.BRANK;
        // 送信者名
        String sendFromNm = NaikaraTalkConstants.BRANK;
        // 宛先IDリスト
        List<String> sendIdList = new ArrayList<String>();
        sendIdList.add(0, model.getStudentId_lbl());
        // 宛先アドレスリスト
        List<String> sendToList = new ArrayList<String>();
        sendToList.add(0, model.getMailAddress1_txt());
        // CC
        String cc = NaikaraTalkConstants.BRANK;
        // 未成年の場合
        if (adultKbn == AgeCalculateLogic.ADULT_KBN_MINORITY) {
            cc = model.getGuardianMailAddress1_txt();
        }
        // BCC
        String bcc = NaikaraTalkConstants.BRANK;
        // 件名リスト
        List<String> subjecCTitleList = new ArrayList<String>();
        // 本文リスト
        List<List<String>> mailTextList = new ArrayList<List<String>>();
        List<String> mailTextList0 = new ArrayList<String>();
        mailTextList0.add(0, NaikaraStringUtil.unionName(model.getFamilyJnm_txt(), model.getFirstJnm_txt()));
        mailTextList.add(0, mailTextList0);

        List<String> mailTextList1 = new ArrayList<String>();
        mailTextList1.add(0, NaikaraTalkConstants.BRANK);
        mailTextList.add(1, mailTextList1);

        List<String> mailTextList2 = new ArrayList<String>();
        mailTextList2.add(0, NaikaraTalkConstants.BRANK);
        mailTextList.add(2, mailTextList2);

        List<String> mailTextList3 = new ArrayList<String>();
        mailTextList3.add(0, NaikaraTalkConstants.BRANK);
        mailTextList.add(3, mailTextList3);

        List<String> mailTextList4 = new ArrayList<String>();
        mailTextList4.add(0, NaikaraTalkConstants.BRANK);
        mailTextList.add(4, mailTextList4);

        List<String> mailTextList5 = new ArrayList<String>();
        mailTextList5.add(0, NaikaraTalkConstants.BRANK);
        mailTextList.add(5, mailTextList5);

        List<String> mailTextList6 = new ArrayList<String>();
        mailTextList6.add(0, NaikaraTalkConstants.BRANK);
        mailTextList.add(6, mailTextList6);

        List<String> mailTextList7 = new ArrayList<String>();
        mailTextList7.add(0, model.getStudentId_lbl());
        mailTextList.add(7, mailTextList7);

        List<String> mailTextList8 = new ArrayList<String>();
        mailTextList8.add(0, model.getPassword_txt());
        mailTextList.add(8, mailTextList8);

        List<String> mailTextList9 = new ArrayList<String>();
        mailTextList9.add(0, NaikaraTalkConstants.BRANK);
        mailTextList.add(9, mailTextList9);

        List<String> mailTextList10 = new ArrayList<String>();
        mailTextList10.add(0, NaikaraTalkConstants.BRANK);
        mailTextList.add(10, mailTextList10);

        List<String> mailTextList11 = new ArrayList<String>();
        mailTextList11.add(0, NaikaraTalkConstants.BRANK);
        mailTextList.add(11, mailTextList11);

        List<String> mailTextList12 = new ArrayList<String>();

        // コード管理マスタのキャッシュ読み込み
        CodeManagMstCache cache = CodeManagMstCache.getInstance();
        LinkedHashMap<String, CodeManagMstDto> pointList = cache.getList(NaikaraTalkConstants.CODE_CATEGORY_JOIN_POINT);
        mailTextList12.add(0,
                NaikaraStringUtil.addZenkakuComma(pointList.get(NaikaraTalkConstants.JOIN_POIN).getManagerNm()));
        mailTextList.add(12, mailTextList12);

        List<String> mailTextList13 = new ArrayList<String>();
        mailTextList13.add(0, NaikaraTalkConstants.BRANK);
        mailTextList.add(13, mailTextList13);

        List<String> mailTextList14 = new ArrayList<String>();
        mailTextList14.add(0, NaikaraTalkConstants.BRANK);
        mailTextList.add(14, mailTextList14);

        List<String> mailTextList15 = new ArrayList<String>();
        mailTextList15.add(0, NaikaraTalkConstants.BRANK);
        mailTextList.add(15, mailTextList15);

        List<String> mailTextList16 = new ArrayList<String>();
        mailTextList16.add(0, NaikaraTalkConstants.BRANK);
        mailTextList.add(16, mailTextList16);

        List<String> mailTextList17 = new ArrayList<String>();
        mailTextList17.add(0, NaikaraTalkConstants.BRANK);
        mailTextList.add(17, mailTextList17);

        List<String> mailTextList18 = new ArrayList<String>();
        mailTextList18.add(0, NaikaraTalkConstants.BRANK);
        mailTextList.add(18, mailTextList18);

        List<String> mailTextList19 = new ArrayList<String>();
        mailTextList19.add(0, NaikaraTalkConstants.BRANK);
        mailTextList.add(19, mailTextList19);

        List<String> mailTextList20 = new ArrayList<String>();
        mailTextList20.add(0, NaikaraTalkConstants.BRANK);
        mailTextList.add(20, mailTextList20);

        List<String> mailTextList21 = new ArrayList<String>();
        mailTextList21.add(0, NaikaraTalkConstants.BRANK);
        mailTextList.add(21, mailTextList21);

        List<String> mailTextList22 = new ArrayList<String>();
        mailTextList22.add(0, NaikaraTalkConstants.BRANK);
        mailTextList.add(22, mailTextList22);

        List<String> mailTextList23 = new ArrayList<String>();
        mailTextList23.add(0, NaikaraTalkConstants.BRANK);
        mailTextList.add(23, mailTextList23);

        List<String> mailTextList24 = new ArrayList<String>();
        mailTextList24.add(0, NaikaraTalkConstants.BRANK);
        mailTextList.add(24, mailTextList24);

        List<String> mailTextList25 = new ArrayList<String>();
        mailTextList25.add(0, NaikaraTalkConstants.BRANK);
        mailTextList.add(25, mailTextList25);

        List<String> mailTextList26 = new ArrayList<String>();
        mailTextList26.add(0, NaikaraTalkConstants.BRANK);
        mailTextList.add(26, mailTextList26);

        List<String> mailTextList27 = new ArrayList<String>();
        mailTextList27.add(0, NaikaraTalkConstants.BRANK);
        mailTextList.add(27, mailTextList27);

        List<String> mailTextList28 = new ArrayList<String>();
        mailTextList28.add(0, NaikaraTalkConstants.BRANK);
        mailTextList.add(28, mailTextList28);

        List<String> mailTextList29 = new ArrayList<String>();
        mailTextList29.add(0, NaikaraTalkConstants.BRANK);
        mailTextList.add(29, mailTextList29);

        List<String> mailTextList30 = new ArrayList<String>();
        mailTextList30.add(0, NaikaraTalkConstants.BRANK);
        mailTextList.add(30, mailTextList30);

        List<String> mailTextList31 = new ArrayList<String>();
        mailTextList31.add(0, NaikaraTalkConstants.BRANK);
        mailTextList.add(31, mailTextList31);

        List<String> mailTextList32 = new ArrayList<String>();
        mailTextList32.add(0, NaikaraTalkConstants.BRANK);
        mailTextList.add(32, mailTextList32);

        List<String> mailTextList33 = new ArrayList<String>();
        mailTextList33.add(0, NaikaraTalkConstants.BRANK);
        mailTextList.add(33, mailTextList33);

        List<String> mailTextList34 = new ArrayList<String>();
        mailTextList34.add(0, NaikaraTalkConstants.BRANK);
        mailTextList.add(34, mailTextList34);

        // 添付
        String file = NaikaraTalkConstants.BRANK;
        String reservationNo = NaikaraTalkConstants.BRANK;

        Connection connMail = null;
        try {
            connMail = DbUtil.getConnection();

            SendMailBatch sendMailBatch = new SendMailBatch(connMail);
            sendMailBatch.sendMail(mailPatternCd, sendFrom, sendFromNm, sendIdList, sendToList, cc, bcc,
                    subjecCTitleList, mailTextList, file, reservationNo);
            connMail.commit();

        } catch (Exception se) {
            se.printStackTrace();
            throw new NaiException(se);
        } finally {
            try {
                connMail.close();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }

        // 保護者メール送信
        if (adultKbn == AgeCalculateLogic.ADULT_KBN_MINORITY
                && StringUtils.equals(NaikaraTalkConstants.PARENTAL_CONSENT_FLG_NO,
                        model.getConsentDocumentAcquisitionFlg_rdl())) {
            // 保護者:メールパターンコード
            String mailPatternCd_G = NaikaraTalkConstants.MAIL_PATTERN_CODE_AGS;
            // 保護者:送信者
            String sendFrom_G = NaikaraTalkConstants.BRANK;
            // 保護者:送信者名
            String sendFromNm_G = NaikaraTalkConstants.BRANK;
            // 保護者:宛先IDリスト
            List<String> sendIdList_G = new ArrayList<String>();
            sendIdList_G.add(0, NaikaraTalkConstants.BRANK);
            // 保護者:宛先アドレスリスト
            List<String> sendToList_G = new ArrayList<String>();
            // 保護者:CC
            String cc_G = NaikaraTalkConstants.BRANK;
            // 保護者:BCC
            String bcc_G = NaikaraTalkConstants.BRANK;
            // 保護者:件名リスト
            List<String> subjecCTitleList_G = new ArrayList<String>();
            // 保護者:本文リスト
            List<List<String>> mailTextList_G = new ArrayList<List<String>>();

            List<String> maiTextList_G0 = new ArrayList<String>();
            maiTextList_G0.add(0, NaikaraTalkConstants.BRANK);
            mailTextList_G.add(0, maiTextList_G0);

            List<String> maiTextList_G1 = new ArrayList<String>();
            maiTextList_G1.add(0, NaikaraTalkConstants.BRANK);
            mailTextList_G.add(1, maiTextList_G1);

            List<String> maiTextList_G2 = new ArrayList<String>();
            maiTextList_G2.add(0, NaikaraTalkConstants.BRANK);
            mailTextList_G.add(2, maiTextList_G2);

            List<String> maiTextList_G3 = new ArrayList<String>();
            maiTextList_G3.add(0, model.getStudentId_lbl());
            mailTextList_G.add(3, maiTextList_G3);

            List<String> maiTextList_G4 = new ArrayList<String>();
            maiTextList_G4.add(0, NaikaraStringUtil.unionName(model.getFamilyJnm_txt(), model.getFirstJnm_txt()));
            mailTextList_G.add(4, maiTextList_G4);

            List<String> maiTextList_G5 = new ArrayList<String>();
            maiTextList_G5.add(0, NaikaraStringUtil.converToYYYY_MM_DD(strBirthDate));
            mailTextList_G.add(5, maiTextList_G5);

            List<String> maiTextList_G6 = new ArrayList<String>();
            maiTextList_G6.add(0, NaikaraTalkConstants.BRANK);
            mailTextList_G.add(6, maiTextList_G6);

            List<String> maiTextList_G7 = new ArrayList<String>();
            maiTextList_G7.add(0,
                    NaikaraStringUtil.unionName(model.getGuardianFamilyJnm_txt(), model.getGuardianFirstJnm_txt()));
            mailTextList_G.add(7, maiTextList_G7);

            List<String> maiTextList_G8 = new ArrayList<String>();
            maiTextList_G8.add(0, model.getGuardianTel1_txt());
            mailTextList_G.add(8, maiTextList_G8);

            List<String> maiTextList_G9 = new ArrayList<String>();
            maiTextList_G9.add(0, model.getGuardianZipCd_txt());
            mailTextList_G.add(9, maiTextList_G9);

            // コード管理マスタのキャッシュ読み込み
            LinkedHashMap<String, CodeManagMstDto> stateList = cache.getList(NaikaraTalkConstants.CODE_CATEGORY_STATE);
            String strStateNm = stateList.get(model.getGuardianAddressPrefectureCd_sel()).getManagerNm();

            List<String> maiTextList_G10 = new ArrayList<String>();
            maiTextList_G10.add(0, strStateNm + model.getGuardianAddressCity_txt());
            mailTextList_G.add(10, maiTextList_G10);

            List<String> maiTextList_G11 = new ArrayList<String>();
            maiTextList_G11.add(0, model.getGuardianAddressOthers_txt());
            mailTextList_G.add(11, maiTextList_G11);

            List<String> maiTextList_G12 = new ArrayList<String>();
            maiTextList_G12.add(0, model.getGuardianMailAddress1_txt());
            mailTextList_G.add(12, maiTextList_G12);

            // 添付
            String file_G = NaikaraTalkConstants.BRANK;

            String reservationNo_G = NaikaraTalkConstants.BRANK;

            Connection conn_G = null;
            try {
                conn_G = DbUtil.getConnection();

                SendMailBatch sendMailBatch = new SendMailBatch(conn_G);
                sendMailBatch.sendMail(mailPatternCd_G, sendFrom_G, sendFromNm_G, sendIdList_G, sendToList_G, cc_G,
                        bcc_G, subjecCTitleList_G, mailTextList_G, file_G, reservationNo_G);
                conn_G.commit();

            } catch (Exception se) {
                se.printStackTrace();
                throw new NaiException(se);
            } finally {
                try {
                    conn_G.close();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        }

    }
}

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>マスタ保守。<br>
 * <b>クラス名称　　　:</b>リストソートServiceクラス。<br>
 * <b>クラス概要　　　:</b>リストソートService。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/07/30 TECS 新規作成。
 */
class ComparatorPointOwnershipTrnDto implements Comparator<PointOwnershipTrnDto> {

    @Override
    public int compare(PointOwnershipTrnDto o1, PointOwnershipTrnDto o2) {

        int flag = o1.getEndDt().compareTo(o2.getEndDt());

        if (flag == 0) {
            // 比較No．
            return o1.getOwnershipId().compareTo(o2.getOwnershipId());
        } else {
            return flag;
        }
    }

}