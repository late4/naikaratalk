package com.naikara_talk.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.naikara_talk.dbutil.DbUtil;
import com.naikara_talk.dto.CourseGoodsListDto;
import com.naikara_talk.dto.CourseMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.logic.DetailDescriptionLogic;
import com.naikara_talk.model.DetailDescriptionModel;
import com.naikara_talk.util.NaikaraStringUtil;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>お客様(個人)_初期登録<br>
 * <b>クラス名称　　　:</b>詳細説明初期処理Serviceクラス。<br>
 * <b>クラス概要　　　:</b>詳細説明の情報を表示。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/08/01 TECS 新規作成。
 */
public class DetailDescriptionLoadService implements ActionService {

    /**
     * 検索前チェック処理<br>
     * <br>
     * 検索前チェック処理<br>
     * <br>
     * @param DetailDescriptionModel<br>
     * @return int チェック結果<br>
     * @exception Exception
     */
    public int checkPreSelect(DetailDescriptionModel model) throws NaiException {

        // 入力チェック - DBアクセスありチェック
        // 共通部品：ポイント管理マスタのデータ件数取得処理
        return getRowCount(model);

    }

    /**
     * 検索データ件数取得。<br>
     * <br>
     * 検索データ件数取得。<br>
     * <br>
     * @param DetailDescriptionModel 画面のパラメータ<br>
     * @return int 検索データ件数<br>
     * @exception NaiException
     */
    public int getRowCount(DetailDescriptionModel model) throws NaiException {

        Connection conn = null;
        try {
            conn = DbUtil.getConnection();

            // 初期化処理
            DetailDescriptionLogic logic = new DetailDescriptionLogic(conn);

            // DTOの初期化
            CourseMstDto dto = new CourseMstDto();

            // Model値をDTOにセット
            dto = this.modelToDto(model);

            // データの取得件数＆リターン
            int count = logic.getRowCount(dto);
            model.setCount(count);
            return model.getCount();
        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
                throw new NaiException(e1);
            }
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
     * 初期処理<br>
     * <br>
     * 画面項目の初期処理を行う<br>
     * <br>
     * @param DetailDescriptionModel 画面パラメータ<br>
     * @return DetailDescriptionModel 新画面パラメータ<br>
     * @exception NaiException
     */
    public DetailDescriptionModel select(DetailDescriptionModel model) throws NaiException {

        Connection conn = null;
        try {
            conn = DbUtil.getConnection();
            DetailDescriptionLogic detailDescriptionLogic = new DetailDescriptionLogic(conn);

            // DTOの初期化
            CourseMstDto prmDto = new CourseMstDto();

            // Model値をDTOにセット
            prmDto = this.modelToDto(model);

            // 検索実行
            CourseMstDto resultDto = detailDescriptionLogic.select(prmDto);

            // DTO値をModelにセット
            model = this.dtoToModel(resultDto, model);

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
     * コース単位の該当商品の取得処理
     * <br>
     * コース単位の該当商品の取得処理
     * <br>
     * @param String courseCd
     * @return List<CourseGoodsListDto>
     * @throws Exception
     */
    public List<CourseGoodsListDto> selectGoods(String courseCd) throws NaiException {
        Connection conn = null;
        try {
            conn = DbUtil.getConnection();

            DetailDescriptionLogic detailDescriptionLogic = new DetailDescriptionLogic(conn);

            // DTOの初期化
            CourseGoodsListDto cglDto = new CourseGoodsListDto();

            // Model値をDTOにセット
            cglDto.setCourseCd(courseCd);

            // 検索実行
            return detailDescriptionLogic.selectGoods(cglDto);

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
     * Model値をセット<br>
     * <br>
     * Model値をDTOにセット<br>
     * <br>
     * @param DetailDescriptionModel 画面パラメータ<br>
     * @return prmDto 変換後結果<br>
     * @exception Exception
     */
    private CourseMstDto modelToDto(DetailDescriptionModel model) throws NaiException {

        // DTOの初期化
        CourseMstDto prmDto = new CourseMstDto();
        prmDto.setCourseCd(model.getCourseCd());                  // 組織ID
        return prmDto;

    }

    /**
     * DTO値をModelにセット。<br>
     * <br>
     * DTO値をModelにセット。<br>
     * <br>
     * @param CourseMstDto
     * @param DetailDescriptionModel
     * @return DetailDescriptionModel
     * @throws Exception
     */
    private DetailDescriptionModel dtoToModel(CourseMstDto prmDto, DetailDescriptionModel model) throws NaiException {

        model.setBigClassificationCdNm(prmDto.getBigClassificationCdNm());                                // 大分類名
        model.setMiddleClassificationCdNm(prmDto.getMiddleClassificationCdNm());                            // 中分類名
        model.setSmallClassificationCdNm(prmDto.getSmallClassificationCdNm());                            // 小分類名
        model.setCourseJnm(prmDto.getCourseJnm());                                                        // コース名
        model.setAttachmentFlg(prmDto.getAttachmentFlg());                                                // 添付付き有無フラグ名
        model.setLessonTime(prmDto.getLessonTime());                                                      // レッスン時間
        model.setUseStrDt(NaikaraStringUtil.converToYYYY_MM_DD(prmDto.getUseStrDt()));                    // 提供開始日
        model.setUseEndDt(NaikaraStringUtil.converToYYYY_MM_DD(prmDto.getUseEndDt()));                    // 提供終了日
        model.setExplanation1(prmDto.getExplanation1());                                                  // 詳細説明1
        model.setExplanation2(prmDto.getExplanation2());                                                  // 詳細説明2
        model.setExplanation3(prmDto.getExplanation3());                                                  // 詳細説明3
        model.setExplanation4(prmDto.getExplanation4());                                                  // 詳細説明4
        model.setExplanation5(prmDto.getExplanation5());                                                  // 詳細説明5
        model.setExplanation6Nm(prmDto.getExplanation6Nm());                                              // 詳細説明6
        model.setExplanation6(prmDto.getExplanation6());                                                  // 詳細説明6(PDF)
        return model;

    }

}
