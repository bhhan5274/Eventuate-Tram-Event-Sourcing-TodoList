package com.bhhan.eventuate.backend;

import io.eventuate.CompletableFutureUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * Created by hbh5274@gmail.com on 2020-11-11
 * Github : http://github.com/bhhan5274
 */

@Service
@RequiredArgsConstructor
public class TodoQueryService {
    private final TodoRepository todoRepository;

    public Todo save(Todo todo){
        return todoRepository.save(todo);
    }

    public List<Todo> getAll(){
        return todoRepository.findAll();
    }

    public void remove(String id){
        todoRepository.deleteById(id);
    }

    public void removeAll(){
        todoRepository.deleteAll();
    }

    public CompletableFuture<Todo> findById(String todoId){
        Optional<Todo> todo = todoRepository.findById(todoId);

        return todo.map(CompletableFuture::completedFuture).orElseGet(() -> CompletableFutureUtil.failedFuture(new NoSuchElementException("No todo with given id found")));
    }
}
