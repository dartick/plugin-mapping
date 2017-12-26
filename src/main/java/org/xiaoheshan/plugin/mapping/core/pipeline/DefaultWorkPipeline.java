package org.xiaoheshan.plugin.mapping.core.pipeline;

import org.xiaoheshan.plugin.mapping.core.pipeline.worker.Worker;
import org.xiaoheshan.plugin.mapping.util.ObjectUtil;

import java.util.LinkedList;
import java.util.List;

/**
 * @author _Chf
 * @date 2017-12-16
 */
public class DefaultWorkPipeline implements WorkPipeline {

    private List<Worker> workers;
    private WorkListener workListener;

    public DefaultWorkPipeline() {
        this.workers = new LinkedList<Worker>();
    }

    @Override
    public WorkPipeline addLast(Worker worker) {
        this.workers.add(worker);
        return this;
    }

    @Override
    public WorkPipeline setHandleListener(WorkListener listener) {
        this.workListener = listener;
        return this;
    }

    @Override
    public void startWork() {
        for (Worker worker : workers) {
            if (ObjectUtil.isNoneNull(workListener)) {
                workListener.workBefore(worker);
            }
            try {
                worker.work();
            }
            catch (Exception e) {
                if (ObjectUtil.isNoneNull(workListener)) {
                    workListener.workException(e);
                }
            }
            finally {
                if (ObjectUtil.isNoneNull(workListener)) {
                    workListener.workAfter(worker);
                }
            }
        }
    }

}
