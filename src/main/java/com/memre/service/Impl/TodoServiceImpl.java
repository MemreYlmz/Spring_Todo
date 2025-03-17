package com.memre.service.Impl;

import com.memre.entities.Tasks;
import com.memre.entities.Todo;
import com.memre.repository.ITodoRepository;
import com.memre.service.ITodoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TodoServiceImpl implements ITodoService {

    @Autowired
    private ITodoRepository todoRepository;

    @Override
    public Todo findTodoById(Long id) {
        Optional<Todo> optional = todoRepository.findById(id);
        Todo todo = optional.get();
        if (optional.isEmpty()) {
            return null;
        }
        return todo;
    }


    @Override
    public List<Todo> findAllTodoList() {
        List<Todo> todos = todoRepository.findAll();
        return todos;
    }

    @Override
    public Todo addNewTodo(Todo newTodo) {
        Todo response = new Todo();
        BeanUtils.copyProperties(newTodo,response);
        if(newTodo.getTask().isEmpty()){
            todoRepository.save(response);
            return response;
        }
        todoRepository.save(response);
        return response;
    }

    @Override
    public Todo deleteTodoById(Long id) {
        Optional<Todo> optional = todoRepository.findById(id);
        if (optional.isPresent()) {
            Todo todo = optional.get();
            todoRepository.delete(todo);
            return todo;
        }
        return null;
    }



    @Override
    public Todo changeTodo(Long id,Todo changeTodo) {
        Optional<Todo> optional = todoRepository.findById(id);
        if(optional.isEmpty()){
            return null;
        }
        Todo todo = optional.get();
        todo.setDescription(changeTodo.getDescription());
        todo.setStatus(changeTodo.getStatus());
        if (changeTodo.getTask() != null && !changeTodo.getTask().isEmpty()) {
            List<Tasks> existingTasks = todo.getTask(); // Mevcut task listesi
            List<Tasks> newTasks = changeTodo.getTask(); // Güncellenmek istenen task listesi

            for (Tasks newTask : newTasks) {
                for (Tasks existingTask : existingTasks) {
                    if (existingTask.getId().equals(newTask.getId())) { // Aynı ID'ye sahip task varsa güncelle
                        existingTask.setDescription(newTask.getDescription());
                        existingTask.setStatus(newTask.getStatus());

                        break;
                    }
                }

            }
        }


        todoRepository.save(todo);

        return todo;
    }


}

