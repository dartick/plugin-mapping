/**
 * Copyright (C), 2011-2017, 微贷网.
 */
package org.xiaoheshan.plugin.mapping.core.generator;

import java.util.Map;

/**
 * ${DESCRIPTION}
 *
 * @author chenhongfa 17-12-19.
 */
public interface TemplateMaterial extends Material {

    /**
     * @return template text
     */
    String getTemplateText();

    Map<String, Object> getVariables();

    void addVariable(String key, Object value);

    void removeVariable(String key);

    void clearVariable();

    int variableSize();

}
