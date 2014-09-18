package com.naikara_talk.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.naikara_talk.dbutil.DbUtil;
import com.naikara_talk.dto.StudentMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.logic.StudentSelectionListLogic;
import com.naikara_talk.model.StudentSelectionListModel;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>お客様(個人)_初期登録。<br>
 * <b>クラス名称　　　:</b>マイページ(お客様_個人)検索処理Serviceクラス。<br>
 * <b>クラス概要　　　:</b>マイページ(お客様_個人)検索処理Service。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/07/31 TECS 新規作成。
 */
public class StudentSelectionListSearchService implements ActionService {

    /** 一覧 ZERO件 */
    private static final int LIST_ZERO_CNT = 0;

    /** 一覧の表示上限 */
    private static final int LIST_MAX_CNT = 100;

    /** 検索前チェック：データ件数ZERO件 */
    public static final int ERR_ZERO_DATA = 2;

    /** 検索前チェック：データ件数上限以上の件数 */
    public static final int ERR_MAXOVER_DATA = 3;

    /**
     * チェック処理<br>
     * <br>
     * 検索前チェック処理<br>
     * <br>
     * @param model 画面パラメータ<br>
     * @return int エラー有無フラグ<br>
     * @exception Exception
     */
    public int checkPreSelect(StudentSelectionListModel model) throws NaiException {

        // 入力チェック - DBアクセスありチェック
        // 共通部品：受講者マスタのデータ件数取得処理
        int count = getRowCount(model);

        // ZERO件の場合
        if (count == LIST_ZERO_CNT) {

            return ERR_ZERO_DATA;

        } else if (count > LIST_MAX_CNT) {

            return ERR_MAXOVER_DATA;

        }

        return StudentSelectionListModel.CHECK_OK;
    }

    /**
     * 検索データ件数取得<br>
     * <br>
     * 検索データ件数取得を行う<br>
     * <br>
     * @param model 画面パラメータ<br>
     * @return int 件数<br>
     * @exception Exception
     */
    public int getRowCount(StudentSelectionListModel model) throws NaiException {

        Connection conn = null;
        try {
            conn = DbUtil.getConnection();

            // ロジックの初期化
            StudentSelectionListLogic studentSelectionListLogic = new StudentSelectionListLogic(conn);

            // Model値をDTOにセット
            StudentMstDto dto = this.modelToDto(model);

            // データの取得件数＆リターン
            int count = studentSelectionListLogic.getRowCount(dto);

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
     * 選択遷移<br>
     * <br>
     * 画面項目の選択遷移を行う<br>
     * <br>
     * @param model 画面パラメータ<br>
     * @return model 新画面パラメータ<br>
     * @exception Exception
     */
    public StudentSelectionListModel select(StudentSelectionListModel model) throws NaiException {

        Connection conn = null;
        try {
            conn = DbUtil.getConnection();

            // ロジックの初期化
            StudentSelectionListLogic studentSelectionListLogic = new StudentSelectionListLogic(conn);

            // Model値をDTOにセット
            StudentMstDto prmDto = this.modelToDto(model);

            // 検索を実行
            StudentMstDto resultDto = studentSelectionListLogic.select(prmDto);

            // DTO値をModelにセット
            StudentSelectionListModel returnModel = this.dtoToModel(resultDto, model);

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
     * 受講者リスト取得<br>
     * <br>
     * 受講者リスト取得を行う<br>
     * <br>
     * @param model 画面パラメータ<br>
     * @return List 検索結果<br>
     * @exception Exception
     */
    public List<StudentMstDto> selectList(StudentSelectionListModel model) throws NaiException {

        Connection conn = null;
        try {
            conn = DbUtil.getConnection();

            // ロジックの初期化
            StudentSelectionListLogic studentSelectionListLogic = new StudentSelectionListLogic(conn);

            // Model値をDTOにセット
            StudentMstDto dto = this.modelToDto(model);

            // データを取得
            List<StudentMstDto> studentList = studentSelectionListLogic.selectList(dto);

            return studentList;
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
     * Model値をセット<br>
     * <br>
     * Model値をDTOにセット<br>
     * <br>
     * @param model 画面パラメータ<br>
     * @return prmDto 変換後結果<br>
     * @exception Exception
     */
    private StudentMstDto modelToDto(StudentSelectionListModel model) throws NaiException {

        // DTOの初期化
        StudentMstDto prmDto = new StudentMstDto();

        // 顧客区分
        prmDto.setCustomerKbn(model.getCustomerKbn());
        // 受講者ID
        prmDto.setStudentId(model.getStudentId());
        // ニックネーム
        prmDto.setNickNm(model.getStudentNm());
        // 受講者名(フリガナ)
        prmDto.setStudentNm(model.getStudentFuri());
        // 組織名
        prmDto.setOrganizationNm(model.getOrganization());

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
    private StudentSelectionListModel dtoToModel(StudentMstDto prmDto, StudentSelectionListModel model)
            throws NaiException {

        // モデルの初期化
        StudentSelectionListModel returnModel = new StudentSelectionListModel();

        // 受講者ID
        returnModel.setStudentId(prmDto.getStudentId());

        // 受講者名(ニックネーム)
        returnModel.setStudentNm(prmDto.getNickNm());

        // 顧客区分
        returnModel.setCustomerKbn(prmDto.getCustomerKbn());

        // 保護者の同意書の入手フラグ
        returnModel.setConsentDocumentAcquisitionFlg(prmDto.getConsentDocumentAcquisitionFlg());

        // ポイント購入済フラグ
        returnModel.setPointPurchaseFlg(prmDto.getPointPurchaseFlg());

        return returnModel;
    }
}