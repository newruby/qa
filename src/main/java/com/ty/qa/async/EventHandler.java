package com.ty.qa.async;

import java.util.List;

/**
 * created by TY on 2019/5/19.
 */

public interface EventHandler {
    void doHandle(EventModel model);

    List<EventType> getSupportEventTypes();
}
