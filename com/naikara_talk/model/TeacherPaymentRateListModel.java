package com.naikara_talk.model;

import java.sql.Blob;
import java.util.List;

import com.naikara_talk.dto.TeaCouMTeaRateMCouUsePoiMDto;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>講師初期登録ページ。<br>
 * <b>クラス名称       :</b>講師初期登録ページ初期処理Modelクラス。<br>
 * <b>クラス概要       :</b>講師初期登録ページ初期処理Model。<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/01 TECS 新規作成
 */
public class TeacherPaymentRateListModel implements Model {

    private static final long serialVersionUID = 1L;

    /** 利用者ID */
    private String userId;
    /** ニックネーム  */
    private String nickAnm;
    /** 講師画像：ファイル名 */
    private Blob imgPhoto;
    /** 表示用のデータ一覧 */
    private List<TeaCouMTeaRateMCouUsePoiMDto> teaCouMTeaRateMCouUsePoiMDto;

    /**
     * @return userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId セットする userId
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return nickAnm
     */
    public String getNickAnm() {
        return nickAnm;
    }

    /**
     * @param nickAnm セットする nickAnm
     */
    public void setNickAnm(String nickAnm) {
        this.nickAnm = nickAnm;
    }

    /**
     * @return imgPhoto
     */
    public Blob getImgPhoto() {
        return imgPhoto;
    }

    /**
     * @param imgPhoto セットする imgPhoto
     */
    public void setImgPhoto(Blob imgPhoto) {
        this.imgPhoto = imgPhoto;
    }

    /**
     * @return teaCouMTeaRateMCouUsePoiMDto
     */
    public List<TeaCouMTeaRateMCouUsePoiMDto> getTeaCouMTeaRateMCouUsePoiMDto() {
        return teaCouMTeaRateMCouUsePoiMDto;
    }

    /**
     * @param teaCouMTeaRateMCouUsePoiMDto セットする teaCouMTeaRateMCouUsePoiMDto
     */
    public void setTeaCouMTeaRateMCouUsePoiMDto(List<TeaCouMTeaRateMCouUsePoiMDto> teaCouMTeaRateMCouUsePoiMDto) {
        this.teaCouMTeaRateMCouUsePoiMDto = teaCouMTeaRateMCouUsePoiMDto;
    }

}