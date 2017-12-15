/**
 * Copyright (C), 2011-2017, 微贷网.
 */
package org.xiaoheshan.plugin.mapping.util;

/**
 * ${DESCRIPTION}
 *
 * @author chenhongfa 17-12-13.
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

}
