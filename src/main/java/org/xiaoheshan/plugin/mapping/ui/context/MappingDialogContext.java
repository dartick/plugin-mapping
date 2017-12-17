package org.xiaoheshan.plugin.mapping.ui.context;

import org.xiaoheshan.plugin.mapping.core.definition.Clazz;

/**
 * @author _Chf
 * @date 2017-12-16
 */
public interface MappingDialogContext extends PluginContext {

    Clazz getOriginClazz();

    Clazz getDestinationClazz();

}
