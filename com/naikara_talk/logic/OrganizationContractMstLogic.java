package com.naikara_talk.logic;

import java.sql.Connection;
import java.util.List;

import com.naikara_talk.dao.OrganizationMstDao;
import com.naikara_talk.dao.StudentMstDao;
import com.naikara_talk.dbutil.ConditionMapper;
import com.naikara_talk.dbutil.OrderCondition;
import com.naikara_talk.dto.OrganizationMstDto;
import com.naikara_talk.dto.StudentMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.model.OrganizationContractMstListModel;
import com.naikara_talk.service.OrganizationContractMstLoadService;
import com.naikara_talk.service.OrganizationContractMstUpdateService;
import com.naikara_talk.util.DateUtil;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>組織契約情報登録ページ。<br>
 * <b>クラス名称       :</b>組織契約情報登録ページLogicクラス。<br>
 * <b>クラス概要       :</b>組織契約情報登録<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/09 TECS 新規作成
 */
public class OrganizationContractMstLogic {

    // コネクション変数
    public Connection conn = null;

    // コンストラクタ
    public OrganizationContractMstLogic(Connection con) {
        this.conn = con;
    }

    /**
     * 組織マスタのデータ取得処理。<br>
     * <br>
     * @param dto OrganizationMstDto
     * @return dtoResult OrganizationMstDto
     * @throws NaiException
     */
    public List<OrganizationMstDto> selectData(OrganizationMstDto dto, String processKbn, String searchKye)
            throws NaiException {

        OrganizationMstDao dao = new OrganizationMstDao(this.conn);

        // 検索条件の作成
        ConditionMapper conditions = new ConditionMapper();
        // 並び順
        OrderCondition orderBy = new OrderCondition();

        // 組織契約情報登録ロードとデータの存在チェック用
        if (searchKye.equals(OrganizationContractMstLoadService.SEARCH_KYE_ZERO)) {

            conditions.addCondition(OrganizationMstDao.COND_ORGANIZATION_ID, ConditionMapper.OPERATOR_EQUAL,
                    dto.getOrganizationId());

            conditions.addCondition(OrganizationMstDao.COND_CONS_SEQ, ConditionMapper.OPERATOR_EQUAL, dto.getConsSeq());

            if (processKbn.equals(OrganizationContractMstListModel.PROS_KBN_UPD)) {
                conditions.addCondition(OrganizationMstDao.COND_CONTRACT_END_DT, ConditionMapper.OPERATOR_LARGE_EQUAL,
                        DateUtil.getSysDate());
            }
            orderBy.addCondition(OrganizationMstDao.COND_ORGANIZATION_ID, OrderCondition.ASC);
        }

        //  DBアクセスありチェック、現在のデータの1つ前の情報の取得用
        if (searchKye.equals(OrganizationContractMstUpdateService.SEARCH_KYE_ONE)) {

            conditions.addCondition(OrganizationMstDao.COND_ORGANIZATION_ID, ConditionMapper.OPERATOR_EQUAL,
                    dto.getOrganizationId());

            conditions.addCondition(OrganizationMstDao.COND_CONS_SEQ, ConditionMapper.OPERATOR_LESS_EQUAL,
                    dto.getConsSeq() - 1);
        }

        //  DBアクセスありチェック、現在のデータの1つ後の情報の取得
        if (searchKye.equals(OrganizationContractMstUpdateService.SEARCH_KYE_TWO)) {

            conditions.addCondition(OrganizationMstDao.COND_ORGANIZATION_ID, ConditionMapper.OPERATOR_EQUAL,
                    dto.getOrganizationId());

            conditions.addCondition(OrganizationMstDao.COND_CONS_SEQ, ConditionMapper.OPERATOR_LARGE_EQUAL,
                    dto.getConsSeq() + 1);

            orderBy.addCondition(OrganizationMstDao.COND_CONS_SEQ, OrderCondition.ASC);
        }

        //  組織ID単位での情報の取得
        if (searchKye.equals(OrganizationContractMstUpdateService.SEARCH_KYE_THREE)) {

            conditions.addCondition(OrganizationMstDao.COND_ORGANIZATION_ID, ConditionMapper.OPERATOR_EQUAL,
                    dto.getOrganizationId());

        }

        // 講師マスタから対象データの取得
        List<OrganizationMstDto> resultDtoList = dao.search(conditions, orderBy);

        return resultDtoList;

    }

    /**
     * 登録、流用作成処理。<br>
     * <br>
     * @param OrganizationMstDto 画面のパラメータ
     * @return なし
     * @throws NaiException
     */
    public int insert(OrganizationMstDto dto) throws NaiException {
        OrganizationMstDao dao = new OrganizationMstDao(this.conn);
        return dao.insert(dto);

    }

    /**
     * 更新処理。<br>
     * <br>
     * @param OrganizationMstDto 画面のパラメータ
     * @return int
     * @throws NaiException
     */
    public int update(OrganizationMstDto dto) throws NaiException {
        OrganizationMstDao dao = new OrganizationMstDao(this.conn);
        return dao.update(dto);

    }

    /**
     * 削除処理。<br>
     * <br>
     * @param OrganizationMstDto 画面のパラメータ
     * @return int
     * @throws NaiException
     */
    public int delete(OrganizationMstDto dto) throws NaiException {
        OrganizationMstDao dao = new OrganizationMstDao(this.conn);
        return dao.delete(dto);

    }

    /**
     * 受講者マスタのデータ取得処理。<br>
     * <br>
     * @param StudentMstDto 画面のパラメータ
     * @return StudentMstDto resultDtoList
     * @throws NaiException
     */
    public List<StudentMstDto> searchStudentMst(StudentMstDto dto) throws NaiException {
        StudentMstDao dao = new StudentMstDao(this.conn);
        // 検索条件の作成
        ConditionMapper conditions = new ConditionMapper();

        conditions.addCondition(StudentMstDao.COND_ORGANIZATION_ID, ConditionMapper.OPERATOR_EQUAL,
                dto.getOrganizationId());

        conditions.addCondition(StudentMstDao.COND_USE_STOP_FLG, ConditionMapper.OPERATOR_EQUAL,
                NaikaraTalkConstants.USE_KBN_OK);
        // 並び順
        OrderCondition orderBy = new OrderCondition();

        // 講師マスタから対象データの取得
        List<StudentMstDto> resultDtoList = dao.search(conditions, orderBy);

        return resultDtoList;

    }

    /**
     * 受講者マスタの更新処理。<br>
     * <br>
     * @param StudentMstDto 画面のパラメータ
     * @return StudentMstDto resultDtoList
     * @throws NaiException
     */
    public int updateStudentMst(StudentMstDto dto) throws NaiException {
        StudentMstDao dao = new StudentMstDao(this.conn);
        return dao.update(dto);

    }

}
