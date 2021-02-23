package com.wade.decompiler.generate.attribute;

import java.util.Arrays;

import com.wade.decompiler.classfile.attribute.Deprecated;
import com.wade.decompiler.classfile.constant.ConstantPool;

public class DeprecatedGen extends AttributeGen {
    private byte[] bytes;

    public DeprecatedGen(Deprecated attribute, ConstantPool constantPool) {
        super(attribute, constantPool);
        this.bytes = attribute.getBytes();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        DeprecatedGen other = (DeprecatedGen) obj;
        if (!Arrays.equals(bytes, other.bytes))
            return false;
        return true;
    }

    public byte[] getBytes() {
        return bytes;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.hashCode(bytes);
        return result;
    }

    @Override
    public String toString() {
        return "DeprecatedGen [bytes=" + Arrays.toString(bytes) + "]";
    }
}
