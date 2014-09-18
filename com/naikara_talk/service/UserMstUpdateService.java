package com.naikara_talk.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dbutil.DbUtil;
import com.naikara_talk.dto.UserMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.logic.OrderNumbersMstLogic;
import com.naikara_talk.logic.PasswordChkLogic;
import com.naikara_talk.logic.UserMstLogic;
import com.naikara_talk.model.UserMstModel;
import com.naikara_talk.sessiondata.SessionUser;
import com.naikara_talk.util.DateUtil;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>マスタ保守。<br>
 * <b>クラス名称　　　:</b>利用者マスタメンテナンス登録更新Serviceクラス。<br>
 * <b>クラス概要　　　:</b>利用者マスタメンテナンス登録更新Service。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/07/04 TECS 新規作成。
 */
public class UserMstUpdateService implements ActionService {

    /** ｢種別(チェック用)｣の値 = ｢種別｣チェックエラー */
    public static final int NG_TEACHER_1 = 1;

    /** ｢種別(チェック用)｣ = ”T” (講師) 且つ ｢種別｣ ≠ ”T” (講師)チェックエラー */
    public static final int NG_TEACHER_2 = 2;

    /** ｢種別(チェック用)｣ ≠ ”T” (講師) 且つ ｢種別｣ ＝ ”T” (講師)チェックエラー */
    public static final int NG_TEACHER_3 = 3;

    /** 過去日チェックエラー */
    public static final int NG_DATE_1 = 4;

    /** 日付の整合性チェック (日付)チェックエラー */
    public static final int NG_DATE_2 = 5;

    /** パスワードの複雑性のチェックエラー */
    public static final int NG_PASSWORD = 6;

    /** エラーなし */
    public static final int OK_CHECK = 7;

    /** 住所(都道府県)コンボボックスの必須入力チェックエラー */
    public static final int NG_ADDRESS = 8;

    /** 生年月日：月チェックエラー */
    public static final int NG_MONTH = 9;

    /** 生年月日：日チェックエラー */
    public static final int NG_DATE = 10;

    /**
     * 登録、更新のチェック<br>
     * <br>
     * 登録、更新のチェックを行う<br>
     * <br>
     * @param model 画面パラメータ<br>
     * @return int 結果フラグ<br>
     * @exception Exception
     */
    public int errorCheck(UserMstModel model) throws Exception {

        // 生年月日String(yyyyMMdd)
        String birthStr = NaikaraStringUtil.converToYYYYMMDD(NaikaraStringUtil.unionString2(
                NaikaraStringUtil.unionString2(model.getBirthdayYy_txt(), model.getBirthdayMm_txt()),
                model.getBirthdayDd_txt()));

        // 生年月日Date(yyyyMMdd)
        Date birthDate = new SimpleDateFormat(DateUtil.DATE_FORMAT_yyyyMMdd).parse(birthStr);

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

                return NG_MONTH;
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

                return NG_DATE;
            }
        }

        // 住所(都道府県)コンボボックスの必須入力チェック
        if (StringUtils.equals(model.getAddress2_sel(), NaikaraTalkConstants.CHOICE_ALL_ZERO)) {

            return NG_ADDRESS;
        }

        // 新規を除く
        if (!StringUtils.equals(UserMstModel.PROS_KBN_ADD, model.getProcessKbn_rdl())) {

            // 種別の変更チェック
            if (!StringUtils.equals(model.getUserKbn_chk(), model.getUserKbn_rdl())) {

                // ｢種別(チェック用)｣ = ”T” (講師) 且つ ｢種別｣ ≠ ”T” (講師) の場合
                if (StringUtils.equals(model.getUserKbn_chk(), NaikaraTalkConstants.AUTHORITY_T)
                        && !StringUtils.equals(model.getUserKbn_rdl(), NaikaraTalkConstants.AUTHORITY_T)) {

                    return NG_TEACHER_2;
                }

                // ｢種別(チェック用)｣ ≠ ”T” (講師) 且つ ｢種別｣ ＝ ”T” (講師) の場合
                if (!StringUtils.equals(model.getUserKbn_chk(), NaikaraTalkConstants.AUTHORITY_T)
                        && StringUtils.equals(model.getUserKbn_rdl(), NaikaraTalkConstants.AUTHORITY_T)) {

                    return NG_TEACHER_3;
                }
            }
        }

        StringBuffer sb = new StringBuffer();
        sb.append(model.getBirthdayYy_txt());
        if (model.getBirthdayMm_txt().length() == 1) {
            sb.append("0");
        }
        sb.append(model.getBirthdayMm_txt());
        if (model.getBirthdayDd_txt().length() == 1) {
            sb.append("0");
        }
        sb.append(model.getBirthdayDd_txt());

        // 過去日チェック
        if (DateUtil.dateCompare2(sb.toString(), DateUtil.getSysDate())) {

            return NG_DATE_1;
        }

        // 日付の整合性チェック (日付)
        if (!StringUtils.isEmpty(model.getUtilizationStart_txt())
                && !StringUtils.isEmpty(model.getUtilizationEnd_txt())) {

            if (DateUtil.dateCompare1(model.getUtilizationStart_txt(), model.getUtilizationEnd_txt())) {

                return NG_DATE_2;
            }
        }

        // ロジックの初期化
        PasswordChkLogic logic = new PasswordChkLogic();

        // パスワード複雑性をチェック
        int ret = logic.passwordCheck(model.getPassword_txt());

        // ダメの場合
        if (ret != 0) {

            return NG_PASSWORD;
        }

        return OK_CHECK;
    }

    /**
     * 遷移チェック<br>
     * <br>
     * 遷移チェックを行う<br>
     * <br>
     * @param model 画面パラメータ<br>
     * @return boolean 結果フラグ<br>
     * @exception なし
     */
    public boolean redirectCheck(UserMstModel model) {

        // ユーザIDを取得
        String userId = ((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString())).getUserId();

        // 遷移チェック
        if (StringUtils.equals(model.getUserId_lbl(), userId)
                && StringUtils.equals(model.getUserKbn_rdl(), NaikaraTalkConstants.AUTHORITY_S)) {
            ((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString()))
                    .setRole(model.getUserKbn_rdl());

            return false;
        }

        return true;
    }

    /**
     * 登録処理<br>
     * <br>
     * 登録処理を行う<br>
     * <br>
     * @param model 画面パラメータ<br>
     * @return int<br>
     * @exception Exception
     */
    public int insert(UserMstModel model) throws Exception {

        Connection conn = null;
        try {
            conn = DbUtil.getConnection();

            String orderNumId = "";

            // ロジックの初期化
            UserMstLogic userMstLogic = new UserMstLogic(conn);

            // DTOの初期化
            UserMstDto prmDto = new UserMstDto();

            // Model値をDTOにセット
            prmDto = this.modelToDto(model);

            if (StringUtils.equals(prmDto.getClassificationKbn(), NaikaraTalkConstants.AUTHORITY_A)
                    || StringUtils.equals(prmDto.getClassificationKbn(), NaikaraTalkConstants.AUTHORITY_M)
                    || StringUtils.equals(prmDto.getClassificationKbn(), NaikaraTalkConstants.AUTHORITY_S)) {

                orderNumId = NaikaraTalkConstants.ORDER_NUMBERS_ST;

            } else if (StringUtils.equals(prmDto.getClassificationKbn(), NaikaraTalkConstants.AUTHORITY_T)) {

                //全ての種別で同じIDを使用する
                //orderNumId = NaikaraTalkConstants.ORDER_NUMBERS_TE;
            	orderNumId = NaikaraTalkConstants.ORDER_NUMBERS_ST;

            }

            // 採番マスタの自動採番処理
            OrderNumbersMstLogic orderNumbersMstLogic = new OrderNumbersMstLogic();

            // 利用者ID
            prmDto.setUserId(orderNumbersMstLogic.getOrderNumber(orderNumId, DateUtil.getSysDate()).getOrderNumber());
            model.setUserId_lbl(prmDto.getUserId());
            // ユーザIDを取得
            String userId = ((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString())).getUserId();

            // 更新者コード
            prmDto.setUpdateCd(userId);

            // 登録者コード
            prmDto.setInsertCd(userId);

            // 利用開始日
            prmDto.setUseStartDt(NaikaraStringUtil.delSlash(prmDto.getUseStartDt()));

            // 利用終了日
            prmDto.setUseEndDt(NaikaraStringUtil.delSlash(prmDto.getUseEndDt()));

            // 登録を実行
            int result = userMstLogic.insert(prmDto);

            // コネクションをコミット
            conn.commit();

            return result;

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
     * 更新処理<br>
     * <br>
     * 更新処理を行う<br>
     * <br>
     * @param model 画面パラメータ<br>
     * @return cnt 更新結果<br>
     * @exception Exception
     */
    public int update(UserMstModel model) throws Exception {

        Connection conn = null;

        int cnt = 0;

        try {

            // コネクションを取得
            conn = DbUtil.getConnection();

            // ロジックの初期化
            UserMstLogic userMstLogic = new UserMstLogic(conn);

            // DTOの初期化
            UserMstDto prmDto = new UserMstDto();

            // Model値をDTOにセット
            prmDto = this.modelToDto(model);

            // ユーザIDを取得
            String userId = ((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString())).getUserId();

            // 更新者コード
            prmDto.setUpdateCd(userId);

            // 利用開始日
            prmDto.setUseStartDt(NaikaraStringUtil.delSlash(prmDto.getUseStartDt()));

            // 利用終了日
            prmDto.setUseEndDt(NaikaraStringUtil.delSlash(prmDto.getUseEndDt()));

            // 更新を実行
            cnt = userMstLogic.update(prmDto);

            // コネクションをコミット
            conn.commit();

        } catch (Exception e) {

            // コネクションをロールバック
            conn.rollback();

            throw new Exception(e);

        } finally {

            // コネクションを閉じる
            conn.close();
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
     * @exception Exception
     */
    public LinkedHashMap<String, String> selectCodeMst(String category) throws Exception {

        Connection conn = null;

        try {
            conn = DbUtil.getConnection();

            // ロジックの初期化
            UserMstLogic userMstLogic = new UserMstLogic(conn);

            // コード管理マスタを検索
            LinkedHashMap<String, String> codeMap = userMstLogic.selectCodeMst(category);

            return codeMap;

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
     * Model値をセット<br>
     * <br>
     * Model値をDTOにセット<br>
     * <br>
     * @param model 画面パラメータ<br>
     * @return prmDto 変換後結果<br>
     * @exception Exception
     */
    private UserMstDto modelToDto(UserMstModel model) throws Exception {

        // DTOの初期化
        UserMstDto prmDto = new UserMstDto();

        prmDto.setUserId(model.getUserId_lbl());                           // 利用者ID
        prmDto.setPassword(model.getPassword_txt());                       // パスワード
        prmDto.setFamilyJnm(model.getNameFamiy_txt());                     // 名前(姓)
        prmDto.setFirstJnm(model.getNameFirst_txt());                      // 名前(名)
        prmDto.setFamilyKnm(model.getFuriganaFamiy_txt());                 // フリガナ(姓)
        prmDto.setFirstKnm(model.getFuriganaFirst_txt());                  // フリガナ(名）
        prmDto.setFamilyRomaji(model.getRomajiFamiy_txt());                // ローマ字(姓)
        prmDto.setFirstRomaji(model.getRomajiFirst_txt());                 // ローマ字(名)
        prmDto.setTel1(model.getTelephone1_txt());                         // 電話番号1
        prmDto.setTel2(model.getTelephone2_txt());                         // 電話番号2
        prmDto.setBirthYyyy(model.getBirthdayYy_txt());                    // 生年月日：年
        prmDto.setBirthMm(model.getBirthdayMm_txt());                      // 生年月日：月
        prmDto.setBirthDd(model.getBirthdayDd_txt());                      // 生年月日：日
        prmDto.setZipCd(model.getZipCode_txt());                           // 郵便番号
        prmDto.setAddressAreaCd(model.getAddress1_sel());                  // 住所(地域)コード
        prmDto.setAddressPrefectureCd(model.getAddress2_sel());            // 住所(都道府県)コード
        prmDto.setAddressCity(model.getAddress3_txt());                    // 住所(市区町村)
        prmDto.setAddressOthers(model.getAddress4_txt());                  // 住所(ﾋﾞﾙ、ﾏﾝｼｮﾝ名 等)
        prmDto.setGenderKbn(model.getSex_rdl());                           // 性別区分
        prmDto.setMailAddress1(model.getMailAdd1_txt());                   // メールアドレス1
        prmDto.setMailAddress2(model.getMailAdd2_txt());                   // メールアドレス2
        prmDto.setMailAddress3(model.getMailAdd3_txt());                   // メールアドレス3
        prmDto.setUseStartDt(model.getUtilizationStart_txt());             // 利用開始日
        prmDto.setUseEndDt(model.getUtilizationEnd_txt());                 // 利用終了日
        prmDto.setCityTown(model.getContract_txt());                       // 勤務拠点
        prmDto.setClassificationKbn(model.getUserKbn_rdl());               // 種別区分
        prmDto.setRemark(model.getRemarks_txa().replaceAll(NaikaraTalkConstants.NEW_LINE_CODE_WIN,
                NaikaraTalkConstants.NEW_LINE_CODE_UNIX));                 // 備考
        prmDto.setRecordVerNo(model.getRecordVerNo());                     // レコードバージョン番号

        return prmDto;
    }

    /**
     * データの存在チェック<br>
     * <br>
     * データの存在チェックを行う<br>
     * <br>
     * @param model 画面パラメータ<br>
     * @return int チェック結果<br>
     * @exception Exception
     */
    public int getExists(UserMstModel model) throws Exception {

        Connection conn = null;

        try {
            conn = DbUtil.getConnection();

            // ロジックの初期化
            UserMstLogic userMstLogic = new UserMstLogic(conn);

            // Model値をDTOにセット
            UserMstDto prmDto = this.modelToDto(model);

            // 検索を実行
            return userMstLogic.getExists(prmDto);

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
}