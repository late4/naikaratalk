package com.naikara_talk.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dto.TeacherRateMstDto;
import com.naikara_talk.model.TeacherRateMstModel;
import com.naikara_talk.sessiondata.SessionUserMstTeacherMst;
import com.naikara_talk.util.DateComparatorUtil;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>マスタ保守<br>
 * <b>クラス名称       :</b>講師支払比率の設定<br>
 * <b>クラス概要       :</b>講師支払比率の設定保存Service<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/30 TECS 新規作成
 */
public class TeacherRateMstSaveService implements ActionService {

    /** チェック：日付期間チェックエラー */
    public static final String ERR_INTEGRITY_DT = "integrity";

    /** りすとサイズ　ONE */
    private static final int LIST_SIZE_ONE = 1;

    /**
     * 整合性チェック<br>
     * <br>
     * 整合性チェック<br>
     * <br>
     * @param TeacherRateMstModel<br>
     * @return int<br>
     * @exception なし
     */
    public List<String> nextPageRequest(TeacherRateMstModel model) {
        List<String> resList = new ArrayList<String>();
        SessionUserMstTeacherMst sessionData = (SessionUserMstTeacherMst) SessionDataUtil
                .getSessionData(SessionUserMstTeacherMst.class.toString());
        List<TeacherRateMstDto> tempResultList = sessionData.getTempResultList();
        List<String[]> checkList = this.createCheckList(tempResultList);
        // 時間間隔チェック
        if (LIST_SIZE_ONE < checkList.size()) {
            DateComparatorUtil dateComparatorUtil = new DateComparatorUtil();
            resList = dateComparatorUtil.checkDateList(checkList);
            // 期間が連続してい
            if (StringUtils.equals(DateComparatorUtil.CHECK_OK, resList.get(0))) {
                // ｢契約期間：開始日｣＝講師支払比率の全明細の「適用開始日」最小値 And
                // ｢契約期間：終了日｣＝講師支払比率の全明細の「適用終了日」最大値 の場合
                if (StringUtils.equals(resList.get(1), NaikaraStringUtil.converToYYYYMMDD(model.getContractStartDt()))
                        && StringUtils.equals(resList.get(2),
                                NaikaraStringUtil.converToYYYYMMDD(model.getContractEndDt()))) {
                    resList.clear();
                    resList.add(new String(TeacherRateMstModel.CHECK_OK));
                } else {
                    resList.clear();
                    resList.add(new String(TeacherRateMstSaveService.ERR_INTEGRITY_DT));
                }
            }
        } else if (LIST_SIZE_ONE == checkList.size()) {
            // ｢契約期間：開始日｣＝講師支払比率の全明細の「適用開始日」最小値 And
            // ｢契約期間：終了日｣＝講師支払比率の全明細の「適用終了日」最大値 の場合
            if (StringUtils.equals(checkList.get(0)[0], NaikaraStringUtil.converToYYYYMMDD(model.getContractStartDt()))
                    && StringUtils.equals(checkList.get(0)[1],
                            NaikaraStringUtil.converToYYYYMMDD(model.getContractEndDt()))) {
                resList.add(new String(TeacherRateMstModel.CHECK_OK));
            } else {
                resList.add(new String(TeacherRateMstSaveService.ERR_INTEGRITY_DT));
            }
        } else {
            // データなし
            resList.add(new String(TeacherRateMstSaveService.ERR_INTEGRITY_DT));
        }

        return resList;
    }

    /**
     * 日付期間取得<br>
     * <br>
     * 日付期間取得<br>
     * <br>
     * @param List<TeacherRateMstDto><br>
     * @return List<String[]><br>
     * @exception なし
     */
    private List<String[]> createCheckList(List<TeacherRateMstDto> list) {
        List<String[]> checkList = new ArrayList<String[]>();
        for (int i = 0; i < list.size(); i++) {
            TeacherRateMstDto dto = list.get(i);
            String[] tempStrings = new String[] { NaikaraStringUtil.converToYYYYMMDD(dto.getStartDt()),
                    NaikaraStringUtil.converToYYYYMMDD(dto.getEndDt()), String.valueOf(i + 1) };
            checkList.add(tempStrings);
        }
        return checkList;
    }
}
