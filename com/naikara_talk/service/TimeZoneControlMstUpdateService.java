package com.naikara_talk.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedHashMap;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dbutil.DbUtil;
import com.naikara_talk.dto.TimeManagMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.logic.TimeZoneControlMstLogic;
import com.naikara_talk.model.TimeZoneControlMstModel;
import com.naikara_talk.util.DateUtil;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>マスタ保守<br>
 * <b>クラス名称　　　:</b>時差管理マスタメンテナンス【単票】登録更新Serviceクラス。<br>
 * <b>クラス概要　　　:</b>時差管理マスタメンテナンス【単票】登録更新Service。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/07/10 TECS 新規作成。
 */
public class TimeZoneControlMstUpdateService implements ActionService {

    /** 更新前チェック：国コードのチェックエラー */
    public static final int ERR_CONY_ALL = 1;

    /** 更新前チェック：時差地域Noのチェックエラー */
    public static final int ERR_AREA_NO_ALL = 2;

    /** 更新前チェック：｢サマータイムフラグ｣＝1(有)の場合:サマータイム開始日 is Emptyのチェックエラー */
    public static final int ERR_SUM_START_DATE_EMPTY = 3;

    /** 更新前チェック：｢サマータイムフラグ｣＝1(有)の場合:サマータイム終了日 is Emptyのチェックエラー */
    public static final int ERR_SUM_END_DATE_EMPTY = 4;

    /** 更新前チェック：｢サマータイムフラグ｣＝1(有)の場合:サマータイム(時間)(符号) is Emptyのチェックエラー */
    public static final int ERR_SUM_TIME_FLG_EMPTY = 5;

    /** 更新前チェック：｢サマータイムフラグ｣＝1(有)の場合:サマータイム(時間)(分) is Zeroのチェックエラー */
    public static final int ERR_SUM_TIME_MINUTES_EMPTY = 6;

    /** 更新前チェック：｢サマータイムフラグ｣＝0(無)の場合:サマータイム開始日 is not Emptyのチェックエラー */
    public static final int ERR_SUM_START_DATE_NOT_EMPTY = 7;

    /** 更新前チェック：｢サマータイムフラグ｣＝0(無)の場合:サマータイム終了日 is not Emptyのチェックエラー */
    public static final int ERR_SUM_END_DATE_NOT_EMPTY = 8;

    /** 更新前チェック：｢サマータイムフラグ｣＝0(無)の場合:サマータイム(時間)(符号) is not Emptyのチェックエラー */
    public static final int ERR_SUM_TIME_FLG_NOT_EMPTY = 9;

    /** 更新前チェック：｢サマータイムフラグ｣＝0(無)の場合:サマータイム(時間)(分) is not Zeroのチェックエラー */
    public static final int ERR_SUM_TIME_MINUTES_NOT_EMPTY = 10;

    /** 更新前チェック：日付の整合性チェック (日付)エラー */
    public static final int ERR_INTEGRITY_DT = 11;

    /** サマータイム(時間)(分)の下限 */
    public static final int MIN_MINUTES = 0;

    /** エラーなし */
    public static final int OK_CHECK = 0;

    /**
     * 登録、更新のチェック。<br>
     * <br>
     * @param なし
     * @return boolean
     * @throws Exception
     */
    public int errorCheck(TimeZoneControlMstModel model) throws Exception {

        // 関連チェック
        // ｢国コード｣="0000"の場合
        if (StringUtils.equals(NaikaraTalkConstants.CHOICE_ALL_ZERO, model.getCountryCd_sel())) {
            return ERR_CONY_ALL;
        }

        // ｢時差地域No｣="0000"の場合
        if (StringUtils.equals(NaikaraTalkConstants.CHOICE_ALL_ZERO, model.getAreaNoCd_sel())) {
            return ERR_AREA_NO_ALL;
        }
        // ｢サマータイムフラグ｣＝”1”(有)の場合
        if (StringUtils.equals(NaikaraTalkConstants.SUM_TIME_FLG_YES, model.getSumTimeFlg_rdl())) {
            // サマータイム開始日 is Empty
            if (StringUtils.isEmpty(model.getSumTimeStrDt_txt())) {
                return ERR_SUM_START_DATE_EMPTY;
            }
            // サマータイム終了日 is Empty
            if (StringUtils.isEmpty(model.getSumTimeEndDt_txt())) {
                return ERR_SUM_END_DATE_EMPTY;
            }
            // 日付の整合性チェック (日付)
            if (DateUtil.dateCompare2(NaikaraStringUtil.converToYYYYMMDD(model.getSumTimeStrDt_txt()),
                    NaikaraStringUtil.converToYYYYMMDD(model.getSumTimeEndDt_txt()))) {
                return ERR_INTEGRITY_DT;
            }
            // サマータイム(時間)(符号) is Empty
            if (StringUtils.isEmpty(model.getSumTimeMarkKbn_rdl())) {
                return ERR_SUM_TIME_FLG_EMPTY;
            }
            // サマータイム(時間)(分) is Zero
            if (StringUtils.isEmpty(model.getSumTimeMinutes_txt())) {
                return ERR_SUM_TIME_MINUTES_EMPTY;
            }

        }

        // ｢サマータイムフラグ｣＝”0”(無)の場合
        if (StringUtils.equals(NaikaraTalkConstants.SUM_TIME_FLG_NO, model.getSumTimeFlg_rdl())) {
            // サマータイム開始日 is Empty
            if (!StringUtils.isEmpty(model.getSumTimeStrDt_txt())) {
                return ERR_SUM_START_DATE_NOT_EMPTY;
            }
            // サマータイム終了日 is Empty
            if (!StringUtils.isEmpty(model.getSumTimeEndDt_txt())) {
                return ERR_SUM_END_DATE_NOT_EMPTY;
            }
            // サマータイム(時間)(分) is Zero
            if (!StringUtils.isEmpty(model.getSumTimeMinutes_txt())) {
                return ERR_SUM_TIME_MINUTES_NOT_EMPTY;
            }
        }

        return OK_CHECK;
    }

    /**
     * 登録処理。<br>
     * <br>
     * @param TimeZoneControlMstModel
     *            画面のパラメータ
     * @return なし
     * @throws Exception
     */
    public int insert(TimeZoneControlMstModel model) throws Exception {

        Connection conn = null;
        try {
            conn = DbUtil.getConnection();

            TimeZoneControlMstLogic timeZoneControlMstLogic = new TimeZoneControlMstLogic(conn);
            // DTOの初期化
            TimeManagMstDto prmDto = new TimeManagMstDto();
            // Model値をDTOにセット
            prmDto = this.modelToDto(model);

            // 登録実行
            int result = timeZoneControlMstLogic.insert(prmDto);

            // コミット
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
     * 更新処理。<br>
     * <br>
     * @param TimeZoneControlMstModel
     *            画面のパラメータ
     * @return int
     * @throws Exception
     */
    public int update(TimeZoneControlMstModel model) throws Exception {

        int cnt = 0;
        Connection conn = null;
        try {
            conn = DbUtil.getConnection();

            TimeZoneControlMstLogic timeZoneControlMstLogic = new TimeZoneControlMstLogic(conn);
            // DTOの初期化
            TimeManagMstDto prmDto = new TimeManagMstDto();
            // Model値をDTOにセット
            prmDto = this.modelToDto(model);

            // 更新実行
            cnt = timeZoneControlMstLogic.update(prmDto);

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
     * 削除処理。<br>
     * <br>
     * @param TimeZoneControlMstModel
     *            画面のパラメータ
     * @return int
     * @throws Exception
     */
    public int delete(TimeZoneControlMstModel model) throws Exception {
        int cnt = 0;
        Connection conn = null;
        try {
            conn = DbUtil.getConnection();

            TimeZoneControlMstLogic timeZoneControlMstLogic = new TimeZoneControlMstLogic(conn);
            // DTOの初期化
            TimeManagMstDto prmDto = new TimeManagMstDto();
            // Model値をDTOにセット
            prmDto = this.modelToDto(model);

            // 更新実行
            cnt = timeZoneControlMstLogic.delete(prmDto);

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
     * @param TimeZoneControlMstModel
     *            画面のパラメータ
     * @return boolean
     * @throws Exception
     */
    public int getExist(TimeZoneControlMstModel model) throws Exception {

        Connection conn = null;
        try {
            conn = DbUtil.getConnection();

            TimeZoneControlMstLogic timeZoneControlMstLogic = new TimeZoneControlMstLogic(conn);
            // DTOの初期化
            TimeManagMstDto prmDto = new TimeManagMstDto();
            // Model値をDTOにセット
            prmDto = this.modelToDto(model);

            // 検索実行
            return timeZoneControlMstLogic.getExist(prmDto);

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
     * @throws Exception
     */
    public LinkedHashMap<String, String> selectCodeMst(String category) throws Exception {

        Connection conn = null;
        try {
            conn = DbUtil.getConnection();

            TimeZoneControlMstLogic timeZoneControlMstLogic = new TimeZoneControlMstLogic(conn);
            // コード管理マスタ検索
            return timeZoneControlMstLogic.selectCodeMst(category);

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
     *           TimeZoneControlMstModel
     * @return TimeManagMstDto
     * @throws Exception
     */
    private TimeManagMstDto modelToDto(TimeZoneControlMstModel model) throws Exception {

        // DTOの初期化
        TimeManagMstDto prmDto = new TimeManagMstDto();

        prmDto.setCountryCd(model.getCountryCd_sel());
        prmDto.setAreaNoCd(model.getAreaNoCd_sel());
        prmDto.setTimeMarkKbn(model.getTimeMarkKbn_rdl());
        prmDto.setTimeMinutes(model.getTimeMinutes_txt());
        prmDto.setSumTimeFlg(model.getSumTimeFlg_rdl());
        if (StringUtils.equals(NaikaraTalkConstants.SUM_TIME_FLG_YES, model.getSumTimeFlg_rdl())) {
            prmDto.setSumTimeStrDt(NaikaraStringUtil.converToYYYYMMDD(model.getSumTimeStrDt_txt()));
            prmDto.setSumTimeEndDt(NaikaraStringUtil.converToYYYYMMDD(model.getSumTimeEndDt_txt()));
            prmDto.setSumTimeMarkKbn(model.getSumTimeMarkKbn_rdl());
            prmDto.setSumTimeMinutes(Integer.valueOf(model.getSumTimeMinutes_txt()));
        } else {
            prmDto.setSumTimeStrDt("");
            prmDto.setSumTimeEndDt("");
            prmDto.setSumTimeMarkKbn("");
            prmDto.setSumTimeMinutes(0);
        }

        prmDto.setRemark(model.getRemark_txa());
        prmDto.setRecordVerNo(model.getRecordVerNo());

        return prmDto;

    }
}
