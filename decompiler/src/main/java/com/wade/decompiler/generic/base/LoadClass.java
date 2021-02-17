package com.wade.decompiler.generic.base;

import com.wade.decompiler.generic.gen.ConstantPoolGen;

public interface LoadClass {
    ObjectType getLoadClassType(ConstantPoolGen cpg);

    Type getType(ConstantPoolGen cpg);
}
