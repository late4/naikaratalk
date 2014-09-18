package com.naikara_talk.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dbutil.DbUtil;
import com.naikara_talk.dto.TimeManagMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.logic.TimeZoneControlMstListLogic;
import com.naikara_talk.model.TimeZoneControlMstListModel;
import com.naikara_talk.sessiondata.SessionUser;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称　　　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称　　:</b>マスタ保守<br>
 * <b>クラス名称　　　　　:</b>時差管理マスタメンテナンス【一覧】検索処理Serviceクラス。<br>
 * <b>クラス概要　　　　　:</b>時差管理マスタメンテナンス【一覧】検索処理Service。<br>
 * <br>
 * <b>著作権　　　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　　　:</b>2013/07/10 TECS 新規作成。
 */
public class TimeZoneControlMstListSearchService implements ActionService {

    /** 一覧 ZERO件 */
    private static final int LIST_ZERO_CNT = 0;

    /** 一覧の表示上限 */
    private static final int LIST_MAX_CNT = 100;

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
     * @param int
     * @return TimeZoneControlMstListModel
     * @exception Exception
     */
    public int checkPreSelect(TimeZoneControlMstListModel model) throws Exception {

        // 処理区分のチェック、処理区分が[新規]の場合は、メッセージ情報を設定する
        // '0':'新規','1':'修正','2':'削除','3':'照会'
        if (StringUtils.equals(TimeZoneControlMstListModel.PROS_KBN_ADD, model.getProcessKbn_rdl())) {
            return ERR_PROS_BTN_MISMATCH;
        }

        // 権限
        String userRole = ((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString())).getRole();

        // 処理区分
        String processKbn = model.getProcessKbn_rdl();

        // (1) 処理区分が [照会] 以外の場合で 且つ 操作者（ログイン者）の権限が 「スタッフ」
        // の場合は、メッセージ情報を設定する
        if (!StringUtils.equals(TimeZoneControlMstListModel.PROS_KBN_REF, processKbn)
                && StringUtils.equals(SessionUser.ROLE_STAFF, userRole)) {
            return ERR_NO_AUTH;
        }
        // (2) 処理区分が [削除] の場合で 且つ 操作者（ログイン者）の権限が 「管理者」
        // の場合は、メッセージ情報を設定する
        if (StringUtils.equals(TimeZoneControlMstListModel.PROS_KBN_DEL, processKbn)
                && StringUtils.equals(SessionUser.ROLE_ADMIN, userRole)) {
            return ERR_NO_AUTH;
        }

        // 入力チェック - DBアクセスありチェック
        // 共通部品：販売商品マスタのデータ件数取得処理
        int count = getRowCount(model);
        if (count == LIST_ZERO_CNT) {
            return ERR_ZERO_DATA;
        } else if (count > LIST_MAX_CNT) {
            return ERR_MAXOVER_DATA;
        }

        // 正常
        return TimeZoneControlMstListModel.CHECK_OK;

    }

    /**
     * 検索データ件数取得。<br>
     * <br>
     * @param TimeZoneControlMstListModel
     *            画面のパラメータ
     * @return int
     * @throws Exception
     */
    public int getRowCount(TimeZoneControlMstListModel model) throws Exception {
        Connection conn = null;
        try {
            conn = DbUtil.getConnection();

            // 初期化処理
            TimeZoneControlMstListLogic timeZoneControlMstListLogic = new TimeZoneControlMstListLogic(conn);

            // DTOの初期化
            TimeManagMstDto dto = new TimeManagMstDto();

            // Model値をDTOにセット
            dto = this.modelToDto(model);

            // データの取得件数＆リターン
            return timeZoneControlMstListLogic.getRowCount(dto);
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
     * 画面一覧データの取得。<br>
     * <br>
     * @param TimeZoneControlMstListModel
     *            画面のパラメータ
     * @return List<TimeManagMstDto>
     * @throws Exception
     */
    public List<TimeManagMstDto> selectList(TimeZoneControlMstListModel model) throws Exception {
        Connection conn = null;
        try {
            conn = DbUtil.getConnection();

            // 初期化処理
            TimeZoneControlMstListLogic timeZoneControlMstListLogic = new TimeZoneControlMstListLogic(conn);

            // DTOの初期化
            TimeManagMstDto dto = new TimeManagMstDto();

            // Model値をDTOにセット
            dto = this.modelToDto(model);

            // データの取得＆リターン
            return timeZoneControlMstListLogic.selectList(dto);
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
     * @param model
     *           TimeZoneControlMstListModel
     * @return TimeManagMstDto
     * @throws Exception
     */
    private TimeManagMstDto modelToDto(TimeZoneControlMstListModel model) throws Exception {

        // DTOの初期化
        TimeManagMstDto prmDto = new TimeManagMstDto();

        prmDto.setCountryCd(model.getCountryCd_sel());                           // 国コード設定
        prmDto.setAreaNoCd(model.getAreaNoCd_sel());                             // 時差地域NO設定

        return prmDto;

    }

}
