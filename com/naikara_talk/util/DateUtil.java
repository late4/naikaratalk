package com.naikara_talk.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

public class DateUtil {

    /** Date型文字列書式 */
    public static final String DATE_FORMAT_yyyyMMdd = "yyyyMMdd";

    /** Date型文字列書式 */
    public static final String DATE_FORMAT_yyyy_MM_dd = "yyyy/MM/dd";

    /** Date型文字列書式 */
    public static final String DATE_FORMAT_yyyyMM = "yyyyMM";

    /** Date型文字列書式 */
    public static final String DATE_FORMAT_yyyy_MM = "yyyy/MM";

    /** Date型文字列書式 */
    public static final String DATE_FORMAT_yyyyMMddHHmmssSSS = "yyyyMMddHHmmssSSS";

    /** Date型文字列書式 */
    public static final String DATE_FORMAT_yyyyMMddHHmmss = "yyyyMMddHHmmss";

    /** Date型文字列書式 */
    public static final String DATE_FORMAT_yyyy_MM_dd_HH_mm_ss = "yyyy/MM/dd HH:mm:ss";

    /** Date型文字列書式 */
    public static final String DATE_FORMAT_yyyy_MM_dd_HH_mm = "yyyy/MM/dd HH:mm";

    // 2014/06/02 レッスン予約に関する「応相談」対応 Add Start
    /** Date型文字列書式 */
    public static final String DATE_FORMAT_yy_MM_dd_HH_mm = "yy/MM/dd HH:mm";
    /** Date型文字列書式 */
    public static final String DATE_FORMAT_yy_MM_dd = "yy/MM/dd";
    /** Date型文字列書式 */
    public static final String DATE_FORMAT_HH_mm = "HH:mm";
    // 2014/06/02 レッスン予約に関する「応相談」対応 Add End

    /** 日付比較用書式 */
    private static final DateFormat df = new SimpleDateFormat(DATE_FORMAT_yyyyMMdd);

    /** 日付比較用書式 */
    private static final DateFormat df1 = new SimpleDateFormat(DATE_FORMAT_yyyy_MM_dd);

    /** 日付比較用書式 */
    private static final DateFormat df2 = new SimpleDateFormat(DATE_FORMAT_yyyyMMdd);

    /** 日付比較用書式 */
    private static final DateFormat df3 = new SimpleDateFormat(DATE_FORMAT_yyyy_MM_dd_HH_mm);

    /** 日付比較用書式 */
    private static final DateFormat df4 = new SimpleDateFormat(DATE_FORMAT_yyyy_MM);

    /** Date型文字列書式 */
    public static final String TIME_FORMAT_dd = "dd";

    /** Date型文字列書式 */
    public static final String TIME_FORMAT_HH_mm_ss = "HHmmss";

    /**
     * Date型をStringに変換します。
     * 毎回書式をコンパイルするため、大量処理では負荷がかかります。
     *
     * @param date
     * @param format
     *            変換する書式
     * @return
     */
    public static String toString(Date date, String format) {
    	if (date == null) {
        	return null;
        }
        DateFormat df = new SimpleDateFormat(format);
        return df.format(date);
    }

    /**
     * 日付文字列をDate型に変換します。
     * 毎回書式をコンパイルするため、大量処理では負荷がかかります。
     *
     * @param date
     * @param format
     *            変換する書式
     * @return
     */
    public static Date toDate(String dateStr, String format) {
        DateFormat df = new SimpleDateFormat(format);
        return df.parse(dateStr, new ParsePosition(0));
    }

    /**
     * 業務日付を返却します。 FIXME:暫定的に作っただけなので推奨されないメソッドにしておきます。
     *
     * @return 業務日付
     */
//    @Deprecated
    public static Date getOperationDate() {
        return new Date();
    }

    /**
     * 現在のタイムスタンプをを返却します。
     *
     * @return 業務日付
     */
    public static Timestamp getSysTimestamp() {
        return     new Timestamp(System.currentTimeMillis());
    }

    /**
     *
     * Date型　日付の比較
     *
     * @param a
     * @param b
     * @return
     */
    public static boolean dateEquals(Date a, Date b) {
        return StringUtils.equals(df.format(a), df.format(b));
    }

    /**
     *
     * システム日付の先月取得(yyyy/MM)
     *
     * @param なし
     * @return String
     */
    public static String getSysDateYM() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        DateFormat df = new SimpleDateFormat(DATE_FORMAT_yyyy_MM);
        return df.format(calendar.getTime());
    }

    /**
    *
    * システム日付の当月取得(yyyy/MM)
    *
    * @param なし
    * @return String
    */
   public static String getSysDateYMNow() {
       Calendar calendar = Calendar.getInstance();
       DateFormat df = new SimpleDateFormat(DATE_FORMAT_yyyy_MM);
       return df.format(calendar.getTime());
   }

    /**
     *
     * システム日付取得(yyyyMMdd)
     *
     * @param なし
     * @return String
     */
    public static String getSysDate() {
        DateFormat df = new SimpleDateFormat(DATE_FORMAT_yyyyMMdd);
        return df.format(new Date());
    }

    /**
     *
     * システム日付 + Dayの取得(yyyyMMdd)
     *
     * @param day 日数
     * @return String
     */
    public static String getSysDateAddDay(int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, day);
        DateFormat df = new SimpleDateFormat(DATE_FORMAT_yyyyMMdd);
        return df.format(calendar.getTime());
    }

    /**
     *
     * システム日付 + Minuteの取得(yyyy/MM/dd HH:mm)
     *
     * @param min 分数
     * @return String
     */
    public static String getSysDateAddMinute(int min) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, min);
        DateFormat df = new SimpleDateFormat(DATE_FORMAT_yyyy_MM_dd_HH_mm);
        return df.format(calendar.getTime());
    }

    /**
    *
    * システム日付 + Monthの取得(yyyyMM)
    *
    * @param day 日数
    * @return String
    */
   public static String getSysDateAddMonth1(int month) {
       Calendar calendar = Calendar.getInstance();
       calendar.add(Calendar.MONTH, month);
       DateFormat df = new SimpleDateFormat(DATE_FORMAT_yyyyMM);
       return df.format(calendar.getTime());
   }

    /**
     *
     * システム日付取得(yyyyMMddHHmmss)
     *
     * @param なし
     * @return String
     */
    public static String getSysDateTimeNoSplit() {
        DateFormat df = new SimpleDateFormat(DATE_FORMAT_yyyyMMddHHmmss);
        return df.format(new Date());
    }

    /**
     *
     * システム日付取得(yyyyMMddHHmmssSSS)
     *
     * @param なし
     * @return String
     */
    public static String getSysDateTime() {
        DateFormat df = new SimpleDateFormat(DATE_FORMAT_yyyyMMddHHmmssSSS);
        return df.format(new Date());
    }

    /**
     *
     * システム日付取得(yyyy/MM/dd HH:mm:ss)
     *
     * @param なし
     * @return String
     */
    public static String getSysDateTimestamp() {
        DateFormat df = new SimpleDateFormat(DATE_FORMAT_yyyy_MM_dd_HH_mm_ss);
        return df.format(new Date());
    }

    /**
     *
     * 日付の比較(yyyy/MM/dd)
     *
     * @param 日付１
     * @param 日付２
     * @return boolean
     */
    public static boolean dateCompare1(String dateFrom, String dateTo) {
        try {
            Date dtStart = df1.parse(dateFrom);
            Date dtEnd = df1.parse(dateTo);
            if (dtStart.after(dtEnd)) {
                return true;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     *
     * 日付の比較(yyyyMMdd)
     *
     * @param 日付１
     * @param 日付２
     * @return boolean >場合：true、<場合：false
     */
    public static boolean dateCompare2(String dateFrom, String dateTo) {
        try {
            Date dtStart = df2.parse(dateFrom);
            Date dtEnd = df2.parse(dateTo);
            if (dtStart.after(dtEnd)) {
                return true;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 日付の比較(yyyyMMdd)
     *
     * @param 日付１
     * @param 日付２
     * @return boolean >=場合：true、<場合：false
     */
    public static boolean dateCompare3(String dateFrom, String dateTo) {

        try {
            Date dtStart = df2.parse(dateFrom);
            Date dtEnd = df2.parse(dateTo);
            // 日付型を比較
            if (dtStart.compareTo(dtEnd) >= 0) {
                return true;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 日付の比較(yyyy/MM/dd HH:mm)
     *
     * @param 日付１
     * @param 日付２
     * @return boolean >場合：true、<場合：false
     */
    public static boolean dateCompare4(String dateFrom, String dateTo) {

        try {
            Date dtStart = df3.parse(dateFrom);
            Date dtEnd = df3.parse(dateTo);
            // 日付型を比較
            if (dtStart.after(dtEnd)) {
                return true;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 日付の比較(yyyy/MM)
     *
     * @param 日付１
     * @param 日付２
     * @return boolean >場合：true、<場合：false
     */
    public static boolean dateCompare5(String dateFrom, String dateTo) {

        try {
            Date dtStart = df4.parse(dateFrom);
            Date dtEnd = df4.parse(dateTo);
            // 日付型を比較
            if (dtStart.after(dtEnd)) {
                return true;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     *
     * システム日(dd)
     *
     * @param なし
     * @return String
     */
    public static String getSysDatedd() {
        DateFormat df = new SimpleDateFormat(TIME_FORMAT_dd);
        return df.format(new Date());
    }

    /**
     *
     * システム時間取得(HHmmss)
     *
     * @param なし
     * @return String
     */
    public static String getSysTime() {
        DateFormat df = new SimpleDateFormat(TIME_FORMAT_HH_mm_ss);
        return df.format(new Date());
    }

    /**
     *
     * システム日付 + nヶ月の取得(yyyyMMdd)
     *
     * @param なし
     * @return String
     */
    public static String getSysDateAddMonth(int n) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, n);
        DateFormat df = new SimpleDateFormat(DATE_FORMAT_yyyyMMdd);
        return df.format(calendar.getTime());
    }

    /**
     *
     * 日付(yyyyMMdd) + nヶ日の取得(yyyyMMdd)
     *
     * @param value 日付
     * @param n 日数
     * @return String
     */
    public static String getDateAddDay(String value, int n) {
        DateFormat df = new SimpleDateFormat(DATE_FORMAT_yyyyMMdd);
        Date date = df.parse(value, new ParsePosition(0));
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, n);
        return df.format(calendar.getTime());
    }

    /**
     *
     * システム日付取得(yyyyMM)
     *
     * @param なし
     * @return String
     */
    public static String getSysDateYMNoSplit() {
        DateFormat df = new SimpleDateFormat(DATE_FORMAT_yyyyMM);
        return df.format(new Date());
    }

    /**
    *
    * システム日付 + nヶ年の取得(yyyyMMdd)
    *
    * @param なし
    * @return String
    */
   public static String getSysDateAddYear(int n) {
       Calendar calendar = Calendar.getInstance();
       calendar.add(Calendar.YEAR, n);
       DateFormat df = new SimpleDateFormat(DATE_FORMAT_yyyyMMdd);
       return df.format(calendar.getTime());
   }

}