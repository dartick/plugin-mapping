package org.xiaoheshan.plugin.mapping.ui.context;

import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiFile;
import org.xiaoheshan.plugin.mapping.core.definition.Clazz;

/**
 * @author _Chf
 * @date 2017-12-16
 */
public class DefaultMappingDialogContext implements MappingDialogContext {

    private PluginContext context;
    private Clazz originClazz;
    private Clazz destinationClazz;

    public DefaultMappingDialogContext(PluginContext context, Clazz originClazz, Clazz destinationClazz) {
        this.context = context;
        this.originClazz = originClazz;
        this.destinationClazz = destinationClazz;
    }

    @Override
    public Project getProject() {
        return context.getProject();
    }

    @Override
    public Editor getEditor() {
        return context.getEditor();
    }

    @Override
    public PsiFile getTargetFile() {
        return context.getTargetFile();
    }

    @Override
    public PsiClass getTargetClass() {
        return context.getTargetClass();
    }

    @Override
    public Clazz getOriginClazz() {
        return originClazz;
    }

    @Override
    public Clazz getDestinationClazz() {
        return destinationClazz;
    }
}
