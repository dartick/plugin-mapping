/**
 * Copyright (C), 2011-2017, 微贷网.
 */
package org.xiaoheshan.plugin.mapping.ui;

import com.intellij.psi.JavaPsiFacade;
import com.intellij.psi.PsiClass;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.ui.ReferenceEditorWithBrowseButton;
import org.xiaoheshan.plugin.mapping.core.constant.TextConstant;
import org.xiaoheshan.plugin.mapping.core.definition.Clazz;
import org.xiaoheshan.plugin.mapping.core.definition.EmptyClazz;
import org.xiaoheshan.plugin.mapping.core.definition.Field;
import org.xiaoheshan.plugin.mapping.core.definition.Method;
import org.xiaoheshan.plugin.mapping.ui.context.DefaultMappingDialogContext;
import org.xiaoheshan.plugin.mapping.ui.context.MappingDialogContext;
import org.xiaoheshan.plugin.mapping.ui.context.PluginContext;
import org.xiaoheshan.plugin.mapping.ui.dialog.MappingDialog;
import org.xiaoheshan.plugin.mapping.util.ClassUtil;
import org.xiaoheshan.plugin.mapping.util.ObjectUtil;

import javax.swing.*;
import java.util.HashMap;

/**
 * ${DESCRIPTION}
 *
 * @author chenhongfa 17-12-15.
 */
public class ChooseSingleClassForm extends BaseChooseClassForm {

    private JPanel contentPanel;
    private ReferenceEditorWithBrowseButton originEditor;

    public ChooseSingleClassForm(PluginContext context) {
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
        String originName = originEditor.getText();
        GlobalSearchScope scope = GlobalSearchScope.allScope(getContext().getProject());
        PsiClass originClass = JavaPsiFacade.getInstance(getContext().getProject()).findClass(originName, scope);
        if (ObjectUtil.isNoneNull(originClass)) {
            Clazz originClazz = ClassUtil.parseClazz(originClass);
            MappingDialogContext mappingDialogContext = new DefaultMappingDialogContext(
                    getContext(),
                    originClazz,
                    EmptyClazz.INSTANCE
            );
            new MappingDialog(mappingDialogContext, new HashMap<String, String>()).show();
        }
    }

    @Override
    public void onCancel() {
        //do nothing
    }

    private void createUIComponents() {
        originEditor = newReferenceEditor();
    }

}
