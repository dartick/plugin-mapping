package org.xiaoheshan.plugin.mapping.core;

/**
 * @author _Chf
 * @date 2017-12-17
 */
public interface Mapper<Origin, Destination> {

    /**
     * decide how to map
     * @param origin
     * @param destination
     * @return
     */
    boolean map(Origin origin, Destination destination);
}
