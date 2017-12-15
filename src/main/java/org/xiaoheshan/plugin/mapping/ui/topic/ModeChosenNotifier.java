/**
 * Copyright (C), 2011-2017, 微贷网.
 */
package org.xiaoheshan.plugin.mapping.ui.topic;

import com.intellij.openapi.project.Project;
import com.intellij.util.messages.Topic;
import org.xiaoheshan.plugin.mapping.core.constant.MappingModeEnum;

/**
 * ${DESCRIPTION}
 *
 * @author chenhongfa 17-12-15.
 */
public interface ModeChosenNotifier {

    Topic<ModeChosenNotifier> MODE_CHOSEN_TOPIC = Topic.create("ModeChosen", ModeChosenNotifier.class);

    /**
     * on chosen mode
     * @param mode
     */
    void onChosen(Project project, MappingModeEnum mode);

}
