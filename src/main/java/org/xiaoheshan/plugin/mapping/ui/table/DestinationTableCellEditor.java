package org.xiaoheshan.plugin.mapping.ui.table;

import com.intellij.openapi.ui.ComboBox;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * @author _Chf
 * @date 2017-12-17
 */
public class DestinationTableCellEditor extends DefaultCellEditor {

    private AfterEditedListener afterEditedListener;
    private TableItemListener itemListener;

    public DestinationTableCellEditor(JComboBox comboBox) {
        super(comboBox);
        if (comboBox instanceof ComboBox) {
            ((ComboBox) comboBox).registerTableCellEditor(this);
        }
        this.itemListener = new TableItemListener();
        comboBox.addItemListener(itemListener);
    }

    public DestinationTableCellEditor(JComboBox comboBox, AfterEditedListener afterEditedListener) {
        this(comboBox);
        this.afterEditedListener = afterEditedListener;
    }

    public void setAfterEditedListener(AfterEditedListener afterEditedListener) {
        this.afterEditedListener = afterEditedListener;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        itemListener.editRow = row;
        itemListener.editColumn = column;
        return super.getTableCellEditorComponent(table, value, isSelected, row, column);
    }

    private class TableItemListener implements ItemListener {
        int editRow;
        int editColumn;
        @Override
        public void itemStateChanged(ItemEvent e) {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                afterEditedListener.afterEdited(editRow, editColumn, e.getItem());
            }
            DestinationTableCellEditor.super.stopCellEditing();
        }
    }

    public interface AfterEditedListener {
        void afterEdited(int row, int column, Object value);
    }

}
