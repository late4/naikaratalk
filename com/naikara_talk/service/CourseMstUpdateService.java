package com.naikara_talk.service;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dbutil.DbUtil;
import com.naikara_talk.dto.CourseGoodsListDto;
import com.naikara_talk.dto.CourseGoodsMstDto;
import com.naikara_talk.dto.CourseMstDto;
import com.naikara_talk.dto.CourseUsePointMstDto;
import com.naikara_talk.dto.GoodsMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.logic.CourseMstLogic;
import com.naikara_talk.logic.GoodsCourseUsePointMstLogic;
import com.naikara_talk.logic.TeacherMstLogic;
import com.naikara_talk.model.CourseMstListModel;
import com.naikara_talk.model.CourseMstModel;
import com.naikara_talk.util.DateUtil;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>マスタ保守<br>
 * <b>クラス名称       :</b>コースマスタメンテナンス(単票)<br>
 * <b>クラス概要       :</b>コースマスタメンテナンス(単票)登録更新Service<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/08/14 TECS 新規作成
 *                     :</b>2014/04/22 TECS 項目の追加(短コース名)対応
 */
public class CourseMstUpdateService implements ActionService {

    /** ドロップダウンリストの必須チェック　(大分類) */
    public static final int ERR_NOT_SELECT_COURSELARGE_SEL = 1;

    /** ドロップダウンリストの必須チェック　(中分類) */
    public static final int ERR_NOT_SELECT_COURSEMEDIUM_SEL = 2;

    /** ドロップダウンリストの必須チェック　(小分類) */
    public static final int ERR_NOT_SELECT_COURSESMALL_SEL = 3;

    /** チェック：｢提供期間：開始日｣　＞　｢提供期間：終了日｣　の場合 */
    public static final int ERR_INTEGRITY_DT = 4;

    /** チェック：提供期間：開始日のチェック (日付)　※処理区分[修正]の場合 */
    public static final int ERR_OLD_USESTRDT = 5;

    /** チェック：提供期間：終了日のチェック (日付)　※処理区分[修正]の場合 */
    public static final int ERR_USEENDDT = 6;

    /** チェック： コース別利用ポイント設定画面にて、1件でもデータを保存してあるかどうかのチェック */
    public static final int ERR_ZERO_COUNT = 7;

    /** チェック： 提供期間とコース別利用ポイントの整合性チェック */
    public static final int ERR_CONTRACT_DT = 8;

    /** チェック：提供期間と該当商品の期間の範囲チェック(提供開始日) */
    public static final int ERR_GOODS_STR_DATE = 9;

    /** チェック：提供期間と該当商品の期間の範囲チェック(提供終了日) */
    public static final int ERR_GOODS_END_DATE = 10;

    /** チェック：データの存在チェック(新規) */
    public static final int ERR_CHECK_COURSECD_INS = 11;

    /** チェック：データの存在チェック(修正) */
    public static final int ERR_CHECK_COURSECD_UPD = 12;

    /** チェック： 詳細説明６：削除　のチェック */
    public static final int ERR_FILE = 13;

    /** チェック： ファイルサイズのチェック */
    public static final int ERR_FILE_MAX = 14;

    /** チェック：｢該当商品｣が複数設定されている場合は、重複チェック */
    public static final int ERR_GOODS_REPEAT = 15;

    /**
     * 登録、更新のチェック。<br>
     * <br>
     * 登録、更新のチェック。<br>
     * <br>
     * @param CourseMstModel<br>
     * @return int チェック結果<br>
     * @exception Exception
     */
    public int errorCheck(CourseMstModel model) throws NaiException {

        Connection conn = null;
        try {
            conn = DbUtil.getConnection();
            CourseMstLogic logic = new CourseMstLogic(conn);

            // 関連チェック
            // 大分類のコード値＝”0000”の場合
            if (StringUtils.equals(NaikaraTalkConstants.CHOICE_ALL_ZERO, model.getBigClassificationCd())) {
                return ERR_NOT_SELECT_COURSELARGE_SEL;
            }

            // 中分類のコード値＝”0000”の場合
            if (StringUtils.equals(NaikaraTalkConstants.CHOICE_ALL_ZERO, model.getMiddleClassificationCd())) {
                return ERR_NOT_SELECT_COURSEMEDIUM_SEL;
            }

            // 小分類のコード値＝”0000”の場合
            if (StringUtils.equals(NaikaraTalkConstants.CHOICE_ALL_ZERO, model.getSmallClassificationCd())) {
                return ERR_NOT_SELECT_COURSESMALL_SEL;
            }

            // ｢提供期間：開始日｣　＞　｢提供期間：終了日｣　の場合
            if (DateUtil.dateCompare2(NaikaraStringUtil.converToYYYYMMDD(model.getUseStrDt()),
                    NaikaraStringUtil.converToYYYYMMDD(model.getUseEndDt()))) {
                return ERR_INTEGRITY_DT;
            }

            // 処理区分[修正]の場合
            if (StringUtils.equals(CourseMstListModel.PROS_KBN_UPD, model.getProcessKbn_rdl())) {
                // 提供期間：開始日のチェック (日付)
                if (!StringUtils.equals(NaikaraStringUtil.converToYYYYMMDD(model.getOldUseStrDt()),
                        NaikaraStringUtil.converToYYYYMMDD(model.getUseStrDt()))) {
                    if (!DateUtil.dateCompare3(NaikaraStringUtil.converToYYYYMMDD(model.getOldUseStrDt()),
                            DateUtil.getDateAddDay(DateUtil.getSysDate(), 28))) {
                        return ERR_OLD_USESTRDT;
                    }
                }
                // 提供期間：終了日のチェック (日付)
                if (!DateUtil.dateCompare3(NaikaraStringUtil.converToYYYYMMDD(model.getUseEndDt()),
                        DateUtil.getDateAddDay(DateUtil.getSysDate(), 28))) {
                    return ERR_USEENDDT;
                }
            }

            List<CourseUsePointMstDto> tempCourseUsePointMstDtoList = new ArrayList<CourseUsePointMstDto>();
            tempCourseUsePointMstDtoList.addAll(model.getCourseUsePointMstDtoList());
            // リストソート
            Collections.sort(tempCourseUsePointMstDtoList, new ComparatorCourseUsePointMstDtos());
            if (0 == tempCourseUsePointMstDtoList.size()) {
                return ERR_ZERO_COUNT;
            } else {
                if (!(StringUtils.equals(NaikaraStringUtil.converToYYYYMMDD(model.getUseStrDt()),
                        NaikaraStringUtil.converToYYYYMMDD(tempCourseUsePointMstDtoList.get(0).getUsePointStrDt())) && StringUtils
                        .equals(NaikaraStringUtil.converToYYYYMMDD(model.getUseEndDt()),
                                NaikaraStringUtil.converToYYYYMMDD(tempCourseUsePointMstDtoList.get(
                                        tempCourseUsePointMstDtoList.size() - 1).getUsePointEndDt())))) {
                    StringBuffer sb = new StringBuffer();
                    sb.append("コースマスタメンテナンス画面の提供期間(")
                            .append(NaikaraStringUtil.converToYYYYMMDD(model.getUseStrDt()))
                            .append("～")
                            .append(NaikaraStringUtil.converToYYYYMMDD(model.getUseEndDt()))
                            .append(")と利用ポイント開始日(")
                            .append(NaikaraStringUtil.converToYYYYMMDD(tempCourseUsePointMstDtoList.get(0)
                                    .getUsePointStrDt()))
                            .append(")・終了日(")
                            .append(NaikaraStringUtil.converToYYYYMMDD(tempCourseUsePointMstDtoList.get(
                                    tempCourseUsePointMstDtoList.size() - 1).getUsePointEndDt())).append(")");
                    model.setCheckMessage(sb.toString());
                    return ERR_CONTRACT_DT;
                }
            }

            // コース別利用ポイントマスタ．利用ポイント開始日≧画面の「提供開始日」
            // コース別利用ポイントマスタ．利用ポイント開始日≦画面の「提供終了日」
            // コース別利用ポイントマスタ．利用ポイント終了日≧画面の「提供開始日」
            // コース別利用ポイントマスタ．利用ポイント終了日≦画面の「提供終了日」
            // 上記の場合のみＯＫ
            for (CourseUsePointMstDto courseUsePointMstDto : model.getCourseUsePointMstDtoList()) {
                if (!(DateUtil.dateCompare3(
                        NaikaraStringUtil.converToYYYYMMDD(courseUsePointMstDto.getUsePointStrDt()),
                        NaikaraStringUtil.converToYYYYMMDD(model.getUseStrDt()))
                        && DateUtil.dateCompare3(NaikaraStringUtil.converToYYYYMMDD(model.getUseEndDt()),
                                NaikaraStringUtil.converToYYYYMMDD(courseUsePointMstDto.getUsePointStrDt()))
                        && DateUtil.dateCompare3(
                                NaikaraStringUtil.converToYYYYMMDD(courseUsePointMstDto.getUsePointEndDt()),
                                NaikaraStringUtil.converToYYYYMMDD(model.getUseStrDt())) && DateUtil.dateCompare3(
                        NaikaraStringUtil.converToYYYYMMDD(model.getUseEndDt()),
                        NaikaraStringUtil.converToYYYYMMDD(courseUsePointMstDto.getUsePointEndDt())))) {
                    StringBuffer sb = new StringBuffer();
                    sb.append("コースマスタメンテナンス画面の提供期間(").append(NaikaraStringUtil.converToYYYYMMDD(model.getUseStrDt()))
                            .append("～").append(NaikaraStringUtil.converToYYYYMMDD(model.getUseEndDt()))
                            .append(")と利用ポイント開始日(")
                            .append(NaikaraStringUtil.converToYYYYMMDD(courseUsePointMstDto.getUsePointStrDt()))
                            .append(")・終了日(")
                            .append(NaikaraStringUtil.converToYYYYMMDD(courseUsePointMstDto.getUsePointEndDt()))
                            .append(")");
                    model.setCheckMessage(sb.toString());
                    return ERR_CONTRACT_DT;
                }
            }

            // 提供期間と該当商品の期間の範囲チェック
            for (int i = 0; i < model.getCourseGoodsListDtoList().size(); i++) {
                CourseGoodsListDto tempDto = model.getCourseGoodsListDtoList().get(i);
                if (StringUtils.isEmpty(tempDto.getGoodsCd())) {
                    continue;
                }
                GoodsMstDto goodsMstDto = new GoodsMstDto();
                goodsMstDto.setGoodsCd(tempDto.getGoodsCd());
                goodsMstDto = logic.getGoods(goodsMstDto);
                // 販売商品マスタ．提供開始日≦画面の「提供終了日」
                if (!DateUtil.dateCompare3(NaikaraStringUtil.converToYYYYMMDD(model.getUseEndDt()),
                        NaikaraStringUtil.converToYYYYMMDD(goodsMstDto.getUseStrDt()))) {
                    model.setCheckMessage(String.valueOf(i + 1));
                    return ERR_GOODS_STR_DATE;
                }
                // 販売商品マスタ．提供終了日≧画面の「提供開始日」
                if (!DateUtil.dateCompare3(NaikaraStringUtil.converToYYYYMMDD(goodsMstDto.getUseEndDt()),
                        NaikaraStringUtil.converToYYYYMMDD(model.getUseStrDt()))) {
                    model.setCheckMessage(String.valueOf(i + 1));
                    return ERR_GOODS_END_DATE;
                }
            }

            // データの存在チェック
            CourseMstDto courseMstDto = new CourseMstDto();
            courseMstDto.setCourseCd(model.getCourseCd());
            int isExist = logic.getExist(courseMstDto);
            if (StringUtils.equals(CourseMstListModel.PROS_KBN_ADD, model.getProcessKbn_rdl())) {
                if (NaikaraTalkConstants.RETURN_CD_DATA_YES == isExist) {
                    return ERR_CHECK_COURSECD_INS;
                }
            } else if (StringUtils.equals(CourseMstListModel.PROS_KBN_UPD, model.getProcessKbn_rdl())) {
                if (NaikaraTalkConstants.RETURN_CD_DATA_NO == isExist) {
                    return ERR_CHECK_COURSECD_UPD;
                }
            }

            // 詳細説明６：ファイル名==なし && 詳細説明６：削除==true
            if (StringUtils.equals(NaikaraTalkConstants.TRUE, model.getDetail6FileDel())
                    && StringUtils.isEmpty(model.getOldExplanation6Nm())) {
                return ERR_FILE;
            }

            // ファイルサイズが１５．０ＭＢ以上の場合
            if (model.getExplanation6() != null) {
                FileInputStream fis = new FileInputStream(model.getExplanation6());
                if (fis.available() > 15 * 1024 * 1024) {
                    return ERR_FILE_MAX;
                }
            }

            // ｢該当商品｣のリスト内に重複するコースコードが存在する場合
            for (int i = 0; i < model.getCourseGoodsListDtoList().size(); i++) {
                if (StringUtils.isEmpty(model.getCourseGoodsListDtoList().get(i).getGoodsCd())
                        || StringUtils.equals(NaikaraTalkConstants.TRUE, model.getCourseGoodsListDtoList().get(i)
                                .getCourse_chkn())) {
                    continue;
                }
                for (int j = 0; j < model.getCourseGoodsListDtoList().size(); j++) {
                    if (StringUtils.isEmpty(model.getCourseGoodsListDtoList().get(j).getGoodsCd())
                            || StringUtils.equals(NaikaraTalkConstants.TRUE, model.getCourseGoodsListDtoList().get(j)
                                    .getCourse_chkn())) {
                        continue;
                    }
                    if (i == j) {
                        continue;
                    }
                    if (StringUtils.equals(model.getCourseGoodsListDtoList().get(i).getGoodsCd(), model
                            .getCourseGoodsListDtoList().get(j).getGoodsCd())) {
                        model.setCheckMessage("該当商品(" + (i + 1) + "個目と" + (j + 1) + "個目)");
                        return ERR_GOODS_REPEAT;
                    }
                }
            }
            return CourseMstModel.PROCESS_NORMAL;

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
     * 登録処理。<br>
     * <br>
     * 登録処理。<br>
     * <br>
     * @param CourseMstModel 画面のパラメータ<br>
     * @return int<br>
     * @exception NaiException
     */
    public int insert(CourseMstModel model) throws NaiException {
        int cnt = NaikaraTalkConstants.RETURN_CD_ERR_INS;

        Connection conn = null;
        try {
            conn = DbUtil.getConnection();

            // コースマスタへ追加処理を行う　
            cnt = this.insertCourseMst(model, conn);
            if (NaikaraTalkConstants.RETURN_CD_ERR_INS == cnt) {
                // ロールバック
                try {
                    conn.rollback();
                } catch (Exception e1) {
                    e1.printStackTrace();
                    throw new NaiException(e1);
                }
                return cnt;
            }

            // コース別利用ポイントマスタへ追加処理を行う　
            for (CourseUsePointMstDto courseUsePointMstDto : model.getCourseUsePointMstDtoList()) {
                courseUsePointMstDto.setCourseCd(model.getCourseCd());
                cnt = this.insertCourseUsePointMst(courseUsePointMstDto, conn);
                if (NaikaraTalkConstants.RETURN_CD_ERR_INS == cnt) {
                    // ロールバック
                    try {
                        conn.rollback();
                    } catch (Exception e1) {
                        e1.printStackTrace();
                        throw new NaiException(e1);
                    }
                    return cnt;
                }
            }

            // コース別商品マスタへ追加処理を行う　
            for (CourseGoodsListDto courseGoodsListDto : model.getCourseGoodsListDtoList()) {
                if (!StringUtils.isEmpty(courseGoodsListDto.getGoodsCd())) {
                    CourseGoodsMstDto courseGoodsMstDto = new CourseGoodsMstDto();
                    courseGoodsMstDto.setCourseCd(model.getCourseCd());
                    courseGoodsMstDto.setGoodsCd(courseGoodsListDto.getGoodsCd());
                    cnt = this.insertCourseGoodsMst(courseGoodsMstDto, conn);
                    if (NaikaraTalkConstants.RETURN_CD_ERR_INS == cnt) {
                        // ロールバック
                        try {
                            conn.rollback();
                        } catch (Exception e1) {
                            e1.printStackTrace();
                            throw new NaiException(e1);
                        }
                        return cnt;
                    }
                }
            }

            // コミット
            conn.commit();
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
        return cnt;
    }

    /**
     * 更新処理。<br>
     * <br>
     * 更新処理。<br>
     * <br>
     * @param CourseMstModel 画面のパラメータ<br>
     * @return int<br>
     * @exception NaiException
     */
    public int update(CourseMstModel model) throws NaiException {
        int cnt = NaikaraTalkConstants.RETURN_CD_ERR_UPD;
        int delcnt = NaikaraTalkConstants.RETURN_CD_ERR_DEL;

        Connection conn = null;
        try {
            conn = DbUtil.getConnection();

            // コースマスタへ更新処理を行う
            cnt = this.updateCourseMst(model, conn);
            if (NaikaraTalkConstants.RETURN_CD_ERR_UPD == cnt || NaikaraTalkConstants.RETURN_CD_ERR_NO_UPD == cnt) {
                // ロールバック
                try {
                    conn.rollback();
                } catch (Exception e1) {
                    e1.printStackTrace();
                    throw new NaiException(e1);
                }
                return cnt;
            }

            // 検索用DTO作成
            CourseUsePointMstDto delCourseUsePointMstDto = new CourseUsePointMstDto();
            delCourseUsePointMstDto.setCourseCd(model.getCourseCd());
            // 削除用リスト取得処理
            GoodsCourseUsePointMstLogic goodsCourseUsePointMstLogic = new GoodsCourseUsePointMstLogic(conn);
            List<CourseUsePointMstDto> courseUsePointMstDtoList = goodsCourseUsePointMstLogic
                    .select(delCourseUsePointMstDto);
            // データがない場合リストをクリアする
            if (NaikaraTalkConstants.RETURN_CD_DATA_NO == courseUsePointMstDtoList.get(0).getReturnCode()) {
                courseUsePointMstDtoList.clear();
            }
            // ループ削除用リスト
            for (int i = 0; i < courseUsePointMstDtoList.size(); i++) {
                // 画面リストをループする
                for (int j = 0; j < model.getCourseUsePointMstDtoList().size(); j++) {
                    // 商品コードが同じの場合
                    if (StringUtils.equals(model.getCourseUsePointMstDtoList().get(j).getUsePointStrDt(),
                            courseUsePointMstDtoList.get(i).getUsePointStrDt())) {
                        // 該当データを削除する
                        courseUsePointMstDtoList.remove(i);
                        i = -1;
                        break;
                    }
                }
            }
            // ループ削除用リスト
            for (CourseUsePointMstDto courseUsePointMstDto : courseUsePointMstDtoList) {
                // コース別利用ポイントマスタへ削除処理を行う　
                delcnt = this.delCourseUsePointMst(courseUsePointMstDto, conn);
                if (NaikaraTalkConstants.RETURN_CD_ERR_DEL == delcnt) {
                    cnt = delcnt;
                    // ロールバック
                    try {
                        conn.rollback();
                    } catch (Exception e1) {
                        e1.printStackTrace();
                        throw new NaiException(e1);
                    }
                    return cnt;
                }
            }
            // コース別利用ポイントマスタへ追加・更新処理を行う　
            for (CourseUsePointMstDto courseUsePointMstDto : model.getCourseUsePointMstDtoList()) {
                CourseMstLogic courseMstLogic = new CourseMstLogic(conn);
                // コース別利用ポイントマスタへ追加処理を行う　
                if (NaikaraTalkConstants.RETURN_CD_DATA_NO == courseMstLogic
                        .getExistCourseUsePointMst(courseUsePointMstDto)) {
                    cnt = this.insertCourseUsePointMst(courseUsePointMstDto, conn);
                    if (NaikaraTalkConstants.RETURN_CD_ERR_INS == cnt) {
                        // ロールバック
                        try {
                            conn.rollback();
                        } catch (Exception e1) {
                            e1.printStackTrace();
                            throw new NaiException(e1);
                        }
                        return cnt;
                    }
                    // コース別利用ポイントマスタへ更新処理を行う　
                } else {
                    cnt = this.updateCourseUsePointMst(courseUsePointMstDto, conn);
                    if (NaikaraTalkConstants.RETURN_CD_ERR_UPD == cnt
                            || NaikaraTalkConstants.RETURN_CD_ERR_NO_UPD == cnt) {
                        // ロールバック
                        try {
                            conn.rollback();
                        } catch (Exception e1) {
                            e1.printStackTrace();
                            throw new NaiException(e1);
                        }
                        return cnt;
                    }
                }

            }

            // 検索用DTO作成
            CourseGoodsListDto delCourseGoodsListDto = new CourseGoodsListDto();
            delCourseGoodsListDto.setCourseCd(model.getCourseCd());
            // 削除用リスト取得処理
            CourseMstLogic courseMstLogic = new CourseMstLogic(conn);
            List<CourseGoodsListDto> courseGoodsListDtoList = courseMstLogic
                    .getCourseGoodsListDtos(delCourseGoodsListDto);
            // データがない場合リストをクリアする
            if (NaikaraTalkConstants.RETURN_CD_DATA_NO == courseGoodsListDtoList.get(0).getReturnCode()) {
                courseGoodsListDtoList.clear();
            }
            // ループ削除用リスト
            for (int i = 0; i < courseGoodsListDtoList.size(); i++) {
                // 画面リストをループする
                for (int j = 0; j < model.getCourseGoodsListDtoList().size(); j++) {
                    // 画面の該当データがブランクまたは削除チェックボックスがチェックされた場合
                    if (StringUtils.isEmpty(model.getCourseGoodsListDtoList().get(j).getGoodsCd())
                            || StringUtils.equals(NaikaraTalkConstants.TRUE, model.getCourseGoodsListDtoList().get(j)
                                    .getCourse_chkn())) {
                        continue;
                    }
                    // 商品コードが同じの場合
                    if (StringUtils.equals(model.getCourseGoodsListDtoList().get(j).getGoodsCd(),
                            courseGoodsListDtoList.get(i).getGoodsCd())) {
                        // 該当データを削除する
                        courseGoodsListDtoList.remove(i);
                        i = -1;
                        break;
                    }
                }
            }
            // ループ削除用リスト
            for (CourseGoodsListDto courseGoodsListDto : courseGoodsListDtoList) {
                // コース別商品マスタへ削除処理を行う　
                CourseGoodsMstDto delCourseGoodsMstDto = new CourseGoodsMstDto();
                delCourseGoodsMstDto.setCourseCd(model.getCourseCd());
                delCourseGoodsMstDto.setGoodsCd(courseGoodsListDto.getGoodsCd());
                delcnt = this.deleteCourseGoodsMst(delCourseGoodsMstDto, conn);
                if (NaikaraTalkConstants.RETURN_CD_ERR_DEL == delcnt) {
                    cnt = delcnt;
                    // ロールバック
                    try {
                        conn.rollback();
                    } catch (Exception e1) {
                        e1.printStackTrace();
                        throw new NaiException(e1);
                    }
                    return cnt;
                }
            }

            // コース別商品マスタへ追加・更新処理を行う　
            for (CourseGoodsListDto courseGoodsListDto : model.getCourseGoodsListDtoList()) {
                if (!StringUtils.isEmpty(courseGoodsListDto.getGoodsCd())
                        && !StringUtils.equals(NaikaraTalkConstants.TRUE, courseGoodsListDto.getCourse_chkn())) {
                    CourseGoodsMstDto courseGoodsMstDto = new CourseGoodsMstDto();
                    courseGoodsMstDto.setCourseCd(model.getCourseCd());
                    courseGoodsMstDto.setGoodsCd(courseGoodsListDto.getGoodsCd());
                    // コース別商品マスタへ追加処理を行う
                    if (NaikaraTalkConstants.RETURN_CD_DATA_NO == courseMstLogic
                            .getExistCourseGoodsMstDto(courseGoodsMstDto)) {
                        cnt = this.insertCourseGoodsMst(courseGoodsMstDto, conn);
                        if (NaikaraTalkConstants.RETURN_CD_ERR_INS == cnt) {
                            // ロールバック
                            try {
                                conn.rollback();
                            } catch (Exception e1) {
                                e1.printStackTrace();
                                throw new NaiException(e1);
                            }
                            return cnt;
                        }
                        // コース別商品マスタへ更新処理を行う　
                    } else {
                        cnt = this.updagteCourseGoodsMst(courseGoodsMstDto, conn);
                        if (NaikaraTalkConstants.RETURN_CD_ERR_UPD == cnt
                                || NaikaraTalkConstants.RETURN_CD_ERR_NO_UPD == cnt) {
                            // ロールバック
                            try {
                                conn.rollback();
                            } catch (Exception e1) {
                                e1.printStackTrace();
                                throw new NaiException(e1);
                            }
                            return cnt;
                        }
                    }
                }
            }

            // コミット
            conn.commit();

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
        return cnt;
    }

    /**
     * コード管理マスタからデータ取得処理。<br>
     * <br>
     * コード管理マスタからデータ取得処理。<br>
     * <br>
     * @param category 汎用コード<br>
     * @return LinkedHashMap<String, String><br>
     * @exception NaiException
     */
    public LinkedHashMap<String, String> selectCodeMst(String category) throws NaiException {

        Connection conn = null;

        try {
            conn = DbUtil.getConnection();
            TeacherMstLogic teacherMstLogic = new TeacherMstLogic(conn);
            // コード管理マスタ検索
            return teacherMstLogic.selectCodeMst(category);
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
     * コースマス登録処理<br>
     * <br>
     * コースマス登録処理<br>
     * <br>
     * @param CourseMstModel Connection  <br>
     * @return int <br>
     * @exception NaiException
     */
    private int insertCourseMst(CourseMstModel model, Connection conn) throws NaiException {

        CourseMstLogic courseMstLogic = new CourseMstLogic(conn);
        // DTOの初期化
        CourseMstDto prmDto = new CourseMstDto();
        prmDto = this.modelToCourseMstDto(model, prmDto);
        // レッスンコメントテーブル更新
        return courseMstLogic.insertCourseMst(prmDto);
    }

    /**
     * コースマスタ更新処理<br>
     * <br>
     * コースマスタ更新処理<br>
     * <br>
     * @param CourseMstModel Connection  <br>
     * @return int <br>
     * @exception NaiException
     */
    private int updateCourseMst(CourseMstModel model, Connection conn) throws NaiException {

        CourseMstLogic courseMstLogic = new CourseMstLogic(conn);
        // DTOの初期化
        CourseMstDto prmDto = new CourseMstDto();
        prmDto = this.modelToCourseMstDto(model, prmDto);
        // レッスンコメントテーブル更新
        return courseMstLogic.updateCourseMst(prmDto);
    }

    /**
     * コース別利用ポイントの登録処理<br>
     * <br>
     * コース別利用ポイントの登録処理<br>
     * <br>
     * @param CourseUsePointMstDto Connection  <br>
     * @return int <br>
     * @exception NaiException
     */
    private int insertCourseUsePointMst(CourseUsePointMstDto dto, Connection conn) throws NaiException {

        CourseMstLogic courseMstLogic = new CourseMstLogic(conn);
        // コース別利用ポイント更新
        return courseMstLogic.insertCourseUsePointMst(dto);
    }

    /**
     * コース別利用ポイントの更新処理<br>
     * <br>
     * コース別利用ポイントの更新処理<br>
     * <br>
     * @param CourseUsePointMstDto Connection <br>
     * @return int <br>
     * @exception NaiException
     */
    private int updateCourseUsePointMst(CourseUsePointMstDto dto, Connection conn) throws NaiException {

        CourseMstLogic courseMstLogic = new CourseMstLogic(conn);
        // コース別利用ポイント更新
        return courseMstLogic.updateCourseUsePointMst(dto);
    }

    /**
     * コース別利用ポイントの削除処理<br>
     * <br>
     * コース別利用ポイントの削除処理<br>
     * <br>
     * @param CourseUsePointMstDto Connection <br>
     * @return int <br>
     * @exception NaiException
     */
    private int delCourseUsePointMst(CourseUsePointMstDto dto, Connection conn) throws NaiException {

        CourseMstLogic courseMstLogic = new CourseMstLogic(conn);
        // コース別利用ポイントの削除
        return courseMstLogic.delCourseUsePointMst(dto);
    }

    /**
     * コース別商品マスタの登録処理<br>
     * <br>
     * コース別商品マスタの登録処理<br>
     * <br>
     * @param CourseGoodsMstDto Connection <br>
     * @return int <br>
     * @exception NaiException
     */
    private int insertCourseGoodsMst(CourseGoodsMstDto dto, Connection conn) throws NaiException {

        CourseMstLogic courseMstLogic = new CourseMstLogic(conn);
        return courseMstLogic.insertCourseGoodsMst(dto);
    }

    /**
     * コース別商品マスタの更新処理<br>
     * <br>
     * コース別商品マスタの更新処理<br>
     * <br>
     * @param CourseGoodsMstDto Connection <br>
     * @return int <br>
     * @exception NaiException
     */
    private int updagteCourseGoodsMst(CourseGoodsMstDto dto, Connection conn) throws NaiException {

        CourseMstLogic courseMstLogic = new CourseMstLogic(conn);
        return courseMstLogic.updateCourseGoodsMst(dto);
    }

    /**
     * 講師別コースマスタの削除処理<br>
     * <br>
     * 講師別コースマスタの削除処理<br>
     * <br>
     * @param CourseGoodsMstDto Connection <br>
     * @return int <br>
     * @exception NaiException
     */
    public int deleteCourseGoodsMst(CourseGoodsMstDto dto, Connection conn) throws NaiException {

        CourseMstLogic courseMstLogic = new CourseMstLogic(conn);
        // レッスンコメントテーブル更新
        return courseMstLogic.deleteCourseGoodsMst(dto);
    }

    /**
     * Model値をDTOにセット。<br>
     * <br>
     * Model値をDTOにセット。<br>
     * <br>
     * @param TeacherMstModel TeacherMstDto<br>
     * @return TeacherMstDto<br>
     * @exception なし
     */
    private CourseMstDto modelToCourseMstDto(CourseMstModel model, CourseMstDto prmDto) {

        // 大分類
        prmDto.setBigClassificationCd(model.getBigClassificationCd());
        // 中分類
        prmDto.setMiddleClassificationCd(model.getMiddleClassificationCd());
        // 小分類
        prmDto.setSmallClassificationCd(model.getSmallClassificationCd());
        // コースコード
        prmDto.setCourseCd(model.getCourseCd());
        // コース名
        prmDto.setCourseJnm(model.getCourseJnm());

        // 2014/04/22 Add Start 検索条件の追加(短コース名)対応
        prmDto.setCourseJnmShort(model.getCourseJnmShort());                    // 短コース名
        // 2014/04/22 Add End   検索条件の追加(短コース名)対応

        // コース名 (英語名)
        prmDto.setCourseEnm(model.getCourseEnm());
        // 添付付き有無フラグ
        prmDto.setAttachmentFlg(model.getAttachmentFlg());
        // キーワード1
        prmDto.setKeyword1(model.getKeyword1());
        // キーワード2
        prmDto.setKeyword2(model.getKeyword2());
        // キーワード3
        prmDto.setKeyword3(model.getKeyword3());
        // キーワード4
        prmDto.setKeyword4(model.getKeyword4());
        // キーワード5
        prmDto.setKeyword5(model.getKeyword5());
        // 簡易説明
        prmDto.setSimpleExplanation(model.getSimpleExplanation());
        // 詳細説明1
        prmDto.setExplanation1(model.getExplanation1());
        // 詳細説明2
        prmDto.setExplanation2(model.getExplanation2());
        // 詳細説明3
        prmDto.setExplanation3(model.getExplanation3());
        // 詳細説明4
        prmDto.setExplanation4(model.getExplanation4());
        // 詳細説明5
        prmDto.setExplanation5(model.getExplanation5());

        // 詳細説明6(PDF)名
        prmDto.setExplanation6Nm(model.getExplanation6Nm());
        // 詳細説明6(PDF)
        prmDto.setExplanationPDF(model.getExplanation6());
        // 詳細説明６：削除
        prmDto.setFileChkn(model.getDetail6FileDel());

        // レッスン時間
        prmDto.setLessonTime(model.getLessonTime());
        // 提供開始日
        prmDto.setUseStrDt(NaikaraStringUtil.converToYYYYMMDD(model.getUseStrDt()));
        // 提供終了日
        prmDto.setUseEndDt(NaikaraStringUtil.converToYYYYMMDD(model.getUseEndDt()));
        // 該当書籍有無フラグ
        prmDto.setBookFlg(model.getBookFlg());
        // 備考
        prmDto.setRemark(model.getRemark());
        // レコードバージョン番号
        prmDto.setRecordVerNo(model.getRecordVerNo());
        return prmDto;
    }

    class ComparatorCourseUsePointMstDtos implements Comparator<CourseUsePointMstDto> {

        @Override
        public int compare(CourseUsePointMstDto o1, CourseUsePointMstDto o2) {
            // 比較開始日
            int flag = o1.getUsePointStrDt().compareTo(o2.getUsePointStrDt());
            // 開始日が同じの場合
            if (flag == 0) {
                // 比較終了日．
                return o1.getUsePointEndDt().compareTo(o2.getUsePointEndDt());
            } else {
                return flag;
            }
        }
    }
}
