/**
 * Copyright (C), 2011-2017, 微贷网.
 */
package org.xiaoheshan.plugin.mapping.core.meta;

/**
 * method meta
 *
 * @author chenhongfa 17-12-13.
 */
public class DefaultMethod implements Method {

    /**
     * method name
     */
    private String name;

    /**
     * method return type
     */
    private String returnType;

    /**
     * method params
     */
    private String[] params;

    /**
     * method throw exceptions
     */
    private String[] throwz;

    public DefaultMethod(String name, String returnType, String[] params, String[] throwz) {
        this.name = name;
        this.returnType = returnType;
        this.params = params;
        this.throwz = throwz;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getReturnType() {
        return returnType;
    }

    @Override
    public String[] getParams() {
        return params;
    }

    @Override
    public String[] getThrowz() {
        return throwz;
    }
}
