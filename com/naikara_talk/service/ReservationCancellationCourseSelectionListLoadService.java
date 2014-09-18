package com.naikara_talk.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dto.CodeManagMstDto;
import com.naikara_talk.dto.SchResTLesResPerTDto;
import com.naikara_talk.dto.TeacherCourseDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.model.ReservationCancellationCourseSelectionListModel;
import com.naikara_talk.mstcache.CodeManagMstCache;
import com.naikara_talk.util.DateUtil;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>お客様(個人)_予約処理<br>
 * <b>クラス名称       :</b>コース名選択ページ初期化Serviceクラス<br>
 * <b>クラス概要       :</b>受講者が指定した講師の予約スケジュールを表示して、レッスン予約の登録／取消ができる。<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/29 TECS 新規作成
 */
public class ReservationCancellationCourseSelectionListLoadService implements ActionService {

    /**
     * ｢コース名｣のドロップダウンリストのデータの取得<br>
     * <br>
     * ｢コース名｣のドロップダウンリストのデータの取得を行う。<br>
     * <br>
     * @param model モデル <br>
     * @return LinkedHashMap<String, String> ハッシュマップ<br>
     * @throws NaiException
     */
    public LinkedHashMap<String, String> getCourseLinkedHashMap(ReservationCancellationCourseSelectionListModel model)
            throws NaiException {

        // 初期化
        LinkedHashMap<String, String> hashMap = new LinkedHashMap<String, String>();

        ReservationCancellationTeacherScheduleLoadService loadService = new ReservationCancellationTeacherScheduleLoadService();

        // 講師別コースマスタ(+コースマスタ)リスト取得
        List<TeacherCourseDto> retDtoList = loadService.selectTeacherCourseList(model.getTeacherId(),
                DateUtil.getSysDate(), loadService.getMaxLessonDt(model.getResultList()));

        // コード：0000、名称：空欄 行を追加する
        hashMap.put(NaikaraTalkConstants.CHOICE_ALL_ZERO, NaikaraTalkConstants.BRANK);

        // ｢コース名｣のドロップダウンリストを作成する
        for (TeacherCourseDto dto : retDtoList) {
            // コード：コースコード、名称：コース名（大分類名、中分類名、小分類名、コース名）
            hashMap.put(
                    dto.getCourseCd(),
                    NaikaraStringUtil.unionString4(dto.getBigClassificationJnm(), dto.getMiddleClassificationJnm(),
                            dto.getSmallClassificationJnm(), dto.getCourseJnm()));
        }

        // コード：9999、名称：”予約中止” 行を追加する
        hashMap.put(NaikaraTalkConstants.CHOICE_ALL_NINE, NaikaraTalkConstants.RESERV_STATE_BREAK);

        return hashMap;
    }

    /**
     * コードの名称の設定<br>
     * <br>
     * コードの名称の設定を行う。<br>
     * <br>
     * @param dtoList 講師予定予約テーブル、レッスン予実テーブルDTOリスト <br>
     * @return なし<br>
     * @throws NaiException
     */
    public void setManagerNm(List<SchResTLesResPerTDto> dtoList) throws NaiException {

        if (dtoList != null) {
            // 汎用フィールド名の取得
            CodeManagMstCache cache = CodeManagMstCache.getInstance();
            // レッスン時刻コードの名称一覧取得
            LinkedHashMap<String, CodeManagMstDto> lessonTmList = cache
                    .getList(NaikaraTalkConstants.CODE_CATEGORY_LESSON_TM_S);

            for (SchResTLesResPerTDto dto : dtoList) {
                // レッスン時刻コードの名称
                dto.setLessonTm(lessonTmList.get(dto.getLessonTmCd()).getManagerNm());

                // 隠し項目：予約No＝””の場合
                if (StringUtils.isEmpty(dto.getReservationNo())
                        || StringUtils.equals(NaikaraTalkConstants.RESERVATION_NO_NULL, dto.getReservationNo())) {
                    // コース名は空欄
                    dto.setCourseCd(NaikaraTalkConstants.CHOICE_ALL_ZERO);
                }
            }
        }
    }

    /**
     * 一覧明細の並び順<br>
     * <br>
     * 一覧明細の並び順を行う。<br>
     * <br>
     * @param dtoList 講師予定予約テーブル、レッスン予実テーブルDTOリスト <br>
     * @return なし<br>
     * @throws なし
     */
    public void sort(List<SchResTLesResPerTDto> dtoList) {

        // レッスン日の昇順、レッスン時刻の昇順
        if (dtoList != null) {

            for (int i = 0; i < dtoList.size(); i++) {
                for (int j = i + 1; j < dtoList.size(); j++) {

                    boolean flg = false;

                    // j．レッスン日 ＜ i．レッスン日の場合
                    if (!DateUtil.dateCompare3(dtoList.get(j).getLessonDt(), dtoList.get(i).getLessonDt())) {

                        flg = true;

                        // j．レッスン日 ＝ i．レッスン日の場合
                    } else if (StringUtils.equals(dtoList.get(j).getLessonDt(), dtoList.get(i).getLessonDt())) {

                        // j．レッスン時刻 ＜ i．レッスン時刻の場合
                        if (dtoList.get(j).getLessonTm().compareTo(dtoList.get(i).getLessonTm()) < 0) {

                            flg = true;
                        }
                    }

                    if (flg) {
                        SchResTLesResPerTDto tempDto = dtoList.get(j);
                        dtoList.set(j, dtoList.get(i));
                        dtoList.set(i, tempDto);
                    }
                }
            }

            // 予約DTOリスト
            List<SchResTLesResPerTDto> reserveDtoList = new ArrayList<SchResTLesResPerTDto>();

            // 取消DTOリスト
            List<SchResTLesResPerTDto> cancelDtoList = new ArrayList<SchResTLesResPerTDto>();

            // 予約、取消の順
            for (SchResTLesResPerTDto dto : dtoList) {
                // 予約
                if (StringUtils.isEmpty(dto.getReservationNo())) {
                    reserveDtoList.add(dto);
                } else {
                    // 取消
                    cancelDtoList.add(dto);
                }
            }

            dtoList.clear();
            // 予約を追加
            dtoList.addAll(reserveDtoList);
            // 取消を追加
            dtoList.addAll(cancelDtoList);
        }
    }
}
