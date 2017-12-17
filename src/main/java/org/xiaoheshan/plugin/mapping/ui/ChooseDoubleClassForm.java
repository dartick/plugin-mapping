/**
 * Copyright (C), 2011-2017, 微贷网.
 */
package org.xiaoheshan.plugin.mapping.ui;

import com.intellij.ui.ReferenceEditorWithBrowseButton;
import org.xiaoheshan.plugin.mapping.core.constant.TextConstant;
import org.xiaoheshan.plugin.mapping.ui.context.PluginContext;

import javax.swing.*;

/**
 * ${DESCRIPTION}
 *
 * @author chenhongfa 17-12-15.
 */
public class ChooseDoubleClassForm extends BaseChooseClassForm {

    private JPanel contentPanel;
    private ReferenceEditorWithBrowseButton originEditor;
    private ReferenceEditorWithBrowseButton destinationEditor;

    public ChooseDoubleClassForm(PluginContext context) {
        super(context);
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


    private void createUIComponents() {
        originEditor = newReferenceEditor();
        destinationEditor = newReferenceEditor();
    }

}
