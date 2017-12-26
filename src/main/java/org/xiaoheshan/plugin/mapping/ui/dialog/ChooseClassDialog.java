package org.xiaoheshan.plugin.mapping.ui.dialog;

import com.intellij.openapi.ui.DialogWrapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.xiaoheshan.plugin.mapping.core.constant.MappingModeEnum;
import org.xiaoheshan.plugin.mapping.ui.designer.ChooseClassForm;
import org.xiaoheshan.plugin.mapping.ui.context.PluginContext;

import javax.swing.*;

/**
 * @author _Chf
 * @date 2017-12-16
 */
public class ChooseClassDialog extends DialogWrapper {

    private DialogAdapter innerDialog;

    public ChooseClassDialog(PluginContext context, @NotNull MappingModeEnum mode) {
        super(context.getProject());
        switch (mode) {
            default:
            case NORMAL:
                this.innerDialog = new ChooseClassForm(context);
        }
        this.setTitle(innerDialog.getTitle());
        init();
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        return innerDialog.getTopPanel();
    }

    @Override
    protected void doOKAction() {
        super.doOKAction();
        innerDialog.onOk();
    }

    @Override
    public void doCancelAction() {
        super.doCancelAction();
        innerDialog.onCancel();
    }

}
