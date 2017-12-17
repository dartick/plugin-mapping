package org.xiaoheshan.plugin.mapping.ui;

import com.intellij.ide.util.TreeClassChooser;
import com.intellij.ide.util.TreeClassChooserFactory;
import com.intellij.psi.PsiClass;
import com.intellij.ui.JavaReferenceEditorUtil;
import com.intellij.ui.RecentsManager;
import com.intellij.ui.ReferenceEditorWithBrowseButton;
import org.xiaoheshan.plugin.mapping.ui.context.PluginContext;
import org.xiaoheshan.plugin.mapping.ui.dialog.DialogAdapter;
import org.xiaoheshan.plugin.mapping.util.ArrayUtil;
import org.xiaoheshan.plugin.mapping.util.ObjectUtil;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author _Chf
 * @date 2017-12-16
 */
public abstract class BaseChooseClassForm implements DialogAdapter {

    private PluginContext context;

    public BaseChooseClassForm(PluginContext context) {
        this.context = context;
    }

    protected PluginContext getContext() {
        return context;
    }

    protected ReferenceEditorWithBrowseButton newReferenceEditor() {
        return newReferenceEditor(null);
    }

    protected ReferenceEditorWithBrowseButton newReferenceEditor(final ChooseClassListener listener) {
        final ReferenceEditorWithBrowseButton editor = JavaReferenceEditorUtil
                .createReferenceEditorWithBrowseButton(
                        null,
                        "",
                        context.getProject(),
                        true
                );
        editor.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        TreeClassChooserFactory chooserFactory = TreeClassChooserFactory.getInstance(context.getProject());
                        TreeClassChooser chooser = chooserFactory.createAllProjectScopeChooser("Choose Class");
                        chooser.showDialog();
                        PsiClass psiClass = chooser.getSelected();
                        if (ObjectUtil.isNull(psiClass)) {
                            return;
                        }
                        String className = psiClass.getQualifiedName();
                        editor.setText(className != null ? className : "");
                        if (ObjectUtil.isNoneNull(listener)) {
                            listener.afterChosen(psiClass);
                        }
                    }
                }
        );
        editor.setMinimumSize(editor.getPreferredSize());

        return editor;
    }

    protected interface ChooseClassListener {
        void afterChosen(PsiClass psiClass);
    }

}
