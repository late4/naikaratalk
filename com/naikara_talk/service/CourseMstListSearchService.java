package com.naikara_talk.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dbutil.DbUtil;
import com.naikara_talk.dto.CourseMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.logic.CourseMstListLogic;
import com.naikara_talk.model.CourseMstListModel;
import com.naikara_talk.sessiondata.SessionUser;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>マスタ保守<br>
 * <b>クラス名称       :</b>コースマスタメンテナンス(一覧)<br>
 * <b>クラス概要       :</b>コースマスタメンテナンス(一覧)検索処理Service<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/08/02 TECS 新規作成
 */
public class CourseMstListSearchService implements ActionService {

    /** 一覧 ZERO件 */
    private static final int LIST_ZERO_CNT = 0;

    /** 一覧の表示上限 */
    private static final int LIST_MAX_CNT = 200;

    /** 検索前チェック：処理区分と押下ボタンの不整合 */
    public static final int ERR_PROS_BTN_MISMATCH = 1;

    /** 検索前チェック：データ件数ZERO件 */
    public static final int ERR_ZERO_DATA = 2;

    /** 検索前チェック：データ件数上限以上の件数 */
    public static final int ERR_MAXOVER_DATA = 3;

    /** 検索前チェック： 権限無*/
    public static final int ERR_NO_AUTH = 4;

    /**
     * 検索前チェック処理<br>
     * <br>
     * 検索前チェック処理<br>
     * <br>
     * @param CourseMstListModel<br>
     * @return int チェック結果<br>
     * @exception NaiException
     */
    public int checkPreSelect(CourseMstListModel model) throws NaiException {

        // 処理区分のチェック、処理区分が[新規]の場合は、メッセージ情報を設定する
        // '0':'新規','1':'修正','2':'照会'
        if (StringUtils.equals(CourseMstListModel.PROS_KBN_ADD, model.getProcessKbn())) {
            return ERR_PROS_BTN_MISMATCH;
        }

        // 権限
        String userRole = ((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString())).getRole();

        // 処理区分
        String processKbn = model.getProcessKbn();

        // 処理区分が [照会] 以外の場合で
        // 且つ 操作者（ログイン者）の権限が 「スタッフ」 の場合は、メッセージ情報を設定する
        if (!StringUtils.equals(CourseMstListModel.PROS_KBN_REF, processKbn)
                && StringUtils.equals(SessionUser.ROLE_STAFF, userRole)) {
            return ERR_NO_AUTH;
        }

        // 入力チェック - DBアクセスありチェック
        // 共通部品：ポイント管理マスタのデータ件数取得処理
        int count = getRowCount(model);
        if (LIST_ZERO_CNT == count) {
            return ERR_ZERO_DATA;
        } else if (LIST_MAX_CNT < count) {
            return ERR_MAXOVER_DATA;
        }

        // 正常
        return CourseMstListModel.CHECK_OK;

    }

    /**
     * 検索データ件数取得。<br>
     * <br>
     * 検索データ件数取得。<br>
     * <br>
     * @param CourseMstListModel 画面のパラメータ<br>
     * @return int 検索データ件数<br>
     * @exception NaiException
     */
    public int getRowCount(CourseMstListModel model) throws NaiException {

        Connection conn = null;

        try {
            conn = DbUtil.getConnection();

            // 初期化処理
            CourseMstListLogic logic = new CourseMstListLogic(conn);

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
     * @param CourseMstListModel 画面のパラメータ<br>
     * @return List<CourseMstDto><br>
     * @exception Exception
     */
    public List<CourseMstDto> selectList(CourseMstListModel model) throws NaiException {

        Connection conn = null;

        try {
            conn = DbUtil.getConnection();

            // 初期化処理
            CourseMstListLogic logic = new CourseMstListLogic(conn);

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
     * @param CourseMstListModel 画面のパラメータ<br>
     * @return CourseMstDto<br>
     * @exception Exception
     */
    private CourseMstDto modelToDto(CourseMstListModel model) {

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

        return dto;
    }
}