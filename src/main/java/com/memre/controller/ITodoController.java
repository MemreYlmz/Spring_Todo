package com.memre.controller;

import com.memre.entities.Tasks;
import com.memre.entities.Todo;

import java.util.List;

public interface ITodoController {
    public Todo findTodoById(Long id);
    public List<Todo> findAllTodoList();
    public Todo deleteTodoById(Long id);
    public Todo addNewTodo(Todo newTodo);
    public Todo changeTodo(Long id ,Todo changeTodo);



}
