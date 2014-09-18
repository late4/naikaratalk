package com.naikara_talk.util;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

import com.csvreader.CsvWriter;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>CSV共通<br>
 * <b>クラス名称       :</b>CSV共通クラス。<br>
 * <b>クラス概要       :</b>CSV共通。<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/09 TECS 新規作成
 */
public class CsvUtil {

    /**
     * CSVファイルの作成<br>
     * <br>
     * CSVファイルを作成する。<br>
     * <br>
     * @param list データリスト<br>
     * @param filePath ファイルのパス<br>
     * @return なし <br>
     * @exception なし
     */
    public static void createCsvFile(List<List<String>> list, String filePath) {
        CsvWriter cw = null;
        try {
            cw = new CsvWriter(filePath, NaikaraTalkConstants.COMMA, Charset.forName(NaikaraTalkConstants.SJIS));
            cw.setForceQualifier(true);
            for (List<String> lineList : list) {
                String[] ss = new String[lineList.size()];
                cw.writeRecord(lineList.toArray(ss));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (cw != null) {
                cw.close();
            }
        }
    }

}