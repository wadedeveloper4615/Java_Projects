package com.wade.decompiler.generate.attribute;

import com.wade.decompiler.classfile.attribute.ConstantValue;
import com.wade.decompiler.classfile.constant.*;
import com.wade.decompiler.enums.ClassFileConstants;
import com.wade.decompiler.util.Utility;

public class ConstantValueGen extends AttributeGen {
    private Constant value;

    public ConstantValueGen(ConstantValue attribute, ConstantPool constantPool) {
        super(attribute, constantPool);
        value = constantPool.getConstant(attribute.getConstantValueIndex());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        ConstantValueGen other = (ConstantValueGen) obj;
        if (value == null) {
            if (other.value != null) return false;
        } else if (!value.equals(other.value)) return false;
        return true;
    }

    public Constant getValue() {
        return value;
    }

    public String getValueAsString() {
        String buf = switch (value.getTag()) {
            case CONSTANT_Long -> String.valueOf(((ConstantLong) value).getBytes());
            case CONSTANT_Float -> String.valueOf(((ConstantFloat) value).getBytes());
            case CONSTANT_Double -> String.valueOf(((ConstantDouble) value).getBytes());
            case CONSTANT_Integer -> String.valueOf(((ConstantInteger) value).getBytes());
            case CONSTANT_String -> {
                int i = ((ConstantString) value).getStringIndex();
                Constant c = constantPool.getConstant(i, ClassFileConstants.CONSTANT_Utf8);
                yield "\"" + Utility.convertString(((ConstantUtf8) c).getBytes()) + "\"";
            }
            default -> throw new IllegalStateException("Type of ConstValue invalid: " + value);
        };
        return buf;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((value == null) ? 0 : value.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "ConstantValueGen [value=" + getValueAsString() + "]";
    }
}
