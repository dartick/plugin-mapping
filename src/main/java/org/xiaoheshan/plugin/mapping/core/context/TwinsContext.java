/**
 * Copyright (C), 2011-2017, 微贷网.
 */
package org.xiaoheshan.plugin.mapping.core.context;

import org.xiaoheshan.plugin.mapping.core.definition.Clazz;

/**
 * ${DESCRIPTION}
 *
 * @author chenhongfa 17-12-13.
 */
public class TwinsContext extends BaseContext {

    private Clazz origin;

    private Clazz destination;

    public TwinsContext(Clazz origin, Clazz destination) {
        this.origin = origin;
        this.destination = destination;
    }

    public Clazz getOrigin() {
        return origin;
    }

    public Clazz getDestination() {
        return destination;
    }

}
