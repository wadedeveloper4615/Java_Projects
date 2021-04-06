package com.wade.decompiler.generate.attribute;

import com.wade.decompiler.classfile.attribute.ModuleMainClass;
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
public class ModuleMainClassGen extends AttributeGen {
    private String className;

    public ModuleMainClassGen(ModuleMainClass attribute, ConstantPool constantPool) {
        super(attribute, constantPool);
        this.className = constantPool.constantToString(attribute.getMainClassIndex(), ClassFileConstants.CONSTANT_Class);
    }
}
