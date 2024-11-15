package com.example.demo.controller;

import com.example.demo.model.TodoDTO;
import com.example.demo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class TodoController {

    @Autowired
    private TodoRepository todoRepo;

    @GetMapping("/todos")
    public ResponseEntity<?> getAllTodos(){
        List<TodoDTO> todos = todoRepo.findAll();
        if (todos.isEmpty()) {
            return new ResponseEntity<>("no availavle",HttpStatus.NOT_FOUND); // Renvoie un code HTTP 204 (No Content)
        } else {
            return  new ResponseEntity<List<TodoDTO>>(todos, HttpStatus.OK); // Renvoie un code HTTP 200 et la liste des Todos
        }
    }

    @GetMapping("/todos/{id}")
    public ResponseEntity<?> getSingleTodos(@PathVariable("id") String id){
        Optional<TodoDTO> todOptional = todoRepo.findById(id);
        if (todOptional.isPresent()) {
            return new ResponseEntity<>(todOptional.get(), HttpStatus.OK);
        } else {
            return   new ResponseEntity<>("no availavle",HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/todos")
    public ResponseEntity<?> createTodo(@RequestBody TodoDTO todo){
        try {
            todo.setCratedAt(new Date(System.currentTimeMillis()));
            todoRepo.save(todo);
            return new ResponseEntity<TodoDTO>(todo, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/todos/{id}")
    public ResponseEntity<?> updateByID(@PathVariable("id") String id, @RequestBody TodoDTO todo){
        Optional<TodoDTO> todOptional = todoRepo.findById(id);
        if (todOptional.isPresent()) {
            TodoDTO todoToSave = todOptional.get();
            todoToSave.setCompleted(todo.isCompleted() != todoToSave.isCompleted() ? todo.isCompleted() : todoToSave.isCompleted());
            todoToSave.setTodo(todo.getTodo() != null ? todo.getTodo() : todoToSave.getTodo());
            todoToSave.setDesc(todo.getDesc() != null ? todo.getDesc() : todoToSave.getDesc());
            todoToSave.setUpdatedAt(new Date(System.currentTimeMillis()));
            todoRepo.save(todoToSave);
            return new ResponseEntity<>(todOptional.get(), HttpStatus.OK);
        } else {
            return   new ResponseEntity<>("no availavle",HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/todos/{id}")
    public ResponseEntity<?> deleteTodos(@PathVariable("id") String id){
        if (todoRepo.findById(id).isPresent()){
            todoRepo.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>("no availavle",HttpStatus.NOT_FOUND);
        }



    }
}
