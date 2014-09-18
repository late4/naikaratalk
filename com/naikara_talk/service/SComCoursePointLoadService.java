package com.naikara_talk.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;

import com.naikara_talk.dbutil.DbUtil;
import com.naikara_talk.dto.CourseGoodsListDto;
import com.naikara_talk.dto.CourseUsePointListDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.logic.SComCoursePointLogic;
import com.naikara_talk.model.SComCoursePointModel;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>お客様(個人)_初期登録。<br>
 * <b>クラス名称       :</b>コース別ポイント一覧初期処理Serviceクラス。<br>
 * <b>クラス概要       :</b>コース別ポイントの一覧表示を行う<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/08/06 TECS 新規作成
 * <b>                 :</b>2014/04/22 TECS 検索条件：希望日のコントロールをテキストからプルダウンへ変更対応
 * <b>                 :</b>2014/04/22 TECS 検索条件：キーワード検索の削除・コース名の追加対応
 */
public class SComCoursePointLoadService implements ActionService {

    /**
     * コースマスタ、コース別利用ポイントマスタのデータ取得処理
     * <br>
     * @param model SComCoursePointModel
     * @return model SComCoursePointModel
     * @throws Exception
     */
    public List<CourseUsePointListDto> select(SComCoursePointModel model) throws NaiException {
        Connection conn = null;
        try {
            conn = DbUtil.getConnection();

            SComCoursePointLogic sComCoursePointLogic = new SComCoursePointLogic(conn);

            // DTOの初期化
            CourseUsePointListDto cupDto = new CourseUsePointListDto();

            // Model値をDTOにセット
            cupDto = this.modelToDto(model);

            // DTO値をModelにセット
            return sComCoursePointLogic.selectList(cupDto);

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
     * @param SComCoursePointModel 画面パラメータ<br>
     * @return cupDto 変換後結果<br>
     * @exception Exception
     */
    private CourseUsePointListDto modelToDto(SComCoursePointModel model) throws NaiException {

        // DTOの初期化
        CourseUsePointListDto cupDto = new CourseUsePointListDto();

        // 2014/04/22 Add Start 希望日をテキストからプルダウンへ変更対応
        // システム日付
        //cupDto.setSysDate(model.getSysDate());
        cupDto.setHopeDt(model.getHopeDt());
        // 2014/04/22 Add End   希望日をテキストからプルダウンへ変更対応

        // 2014/04/22 Del Start キーワード検索の削除・コース名の追加対応
        //// キーワード１
        //cupDto.setKeyword1(model.getKeyword1());

        //// キーワード２
        //cupDto.setKeyword2(model.getKeyword2());

        //// キーワード３
        //cupDto.setKeyword3(model.getKeyword3());

        //// キーワード４
        //cupDto.setKeyword4(model.getKeyword4());

        //// キーワード５
        //cupDto.setKeyword5(model.getKeyword5());
        // 2014/04/22 Del End   キーワード検索の削除・コース名の追加対応

        // 2014/04/22 Add Start 検索条件：コース名の追加対応
        // 検索条件部：コース名(大分類)
        cupDto.setSearchBigClassificationCd(model.getSearchCourseLargeCd());
        // 検索条件部：コース名(中分類)
        cupDto.setSearchMiddleClassificationCd(model.getSearchCourseMediumCd());
        // 検索条件部：コース名(小分類)
        cupDto.setSearchSmallClassificationCd(model.getSearchCourseSmallCd());
        // 検索条件部：コース名(コース名⇒キーワード)
        cupDto.setSearchKeyword(model.getSearchKeyword());
        // 2014/04/22 Add End   検索条件：コース名の追加対応

        return cupDto;

    }

    /**
     * コース単位の該当商品の取得処理
     * <br>
     * コース単位の該当商品の取得処理
     * <br>
     * @param String courseCd
     * @return List CourseGoodsListDto
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

    // 2014/04/22 Add Start 検索条件：コース名の追加対応
    /**
     * コード管理マスタからデータ取得処理。<br>
     * <br>
     * コード管理マスタからデータを取得処理する。<br>
     * <br>
     * @param category 汎用コード<br>
     * @return LinkedHashMap<String, String> ハッシュマップ<br>
     * @throws NaiException
     */
    public LinkedHashMap<String, String> selectCodeMst(String category) throws NaiException {

        Connection conn = null;

        try {
            conn = DbUtil.getConnection();

            SComCoursePointLogic logic = new SComCoursePointLogic(conn);

            // コード管理マスタ検索
            return logic.selectCodeMst(category);

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
    // 2014/04/22 Add End   検索条件：コース名の追加対応

}
