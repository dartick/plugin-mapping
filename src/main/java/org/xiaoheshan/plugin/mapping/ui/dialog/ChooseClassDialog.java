/**
 * Copyright (C), 2011-2017, 微贷网.
 */
package org.xiaoheshan.plugin.mapping.ui.dialog;

import com.intellij.openapi.ui.DialogWrapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.xiaoheshan.plugin.mapping.core.constant.MappingModeEnum;
import org.xiaoheshan.plugin.mapping.ui.ChooseDoubleClassForm;
import org.xiaoheshan.plugin.mapping.ui.ChooseSingleClassForm;
import org.xiaoheshan.plugin.mapping.ui.context.PluginContext;
import org.xiaoheshan.plugin.mapping.ui.dialog.DialogAdapter;

import javax.swing.*;

/**
 * ${DESCRIPTION}
 *
 * @author chenhongfa 17-12-15.
 */
public class ChooseClassDialog extends DialogWrapper {

    private DialogAdapter innerDialog;

    public ChooseClassDialog(PluginContext context, @NotNull MappingModeEnum mode) {
        super(context.getProject());
        switch (mode) {
            case SINGLE:
                this.innerDialog = new ChooseSingleClassForm(context);
                break;
            default:
            case TWINS:
                this.innerDialog = new ChooseDoubleClassForm(context);
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