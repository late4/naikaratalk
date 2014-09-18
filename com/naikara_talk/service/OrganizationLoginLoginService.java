package com.naikara_talk.service;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dbutil.DbUtil;
import com.naikara_talk.dto.OrganizationMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.logic.OrganizationLoginLogic;
import com.naikara_talk.model.OrganizationLoginModel;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>法人<br>
 * <b>クラス名称　　　:</b>ログイン(組織)登録処理Serviceクラス。<br>
 * <b>クラス概要　　　:</b>組織責任者の登録処理Service。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/07/05 TECS 新規作成。
 */
public class OrganizationLoginLoginService implements ActionService {

    /* ログインID：データが存在しない場合 */
    private static final int DATA_ZERO_CNT = 0;

    /* 入力チェック：データが存在しない場合/パスワードが一致しない場合*/
    public static final int ERR_DATA_MIS = 1;

    /**
     * 登録前チェック処理<br>
     * <br>
     * 登録前チェック処理を行う<br>
     * <br>
     * @param OrganizationLoginModel
     * @return int 登録チェック結果 <br>
     * @exception Exception
     */
    public int checkPreSelect(OrganizationLoginModel model) throws Exception {

        // 入力チェック - DBアクセスありチェック
        // ログインIDの存在チェック：データが存在しない場合
        int count = getRowCount(model);
        if (count == DATA_ZERO_CNT) {
            return ERR_DATA_MIS;
        }

        // パスワードの確認チェック：取得項目の｢パスワード｣≠画面の｢パスワード｣の場合
        OrganizationLoginModel checkModel = selectList(model);
        if (!StringUtils.equals(model.getPassword(), checkModel.getPasswordChk())) {
            return ERR_DATA_MIS;
        }

        // 正常
        return OrganizationLoginModel.CHECK_OK;

    }

    /**
     * 検索データ件数取得<br>
     * <br>
     * 検索データ件数取得を行う<br>
     * <br>
     * @param OrganizationLoginModel
     * @return DataCount 件数<br>
     * @exception Exception
     */
    public int getRowCount(OrganizationLoginModel model) throws Exception {

        Connection conn = null;
        try {
            conn = DbUtil.getConnection();

            // 初期化処理
            OrganizationLoginLogic organizationLoginLogic = new OrganizationLoginLogic(conn);

            // Model値をDTOにセット
            OrganizationMstDto dto = this.modelToDto(model);

            // データの取得件数＆リターン
            return organizationLoginLogic.getRowCount(dto);

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
     * 組織ID取得<br>
     * <br>
     * 組織ID取得を行う<br>
     * <br>
     * @param OrganizationLoginModel
     * @return retDto 検索結果<br>
     * @exception Exception
     */
    public OrganizationLoginModel selectList(OrganizationLoginModel model) throws Exception {

        Connection conn = null;
        try {
            conn = DbUtil.getConnection();

            // 初期化処理
            OrganizationLoginLogic organizationLoginLogic = new OrganizationLoginLogic(conn);

            // Model値をDTOにセット
            OrganizationMstDto dto = this.modelToDto(model);

            // データの取得＆リターン
            OrganizationMstDto organizationMstDto = organizationLoginLogic.selectList(dto);

            // DTO値をModelにセット
            OrganizationLoginModel returnModel = this.dtoToModel(organizationMstDto, model);

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
     * @param OrganizationLoginModel
     * @return OrganizationMstDto<br>
     * @exception Exception
     */
    private OrganizationMstDto modelToDto(OrganizationLoginModel model) throws Exception {

        // DTOの初期化
        OrganizationMstDto prmDto = new OrganizationMstDto();
        // 組織ID
        prmDto.setOrganizationId(model.getLoginId());

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
    private OrganizationLoginModel dtoToModel(OrganizationMstDto prmDto, OrganizationLoginModel model) throws Exception {

        model.setOrganizationId(prmDto.getOrganizationId());                            // 組織ID
        model.setManagFamilyJnm(prmDto.getManagFamilyJnm());                            // 責任者名(姓)
        model.setManagFirstJnm(prmDto.getManagFirstJnm());                              // 責任者名(名)
        model.setOrganizationJnm(prmDto.getOrganizationJnm());                          // 組織名
        model.setConsSeq(prmDto.getConsSeq());                                          // 連番
        model.setPasswordChk(prmDto.getPassword());                                     // パスワード
        return model;
    }

}
