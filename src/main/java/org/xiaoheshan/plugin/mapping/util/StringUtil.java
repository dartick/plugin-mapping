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

    public static String cutSuffix(String input, String suffix) {
        if (!input.endsWith(suffix)) {
            throw new IllegalArgumentException(String.format("this String [%s] did not has the suffix [%s]", input, suffix));
        }
        return input.substring(0, input.length() - suffix.length());
    }

}
