package com.memre.repository;

import com.memre.entities.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITodoRepository  extends JpaRepository<Todo,Long> {

}
