package com.wade.decompiler.generic.base;

import com.wade.decompiler.generic.type.Type;

public interface NamedAndTyped {
    String getName();

    Type getType();

    void setName(String name);

    void setType(Type type);
}
