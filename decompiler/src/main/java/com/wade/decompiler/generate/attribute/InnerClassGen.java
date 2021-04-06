package com.wade.decompiler.generate.attribute;

import com.wade.decompiler.classfile.attribute.InnerClass;
import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.ClassAccessFlagsList;
import com.wade.decompiler.enums.ClassFileConstants;

public class InnerClassGen {
    private String innerClass;
    private String outerClass;
    private String innerName;
    private ClassAccessFlagsList accessFlags;

    public InnerClassGen(InnerClass attribute, ConstantPool constantPool) {
        this.innerClass = constantPool.constantToString(attribute.getInnerClassIndex(), ClassFileConstants.CONSTANT_Class);
        this.outerClass = constantPool.constantToString(attribute.getOuterClassIndex(), ClassFileConstants.CONSTANT_Class);
        this.innerName = constantPool.constantToString(attribute.getInnerNameIndex(), ClassFileConstants.CONSTANT_Class);
        this.accessFlags = attribute.getInnerAccessFlags();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        InnerClassGen other = (InnerClassGen) obj;
        if (accessFlags == null) {
            if (other.accessFlags != null) return false;
        } else if (!accessFlags.equals(other.accessFlags)) return false;
        if (innerClass == null) {
            if (other.innerClass != null) return false;
        } else if (!innerClass.equals(other.innerClass)) return false;
        if (innerName == null) {
            if (other.innerName != null) return false;
        } else if (!innerName.equals(other.innerName)) return false;
        if (outerClass == null) {
            if (other.outerClass != null) return false;
        } else if (!outerClass.equals(other.outerClass)) return false;
        return true;
    }

    public ClassAccessFlagsList getAccessFlags() {
        return accessFlags;
    }

    public String getInnerClass() {
        return innerClass;
    }

    public String getInnerName() {
        return innerName;
    }

    public String getOuterClass() {
        return outerClass;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((accessFlags == null) ? 0 : accessFlags.hashCode());
        result = prime * result + ((innerClass == null) ? 0 : innerClass.hashCode());
        result = prime * result + ((innerName == null) ? 0 : innerName.hashCode());
        result = prime * result + ((outerClass == null) ? 0 : outerClass.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "InnerClassGen [innerClass=" + innerClass + ", outerClass=" + outerClass + ", innerName=" + innerName + ", accessFlags=" + accessFlags + "]";
    }
}
