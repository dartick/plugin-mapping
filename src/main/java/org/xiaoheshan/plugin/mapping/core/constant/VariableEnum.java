/**
 * Copyright (C), 2011-2017, 微贷网.
 */
package org.xiaoheshan.plugin.mapping.core.constant;

/**
 * ${DESCRIPTION}
 *
 * @author chenhongfa 17-12-20.
 */
public enum  VariableEnum {
    MAP("map"),
    ORIGIN("origin"),
    DESTINATION("destination")
    ;

    private String name;

    VariableEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
