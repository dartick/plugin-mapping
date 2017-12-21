package org.xiaoheshan.plugin.mapping.ui.dialog;

import com.intellij.openapi.ui.DialogWrapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.xiaoheshan.plugin.mapping.ui.designer.MappingForm;
import org.xiaoheshan.plugin.mapping.ui.context.MappingDialogContext;

import javax.swing.*;
import java.util.Map;

/**
 * @author _Chf
 * @date 2017-12-16
 */
public class MappingDialog extends DialogWrapper {

    private DialogAdapter innerDialog;

    public MappingDialog(@NotNull MappingDialogContext context) {
        super(context.getProject());
        this.innerDialog = new MappingForm(context);
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
}
