package com.naikara_talk.service;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.LinkedHashMap;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dbutil.DbUtil;
import com.naikara_talk.dto.TeacherRateMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.logic.TeacherRateMstLogic;
import com.naikara_talk.model.TeacherRateMstModel;
import com.naikara_talk.util.DateUtil;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>マスタ保守<br>
 * <b>クラス名称       :</b>講師支払比率の設定<br>
 * <b>クラス概要       :</b>講師支払比率の設定一覧へ反映Service<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/30 TECS 新規作成
 *                          2013/11/14 TECS 要望対応 No1022-6(講師支払比率99⇒99.999)
 */
public class TeacherRateMstReflectionService implements ActionService {

    /** チェック：問題なし(正常) */
    public static final int CHECK_OK = 0;

    /** 更新前チェック：日付の整合性チェック (日付)エラー (｢適用開始日｣　＞　｢適用終了日｣　の場合)*/
    public static final int ERR_INTEGRITY_DT = 1;

    /** 更新前チェック：日付の整合性チェック (日付)エラー */
    public static final int ERR_INTEGRITY_CONTRACT_DT = 2;

    /** 更新前チェック：支払比率の上限チェックエラー */
    public static final int ERR_MAX_MONEY = 3;

    /** 更新前チェック：支払比率の下限チェックエラー */
    public static final int ERR_MIN_MONEY = 4;

    /** ドロップダウンリストの必須チェック　(支払サイクル) */
    public static final int ERR_NOT_SELECT_PAYMENTCYCLE_SEL = 5;

    /** 更新前チェック：支払比率の小数点以下の桁数チェックエラー */
    public static final int ERR_PAYMENTRATE_SCALE = 6;

    /** 更新前チェック：数値チェックエラー */
    public static final int ERR_PAYMENTRATE_NUM = 7;


    /**
     * 一覧へ反映処理のチェック。<br>
     * <br>
     * 一覧へ反映処理のチェック。<br>
     * <br>
     * @param TeacherRateMstModel<br>
     * @return int チェック結果<br>
     * @exception なし
     */
    public int errorCheck(TeacherRateMstModel model) {

        // 関連チェック

        // 日付の整合性チェック (日付)(｢適用開始日｣　＞　｢適用終了日｣　の場合)
        if (DateUtil.dateCompare2(NaikaraStringUtil.converToYYYYMMDD(model.getStartDt()),
                NaikaraStringUtil.converToYYYYMMDD(model.getEndDt()))) {
            return ERR_INTEGRITY_DT;
        }

        // 下記を全て満たさない場合は、エラーとする
        // 適用開始日　≧前画面の「契約期間：開始日」 And
        // 適用終了日　≦前画面の「契約期間：終了日」
        if (!(DateUtil.dateCompare3(NaikaraStringUtil.converToYYYYMMDD(model.getStartDt()),
                NaikaraStringUtil.converToYYYYMMDD(model.getContractStartDt())) && DateUtil.dateCompare3(
                NaikaraStringUtil.converToYYYYMMDD(model.getContractEndDt()),
                NaikaraStringUtil.converToYYYYMMDD(model.getEndDt())))) {
            return ERR_INTEGRITY_CONTRACT_DT;
        }


        //No1022-6 講師支払比率99⇒99.999へ変更対応-Start
        // 支払比率 > 100 の場合
        /*
        if (NaikaraTalkConstants.PAYMENT_RATE_MAX < model.getPaymentRate()) {
            return ERR_MAX_MONEY;
        }

        // 支払比率 < 0.000 の場合
        if (NaikaraTalkConstants.PAYMENT_RATE_MIN > model.getPaymentRate()) {
            return ERR_MIN_MONEY;
        }
        */


        // 支払比率の数値チェック
        try {
            BigDecimal bBuaiRitsuTxt = new BigDecimal(model.getPaymentRate().toString());
        } catch (Exception ex) {
            ex.printStackTrace();
            return ERR_PAYMENTRATE_NUM;
        }

        // 支払比率の小数点以下の桁数の取得
        BigDecimal payRate = new BigDecimal(model.getPaymentRate().toString());
        int scale = payRate.scale();
        if (scale > 3) {
            // 小数点以下は3桁まで（999.999）
            return ERR_PAYMENTRATE_SCALE;
        }

        // 支払比率 > 100 の場合
        //PaymentRate の数値が PAYMENT_RATE_MAX より小さい場合は -1、等しい場合は 0、大きい場合は 1
        if (payRate.compareTo(NaikaraTalkConstants.PAYMENT_RATE_MAX) == 1 ) {
            return ERR_MAX_MONEY;
        }

        // 支払比率 < 0.000 の場合
        if (payRate.compareTo(NaikaraTalkConstants.PAYMENT_RATE_MIN ) == -1 ) {
            return ERR_MIN_MONEY;
        }
        //No1022-6 講師支払比率99⇒99.999へ変更対応-End


        // 支払サイクルのコード値＝”0000”の場合
        if (StringUtils.equals(NaikaraTalkConstants.CHOICE_ALL_ZERO, model.getPaymentCycleCd())) {
            return ERR_NOT_SELECT_PAYMENTCYCLE_SEL;
        }

        return CHECK_OK;
    }

    /**
     * 一覧へ反映処理。<br>
     * <br>
     * 一覧へ反映処理。<br>
     * <br>
     * @param TeacherRateMstModel 画面のパラメータ<br>
     * @return void<br>
     * @exception NaiException
     */
    public void reflection(TeacherRateMstModel model) throws NaiException {

        // 「修正No」が記載されていない場合
        if (StringUtils.isEmpty(model.getNumberNo())) {
            TeacherRateMstDto dto = new TeacherRateMstDto();
            // 講師ID (利用者ID)
            dto.setUserId(model.getUserId());
            // 適用期間：開始日
            dto.setStartDt(NaikaraStringUtil.converToYYYYMMDD(model.getStartDt()));
            // 適用期間：終了日
            dto.setEndDt(NaikaraStringUtil.converToYYYYMMDD(model.getEndDt()));

            // 2013/11/14 No1022-6 支払比率の書式設定を追加-Start
            // 支払比率
            DecimalFormat df = new DecimalFormat("##0.000");
            dto.setPaymentRate(new BigDecimal(df.format(new BigDecimal(model.getPaymentRate()))));
            // 2013/11/14 No1022-6 支払比率の書式設定を追加-End

            // 支払サイクルコード
            dto.setPaymentCycleCd(model.getPaymentCycleCd());
            // 支払サイクルコード名
            dto.setPaymentCycleCdNm(this.selectCodeMst(NaikaraTalkConstants.CODE_CATEGORY_PAYMENT_CYCLE).get(
                    model.getPaymentCycleCd()));
            // 源泉税有無区分
            dto.setWithholdingTaxKbn(model.getWithholdingTaxKbn());
            // 源泉税有無区分名
            dto.setWithholdingTaxKbnNm(this.selectCodeMst(NaikaraTalkConstants.CODE_CATEGORY_SOURCE_WHETHER).get(
                    model.getWithholdingTaxKbn()));
            model.getResultList().add(dto);
        } else {
            int index = Integer.parseInt(model.getNumberNo()) - 1;
            // 適用期間：終了日
            model.getResultList().get(index).setEndDt(model.getEndDt());

            // 2013/11/14 No1022-6 支払比率の書式設定を追加-Start
            // 支払比率
            DecimalFormat df = new DecimalFormat("##0.000");
            model.getResultList().get(index).setPaymentRate(new BigDecimal(df.format(new BigDecimal(model.getPaymentRate()))));
            // 2013/11/14 No1022-6 支払比率の書式設定を追加-End

            // 支払サイクルコード
            model.getResultList().get(index).setPaymentCycleCd(model.getPaymentCycleCd());
            // 支払サイクルコード名
            model.getResultList()
                    .get(index)
                    .setPaymentCycleCdNm(
                            this.selectCodeMst(NaikaraTalkConstants.CODE_CATEGORY_PAYMENT_CYCLE).get(
                                    model.getPaymentCycleCd()));
            // 源泉税有無区分
            model.getResultList().get(index).setWithholdingTaxKbn(model.getWithholdingTaxKbn());
            // 源泉税有無区分名
            model.getResultList()
                    .get(index)
                    .setWithholdingTaxKbnNm(
                            this.selectCodeMst(NaikaraTalkConstants.CODE_CATEGORY_SOURCE_WHETHER).get(
                                    model.getWithholdingTaxKbn()));
        }
    }

    /**
     * コード管理マスタからデータ取得処理。<br>
     * <br>
     * コード管理マスタからデータ取得処理。<br>
     * <br>
     * @param category 汎用コード<br>
     * @return LinkedHashMap<String, String><br>
     * @exception NaiException
     */
    public LinkedHashMap<String, String> selectCodeMst(String category) throws NaiException {

        Connection conn = null;
        try {
            conn = DbUtil.getConnection();
            TeacherRateMstLogic teacherRateMstLogic = new TeacherRateMstLogic(conn);
            // コード管理マスタ検索
            return teacherRateMstLogic.selectCodeMst(category);
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
