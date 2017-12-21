package org.xiaoheshan.plugin.mapping.ui.context;

import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiFile;
import org.xiaoheshan.plugin.mapping.core.meta.Clazz;

/**
 * @author _Chf
 * @date 2017-12-16
 */
public class DefaultMappingDialogContext implements MappingDialogContext {

    private PluginContext context;
    private PsiClass originPsiClass;
    private PsiClass destinationPsiClass;

    public DefaultMappingDialogContext(PluginContext context,
                                       PsiClass originPsiClass,
                                       PsiClass destinationPsiClass) {
        this.context = context;
        this.originPsiClass = originPsiClass;
        this.destinationPsiClass = destinationPsiClass;
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
    public PsiClass getOriginPsiClass() {
        return originPsiClass;
    }

    @Override
    public PsiClass getDestinationPsiClass() {
        return destinationPsiClass;
    }
}
