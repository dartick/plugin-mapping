/**
 * Copyright (C), 2011-2017, 微贷网.
 */
package org.xiaoheshan.plugin.mapping.core.pipeline;

import org.xiaoheshan.plugin.mapping.core.pipeline.handler.Handler;

/**
 * ${DESCRIPTION}
 *
 * @author chenhongfa 17-12-13.
 */
public interface WorkPipeline {

    /**
     * add handler to work flow
     * @param handler
     * @return
     */
    WorkPipeline addLast(Handler handler);

    /**
     * add handler listener
     * @param listener
     * @return
     */
    WorkPipeline setHandleListener(HandleListener listener);

    /**
     * start to work
     */
    void startWork();
}
