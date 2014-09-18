package com.naikara_talk.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dbutil.DbUtil;
import com.naikara_talk.dto.SmaAccHisTSmaAccHisDetTStuMDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.logic.SchoolMailListLogic;
import com.naikara_talk.model.SchoolMailListModel;
import com.naikara_talk.util.DateUtil;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>マスタ保守<br>
 * <b>クラス名称       :</b>スクールのメール送信・受信履歴照会<br>
 * <b>クラス概要       :</b>スクールのメール送信・受信履歴照会検索処理Service<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/01 TECS 新規作成
 */
public class SchoolMailListSearchService implements ActionService {

    /** 一覧 ZERO件 */
    private static final int LIST_ZERO_CNT = 0;

    /** 一覧の表示上限 */
    private static final int LIST_MAX_CNT = 100;

    /** 検索前チェック：データ件数ZERO件 */
    public static final int ERR_MAIL_SEND_DT = 1;

    /** 検索前チェック：データ件数ZERO件 */
    public static final int ERR_ZERO_DATA = 2;

    /** 検索前チェック：データ件数上限以上の件数 */
    public static final int ERR_MAXOVER_DATA = 3;

    /**
     * 検索前チェック処理<br>
     * <br>
     * 検索前チェック処理<br>
     * <br>
     * @param SchoolMailListModel<br>
     * @return int チェック結果<br>
     * @exception Exception
     */
    public int checkPreSelect(SchoolMailListModel model) throws Exception {

        // 日付の整合性チェック (送信日時)
        if (DateUtil.dateCompare2(NaikaraStringUtil.converToYYYYMMDD(model.getMailSendDtFr()),
                NaikaraStringUtil.converToYYYYMMDD(model.getMailSendDtTo()))) {
            return ERR_MAIL_SEND_DT;
        }

        // 入力チェック - DBアクセスありチェック
        int count = getRowCount(model);
        if (LIST_ZERO_CNT == count) {
            return ERR_ZERO_DATA;
        } else if (LIST_MAX_CNT < count) {
            return ERR_MAXOVER_DATA;
        }
        // 正常
        return SchoolMailListModel.CHECK_OK;

    }

    /**
     * 検索データ件数取得。<br>
     * <br>
     * 検索データ件数取得。<br>
     * <br>
     * @param SchoolMailListModel 画面のパラメータ<br>
     * @return int 検索データ件数<br>
     * @exception NaiException
     */
    public int getRowCount(SchoolMailListModel model) throws NaiException {

        Connection conn = null;
        try {
            conn = DbUtil.getConnection();
            // 初期化処理
            SchoolMailListLogic schoolMailListLogic = new SchoolMailListLogic(conn);

            // DTOの初期化
            SmaAccHisTSmaAccHisDetTStuMDto dto = new SmaAccHisTSmaAccHisDetTStuMDto();

            // Model値をDTOにセット
            dto = this.modelToDto(model);

            // データの取得件数＆リターン
            return schoolMailListLogic.getRowCount(dto);
        } catch (SQLException e) {
            throw new NaiException(e);
        } catch (Exception e) {
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
     * 画面データの取得。<br>
     * <br>
     * 画面データの取得。<br>
     * <br>
     * @param SchoolMailListModel 画面のパラメータ<br>
     * @return List<SmaAccHisTSmaAccHisDetTStuMDto><br>
     * @exception NaiException
     */
    public List<SmaAccHisTSmaAccHisDetTStuMDto> selectList(SchoolMailListModel model) throws NaiException {

        Connection conn = null;
        try {
            conn = DbUtil.getConnection();
            // 初期化処理
            SchoolMailListLogic schoolMailListLogic = new SchoolMailListLogic(conn);

            // DTOの初期化
            SmaAccHisTSmaAccHisDetTStuMDto dto = new SmaAccHisTSmaAccHisDetTStuMDto();

            // Model値をDTOにセット
            dto = this.modelToDto(model);

            // データの取得
            List<SmaAccHisTSmaAccHisDetTStuMDto> getDtoList = schoolMailListLogic.selectList(dto);

            // データが存在する場合
            if (getDtoList.size() > 0 ) {
                SmaAccHisTSmaAccHisDetTStuMDto retDto = new SmaAccHisTSmaAccHisDetTStuMDto();
                retDto = getDtoList.get(0);
                if (retDto.getReturnCode() != NaikaraTalkConstants.RETURN_CD_DATA_NO) {
                    // 編集処理
                    getDtoList = editList(getDtoList);
                }
            }

            return getDtoList;

        } catch (SQLException e) {
            throw new NaiException(e);
        } catch (Exception e) {
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
     * Model値をDTOにセット。<br>
     * <br>
     * Model値をDTOにセット。<br>
     * <br>
     * @param SchoolMailListModel 画面のパラメータ<br>
     * @return SmaAccHisTSmaAccHisDetTStuMDto<br>
     * @exception なし
     */
    private SmaAccHisTSmaAccHisDetTStuMDto modelToDto(SchoolMailListModel model) {

        // DTOの初期化
        SmaAccHisTSmaAccHisDetTStuMDto dto = new SmaAccHisTSmaAccHisDetTStuMDto();

        // メール送信日 (From)
        dto.setMailSendDtFr(model.getMailSendDtFr());
        // メール送信日(To)
        dto.setMailSendDtTo(model.getMailSendDtTo());
        // メールパターン
        dto.setMailPatternCd(model.getMailPatternCd());
        // 顧客区分
        dto.setCustomerKbn(model.getCustomerKbn());
        // 受講者ID
        dto.setStudentId(model.getStudentId());
        // 受講者名(ニックネーム)
        dto.setNickNm(model.getNickNm());
        // 受講者名(フリガナ)
        dto.setStudentKnm(model.getStudentKnm());
        // 組織名
        dto.setOrganizationNm(model.getOrganizationNm());

        return dto;

    }

    /**
     * 画面一覧部分の編集<br>
     * <br>
     * 画面一覧部分の編集<br>
     * <br>
     * @param List<SmaAccHisTSmaAccHisDetTStuMDto><br>
     * @return List<SmaAccHisTSmaAccHisDetTStuMDto><br>
     * @exception なし
     */
    private List<SmaAccHisTSmaAccHisDetTStuMDto> editList(List<SmaAccHisTSmaAccHisDetTStuMDto> list) {

        for (SmaAccHisTSmaAccHisDetTStuMDto dto : list) {

            SimpleDateFormat sdf = new SimpleDateFormat("yy/MM/dd");
            dto.setSendDttmYMD(sdf.format(dto.getSendDttm()));

            // スクールメール・アカウント変更履歴テーブル．メールパターンコード = ”1001” の場合
            if (StringUtils.equals(NaikaraTalkConstants.MAIL_PATTERN_ACCOUNT_UPD, dto.getMailPatternCd())) {

                // 詳細
                StringBuffer sb = new StringBuffer();
                // 名前(姓)
                if (!StringUtils.equals(dto.getAFamilyJnm(), dto.getBFamilyJnm())) {
                    sb.append(NaikaraTalkConstants.COMMA_BRANK).append(NaikaraTalkConstants.JNM_FAMILY_JNM)
                            .append(NaikaraTalkConstants.BRACKET_START).append(dto.getBFamilyJnm())
                            .append(NaikaraTalkConstants.RIGHT).append(dto.getAFamilyJnm())
                            .append(NaikaraTalkConstants.BRACKET_END);
                }
                // 名前(名)
                if (!StringUtils.equals(dto.getAFirstJnm(), dto.getBFirstJnm())) {
                    sb.append(NaikaraTalkConstants.COMMA_BRANK).append(NaikaraTalkConstants.JNM_FIRST_JNM)
                            .append(NaikaraTalkConstants.BRACKET_START).append(dto.getBFirstJnm())
                            .append(NaikaraTalkConstants.RIGHT).append(dto.getAFirstJnm())
                            .append(NaikaraTalkConstants.BRACKET_END);
                }
                // フリガナ(姓)
                if (!StringUtils.equals(dto.getAFamilyKnm(), dto.getBFamilyKnm())) {
                    sb.append(NaikaraTalkConstants.COMMA_BRANK).append(NaikaraTalkConstants.JNM_FAMILY_KNM)
                            .append(NaikaraTalkConstants.BRACKET_START).append(dto.getBFamilyKnm())
                            .append(NaikaraTalkConstants.RIGHT).append(dto.getAFamilyKnm())
                            .append(NaikaraTalkConstants.BRACKET_END);
                }
                // フリガナ(名)
                if (!StringUtils.equals(dto.getAFirstKnm(), dto.getBFirstKnm())) {
                    sb.append(NaikaraTalkConstants.COMMA_BRANK).append(NaikaraTalkConstants.JNM_FIRST_KNM)
                            .append(NaikaraTalkConstants.BRACKET_START).append(dto.getBFirstKnm())
                            .append(NaikaraTalkConstants.RIGHT).append(dto.getAFirstKnm())
                            .append(NaikaraTalkConstants.BRACKET_END);
                }
                // ローマ字(姓)
                if (!StringUtils.equals(dto.getAFamilyRomaji(), dto.getBFamilyRomaji())) {
                    sb.append(NaikaraTalkConstants.COMMA_BRANK).append(NaikaraTalkConstants.JNM_FAMILY_ROMAJI)
                            .append(NaikaraTalkConstants.BRACKET_START).append(dto.getBFamilyRomaji())
                            .append(NaikaraTalkConstants.RIGHT).append(dto.getAFamilyRomaji())
                            .append(NaikaraTalkConstants.BRACKET_END);
                }
                // ローマ字(名)
                if (!StringUtils.equals(dto.getAFirstRomaji(), dto.getBFirstRomaji())) {
                    sb.append(NaikaraTalkConstants.COMMA_BRANK).append(NaikaraTalkConstants.JNM_FIRST_ROMAJI)
                            .append(NaikaraTalkConstants.BRACKET_START).append(dto.getBFirstRomaji())
                            .append(NaikaraTalkConstants.RIGHT).append(dto.getAFirstRomaji())
                            .append(NaikaraTalkConstants.BRACKET_END);
                }
                // ニックネーム
                if (!StringUtils.equals(dto.getANickNm(), dto.getBNickNm())) {
                    sb.append(NaikaraTalkConstants.COMMA_BRANK).append(NaikaraTalkConstants.JNM_NICK_NM)
                            .append(NaikaraTalkConstants.BRACKET_START).append(dto.getBNickNm())
                            .append(NaikaraTalkConstants.RIGHT).append(dto.getANickNm())
                            .append(NaikaraTalkConstants.BRACKET_END);
                }
                // パスワード
                if (!StringUtils.equals(dto.getAPassword(), dto.getBPassword())) {
                    sb.append(NaikaraTalkConstants.COMMA_BRANK).append(NaikaraTalkConstants.JNM_PASSWORD)
                            .append(NaikaraTalkConstants.BRACKET_START).append(dto.getBPassword())
                            .append(NaikaraTalkConstants.RIGHT).append(dto.getAPassword())
                            .append(NaikaraTalkConstants.BRACKET_END);
                }
                // 電話番号1
                if (!StringUtils.equals(dto.getATel1(), dto.getBTel1())) {
                    sb.append(NaikaraTalkConstants.COMMA_BRANK).append(NaikaraTalkConstants.JNM_TEL1)
                            .append(NaikaraTalkConstants.BRACKET_START).append(dto.getBTel1())
                            .append(NaikaraTalkConstants.RIGHT).append(dto.getATel1())
                            .append(NaikaraTalkConstants.BRACKET_END);
                }
                // 電話番号2
                if (!StringUtils.equals(dto.getATel2(), dto.getBTel2())) {
                    sb.append(NaikaraTalkConstants.COMMA_BRANK).append(NaikaraTalkConstants.JNM_TEL2)
                            .append(NaikaraTalkConstants.BRACKET_START).append(dto.getBTel2())
                            .append(NaikaraTalkConstants.RIGHT).append(dto.getATel2())
                            .append(NaikaraTalkConstants.BRACKET_END);
                }
                // 生年月日
                if (!StringUtils.equals(dto.getABirthDt(), dto.getBBirthDt())) {
                    sb.append(NaikaraTalkConstants.COMMA_BRANK).append(NaikaraTalkConstants.JNM_BIRTH_DT)
                            .append(NaikaraTalkConstants.BRACKET_START).append(dto.getBBirthDt())
                            .append(NaikaraTalkConstants.RIGHT).append(dto.getABirthDt())
                            .append(NaikaraTalkConstants.BRACKET_END);
                }
                // 郵便番号
                if (!StringUtils.equals(dto.getAZipCd(), dto.getBZipCd())) {
                    sb.append(NaikaraTalkConstants.COMMA_BRANK).append(NaikaraTalkConstants.JNM_ZIP_CD)
                            .append(NaikaraTalkConstants.BRACKET_START).append(dto.getBZipCd())
                            .append(NaikaraTalkConstants.RIGHT).append(dto.getAZipCd())
                            .append(NaikaraTalkConstants.BRACKET_END);
                }
                // 住所(地域)名
                if (!StringUtils.equals(dto.getAAddressAreaJnm(), dto.getBAddressAreaJnm())) {
                    sb.append(NaikaraTalkConstants.COMMA_BRANK).append(NaikaraTalkConstants.JNM_ADDRESS_AREA)
                            .append(NaikaraTalkConstants.BRACKET_START).append(dto.getBAddressAreaJnm())
                            .append(NaikaraTalkConstants.RIGHT).append(dto.getAAddressAreaJnm())
                            .append(NaikaraTalkConstants.BRACKET_END);
                }
                // 住所(都道府県)名
                if (!StringUtils.equals(dto.getAAddressPrefectureJnm(), dto.getBAddressPrefectureJnm())) {
                    sb.append(NaikaraTalkConstants.COMMA_BRANK).append(NaikaraTalkConstants.JNM_ADDRESS_PREFECTURE)
                            .append(NaikaraTalkConstants.BRACKET_START).append(dto.getBAddressPrefectureJnm())
                            .append(NaikaraTalkConstants.RIGHT).append(dto.getAAddressPrefectureJnm())
                            .append(NaikaraTalkConstants.BRACKET_END);
                }
                // 住所(市区町村 等)
                if (!StringUtils.equals(dto.getAAddressCity(), dto.getBAddressCity())) {
                    sb.append(NaikaraTalkConstants.COMMA_BRANK).append(NaikaraTalkConstants.JNM_ADDRESS_CITY)
                            .append(NaikaraTalkConstants.BRACKET_START).append(dto.getBAddressCity())
                            .append(NaikaraTalkConstants.RIGHT).append(dto.getAAddressCity())
                            .append(NaikaraTalkConstants.BRACKET_END);
                }
                // 住所(ﾋﾞﾙ、ﾏﾝｼｮﾝ名 等)
                if (!StringUtils.equals(dto.getAAddressOthers(), dto.getBAddressOthers())) {
                    sb.append(NaikaraTalkConstants.COMMA_BRANK).append(NaikaraTalkConstants.JNM_ADDRESS_OTHERS)
                            .append(NaikaraTalkConstants.BRACKET_START).append(dto.getBAddressOthers())
                            .append(NaikaraTalkConstants.RIGHT).append(dto.getAAddressOthers())
                            .append(NaikaraTalkConstants.BRACKET_END);
                }
                // 性別名
                if (!StringUtils.equals(dto.getAGenderJnm(), dto.getBGenderJnm())) {
                    sb.append(NaikaraTalkConstants.COMMA_BRANK).append(NaikaraTalkConstants.JNM_GENDER)
                            .append(NaikaraTalkConstants.BRACKET_START).append(dto.getBGenderJnm())
                            .append(NaikaraTalkConstants.RIGHT).append(dto.getAGenderJnm())
                            .append(NaikaraTalkConstants.BRACKET_END);
                }
                // メールアドレス1
                if (!StringUtils.equals(dto.getAMailAddress1(), dto.getBMailAddress1())) {
                    sb.append(NaikaraTalkConstants.COMMA_BRANK).append(NaikaraTalkConstants.JNM_MAIL_ADDRESS1)
                            .append(NaikaraTalkConstants.BRACKET_START).append(dto.getBMailAddress1())
                            .append(NaikaraTalkConstants.RIGHT).append(dto.getAMailAddress1())
                            .append(NaikaraTalkConstants.BRACKET_END);
                }
                // メールアドレス2
                if (!StringUtils.equals(dto.getAMailAddress2(), dto.getBMailAddress2())) {
                    sb.append(NaikaraTalkConstants.COMMA_BRANK).append(NaikaraTalkConstants.JNM_MAIL_ADDRESS2)
                            .append(NaikaraTalkConstants.BRACKET_START).append(dto.getBMailAddress2())
                            .append(NaikaraTalkConstants.RIGHT).append(dto.getAMailAddress2())
                            .append(NaikaraTalkConstants.BRACKET_END);
                }
                // メールアドレス3
                if (!StringUtils.equals(dto.getAMailAddress3(), dto.getBMailAddress3())) {
                    sb.append(NaikaraTalkConstants.COMMA_BRANK).append(NaikaraTalkConstants.JNM_MAIL_ADDRESS3)
                            .append(NaikaraTalkConstants.BRACKET_START).append(dto.getBMailAddress3())
                            .append(NaikaraTalkConstants.RIGHT).append(dto.getAMailAddress3())
                            .append(NaikaraTalkConstants.BRACKET_END);
                }
                // 保護者：名前(姓)
                if (!StringUtils.equals(dto.getAGuardianFamilyJnm(), dto.getBGuardianFamilyJnm())) {
                    sb.append(NaikaraTalkConstants.COMMA_BRANK).append(NaikaraTalkConstants.JNM_GUARDIAN_FAMILY_JNM)
                            .append(NaikaraTalkConstants.BRACKET_START).append(dto.getBGuardianFamilyJnm())
                            .append(NaikaraTalkConstants.RIGHT).append(dto.getAGuardianFamilyJnm())
                            .append(NaikaraTalkConstants.BRACKET_END);
                }
                // 保護者：名前(名）
                if (!StringUtils.equals(dto.getAGuardianFirstJnm(), dto.getBGuardianFirstJnm())) {
                    sb.append(NaikaraTalkConstants.COMMA_BRANK).append(NaikaraTalkConstants.JNM_GUARDIAN_FIRST_JNM)
                            .append(NaikaraTalkConstants.BRACKET_START).append(dto.getBGuardianFirstJnm())
                            .append(NaikaraTalkConstants.RIGHT).append(dto.getAGuardianFirstJnm())
                            .append(NaikaraTalkConstants.BRACKET_END);
                }
                // 保護者：フリガナ(姓)
                if (!StringUtils.equals(dto.getAGuardianFamilyKnm(), dto.getBGuardianFamilyKnm())) {
                    sb.append(NaikaraTalkConstants.COMMA_BRANK).append(NaikaraTalkConstants.JNM_GUARDIAN_FAMILY_KNM)
                            .append(NaikaraTalkConstants.BRACKET_START).append(dto.getBGuardianFamilyKnm())
                            .append(NaikaraTalkConstants.RIGHT).append(dto.getAGuardianFamilyKnm())
                            .append(NaikaraTalkConstants.BRACKET_END);
                }
                // 保護者：フリガナ(名）
                if (!StringUtils.equals(dto.getAGuardianFirstKnm(), dto.getBGuardianFirstKnm())) {
                    sb.append(NaikaraTalkConstants.COMMA_BRANK).append(NaikaraTalkConstants.JNM_GUARDIAN_FIRST_KNM)
                            .append(NaikaraTalkConstants.BRACKET_START).append(dto.getBGuardianFirstKnm())
                            .append(NaikaraTalkConstants.RIGHT).append(dto.getAGuardianFirstKnm())
                            .append(NaikaraTalkConstants.BRACKET_END);
                }
                // あなたとの続柄
                if (!StringUtils.equals(dto.getAGuardianFamilyRelationship(), dto.getBGuardianFamilyRelationship())) {
                    sb.append(NaikaraTalkConstants.COMMA_BRANK)
                            .append(NaikaraTalkConstants.JNM_GUARDIAN_FAMILY_RELATIONSHIP)
                            .append(NaikaraTalkConstants.BRACKET_START).append(dto.getBGuardianFamilyRelationship())
                            .append(NaikaraTalkConstants.RIGHT).append(dto.getAGuardianFamilyRelationship())
                            .append(NaikaraTalkConstants.BRACKET_END);
                }
                // 保護者：電話番号1
                if (!StringUtils.equals(dto.getAGuardianTel1(), dto.getBGuardianTel1())) {
                    sb.append(NaikaraTalkConstants.COMMA_BRANK).append(NaikaraTalkConstants.JNM_GUARDIAN_TEL1)
                            .append(NaikaraTalkConstants.BRACKET_START).append(dto.getBGuardianTel1())
                            .append(NaikaraTalkConstants.RIGHT).append(dto.getAGuardianTel1())
                            .append(NaikaraTalkConstants.BRACKET_END);
                }
                // 保護者：電話番号2
                if (!StringUtils.equals(dto.getAGuardianTel2(), dto.getBGuardianTel2())) {
                    sb.append(NaikaraTalkConstants.COMMA_BRANK).append(NaikaraTalkConstants.JNM_GUARDIAN_TEL2)
                            .append(NaikaraTalkConstants.BRACKET_START).append(dto.getBGuardianTel2())
                            .append(NaikaraTalkConstants.RIGHT).append(dto.getAGuardianTel2())
                            .append(NaikaraTalkConstants.BRACKET_END);
                }
                // 保護者：生年月日
                if (!StringUtils.equals(dto.getAGuardianBirthDt(), dto.getBGuardianBirthDt())) {
                    sb.append(NaikaraTalkConstants.COMMA_BRANK).append(NaikaraTalkConstants.JNM_GUARDIAN_BIRTH_DT)
                            .append(NaikaraTalkConstants.BRACKET_START).append(dto.getBGuardianBirthDt())
                            .append(NaikaraTalkConstants.RIGHT).append(dto.getAGuardianBirthDt())
                            .append(NaikaraTalkConstants.BRACKET_END);
                }
                // 保護者：郵便番号
                if (!StringUtils.equals(dto.getAGuardianZipCd(), dto.getBGuardianZipCd())) {
                    sb.append(NaikaraTalkConstants.COMMA_BRANK).append(NaikaraTalkConstants.JNM_GUARDIAN_ZIP_CD)
                            .append(NaikaraTalkConstants.BRACKET_START).append(dto.getBGuardianZipCd())
                            .append(NaikaraTalkConstants.RIGHT).append(dto.getAGuardianZipCd())
                            .append(NaikaraTalkConstants.BRACKET_END);
                }
                // 保護者：住所(地域)名
                if (!StringUtils.equals(dto.getAGuardianAddressAreaJnm(), dto.getBGuardianAddressAreaJnm())) {
                    sb.append(NaikaraTalkConstants.COMMA_BRANK).append(NaikaraTalkConstants.JNM_GUARDIAN_ADDRESS_AREA)
                            .append(NaikaraTalkConstants.BRACKET_START).append(dto.getBGuardianAddressAreaJnm())
                            .append(NaikaraTalkConstants.RIGHT).append(dto.getAGuardianAddressAreaJnm())
                            .append(NaikaraTalkConstants.BRACKET_END);
                }
                // 保護者：住所(都道府県)名
                if (!StringUtils.equals(dto.getAGuardianAddressPrefectureJnm(), dto.getBGuardianAddressPrefectureJnm())) {
                    sb.append(NaikaraTalkConstants.COMMA_BRANK)
                            .append(NaikaraTalkConstants.JNM_GUARDIAN_ADDRESS_PREFECTURE)
                            .append(NaikaraTalkConstants.BRACKET_START).append(dto.getBGuardianAddressPrefectureJnm())
                            .append(NaikaraTalkConstants.RIGHT).append(dto.getAGuardianAddressPrefectureJnm())
                            .append(NaikaraTalkConstants.BRACKET_END);
                }
                // 保護者：住所(市区町村 等)
                if (!StringUtils.equals(dto.getAGuardianAddressCity(), dto.getBGuardianAddressCity())) {
                    sb.append(NaikaraTalkConstants.COMMA_BRANK).append(NaikaraTalkConstants.JNM_GUARDIAN_ADDRESS_CITY)
                            .append(NaikaraTalkConstants.BRACKET_START).append(dto.getBGuardianAddressCity())
                            .append(NaikaraTalkConstants.RIGHT).append(dto.getAGuardianAddressCity())
                            .append(NaikaraTalkConstants.BRACKET_END);
                }
                // 保護者：住所(ﾋﾞﾙ、ﾏﾝｼｮﾝ名 等)
                if (!StringUtils.equals(dto.getAGuardianAddressOthers(), dto.getBGuardianAddressOthers())) {
                    sb.append(NaikaraTalkConstants.COMMA_BRANK)
                            .append(NaikaraTalkConstants.JNM_GUARDIAN_ADDRESS_OTHERS)
                            .append(NaikaraTalkConstants.BRACKET_START).append(dto.getBGuardianAddressOthers())
                            .append(NaikaraTalkConstants.RIGHT).append(dto.getAGuardianAddressOthers())
                            .append(NaikaraTalkConstants.BRACKET_END);
                }
                // 保護者：性別名
                if (!StringUtils.equals(dto.getAGuardianGenderJnm(), dto.getBGuardianGenderJnm())) {
                    sb.append(NaikaraTalkConstants.COMMA_BRANK).append(NaikaraTalkConstants.JNM_GUARDIAN_GENDER_KBN)
                            .append(NaikaraTalkConstants.BRACKET_START).append(dto.getBGuardianGenderJnm())
                            .append(NaikaraTalkConstants.RIGHT).append(dto.getAGuardianGenderJnm())
                            .append(NaikaraTalkConstants.BRACKET_END);
                }
                // 保護者：メールアドレス1
                if (!StringUtils.equals(dto.getAGuardianMailAddress1(), dto.getBGuardianMailAddress1())) {
                    sb.append(NaikaraTalkConstants.COMMA_BRANK).append(NaikaraTalkConstants.JNM_GUARDIAN_MAIL_ADDRESS1)
                            .append(NaikaraTalkConstants.BRACKET_START).append(dto.getBGuardianMailAddress1())
                            .append(NaikaraTalkConstants.RIGHT).append(dto.getAGuardianMailAddress1())
                            .append(NaikaraTalkConstants.BRACKET_END);
                }
                // 保護者：メールアドレス2
                if (!StringUtils.equals(dto.getAGuardianMailAddress2(), dto.getBGuardianMailAddress2())) {
                    sb.append(NaikaraTalkConstants.COMMA_BRANK).append(NaikaraTalkConstants.JNM_GUARDIAN_MAIL_ADDRESS2)
                            .append(NaikaraTalkConstants.BRACKET_START).append(dto.getBGuardianMailAddress2())
                            .append(NaikaraTalkConstants.RIGHT).append(dto.getAGuardianMailAddress2())
                            .append(NaikaraTalkConstants.BRACKET_END);
                }
                // 保護者：メールアドレス3
                if (!StringUtils.equals(dto.getAGuardianMailAddress3(), dto.getBGuardianMailAddress3())) {
                    sb.append(NaikaraTalkConstants.COMMA_BRANK).append(NaikaraTalkConstants.JNM_GUARDIAN_MAIL_ADDRESS3)
                            .append(NaikaraTalkConstants.BRACKET_START).append(dto.getBGuardianMailAddress3())
                            .append(NaikaraTalkConstants.RIGHT).append(dto.getAGuardianMailAddress3())
                            .append(NaikaraTalkConstants.BRACKET_END);
                }
                // 詳細
                if (sb.length() != 0) {
                    dto.setDetail(sb.substring(2));
                }
            }
        }
        return list;
    }
}
