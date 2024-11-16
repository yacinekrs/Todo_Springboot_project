package com.example.demo.model;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "Todos")
public class TodoDTO {
    @Id
    private String id;

    @NotNull(message = "todo cannot be null")
    private String todo;
    @NotNull(message = "desc cannot be null")
    private String desc;
    @NotNull(message = "completed cannot be null")
    private boolean completed;
    private Date cratedAt;
    private Date updatedAt;
}
