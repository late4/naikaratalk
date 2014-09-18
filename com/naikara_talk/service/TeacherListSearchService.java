package com.naikara_talk.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.naikara_talk.dbutil.DbUtil;
import com.naikara_talk.dto.TeacherBookmarkMstDto;
import com.naikara_talk.dto.TeacherCourseDto;
import com.naikara_talk.dto.UserMstTeacherMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.logic.TeacherListLogic;
import com.naikara_talk.model.TeacherListModel;
import com.naikara_talk.sessiondata.SessionUser;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>お客様(個人)_予約処理<br>
 * <b>クラス名称       :</b>講師一覧（Pop）Serviceクラス<br>
 * <b>クラス概要       :</b>登録済みの講師情報の検索処理を行い。<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/19 TECS 新規作成
 *                     :</b>2014/04/22 TECS 検索条件の追加(コース名)対応
 */
public class TeacherListSearchService implements ActionService {

    /** 一覧 ZERO件 */
    private static final int LIST_ZERO_CNT = 0;

    /**
     * 利用者マスタ、受講者マスタデータ取得。<br>
     * <br>
     * 利用者マスタ、受講者マスタリストを戻り値で返却する。<br>
     * <br>
     * @param model モデル<br>
     * @return List<UserMstTeacherMstDto> 利用者マスタ、講師マスタデータ取得DTOリスト<br>
     * @throws NaiException
     */
    public List<UserMstTeacherMstDto> selectTeacherList(TeacherListModel model) throws NaiException {

        Connection conn = null;

        try {
            conn = DbUtil.getConnection();

            // 初期化
            TeacherListLogic logic = new TeacherListLogic(conn);

            // Model値をDTOにセット
            UserMstTeacherMstDto dto = this.modelToDto(model);

            List<UserMstTeacherMstDto> retDtoList = logic.selectUserMstTeacherMstList(dto);

            // 取得したデータ件数分 以下の処理を行い、付帯情報を付加する
            for (UserMstTeacherMstDto retDto : retDtoList) {
                // 一覧の｢ブックマーク｣の箇所
                // 受講者別講師ブックマークテーブルのデータ件数取得処理
                int count = this.selectTeacherBookmarkCount(retDto.getUserId());
                // 0件の場合
                if (count == LIST_ZERO_CNT) {
                    // 一覧のブックマークの項目は、””（空白）
                    retDto.setBookmark(NaikaraTalkConstants.BRANK);
                } else {
                    // 一覧のブックマークの項目は、”済”
                    retDto.setBookmark(NaikaraTalkConstants.BOOKMARK_OK);
                }

                // 一覧の｢コース名｣の箇所
                // 講師単位のコースリスト取得処理
                retDto.setTeacherCourseDtoList(this.selectTeacherCourseDtoList(retDto.getUserId()));
            }

            return retDtoList;

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
     * 受講者別講師ブックマークテーブルのデータ件数取得処理。<br>
     * <br>
     * 受講者別講師ブックマークテーブルのデータ件数取得処理を行う。<br>
     * <br>
     * @param teacherId 講師ID<br>
     * @return int 件数<br>
     * @throws NaiException
     */
    public int selectTeacherBookmarkCount(String teacherId) throws NaiException {

        // ユーザIDを取得
        String userId = ((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString())).getUserId();

        Connection conn = null;

        try {
            conn = DbUtil.getConnection();

            // 初期化
            TeacherListLogic logic = new TeacherListLogic(conn);

            TeacherBookmarkMstDto dto = new TeacherBookmarkMstDto();
            // 受講者ID
            dto.setStudentId(userId);
            // 利用者ID
            dto.setUserId(teacherId);

            return logic.selectTeacherBookmarkCount(dto);

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
            TeacherListLogic logic = new TeacherListLogic(conn);

            TeacherCourseDto dto = new TeacherCourseDto();
            // 講師ID
            dto.setUserId(teacherId);

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

    /**
     * Model値をDTOにセット<br>
     * <br>
     * Model値をDTOにセット。<br>
     * <br>
     * @param model モデル<br>
     * @return UserMstTeacherMstDto 利用者マスタ、講師マスタデータ取得DTO<br>
     * @throws なし
     */
    private UserMstTeacherMstDto modelToDto(TeacherListModel model) {

        // DTOの初期化
        UserMstTeacherMstDto dto = new UserMstTeacherMstDto();

        // 講師ID
        dto.setUserId(model.getTeacherId());
        // ニックネーム
        dto.setNickAnm(model.getTeacherNm());
        // 性別
        dto.setGenderKbn(model.getSexKbn());

        // 2014/04/22 Add Start 検索条件の追加(コース名)対応
        // 大分類
        dto.setBigClassificationCd(model.getCourseLargeCd());
        // 中分類
        dto.setMiddleClassificationCd(model.getCourseMediumCd());
        // 小分類
        dto.setSmallClassificationCd(model.getCourseSmallCd());
        // コース名
        dto.setCourseJnm(model.getCourseJNm());
        // 2014/04/22 Add Start 検索条件の追加(コース名)対応

        return dto;
    }
}
