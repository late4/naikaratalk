package com.naikara_talk.action;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dto.TeaBooMTeaMUsrMDto;
import com.naikara_talk.model.TeacherBookmarkListModel;
import com.naikara_talk.service.TeacherBookmarkListLoadService;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>マスタ保守<br>
 * <b>クラス名称       :</b>ブックマーク済の講師一覧（Pop）Actionスーパークラス<br>
 * <b>クラス概要       :</b>ブックマーク済の講師一覧情報を処理する。<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/08/13 TECS 新規作成
 */
public abstract class TeacherBookmarkListActionSupport extends NaikaraActionSupport {

    private static final long serialVersionUID = 1L;

    // 画面表示のタイトル
    protected String title = "ブックマーク済の講師一覧";

    // Help画面名
    protected String helpPageId = "HelpTeacherBookmarkList.html";

    /**
     * 画面のパラメータをセット。<br>
     * <br>
     * 画面のパラメータをセット。<br>
     * <br>
     * @param なし<br>
     * @return なし<br>
     * @exception なし
     */
    public void load() {
        if (!StringUtils.isEmpty(this.select_rdl)) {
            String[] selected = this.getSelect_rdl().substring(1, this.getSelect_rdl().length() - 1)
                    .replaceAll(NaikaraTalkConstants.HALF_SPACE, NaikaraTalkConstants.BRANK)
                    .split(String.valueOf(NaikaraTalkConstants.COMMA));
            // 一覧選択の講師ID
            this.teacherId = selected[0];
            // 一覧選択の講師名(ニックネーム)
            this.teacherNm = selected[1];
            // レコードバージョン番号
            this.recordVerNo = Integer.parseInt(selected[2]);
        }
    }

    /**
     * パラメータをモデルにセット。<br>
     * <br>
     * パラメータをモデルにセット。<br>
     * <br>
     * @param なし<br>
     * @return なし<br>
     * @exception なし
     */
    public void setupModel() {

        // 受講者ID
        this.model.setStudentId(this.studentId);

        if (!StringUtils.isEmpty(this.select_rdl)) {
            // 講師ID
            this.model.setTeacherId(this.teacherId);
            // 講師名(ニックネーム
            this.model.setTeacherNm(this.teacherNm);
            // レコードバージョン番号
            this.model.setRecordVerNo(this.recordVerNo);
        }
    }

    /**
     * 一覧データの取得。<br>
     * <br>
     * 一覧データを取得する。<br>
     * <br>
     * @param なし<br>
     * @return なし<br>
     * @throws Exception
     */
    protected void select() throws Exception {

        TeacherBookmarkListLoadService service = new TeacherBookmarkListLoadService();

        // 受講者別講師ブックマークテーブルのデータ取得
        List<TeaBooMTeaMUsrMDto> retDtoList = service.selectTeacherBookmarkList(this.model);

        if (retDtoList.get(0).getReturnCode() == NaikaraTalkConstants.RETURN_CD_DATA_YES) {
            this.model.setResultList(retDtoList);
        }

        // データ件数 ＞ 50 の場合
        if (this.model.getResultList().size() > NaikaraTalkConstants.TEACHER_LIST_DATA_COUNT) {
            // メッセージ情報を設定する
            this.addActionMessage(getMessage("EC0060", new String[] { "50" }));
        }

        for (TeaBooMTeaMUsrMDto retDto : this.model.getResultList()) {
            // 講師単位のコースリスト取得処理
            retDto.setTeacherCourseDtoList(service.selectTeacherCourseDtoList(retDto.getUserId()));
        }
    }

    /** 受講者ID */
    protected String studentId;

    /** 検索結果 */
    protected TeacherBookmarkListModel model = new TeacherBookmarkListModel();

    /** 一覧から選択された明細データ(jsp) */
    protected String select_rdl;

    /** 講師ID */
    protected String teacherId;

    /** 講師名(ニックネーム) */
    protected String teacherNm;

    /** レコードバージョン番号 */
    protected int recordVerNo;

    /** closeFlg */
    protected String closeFlg;

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
     * @return model
     */
    public TeacherBookmarkListModel getModel() {
        return model;
    }

    /**
     * @param model セットする model
     */
    public void setModel(TeacherBookmarkListModel model) {
        this.model = model;
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
     * @return teacherId
     */
    public String getTeacherId() {
        return teacherId;
    }

    /**
     * @param teacherId セットする teacherId
     */
    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    /**
     * @return teacherNm
     */
    public String getTeacherNm() {
        return teacherNm;
    }

    /**
     * @param teacherNm セットする teacherNm
     */
    public void setTeacherNm(String teacherNm) {
        this.teacherNm = teacherNm;
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
     * @return closeFlg
     */
    public String getCloseFlg() {
        return closeFlg;
    }

    /**
     * @param closeFlg セットする closeFlg
     */
    public void setCloseFlg(String closeFlg) {
        this.closeFlg = closeFlg;
    }

}
