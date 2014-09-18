package com.naikara_talk.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.naikara_talk.dbutil.DbUtil;
import com.naikara_talk.dto.CodeManagMstDto;
import com.naikara_talk.dto.CourseMstDto;
import com.naikara_talk.dto.LessonReserveCancelResultListDto;
import com.naikara_talk.dto.SchResTLesResPerTDto;
//import com.naikara_talk.dto.SchResTLesResPerTDto;
import com.naikara_talk.dto.StudentMstDto;
import com.naikara_talk.dto.UserMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.logic.LessonReserveCancelLogic;
import com.naikara_talk.logic.SComReservationCancellationConfirmationLogic;
import com.naikara_talk.model.SComReservationCancellationConfirmationModel;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>お客様(個人)_予約処理<br>
 * <b>クラス名称       :</b>予約／取消確認ページメール送信Serviceクラス<br>
 * <b>クラス概要       :</b>受講者が指定した講師の予約スケジュールを表示して、レッスン予約の登録／取消ができる。<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/08/02 TECS 新規作成
 */
public class SComReservationCancellationConfirmationSendService implements ActionService {

    protected Logger log = Logger.getLogger(this.getClass());

    /** レッスン予約・取消結果：全て正常 */
    public static final int LESSON_RIGHT_ALL = 1;

    /** レッスン予約・取消結果：全て異常 */
    public static final int LESSON_WRONG_ALL = 2;

    /** レッスン予約・取消結果：一部正常 */
    public static final int LESSON_RIGHT_SOME = 3;

    /** チェック：データが存在しない */
    public static final int ERR_RETURN_CD_DATA_NO = 4;

    /** チェック：メールアドレスエラー */
    public static final int ERR_MAIL_ADDRESS = 5;

    /** メール送信エラー：レッスン予約：受講者 */
    public static final int ERR_SEND_MAIL_RESERVE_STUDENT = 6;

    /** メール送信エラー：レッスン予約：講師 */
    public static final int ERR_SEND_MAIL_RESERVE_TEACHER = 7;

    /** メール送信エラー：レッスン取消：受講者 */
    public static final int ERR_SEND_MAIL_CANCEL_STUDENT = 8;

    /** メール送信エラー：レッスン取消：講師 */
    public static final int ERR_SEND_MAIL_CANCEL_TEACHER = 9;

    /**
     * レッスン予約・取消<br>
     * <br>
     * レッスン予約・取消を行う<br>
     * <br>
     * @param model モデル<br>
     * @return int レッスン予約・取消結果<br>
     * @throws NaiException
     */
    public int lessonReserveCancel(SComReservationCancellationConfirmationModel model) throws NaiException {

        Connection conn = null;

        try {
            conn = DbUtil.getConnection();

            // 初期化
            LessonReserveCancelLogic logic = new LessonReserveCancelLogic(conn);

            // ◆◆◆レッスン予約・取消結果リスト(ログイン者ID、予約リスト、解除リスト)
            model.setLrcrlDtoList(logic.lessonReserveCancel(model.getLoginId(), model.getPpdlDtoList(),
                    model.getPrdlDtoList()));

            // レッスン予約・取消結果リストの全ての明細のリターンコード＝”0”
            boolean rightFlg = true;

            // レッスン予約・取消結果リストの全ての明細のリターンコード≠”0”
            boolean wrongFlg = true;

            // ◆◆◆処理結果判定
            for (LessonReserveCancelResultListDto dto : model.getLrcrlDtoList()) {

                if (dto.getReturnCode() == NaikaraTalkConstants.RETURN_CD_DATA_YES) {
                    wrongFlg = false;

                } else {
                    rightFlg = false;
                }
            }

            // レッスン予約・取消結果
            int result;

            if (rightFlg) {
                // 全て正常
                result = LESSON_RIGHT_ALL;

            } else if (wrongFlg) {
                // 全て異常
                result = LESSON_WRONG_ALL;

            } else {
                // 一部正常
                result = LESSON_RIGHT_SOME;
            }

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

    /**.
     * メール送信処理<br>
     * <br>
     * メール送信処理を行う<br>
     * <br>
     * @param model モデル<br>
     * @return Map<Integer, Boolean> 送信結果<br>
     * @throws NaiException
     */
    public Map<Integer, Boolean> sendMail(SComReservationCancellationConfirmationModel model) throws NaiException {

        // 送信結果
        Map<Integer, Boolean> retMap = new HashMap<Integer, Boolean>();

        // エラーの初期化
        retMap.put(ERR_RETURN_CD_DATA_NO, false);
        retMap.put(ERR_MAIL_ADDRESS, false);
        retMap.put(ERR_SEND_MAIL_RESERVE_STUDENT, false);
        retMap.put(ERR_SEND_MAIL_RESERVE_TEACHER, false);
        retMap.put(ERR_SEND_MAIL_CANCEL_STUDENT, false);
        retMap.put(ERR_SEND_MAIL_CANCEL_TEACHER, false);

        // 受講者マスタのデータのエラーフラグ
        boolean smDataErrFlg = false;

        // 利用者マスタのデータのエラーフラグ
        boolean umDataErrFlg = false;

        // コースマスタのデータのエラーフラグ
        boolean cmDataErrFlg = false;

        // 受講者マスタのデータ取得処理
        StudentMstDto smDto = this.selectStudentMst(model);

        // データが存在する場合
        if (smDto.getReturnCode() == NaikaraTalkConstants.RETURN_CD_DATA_YES) {

            // 受講者マスタ．メールアドレス1 = ”” の場合
            if (StringUtils.isEmpty(smDto.getMailAddress1())) {
                smDataErrFlg = true;
                retMap.put(ERR_MAIL_ADDRESS, true);
            }
        } else {
            // データが存在しない場合
            smDataErrFlg = true;
            retMap.put(ERR_RETURN_CD_DATA_NO, true);
        }

        // 利用者マスタのデータ取得処理
        UserMstDto umDto = this.selectUserMst(model.getTeacherId());

        // データが存在しない場合
        if (umDto.getReturnCode() == NaikaraTalkConstants.RETURN_CD_DATA_NO) {
            umDataErrFlg = true;
            retMap.put(ERR_RETURN_CD_DATA_NO, true);
        }

        if (!smDataErrFlg || !umDataErrFlg) {

            // 画面上の一覧の件数分を繰り返す
            for (int i = 0; i < model.getLrcrlDtoList().size(); i++) {

                // レッスン予約・取消結果リストの明細のリターンコード＝”0”(正常)の場合
                if (model.getLrcrlDtoList().get(i).getReturnCode() == NaikaraTalkConstants.RETURN_CD_DATA_YES) {

                    //2013/10/01-システムエラー対応-レッスン予約・解除結果を使用-Start
                    //SchResTLesResPerTDto dto = model.getResultList().get(i);
                    LessonReserveCancelResultListDto dto = model.getLrcrlDtoList().get(i);
                    //2013/10/01-システムエラー対応-レッスン予約・解除結果を使用-End

                    // 一覧の｢コース名｣のコード≠”9999” (予約中止) の場合
                    if (!StringUtils.equals(dto.getCourseCd(), NaikaraTalkConstants.CHOICE_ALL_NINE)) {

                        // 利用者マスタのデータが取得できた場合はメール送信用のコース名を取得
                        if (!umDataErrFlg) {

                            cmDataErrFlg = false;

                            // コースマスタのデータ取得処理
                            CourseMstDto cmDto = this.selectCourseMst(dto.getCourseCd());

                            // データが存在する場合
                            if (cmDto.getReturnCode() == NaikaraTalkConstants.RETURN_CD_DATA_YES) {

                                // 指定条件のコード管理マスタの取得処理：大分類(講師用)
                                CodeManagMstDto bigCmmDto = this.selectCodeManagMst(
                                        NaikaraTalkConstants.CODE_CATEGORY_BIG_CLASSIFICATION_T,
                                        cmDto.getBigClassificationCd());

                                // 指定条件のコード管理マスタの取得処理：中分類(講師用)
                                CodeManagMstDto middleCmmDto = this.selectCodeManagMst(
                                        NaikaraTalkConstants.CODE_CATEGORY_MIDDLE_CLASSIFICATION_T,
                                        cmDto.getMiddleClassificationCd());

                                // 指定条件のコード管理マスタの取得処理：小分類(講師用)
                                CodeManagMstDto smallCmmDto = this.selectCodeManagMst(
                                        NaikaraTalkConstants.CODE_CATEGORY_SMALL_CLASSIFICATION_T,
                                        cmDto.getSmallClassificationCd());

                                // コース名(英語)
                                model.getMailCourseEnmList().add(
                                        NaikaraStringUtil.unionString4(bigCmmDto.getManagerNm(),
                                                middleCmmDto.getManagerNm(), smallCmmDto.getManagerNm(),
                                                cmDto.getCourseEnm()));
                            } else {
                                // データが存在しない場合
                                cmDataErrFlg = true;
                                retMap.put(ERR_RETURN_CD_DATA_NO, true);
                            }
                        }

                        if (StringUtils.equals(dto.getReserveCancelFlg(), NaikaraTalkConstants.LESSON_RESERVE)) {
                            // 予約フラグ
                            model.setReserveFlg(true);
                        } else {
                            // 取消フラグ
                            model.setCancelFlg(true);
                        }

                        // 受講者マスタのデータのエラーフラグ
                        if (!smDataErrFlg) {
                            model.getMailStudentFlgList().add(true);
                        } else {
                            model.getMailStudentFlgList().add(false);
                        }

                        // 利用者マスタのデータのエラーフラグ
                        // コースマスタのデータのエラーフラグ
                        if (!umDataErrFlg && !cmDataErrFlg) {
                            model.getMailTeacherFlgList().add(true);
                        } else {
                            model.getMailTeacherFlgList().add(false);
                        }

                    }

                }
            }


            // メール送信START
            Thread thread = new Thread(
                    new SComReservationCancellationConfirmationSendThreadService(model, smDto, umDto));
            thread.start();
        }

        return retMap;
    }

    /**
     * 受講者マスタデータ取得<br>
     * <br>
     * 受講者マスタリストを戻り値で返却する<br>
     * <br>
     * @param model モデル<br>
     * @return StudentMstDto 受講者マスタDTO<br>
     * @throws NaiException
     */
    public StudentMstDto selectStudentMst(SComReservationCancellationConfirmationModel model) throws NaiException {

        Connection conn = null;

        try {
            conn = DbUtil.getConnection();

            // 初期化
            SComReservationCancellationConfirmationLogic logic = new SComReservationCancellationConfirmationLogic(conn);

            return logic.selectStudentMst(model.getLoginId());

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
     * 利用者マスタデータ取得<br>
     * <br>
     * 利用者マスタリストを戻り値で返却する<br>
     * <br>
     * @param teacherId 講師ID<br>
     * @return UserMstDto 利用者マスタDTO<br>
     * @throws NaiException
     */
    public UserMstDto selectUserMst(String teacherId) throws NaiException {

        Connection conn = null;

        try {
            conn = DbUtil.getConnection();

            // 初期化
            SComReservationCancellationConfirmationLogic logic = new SComReservationCancellationConfirmationLogic(conn);

            return logic.selectUserMst(teacherId);

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
     * コースマスタデータ取得<br>
     * <br>
     * コースマスタリストを戻り値で返却する<br>
     * <br>
     * @param courseCd コースコード<br>
     * @return CourseMstDto コースマスタDTO<br>
     * @throws NaiException
     */
    public CourseMstDto selectCourseMst(String courseCd) throws NaiException {

        Connection conn = null;

        try {
            conn = DbUtil.getConnection();

            // 初期化
            SComReservationCancellationConfirmationLogic logic = new SComReservationCancellationConfirmationLogic(conn);

            return logic.selectCourseMst(courseCd);

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
     * コード管理マスタデータ取得<br>
     * <br>
     * コード管理マスタリストを戻り値で返却する<br>
     * <br>
     * @param categoryCd 種別コード<br>
     * @param managerCd 汎用コード<br>
     * @return CodeManagMstDto コード管理マスタDTO<br>
     * @throws NaiException
     */
    public CodeManagMstDto selectCodeManagMst(String categoryCd, String managerCd) throws NaiException {

        Connection conn = null;

        try {
            conn = DbUtil.getConnection();

            // 初期化
            SComReservationCancellationConfirmationLogic logic = new SComReservationCancellationConfirmationLogic(conn);

            return logic.selectCodeManagMst(categoryCd, managerCd);

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
