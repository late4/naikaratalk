package com.naikara_talk.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public class CheckUtil {

    /**
     * 半角文字のチェック
     *
     * @param str
     *            文字
     * @return true, false
     */
    public static boolean isHalfChar(String str) {

        // 文字列がnullの場合、trueを戻る
        if (str == null)
            return true;

        return str.length() != str.getBytes().length;
    }

    /**
     * 半角英数字のチェック
     *
     * @param String str：文字列
     * @return　 true|false
     */
    public static boolean isHalfCharAndNum(String str) {

        // 文字列がnullの場合、falseを戻る
        if (str == null)
            return false;

        return str.matches("[0-9a-zA-z]+");
    }

    /**
     * 文字列のバイトをチェックする
     *
     * @param str
     *            文字列
     * @param length
     *            バイト長さ
     * @return　true、false
     */
    public static boolean validateStrByByte(String str, int length) {
        return str.getBytes().length <= length;
    }

    /**
     * イメージフォマートのチェック
     *
     * @param String contentType：イメージ名称
     * @return true|false
     */
    public static boolean checkImageFileType(String contentType) {
        String[] contentTypeArray = { "image/png", "image/gif", "image/jpeg", "image/jpg", "image/pjpeg", "image/x-png" };
        for (String type : contentTypeArray) {
            if (type.equals(contentType))
                return true;
        }
        return false;
    }

    /**
     * 数値（記号を含まない）チェック
     *
     * @param String str：文字列
     * @return true|false
     */
    public static boolean isNum(String str) {

        // 文字列がnullの場合、falseを戻る
        if (str == null) {
            return false;
        }
        return str.matches("[0-9-]*");
    }

    /**
     * 数値（記号を含む）チェック
     *
     * @param String str：文字列
     * @return true|false
     */
    public static boolean isNumSpecChar(String str) {

        // 文字列がnullの場合、falseを戻る
        if (str == null) {
            return false;
        }
        return str.matches("[0-9-+,-.]*");
    }

    /**
     * 英字（記号を含まない）チェック
     *
     * @param String str：文字列
     * @return true|false
     */
    public static boolean isEn(String str) {

        // 文字列がnullの場合、falseを戻る
        if (str == null) {
            return false;
        }
        return str.matches("[a-zA-Z]*");
    }

    /**
     * 英字（記号を含む）チェック
     *
     * @param String str：文字列
     * @return true|false
     */
    public static boolean isEnSpecChar(String str) {

        // 文字列がnullの場合、falseを戻る
        if (str == null) {
            return false;
        }
        return str.matches("[a-zA-Z-^\\[@\\]:;/.,!\"#$%&'()=~|{`}*+_?><]*");
    }

    /**
     * 全角のみチェック
     *
     * @param String str：文字列
     * @return true|false
     */
    public static boolean isZenkaku(String str) {

        // 文字列がnullの場合、falseを戻る
        if (str == null) {
            return false;
        }
        return str.matches("[^ -~｡-ﾟ]*");
    }

    /**
     * 全角かなチェック
     *
     * @param String str：文字列
     * @return true|false
     */
    public static boolean isZenkakuKana(String str) {

        // 文字列がnullの場合、falseを戻る
        if (str == null) {
            return false;
        }
        return str.matches("[ァ-ヶ]*");
    }

    /**
     * 日付のチェック
     *
     * @param String str：文字列
     * @return true|false
     */
    public static boolean isDate(String str) {

        // 文字列がnullの場合、trueを戻る
        if (StringUtils.isEmpty(str)) {
            return true;
        }

        // 年月の日付対応
        if (str.length() == 6 || str.length() == 7) {
            StringBuffer sb = new StringBuffer();
            str = sb.append(str).append("/01").toString();
        }

        // 日付のファーマット
        final String fmt1 = "yyyy/MM/dd";
        SimpleDateFormat sdf1 = new SimpleDateFormat(fmt1);

        // 日付のファーマット
        final String fmt2 = "yyyyMMdd";
        SimpleDateFormat sdf2 = new SimpleDateFormat(fmt2);

        // 日付のファーマット
        final String fmt3 = "yy/MM/dd";
        SimpleDateFormat sdf3 = new SimpleDateFormat(fmt3);

        // 日付のファーマット
        final String fmt4 = "yy/M/d";
        SimpleDateFormat sdf4 = new SimpleDateFormat(fmt4);

        // 日付のファーマット
        final String fmt5 = "yy/MM/d";
        SimpleDateFormat sdf5 = new SimpleDateFormat(fmt5);

        // 日付のファーマット
        final String fmt6 = "yy/M/dd";
        SimpleDateFormat sdf6 = new SimpleDateFormat(fmt6);

        boolean hasError = true;

        // 数値のファーマットチェック
        String expression = "[0-9/]*";
        Pattern pattern;
        pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(str);
        if (!matcher.matches()) {
            return false;
        }

        // 日付のファーマットチェック１
        try {
            sdf1.setLenient(false);
            sdf1.parse(str);
            // エラーがないの場合
            hasError = false;
        } catch (ParseException e) {
        }

        // 日付のファーマットチェック２
        try {
            sdf2.setLenient(false);
            sdf2.parse(str);
            // エラーがないの場合
            hasError = false;
        } catch (ParseException e) {
        }

        // 日付のファーマットチェック３
        try {
            sdf3.setLenient(false);
            sdf3.parse(str);
            // エラーがないの場合
            hasError = false;
        } catch (ParseException e) {
        }

        // 日付のファーマットチェック４
        try {
            sdf4.setLenient(false);
            sdf4.parse(str);
            // エラーがないの場合
            hasError = false;
        } catch (ParseException e) {
        }

        // 日付のファーマットチェック５
        try {
            sdf5.setLenient(false);
            sdf5.parse(str);
            // エラーがないの場合
            hasError = false;
        } catch (ParseException e) {
        }

        // 日付のファーマットチェック６
        try {
            sdf6.setLenient(false);
            sdf6.parse(str);
            // エラーがないの場合
            hasError = false;
        } catch (ParseException e) {
        }

        if (hasError) {
            return false;
        }

        return true;
    }

    /**
     * イメージサイズのチェック
     *
     * @param File imageFile：イメージファイル
     * @param ImageSizeEnum sizeEnum：指定のサイズ
     * @return true|false
     */
    /*
    public static boolean checkImageSize(File imageFile, ImageSizeEnum sizeEnum) {
     try {
         FileSeekableStream stream = new FileSeekableStream(imageFile);
         RenderedOp img = JAI.create("stream", stream);

         Integer fileWidth = img.getWidth();
         Integer fileHeight = img.getHeight();

         if (fileWidth > sizeEnum.getSize().getWidth()
                 || fileHeight > sizeEnum.getSize().getHeight()) {
             return false;
         }
     } catch (Exception e) {
         return false;
     }
     return true;
    }
    */
}