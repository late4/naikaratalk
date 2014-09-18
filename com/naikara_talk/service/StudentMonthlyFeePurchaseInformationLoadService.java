package com.naikara_talk.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.naikara_talk.dbutil.DbUtil;
import com.naikara_talk.dto.PointOwnershipTrnDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.logic.StudentMonthlyFeePurchaseInformationLogic;
import com.naikara_talk.model.StudentMonthlyFeePurchaseInformationModel;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称　　　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称　　:</b>マスタ保守<br>
 * <b>クラス名称　　　　　:</b>受講者マスタメンテナンス 受講者別月謝購入情報初期処理Serviceクラス。<br>
 * <b>クラス概要　　　　　:</b>受講者マスタメンテナンス 受講者別月謝購入情報初期処理Service。<br>
 * <br>
 * <b>著作権　　　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　　　:</b>2013/07/30 TECS 新規作成。
 */
public class StudentMonthlyFeePurchaseInformationLoadService implements ActionService {

    /** 一覧 ZERO件 */
    public static final int LIST_ZERO_CNT = 0;

    /** 検索前チェック：データ件数ZERO件 */
    public static final int ERR_ZERO_DATA = 1;

    /**
     * 検索前チェック処理<br>
     * <br>
     * @param int
     * @return StudentMonthlyFeePurchaseInformationModel
     * @exception NaiException
     */
    public int checkPreSelect(StudentMonthlyFeePurchaseInformationModel model) throws NaiException {

        // 入力チェック - DBアクセスありチェック
        // 共通部品：ポイン所有テーブルマスタのデータ件数取得処理
        int count = getRowCount(model);
        if (count == LIST_ZERO_CNT) {
            return ERR_ZERO_DATA;
        }

        // 正常
        return StudentMonthlyFeePurchaseInformationModel.CHECK_OK;

    }

    /**
     * 検索データ件数取得。<br>
     * <br>
     * @param StudentMonthlyFeePurchaseInformationModel
     *            画面のパラメータ
     * @return int
     * @throws NaiException
     */
    public int getRowCount(StudentMonthlyFeePurchaseInformationModel model) throws NaiException {

        Connection conn = null;
        try {
            conn = DbUtil.getConnection();

            // 初期化処理
            StudentMonthlyFeePurchaseInformationLogic studentMonthlyFeePurchaseInformationLogic = new StudentMonthlyFeePurchaseInformationLogic(
                    conn);

            // DTOの初期化
            PointOwnershipTrnDto dto = new PointOwnershipTrnDto();

            // Model値をDTOにセット
            dto = this.modelToDto(model);

            // データの取得件数＆リターン
            return studentMonthlyFeePurchaseInformationLogic.getRowCount(dto);

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
     * 画面一覧データの取得。<br>
     * <br>
     * @param StudentMonthlyFeePurchaseInformationModel
     *            画面のパラメータ
     * @return List<PointOwnershipTrnDto>
     * @throws NaiException
     */
    public List<PointOwnershipTrnDto> selectList(StudentMonthlyFeePurchaseInformationModel model) throws NaiException {

        Connection conn = null;
        try {
            conn = DbUtil.getConnection();

            // 初期化処理
            StudentMonthlyFeePurchaseInformationLogic studentMonthlyFeePurchaseInformationLogic = new StudentMonthlyFeePurchaseInformationLogic(
                    conn);

            // DTOの初期化
            PointOwnershipTrnDto dto = new PointOwnershipTrnDto();

            // Model値をDTOにセット
            dto = this.modelToDto(model);

            // データの取得＆リターン
            List<PointOwnershipTrnDto> reList = studentMonthlyFeePurchaseInformationLogic.selectList(dto);

            for (int i = 0; i < reList.size(); i++) {
                String endDt = NaikaraStringUtil.converToYYYY_MM(reList.get(i).getEndDt());
                reList.get(i).setEndDt(endDt);
            }
            return reList;

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
     *           StudentMonthlyFeePurchaseInformationModel
     * @return PointOwnershipTrnDto
     * @throws NaiException
     */
    private PointOwnershipTrnDto modelToDto(StudentMonthlyFeePurchaseInformationModel model) throws NaiException {

        // DTOの初期化
        PointOwnershipTrnDto prmDto = new PointOwnershipTrnDto();
        prmDto.setStudentId(model.getStudentId());
        prmDto.setFeeKbn(NaikaraTalkConstants.FEE_KBN_MONTHLY);

        return prmDto;

    }
}
