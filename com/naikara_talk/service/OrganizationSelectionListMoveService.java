package com.naikara_talk.service;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dbutil.DbUtil;
import com.naikara_talk.dto.OrganizationMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.logic.OrganizationSelectionListLogic;
import com.naikara_talk.model.OrganizationSelectionListModel;
import com.naikara_talk.util.NaikaraStringUtil;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>法人_初期処理<br>
 * <b>クラス名称       :</b>組織マイページ(組織 選択)登録選択Service。<br>
 * <b>クラス概要       :</b>組織マイページ(組織 選択)。<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/08/01 TECS 新規作成
 */
public class OrganizationSelectionListMoveService implements ActionService {

    /** チェック：一覧部の項目が選択なし */
    public static final int ERR_NO_SELECT = 1;

    /** チェック：一覧画面へ戻る */
    public static final int ERR_LIST_BACK = 2;

    /**
     * 単票へ画面遷移する制御処理<br>
     * <br>
     * 単票へ画面遷移する制御処理<br>
     * <br>
     * @param OrganizationSelectionListModel select_rdl hasSearchFlg<br>
     * @return OrganizationSelectionListModel<br>
     * @exception なし
     */
    public int nextPageRequest(OrganizationSelectionListModel model, String select_rdl) {

        // 処理区分が[新規]を除く場合、一覧部の項目が選択されていない場合は、メッセージ情報を設定する
        if (StringUtils.isEmpty(select_rdl)) {
            return ERR_NO_SELECT;

        }

        return OrganizationSelectionListModel.CHECK_OK;
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
    public OrganizationSelectionListModel select(OrganizationSelectionListModel model) throws NaiException {

        Connection conn = null;
        try {
            conn = DbUtil.getConnection();
            // ロジックの初期化
            OrganizationSelectionListLogic organizationSelectionListLogic = new OrganizationSelectionListLogic(conn);

            // DTOの初期化
            OrganizationMstDto prmDto = new OrganizationMstDto();

            // Model値をDTOにセット
            prmDto = this.modelToDto(model);

            // 検索を実行
            OrganizationMstDto resultDto = organizationSelectionListLogic.select(prmDto);

            // DTO値をModelにセット
            OrganizationSelectionListModel returnModel = this.dtoToModel(resultDto, model);

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
     * DTO値をセット<br>
     * <br>
     * DTO値をModelにセット<br>
     * <br>
     * @param prmDto 変換前DTO<br>
     * @param model 画面パラメータ<br>
     * @return model 新画面パラメータ<br>
     * @exception NaiException
     */
    private OrganizationSelectionListModel dtoToModel(OrganizationMstDto prmDto, OrganizationSelectionListModel model)
            throws NaiException {

        // モデルの初期化
        OrganizationSelectionListModel returnModel = new OrganizationSelectionListModel();

        // 組織ID
        returnModel.setOrganizationId(prmDto.getOrganizationId());

        // 組織名
        returnModel.setOrganizationNm(prmDto.getOrganizationJnm());

        // 組織責任者名
        returnModel.setManagJnm(NaikaraStringUtil.unionName(prmDto.getManagFamilyJnm(), prmDto.getManagFirstJnm()));

        // 連番
        returnModel.setConsSeq(String.valueOf(prmDto.getConsSeq()));

        return returnModel;
    }

    /**
     * Model値をDTOにセット。<br>
     * <br>
     * Model値をDTOにセット。<br>
     * <br>
     * @param OrganizationSelectionListModel 画面のパラメータ<br>
     * @return OrganizationMstDto<br>
     * @exception NaiException
     */
    private OrganizationMstDto modelToDto(OrganizationSelectionListModel model) throws NaiException {

        // DTOの初期化
        OrganizationMstDto dto = new OrganizationMstDto();

        // 組織ID
        dto.setOrganizationId(model.getOrganizationId());
        // 組織名
        dto.setOrganizationJnm(model.getOrganizationNm());
        // 組織責任者名(フリガナ)
        dto.setManagKnm(model.getManagKnm());

        return dto;

    }

}
