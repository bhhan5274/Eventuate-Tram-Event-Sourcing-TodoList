package com.bhhan.eventuate.backend.command;

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
public class UpdateTodoCommand implements TodoCommand {
    private String id;
    private TodoInfo todo;

    public UpdateTodoCommand(String id, TodoInfo todo) {
        this.id = id;
        this.todo = todo;
    }
}
