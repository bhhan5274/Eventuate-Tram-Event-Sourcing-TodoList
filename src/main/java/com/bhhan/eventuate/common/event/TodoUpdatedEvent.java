package com.bhhan.eventuate.common.event;

import com.bhhan.eventuate.common.model.TodoInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by hbh5274@gmail.com on 2020-11-11
 * Github : http://github.com/bhhan5274
 */

@Getter
@Setter
@NoArgsConstructor
public class TodoUpdatedEvent implements TodoEvent{
    private TodoInfo todo;

    public TodoUpdatedEvent(TodoInfo todo) {
        this.todo = todo;
    }
}
