package org.xiaoheshan.plugin.mapping.ui.context;

import com.intellij.psi.PsiClass;
import org.xiaoheshan.plugin.mapping.core.meta.Clazz;

/**
 * @author _Chf
 * @date 2017-12-16
 */
public interface MappingDialogContext extends PluginContext {

    PsiClass getOriginPsiClass();

    PsiClass getDestinationPsiClass();
}
