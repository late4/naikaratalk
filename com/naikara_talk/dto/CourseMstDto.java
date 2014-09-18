package com.naikara_talk.dto;

import java.io.File;
import java.sql.Blob;
import java.sql.Timestamp;

import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称:</b>共通部品 DTOクラス<br>
 * <b>クラス名称　　　:</b>コースマスタクラス<br>
 * <b>クラス概要　　　:</b>コースマスタDTO<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/05/30 TECS 新規作成
 *  　　　　　　　　　:</b>2014/04/22 TECS 項目の追加(短コース名)対応
 */
public class CourseMstDto extends AbstractDto {

    private String courseCd;                    // コースコード
    private String bigClassificationCd;         // 大分類コード
    private String middleClassificationCd;      // 中分類コード
    private String smallClassificationCd;       // 小分類コード
    private String courseJnm;                   // コース名

    // 2014/04/22 Add Start 検索条件の追加(短コース名)対応
    private String courseJnmShort;              // 短コース名
    // 2014/04/22 Add End   検索条件の追加(短コース名)対応

    private String courseEnm;                   // コース名(英語名)
    private String attachmentFlg;               // 添付付き有無フラグ
    private String keyword1;                    // キーワード１
    private String keyword2;                    // キーワード２
    private String keyword3;                    // キーワード３
    private String keyword4;                    // キーワード４
    private String keyword5;                    // キーワード５
    private String simpleExplanation;           // 簡易説明
    private String explanation1;                // 詳細説明1
    private String explanation2;                // 詳細説明2
    private String explanation3;                // 詳細説明3
    private String explanation4;                // 詳細説明4
    private String explanation5;                // 詳細説明5
    private String explanation6Nm;              // 詳細説明6(PDF)名
    private Blob explanation6;                  // 詳細説明6(PDF)
    private int lessonTime;                     // レッスン時間
    private String useStrDt;                    // 提供開始日
    private String useEndDt;                    // 提供終了日
    private String bookFlg;                     // 該当書籍有無フラグ
    private String remark;                      // 備考
    private int recordVerNo;                    // レコードバージョン番号
    private Timestamp insertDttm;               // 登録日時
    private String insertCd;                    // 登録者コード
    private Timestamp updateDttm;               // 更新日時
    private String updateCd;                    // 更新者コード
    private int returnCode;                     // リターンコード

    // 画面処理必要項目
    private File explanationPDF;                // PDF
    private String fileChkn;                    // 詳細説明６：削除
    private String bigClassificationCdNm;       // 大分類名
    private String middleClassificationCdNm;    // 中分類名
    private String smallClassificationCdNm;     // 小分類名
    private String attachmentFlgNm;             // 添付付き有無フラグ名

    /**
     * コースコード取得<br>
     * <br>
     * コースコードを戻り値で返却する<br>
     * <br>
     * @return courseCd
     */
    public String getCourseCd() {
        return courseCd;
    }

    /**
     * コースコード設定<br>
     * <br>
     * コースコードを引数で設定する<br>
     * <br>
     * @param courseCd
     */
    public void setCourseCd(String courseCd) {
        this.courseCd = courseCd;
    }

    /**
     * 大分類コード取得<br>
     * <br>
     * 大分類コードを戻り値で返却する<br>
     * <br>
     * @return bigClassificationCd
     */
    public String getBigClassificationCd() {
        return bigClassificationCd;
    }

    /**
     * 大分類コード設定<br>
     * <br>
     * 大分類コードを引数で設定する<br>
     * <br>
     * @param bigClassificationCd
     */
    public void setBigClassificationCd(String bigClassificationCd) {
        this.bigClassificationCd = bigClassificationCd;
    }

    /**
     * 中分類コード取得<br>
     * <br>
     * 中分類コードを戻り値で返却する<br>
     * <br>
     * @return middleClassificationCd
     */
    public String getMiddleClassificationCd() {
        return middleClassificationCd;
    }

    /**
     * 中分類コード設定<br>
     * <br>
     * 中分類コードを引数で設定する<br>
     * <br>
     * @param middleClassificationCd
     */
    public void setMiddleClassificationCd(String middleClassificationCd) {
        this.middleClassificationCd = middleClassificationCd;
    }

    /**
     * 小分類コード取得<br>
     * <br>
     * 小分類コードを戻り値で返却する<br>
     * <br>
     * @return smallClassificationCd
     */
    public String getSmallClassificationCd() {
        return smallClassificationCd;
    }

    /**
     * 小分類コード設定<br>
     * <br>
     * 小分類コードを引数で設定する<br>
     * <br>
     * @param smallClassificationCd
     */
    public void setSmallClassificationCd(String smallClassificationCd) {
        this.smallClassificationCd = smallClassificationCd;
    }

    /**
     * コース名取得<br>
     * <br>
     * コース名を戻り値で返却する<br>
     * <br>
     * @return courseJnm
     */
    public String getCourseJnm() {
        return courseJnm;
    }

    /**
     * コース名設定<br>
     * <br>
     * コース名を引数で設定する<br>
     * <br>
     * @param courseJnm
     */
    public void setCourseJnm(String courseJnm) {
        this.courseJnm = courseJnm;
    }

    // 2014/04/22 Add Start 検索条件の追加(短コース名)対応
    /**
     * コース名取得<br>
     * <br>
     * コース名を戻り値で返却する<br>
     * <br>
     * @return courseJnmShort
     */
    public String getCourseJnmShort() {
        return courseJnmShort;
    }

    /**
     * コース名設定<br>
     * <br>
     * コース名を引数で設定する<br>
     * <br>
     * @param courseJnmShort
     */
    public void setCourseJnmShort(String courseJnmShort) {
        this.courseJnmShort = courseJnmShort;
    }
    // 2014/04/22 Add End   検索条件の追加(短コース名)対応

    /**
     * コース名(英語名)取得<br>
     * <br>
     * コース名(英語名)を戻り値で返却する<br>
     * <br>
     * @return courseEnm
     */
    public String getCourseEnm() {
        return courseEnm;
    }

    /**
     * コース名(英語名)設定<br>
     * <br>
     * コース名(英語名)を引数で設定する<br>
     * <br>
     * @param courseEnm
     */
    public void setCourseEnm(String courseEnm) {
        this.courseEnm = courseEnm;
    }

    /**
     * 添付付き有無フラグ取得<br>
     * <br>
     * 添付付き有無フラグを戻り値で返却する<br>
     * <br>
     * @return attachmentFlg
     */
    public String getAttachmentFlg() {
        return attachmentFlg;
    }

    /**
     * 添付付き有無フラグ設定<br>
     * <br>
     * 添付付き有無フラグを引数で設定する<br>
     * <br>
     * @param attachmentFlg
     */
    public void setAttachmentFlg(String attachmentFlg) {
        this.attachmentFlg = attachmentFlg;
    }

    /**
     * キーワード１取得<br>
     * <br>
     * キーワード１を戻り値で返却する<br>
     * <br>
     * @return keyword1
     */
    public String getKeyword1() {
        return keyword1;
    }

    /**
     * キーワード１設定<br>
     * <br>
     * キーワード１を引数で設定する<br>
     * <br>
     * @param keyword1
     */
    public void setKeyword1(String keyword1) {
        this.keyword1 = keyword1;
    }

    /**
     * キーワード２取得<br>
     * <br>
     * キーワード２を戻り値で返却する<br>
     * <br>
     * @return keyword2
     */
    public String getKeyword2() {
        return keyword2;
    }

    /**
     * キーワード２設定<br>
     * <br>
     * キーワード２を引数で設定する<br>
     * <br>
     * @param keyword2
     */
    public void setKeyword2(String keyword2) {
        this.keyword2 = keyword2;
    }

    /**
     * キーワード３取得<br>
     * <br>
     * キーワード３を戻り値で返却する<br>
     * <br>
     * @return keyword3
     */
    public String getKeyword3() {
        return keyword3;
    }

    /**
     * キーワード３設定<br>
     * <br>
     * キーワード３を引数で設定する<br>
     * <br>
     * @param keyword3
     */
    public void setKeyword3(String keyword3) {
        this.keyword3 = keyword3;
    }

    /**
     * キーワード４取得<br>
     * <br>
     * キーワード４を戻り値で返却する<br>
     * <br>
     * @return keyword4
     */
    public String getKeyword4() {
        return keyword4;
    }

    /**
     * キーワード４設定<br>
     * <br>
     * キーワード４を引数で設定する<br>
     * <br>
     * @param keyword4
     */
    public void setKeyword4(String keyword4) {
        this.keyword4 = keyword4;
    }

    /**
     * キーワード５取得<br>
     * <br>
     * キーワード５を戻り値で返却する<br>
     * <br>
     * @return keyword5
     */
    public String getKeyword5() {
        return keyword5;
    }

    /**
     * キーワード５設定<br>
     * <br>
     * キーワード５を引数で設定する<br>
     * <br>
     * @param keyword5
     */
    public void setKeyword5(String keyword5) {
        this.keyword5 = keyword5;
    }

    /**
     * 簡易説明取得<br>
     * <br>
     * 簡易説明を戻り値で返却する<br>
     * <br>
     * @return simpleExplanation
     */
    public String getSimpleExplanation() {
        return simpleExplanation;
    }

    /**
     * 簡易説明設定<br>
     * <br>
     * 簡易説明を引数で設定する<br>
     * <br>
     * @param simpleExplanation
     */
    public void setSimpleExplanation(String simpleExplanation) {
        this.simpleExplanation = simpleExplanation;
    }

    /**
     * 詳細説明1取得<br>
     * <br>
     * 詳細説明1を戻り値で返却する<br>
     * <br>
     * @return explanation1
     */
    public String getExplanation1() {
        return explanation1;
    }

    /**
     * 詳細説明1設定<br>
     * <br>
     * 詳細説明1を引数で設定する<br>
     * <br>
     * @param explanation1
     */
    public void setExplanation1(String explanation1) {
        this.explanation1 = explanation1;
    }

    /**
     * 詳細説明2取得<br>
     * <br>
     * 詳細説明2を戻り値で返却する<br>
     * <br>
     * @return explanation2
     */
    public String getExplanation2() {
        return explanation2;
    }

    /**
     * 詳細説明2設定<br>
     * <br>
     * 詳細説明2を引数で設定する<br>
     * <br>
     * @param explanation2
     */
    public void setExplanation2(String explanation2) {
        this.explanation2 = explanation2;
    }

    /**
     * 詳細説明3取得<br>
     * <br>
     * 詳細説明3を戻り値で返却する<br>
     * <br>
     * @return explanation3
     */
    public String getExplanation3() {
        return explanation3;
    }

    /**
     * 詳細説明3設定<br>
     * <br>
     * 詳細説明3を引数で設定する<br>
     * <br>
     * @param explanation3
     */
    public void setExplanation3(String explanation3) {
        this.explanation3 = explanation3;
    }

    /**
     * 詳細説明4取得<br>
     * <br>
     * 詳細説明4を戻り値で返却する<br>
     * <br>
     * @return explanation4
     */
    public String getExplanation4() {
        return explanation4;
    }

    /**
     * 詳細説明4設定<br>
     * <br>
     * 詳細説明4を引数で設定する<br>
     * <br>
     * @param explanation4
     */
    public void setExplanation4(String explanation4) {
        this.explanation4 = explanation4;
    }

    /**
     * 詳細説明5取得<br>
     * <br>
     * 詳細説明5を戻り値で返却する<br>
     * <br>
     * @return explanation5
     */
    public String getExplanation5() {
        return explanation5;
    }

    /**
     * 詳細説明5設定<br>
     * <br>
     * 詳細説明5を引数で設定する<br>
     * <br>
     * @param explanation5
     */
    public void setExplanation5(String explanation5) {
        this.explanation5 = explanation5;
    }

    /**
     * 詳細説明6(PDF)名取得<br>
     * <br>
     * 詳細説明6(PDF)名を戻り値で返却する<br>
     * <br>
     * @return explanation6Nm
     */
    public String getExplanation6Nm() {
        return explanation6Nm;
    }

    /**
     * 詳細説明6(PDF)名設定<br>
     * <br>
     * 詳細説明6(PDF)名を引数で設定する<br>
     * <br>
     * @param explanation6Nm
     */
    public void setExplanation6Nm(String explanation6Nm) {
        this.explanation6Nm = explanation6Nm;
    }

    /**
     * 詳細説明6(PDF)取得<br>
     * <br>
     * 詳細説明6(PDF)を戻り値で返却する<br>
     * <br>
     * @return explanation6
     */
    public Blob getExplanation6() {
        return explanation6;
    }

    /**
     * 詳細説明6(PDF)設定<br>
     * <br>
     * 詳細説明6(PDF)を引数で設定する<br>
     * <br>
     * @param explanation6
     */
    public void setExplanation6(Blob explanation6) {
        this.explanation6 = explanation6;
    }

    /**
     * レッスン時間取得<br>
     * <br>
     * レッスン時間を戻り値で返却する<br>
     * <br>
     * @return lessonTime
     */
    public int getLessonTime() {
        return lessonTime;
    }

    /**
     * レッスン時間設定<br>
     * <br>
     * レッスン時間を引数で設定する<br>
     * <br>
     * @param lessonTime
     */
    public void setLessonTime(int lessonTime) {
        this.lessonTime = lessonTime;
    }

    /**
     * 提供開始日取得<br>
     * <br>
     * 提供開始日を戻り値で返却する<br>
     * <br>
     * @return useStrDt
     */
    public String getUseStrDt() {
        return useStrDt;
    }

    /**
     * 提供開始日設定<br>
     * <br>
     * 提供開始日を引数で設定する<br>
     * <br>
     * @param useStrDt
     */
    public void setUseStrDt(String useStrDt) {
        this.useStrDt = useStrDt;
    }

    /**
     * 提供終了日取得<br>
     * <br>
     * 提供終了日を戻り値で返却する<br>
     * <br>
     * @return useEndDt
     */
    public String getUseEndDt() {
        return useEndDt;
    }

    /**
     * 提供終了日設定<br>
     * <br>
     * 提供終了日を引数で設定する<br>
     * <br>
     * @param useEndDt
     */
    public void setUseEndDt(String useEndDt) {
        this.useEndDt = useEndDt;
    }

    /**
     * 該当書籍有無フラグ取得<br>
     * <br>
     * 該当書籍有無フラグを戻り値で返却する<br>
     * <br>
     * @return bookFlg
     */
    public String getBookFlg() {
        return bookFlg;
    }

    /**
     * 該当書籍有無フラグ設定<br>
     * <br>
     * 該当書籍有無フラグを引数で設定する<br>
     * <br>
     * @param bookFlg
     */
    public void setBookFlg(String bookFlg) {
        this.bookFlg = bookFlg;
    }

    /**
     * 備考取得<br>
     * <br>
     * 備考を戻り値で返却する<br>
     * <br>
     * @return remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 備考設定<br>
     * <br>
     * 備考を引数で設定する<br>
     * <br>
     * @param remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * レコードバージョン番号取得<br>
     * <br>
     * レコードバージョン番号を戻り値で返却する<br>
     * <br>
     * @return recordVerNo
     */
    public int getRecordVerNo() {
        return recordVerNo;
    }

    /**
     * レコードバージョン番号設定<br>
     * <br>
     * レコードバージョン番号を引数で設定する<br>
     * <br>
     * @param recordVerNo
     */
    public void setRecordVerNo(int recordVerNo) {
        this.recordVerNo = recordVerNo;
    }

    /**
     * 登録日時取得<br>
     * <br>
     * 登録日時を戻り値で返却する<br>
     * <br>
     * @return insertDttm
     */
    public Timestamp getInsertDttm() {
        return insertDttm;
    }

    /**
     * 登録日時設定<br>
     * <br>
     * 登録日時を引数で設定する<br>
     * <br>
     * @param insertDttm
     */
    public void setInsertDttm(Timestamp insertDttm) {
        this.insertDttm = insertDttm;
    }

    /**
     * 登録者コード取得<br>
     * <br>
     * 登録者コードを戻り値で返却する<br>
     * <br>
     * @return insertCd
     */
    public String getInsertCd() {
        return insertCd;
    }

    /**
     * 登録者コード設定<br>
     * <br>
     * 登録者コードを引数で設定する<br>
     * <br>
     * @param insertCd
     */
    public void setInsertCd(String insertCd) {
        this.insertCd = insertCd;
    }

    /**
     * 更新日時取得<br>
     * <br>
     * 更新日時を戻り値で返却する<br>
     * <br>
     * @return updateDttm
     */
    public Timestamp getUpdateDttm() {
        return updateDttm;
    }

    /**
     * 更新日時設定<br>
     * <br>
     * 更新日時を引数で設定する<br>
     * <br>
     * @param updateDttm
     */
    public void setUpdateDttm(Timestamp updateDttm) {
        this.updateDttm = updateDttm;
    }

    /**
     * 更新者コード取得<br>
     * <br>
     * 更新者コードを戻り値で返却する<br>
     * <br>
     * @return updateCd
     */
    public String getUpdateCd() {
        return updateCd;
    }

    /**
     * 更新者コード設定<br>
     * <br>
     * 更新者コードを引数で設定する<br>
     * <br>
     * @param updateCd
     */
    public void setUpdateCd(String updateCd) {
        this.updateCd = updateCd;
    }

    /**
     * リターンコード取得<br>
     * <br>
     * リターンコードを戻り値で返却する<br>
     * <br>
     * @return returnCode
     */
    public int getReturnCode() {
        return returnCode;
    }

    /**
     * リターンコード設定<br>
     * <br>
     * リターンコードを引数で設定する<br>
     * <br>
     * @param returnCode
     */
    public void setReturnCode(int returnCode) {
        this.returnCode = returnCode;
    }

    /**
     * @return explanationPDF
     */
    public File getExplanationPDF() {
        return explanationPDF;
    }

    /**
     * @param explanationPDF セットする explanationPDF
     */
    public void setExplanationPDF(File explanationPDF) {
        this.explanationPDF = explanationPDF;
    }

    /**
     * @return fileChkn
     */
    public String getFileChkn() {
        if (this.fileChkn == null) {
            this.fileChkn = NaikaraTalkConstants.FALSE;
        }
        return fileChkn;
    }

    /**
     * @param fileChkn セットする fileChkn
     */
    public void setFileChkn(String fileChkn) {
        this.fileChkn = fileChkn;
    }

    /**
     * 大分類名取得<br>
     * <br>
     * 大分類名を戻り値で返却する<br>
     * <br>
     * @return bigClassificationCdNm
     */
    public String getBigClassificationCdNm() {
        return bigClassificationCdNm;
    }

    /**
     * 大分類名設定<br>
     * <br>
     * 大分類名を引数で設定する<br>
     * <br>
     * @param bigClassificationCdNm
     */
    public void setBigClassificationCdNm(String bigClassificationCdNm) {
        this.bigClassificationCdNm = bigClassificationCdNm;
    }

    /**
     * 中分類名取得<br>
     * <br>
     * 中分類名を戻り値で返却する<br>
     * <br>
     * @return middleClassificationCdNm
     */
    public String getMiddleClassificationCdNm() {
        return middleClassificationCdNm;
    }

    /**
     * 中分類名設定<br>
     * <br>
     * 中分類名を引数で設定する<br>
     * <br>
     * @param middleClassificationCdNm
     */
    public void setMiddleClassificationCdNm(String middleClassificationCdNm) {
        this.middleClassificationCdNm = middleClassificationCdNm;
    }

    /**
     * 小分類名取得<br>
     * <br>
     * 小分類名を戻り値で返却する<br>
     * <br>
     * @return smallClassificationCdNm
     */
    public String getSmallClassificationCdNm() {
        return smallClassificationCdNm;
    }

    /**
     * 小分類名設定<br>
     * <br>
     * 小分類名を引数で設定する<br>
     * <br>
     * @param smallClassificationCdNm
     */
    public void setSmallClassificationCdNm(String smallClassificationCdNm) {
        this.smallClassificationCdNm = smallClassificationCdNm;
    }

    /**
     * 添付付き有無フラグ名取得<br>
     * <br>
     * 添付付き有無フラグ名を戻り値で返却する<br>
     * <br>
     * @return attachmentFlgNm
     */
    public String getAttachmentFlgNm() {
        return attachmentFlgNm;
    }

    /**
     * 添付付き有無フラグ名設定<br>
     * <br>
     * 添付付き有無フラグ名を引数で設定する<br>
     * <br>
     * @param attachmentFlgNm
     */
    public void setAttachmentFlgNm(String attachmentFlgNm) {
        this.attachmentFlgNm = attachmentFlgNm;
    }
}
