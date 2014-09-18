package com.naikara_talk.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;

import com.naikara_talk.dbutil.DbUtil;
import com.naikara_talk.dto.PointOwnershipTrnDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.logic.StudentSpecialFreePointMstLogic;
import com.naikara_talk.model.StudentSpecialFreePointMstModel;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称　　　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称　　:</b>マスタ保守<br>
 * <b>クラス名称　　　　　:</b>受講者マスタメンテナンス 特別無償ポイント設定検索処理Serviceクラス。<br>
 * <b>クラス概要　　　　　:</b>受講者マスタメンテナンス 特別無償ポイント設定検索処理Service。<br>
 * <br>
 * <b>著作権　　　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　　　:</b>2013/07/30 TECS 新規作成。
 */
public class StudentSpecialFreePointMstLoadService implements ActionService {

    /** 一覧 ZERO件 */
    private static final int LIST_ZERO_CNT = 0;

    /** 一覧の表示上限 */
    private static final int LIST_MAX_CNT = 100;

    /** 検索前チェック：処理区分と押下ボタンの不整合 */
    public static final int ERR_PROS_BTN_MISMATCH = 1;

    /** 検索前チェック：データ件数ZERO件 */
    public static final int ERR_ZERO_DATA = 2;

    /** 検索前チェック：データ件数上限以上の件数 */
    public static final int ERR_MAXOVER_DATA = 3;

    /** 検索前チェック： 権限無*/
    public static final int ERR_NO_AUTH = 4;

    /**
     * 検索前チェック処理<br>
     * <br>
     * @param int
     * @return StudentSpecialFreePointMstModel
     * @exception NaiException
     */
    public int checkPreSelect(StudentSpecialFreePointMstModel model) throws NaiException {

        // 入力チェック - DBアクセスありチェック
        // 共通部品：受講者マスタのデータ件数取得処理
        int count = getRowCount(model);
        if (count == LIST_ZERO_CNT) {
            return ERR_ZERO_DATA;
        } else if (count > LIST_MAX_CNT) {
            return ERR_MAXOVER_DATA;
        }

        // 正常
        return StudentSpecialFreePointMstModel.CHECK_OK;

    }

    /**
     * 検索データ件数取得。<br>
     * <br>
     * @param StudentSpecialFreePointMstModel
     *            画面のパラメータ
     * @return int
     * @throws NaiException
     */
    public int getRowCount(StudentSpecialFreePointMstModel model) throws NaiException {

        Connection conn = null;
        try {
            conn = DbUtil.getConnection();

            // 初期化処理
            StudentSpecialFreePointMstLogic studentSpecialFreePointMstLogic = new StudentSpecialFreePointMstLogic(conn);

            // DTOの初期化
            PointOwnershipTrnDto dto = new PointOwnershipTrnDto();

            // Model値をDTOにセット
            dto = this.modelToDto(model);

            // データの取得件数＆リターン
            return studentSpecialFreePointMstLogic.getRowCount(dto);
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
     * @param StudentSpecialFreePointMstModel
     *            画面のパラメータ
     * @return List<PointOwnershipTrnDto>
     * @throws NaiException
     */
    public List<PointOwnershipTrnDto> selectList(StudentSpecialFreePointMstModel model) throws NaiException {

        Connection conn = null;
        try {
            conn = DbUtil.getConnection();

            // 初期化処理
            StudentSpecialFreePointMstLogic studentSpecialFreePointMstLogic = new StudentSpecialFreePointMstLogic(conn);

            // DTOの初期化
            PointOwnershipTrnDto dto = new PointOwnershipTrnDto();

            // Model値をDTOにセット
            dto = this.modelToDto(model);

            // データの取得＆リターン
            return studentSpecialFreePointMstLogic.selectList(dto);
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
     *           StudentSpecialFreePointMstModel
     * @return PointOwnershipTrnDto
     * @throws NaiException
     */
    private PointOwnershipTrnDto modelToDto(StudentSpecialFreePointMstModel model) throws NaiException {

        // DTOの初期化
        PointOwnershipTrnDto prmDto = new PointOwnershipTrnDto();
        prmDto.setStudentId(model.getStudentId());
        prmDto.setFeeKbn(NaikaraTalkConstants.FEE_KBN_MONTHLY);

        return prmDto;

    }

    /**
     * コード管理マスタからデータ取得処理。<br>
     * <br>
     * @param category
     *            汎用コード
     * @return LinkedHashMap<String, String>
     * @throws NaiException
     */
    public LinkedHashMap<String, String> selectCodeMst(String category) throws NaiException {

        Connection conn = null;
        try {
            conn = DbUtil.getConnection();
            StudentSpecialFreePointMstLogic studentSpecialFreePointMstLogic = new StudentSpecialFreePointMstLogic(conn);
            // コード管理マスタ検索
            return studentSpecialFreePointMstLogic.selectCodeMst(category);
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
