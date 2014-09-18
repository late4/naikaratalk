package com.naikara_talk.action;

import java.util.ArrayList;
import java.util.List;

import com.naikara_talk.dto.PointOwnershipTrnDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.model.StudentMonthlyFeePurchaseInformationModel;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>マスタ保守<br>
 * <b>クラス名称　　　:</b>受講者マスタメンテナンス 受講者別月謝購入情報共通Actionクラス。<br>
 * <b>クラス概要　　　:</b>受講者マスタメンテナンス 受講者別月謝購入情報共通Action。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/07/30 TECS 新規作成。
 */
public abstract class StudentMonthlyFeePurchaseInformationActionSupport extends NaikaraActionSupport {

    private static final long serialVersionUID = 1L;

    // 画面表示のタイトル
    protected String title = "受講者別月謝購入情報";

    // Help画面名
    protected String helpPageId = "HelpStudentMonthlyFeePurchaseInformation.html";

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
        this.model.setStudentId(this.studentId);
        this.model.setStudentFamilyNm(this.studentFamilyNm);
        this.model.setStudentFirstNm(this.studentFirstNm);
        this.model.setUseStartDt(this.useStartDt);
        this.model.setUseEndDt(this.useEndDt);
        this.model.setNickNm(this.nickNm);
        this.model.setEndDt(this.endDt);

    }

    /** メッセージ */
    protected String message;

    /** 処理区分(前画面の引き継ぎ情報) */
    protected String processKbn_rdl;

    /** 受講者ID */
    protected String studentId;

    /** ニックネーム */
    protected String nickNm;

    /** 名前(姓) */
    protected String studentFamilyNm;

    /** 名前(名) */
    protected String studentFirstNm;

    /** 利用期間：開始日 */
    protected String useStartDt;

    /** 利用期間：終了日 */
    protected String useEndDt;

    /** 検索結果一覧 */
    protected List<PointOwnershipTrnDto> resultList = new ArrayList<PointOwnershipTrnDto>();

    /** 初期化model */
    protected StudentMonthlyFeePurchaseInformationModel model = new StudentMonthlyFeePurchaseInformationModel();

    /** 月謝停止年月 */
    protected String[] endDt;

    /** 検索結果一覧なし */
    protected int noData = 0;

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
     * @return studentId
     */
    public String getStudentId() {
        return studentId;
    }

    /**
     * @param studentId セットする studentId
     */
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    /**
     * @return nickNm
     */
    public String getNickNm() {
        return nickNm;
    }

    /**
     * @param nickNm セットする nickNm
     */
    public void setNickNm(String nickNm) {
        this.nickNm = nickNm;
    }

    /**
     * @return studentFamilyNm
     */
    public String getStudentFamilyNm() {
        return studentFamilyNm;
    }

    /**
     * @param studentFamilyNm セットする studentFamilyNm
     */
    public void setStudentFamilyNm(String studentFamilyNm) {
        this.studentFamilyNm = studentFamilyNm;
    }

    /**
     * @return studentFirstNm
     */
    public String getStudentFirstNm() {
        return studentFirstNm;
    }

    /**
     * @param studentFirstNm セットする studentFirstNm
     */
    public void setStudentFirstNm(String studentFirstNm) {
        this.studentFirstNm = studentFirstNm;
    }

    /**
     * @return useStartDt
     */
    public String getUseStartDt() {
        return useStartDt;
    }

    /**
     * @param useStartDt セットする useStartDt
     */
    public void setUseStartDt(String useStartDt) {
        this.useStartDt = useStartDt;
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
    public List<PointOwnershipTrnDto> getResultList() {
        return resultList;
    }

    /**
     * @param resultList セットする resultList
     */
    public void setResultList(List<PointOwnershipTrnDto> resultList) {
        this.resultList = resultList;
    }

    /**
     * @return model
     */
    public StudentMonthlyFeePurchaseInformationModel getModel() {
        return model;
    }

    /**
     * @param model セットする model
     */
    public void setModel(StudentMonthlyFeePurchaseInformationModel model) {
        this.model = model;
    }

    /**
     * @return endDt
     */
    public String[] getEndDt() {
        return endDt;
    }

    /**
     * @param endDt セットする endDt
     */
    public void setEndDt(String[] endDt) {
        this.endDt = endDt;
    }

    /**
     * @return noData
     */
    public int getNoData() {
        return noData;
    }

    /**
     * @param noData セットする noData
     */
    public void setNoData(int noData) {
        this.noData = noData;
    }

}
