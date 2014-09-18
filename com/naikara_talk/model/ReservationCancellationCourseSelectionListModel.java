package com.naikara_talk.model;

import java.util.ArrayList;
import java.util.List;

import com.naikara_talk.dto.SchResTLesResPerTDto;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>お客様(個人)_予約処理<br>
 * <b>クラス名称       :</b>コース名選択ページModelクラス<br>
 * <b>クラス概要       :</b>受講者が指定した講師の予約スケジュールを表示して、レッスン予約の登録／取消ができる。<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/29 TECS 新規作成
 */
public class ReservationCancellationCourseSelectionListModel implements Model {

    private static final long serialVersionUID = 1L;

    /** 講師ID */
    private String teacherId;

    /** 一覧のDTOリスト */
    private List<SchResTLesResPerTDto> resultList = new ArrayList<SchResTLesResPerTDto>();

    /**
     * @return teacherId
     */
    public String getTeacherId() {
        return teacherId;
    }

    /**
     * @param teacherId セットする teacherId
     */
    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    /**
     * @return resultList
     */
    public List<SchResTLesResPerTDto> getResultList() {
        return resultList;
    }

    /**
     * @param resultList セットする resultList
     */
    public void setResultList(List<SchResTLesResPerTDto> resultList) {
        this.resultList = resultList;
    }

}