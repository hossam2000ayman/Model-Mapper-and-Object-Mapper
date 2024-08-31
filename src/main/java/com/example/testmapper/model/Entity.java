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
    private Map<String, Object> field;
    private Map<String, Function<String, Object>> methodWithStringArgument;
    private Map<String, BiFunction<String, String, Object>> methodWithTwoStringArguments;
    private Map<String, Consumer<String>> voidMethodWithStringArgument;
    private Map<String, BiConsumer<String, String>> voidMethodWithTwoStringArguments;
    private Map<String, Predicate<String>> booleanMethodWithStringArgument;
    private Map<String, BiPredicate<String, String>> booleanMethodWithTwoStringArguments;
}
