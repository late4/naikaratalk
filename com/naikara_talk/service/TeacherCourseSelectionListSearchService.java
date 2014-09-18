package com.naikara_talk.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.naikara_talk.dbutil.DbUtil;
import com.naikara_talk.dto.CourseMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.logic.TeacherCourseSelectionListLogic;
import com.naikara_talk.model.TeacherCourseSelectionListModel;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>マスタ保守<br>
 * <b>クラス名称       :</b>講師マスタメンテナンス(コース 選択)<br>
 * <b>クラス概要       :</b>講師マスタメンテナンス(コース 選択)検索処理Service<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/08/02 TECS 新規作成
 */
public class TeacherCourseSelectionListSearchService implements ActionService {

    /** 一覧 ZERO件 */
    private static final int LIST_ZERO_CNT = 0;

    /** 一覧の表示上限 */
    private static final int LIST_MAX_CNT = 100;

    /** 検索前チェック：データ件数ZERO件 */
    public static final int ERR_ZERO_DATA = 2;

    /** 検索前チェック：データ件数上限以上の件数 */
    public static final int ERR_MAXOVER_DATA = 3;

    /**
     * 検索前チェック処理<br>
     * <br>
     * 検索前チェック処理<br>
     * <br>
     * @param TeacherCourseSelectionListModel<br>
     * @return int チェック結果<br>
     * @exception NaiException
     */
    public int checkPreSelect(TeacherCourseSelectionListModel model) throws NaiException {

        // 入力チェック - DBアクセスありチェック
        // 共通部品：ポイント管理マスタのデータ件数取得処理
        int count = getRowCount(model);
        if (LIST_ZERO_CNT == count) {
            return ERR_ZERO_DATA;
        } else if (LIST_MAX_CNT < count) {
            return ERR_MAXOVER_DATA;
        }

        // 正常
        return TeacherCourseSelectionListModel.CHECK_OK;

    }

    /**
     * 検索データ件数取得。<br>
     * <br>
     * 検索データ件数取得。<br>
     * <br>
     * @param TeacherCourseSelectionListModel 画面のパラメータ<br>
     * @return int 検索データ件数<br>
     * @exception NaiException
     */
    public int getRowCount(TeacherCourseSelectionListModel model) throws NaiException {

        Connection conn = null;
        try {
            conn = DbUtil.getConnection();
            // 初期化処理
            TeacherCourseSelectionListLogic logic = new TeacherCourseSelectionListLogic(conn);

            // DTOの初期化
            CourseMstDto dto = new CourseMstDto();

            // Model値をDTOにセット
            dto = this.modelToDto(model);

            // データの取得件数＆リターン
            return logic.getRowCount(dto);
        } catch (SQLException e) {
            throw new NaiException(e);
        } catch (Exception e) {
            throw new NaiException(e);
        } finally {
            try {
                conn.close();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * 画面データの取得、初回表示の場合。<br>
     * <br>
     * 画面データの取得、初回表示の場合。<br>
     * <br>
     * @param TeacherMstListModel 画面のパラメータ<br>
     * @return List<CourseMstDto><br>
     * @exception NaiException
     */
    public List<CourseMstDto> selectList(TeacherCourseSelectionListModel model) throws NaiException {

        Connection conn = null;
        try {
            conn = DbUtil.getConnection();
            // 初期化処理
            TeacherCourseSelectionListLogic logic = new TeacherCourseSelectionListLogic(conn);

            // DTOの初期化
            CourseMstDto dto = new CourseMstDto();

            // Model値をDTOにセット
            dto = this.modelToDto(model);

            // データの取得＆リターン
            return logic.selectList(dto);
        } catch (SQLException e) {
            throw new NaiException(e);
        } catch (Exception e) {
            throw new NaiException(e);
        } finally {
            try {
                conn.close();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * Model値をDTOにセット。<br>
     * <br>
     * Model値をDTOにセット。<br>
     * <br>
     * @param TeacherMstListModel 画面のパラメータ<br>
     * @return CourseMstDto<br>
     * @exception なし
     */
    private CourseMstDto modelToDto(TeacherCourseSelectionListModel model) {

        // DTOの初期化
        CourseMstDto dto = new CourseMstDto();

        // 大分類
        dto.setBigClassificationCd(model.getBigClassificationCd());

        // 中分類
        dto.setMiddleClassificationCd(model.getMiddleClassificationCd());

        // 小分類
        dto.setSmallClassificationCd(model.getSmallClassificationCd());

        // コース名
        dto.setCourseJnm(model.getCourseJnm());

        // キーワード1
        dto.setKeyword1(model.getKeyword1());

        // キーワード2
        dto.setKeyword2(model.getKeyword2());

        // キーワード3
        dto.setKeyword3(model.getKeyword3());

        // キーワード4
        dto.setKeyword4(model.getKeyword4());

        // キーワード5
        dto.setKeyword5(model.getKeyword5());

        // 契約期間：開始日
        dto.setUseStrDt(model.getContractStart());

        // 契約期間：終了日
        dto.setUseEndDt(model.getContractEnd());

        return dto;

    }

}
