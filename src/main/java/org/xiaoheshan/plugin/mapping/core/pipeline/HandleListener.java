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
public interface HandleListener {

    void handleBefore(Handler handler);

    void handleAfter(Handler handler);

    void handleException(Exception e);

}
