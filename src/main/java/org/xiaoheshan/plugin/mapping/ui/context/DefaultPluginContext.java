package org.xiaoheshan.plugin.mapping.ui.context;

import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiFile;

/**
 * @author _Chf
 * @date 2017-12-16
 */
public class DefaultPluginContext implements PluginContext {

    private Project project;
    private Editor editor;
    private PsiFile targetFile;
    private PsiClass targetClass;

    public DefaultPluginContext(Project project, Editor editor, PsiFile targetFile, PsiClass targetClass) {
        this.project = project;
        this.editor = editor;
        this.targetFile = targetFile;
        this.targetClass = targetClass;
    }

    @Override
    public Project getProject() {
        return project;
    }

    @Override
    public Editor getEditor() {
        return editor;
    }

    @Override
    public PsiFile getTargetFile() {
        return targetFile;
    }

    @Override
    public PsiClass getTargetClass() {
        return targetClass;
    }

}
