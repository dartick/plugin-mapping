package org.xiaoheshan.plugin.mapping;

import com.intellij.codeInsight.CodeInsightActionHandler;
import com.intellij.codeInsight.generation.actions.BaseGenerateAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.openapi.ui.popup.ListPopup;
import com.intellij.openapi.ui.popup.PopupStep;
import com.intellij.openapi.ui.popup.util.BaseListPopupStep;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiFile;
import com.intellij.psi.util.PsiUtilBase;
import org.jetbrains.annotations.NotNull;
import org.xiaoheshan.plugin.mapping.core.constant.MappingModeEnum;
import org.xiaoheshan.plugin.mapping.ui.dialog.MappingDialog;
import org.xiaoheshan.plugin.mapping.ui.dialog.ChooseClassDialog;
import org.xiaoheshan.plugin.mapping.ui.context.DefaultPluginContext;
import org.xiaoheshan.plugin.mapping.ui.context.PluginContext;
import org.xiaoheshan.plugin.mapping.util.ObjectUtil;

/**
 * ${DESCRIPTION}
 *
 * @author chenhongfa 17-12-14.
 */
public class MainAction extends BaseGenerateAction {

    public MainAction() {
        super(null);
    }

    public MainAction(CodeInsightActionHandler handler) {
        super(handler);
    }

    @Override
    protected boolean isValidForClass(final PsiClass targetClass) {
        return super.isValidForClass(targetClass);
    }

    @Override
    public boolean isValidForFile(@NotNull Project project, @NotNull Editor editor, @NotNull PsiFile file) {
        return super.isValidForFile(project, editor, file);
    }

    @Override
    public void actionPerformed(AnActionEvent event) {

        Project project = event.getData(PlatformDataKeys.PROJECT);
        Editor editor = event.getData(PlatformDataKeys.EDITOR);
        if (!ObjectUtil.areNoneNull(project, editor)) {
            return;
        }
        PsiFile mFile = PsiUtilBase.getPsiFileInEditor(editor, project);
        if (ObjectUtil.isNull(mFile)) {
            return;
        }
        PsiClass psiClass = getTargetClass(editor, mFile);

        PluginContext context = new DefaultPluginContext(project, editor, mFile, psiClass);

        ListPopup popup = JBPopupFactory.getInstance().createListPopup(
                new BaseListPopupStep<MappingModeEnum>("Select Mode", MappingModeEnum.values()) {
                    @Override
                    public PopupStep onChosen(MappingModeEnum selectedValue, boolean finalChoice) {
                        if (finalChoice) {
                            return doFinalStep(new Runnable() {
                                @Override
                                public void run() {
                                    new ChooseClassDialog(context, selectedValue).show();
                                }
                            });
                        }
                        return super.onChosen(selectedValue, false);
                    }
                }
        );

        popup.showInBestPositionFor(editor);
    }

}
