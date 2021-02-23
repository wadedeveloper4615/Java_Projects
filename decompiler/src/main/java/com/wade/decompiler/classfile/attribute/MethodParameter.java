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

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        MethodParameter other = (MethodParameter) obj;
        if (accessFlags != other.accessFlags)
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (nameIndex != other.nameIndex)
            return false;
        return true;
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((accessFlags == null) ? 0 : accessFlags.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + nameIndex;
        return result;
    }

    public boolean isAbstract() {
        return accessFlags.isAbstract();
    }

    public boolean isAnnotation() {
        return accessFlags.isAnnotation();
    }

    public boolean isEnum() {
        return accessFlags.isEnum();
    }

    public boolean isFinalAndAbstract() {
        return accessFlags.isFinalAndAbstract();
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

    @Override
    public String toString() {
        return "MethodParameter [nameIndex=" + nameIndex + ", accessFlags=" + accessFlags + ", name=" + name + "]";
    }
}
