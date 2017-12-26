package org.xiaoheshan.plugin.mapping.core.context;

import java.util.Map;

/**
 * @author _Chf
 * @date 2017-12-16
 */
public interface MappingContext extends IContext {

    /**
     * @return mapping container
     */
    Map<String, String> getMap();

}
