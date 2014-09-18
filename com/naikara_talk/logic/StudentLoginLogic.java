package com.naikara_talk.logic;

import java.sql.Connection;
import java.util.List;

import com.naikara_talk.dao.StudentMstDao;
import com.naikara_talk.dao.TableCountDao;
import com.naikara_talk.dbutil.ConditionMapper;
import com.naikara_talk.dbutil.OrderCondition;
import com.naikara_talk.dto.StudentMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>お客様（個人）<br>
 * <b>クラス名称　　　:</b>ログイン(お客様（個人）)Logicクラス。<br>
 * <b>クラス概要　　　:</b>お客様（個人）責任者の認証処理Logic。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/07/25 TECS 新規作成。
 */
public class StudentLoginLogic {

    // コネクション変数
    public Connection conn = null;

    // コンストラクタ
    public StudentLoginLogic(Connection con) {
        this.conn = con;
    }

    /**
     * 検索データ件数取得<br>
     * <br>
     * 検索データ件数取得を行う<br>
     * <br>
     * @param StudentMstDto
     * @return DataCount 件数<br>
     * @exception NaiException
     */
    public int getRowCount(StudentMstDto dto) throws NaiException {

        // 初期化処理
        TableCountDao dao = new TableCountDao(this.conn);

        // 一覧のデータ件数を取得する
        int DataCount = dao.rowCount(NaikaraTalkConstants.STUDENT_MST, SetConditions(dto));

        // 戻り値
        return DataCount;
    }

    /**
     * 利用者取得<br>
     * <br>
     * 利用者の情報取得を行う<br>
     * <br>
     * @param StudentMstDto
     * @return dtoResult 検索結果<br>
     * @exception Exception
     */
    public StudentMstDto selectList(StudentMstDto dto) throws Exception {

        // DAOの初期化
        StudentMstDao dao = new StudentMstDao(this.conn);

        // 並び順の初期化
        OrderCondition orderBy = new OrderCondition();

        // 検索を実行
        List<StudentMstDto> resultDto = dao.search(SetConditions(dto), orderBy);

        // DTOの初期化
        StudentMstDto dtoResult = new StudentMstDto();

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
     * @param StudentMstDto
     * @return conditions 検索条件<br>
     * @exception NaiException
     */
    private ConditionMapper SetConditions(StudentMstDto dto) throws NaiException {

        // 検索条件の作成
        ConditionMapper conditions = new ConditionMapper();

        // ログインIDを入力されている場合
        conditions.addCondition(StudentMstDao.COND_STUDENT_ID, ConditionMapper.OPERATOR_EQUAL, dto.getStudentId());

        // 戻り値
        return conditions;
    }

    /**
     * 更新処理。<br>
     * <br>
     * 更新処理。<br>
     * <br>
     * @param StudentMstDto 画面のパラメータ<br>
     * @return int<br>
     * @exception NaiException
     */
    public int update(StudentMstDto dto) throws NaiException {
        // DAOの初期化
        StudentMstDao dao = new StudentMstDao(this.conn);

        // 更新を行う
        return dao.loginUpdate(dto);
    }

}
