package com.naikara_talk.action;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.naikara_talk.dto.PointOwnershipTrnDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.model.StudentSpecialFreePointMstModel;
import com.naikara_talk.service.StudentSpecialFreePointMstLoadService;
import com.naikara_talk.sessiondata.SessionStudentMst;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>マスタ保守<br>
 * <b>クラス名称　　　:</b>受講者マスタメンテナンス【単票】特別無償ポイント設定共通Actionクラス。<br>
 * <b>クラス概要　　　:</b>受講者マスタメンテナンス【単票】特別無償ポイント設定共通Action。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/07/30 TECS 新規作成。
 */
public abstract class StudentSpecialFreePointMstActionSupport extends NaikaraActionSupport {

    private static final long serialVersionUID = 1L;

    // 画面表示のタイトル
    protected String title = "特別無償ポイント設定";

    // Help画面名
    protected String helpPageId = "HelpStudentSpecialFreePointMst.html";

    /**
     * チェック。<br>
     * <br>
     * コースコードを戻り値で返却する<br>
     * <br>
     * @param なし
     * @return なし
     */
    @Override
    public void validate() {

        // チェックエラーの場合、ドロップダウンと一覧の再取得。
        try {
            initSelect();
            this.model.setResultList(((SessionStudentMst) SessionDataUtil.getSessionData(SessionStudentMst.class
                    .toString())).getTempSpecialFreeReturnList());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * サービスの呼び出しの実装。
     */
    abstract protected String requestService() throws NaiException;

    /**
     * ドロップダウンを取得する。<br>
     * <br>
     * @param なし
     * @return void
     * @throws Exception
     */
    public void initSelect() throws Exception {

        StudentSpecialFreePointMstLoadService service = new StudentSpecialFreePointMstLoadService();
        // 条件キー条件１～５のリストを取得する
        this.pointAdditionReason_sell = service
                .selectCodeMst(NaikaraTalkConstants.CODE_CATEGORY_POINT_ADDITIONAL_REASON);

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
        this.model.setStudentId(this.studentId);
        this.model.setStudentFamilyNm(this.studentFamilyNm);
        this.model.setStudentFirstNm(this.studentFirstNm);
        this.model.setUseStartDt(this.useStartDt);
        this.model.setUseEndDt(this.useEndDt);
        this.model.setEffectStartDt(this.effectStartDt_txt);
        this.model.setEffectEndDt(this.effectEndDt_txt);
        this.model.setBalancePoint(this.balancePoint_txt);
        this.model.setPointAdditionReason(this.pointAdditionReason_sel);

    }

    /** メッセージ */
    protected String message;

    /** 処理区分(前画面の引き継ぎ情報) */
    protected String processKbn_rdl;

    /** 受講者ID */
    protected String studentId;

    /** 名前(姓) */
    protected String studentFamilyNm;

    /** 名前(名) */
    protected String studentFirstNm;

    /** 利用期間：開始日 */
    protected String useStartDt;

    /** 利用期間：終了日 */
    protected String useEndDt;

    /** 入力：有効開始日 */
    protected String effectStartDt_txt;

    /** 入力：有効終了日 */
    protected String effectEndDt_txt;

    /** 入力：無償ポイント */
    protected String balancePoint_txt;

    /** 入力：ポイント付加理由 */
    protected String pointAdditionReason_sel;
    protected Map<String, String> pointAdditionReason_sell = new LinkedHashMap<String, String>();

    /** 検索結果一覧 */
    protected List<PointOwnershipTrnDto> resultList = new ArrayList<PointOwnershipTrnDto>();

    /** 初期化model */
    protected StudentSpecialFreePointMstModel model = new StudentSpecialFreePointMstModel();

    /** 一覧から選択された明細データ(jsp:checkboxlist) */
    protected List<Integer> select_chk = new ArrayList<Integer>();

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
     * @return effectStartDt_txt
     */
    public String getEffectStartDt_txt() {
        return effectStartDt_txt;
    }

    /**
     * @param effectStartDt_txt セットする effectStartDt_txt
     */
    public void setEffectStartDt_txt(String effectStartDt_txt) {
        this.effectStartDt_txt = effectStartDt_txt;
    }

    /**
     * @return effectEndDt_txt
     */
    public String getEffectEndDt_txt() {
        return effectEndDt_txt;
    }

    /**
     * @param effectEndDt_txt セットする effectEndDt_txt
     */
    public void setEffectEndDt_txt(String effectEndDt_txt) {
        this.effectEndDt_txt = effectEndDt_txt;
    }

    /**
     * @return balancePoint_txt
     */
    public String getBalancePoint_txt() {
        return balancePoint_txt;
    }

    /**
     * @param balancePoint_txt セットする balancePoint_txt
     */
    public void setBalancePoint_txt(String balancePoint_txt) {
        this.balancePoint_txt = balancePoint_txt;
    }

    /**
     * @return pointAdditionReason_sel
     */
    public String getPointAdditionReason_sel() {
        return pointAdditionReason_sel;
    }

    /**
     * @param pointAdditionReason_sel セットする pointAdditionReason_sel
     */
    public void setPointAdditionReason_sel(String pointAdditionReason_sel) {
        this.pointAdditionReason_sel = pointAdditionReason_sel;
    }

    /**
     * @return pointAdditionReason_sell
     */
    public Map<String, String> getPointAdditionReason_sell() {
        return pointAdditionReason_sell;
    }

    /**
     * @param pointAdditionReason_sell セットする pointAdditionReason_sell
     */
    public void setPointAdditionReason_sell(Map<String, String> pointAdditionReason_sell) {
        this.pointAdditionReason_sell = pointAdditionReason_sell;
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
    public StudentSpecialFreePointMstModel getModel() {
        return model;
    }

    /**
     * @param model セットする model
     */
    public void setModel(StudentSpecialFreePointMstModel model) {
        this.model = model;
    }

    /**
     * @return select_chk
     */
    public List<Integer> getSelect_chk() {
        return select_chk;
    }

    /**
     * @param select_chk セットする select_chk
     */
    public void setSelect_chk(List<Integer> select_chk) {
        this.select_chk = select_chk;
    }

}
