package com.naikara_talk.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.naikara_talk.dbutil.DbUtil;
import com.naikara_talk.dto.OrganizationMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.logic.OrganizationContractMstListLogic;
import com.naikara_talk.model.OrganizationContractMstListModel;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>組織契約情報登録一覧。<br>
 * <b>クラス名称       :</b>組織契約情報登録一覧検索Serviceクラス。<br>
 * <b>クラス概要       :</b>組織契約情報登録一覧の情報検索ができる<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/08/01 TECS 新規作成
 */
public class OrganizationContractMstListSearchService implements ActionService {

    /** 検索前チェック：処理区分のチェック */
    public static final int ERR_PROCESS_KBN = -1;
    /** 検索前チェック：取得したデータ件数0 */
    public static final int ERR_GET_DATA_ZERO = 0;
    /** 検索前チェック：取得したデータ件数100 */
    public static final int ERR_GET_DATE_HUNDRED = 200;

    /**
     * 組織マスタのデータ取得処理。<br>
     * <br>
     * @param model OrganizationContractMstListModel
     * @return model OrganizationContractMstListModel
     * @throws Exception
     */
    public OrganizationContractMstListModel select(OrganizationContractMstListModel model) throws Exception {

        Connection conn = null;

        try {

            conn = DbUtil.getConnection();
            OrganizationContractMstListLogic logic = new OrganizationContractMstListLogic(conn);
            // DTOの初期化
            OrganizationMstDto prmDto = new OrganizationMstDto();

            // Model値をDTOにセット
            prmDto = this.modelToDto(model);

            // 検索実行
            List<OrganizationMstDto> resultDto = logic.selectData(prmDto, model.getProcessKbn_rdl());
            if (resultDto.get(0).getReturnCode() == NaikaraTalkConstants.RETURN_CD_DATA_YES) {
                model.setOrganizationMstDto(resultDto);
            }
            return model;
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
     * @param model OrganizationContractMstListModel
     * @return OrganizationMstDto prmDto
     * @throws Exception
     */
    private OrganizationMstDto modelToDto(OrganizationContractMstListModel model) throws Exception {

        // DTOの初期化
        OrganizationMstDto prmDto = new OrganizationMstDto();

        // 組織ID
        prmDto.setOrganizationId(model.getOrganizationId());
        // 組織名
        prmDto.setOrganizationJnm(model.getOrganizationJnm());

        return prmDto;

    }

    /**
     * 検索のチェック。<br>
     * <br>
     * @param model OrganizationContractMstListModel
     * @return int count
     * @throws Exception
     */
    public int errorCheck(OrganizationContractMstListModel model) throws Exception {

        if (model.getProcessKbn_rdl().equals(OrganizationContractMstListModel.PROS_KBN_ADD)) {
            return ERR_PROCESS_KBN;
        }
        Connection conn = null;

        try {

            conn = DbUtil.getConnection();
            OrganizationContractMstListLogic logic = new OrganizationContractMstListLogic(conn);
            // DTOの初期化
            OrganizationMstDto prmDto = new OrganizationMstDto();

            // Model値をDTOにセット
            prmDto = this.modelToDto(model);

            // 検索実行
            int count = logic.selectCount(prmDto, model.getProcessKbn_rdl());

            return count;
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
