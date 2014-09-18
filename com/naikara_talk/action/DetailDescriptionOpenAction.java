package com.naikara_talk.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.service.DetailDescriptionLoadService;
import com.naikara_talk.util.NaikaraStringUtil;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>お客様(個人)_初期登録<br>
 * <b>クラス名称       :</b>>詳細説明Actionクラス<br>
 * <b>クラス概要       :</b>>詳細説明ファイル出力Action<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/08/09 TECS 新規作成
 */
public class DetailDescriptionOpenAction extends DetailDescriptionActionSupport {

    private static final long serialVersionUID = 1L;

    /**
     * ファイル出力処理。<br>
     * <br>
     * ファイル出力処理。<br>
     * <br>
     * @param なし<br>
     * @return String SUCCESS<br>
     * @exception NaiException
     */
    public String requestService() throws NaiException {

        // 開始ログ
        log.info(NaikaraStringUtil.unionProcesslog("Start"));

        this.model.setCourseCd(this.courseCd);
        DetailDescriptionLoadService service = new DetailDescriptionLoadService();
        model = service.select(this.model);
        baos = new ByteArrayOutputStream();
        try {
            byte[] inDataByte = model.getExplanation6().getBytes(1, (int) model.getExplanation6().length());
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
     * ファイルの出力。<br>
     * <br>
     * ファイルの出力。<br>
     * <br>
     * @param なし<br>
     * @return InputStream<br>
     * @exception Exception
     */
    public InputStream getInputStream() {

        return new ByteArrayInputStream(baos.toByteArray());
    }

    ByteArrayOutputStream baos = null;
}