package com.example.testmapper.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.function.*;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Entity {
    private String name;
    private Map<String, Object> field;
}
