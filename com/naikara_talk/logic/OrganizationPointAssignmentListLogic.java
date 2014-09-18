package com.naikara_talk.logic;

import java.sql.Connection;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dao.OrganizationMstDao;
import com.naikara_talk.dao.OrganizationPointAssignmentListDao;
import com.naikara_talk.dao.StudentMstDao;
import com.naikara_talk.dbutil.ConditionMapper;
import com.naikara_talk.dbutil.OrderCondition;
import com.naikara_talk.dto.OrganizationMstDto;
import com.naikara_talk.dto.OrganizationPointAssignmentListDto;
import com.naikara_talk.dto.StudentMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>ポイント割振り<br>
 * <b>クラス名称　　　:</b>ポイント割振りクラス。<br>
 * <b>クラス概要　　　:</b>ポイント割振りLOGIC。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 *
 * @author TECS <b>変更履歴　　　　:</b>2013/07/17 TECS 新規作成。
 */
public class OrganizationPointAssignmentListLogic {

	// コネクション変数
	public Connection conn = null;

	// コンストラクタ
	public OrganizationPointAssignmentListLogic(Connection con) {
		this.conn = con;
	}

	/**
	 * 受講者マスタデータ取得。
	 * @param dto ポイント割振りDTO
	 * @return　list
	 * @throws NaiException
	 */
	public List<StudentMstDto> searchStudent(OrganizationPointAssignmentListDto dto) throws NaiException {

		ConditionMapper  cm = new ConditionMapper();
		OrderCondition  oc = new OrderCondition();
		StudentMstDao dao = new StudentMstDao(this.conn);

		// 必須条件：組織ID
		cm.addCondition(StudentMstDao.COND_ORGANIZATION_ID, ConditionMapper.OPERATOR_EQUAL, dto.getOrganizationId());

		// 利用開始日
		////cm.addCondition(StudentMstDao.COND_USE_STR_DT, ConditionMapper.OPERATOR_LESS_EQUAL, dto.getContractFrom());
		//cm.addCondition(StudentMstDao.COND_USE_STR_DT, ConditionMapper.OPERATOR_LARGE_EQUAL, dto.getContractFrom());
		cm.addCondition(StudentMstDao.COND_USE_STR_DT, ConditionMapper.OPERATOR_LESS_EQUAL, dto.getContractTo());

		// 利用終了日
		cm.addCondition(StudentMstDao.COND_USE_END_DT, ConditionMapper.OPERATOR_LARGE_EQUAL, dto.getContractFrom());
		////cm.addCondition(StudentMstDao.COND_USE_END_DT, ConditionMapper.OPERATOR_LARGE_EQUAL, dto.getContractTo());


		// 画面任意条件：受講者所属部署
		if (StringUtils.isNotEmpty(dto.getStudentPosition())) {
			StringBuffer sb = new StringBuffer();
			sb.append(NaikaraTalkConstants.OPERATOR_PERCENT).append(dto.getStudentPosition()).append(NaikaraTalkConstants.OPERATOR_PERCENT);
			cm.addCondition(StudentMstDao.COND_STUDENT_POSITION, ConditionMapper.OPERATOR_LIKE, sb.toString());
		}
		// 画面任意条件：所属組織内IDFrom
		if (StringUtils.isNotEmpty(dto.getPositionOrganizationIdFrom())) {
			cm.addCondition(StudentMstDao.COND_POSITION_ORGANIZATION_ID, ConditionMapper.OPERATOR_LARGE_EQUAL, dto.getPositionOrganizationIdFrom());
		}
		// 画面任意条件：所属組織内IDTo
		if (StringUtils.isNotEmpty(dto.getPositionOrganizationIdTo())) {
			cm.addCondition(StudentMstDao.COND_POSITION_ORGANIZATION_ID, ConditionMapper.OPERATOR_LESS_EQUAL, dto.getPositionOrganizationIdTo());
		}

		// データ取得処理
		return dao.search(cm, oc);

	}

	/**
	 * 組織マスタデータ取得。
	 * @param dto ポイント割振りDTO
	 * @return list
	 * @throws NaiException
	 */
	public List<OrganizationMstDto> searchOrganization(OrganizationPointAssignmentListDto dto) throws NaiException {

		ConditionMapper cm = new ConditionMapper();
		OrderCondition oc = new OrderCondition();
		OrganizationMstDao dao = new OrganizationMstDao(this.conn);

		// 組織ID
		cm.addCondition(StudentMstDao.COND_ORGANIZATION_ID, ConditionMapper.OPERATOR_EQUAL, dto.getOrganizationId());
		// 契約開始日
		cm.addCondition(OrganizationMstDao.COND_CONTRACT_STR_DT,ConditionMapper.OPERATOR_EQUAL, dto.getContractFrom());
		// 契約終了日
		cm.addCondition(OrganizationMstDao.COND_CONTRACT_END_DT,ConditionMapper.OPERATOR_EQUAL, dto.getContractTo());

		return dao.search(cm, oc);

	}

	/**
	 * ポイント所有テーブルより残高ポイントの取得を行う (集計値)。
	 * @param dto ポイント割振りDTO
	 * @return OrganizationPointAssignmentListDto
	 * @throws NaiException
	 */
	public OrganizationPointAssignmentListDto getBalancePoint(OrganizationPointAssignmentListDto dto) throws NaiException {

		OrganizationPointAssignmentListDao dao = new OrganizationPointAssignmentListDao(this.conn);

		return dao.getBalancePoint(dto);

	}

	/**
	 * 購入／無償ポイントと残高ポイントの取得。
	 * @param dto ポイント割振りDTO
	 * @return OrganizationPointAssignmentListDto
	 * @throws NaiException
	 */
	public OrganizationPointAssignmentListDto getPointOwnershipTrn(OrganizationPointAssignmentListDto dto) throws NaiException {

		OrganizationPointAssignmentListDao dao = new OrganizationPointAssignmentListDao(this.conn);

		// 購入／無償ポイントと残高ポイントの取得
		return dao.getPointOwnershipTrn(dto);

	}

	/**
	 * 更新処理。
	 * @param dto ポイント割振りDTO
	 * @param list 画面一覧のデータ
	 * @return
	 * @throws NaiException
	 */
    public int update(OrganizationPointAssignmentListDto dto ,List<OrganizationPointAssignmentListDto> list) throws NaiException {

    	OrganizationPointAssignmentListDao dao = new OrganizationPointAssignmentListDao(this.conn);
        try {
        	// 更新処理
            return dao.update(dto ,list);
        } catch (Exception e) {
        	return NaikaraTalkConstants.RETURN_CD_ERR_UPD;
        }
    }

}
