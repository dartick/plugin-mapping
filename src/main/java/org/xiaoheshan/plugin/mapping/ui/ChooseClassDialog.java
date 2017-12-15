/**
 * Copyright (C), 2011-2017, 微贷网.
 */
package org.xiaoheshan.plugin.mapping.ui;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.xiaoheshan.plugin.mapping.core.constant.MappingModeEnum;

import javax.swing.*;

/**
 * ${DESCRIPTION}
 *
 * @author chenhongfa 17-12-15.
 */
public class ChooseClassDialog extends DialogWrapper {

    private DialogAdapter innerDialog;

    public ChooseClassDialog(@NotNull Project project, @NotNull MappingModeEnum mode) {
        super(project);
        switch (mode) {
            case SINGLE:
                this.innerDialog = new ChooseSingleClassForm(project);
                break;
            default:
            case TWINS:
                this.innerDialog = new ChooseDoubleClassForm(project);
        }
        this.setTitle(innerDialog.getTitle());
        init();
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        return innerDialog.getTopPanel();
    }

}
