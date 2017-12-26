package org.xiaoheshan.plugin.mapping.core.context;

import org.xiaoheshan.plugin.mapping.core.meta.Clazz;

/**
 * @author _Chf
 * @date 2017-12-16
 */
public class DoubleClassContext extends BaseContext {

    private Clazz origin;

    private Clazz destination;

    public DoubleClassContext(Clazz origin, Clazz destination) {
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
