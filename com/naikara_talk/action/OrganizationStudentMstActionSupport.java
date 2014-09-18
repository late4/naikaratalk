package com.naikara_talk.action;

import java.util.LinkedHashMap;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dto.CodeManagMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.model.OrganizationStudentMstModel;
import com.naikara_talk.mstcache.CodeManagMstCache;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>組織_初期処理。<br>
 * <b>クラス名称　　　:</b>システム受講者登録(単票)Actionスーパークラス。<br>
 * <b>クラス概要　　　:</b>組織の社員情報(受講者)の新規アカウント(単票)。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/07/15 TECS 新規作成。
 */
public abstract class OrganizationStudentMstActionSupport extends NaikaraActionSupport {

    private static final long serialVersionUID = 1L;

    // 画面表示のタイトル
    protected String title = "システム受講者登録";

    // Help画面名
    protected String helpPageId = "HelpOrganizationStudentMst.html";

    /**
     * 利用停止状態の再取得<br>
     * <br>
     * チェックエラーの場合、利用停止状態の再取得。<br>
     * <br>
     * @param なし<br>
     * @return なし<br>
     * @exception なし
     */
    @Override
    public void validate() {

        // チェックエラーの場合、利用停止状態の再取得。
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
     * 利用停止状態を取得する。<br>
     * <br>
     * 利用停止状態を取得する。<br>
     * <br>
     * @param なし<br>
     * @return なし<br>
     * @exception Exception
     */
    public void initRadio() throws Exception {

        // 利用停止状態を取得する
        CodeManagMstCache cache = CodeManagMstCache.getInstance();

        LinkedHashMap<String, CodeManagMstDto> useStopFlgNmList = cache
                .getList(NaikaraTalkConstants.CODE_CATEGORY_SUSPENDED_STATE);

        this.useStopFlgNm = useStopFlgNmList.get(NaikaraTalkConstants.SUSPENDED_STATE).getManagerNm();
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
        // 処理区分(前画面の引き継ぎ情報)
        this.model.setProcessKbn_rdl(this.processKbn_rdl);
        // 画面表示処理区分
        this.model.setProcessKbn_txt(this.processKbn_txt);
        // 組織名
        this.model.setOrganizationNm_txt(this.organizationNm_txt);
        // 組織ID
        this.model.setOrganizationId(this.organizationId);
        // 受講者ID
        this.model.setStudentId(this.studentId);
        // 仮パスワード
        this.model.setPassword_txt(this.password_txt);
        // 仮パスワード確認
        this.model.setPassworCon_txt(this.passworCon_txt);
        // 受講者所属部署
        this.model.setStudentPosition(this.studentPosition_txt);
        // 所属組織内ID
        this.model.setPositionOrganizationId(this.positionOrganizationId_txt);
        // お名前(姓)
        this.model.setFamilyJnm(this.familyJnm_txt);
        // お名前(名)
        this.model.setFirstJnm(this.firstJnm_txt);
        // 自己負担率
        this.model.setBurdenNum(Integer.parseInt(StringUtils.isEmpty(this.burdenNum_txt) ? "0" : this.burdenNum_txt));
        // 利用停止状態
        this.model.setUseStopFlg(this.useStopFlg_chkn);
        // 利用停止日
        this.model.setUseEndDt(this.useEndDt_txt);
        // レコードバージョン番号
        String recVerNo = StringUtils.isEmpty(this.recordVerNo) ? "0" : this.recordVerNo;
        this.model.setRecordVerNo(Integer.parseInt(recVerNo));

    }

    /** メッセージ */
    protected String message;

    /** 処理区分(前画面の引き継ぎ情報) */
    protected String processKbn_rdl;

    /** 処理区分 */
    protected String processKbn_txt;

    /** 組織id */
    protected String organizationId;

    /** 組織名 */
    protected String organizationNm_txt;

    /** 受講者ID */
    protected String studentId;

    /** 仮パスワード */
    protected String password_txt;

    /** 仮パスワード確認 */
    protected String passworCon_txt;

    /** 受講者所属部署 */
    protected String studentPosition_txt;

    /** 所属組織内ID */
    protected String positionOrganizationId_txt;

    /** お名前(姓) */
    protected String familyJnm_txt;

    /** お名前(名) */
    protected String firstJnm_txt;

    /** 自己負担率 */
    protected String burdenNum_txt;

    /** 利用停止状態 */
    protected String useStopFlgNm;

    /** 利用停止フラグ */
    protected String useStopFlg_chkn;

    /** 利用停止日 */
    protected String useEndDt_txt;

    /** 排他用レコードバージョン */
    protected String recordVerNo;

    /** 処理結果 */
    protected OrganizationStudentMstModel model = new OrganizationStudentMstModel();

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
     * @return organizationId
     */
    public String getOrganizationId() {
        return organizationId;
    }

    /**
     * @param organizationId セットする organizationId
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
     * @param organizationNm_txt セットする organizationNm_txt
     */
    public void setOrganizationNm_txt(String organizationNm_txt) {
        this.organizationNm_txt = organizationNm_txt;
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
     * @return password_txt
     */
    public String getPassword_txt() {
        return password_txt;
    }

    /**
     * @param password_txt セットする password_txt
     */
    public void setPassword_txt(String password_txt) {
        this.password_txt = password_txt;
    }

    /**
     * @return passworCon_txt
     */
    public String getPassworCon_txt() {
        return passworCon_txt;
    }

    /**
     * @param passworCon_txt セットする passworCon_txt
     */
    public void setPassworCon_txt(String passworCon_txt) {
        this.passworCon_txt = passworCon_txt;
    }

    /**
     * @return studentPosition_txt
     */
    public String getStudentPosition_txt() {
        return studentPosition_txt;
    }

    /**
     * @param studentPosition_txt セットする studentPosition_txt
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
     * @param positionOrganizationId_txt セットする positionOrganizationId_txt
     */
    public void setPositionOrganizationId_txt(String positionOrganizationId_txt) {
        this.positionOrganizationId_txt = positionOrganizationId_txt;
    }

    /**
     * @return familyJnm_txt
     */
    public String getFamilyJnm_txt() {
        return familyJnm_txt;
    }

    /**
     * @param familyJnm_txt セットする familyJnm_txt
     */
    public void setFamilyJnm_txt(String familyJnm_txt) {
        this.familyJnm_txt = familyJnm_txt;
    }

    /**
     * @return firstJnm_txt
     */
    public String getFirstJnm_txt() {
        return firstJnm_txt;
    }

    /**
     * @param firstJnm_txt セットする firstJnm_txt
     */
    public void setFirstJnm_txt(String firstJnm_txt) {
        this.firstJnm_txt = firstJnm_txt;
    }

    /**
     * @return burdenNum_txt
     */
    public String getBurdenNum_txt() {
        return burdenNum_txt;
    }

    /**
     * @param burdenNum_txt セットする burdenNum_txt
     */
    public void setBurdenNum_txt(String burdenNum_txt) {
        this.burdenNum_txt = burdenNum_txt;
    }

    /**
     * @return useEndDt_txt
     */
    public String getUseEndDt_txt() {
        return useEndDt_txt;
    }

    /**
     * @param useEndDt_txt セットする useEndDt_txt
     */
    public void setUseEndDt_txt(String useEndDt_txt) {
        this.useEndDt_txt = useEndDt_txt;
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
     * @return model
     */
    public OrganizationStudentMstModel getModel() {
        return model;
    }

    /**
     * @param model セットする model
     */
    public void setModel(OrganizationStudentMstModel model) {
        this.model = model;
    }

    /**
     * @return useStopFlgNm
     */
    public String getUseStopFlgNm() {
        return useStopFlgNm;
    }

    /**
     * @param useStopFlgNm セットする useStopFlgNm
     */
    public void setUseStopFlgNm(String useStopFlgNm) {
        this.useStopFlgNm = useStopFlgNm;
    }

    /**
     * @return useStopFlg
     */
    public String getUseStopFlg_chkn() {
        return useStopFlg_chkn;
    }

    /**
     * @param useStopFlg セットする useStopFlg
     */
    public void setUseStopFlg_chkn(String useStopFlg_chkn) {
        this.useStopFlg_chkn = useStopFlg_chkn;
    }

}
