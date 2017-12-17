package org.xiaoheshan.plugin.mapping.ui.context;

import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiFile;
import org.xiaoheshan.plugin.mapping.core.context.IContext;

/**
 * @author _Chf
 * @date 2017-12-16
 */
public interface PluginContext extends IContext {

    /**
     * get project
     * @return
     */
    Project getProject();

    Editor getEditor();

    PsiFile getTargetFile();

    PsiClass getTargetClass();

}
