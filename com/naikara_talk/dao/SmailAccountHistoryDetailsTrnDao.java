package com.naikara_talk.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.log4j.Logger;

import com.naikara_talk.dto.SmailAccountHistoryDetailsTrnDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称:</b>共通部品 DAOクラス<br>
 * <b>クラス名称　　　:</b>スクールメール・アカウント変更履歴明細テーブルデータアクセスクラスクラス<br>
 * <b>クラス概要　　　:</b>スクールメール・アカウント変更履歴明細テーブルの登録を行う<br>
 * <br>
 * <b>著作権　　　　　:</b>Copyright (C) 2013 NAI, Inc. All rights reserved.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/12/29 TECS 新規作成
 */
public class SmailAccountHistoryDetailsTrnDao extends AbstractDao {

    protected Logger log = Logger.getLogger(this.getClass());


    // コネクション変数
    public Connection conn = null;

    // コンストラクタ
    public SmailAccountHistoryDetailsTrnDao(Connection con) {
        this.conn = con;
    }

    /**
     * スクールメール・アカウント変更履歴明細テーブルのデータ取得<br>
     * <br>
     * スクールメール・アカウント変更履歴明細テーブルの条件に一致する連番の最大値を戻り値で返却する<br>
     * <br>
     * @param conditions 検索条件<br>
     * @param orderConditions 並び順条件<br>
     * @return int 最大値<br>
     * @exception NaiException
     */
    public int searchMaxSeq(Timestamp sendDttm) throws NaiException {

        int rtn = 0;
        ResultSet res = null;

        // スクールメール・アカウント変更履歴明細テーブルの連番の最大値の取得
        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT ");
        sb.append("   MAX(SEND_DTTM_SEQ) MAX_SEQ");            // 連番
        sb.append(" FROM ");
        sb.append("   SMAIL_ACCOUNT_HISTORY_DETAILS_TRN ");    // スクールメール・アカウント変更履歴明細テーブル
        sb.append(" WHERE ");
        sb.append("   SEND_DTTM = ?");

        try {

            // PreparedStatementの作成
            PreparedStatement ps = conn.prepareStatement(sb.toString());
            ps.setTimestamp(1, sendDttm);

            // SQL文の実行
            res = ps.executeQuery();

            while (res.next()) {
                rtn = res.getInt("MAX_SEQ");    // 連番の最大値
            }

            // 返却値
            return rtn;

        } catch (SQLException se) {
            log.info(se);
            throw new NaiException(se);
        } finally {
            try {
                if (res != null) {
                    res.close();
                }
            } catch (SQLException e) {
                log.info(e);
                e.printStackTrace();
                throw new NaiException(e);
            }
        }
    }

    /**
     * スクールメール・アカウント変更履歴明細テーブル登録処理<br>
     * <br>
     * スクールメール・アカウント変更履歴明細テーブルの登録を行う<br>
     * <br>
     * @param dto 登録データ
     * @return seq 登録時の連番
     * @exception NaiException
     */
    public int insert(SmailAccountHistoryDetailsTrnDto dto) throws NaiException {

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        int seq = NaikaraTalkConstants.RETURN_CD_ERR_INS; // 登録データ件数

        StringBuffer sb = new StringBuffer();

        // データ登録処理
        sb.append("insert into ");
        sb.append(" SMAIL_ACCOUNT_HISTORY_DETAILS_TRN ");
        sb.append(" ( ");
        sb.append(" SEND_DTTM ");                             // 01.送信日時
        sb.append(" ,SEND_DTTM_SEQ ");                        // 02.連番
        sb.append(" ,STUDENT_ID ");                           // 03.受講者ID
        sb.append(" ,B_FAMILY_JNM ");                         // 04.変更前：名前(姓)
        sb.append(" ,B_FIRST_JNM ");                          // 05.変更前：名前(名)
        sb.append(" ,B_FAMILY_KNM ");                         // 06.変更前：フリガナ(姓)
        sb.append(" ,B_FIRST_KNM ");                          // 07.変更前：フリガナ(名)
        sb.append(" ,B_FAMILY_ROMAJI ");                      // 08.変更前：ローマ字(姓)
        sb.append(" ,B_FIRST_ROMAJI ");                       // 09.変更前：ローマ字(名)
        sb.append(" ,B_NICK_NM ");                            // 10.変更前：ニックネーム
        sb.append(" ,B_PASSWORD ");                           // 11.変更前：パスワード
        sb.append(" ,B_TEL1 ");                               // 12.変更前：電話番号1
        sb.append(" ,B_TEL2 ");                               // 13.変更前：電話番号2
        sb.append(" ,B_BIRTH_DT ");                           // 14.変更前：生年月日
        sb.append(" ,B_ZIP_CD ");                             // 15.変更前：郵便番号
        sb.append(" ,B_ADDRESS_AREA_CD ");                    // 16.変更前：住所(地域)コード
        sb.append(" ,B_ADDRESS_AREA_JNM ");                   // 17.変更前：住所(地域)名
        sb.append(" ,B_ADDRESS_PREFECTURE_CD ");              // 18.変更前：住所(都道府県)コード
        sb.append(" ,B_ADDRESS_PREFECTURE_JNM ");             // 19.変更前：住所(都道府県)名
        sb.append(" ,B_ADDRESS_CITY ");                       // 20.変更前：住所(市区町村 等)
        sb.append(" ,B_ADDRESS_OTHERS ");                     // 21.変更前：住所(ﾋﾞﾙ、ﾏﾝｼｮﾝ名 等)
        sb.append(" ,B_GENDER_KBN ");                         // 22.変更前：性別区分
        sb.append(" ,B_GENDER_JNM ");                         // 23.変更前：性別名
        sb.append(" ,B_MAIL_ADDRESS1 ");                      // 24.変更前：メールアドレス1
        sb.append(" ,B_MAIL_ADDRESS2 ");                      // 25.変更前：メールアドレス2
        sb.append(" ,B_MAIL_ADDRESS3 ");                      // 26.変更前：メールアドレス3
        sb.append(" ,B_GUARDIAN_FAMILY_JNM ");                // 27.変更前：保護者：名前(姓)
        sb.append(" ,B_GUARDIAN_FIRST_JNM ");                 // 28.変更前：保護者：名前(名）
        sb.append(" ,B_GUARDIAN_FAMILY_KNM ");                // 29.変更前：保護者：フリガナ(姓)
        sb.append(" ,B_GUARDIAN_FIRST_KNM ");                 // 30.変更前：保護者：フリガナ(名）
        sb.append(" ,B_GUARDIAN_FAMILY_RELATIONSHIP ");       // 31.変更前：あなたとの続柄
        sb.append(" ,B_GUARDIAN_TEL1 ");                      // 32.変更前：保護者：電話番号1
        sb.append(" ,B_GUARDIAN_TEL2 ");                      // 33.変更前：保護者：電話番号2
        sb.append(" ,B_GUARDIAN_BIRTH_DT ");                  // 34.変更前：保護者：生年月日
        sb.append(" ,B_GUARDIAN_ZIP_CD ");                    // 35.変更前：保護者：郵便番号
        sb.append(" ,B_GUARDIAN_ADDRESS_AREA_CD ");           // 36.変更前：保護者：住所(地域)コード
        sb.append(" ,B_GUARDIAN_ADDRESS_AREA_JNM ");          // 37.変更前：保護者：住所(地域)名
        sb.append(" ,B_GUARDIAN_ADDRESS_PREFECTURE_CD ");     // 38.変更前：保護者：住所(都道府県)コード
        sb.append(" ,B_GUARDIAN_ADDRESS_PREFECTURE_JNM ");    // 39.変更前：保護者：住所(都道府県)名
        sb.append(" ,B_GUARDIAN_ADDRESS_CITY ");              // 40.変更前：保護者：住所(市区町村 等)
        sb.append(" ,B_GUARDIAN_ADDRESS_OTHERS ");            // 41.変更前：保護者：住所(ﾋﾞﾙ、ﾏﾝｼｮﾝ名 等)
        sb.append(" ,B_GUARDIAN_GENDER_KBN ");                // 42.変更前：保護者：性別区分
        sb.append(" ,B_GUARDIAN_GENDER_JNM ");                // 43.変更前：保護者：性別名
        sb.append(" ,B_GUARDIAN_MAIL_ADDRESS1 ");             // 44.変更前：保護者：メールアドレス1
        sb.append(" ,B_GUARDIAN_MAIL_ADDRESS2 ");             // 45.変更前：保護者：メールアドレス2
        sb.append(" ,B_GUARDIAN_MAIL_ADDRESS3 ");             // 46.変更前：保護者：メールアドレス3
        sb.append(" ,A_FAMILY_JNM ");                         // 47.変更後：名前(姓)
        sb.append(" ,A_FIRST_JNM ");                          // 48.変更後：名前(名)
        sb.append(" ,A_FAMILY_KNM ");                         // 49.変更後：フリガナ(姓)
        sb.append(" ,A_FIRST_KNM ");                          // 50.変更後：フリガナ(名)
        sb.append(" ,A_FAMILY_ROMAJI ");                      // 51.変更後：ローマ字(姓)
        sb.append(" ,A_FIRST_ROMAJI ");                       // 52.変更後：ローマ字(名)
        sb.append(" ,A_NICK_NM ");                            // 53.変更後：ニックネーム
        sb.append(" ,A_PASSWORD ");                           // 54.変更後：パスワード
        sb.append(" ,A_TEL1 ");                               // 55.変更後：電話番号1
        sb.append(" ,A_TEL2 ");                               // 56.変更後：電話番号2
        sb.append(" ,A_BIRTH_DT ");                           // 57.変更後：生年月日
        sb.append(" ,A_ZIP_CD ");                             // 58.変更後：郵便番号
        sb.append(" ,A_ADDRESS_AREA_CD ");                    // 59.変更後：住所(地域)コード
        sb.append(" ,A_ADDRESS_AREA_JNM ");                   // 60.変更後：住所(地域)名
        sb.append(" ,A_ADDRESS_PREFECTURE_CD ");              // 61.変更後：住所(都道府県)コード
        sb.append(" ,A_ADDRESS_PREFECTURE_JNM ");             // 62.変更後：住所(都道府県)名
        sb.append(" ,A_ADDRESS_CITY ");                       // 63.変更後：住所(市区町村 等)
        sb.append(" ,A_ADDRESS_OTHERS ");                     // 64.変更後：住所(ﾋﾞﾙ、ﾏﾝｼｮﾝ名 等)
        sb.append(" ,A_GENDER_KBN ");                         // 65.変更後：性別区分
        sb.append(" ,A_GENDER_JNM ");                         // 66.変更後：性別名
        sb.append(" ,A_MAIL_ADDRESS1 ");                      // 67.変更後：メールアドレス1
        sb.append(" ,A_MAIL_ADDRESS2 ");                      // 68.変更後：メールアドレス2
        sb.append(" ,A_MAIL_ADDRESS3 ");                      // 69.変更後：メールアドレス3
        sb.append(" ,A_GUARDIAN_FAMILY_JNM ");                // 70.変更後：保護者：名前(姓)
        sb.append(" ,A_GUARDIAN_FIRST_JNM ");                 // 71.変更後：保護者：名前(名）
        sb.append(" ,A_GUARDIAN_FAMILY_KNM ");                // 72.変更後：保護者：フリガナ(姓)
        sb.append(" ,A_GUARDIAN_FIRST_KNM ");                 // 73.変更後：保護者：フリガナ(名）
        sb.append(" ,A_GUARDIAN_FAMILY_RELATIONSHIP ");       // 74.変更後：あなたとの続柄
        sb.append(" ,A_GUARDIAN_TEL1 ");                      // 75.変更後：保護者：電話番号1
        sb.append(" ,A_GUARDIAN_TEL2 ");                      // 76.変更後：保護者：電話番号2
        sb.append(" ,A_GUARDIAN_BIRTH_DT ");                  // 77.変更後：保護者：生年月日
        sb.append(" ,A_GUARDIAN_ZIP_CD ");                    // 78.変更後：保護者：郵便番号
        sb.append(" ,A_GUARDIAN_ADDRESS_AREA_CD ");           // 79.変更後：保護者：住所(地域)コード
        sb.append(" ,A_GUARDIAN_ADDRESS_AREA_JNM ");          // 80.変更後：保護者：住所(地域)名
        sb.append(" ,A_GUARDIAN_ADDRESS_PREFECTURE_CD ");     // 81.変更後：保護者：住所(都道府県)コード
        sb.append(" ,A_GUARDIAN_ADDRESS_PREFECTURE_JNM ");    // 82.変更後：保護者：住所(都道府県)名
        sb.append(" ,A_GUARDIAN_ADDRESS_CITY ");              // 83.変更後：保護者：住所(市区町村 等)
        sb.append(" ,A_GUARDIAN_ADDRESS_OTHERS ");            // 84.変更後：保護者：住所(ﾋﾞﾙ、ﾏﾝｼｮﾝ名 等)
        sb.append(" ,A_GUARDIAN_GENDER_KBN ");                // 85.変更後：保護者：性別区分
        sb.append(" ,A_GUARDIAN_GENDER_JNM ");                // 86.変更後：保護者：性別名
        sb.append(" ,A_GUARDIAN_MAIL_ADDRESS1 ");             // 87.変更後：保護者：メールアドレス1
        sb.append(" ,A_GUARDIAN_MAIL_ADDRESS2 ");             // 88.変更後：保護者：メールアドレス2
        sb.append(" ,A_GUARDIAN_MAIL_ADDRESS3 ");             // 89.変更後：保護者：メールアドレス3
        sb.append(" ,RECORD_VER_NO ");                        // 90.レコードバージョン番号
        sb.append(" ,INSERT_DTTM ");                          // 91.登録日時
        sb.append(" ,INSERT_CD ");                            // 92.登録者コード
        sb.append(" ,UPDATE_DTTM ");                          // 93.更新日時
        sb.append(" ,UPDATE_CD ");                            // 94.更新者コード
        sb.append(" ) value ( ");
        sb.append("  ? ");                                    // 01.SEND_DTTM
        sb.append(" ,? ");                                    // 02.SEND_DTTM_SEQ
        sb.append(" ,? ");                                    // 03.STUDENT_ID
        sb.append(" ,? ");                                    // 04.B_FAMILY_JNM
        sb.append(" ,? ");                                    // 05.B_FIRST_JNM
        sb.append(" ,? ");                                    // 06.B_FAMILY_KNM
        sb.append(" ,? ");                                    // 07.B_FIRST_KNM
        sb.append(" ,? ");                                    // 08.B_FAMILY_ROMAJI
        sb.append(" ,? ");                                    // 09.B_FIRST_ROMAJI
        sb.append(" ,? ");                                    // 10.B_NICK_NM
        sb.append(" ,? ");                                    // 11.B_PASSWORD
        sb.append(" ,? ");                                    // 12.B_TEL1
        sb.append(" ,? ");                                    // 13.B_TEL2
        sb.append(" ,? ");                                    // 14.B_BIRTH_DT
        sb.append(" ,? ");                                    // 15.B_ZIP_CD
        sb.append(" ,? ");                                    // 16.B_ADDRESS_AREA_CD
        sb.append(" ,? ");                                    // 17.B_ADDRESS_AREA_JNM
        sb.append(" ,? ");                                    // 18.B_ADDRESS_PREFECTURE_CD
        sb.append(" ,? ");                                    // 19.B_ADDRESS_PREFECTURE_JNM
        sb.append(" ,? ");                                    // 20.B_ADDRESS_CITY
        sb.append(" ,? ");                                    // 21.B_ADDRESS_OTHERS
        sb.append(" ,? ");                                    // 22.B_GENDER_KBN
        sb.append(" ,? ");                                    // 23.B_GENDER_JNM
        sb.append(" ,? ");                                    // 24.B_MAIL_ADDRESS1
        sb.append(" ,? ");                                    // 25.B_MAIL_ADDRESS2
        sb.append(" ,? ");                                    // 26.B_MAIL_ADDRESS3
        sb.append(" ,? ");                                    // 27.B_GUARDIAN_FAMILY_JNM
        sb.append(" ,? ");                                    // 28.B_GUARDIAN_FIRST_JNM
        sb.append(" ,? ");                                    // 29.B_GUARDIAN_FAMILY_KNM
        sb.append(" ,? ");                                    // 30.B_GUARDIAN_FIRST_KNM
        sb.append(" ,? ");                                    // 31.B_GUARDIAN_FAMILY_RELATIONSHIP
        sb.append(" ,? ");                                    // 32.B_GUARDIAN_TEL1
        sb.append(" ,? ");                                    // 33.B_GUARDIAN_TEL2
        sb.append(" ,? ");                                    // 34.B_GUARDIAN_BIRTH_DT
        sb.append(" ,? ");                                    // 35.B_GUARDIAN_ZIP_CD
        sb.append(" ,? ");                                    // 36.B_GUARDIAN_ADDRESS_AREA_CD
        sb.append(" ,? ");                                    // 37.B_GUARDIAN_ADDRESS_AREA_JNM
        sb.append(" ,? ");                                    // 38.B_GUARDIAN_ADDRESS_PREFECTURE_CD
        sb.append(" ,? ");                                    // 39.B_GUARDIAN_ADDRESS_PREFECTURE_JNM
        sb.append(" ,? ");                                    // 40.B_GUARDIAN_ADDRESS_CITY
        sb.append(" ,? ");                                    // 41.B_GUARDIAN_ADDRESS_OTHERS
        sb.append(" ,? ");                                    // 42.B_GUARDIAN_GENDER_KBN
        sb.append(" ,? ");                                    // 43.B_GUARDIAN_GENDER_JNM
        sb.append(" ,? ");                                    // 44.B_GUARDIAN_MAIL_ADDRESS1
        sb.append(" ,? ");                                    // 45.B_GUARDIAN_MAIL_ADDRESS2
        sb.append(" ,? ");                                    // 46.B_GUARDIAN_MAIL_ADDRESS3
        sb.append(" ,? ");                                    // 47.A_FAMILY_JNM
        sb.append(" ,? ");                                    // 48.A_FIRST_JNM
        sb.append(" ,? ");                                    // 49.A_FAMILY_KNM
        sb.append(" ,? ");                                    // 50.A_FIRST_KNM
        sb.append(" ,? ");                                    // 51.A_FAMILY_ROMAJI
        sb.append(" ,? ");                                    // 52.A_FIRST_ROMAJI
        sb.append(" ,? ");                                    // 53.A_NICK_NM
        sb.append(" ,? ");                                    // 54.A_PASSWORD
        sb.append(" ,? ");                                    // 55.A_TEL1
        sb.append(" ,? ");                                    // 56.A_TEL2
        sb.append(" ,? ");                                    // 57.A_BIRTH_DT
        sb.append(" ,? ");                                    // 58.A_ZIP_CD
        sb.append(" ,? ");                                    // 59.A_ADDRESS_AREA_CD
        sb.append(" ,? ");                                    // 60.A_ADDRESS_AREA_JNM
        sb.append(" ,? ");                                    // 61.A_ADDRESS_PREFECTURE_CD
        sb.append(" ,? ");                                    // 62.A_ADDRESS_PREFECTURE_JNM
        sb.append(" ,? ");                                    // 63.A_ADDRESS_CITY
        sb.append(" ,? ");                                    // 64.A_ADDRESS_OTHERS
        sb.append(" ,? ");                                    // 65.A_GENDER_KBN
        sb.append(" ,? ");                                    // 66.A_GENDER_JNM
        sb.append(" ,? ");                                    // 67.A_MAIL_ADDRESS1
        sb.append(" ,? ");                                    // 68.A_MAIL_ADDRESS2
        sb.append(" ,? ");                                    // 69.A_MAIL_ADDRESS3
        sb.append(" ,? ");                                    // 70.A_GUARDIAN_FAMILY_JNM
        sb.append(" ,? ");                                    // 71.A_GUARDIAN_FIRST_JNM
        sb.append(" ,? ");                                    // 72.A_GUARDIAN_FAMILY_KNM
        sb.append(" ,? ");                                    // 73.A_GUARDIAN_FIRST_KNM
        sb.append(" ,? ");                                    // 74.A_GUARDIAN_FAMILY_RELATIONSHIP
        sb.append(" ,? ");                                    // 75.A_GUARDIAN_TEL1
        sb.append(" ,? ");                                    // 76.A_GUARDIAN_TEL2
        sb.append(" ,? ");                                    // 77.A_GUARDIAN_BIRTH_DT
        sb.append(" ,? ");                                    // 78.A_GUARDIAN_ZIP_CD
        sb.append(" ,? ");                                    // 79.A_GUARDIAN_ADDRESS_AREA_CD
        sb.append(" ,? ");                                    // 80.A_GUARDIAN_ADDRESS_AREA_JNM
        sb.append(" ,? ");                                    // 81.A_GUARDIAN_ADDRESS_PREFECTURE_CD
        sb.append(" ,? ");                                    // 82.A_GUARDIAN_ADDRESS_PREFECTURE_JNM
        sb.append(" ,? ");                                    // 83.A_GUARDIAN_ADDRESS_CITY
        sb.append(" ,? ");                                    // 84.A_GUARDIAN_ADDRESS_OTHERS
        sb.append(" ,? ");                                    // 85.A_GUARDIAN_GENDER_KBN
        sb.append(" ,? ");                                    // 86.A_GUARDIAN_GENDER_JNM
        sb.append(" ,? ");                                    // 87.A_GUARDIAN_MAIL_ADDRESS1
        sb.append(" ,? ");                                    // 88.A_GUARDIAN_MAIL_ADDRESS2
        sb.append(" ,? ");                                    // 89.A_GUARDIAN_MAIL_ADDRESS3
        sb.append(" ,? ");                                    // 90.RECORD_VER_NO
        sb.append(" ,? ");                                    // 91.INSERT_DTTM
        sb.append(" ,? ");                                    // 92.INSERT_CD
        sb.append(" ,? ");                                    // 93.UPDATE_DTTM
        sb.append(" ,? ");                                    // 94.UPDATE_CD
        sb.append(" ) ");

        try {

            PreparedStatement ps = conn.prepareStatement(sb.toString());

            // 連番の最大値を取得
            seq = this.searchMaxSeq(dto.getSendDttm());
            seq = seq + 1;

            ps.setTimestamp(1, dto.getSendDttm());                       //01.送信日時
            ps.setInt(2, seq);                                           //02.連番
            ps.setString(3, dto.getStudentId());                         //03.受講者ID

            ps.setString(4, dto.getBFamilyJnm());                        //04.変更前：名前(姓)
            ps.setString(5, dto.getBFirstJnm());                         //05.変更前：名前(名)
            ps.setString(6, dto.getBFamilyKnm());                        //06.変更前：フリガナ(姓)
            ps.setString(7, dto.getBFirstKnm());                         //07.変更前：フリガナ(名)
            ps.setString(8, dto.getBFamilyRomaji());                     //08.変更前：ローマ字(姓)
            ps.setString(9, dto.getBFirstRomaji());                      //09.変更前：ローマ字(名)
            ps.setString(10, dto.getBNickNm());                          //10.変更前：ニックネーム
            ps.setString(11, dto.getBPassword());                        //11.変更前：パスワード
            ps.setString(12, dto.getBTel1());                            //12.変更前：電話番号1
            ps.setString(13, dto.getBTel2());                            //13.変更前：電話番号2
            ps.setString(14, dto.getBBirthDt());                         //14.変更前：生年月日
            ps.setString(15, dto.getBZipCd());                           //15.変更前：郵便番号
            ps.setString(16, dto.getBAddressAreaCd());                   //16.変更前：住所(地域)コード
            ps.setString(17, dto.getBAddressAreaJnm());                  //17.変更前：住所(地域)名
            ps.setString(18, dto.getBAddressPrefectureCd());             //18.変更前：住所(都道府県)コード
            ps.setString(19, dto.getBAddressPrefectureJnm());            //19.変更前：住所(都道府県)名
            ps.setString(20, dto.getBAddressCity());                     //20.変更前：住所(市区町村 等)
            ps.setString(21, dto.getBAddressOthers());                   //21.変更前：住所(ﾋﾞﾙ、ﾏﾝｼｮﾝ名 等)
            ps.setString(22, dto.getBGenderKbn());                       //22.変更前：性別区分
            ps.setString(23, dto.getBGenderJnm());                       //23.変更前：性別名
            ps.setString(24, dto.getBMailAddress1());                    //24.変更前：メールアドレス1
            ps.setString(25, dto.getBMailAddress2());                    //25.変更前：メールアドレス2
            ps.setString(26, dto.getBMailAddress3());                    //26.変更前：メールアドレス3
            ps.setString(27, dto.getBGuardianFamilyJnm());               //27.変更前：保護者：名前(姓)
            ps.setString(28, dto.getBGuardianFirstJnm());                //28.変更前：保護者：名前(名）
            ps.setString(29, dto.getBGuardianFamilyKnm());               //29.変更前：保護者：フリガナ(姓)
            ps.setString(30, dto.getBGuardianFirstKnm());                //30.変更前：保護者：フリガナ(名）
            ps.setString(31, dto.getBGuardianFamilyRelationship());      //31.変更前：あなたとの続柄
            ps.setString(32, dto.getBGuardianTel1());                    //32.変更前：保護者：電話番号1
            ps.setString(33, dto.getBGuardianTel2());                    //33.変更前：保護者：電話番号2
            ps.setString(34, dto.getBGuardianBirthDt());                 //34.変更前：保護者：生年月日
            ps.setString(35, dto.getBGuardianZipCd());                   //35.変更前：保護者：郵便番号
            ps.setString(36, dto.getBGuardianAddressAreaCd());           //36.変更前：保護者：住所(地域)コード
            ps.setString(37, dto.getBGuardianAddressAreaJnm());          //37.変更前：保護者：住所(地域)名
            ps.setString(38, dto.getBGuardianAddressPrefectureCd());     //38.変更前：保護者：住所(都道府県)コード
            ps.setString(39, dto.getBGuardianAddressPrefectureJnm());    //39.変更前：保護者：住所(都道府県)名
            ps.setString(40, dto.getBGuardianAddressCity());             //40.変更前：保護者：住所(市区町村 等)
            ps.setString(41, dto.getBGuardianAddressOthers());           //41.変更前：保護者：住所(ﾋﾞﾙ、ﾏﾝｼｮﾝ名 等)
            ps.setString(42, dto.getBGuardianGenderKbn());               //42.変更前：保護者：性別区分
            ps.setString(43, dto.getBGuardianGenderJnm());               //43.変更前：保護者：性別名
            ps.setString(44, dto.getBGuardianMailAddress1());            //44.変更前：保護者：メールアドレス1
            ps.setString(45, dto.getBGuardianMailAddress2());            //45.変更前：保護者：メールアドレス2
            ps.setString(46, dto.getBGuardianMailAddress3());            //46.変更前：保護者：メールアドレス3
            ps.setString(47, dto.getAFamilyJnm());                       //47.変更後：名前(姓)
            ps.setString(48, dto.getAFirstJnm());                        //48.変更後：名前(名)
            ps.setString(49, dto.getAFamilyKnm());                       //49.変更後：フリガナ(姓)
            ps.setString(50, dto.getAFirstKnm());                        //50.変更後：フリガナ(名)
            ps.setString(51, dto.getAFamilyRomaji());                    //51.変更後：ローマ字(姓)
            ps.setString(52, dto.getAFirstRomaji());                     //52.変更後：ローマ字(名)
            ps.setString(53, dto.getANickNm());                          //53.変更後：ニックネーム
            ps.setString(54, dto.getAPassword());                        //54.変更後：パスワード
            ps.setString(55, dto.getATel1());                            //55.変更後：電話番号1
            ps.setString(56, dto.getATel2());                            //56.変更後：電話番号2
            ps.setString(57, dto.getABirthDt());                         //57.変更後：生年月日
            ps.setString(58, dto.getAZipCd());                           //58.変更後：郵便番号
            ps.setString(59, dto.getAAddressAreaCd());                   //59.変更後：住所(地域)コード
            ps.setString(60, dto.getAAddressAreaJnm());                  //60.変更後：住所(地域)名
            ps.setString(61, dto.getAAddressPrefectureCd());             //61.変更後：住所(都道府県)コード
            ps.setString(62, dto.getAAddressPrefectureJnm());            //62.変更後：住所(都道府県)名
            ps.setString(63, dto.getAAddressCity());                     //63.変更後：住所(市区町村 等)
            ps.setString(64, dto.getAAddressOthers());                   //64.変更後：住所(ﾋﾞﾙ、ﾏﾝｼｮﾝ名 等)
            ps.setString(65, dto.getAGenderKbn());                       //65.変更後：性別区分
            ps.setString(66, dto.getAGenderJnm());                       //66.変更後：性別名
            ps.setString(67, dto.getAMailAddress1());                    //67.変更後：メールアドレス1
            ps.setString(68, dto.getAMailAddress2());                    //68.変更後：メールアドレス2
            ps.setString(69, dto.getAMailAddress3());                    //69.変更後：メールアドレス3
            ps.setString(70, dto.getAGuardianFamilyJnm());               //70.変更後：保護者：名前(姓)
            ps.setString(71, dto.getAGuardianFirstJnm());                //71.変更後：保護者：名前(名）
            ps.setString(72, dto.getAGuardianFamilyKnm());               //72.変更後：保護者：フリガナ(姓)
            ps.setString(73, dto.getAGuardianFirstKnm());                //73.変更後：保護者：フリガナ(名）
            ps.setString(74, dto.getAGuardianFamilyRelationship());      //74.変更後：あなたとの続柄
            ps.setString(75, dto.getAGuardianTel1());                    //75.変更後：保護者：電話番号1
            ps.setString(76, dto.getAGuardianTel2());                    //76.変更後：保護者：電話番号2
            ps.setString(77, dto.getAGuardianBirthDt());                 //77.変更後：保護者：生年月日
            ps.setString(78, dto.getAGuardianZipCd());                   //78.変更後：保護者：郵便番号
            ps.setString(79, dto.getAGuardianAddressAreaCd());           //79.変更後：保護者：住所(地域)コード
            ps.setString(80, dto.getAGuardianAddressAreaJnm());          //80.変更後：保護者：住所(地域)名
            ps.setString(81, dto.getAGuardianAddressPrefectureCd());     //81.変更後：保護者：住所(都道府県)コード
            ps.setString(82, dto.getAGuardianAddressPrefectureJnm());    //82.変更後：保護者：住所(都道府県)名
            ps.setString(83, dto.getAGuardianAddressCity());             //83.変更後：保護者：住所(市区町村 等)
            ps.setString(84, dto.getAGuardianAddressOthers());           //84.変更後：保護者：住所(ﾋﾞﾙ、ﾏﾝｼｮﾝ名 等)
            ps.setString(85, dto.getAGuardianGenderKbn());               //85.変更後：保護者：性別区分
            ps.setString(86, dto.getAGuardianGenderJnm());               //86.変更後：保護者：性別名
            ps.setString(87, dto.getAGuardianMailAddress1());            //87.変更後：保護者：メールアドレス1
            ps.setString(88, dto.getAGuardianMailAddress2());            //88.変更後：保護者：メールアドレス2
            ps.setString(89, dto.getAGuardianMailAddress3());            //89.変更後：保護者：メールアドレス3
            ps.setString(90, String.valueOf(dto.getRecordVerNo()));      // 90.レコードバージョン番号
            ps.setString(91, String.valueOf(Timestamp.valueOf(
            		sdf.format(cal.getTime()))));                        // 91.登録日時
            ps.setString(92, dto.getInsertCd());                         // 92.登録者コード
            ps.setString(93, String.valueOf(Timestamp.valueOf(
            		sdf.format(cal.getTime()))));                        // 93.更新日時
            ps.setString(94, dto.getUpdateCd());                         // 94.更新者コード

            // 実行
            ps.executeUpdate();

            // 返却：連番
            return seq;

        } catch (SQLException se) {
            se.printStackTrace();
            throw new NaiException(se);
        }
    }



}
