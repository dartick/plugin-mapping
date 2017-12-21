/**
 * Copyright (C), 2011-2017, 微贷网.
 */
package org.xiaoheshan.plugin.mapping.core.meta;

/**
 * class meta
 *
 * @author chenhongfa 17-12-13.
 */
public class DefaultClazz implements Clazz {

    private String type;

    /**
     * class fields
     */
    private Field[] fields;

    /**
     * all fields
     */
    private Field[] allFields;

    /**
     * class methods
     */
    private Method[] methods;

    /**
     * all methods
     */
    private Method[] allMethods;

    public DefaultClazz(String type, Field[] fields, Field[] allFields, Method[] methods, Method[] allMethods) {
        this.type = type;
        this.fields = fields;
        this.allFields = allFields;
        this.methods = methods;
        this.allMethods = allMethods;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public Field[] getFields() {
        return fields;
    }

    public void setFields(Field[] fields) {
        this.fields = fields;
    }

    @Override
    public Field[] getAllFields() {
        return allFields;
    }

    public void setAllFields(Field[] allFields) {
        this.allFields = allFields;
    }

    @Override
    public Method[] getMethods() {
        return methods;
    }

    public void setMethods(Method[] methods) {
        this.methods = methods;
    }

    @Override
    public Method[] getAllMethods() {
        return allMethods;
    }

    public void setAllMethods(Method[] allMethods) {
        this.allMethods = allMethods;
    }
}