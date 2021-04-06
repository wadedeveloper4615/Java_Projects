package com.wade.decompiler.generate.attribute;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.constant.ConstantUtf8;
import com.wade.decompiler.classfile.element.EnumElementValue;
import com.wade.decompiler.enums.ClassFileConstants;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = true, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
public class EnumElementValueGen extends ElementValueGen {
    private String valueName;
    private String typeName;

    public EnumElementValueGen(int type, EnumElementValue element, ConstantPool constantPool) {
        super(type);
        this.typeName = ((ConstantUtf8) constantPool.getConstant(element.getTypeIdx(), ClassFileConstants.CONSTANT_Utf8)).getBytes();
        this.valueName = ((ConstantUtf8) constantPool.getConstant(element.getValueIdx(), ClassFileConstants.CONSTANT_Utf8)).getBytes();
    }
}
