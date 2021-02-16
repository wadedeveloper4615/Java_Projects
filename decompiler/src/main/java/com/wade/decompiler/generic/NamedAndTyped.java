package com.wade.decompiler.generic;

public interface NamedAndTyped {
    String getName();

    Type getType();

    void setName(String name);

    void setType(Type type);
}
