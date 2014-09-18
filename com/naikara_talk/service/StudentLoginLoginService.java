package com.naikara_talk.service;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dbutil.DbUtil;
import com.naikara_talk.dto.StudentMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.logic.StudentLoginLogic;
import com.naikara_talk.model.StudentLoginModel;
import com.naikara_talk.util.DateUtil;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>お客様（個人）<br>
 * <b>クラス名称　　　:</b>ログイン(お客様（個人）)ログイン処理Serviceクラス。<br>
 * <b>クラス概要　　　:</b>お客様（個人）責任者のログイン処理Service。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/07/25 TECS 新規作成。
 */
public class StudentLoginLoginService implements ActionService {

    /* ログインID：データが存在しない場合 */
    private static final int DATA_ZERO_CNT = 0;

    /* 入力チェック：データが存在しない場合/パスワードが一致しない場合*/
    public static final int ERR_DATA_MIS = 1;

    /* 範囲チェックを行い、範囲外の場合*/
    public static final int ERR_DATA_NG = 2;

    /**
     * ログイン前チェック処理<br>
     * <br>
     * ログイン前チェック処理を行う<br>
     * <br>
     * @param StudentLoginModel
     * @return int ログインチェック結果 <br>
     * @exception Exception
     */
    public int checkPreSelect(StudentLoginModel model) throws Exception {

        // 入力チェック - DBアクセスありチェック
        // ログインIDの存在チェック：データが存在しない場合
        int count = getRowCount(model);
        if (count == DATA_ZERO_CNT) {
            return ERR_DATA_MIS;
        }

        // パスワードの確認チェック：取得項目の｢パスワード｣≠画面の｢パスワード｣の場合
        StudentLoginModel checkModel = selectList(model);
        if (!StringUtils.equals(model.getPassword(), checkModel.getPasswordChk())) {
            return ERR_DATA_MIS;
        }

        // 範囲チェックを行い、範囲外の場合
        if (StringUtils.isEmpty(checkModel.getUseStrDt()) || StringUtils.isEmpty(checkModel.getUseEndDt())) {
            return ERR_DATA_NG;
        }
        // 利用期間チェック
        if (DateUtil.dateCompare2(checkModel.getUseStrDt(), DateUtil.getSysDate())
                || DateUtil.dateCompare2(DateUtil.getSysDate(), checkModel.getUseEndDt())) {

            return ERR_DATA_NG;
        }

        // 正常
        return StudentLoginModel.CHECK_OK;

    }

    /**
     * 検索データ件数取得<br>
     * <br>
     * 検索データ件数取得を行う<br>
     * <br>
     * @param StudentLoginModel
     * @return DataCount 件数<br>
     * @exception Exception
     */
    public int getRowCount(StudentLoginModel model) throws NaiException {

        Connection conn = null;
        try {
            conn = DbUtil.getConnection();

            // 初期化処理
            StudentLoginLogic studentLoginLogic = new StudentLoginLogic(conn);

            // Model値をDTOにセット
            StudentMstDto dto = this.modelToDto(model);

            // データの取得件数＆リターン
            return studentLoginLogic.getRowCount(dto);
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
     * 受講者情報取得<br>
     * <br>
     * 受講者情報の取得を行う<br>
     * <br>
     * @param StudentLoginModel
     * @return retDto 検索結果<br>
     * @exception Exception
     */
    public StudentLoginModel selectList(StudentLoginModel model) throws NaiException {

        Connection conn = null;
        try {
            conn = DbUtil.getConnection();

            // 初期化処理
            StudentLoginLogic studentLoginLogic = new StudentLoginLogic(conn);

            // Model値をDTOにセット
            StudentMstDto dto = this.modelToDto(model);

            // データの取得＆リターン
            StudentMstDto studentMstDto = studentLoginLogic.selectList(dto);

            // DTO値をModelにセット
            StudentLoginModel returnModel = this.dtoToModel(studentMstDto, model);

            return returnModel;

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
     * Model値セット<br>
     * <br>
     * Model値をDTOにセット<br>
     * <br>
     * @param StudentLoginModel
     * @return StudentMstDto<br>
     * @exception Exception
     */
    private StudentMstDto modelToDto(StudentLoginModel model) throws Exception {

        // DTOの初期化
        StudentMstDto prmDto = new StudentMstDto();
        // 受講者ID
        prmDto.setStudentId(model.getLoginId());

        // レコードバージョン番号
        prmDto.setRecordVerNo(model.getRecordVerNo());

        return prmDto;

    }

    /**
     * DTO値をセット<br>
     * <br>
     * DTO値をModelにセット<br>
     * <br>
     * @param prmDto 変換前DTO<br>
     * @param model 画面パラメータ<br>
     * @return model 新画面パラメータ<br>
     * @exception Exception
     */
    private StudentLoginModel dtoToModel(StudentMstDto prmDto, StudentLoginModel model) throws Exception {

        model.setStudentId(prmDto.getStudentId());                                          // 受講者ID
        model.setNickNm(prmDto.getNickNm());                                                // ユーザ表示名
        model.setConsentDocumentAcquisitionFlg(prmDto.getConsentDocumentAcquisitionFlg());  // 保護者の同意書の入手フラグ
        model.setPointPurchaseFlg(prmDto.getPointPurchaseFlg());                            // ポイント購入済フラグ
        model.setUseStrDt(prmDto.getUseStrDt());                                            // 利用開始日
        model.setUseEndDt(prmDto.getUseEndDt());                                            // 利用終了日
        model.setRecordVerNo(prmDto.getRecordVerNo());                                      // レコードバージョン番号
        model.setPasswordChk(prmDto.getPassword());                                         // パスワード

        model.setFamilyJnm(prmDto.getFamilyJnm());                                          // 名前(姓)
        model.setFirstJnm(prmDto.getFirstJnm());                                            // 名前(名)
        model.setCustomerKbn(prmDto.getCustomerKbn());                                      // 顧客区分


        return model;
    }

    /**
     * 更新処理。<br>
     * <br>
     * 更新処理。<br>
     * <br>
     * @param StudentLoginModel 画面のパラメータ<br>
     * @return int<br>
     * @exception Exception
     */
    public int update(StudentLoginModel model) throws NaiException {

        Connection conn = null;
        int cnt = 0;
        try {
            conn = DbUtil.getConnection();

            StudentLoginLogic studentLoginLogic = new StudentLoginLogic(conn);
            // DTOの初期化
            StudentMstDto prmDto = new StudentMstDto();
            // Model値をDTOにセット
            prmDto = this.modelToDto(model);

            // 更新実行
            cnt = studentLoginLogic.update(prmDto);
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

}
