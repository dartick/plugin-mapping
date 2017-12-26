package org.xiaoheshan.plugin.mapping.util;

/**
 * @author _Chf
 * @date 2017-12-16
 */
public final class ObjectUtil {

    private ObjectUtil() {
    }

    public static boolean isNull(Object object) {
        return object == null;
    }

    public static boolean isNoneNull(Object object) {
        return object != null;
    }

    public static boolean areNull(Object... objects) {
        if (objects.length <=0 ) {
            throw new IllegalArgumentException("the number of argument should larger than 0");
        }
        for (Object object : objects) {
            if (object != null) {
                return false;
            }
        }
        return true;
    }

    public static boolean areNoneNull(Object... objects) {
        if (objects.length <=0 ) {
            throw new IllegalArgumentException("the number of argument should larger than 0");
        }
        for (Object object : objects) {
            if (object == null) {
                return false;
            }
        }
        return true;
    }

}
