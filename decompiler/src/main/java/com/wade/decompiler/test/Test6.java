package com.wade.decompiler.test;

import java.util.List;
import java.util.stream.Stream;

public class Test6 {
    public long numberOfLongColorNames() {
        List<String> value = List.of("Red", "Green", "Blue");
        Stream<String> stream = value.stream();
        Stream<String> filter = stream.filter(c -> c.length() > 3);
        return filter.count();
    }
}
