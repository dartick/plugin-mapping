/**
 * Copyright (C), 2011-2017, 微贷网.
 */
package org.xiaoheshan.plugin.mapping.core.constant;

/**
 * ${DESCRIPTION}
 *
 * @author chenhongfa 17-12-13.
 */
public enum MappingModeEnum {
    /**
     * single
     */
    SINGLE(1, "SINGLE", "mapping single object"),

    /**
     * twins
     */
    TWINS(2, "TWINS", "mapping double object")
    ;

    private int code;
    private String name;
    private String describe;

    MappingModeEnum(int code, String name, String describe) {
        this.code = code;
        this.name = name;
        this.describe = describe;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getDescribe() {
        return describe;
    }

    @Override
    public String toString() {
        return name + " : " + describe;
    }
}
