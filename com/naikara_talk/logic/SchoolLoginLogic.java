package com.naikara_talk.logic;

import java.sql.Connection;
import java.util.List;

import com.naikara_talk.dao.TableCountDao;
import com.naikara_talk.dao.UserMstDao;
import com.naikara_talk.dbutil.ConditionMapper;
import com.naikara_talk.dbutil.OrderCondition;
import com.naikara_talk.dto.UserMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>ログイン・メニュー。<br>
 * <b>クラス名称　　　:</b>会社側のログイン処理Logicクラス。<br>
 * <b>クラス概要　　　:</b>会社側のログイン処理Logic。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/07/02 TECS 新規作成。
 */
public class SchoolLoginLogic {

    // コネクション変数
    public Connection conn = null;

    // コンストラクタ
    public SchoolLoginLogic(Connection con) {
        this.conn = con;
    }

    /**
     * 検索データ件数取得<br>
     * <br>
     * 検索データ件数取得を行う<br>
     * <br>
     * @param dto 検索条件<br>
     * @return dataCount 件数<br>
     * @exception NaiException
     */
    public int getRowCount(UserMstDto dto) throws NaiException {

        // DAOの初期化
        TableCountDao dao = new TableCountDao(this.conn);

        // データ件数を取得
        int dataCount = dao.rowCount(NaikaraTalkConstants.USER_MST, setConditions(dto));

        // 戻り値
        return dataCount;
    }

    /**
     * 利用者取得<br>
     * <br>
     * 利用者取得を行う<br>
     * <br>
     * @param dto 検索条件<br>
     * @return retDto 検索結果<br>
     * @exception Exception
     */
    public UserMstDto selectUser(UserMstDto dto) throws Exception {

        // DAOの初期化
        UserMstDao dao = new UserMstDao(this.conn);

        // 並び順:利用者ID の昇順
        OrderCondition orderBy = new OrderCondition();

        // 検索を実行
        List<UserMstDto> resultDto = dao.search(setConditions(dto), orderBy);

        // DTOの初期化
        UserMstDto dtoResult = new UserMstDto();

        // データありの場合
        if (resultDto.get(0).getReturnCode() != NaikaraTalkConstants.RETURN_CD_DATA_NO) {

            dtoResult = resultDto.get(0);
        }

        return dtoResult;
    }

    /**
     * 検索条件設定<br>
     * <br>
     * 検索条件を設定<br>
     * <br>
     * @param dto 検索条件<br>
     * @return conditions 設定後の検索条件<br>
     * @exception NaiException
     */
    private ConditionMapper setConditions(UserMstDto dto) throws NaiException {

        // 検索条件の初期化
        ConditionMapper conditions = new ConditionMapper();

        // 検索条件を設定
        conditions.addCondition(UserMstDao.COND_USER_ID, ConditionMapper.OPERATOR_EQUAL, dto.getUserId());

        return conditions;
    }
}