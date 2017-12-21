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
public interface WorkPipeline {

    /**
     * add worker to work flow
     * @param worker
     * @return
     */
    WorkPipeline addLast(Worker worker);

    /**
     * add worker listener
     * @param listener
     * @return
     */
    WorkPipeline setHandleListener(WorkListener listener);

    /**
     * start to work
     */
    void startWork();
}
