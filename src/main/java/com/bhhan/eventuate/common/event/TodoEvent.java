package com.bhhan.eventuate.common.event;

import io.eventuate.Event;
import io.eventuate.EventEntity;

/**
 * Created by hbh5274@gmail.com on 2020-11-11
 * Github : http://github.com/bhhan5274
 */

@EventEntity(entity = "com.bhhan.eventuate.backend.domain.TodoAggregate")
public interface TodoEvent extends Event {
}
