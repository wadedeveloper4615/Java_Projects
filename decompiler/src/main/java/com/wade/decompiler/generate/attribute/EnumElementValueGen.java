package com.wade.decompiler.generate.attribute;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.constant.ConstantUtf8;
import com.wade.decompiler.classfile.element.EnumElementValue;
import com.wade.decompiler.enums.ClassFileConstants;

public class EnumElementValueGen extends ElementValueGen {
    private String valueName;
    private String typeName;

    public EnumElementValueGen(int type, EnumElementValue element, ConstantPool constantPool) {
        super(type, constantPool);
        this.typeName = ((ConstantUtf8) constantPool.getConstant(element.getTypeIndex(), ClassFileConstants.CONSTANT_Utf8)).getBytes();
        this.valueName = ((ConstantUtf8) constantPool.getConstant(element.getValueIndex(), ClassFileConstants.CONSTANT_Utf8)).getBytes();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        EnumElementValueGen other = (EnumElementValueGen) obj;
        if (typeName == null) {
            if (other.typeName != null)
                return false;
        } else if (!typeName.equals(other.typeName))
            return false;
        if (valueName == null) {
            if (other.valueName != null)
                return false;
        } else if (!valueName.equals(other.valueName))
            return false;
        return true;
    }

    public String getEnumTypeString() {
        return this.typeName;
    }

    public String getEnumValueString() {
        return this.valueName;
    }

    public String getTypeName() {
        return typeName;
    }

    public String getValueName() {
        return valueName;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((typeName == null) ? 0 : typeName.hashCode());
        result = prime * result + ((valueName == null) ? 0 : valueName.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "EnumElementValueGen [valueName=" + valueName + ", typeName=" + typeName + "]";
    }
}
