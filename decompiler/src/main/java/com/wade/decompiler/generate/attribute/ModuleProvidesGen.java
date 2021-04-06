package com.wade.decompiler.generate.attribute;

import com.wade.decompiler.classfile.attribute.ModuleProvides;
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
public class ModuleProvidesGen {
    private String[] providesIndex;
    private String interface_name;

    public ModuleProvidesGen(ModuleProvides attribute, ConstantPool constantPool) {
        this.interface_name = constantPool.constantToString(attribute.getProvidesIndex(), ClassFileConstants.CONSTANT_Class);
        int[] providesIndex = attribute.getProvidesWithIndex();
        int providesWithCount = providesIndex.length;
        this.providesIndex = new String[providesWithCount];
        for (int i = 0; i < providesWithCount; i++) {
            this.providesIndex[i] = constantPool.constantToString(providesIndex[i], ClassFileConstants.CONSTANT_Module);
        }
    }
}
