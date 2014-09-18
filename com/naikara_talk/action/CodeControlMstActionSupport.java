package com.naikara_talk.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dto.CodeClassMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.model.CodeControlMstListModel;
import com.naikara_talk.model.CodeControlMstModel;
import com.naikara_talk.service.CodeControlMstListLoadService;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>コード管理マスタメンテナンス(単票)。<br>
 * <b>クラス名称　　　:</b>コード管理マスタメンテナンスActionスーパークラス。<br>
 * <b>クラス概要　　　:</b>コード管理マスタメンテナンスAction。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/07/16 TECS 新規作成。
 */
public abstract class CodeControlMstActionSupport extends NaikaraActionSupport {

    private static final long serialVersionUID = 1L;

    // 画面表示のタイトル
    protected String title = "コード管理マスタメンテナンス";

    // Help画面名
    protected String helpPageId = "HelpCodeControlMst.html";

    /**
     * チェック。<br>
     * <br>
     * コード種別名を戻り値で返却する<br>
     * <br>
     * @param なし
     * @return なし
     */
    @Override
    public void validate() {

        // チェックエラーの場合、コード種別名(ドロップダウンリスト)の再取得。
        try {
            this.initSelectList();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * サービスの呼び出しの実装。
     */
    abstract protected String requestService() throws NaiException;

    /**
     * コード種別名(ドロップダウンリスト)を取得する。<br>
     * <br>
     * @param なし
     * @return void
     * @throws Exception
     */
    public void initSelectList() throws Exception {

        // CodeControlMstListLoadServiceクラスの生成
        CodeControlMstListLoadService service = new CodeControlMstListLoadService();

        // CodeClassMstDtoクラスの生成
        CodeClassMstDto dto = new CodeClassMstDto();

        // コード種別名(ドロップダウンリスト)のデータの取得
        List<CodeClassMstDto> list = service.selectCodeClassMst(dto);

        // コード種別名(ドロップダウンリスト)を取得する
        this.setCdCategoryList(list);

    }

    /**
     * 画面のパラメータをモデルにセット。<br>
     * <br>
     * @param なし
     * @return なし
     */
    public void setupModel() {

        // 一覧画面の情報
        this.model.setProcessKbn_Rdl(this.processKbn_rdl);               // 処理区分(前画面の引き継ぎ情報)
        this.model.setDefaultCdCategory(this.defaultCdCategory);         // コード種別(前画面で選択された値)

        // 単票画面の情報
        this.model.setProcessKbn_Txt(this.processKbn_txt);               // 処理区分(画面表示)
        this.model.setCdCategoryList(this.cdCategoryList);               // コード種別(List)

        if (StringUtils.equals(CodeControlMstListModel.PROS_KBN_ADD, this.processKbn_rdl)) {
            this.model.setCdCategorySelected(this.cdCategory_sel);       // コード種別
        } else {
            // disabled="true" の場合は値の取得ができない為の対応
            this.model.setCdCategorySelected(this.defaultCdCategory);    // コード種別
        }
        this.model.setManagerCd(this.managerCd_txt);                     // 汎用コード
        this.model.setManagerNm(this.managerNm_txt);                     // 汎用フィールド
        this.model.setOrderBy(this.orderBy_txt);                         // 並び順
        this.model.setRemark(this.remark_txa);                           // 備考

        // システムで使用する項目
        this.model.setStudentMstEnm(this.studentMstEnm);                 // 受講者マスタ英語項目名
        this.model.setMark(this.mark);                                   // 表示用点数

        String sysDelNgFlg = StringUtils.isEmpty(this.systemDelNgFlg)
                ? NaikaraTalkConstants.SYSTEM_DEL_NG_FLG_NO
                : this.systemDelNgFlg;
        this.model.setSystemDelNgFlg(sysDelNgFlg);                       // システム削除不可フラグ

        String recVerNo = StringUtils.isEmpty(this.recordVerNo)
                ? "0"
                : this.recordVerNo;
        this.model.setRecordVerNo(Integer.parseInt(recVerNo));           // レコードバージョン番号

    }

    /** メッセージ */
    protected String message;

    /** 処理区分(前画面の引き継ぎ情報) */
    protected String processKbn_rdl;

    /** 処理区分(画面表示) */
    protected String processKbn_txt;

    /** 説明コメント(照会の場合) */
    protected String comment;

    /** コード種別名(jsp) name */
    protected String cdCategory_sel;

    /** コード種別名(jsp) name,list */
    protected List<CodeClassMstDto> cdCategoryList = new ArrayList<CodeClassMstDto>();

    /** コード種別名(jsp)-value 初期値 */
    protected String defaultCdCategory;

    /** 汎用コード(jsp) */
    protected String managerCd_txt;

    /** 汎用フィールド(jsp) */
    protected String orderBy_txt;

    /** 並び順(jsp) */
    protected String managerNm_txt;

    /** 備考(jsp) */
    protected String remark_txa;

    /** 受講者マスタ英語項目名(jsp) */
    protected String studentMstEnm;

    /** 表示用点数(jsp) */
    protected String mark;

    /** システム削除不可フラグ(jsp) */
    protected String systemDelNgFlg;

    /** 排他用レコードバージョン(jsp) */
    protected String recordVerNo;

    /** 処理結果 */
    protected CodeControlMstModel model = new CodeControlMstModel();


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
     *            セットする processKbn_rdl
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
     * @param processKbn_txt
     *            セットする processKbn_txt
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
     * @param comment
     *            セットする comment
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * @return cdCategory_sel
     */
    public String getCdCategory_sel() {
        return cdCategory_sel;
    }

    /**
     * @param cdCategory_sel
     *            セットする cdCategory_sel
     */
    public void setCdCategory_sel(String cdCategory_sel) {
        this.cdCategory_sel = cdCategory_sel;
    }

    /**
     * @return cdCategoryList
     */
    public List<CodeClassMstDto> getCdCategoryList() {
        return cdCategoryList;
    }

    /**
     * @param cdCategoryList
     *            セットする cdCategoryList
     */
    public void setCdCategoryList(List<CodeClassMstDto> cdCategoryList) {
        this.cdCategoryList = cdCategoryList;
    }

    /**
     * @return defaultCdCategory
     */
    public String getDefaultCdCategory() {
        return defaultCdCategory;
    }

    /**
     * @param defaultCdCategory
     *            セットする defaultCdCategory
     */
    public void setDefaultCdCategory(String defaultCdCategory) {
        this.defaultCdCategory = defaultCdCategory;
    }

    /**
     * @return managerCd_txt
     */
    public String getManagerCd_txt() {
        return managerCd_txt;
    }

    /**
     * @param managerCd_txt
     *            セットする managerCd_txt
     */
    public void setManagerCd_txt(String managerCd_txt) {
        this.managerCd_txt = managerCd_txt;
    }

    /**
     * @return managerNm_txt
     */
    public String getManagerNm_txt() {
        return managerNm_txt;
    }

    /**
     * @param managerNm_txt
     *            セットする managerNm_txt
     */
    public void setManagerNm_txt(String managerNm_txt) {
        this.managerNm_txt = managerNm_txt;
    }

    /**
     * @return orderBy_txt
     */
    public String getOrderBy_txt() {
        return orderBy_txt;
    }

    /**
     * @param orderBy_txt
     *            セットする orderBy_txt
     */
    public void setOrderBy_txt(String orderBy_txt) {
        this.orderBy_txt = orderBy_txt;
    }

    /**
     * @return remark_txa
     */
    public String getRemark_txa() {
        return remark_txa;
    }

    /**
     * @param remark_txa
     *            セットする remark_txa
     */
    public void setRemark_txa(String remark_txa) {
        this.remark_txa = remark_txa;
    }

    /**
     * @return studentMstEnm
     */
    public String getStudentMstEnm() {
        return studentMstEnm;
    }

    /**
     * @param studentMstEnm
     *            セットする studentMstEnm
     */
    public void setStudentMstEnm(String studentMstEnm) {
        this.studentMstEnm = studentMstEnm;
    }

    /**
     * @return mark
     */
    public String getMark() {
        return mark;
    }

    /**
     * @param mark
     *            セットする mark
     */
    public void setMark(String mark) {
        this.mark = mark;
    }

    /**
     * @return systemDelNgFlg
     */
    public String getSystemDelNgFlg() {
        return systemDelNgFlg;
    }

    /**
     * @param systemDelNgFlg
     *            セットする systemDelNgFlg
     */
    public void setSystemDelNgFlg(String systemDelNgFlg) {
        this.systemDelNgFlg = systemDelNgFlg;
    }

    /**
     * @return recordVerNo
     */
    public String getRecordVerNo() {
        return recordVerNo;
    }

    /**
     * @param recordVerNo
     *            セットする recordVerNo
     */
    public void setRecordVerNo(String recordVerNo) {
        this.recordVerNo = recordVerNo;
    }

    /**
     * @return model
     */
    public CodeControlMstModel getModel() {
        return model;
    }

    /**
     * @param model
     *            セットする model
     */
    public void setModel(CodeControlMstModel model) {
        this.model = model;
    }

}
