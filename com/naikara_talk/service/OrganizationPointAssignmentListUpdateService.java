package com.naikara_talk.service;

import java.sql.Connection;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dbutil.DbUtil;
import com.naikara_talk.dto.OrganizationPointAssignmentListDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.logic.OrganizationPointAssignmentListLogic;
import com.naikara_talk.model.OrganizationPointAssignmentListModel;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>ポイント割振り<br>
 * <b>クラス名称　　　:</b>ポイント割振り初期処理クラス。<br>
 * <b>クラス概要　　　:</b>ポイント割振り初期処理Service。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 *
 * @author TECS <b>変更履歴　　　　:</b>2013/07/17 TECS 新規作成。
 */
public class OrganizationPointAssignmentListUpdateService implements ActionService {

	/**
	 *更新処理。
	 * @param model ポイント割振り初期処理Model
	 * @param list 画面一覧のlist
	 * @return int
	 * @throws NaiException
	 */
	public int update(OrganizationPointAssignmentListModel model,
			List<OrganizationPointAssignmentListDto> list) throws NaiException {

        int cnt = NaikaraTalkConstants.RETURN_CD_ERR_UPD;
		// コネクション変数
		Connection conn = null;

		try {
			// コネクションを取得
			conn = DbUtil.getConnection();

        	OrganizationPointAssignmentListLogic logic = new OrganizationPointAssignmentListLogic(conn);
            // DTOの初期化
        	OrganizationPointAssignmentListDto dto = new OrganizationPointAssignmentListDto();
            // Model値をDTOにセット
        	dto = this.modelToDto(model);

            // 更新実行
            cnt = logic.update(dto ,list);

            if (cnt > NaikaraTalkConstants.RETURN_CD_ERR_NO_UPD) {
            	// 正常に更新
            	conn.commit();
            } else {
            	conn.rollback();
            }

		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (Exception e1) {
				e1.printStackTrace();
				throw new NaiException(e1);
			}
			throw new NaiException(e);
		}  finally {
			try {
				conn.close();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
        return cnt;
    }

    /**
	 * Model値をDTOにセット。
     * @param model ポイント割振り初期処理Model
     * @return OrganizationPointAssignmentListDto
	 */
    private OrganizationPointAssignmentListDto modelToDto(OrganizationPointAssignmentListModel model) throws NaiException {

        // DTOの初期化
    	OrganizationPointAssignmentListDto prmDto = new OrganizationPointAssignmentListDto();

    	// 契約期間
		String contract= model.getContract();
		String split[] = StringUtils.split(contract, "-");
		// 契約開始日
		prmDto.setContractFrom(StringUtils.replaceChars(split[0], "/", ""));
		// 契約終了日
		prmDto.setContractTo(StringUtils.replaceChars(split[1], "/", ""));

    	// 未割振ポイント(残高)
        prmDto.setBalancePointNumDown(model.getBalancePointNumDown());
        // 組織ID
        prmDto.setOrganizationId(model.getOrganizationId());
        // 連番
        prmDto.setConsSeq(model.getConsSeq());
        // レコードバージョン番号
        prmDto.setRecordVerNo(model.getRecordVerNo());

    	return prmDto;

    }

}
