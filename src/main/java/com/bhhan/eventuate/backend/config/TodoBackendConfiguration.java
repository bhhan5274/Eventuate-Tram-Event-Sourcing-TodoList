package com.bhhan.eventuate.backend.config;

import com.bhhan.eventuate.backend.TodoQueryService;
import com.bhhan.eventuate.backend.TodoQueryWorkFlow;
import com.bhhan.eventuate.backend.command.TodoCommand;
import com.bhhan.eventuate.backend.domain.TodoAggregate;
import com.bhhan.eventuate.backend.domain.TodoBulkDeleteAggregate;
import com.bhhan.eventuate.backend.domain.TodoCommandWorkflow;
import com.bhhan.eventuate.backend.domain.TodoService;
import io.eventuate.AggregateRepository;
import io.eventuate.EventuateAggregateStore;
import io.eventuate.javaclient.spring.EnableEventHandlers;
import io.eventuate.local.java.spring.javaclient.driver.EventuateDriverConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by hbh5274@gmail.com on 2020-11-11
 * Github : http://github.com/bhhan5274
 */

@Configuration
@Import({EventuateDriverConfiguration.class})
@EnableEventHandlers
public class TodoBackendConfiguration {
    @Bean
    public TodoCommandWorkflow todoCommandWorkflow(){
        return new TodoCommandWorkflow();
    }

    @Bean
    public AggregateRepository<TodoAggregate, TodoCommand> aggregateRepository(EventuateAggregateStore eventStore){
        return new AggregateRepository<>(TodoAggregate.class, eventStore);
    }

    @Bean
    public AggregateRepository<TodoBulkDeleteAggregate, TodoCommand> bulkDeleteAggregateRepository(EventuateAggregateStore eventStore){
        return new AggregateRepository<>(TodoBulkDeleteAggregate.class, eventStore);
    }

    @Bean
    public TodoService updateService(AggregateRepository<TodoAggregate, TodoCommand> aggregateRepository,
                                     AggregateRepository<TodoBulkDeleteAggregate, TodoCommand> bulkDeleteAggregateRepository){
        return new TodoService(aggregateRepository, bulkDeleteAggregateRepository);
    }

    @Bean
    public TodoQueryWorkFlow todoQueryWorkFlow(TodoQueryService todoQueryService){
        return new TodoQueryWorkFlow(todoQueryService);
    }
}
