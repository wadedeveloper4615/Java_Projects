package com.wade.decompiler.classfile.instructions.base.inter;

import com.wade.decompiler.classfile.instructions.type.Type;

public interface NamedAndTyped {
    String getName();

    Type getType();

    void setName(String name);

    void setType(Type type);
}
