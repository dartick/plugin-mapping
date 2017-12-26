package org.xiaoheshan.plugin.mapping.core.constant;

/**
 * @author _Chf
 * @date 2017-12-16
 */
public enum MappingModeEnum {

    /**
     * normal
     */
    NORMAL(1, "NORMAL", "choose double object to map")

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
        return name + "   (" + describe + ")";
    }
}
