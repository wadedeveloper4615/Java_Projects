package com.wade.decompiler.generate.attribute;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.constant.ConstantUtf8;
import com.wade.decompiler.classfile.element.ClassElementValue;
import com.wade.decompiler.enums.ClassFileConstants;

public class ClassElementValueGen extends ElementValueGen {
    private String index;

    public ClassElementValueGen(byte type, ClassElementValue value, ConstantPool constantPool) {
        super(type);
        this.index = ((ConstantUtf8) constantPool.getConstant(value.getIndex(), ClassFileConstants.CONSTANT_Utf8)).getBytes();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ClassElementValueGen other = (ClassElementValueGen) obj;
        if (index == null) {
            if (other.index != null)
                return false;
        } else if (!index.equals(other.index))
            return false;
        return true;
    }

    public String getClassString() {
        return index;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((index == null) ? 0 : index.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "ClassElementValueGen [index=" + index + "]";
    }
}
