package com.bhhan.eventuate.backend.domain;

import com.bhhan.eventuate.backend.command.*;
import com.bhhan.eventuate.common.model.TodoInfo;
import io.eventuate.AggregateRepository;
import io.eventuate.EntityWithIdAndVersion;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Created by hbh5274@gmail.com on 2020-11-11
 * Github : http://github.com/bhhan5274
 */

@RequiredArgsConstructor
public class TodoService {
    private final AggregateRepository<TodoAggregate, TodoCommand> aggregateRepository;
    private final AggregateRepository<TodoBulkDeleteAggregate, TodoCommand> bulkDeleteAggregateRepository;

    public CompletableFuture<EntityWithIdAndVersion<TodoAggregate>> save(TodoInfo todo){
        CompletableFuture<EntityWithIdAndVersion<TodoAggregate>> save = aggregateRepository.save(new CreateTodoCommand(todo));
        return save;
    }

    public CompletableFuture<EntityWithIdAndVersion<TodoAggregate>> remove(String id){
        return aggregateRepository.update(id, new DeleteTodoCommand());
    }

    public CompletableFuture<EntityWithIdAndVersion<TodoAggregate>> update(String id, TodoInfo newTodo){
        return aggregateRepository.update(id, new UpdateTodoCommand(id, newTodo));
    }

    public CompletableFuture<EntityWithIdAndVersion<TodoBulkDeleteAggregate>> deleteAll(List<String> ids){
        return bulkDeleteAggregateRepository.save(new DeleteTodosCommand(ids));
    }
}
