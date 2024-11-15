package com.example.demo.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "Todos")
public class TodoDTO {
    @Id
    private String id;
    private String todo;
    private String desc;
    private boolean completed;
    private Date cratedAt;
    private Date updatedAt;
}
