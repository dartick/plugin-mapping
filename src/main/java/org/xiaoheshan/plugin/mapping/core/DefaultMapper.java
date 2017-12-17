package org.xiaoheshan.plugin.mapping.core;

/**
 * @author _Chf
 * @date 2017-12-17
 */
public class DefaultMapper<Origin, Destination> implements Mapper<Origin, Destination> {
    @Override
    public boolean map(Origin origin, Destination destination) {
        return origin.toString().equals(destination.toString());
    }
}
