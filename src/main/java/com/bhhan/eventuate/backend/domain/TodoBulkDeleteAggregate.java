package com.bhhan.eventuate.backend.domain;

import com.bhhan.eventuate.backend.command.DeleteTodosCommand;
import com.bhhan.eventuate.backend.command.TodoCommand;
import com.bhhan.eventuate.common.event.TodoDeletionRequestedEvent;
import io.eventuate.Event;
import io.eventuate.ReflectiveMutableCommandProcessingAggregate;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by hbh5274@gmail.com on 2020-11-11
 * Github : http://github.com/bhhan5274
 */
public class TodoBulkDeleteAggregate extends ReflectiveMutableCommandProcessingAggregate<TodoBulkDeleteAggregate, TodoCommand> {
    public List<Event> process(DeleteTodosCommand cmd){
        return cmd.getIds()
                .stream()
                .map(TodoDeletionRequestedEvent::new)
                .collect(Collectors.toList());
    }

    public void apply(TodoDeletionRequestedEvent event){

    }
}
