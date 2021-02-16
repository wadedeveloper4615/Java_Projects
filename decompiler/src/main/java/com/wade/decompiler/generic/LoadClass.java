package com.wade.decompiler.generic;

public interface LoadClass {
    ObjectType getLoadClassType(ConstantPoolGen cpg);

    Type getType(ConstantPoolGen cpg);
}
