package com.naikara_talk.action;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.service.DetailDescriptionLoadService;
import com.naikara_talk.util.NaikaraStringUtil;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>お客様(個人)_初期登録<br>
 * <b>クラス名称　　　:</b>詳細説明Actionクラス。<br>
 * <b>クラス概要　　　:</b>詳細説明の情報を表示。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/08/09 TECS 新規作成。
 */
public class DetailDescriptionLoadAction extends DetailDescriptionActionSupport {

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

        // コースコード
        this.model.setCourseCd(this.courseCd);

        DetailDescriptionLoadService service = new DetailDescriptionLoadService();
        try {
            this.count = service.checkPreSelect(model);
            // エラーの場合、メッセージ設定
            if (count == 0) {
                this.addActionMessage(getMessage("EC0020", new String[] {}));
                return SUCCESS;
            }
        } catch (Exception e) {
            throw new NaiException(e);
        }

        try {
            this.load();
            return SUCCESS;
        } catch (Exception e) {
            throw new NaiException(e);
        }

    }

    /**
     * 初期処理、表示データの取得。<br>
     * <br>
     * 初期処理、表示データの取得。<br>
     * <br>
     * @param なし<br>
     * @return なし <br>
     * @exception Exception
     */
    private void load() throws Exception {

        // 表示データを取得する
        DetailDescriptionLoadService service = new DetailDescriptionLoadService();

        // コースコード
        this.model.setCourseCd(this.courseCd);

        model = service.select(this.model);

        /** 大分類(jsp) */
        this.bigClassificationCdNm = model.getBigClassificationCdNm();
        /** 中分類(jsp) */
        this.middleClassificationCdNm = model.getMiddleClassificationCdNm();
        /** 小分類(jsp) */
        this.smallClassificationCdNm = model.getSmallClassificationCdNm();
        /** コース名(jsp) */
        this.courseJnm = model.getCourseJnm();
        /** 添付付き有無フラグ(jsp) */
        this.attachmentFlg = model.getAttachmentFlg();
        /** レッスン時間 (jsp) */
        this.lessonTime = model.getLessonTime();
        /** 利用開始日 (jsp) */
        this.useStrDt = model.getUseStrDt();
        /** 利用終了日 (jsp) */
        this.useEndDt = model.getUseEndDt();
        /** 該当商品 (jsp) */
        this.goodsNm = model.getGoodsNm();
        /** 詳細説明1 (jsp) */
        this.explanation1 = model.getExplanation1();
        /** 詳細説明2 (jsp) */
        this.explanation2 = model.getExplanation2();
        /** 詳細説明3 (jsp) */
        this.explanation3 = model.getExplanation3();
        /** 詳細説明4 */
        this.explanation4 = model.getExplanation4();
        /** 詳細説明5 */
        this.explanation5 = model.getExplanation5();
        /** 詳細説明6(PDF)名 */
        this.explanation6Nm = model.getExplanation6Nm();
        /** 詳細説明6(PDF) */
        this.explanation6 = model.getExplanation6();

        // コース単位の該当商品の取得処理
        this.model.setResultList(service.selectGoods(this.model.getCourseCd()));

    }
}
