package com.naikara_talk.action;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.service.CourseMstMoveService;
import com.naikara_talk.sessiondata.SessionCourseMstGoodsMst;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>マスタ保守<br>
 * <b>クラス名称       :</b>コースマスタメンテナンス(単票)<br>
 * <b>クラス概要       :</b>コースマスタメンテナンス(単票)利用ポイント設定Action<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/08/13 TECS 新規作成
 * <b>変更履歴         :</b>2014/04/22 TECS 検索条件の追加(短コース名)対応
 */
public class CourseMstMoveAction extends CourseMstActionSupport {

    private static final long serialVersionUID = 1L;

    /**
     * 利用ポイント設定ボタンの処理。<br>
     * <br>
     * 利用ポイント設定ボタンの処理。<br>
     * <br>
     * @param なし<br>
     * @return String <br>
     * @exception NaiException
     */
    public String requestService() throws NaiException {

        // 開始ログ
        log.info(NaikaraStringUtil.unionProcesslog("Start"));

        // Modelクラス設定
        setupModel();
        // Serviceクラス生成
        CourseMstMoveService service = new CourseMstMoveService();

        int chkResult = service.nextPageRequest(this.model);

        try {
            switch (chkResult) {
            case CourseMstMoveService.ERR_NOT_SELECT_COURSELARGE_SEL:
                this.addActionMessage(getMessage("EN0001", new String[] { "大分類" }));
                return SUCCESS;
            case CourseMstMoveService.ERR_NOT_SELECT_COURSEMEDIUM_SEL:
                this.addActionMessage(getMessage("EN0001", new String[] { "中分類" }));
                return SUCCESS;
            case CourseMstMoveService.ERR_NOT_SELECT_COURSESMALL_SEL:
                this.addActionMessage(getMessage("EN0001", new String[] { "小分類" }));
                return SUCCESS;
            case CourseMstMoveService.ERR_INTEGRITY_DT:
                this.addActionMessage(getMessage("EN0011", new String[] { "提供期間：開始日", "提供期間：終了日" }));
                return SUCCESS;
            }
            // 戻る用に必要な情報を取得/格納
            this.actionToSession();
        } catch (Exception e) {
            throw new NaiException(e);
        }

        // ヘッダの戻るリンク用
        setCurrentActionName(NaikaraTalkConstants.COURSE_MST_LOAD);

        // 詳細画面遷移
        return NEXTPAGE;
    }

    /**
     * Action値 To Session<br>
     * <br>
     * Action値・SessionCourseMstGoodsMst値をSessionCourseMstGoodsMstにセット。<br>
     * <br>
     * @param なし<br>
     * @return なし<br>
     * @exception なし
     */
    private void actionToSession() {

        // 戻る用に必要な情報を格納
        SessionCourseMstGoodsMst sessionData = null;
        if ((SessionCourseMstGoodsMst) SessionDataUtil.getSessionData(SessionCourseMstGoodsMst.class.toString()) != null) {
            sessionData = (SessionCourseMstGoodsMst) SessionDataUtil.getSessionData(SessionCourseMstGoodsMst.class
                    .toString());
        } else {
            sessionData = new SessionCourseMstGoodsMst();
        }
        sessionData.setReturnOnFlg(true); // 戻る判定Onフラグ

        // 大分類
        sessionData.setBigClassificationCd(this.courseLarge_sel);
        // 中分類
        sessionData.setMiddleClassificationCd(this.courseMedium_sel);
        // 小分類
        sessionData.setSmallClassificationCd(this.courseSmall_sel);
        // コースコード
        sessionData.setCourseCd(this.courseCode_txt);
        // コース名
        sessionData.setCourseJnm(this.courseName_txt);

        // 2014/04/22 Add Start 検索条件の追加(短コース名)対応
        // 短コース名
        sessionData.setCourseJnmShort(this.courseNameShort_txt);
        // 2014/04/22 Add End   検索条件の追加(短コース名)対応

        // コース名 (英語名)
        sessionData.setCourseEnm(this.courseNameEng_txt);
        // 添付付き有無フラグ
        sessionData.setAttachmentFlg(this.tempKbn_rdl);
        // キーワード1
        sessionData.setKeyword1(this.keyword1_txt);
        // キーワード2
        sessionData.setKeyword2(this.keyword2_txt);
        // キーワード3
        sessionData.setKeyword3(this.keyword3_txt);
        // キーワード4
        sessionData.setKeyword4(this.keyword4_txt);
        // キーワード5
        sessionData.setKeyword5(this.keyword5_txt);
        // 簡易説明
        sessionData.setSimpleExplanation(this.simplicity_txa);
        // 詳細説明1
        sessionData.setExplanation1(this.detail1_txa);
        // 詳細説明2
        sessionData.setExplanation2(this.detail2_txa);
        // 詳細説明3
        sessionData.setExplanation3(this.detail3_txa);
        // 詳細説明4
        sessionData.setExplanation4(this.detail4_txa);
        // 詳細説明5
        sessionData.setExplanation5(this.detail5_txa);
        // 詳細説明６：ファイル名
        sessionData.setOldExplanation6Nm(this.detail6_fileName);
        // 詳細説明6(PDF)名
        sessionData.setExplanation6Nm(this.detail6_filFileName);
        // 詳細説明6(PDF)
        sessionData.setExplanation6(this.detail6_fil);
        // 詳細説明６：削除
        sessionData.setDetail6FileDel(this.detail6FileDel_chkn);
        // レッスン時間
        String lessonTime = StringUtils.isEmpty(this.lessonTime) ? "30" : this.lessonTime;
        sessionData.setLessonTime(Integer.parseInt(lessonTime));
        // 提供開始日
        sessionData.setUseStrDt(NaikaraStringUtil.converToYYYYMMDD(this.utilizationStart_txt));
        // 提供終了日
        sessionData.setUseEndDt(NaikaraStringUtil.converToYYYYMMDD(this.utilizationEnd_txt));
        // 更新前提供開始日
        sessionData.setOldUseStrDt(this.old_utilizationStart_txt);
        // コース別利用ポイント
        sessionData.setCourseUsePointMstList(this.courseUsePointMstDtoList);
        // 該当書籍有無フラグ
        sessionData.setBookFlg(this.existence_rdl);
        // コース別商品マスタ
        sessionData.setCourseGoodsListDtoList(this.courseGoodsListDtoList);
        // 備考
        sessionData.setRemark(this.remarks_txa);
        // レコードバージョン番号
        String recVerNo = StringUtils.isEmpty(this.recordVerNo) ? "0" : this.recordVerNo;
        sessionData.setRecordVerNo(Integer.parseInt(recVerNo));

        // 処理区分(前画面情報)
        sessionData.setProcessKbn_rdl(this.processKbn_rdl);
        // 画面表示処理区分
        sessionData.setProcessKbn_txt(this.processKbn_txt);

        SessionDataUtil.setSessionData(sessionData);
    }
}
