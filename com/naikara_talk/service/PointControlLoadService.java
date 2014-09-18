package com.naikara_talk.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedHashMap;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dbutil.DbUtil;
import com.naikara_talk.dto.PointManagMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.logic.PointControlLogic;
import com.naikara_talk.model.PointControlListModel;
import com.naikara_talk.model.PointControlModel;
import com.naikara_talk.util.DateUtil;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>マスタ保守<br>
 * <b>クラス名称       :</b>ポイント管理マスタメンテナンス(単票)<br>
 * <b>クラス概要       :</b>ポイント管理マスタメンテナンス(単票)初期処理Service<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/01 TECS 新規作成
 */
public class PointControlLoadService implements ActionService {

    /**
     * 初期処理<br>
     * <br>
     * 画面項目の初期処理を行う<br>
     * <br>
     * @param PointControlModel<br>
     * @return PointControlListModel<br>
     * @exception なし
     */
    public PointControlModel initLoad(PointControlModel model) {
        PointControlModel workModel = new PointControlModel();

        // 前画面から処理区分を画面にセット
        workModel.setProcessKbn_Rdl(model.getProcessKbn_Rdl());
        if (StringUtils.equals(PointControlListModel.PROS_KBN_ADD, model.getProcessKbn_Rdl())) {
            workModel.setProcessKbn_Txt(NaikaraTalkConstants.PROCESSKBN_INS);
        } else if (StringUtils.equals(PointControlListModel.PROS_KBN_UPD, model.getProcessKbn_Rdl())) {
            workModel.setProcessKbn_Txt(NaikaraTalkConstants.PROCESSKBN_UPD);
        } else {
            workModel.setProcessKbn_Txt(NaikaraTalkConstants.PROCESSKBN_REF);
        }

        // 無償ポイント 初期値設定
        workModel.setFreePoint(NaikaraTalkConstants.FREE_POINT_INIT);
        // 無償ポイント期限 初期値設定
        workModel.setFreePointTim(NaikaraTalkConstants.FREE_POINT_TIM_INIT);
        // 提供期間：開始日 初期値設定
        workModel.setUseStrDt(NaikaraStringUtil.converToYYYY_MM_DD(DateUtil.getSysDate()));
        // 提供終了日 初期値設定
        workModel.setUseEndDt(NaikaraTalkConstants.MAX_END_DT);

        return workModel;
    }

    /**
     * 画面初期表示。<br>
     * <br>
     * 画面初期表示。<br>
     * <br>
     * @param PointControlModel  画面のパラメータ<br>
     * @return PointControlListModel<br>
     * @exception NaiException
     */
    public PointControlModel select(PointControlModel model) throws NaiException {

        Connection conn = null;

        try {
            conn = DbUtil.getConnection();

            PointControlLogic pointControlLogic = new PointControlLogic(conn);
            // DTOの初期化
            PointManagMstDto prmDto = new PointManagMstDto();

            // Model値をDTOにセット
            prmDto = this.modelToDto(model);

            // 検索実行
            PointManagMstDto resultDto = pointControlLogic.select(prmDto);

            // DTO値をModelにセット
            model = this.dtoToModel(resultDto, model);

            return model;
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
     * データの存在チェック。<br>
     * <br>
     * 画面初期表示。<br>
     * <br>
     * @param PointControlModel  画面のパラメータ<br>
     * @return int<br>
     * @exception NaiException
     */
    public int getExist(PointControlModel model) throws NaiException {

        Connection conn = null;

        try {
            conn = DbUtil.getConnection();
            PointControlLogic pointControlLogic = new PointControlLogic(conn);
            // DTOの初期化
            PointManagMstDto prmDto = new PointManagMstDto();

            // Model値をDTOにセット
            prmDto = this.modelToDto(model);

            // 検索実行
            return pointControlLogic.getExist(prmDto);
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

            PointControlLogic pointControlLogic = new PointControlLogic(conn);

            // コード管理マスタ検索
            return pointControlLogic.selectCodeMst(category);
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
     * @param PointControlModel<br>
     * @return PointManagMstDto<br>
     * @exception なし
     */
    private PointManagMstDto modelToDto(PointControlModel model) {

        // DTOの初期化
        PointManagMstDto prmDto = new PointManagMstDto();

        prmDto.setPointCd(model.getPointCd());                  // ポイントコード
        prmDto.setFeeKbn(model.getFeeKbn());                    // 通常月謝区分
        prmDto.setFeeKbnNm(model.getFeeKbnNm());                // 通常月謝区分名
        prmDto.setMoneyYen(model.getMoneyYen());                // 金額(税込)
        prmDto.setPaymentPoint(model.getPaymentPoint());        // 有償ポイント
        prmDto.setPaymentPointTim(model.getPaymentPointTim());  // 有償ポイント期限
        prmDto.setFreePoint(model.getFreePoint());              // 無償ポイント
        prmDto.setFreePointTim(model.getFreePointTim());        // 無償ポイント期限
        prmDto.setUseStrDt(model.getUseStrDt());                // 提供開始日
        prmDto.setUseEndDt(model.getUseEndDt());                // 提供終了日
        prmDto.setRemarks(model.getRemarks());                  // 備考
        prmDto.setRecordVerNo(model.getRecordVerNo());          // レコードバージョン番号

        return prmDto;

    }

    /**
     * DTO値をModelにセット。<br>
     * <br>
     * DTO値をModelにセット。<br>
     * <br>
     * @param PointManagMstDto<br>
     * @return PointControlModel<br>
     * @exception なし
     */
    private PointControlModel dtoToModel(PointManagMstDto prmDto, PointControlModel model) {

        model.setProcessKbn_Rdl(model.getProcessKbn_Rdl());     // 処理区分
        model.setProcessKbn_Txt(model.getProcessKbn_Txt());     // 処理区分名

        model.setPointCd(prmDto.getPointCd());                  // ポイントコード
        model.setFeeKbn(prmDto.getFeeKbn());                    // 通常月謝区分
        model.setFeeKbnNm(prmDto.getFeeKbnNm());                // 通常月謝区分
        model.setMoneyYen(prmDto.getMoneyYen());                // 金額(税込)
        model.setPaymentPoint(prmDto.getPaymentPoint());        // 有償ポイント
        model.setPaymentPointTim(prmDto.getPaymentPointTim());  // 有償ポイント期限
        model.setFreePoint(prmDto.getFreePoint());              // 無償ポイント
        model.setFreePointTim(prmDto.getFreePointTim());        // 無償ポイント期限
        model.setUseStrDt(prmDto.getUseStrDt());                // 提供開始日
        model.setUseEndDt(prmDto.getUseEndDt());                // 提供終了日
        model.setRemarks(prmDto.getRemarks());                  // 備考
        model.setRecordVerNo(prmDto.getRecordVerNo());          // レコードバージョン番号

        return model;

    }

}
