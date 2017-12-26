package org.xiaoheshan.plugin.mapping.core.pipeline;

import org.xiaoheshan.plugin.mapping.core.pipeline.worker.Worker;

/**
 * @author _Chf
 * @date 2017-12-16
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
