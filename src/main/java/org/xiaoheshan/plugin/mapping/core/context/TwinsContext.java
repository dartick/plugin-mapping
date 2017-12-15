/**
 * Copyright (C), 2011-2017, 微贷网.
 */
package org.xiaoheshan.plugin.mapping.core.context;

import org.xiaoheshan.plugin.mapping.core.definition.ClassDefinition;

/**
 * ${DESCRIPTION}
 *
 * @author chenhongfa 17-12-13.
 */
public class TwinsContext extends BaseContext {

    private ClassDefinition origin;

    private ClassDefinition destination;

    public TwinsContext(ClassDefinition origin, ClassDefinition destination) {
        this.origin = origin;
        this.destination = destination;
    }

    public ClassDefinition getOrigin() {
        return origin;
    }

    public ClassDefinition getDestination() {
        return destination;
    }

}
