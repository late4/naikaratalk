package com.naikara_talk.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dto.CourseUsePointMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.model.GoodsCourseUsePointMstModel;
import com.naikara_talk.service.GoodsCourseUsePointMstLoadService;
import com.naikara_talk.sessiondata.SessionCourseMstGoodsMst;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>マスタ保守<br>
 * <b>クラス名称       :</b>コースマスタメンテナンス(コース利用ポイント)<br>
 * <b>クラス概要       :</b>コースマスタメンテナンス(コース利用ポイント)共通Action<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/08/13 TECS 新規作成
 */
public abstract class GoodsCourseUsePointMstActionSupport extends NaikaraActionSupport {

    private static final long serialVersionUID = 1L;

    // 画面表示のタイトル
    protected String title = "コース利用ポイント設定";

    // Help画面名
    protected String helpPageId = "HelpGoodsCourseUsePointMst.html";

    /**
     * 検索結果の再取得<br>
     * <br>
     * チェックエラーの場合、検索結果の再取得。<br>
     * <br>
     * @param なし<br>
     * @return なし<br>
     * @exception なし
     */
    @Override
    public void validate() {

        // チェックエラーの検索結果の再取得。
        try {
            initRadio();
            this.model.setResultList(((SessionCourseMstGoodsMst) SessionDataUtil
                    .getSessionData(SessionCourseMstGoodsMst.class.toString())).getTempCourseUsePointMstList());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * コース名を取得する。<br>
     * <br>
     * コース名を取得する。<br>
     * <br>
     * @param なし<br>
     * @return なし<br>
     * @throws NaiException
     */
    public void initRadio() throws NaiException {

        GoodsCourseUsePointMstLoadService service = new GoodsCourseUsePointMstLoadService();
        // コース名を取得する
        this.courseNm = NaikaraStringUtil.unionString4(
                service.selectCodeMst(NaikaraTalkConstants.CODE_CATEGORY_BIG_CLASSIFICATION).get(
                        this.bigClassificationCd),
                service.selectCodeMst(NaikaraTalkConstants.CODE_CATEGORY_MIDDLE_CLASSIFICATION).get(
                        this.middleClassificationCd),
                service.selectCodeMst(NaikaraTalkConstants.CODE_CATEGORY_SMALL_CLASSIFICATION).get(
                        this.smallClassificationCd), this.courseJnm);

    }

    /**
     * サービスの呼び出しの実装。
     */
    abstract protected String requestService() throws NaiException;

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
        // コースコード
        this.model.setCourseCd(this.courseCd);
        // 大分類
        this.model.setBigClassificationCd(this.bigClassificationCd);
        // 中分類
        this.model.setMiddleClassificationCd(this.middleClassificationCd);
        // 小分類
        this.model.setSmallClassificationCd(this.smallClassificationCd);
        // コース名
        this.model.setCourseJnm(this.courseJnm);
        // 提供開始日
        this.model.setUseStrDt(this.useStrDt);
        // 提供終了日
        this.model.setUseEndDt(this.useEndDt);
        // 修正No
        this.model.setNumberNo(this.numberNo);
        // 利用ポイント開始日
        this.model.setUsePointStrDt(this.utilizationStart_txt);
        // 利用ポイント終了日
        this.model.setUsePointEndDt(this.utilizationEnd_txt);
        // 利用ポイント
        this.model.setUsePoint(new BigDecimal(
                NaikaraStringUtil.delComma(StringUtils.isEmpty(this.utilizationPoint_txt) ? "0"
                        : this.utilizationPoint_txt)));
        // お知らせ
        this.model.setInformation(this.notice_txt);
        // レコードバージョン番号
        String recVerNo = StringUtils.isEmpty(this.recordVerNo) ? "0" : this.recordVerNo;
        this.model.setRecordVerNo(Integer.parseInt(recVerNo));

        // 処理区分(前画面の引き継ぎ情報)
        this.model.setProcessKbn_rdl(this.processKbn_rdl);
    }

    /** コースコード */
    protected String courseCd;

    /** 大分類 */
    protected String bigClassificationCd;

    /** 中分類 */
    protected String middleClassificationCd;

    /** 小分類 */
    protected String smallClassificationCd;

    /** コース名  */
    protected String courseJnm;

    /** 画面表示コース名 */
    protected String courseNm;

    /** 提供開始日 */
    protected String useStrDt;

    /** 提供終了日 */
    protected String useEndDt;

    /** 検索結果一覧 */
    protected List<CourseUsePointMstDto> resultList;

    /** 一覧部の「修正」 */
    protected String select_rdl;

    /** 一覧部の「削除」 */
    protected List<String> select_chk_list;

    /** 修正No */
    protected String numberNo;

    /** 利用ポイント開始日 */
    protected String utilizationStart_txt;

    /** 利用ポイント終了日 */
    protected String utilizationEnd_txt;

    /** 利用ポイント */
    protected String utilizationPoint_txt;

    /** お知らせ*/
    protected String notice_txt;

    /** レコードバージョン番号 */
    protected String recordVerNo;

    /** 修正チェック */
    protected String isUpdateChk;

    /** 処理区分(前画面の引き継ぎ情報) */
    protected String processKbn_rdl;

    /** 検索結果 */
    protected GoodsCourseUsePointMstModel model = new GoodsCourseUsePointMstModel();

    /**
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title
     *            セットする title
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
     * @param helpPageId
     *            セットする helpPageId
     */
    public void setHelpPageId(String helpPageId) {
        this.helpPageId = helpPageId;
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

    /**
     * @return courseNm
     */
    public String getCourseNm() {
        return courseNm;
    }

    /**
     * @param courseNm セットする courseNm
     */
    public void setCourseNm(String courseNm) {
        this.courseNm = courseNm;
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
     * @return resultList
     */
    public List<CourseUsePointMstDto> getResultList() {
        return resultList;
    }

    /**
     * @param resultList セットする resultList
     */
    public void setResultList(List<CourseUsePointMstDto> resultList) {
        this.resultList = resultList;
    }

    /**
     * @return select_rdl
     */
    public String getSelect_rdl() {
        return select_rdl;
    }

    /**
     * @param select_rdl セットする select_rdl
     */
    public void setSelect_rdl(String select_rdl) {
        this.select_rdl = select_rdl;
    }

    /**
     * @return select_chk_list
     */
    public List<String> getSelect_chk_list() {
        if (this.select_chk_list == null) {
            this.select_chk_list = new ArrayList<String>();
        }
        return select_chk_list;
    }

    /**
     * @param select_chk_list セットする select_chk_list
     */
    public void setSelect_chk_list(List<String> select_chk_list) {
        this.select_chk_list = select_chk_list;
    }

    /**
     * @return numberNo
     */
    public String getNumberNo() {
        return numberNo;
    }

    /**
     * @param numberNo セットする numberNo
     */
    public void setNumberNo(String numberNo) {
        this.numberNo = numberNo;
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
     * @return utilizationPoint_txt
     */
    public String getUtilizationPoint_txt() {
        return utilizationPoint_txt;
    }

    /**
     * @param utilizationPoint_txt セットする utilizationPoint_txt
     */
    public void setUtilizationPoint_txt(String utilizationPoint_txt) {
        this.utilizationPoint_txt = utilizationPoint_txt;
    }

    /**
     * @return notice_txt
     */
    public String getNotice_txt() {
        return notice_txt;
    }

    /**
     * @param notice_txt セットする notice_txt
     */
    public void setNotice_txt(String notice_txt) {
        this.notice_txt = notice_txt;
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
     * @return isUpdateChk
     */
    public String getIsUpdateChk() {
        return isUpdateChk;
    }

    /**
     * @param isUpdateChk セットする isUpdateChk
     */
    public void setIsUpdateChk(String isUpdateChk) {
        this.isUpdateChk = isUpdateChk;
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
     * @return model
     */
    public GoodsCourseUsePointMstModel getModel() {
        return model;
    }

    /**
     * @param model セットする model
     */
    public void setModel(GoodsCourseUsePointMstModel model) {
        this.model = model;
    }

}
