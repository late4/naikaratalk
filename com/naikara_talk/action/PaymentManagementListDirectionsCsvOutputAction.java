package com.naikara_talk.action;

import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.LinkedHashMap;

import com.naikara_talk.dto.CodeManagMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.mstcache.CodeManagMstCache;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>会社側_管理帳票<br>
 * <b>クラス名称       :</b>講師毎の支払管理表Actionクラス<br>
 * <b>クラス概要       :</b>講師毎の支払管理表CSV出力Action<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/08 TECS 新規作成
 */
public class PaymentManagementListDirectionsCsvOutputAction extends PaymentManagementListDirectionsActionSupport {

    private static final long serialVersionUID = 1L;

    /**
     * CSV出力処理。<br>
     * <br>
     * CSV出力処理。<br>
     * <br>
     * @param なし<br>
     * @return String SUCCESS<br>
     * @exception NaiException
     */
    public String requestService() throws NaiException {
        return SUCCESS;
    }

    /**
     * CSVファイルの出力。<br>
     * <br>
     * CSVファイルの出力。<br>
     * <br>
     * @param なし<br>
     * @return InputStream<br>
     * @exception Exception
     */
    public InputStream getInputStream() {
        try {
            CodeManagMstCache cache = CodeManagMstCache.getInstance();
            LinkedHashMap<String, CodeManagMstDto> csvOutputList = cache
                    .getList(NaikaraTalkConstants.CODE_CATEGORY_PAYMENT_CSV_OUTPUT);
            String filePath = csvOutputList.get(NaikaraTalkConstants.PAYMENT_CSV_OUTPUT).getManagerNm();
            StringBuffer sb = new StringBuffer();
            sb.append(filePath.replace("\\", NaikaraTalkConstants.DATE_SEPARATOR))
                    .append(NaikaraTalkConstants.DATE_SEPARATOR).append(fileName);

            if (this.request.getHeader(NaikaraTalkConstants.USER_AGENT).toLowerCase()
                    .indexOf(NaikaraTalkConstants.MSIE) > 0) {
                this.fileName = URLEncoder.encode(fileName, NaikaraTalkConstants.UTF_8);
            } else {
                this.fileName = new String(this.fileName.getBytes(NaikaraTalkConstants.UTF_8),
                        NaikaraTalkConstants.ISO8859_1);
            }

            return new FileInputStream(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}