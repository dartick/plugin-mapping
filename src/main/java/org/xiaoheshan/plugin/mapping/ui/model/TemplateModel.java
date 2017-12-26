package org.xiaoheshan.plugin.mapping.ui.model;

/**
 * @author _Chf
 * @date 2017-12-16
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
