package com.bhhan.eventuate.backend.web;

import com.bhhan.eventuate.backend.Todo;
import com.bhhan.eventuate.backend.TodoQueryService;
import com.bhhan.eventuate.backend.domain.TodoService;
import com.bhhan.eventuate.common.controller.BaseController;
import com.bhhan.eventuate.common.model.ResourceWithUrl;
import com.bhhan.eventuate.common.model.TodoInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.server.mvc.ControllerLinkBuilder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.PATCH;

/**
 * Created by hbh5274@gmail.com on 2020-11-11
 * Github : http://github.com/bhhan5274
 */

@RestController
@RequestMapping(value = "/todos")
@RequiredArgsConstructor
public class TodoController extends BaseController {
    private final TodoService todoService;
    private final TodoQueryService todoQueryService;

    @PostMapping
    public CompletableFuture<ResourceWithUrl> saveTodo(@RequestBody TodoInfo todo,
                                                       HttpServletRequest request){
        CompletableFuture<ResourceWithUrl> resourceWithUrlCompletableFuture = todoService.save(todo)
                .thenApply(e -> withRequestAttributeContext(request,
                        () -> toResource(e.getAggregate().getTodo(), e.getEntityId())));
        return resourceWithUrlCompletableFuture;
    }

    @RequestMapping(value = "/{todo-id}", method = DELETE)
    public CompletableFuture<ResourceWithUrl> deleteOneTodo(@PathVariable("todo-id") String id, HttpServletRequest request) {
        return todoService.remove(id).thenApply(e -> withRequestAttributeContext(request, () -> toResource(e.getAggregate().getTodo(), e.getEntityId())));
    }

    @RequestMapping(method = DELETE)
    public void deleteAllTodos() throws Exception {
        List<Todo> todosToDelete = todoQueryService.getAll();
        if (todosToDelete.size() > 0) {
            todoService.deleteAll(todoQueryService.getAll()
                    .stream()
                    .map(Todo::getId)
                    .collect(Collectors.toList()));
        }
    }

    @RequestMapping(value = "/{todo-id}", method = PATCH, headers = {"Content-type=application/json"})
    public CompletableFuture<ResourceWithUrl> updateTodo(@PathVariable("todo-id") String id, @RequestBody TodoInfo newTodo, HttpServletRequest request) {
        return todoService.update(id, newTodo).thenApply(e -> withRequestAttributeContext(request, () -> toResource(e.getAggregate().getTodo(), e.getEntityId()))
        );
    }

    protected ResourceWithUrl toResource(TodoInfo todo, String id) {
        ResourceWithUrl<TodoInfo> result = new ResourceWithUrl<>(todo);
        result.setId(id);
        result.setUrl(ControllerLinkBuilder.linkTo(methodOn(TodoViewController.class).getTodo(id)).withSelfRel().getHref());
        return result;
    }
}
