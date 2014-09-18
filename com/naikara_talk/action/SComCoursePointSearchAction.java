package com.naikara_talk.action;

import com.naikara_talk.dto.CourseUsePointListDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.model.SComCoursePointModel;
import com.naikara_talk.service.SComCoursePointSearchService;
import com.naikara_talk.util.NaikaraStringUtil;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>お客様(個人)_初期登録。<br>
 * <b>クラス名称       :</b>コース別ポイント検索Actionクラス。<br>
 * <b>クラス概要       :</b>コース別ポイントの一覧の検索Action<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/08/06 TECS 新規作成
 * <b>                 :</b>2014/04/22 TECS 検索条件：希望日のコントロールをテキストからプルダウンへ変更対応
 * <b>                 :</b>2014/04/22 TECS 検索条件：キーワード検索の削除・コース名の追加対応
 */
public class SComCoursePointSearchAction extends SComCoursePointActionSupport {
    private static final long serialVersionUID = 1L;

    /**
     * 検索処理。<br>
     * <br>
     * 検索処理。<br>
     * <br>
     * @param なし<br>
     * @return String SUCCESS<br>
     * @exception NaiException
     */
    public String requestService() throws NaiException {

        // 開始ログ
        log.info(NaikaraStringUtil.unionProcesslog("Start"));

        // 検索条件のModelクラス設定

        // 2014/04/22 Del Start 希望日をテキストからプルダウンへ変更対応
        // レッスン希望日
        ////this.model.setSysDate(this.sysDate);
        ////this.sysDate = NaikaraStringUtil.converToYYYY_MM_DD(this.sysDate);       // 画面.希望日への設定
        //this.hopeDt_sel = NaikaraStringUtil.converToYYYY_MM_DD(this.hopeDt_sel);   // 画面.希望日への設定
        // 2014/04/22 Del End   希望日をテキストからプルダウンへ変更対応

        // 検索部のパラメータをモデルにセット
        setupSearchModel();

        // Serviceクラス生成
        SComCoursePointSearchService service = new SComCoursePointSearchService();

        // 検索前チェック
        int chkResult;
        try {
            chkResult = service.checkPreSelect(model);
            // データ件数がZero件場合、エラーメッセージ設定
            if (chkResult != SComCoursePointModel.CHECK_OK) {
                switch (chkResult) {
                case SComCoursePointSearchService.ERR_ZERO_DATA:
                    this.addActionMessage(getMessage("EC0020", new String[] {}));
                    break;
                }
                return SUCCESS;
            } else {
                // 表示データの取得(コースマスタ、コース別利用ポイントマスタのデータ取得処理)
                this.model.setResultList(service.selectList(this.model));

                // 取得したデータの件数分を繰り返す、付帯情報を付加する
                for (CourseUsePointListDto cupDto : this.model.getResultList()) {

                    // コース単位の該当商品の取得処理
                    cupDto.setCourseGoodsListDto(service.selectGoods(cupDto.getCourseCd()));

                }
            }
        } catch (Exception e) {
            throw new NaiException(e);
        }

        return SUCCESS;
    }
}
