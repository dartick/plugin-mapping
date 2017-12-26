package org.xiaoheshan.plugin.mapping;

import com.intellij.ide.fileTemplates.FileTemplate;
import com.intellij.ide.fileTemplates.FileTemplateManager;
import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.util.io.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xiaoheshan.plugin.mapping.core.constant.TextConstant;
import org.xiaoheshan.plugin.mapping.core.pipeline.DefaultWorkPipeline;
import org.xiaoheshan.plugin.mapping.core.pipeline.WorkListener;
import org.xiaoheshan.plugin.mapping.core.pipeline.worker.Worker;
import org.xiaoheshan.plugin.mapping.util.ObjectUtil;
import org.xiaoheshan.plugin.mapping.util.StringUtil;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @author _Chf
 * @date 2017-12-16
 */
public class BootStrap implements ApplicationComponent {

    private final Logger logger = LoggerFactory.getLogger(BootStrap.class);

    private Map<String, String> templatesMap = new HashMap<String, String>();
    private static final String TEMPLATE_DIR = "/templates/";
    private static final String TEMPLATE_PREFIX = "templates/";
    private static final String VM_SUFFIX = ".vm";
    private static final String TEMPLATE_EXTENSION = "java";

    @Override
    public void initComponent() {
        logger.info("init bootstrap");
        new DefaultWorkPipeline()
                .addLast(new DefaultTemplateLoader())
                .addLast(new TemplateRegister())
                .setHandleListener(new WorkListener() {
                    @Override
                    public void workBefore(Worker worker) {
                    }
                    @Override
                    public void workAfter(Worker worker) {
                    }
                    @Override
                    public void workException(Exception e) {
                        logger.error("error happen in bootstrap", e);
                    }
                })
                .startWork();
    }

    private class DefaultTemplateLoader implements Worker {
        @Override
        public void work() throws Exception {
            URL url = BootStrap.class.getResource(TEMPLATE_DIR);
            JarURLConnection urlConnection = (JarURLConnection) url.openConnection();
            JarFile templateDirFile = urlConnection.getJarFile();
            InputStream inputStream = null;
            String templateName = "";
            try {
                Enumeration<JarEntry> templateEntries = templateDirFile.entries();
                if (ObjectUtil.isNull(templateEntries)) {
                    return;
                }
                while(templateEntries.hasMoreElements()) {
                    JarEntry templateEntry = templateEntries.nextElement();
                    templateName = templateEntry.getName();
                    if (!templateName.startsWith(TEMPLATE_PREFIX)) {
                        continue;
                    }
                    templateName = templateName.substring(TEMPLATE_PREFIX.length());
                    if (StringUtil.isEmpty(templateName)) {
                        continue;
                    }
                    if (templateName.contains(File.separator)) {
                        continue;
                    }
                    inputStream = templateDirFile.getInputStream(templateEntry);
                    templatesMap.put(StringUtil.cutSuffix(templateName, VM_SUFFIX), FileUtil.loadTextAndClose(inputStream));
                }
            } catch (IOException e) {
                logger.error("template file load error:{}", templateName, e);
                throw e;
            }
            finally {
                if (ObjectUtil.isNoneNull(inputStream)) {
                    inputStream.close();
                }
                if (ObjectUtil.isNoneNull(templateDirFile)) {
                    templateDirFile.close();
                }
            }
        }
    }

    private class TemplateRegister implements Worker {
        @Override
        public void work() {
            FileTemplateManager templateManager = FileTemplateManager.getDefaultInstance();
            List<FileTemplate> templates = new ArrayList<FileTemplate>();
            for (Map.Entry<String, String> entry : templatesMap.entrySet()) {
                String registerName = TextConstant.PLUGIN_NAME + " " + entry.getKey();
                if (ObjectUtil.isNoneNull(templateManager.getTemplate(registerName))) {
                    continue;
                }
                FileTemplate template = templateManager.addTemplate(registerName, TEMPLATE_EXTENSION);
                templates.add(template);
                template.setText(entry.getValue());
            }
            templateManager.setTemplates(TextConstant.PLUGIN_NAME, templates);
        }

    }

}
