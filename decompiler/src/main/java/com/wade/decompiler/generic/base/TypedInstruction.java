package com.wade.decompiler.generic.base;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.generic.type.Type;

public interface TypedInstruction {
    Type getType(ConstantPool cp);
}
