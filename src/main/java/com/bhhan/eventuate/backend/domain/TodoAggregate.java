package com.bhhan.eventuate.backend.domain;

import com.bhhan.eventuate.backend.command.CreateTodoCommand;
import com.bhhan.eventuate.backend.command.DeleteTodoCommand;
import com.bhhan.eventuate.backend.command.TodoCommand;
import com.bhhan.eventuate.backend.command.UpdateTodoCommand;
import com.bhhan.eventuate.common.event.TodoCreateEvent;
import com.bhhan.eventuate.common.event.TodoDeleteEvent;
import com.bhhan.eventuate.common.event.TodoUpdatedEvent;
import com.bhhan.eventuate.common.model.TodoInfo;
import io.eventuate.Event;
import io.eventuate.EventUtil;
import io.eventuate.ReflectiveMutableCommandProcessingAggregate;

import java.util.Collections;
import java.util.List;

/**
 * Created by hbh5274@gmail.com on 2020-11-11
 * Github : http://github.com/bhhan5274
 */
public class TodoAggregate extends ReflectiveMutableCommandProcessingAggregate<TodoAggregate, TodoCommand> {
    private TodoInfo todo;
    private boolean deleted;

    public List<Event> process(CreateTodoCommand cmd){
        if(this.deleted){
            return Collections.emptyList();
        }

        return EventUtil.events(new TodoCreateEvent(cmd.getTodo()));
    }

    public List<Event> process(UpdateTodoCommand cmd){
        if(this.deleted){
            return Collections.emptyList();
        }
        return EventUtil.events(new TodoUpdatedEvent(cmd.getTodo()));
    }

    public List<Event> process(DeleteTodoCommand cmd){
        if(this.deleted){
            return Collections.emptyList();
        }
        return EventUtil.events(new TodoDeleteEvent());
    }

    public void apply(TodoCreateEvent event){
        this.todo = event.getTodo();
    }

    public void apply(TodoUpdatedEvent event){
        this.todo = event.getTodo();
    }

    public void apply(TodoDeleteEvent event){
        this.deleted = true;
    }

    public TodoInfo getTodo() {
        return todo;
    }
}
