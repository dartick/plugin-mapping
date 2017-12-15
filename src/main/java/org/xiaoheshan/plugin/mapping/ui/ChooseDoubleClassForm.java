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
public class ChooseDoubleClassForm implements DialogAdapter {

    private JPanel contentPanel;
    private JTextField originTextFiled;
    private JTextField destTextFiled;
    private JButton originBtn;
    private JButton destBtn;

    private Project project;
    private PsiClass originClass;
    private PsiClass destinationClass;

    public ChooseDoubleClassForm(@NotNull Project project) {
        this.project = project;
        this.initListener();
    }

    private void initListener() {

        originTextFiled.setEnabled(false);
        destTextFiled.setEnabled(false);

        originBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                TreeClassChooserFactory instance = TreeClassChooserFactory.getInstance(ChooseDoubleClassForm.this.project);
                TreeClassChooser chooser = instance.createAllProjectScopeChooser("Choose Origin Class");
                chooser.showDialog();
                originClass = chooser.getSelected();
                if (ObjectUtil.isNoneNull(originClass)) {
                    originTextFiled.setText(originClass.getQualifiedName());
                }
            }
        });

        destBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                TreeClassChooserFactory instance = TreeClassChooserFactory.getInstance(ChooseDoubleClassForm.this.project);
                TreeClassChooser chooser = instance.createAllProjectScopeChooser("Choose Destination Class");
                chooser.showDialog();
                destinationClass = chooser.getSelected();
                if (ObjectUtil.isNoneNull(destinationClass)) {
                    destTextFiled.setText(destinationClass.getQualifiedName());
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
