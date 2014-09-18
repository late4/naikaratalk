package com.naikara_talk.logic;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.naikara_talk.dao.CodeManagMstDao;
import com.naikara_talk.dbutil.ConditionMapper;
import com.naikara_talk.dto.CodeManagMstDto;
import com.naikara_talk.exception.NaiException;

public class CodeManagMstLstLogic {

    // コネクション変数
    public Connection conn = null;

    // コンストラクタ
    public CodeManagMstLstLogic(Connection con) {
        this.conn = con;
    }


    /**
     * コード管理マスタから特定コード種別のリストを作成する。
     * @param category
     * @return
     * @throws SQLException
     */
    public List<CodeManagMstDto> getList(String category) throws NaiException {

        ConditionMapper conditions = new ConditionMapper();

        conditions.addCondition(CodeManagMstDao.COND_CD_CATEGORY, ConditionMapper.OPERATOR_EQUAL, category);

        CodeManagMstDao dao = new CodeManagMstDao(this.conn);
            return dao.search(conditions);

    }
}
