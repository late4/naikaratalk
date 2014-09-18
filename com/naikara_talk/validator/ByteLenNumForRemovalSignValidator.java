package com.naikara_talk.validator;

import java.io.UnsupportedEncodingException;

import org.apache.commons.lang3.StringUtils;

import com.opensymphony.xwork2.validator.ValidationException;
import com.opensymphony.xwork2.validator.validators.FieldValidatorSupport;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称:</b>共通部品 Validatorクラス<br>
 * <b>クラス名称　　　:</b>数字項目用のフィールドの入力文字列のバイト数の妥当性を判定するバリデータ<br>
 * <b>クラス概要　　　:</b>数字項目の場合はカンマを除去してバイト数を判定<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 *
 * @author TECS <b>変更履歴　　　　:</b>
 * 2013/06/03 TECS 新規作成
 */
public class ByteLenNumForRemovalSignValidator extends FieldValidatorSupport {

    /**
     * 入力値に対してトリムを行う場合は true.
     */
    private boolean doTrim = true;

    /**
     * 最大入力バイト数.
     */
    private int maxLength = -1;

    /**
     * 最小入力バイト数.
     */
    private int minLength = -1;

    /**
     * 文字セット名.
     */
    private String charset = "MS932";

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
     * 最大入力バイト数を取得します.
     *
     * @return 最大入力バイト数
     */
    public int getMaxLength() {
        return maxLength;
    }

    /**
     * 最大入力バイト数を設定します.
     *
     * @param maxLength
     *            最大入力バイト数
     */
    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }

    /**
     * 最小入力バイト数を取得します.
     *
     * @return 最小入力バイト数
     */
    public int getMinLength() {
        return minLength;
    }

    /**
     * 最小入力バイト数を設定します.
     *
     * @param minLength
     *            最小入力バイト数
     */
    public void setMinLength(int minLength) {
        this.minLength = minLength;
    }

    /**
     * 文字セット名を取得します.
     *
     * @return 文字セット名
     */
    public String getCharset() {
        return charset;
    }

    /**
     * 文字セット名を設定します.
     *
     * @param charset
     *            文字セット名
     */
    public void setCharset(String charset) {
        this.charset = charset;
    }

    @Override
    public void validate(Object object) throws ValidationException {

        // フィールド名を取得
        final String fieldName = getFieldName();

        // フィールド値を取得
        String value = (String) getFieldValue(fieldName, object);

        // トリムを行う場合はトリムを実行
        if (doTrim && value != null) {
            value = value.trim();
        }

        // 入力されていない場合は処理を抜ける
        if (StringUtils.isEmpty(value)) {
            return;
        }

        // カンマを除去
        value = value.replace(",", "");

        // バイト数を取得
        final int length = getByteLength(value);

        // 最小バイト数未満の場合はフィールドエラーを追加
        if ((-1 < minLength) && (length < minLength)) {
            addFieldError(fieldName, object);
        }
        // バイト数超過の場合はフィールドエラーを追加
        else if ((-1 < maxLength) && (maxLength < length)) {
            addFieldError(fieldName, object);
        }
    }

    /**
     * 文字列のバイト数を取得します.
     *
     * @param value
     *            文字列
     * @return バイト数
     */
    private int getByteLength(String value) {
        if (StringUtils.isEmpty(charset)) {
            return value.getBytes().length;
        } else {
            try {
                return value.getBytes(charset).length;
            } catch (UnsupportedEncodingException e) {
                return value.getBytes().length;
            }
        }
    }
}
