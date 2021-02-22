package com.wade.decompiler.classfile.instructions.type;

import com.wade.decompiler.enums.TypeEnum;

public abstract class ReferenceType extends Type {
    public ReferenceType() {
        super(TypeEnum.T_OBJECT, "<null object>");
    }

    public ReferenceType(TypeEnum t, String s) {
        super(t, s);
    }
}
