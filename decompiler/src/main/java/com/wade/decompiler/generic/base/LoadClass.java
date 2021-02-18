package com.wade.decompiler.generic.base;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.generic.type.ObjectType;
import com.wade.decompiler.generic.type.Type;

public interface LoadClass {
    ObjectType getLoadClassType(ConstantPool cpg);

    Type getType(ConstantPool cpg);
}
