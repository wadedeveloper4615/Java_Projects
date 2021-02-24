package com.wade.decompiler.generate.attribute;

import com.wade.decompiler.classfile.attribute.MethodParameter;
import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.constant.ConstantUtf8;
import com.wade.decompiler.enums.ClassAccessFlags;
import com.wade.decompiler.enums.ClassFileConstants;

public class MethodParameterGen {
    private ClassAccessFlags accessFlags;
    private String name;

    public MethodParameterGen(MethodParameter attribute, ConstantPool constantPool) {
        this.accessFlags = attribute.getAccessFlags();
        this.name = ((ConstantUtf8) constantPool.getConstant(attribute.getNameIndex(), ClassFileConstants.CONSTANT_Utf8)).getBytes();
    }

    public final int compareTo(ClassAccessFlags o) {
        return accessFlags.compareTo(o);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        MethodParameterGen other = (MethodParameterGen) obj;
        if (accessFlags != other.accessFlags)
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

    public ClassAccessFlags getAccessFlags() {
        return accessFlags;
    }

    public String getName() {
        return name;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((accessFlags == null) ? 0 : accessFlags.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
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

    public boolean isFinal() {
        return accessFlags.isFinal();
    }

    public boolean isInterface() {
        return accessFlags.isInterface();
    }

    public boolean isMandated() {
        return accessFlags.isMandated();
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

    public boolean isSet(int p) {
        return accessFlags.isSet(p);
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

    public boolean isSynthetic() {
        return accessFlags.isSynthetic();
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
        return "MethodParameterGen [accessFlags=" + accessFlags + ", name=" + name + "]";
    }
}
