package com.naikara_talk.service;

//import java.io.File;
//import java.math.BigDecimal;
//import java.sql.Blob;
//import java.sql.Timestamp;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedHashMap;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dbutil.DbUtil;
import com.naikara_talk.dto.StudentMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.logic.OrganizationStudentMstLogic;
import com.naikara_talk.model.OrganizationStudentMstListModel;
import com.naikara_talk.model.OrganizationStudentMstModel;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>組織_初期処理<br>
 * <b>クラス名称　　　:</b>システム受講者登録(単票)初期処理Serviceクラス。<br>
 * <b>クラス概要　　　:</b>組織の社員情報(受講者)の新規アカウント(単票)。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/06/18 TECS 新規作成。
 */
public class OrganizationStudentMstLoadService implements ActionService {

    /**
     * 初期処理<br>
     * <br>
     * 画面項目の初期処理を行う<br>
     * <br>
     * @param model 画面パラメータ<br>
     * @return workModel 新画面パラメータ<br>
     * @exception なし
     */
    public OrganizationStudentMstModel initLoad(OrganizationStudentMstModel model) {

        // モデルの初期化
        OrganizationStudentMstModel workModel = new OrganizationStudentMstModel();

        // 前画面から処理区分を画面にセット
        workModel.setProcessKbn_rdl(model.getProcessKbn_rdl());

        if (StringUtils.equals(OrganizationStudentMstListModel.PROS_KBN_ADD, model.getProcessKbn_rdl())) {

            // 画面表示処理区分
            workModel.setProcessKbn_txt(NaikaraTalkConstants.PROCESSKBN_INS);

        } else if (StringUtils.equals(OrganizationStudentMstListModel.PROS_KBN_UPD, model.getProcessKbn_rdl())) {

            // 画面表示処理区分
            workModel.setProcessKbn_txt(NaikaraTalkConstants.PROCESSKBN_UPD);

        } else {

            // 画面表示処理区分
            workModel.setProcessKbn_txt(NaikaraTalkConstants.PROCESSKBN_DEL);
        }

        return workModel;
    }

    /**
     * 初期処理<br>
     * <br>
     * 画面項目の初期処理を行う<br>
     * <br>
     * @param model 画面パラメータ<br>
     * @return model 新画面パラメータ<br>
     * @exception Exception
     */
    public OrganizationStudentMstModel select(OrganizationStudentMstModel model) throws NaiException {

        Connection conn = null;
        try {
            conn = DbUtil.getConnection();
            OrganizationStudentMstLogic organizationStudentMstLogic = new OrganizationStudentMstLogic(conn);
            // DTOの初期化
            StudentMstDto prmDto = new StudentMstDto();

            // Model値をDTOにセット
            prmDto = this.modelToDto(model);

            // 検索実行
            StudentMstDto resultDto = organizationStudentMstLogic.select(prmDto);

            // DTO値をModelにセット
            model = this.dtoToModel(resultDto, model);

            return model;
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
     * データの存在チェック<br>
     * <br>
     * データの存在チェックを行う<br>
     * <br>
     * @param model 画面パラメータ<br>
     * @return int チェック結果<br>
     * @exception Exception
     */
    public int isExists(OrganizationStudentMstModel model) throws NaiException {

        Connection conn = null;
        try {
            conn = DbUtil.getConnection();
            // ロジックの初期化
            OrganizationStudentMstLogic organizationStudentMstLogic = new OrganizationStudentMstLogic(conn);

            // DTOの初期化
            StudentMstDto prmDto = new StudentMstDto();

            // Model値をDTOにセット
            prmDto = this.modelToDto(model);

            // 検索実行
            return organizationStudentMstLogic.isExists(prmDto);
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
     * コード取得<br>
     * <br>
     * コード管理マスタからデータ取得処理<br>
     * <br>
     * @param category 汎用コード<br>
     * @return LinkedHashMap 取得結果<br>
     * @exception Exception
     */
    public LinkedHashMap<String, String> selectCodeMst(String category) throws NaiException {

        Connection conn = null;

        try {
            conn = DbUtil.getConnection();
            OrganizationStudentMstLogic organizationStudentMstLogic = new OrganizationStudentMstLogic(conn);

            // コード管理マスタ検索
            return organizationStudentMstLogic.selectCodeMst(category);
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
    private StudentMstDto modelToDto(OrganizationStudentMstModel model) throws Exception {

        // DTOの初期化
        StudentMstDto prmDto = new StudentMstDto();
        prmDto.setStudentId(model.getStudentId());                  // 受講者ID
        prmDto.setPassword(model.getPassword_txt());                // 仮パスワード
        prmDto.setPassword(model.getPassworCon_txt());              // 仮パスワード確認
        prmDto.setStudentPosition(model.getStudentPosition());      // 受講者所属部署
        prmDto.setOrganizationId(model.getPositionOrganizationId());// 所属組織内ID
        prmDto.setFamilyJnm(model.getFamilyJnm());                  // 名前(姓)
        prmDto.setFirstJnm(model.getFirstJnm());                    // 名前(名)
        prmDto.setBurdenNum(model.getBurdenNum());                  // 自己負担率
        prmDto.setUseStopFlg(model.getUseStopFlg());                // 利用停止フラグ
        prmDto.setUseEndDt(model.getUseEndDt());                    // 利用終了日
        prmDto.setOrganizationId(model.getStudentId());             // 受講者ID
        prmDto.setRecordVerNo(model.getRecordVerNo());              // レコードバージョン番号
        return prmDto;

    }

    /**
     * DTO値をModelにセット。<br>
     * <br>
     * DTO値をModelにセット。<br>
     * <br>
     * @param StudentMstDto
     * @param OrganizationStudentMstModel
     * @return OrganizationStudentMstModel
     * @throws Exception
     */
    private OrganizationStudentMstModel dtoToModel(StudentMstDto prmDto, OrganizationStudentMstModel model)
            throws Exception {

        model.setProcessKbn_rdl(model.getProcessKbn_rdl());                             // 処理区分
        model.setProcessKbn_txt(model.getProcessKbn_txt());                             // 処理区分名
        model.setOrganizationNm_txt(prmDto.getOrganizationNm());                        // 組織名
        model.setStudentId(prmDto.getStudentId());                                      // 受講者ID
        model.setStudentPosition(prmDto.getStudentPosition());                          // 受講者所属部署
        model.setPositionOrganizationId(prmDto.getPositionOrganizationId());            // 所属組織内ID
        model.setPassword_txt(prmDto.getPassword());                                    // 所属組織内ID
        model.setPassworCon_txt(prmDto.getPassword());                                  // 所属組織内ID
        model.setFamilyJnm(prmDto.getFamilyJnm());                                      // 名前(姓)
        model.setFirstJnm(prmDto.getFirstJnm());                                        // 名前(名)
        model.setBurdenNum(prmDto.getBurdenNum());                                      // 自己負担率
        model.setUseStopFlg(prmDto.getUseStopFlg());                                    // 利用停止フラグ
        model.setUseEndDt(NaikaraStringUtil.converToYYYY_MM_DD(prmDto.getUseEndDt()));  // 利用終了日
        model.setRecordVerNo(prmDto.getRecordVerNo());                                  // レコードバージョン番号
        return model;

    }

}
