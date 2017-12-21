/**
 * Copyright (C), 2011-2017, 微贷网.
 */
package org.xiaoheshan.plugin.mapping.core.meta;

/**
 * ${DESCRIPTION}
 *
 * @author chenhongfa 17-12-13.
 */
public interface Clazz {

    String getType();

    /**
     * @return fields
     */
    Field[] getFields();

    /**
     * @return all fields include parent's
     */
    Field[] getAllFields();

    /**
     * @return methods
     */
    Method[] getMethods();

    /**
     * @return all methods include parent's
     */
    Method[] getAllMethods();

}
