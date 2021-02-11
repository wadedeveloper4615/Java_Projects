
package org.apache.bcel.classfile;

import java.io.DataOutputStream;
import java.io.IOException;

import org.apache.bcel.Const;

public class EnumElementValue extends ElementValue {
    // For enum types, these two indices point to the type and value
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
        dos.writeByte(super.getType()); // u1 type of value (ENUM_CONSTANT == 'e')
        dos.writeShort(typeIdx); // u2
        dos.writeShort(valueIdx); // u2
    }

    @Override
    public String stringifyValue() {
        final ConstantUtf8 cu8 = (ConstantUtf8) super.getConstantPool().getConstant(valueIdx, Const.CONSTANT_Utf8);
        return cu8.getBytes();
    }

    public String getEnumTypeString() {
        final ConstantUtf8 cu8 = (ConstantUtf8) super.getConstantPool().getConstant(typeIdx, Const.CONSTANT_Utf8);
        return cu8.getBytes();// Utility.signatureToString(cu8.getBytes());
    }

    public String getEnumValueString() {
        final ConstantUtf8 cu8 = (ConstantUtf8) super.getConstantPool().getConstant(valueIdx, Const.CONSTANT_Utf8);
        return cu8.getBytes();
    }

    public int getValueIndex() {
        return valueIdx;
    }

    public int getTypeIndex() {
        return typeIdx;
    }
}
