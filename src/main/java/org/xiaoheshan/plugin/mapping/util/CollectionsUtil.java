/**
 * Copyright (C), 2011-2017, 微贷网.
 */
package org.xiaoheshan.plugin.mapping.util;

import java.util.Collection;

/**
 * ${DESCRIPTION}
 *
 * @author chenhongfa 17-12-18.
 */
public final class CollectionsUtil {

    private CollectionsUtil() {
    }

    public static boolean isEmpty(Collection collection) {
        if (collection == null || collection.isEmpty()) {
            return true;
        }
        return false;
    }
}
