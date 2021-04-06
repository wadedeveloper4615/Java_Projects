package com.wade.decompiler.generate.attribute;

import com.wade.decompiler.classfile.attribute.InnerClass;
import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.ClassAccessFlagsList;
import com.wade.decompiler.enums.ClassFileConstants;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = true, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
public class InnerClassGen {
    private String innerClass;
    private String outerClass;
    private String innerName;
    private ClassAccessFlagsList accessFlags;

    public InnerClassGen(InnerClass attribute, ConstantPool constantPool) {
        this.innerClass = constantPool.constantToString(attribute.getInnerClassIndex(), ClassFileConstants.CONSTANT_Class);
        this.outerClass = constantPool.constantToString(attribute.getOuterClassIndex(), ClassFileConstants.CONSTANT_Class);
        this.innerName = constantPool.constantToString(attribute.getInnerNameIndex(), ClassFileConstants.CONSTANT_Utf8);
        this.accessFlags = attribute.getInnerAccessFlags();
    }
}
