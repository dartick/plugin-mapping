package org.xiaoheshan.plugin.mapping.util;

import java.util.Collection;

/**
 * @author _Chf
 * @date 2017-12-16
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
