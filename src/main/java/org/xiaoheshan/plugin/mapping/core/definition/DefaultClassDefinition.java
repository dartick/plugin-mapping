/**
 * Copyright (C), 2011-2017, 微贷网.
 */
package org.xiaoheshan.plugin.mapping.core.definition;

/**
 * class definition
 *
 * @author chenhongfa 17-12-13.
 */
public class DefaultClassDefinition implements ClassDefinition {

    /**
     * class fields
     */
    private DefaultFieldDefinition[] fields;

    /**
     * class methods
     */
    private DefaultMethodDefinition[] methods;

    @Override
    public DefaultFieldDefinition[] getFields() {
        return fields;
    }

    @Override
    public DefaultMethodDefinition[] getMethods() {
        return methods;
    }
}
