package org.xiaoheshan.plugin.mapping.core.meta;

/**
 * @author _Chf
 * @date 2017-12-17
 */
public class EmptyClazz implements Clazz {

    public static final Clazz INSTANCE = new EmptyClazz();

    @Override
    public String getType() {
        return "";
    }

    @Override
    public Field[] getFields() {
        return new Field[0];
    }

    @Override
    public Field[] getAllFields() {
        return new Field[0];
    }

    @Override
    public Method[] getMethods() {
        return new Method[0];
    }

    @Override
    public Method[] getAllMethods() {
        return new Method[0];
    }
}
