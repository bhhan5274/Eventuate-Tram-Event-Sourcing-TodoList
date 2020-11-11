package com.bhhan.eventuate.backend.domain;

import com.bhhan.eventuate.backend.command.DeleteTodoCommand;
import com.bhhan.eventuate.common.event.TodoDeletionRequestedEvent;
import io.eventuate.EntityWithIdAndVersion;
import io.eventuate.EventHandlerContext;
import io.eventuate.EventHandlerMethod;
import io.eventuate.EventSubscriber;

import java.util.concurrent.CompletableFuture;

/**
 * Created by hbh5274@gmail.com on 2020-11-11
 * Github : http://github.com/bhhan5274
 */

@EventSubscriber(id = "todoCommandSideEventHandlers")
public class TodoCommandWorkflow {
    @EventHandlerMethod
    public CompletableFuture<EntityWithIdAndVersion<TodoAggregate>> deleteTodo(EventHandlerContext<TodoDeletionRequestedEvent> ctx){
        String todoId = ctx.getEvent().getTodoId();
        return ctx.update(TodoAggregate.class, todoId, new DeleteTodoCommand());
    }
}
