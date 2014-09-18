package com.naikara_talk.logic;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dao.OrganizationMstDao;
import com.naikara_talk.dbutil.ConditionMapper;
import com.naikara_talk.dbutil.OrderCondition;
import com.naikara_talk.dto.OrganizationMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.util.DateUtil;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>法人_初期処理<br>
 * <b>クラス名称       :</b>組織マイページ(組織 選択)Logicクラス。<br>
 * <b>クラス概要       :</b>組織マイページ(組織 選択)。<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/08/01 TECS 新規作成
 */
public class OrganizationSelectionListLogic {

    // コネクション変数
    public Connection conn = null;

    // コンストラクタ
    public OrganizationSelectionListLogic(Connection con) {
        this.conn = con;
    }

    /**
     * 検索データ件数取得。<br>
     * <br>
     * 検索データ件数取得。<br>
     * <br>
     * @param OrganizationMstDto:dto<br>
     * @return int:DataCount<br>
     * @exception NaiException
     */
    public int getRowCount(OrganizationMstDto dto) throws NaiException {

        // 初期化処理
        OrganizationMstDao dao = new OrganizationMstDao(this.conn);

        // 一覧のデータ件数を取得する
        int DataCount = dao.rowCount(NaikaraTalkConstants.ORGANIZATION_MST, SetConditions(dto), dto);

        // 戻り値
        return DataCount;

    }

    /**
     * 検索処理<br>
     * <br>
     * 検索処理<br>
     * <br>
     * @param OrganizationMstDto 画面のパラメータ<br>
     * @return List<OrganizationMstDto> 一覧データ <br>
     * @exception Exception
     */
    public List<OrganizationMstDto> selectList(OrganizationMstDto dto) throws Exception {

        // 初期化処理
        List<OrganizationMstDto> list = new ArrayList<OrganizationMstDto>();
        OrganizationMstDao dao = new OrganizationMstDao(this.conn);

        // 並び順:組織名(フリガナ) の昇順、組織ID の昇順
        OrderCondition orderBy = new OrderCondition();
        orderBy.addCondition(OrganizationMstDao.COND_ORGANIZATION_KNM, OrderCondition.ASC);
        orderBy.addCondition(OrganizationMstDao.COND_ORGANIZATION_ID, OrderCondition.ASC);

        // 一覧データを取得する
        list = dao.searchWithOr(SetConditions(dto), orderBy, dto);

        // 戻り値
        return list;

    }

    /**
     * 選択遷移<br>
     * <br>
     * 選択遷移の検索処理<br>
     * <br>
     * @param dto 検索条件<br>
     * @return dtoResult 検索結果<br>
     * @exception NaiException
     */
    public OrganizationMstDto select(OrganizationMstDto dto) throws NaiException {

        // DAOの初期化
        OrganizationMstDao dao = new OrganizationMstDao(this.conn);

        // 並び順:組織名(フリガナ) の昇順、組織ID の昇順
        OrderCondition orderBy = new OrderCondition();
        orderBy.addCondition(OrganizationMstDao.COND_ORGANIZATION_KNM, OrderCondition.ASC);
        orderBy.addCondition(OrganizationMstDao.COND_ORGANIZATION_ID, OrderCondition.ASC);

        // 検索を行う
        List<OrganizationMstDto> resultDto = dao.searchWithOr(SetConditions(dto), orderBy, new OrganizationMstDto());

        // DTOの初期化
        OrganizationMstDto dtoResult = new OrganizationMstDto();

        // データありの場合
        if (resultDto.get(0).getReturnCode() == NaikaraTalkConstants.RETURN_CD_DATA_YES) {

            dtoResult = resultDto.get(0);
        }

        return dtoResult;
    }

    /**
     * 検索条件の設定。<br>
     * <br>
     * 検索条件の設定。<br>
     * <br>
     * @param OrganizationMstDto 画面のパラメータ<br>
     * @return ConditionMapper:conditions 検索条件<br>
     * @exception NaiException
     */
    private ConditionMapper SetConditions(OrganizationMstDto dto) throws NaiException {

        // 検索条件の作成
        ConditionMapper conditions = new ConditionMapper();

        StringBuffer sb = new StringBuffer();

        conditions.addCondition(OrganizationMstDao.COND_CONTRACT_STR_DT, ConditionMapper.OPERATOR_LESS_EQUAL,
                DateUtil.getSysDate());

        conditions.addCondition(OrganizationMstDao.COND_CONTRACT_END_DT, ConditionMapper.OPERATOR_LARGE_EQUAL,
                DateUtil.getSysDate());

        // 組織IDを入力されている場合
        if (!StringUtils.isEmpty(dto.getOrganizationId())) {
            sb.setLength(0);
            sb.append(NaikaraTalkConstants.OPERATOR_PERCENT).append(dto.getOrganizationId())
                    .append(NaikaraTalkConstants.OPERATOR_PERCENT);
            conditions.addCondition(OrganizationMstDao.COND_ORGANIZATION_ID, ConditionMapper.OPERATOR_LIKE,
                    sb.toString());
        }

        // 組織名を入力されている場合
        if (!StringUtils.isEmpty(dto.getOrganizationJnm())) {
            sb.setLength(0);
            sb.append(NaikaraTalkConstants.OPERATOR_PERCENT).append(dto.getOrganizationJnm())
                    .append(NaikaraTalkConstants.OPERATOR_PERCENT);
            conditions.addCondition(OrganizationMstDao.COND_ORGANIZATION_JNM, ConditionMapper.OPERATOR_LIKE,
                    sb.toString());
        }

        // 戻り値
        return conditions;

    }

}
