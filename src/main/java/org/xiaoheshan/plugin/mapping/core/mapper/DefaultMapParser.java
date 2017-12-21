package org.xiaoheshan.plugin.mapping.core.mapper;

import java.util.HashMap;
import java.util.Map;

/**
 * @author _Chf
 * @date 2017-12-17
 */
public class DefaultMapParser<Origin, Destination> implements MapParser<Origin, Destination> {

    private Mapper<Origin, Destination> mapper;

    public DefaultMapParser() {
        this.mapper = new DefaultMapper<Origin, Destination>();
    }

    @Override
    public Map<Origin, Destination> parse(Origin[] origins,
                                          Destination[] destinations,
                                          Mapper<Origin, Destination> mapper) {
        Map<Origin,Destination> result = new HashMap<Origin, Destination>();
        for (Origin origin : origins) {
            for (Destination destination : destinations) {
                if (mapper.map(origin, destination)) {
                    result.put(origin, destination);
                }
            }
        }
        return result;
    }

    @Override
    public Map<Origin, Destination> parse(Origin[] origins, Destination[] destinations) {
        return parse(origins, destinations, mapper);
    }
}
