package com.wade.decompiler.generate.attribute;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.constant.ConstantUtf8;
import com.wade.decompiler.classfile.element.ClassElementValue;
import com.wade.decompiler.enums.ClassFileConstants;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = true, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
public class ClassElementValueGen extends ElementValueGen {
    private String index;

    public ClassElementValueGen(byte type, ClassElementValue value, ConstantPool constantPool) {
        super(type);
        this.index = ((ConstantUtf8) constantPool.getConstant(value.getIdx(), ClassFileConstants.CONSTANT_Utf8)).getBytes();
    }
}
