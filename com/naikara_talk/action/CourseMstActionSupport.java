package com.naikara_talk.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dto.CourseGoodsListDto;
import com.naikara_talk.dto.CourseUsePointMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.model.CourseMstModel;
import com.naikara_talk.service.CourseMstLoadService;
import com.naikara_talk.sessiondata.SessionCourseMstGoodsMst;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>マスタ保守<br>
 * <b>クラス名称       :</b>コースマスタメンテナンス(単票)<br>
 * <b>クラス概要       :</b>コースマスタメンテナンス(単票)共通Action<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/08/09 TECS 新規作成
 * <b>                 :</b>2014/04/22 TECS 項目の追加(短コース名)対応
 */
public abstract class CourseMstActionSupport extends NaikaraActionSupport {

    private static final long serialVersionUID = 1L;

    // 画面表示のタイトル
    protected String title = "コースマスタメンテナンス";

    // Help画面名
    protected String helpPageId = "HelpCourseMst.html";

    /**
     * 大分類、中分類、小分類、添付付き有無フラグ、該当書籍有無フラグの再取得<br>
     * <br>
     * チェックエラーの場合、大分類、中分類、小分類、添付付き有無フラグ、該当書籍有無フラグの再取得。<br>
     * <br>
     * @param なし<br>
     * @return なし<br>
     * @exception なし
     */
    @Override
    public void validate() {

        // チェックエラーの場合、大分類、中分類、小分類、添付付き有無フラグ、該当書籍有無フラグの再取得。
        try {
            initRadio();
            SessionCourseMstGoodsMst sessionData = null;

            // コースマスタメンテナンスのセッション情報の存在判断
            if ((SessionCourseMstGoodsMst) SessionDataUtil.getSessionData(SessionCourseMstGoodsMst.class.toString()) != null) {
                sessionData = (SessionCourseMstGoodsMst) SessionDataUtil.getSessionData(SessionCourseMstGoodsMst.class
                        .toString());
            } else {
                sessionData = new SessionCourseMstGoodsMst();
            }
            // 詳細説明6(PDF)の選択判断
            if (this.detail6_fil != null) {
                // 詳細説明6(PDF)はセッションに存在判断
                if (sessionData.getExplanation6() != null) {
                    File file = sessionData.getExplanation6();
                    file.delete();
                }
                InputStream is = new FileInputStream(this.detail6_fil);

                byte[] bytes = new byte[is.available()];
                is.read(bytes, 0, is.available());

                // 選択された詳細説明6をセッションに格納
                sessionData.setBytExplanation6(bytes);

                is.close();

                // 詳細説明6Fileはセッションに格納
                sessionData.setExplanation6(this.detail6_fil);
                // 詳細説明6(PDF)名はセッションに格納
                sessionData.setExplanation6Nm(this.detail6_filFileName);
            } else {

                // 詳細説明6(PDF)はセッションに存在判断
                if (sessionData.getBytExplanation6() != null) {
                    File file = sessionData.getExplanation6();
                    byte[] bytes = sessionData.getBytExplanation6();
                    try {
                        OutputStream os = new FileOutputStream(file);
                        if (bytes != null) {
                            os.write(bytes);
                        }
                        os.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * サービスの呼び出しの実装。
     */
    abstract protected String requestService() throws NaiException;

    /**
     * 大分類、中分類、小分類、添付付き有無フラグ、該当書籍有無フラグを取得する。<br>
     * <br>
     * 大分類、中分類、小分類、添付付き有無フラグ、該当書籍有無フラグを取得する。<br>
     * <br>
     * @param なし<br>
     * @return なし<br>
     * @exception NaiException
     */
    public void initRadio() throws NaiException {

        CourseMstLoadService service = new CourseMstLoadService();
        // 大分類を取得する
        this.courseLarge_sell = service.selectCodeMst(NaikaraTalkConstants.CODE_CATEGORY_BIG_CLASSIFICATION);
        // 中分類を取得する
        this.courseMedium_sell = service.selectCodeMst(NaikaraTalkConstants.CODE_CATEGORY_MIDDLE_CLASSIFICATION);
        // 小分類を取得する
        this.courseSmall_sell = service.selectCodeMst(NaikaraTalkConstants.CODE_CATEGORY_SMALL_CLASSIFICATION);
        // 添付付き有無フラグを取得する
        this.tempKbn_rdll = service.selectCodeMst(NaikaraTalkConstants.CODE_CATEGORY_ATTACHMENT_KBN);
        // 該当書籍有無フラグを取得する
        this.existence_rdll = service.selectCodeMst(NaikaraTalkConstants.CODE_CATEGORY_PRODUCTS_GOODS_KBN);
    }

    /**
     * 画面のパラメータをモデルにセット。<br>
     * <br>
     * 画面のパラメータをモデルにセット。<br>
     * <br>
     * @param なし<br>
     * @return なし<br>
     * @exception なし
     */
    public void setupModel() {

        // 大分類
        this.model.setBigClassificationCd(this.courseLarge_sel);
        // 中分類
        this.model.setMiddleClassificationCd(this.courseMedium_sel);
        // 小分類
        this.model.setSmallClassificationCd(this.courseSmall_sel);
        // コースコード
        this.model.setCourseCd(this.courseCode_txt);
        // コース名
        this.model.setCourseJnm(this.courseName_txt);

        // 2014/04/22 Add Start 項目の追加(短コース名)対応
        // 短コース名
        this.model.setCourseJnmShort(this.courseNameShort_txt);
        // 2014/04/22 Add End   項目の追加(短コース名)対応

        // コース名 (英語名)
        this.model.setCourseEnm(this.courseNameEng_txt);
        // 添付付き有無フラグ
        this.model.setAttachmentFlg(this.tempKbn_rdl);
        // キーワード1
        this.model.setKeyword1(this.keyword1_txt);
        // キーワード2
        this.model.setKeyword2(this.keyword2_txt);
        // キーワード3
        this.model.setKeyword3(this.keyword3_txt);
        // キーワード4
        this.model.setKeyword4(this.keyword4_txt);
        // キーワード5
        this.model.setKeyword5(this.keyword5_txt);
        // 簡易説明
        this.model.setSimpleExplanation(this.simplicity_txa);
        // 詳細説明1
        this.model.setExplanation1(this.detail1_txa);
        // 詳細説明2
        this.model.setExplanation2(this.detail2_txa);
        // 詳細説明3
        this.model.setExplanation3(this.detail3_txa);
        // 詳細説明4
        this.model.setExplanation4(this.detail4_txa);
        // 詳細説明5
        this.model.setExplanation5(this.detail5_txa);
        // 詳細説明６：ファイル名
        this.model.setOldExplanation6Nm(this.detail6_fileName);

        if ((SessionCourseMstGoodsMst) SessionDataUtil.getSessionData(SessionCourseMstGoodsMst.class.toString()) != null) {
            SessionCourseMstGoodsMst sessionData = (SessionCourseMstGoodsMst) SessionDataUtil
                    .getSessionData(SessionCourseMstGoodsMst.class.toString());
            // 詳細説明6(PDF)名
            this.model.setExplanation6Nm(sessionData.getExplanation6Nm());
            // 詳細説明6(PDF)
            this.model.setExplanation6(sessionData.getExplanation6());
        } else {
            // 詳細説明6(PDF)名
            this.model.setExplanation6Nm(this.detail6_filFileName);
            // 詳細説明6(PDF)
            this.model.setExplanation6(this.detail6_fil);
        }
        // 詳細説明６：削除
        this.model.setDetail6FileDel(this.detail6FileDel_chkn);
        // レッスン時間
        String lessonTime = StringUtils.isEmpty(this.lessonTime) ? "30" : this.lessonTime;
        this.model.setLessonTime(Integer.parseInt(lessonTime));
        // 提供開始日
        this.model.setUseStrDt(this.utilizationStart_txt);
        // 提供終了日
        this.model.setUseEndDt(this.utilizationEnd_txt);
        // 更新前提供開始日
        this.model.setOldUseStrDt(this.old_utilizationStart_txt);
        // コース別利用ポイント
        this.model.setCourseUsePointMstDtoList(this.courseUsePointMstDtoList);
        // 該当書籍有無フラグ
        this.model.setBookFlg(this.existence_rdl);
        // コース別商品マスタ
        this.model.setCourseGoodsListDtoList(this.courseGoodsListDtoList);
        // 備考
        this.model.setRemark(this.remarks_txa);
        // レコードバージョン番号
        String recVerNo = StringUtils.isEmpty(this.recordVerNo) ? "0" : this.recordVerNo;
        this.model.setRecordVerNo(Integer.parseInt(recVerNo));

        // 処理区分(前画面の引き継ぎ情報)
        this.model.setProcessKbn_rdl(this.processKbn_rdl);
    }

    /** メッセージ */
    protected String message;

    /** 大分類 */
    protected String courseLarge_sel;
    protected Map<String, String> courseLarge_sell = new LinkedHashMap<String, String>();

    /** 中分類 */
    protected String courseMedium_sel;
    protected Map<String, String> courseMedium_sell = new LinkedHashMap<String, String>();

    /** 小分類 */
    protected String courseSmall_sel;
    protected Map<String, String> courseSmall_sell = new LinkedHashMap<String, String>();

    /** コースコード */
    protected String courseCode_txt;

    /** コース名  */
    protected String courseName_txt;

    // 2014/04/22 Add Start 項目の追加(短コース名)対応
    /** コース名  */
    protected String courseNameShort_txt;
    // 2014/04/22 Add End   項目の追加(短コース名)対応

    /** コース名 (英語名) */
    protected String courseNameEng_txt;

    /** 添付付き有無フラグ  */
    protected String tempKbn_rdl;
    protected Map<String, String> tempKbn_rdll = new LinkedHashMap<String, String>();

    /** キーワード1 */
    protected String keyword1_txt;

    /** キーワード2 */
    protected String keyword2_txt;

    /** キーワード3 */
    protected String keyword3_txt;

    /** キーワード4 */
    protected String keyword4_txt;

    /** キーワード5 */
    protected String keyword5_txt;

    /** 簡易説明 */
    protected String simplicity_txa;

    /** 詳細説明1 */
    protected String detail1_txa;

    /** 詳細説明2 */
    protected String detail2_txa;

    /** 詳細説明3 */
    protected String detail3_txa;

    /** 詳細説明4 */
    protected String detail4_txa;

    /** 詳細説明5 */
    protected String detail5_txa;

    /** 詳細説明６：ファイル名 */
    protected String detail6_fileName;

    /** 詳細説明6(PDF)名 */
    protected String detail6_filFileName;

    /** 詳細説明6(PDF) */
    protected File detail6_fil;

    /** 詳細説明６：削除 */
    protected String detail6FileDel_chkn;

    /** レッスン時間 */
    protected String lessonTime;

    /** 提供開始日 */
    protected String utilizationStart_txt;

    /** 提供終了日 */
    protected String utilizationEnd_txt;

    /** 更新前提供開始日 */
    protected String old_utilizationStart_txt;

    /** コース別利用ポイント */
    protected List<CourseUsePointMstDto> courseUsePointMstDtoList;

    /** 該当書籍有無フラグ */
    protected String existence_rdl;
    protected Map<String, String> existence_rdll = new LinkedHashMap<String, String>();

    /** コース別商品マスタ */
    protected List<CourseGoodsListDto> courseGoodsListDtoList;

    /** 備考 */
    protected String remarks_txa;

    /** レコードバージョン番号 */
    protected String recordVerNo;

    /** 処理区分(前画面の引き継ぎ情報) */
    protected String processKbn_rdl;

    /** 画面表示処理区分 */
    protected String processKbn_txt;

    /** 説明コメント */
    protected String comment;

    /** 処理結果 */
    protected CourseMstModel model = new CourseMstModel();

    /** 提供期間：開始日 が設定されていない */
    protected String error_utilizationStart_empty;

    /** 提供期間：終了日 が設定されていない */
    protected String error_utilizationEnd_empty;

    /** 提供期間：開始日 が日付ではない */
    protected String error_utilizationStart_notDate;

    /** 提供期間：終了日 が日付ではない */
    protected String error_utilizationEnd_notDate;

    /** ｢提供期間：開始日｣　＞　｢提供期間：終了日｣　の場合 */
    protected String err_integrity_date;

    /**
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title セットする title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return helpPageId
     */
    public String getHelpPageId() {
        return helpPageId;
    }

    /**
     * @param helpPageId セットする helpPageId
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
     * @param message セットする message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return courseLarge_sel
     */
    public String getCourseLarge_sel() {
        return courseLarge_sel;
    }

    /**
     * @param courseLarge_sel セットする courseLarge_sel
     */
    public void setCourseLarge_sel(String courseLarge_sel) {
        this.courseLarge_sel = courseLarge_sel;
    }

    /**
     * @return courseLarge_sell
     */
    public Map<String, String> getCourseLarge_sell() {
        return courseLarge_sell;
    }

    /**
     * @param courseLarge_sell セットする courseLarge_sell
     */
    public void setCourseLarge_sell(Map<String, String> courseLarge_sell) {
        this.courseLarge_sell = courseLarge_sell;
    }

    /**
     * @return courseMedium_sel
     */
    public String getCourseMedium_sel() {
        return courseMedium_sel;
    }

    /**
     * @param courseMedium_sel セットする courseMedium_sel
     */
    public void setCourseMedium_sel(String courseMedium_sel) {
        this.courseMedium_sel = courseMedium_sel;
    }

    /**
     * @return courseMedium_sell
     */
    public Map<String, String> getCourseMedium_sell() {
        return courseMedium_sell;
    }

    /**
     * @param courseMedium_sell セットする courseMedium_sell
     */
    public void setCourseMedium_sell(Map<String, String> courseMedium_sell) {
        this.courseMedium_sell = courseMedium_sell;
    }

    /**
     * @return courseSmall_sel
     */
    public String getCourseSmall_sel() {
        return courseSmall_sel;
    }

    /**
     * @param courseSmall_sel セットする courseSmall_sel
     */
    public void setCourseSmall_sel(String courseSmall_sel) {
        this.courseSmall_sel = courseSmall_sel;
    }

    /**
     * @return courseSmall_sell
     */
    public Map<String, String> getCourseSmall_sell() {
        return courseSmall_sell;
    }

    /**
     * @param courseSmall_sell セットする courseSmall_sell
     */
    public void setCourseSmall_sell(Map<String, String> courseSmall_sell) {
        this.courseSmall_sell = courseSmall_sell;
    }

    /**
     * @return courseCode_txt
     */
    public String getCourseCode_txt() {
        return courseCode_txt;
    }

    /**
     * @param courseCode_txt セットする courseCode_txt
     */
    public void setCourseCode_txt(String courseCode_txt) {
        this.courseCode_txt = courseCode_txt;
    }

    /**
     * @return courseName_txt
     */
    public String getCourseName_txt() {
        return courseName_txt;
    }

    /**
     * @param courseName_txt セットする courseName_txt
     */
    public void setCourseName_txt(String courseName_txt) {
        this.courseName_txt = courseName_txt;
    }

    // 2014/04/22 Add Start 項目の追加(短コース名)対応
    /**
     * @return courseNameShort_txt
     */
    public String getCourseNameShort_txt() {
        return courseNameShort_txt;
    }

    /**
     * @param courseNameShort_txt セットする courseNameShort_txt
     */
    public void setCourseNameShort_txt(String courseNameShort_txt) {
        this.courseNameShort_txt = courseNameShort_txt;
    }
    // 2014/04/22 Add End   項目の追加(短コース名)対応


    /**
     * @return courseNameEng_txt
     */
    public String getCourseNameEng_txt() {
        return courseNameEng_txt;
    }

    /**
     * @param courseNameEng_txt セットする courseNameEng_txt
     */
    public void setCourseNameEng_txt(String courseNameEng_txt) {
        this.courseNameEng_txt = courseNameEng_txt;
    }

    /**
     * @return tempKbn_rdl
     */
    public String getTempKbn_rdl() {
        return tempKbn_rdl;
    }

    /**
     * @param tempKbn_rdl セットする tempKbn_rdl
     */
    public void setTempKbn_rdl(String tempKbn_rdl) {
        this.tempKbn_rdl = tempKbn_rdl;
    }

    /**
     * @return tempKbn_rdll
     */
    public Map<String, String> getTempKbn_rdll() {
        return tempKbn_rdll;
    }

    /**
     * @param tempKbn_rdll セットする tempKbn_rdll
     */
    public void setTempKbn_rdll(Map<String, String> tempKbn_rdll) {
        this.tempKbn_rdll = tempKbn_rdll;
    }

    /**
     * @return keyword1_txt
     */
    public String getKeyword1_txt() {
        return keyword1_txt;
    }

    /**
     * @param keyword1_txt セットする keyword1_txt
     */
    public void setKeyword1_txt(String keyword1_txt) {
        this.keyword1_txt = keyword1_txt;
    }

    /**
     * @return keyword2_txt
     */
    public String getKeyword2_txt() {
        return keyword2_txt;
    }

    /**
     * @param keyword2_txt セットする keyword2_txt
     */
    public void setKeyword2_txt(String keyword2_txt) {
        this.keyword2_txt = keyword2_txt;
    }

    /**
     * @return keyword3_txt
     */
    public String getKeyword3_txt() {
        return keyword3_txt;
    }

    /**
     * @param keyword3_txt セットする keyword3_txt
     */
    public void setKeyword3_txt(String keyword3_txt) {
        this.keyword3_txt = keyword3_txt;
    }

    /**
     * @return keyword4_txt
     */
    public String getKeyword4_txt() {
        return keyword4_txt;
    }

    /**
     * @param keyword4_txt セットする keyword4_txt
     */
    public void setKeyword4_txt(String keyword4_txt) {
        this.keyword4_txt = keyword4_txt;
    }

    /**
     * @return keyword5_txt
     */
    public String getKeyword5_txt() {
        return keyword5_txt;
    }

    /**
     * @param keyword5_txt セットする keyword5_txt
     */
    public void setKeyword5_txt(String keyword5_txt) {
        this.keyword5_txt = keyword5_txt;
    }

    /**
     * @return simplicity_txa
     */
    public String getSimplicity_txa() {
        return simplicity_txa;
    }

    /**
     * @param simplicity_txa セットする simplicity_txa
     */
    public void setSimplicity_txa(String simplicity_txa) {
        this.simplicity_txa = simplicity_txa;
    }

    /**
     * @return detail1_txa
     */
    public String getDetail1_txa() {
        return detail1_txa;
    }

    /**
     * @param detail1_txa セットする detail1_txa
     */
    public void setDetail1_txa(String detail1_txa) {
        this.detail1_txa = detail1_txa;
    }

    /**
     * @return detail2_txa
     */
    public String getDetail2_txa() {
        return detail2_txa;
    }

    /**
     * @param detail2_txa セットする detail2_txa
     */
    public void setDetail2_txa(String detail2_txa) {
        this.detail2_txa = detail2_txa;
    }

    /**
     * @return detail3_txa
     */
    public String getDetail3_txa() {
        return detail3_txa;
    }

    /**
     * @param detail3_txa セットする detail3_txa
     */
    public void setDetail3_txa(String detail3_txa) {
        this.detail3_txa = detail3_txa;
    }

    /**
     * @return detail4_txa
     */
    public String getDetail4_txa() {
        return detail4_txa;
    }

    /**
     * @param detail4_txa セットする detail4_txa
     */
    public void setDetail4_txa(String detail4_txa) {
        this.detail4_txa = detail4_txa;
    }

    /**
     * @return detail5_txa
     */
    public String getDetail5_txa() {
        return detail5_txa;
    }

    /**
     * @param detail5_txa セットする detail5_txa
     */
    public void setDetail5_txa(String detail5_txa) {
        this.detail5_txa = detail5_txa;
    }

    /**
     * @return detail6_fileName
     */
    public String getDetail6_fileName() {
        return detail6_fileName;
    }

    /**
     * @param detail6_fileName セットする detail6_fileName
     */
    public void setDetail6_fileName(String detail6_fileName) {
        this.detail6_fileName = detail6_fileName;
    }

    /**
     * @return detail6_filFileName
     */
    public String getDetail6_filFileName() {
        return detail6_filFileName;
    }

    /**
     * @param detail6_filFileName セットする detail6_filFileName
     */
    public void setDetail6_filFileName(String detail6_filFileName) {
        this.detail6_filFileName = detail6_filFileName;
    }

    /**
     * @return detail6_fil
     */
    public File getDetail6_fil() {
        return detail6_fil;
    }

    /**
     * @param detail6_fil セットする detail6_fil
     */
    public void setDetail6_fil(File detail6_fil) {
        this.detail6_fil = detail6_fil;
    }

    /**
     * @return detail6FileDel_chkn
     */
    public String getDetail6FileDel_chkn() {
        return detail6FileDel_chkn;
    }

    /**
     * @param detail6FileDel_chkn セットする detail6FileDel_chkn
     */
    public void setDetail6FileDel_chkn(String detail6FileDel_chkn) {
        this.detail6FileDel_chkn = detail6FileDel_chkn;
    }

    /**
     * @return lessonTime
     */
    public String getLessonTime() {
        return lessonTime;
    }

    /**
     * @param lessonTime セットする lessonTime
     */
    public void setLessonTime(String lessonTime) {
        this.lessonTime = lessonTime;
    }

    /**
     * @return utilizationStart_txt
     */
    public String getUtilizationStart_txt() {
        return utilizationStart_txt;
    }

    /**
     * @param utilizationStart_txt セットする utilizationStart_txt
     */
    public void setUtilizationStart_txt(String utilizationStart_txt) {
        this.utilizationStart_txt = utilizationStart_txt;
    }

    /**
     * @return utilizationEnd_txt
     */
    public String getUtilizationEnd_txt() {
        return utilizationEnd_txt;
    }

    /**
     * @param utilizationEnd_txt セットする utilizationEnd_txt
     */
    public void setUtilizationEnd_txt(String utilizationEnd_txt) {
        this.utilizationEnd_txt = utilizationEnd_txt;
    }

    /**
     * @return old_utilizationStart_txt
     */
    public String getOld_utilizationStart_txt() {
        return old_utilizationStart_txt;
    }

    /**
     * @param old_utilizationStart_txt セットする old_utilizationStart_txt
     */
    public void setOld_utilizationStart_txt(String old_utilizationStart_txt) {
        this.old_utilizationStart_txt = old_utilizationStart_txt;
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
     * @return existence_rdl
     */
    public String getExistence_rdl() {
        return existence_rdl;
    }

    /**
     * @param existence_rdl セットする existence_rdl
     */
    public void setExistence_rdl(String existence_rdl) {
        this.existence_rdl = existence_rdl;
    }

    /**
     * @return existence_rdll
     */
    public Map<String, String> getExistence_rdll() {
        return existence_rdll;
    }

    /**
     * @param existence_rdll セットする existence_rdll
     */
    public void setExistence_rdll(Map<String, String> existence_rdll) {
        this.existence_rdll = existence_rdll;
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
     * @return remarks_txa
     */
    public String getRemarks_txa() {
        return remarks_txa;
    }

    /**
     * @param remarks_txa セットする remarks_txa
     */
    public void setRemarks_txa(String remarks_txa) {
        this.remarks_txa = remarks_txa;
    }

    /**
     * @return recordVerNo
     */
    public String getRecordVerNo() {
        return recordVerNo;
    }

    /**
     * @param recordVerNo セットする recordVerNo
     */
    public void setRecordVerNo(String recordVerNo) {
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
     * @return comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * @param comment セットする comment
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * @return model
     */
    public CourseMstModel getModel() {
        return model;
    }

    /**
     * @param model セットする model
     */
    public void setModel(CourseMstModel model) {
        this.model = model;
    }

    /**
     * @return error_utilizationStart_empty
     */
    public String getError_utilizationStart_empty() {
        return error_utilizationStart_empty;
    }

    /**
     * @param error_utilizationStart_empty セットする error_utilizationStart_empty
     */
    public void setError_utilizationStart_empty(String error_utilizationStart_empty) {
        this.error_utilizationStart_empty = error_utilizationStart_empty;
    }

    /**
     * @return error_utilizationEnd_empty
     */
    public String getError_utilizationEnd_empty() {
        return error_utilizationEnd_empty;
    }

    /**
     * @param error_utilizationEnd_empty セットする error_utilizationEnd_empty
     */
    public void setError_utilizationEnd_empty(String error_utilizationEnd_empty) {
        this.error_utilizationEnd_empty = error_utilizationEnd_empty;
    }

    /**
     * @return error_utilizationStart_notDate
     */
    public String getError_utilizationStart_notDate() {
        return error_utilizationStart_notDate;
    }

    /**
     * @param error_utilizationStart_notDate セットする error_utilizationStart_notDate
     */
    public void setError_utilizationStart_notDate(String error_utilizationStart_notDate) {
        this.error_utilizationStart_notDate = error_utilizationStart_notDate;
    }

    /**
     * @return error_utilizationEnd_notDate
     */
    public String getError_utilizationEnd_notDate() {
        return error_utilizationEnd_notDate;
    }

    /**
     * @param error_utilizationEnd_notDate セットする error_utilizationEnd_notDate
     */
    public void setError_utilizationEnd_notDate(String error_utilizationEnd_notDate) {
        this.error_utilizationEnd_notDate = error_utilizationEnd_notDate;
    }

    /**
     * @return err_integrity_date
     */
    public String getErr_integrity_date() {
        return err_integrity_date;
    }

    /**
     * @param err_integrity_date セットする err_integrity_date
     */
    public void setErr_integrity_date(String err_integrity_date) {
        this.err_integrity_date = err_integrity_date;
    }

}
