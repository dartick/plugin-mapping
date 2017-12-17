/**
 * Copyright (C), 2011-2017, 微贷网.
 */
package org.xiaoheshan.plugin.mapping.core.definition;

/**
 * ${DESCRIPTION}
 *
 * @author chenhongfa 17-12-13.
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
