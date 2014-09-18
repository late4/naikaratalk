package com.naikara_talk.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.opensymphony.xwork2.validator.ValidationException;
import com.opensymphony.xwork2.validator.validators.FieldValidatorSupport;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称:</b>バリデーションチェック<br>
 * <b>クラス名称　　　:</b>半角英数字（記号を含む）チェッククラス<br>
 * <b>クラス概要　　　:</b>半角英数字（記号を含む）チェック<br>
 * <br>
 * <b>著作権　　　　　:</b>Copyright (C) 2013 NAI, Inc. All rights reserved.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/06/27 TECS 新規作成
 */
public class HankakuEnNumSpecCharValidator extends FieldValidatorSupport {

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
     * 半角英数字（記号を含む）のチェック
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

        // ファーマットチェック
        String expression = "[a-zA-Z0-9-^\\[@\\]:;/.,!\"#$%&'()=~|{`}*+-_?><]*";
        Pattern pattern;
        pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(fieldValue);
        if (!matcher.matches()) {
            this.addFieldError(fieldName, object);
        }

    }
}
