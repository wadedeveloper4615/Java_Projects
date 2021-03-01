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
@ToString
@EqualsAndHashCode(callSuper = false)
public class ClassElementValue extends ElementValue {
    private int idx;

    public ClassElementValue(int type, int idx, ConstantPool cpool) {
        super(type, cpool);
        this.idx = idx;
    }

    public String getClassString() {
        ConstantUtf8 c = (ConstantUtf8) super.getConstantPool().getConstant(idx, ClassFileConstants.CONSTANT_Utf8);
        return c.getBytes();
    }

    @Override
    public String stringifyValue() {
        ConstantUtf8 cu8 = (ConstantUtf8) super.getConstantPool().getConstant(idx, ClassFileConstants.CONSTANT_Utf8);
        return cu8.getBytes();
    }
}
