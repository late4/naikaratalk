package com.naikara_talk.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.naikara_talk.dbutil.DbUtil;
import com.naikara_talk.dto.CodeManagMstDto;
import com.naikara_talk.dto.SchResTCouMUsrMTeaMCouUsePoiMTeaCouMLesResPerTDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.logic.ReservationCancellationCourseListLogic;
import com.naikara_talk.model.ReservationCancellationCourseListModel;
import com.naikara_talk.mstcache.CodeManagMstCache;
import com.naikara_talk.sessiondata.SessionUser;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>お客様(個人)_予約処理<br>
 * <b>クラス名称       :</b>予約申込ページ検索Serviceクラス<br>
 * <b>クラス概要       :</b>受講者がレッスンを予約登録、取消ができる。<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/18 TECS 新規作成
 */
public class ReservationCancellationCourseListSearchService implements ActionService {

    /**
     * 希望時刻の整合性チェック (日付)。<br>
     * <br>
     * 希望時刻の整合性チェック (日付)を行う。<br>
     * <br>
     * @param model モデル<br>
     * @return boolean 判定フラグ<br>
     * @throws NaiException
     */
    public boolean hopeTimeCheck(ReservationCancellationCourseListModel model) throws NaiException {

        // ｢希望時刻（From）｣ ＞ ｢希望時刻（To）｣ の場合
        if (model.getHopeTimeFr().compareTo(model.getHopeTimeTo()) > 0) {
            return false;
        }
        return true;
    }

    /**
     * 講師予定予約テーブル情報検索処理。<br>
     * <br>
     * 講師予定予約テーブル情報検索処理を行う。<br>
     * <br>
     * @param model モデル<br>
     * @return List<SchResTCouMUsrMTeaMCouUsePoiMTeaCouMLesResPerTDto> 予約申込ページDTO<br>
     * @throws NaiException
     */
    public List<SchResTCouMUsrMTeaMCouUsePoiMTeaCouMLesResPerTDto> selectScheduleReservationInfoList(
            ReservationCancellationCourseListModel model) throws NaiException {

        Connection conn = null;

        try {
            conn = DbUtil.getConnection();

            // 初期化
            List<SchResTCouMUsrMTeaMCouUsePoiMTeaCouMLesResPerTDto> retDtoList = new ArrayList<SchResTCouMUsrMTeaMCouUsePoiMTeaCouMLesResPerTDto>();

            ReservationCancellationCourseListLogic logic = new ReservationCancellationCourseListLogic(conn);

            // Model値をDTOにセット
            SchResTCouMUsrMTeaMCouUsePoiMTeaCouMLesResPerTDto dto = this.modelToDto(model);

            retDtoList = logic.selectScheduleReservationInfoList(dto);

            if (retDtoList.get(0).getReturnCode() == NaikaraTalkConstants.RETURN_CD_DATA_YES) {

                // ユーザIDを取得
                String userId = ((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString()))
                        .getUserId();

                model.setLoginId(userId);

                // 汎用フィールド名の取得
                CodeManagMstCache cache = CodeManagMstCache.getInstance();
                // 大分類の名称一覧取得
                LinkedHashMap<String, CodeManagMstDto> workBigList = cache
                        .getList(NaikaraTalkConstants.CODE_CATEGORY_BIG_CLASSIFICATION);

                // 中分類の名称一覧取得
                LinkedHashMap<String, CodeManagMstDto> workMidList = cache
                        .getList(NaikaraTalkConstants.CODE_CATEGORY_MIDDLE_CLASSIFICATION);

                // 小分類の名称一覧取得
                LinkedHashMap<String, CodeManagMstDto> workSmoList = cache
                        .getList(NaikaraTalkConstants.CODE_CATEGORY_SMALL_CLASSIFICATION);

                // 大分類(英語)の名称一覧取得
                LinkedHashMap<String, CodeManagMstDto> workBigListE = cache
                        .getList(NaikaraTalkConstants.CODE_CATEGORY_BIG_CLASSIFICATION_T);

                // 中分類(英語)の名称一覧取得
                LinkedHashMap<String, CodeManagMstDto> workMidListE = cache
                        .getList(NaikaraTalkConstants.CODE_CATEGORY_MIDDLE_CLASSIFICATION_T);

                // 小分類(英語)の名称一覧取得
                LinkedHashMap<String, CodeManagMstDto> workSmoListE = cache
                        .getList(NaikaraTalkConstants.CODE_CATEGORY_SMALL_CLASSIFICATION_T);

                for (SchResTCouMUsrMTeaMCouUsePoiMTeaCouMLesResPerTDto retDto : retDtoList) {
                    // 日本語コース名
                    retDto.setCourseJnm(NaikaraStringUtil.unionString4(
                            getName(workBigList, retDto.getBigClassificationCd()),
                            getName(workMidList, retDto.getMiddleClassificationCd()),
                            getName(workSmoList, retDto.getSmallClassificationCd()), retDto.getCourseJnm()));
                    // 英語コース名
                    retDto.setCourseEnm(NaikaraStringUtil.unionString4(
                            getName(workBigListE, retDto.getBigClassificationCd()),
                            getName(workMidListE, retDto.getMiddleClassificationCd()),
                            getName(workSmoListE, retDto.getSmallClassificationCd()), retDto.getCourseEnm()));

                    // コースポイントのコンマ追加
                    retDto.setCoursePoint(retDto.getCoursePoint());
                }
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
     * Model値をDTOにセット<br>
     * <br>
     * Model値をDTOにセット。<br>
     * <br>
     * @param model モデル<br>
     * @return SchResTCouMUsrMTeaMCouUsePoiMTeaCouMLesResPerTDto 予約申込ページDTO<br>
     * @throws なし
     */
    private SchResTCouMUsrMTeaMCouUsePoiMTeaCouMLesResPerTDto modelToDto(ReservationCancellationCourseListModel model) {

        // DTOの初期化
        SchResTCouMUsrMTeaMCouUsePoiMTeaCouMLesResPerTDto dto = new SchResTCouMUsrMTeaMCouUsePoiMTeaCouMLesResPerTDto();

        // 希望日
        dto.setLessonDt(NaikaraStringUtil.converToYYYYMMDD(model.getHopeDt()));
        // 希望時刻(From)
        dto.setLessonTmCdFr(model.getHopeTimeFr());
        // 希望時刻(To)
        dto.setLessonTmCdTo(model.getHopeTimeTo());
        // 予約状況
        dto.setReservationKbn(model.getReservationKbn());
        // 性別
        dto.setGenderKbn(model.getSexKbn());
        // コースコード
        dto.setCourseCd(model.getCourseCode());
        // 大分類
        dto.setBigClassificationCd(model.getCourseLarge());
        // 中分類
        dto.setMiddleClassificationCd(model.getCourseMedium());
        // 小分類
        dto.setSmallClassificationCd(model.getCourseSmall());
        // コース名：キーワード
        dto.setCourseJnm(model.getCourseName());

        return dto;
    }

    /**
     * 汎用コードから汎用フィールドを抽出する<br>
     * <br>
     * 汎用コードから汎用フィールドを抽出する。<br>
     * <br>
     * @param list LinkedHashMap ハッシュマップ<br>
     * @param managerCd String コード<br>
     * @return ManagerNm String コード名<br>
     * @exception なし
     */
    private String getName(LinkedHashMap<String, CodeManagMstDto> list, String managerCd) {

        CodeManagMstDto dto;
        dto = list.get(managerCd);
        return dto.getManagerNm();
    }
}
