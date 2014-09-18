package com.naikara_talk.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.naikara_talk.dbutil.DbUtil;
import com.naikara_talk.dto.CourseGoodsListDto;
import com.naikara_talk.dto.CourseMstDto;
import com.naikara_talk.dto.CourseUsePointListDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.logic.SComCoursePointLogic;
import com.naikara_talk.model.SComCoursePointModel;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>お客様(個人)_初期登録。<br>
 * <b>クラス名称       :</b>コース別ポイント一覧クラス<br>
 * <b>クラス概要       :</b>コース別ポイント一覧の検索処理Service<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/08/06 TECS 新規作成
 * <b>                 :</b>2014/04/22 TECS 検索条件：希望日のコントロールをテキストからプルダウンへ変更対応
 * <b>                 :</b>2014/04/22 TECS 検索条件：キーワード検索の削除・コース名の追加対応
 */
public class SComCoursePointSearchService {

    /** 一覧 ZERO件 */
    private static final int LIST_ZERO_CNT = 0;

    /** 検索前チェック：データ件数ZERO件 */
    public static final int ERR_ZERO_DATA = 2;

    /**
     * 検索前チェック処理<br>
     * <br>
     * 検索前チェック処理<br>
     * <br>
     * @param SComCoursePointModel<br>
     * @return int チェック結果<br>
     * @exception Exception
     */
    public int checkPreSelect(SComCoursePointModel model) throws NaiException {

        // 入力チェック - DBアクセスありチェック
        // 共通部品：コースマスタ、コース別利用ポイントマスタのデータ件数取得処理
        int count = getRowCount(model);
        if (LIST_ZERO_CNT == count) {
            return ERR_ZERO_DATA;
        }
        // 正常
        return SComCoursePointModel.CHECK_OK;

    }

    /**
     * 検索データ件数取得。<br>
     * <br>
     * 検索データ件数取得。<br>
     * <br>
     * @param SComCoursePointModel 画面のパラメータ<br>
     * @return int 検索データ件数<br>
     * @exception Exception
     */
    public int getRowCount(SComCoursePointModel model) throws NaiException {

        Connection conn = null;
        try {
            conn = DbUtil.getConnection();

            // 初期化処理
            SComCoursePointLogic sComCoursePointLogic = new SComCoursePointLogic(conn);

            // DTOの初期化
            CourseMstDto dto = new CourseMstDto();

            // Model値をDTOにセット
            dto = this.modelToDto(model);

            // データの取得件数＆リターン
            return sComCoursePointLogic.getRowCount(dto);
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
     * Model値をDTOにセット。<br>
     * <br>
     * @param SComCoursePointModel 画面のパラメータ<br>
     * @return CourseUsePointListDto<br>
     * @exception Exception
     */
    private CourseMstDto modelToDto(SComCoursePointModel model) throws NaiException {

        // DTOの初期化
        CourseMstDto dto = new CourseMstDto();

        // 2014/04/22 Upd Start 希望日をテキストからプルダウンへ変更対応
        //dto.setUseStrDt(model.getSysDate());
        //dto.setUseEndDt(model.getSysDate());
        dto.setUseStrDt(model.getHopeDt());
        dto.setUseEndDt(model.getHopeDt());
        // 2014/04/22 Upd Start 希望日をテキストからプルダウンへ変更対応

        // 2014/04/22 Del Start キーワード検索の削除・コース名の追加対応
        //dto.setKeyword1(model.getKeyword1());
        //dto.setKeyword2(model.getKeyword2());
        //dto.setKeyword3(model.getKeyword3());
        //dto.setKeyword4(model.getKeyword4());
        //dto.setKeyword5(model.getKeyword5());
        // 2014/04/22 Del End   キーワード検索の削除・コース名の追加対応

        // 2014/04/22 Add Start コース名の追加対応 ※コース名(コース名)⇒コース名(キーワード)とする
        dto.setBigClassificationCd(model.getSearchCourseLargeCd());
        dto.setMiddleClassificationCd(model.getSearchCourseMediumCd());
        dto.setSmallClassificationCd(model.getSearchCourseSmallCd());
        dto.setKeyword1(model.getSearchKeyword());
        // 2014/04/22 Add End   コース名の追加対応 ※コース名(コース名)⇒コース名(キーワード)とする

        return dto;
    }

    /**
     * Model値をDTOにセット。<br>
     * <br>
     * Model値をDTOにセット。<br>
     * <br>
     * @param SComCoursePointModel 画面のパラメータ<br>
     * @return CourseUsePointListDto<br>
     * @exception Exception
     */
    private CourseUsePointListDto modelToListDto(SComCoursePointModel model) throws NaiException {

        // DTOの初期化
        CourseUsePointListDto dto = new CourseUsePointListDto();

        // 2014/04/22 Upd Start 希望日をテキストからプルダウンへ変更対応
        //dto.setSysDate(model.getSysDate());
        dto.setHopeDt(model.getHopeDt());
        // 2014/04/22 Upd End   希望日をテキストからプルダウンへ変更対応

        // 2014/04/22 Del Start キーワード検索の削除・コース名の追加対応
        //dto.setKeyword1(model.getKeyword1());
        //dto.setKeyword2(model.getKeyword2());
        //dto.setKeyword3(model.getKeyword3());
        //dto.setKeyword4(model.getKeyword4());
        //dto.setKeyword5(model.getKeyword5());
        // 2014/04/22 Del End   キーワード検索の削除・コース名の追加対応

        // 2014/04/22 Add Start コース名の追加対応 ※コース名(コース名)⇒コース名(キーワード)とする
        dto.setSearchBigClassificationCd(model.getSearchCourseLargeCd());
        dto.setSearchMiddleClassificationCd(model.getSearchCourseMediumCd());
        dto.setSearchSmallClassificationCd(model.getSearchCourseSmallCd());
        dto.setSearchKeyword(model.getSearchKeyword());
        // 2014/04/22 Add End   コース名の追加対応 ※コース名(コース名)⇒コース名(キーワード)とする

        return dto;
    }

    /**
     * 画面データの取得、初回表示の場合。<br>
     * <br>
     * 画面データの取得、初回表示の場合。<br>
     * <br>
     * @param SComCoursePointModel 画面のパラメータ<br>
     * @return List<CourseUsePointListDto><br>
     * @exception Exception
     */
    public List<CourseUsePointListDto> selectList(SComCoursePointModel model) throws NaiException {

        Connection conn = null;
        try {
            conn = DbUtil.getConnection();

            // 初期化処理
            SComCoursePointLogic sComCoursePointLogic = new SComCoursePointLogic(conn);

            // DTOの初期化
            CourseUsePointListDto dto = new CourseUsePointListDto();

            // Model値をDTOにセット
            dto = this.modelToListDto(model);

            // データの取得＆リターン
            return sComCoursePointLogic.selectList(dto);
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

            SComCoursePointLogic sComCoursePointLogic = new SComCoursePointLogic(conn);

            // DTOの初期化
            CourseGoodsListDto cglDto = new CourseGoodsListDto();

            // Model値をDTOにセット
            cglDto.setCourseCd(courseCd);

            // 検索実行
            return sComCoursePointLogic.selectGoods(cglDto);

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
