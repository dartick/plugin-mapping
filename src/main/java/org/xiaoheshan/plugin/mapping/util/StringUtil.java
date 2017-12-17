package org.xiaoheshan.plugin.mapping.util;

/**
 * @author _Chf
 * @date 2017-12-16
 */
public final class StringUtil {

    private StringUtil() {
    }


    public static boolean isEmpty(CharSequence charSequence) {
        return charSequence == null || charSequence.length() <= 0;
    }

    public static boolean isBlank(CharSequence charSequence) {
        if (isEmpty(charSequence)) {
            return true;
        }
        for (int i = 0; i < charSequence.length(); i++) {
            if (charSequence.charAt(i) > ' ') {
                return false;
            }
        }
        return true;
    }

}
