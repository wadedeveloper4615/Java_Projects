package com.wade.decompiler.generic.base;

import com.wade.decompiler.generic.gen.ConstantPoolGen;
import com.wade.decompiler.generic.type.ObjectType;
import com.wade.decompiler.generic.type.Type;

public interface LoadClass {
    ObjectType getLoadClassType(ConstantPoolGen cpg);

    Type getType(ConstantPoolGen cpg);
}
