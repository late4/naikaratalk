package com.naikara_talk.action;

import java.sql.Blob;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.model.DetailDescriptionModel;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>お客様(個人)_初期登録<br>
 * <b>クラス名称       :</b>詳細説明Actionスーパークラス。<br>
 * <b>クラス概要       :</b>詳細説明の情報を表示。<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/08/09 TECS 新規作成
 */
public abstract class DetailDescriptionActionSupport extends NaikaraActionSupport {

    private static final long serialVersionUID = 1L;

    // Help画面名
    protected String helpPageId = "HelpDetailDescription.html";

    /**
     * サービスの呼び出しの実装。
     */
    abstract protected String requestService() throws NaiException;

    /** メッセージ */
    protected String message;

    /** 大分類(jsp) */
    protected String bigClassificationCdNm;

    /** 中分類(jsp) */
    protected String middleClassificationCdNm;

    /** 小分類(jsp) */
    protected String smallClassificationCdNm;

    /** コースコード(jsp) */
    protected String courseCd;

    /** コース名(jsp) */
    protected String courseJnm;

    /** 添付付き有無フラグ(jsp) */
    protected String attachmentFlg;

    /** 検索結果 */
    protected DetailDescriptionModel model = new DetailDescriptionModel();

    /** レッスン時間 (jsp) */
    protected int lessonTime;

    /** 利用開始日 (jsp) */
    protected String useStrDt;

    /** 利用終了日 (jsp) */
    protected String useEndDt;

    /** 該当商品 (jsp) */
    protected String goodsNm;

    /** 詳細説明1 (jsp) */
    protected String explanation1;

    /** 詳細説明2 (jsp) */
    protected String explanation2;

    /** 詳細説明3 (jsp) */
    protected String explanation3;

    /** 詳細説明4 */
    protected String explanation4;

    /** 詳細説明5 */
    protected String explanation5;

    /** 詳細説明6(PDF)名 */
    protected String explanation6Nm;

    /** 詳細説明6(PDF) */
    protected Blob explanation6;

    /** ファイル名 */
    protected String fileName;

    /** 件数 */
    protected int count;

    /**
     * @return helpPageId
     */
    public String getHelpPageId() {
        return helpPageId;
    }

    /**
     * @param helpPageId
     *            セットする helpPageId
     */
    public void setHelpPageId(String helpPageId) {
        this.helpPageId = helpPageId;
    }

    /**
     * @return message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message
     *            セットする message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return bigClassificationCdNm
     */
    public String getBigClassificationCdNm() {
        return bigClassificationCdNm;
    }

    /**
     * @param bigClassificationCdNm セットする bigClassificationCdNm
     */
    public void setBigClassificationCdNm(String bigClassificationCdNm) {
        this.bigClassificationCdNm = bigClassificationCdNm;
    }

    /**
     * @return middleClassificationCdNm
     */
    public String getMiddleClassificationCdNm() {
        return middleClassificationCdNm;
    }

    /**
     * @param middleClassificationCdNm セットする middleClassificationCdNm
     */
    public void setMiddleClassificationCdNm(String middleClassificationCdNm) {
        this.middleClassificationCdNm = middleClassificationCdNm;
    }

    /**
     * @return smallClassificationCdNm
     */
    public String getSmallClassificationCdNm() {
        return smallClassificationCdNm;
    }

    /**
     * @param smallClassificationCdNm セットする smallClassificationCdNm
     */
    public void setSmallClassificationCdNm(String smallClassificationCdNm) {
        this.smallClassificationCdNm = smallClassificationCdNm;
    }

    /**
     * @return courseCd
     */
    public String getCourseCd() {
        return courseCd;
    }

    /**
     * @param courseCd セットする courseCd
     */
    public void setCourseCd(String courseCd) {
        this.courseCd = courseCd;
    }

    /**
     * @return courseJnm
     */
    public String getCourseJnm() {
        return courseJnm;
    }

    /**
     * @param courseJnm セットする courseJnm
     */
    public void setCourseJnm(String courseJnm) {
        this.courseJnm = courseJnm;
    }

    /**
     * @return attachmentFlg
     */
    public String getAttachmentFlg() {
        return attachmentFlg;
    }

    /**
     * @param attachmentFlg セットする attachmentFlg
     */
    public void setAttachmentFlg(String attachmentFlg) {
        this.attachmentFlg = attachmentFlg;
    }

    /**
     * @return model
     */
    public DetailDescriptionModel getModel() {
        return model;
    }

    /**
     * @param model セットする model
     */
    public void setModel(DetailDescriptionModel model) {
        this.model = model;
    }

    /**
     * @return lessonTime
     */
    public int getLessonTime() {
        return lessonTime;
    }

    /**
     * @param lessonTime セットする lessonTime
     */
    public void setLessonTime(int lessonTime) {
        this.lessonTime = lessonTime;
    }

    /**
     * @return useStrDt
     */
    public String getUseStrDt() {
        return useStrDt;
    }

    /**
     * @param useStrDt セットする useStrDt
     */
    public void setUseStrDt(String useStrDt) {
        this.useStrDt = useStrDt;
    }

    /**
     * @return useEndDt
     */
    public String getUseEndDt() {
        return useEndDt;
    }

    /**
     * @param useEndDt セットする useEndDt
     */
    public void setUseEndDt(String useEndDt) {
        this.useEndDt = useEndDt;
    }

    /**
     * @return goodsNm
     */
    public String getGoodsNm() {
        return goodsNm;
    }

    /**
     * @param goodsNm セットする goodsNm
     */
    public void setGoodsNm(String goodsNm) {
        this.goodsNm = goodsNm;
    }

    /**
     * @return explanation1
     */
    public String getExplanation1() {
        return explanation1;
    }

    /**
     * @param explanation1 セットする explanation1
     */
    public void setExplanation1(String explanation1) {
        this.explanation1 = explanation1;
    }

    /**
     * @return explanation2
     */
    public String getExplanation2() {
        return explanation2;
    }

    /**
     * @param explanation2 セットする explanation2
     */
    public void setExplanation2(String explanation2) {
        this.explanation2 = explanation2;
    }

    /**
     * @return explanation3
     */
    public String getExplanation3() {
        return explanation3;
    }

    /**
     * @param explanation3 セットする explanation3
     */
    public void setExplanation3(String explanation3) {
        this.explanation3 = explanation3;
    }

    /**
     * @return explanation4
     */
    public String getExplanation4() {
        return explanation4;
    }

    /**
     * @param explanation4 セットする explanation4
     */
    public void setExplanation4(String explanation4) {
        this.explanation4 = explanation4;
    }

    /**
     * @return explanation5
     */
    public String getExplanation5() {
        return explanation5;
    }

    /**
     * @param explanation5 セットする explanation5
     */
    public void setExplanation5(String explanation5) {
        this.explanation5 = explanation5;
    }

    /**
     * @return explanation6Nm
     */
    public String getExplanation6Nm() {
        return explanation6Nm;
    }

    /**
     * @param explanation6Nm セットする explanation6Nm
     */
    public void setExplanation6Nm(String explanation6Nm) {
        this.explanation6Nm = explanation6Nm;
    }

    /**
     * @return count
     */
    public int getCount() {
        return count;
    }

    /**
     * @param count セットする count
     */
    public void setCount(int count) {
        this.count = count;
    }

    /**
     * @return explanation6
     */
    public Blob getExplanation6() {
        return explanation6;
    }

    /**
     * @param explanation6 セットする explanation6
     */
    public void setExplanation6(Blob explanation6) {
        this.explanation6 = explanation6;
    }

    /**
     * @return fileName
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * @param fileName セットする fileName
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

}
