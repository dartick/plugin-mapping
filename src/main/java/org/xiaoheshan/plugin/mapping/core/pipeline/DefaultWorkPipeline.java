/**
 * Copyright (C), 2011-2017, 微贷网.
 */
package org.xiaoheshan.plugin.mapping.core.pipeline;

import org.xiaoheshan.plugin.mapping.core.pipeline.handler.Handler;
import org.xiaoheshan.plugin.mapping.util.ObjectUtil;

import java.util.LinkedList;
import java.util.List;

/**
 * ${DESCRIPTION}
 *
 * @author chenhongfa 17-12-13.
 */
public class DefaultWorkPipeline implements WorkPipeline {

    private List<Handler> handlers;
    private HandleListener handleListener;

    public DefaultWorkPipeline() {
        this.handlers = new LinkedList<Handler>();
    }

    @Override
    public WorkPipeline addLast(Handler handler) {
        this.handlers.add(handler);
        return this;
    }

    @Override
    public WorkPipeline setHandleListener(HandleListener listener) {
        this.handleListener = listener;
        return this;
    }

    @Override
    public void startWork() {
        for (Handler handler : handlers) {
            if (ObjectUtil.isNoneNull(handleListener)) {
                handleListener.handleBefore(handler);
            }
            try {
                handler.handle();
            }
            catch (Exception e) {
                if (ObjectUtil.isNoneNull(handleListener)) {
                    handleListener.handleException(e);
                }
            }
            finally {
                if (ObjectUtil.isNoneNull(handleListener)) {
                    handleListener.handleAfter(handler);
                }
            }
        }
    }

}
