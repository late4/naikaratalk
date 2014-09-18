package com.naikara_talk.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.sql.SQLException;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

//import com.naikara_talk.service.なし;
import com.naikara_talk.sessiondata.SessionUser;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>共通<br>
 * <b>クラス名称       :</b>StringUtilクラス<br>
 * <b>クラス概要       :</b><br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/0X/0X XXXX 新規作成
 *                     :</b>2014/06/02 TECS レッスン予約に関する「応相談」対応
 */
public class NaikaraStringUtil {

    /**
     * コンマ削除。
     *
     * @param String パラメータ
     *
     * @return String 返却値
     */
    public static String delComma(String value) {

        return value == null ? "" : value.replaceAll(",", "");

    }

    /**
     * コンマ削除。
     *
     * @param BigDecimal パラメータ
     *
     * @return BigDecimal 返却値
     */
    public static BigDecimal delComma(BigDecimal value) {

        return value == null ? BigDecimal.ZERO : new BigDecimal(value.toString().replaceAll(",", ""));

    }

    /**
     * スラッシュ削除。
     *
     * @param String パラメータ
     *
     * @return String 返却値
     */
    public static String delSlash(String value) {

        return value == null ? "" : value.replaceAll("/", "");

    }

    /**
     * Blob読取処理。
     *
     * @param String
     * @param Blob
     * @return なし
     * @throws SQLException
     * @throws IOException
     */
    /*
        public static void readBlob(String filePath, Blob file) throws SQLException, IOException {

            // パラメータはnullの場合
            if (file == null) {
                return;
            }

            int size = 0;
            FileOutputStream fos = null;
            // Blob読取
            InputStream is = file.getBinaryStream();
            // ファイル作成
            File fl = new File(filePath);
            try {
                fos = new FileOutputStream(fl);
                while ((size = is.read()) != -1) {
                    fos.write(size);
                }
            } catch (IOException e) {
                throw e;
            } finally {
                is.close();
                fos.close();
            }

        }
    */

    /**
     * コンマ追加
     * @param str パラメータ
     * @return 返却値
     */
    public static String addComma(String str) {
        // マイナス
        boolean neg = false;
        if (str.startsWith("-")) {
            str = str.substring(1);
            neg = true;
        }
        // 小数
        String tail = null;
        if (str.indexOf('.') != -1) { //
            tail = str.substring(str.indexOf('.'));
            str = str.substring(0, str.indexOf('.'));
        }
        StringBuilder sb = new StringBuilder(str);
        sb.reverse();
        for (int i = 3; i < sb.length(); i += 4) {
            sb.insert(i, ',');
        }
        sb.reverse();
        if (neg) {
            sb.insert(0, '-');
        }
        if (tail != null) {
            sb.append(tail);
        }
        return sb.toString();
    }

    /**
     * 全角コンマ追加
     * @param str パラメータ
     * @return 返却値
     */
    public static String addZenkakuComma(String str) {
        // マイナス
        boolean neg = false;
        if (str.startsWith("-")) {
            str = str.substring(1);
            neg = true;
        }
        // 小数
        String tail = null;
        if (str.indexOf('.') != -1) { //
            tail = str.substring(str.indexOf('.'));
            str = str.substring(0, str.indexOf('.'));
        }
        StringBuilder sb = new StringBuilder(str);
        sb.reverse();
        for (int i = 3; i < sb.length(); i += 4) {
            sb.insert(i, '，');
        }
        sb.reverse();
        if (neg) {
            sb.insert(0, '-');
        }
        if (tail != null) {
            sb.append(tail);
        }
        return sb.toString();
    }

    /**
     * 支払年月作成(yyyy年MM月)。<br>
     * <br>
     * 支払年月作成(yyyy年MM月)。<br>
     * <br>
     * @param String yyyyMM <br>
     * @return String yyyy年MM月 <br>
     * @exception なし
     */
    public static String converToYYYY_Year_MM_Month(String value) {

        if (StringUtils.isEmpty(value)) {
            return NaikaraTalkConstants.BRANK;
        }

        int i = value.indexOf(NaikaraTalkConstants.DATE_SEPARATOR);
        if (i > 0) {
            String[] array = value.split(NaikaraTalkConstants.DATE_SEPARATOR);
            String year = array[0];
            String month = array[1];
            if (year.length() == 2) {
                StringBuffer sbY = new StringBuffer();
                sbY = sbY.append("20").append(year);
                year = sbY.toString();
            }
            if (month.length() == 1) {
                StringBuffer sbM = new StringBuffer();
                sbM = sbM.append("0").append(month);
                month = sbM.toString();
            }
            StringBuffer sbYM = new StringBuffer();
            sbYM.append(year).append("年").append(month).append("月");
            return sbYM.toString();
        }

        return value;
    }

    /**
     * 発生年月変換(yyyy/MM)。<br>
     * <br>
     * 発生年月変換(yyyyMMdd,yyyyMM ⇒ yyyy/MM)。<br>
     * <br>
     * @param String パラメータ <br>
     * @return String yyyy/MM <br>
     * @exception なし
     */
    public static String converToYYYY_MM(String value) {

        if (StringUtils.isEmpty(value)) {
            return NaikaraTalkConstants.BRANK;
        }

        int i = value.length();
        if (i == 6 || i == 8) {
            String year;
            String month;

            year = value.substring(0, 4);
            month = value.substring(4, 6);

            StringBuffer reYyyyMm = new StringBuffer();
            reYyyyMm.append(year).append(NaikaraTalkConstants.DATE_SEPARATOR).append(month);
            return reYyyyMm.toString();
        }

        return value;

    }

    /**
     * 発生月日(MM/dd)。<br>
     * <br>
     * 発生月日変換(yyyyMMdd ⇒ MM/dd)。<br>
     * <br>
     * @param String yyyyMMdd <br>
     * @return String MM/dd <br>
     * @exception なし
     */
    public static String converToMM_DD(String value) {

        if (StringUtils.isEmpty(value)) {
            return NaikaraTalkConstants.BRANK;
        }

        int i = value.length();
        if (i == 8) {
            String month;
            String day;

            month = value.substring(4, 6);
            day = value.substring(6, 8);

            StringBuffer sbMD = new StringBuffer();
            sbMD.append(month).append(NaikaraTalkConstants.DATE_SEPARATOR).append(day);
            return sbMD.toString();
        }

        return value;
    }

    /**
     * 発生月日(M/d)。<br>
     * <br>
     * 発生月日変換(yyyyMMdd ⇒ M/d)。<br>
     * <br>
     * @param String yyyyMMdd <br>
     * @return String M/d <br>
     * @exception なし
     */
    public static String converToM_D(String value) {

        if (StringUtils.isEmpty(value)) {
            return NaikaraTalkConstants.BRANK;
        }

        int i = value.length();
        if (i == 8) {
            String month;
            String day;

            month = value.substring(4, 6);
            if (month.charAt(0) == '0') {
                month = value.substring(5, 6);
            }

            day = value.substring(6, 8);
            if (day.charAt(0) == '0') {
                day = value.substring(7, 8);
            }

            StringBuffer sbMD = new StringBuffer();
            sbMD.append(month).append(NaikaraTalkConstants.DATE_SEPARATOR).append(day);
            return sbMD.toString();
        }

        return value;
    }

    /**
     * Dateフォマット変換
     * yyyy/MM/dd,yy/MM/dd,yyyy/M/d ⇒ yyyyMMdd
     * @param value パラメータ
     * @return 返却値
     */
    public static String converToYYYYMMDD(String value) {

        if (StringUtils.isEmpty(value)) {
            return "";
        }

        int i = value.indexOf("/");
        if (i > 0) {
            String[] array = value.split("/");
            String year = array[0];
            String month = array[1];
            String date = array[2];
            if (year.length() == 2) {
                StringBuffer sbY = new StringBuffer();
                sbY = sbY.append("20").append(year);
                year = sbY.toString();
            }
            if (month.length() == 1) {
                StringBuffer sbM = new StringBuffer();
                sbM = sbM.append("0").append(month);
                month = sbM.toString();
            }
            if (date.length() == 1) {
                StringBuffer sbD = new StringBuffer();
                sbD = sbD.append("0").append(date);
                date = sbD.toString();
            }
            StringBuffer sbYMD = new StringBuffer();
            sbYMD = sbYMD.append(year).append(month).append(date);
            return sbYMD.toString();
        }

        return value;
    }

    /**
     * Dateフォマット変換
     * yyyy/MM,yy/MM,yyyy/M ⇒ yyyyMM
     * @param value パラメータ
     * @return 返却値
     */
    public static String converToYYYYMM(String value) {

        if (StringUtils.isEmpty(value)) {
            return NaikaraTalkConstants.BRANK;
        }

        int i = value.indexOf("/");
        if (i > 0) {
            String[] array = value.split("/");
            String year = array[0];
            String month = array[1];
            if (year.length() == 2) {
                StringBuffer sbY = new StringBuffer();
                sbY = sbY.append("20").append(year);
                year = sbY.toString();
            }
            if (month.length() == 1) {
                StringBuffer sbM = new StringBuffer();
                sbM = sbM.append("0").append(month);
                month = sbM.toString();
            }
            StringBuffer sbYM = new StringBuffer();
            sbYM = sbYM.append(year).append(month);
            return sbYM.toString();
        }

        return value;
    }

    /**
     * Dateフォマット変換
     * yyyyMMdd ⇒ yyyy/MM/dd
     * @param value パラメータ
     * @return 返却値
     */
    public static String converToYYYY_MM_DD(String value) {

        if (StringUtils.isEmpty(value)) {
            return "";
        }

        int i = value.length();
        if (i == 8) {
            String year;
            String month;
            String date;

            year = value.substring(0, 4);
            month = value.substring(4, 6);
            date = value.substring(6, 8);

            StringBuffer sbYMD = new StringBuffer();
            sbYMD = sbYMD.append(year).append("/").append(month).append("/").append(date);
            return sbYMD.toString();
        }

        return value;
    }

    /**
     * Dateフォマット変換
     * yyyyMMdd ⇒ yyyy-MM-dd
     * @param value パラメータ
     * @return 返却値
     */
    public static String addDateHyphen(String value) {

        if (StringUtils.isEmpty(value)) {
            return "";
        }

        int i = value.length();
        if (i == 8) {
            String year;
            String month;
            String date;

            year = value.substring(0, 4);
            month = value.substring(4, 6);
            date = value.substring(6, 8);

            StringBuffer sbYMD = new StringBuffer();
            sbYMD = sbYMD.append(year).append("-").append(month).append("-").append(date);
            return sbYMD.toString();
        }

        return value;
    }

    // 2014/06/02 レッスン予約に関する「応相談」対応 Add Start
    /**
     * Dateフォマット変換
     * yyyyMMdd ⇒ yyyy年M月d日
     * @param value パラメータ
     * @return 返却値
     */
    public static String converToYYYYMD(String value) {

        if (StringUtils.isEmpty(value)) {
            return "";
        }

        int i = value.length();
        if (i == 8) {
            String year;
            String month;
            String date;
            String wkMonth1;
            String wkDate1;

            year = value.substring(0, 4);
            month = value.substring(4, 6);
            date = value.substring(6, 8);

            wkMonth1 = value.substring(4, 5);
            if (StringUtils.equals(wkMonth1, "0")) {
                month = value.substring(5, 6);
            }

            wkDate1 = value.substring(6, 7);
            if (StringUtils.equals(wkDate1, "0")) {
                date = value.substring(7, 8);
            }

            StringBuffer sbYMD = new StringBuffer();
            sbYMD = sbYMD.append(year).append("年").append(month).append("月").append(date).append("日");
            return sbYMD.toString();
        }

        return value;
    }

    /**
     * レッスン時刻コードフォマット変換
     * HH:MM-HH:MM ⇒ HH:MM ※前半のHH:MM
     * @param string tm パラメータ
     * @return string 返却値
     */
    public static String converToHHMM(String tm) {

        if (StringUtils.isEmpty(tm)) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        String hyphen = "-";
        int j = tm.indexOf(hyphen);
        if (j > 0) {
            String[] array = tm.split(hyphen);
            String starttm = array[0];
            if (starttm != null) {
                sb.append(starttm);
            }
        }

        return sb.toString();
    }
    // 2014/06/02 レッスン予約に関する「応相談」対応 Add End


    /**
     * Dateフォマット変換
     * yy/MM/dd ⇒ dd/MM/yy
     * @param string dt パラメータ
     * @return string 返却値
     */
    public static String converToDDMMYY(String dt) {

        if (StringUtils.isEmpty(dt)) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        String slash = "/";
        int i = dt.indexOf(slash);
        if (i > 0) {
            String[] array = dt.split(slash);
            String year = array[0];
            String month = array[1];
            String date = array[2];
            if (date != null) {
                sb.append(date);
            }
            if (month != null) {
                sb.append(slash);
                sb.append(month);
            }
            if (year != null) {
                sb.append(slash);
                sb.append(year);
            }
            return sb.toString();
        }

        return dt;
    }

    /**
     * Dateフォマット変換
     * yy/MM/dd,HH:MM ⇒ dd/MM/yy HH:MM
     * @param string dt パラメータ
     * @param string tm パラメータ
     * @return string 返却値
     */
    public static String converToDDMMYYHHMM(String dt, String tm) {

        if (StringUtils.isEmpty(dt)) {
            return "";
        }

        if (StringUtils.isEmpty(tm)) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        String blank = " ";
        String slash = "/";
        String hyphen = "-";
        int i = dt.indexOf(slash);
        if (i > 0) {
            String[] array = dt.split(slash);
            String year = array[0];
            String month = array[1];
            String date = array[2];
            if (date != null) {
                sb.append(date);
            }
            if (month != null) {
                sb.append(slash);
                sb.append(month);
            }
            if (year != null) {
                sb.append(slash);
                sb.append(year);
            }
        }
        int j = tm.indexOf(hyphen);
        if (j > 0) {
            String[] array = tm.split(hyphen);
            String starttm = array[0];
            if (starttm != null) {
                sb.append(blank);
                sb.append(starttm);
            }
        }

        return sb.toString();
    }

    /**
     * Dateフォマット変換
     * yy/MM/dd,HH:MM-HH:MM ⇒ yy/MM/dd HH:MM
     * @param string dt パラメータ
     * @param string tm パラメータ
     * @return string 返却値
     */
    public static String converToYYMMDDHHMM(String dt, String tm) {

        if (StringUtils.isEmpty(dt)) {
            return "";
        }

        if (StringUtils.isEmpty(tm)) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        String blank = " ";
        String slash = "/";
        String hyphen = "-";
        int i = dt.indexOf(slash);
        if (i > 0) {
            String[] array = dt.split(slash);
            String year = array[0];
            String month = array[1];
            String date = array[2];
            if (year != null) {
                sb.append(year);
            }
            if (month != null) {
                sb.append(slash);
                sb.append(month);
            }
            if (date != null) {
                sb.append(slash);
                sb.append(date);
            }
        }
        int j = tm.indexOf(hyphen);
        if (j > 0) {
            String[] array = tm.split(hyphen);
            String starttm = array[0];
            if (starttm != null) {
                sb.append(blank);
                sb.append(starttm);
            }
        }

        return sb.toString();
    }

    /**
     * Dateフォマット変換
     * yyyyMMdd ⇒ yy/MM/dd
     * @param value パラメータ
     * @return 返却値
     */
    public static String converToYY_MM_DD(String value) {

        if (StringUtils.isEmpty(value)) {
            return "";
        }

        int i = value.length();
        if (i == 8) {
            String year;
            String month;
            String date;

            year = value.substring(2, 4);
            month = value.substring(4, 6);
            date = value.substring(6, 8);

            StringBuffer sbYMD = new StringBuffer();
            sbYMD = sbYMD.append(year).append("/").append(month).append("/").append(date);
            return sbYMD.toString();
        }

        return value;
    }

    /**
     * 文字列の"/"結合
     *
     * @param string str1 パラメータ
     * @param string str2 パラメータ
     * @return string 返却値
     */
    public static String unionString2(String str1, String str2) {

        if (StringUtils.isEmpty(str1)) {
            return "";
        }

        if (StringUtils.isEmpty(str2)) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        String slash = "/";

        sb.append(str1);
        sb.append(slash);
        sb.append(str2);

        return sb.toString();
    }

    /**
     * 文字列の"/"結合
     *
     * @param string str1 パラメータ
     * @param string str2 パラメータ
     * @param string str3 パラメータ
     * @param string str4 パラメータ
     * @return string 返却値
     */
    public static String unionString4(String str1, String str2, String str3, String str4) {

        if (StringUtils.isEmpty(str1)) {
            return "";
        }

        if (StringUtils.isEmpty(str2)) {
            return "";
        }

        if (StringUtils.isEmpty(str3)) {
            return "";
        }

        if (StringUtils.isEmpty(str4)) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        String slash = "/";

        sb.append(str1);
        sb.append(slash);
        sb.append(str2);
        sb.append(slash);
        sb.append(str3);
        sb.append(slash);
        sb.append(str4);

        return sb.toString();
    }

    /**
     * 売上区分、大分類、中分類、小分類、コース名の結合
     *
     * @param string str1 パラメータ
     * @param string str2 パラメータ
     * @param string str3 パラメータ
     * @param string str4 パラメータ
     * @return string 返却値
     */
    public static String purchaseNameEdit(String[] str) {

        if (str.length != 5) {
            return "";
        }

        for (String val : str) {
            if (StringUtils.isEmpty(val)) {
                return "";
            }
        }

        StringBuilder sb = new StringBuilder();
        String slash = "/";
        String lp = "(";
        String rp = ")";

        sb.append(str[0]);
        sb.append(lp);
        sb.append(str[1]);
        sb.append(slash);
        sb.append(str[2]);
        sb.append(slash);
        sb.append(str[3]);
        sb.append(slash);
        sb.append(str[4]);
        sb.append(rp);

        return sb.toString();
    }

    /**
     * 名前の" "結合
     *
     * @param string str1 パラメータ
     * @param string str2 パラメータ
     * @return string 返却値
     */
    public static String unionName(String str1, String str2) {

        if (StringUtils.isEmpty(str1)) {
            return "";
        }

        if (StringUtils.isEmpty(str2)) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        String blank = " ";

        sb.append(str1);
        sb.append(blank);
        sb.append(str2);

        return sb.toString();
    }

    /**
     * nullの空文字変換<br>
     * <br>
     * 引数がnullの場合、空文字に変換する<br>
     * それ以外は、処理を行わない<br>
     * <br>
     * @param inValue
     * @return "" or Object
     */
    public static Object nvlString(Object inValue) {
        if (inValue == null) {
            return "";
        }
        return inValue;
    }

    /**
     * SQL中の特別文字を変更
     *
     * @author xieyan
     * @param str
     *            文字列
     * @return 変更後の文字列
     */
    public static String changeSpecialTag(String str) {
        if (str == null) {
            return "";
        }
        return str.replace("'", "\\'").replace("%", "\\%").replace("_", "\\_");
    }

    /**
     * 処理ログ。
     *
     * @param memo メモ
     * @return slog 返却値
     * @throws SQLException
     * @throws IOException
     */
    public static String unionProcesslog(String memo) {

        String userId = "NOT_LOGIN";
        SessionUser SessionUserData = ((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString()));
        if (SessionUserData != null) {
            // ユーザIDを取得
            userId = ((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString())).getUserId();
        }

        // セッションIDを取得
        String sessionId = SessionDataUtil.getSessionId();

        StringBuffer slog = new StringBuffer();

        slog.append(NaikaraTalkConstants.LOG_PAR_START).append(NaikaraTalkConstants.LOG_USER_ID_NM).append(userId)
                .append(NaikaraTalkConstants.LOG_PAR_END);                                                 // ユーザID
        slog.append(NaikaraTalkConstants.LOG_COMMA).append(NaikaraTalkConstants.LOG_PAR_START)
                .append(NaikaraTalkConstants.LOG_SESSION_ID_NM).append(sessionId)
                .append(NaikaraTalkConstants.LOG_PAR_END);    // セッションID
        slog.append(NaikaraTalkConstants.LOG_COMMA).append(NaikaraTalkConstants.LOG_PAR_START).append(memo)
                .append(NaikaraTalkConstants.LOG_PAR_END);

        return slog.toString();

    }

    /**
     * 最初の文字の切り取り
     *
     * @param string str パラメータ
     * @return string 返却値
     */
    public static String getFirstString(String str) {

        if (StringUtils.isEmpty(str)) {
            return NaikaraTalkConstants.BRANK;
        }

        int i = str.length();
        if (i > 1) {
            return str.substring(0, 1);
        }

        return str;
    }

    /**
     * 時間フォマット変換(HH:mm:ss)。<br>
     * <br>
     * 時間フォマット変換(HHmmss ⇒ HH:mm:ss)。<br>
     * <br>
     * @param String HHmmss <br>
     * @return String HH:mm:ss <br>
     * @exception なし
     */
    public static String converToHH_MM_SS(String value) {

        if (StringUtils.isEmpty(value)) {
            return "";
        }

        int i = value.length();
        if (i == 6) {
            String hour;
            String minute;
            String second;

            hour = value.substring(0, 2);
            minute = value.substring(2, 4);
            second = value.substring(4, 6);

            StringBuffer sbHMS = new StringBuffer();
            sbHMS = sbHMS.append(hour).append(":").append(minute).append(":").append(second);
            return sbHMS.toString();
        }

        return value;
    }

    /**
     * 時間フォマット変換(HH:mm)。<br>
     * <br>
     * 時間フォマット変換(HHmmss ⇒ HH:mm)。<br>
     * <br>
     * @param String HHmmss <br>
     * @return String HH:mm <br>
     * @exception なし
     */
    public static String converToHH_MM(String value) {

        if (StringUtils.isEmpty(value)) {
            return "";
        }

        int i = value.length();
        if (i == 6) {
            String hour;
            String minute;

            hour = value.substring(0, 2);
            minute = value.substring(2, 4);

            StringBuffer sbHM = new StringBuffer();
            sbHM = sbHM.append(hour).append(":").append(minute);
            return sbHM.toString();
        }

        return value;
    }

    /**
     * Dateフォマット変換
     * yyyy-MM-dd HH:mm:ss ⇒ yy/MM/dd
     * @param value パラメータ
     * @return 返却値
     */
    public static String converToYYMMDD(String value) {

        if (StringUtils.isEmpty(value)) {
            return "";
        }

        int i = value.indexOf("-");
        if (i > 0) {
            String[] array = value.split("-");
            String year = array[0];
            String month = array[1];
            String date = array[2];
            if (year.length() == 4) {
                year = year.substring(2, 4);
            }
            if (date.length() > 2) {
                date = date.substring(0, 2);
            }
            StringBuffer sbYMD = new StringBuffer();
            sbYMD = sbYMD.append(year).append(NaikaraTalkConstants.DATE_SEPARATOR).append(month)
                    .append(NaikaraTalkConstants.DATE_SEPARATOR).append(date);
            return sbYMD.toString();
        }

        return value;
    }

    /**
     * PayPal商品説明編集<br>
     * <br>
     * @param String[] value <br>
     * @param String price <br>
     * @return String description <br>
     * @throws UnsupportedEncodingException
     * @exception なし
     */
    public static String descEdit(String[] value, String price) throws UnsupportedEncodingException {

    	int descLen = 127;
    	String strNameEnd = "…";
    	String strComma = "，";
    	String strPriceFront = "（ 合計 ";
    	String strPriceRear = "円 ）";

    	if (ArrayUtils.isEmpty(value)) {
            return "";
        }

    	if (StringUtils.isEmpty(price)) {
            return "";
        }


    	StringBuffer sb = new StringBuffer();
    	sb.append(strPriceFront);
    	sb.append(NaikaraStringUtil.addComma(price));
    	sb.append(strPriceRear);
    	String strPrice = sb.toString();

    	sb = new StringBuffer();
    	for (int i = 0; i < value.length; i++) {
    		if (i != 0) {
            	sb.append(strComma);
    		}
        	sb.append(value[i]);
    	}
    	String strName = sb.toString();

    	sb = new StringBuffer();
    	sb.append(strName);
    	sb.append(strPrice);

    	int j = sb.toString().getBytes("UTF-8").length;
    	while(j > descLen){
    		strName = strName.substring(0, strName.length() - 1);
        	sb = new StringBuffer();
        	sb.append(strName);
        	sb.append(strNameEnd);
        	sb.append(strPrice);
        	j = sb.toString().getBytes("UTF-8").length;
    	}
    	String strDesc = sb.toString();

        return strDesc;
    }


    // 2014/06/02 レッスン予約に関する「応相談」対応 Add Start
    /**
     * 予約区分(予約状況)の判定<br>
     * <br>
     * 予約区分(予約状況)の判定を行う。<br>
     * <br>
     * @param reservProcessKbn 0：取消／1:予約<br>
     * @param nowReservationKbn 予約区分(予約状況)<br>
     * @return String 予約区分(予約状況)の判定結果<br>
     * @throws なし
     */
    public static String judgmentReservationKbn(String reservProcessKbn, String nowReservationKbn) {

        // ◆◆◆ 予約区分(予約状況)の判定
        String reservationKbnSet = null;
        if (StringUtils.equals(nowReservationKbn, NaikaraTalkConstants.RESERV_KBN_NO)) {
            // ※※※発生しない※※※ 予約不可の場合 ⇒ 予約不可
            reservationKbnSet = NaikaraTalkConstants.RESERV_KBN_NO;
        }

        if (StringUtils.equals(reservProcessKbn, NaikaraTalkConstants.RESERV_PROCESS_KBN_NO)) {
            // ◆◆◆ 取消の場合
            if (StringUtils.equals(nowReservationKbn, NaikaraTalkConstants.RESERV_KBN_ALREADY)) {
                // (自分の)予約済の場合 ⇒ 予約可 【取消】
                reservationKbnSet = NaikaraTalkConstants.RESERV_KBN_YES;

            } else if (StringUtils.equals(nowReservationKbn, NaikaraTalkConstants.RESERV_KBN_CON_PROVISIONAL_RESERV)) {
                // (自分の)応相談予約済(仮予約)の場合 ⇒ 応相談可 【応相談：仮予約の取消】
                reservationKbnSet = NaikaraTalkConstants.RESERV_KBN_CON_YES;

            } else if (StringUtils.equals(nowReservationKbn, NaikaraTalkConstants.RESERV_KBN_CON_ALREADY)) {
                // (自分の)応相談予約済(予約確定)の場合 ⇒ 応相談可 【応相談：予約確定の取消】
                reservationKbnSet = NaikaraTalkConstants.RESERV_KBN_CON_YES;
            }

        } else {
            // ◆◆◆ 予約の場合
            if (StringUtils.equals(nowReservationKbn, NaikaraTalkConstants.RESERV_KBN_YES)) {
                // 予約可の場合 ⇒ 予約済 【予約】
                reservationKbnSet = NaikaraTalkConstants.RESERV_KBN_ALREADY;

            } else if (StringUtils.equals(nowReservationKbn, NaikaraTalkConstants.RESERV_KBN_CON_YES)) {
                // 応相談可の場合 ⇒ 応相談予約済 【応相談：仮予約】
                reservationKbnSet = NaikaraTalkConstants.RESERV_KBN_CON_PROVISIONAL_RESERV;

            } else if (StringUtils.equals(nowReservationKbn, NaikaraTalkConstants.RESERV_KBN_CON_PROVISIONAL_RESERV)) {
                // (自分の)応相談予約済(仮予約)の場合 ⇒ 応相談予約確定
                reservationKbnSet = NaikaraTalkConstants.RESERV_KBN_CON_ALREADY;

            }

        }


        // 判定結果の返却
        return reservationKbnSet;
    }
    // 2014/06/02 レッスン予約に関する「応相談」対応 Add End

}