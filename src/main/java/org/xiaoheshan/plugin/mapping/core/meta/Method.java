package org.xiaoheshan.plugin.mapping.core.meta;

/**
 * @author _Chf
 * @date 2017-12-16
 */
public interface Method {

    /**
     * @return name
     */
    String getName();

    /**
     * @return returnType
     */
    String getReturnType();

    /**
     * @return params
     */
    String[] getParams();

    /**
     * @return throws exceptions
     */
    String[] getThrowz();

}
