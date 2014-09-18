package com.naikara_talk.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.naikara_talk.dbutil.DbUtil;
import com.naikara_talk.dto.TeaBooMTeaMUsrMDto;
import com.naikara_talk.dto.TeacherCourseDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.logic.TeacherBookmarkListLogic;
import com.naikara_talk.model.TeacherBookmarkListModel;
import com.naikara_talk.util.DateUtil;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>お客様(個人)_予約処理<br>
 * <b>クラス名称       :</b>ブックマーク済の講師一覧（Pop）初期化Serviceクラス<br>
 * <b>クラス概要       :</b>ブックマーク済の講師一覧情報を処理する。<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/08/13 TECS 新規作成
 */
public class TeacherBookmarkListLoadService implements ActionService {

    /**
     * 受講者別講師ブックマークテーブルのデータ取得。<br>
     * <br>
     * 受講者別講師ブックマークテーブルからデータを取得する。<br>
     * <br>
     * @param model モデル<br>
     * @return List<TeaBooMTeaMUsrMDto> 受講者別講師ブックマークテーブル、講師マスタ、利用者マスタDTOリスト<br>
     * @throws NaiException
     */
    public List<TeaBooMTeaMUsrMDto> selectTeacherBookmarkList(TeacherBookmarkListModel model) throws NaiException {

        Connection conn = null;

        try {
            conn = DbUtil.getConnection();

            // 初期化
            TeacherBookmarkListLogic logic = new TeacherBookmarkListLogic(conn);

            TeaBooMTeaMUsrMDto prmDto = new TeaBooMTeaMUsrMDto();

            prmDto.setStudentId(model.getStudentId());

            // データを取得
            return logic.selectTeacherBookmarkList(prmDto);

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
     * 講師別コースマスタ(+コースマスタ)リスト取得。<br>
     * <br>
     * 講師別コースマスタ(+コースマスタ)リスト取得を行う。<br>
     * <br>
     * @param teacherId 講師ID<br>
     * @return List<TeacherCourseDto> 講師別コースマスタ(+コースマスタ)DTOリスト<br>
     * @throws NaiException
     */
    public List<TeacherCourseDto> selectTeacherCourseDtoList(String teacherId) throws NaiException {

        Connection conn = null;

        try {
            conn = DbUtil.getConnection();

            // 初期化
            TeacherBookmarkListLogic logic = new TeacherBookmarkListLogic(conn);

            TeacherCourseDto dto = new TeacherCourseDto();
            // 講師ID
            dto.setUserId(teacherId);
            // 提供開始日 = サーバーシステム日付
            dto.setUseStrDt(DateUtil.getSysDate());
            // 提供終了日 = サーバーシステム日付 + 27日
            dto.setUseEndDt(DateUtil.getSysDateAddDay(27));

            return logic.selectTeacherCourseDtoList(dto);

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
