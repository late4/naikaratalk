package com.naikara_talk.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dto.CourseUsePointMstDto;
import com.naikara_talk.model.GoodsCourseUsePointMstModel;
import com.naikara_talk.model.TeacherRateMstModel;
import com.naikara_talk.sessiondata.SessionCourseMstGoodsMst;
import com.naikara_talk.util.DateComparatorUtil;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>マスタ保守<br>
 * <b>クラス名称       :</b>コースマスタメンテナンス(コース利用ポイント)<br>
 * <b>クラス概要       :</b>コースマスタメンテナンス(コース利用ポイント)保存Service<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/08/13 TECS 新規作成
 */
public class GoodsCourseUsePointMstSaveService implements ActionService {

    /** チェック：日付期間チェックエラー */
    public static final String ERR_INTEGRITY_DT = "integrity";

    /** りすとサイズ　ONE */
    private static final int LIST_SIZE_ONE = 1;

    /**
     * 整合性チェック<br>
     * <br>
     * 整合性チェック<br>
     * <br>
     * @param GoodsCourseUsePointMstModel<br>
     * @return int<br>
     * @exception なし
     */
    public List<String> nextPageRequest(GoodsCourseUsePointMstModel model) {
        List<String> resList = new ArrayList<String>();
        SessionCourseMstGoodsMst sessionData = (SessionCourseMstGoodsMst) SessionDataUtil
                .getSessionData(SessionCourseMstGoodsMst.class.toString());
        List<CourseUsePointMstDto> tempResultList = sessionData.getTempCourseUsePointMstList();
        List<String[]> checkList = this.createCheckList(tempResultList);
        // 時間間隔チェック
        if (LIST_SIZE_ONE < checkList.size()) {
            DateComparatorUtil dateComparatorUtil = new DateComparatorUtil();
            resList = dateComparatorUtil.checkDateList(checkList);
            // 期間が連続してい
            if (StringUtils.equals(DateComparatorUtil.CHECK_OK, resList.get(0))) {
                // ｢提供開始日｣＝コース利用ポイントの全明細の「適用開始日」最小値 And
                // ｢提供終了日｣＝コース利用ポイントの全明細の「適用終了日」最大値 の場合
                if (StringUtils.equals(resList.get(1), NaikaraStringUtil.converToYYYYMMDD(model.getUseStrDt()))
                        && StringUtils.equals(resList.get(2), NaikaraStringUtil.converToYYYYMMDD(model.getUseEndDt()))) {
                    resList.clear();
                    resList.add(new String(GoodsCourseUsePointMstModel.CHECK_OK));
                } else {
                    resList.clear();
                    resList.add(new String(GoodsCourseUsePointMstSaveService.ERR_INTEGRITY_DT));
                }
            }
        } else if (LIST_SIZE_ONE == checkList.size()) {
            // ｢提供開始日｣＝コース利用ポイントの全明細の「適用開始日」最小値 And
            // ｢提供終了日｣＝コース利用ポイントの全明細の「適用終了日」最大値 の場合
            if (StringUtils.equals(checkList.get(0)[0], NaikaraStringUtil.converToYYYYMMDD(model.getUseStrDt()))
                    && StringUtils.equals(checkList.get(0)[1], NaikaraStringUtil.converToYYYYMMDD(model.getUseEndDt()))) {
                resList.add(new String(TeacherRateMstModel.CHECK_OK));
            } else {
                resList.add(new String(GoodsCourseUsePointMstSaveService.ERR_INTEGRITY_DT));
            }
        } else {
            // データなし
            resList.add(new String(GoodsCourseUsePointMstSaveService.ERR_INTEGRITY_DT));
        }

        return resList;
    }

    /**
     * 日付期間取得<br>
     * <br>
     * 日付期間取得<br>
     * <br>
     * @param List<CourseUsePointMstDto><br>
     * @return List<String[]><br>
     * @exception なし
     */
    private List<String[]> createCheckList(List<CourseUsePointMstDto> list) {
        List<String[]> checkList = new ArrayList<String[]>();
        for (int i = 0; i < list.size(); i++) {
            CourseUsePointMstDto dto = list.get(i);
            String[] tempStrings = new String[] { NaikaraStringUtil.converToYYYYMMDD(dto.getUsePointStrDt()),
                    NaikaraStringUtil.converToYYYYMMDD(dto.getUsePointEndDt()), String.valueOf(i + 1) };
            checkList.add(tempStrings);
        }
        return checkList;
    }
}
