/**
 * Copyright (C), 2011-2017, 微贷网.
 */
package org.xiaoheshan.plugin.mapping.core.context;

import org.xiaoheshan.plugin.mapping.core.definition.ClassDefinition;

/**
 * 单类模式工厂
 *
 * @author chenhongfa 17-12-13.
 */
public class SingleContext extends BaseContext {

    private ClassDefinition target;

    public SingleContext(ClassDefinition target) {
        this.target = target;
    }

    public ClassDefinition getTarget() {
        return target;
    }

}
