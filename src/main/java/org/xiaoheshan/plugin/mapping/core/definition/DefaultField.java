/**
 * Copyright (C), 2011-2017, 微贷网.
 */
package org.xiaoheshan.plugin.mapping.core.definition;

/**
 * field definition
 *
 * @author chenhongfa 17-12-13.
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
