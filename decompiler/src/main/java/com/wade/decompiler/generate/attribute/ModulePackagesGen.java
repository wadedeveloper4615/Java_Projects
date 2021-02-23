package com.wade.decompiler.generate.attribute;

import java.util.Arrays;

import com.wade.decompiler.classfile.attribute.ModulePackages;
import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.ClassFileConstants;

public class ModulePackagesGen extends AttributeGen {
    private String[] packageIndexNames;

    public ModulePackagesGen(ModulePackages attribute, ConstantPool constantPool) {
        super(attribute, constantPool);
        int[] packageIndexTable = attribute.getPackageIndexTable();
        this.packageIndexNames = new String[packageIndexTable.length];
        for (int i = 0; i < packageIndexTable.length; i++) {
            this.packageIndexNames[i] = constantPool.getConstantString(packageIndexTable[i], ClassFileConstants.CONSTANT_Package);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ModulePackagesGen other = (ModulePackagesGen) obj;
        if (!Arrays.equals(packageIndexNames, other.packageIndexNames))
            return false;
        return true;
    }

    public String[] getPackageIndexNames() {
        return packageIndexNames;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.hashCode(packageIndexNames);
        return result;
    }

    @Override
    public String toString() {
        return "ModulePackagesGen [packageIndexNames=" + Arrays.toString(packageIndexNames) + "]";
    }
}
