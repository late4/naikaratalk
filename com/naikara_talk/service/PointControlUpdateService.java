package com.naikara_talk.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedHashMap;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dbutil.DbUtil;
import com.naikara_talk.dto.PointManagMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.logic.OrderNumbersMstLogic;
import com.naikara_talk.logic.PointControlLogic;
import com.naikara_talk.model.PointControlModel;
import com.naikara_talk.util.DateUtil;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>マスタ保守<br>
 * <b>クラス名称       :</b>ポイント管理マスタメンテナンス(単票)<br>
 * <b>クラス概要       :</b>ポイント管理マスタメンテナンス(単票)登録更新Service<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/01 TECS 新規作成
 */
public class PointControlUpdateService implements ActionService {

    /** 更新前チェック：金額(税込)／有償ポイントの下限チェックエラー */
    public static final int ERR_MIN_MONEY = 1;

    /** 更新前チェック： 有償ポイント期限の下限チェックエラー */
    public static final int ERR_MIN_TIM = 2;

    /** 通常月謝区分＝月謝の場合の有償ポイント期限のチェックエラー */
    public static final int ERR_ONE_FEEKBN = 3;

    /** 更新前チェック：日付の整合性チェック (日付)エラー */
    public static final int ERR_INTEGRITY_DT = 4;

    /** BigDecimal 比較する 戻る */
    private final int BIGDECIMAL_COMPARE_LT = 1;

    /** エラーなし */
    public static final int OK_CHECK = 0;

    /**
     * 登録、更新のチェック。<br>
     * <br>
     * 登録、更新のチェック。<br>
     * <br>
     * @param PointControlModel<br>
     * @return int チェック結果<br>
     * @exception Exception
     */
    public int errorCheck(PointControlModel model) throws Exception {

        // 関連チェック
        // 金額(税込)／有償ポイント < 1000 の場合
        if (BIGDECIMAL_COMPARE_LT == NaikaraTalkConstants.MONEY_YEN_MIN.compareTo(model.getMoneyYen())) {
            return ERR_MIN_MONEY;
        }

        // 有償ポイント期限 < 1 の場合
        if (NaikaraTalkConstants.PAYMENT_POINT_TIM_MIN > model.getPaymentPointTim()) {
            return ERR_MIN_TIM;
        }

        // 通常月謝区分＝月謝 有償ポイント期限 != 1 の場合
        if (StringUtils.equals(NaikaraTalkConstants.FEE_KBN_MONTHLY, model.getFeeKbn())
                && NaikaraTalkConstants.PAYMENT_POINT_TIM_MIN != model.getPaymentPointTim()) {
            return ERR_ONE_FEEKBN;
        }

        // 日付の整合性チェック (日付)
        if (DateUtil.dateCompare2(NaikaraStringUtil.converToYYYYMMDD(model.getUseStrDt()),
                NaikaraStringUtil.converToYYYYMMDD(model.getUseEndDt()))) {
            return ERR_INTEGRITY_DT;
        }

        return OK_CHECK;
    }

    /**
     * 登録処理。<br>
     * <br>
     * 登録処理。<br>
     * <br>
     * @param PointControlListModel 画面のパラメータ<br>
     * @return int<br>
     * @exception NaiException
     */
    public int insert(PointControlModel model) throws NaiException {

        Connection conn = null;
        try {
            conn = DbUtil.getConnection();

            PointControlLogic pointControlLogic = new PointControlLogic(conn);
            // DTOの初期化
            PointManagMstDto prmDto = new PointManagMstDto();
            // Model値をDTOにセット
            prmDto = this.modelToDto(model);

            // 採番マスタの自動採番処理
            OrderNumbersMstLogic orderNumbersMstLogic = new OrderNumbersMstLogic();
            prmDto.setPointCd(orderNumbersMstLogic.getOrderNumber(NaikaraTalkConstants.ORDER_NUMBERS_PT,
                    DateUtil.getSysDate()).getOrderNumber());
            model.setPointCd(prmDto.getPointCd());
            // 登録実行
            int result = pointControlLogic.insert(prmDto);

            // コミット
            conn.commit();
            return result;

        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
                throw new NaiException(e1);
            }
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
     * 更新処理。<br>
     * <br>
     * 更新処理。<br>
     * <br>
     * @param PointControlListModel 画面のパラメータ<br>
     * @return int<br>
     * @exception NaiException
     */
    public int update(PointControlModel model) throws NaiException {

        Connection conn = null;
        int cnt = 0;
        try {
            conn = DbUtil.getConnection();

            PointControlLogic pointControlLogic = new PointControlLogic(conn);
            // DTOの初期化
            PointManagMstDto prmDto = new PointManagMstDto();
            // Model値をDTOにセット
            prmDto = this.modelToDto(model);

            // 更新実行
            cnt = pointControlLogic.update(prmDto);

            // コミット
            conn.commit();

        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
                throw new NaiException(e1);
            }
            throw new NaiException(e);
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
     * データの存在チェック。<br>
     * <br>
     * @param PointControlListModel 画面のパラメータ<br>
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
     * @param category 汎用コード<br>
     * @return LinkedHashMap<String, String><br>
     * @exception Exception
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

        prmDto.setPointCd(model.getPointCd());                              // ポイントコード
        prmDto.setFeeKbn(model.getFeeKbn());                                // 通常月謝区分
        prmDto.setMoneyYen(model.getMoneyYen());                            // 金額(税込)
        prmDto.setPaymentPoint(model.getPaymentPoint());                    // 有償ポイント
        prmDto.setPaymentPointTim(model.getPaymentPointTim());              // 有償ポイント期限
        prmDto.setFreePoint(model.getFreePoint());                          // 無償ポイント
        prmDto.setFreePointTim(model.getFreePointTim());                    // 無償ポイント期限
        prmDto.setUseStrDt(model.getUseStrDt());                            // 提供開始日
        prmDto.setUseEndDt(model.getUseEndDt());                            // 提供終了日
        prmDto.setRemarks(model.getRemarks().replaceAll(NaikaraTalkConstants.NEW_LINE_CODE_WIN,
                NaikaraTalkConstants.NEW_LINE_CODE_UNIX));                  // 備考
        prmDto.setRecordVerNo(model.getRecordVerNo());                      // レコードバージョン番号

        return prmDto;

    }

}
