package com.naikara_talk.action;

import com.naikara_talk.dto.CourseUsePointListDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.service.SComCoursePointLoadService;
import com.naikara_talk.util.DateUtil;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>お客様(個人)_初期登録。<br>
 * <b>クラス名称       :</b>コース別ポイント初期処理Actionクラス。<br>
 * <b>クラス概要       :</b>コース別ポイントの一覧表示を行う<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/08/06 TECS 新規作成
 * <b>                 :</b>2014/04/22 TECS 検索条件：希望日のコントロールをテキストからプルダウンへ変更対応
 * <b>                 :</b>2014/04/22 TECS 検索条件：コース名の追加対応
 */

public class SComCoursePointLoadAction extends SComCoursePointActionSupport {

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

        // コースマスタ、コース別利用ポイントマスタのデータ取得処理
        try {
            courseLoad();
        } catch (Exception e) {
            throw new NaiException(e);
        }

        return SUCCESS;
    }

    /**
     * コースマスタ、コース別利用ポイントマスタのデータ取得処理<br>
     * <br>
     * コースマスタ、コース別利用ポイントマスタのデータ取得処理<br>
     * <br>
     * @param なし<br>
     * @return なし <br>
     * @exception Exception
     */
    private void courseLoad() throws Exception {

        // 表示データを取得する
        SComCoursePointLoadService service = new SComCoursePointLoadService();

        // 2014/04/22 Upd Start 希望日をテキストからプルダウンへ変更対応
        // システム日付
        //this.setSysDate(NaikaraStringUtil.converToYYYY_MM_DD(DateUtil.getSysDate()));
        this.setHopeDt_sel(NaikaraStringUtil.converToYYYY_MM_DD(DateUtil.getSysDate()));
        // 2014/04/22 Upd End   希望日をテキストからプルダウンへ変更対応

        // 2014/04/22 Add Start コース名の追加対応
        // ◆希望日、コース名(大分類)、コース名(中分類)、コース名(小分類)
        initSelect();

        // コース名(コース名⇒キーワード)
        this.setSearchKeyword_txt(NaikaraTalkConstants.BRANK);
        // 2014/04/22 Add End   コース名の追加対応

        // 検索部のパラメータをモデルにセット
        setupSearchModel();

        // コースマスタ、コース別利用ポイントマスタのデータ取得処理
        this.model.setResultList(service.select(this.model));

        // 取得したデータの件数分を繰り返す、付帯情報を付加する
        for (CourseUsePointListDto cupDto : this.model.getResultList()) {

            // コース単位の該当商品の取得処理
            cupDto.setCourseGoodsListDto(service.selectGoods(cupDto.getCourseCd()));

        }
    }
}
