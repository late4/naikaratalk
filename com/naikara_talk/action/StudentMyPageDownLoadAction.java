package com.naikara_talk.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URLEncoder;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.service.StudentMyPageLoadService;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>お客様(個人)_初期処理<br>
 * <b>クラス名称       :</b>受講者用マイページダウンロードActionクラス<br>
 * <b>クラス概要       :</b>販売商品マスタ．商品ファイルダウンロードAction<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/08/16 TECS 新規作成
 */
public class StudentMyPageDownLoadAction extends StudentMyPageActionSupport {

    private static final long serialVersionUID = 1L;

    /**
     * ダウンロード処理。<br>
     * <br>
     * ダウンロード処理。<br>
     * <br>
     * @param なし<br>
     * @return String SUCCESS<br>
     * @exception NaiException
     */
    public String requestService() throws NaiException {

        // 開始ログ
        log.info(NaikaraStringUtil.unionProcesslog("Start"));

        this.model.setGoodsCd(this.goodsCd);
        StudentMyPageLoadService service = new StudentMyPageLoadService();
        this.model = service.selectGoods(this.model);
        this.goodsFileNm = model.getGoodsFileNm();
        baos = new ByteArrayOutputStream();
        try {
            byte[] inDataByte = model.getGoodsFile().getBytes(1, (int) model.getGoodsFile().length());
            if (inDataByte != null) {
                if (inDataByte.length > 0) {
                    baos.write(inDataByte);
                } else {
                    baos.write(0);
                }
            }
        } catch (Exception e) {
            throw new NaiException(e);
        }
        return SUCCESS;
    }

    /**
     * ファイルのダウンロード。<br>
     * <br>
     * ファイルのダウンロード。<br>
     * <br>
     * @param なし<br>
     * @return InputStream<br>
     * @exception Exception
     */
    public InputStream getInputStream() {

        try {
            if (this.request.getHeader(NaikaraTalkConstants.USER_AGENT).toLowerCase()
                    .indexOf(NaikaraTalkConstants.MSIE) > 0) {

                this.goodsFileNm = URLEncoder.encode(goodsFileNm, NaikaraTalkConstants.UTF_8);

            } else {
                this.goodsFileNm = new String(this.goodsFileNm.getBytes(NaikaraTalkConstants.UTF_8),
                        NaikaraTalkConstants.ISO8859_1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ByteArrayInputStream(baos.toByteArray());
    }

    private ByteArrayOutputStream baos;
}