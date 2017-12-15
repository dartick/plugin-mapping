package org.xiaoheshan.plugin.mapping;

import com.intellij.codeInsight.CodeInsightActionHandler;
import com.intellij.codeInsight.generation.actions.BaseGenerateAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.components.ComponentManager;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.openapi.ui.popup.ListPopup;
import com.intellij.openapi.ui.popup.PopupStep;
import com.intellij.openapi.ui.popup.util.BaseListPopupStep;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiFile;
import com.intellij.psi.util.PsiUtilBase;
import com.intellij.util.messages.MessageBus;
import org.jetbrains.annotations.NotNull;
import org.xiaoheshan.plugin.mapping.core.constant.MappingModeEnum;
import org.xiaoheshan.plugin.mapping.ui.ChooseClassDialog;
import org.xiaoheshan.plugin.mapping.ui.topic.ModeChosenNotifier;

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
        PsiFile mFile = PsiUtilBase.getPsiFileInEditor(editor, project);
        PsiClass psiClass = getTargetClass(editor, mFile);

        initSubscribe(project.getMessageBus());

        JBPopupFactory instance = JBPopupFactory.getInstance();
        ListPopup popup = instance.createListPopup(
                new BaseListPopupStep<MappingModeEnum>("Select Mode", MappingModeEnum.values()) {
                    @Override
                    public PopupStep onChosen(MappingModeEnum selectedValue, boolean finalChoice) {
                        if (finalChoice) {
                            ModeChosenNotifier publisher = project.getMessageBus()
                                    .syncPublisher(ModeChosenNotifier.MODE_CHOSEN_TOPIC);
                            publisher.onChosen(project, selectedValue);
                        }
                        return super.onChosen(selectedValue, finalChoice);
                    }
                }
        );

        popup.showInBestPositionFor(editor);
    }

    private void initSubscribe(MessageBus bus) {

        bus.connect().subscribe(ModeChosenNotifier.MODE_CHOSEN_TOPIC, new ModeChosenNotifier() {
            @Override
            public void onChosen(Project project, MappingModeEnum mode) {
                new ChooseClassDialog(project, mode)
                        .show();
            }
        });

    }
}
