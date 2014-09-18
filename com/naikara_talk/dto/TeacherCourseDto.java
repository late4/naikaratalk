package com.naikara_talk.dto;

import java.sql.Blob;
import java.sql.Timestamp;

import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称:</b>共通部品 DTOクラス<br>
 * <b>クラス名称　　　:</b>講師別コースマスタ(+コースマスタ)クラス<br>
 * <b>クラス概要　　　:</b>講師別コースマスタ(+コースマスタ)DTO<br>
 * <br>
 * <b>著作権　　　　　:</b>Copyright (C) 2013 NAI, Inc. All rights reserved.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/07/03 TECS 新規作成
 * <b>変更履歴　　　　:</b>2014/04/22 TECS 検索条件の追加(短コース名)対応
 */
public class TeacherCourseDto extends AbstractDto {

    // 講師別コースマスタ
    private String userId;                  // 講師ID (利用者ID)
    private String courseCd;                // コースコード
    private int recordVerNoT;               // レコードバージョン番号
    private Timestamp insertDttmT;          // 登録日時
    private String insertCdT;               // 登録者コード
    private Timestamp updateDttmT;          // 更新日時
    private String updateCdT;               // 更新者コード

    // コースマスタ
    private String bigClassificationCd;     // 大分類コード
    private String bigClassificationJnm;    // 大分類名
    private String bigClassificationEnm;    // 大分類名(英語名)
    private String middleClassificationCd;  // 中分類コード
    private String middleClassificationJnm; // 中分類名
    private String middleClassificationEnm; // 中分類名(英語名)
    private String smallClassificationCd;   // 小分類コード
    private String smallClassificationJnm;  // 小分類名
    private String smallClassificationEnm;  // 小分類名(英語名)
    private String courseJnm;               // コース名

    // 2014/04/22 Add Start 検索条件の追加(短コース名)対応
    private String courseJnmShort;          // コース名
    // 2014/04/22 Add End   検索条件の追加(短コース名)対応

    private String courseEnm;               // コース名(英語名)
    private String attachmentFlg;           // 添付付き有無フラグ
    private String keyword1;                // キーワード１
    private String keyword2;                // キーワード２
    private String keyword3;                // キーワード３
    private String keyword4;                // キーワード４
    private String keyword5;                // キーワード５
    private String simpleExplanation;       // 簡易説明
    private String explanation1;            // 詳細説明1
    private String explanation2;            // 詳細説明2
    private String explanation3;            // 詳細説明3
    private String explanation4;            // 詳細説明4
    private String explanation5;            // 詳細説明5
    private String explanation6Nm;          // 詳細説明6(PDF)名
    private Blob explanation6;              // 詳細説明6(PDF)
    private int lessonTime;                 // レッスン時間
    private String useStrDt;                // 提供開始日
    private String useEndDt;                // 提供終了日
    private String bookFlg;                 // 該当書籍有無フラグ
    private String remark;                  // 備考
    private int recordVerNoC;               // レコードバージョン番号
    private Timestamp insertDttmC;          // 登録日時
    private String insertCdC;               // 登録者コード
    private Timestamp updateDttmC;          // 更新日時
    private String updateCdC;               // 更新者コード
    private int returnCode;                 // リターンコード

    // 画面処理必要項目
    private String course_chkn;             // 削除

    /**
     * 講師ID (利用者ID)取得<br>
     * <br>
     * 講師ID (利用者ID)を戻り値で返却する<br>
     * <br>
     * @return userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 講師ID (利用者ID)設定<br>
     * <br>
     * 講師ID (利用者ID)を引数で設定する<br>
     * <br>
     * @param userId
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

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
     * レコードバージョン番号取得<br>
     * <br>
     * レコードバージョン番号を戻り値で返却する<br>
     * <br>
     * @return recordVerNoT
     */
    public int getRecordVerNoT() {
        return recordVerNoT;
    }

    /**
     * レコードバージョン番号設定<br>
     * <br>
     * レコードバージョン番号を引数で設定する<br>
     * <br>
     * @param recordVerNoT
     */
    public void setRecordVerNoT(int recordVerNoT) {
        this.recordVerNoT = recordVerNoT;
    }

    /**
     * 登録日時取得<br>
     * <br>
     * 登録日時を戻り値で返却する<br>
     * <br>
     * @return insertDttmT
     */
    public Timestamp getInsertDttmT() {
        return insertDttmT;
    }

    /**
     * 登録日時設定<br>
     * <br>
     * 登録日時を引数で設定する<br>
     * <br>
     * @param insertDttmT
     */
    public void setInsertDttmT(Timestamp insertDttmT) {
        this.insertDttmT = insertDttmT;
    }

    /**
     * 登録者コード取得<br>
     * <br>
     * 登録者コードを戻り値で返却する<br>
     * <br>
     * @return insertCdT
     */
    public String getInsertCdT() {
        return insertCdT;
    }

    /**
     * 登録者コード設定<br>
     * <br>
     * 登録者コードを引数で設定する<br>
     * <br>
     * @param insertCdT
     */
    public void setInsertCdT(String insertCdT) {
        this.insertCdT = insertCdT;
    }

    /**
     * 更新日時取得<br>
     * <br>
     * 更新日時を戻り値で返却する<br>
     * <br>
     * @return updateDttmT
     */
    public Timestamp getUpdateDttmT() {
        return updateDttmT;
    }

    /**
     * 更新日時設定<br>
     * <br>
     * 更新日時を引数で設定する<br>
     * <br>
     * @param updateDttmT
     */
    public void setUpdateDttmT(Timestamp updateDttmT) {
        this.updateDttmT = updateDttmT;
    }

    /**
     * 更新者コード取得<br>
     * <br>
     * 更新者コードを戻り値で返却する<br>
     * <br>
     * @return updateCdT
     */
    public String getUpdateCdT() {
        return updateCdT;
    }

    /**
     * 更新者コード設定<br>
     * <br>
     * 更新者コードを引数で設定する<br>
     * <br>
     * @param updateCdT
     */
    public void setUpdateCdT(String updateCdT) {
        this.updateCdT = updateCdT;
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
     * 大分類名取得<br>
     * <br>
     * 大分類名を戻り値で返却する<br>
     * <br>
     * @return bigClassificationJnm
     */
    public String getBigClassificationJnm() {
        return bigClassificationJnm;
    }

    /**
     * 大分類名設定<br>
     * <br>
     * 大分類名を引数で設定する<br>
     * <br>
     * @param bigClassificationJnm
     */
    public void setBigClassificationJnm(String bigClassificationJnm) {
        this.bigClassificationJnm = bigClassificationJnm;
    }

    /**
     * 大分類名(英語名)取得<br>
     * <br>
     * 大分類名(英語名)を戻り値で返却する<br>
     * <br>
     * @return bigClassificationEnm
     */
    public String getBigClassificationEnm() {
        return bigClassificationEnm;
    }

    /**
     * 大分類名(英語名)設定<br>
     * <br>
     * 大分類名(英語名)を引数で設定する<br>
     * <br>
     * @param bigClassificationEnm
     */
    public void setBigClassificationEnm(String bigClassificationEnm) {
        this.bigClassificationEnm = bigClassificationEnm;
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
     * 中分類名取得<br>
     * <br>
     * 中分類名を戻り値で返却する<br>
     * <br>
     * @return middleClassificationJnm
     */
    public String getMiddleClassificationJnm() {
        return middleClassificationJnm;
    }

    /**
     * 中分類名設定<br>
     * <br>
     * 中分類名を引数で設定する<br>
     * <br>
     * @param middleClassificationJnm
     */
    public void setMiddleClassificationJnm(String middleClassificationJnm) {
        this.middleClassificationJnm = middleClassificationJnm;
    }

    /**
     * 中分類名(英語名)取得<br>
     * <br>
     * 中分類名(英語名)を戻り値で返却する<br>
     * <br>
     * @return middleClassificationJnm
     */
    public String getMiddleClassificationEnm() {
        return middleClassificationEnm;
    }

    /**
     * 中分類名(英語名)設定<br>
     * <br>
     * 中分類名(英語名)を引数で設定する<br>
     * <br>
     * @param middleClassificationEnm
     */
    public void setMiddleClassificationEnm(String middleClassificationEnm) {
        this.middleClassificationEnm = middleClassificationEnm;
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
     * 小分類名取得<br>
     * <br>
     * 小分類名を戻り値で返却する<br>
     * <br>
     * @return smallClassificationJnm
     */
    public String getSmallClassificationJnm() {
        return smallClassificationJnm;
    }

    /**
     * 小分類名設定<br>
     * <br>
     * 小分類名を引数で設定する<br>
     * <br>
     * @param smallClassificationJnm
     */
    public void setSmallClassificationJnm(String smallClassificationJnm) {
        this.smallClassificationJnm = smallClassificationJnm;
    }

    /**
     * 小分類名(英語名)取得<br>
     * <br>
     * 小分類名(英語名)を戻り値で返却する<br>
     * <br>
     * @return smallClassificationEnm
     */
    public String getSmallClassificationEnm() {
        return smallClassificationEnm;
    }

    /**
     * 小分類名(英語名)設定<br>
     * <br>
     * 小分類名(英語名)を引数で設定する<br>
     * <br>
     * @param smallClassificationEnm
     */
    public void setSmallClassificationEnm(String smallClassificationEnm) {
        this.smallClassificationEnm = smallClassificationEnm;
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
     * 短コース名取得<br>
     * <br>
     * 短コース名を戻り値で返却する<br>
     * <br>
     * @return courseJnmShort
     */
    public String getCourseJnmShort() {
        return courseJnmShort;
    }

    /**
     * 短コース名設定<br>
     * <br>
     * 短コース名を引数で設定する<br>
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
     * @return recordVerNoC
     */
    public int getRecordVerNoC() {
        return recordVerNoC;
    }

    /**
     * レコードバージョン番号設定<br>
     * <br>
     * レコードバージョン番号を引数で設定する<br>
     * <br>
     * @param recordVerNoC
     */
    public void setRecordVerNoC(int recordVerNoC) {
        this.recordVerNoC = recordVerNoC;
    }

    /**
     * 登録日時取得<br>
     * <br>
     * 登録日時を戻り値で返却する<br>
     * <br>
     * @return insertDttmC
     */
    public Timestamp getInsertDttmC() {
        return insertDttmC;
    }

    /**
     * 登録日時設定<br>
     * <br>
     * 登録日時を引数で設定する<br>
     * <br>
     * @param insertDttmC
     */
    public void setInsertDttmC(Timestamp insertDttmC) {
        this.insertDttmC = insertDttmC;
    }

    /**
     * 登録者コード取得<br>
     * <br>
     * 登録者コードを戻り値で返却する<br>
     * <br>
     * @return insertCdC
     */
    public String getInsertCdC() {
        return insertCdC;
    }

    /**
     * 登録者コード設定<br>
     * <br>
     * 登録者コードを引数で設定する<br>
     * <br>
     * @param insertCdC
     */
    public void setInsertCdC(String insertCdC) {
        this.insertCdC = insertCdC;
    }

    /**
     * 更新日時取得<br>
     * <br>
     * 更新日時を戻り値で返却する<br>
     * <br>
     * @return updateDttmC
     */
    public Timestamp getUpdateDttmC() {
        return updateDttmC;
    }

    /**
     * 更新日時設定<br>
     * <br>
     * 更新日時を引数で設定する<br>
     * <br>
     * @param updateDttmC
     */
    public void setUpdateDttmC(Timestamp updateDttmC) {
        this.updateDttmC = updateDttmC;
    }

    /**
     * 更新者コード取得<br>
     * <br>
     * 更新者コードを戻り値で返却する<br>
     * <br>
     * @return updateCdC
     */
    public String getUpdateCdC() {
        return updateCdC;
    }

    /**
     * 更新者コード設定<br>
     * <br>
     * 更新者コードを引数で設定する<br>
     * <br>
     * @param updateCdC
     */
    public void setUpdateCdC(String updateCdC) {
        this.updateCdC = updateCdC;
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
     * 削除取得<br>
     * <br>
     * 削除を戻り値で返却する<br>
     * <br>
     * @return course_chkn
     */
    public String getCourse_chkn() {
        if (this.course_chkn == null) {
            this.course_chkn = NaikaraTalkConstants.FALSE;
        }
        return course_chkn;
    }

    /**
     * 削除設定<br>
     * <br>
     * 削除を引数で設定する<br>
     * <br>
     * @param course_chkn
     */
    public void setCourse_chkn(String course_chkn) {
        this.course_chkn = course_chkn;
    }
}
