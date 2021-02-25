package com.wade.decompiler.classfile.instructions.base;

import com.wade.decompiler.classfile.instructions.type.ObjectType;
import com.wade.decompiler.classfile.instructions.type.Type;

public interface LoadClass {
    ObjectType getLoadClassType();

    Type getType();
}
