package org.xiaoheshan.plugin.mapping.ui;

import com.intellij.openapi.ui.ComboBox;
import com.intellij.ui.AnActionButton;
import com.intellij.ui.AnActionButtonRunnable;
import com.intellij.ui.ToolbarDecorator;
import com.intellij.ui.table.JBTable;
import org.xiaoheshan.plugin.mapping.core.constant.TextConstant;
import org.xiaoheshan.plugin.mapping.core.definition.Clazz;
import org.xiaoheshan.plugin.mapping.core.definition.Field;
import org.xiaoheshan.plugin.mapping.ui.context.MappingDialogContext;
import org.xiaoheshan.plugin.mapping.ui.dialog.DialogAdapter;
import org.xiaoheshan.plugin.mapping.ui.dialog.KeyValueDialog;
import org.xiaoheshan.plugin.mapping.ui.table.DestinationTableCellEditor;
import org.xiaoheshan.plugin.mapping.ui.table.MappingModel;
import org.xiaoheshan.plugin.mapping.ui.table.MappingTableModel;
import org.xiaoheshan.plugin.mapping.util.ClassUtil;

import javax.swing.*;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author _Chf
 * @date 2017-12-15
 */
public class MappingForm implements DialogAdapter {

    private JPanel contentPanel;
    private JComboBox templateComboBox;
    private JCheckBox showInheritedFieldsCheckBox;
    private JPanel mappingPanel;

    private MappingTableModel mappingTableModel;
    private MutableComboBoxModel<String> destinationComboBoxModel;
    private MappingDialogContext context;
    private Field[] originSupperFields;
    private Field[] destinationSupperFields;

    public MappingForm(MappingDialogContext context, Map<String, String> map) {
        this.context = context;
        this.originSupperFields = ClassUtil.parseSupperFields(context.getOriginClazz());
        this.destinationSupperFields = ClassUtil.parseSupperFields(context.getDestinationClazz());
        this.injectView(context, map);
    }

    private void injectView(MappingDialogContext context, Map<String, String> map) {
        List<MappingModel> tableData = new ArrayList<MappingModel>();
        Clazz originClazz = context.getOriginClazz();
        Clazz destinationClazz = context.getDestinationClazz();

        for (Field field : originClazz.getFields()) {
            String dest = map.get(field.getName());
            tableData.add(new MappingModel(field.getName(), dest == null ? "" : dest));
        }

        Field[] destinationClazzFields = destinationClazz.getFields();
        String[] comboBoxData = new String[destinationClazzFields.length];
        for (int i = 0; i < destinationClazzFields.length; i++) {
            comboBoxData[i] = destinationClazzFields[i].getName();
        }

        this.mappingTableModel = new MappingTableModel(tableData);
        JTable mappingTable = new JBTable(mappingTableModel);
        mappingTable.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        mappingTable.setDragEnabled(false);
        TableColumnModel columnModel = mappingTable.getColumnModel();

        TableColumn destinationColumn = columnModel.getColumn(MappingTableModel.DESTINATION_COLUMN_INDEX);
        this.destinationComboBoxModel = new DefaultComboBoxModel<String>(comboBoxData);
        JComboBox<String> comboBox = new ComboBox<String>(destinationComboBoxModel);
        comboBox.setEditable(true);
        comboBox.setMinimumSize(comboBox.getPreferredSize());

        DestinationTableCellEditor cellEditor = new DestinationTableCellEditor(comboBox, mappingTableModel);
        destinationColumn.setCellEditor(cellEditor);

        TableColumn originColumn = columnModel.getColumn(MappingTableModel.ORIGIN_COLUMN_INDEX);
        JTextField field = new JTextField();
        field.setEditable(false);
        originColumn.setCellEditor(new DefaultCellEditor(field));

        JPanel tablePanel = ToolbarDecorator.createDecorator(mappingTable)
                .setAddAction(new MappingAddRunnable())
                .createPanel();

        mappingPanel.add(tablePanel, BorderLayout.CENTER);

        showInheritedFieldsCheckBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (showInheritedFieldsCheckBox.isSelected()) {

                } else {

                }
            }
        });

    }

    private void showInherited() {

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
        //do nothing
    }

    private class MappingAddRunnable implements AnActionButtonRunnable, KeyValueDialog.OnFinishedListener {

        @Override
        public void run(AnActionButton anActionButton) {
            new KeyValueDialog(context.getProject(), this).show();
        }

        @Override
        public void onFinished(String key, String value) {
            mappingTableModel.addMaping(key, value);
        }
    }

}
