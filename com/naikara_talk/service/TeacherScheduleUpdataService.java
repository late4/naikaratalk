package com.naikara_talk.service;

import java.sql.Connection;
import java.sql.SQLException;

//import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dbutil.DbUtil;
import com.naikara_talk.dto.ScheduleReservationTrnDto;
import com.naikara_talk.dto.TeacherMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.logic.TeacherScheduleLogic;
import com.naikara_talk.model.TeacherScheduleModel;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>講師スケジュール。<br>
 * <b>クラス名称       :</b>講師スケジュール更新Serviceクラス。<br>
 * <b>クラス概要       :</b>講師予定予約テーブルの情報更新ができる<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/09 TECS 新規作成
 * <b>                 :</b>2014/06/02 TECS レッスン予約に関する「応相談」対応
 */
public class TeacherScheduleUpdataService implements ActionService {

    /** 更新前チェック：チェックBOX)がONのものが0つも存在しない場合 */
    public static final int ERR_CHECK_ON = 1;
    /** 更新前チェック：チェックBOX)がONのものが1つも存在する場合 */
    public static final int ERR_CHECK_OFF = 0;

    // 2014/06/02 レッスン予約に関する「応相談」対応 Del Start
    // ※NaikaraTalkConstantsの値を使用する
    ///** 予約区分：予約受付ＮＧ */
    //public static final String RESERVATION_KBN_NG = "0";
    ///** 予約区分：予約受付OK */
    //public static final String RESERVATION_KBN_OK = "1";
    ///** 予約区分：予約済み */
    //public static final String RESERVATION_KBN_END = "2";
    // 2014/06/02 レッスン予約に関する「応相談」対応 Del End

    /** =チェック：予約済み */
    public static final int ERR_KBN_END = 2;
    /** =チェック：講師マスタ．契約開始日～契約終了日外 */
    public static final int ERR_CONTRACT_DT_END = 3;


    /**
     * 講師予定予約テーブル更新処理。<br>
     * <br>
     * @param model TeacherScheduleModel
     * @return cnt int
     * @throws NaiException
     */
    public int update(TeacherScheduleModel model) throws NaiException {
        int cnt = NaikaraTalkConstants.RETURN_CD_ERR_NO_UPD;

        Connection conn = null;

        try {
            // DB接続
            conn = DbUtil.getConnection();

            // TeacherScheduleLogic生成
            TeacherScheduleLogic teacherScheduleLogic = new TeacherScheduleLogic(conn);

            for (int i = 0; i < model.getScheduleReservationTrnDto().size(); i++) {

                // ◆◆◆講師予定予約テーブル検索処理
                ScheduleReservationTrnDto prmDto = this.search(model.getScheduleReservationTrnDto().get(i));

                // ◆◆◆更新値の設定
                // 2014/06/02 レッスン予約に関する「応相談」対応 Upd Start
                //if (prmDto.getReservationKbn().equals(NaikaraTalkConstants.RESERV_KBN_NO)) {
                //    // 予約受付不可⇒予約受付可
                //    prmDto.setReservationKbn(NaikaraTalkConstants.RESERV_KBN_YES);                      // 予約区分(予約状況)
                //} else {
                //    if (prmDto.getReservationKbn().equals(NaikaraTalkConstants.RESERV_KBN_YES)) {
                //        // 予約受付可⇒予約受付不可
                //        prmDto.setReservationKbn(NaikaraTalkConstants.RESERV_KBN_NO);                   // 予約区分(予約状況)
                //    }
                //}

                // DB最新の値から画面の値へ入れ替え
                prmDto.setReservationKbn(model.getScheduleReservationTrnDto().get(i).getReservationKbn());    // 予約区分(予約状況)
                // 2014/06/02 レッスン予約に関する「応相談」対応 Upd End


                prmDto.setRecordVerNo(model.getScheduleReservationTrnDto().get(i).getRecordVerNo());    // レコードバージョン番号
                prmDto.setUpdateCd(model.getScheduleReservationTrnDto().get(i).getUpdateCd());          // 更新者コード


                // ◆◆◆講師予定予約テーブル更新実行
                cnt = teacherScheduleLogic.updateDto(prmDto);

                // ◆◆◆更新結果の判定
                if (NaikaraTalkConstants.RETURN_CD_ERR_UPD == cnt || NaikaraTalkConstants.RETURN_CD_ERR_NO_UPD == cnt) {
                    // ロールバック
                    try {
                        conn.rollback();
                    } catch (Exception e1) {
                        e1.printStackTrace();
                        throw new NaiException(e1);
                    }
                    return cnt;
                }
            }

            // コミット
            conn.commit();

            // 返却
            return cnt;
        } catch (Exception e) {
            try {
                // ロールバック
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
     * 講師マスタ検索処理。<br>
     * <br>
     * @param prmDto TeacherMstDto
     * @return int
     * @throws NaiException
     */
    public int teacherSearch(TeacherMstDto prmDto) throws NaiException {
        Connection conn = null;

        try {

            // DBの接続
            conn = DbUtil.getConnection();

            // TeacherScheduleLogicの生成
            TeacherScheduleLogic teacherScheduleLogic = new TeacherScheduleLogic(conn);

            // ◆◆◆講師マスタから対象データのカウント取得
            return teacherScheduleLogic.selectTeacherMstCnt(prmDto);

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
     * 講師予定予約テーブル検索処理。<br>
     * <br>
     * @param prmDto ScheduleReservationTrnDto
     * @return ScheduleReservationTrnDto ScheduleReservationTrnDto
     * @throws NaiException
     */
    public ScheduleReservationTrnDto search(ScheduleReservationTrnDto prmDto) throws NaiException {
        Connection conn = null;

        try {

            // DB接続
            conn = DbUtil.getConnection();

            // TeacherScheduleLogicの生成
            TeacherScheduleLogic teacherScheduleLogic = new TeacherScheduleLogic(conn);

            // ◆◆◆講師予定予約テーブルから対象データの取得
            return teacherScheduleLogic.selectScheduleReservationTrnDto(prmDto);

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
     * 講師予定予約テーブル登録処理。<br>insert
     * <br>
     * @param model TeacherScheduleModel
     * @param prmDto ScheduleReservationTrnDto
     * @return 処理結果 int
     * @throws NaiException
     */
    public int insert(TeacherScheduleModel model, ScheduleReservationTrnDto prmDto) throws NaiException {
        int cnt = NaikaraTalkConstants.RETURN_CD_ERR_NO_UPD;
        Connection conn = null;

        try {
            // DB接続
            conn = DbUtil.getConnection();

            // TeacherScheduleLogicの生成
            TeacherScheduleLogic teacherScheduleLogic = new TeacherScheduleLogic(conn);

            prmDto.setRecordVerNo(0);    // レコードバージョン番号の設定 ※InsertなのでZero

            // ◆◆◆講師予定予約テーブルへ対象データの登録
            cnt = teacherScheduleLogic.insert(prmDto);

            // コミット
            conn.commit();

            // 処理結果の返却
            return cnt;

        } catch (Exception e) {
            try {
                // ロールバック
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
     * 更新のチェック。<br>
     * <br>
     * @param model TeacherScheduleModel
     * @return int
     * @throws NaiException
     */
    public int errorCheck(TeacherScheduleModel model) throws NaiException {

        if (model.getScheduleReservationTrnDto() == null) {
            return ERR_CHECK_ON;
        }
        if (model.getScheduleReservationTrnDto().size() == 0) {
            return ERR_CHECK_ON;
        }

        return ERR_CHECK_OFF;
    }

    /**
     * 更新のチェック。<br>
     * <br>
     * @param model TeacherScheduleModel
     * @return int
     * @throws NaiException
     */
    public int errorCheck2(TeacherScheduleModel model) throws NaiException {

        if (model.getScheduleReservationTrnDto() == null) {
            // 更新すべきデータ無し Err
            return ERR_CHECK_ON;
        } else {

            if (model.getScheduleReservationTrnDto().size() == 0) {
                return ERR_CHECK_ON;
            }

            for (int i = 0; i < model.getScheduleReservationTrnDto().size(); i++) {
                ScheduleReservationTrnDto scheduleRTrnDto = model.getScheduleReservationTrnDto().get(i);

                // ◆◆◆講師予定予約テーブルの検索処理
                ScheduleReservationTrnDto scheduleReservationTrnDto = this.search(scheduleRTrnDto);

                // 検索結果の判定
                if (scheduleReservationTrnDto.getReturnCode() == NaikaraTalkConstants.RETURN_CD_DATA_YES) {
                    // 正常の場合
                    if (scheduleReservationTrnDto.getReservationKbn().equals(NaikaraTalkConstants.RESERV_KBN_ALREADY)) {
                        // 取得したデータの予約区分(予約状況)＝"予約済"の場合 Err
                        return ERR_KBN_END;
                    }
                } else {
                    // ◆◆◆講師予定予約テーブルへの追加処理
                    this.insert(model, model.getScheduleReservationTrnDto().get(i));
                }
            }
        }

        // 更新すべきデータ有り
        return ERR_CHECK_OFF;
    }


}
