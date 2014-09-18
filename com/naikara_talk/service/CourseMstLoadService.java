package com.naikara_talk.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dbutil.DbUtil;
import com.naikara_talk.dto.CourseGoodsListDto;
import com.naikara_talk.dto.CourseMstDto;
import com.naikara_talk.dto.CourseUsePointMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.logic.CourseMstLogic;
import com.naikara_talk.logic.GoodsCourseUsePointMstLogic;
import com.naikara_talk.model.CourseMstListModel;
import com.naikara_talk.model.CourseMstModel;
import com.naikara_talk.util.DateUtil;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>マスタ保守<br>
 * <b>クラス名称       :</b>コースマスタメンテナンス(単票)<br>
 * <b>クラス概要       :</b>コースマスタメンテナンス(単票)初期処理Service<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/08/09 TECS 新規作成
 *                     :</b>2014/04/22 TECS 項目の追加(短コース名)対応
 */
public class CourseMstLoadService implements ActionService {

    /**
     * 初期処理<br>
     * <br>
     * 画面項目の初期処理を行う<br>
     * <br>
     * @param CourseMstModel<br>
     * @return CourseMstModel<br>
     * @exception なし
     */
    public CourseMstModel initLoad(CourseMstModel model) {
        CourseMstModel workModel = new CourseMstModel();

        // 前画面から処理区分を画面にセット
        workModel.setProcessKbn_rdl(model.getProcessKbn_rdl());
        if (StringUtils.equals(CourseMstListModel.PROS_KBN_ADD, model.getProcessKbn_rdl())) {
            workModel.setProcessKbn_txt(NaikaraTalkConstants.PROCESSKBN_INS);
        } else if (StringUtils.equals(CourseMstListModel.PROS_KBN_UPD, model.getProcessKbn_rdl())) {
            workModel.setProcessKbn_txt(NaikaraTalkConstants.PROCESSKBN_UPD);
        } else {
            workModel.setProcessKbn_txt(NaikaraTalkConstants.PROCESSKBN_REF);
        }
        // 提供期間：開始日 初期値設定
        workModel.setUseStrDt(NaikaraStringUtil.converToYYYY_MM_DD(DateUtil.getSysDate()));
        // 提供終了日 初期値設定
        workModel.setUseEndDt(NaikaraTalkConstants.MAX_END_DT);
        // レッスン時間 初期値設定
        workModel.setLessonTime(NaikaraTalkConstants.FREE_LESSON_TIME_INIT);
        // コース別商品
        List<CourseGoodsListDto> initCourseGoodsListDtoList = new ArrayList<CourseGoodsListDto>();
        for (int i = 0; i < 5; i++) {
            initCourseGoodsListDtoList.add(new CourseGoodsListDto());
        }
        workModel.setCourseGoodsListDtoList(initCourseGoodsListDtoList);

        return workModel;
    }

    /**
     * データの存在チェック。<br>
     * <br>
     * 画面初期表示。<br>
     * <br>
     * @param CourseMstModel  画面のパラメータ<br>
     * @return int<br>
     * @exception NaiException
     */
    public int getExist(CourseMstModel model) throws NaiException {

        Connection conn = null;
        try {
            conn = DbUtil.getConnection();

            CourseMstLogic courseMstLogic = new CourseMstLogic(conn);
            // DTOの初期化
            CourseMstDto dto = new CourseMstDto();

            // Model値をDTOにセット
            dto = this.modelToDto(model);

            // 検索実行
            return courseMstLogic.getExist(dto);
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
     * コード管理マスタからデータ取得処理。<br>
     * <br>
     * コード管理マスタからデータ取得処理。<br>
     * <br>
     * @param category  汎用コード<br>
     * @return LinkedHashMap<String, String><br>
     * @exception NaiException
     */
    public LinkedHashMap<String, String> selectCodeMst(String category) throws NaiException {

        Connection conn = null;
        try {
            conn = DbUtil.getConnection();

            CourseMstLogic courseMstLogic = new CourseMstLogic(conn);

            // コード管理マスタ検索
            return courseMstLogic.selectCodeMst(category);
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
     * 画面初期表示。<br>
     * <br>
     * 画面初期表示。<br>
     * <br>
     * @param CourseMstModel  画面のパラメータ<br>
     * @return CourseMstModel<br>
     * @exception なし
     */
    public CourseMstModel select(CourseMstModel model) throws NaiException {

        Connection conn = null;
        try {
            conn = DbUtil.getConnection();

            CourseMstLogic courseMstLogic = new CourseMstLogic(conn);
            // DTOの初期化
            CourseMstDto prmDto = new CourseMstDto();

            // Model値をDTOにセット
            prmDto = this.modelToDto(model);

            // 検索実行
            CourseMstDto resultDto = courseMstLogic.select(prmDto);

            // DTO値をModelにセット
            model = this.dtoToModel(resultDto, model);
            model.setCourseGoodsListDtoList(this.getCourseGoodsList(model));
            List<CourseUsePointMstDto> tempList = this.selectCourseUsePointMstDtoList(model);
            if (NaikaraTalkConstants.RETURN_CD_DATA_NO != tempList.get(0).getReturnCode()) {
                model.setCourseUsePointMstDtoList(tempList);
            }

            return model;
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
     * コース別商品マスタデータの存在チェック。<br>
     * <br>
     * コース別商品マスタデータの存在チェック。<br>
     * <br>
     * @param CourseMstModel  画面のパラメータ<br>
     * @return int<br>
     * @exception NaiException
     */
    public int getCourseGoodsExist(CourseMstModel model) throws NaiException {

        Connection conn = null;
        try {
            conn = DbUtil.getConnection();

            CourseMstLogic courseMstLogic = new CourseMstLogic(conn);
            // DTOの初期化
            CourseGoodsListDto dto = new CourseGoodsListDto();

            // Model値をDTOにセット
            dto.setCourseCd(model.getCourseCd());

            // 検索実行
            return courseMstLogic.getCourseGoodsExist(dto);
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
     * コース別商品マスタ取得処理。<br>
     * <br>
     * コース別商品マスタ取得処理。<br>
     * <br>
     * @param CourseMstModel<br>
     * @return List<CourseGoodsListDto><br>
     * @exception NaiException
     */
    private List<CourseGoodsListDto> getCourseGoodsList(CourseMstModel model) throws NaiException {

        Connection conn = null;
        try {
            conn = DbUtil.getConnection();

            CourseMstLogic courseMstLogic = new CourseMstLogic(conn);
            CourseGoodsListDto dto = new CourseGoodsListDto();
            dto.setCourseCd(model.getCourseCd());

            List<CourseGoodsListDto> courseGoodsListDtoList = courseMstLogic.getCourseGoodsListDtos(dto);
            if (NaikaraTalkConstants.RETURN_CD_DATA_NO == courseGoodsListDtoList.get(0).getReturnCode()) {
                courseGoodsListDtoList.clear();
            }
            if (courseGoodsListDtoList.size() < 5) {
                for (int i = courseGoodsListDtoList.size(); i < 5; i++) {
                    courseGoodsListDtoList.add(new CourseGoodsListDto());
                }
            }
            return courseGoodsListDtoList;
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
     * コース別利用ポイント取得処理。<br>
     * <br>
     * コース別利用ポイント取得処理。<br>
     * <br>
     * @param TeacherMstModel<br>
     * @return List<CourseUsePointMstDto><br>
     * @exception NaiException
     */
    public List<CourseUsePointMstDto> selectCourseUsePointMstDtoList(CourseMstModel model) throws NaiException {

        Connection conn = null;
        try {
            conn = DbUtil.getConnection();

            // 初期化処理
            GoodsCourseUsePointMstLogic goodsCourseUsePointMstLogic = new GoodsCourseUsePointMstLogic(conn);

            // DTOの初期化
            CourseUsePointMstDto dto = new CourseUsePointMstDto();

            // コースコード
            dto.setCourseCd(model.getCourseCd());

            // データの取得＆リターン
            return goodsCourseUsePointMstLogic.select(dto);
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
     * @param CourseMstModel<br>
     * @return CourseMstDto<br>
     * @exception なし
     */
    private CourseMstDto modelToDto(CourseMstModel model) {

        // DTOの初期化
        CourseMstDto prmDto = new CourseMstDto();

        // コースコード
        prmDto.setCourseCd(model.getCourseCd());

        return prmDto;

    }

    /**
     * DTO値をModelにセット。<br>
     * <br>
     * DTO値をModelにセット。<br>
     * <br>
     * @param CourseMstDto<br>
     * @return TeacherMstModel<br>
     * @exception なし
     */
    private CourseMstModel dtoToModel(CourseMstDto prmDto, CourseMstModel model) {

        model.setCourseCd(prmDto.getCourseCd());                                // コースコード
        model.setBigClassificationCd(prmDto.getBigClassificationCd());          // 大分類
        model.setMiddleClassificationCd(prmDto.getMiddleClassificationCd());    // 中分類
        model.setSmallClassificationCd(prmDto.getSmallClassificationCd());      // 小分類
        model.setCourseJnm(prmDto.getCourseJnm());                              // コース名

        // 2014/04/22 Add Start 検索条件の追加(短コース名)対応
        model.setCourseJnmShort(prmDto.getCourseJnmShort());                    // 短コース名
        // 2014/04/22 Add End   検索条件の追加(短コース名)対応

        model.setCourseEnm(prmDto.getCourseEnm());                              // コース名 (英語名)
        model.setAttachmentFlg(prmDto.getAttachmentFlg());                      // 添付付き有無フラグ
        model.setKeyword1(prmDto.getKeyword1());                                // キーワード1
        model.setKeyword2(prmDto.getKeyword2());                                // キーワード2
        model.setKeyword3(prmDto.getKeyword3());                                // キーワード3
        model.setKeyword4(prmDto.getKeyword4());                                // キーワード4
        model.setKeyword5(prmDto.getKeyword5());                                // キーワード5
        model.setSimpleExplanation(prmDto.getSimpleExplanation());              // 簡易説明
        model.setExplanation1(prmDto.getExplanation1());                        // 詳細説明1
        model.setExplanation2(prmDto.getExplanation2());                        // 詳細説明2
        model.setExplanation3(prmDto.getExplanation3());                        // 詳細説明3
        model.setExplanation4(prmDto.getExplanation4());                        // 詳細説明4
        model.setExplanation5(prmDto.getExplanation5());                        // 詳細説明5
        model.setOldExplanation6Nm(prmDto.getExplanation6Nm());                 // 詳細説明6(PDF)名
        model.setExplanation6(prmDto.getExplanationPDF());                      // 詳細説明6 PDF
        model.setLessonTime(prmDto.getLessonTime());                             // レッスン時間
        model.setUseStrDt(prmDto.getUseStrDt());                                // 提供開始日
        model.setUseEndDt(prmDto.getUseEndDt());                                // 提供終了日
        model.setBookFlg(prmDto.getBookFlg());                                  // 該当書籍有無フラグ
        model.setRemark(prmDto.getRemark());                                    // 備考
        model.setRecordVerNo(prmDto.getRecordVerNo());                          // レコードバージョン番号

        return model;

    }

}
