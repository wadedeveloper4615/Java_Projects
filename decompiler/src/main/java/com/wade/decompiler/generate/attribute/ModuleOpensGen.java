package com.wade.decompiler.generate.attribute;

import com.wade.decompiler.classfile.attribute.ModuleOpens;
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
public class ModuleOpensGen {
    private String package_name;
    private String[] opensToIndex;
    private ClassAccessFlagsList opensFlags;

    public ModuleOpensGen(ModuleOpens attribute, ConstantPool constantPool) {
        this.package_name = constantPool.constantToString(attribute.getOpensIndex(), ClassFileConstants.CONSTANT_Package);
        this.opensFlags = new ClassAccessFlagsList(attribute.getOpensFlags());
        int[] opensToIndex = attribute.getOpensToIndex();
        int opensToCount = opensToIndex.length;
        this.opensToIndex = new String[opensToCount];
        for (int i = 0; i < opensToCount; i++) {
            this.opensToIndex[i] = constantPool.constantToString(opensToIndex[i], ClassFileConstants.CONSTANT_Module);
        }
    }
}
