package com.memre.controller.Impl;

import com.memre.controller.ITodoController;
import com.memre.entities.Tasks;
import com.memre.entities.Todo;
import com.memre.service.ITodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/api/todo")
public class TodoControllerImpl implements ITodoController {
    @Autowired
    private ITodoService todoService;

    @GetMapping("/list/{id}")
    @Override
    public Todo findTodoById( @PathVariable(name = "id") Long id) {
        return todoService.findTodoById(id);
    }

    @GetMapping("/list")
    @Override
    public List<Todo> findAllTodoList() {
        return todoService.findAllTodoList();
    }

    @DeleteMapping("/delete/{id}")
    @Override
    public Todo deleteTodoById( @PathVariable(name = "id") Long id) {
        return todoService.deleteTodoById(id);
    }

    @PostMapping("/add")
    @Override
    public Todo addNewTodo(@RequestBody Todo newTodo) {
        return todoService.addNewTodo(newTodo);
    }

    @PutMapping("/change/{id}")
    @Override
    public Todo changeTodo( @PathVariable(name = "id") Long id, @RequestBody Todo changeTodo) {
        return todoService.changeTodo(id ,changeTodo);
    }


}
