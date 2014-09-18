package com.naikara_talk.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.naikara_talk.dbutil.DbUtil;
import com.naikara_talk.dto.TeaCouMTeaRateMCouUsePoiMDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.logic.TeacherPaymentRateListLogic;
import com.naikara_talk.model.TeacherPaymentRateListModel;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>支払単価表。<br>
 * <b>クラス名称       :</b>支払単価表初期処理Serviceクラス。<br>
 * <b>クラス概要       :</b>支払比率で、コース単位の支払単価の一覧表示を行う<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/09 TECS 新規作成
 */
public class TeacherPaymentRateListLoadService implements ActionService {

    /**
     * 講師予定予約テーブルのデータ取得処理。<br>
     * <br>
     * @param model TeacherPaymentRateListModel
     * @return model TeacherPaymentRateListModel
     * @throws NaiException
     */
    public TeacherPaymentRateListModel select(TeacherPaymentRateListModel model) throws NaiException {
        Connection conn = null;

        try {

            conn = DbUtil.getConnection();
            TeacherPaymentRateListLogic teacherPaymentRateListLogic = new TeacherPaymentRateListLogic(conn);
            // DTOの初期化
            TeaCouMTeaRateMCouUsePoiMDto prmDto = new TeaCouMTeaRateMCouUsePoiMDto();

            // Model値をDTOにセット
            prmDto = this.modelToDto(model);

            // 検索実行
            List<TeaCouMTeaRateMCouUsePoiMDto> returnList = teacherPaymentRateListLogic.select(prmDto);

            model.setTeaCouMTeaRateMCouUsePoiMDto(returnList);
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
     * @param model TeacherPaymentRateListModel
     * @return TeaCouMTeaRateMCouUsePoiMDto
     * @throws NaiException
     */
    private TeaCouMTeaRateMCouUsePoiMDto modelToDto(TeacherPaymentRateListModel model) throws NaiException {

        // DTOの初期化
        TeaCouMTeaRateMCouUsePoiMDto prmDto = new TeaCouMTeaRateMCouUsePoiMDto();

        prmDto.setUserId(model.getUserId()); // 利用者ID

        return prmDto;

    }

}
