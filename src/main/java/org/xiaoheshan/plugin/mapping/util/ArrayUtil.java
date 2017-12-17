package org.xiaoheshan.plugin.mapping.util;

/**
 * @author _Chf
 * @date 2017-12-16
 */
public final class ArrayUtil {

    private ArrayUtil() {
    }

    public static boolean isEmpty(Object[] objects) {
        if (objects == null || objects.length <= 0) {
            return true;
        }
        return false;
    }

}
