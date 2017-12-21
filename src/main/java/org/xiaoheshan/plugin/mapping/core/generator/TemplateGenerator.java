/**
 * Copyright (C), 2011-2017, 微贷网.
 */
package org.xiaoheshan.plugin.mapping.core.generator;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.log.NullLogChute;

import java.io.StringWriter;
import java.util.Map;

/**
 * ${DESCRIPTION}
 *
 * @author chenhongfa 17-12-19.
 */
public class TemplateGenerator implements Generator<String, TemplateMaterial> {

    private VelocityEngine velocityEngine;

    public TemplateGenerator() {
        this.velocityEngine = new VelocityEngine();
        this.velocityEngine.setProperty(
                RuntimeConstants.RUNTIME_LOG_LOGSYSTEM_CLASS,
                NullLogChute.class.getName());
        this.velocityEngine.init();
    }

    @Override
    public String generate(TemplateMaterial material) {
        VelocityContext context = new VelocityContext();
        Map<String, Object> variables = material.getVariables();
        for (Map.Entry<String, Object> entry : variables.entrySet()) {
            context.put(entry.getKey(), entry.getValue());
        }
        StringWriter writer = new StringWriter();
        velocityEngine.evaluate(context, writer, "", material.getTemplateText());

        return writer.toString();
    }

}
