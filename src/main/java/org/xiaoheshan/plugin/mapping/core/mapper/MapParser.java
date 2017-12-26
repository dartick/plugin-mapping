package org.xiaoheshan.plugin.mapping.core.mapper;

import java.util.Map;

/**
 * @author _Chf
 * @date 2017-12-17
 */
public interface MapParser<Origin, Destination> {

    /**
     * map origin to destination
     * @param origins
     * @param destinations
     * @return
     */
    Map<Origin, Destination> parse(Origin[] origins, Destination[] destinations, Mapper<Origin, Destination> mapper);

    /**
     * map origin to destination
     * @param origins
     * @param destinations
     * @return
     */
    Map<Origin, Destination> parse(Origin[] origins, Destination[] destinations);

}
