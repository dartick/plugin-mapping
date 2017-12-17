package org.xiaoheshan.plugin.mapping.ui.dialog;

import com.intellij.openapi.ui.DialogWrapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.xiaoheshan.plugin.mapping.ui.MappingForm;
import org.xiaoheshan.plugin.mapping.ui.context.DefaultMappingDialogContext;
import org.xiaoheshan.plugin.mapping.ui.context.MappingDialogContext;
import org.xiaoheshan.plugin.mapping.ui.context.PluginContext;
import org.xiaoheshan.plugin.mapping.ui.dialog.DialogAdapter;

import javax.swing.*;
import java.util.Map;

/**
 * @author _Chf
 * @date 2017-12-16
 */
public class MappingDialog extends DialogWrapper {

    private DialogAdapter innerDialog;

    public MappingDialog(@NotNull MappingDialogContext context, Map<String, String> map) {
        super(context.getProject());
        innerDialog = new MappingForm(context, map);
        init();
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        return innerDialog.getTopPanel();
    }
}
