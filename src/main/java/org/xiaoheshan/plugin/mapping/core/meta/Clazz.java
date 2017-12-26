package org.xiaoheshan.plugin.mapping.core.meta;

/**
 * @author _Chf
 * @date 2017-12-16
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
