package com.wade.decompiler.classfile.attribute;

import java.io.DataInput;
import java.io.IOException;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.constant.ConstantUtf8;
import com.wade.decompiler.enums.ClassAccessFlags;
import com.wade.decompiler.enums.ClassFileConstants;

public class MethodParameter {
    private int nameIndex;
    private ClassAccessFlags accessFlags;
    private String name;

    public MethodParameter(DataInput input, ConstantPool constant_pool) throws IOException {
        nameIndex = input.readUnsignedShort();
        accessFlags = ClassAccessFlags.read(input.readUnsignedShort());
        name = ((ConstantUtf8) constant_pool.getConstant(nameIndex, ClassFileConstants.CONSTANT_Utf8)).getBytes();
    }

    public ClassAccessFlags getAccessFlags() {
        return accessFlags;
    }

    public String getName() {
        return name;
    }

    public int getNameIndex() {
        return nameIndex;
    }

    public boolean isAbstract() {
        return accessFlags.isAbstract();
    }

    public boolean isAndAbstract() {
        return accessFlags.isAndAbstract();
    }

    public boolean isAnnotation() {
        return accessFlags.isAnnotation();
    }

    public boolean isEnum() {
        return accessFlags.isEnum();
    }

    public boolean isInterface() {
        return accessFlags.isInterface();
    }

    public boolean isNative() {
        return accessFlags.isNative();
    }

    public boolean isPrivate() {
        return accessFlags.isPrivate();
    }

    public boolean isProtected() {
        return accessFlags.isProtected();
    }

    public boolean isPublic() {
        return accessFlags.isPublic();
    }

    public boolean isStatic() {
        return accessFlags.isStatic();
    }

    public boolean isStrictfp() {
        return accessFlags.isStrictfp();
    }

    public boolean isSuper() {
        return accessFlags.isSuper();
    }

    public boolean isSynchronized() {
        return accessFlags.isSynchronized();
    }

    public boolean isTransient() {
        return accessFlags.isTransient();
    }

    public boolean isVarArgs() {
        return accessFlags.isVarArgs();
    }

    public boolean isVolatile() {
        return accessFlags.isVolatile();
    }
}
