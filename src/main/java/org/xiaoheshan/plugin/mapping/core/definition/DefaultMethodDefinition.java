/**
 * Copyright (C), 2011-2017, 微贷网.
 */
package org.xiaoheshan.plugin.mapping.core.definition;

/**
 * method definition
 *
 * @author chenhongfa 17-12-13.
 */
public class DefaultMethodDefinition implements MethodDefinition {

    /**
     * method name
     */
    private String name;

    /**
     * method modifier
     */
    private String modifier;

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


    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getModifier() {
        return modifier;
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
