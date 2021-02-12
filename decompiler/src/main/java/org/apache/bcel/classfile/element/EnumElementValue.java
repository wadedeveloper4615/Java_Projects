package org.apache.bcel.classfile.element;

import java.io.DataOutputStream;
import java.io.IOException;

import org.apache.bcel.classfile.constant.ConstantPool;
import org.apache.bcel.classfile.constant.ConstantUtf8;
import org.apache.bcel.enums.ClassFileConstants;

public class EnumElementValue extends ElementValue {
    private final int typeIdx;
    private final int valueIdx;

    public EnumElementValue(final int type, final int typeIdx, final int valueIdx, final ConstantPool cpool) {
        super(type, cpool);
        if (type != ENUM_CONSTANT) {
            throw new IllegalArgumentException("Only element values of type enum can be built with this ctor - type specified: " + type);
        }
        this.typeIdx = typeIdx;
        this.valueIdx = valueIdx;
    }

    @Override
    public void dump(final DataOutputStream dos) throws IOException {
        dos.writeByte(super.getType());
        dos.writeShort(typeIdx);
        dos.writeShort(valueIdx);
    }

    public String getEnumTypeString() {
        final ConstantUtf8 cu8 = (ConstantUtf8) super.getConstantPool().getConstant(typeIdx, ClassFileConstants.CONSTANT_Utf8);
        return cu8.getBytes();
    }

    public String getEnumValueString() {
        final ConstantUtf8 cu8 = (ConstantUtf8) super.getConstantPool().getConstant(valueIdx, ClassFileConstants.CONSTANT_Utf8);
        return cu8.getBytes();
    }

    public int getTypeIndex() {
        return typeIdx;
    }

    public int getValueIndex() {
        return valueIdx;
    }

    @Override
    public String stringifyValue() {
        final ConstantUtf8 cu8 = (ConstantUtf8) super.getConstantPool().getConstant(valueIdx, ClassFileConstants.CONSTANT_Utf8);
        return cu8.getBytes();
    }
}
