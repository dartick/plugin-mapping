package org.xiaoheshan.plugin.mapping.core.meta;

/**
 * @author _Chf
 * @date 2017-12-16
 */
public class DefaultField implements Field {

    /**
     * field name
     */
    private String name;

    /**
     * field type
     */
    private String type;

    public DefaultField(String name, String type) {
        this.name = name;
        this.type = type;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getType() {
        return type;
    }
}
