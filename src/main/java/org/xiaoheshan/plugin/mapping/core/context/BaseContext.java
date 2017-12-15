/**
 * Copyright (C), 2011-2017, 微贷网.
 */
package org.xiaoheshan.plugin.mapping.core.context;

import java.util.HashMap;
import java.util.Map;

/**
 *
 *
 * @author chenhongfa 17-12-13.
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
