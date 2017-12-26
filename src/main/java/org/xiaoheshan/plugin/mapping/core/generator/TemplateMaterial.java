package org.xiaoheshan.plugin.mapping.core.generator;

import java.util.Map;

/**
 * @author _Chf
 * @date 2017-12-16
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
