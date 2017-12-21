package org.xiaoheshan.plugin.mapping.ui.designer;

import com.intellij.ide.highlighter.JavaFileType;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.application.Result;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.EditorFactory;
import com.intellij.openapi.fileTypes.FileTypeManager;
import com.intellij.openapi.ui.ComboBox;
import com.intellij.openapi.ui.Messages;
import com.intellij.ui.AnActionButton;
import com.intellij.ui.AnActionButtonRunnable;
import com.intellij.ui.JBSplitter;
import com.intellij.ui.ToolbarDecorator;
import com.intellij.ui.table.JBTable;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import org.jetbrains.annotations.NotNull;
import org.xiaoheshan.plugin.mapping.core.constant.VariableEnum;
import org.xiaoheshan.plugin.mapping.core.generator.DefaultTemplateMaterial;
import org.xiaoheshan.plugin.mapping.core.generator.TemplateGenerator;
import org.xiaoheshan.plugin.mapping.core.generator.TemplateMaterial;
import org.xiaoheshan.plugin.mapping.core.mapper.DefaultMapParser;
import org.xiaoheshan.plugin.mapping.core.constant.TextConstant;
import org.xiaoheshan.plugin.mapping.core.meta.Clazz;
import org.xiaoheshan.plugin.mapping.core.meta.EmptyClazz;
import org.xiaoheshan.plugin.mapping.core.meta.Field;
import org.xiaoheshan.plugin.mapping.ui.combobox.TemplateComboBoxModel;
import org.xiaoheshan.plugin.mapping.ui.context.MappingDialogContext;
import org.xiaoheshan.plugin.mapping.ui.dialog.DialogAdapter;
import org.xiaoheshan.plugin.mapping.ui.dialog.KeyValueDialog;
import org.xiaoheshan.plugin.mapping.ui.combobox.DestinationComboBoxModel;
import org.xiaoheshan.plugin.mapping.ui.model.TemplateModel;
import org.xiaoheshan.plugin.mapping.ui.table.DestinationTableCellEditor;
import org.xiaoheshan.plugin.mapping.ui.model.MappingModel;
import org.xiaoheshan.plugin.mapping.ui.table.MappingTableModel;
import org.xiaoheshan.plugin.mapping.util.ClassUtil;
import org.xiaoheshan.plugin.mapping.util.CodeUtil;
import org.xiaoheshan.plugin.mapping.util.ObjectUtil;
import org.xiaoheshan.plugin.mapping.util.TemplateUtil;

import javax.swing.*;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author _Chf
 * @date 2017-12-15
 */
public class MappingForm implements DialogAdapter {

    private JPanel contentPanel;
    private JBSplitter splitter;
    private JComboBox<TemplateModel> templateComboBox;
    private JCheckBox showInheritedFieldsCheckBox;
    private JPanel mappingPanel;
    private JPanel editorPanel;
    private Editor templateEditor;

    private MappingTableModel mappingTableModel;
    private DestinationComboBoxModel destinationComboBoxModel;

    private MappingDialogContext context;
    private Clazz originClazz;
    private Clazz destinationClazz;
    private Map<String, String> supperMapFields;
    private List<String> destinationSupperFields;

    public MappingForm(MappingDialogContext context) {
        this.context = context;
        if (ObjectUtil.isNull(context.getOriginPsiClass())) {
            this.originClazz = EmptyClazz.INSTANCE;
        } else {
            this.originClazz = ClassUtil.parseClazz(context.getOriginPsiClass());
        }
        if (ObjectUtil.isNull(context.getDestinationPsiClass())) {
            this.destinationClazz = EmptyClazz.INSTANCE;
        } else {
            this.destinationClazz = ClassUtil.parseClazz(context.getDestinationPsiClass());
        }
        String[] originFields = ClassUtil.parseFieldNames(this.originClazz.getFields());
        String[] destinationFields = ClassUtil.parseFieldNames(this.destinationClazz.getFields());

        Map<String, String> map = new DefaultMapParser<String, String>()
                .parse(originFields, destinationFields);

        this.injectView();
        this.initEditor(context);
        this.initFields(map);
        this.injectTable(map);
        this.injectCheckBox();
        this.injectComboBox();
    }

    private void injectView() {
        MappingTablePanel tablePanel = new MappingTablePanel();
        this.showInheritedFieldsCheckBox = tablePanel.getShowInheritedCheckBox();
        this.mappingPanel = tablePanel.getMappingPanel();

        MappingEditorPanel mappingEditorPanel = new MappingEditorPanel();
        this.templateComboBox = mappingEditorPanel.getTemplateComboBox();
        this.editorPanel = mappingEditorPanel.getEditorPanel();

        splitter.setFirstComponent(tablePanel.getContentPanel());
        splitter.setSecondComponent(mappingEditorPanel.getContentPanel());
    }

    private void initFields(Map<String, String> map) {
        String[] originSupperFields = ClassUtil.parseFieldNames(
                ClassUtil.parseSupperFields(this.originClazz));
        Field[] destinationFields = ClassUtil.parseSupperFields(this.destinationClazz);
        this.destinationSupperFields = new ArrayList<String>(destinationFields.length);
        for (Field field : destinationFields) {
            if (map.containsKey(field.getName())) {
                continue;
            }
            this.destinationSupperFields.add(field.getName());
        }
        this.supperMapFields = new DefaultMapParser<String, String>().parse(
                originSupperFields,
                destinationSupperFields.toArray(new String[destinationSupperFields.size()])
        );
        for (String field : originSupperFields) {
            if (this.supperMapFields.containsKey(field)) {
                continue;
            }
            this.supperMapFields.put(field, "");
        }
        for (Field field : this.originClazz.getFields()) {
            this.supperMapFields.remove(field.getName());
        }
    }

    private void injectCheckBox() {
        showInheritedFieldsCheckBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (showInheritedFieldsCheckBox.isSelected()) {
                    showInherited();
                } else {
                    hideInherited();
                }
            }
        });
    }

    private void injectTable(Map<String, String> map) {
        List<MappingModel> tableData = new ArrayList<MappingModel>();

        for (Field field : this.originClazz.getFields()) {
            String dest = map.get(field.getName());
            tableData.add(new MappingModel(field.getName(), dest == null ? "" : dest));
        }

        Field[] destinationClazzFields = this.destinationClazz.getFields();
        List<String> comboBoxData = new ArrayList<String>(destinationClazzFields.length);
        for (Field field : destinationClazzFields) {
            comboBoxData.add(field.getName());
        }

        this.mappingTableModel = new MappingTableModel(tableData);
        JTable mappingTable = new JBTable(mappingTableModel);
        mappingTable.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        mappingTable.setDragEnabled(false);
        TableColumnModel columnModel = mappingTable.getColumnModel();

        TableColumn destinationColumn = columnModel.getColumn(MappingTableModel.DESTINATION_COLUMN_INDEX);
        this.destinationComboBoxModel = new DestinationComboBoxModel(comboBoxData);
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
    }

    private void injectComboBox() {
        Map<String, String> templates = TemplateUtil.getTemplates();
        List<TemplateModel> data = new ArrayList<TemplateModel>();
        for (Map.Entry<String, String> entry : templates.entrySet()) {
            data.add(new TemplateModel(entry.getKey(), entry.getValue()));
        }
        templateComboBox.setModel(new TemplateComboBoxModel(data));
        templateComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    TemplateModel item = (TemplateModel) e.getItem();
                    ApplicationManager.getApplication()
                            .runWriteAction(new Runnable() {
                                @Override
                                public void run() {
                                    templateEditor.getDocument().setText(item.getContent());
                                }
                            });
                }
            }
        });
        templateComboBox.setSelectedIndex(0);
    }

    private void initEditor(MappingDialogContext context) {
        EditorFactory factory = EditorFactory.getInstance();
        Document velocityTemplate = factory.createDocument("");
        templateEditor = factory.createEditor(
                velocityTemplate, context.getProject(),
                FileTypeManager.getInstance().getFileTypeByExtension("vm"),
                false);
        editorPanel.add(templateEditor.getComponent(), BorderLayout.CENTER);
    }

    private void showInherited() {
        mappingTableModel.addMapping(supperMapFields);
        destinationComboBoxModel.addDestinations(destinationSupperFields);
    }

    private void hideInherited() {
        mappingTableModel.removeMapping(supperMapFields.keySet());
        destinationComboBoxModel.removeDestinations(destinationSupperFields);
    }

    private Map<String, String> extractMapping() {
        Map<String, String> map = new HashMap<String, String>();
        for (MappingModel model : mappingTableModel.getData()) {
            map.put(model.getOrigin(), model.getDestination());
        }
        return map;
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
        String templateText = templateEditor.getDocument().getText();
        TemplateMaterial templateMaterial = new DefaultTemplateMaterial(templateText);
        templateMaterial.addVariable(VariableEnum.MAP.getName(), extractMapping());
        templateMaterial.addVariable(VariableEnum.ORIGIN.getName(), this.originClazz);
        templateMaterial.addVariable(VariableEnum.DESTINATION.getName(), this.destinationClazz);
        try {
            String code = new TemplateGenerator().generate(templateMaterial);
            new WriteCommandAction(context.getProject()) {
                @Override
                protected void run(@NotNull Result result) throws Throwable {
                    CodeUtil.appendCode(context.getProject(), context.getEditor(), code);
                    CodeUtil.formatJavaCode(
                            context.getProject(),
                            context.getTargetFile(),
                            context.getOriginPsiClass(),
                            context.getDestinationPsiClass());
                }
            }.execute();
        } catch (Exception e) {
            Messages.showErrorDialog(context.getProject(), "error template code", "Template Error");
        }
    }

    @Override
    public void onCancel() {
        //do nothing
    }

    private void createUIComponents() {
        templateComboBox = new ComboBox<TemplateModel>();
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        splitter = new JBSplitter();
        contentPanel.add(splitter, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return contentPanel;
    }

    private class MappingAddRunnable implements AnActionButtonRunnable, KeyValueDialog.OnFinishedListener {
        @Override
        public void run(AnActionButton anActionButton) {
            new KeyValueDialog(context.getProject(), this).show();
        }

        @Override
        public void onFinished(String key, String value) {
            mappingTableModel.addMapping(key, value);
        }
    }

}
