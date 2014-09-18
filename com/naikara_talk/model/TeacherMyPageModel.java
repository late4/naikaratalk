package com.naikara_talk.model;

import java.sql.Blob;
import java.util.List;

import com.naikara_talk.dto.SchResTLesResPerTStuMLocSumTMDto;

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
public class TeacherMyPageModel implements Model {

    private static final long serialVersionUID = 1L;

    /** スクール側からお知らせ */
    protected String osiraseJ;
    /** スクール側からお知らせ */
    protected String osiraseE;
    /** 利用者ID */
    private String userId;
    /** ニックネーム  */
    private String nickAnm;
    /** 講師画像：ファイル名 */
    private Blob imgPhoto;
    /** 問合せメールアドレス(講師用個別マイページ用) */
    private String mailAdd;
    /** 表示用のデータ一覧 */
    private List<SchResTLesResPerTStuMLocSumTMDto> schResTLesResPerTStuMLocSumTMDto;

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
     * @return schResTLesResPerTStuMLocSumTMDto
     */
    public List<SchResTLesResPerTStuMLocSumTMDto> getSchResTLesResPerTStuMLocSumTMDto() {
        return schResTLesResPerTStuMLocSumTMDto;
    }

    /**
     * @param schResTLesResPerTStuMLocSumTMDto セットする schResTLesResPerTStuMLocSumTMDto
     */
    public void setSchResTLesResPerTStuMLocSumTMDto(
            List<SchResTLesResPerTStuMLocSumTMDto> schResTLesResPerTStuMLocSumTMDto) {
        this.schResTLesResPerTStuMLocSumTMDto = schResTLesResPerTStuMLocSumTMDto;
    }

    /**
     * @return mailAdd
     */
    public String getMailAdd() {
        return mailAdd;
    }

    /**
     * @param mailAdd セットする mailAdd
     */
    public void setMailAdd(String mailAdd) {
        this.mailAdd = mailAdd;
    }

    /**
     * @return osiraseJ
     */
    public String getOsiraseJ() {
        return osiraseJ;
    }

    /**
     * @param osiraseJ セットする osiraseJ
     */
    public void setOsiraseJ(String osiraseJ) {
        this.osiraseJ = osiraseJ;
    }

    /**
     * @return osiraseE
     */
    public String getOsiraseE() {
        return osiraseE;
    }

    /**
     * @param osiraseE セットする osiraseE
     */
    public void setOsiraseE(String osiraseE) {
        this.osiraseE = osiraseE;
    }

}