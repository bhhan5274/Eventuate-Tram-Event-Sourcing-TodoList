package com.bhhan.eventuate.backend;

import com.bhhan.eventuate.common.event.TodoCreateEvent;
import com.bhhan.eventuate.common.event.TodoDeleteEvent;
import com.bhhan.eventuate.common.event.TodoUpdatedEvent;
import io.eventuate.DispatchedEvent;
import io.eventuate.EventHandlerMethod;
import io.eventuate.EventSubscriber;
import lombok.RequiredArgsConstructor;

/**
 * Created by hbh5274@gmail.com on 2020-11-11
 * Github : http://github.com/bhhan5274
 */

@EventSubscriber(id = "todoQuerySideEventHandlers")
@RequiredArgsConstructor
public class TodoQueryWorkFlow {
    private final TodoQueryService todoQueryService;

    @EventHandlerMethod
    public void create(DispatchedEvent<TodoCreateEvent> de){
        Todo todo = new Todo(de.getEvent().getTodo());
        todo.setId(de.getEntityId());
        todoQueryService.save(todo);
    }

    @EventHandlerMethod
    public void delete(DispatchedEvent<TodoDeleteEvent> de){
        todoQueryService.remove(de.getEntityId());
    }

    @EventHandlerMethod
    public void update(DispatchedEvent<TodoUpdatedEvent> de){
        Todo todo = new Todo(de.getEvent().getTodo());
        todo.setId(de.getEntityId());

        todoQueryService.save(todo);
    }
}
