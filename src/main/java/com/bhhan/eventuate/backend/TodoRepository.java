package com.bhhan.eventuate.backend;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by hbh5274@gmail.com on 2020-11-11
 * Github : http://github.com/bhhan5274
 */
public interface TodoRepository extends JpaRepository<Todo, String> {
}
