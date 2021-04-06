package com.wade.decompiler.generate.attribute;

import com.wade.decompiler.classfile.attribute.NestHost;
import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.ClassFileConstants;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = true, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
public class NestHostGen extends AttributeGen {
    private String hostClassName;

    public NestHostGen(NestHost attribute, ConstantPool constantPool) {
        super(attribute, constantPool);
        this.hostClassName = constantPool.constantToString(attribute.getHostClassIndex(), ClassFileConstants.CONSTANT_Class);
    }
}
