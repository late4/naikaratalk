package com.naikara_talk.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedHashMap;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dbutil.DbUtil;
import com.naikara_talk.dto.TimeManagMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.logic.TimeZoneControlMstLogic;
import com.naikara_talk.model.TimeZoneControlMstListModel;
import com.naikara_talk.model.TimeZoneControlMstModel;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>マスタ保守<br>
 * <b>クラス名称　　　:</b>時差管理マスタメンテナンス【単票】初期処理Serviceクラス。<br>
 * <b>クラス概要　　　:</b>時差管理マスタメンテナンス【単票】初期処理Service。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/07/11 TECS 新規作成。
 */
public class TimeZoneControlMstLoadService implements ActionService {

    /**
     * 初期処理<br>
     * <br>
     * 画面項目の初期処理を行う<br>
     * <br>
     * @return TimeZoneControlMstModel
     */
    public TimeZoneControlMstModel initLoad(TimeZoneControlMstModel model) {
        TimeZoneControlMstModel workModel = new TimeZoneControlMstModel();

        // 前画面から処理区分を画面にセット
        workModel.setProcessKbn_rdl(model.getProcessKbn_rdl());
        if (StringUtils.equals(TimeZoneControlMstListModel.PROS_KBN_ADD, model.getProcessKbn_rdl())) {
            workModel.setProcessKbn_txt(NaikaraTalkConstants.PROCESSKBN_INS);
        } else if (StringUtils.equals(TimeZoneControlMstListModel.PROS_KBN_UPD, model.getProcessKbn_rdl())) {
            workModel.setProcessKbn_txt(NaikaraTalkConstants.PROCESSKBN_UPD);
        } else if (StringUtils.equals(TimeZoneControlMstListModel.PROS_KBN_DEL, model.getProcessKbn_rdl())) {
            workModel.setProcessKbn_txt(NaikaraTalkConstants.PROCESSKBN_DEL);
        } else {
            workModel.setProcessKbn_txt(NaikaraTalkConstants.PROCESSKBN_REF);
        }

        return workModel;
    }

    /**
     * 画面初期表示。<br>
     * <br>
     * @param TimeZoneControlMstModel
     *            画面のパラメータ
     * @return なし
     * @throws Exception
     */
    public TimeZoneControlMstModel select(TimeZoneControlMstModel model) throws Exception {

        Connection conn = null;
        try {
            conn = DbUtil.getConnection();

            TimeZoneControlMstLogic timeZoneControlMstLogic = new TimeZoneControlMstLogic(conn);
            // DTOの初期化
            TimeManagMstDto prmDto = new TimeManagMstDto();

            // Model値をDTOにセット
            prmDto = this.modelToDto(model);

            // 検索実行
            TimeManagMstDto resultDto = timeZoneControlMstLogic.select(prmDto);

            // DTO値をModelにセット
            model = this.dtoToModel(resultDto, model);

            return model;
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

        prmDto.setCountryCd(model.getCountryCd_sel());                                          // 国コード
        prmDto.setAreaNoCd(model.getAreaNoCd_sel());                                            // 時差地域NO
        prmDto.setTimeMarkKbn(model.getTimeMarkKbn_rdl());                                      // 時間（符号）
        prmDto.setTimeMinutes(model.getTimeMinutes_txt());                                      // 時間(分)
        prmDto.setSumTimeFlg(model.getSumTimeFlg_rdl());                                        // サマータイムフラグ
        prmDto.setSumTimeStrDt(model.getSumTimeStrDt_txt());                                    // サマータイム開始日
        prmDto.setSumTimeEndDt(model.getSumTimeEndDt_txt());                                    // サマータイム終了日
        prmDto.setSumTimeMarkKbn(model.getSumTimeMarkKbn_rdl());                                // サマータイム(時間)(符号)
        prmDto.setSumTimeMinutes(Integer.valueOf(StringUtils.isEmpty(model.getSumTimeMinutes_txt()) ? "0" : model
                .getSumTimeMinutes_txt()));                                                     // サマータイム(時間)(分)
        prmDto.setRemark(model.getRemark_txa());                                                // 備考
        prmDto.setRecordVerNo(model.getRecordVerNo());                                          // レコードバージョン番号

        return prmDto;

    }

    /**
     * DTO値をModelにセット。<br>
     * <br>
     * @param prmDto
     *           TimeManagMstDto
     * @param model
     *           TimeZoneControlMstModel
     * @return TimeZoneControlMstModel
     * @throws Exception
     */
    private TimeZoneControlMstModel dtoToModel(TimeManagMstDto prmDto, TimeZoneControlMstModel model) throws Exception {

        model.setProcessKbn_rdl(model.getProcessKbn_rdl());                                  // 処理区分
        model.setProcessKbn_txt(model.getProcessKbn_txt());                                  // 処理区分名

        model.setCountryCd_sel(prmDto.getCountryCd());                                       // 国コード
        model.setAreaNoCd_sel(prmDto.getAreaNoCd());                                         // 時差地域NO
        model.setTimeMarkKbn_rdl(prmDto.getTimeMarkKbn());                                   // 時間（符号）
        model.setTimeMinutes_txt(prmDto.getTimeMinutes());                                   // 時間(分)
        model.setSumTimeFlg_rdl(prmDto.getSumTimeFlg());                                     // サマータイムフラグ
        model.setSumTimeStrDt_txt(prmDto.getSumTimeStrDt());                                 // サマータイム開始日
        model.setSumTimeEndDt_txt(prmDto.getSumTimeEndDt());                                 // サマータイム終了日
        model.setSumTimeMarkKbn_rdl(prmDto.getSumTimeMarkKbn());                             // サマータイム(時間)(符号)
        model.setSumTimeMinutes_txt(String.valueOf(prmDto.getSumTimeMinutes()));             // サマータイム(時間)(分)
        model.setRemark_txa(prmDto.getRemark());                                             // 備考
        model.setRecordVerNo(prmDto.getRecordVerNo());                                       // レコードバージョン番号

        return model;

    }
}
