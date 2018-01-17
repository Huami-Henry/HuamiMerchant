package com.huami.merchant.util;

import android.text.InputFilter;
import android.text.Spanned;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Henry on 2017/9/14.
 */

public class InputFilterManager {
    /**
     * @return InputFilter[]    返回类型
     * @Title: InputFilterEdt
     * @Description: (过滤 表情)
     */
    public static InputFilter[] InputFilterEdt() {
        InputFilter emojiFilter = new InputFilter() {
            Pattern emoji = Pattern.compile("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]", Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                Matcher emojiMatcher = emoji.matcher(source);
                if (emojiMatcher.find()) {
                    return "";
                }
                return null;
            }
        };
        InputFilter[] inputFilters = {emojiFilter};
        return inputFilters;

    }
}
