package com.wade.decompiler.generate.attribute;

import com.wade.decompiler.classfile.attribute.ModulePackages;
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
public class ModulePackagesGen extends AttributeGen {
    private String[] packageIndexNames;

    public ModulePackagesGen(ModulePackages attribute, ConstantPool constantPool) {
        super(attribute, constantPool);
        int[] packageIndexTable = attribute.getPackageIndexTable();
        this.packageIndexNames = new String[packageIndexTable.length];
        for (int i = 0; i < packageIndexTable.length; i++) {
            this.packageIndexNames[i] = constantPool.constantToString(packageIndexTable[i], ClassFileConstants.CONSTANT_Package);
        }
    }
}
