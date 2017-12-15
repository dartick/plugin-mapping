/**
 * Copyright (C), 2011-2017, 微贷网.
 */
package org.xiaoheshan.plugin.mapping.core.definition;

/**
 * field definition
 *
 * @author chenhongfa 17-12-13.
 */
public class DefaultFieldDefinition implements FieldDefinition {

    /**
     * field type
     */
    private String type;

    /**
     * field name
     */
    private String name;

    /**
     * field type
     */
    private String modifier;

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getModifier() {
        return modifier;
    }
}
