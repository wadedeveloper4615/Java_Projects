package org.apache.bcel.generic.base;

import org.apache.bcel.generic.Type;

public interface NamedAndTyped {
    String getName();

    Type getType();

    void setName(String name);

    void setType(Type type);
}
