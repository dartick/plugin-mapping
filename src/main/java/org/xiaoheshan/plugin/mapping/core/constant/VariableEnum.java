package org.xiaoheshan.plugin.mapping.core.constant;

/**
 * @author _Chf
 * @date 2017-12-16
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
