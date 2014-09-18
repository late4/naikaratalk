package com.naikara_talk.service;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dbutil.DbUtil;
import com.naikara_talk.dto.PointOwnershipTrnDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.logic.StudentMonthlyFeePurchaseInformationLogic;
import com.naikara_talk.model.StudentMonthlyFeePurchaseInformationModel;
import com.naikara_talk.util.CheckUtil;
import com.naikara_talk.util.DateUtil;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>マスタ保守<br>
 * <b>クラス名称　　　:</b>受講者マスタメンテナンス【単票】受講者別月謝購入情報保存Serviceクラス。<br>
 * <b>クラス概要　　　:</b>受講者マスタメンテナンス【単票】受講者別月謝購入情報保存Service。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/07/10 TECS 新規作成。
 */
public class StudentMonthlyFeePurchaseInformationSaveService implements ActionService {

    /** 更新前チェック：月謝停止年月フォーマット不正のチェックエラー */
    public static final int ERR_DATE_FORMAT = 1;

    /** 更新前チェック：未来日付チェックエラー */
    public static final int ERR_FUTURE_DATE = 2;

    /** 更新前チェック：月謝停止の最短月以上チェックエラー */
    public static final int ERR_SHORTEST_MONTH = 3;

    /** データ不存在チェック */
    public static final int ERR_DATA_NASI = 4;

    /** 追加 day*/
    public static final String ADD_DAY = "/01";

    /** 問題なし */
    private static int CHECK_OK = 0;

    /**
     * 保存のチェック。<br>
     * <br>
     * @param なし
     * @return boolean
     * @throws NaiException
     */
    public int errorCheck(StudentMonthlyFeePurchaseInformationModel model) throws NaiException {

        String[] endDt = model.getEndDt();
        // 関連チェック
        for (int i = 0; i < endDt.length; i++) {
            String stopDate = endDt[i];
            PointOwnershipTrnDto pointOwnershipTrnDto = new PointOwnershipTrnDto();
            pointOwnershipTrnDto = model.getResultList().get(i);
            if (!StringUtils.isEmpty(stopDate)) {

                // 月謝停止年月 フォーマット不正の場合
                if (!CheckUtil.isDate(stopDate + ADD_DAY)) {
                    return ERR_DATE_FORMAT;
                }

                // 未来日付チェック (日付)
                String sysYyyyMm = DateUtil.getSysDate();
                sysYyyyMm = NaikaraStringUtil.converToYYYY_MM(sysYyyyMm);
                if (DateUtil.dateCompare5(sysYyyyMm, stopDate)) {
                    return ERR_FUTURE_DATE;
                }

                // 月謝停止の最短月以上チェック
                String pointStopDt = NaikaraStringUtil.converToYYYY_MM(pointOwnershipTrnDto.getEffectiveEndDt());
                if (DateUtil.dateCompare5(pointStopDt, stopDate)) {
                    return ERR_SHORTEST_MONTH;
                }

                // データ不存在チェック
                if (!getExist(pointOwnershipTrnDto)) {
                    return ERR_DATA_NASI;
                }
            }
        }
        return CHECK_OK;
    }

    /**
     * データの存在チェック。<br>
     * <br>
     * @param PointOwnershipTrnDto
     *            画面のパラメータ
     * @return boolean
     * @throws NaiException
     */
    public boolean getExist(PointOwnershipTrnDto dto) throws NaiException {

        Connection conn = null;
        try {
            conn = DbUtil.getConnection();

            StudentMonthlyFeePurchaseInformationLogic studentMonthlyFeePurchaseInformationLogic = new StudentMonthlyFeePurchaseInformationLogic(
                    conn);

            // 検索実行
            int cnt = studentMonthlyFeePurchaseInformationLogic.getDataExist(dto);
            if (cnt == NaikaraTalkConstants.RETURN_CD_DATA_NO) {
                return false;
            } else {
                return true;
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

    }
}
