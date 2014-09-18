package com.naikara_talk.service;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dbutil.DbUtil;
import com.naikara_talk.dto.UserMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.logic.SchoolLoginLogic;
import com.naikara_talk.model.SchoolLoginModel;
import com.naikara_talk.util.DateUtil;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>ログイン・メニュー。<br>
 * <b>クラス名称　　　:</b>会社側のログイン処理ログインServiceクラス。<br>
 * <b>クラス概要　　　:</b>会社側のログイン処理ログインService。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/07/02 TECS 新規作成。
 */
public class SchoolLoginLoginService implements ActionService {

    /* ログインID：データが存在しない場合 */
    private static final int DATA_ZERO_CNT = 0;

    /* 入力チェック：データが存在しない場合/パスワードが一致しない場合*/
    public static final int ERR_DATA_MIS = 1;

    /* 利用期間のチェック：データが範囲外の場合 */
    public static final int ERR_DATA_OVERDUE = 2;

    /* 権限のチェック：｢種別｣ ≠ 講師の場合 */
    public static final int ERR_PER_MIS = 3;

    /**
     * 検索前チェック処理<br>
     * <br>
     * 検索前チェック処理を行う<br>
     * <br>
     * @param model 画面パラメータ<br>
     * @return チェック結果 正常：0　異常：1 <br>
     * @exception Exception
     */
    public int checkPreSelect(SchoolLoginModel model) throws Exception {

        // 入力チェック - DBアクセスありチェック
        // 共通部品：利用者マスタのデータ件数取得処理
        int count = getRowCount(model);

        if (count == DATA_ZERO_CNT) {
            return ERR_DATA_MIS;
        }

        // パスワードチェック
        SchoolLoginModel checkModel = selectList(model);
        if (!StringUtils.equals(model.getPassword(), checkModel.getPasswordChk())) {

            return ERR_DATA_MIS;
        }

        // 利用期間チェック
        if (DateUtil.dateCompare2(checkModel.getUseStartDt(), DateUtil.getSysDate())
                || DateUtil.dateCompare2(DateUtil.getSysDate(), checkModel.getUseEndDt())) {

            return ERR_DATA_OVERDUE;
        }

        // 権限チェック
        if (StringUtils.equals(NaikaraTalkConstants.AUTHORITY_T, checkModel.getClassificationKbn())) {

            return ERR_PER_MIS;
        }

        // チェック：問題なし(正常)
        return SchoolLoginModel.CHECK_OK;
    }

    /**
     * 検索データ件数取得<br>
     * <br>
     * 検索データ件数取得を行う<br>
     * <br>
     * @param model 画面パラメータ<br>
     * @return 件数 データの取得件数<br>
     * @exception Exception
     */
    private int getRowCount(SchoolLoginModel model) throws NaiException {

        Connection conn = null;
        try {
            conn = DbUtil.getConnection();

            // ロジックの初期化
            SchoolLoginLogic schoolLoginLogic = new SchoolLoginLogic(conn);

            // Model値をDTOにセット
            UserMstDto dto = this.modelToDto(model);

            // データ件数を取得
            int count = schoolLoginLogic.getRowCount(dto);

            return count;
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
     * @param model 画面パラメータ<br>
     * @return 結果 データの取得<br>
     * @exception Exception
     */
    public SchoolLoginModel selectList(SchoolLoginModel model) throws NaiException {

        Connection conn = null;
        try {
            conn = DbUtil.getConnection();

            // ロジックの初期化
            SchoolLoginLogic schoolLoginLogic = new SchoolLoginLogic(conn);

            // Model値をDTOにセット
            UserMstDto dto = this.modelToDto(model);

            // データを取得
            UserMstDto userDto = schoolLoginLogic.selectUser(dto);

            // DTO値をModelにセット
            SchoolLoginModel returnModel = this.dtoToModel(userDto, model);

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
     * @param model 画面パラメータ<br>
     * @return retDto 変換結果<br>
     * @exception Exception
     */
    private UserMstDto modelToDto(SchoolLoginModel model) throws Exception {

        // DTOの初期化
        UserMstDto retDto = new UserMstDto();

        // ログインID
        retDto.setUserId(model.getLoginId());

        // パスワード
        retDto.setPassword(model.getPassword());

        return retDto;
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
    private SchoolLoginModel dtoToModel(UserMstDto prmDto, SchoolLoginModel model) throws Exception {

        model.setUserId(prmDto.getUserId());                                  // 利用者ID
        model.setFamilyJnm(prmDto.getFamilyJnm());                            // 名前(姓)
        model.setFirstJnm(prmDto.getFirstJnm());                              // 名前(名)
        model.setClassificationKbn(prmDto.getClassificationKbn());            // 種別区分
        model.setUseStartDt(prmDto.getUseStartDt());                          // 利用開始日
        model.setUseEndDt(prmDto.getUseEndDt());                              // 利用終了日
        model.setPasswordChk(prmDto.getPassword());                           // パスワード
        return model;
    }

}