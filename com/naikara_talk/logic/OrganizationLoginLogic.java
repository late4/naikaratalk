package com.naikara_talk.logic;

import java.sql.Connection;
import java.util.List;

import com.naikara_talk.dao.OrganizationMstDao;
import com.naikara_talk.dao.TableCountDao;
import com.naikara_talk.dbutil.ConditionMapper;
import com.naikara_talk.dbutil.OrderCondition;
import com.naikara_talk.dto.OrganizationMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.util.DateUtil;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>法人<br>
 * <b>クラス名称　　　:</b>ログイン(組織)Logicクラス。<br>
 * <b>クラス概要　　　:</b>組織責任者の認証処理Logic。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/07/05 TECS 新規作成。
 */
public class OrganizationLoginLogic {

	// コネクション変数
	public Connection conn = null;

	// コンストラクタ
	public OrganizationLoginLogic(Connection con) {
		this.conn = con;
	}

    /**
     * 検索データ件数取得<br>
     * <br>
     * 検索データ件数取得を行う<br>
     * <br>
     * @param OrganizationMstDto
     * @return DataCount 件数<br>
     * @exception NaiException
     */
    public int getRowCount(OrganizationMstDto dto) throws NaiException {

        // 初期化処理
        TableCountDao dao = new TableCountDao(this.conn);

        // 一覧のデータ件数を取得する
        int DataCount = dao.rowCount(NaikaraTalkConstants.ORGANIZATION_MST, SetConditions(dto));

        // 戻り値
        return DataCount;

    }

    /**
     * 利用者取得<br>
     * <br>
     * 利用者の情報取得を行う<br>
     * <br>
     * @param OrganizationMstDto
     * @return dtoResult 検索結果<br>
     * @exception Exception
     */
    public OrganizationMstDto selectList(OrganizationMstDto dto) throws Exception {

        // DAOの初期化
        OrganizationMstDao dao = new OrganizationMstDao(this.conn);

        // 並び順の初期化
        OrderCondition orderBy = new OrderCondition();

        // 検索を実行
        List<OrganizationMstDto> resultDto = dao.search(SetConditions(dto), orderBy);

        // DTOの初期化
        OrganizationMstDto dtoResult = new OrganizationMstDto();

        // データありの場合
        if (0 < resultDto.size()) {

            dtoResult = resultDto.get(0);
        }

        return dtoResult;

    }

    /**
     * 検索条件設定<br>
     * <br>
     * 検索条件を設定<br>
     * <br>
     * @param OrganizationMstDto
     * @return conditions 検索条件<br>
     * @exception NaiException
     */
    private ConditionMapper SetConditions(OrganizationMstDto dto) throws NaiException {

        // 検索条件の作成
        ConditionMapper conditions = new ConditionMapper();

        // ログインIDを入力されている場合
        conditions.addCondition(OrganizationMstDao.COND_ORGANIZATION_ID, ConditionMapper.OPERATOR_EQUAL,
                dto.getOrganizationId());
        conditions.addCondition(OrganizationMstDao.COND_CONTRACT_STR_DT, ConditionMapper.OPERATOR_LESS_EQUAL,
                DateUtil.getSysDate());
        conditions.addCondition(OrganizationMstDao.COND_CONTRACT_END_DT, ConditionMapper.OPERATOR_LARGE_EQUAL,
                DateUtil.getSysDate());

        // 戻り値
        return conditions;

    }

}
