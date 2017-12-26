package org.xiaoheshan.plugin.mapping.core.generator;

/**
 * @author _Chf
 * @date 2017-12-16
 */
public interface Generator<T, M extends Material> {

    T generate(M material);
}
