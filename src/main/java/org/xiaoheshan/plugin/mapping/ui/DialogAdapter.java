/**
 * Copyright (C), 2011-2017, 微贷网.
 */
package org.xiaoheshan.plugin.mapping.ui;

import javax.swing.*;

/**
 * ${DESCRIPTION}
 *
 * @author chenhongfa 17-12-15.
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
