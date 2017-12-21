/**
 * Copyright (C), 2011-2017, 微贷网.
 */
package org.xiaoheshan.plugin.mapping.core.generator;

import java.util.HashMap;
import java.util.Map;

/**
 * ${DESCRIPTION}
 *
 * @author chenhongfa 17-12-19.
 */
public class DefaultTemplateMaterial implements TemplateMaterial {

    private String templateText;
    private Map<String, Object> variables;

    public DefaultTemplateMaterial(String templateText) {
        this.templateText = templateText;
        this.variables = new HashMap<String, Object>();
    }

    @Override
    public String getTemplateText() {
        return templateText;
    }

    @Override
    public Map<String, Object> getVariables() {
        return variables;
    }

    @Override
    public void addVariable(String key, Object value) {
        variables.put(key, value);
    }

    @Override
    public void removeVariable(String key) {
        variables.remove(key);
    }

    @Override
    public void clearVariable() {
        variables.clear();
    }

    @Override
    public int variableSize() {
        return variables.size();
    }
}
