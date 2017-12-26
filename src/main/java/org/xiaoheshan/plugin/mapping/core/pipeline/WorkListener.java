package org.xiaoheshan.plugin.mapping.core.pipeline;

import org.xiaoheshan.plugin.mapping.core.pipeline.worker.Worker;

/**
 * @author _Chf
 * @date 2017-12-16
 */
public interface WorkListener {

    void workBefore(Worker worker);

    void workAfter(Worker worker);

    void workException(Exception e);

}
