package com.naikara_talk.logic;

import java.sql.Connection;
import java.util.List;

import com.naikara_talk.dao.TableCountDao;
import com.naikara_talk.dao.TeaBooMTeaMUsrMDao;
import com.naikara_talk.dao.TeacherBookmarkDao;
import com.naikara_talk.dao.TeacherCourseDao;
import com.naikara_talk.dao.UserMstDao;
import com.naikara_talk.dbutil.ConditionMapper;
import com.naikara_talk.dbutil.OrderCondition;
import com.naikara_talk.dto.TeaBooMTeaMUsrMDto;
import com.naikara_talk.dto.TeacherBookmarkMstDto;
import com.naikara_talk.dto.TeacherCourseDto;
import com.naikara_talk.dto.UserMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>お客様(個人)_予約処理<br>
 * <b>クラス名称       :</b>ブックマーク済の講師一覧（Pop）Logicクラス<br>
 * <b>クラス概要       :</b>ブックマーク済の講師一覧情報を処理する。<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/08/13 TECS 新規作成
 */
public class TeacherBookmarkListLogic {

    // コネクション変数
    public Connection conn = null;

    // コンストラクタ
    public TeacherBookmarkListLogic(Connection con) {
        this.conn = con;
    }

    /**
     * 受講者別講師ブックマークテーブルのデータ取得。<br>
     * <br>
     * 受講者別講師ブックマークテーブルからデータを取得する。<br>
     * <br>
     * @param model モデル<br>
     * @return List<TeaBooMTeaMUsrMDto> 受講者別講師ブックマークテーブル、講師マスタ、利用者マスタDTOリスト<br>
     * @throws NaiException
     */
    public List<TeaBooMTeaMUsrMDto> selectTeacherBookmarkList(TeaBooMTeaMUsrMDto dto) throws NaiException {

        // 初期化
        TeaBooMTeaMUsrMDao dao = new TeaBooMTeaMUsrMDao(this.conn);

        // 検索条件の作成
        ConditionMapper conditions = new ConditionMapper();

        // コース別利用ポイントマスタ．コースコード
        conditions.addCondition(TeaBooMTeaMUsrMDao.COND_TBM_STUDENT_ID, ConditionMapper.OPERATOR_EQUAL,
                dto.getStudentId());

        // 並び順
        OrderCondition orderBy = new OrderCondition();
        // 講師マスタ．ニックネーム の昇順
        orderBy.addCondition(TeaBooMTeaMUsrMDao.COND_TM_NICK_ANM, OrderCondition.ASC);
        // 講師マスタ．利用者ID の昇順
        orderBy.addCondition(TeaBooMTeaMUsrMDao.COND_TM_USER_ID, OrderCondition.ASC);

        // データを取得する
        return dao.search(conditions, orderBy);
    }

    /**
     * 講師別コースマスタ(+コースマスタ)リスト取得。<br>
     * <br>
     * 講師別コースマスタ(+コースマスタ)リスト取得を行う。<br>
     * <br>
     * @param dto 講師別コースマスタ(+コースマスタ)DTO<br>
     * @return List<TeacherCourseDto> 講師別コースマスタ(+コースマスタ)DTOリスト<br>
     * @throws NaiException
     */
    public List<TeacherCourseDto> selectTeacherCourseDtoList(TeacherCourseDto dto) throws NaiException {

        // 初期化
        TeacherCourseDao dao = new TeacherCourseDao(this.conn);

        // 検索条件の作成
        ConditionMapper conditions = new ConditionMapper();

        // 受講者ID
        conditions.addCondition(TeacherCourseDao.COND_USER_ID, ConditionMapper.OPERATOR_EQUAL, dto.getUserId());
        // 提供開始日
        conditions.addCondition(TeacherCourseDao.COND_USE_STR_DT, ConditionMapper.OPERATOR_LESS_EQUAL,
                dto.getUseStrDt());
        // 提供終了日
        conditions.addCondition(TeacherCourseDao.COND_USE_END_DT, ConditionMapper.OPERATOR_LARGE_EQUAL,
                dto.getUseEndDt());

        // 並び順
        OrderCondition orderBy = new OrderCondition();

        // データを取得する
        return dao.search(conditions, orderBy);
    }

    /**
     * 利用者マスタデータ件数取得。<br>
     * <br>
     * 利用者マスタデータ件数取得を行う。<br>
     * <br>
     * @param dto 利用者マスタDTO<br>
     * @return int 件数<br>
     * @throws NaiException
     */
    public int selectUserMstCount(UserMstDto dto) throws NaiException {

        // 初期化
        TableCountDao dao = new TableCountDao(this.conn);

        // 検索条件の作成
        ConditionMapper conditions = new ConditionMapper();

        // 利用者ＩＤ
        conditions.addCondition(UserMstDao.COND_USER_ID, ConditionMapper.OPERATOR_EQUAL, dto.getUserId());
        // 利用終了日
        conditions.addCondition(UserMstDao.COND_USE_END_DT, ConditionMapper.OPERATOR_LARGE_EQUAL, dto.getUseEndDt());

        // データ件数取得
        return dao.rowCount(NaikaraTalkConstants.USER_MST, conditions);
    }

    /**
     * 受講者別講師ブックマークテーブルのデータ削除<br>
     * <br>
     * 受講者別講師ブックマークテーブルのデータ削除を行う<br>
     * <br>
     * @param dto 受講者別講師ブックマークテーブルDTO<br>
     * @return int 削除データ件数<br>
     * @throws NaiException
     */
    public int deleteTeacherBookmark(TeacherBookmarkMstDto dto) throws NaiException {

        // 初期化
        TeacherBookmarkDao dao = new TeacherBookmarkDao(this.conn);

        // データを削除
        return dao.delete(dto);
    }
}
