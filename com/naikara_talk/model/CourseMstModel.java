package com.naikara_talk.model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.naikara_talk.dto.CourseGoodsListDto;
import com.naikara_talk.dto.CourseUsePointMstDto;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>マスタ保守<br>
 * <b>クラス名称       :</b>コースマスタメンテナンス(単票)<br>
 * <b>クラス概要       :</b>コースマスタメンテナンス(単票)Model<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/08/09 TECS 新規作成
 * <b>                 :</b>2014/04/23 TECS 項目の追加(短コース名)対応
 */
public class CourseMstModel implements Model {

    private static final long serialVersionUID = 1L;

    /** 正常 */
    public static final int PROCESS_NORMAL = 0;

    /** コースコード */
    private String courseCd;

    /** 大分類 */
    private String bigClassificationCd;

    /** 中分類 */
    private String middleClassificationCd;

    /** 小分類 */
    private String smallClassificationCd;

    /** コース名  */
    private String courseJnm;

    // 2014/04/22 Add Start 検索条件の追加(短コース名)対応
    /** 短コース名  */
    private String courseJnmShort;
    // 2014/04/22 Add End   検索条件の追加(短コース名)対応

    /** コース名 (英語名) */
    private String courseEnm;

    /** 添付付き有無フラグ  */
    private String attachmentFlg;

    /** キーワード1 */
    private String keyword1;

    /** キーワード2 */
    private String keyword2;

    /** キーワード3 */
    private String keyword3;

    /** キーワード4 */
    private String keyword4;

    /** キーワード5 */
    private String keyword5;

    /** 簡易説明 */
    private String simpleExplanation;

    /** 詳細説明1 */
    private String explanation1;

    /** 詳細説明2 */
    private String explanation2;

    /** 詳細説明3 */
    private String explanation3;

    /** 詳細説明4 */
    private String explanation4;

    /** 詳細説明5 */
    private String explanation5;

    /** 詳細説明６：ファイル名 */
    private String oldExplanation6Nm;

    /** 詳細説明6(PDF)名 */
    private String explanation6Nm;

    /** 詳細説明6(PDF) */
    private File explanation6;

    /** 詳細説明６：削除 */
    private String detail6FileDel;

    /** レッスン時間 */
    private int lessonTime;

    /** 提供開始日 */
    private String useStrDt;

    /** 提供終了日 */
    private String useEndDt;

    /** 更新前提供開始日 */
    private String oldUseStrDt;

    /** 該当書籍有無フラグ */
    private String bookFlg;

    /** コース別商品 */
    private List<CourseGoodsListDto> courseGoodsListDtoList;

    /** 備考 */
    private String remark;

    /** レコードバージョン番号 */
    private int recordVerNo;

    /** 処理区分(前画面情報) */
    private String processKbn_rdl;

    /** 画面表示処理区分 */
    private String processKbn_txt;

    /** コース別利用ポイント */
    private List<CourseUsePointMstDto> courseUsePointMstDtoList;

    /** 削除のコース別利用ポイント */
    private List<CourseUsePointMstDto> delCourseUsePointMstDtoList;

    /** チェック メッセージ */
    private String checkMessage;

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
     * @return bigClassificationCd
     */
    public String getBigClassificationCd() {
        return bigClassificationCd;
    }

    /**
     * @param bigClassificationCd セットする bigClassificationCd
     */
    public void setBigClassificationCd(String bigClassificationCd) {
        this.bigClassificationCd = bigClassificationCd;
    }

    /**
     * @return middleClassificationCd
     */
    public String getMiddleClassificationCd() {
        return middleClassificationCd;
    }

    /**
     * @param middleClassificationCd セットする middleClassificationCd
     */
    public void setMiddleClassificationCd(String middleClassificationCd) {
        this.middleClassificationCd = middleClassificationCd;
    }

    /**
     * @return smallClassificationCd
     */
    public String getSmallClassificationCd() {
        return smallClassificationCd;
    }

    /**
     * @param smallClassificationCd セットする smallClassificationCd
     */
    public void setSmallClassificationCd(String smallClassificationCd) {
        this.smallClassificationCd = smallClassificationCd;
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

    // 2014/04/22 Add Start 項目の追加(短コース名)対応
    /**
     * @return courseJnmShort
     */
    public String getCourseJnmShort() {
        return courseJnmShort;
    }

    /**
     * @param courseJnmShort セットする courseJnmShort
     */
    public void setCourseJnmShort(String courseJnmShort) {
        this.courseJnmShort = courseJnmShort;
    }
    // 2014/04/22 Add End   項目の追加(短コース名)対応

    /**
     * @return courseEnm
     */
    public String getCourseEnm() {
        return courseEnm;
    }

    /**
     * @param courseEnm セットする courseEnm
     */
    public void setCourseEnm(String courseEnm) {
        this.courseEnm = courseEnm;
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
     * @return keyword1
     */
    public String getKeyword1() {
        return keyword1;
    }

    /**
     * @param keyword1 セットする keyword1
     */
    public void setKeyword1(String keyword1) {
        this.keyword1 = keyword1;
    }

    /**
     * @return keyword2
     */
    public String getKeyword2() {
        return keyword2;
    }

    /**
     * @param keyword2 セットする keyword2
     */
    public void setKeyword2(String keyword2) {
        this.keyword2 = keyword2;
    }

    /**
     * @return keyword3
     */
    public String getKeyword3() {
        return keyword3;
    }

    /**
     * @param keyword3 セットする keyword3
     */
    public void setKeyword3(String keyword3) {
        this.keyword3 = keyword3;
    }

    /**
     * @return keyword4
     */
    public String getKeyword4() {
        return keyword4;
    }

    /**
     * @param keyword4 セットする keyword4
     */
    public void setKeyword4(String keyword4) {
        this.keyword4 = keyword4;
    }

    /**
     * @return keyword5
     */
    public String getKeyword5() {
        return keyword5;
    }

    /**
     * @param keyword5 セットする keyword5
     */
    public void setKeyword5(String keyword5) {
        this.keyword5 = keyword5;
    }

    /**
     * @return simpleExplanation
     */
    public String getSimpleExplanation() {
        return simpleExplanation;
    }

    /**
     * @param simpleExplanation セットする simpleExplanation
     */
    public void setSimpleExplanation(String simpleExplanation) {
        this.simpleExplanation = simpleExplanation;
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
     * @return oldExplanation6Nm
     */
    public String getOldExplanation6Nm() {
        return oldExplanation6Nm;
    }

    /**
     * @param oldExplanation6Nm セットする oldExplanation6Nm
     */
    public void setOldExplanation6Nm(String oldExplanation6Nm) {
        this.oldExplanation6Nm = oldExplanation6Nm;
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
     * @return explanation6
     */
    public File getExplanation6() {
        return explanation6;
    }

    /**
     * @param explanation6 セットする explanation6
     */
    public void setExplanation6(File explanation6) {
        this.explanation6 = explanation6;
    }

    /**
     * @return detail6FileDel
     */
    public String getDetail6FileDel() {
        return detail6FileDel;
    }

    /**
     * @param detail6FileDel セットする detail6FileDel
     */
    public void setDetail6FileDel(String detail6FileDel) {
        this.detail6FileDel = detail6FileDel;
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
     * @return oldUseStrDt
     */
    public String getOldUseStrDt() {
        return oldUseStrDt;
    }

    /**
     * @param oldUseStrDt セットする oldUseStrDt
     */
    public void setOldUseStrDt(String oldUseStrDt) {
        this.oldUseStrDt = oldUseStrDt;
    }

    /**
     * @return bookFlg
     */
    public String getBookFlg() {
        return bookFlg;
    }

    /**
     * @param bookFlg セットする bookFlg
     */
    public void setBookFlg(String bookFlg) {
        this.bookFlg = bookFlg;
    }

    /**
     * @return courseGoodsListDtoList
     */
    public List<CourseGoodsListDto> getCourseGoodsListDtoList() {
        if (this.courseGoodsListDtoList == null) {
            this.courseGoodsListDtoList = new ArrayList<CourseGoodsListDto>();
        }
        return courseGoodsListDtoList;
    }

    /**
     * @param courseGoodsListDtoList セットする courseGoodsListDtoList
     */
    public void setCourseGoodsListDtoList(List<CourseGoodsListDto> courseGoodsListDtoList) {
        this.courseGoodsListDtoList = courseGoodsListDtoList;
    }

    /**
     * @return remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark セットする remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * @return recordVerNo
     */
    public int getRecordVerNo() {
        return recordVerNo;
    }

    /**
     * @param recordVerNo セットする recordVerNo
     */
    public void setRecordVerNo(int recordVerNo) {
        this.recordVerNo = recordVerNo;
    }

    /**
     * @return processKbn_rdl
     */
    public String getProcessKbn_rdl() {
        return processKbn_rdl;
    }

    /**
     * @param processKbn_rdl セットする processKbn_rdl
     */
    public void setProcessKbn_rdl(String processKbn_rdl) {
        this.processKbn_rdl = processKbn_rdl;
    }

    /**
     * @return processKbn_txt
     */
    public String getProcessKbn_txt() {
        return processKbn_txt;
    }

    /**
     * @param processKbn_txt セットする processKbn_txt
     */
    public void setProcessKbn_txt(String processKbn_txt) {
        this.processKbn_txt = processKbn_txt;
    }

    /**
     * @return courseUsePointMstDtoList
     */
    public List<CourseUsePointMstDto> getCourseUsePointMstDtoList() {
        if (this.courseUsePointMstDtoList == null) {
            this.courseUsePointMstDtoList = new ArrayList<CourseUsePointMstDto>();
        }
        return courseUsePointMstDtoList;
    }

    /**
     * @param courseUsePointMstDtoList セットする courseUsePointMstDtoList
     */
    public void setCourseUsePointMstDtoList(List<CourseUsePointMstDto> courseUsePointMstDtoList) {
        this.courseUsePointMstDtoList = courseUsePointMstDtoList;
    }

    /**
     * @return delCourseUsePointMstDtoList
     */
    public List<CourseUsePointMstDto> getDelCourseUsePointMstDtoList() {
        if (this.delCourseUsePointMstDtoList == null) {
            this.delCourseUsePointMstDtoList = new ArrayList<CourseUsePointMstDto>();
        }
        return delCourseUsePointMstDtoList;
    }

    /**
     * @param delCourseUsePointMstDtoList セットする delCourseUsePointMstDtoList
     */
    public void setDelCourseUsePointMstDtoList(List<CourseUsePointMstDto> delCourseUsePointMstDtoList) {
        this.delCourseUsePointMstDtoList = delCourseUsePointMstDtoList;
    }

    /**
     * @return checkMessage
     */
    public String getCheckMessage() {
        return checkMessage;
    }

    /**
     * @param checkMessage セットする checkMessage
     */
    public void setCheckMessage(String checkMessage) {
        this.checkMessage = checkMessage;
    }

}