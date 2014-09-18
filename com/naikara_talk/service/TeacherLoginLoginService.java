package com.naikara_talk.service;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dbutil.DbUtil;
import com.naikara_talk.dto.TeacherMstDto;
import com.naikara_talk.dto.UserMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.logic.TeacherLoginLogic;
import com.naikara_talk.model.TeacherLoginModel;
import com.naikara_talk.util.DateUtil;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>初期処理<br>
 * <b>クラス名称　　　:</b>サービス提供ページ登録処理Serviceクラス。<br>
 * <b>クラス概要　　　:</b>講師の登録処理Service。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/07/02 TECS 新規作成。
 */
public class TeacherLoginLoginService implements ActionService {

    /* ログインID：データが存在しない場合 */
    private static final int DATA_ZERO_CNT = 0;

    /* 入力チェック：データが存在しない場合/パスワードが一致しない場合*/
    public static final int ERR_DATA_MIS = 1;

    /* 利用期間のチェック：データが範囲外の場合 */
    public static final int ERR_DATA_OVERDUE = 2;

    /* 権限のチェック：｢種別｣ ≠ 講師の場合 */
    public static final int ERR_PER_MIS = 3;

    /**
     * 登録前チェック処理<br>
     * <br>
     * 登録前チェック処理を行う<br>
     * <br>
     * @param TeacherLoginModel
     * @return int 登録チェック結果 <br>
     * @exception Exception
     */
    public int checkPreSelect(TeacherLoginModel model) throws Exception {

        // 入力チェック - DBアクセスありチェック
        // ログインIDの存在チェック：データが存在しない場合
        int count = getRowCount(model);
        if (count == DATA_ZERO_CNT) {
            return ERR_DATA_MIS;
        }

        // パスワードの確認チェック：取得項目の｢パスワード｣≠画面の｢パスワード｣の場合
        TeacherLoginModel checkModel = selectList(model);
        if (!StringUtils.equals(model.getPassword(), checkModel.getPasswordChk())) {
            return ERR_DATA_MIS;
        }

        // 利用期間のチェック：取得項目の｢利用開始日｣ ≧ システム日付/取得項目の｢利用終了日｣≦システム日付の場合
        if (DateUtil.dateCompare2(checkModel.getUseStartDt(), DateUtil.getSysDate())
                || DateUtil.dateCompare2(DateUtil.getSysDate(), checkModel.getUseEndDt())) {
            return ERR_DATA_OVERDUE;
        }

        // 権限のチェック：取得項目の｢種別｣ ≠ 講師の場合
        if (!StringUtils.equals(checkModel.getClassificationKbn(), NaikaraTalkConstants.AUTHORITY_T)) {
            return ERR_PER_MIS;
        }

        // 正常
        return TeacherLoginModel.CHECK_OK;

    }

    /**
     * 検索データ件数取得<br>
     * <br>
     * 検索データ件数取得を行う<br>
     * <br>
     * @param TeacherLoginModel
     * @return DataCount 件数<br>
     * @exception Exception
     */
    public int getRowCount(TeacherLoginModel model) throws Exception {

        Connection conn = null;
        try {
            conn = DbUtil.getConnection();
            // 初期化処理
            TeacherLoginLogic teacherLoginLogic = new TeacherLoginLogic(conn);

            // Model値をDTOにセット
            UserMstDto dto = this.modelToDto(model);

            // データの取得件数＆リターン
            return teacherLoginLogic.getRowCount(dto);

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
     * 利用者取得<br>
     * <br>
     * 利用者取得を行う<br>
     * <br>
     * @param TeacherLoginModel
     * @return retDto 検索結果<br>
     * @exception Exception
     */
    public TeacherLoginModel selectList(TeacherLoginModel model) throws Exception {

        Connection conn = null;
        try {
            conn = DbUtil.getConnection();

            // 初期化処理
            TeacherLoginLogic teacherLoginLogic = new TeacherLoginLogic(conn);

            // Model値をDTOにセット
            UserMstDto dto = this.modelToDto(model);

            // データの取得＆リターン
            UserMstDto userDto = teacherLoginLogic.selectList(dto);

            // DTO値をModelにセット
            TeacherLoginModel returnModel = this.dtoToModel(userDto, model);

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
     * 利用者取得<br>
     * <br>
     * 利用者取得を行う<br>
     * <br>
     * @param TeacherLoginModel
     * @return retDto 検索結果<br>
     * @exception Exception
     */
    public TeacherLoginModel selectTeacherList(TeacherLoginModel model) throws Exception {

        Connection conn = null;
        try {
            conn = DbUtil.getConnection();

            // 初期化処理
            TeacherLoginLogic teacherLoginLogic = new TeacherLoginLogic(conn);

            // Model値をDTOにセット
            TeacherMstDto dto = this.modelToTeacherDto(model);

            // データの取得＆リターン
            TeacherMstDto teacherMstDto = teacherLoginLogic.selectNm(dto);

            // DTO値をModelにセット
            TeacherLoginModel returnModel = this.teacherDtoToModel(teacherMstDto, model);

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
     * @param TeacherLoginModel
     * @return UserMstDto<br>
     * @exception Exception
     */
    private UserMstDto modelToDto(TeacherLoginModel model) throws Exception {

        // DTOの初期化
        UserMstDto prmDto = new UserMstDto();
        // 利用者ID
        prmDto.setUserId(model.getLoginId());

        return prmDto;

    }

    /**
     * Model値セット<br>
     * <br>
     * Model値をDTOにセット<br>
     * <br>
     * @param TeacherLoginModel
     * @return UserMstDto<br>
     * @exception Exception
     */
    private TeacherMstDto modelToTeacherDto(TeacherLoginModel model) throws Exception {

        // DTOの初期化
        TeacherMstDto prmDto = new TeacherMstDto();
        // 利用者ID
        prmDto.setUserId(model.getLoginId());

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
    private TeacherLoginModel dtoToModel(UserMstDto prmDto, TeacherLoginModel model) throws Exception {

        model.setUserId(prmDto.getUserId());                                  // 利用者ID
        model.setFamilyJnm(prmDto.getFamilyJnm());                            // 名前(姓)
        model.setFirstJnm(prmDto.getFirstJnm());                              // 名前(名)
        model.setClassificationKbn(prmDto.getClassificationKbn());            // 種別区分
        model.setUseStartDt(prmDto.getUseStartDt());                          // 利用開始日
        model.setUseEndDt(prmDto.getUseEndDt());                              // 利用終了日
        model.setPasswordChk(prmDto.getPassword());                           // パスワード
        return model;
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
    private TeacherLoginModel teacherDtoToModel(TeacherMstDto prmDto, TeacherLoginModel model) throws Exception {

        model.setNickAnm(prmDto.getNickAnm());                                  // ニックネーム
        return model;
    }

}
