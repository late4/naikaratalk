package com.naikara_talk.logic;

import java.sql.Connection;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dao.UserMstTeacherMstDao;
import com.naikara_talk.dbutil.ConditionMapper;
import com.naikara_talk.dbutil.OrderCondition;
import com.naikara_talk.dto.UserMstTeacherMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>一覧_講師選択ページ。<br>
 * <b>クラス名称       :</b>一覧_講師選択ページLogicクラス。<br>
 * <b>クラス概要       :</b>一覧_講師選択<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/09 TECS 新規作成
 */
public class TeacherSelectionListLogic {

    // コネクション変数
    public Connection conn = null;

    // コンストラクタ
    public TeacherSelectionListLogic(Connection con) {
        this.conn = con;
    }

    /**
     * 利用者マスタ、講師マスタのデータ取得処理。<br>
     * <br>
     * @param dto TeacherMstDto
     * @return dtoResult TeacherMstDto
     * @throws NaiException
     */
    public List<UserMstTeacherMstDto> selectData(UserMstTeacherMstDto dto) throws NaiException {

        UserMstTeacherMstDao dao = new UserMstTeacherMstDao(this.conn);

        // 検索条件の作成
        ConditionMapper conditions = new ConditionMapper();
        
        if (!StringUtils.isEmpty(dto.getUserId())) {
            conditions.addCondition(
                    UserMstTeacherMstDao.COND_USER_ID,
                    ConditionMapper.OPERATOR_LIKE,
                    new StringBuffer().append(NaikaraTalkConstants.OPERATOR_PERCENT).append(dto.getUserId())
                            .append(NaikaraTalkConstants.OPERATOR_PERCENT).toString());
        }

        conditions.addCondition(UserMstTeacherMstDao.COND_CLASSIFICATION_KBN, ConditionMapper.OPERATOR_EQUAL,
                NaikaraTalkConstants.AUTHORITY_T);
        // 並び順
        OrderCondition orderBy = new OrderCondition();
        orderBy.addCondition(UserMstTeacherMstDao.COND_FAMILY_JNM, OrderCondition.ASC);
        orderBy.addCondition(UserMstTeacherMstDao.COND_FIRST_JNM, OrderCondition.ASC);

        // 講師マスタから対象データの取得
        List<UserMstTeacherMstDto> resultDtoList = dao.search(conditions, orderBy, dto);

        return resultDtoList;

    }

    /**
     * 利用者マスタのデータ件数取得処理。<br>
     * <br>
     * @param dto TeacherMstDto
     * @return dtoResult TeacherMstDto
     * @throws NaiException
     */
    public int selectCount(UserMstTeacherMstDto dto) throws NaiException {

        UserMstTeacherMstDao dao = new UserMstTeacherMstDao(this.conn);

        // 検索条件の作成
        ConditionMapper conditions = new ConditionMapper();
        
        if (!StringUtils.isEmpty(dto.getUserId())) {
            conditions.addCondition(
                    UserMstTeacherMstDao.COND_USER_ID,
                    ConditionMapper.OPERATOR_LIKE,
                    new StringBuffer().append(NaikaraTalkConstants.OPERATOR_PERCENT).append(dto.getUserId())
                            .append(NaikaraTalkConstants.OPERATOR_PERCENT).toString());
        }
        conditions.addCondition(UserMstTeacherMstDao.COND_CLASSIFICATION_KBN, ConditionMapper.OPERATOR_EQUAL,
                NaikaraTalkConstants.AUTHORITY_T);

        // 時差管理マスタから対象データの取得
        int count = dao.rowCount(conditions, dto);

        return count;

    }
}
