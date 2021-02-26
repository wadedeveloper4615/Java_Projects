package com.wade.decompiler.util;

public class ConsumedChars extends ThreadLocal<Integer> {
    @Override
    protected Integer initialValue() {
        return Integer.valueOf(0);
    }
}
