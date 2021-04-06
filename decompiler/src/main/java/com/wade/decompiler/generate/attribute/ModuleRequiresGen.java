package com.wade.decompiler.generate.attribute;

import com.wade.decompiler.classfile.attribute.ModuleRequires;
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
public class ModuleRequiresGen {
    private String requiresVersion;
    private ClassAccessFlagsList requiresFlags;
    private String moduleName;

    public ModuleRequiresGen(ModuleRequires attribute, ConstantPool constantPool) {
        this.moduleName = constantPool.constantToString(attribute.getRequiresIndex(), ClassFileConstants.CONSTANT_Module);
        this.requiresFlags = new ClassAccessFlagsList(attribute.getRequiresFlags());
        this.requiresVersion = constantPool.constantToString(attribute.getRequiresVersionIndex(), ClassFileConstants.CONSTANT_Utf8);
    }
}
