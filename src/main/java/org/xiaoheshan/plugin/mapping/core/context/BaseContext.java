package org.xiaoheshan.plugin.mapping.core.context;

import java.util.HashMap;
import java.util.Map;

/**
 * @author _Chf
 * @date 2017-12-16
 */
public abstract class BaseContext implements MappingContext {

    private Map<String, String> mapping;

    BaseContext() {
        this.mapping = new HashMap<String, String>();
    }

    @Override
    public Map<String, String> getMap() {
        return mapping;
    }
}
