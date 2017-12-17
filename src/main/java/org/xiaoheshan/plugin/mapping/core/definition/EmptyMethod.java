package org.xiaoheshan.plugin.mapping.core.definition;

/**
 * @author _Chf
 * @date 2017-12-17
 */
public class EmptyMethod implements Method {

    public static final Method INSTANCE = new EmptyMethod();

    @Override
    public String getName() {
        return "";
    }

    @Override
    public String getReturnType() {
        return "";
    }

    @Override
    public String[] getParams() {
        return new String[0];
    }

    @Override
    public String[] getThrowz() {
        return new String[0];
    }
}
