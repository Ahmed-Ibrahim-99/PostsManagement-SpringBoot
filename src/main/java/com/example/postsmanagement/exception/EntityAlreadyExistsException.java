package com.example.postsmanagement.exception;

import java.sql.Struct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class EntityAlreadyExistsException extends RuntimeException {

    List<String> duplicatedFields;
    public EntityAlreadyExistsException(List<String> duplicatedFields) {
        super("Unique Columns Violation");
        this.duplicatedFields = duplicatedFields;
    }

    public List<String> getDuplicatedFields() {
        return duplicatedFields;
    }
}
