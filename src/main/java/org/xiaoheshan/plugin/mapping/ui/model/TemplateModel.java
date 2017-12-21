/**
 * Copyright (C), 2011-2017, 微贷网.
 */
package org.xiaoheshan.plugin.mapping.ui.model;

/**
 * ${DESCRIPTION}
 *
 * @author chenhongfa 17-12-19.
 */
public class TemplateModel {

    private String name;
    private String content;

    public TemplateModel(String name, String content) {
        this.name = name;
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (obj instanceof TemplateModel) {
            return  ((TemplateModel) obj).getName().equals(this.name);
        }
        return false;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
