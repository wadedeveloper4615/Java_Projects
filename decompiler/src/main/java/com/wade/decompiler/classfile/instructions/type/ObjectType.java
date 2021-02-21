package com.wade.decompiler.classfile.instructions.type;

import com.wade.decompiler.enums.TypeEnum;

public class ObjectType extends ReferenceType {
    private String className;

    public ObjectType(String className) {
        super(TypeEnum.T_REFERENCE, "L" + className.replace('.', '/') + ";");
        this.className = className.replace('/', '.');
    }

    public String getClassName() {
        return className;
    }
}
