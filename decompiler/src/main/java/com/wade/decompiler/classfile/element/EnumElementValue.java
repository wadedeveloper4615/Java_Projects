package com.wade.decompiler.classfile.element;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.constant.ConstantUtf8;
import com.wade.decompiler.enums.ClassFileConstants;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = true, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
public class EnumElementValue extends ElementValue {
    private int typeIdx;
    private int valueIdx;

    public EnumElementValue(int type, int typeIdx, int valueIdx, ConstantPool cpool) {
        super(type, cpool);
        if (type != ENUM_CONSTANT) {
            throw new IllegalArgumentException("Only element values of type enum can be built with this ctor - type specified: " + type);
        }
        this.typeIdx = typeIdx;
        this.valueIdx = valueIdx;
    }

    public String getEnumTypeString() {
        ConstantUtf8 cu8 = (ConstantUtf8) super.getConstantPool().getConstant(typeIdx, ClassFileConstants.CONSTANT_Utf8);
        return cu8.getBytes();// Utility.signatureToString(cu8.getBytes());
    }

    public String getEnumValueString() {
        ConstantUtf8 cu8 = (ConstantUtf8) super.getConstantPool().getConstant(valueIdx, ClassFileConstants.CONSTANT_Utf8);
        return cu8.getBytes();
    }
}
