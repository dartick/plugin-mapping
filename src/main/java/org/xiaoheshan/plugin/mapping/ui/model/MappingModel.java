package org.xiaoheshan.plugin.mapping.ui.model;

/**
 * @author _Chf
 * @date 2017-12-16
 */
public class MappingModel {

    private String origin;

    private String destination;

    public MappingModel(String origin, String destination) {
        this.origin = origin;
        this.destination = destination;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }
}
