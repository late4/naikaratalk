package com.naikara_talk.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedHashMap;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dbutil.DbUtil;
import com.naikara_talk.dto.CourseUsePointMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.logic.GoodsCourseUsePointMstLogic;
import com.naikara_talk.model.GoodsCourseUsePointMstModel;
import com.naikara_talk.util.DateUtil;
import com.naikara_talk.util.NaikaraStringUtil;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>マスタ保守<br>
 * <b>クラス名称       :</b>コースマスタメンテナンス(コース利用ポイント)<br>
 * <b>クラス概要       :</b>コースマスタメンテナンス(コース利用ポイント)一覧へ反映Service<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/08/13 TECS 新規作成
 */
public class GoodsCourseUsePointMstReflectionService implements ActionService {

    /** 更新前チェック：日付の整合性チェック (日付)エラー (｢利用ポイント開始日｣　＞　｢利用ポイント終了日｣　の場合)*/
    public static final int ERR_INTEGRITY_DT = 1;

    /** 更新前チェック：日付の整合性チェック (日付)エラー */
    public static final int ERR_INTEGRITY_CONTRACT_DT = 2;

    /** エラーなし */
    public static final int OK_CHECK = 0;

    /**
     * 一覧へ反映処理のチェック。<br>
     * <br>
     * 一覧へ反映処理のチェック。<br>
     * <br>
     * @param GoodsCourseUsePointMstModel<br>
     * @return int チェック結果<br>
     * @exception なし
     */
    public int errorCheck(GoodsCourseUsePointMstModel model) {

        // 関連チェック

        // 日付の整合性チェック (日付)(｢利用ポイント開始日｣　＞　｢利用ポイント終了日｣　の場合)
        if (DateUtil.dateCompare2(NaikaraStringUtil.converToYYYYMMDD(model.getUsePointStrDt()),
                NaikaraStringUtil.converToYYYYMMDD(model.getUsePointEndDt()))) {
            return ERR_INTEGRITY_DT;
        }

        // 下記を全て満たさない場合は、エラーとする
        // 利用ポイント開始日　≧前画面の「提供開始日」 And
        // 利用ポイント終了日　≦前画面の「提供終了日」
        if (!(DateUtil.dateCompare3(NaikaraStringUtil.converToYYYYMMDD(model.getUsePointStrDt()),
                NaikaraStringUtil.converToYYYYMMDD(model.getUseStrDt())) && DateUtil.dateCompare3(
                NaikaraStringUtil.converToYYYYMMDD(model.getUseEndDt()),
                NaikaraStringUtil.converToYYYYMMDD(model.getUsePointEndDt())))) {
            return ERR_INTEGRITY_CONTRACT_DT;
        }

        return OK_CHECK;
    }

    /**
     * 一覧へ反映処理。<br>
     * <br>
     * 一覧へ反映処理。<br>
     * <br>
     * @param TeacherRateMstModel 画面のパラメータ<br>
     * @return void<br>
     * @exception NaiException
     */
    public void reflection(GoodsCourseUsePointMstModel model) throws NaiException {

        // 「修正No」が記載されていない場合
        if (StringUtils.isEmpty(model.getNumberNo())) {
            CourseUsePointMstDto dto = new CourseUsePointMstDto();
            // コースコード
            dto.setCourseCd(model.getCourseCd());
            // 利用ポイント開始日
            dto.setUsePointStrDt(NaikaraStringUtil.converToYYYYMMDD(model.getUsePointStrDt()));
            // 利用ポイント終了日
            dto.setUsePointEndDt(NaikaraStringUtil.converToYYYYMMDD(model.getUsePointEndDt()));
            // 利用ポイント
            dto.setUsePoint(model.getUsePoint());
            // お知らせ
            dto.setInformation(model.getInformation());
            model.getResultList().add(dto);
        } else {
            int index = Integer.parseInt(model.getNumberNo()) - 1;
            // 利用ポイント終了日
            model.getResultList().get(index)
                    .setUsePointEndDt(NaikaraStringUtil.converToYYYYMMDD(model.getUsePointEndDt()));
            // 利用ポイント
            model.getResultList().get(index).setUsePoint(model.getUsePoint());
            // お知らせ
            model.getResultList().get(index).setInformation(model.getInformation());
            // 適用期間：終了日
            model.getResultList().get(index)
                    .setUsePointEndDt(NaikaraStringUtil.converToYYYYMMDD(model.getUsePointEndDt()));
        }
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
            GoodsCourseUsePointMstLogic goodsCourseUsePointMstLogic = new GoodsCourseUsePointMstLogic(conn);
            // コード管理マスタ検索
            return goodsCourseUsePointMstLogic.selectCodeMst(category);
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
}
