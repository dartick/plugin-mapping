/**
 * Copyright (C), 2011-2017, 微贷网.
 */
package org.xiaoheshan.plugin.mapping.ui;

import com.intellij.ide.util.TreeClassChooser;
import com.intellij.ide.util.TreeClassChooserFactory;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiClass;
import org.jetbrains.annotations.NotNull;
import org.xiaoheshan.plugin.mapping.core.constant.TextConstant;
import org.xiaoheshan.plugin.mapping.util.ObjectUtil;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * ${DESCRIPTION}
 *
 * @author chenhongfa 17-12-15.
 */
public class ChooseSingleClassForm implements DialogAdapter {
    private JPanel contentPanel;
    private JTextField targetTextFiled;
    private JButton targetBtn;

    private Project project;
    private PsiClass targetClass;

    public ChooseSingleClassForm(@NotNull Project project) {
        this.project = project;
        this.initListener();
    }

    private void initListener() {
        targetTextFiled.setEnabled(false);

        targetBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                TreeClassChooserFactory instance = TreeClassChooserFactory.getInstance(ChooseSingleClassForm.this.project);
                TreeClassChooser chooser = instance.createAllProjectScopeChooser("Choose Target Class");
                chooser.showDialog();
                targetClass = chooser.getSelected();
                if (ObjectUtil.isNoneNull(targetClass)) {
                    targetTextFiled.setText(targetClass.getQualifiedName());
                }
            }
        });
    }


    @Override
    public JPanel getTopPanel() {
        return contentPanel;
    }

    @Override
    public String getTitle() {
        return TextConstant.PLUGIN_NAME;
    }

    @Override
    public void onOk() {

    }

    @Override
    public void onCancel() {

    }
}
