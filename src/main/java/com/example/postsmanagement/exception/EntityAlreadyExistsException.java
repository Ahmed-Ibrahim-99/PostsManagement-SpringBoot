package com.example.postsmanagement.exception;

import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class EntityAlreadyExistsException extends RuntimeException {
    public EntityAlreadyExistsException(Class entity, String... createParamsMap) {
        super(EntityAlreadyExistsException.generateMessage(entity.getSimpleName(), toMap(String.class, String.class, createParamsMap)));
    }
    private static String generateMessage(String entity, Map<String, String> createParams) {
        return StringUtils.capitalize(entity) + " already exists For" + createParams;
    }

    private static <K,V> Map<K, V> toMap(
            Class<K> keyType, Class<V> valueType, Object... entries) {
        if(entries.length % 2 == 1) {
            throw new IllegalArgumentException("Invalid entries");
        }
        return IntStream.range(0, entries.length / 2).map(i -> i*2).collect(HashMap::new,
                (m, i) -> m.put(keyType.cast(entries[i]), valueType.cast(entries[i+1])), Map::putAll);
    }
}
