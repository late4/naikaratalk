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
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>入金管理ページ<br>
 * <b>クラス名称　　　:</b>入金管理ページクラス。<br>
 * <b>クラス概要　　　:</b>入金管理ページCSV出力集計ActionAction。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/08/09 TECS 新規作成。
 */

public class SalesManagedListCsvCountOutputAction extends SalesManagedListActionSupport{

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
                    .getList(NaikaraTalkConstants.CODE_CATEGORY_PAYMENT_MANAG_COUNT);
            // 格納先を取得
            String filePath = csvOutputList.get(NaikaraTalkConstants.PAYMENT_MANAG_COUNT_CSV_OUTPUT).getManagerNm();
            StringBuffer sb = new StringBuffer();
            sb.append(filePath.replace("\\", NaikaraTalkConstants.DATE_SEPARATOR))
                    .append(NaikaraTalkConstants.DATE_SEPARATOR).append(fileNameCount);

            if (this.request.getHeader(NaikaraTalkConstants.USER_AGENT).toLowerCase()
                    .indexOf(NaikaraTalkConstants.MSIE) > 0) {
                this.fileNameCount = URLEncoder.encode(fileNameCount, NaikaraTalkConstants.UTF_8);
            } else {
                this.fileNameCount = new String(this.fileNameCount.getBytes(NaikaraTalkConstants.UTF_8),
                        NaikaraTalkConstants.ISO8859_1);
            }

            return new FileInputStream(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
