/**
 * Copyright (C), 2011-2017, 微贷网.
 */
package org.xiaoheshan.plugin.mapping.util;

import com.intellij.ide.fileTemplates.FileTemplate;
import com.intellij.ide.fileTemplates.FileTemplateManager;
import org.xiaoheshan.plugin.mapping.core.constant.TextConstant;

import java.util.HashMap;
import java.util.Map;

/**
 * ${DESCRIPTION}
 *
 * @author chenhongfa 17-12-19.
 */
public final class TemplateUtil {

    private static final String TEMPLATE_PREFIX = TextConstant.PLUGIN_NAME + " ";

    public static Map<String, String> getTemplates() {
        Map<String, String> templates = new HashMap<String, String>();
        FileTemplateManager templateManager = FileTemplateManager.getDefaultInstance();
        for (FileTemplate template : templateManager.getAllTemplates()) {
            if (template.getName().startsWith(TEMPLATE_PREFIX)) {
                String templateName = template.getName().substring(TEMPLATE_PREFIX.length());
                templates.put(templateName, template.getText());
            }
        }

        return templates;
    }

}
