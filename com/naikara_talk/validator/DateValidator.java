package com.naikara_talk.validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.opensymphony.xwork2.validator.ValidationException;
import com.opensymphony.xwork2.validator.validators.FieldValidatorSupport;

/**
 * 日付（yyyy/MM/dd）チェック
 *
 * @author tecs
 * @version 1.0
 */
public class DateValidator extends FieldValidatorSupport {

    /**
     * 入力値に対してトリムを行う場合は true.
     */
    private boolean doTrim = true;

    /**
     * 入力値に対してトリムを行うかを取得します.
     *
     * @return 入力値に対してトリムを行う場合は true.
     */
    public boolean isDoTrim() {
        return doTrim;
    }

    /**
     * 入力値に対してトリムを行うかを設定します.
     *
     * @param doTrim
     *            入力値に対してトリムを行う場合は true.
     */
    public void setDoTrim(boolean doTrim) {
        this.doTrim = doTrim;
    }

    /**
     * 日付のチェック
     *
     * @param Object
     *
     * @return なし
     *
     * @exception ValidationException
     */
    @Override
    public void validate(Object object) throws ValidationException {

        // フィールド名を取得
        final String fieldName = getFieldName();

        // フィールド値を取得
        String fieldValue = (String) getFieldValue(fieldName, object);

        // トリムを行う場合はトリムを実行
        if (doTrim && fieldValue != null) {
            fieldValue = fieldValue.trim();
        }

        // 入力されていない場合は処理を抜ける
        if (StringUtils.isEmpty(fieldValue)) {
            return;
        }

        // 年月の日付対応
        if (fieldValue.length() == 7) {
            StringBuffer sb = new StringBuffer();
            fieldValue = sb.append(fieldValue).append("/01").toString();
        }
        if (fieldValue.length() == 6) {
            StringBuffer sb = new StringBuffer();
            fieldValue = sb.append(fieldValue).append("01").toString();
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
        Matcher matcher = pattern.matcher(fieldValue);
        if (!matcher.matches()) {
            this.addFieldError(fieldName, object);
            return;
        }

        // 日付のファーマットチェック１
        try {
            sdf1.setLenient(false);
            sdf1.parse(fieldValue);
            // エラーがないの場合
            hasError = false;
        } catch (ParseException e) {
        }

        // 日付のファーマットチェック２
        try {
            sdf2.setLenient(false);
            sdf2.parse(fieldValue);
            // エラーがないの場合
            hasError = false;
        } catch (ParseException e) {
        }

        // 日付のファーマットチェック３
        try {
            sdf3.setLenient(false);
            sdf3.parse(fieldValue);
            // エラーがないの場合
            hasError = false;
        } catch (ParseException e) {
        }

        // 日付のファーマットチェック４
        try {
            sdf4.setLenient(false);
            sdf4.parse(fieldValue);
            // エラーがないの場合
            hasError = false;
        } catch (ParseException e) {
        }

        // 日付のファーマットチェック５
        try {
            sdf5.setLenient(false);
            sdf5.parse(fieldValue);
            // エラーがないの場合
            hasError = false;
        } catch (ParseException e) {
        }

        // 日付のファーマットチェック６
        try {
            sdf6.setLenient(false);
            sdf6.parse(fieldValue);
            // エラーがないの場合
            hasError = false;
        } catch (ParseException e) {
        }

        if (hasError) {
            // エラーメッセージ（ファイルに取得する）
            this.addFieldError(fieldName, object);
        }

    }
}
