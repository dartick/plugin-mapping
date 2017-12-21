/**
 * Copyright (C), 2011-2017, 微贷网.
 */
package org.xiaoheshan.plugin.mapping.core.generator;

/**
 * ${DESCRIPTION}
 *
 * @author chenhongfa 17-12-19.
 */
public interface Generator<T, M extends Material> {

    T generate(M material);
}
