package com.application.shared.exception.resource;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class Converter {
    public static <K, V> Map<K, V> toMap(Class<K> keyType, Class<V> valueType, Object... entries){
        if (entries.length % 2 == 1)
            throw new IllegalArgumentException("Invalid entries");
        /*
        * TODO : explanation
        * */
        return IntStream
                .range(0, entries.length / 2)
                .map(index -> index * 2)
                .collect (HashMap::new,
                        (m, index) -> m.put(keyType.cast(entries[index]), valueType.cast(entries[index + 1])),
                        Map::putAll);
    }
}
