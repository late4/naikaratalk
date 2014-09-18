package com.naikara_talk.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>日付期間のチェック共通<br>
 * <b>クラス名称       :</b>日付期間のチェッククラス。<br>
 * <b>クラス概要       :</b>日付期間のチェック共通。<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/31 TECS 新規作成
 */
public class DateComparatorUtil {

    /** 重複する箇所がある場合 */
    public static final String ERR_OVERLAP = "Overlap";
    /** データの抜けの期間がある場合 */
    public static final String ERR_MISSING = "Missing";
    /** チェック　OK */
    public static final String CHECK_OK = "checkOk";

    /**
     * 時間間隔チェック<br>
     * <br>
     * 時間間隔チェック<br>
     * <br>
     * @param list<br>
     * @return List<String> <br>
     * @exception なし
     */
    public List<String> checkDateList(List<String[]> list) {

        // リストソート
        Collections.sort(list, new ComparatorStrings());
        // 戻すリスト
        List<String> resList = new ArrayList<String>();

        // 日付期間の比較
        for (int i = 0; i < list.size() - 1; i++) {
            String[] temp1 = (String[]) list.get(i);
            String[] temp2 = (String[]) list.get(i + 1);

            // 期間が連続していない
            if (!StringUtils.equals(DateUtil.getDateAddDay(temp1[1], 1), temp2[0])) {

                if (DateUtil.dateCompare3(temp1[1], temp2[0])) {
                    // 重複する箇所がある場合
                    resList.add(DateComparatorUtil.ERR_OVERLAP);
                    // 重複する明細の期間From
                    resList.add(NaikaraStringUtil.converToYYYY_MM_DD(temp2[0]));
                    if (DateUtil.dateCompare3(temp2[1], temp1[1])) {
                        // 重複する明細の期間To
                        resList.add(NaikaraStringUtil.converToYYYY_MM_DD(temp1[1]));
                    } else {
                        // temp1期間はtemp2期間を含めている
                        // 重複する明細の期間To
                        resList.add(NaikaraStringUtil.converToYYYY_MM_DD(temp2[1]));
                    }
                    // 重複するNo.
                    resList.add(temp1[2]);
                    // 重複するNo.
                    resList.add(temp2[2]);
                } else {
                    // データの抜けの期間がある場合
                    resList.add(DateComparatorUtil.ERR_MISSING);
                    // 抜けている期間From
                    resList.add(NaikaraStringUtil.converToYYYY_MM_DD(temp1[1]));
                    // 抜けている期間To
                    resList.add(NaikaraStringUtil.converToYYYY_MM_DD(temp2[0]));
                }
                return resList;
            }
        }
        // チェック　OK
        resList.add(CHECK_OK);
        // 開始日の最小値
        resList.add(list.get(0)[0]);
        // 終了日の最大値
        resList.add(list.get(list.size() - 1)[1]);
        return resList;
    }

    class ComparatorStrings implements Comparator<String[]> {
        @Override
        public int compare(String[] o1, String[] o2) {
            // 比較開始日
            int flag = o1[0].compareTo(o2[0]);
            // 開始日が同じの場合
            if (flag == 0) {
                // 比較No．
                return o1[2].compareTo(o2[2]);
            } else {
                return flag;
            }
        }
    }
}
