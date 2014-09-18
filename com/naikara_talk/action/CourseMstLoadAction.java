package com.naikara_talk.action;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.model.CourseMstListModel;
import com.naikara_talk.model.CourseMstModel;
import com.naikara_talk.model.PointControlListModel;
import com.naikara_talk.service.CourseMstLoadService;
import com.naikara_talk.sessiondata.SessionCourseMstGoodsMst;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>マスタ保守<br>
 * <b>クラス名称       :</b>コースマスタメンテナンス(単票)<br>
 * <b>クラス概要       :</b>コースマスタメンテナンス(単票)初期処理Action<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/08/12 TECS 新規作成
 *                     :</b>2014/04/22 TECS 項目の追加(短コース名)対応
 */
public class CourseMstLoadAction extends CourseMstActionSupport {

    private static final long serialVersionUID = 1L;

    /**
     * 画面の初期表示。<br>
     * <br>
     * 画面の初期表示。<br>
     * <br>
     * @param なし<br>
     * @return String SUCCESS <br>
     * @exception NaiException
     */
    public String requestService() throws NaiException {

        // 開始ログ
        log.info(NaikaraStringUtil.unionProcesslog("Start"));

        setupModel();
        CourseMstLoadService service = new CourseMstLoadService();

        // 戻る判定Onフラグ
        boolean returnOnFlg = false;

        if ((SessionCourseMstGoodsMst) SessionDataUtil.getSessionData(SessionCourseMstGoodsMst.class.toString()) != null) {

            // 戻る判定Onフラグ
            returnOnFlg = ((SessionCourseMstGoodsMst) SessionDataUtil.getSessionData(SessionCourseMstGoodsMst.class
                    .toString())).getReturnOnFlg();
        }

        // 大分類、中分類、小分類、添付付き有無フラグ、該当書籍有無フラグの初期取得。
        try {
            initRadio();
        } catch (Exception e) {
            throw new NaiException(e);
        }

        // 検索前チェック
        if (!returnOnFlg) {
            CourseMstModel workModel = service.initLoad(model);

            // 前画面から処理区分を画面にセット
            this.processKbn_rdl = workModel.getProcessKbn_rdl();
            this.processKbn_txt = workModel.getProcessKbn_txt();

            // ******************************
            // 新規・修正・照会の処理
            // ******************************

            // 処理区分＝新規の場合('0':'新規','1':'修正','2':'照会')
            if (StringUtils.equals(CourseMstListModel.PROS_KBN_ADD, this.processKbn_rdl)) {

                // コースコード
                this.courseCode_txt = NaikaraTalkConstants.BRANK;

                // 提供期間開始日
                this.utilizationStart_txt = workModel.getUseStrDt();

                // 提供期間終了日
                this.utilizationEnd_txt = workModel.getUseEndDt();

                // レッスン時間 初期値設定
                this.lessonTime = String.valueOf(workModel.getLessonTime());

                // コース別商品
                this.courseGoodsListDtoList = workModel.getCourseGoodsListDtoList();

            }

            // 処理区分＝照会の場合('0':'新規','1':'修正','2':'照会')
            if (StringUtils.equals(CourseMstListModel.PROS_KBN_REF, this.processKbn_rdl)) {
                this.setComment(NaikaraTalkConstants.PROCESSKBN_REF_COMMENT);
            }

            // 処理区分＝修正、照会の場合('0':'新規','1':'修正','2':'照会')
            if (StringUtils.equals(CourseMstListModel.PROS_KBN_UPD, this.processKbn_rdl)
                    || StringUtils.equals(CourseMstListModel.PROS_KBN_REF, this.processKbn_rdl)) {

                // データが存在しない場合
                try {
                    if (NaikaraTalkConstants.RETURN_CD_DATA_NO == service.getExist(model)) {
                        this.message = getMessage("EN0020", new String[] { "コースマスタ", "" });
                        removeLatestActionList();
                        // 前画面(一覧)へ戻り、エラーメッセージを表示
                        return ERROR;
                    } else {
                        // データが存在する場合

                        // 処理区分＝照会の場合('1':'修正','2':'照会')
                        if (StringUtils.equals(PointControlListModel.PROS_KBN_REF, this.processKbn_rdl)) {
                            this.setComment(NaikaraTalkConstants.PROCESSKBN_REF_COMMENT);
                        }

                        // 表示データの取得処理
                        this.load();

                        if (NaikaraTalkConstants.RETURN_CD_DATA_NO == service.getCourseGoodsExist(model)
                                && StringUtils.equals(NaikaraTalkConstants.PRODUCTS_GOODS_KBN_Y, this.existence_rdl) ) {
                            this.addActionMessage(getMessage("EN0020", new String[] { "コース別商品マスタ", "" }));
                        }

                    }
                } catch (Exception e1) {
                    throw new NaiException(e1);
                }
            }
        } else {
            this.sessionToAction();
        }
        try {
            this.actionToSession();
            this.getAlertMessage();
        } catch (Exception e) {
            throw new NaiException(e);
        }
        return SUCCESS;
    }

    /**
     * 初期処理、表示データの取得。<br>
     * <br>
     * 初期処理、表示データの取得。<br>
     * <br>
     * @param なし<br>
     * @return なし <br>
     * @exception NaiException
     */
    private void load() throws NaiException {

        // 表示データを取得する
        CourseMstLoadService service = new CourseMstLoadService();

        // 前画面の情報
        this.model.setCourseCd(this.getCourseCode_txt());
        this.model.setProcessKbn_rdl(this.processKbn_rdl);
        this.model.setProcessKbn_txt(this.processKbn_txt);

        this.model = service.select(this.model);

        // コースコード
        this.courseCode_txt = model.getCourseCd();
        // 大分類
        this.courseLarge_sel = model.getBigClassificationCd();
        // 中分類
        this.courseMedium_sel = model.getMiddleClassificationCd();
        // 小分類
        this.courseSmall_sel = model.getSmallClassificationCd();
        // コース名
        this.courseName_txt = model.getCourseJnm();

        // 2014/04/22 Add Start 検索条件の追加(短コース名)対応
        // 短コース名
        this.courseNameShort_txt = model.getCourseJnmShort();
        // 2014/04/22 Add End   検索条件の追加(短コース名)対応

        // コース名 (英語名)
        this.courseNameEng_txt = model.getCourseEnm();
        // 添付付き有無フラグ
        this.tempKbn_rdl = model.getAttachmentFlg();
        // キーワード1
        this.keyword1_txt = model.getKeyword1();
        // キーワード2
        this.keyword2_txt = model.getKeyword2();
        // キーワード3
        this.keyword3_txt = model.getKeyword3();
        // キーワード4
        this.keyword4_txt = model.getKeyword4();
        // キーワード5
        this.keyword5_txt = model.getKeyword5();
        // 簡易説明
        this.simplicity_txa = model.getSimpleExplanation();
        // 詳細説明1
        this.detail1_txa = model.getExplanation1();
        // 詳細説明2
        this.detail2_txa = model.getExplanation2();
        // 詳細説明3
        this.detail3_txa = model.getExplanation3();
        // 詳細説明4
        this.detail4_txa = model.getExplanation4();
        // 詳細説明5
        this.detail5_txa = model.getExplanation5();
        // 詳細説明６：ファイル名
        this.detail6_fileName = model.getOldExplanation6Nm();
        // 詳細説明6(PDF)名
        this.detail6_filFileName = model.getOldExplanation6Nm();
        // 詳細説明6(PDF)
        this.detail6_fil = model.getExplanation6();
        // 詳細説明６：削除
        this.detail6FileDel_chkn = model.getDetail6FileDel();
        // レッスン時間
        this.lessonTime = String.valueOf(model.getLessonTime());
        // 提供開始日
        this.utilizationStart_txt = NaikaraStringUtil.converToYYYY_MM_DD(model.getUseStrDt());
        // 提供終了日
        this.utilizationEnd_txt = NaikaraStringUtil.converToYYYY_MM_DD(model.getUseEndDt());
        // 更新前提供開始日
        this.old_utilizationStart_txt = NaikaraStringUtil.converToYYYY_MM_DD(model.getUseStrDt());
        // 該当書籍有無フラグ
        this.existence_rdl = model.getBookFlg();
        // コース別商品
        this.courseGoodsListDtoList = model.getCourseGoodsListDtoList();
        // コース別利用ポイント
        this.courseUsePointMstDtoList = model.getCourseUsePointMstDtoList();
        // 備考
        this.remarks_txa = model.getRemark();
        // レコードバージョン番号
        this.recordVerNo = String.valueOf(model.getRecordVerNo());
    }

    /**
     * 検索の後 Session値 To Action<br>
     * <br>
     * SessionCourseMstGoodsMst値をActionにセット。<br>
     * <br>
     * @param なし<br>
     * @return なし<br>
     * @exception なし
     */
    private void sessionToAction() {
        if ((SessionCourseMstGoodsMst) SessionDataUtil.getSessionData(SessionCourseMstGoodsMst.class.toString()) != null) {

            SessionCourseMstGoodsMst sessionData = (SessionCourseMstGoodsMst) SessionDataUtil
                    .getSessionData(SessionCourseMstGoodsMst.class.toString());

            // コースコード
            this.courseCode_txt = sessionData.getCourseCd();
            // 大分類
            this.courseLarge_sel = sessionData.getBigClassificationCd();
            // 中分類
            this.courseMedium_sel = sessionData.getMiddleClassificationCd();
            // 小分類
            this.courseSmall_sel = sessionData.getSmallClassificationCd();
            // コース名
            this.courseName_txt = sessionData.getCourseJnm();

            // 2014/04/22 Add Start 検索条件の追加(短コース名)対応
            // 短コース名
            this.courseNameShort_txt = sessionData.getCourseJnmShort();
            // 2014/04/22 Add End   検索条件の追加(短コース名)対応

            // コース名 (英語名)
            this.courseNameEng_txt = sessionData.getCourseEnm();
            // 添付付き有無フラグ
            this.tempKbn_rdl = sessionData.getAttachmentFlg();
            // キーワード1
            this.keyword1_txt = sessionData.getKeyword1();
            // キーワード2
            this.keyword2_txt = sessionData.getKeyword2();
            // キーワード3
            this.keyword3_txt = sessionData.getKeyword3();
            // キーワード4
            this.keyword4_txt = sessionData.getKeyword4();
            // キーワード5
            this.keyword5_txt = sessionData.getKeyword5();
            // 簡易説明
            this.simplicity_txa = sessionData.getSimpleExplanation();
            // 詳細説明1
            this.detail1_txa = sessionData.getExplanation1();
            // 詳細説明2
            this.detail2_txa = sessionData.getExplanation2();
            // 詳細説明3
            this.detail3_txa = sessionData.getExplanation3();
            // 詳細説明4
            this.detail4_txa = sessionData.getExplanation4();
            // 詳細説明5
            this.detail5_txa = sessionData.getExplanation5();
            // 詳細説明６：ファイル名
            this.detail6_fileName = sessionData.getOldExplanation6Nm();
            // 詳細説明6(PDF)名
            this.detail6_filFileName = sessionData.getExplanation6Nm();
            // 詳細説明6(PDF)
            this.detail6_fil = sessionData.getExplanation6();
            // 詳細説明６：削除
            this.detail6FileDel_chkn = sessionData.getDetail6FileDel();
            // レッスン時間
            this.lessonTime = String.valueOf(sessionData.getLessonTime());
            // 提供開始日
            this.utilizationStart_txt = NaikaraStringUtil.converToYYYY_MM_DD(sessionData.getUseStrDt());
            // 提供終了日
            this.utilizationEnd_txt = NaikaraStringUtil.converToYYYY_MM_DD(sessionData.getUseEndDt());
            // 更新前提供開始日
            this.old_utilizationStart_txt = sessionData.getOldUseStrDt();
            // 該当書籍有無フラグ
            this.existence_rdl = sessionData.getBookFlg();
            // コース別商品
            this.courseGoodsListDtoList = sessionData.getCourseGoodsListDtoList();
            // 備考
            this.remarks_txa = sessionData.getRemark();
            // レコードバージョン番号
            this.recordVerNo = String.valueOf(sessionData.getRecordVerNo());

            // コース別利用ポイント
            this.courseUsePointMstDtoList = sessionData.getCourseUsePointMstList();

            // 処理区分(前画面情報)
            this.processKbn_rdl = sessionData.getProcessKbn_rdl();
            // 画面表示処理区分
            this.processKbn_txt = sessionData.getProcessKbn_txt();

            // 処理区分＝照会の場合('1':'修正','2':'照会')
            if (StringUtils.equals(PointControlListModel.PROS_KBN_REF, this.processKbn_rdl)) {
                this.setComment(NaikaraTalkConstants.PROCESSKBN_REF_COMMENT);
            }

        }
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
        sessionData.setReturnOnFlg(false);

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
        sessionData.setUseStrDt(this.utilizationStart_txt);
        // 提供終了日
        sessionData.setUseEndDt(this.utilizationEnd_txt);
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

    /**
     * メッセージ情報を取得する。<br>
     * <br>
     * メッセージ情報を取得する。<br>
     * <br>
     * @param なし<br>
     * @return なし<br>
     * @exception NaiException
     */
    private void getAlertMessage() throws NaiException {
        // 提供期間：開始日 が設定されていない
        try {
            this.error_utilizationStart_empty = getMessage("EN0001", new String[] { "提供期間：開始日" });
            // 提供期間：終了日 が設定されていない
            this.error_utilizationEnd_empty = getMessage("EN0001", new String[] { "提供期間：終了日" });
            // 提供期間：開始日 が日付ではない
            this.error_utilizationStart_notDate = getMessage("EN0010", new String[] { "提供期間：開始日" });
            // 提供期間：終了日 が日付ではない
            this.error_utilizationEnd_notDate = getMessage("EN0010", new String[] { "提供期間：終了日" });
            // ｢提供期間：開始日｣　＞　｢提供期間：終了日｣　の場合
            this.err_integrity_date = getMessage("EN0011", new String[] { "提供期間：開始日", "提供期間：終了日" });
        } catch (Exception e) {
            throw new NaiException(e);
        }
    }
}