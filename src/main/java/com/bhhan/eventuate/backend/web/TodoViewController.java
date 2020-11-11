package com.bhhan.eventuate.backend.web;

import com.bhhan.eventuate.backend.Todo;
import com.bhhan.eventuate.backend.TodoQueryService;
import com.bhhan.eventuate.common.controller.BaseController;
import com.bhhan.eventuate.common.model.ResourceWithUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by hbh5274@gmail.com on 2020-11-11
 * Github : http://github.com/bhhan5274
 */

@RestController
@RequestMapping(value = "/todos")
public class TodoViewController extends BaseController {

    private final TodoQueryService queryService;

    public TodoViewController(TodoQueryService queryService) {
        this.queryService = queryService;
    }

    @RequestMapping(method = GET)
    public HttpEntity<Collection<ResourceWithUrl>> listAll() {
        List<ResourceWithUrl> resourceWithUrls = queryService.getAll().stream().map(this::toResource).collect(Collectors.toList());
        return new ResponseEntity<>(resourceWithUrls, OK);
    }

    @RequestMapping(value = "/{todo-id}", method = GET)
    public CompletableFuture<ResourceWithUrl> getTodo(@PathVariable("todo-id") String id) {
        return queryService.findById(id).thenApply(this::toResource);
    }

    private ResourceWithUrl toResource(Todo todo) {
        ResourceWithUrl<Todo> result = new ResourceWithUrl<>(todo);
        if (todo != null) {
            result.setUrl(linkTo(methodOn(TodoViewController.class).getTodo(todo.getId())).withSelfRel().getHref());
        }
        return result;
    }
}
