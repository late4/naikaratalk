package com.naikara_talk.logic;

import java.sql.Connection;
import java.util.List;

import com.naikara_talk.dao.TableCountDao;
import com.naikara_talk.dao.TeacherMstDao;
import com.naikara_talk.dao.UserMstDao;
import com.naikara_talk.dbutil.ConditionMapper;
import com.naikara_talk.dbutil.OrderCondition;
import com.naikara_talk.dto.TeacherMstDto;
import com.naikara_talk.dto.UserMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>初期処理<br>
 * <b>クラス名称　　　:</b>サービス提供ページLogicクラス。<br>
 * <b>クラス概要　　　:</b>講師の認証処理Logic。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/07/02 TECS 新規作成。
 */
public class TeacherLoginLogic {

    // コネクション変数
    public Connection conn = null;

    // コンストラクタ
    public TeacherLoginLogic(Connection con) {
        this.conn = con;
    }

    /**
     * 検索データ件数取得<br>
     * <br>
     * 検索データ件数取得を行う<br>
     * <br>
     * @param UserMstDto
     * @return DataCount 件数<br>
     * @exception NaiException
     */
    public int getRowCount(UserMstDto dto) throws NaiException {

        // 初期化処理
        TableCountDao dao = new TableCountDao(this.conn);

        // 一覧のデータ件数を取得する
        int DataCount = dao.rowCount(NaikaraTalkConstants.USER_MST, SetConditions(dto));

        // 戻り値
        return DataCount;

    }

    /**
     * 利用者取得<br>
     * <br>
     * 利用者の情報取得を行う<br>
     * <br>
     * @param UserMstDto
     * @return dtoResult 検索結果<br>
     * @exception Exception
     */
    public UserMstDto selectList(UserMstDto dto) throws Exception {

        // DAOの初期化
        UserMstDao dao = new UserMstDao(this.conn);

        // 並び順の初期化
        OrderCondition orderBy = new OrderCondition();

        // 検索を実行
        List<UserMstDto> resultDto = dao.search(SetConditions(dto), orderBy);

        // DTOの初期化
        UserMstDto dtoResult = new UserMstDto();

        // データありの場合
        if (0 < resultDto.size()) {

            dtoResult = resultDto.get(0);
        }

        return dtoResult;
    }

    /**
     * 利用者取得<br>
     * <br>
     * 利用者の情報取得を行う<br>
     * <br>
     * @param UserMstDto
     * @return dtoResult 検索結果<br>
     * @exception Exception
     */
    public TeacherMstDto selectNm(TeacherMstDto dto) throws Exception {

        // DAOの初期化
        TeacherMstDao dao = new TeacherMstDao(this.conn);

        // 並び順の初期化
        OrderCondition orderBy = new OrderCondition();

        // 検索を実行
        List<TeacherMstDto> resultDto = dao.search(SetConditionsT(dto), orderBy);

        // DTOの初期化
        TeacherMstDto dtoResult = new TeacherMstDto();

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
     * @param UserMstDto
     * @return conditions 検索条件<br>
     * @exception NaiException
     */
    private ConditionMapper SetConditions(UserMstDto dto) throws NaiException {

        // 検索条件の作成
        ConditionMapper conditions = new ConditionMapper();

        // ログインIDを入力されている場合
        conditions.addCondition(UserMstDao.COND_USER_ID, ConditionMapper.OPERATOR_EQUAL, dto.getUserId());

        // 戻り値
        return conditions;

    }

    /**
     * 検索条件設定<br>
     * <br>
     * 検索条件を設定<br>
     * <br>
     * @param UserMstDto
     * @return conditions 検索条件<br>
     * @exception NaiException
     */
    private ConditionMapper SetConditionsT(TeacherMstDto dto) throws NaiException {

        // 検索条件の作成
        ConditionMapper conditions = new ConditionMapper();

        // ログインIDを入力されている場合
        conditions.addCondition(TeacherMstDao.COND_USER_ID, ConditionMapper.OPERATOR_EQUAL, dto.getUserId());

        // 戻り値
        return conditions;

    }

}
