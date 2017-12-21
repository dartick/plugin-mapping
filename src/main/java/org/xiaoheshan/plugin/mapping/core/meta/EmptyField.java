package org.xiaoheshan.plugin.mapping.core.meta;

/**
 * @author _Chf
 * @date 2017-12-17
 */
public class EmptyField implements Field {

    public static final Field INSTANCE = new EmptyField();

    @Override
    public String getType() {
        return "";
    }

    @Override
    public String getName() {
        return "";
    }
}
