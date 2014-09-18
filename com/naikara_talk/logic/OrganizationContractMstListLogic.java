package com.naikara_talk.logic;

import java.sql.Connection;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dao.OrganizationMstDao;
import com.naikara_talk.dao.TableCountDao;
import com.naikara_talk.dbutil.ConditionMapper;
import com.naikara_talk.dbutil.OrderCondition;
import com.naikara_talk.dto.OrganizationMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.model.OrganizationContractMstListModel;
import com.naikara_talk.util.DateUtil;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>組織契約情報一覧ページ。<br>
 * <b>クラス名称       :</b>組織契約情報一覧ページLogicクラス。<br>
 * <b>クラス概要       :</b>組織契約情報一覧<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/09 TECS 新規作成
 */
public class OrganizationContractMstListLogic {

    // コネクション変数
    public Connection conn = null;

    // コンストラクタ
    public OrganizationContractMstListLogic(Connection con) {
        this.conn = con;
    }

    /**
     * 組織マスタのデータ取得処理。<br>
     * <br>
     * @param dto OrganizationMstDto
     * @return dtoResult OrganizationMstDto
     * @throws NaiException
     */
    public List<OrganizationMstDto> selectData(OrganizationMstDto dto, String processKbn) throws NaiException {

        OrganizationMstDao dao = new OrganizationMstDao(this.conn);

        // 検索条件の作成
        ConditionMapper conditions = new ConditionMapper();
        if (!StringUtils.isEmpty(dto.getOrganizationId())) {
            conditions.addCondition(OrganizationMstDao.COND_ORGANIZATION_ID, ConditionMapper.OPERATOR_LIKE,
                    new StringBuffer().append(NaikaraTalkConstants.OPERATOR_PERCENT).append(dto.getOrganizationId())
                            .append(NaikaraTalkConstants.OPERATOR_PERCENT).toString());
        }
        if (!StringUtils.isEmpty(dto.getOrganizationJnm())) {
            conditions.addCondition(OrganizationMstDao.COND_ORGANIZATION_JNM, ConditionMapper.OPERATOR_LIKE,
                    new StringBuffer().append(NaikaraTalkConstants.OPERATOR_PERCENT).append(dto.getOrganizationJnm())
                            .append(NaikaraTalkConstants.OPERATOR_PERCENT).toString());
        }
        if (StringUtils.equals(OrganizationContractMstListModel.PROS_KBN_UPD, processKbn)) {
            conditions.addCondition(OrganizationMstDao.COND_CONTRACT_END_DT, ConditionMapper.OPERATOR_LARGE_EQUAL,
                    DateUtil.getSysDate());
        }

        // 並び順
        OrderCondition orderBy = new OrderCondition();
        orderBy.addCondition(OrganizationMstDao.COND_ORGANIZATION_ID, OrderCondition.ASC);

        // 講師マスタから対象データの取得
        List<OrganizationMstDto> resultDtoList = dao.search(conditions, orderBy);

        return resultDtoList;

    }

    /**
     * 組織マスタのデータ件数取得処理。<br>
     * <br>
     * @param dto OrganizationMstDto
     * @return int count
     * @throws NaiException
     */
    public int selectCount(OrganizationMstDto dto, String processKbn) throws NaiException {

        TableCountDao dao = new TableCountDao(this.conn);

        // 検索条件の作成
        ConditionMapper conditions = new ConditionMapper();
        if (!StringUtils.isEmpty(dto.getOrganizationId())) {
            conditions.addCondition(OrganizationMstDao.COND_ORGANIZATION_ID, ConditionMapper.OPERATOR_LIKE,
                    new StringBuffer().append(NaikaraTalkConstants.OPERATOR_PERCENT).append(dto.getOrganizationId())
                            .append(NaikaraTalkConstants.OPERATOR_PERCENT).toString());
        }
        if (!StringUtils.isEmpty(dto.getOrganizationJnm())) {
            conditions.addCondition(OrganizationMstDao.COND_ORGANIZATION_JNM, ConditionMapper.OPERATOR_LIKE,
                    new StringBuffer().append(NaikaraTalkConstants.OPERATOR_PERCENT).append(dto.getOrganizationJnm())
                            .append(NaikaraTalkConstants.OPERATOR_PERCENT).toString());
        }
        if (StringUtils.equals(OrganizationContractMstListModel.PROS_KBN_UPD, processKbn)) {
            conditions.addCondition(OrganizationMstDao.COND_CONTRACT_END_DT, ConditionMapper.OPERATOR_LARGE_EQUAL,
                    DateUtil.getSysDate());
        }

        // 時差管理マスタから対象データの取得
        int count = dao.rowCount(NaikaraTalkConstants.ORGANIZATION_MST, conditions);

        return count;

    }
}
