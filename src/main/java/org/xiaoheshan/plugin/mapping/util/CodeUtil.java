package org.xiaoheshan.plugin.mapping.util;

import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiJavaFile;
import com.intellij.psi.codeStyle.CodeStyleManager;
import com.intellij.psi.codeStyle.JavaCodeStyleManager;

/**
 * @author _Chf
 * @date 2017-12-16
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
