package com.example.postsmanagement.exception;

import java.sql.Struct;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class EntityAlreadyExistsException extends RuntimeException {
    private Map<String, String> rejectedParams;

    public EntityAlreadyExistsException(Class entity, String... createParamsMap) {
        super("Title Duplication");
        rejectedParams = toMap(String.class, String.class, createParamsMap);
    }

    private static <K,V> Map<K, V> toMap(
            Class<K> keyType, Class<V> valueType, Object... entries) {
        if(entries.length % 2 == 1) {
            throw new IllegalArgumentException("Invalid entries");
        }
        return IntStream.range(0, entries.length / 2).map(i -> i*2).collect(HashMap::new,
                (m, i) -> m.put(keyType.cast(entries[i]), valueType.cast(entries[i+1])), Map::putAll);
    }

    public Map<String, String> getRejectedParams() {
        return rejectedParams;
    }
}
