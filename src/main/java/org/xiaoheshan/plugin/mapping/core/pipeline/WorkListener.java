/**
 * Copyright (C), 2011-2017, 微贷网.
 */
package org.xiaoheshan.plugin.mapping.core.pipeline;

import org.xiaoheshan.plugin.mapping.core.pipeline.worker.Worker;

/**
 * ${DESCRIPTION}
 *
 * @author chenhongfa 17-12-13.
 */
public interface WorkListener {

    void workBefore(Worker worker);

    void workAfter(Worker worker);

    void workException(Exception e);

}
