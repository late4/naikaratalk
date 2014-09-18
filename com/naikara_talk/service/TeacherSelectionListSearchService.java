package com.naikara_talk.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.naikara_talk.dbutil.DbUtil;
import com.naikara_talk.dto.UserMstTeacherMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.logic.TeacherSelectionListLogic;
import com.naikara_talk.model.TeacherSelectionListModel;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>一覧_講師選択ページ。<br>
 * <b>クラス名称       :</b>一覧_講師選択ページ検索Serviceクラス。<br>
 * <b>クラス概要       :</b>一覧_講師選択の情報検索ができる<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/09 TECS 新規作成
 */
public class TeacherSelectionListSearchService implements ActionService {

    /** 検索前チェック：取得したデータ件数0 */
    public static final int ERR_GET_DATA_ZERO = 0;
    /** 検索前チェック：取得したデータ件数100 */
    public static final int ERR_GET_DATE_HUNDRED = 100;

    /**
     * 講師マスタのデータ取得処理。<br>
     * <br>
     * @param model TeacherSelectionListModel
     * @return model TeacherSelectionListModel
     * @throws NaiException
     */
    public TeacherSelectionListModel select(TeacherSelectionListModel model) throws NaiException {
        Connection conn = null;
        try {
            conn = DbUtil.getConnection();
            TeacherSelectionListLogic logic = new TeacherSelectionListLogic(conn);
            // DTOの初期化
            UserMstTeacherMstDto prmDto = new UserMstTeacherMstDto();

            // Model値をDTOにセット
            prmDto = this.modelToDto(model);

            // 検索実行
            List<UserMstTeacherMstDto> resultDto = logic.selectData(prmDto);

            model.setUserMstTeacherMstDto(resultDto);
            return model;
        } catch (SQLException se) {
            se.printStackTrace();
            throw new NaiException(se);
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
     * @param model TeacherSelectionListModel
     * @return UserMstTeacherMstDto prmDto
     * @throws NaiException
     */
    private UserMstTeacherMstDto modelToDto(TeacherSelectionListModel model) throws NaiException {

        // DTOの初期化
        UserMstTeacherMstDto prmDto = new UserMstTeacherMstDto();

        // 講師ID
        prmDto.setUserId(model.getTeacherId());
        // 講師名(フリガナ)
        prmDto.setUserKnm(model.getTeacherFurigana());

        return prmDto;

    }

    /**
     * 検索のチェック。<br>
     * <br>
     * @param model TeacherSelectionListModel
     * @return int count
     * @throws NaiException
     */
    public int errorCheck(TeacherSelectionListModel model) throws NaiException {
        Connection conn = null;
        try {
            conn = DbUtil.getConnection();
            TeacherSelectionListLogic logic = new TeacherSelectionListLogic(conn);
            // DTOの初期化
            UserMstTeacherMstDto prmDto = new UserMstTeacherMstDto();

            // Model値をDTOにセット
            prmDto = this.modelToDto(model);

            // 検索実行
            int count = logic.selectCount(prmDto);

            return count;
        } catch (SQLException se) {
            se.printStackTrace();
            throw new NaiException(se);
        } finally {
            try {
                conn.close();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }
}
