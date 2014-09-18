package com.naikara_talk.action;

import java.util.LinkedHashMap;
import java.util.Map;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.model.OrganizationStudentMstListModel;
import com.naikara_talk.service.OrganizationStudentMstListLoadService;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>組織_初期処理<br>
 * <b>クラス名称       :</b>システム受講者登録(一覧)Actionスーパークラス。<br>
 * <b>クラス概要       :</b>組織の社員情報(受講者)の新規アカウント(一覧)。<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/11 TECS 新規作成
 */
public abstract class OrganizationStudentMstListActionSupport extends NaikaraActionSupport {

    private static final long serialVersionUID = 1L;

    // 画面表示のタイトル
    protected String title = "システム受講者登録";

    // Help画面名
    protected String helpPageId = "HelpOrganizationStudentMstList.html";

    /**
     * 利用状態の再取得<br>
     * <br>
     * チェックエラーの場合、利用状態の再取得。<br>
     * <br>
     * @param なし<br>
     * @return なし<br>
     * @exception なし
     */
    @Override
    public void validate() {

        // チェックエラーの場合、利用状態の再取得。
        try {
            initRadio();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * サービスの呼び出しの実装。
     */
    abstract protected String requestService() throws NaiException;

    /**
     * 利用状態を取得する。<br>
     * <br>
     * 利用状態を取得する。<br>
     * <br>
     * @param なし<br>
     * @return なし<br>
     * @exception Exception
     */
    public void initRadio() throws Exception {

        OrganizationStudentMstListLoadService service = new OrganizationStudentMstListLoadService();
        // 利用状態を取得する
        this.useKbn_rdll = service.selectCodeMst(NaikaraTalkConstants.CODE_CATEGORY_USE_STATE);
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
        this.model.setProcessKbn(this.processKbn_rdl);
        this.model.setPositionOrganizationId(this.positionOrganizationId_txt);
        this.model.setStudentPosition(this.studentPosition_txt);
        this.model.setStudentId(this.studentId_txt);
        this.model.setStudentNm(this.studentNm_txt);
        this.model.setUseKbn(this.useKbn_rdl);
        this.model.setSearchFlg(this.hasSearchFlg);
        this.model.setOrganizationId(this.organizationId);
        this.model.setOrganizationNm(this.organizationNm_txt);

    }

    /** メッセージ */
    protected String message;

    /** 処理区分(jsp) */
    protected String processKbn_rdl;

    /** 受講者所属部署(jsp) */
    protected String studentPosition_txt;

    /** 所属組織内ID(jsp) */
    protected String positionOrganizationId_txt;

    /** 受講者ID(jsp) */
    protected String studentId_txt;

    /** 受講者名(フリガナ)(jsp) */
    protected String studentNm_txt;

    /** 利用状態(jsp) */
    protected String useKbn_rdl;
    protected Map<String, String> useKbn_rdll = new LinkedHashMap<String, String>();

    /** 検索結果 */
    protected OrganizationStudentMstListModel model = new OrganizationStudentMstListModel();

    /** 一覧から選択された明細データ(jsp) */
    protected String select_rdl;

    /** チェックエラー場合、検索判断フラグ */
    protected String hasSearchFlg;

    /** 組織ID(jsp) */
    protected String organizationId;

    /** 組織名(jsp) */
    protected String organizationNm_txt;

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
     * @return processKbn_rdl
     */
    public String getProcessKbn_rdl() {
        return processKbn_rdl;
    }

    /**
     * @param processKbn_rdl
     *            セットする processKbn
     */
    public void setProcessKbn_rdl(String processKbn_rdl) {
        this.processKbn_rdl = processKbn_rdl;
    }

    /**
     * @return studentPosition_txt
     */
    public String getStudentPosition_txt() {
        return studentPosition_txt;
    }

    /**
     * @param studentPosition_txt
     *            セットする studentPosition_txt
     */
    public void setStudentPosition_txt(String studentPosition_txt) {
        this.studentPosition_txt = studentPosition_txt;
    }

    /**
     * @return positionOrganizationId_txt
     */
    public String getPositionOrganizationId_txt() {
        return positionOrganizationId_txt;
    }

    /**
     * @param positionOrganizationId_txt
     *            セットする positionOrganizationId_txt
     */
    public void setPositionOrganizationId_txt(String positionOrganizationId_txt) {
        this.positionOrganizationId_txt = positionOrganizationId_txt;
    }

    /**
     * @return studentId_txt
     */
    public String getStudentId_txt() {
        return studentId_txt;
    }

    /**
     * @param studentId_txt
     *            セットする studentId_txt
     */
    public void setStudentId_txt(String studentId_txt) {
        this.studentId_txt = studentId_txt;
    }

    /**
     * @return studentNm_txt
     */
    public String getStudentNm_txt() {
        return studentNm_txt;
    }

    /**
     * @param studentNm_txt
     *            セットする studentNm_txt
     */
    public void setStudentNm_txt(String studentNm_txt) {
        this.studentNm_txt = studentNm_txt;
    }

    /**
     * @return useKbn_rdl
     */
    public String getUseKbn_rdl() {
        return useKbn_rdl;
    }

    /**
     * @param useKbn_rdl
     *            セットする useKbn_rdl
     */
    public void setUseKbn_rdl(String useKbn_rdl) {
        this.useKbn_rdl = useKbn_rdl;
    }

    /**
     * @return useKbn_rdll
     */
    public Map<String, String> getUseKbn_rdll() {
        return useKbn_rdll;
    }

    /**
     * @param useKbn_rdll
     *            セットする useKbn_rdll
     */
    public void setUseKbn_rdll(Map<String, String> useKbn_rdll) {
        this.useKbn_rdll = useKbn_rdll;
    }

    /**
     * @return model
     */
    public OrganizationStudentMstListModel getModel() {
        return model;
    }

    /**
     * @param model
     *            セットする model
     */
    public void setModel(OrganizationStudentMstListModel model) {
        this.model = model;
    }

    /**
     * @return select_rdl
     */
    public String getSelect_rdl() {
        return select_rdl;
    }

    /**
     * @param select_rdl
     *            セットする select_rdl
     */
    public void setSelect_rdl(String select_rdl) {
        this.select_rdl = select_rdl;
    }

    /**
     * @return hasSearchFlg
     */
    public String getHasSearchFlg() {
        return hasSearchFlg;
    }

    /**
     * @param hasSearchFlg
     *            セットする hasSearchFlg
     */
    public void setHasSearchFlg(String hasSearchFlg) {
        this.hasSearchFlg = hasSearchFlg;
    }

    /**
     * @return organizationId
     */
    public String getOrganizationId() {
        return organizationId;
    }

    /**
     * @param organizationId
     *            セットする organizationId
     */
    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    /**
     * @return organizationNm_txt
     */
    public String getOrganizationNm_txt() {
        return organizationNm_txt;
    }

    /**
     * @param organizationNm_txt
     *            セットする organizationNm_txt
     */
    public void setOrganizationNm_txt(String organizationNm_txt) {
        this.organizationNm_txt = organizationNm_txt;
    }

}
