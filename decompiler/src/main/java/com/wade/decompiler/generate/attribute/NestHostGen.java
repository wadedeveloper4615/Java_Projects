package com.wade.decompiler.generate.attribute;

import com.wade.decompiler.classfile.attribute.NestHost;
import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.ClassFileConstants;

public class NestHostGen extends AttributeGen {
    private String hostClassName;

    public NestHostGen(NestHost attribute, ConstantPool constantPool) {
        super(attribute, constantPool);
        this.hostClassName = constantPool.constantToString(attribute.getHostClassIndex(), ClassFileConstants.CONSTANT_Class);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        NestHostGen other = (NestHostGen) obj;
        if (hostClassName == null) {
            if (other.hostClassName != null) return false;
        } else if (!hostClassName.equals(other.hostClassName)) return false;
        return true;
    }

    public String getHostClassName() {
        return hostClassName;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((hostClassName == null) ? 0 : hostClassName.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "NestHostGen [hostClassName=" + hostClassName + "]";
    }
}
