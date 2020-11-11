package com.bhhan.eventuate.common.event;

import io.eventuate.Event;
import io.eventuate.EventEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by hbh5274@gmail.com on 2020-11-11
 * Github : http://github.com/bhhan5274
 */

@EventEntity(entity = "com.bhhan.eventuate.backend.domain.TodoBulkDeleteAggregate")
@Getter
@Setter
@NoArgsConstructor
public class TodoDeletionRequestedEvent implements Event {
    private String todoId;

    public TodoDeletionRequestedEvent(String todoId) {
        this.todoId = todoId;
    }
}
