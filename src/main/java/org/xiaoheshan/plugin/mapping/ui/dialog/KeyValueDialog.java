package org.xiaoheshan.plugin.mapping.ui.dialog;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.xiaoheshan.plugin.mapping.ui.KeyValueForm;
import org.xiaoheshan.plugin.mapping.util.ObjectUtil;
import org.xiaoheshan.plugin.mapping.util.StringUtil;

import javax.swing.*;

/**
 * @author _Chf
 * @date 2017-12-17
 */
public class KeyValueDialog extends DialogWrapper {

    private OnFinishedListener listener;
    private KeyValueForm keyValueForm;

    public KeyValueDialog(@Nullable Project project, @NotNull OnFinishedListener listener) {
        super(project);
        this.listener = listener;
        this.keyValueForm = new KeyValueForm(this);
        this.setOKActionEnabled(false);
        init();
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        return keyValueForm.getTopPanel();
    }

    @Override
    protected void doOKAction() {
        super.doOKAction();
        keyValueForm.onOk();
        if (ObjectUtil.isNoneNull(listener)) {
            listener.onFinished(keyValueForm.getOrigin(), keyValueForm.getDestination());
        }
    }

    public interface OnFinishedListener {
        void onFinished(String key, String value);
    }



}
