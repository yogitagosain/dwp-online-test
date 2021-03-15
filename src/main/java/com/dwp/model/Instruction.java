package com.dwp.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Instruction {

    @JsonProperty("todo")
    private final String todo;

    @JsonCreator
    public Instruction(@JsonProperty("todo") String todo ){
        this.todo = todo;
    }
}
