package com.naikara_talk.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dbutil.DbUtil;
import com.naikara_talk.dto.StudentMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.logic.OrganizationStudentMstListLogic;
import com.naikara_talk.model.OrganizationStudentMstListModel;
import com.naikara_talk.sessiondata.SessionUser;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>組織_初期処理<br>
 * <b>クラス名称       :</b>システム受講者登録(一覧)検索処理Service。<br>
 * <b>クラス概要       :</b>組織の社員情報(受講者)の新規アカウント(一覧)。<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/11 TECS 新規作成
 */
public class OrganizationStudentMstListSearchService implements ActionService {

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

    /**
     * 検索前チェック処理<br>
     * <br>
     * 検索前チェック処理<br>
     * <br>
     * @param OrganizationStudentMstListModel<br>
     * @return int チェック結果<br>
     * @exception Exception
     */
    public int checkPreSelect(OrganizationStudentMstListModel model) throws NaiException {

        // 処理区分のチェック、処理区分が[新規]の場合は、メッセージ情報を設定する
        if (StringUtils.equals(OrganizationStudentMstListModel.PROS_KBN_ADD, model.getProcessKbn())) {
            return ERR_PROS_BTN_MISMATCH;
        }

        // 入力チェック - DBアクセスありチェック
        // 共通部品：ポイント管理マスタのデータ件数取得処理
        int count = getRowCount(model);
        if (count == LIST_ZERO_CNT) {
            return ERR_ZERO_DATA;
        } else if (count > LIST_MAX_CNT) {
            return ERR_MAXOVER_DATA;
        }

        // 正常
        return OrganizationStudentMstListModel.CHECK_OK;

    }

    /**
     * 検索データ件数取得。<br>
     * <br>
     * 検索データ件数取得。<br>
     * <br>
     * @param OrganizationStudentMstListModel 画面のパラメータ<br>
     * @return int 検索データ件数<br>
     * @exception Exception
     */
    public int getRowCount(OrganizationStudentMstListModel model) throws NaiException {

        Connection conn = null;
        try {
            conn = DbUtil.getConnection();

            // 初期化処理
            OrganizationStudentMstListLogic organizationStudentMstListLogic = new OrganizationStudentMstListLogic(conn);

            // DTOの初期化
            StudentMstDto dto = new StudentMstDto();

            // Model値をDTOにセット
            dto = this.modelToDto(model);

            // データの取得件数＆リターン
            return organizationStudentMstListLogic.getRowCount(dto);
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
     * 画面データの取得、初回表示の場合。<br>
     * <br>
     * 画面データの取得、初回表示の場合。<br>
     * <br>
     * @param OrganizationStudentMstListModel 画面のパラメータ<br>
     * @return List<StudentMstDto><br>
     * @exception Exception
     */
    public List<StudentMstDto> selectList(OrganizationStudentMstListModel model) throws NaiException {

        Connection conn = null;
        try {
            conn = DbUtil.getConnection();
            // 初期化処理
            OrganizationStudentMstListLogic organizationStudentMstListLogic = new OrganizationStudentMstListLogic(conn);

            // DTOの初期化
            StudentMstDto dto = new StudentMstDto();

            // Model値をDTOにセット
            dto = this.modelToDto(model);

            // データの取得＆リターン
            return organizationStudentMstListLogic.selectList(dto);
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
     * @param OrganizationStudentMstListModel 画面のパラメータ<br>
     * @return StudentMstDto<br>
     * @exception Exception
     */
    private StudentMstDto modelToDto(OrganizationStudentMstListModel model) throws NaiException {

        // DTOの初期化
        StudentMstDto dto = new StudentMstDto();

        // 受講者所属部署
        dto.setStudentPosition(model.getStudentPosition());

        // 所属組織内ID
        dto.setPositionOrganizationId(model.getPositionOrganizationId());

        // 受講者ID
        dto.setStudentId(model.getStudentId());

        // 受講者名(フリガナ)
        dto.setStudentNm(model.getStudentNm());

        // 利用状態
        dto.setUseKbn(model.getUseKbn());

        // 組織ID
        dto.setOrganizationId(((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString())).getUserId());

        return dto;

    }
}
