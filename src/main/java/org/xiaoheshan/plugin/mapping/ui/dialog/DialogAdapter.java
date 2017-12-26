package org.xiaoheshan.plugin.mapping.ui.dialog;

import javax.swing.*;

/**
 * @author _Chf
 * @date 2017-12-16
 */
public interface DialogAdapter {

    /**
     * get the top panel
     * @return
     */
    JPanel getTopPanel();

    /**
     * get the dialog title
     * @return
     */
    String getTitle();

    /**
     * do while clicked OK
     */
    void onOk();

    /**
     * do while clicked Cancel
     */
    void onCancel();
}
