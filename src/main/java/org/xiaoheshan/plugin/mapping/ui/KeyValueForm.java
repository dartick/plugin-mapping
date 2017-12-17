package org.xiaoheshan.plugin.mapping.ui;

import com.intellij.openapi.ui.DialogWrapper;
import org.xiaoheshan.plugin.mapping.core.constant.TextConstant;
import org.xiaoheshan.plugin.mapping.ui.dialog.DialogAdapter;
import org.xiaoheshan.plugin.mapping.util.StringUtil;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * @author _Chf
 * @date 2017-12-17
 */
public class KeyValueForm implements DialogAdapter {

    private JPanel contentPanel;
    private JTextField originTextField;
    private JTextField destinationTextField;

    private DialogWrapper dialogWrapper;

    public KeyValueForm(DialogWrapper dialogWrapper) {
        this.dialogWrapper = dialogWrapper;
        EnAbleDoOkListener enAbleDoOkListener = new EnAbleDoOkListener();
        originTextField.getDocument().addDocumentListener(enAbleDoOkListener);
        destinationTextField.getDocument().addDocumentListener(enAbleDoOkListener);
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

    public String getOrigin() {
        return originTextField.getText();
    }

    public String getDestination() {
        return destinationTextField.getText();
    }

    private class EnAbleDoOkListener implements DocumentListener {

        @Override
        public void insertUpdate(DocumentEvent e) {
            isEnAbleDoOk();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            isEnAbleDoOk();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
        }

        private void isEnAbleDoOk() {
            boolean enable = !(StringUtil.isBlank(originTextField.getText())
                    || StringUtil.isBlank(destinationTextField.getText()));
            if (enable) {
                KeyValueForm.this.dialogWrapper.setOKActionEnabled(true);
            }
            else {
                KeyValueForm.this.dialogWrapper.setOKActionEnabled(false);
            }
        }
    }

}
