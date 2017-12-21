package org.xiaoheshan.plugin.mapping.util;

import com.intellij.psi.*;
import org.jetbrains.annotations.NotNull;
import org.xiaoheshan.plugin.mapping.core.meta.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author _Chf
 * @date 2017-12-17
 */
public final class ClassUtil {

    private ClassUtil() {
    }

    public static Clazz parseClazz(@NotNull PsiClass psiClass) {
        String type = psiClass.getName();
        Field[] fields = parseFields(psiClass);
        Field[] allFields = parseAllFields(psiClass);
        Method[] methods = parseMethods(psiClass);
        Method[] allMethods = parseAllMethods(psiClass);
        return new DefaultClazz(type , fields, allFields, methods, allMethods);
    }

    public static Field[] parseFields(@NotNull PsiClass psiClass) {
        PsiField[] psiFields = psiClass.getFields();
        Field[] fields = new Field[psiFields.length];
        parseFieldInternal(fields, psiFields);
        return fields;
    }

    public static Field[] parseAllFields(@NotNull PsiClass psiClass) {
        PsiField[] psiFields = psiClass.getAllFields();
        Field[] fields = new Field[psiFields.length];
        parseFieldInternal(fields, psiFields);
        return fields;
    }

    public static Method[] parseMethods(@NotNull PsiClass psiClass) {
        PsiMethod[] psiMethods = psiClass.getMethods();
        Method[] methods = new Method[psiMethods.length];
        parseMethodInternal(methods, psiMethods);
        return methods;
    }

    public static Method[] parseAllMethods(@NotNull PsiClass psiClass) {
        PsiMethod[] psiMethods = psiClass.getAllMethods();
        Method[] methods = new Method[psiMethods.length];
        parseMethodInternal(methods, psiMethods);
        return methods;
    }

    public static Field[] parseSupperFields(@NotNull Clazz clazz) {
        Field[] fields = clazz.getFields();
        Field[] allFields = clazz.getAllFields();
        List<Field> result = new ArrayList<Field>();
        for (Field allField : allFields) {
            for (Field field : fields) {
                if (allField.getName().equals(field.getName())
                        && allField.getType().equals(field.getType())) {
                    break;
                }
                result.add(allField);
            }
        }
        return result.toArray(new Field[result.size()]);
    }

    public static String[] parseFieldNames(@NotNull Clazz clazz) {
        return parseFieldNames(clazz.getFields());
    }

    public static String[] parseFieldNames(@NotNull Field[] fields) {
        String[] names = new String[fields.length];
        for (int i = 0; i < fields.length; i++) {
            names[i] = fields[i].getName();
        }
        return names;
    }

    private static void parseFieldInternal(Field[] fields, PsiField[] psiFields) {
        assert fields.length == psiFields.length;
        for (int i = 0; i < psiFields.length; i++) {
            PsiField psiField = psiFields[i];
            String name = psiField.getName();
            String type = psiField.getType().getPresentableText();
            Field field = new DefaultField(name, type);
            fields[i] = field;
        }
    }

    private static void parseMethodInternal(Method[] methods, PsiMethod[] psiMethods) {
        assert methods.length == psiMethods.length;
        for (int i = 0;i < psiMethods.length;i++) {
            PsiMethod psiMethod = psiMethods[i];

            String name = psiMethod.getName();
            String returnType = psiMethod.getReturnType() == null ? "" : psiMethod.getReturnType().getCanonicalText();
            PsiParameter[] parameters = psiMethod.getParameterList().getParameters();
            String[] params = new String[parameters.length];
            for (int k = 0; k < parameters.length; k++) {
                params[k] = parameters[k].getType().getPresentableText();
            }

            PsiClassType[] psiThrowz = psiMethod.getThrowsList().getReferencedTypes();
            String[] throwz = new String[psiThrowz.length];
            for (int k = 0; k < psiThrowz.length; k++) {
                throwz[k] = psiThrowz[k].getClassName();
            }

            DefaultMethod method = new DefaultMethod(name, returnType, params, throwz);
            methods[i] = method;
        }
    }
}
