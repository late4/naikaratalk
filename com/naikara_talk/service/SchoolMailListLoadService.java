package com.naikara_talk.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedHashMap;

import com.naikara_talk.dbutil.DbUtil;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.logic.SchoolMailListLogic;
import com.naikara_talk.model.SchoolMailListModel;
import com.naikara_talk.util.DateUtil;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>マスタ保守<br>
 * <b>サブシステム名称 :</b>顧客管理<br>
 * <b>クラス名称       :</b>スクールのメール送信・受信履歴照会<br>
 * <b>クラス概要       :</b>スクールのメール送信・受信履歴照会初期処理Service<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/12/16 TECS 新規作成
 */
public class SchoolMailListLoadService implements ActionService {

    /**
     * 初期処理<br>
     * <br>
     * 画面項目の初期処理を行う<br>
     * <br>
     * @param なし<br>
     * @return SchoolMailListModel<br>
     * @exception なし
     */
    public SchoolMailListModel load() {
        SchoolMailListModel model = new SchoolMailListModel();
        // メール送信日(From)はシステム日付を設定
        model.setMailSendDtFr(NaikaraStringUtil.converToYYYY_MM_DD(DateUtil.getSysDate()));
        // メール送信日(To)はシステム日付を設定
        model.setMailSendDtTo(NaikaraStringUtil.converToYYYY_MM_DD(DateUtil.getSysDate()));
        // 顧客区分は「全て」を選択する
        model.setCustomerKbn(NaikaraTalkConstants.CHOICE_ALL_ZERO);

        return model;
    }

    /**
     * コード管理マスタからデータ取得処理。<br>
     * <br>
     * コード管理マスタからデータ取得処理。<br>
     * <br>
     * @param category 汎用コード <br>
     * @return LinkedHashMap<String, String><br>
     * @exception NaiException
     */
    public LinkedHashMap<String, String> selectCodeMst(String category) throws NaiException {

        Connection conn = null;
        try {
            conn = DbUtil.getConnection();
            SchoolMailListLogic schoolMailListLogic = new SchoolMailListLogic(conn);
            // コード管理マスタ検索
            return schoolMailListLogic.selectCodeMst(category);
        } catch (SQLException e) {
            throw new NaiException(e);
        } catch (NaiException e) {
            throw new NaiException(e);
        } finally {
            try {
                conn.close();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }

    }

}
