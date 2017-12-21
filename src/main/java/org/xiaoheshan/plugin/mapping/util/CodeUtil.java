/**
 * Copyright (C), 2011-2017, 微贷网.
 */
package org.xiaoheshan.plugin.mapping.util;

import com.intellij.codeInsight.actions.ReformatCodeProcessor;
import com.intellij.codeInsight.daemon.impl.JavaReferenceImporter;
import com.intellij.lang.Language;
import com.intellij.lang.java.JavaLanguage;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiJavaFile;
import com.intellij.psi.codeStyle.CodeStyleManager;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.intellij.psi.codeStyle.CodeStyleSettingsManager;
import com.intellij.psi.codeStyle.JavaCodeStyleManager;
import com.intellij.psi.impl.source.codeStyle.CodeFormatterFacade;

/**
 * ${DESCRIPTION}
 *
 * @author chenhongfa 17-12-20.
 */
public final class CodeUtil {

    private CodeUtil() {
    }

    /**
     * should call in write command
     * @param editor
     * @param code
     */
    public static void appendCode(Project project, Editor editor, String code) {
        Document document = editor.getDocument();
        document.insertString(editor.getCaretModel().getOffset(), code);
        PsiDocumentManager.getInstance(project)
                .commitDocument(document);
    }

    /**
     * should call in write command
     * @param project
     * @param psiFile
     * @param importList
     */
    public static void formatJavaCode(Project project, PsiFile psiFile, PsiClass... importList) {
        JavaCodeStyleManager styleManager = JavaCodeStyleManager.getInstance(project);
        for (PsiClass psiClass : importList) {
            if (ObjectUtil.isNoneNull(psiClass)) {
                styleManager.addImport((PsiJavaFile) psiFile, psiClass);
            }
        }
        styleManager.optimizeImports(psiFile);

        CodeStyleManager codeStyleManager = CodeStyleManager.getInstance(project);
        codeStyleManager.reformat(psiFile);
    }

}