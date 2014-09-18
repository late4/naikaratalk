package com.naikara_talk.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedHashMap;

import com.naikara_talk.dbutil.DbUtil;
import com.naikara_talk.dto.OrganizationMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.logic.OrganizationContractMstLogic;
import com.naikara_talk.logic.TeacherRegistrationLogic;
import com.naikara_talk.model.OrganizationContractMstListModel;
import com.naikara_talk.model.OrganizationContractMstModel;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>組織契約情報登録ページ。<br>
 * <b>クラス名称       :</b>組織契約情報登録ページ検索Serviceクラス。<br>
 * <b>クラス概要       :</b>組織契約情報登録の情報検索ができる<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/08/01 TECS 新規作成
 */
public class OrganizationContractMstLoadService implements ActionService {

    /** 検索前チェック：取得したデータ件数0 */
    public static final int ERR_GET_DATA_ZERO = 0;
    /** 検索前チェック：取得したデータ件数100 */
    public static final int ERR_GET_DATE_HUNDRED = 200;
    /** 検索条件区分: 表示用のデータ取得用 */
    public static final String SEARCH_KYE_ZERO = "0";

    /**
     * コード管理マスタからデータ取得処理。<br>
     * <br>
     * @param category 汎用コード
     * @return LinkedHashMap<String, String>
     * @throws Exception
     */
    public LinkedHashMap<String, String> selectCodeMst(String category) throws Exception {
        Connection conn = null;

        try {

            conn = DbUtil.getConnection();
            TeacherRegistrationLogic teacherRegistrationLogic = new TeacherRegistrationLogic(conn);
            // コード管理マスタ検索
            return teacherRegistrationLogic.selectCodeMst(category);
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
     * 組織マスタのデータ取得処理。<br>
     * <br>
     * @param model OrganizationContractMstListModel
     * @return model OrganizationContractMstListModel
     * @throws Exception
     */
    public OrganizationContractMstModel select(OrganizationContractMstModel model) throws Exception {

        Connection conn = null;

        try {

            conn = DbUtil.getConnection();
            OrganizationContractMstLogic logic = new OrganizationContractMstLogic(conn);
            // DTOの初期化
            OrganizationMstDto prmDto = new OrganizationMstDto();

            // Model値をDTOにセット
            prmDto = this.modelToDto(model);

            // 検索実行
            OrganizationMstDto resultDto = logic.selectData(prmDto, model.getProcessKbn_rdl(), SEARCH_KYE_ZERO).get(0);

            // DTO値をModelにセット
            model = this.dtoToModel(resultDto);

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
    private OrganizationMstDto modelToDto(OrganizationContractMstModel model) throws Exception {

        // DTOの初期化
        OrganizationMstDto prmDto = new OrganizationMstDto();

        // 組織ID
        prmDto.setOrganizationId(model.getOrganizationId());
        // 連番
        prmDto.setConsSeq(model.getConsSeq());
        if (model.getProcessKbn_rdl().equals(OrganizationContractMstListModel.PROS_KBN_UPD)) {
            // 契約終了日
            prmDto.setContractEndDt(model.getContractEndDt());
        }

        return prmDto;

    }

    /**
     * DTO値をModelにセット。<br>
     * <br>
     * @param prmDto OrganizationMstDto
     * @return model OrganizationContractMstModel
     * @throws Exception
     */
    private OrganizationContractMstModel dtoToModel(OrganizationMstDto prmDto) throws Exception {

        OrganizationContractMstModel model = new OrganizationContractMstModel();

        model.setOrganizationId(prmDto.getOrganizationId());
        model.setConsSeq(prmDto.getConsSeq());
        model.setPassword(prmDto.getPassword());
        model.setOrganizationJnm(prmDto.getOrganizationJnm());
        model.setOrganizationKnm(prmDto.getOrganizationKnm());
        model.setOrganizationRomaji(prmDto.getOrganizationRomaji());
        model.setTel(prmDto.getTel());
        model.setZipCd(prmDto.getZipCd());
        model.setAddressAreaCd(prmDto.getAddressAreaCd());
        model.setAddressPrefectureCd(prmDto.getAddressPrefectureCd());
        model.setAddressCity(prmDto.getAddressCity());
        model.setAddressOthers(prmDto.getAddressOthers());
        model.setManagFamilyJnm(prmDto.getManagFamilyJnm());
        model.setManagFirstJnm(prmDto.getManagFirstJnm());
        model.setManagFamilyKnm(prmDto.getManagFamilyKnm());
        model.setManagFirstKnm(prmDto.getManagFirstKnm());
        model.setManagFamilyRomaji(prmDto.getManagFamilyRomaji());
        model.setManagFirstRomaji(prmDto.getManagFirstRomaji());
        model.setManagPosition(prmDto.getManagPosition());
        model.setManagGenderKbn(prmDto.getManagGenderKbn());
        model.setManagMailAddress1(prmDto.getManagMailAddress1());
        model.setManagMailAddress2(prmDto.getManagMailAddress2());
        model.setManagMailAddress3(prmDto.getManagMailAddress3());
        model.setContractStrDt(prmDto.getContractStrDt());
        model.setContractEndDt(prmDto.getContractEndDt());
        model.setTempPointNum(prmDto.getTempPointNum());
        model.setBalancePointNum(prmDto.getBalancePointNum());
        model.setRequestAddressKbn(prmDto.getRequestAddressKbn());
        model.setRemarks(prmDto.getRemarks());
        model.setRequestOrganizationJnm(prmDto.getRequestOrganizationJnm());
        model.setRequestOrganizationKnm(prmDto.getRequestOrganizationKnm());
        model.setRequestOrganizationRnm(prmDto.getRequestOrganizationRnm());
        model.setRequestTel(prmDto.getRequestTel());
        model.setRequestZipCd(prmDto.getRequestZipCd());
        model.setRequestAddressAreaCd(prmDto.getRequestAddressAreaCd());
        model.setRequestAddressPrefectureCd(prmDto.getRequestAddressPrefectureCd());
        model.setRequestAddressCity(prmDto.getRequestAddressCity());
        model.setRequestAddressOthers(prmDto.getRequestAddressOthers());
        model.setRequestManagFamilyJnm(prmDto.getRequestManagFamilyJnm());
        model.setRequestManagFirstJnm(prmDto.getRequestManagFirstJnm());
        model.setRequestManagFamilyKnm(prmDto.getRequestManagFamilyKnm());
        model.setRequestManagFirstKnm(prmDto.getRequestManagFirstKnm());
        model.setRequestManagFamilyRomaji(prmDto.getRequestManagFamilyRomaji());
        model.setRequestManagFirstRomaji(prmDto.getRequestManagFirstRomaji());
        model.setRequestManagPosition(prmDto.getRequestManagPosition());
        model.setRequestPaymentSiteKbn(prmDto.getRequestPaymentSiteKbn());
        model.setRecordVerNo(prmDto.getRecordVerNo());
        model.setReturnCode(prmDto.getReturnCode());

        return model;

    }
}
