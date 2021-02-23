package com.wade.decompiler.generate.attribute;

import com.wade.decompiler.classfile.attribute.StackMapType;
import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.ClassFileConstants;

public class StackMapTypeGen {
    private byte type;
    private String name;

    public StackMapTypeGen(StackMapType attribute, ConstantPool constantPool) {
        this.type = attribute.getType();
        this.name = constantPool.constantToString(attribute.getIndex(), ClassFileConstants.CONSTANT_Class);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        StackMapTypeGen other = (StackMapTypeGen) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (type != other.type)
            return false;
        return true;
    }

    public String getName() {
        return name;
    }

    public byte getType() {
        return type;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + type;
        return result;
    }

    @Override
    public String toString() {
        return "StackMapTypeGen [type=" + type + ", name=" + name + "]";
    }
}
