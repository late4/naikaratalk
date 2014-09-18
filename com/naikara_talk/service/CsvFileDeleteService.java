package com.naikara_talk.service;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.List;

import com.naikara_talk.dto.CodeManagMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.mstcache.CodeManagMstCache;
import com.naikara_talk.sessiondata.SessionCsvFileDelete;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>共通部品 Sercice クラス<br>
 * <b>クラス名称       :</b>過去に作成したCSVファイルの削除処理クラス。<br>
 * <b>クラス概要       :</b>過去に作成したCSVファイルの削除処理。<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/11 TECS 新規作成
 */
public class CsvFileDeleteService implements ActionService {

    /**
     * 過去に作成したCSVファイルの削除処理。<br>
     * <br>
     * 過去に作成したCSVファイルの削除処理。<br>
     * <br>
     * @param なし<br>
     * @return なし <br>
     * @exception なし
     */
    public static void removeCsvFile() {

        // SessionData存在
        if ((SessionCsvFileDelete) SessionDataUtil.getSessionData(SessionCsvFileDelete.class.toString()) != null) {
            // ログイン者のログインID取得
            String UserId = ((SessionCsvFileDelete) SessionDataUtil.getSessionData(SessionCsvFileDelete.class
                    .toString())).getUserId();
            // コード種別名取得
            List<String> cdCategory = ((SessionCsvFileDelete) SessionDataUtil.getSessionData(SessionCsvFileDelete.class
                    .toString())).getCdCategory();
            // 汎用フィールド名取得
            List<String> managerCd = ((SessionCsvFileDelete) SessionDataUtil.getSessionData(SessionCsvFileDelete.class
                    .toString())).getManagerCd();
            try {
                for (int i = 0; i < cdCategory.size(); i++) {

                    // 支払管理表CSVファイルの格納先（サーバー）を取得する
                    CodeManagMstCache cache = CodeManagMstCache.getInstance();
                    LinkedHashMap<String, CodeManagMstDto> csvOutputList = cache.getList(cdCategory.get(i));
                    String filePath = csvOutputList.get(managerCd.get(i)).getManagerNm();

                    // 指定フォルダ内に存在し、且つ ファイル名にログイン者のログインIDが入っているファイルを全て削除する
                    File dirFile = new File(filePath);
                    if (!dirFile.exists()) {
                        continue;
                    }

                    File[] files = dirFile.listFiles();
                    for (File file : files) {
                        if (file.getName().contains(UserId)) {
                            file.delete();
                        }
                    }
                }
            } catch (NaiException e) {
                e.printStackTrace();
            }
            SessionDataUtil.clearSessionData(SessionCsvFileDelete.class.toString());
        }
    }
}