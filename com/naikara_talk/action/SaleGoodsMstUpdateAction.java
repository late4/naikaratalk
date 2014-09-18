package com.naikara_talk.action;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dto.GoodsMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.model.SaleGoodsMstListModel;
import com.naikara_talk.mstcache.GoodsMstImgCache;
import com.naikara_talk.service.SaleGoodsMstUpdateService;
import com.naikara_talk.sessiondata.SessionSaleGoodsMst;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;


/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>販売商品マスタメンテナンス(単票)。<br>
 * <b>クラス名称　　　:</b>販売商品マスタメンテナンス登録更新Actionクラス。<br>
 * <b>クラス概要　　　:</b>販売商品マスタメンテナンス登録更新Action。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/06/18 TECS 新規作成。
 */
public class SaleGoodsMstUpdateAction extends SaleGoodsMstActionSupport {

    private static final long serialVersionUID = 1L;

    /** 入力チェックエラー */
    private static final int ERR_CHECK_NG = -1;

    /** 排他エラー */
    private static final int ERR_CHECK_EXCLUSION = -2;

    /** データ存在エラー */
    private static final int ERR_DATA_EXISTENCE = -3;

    /** 追加処理(正常) */
    private static final int INSERT_OK = 1;

    /** 更新処理(正常) */
    private static final int UPDATE_OK = 2;


    /**
     * 登録/更新処理。<br>
     * <br>
     * @param なし
     * @return String
     * @throws NaiException
     */
    public String requestService() throws NaiException {

        // 開始ログ
        log.info(NaikaraStringUtil.unionProcesslog("Start"));

        // 画面遷移先
        String transitionScreen = SUCCESS;    // 初期値の設定

        // 受取方法、商品形態の初期取得
        try {
            this.initRadio();
        } catch (Exception e) {
            throw new NaiException(e);
        }

        // 画面のパラメータをモデルに設定
        this.setupModel();

        // 処理区分＝新規の場合('0':'新規','1':'修正','2':'照会')
        if (StringUtils.equals(SaleGoodsMstListModel.PROS_KBN_ADD, this.processKbn_rdl)) {
            try {
                // 新規処理を実行
                int rtn = this.insert();
                switch(rtn){
                case ERR_CHECK_NG:
                    // 入力チェックエラー
                    transitionScreen = SUCCESS;
                    break;
                case ERR_DATA_EXISTENCE:
                    // データ存在エラー
                    transitionScreen = SUCCESS;
                    break;
                case INSERT_OK:
                    // 追加処理(正常)
                    transitionScreen = NEXTPAGE;
                    break;
                }
            } catch (Exception e) {
                throw new NaiException(e);
            }
        }

        // 処理区分＝修正の場合('0':'新規','1':'修正','2':'照会')
        if (StringUtils.equals(SaleGoodsMstListModel.PROS_KBN_UPD, this.processKbn_rdl)) {
            try {
                // 更新処理を実行
                int rtn = this.update();
                switch(rtn){
                case ERR_CHECK_NG:
                    // 入力チェックエラー
                    transitionScreen = SUCCESS;
                    break;
                case ERR_CHECK_EXCLUSION:
                    // 排他エラー
                    transitionScreen = INPUT;
                    break;
                case UPDATE_OK:
                    // 更新処理(正常)
                    transitionScreen = NEXTPAGE;
                    break;
                }

            } catch (Exception e) {
                throw new NaiException(e);
            }
        }

        if (transitionScreen == NEXTPAGE) {
            // 正常の場合 MoveActionで登録した画面遷移を削除
            try {
                removeLatestActionList();
            } catch (Exception e) {
                throw new NaiException(e);
            }

            //画像キャッシュの内容のリフレッシュ
            GoodsMstImgCache cache = GoodsMstImgCache.getInstance();
            cache.reload();

            //戻る用のsession情報の初期化
            //sessionSessionSaleGoodsMstのクリア
            SessionDataUtil.clearSessionData(SessionSaleGoodsMst.class.toString());
        }

        // 一覧画面を戻る
        return transitionScreen;

    }

    /**
     * 登録処理。<br>
     * <br>
     * @param なし
     * @return int
     * @throws Exception
     */
    private int insert() throws NaiException {

        // 関連チェック
        if (this.errorCheck()) {
            return ERR_CHECK_NG;
        }

        //サービス生成
        SaleGoodsMstUpdateService service = new SaleGoodsMstUpdateService();

        // GoodsMstDtoクラスの生成
        GoodsMstDto dto = new GoodsMstDto();

        // データの取得処理
        dto = service.select(this.model);

        try {

            if (dto != null) {
                if (dto.getReturnCode() == NaikaraTalkConstants.RETURN_CD_DATA_YES) {
                    // データが存在する場合
                    StringBuffer work = new StringBuffer();
                    work.append("(商品コード=");
                    work.append(model.getGoodsCd());
                    work.append(")");

                    this.addActionMessage(getMessage(
                        "EN0019", new String[]
                        { SaleGoodsMstListActionSupport.GOODS_MST_TBL_JNM, work.toString() }));
                    return ERR_DATA_EXISTENCE;
                }
            }

            // 新規処理
            service.insert(this.model);

            // 登録完了メッセージの設定
            this.message = getMessage("IN0010", new String[]
                { SaleGoodsMstListActionSupport.GOODS_MST_TBL_JNM, "" });

            return INSERT_OK;

        } catch (Exception e) {
            throw new NaiException(e);
        }

    }


    /**
     * 更新処理。<br>
     * <br>
     * @param なし
     * @return int
     * @throws Exception
     */
    private int update() throws NaiException {

        // 関連チェック
        if (errorCheck()) {
            return ERR_CHECK_NG;
        }

        //サービス生成
        SaleGoodsMstUpdateService service = new SaleGoodsMstUpdateService();

        // 更新処理のサービス呼び出し
        int cnt = service.update(this.model);

        try {

            if (cnt == 0) {
                // 排他エラーメッセージの設定
                String msg = getMessage("ES0014", new String[] {});
                this.addActionMessage(msg);
                return ERR_CHECK_EXCLUSION;
            }

            // 更新完了メッセージの設定
            this.message = getMessage("IN0011", new String[] { SaleGoodsMstListActionSupport.GOODS_MST_TBL_JNM, "" });

            // 一覧画面を戻る
            return UPDATE_OK;

        } catch (Exception e) {
            throw new NaiException(e);
        }

    }


    /**
     * 登録/更新前チェック。<br>
     * <br>
     * @param なし
     * @return boolean
     * @throws Exception
     */
    private boolean errorCheck() throws NaiException {

		SaleGoodsMstUpdateService service = new SaleGoodsMstUpdateService();

		StringBuffer work = new StringBuffer();

		// 関連チェック
		int chkResult=service.errorCheck(model);

		try {

			switch(chkResult){

	    	// ファイルサイズのチェック(サンプル画像)
			case SaleGoodsMstUpdateService.ERR_IMGPHOTO_FIL:
				this.addActionMessage(getMessage("EN0065", new String[] {"(サンプル画像)65KByte"}));
				return true;

			// ファイルサイズのチェック(商品ファイル)
			case SaleGoodsMstUpdateService.ERR_GOODS_FIL:
				this.addActionMessage(getMessage("EN0065", new String[] {"(商品ファイル)15MByte"}));
				return true;

	    	// ｢受取方法｣＝”1000” (ダウンロード) ｢商品形態｣≧2000 (書籍・その他) の場合
			case SaleGoodsMstUpdateService.ERR_NO_DWL:
				this.addActionMessage(getMessage("EN0002", new String[] {"受取方法","商品形態"}));
				return true;

			// ｢受取方法｣!＝”3000”の場合
			// ｢商品ファイル：削除｣＝ON　｢商品ファイル：ファイル名｣＝””の場合
			case SaleGoodsMstUpdateService.ERR_CHK_OUTSIDE_1:
				this.addActionMessage(getMessage("EN0002", new String[] {"商品ファイル：削除","商品ファイル：ファイル名"}));
				return true;

			// ｢受取方法｣!＝”3000”の場合
			// ｢商品ファイル：削除｣＝OFF　｢商品ファイル名｣＝””　且つ ｢商品ファイル：ファイル名｣≠””の場合
			case SaleGoodsMstUpdateService.ERR_CHK_OUTSIDE_2:
				this.addActionMessage(getMessage("EN0001", new String[] {"商品ファイル：ファイル名"}));
				return true;

			// ｢受取方法｣＝”3000”(外部購入)の場合
			// ｢商品ファイル名｣＝””　且つ ｢商品ファイル：削除｣＝ON の場合
			case SaleGoodsMstUpdateService.ERR_CHK_GOODSHILE_1:
				this.addActionMessage(getMessage("EN0002", new String[] {"商品ファイル：削除","商品ファイル：ファイル名"}));
				return true;

			// ｢受取方法｣＝”3000”(外部購入)の場合
			// ｢商品ファイル名｣＝””　且つ ｢商品ファイル：削除｣＝OFF の場合
			case SaleGoodsMstUpdateService.ERR_CHK_GOODSHILE_2:
				work.setLength(0);
				work.append("受取方法(").append(saleKbn_rdll.get(this.saleKbn_rdl)).append(")");

				this.addActionMessage(getMessage("EN0002", new String[] {
						work.toString(),"商品ファイル：ファイル名" }));
				return true;

			// ｢受取方法｣＝”3000”(外部購入)の場合
			// ｢商品ファイル：ファイル名｣≠””　場合
			case SaleGoodsMstUpdateService.ERR_NO_FILENM:
				work.setLength(0);
				work.append("受取方法(").append(saleKbn_rdll.get(this.saleKbn_rdl)).append(")");

				this.addActionMessage(getMessage("EN0002", new String[] {
						work.toString(),"商品ファイル：ファイル名" }));
				return true;

			// ｢受取方法｣＝”3000”(外部購入)の場合
			// ｢販売価格／ポイント｣ !＝0 の場合
			case SaleGoodsMstUpdateService.ERR_ZERO_PRICE:
				this.addActionMessage(getMessage("EN0056", new String[] {
	                    "受取方法", saleKbn_rdll.get(this.saleKbn_rdl),"｢販売価格／ポイント｣に0を入力" }));
				return true;

			// 日付の整合性チェック (日付)
			case SaleGoodsMstUpdateService.ERR_INTEGRITY_DT:
				this.addActionMessage(getMessage("EN0011", new String[] {"提供期間：開始日", "提供期間：終了日" }));
				return true;
			}

	        return false;

		} catch (Exception e) {
			throw new NaiException(e);
		}

    }


}
