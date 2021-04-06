package com.wade.decompiler.generate.attribute;

import java.util.Arrays;

import com.wade.decompiler.classfile.attribute.ModuleOpens;
import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.ClassAccessFlagsList;
import com.wade.decompiler.enums.ClassFileConstants;

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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        ModuleOpensGen other = (ModuleOpensGen) obj;
        if (opensFlags == null) {
            if (other.opensFlags != null) return false;
        } else if (!opensFlags.equals(other.opensFlags)) return false;
        if (!Arrays.equals(opensToIndex, other.opensToIndex)) return false;
        if (package_name == null) {
            if (other.package_name != null) return false;
        } else if (!package_name.equals(other.package_name)) return false;
        return true;
    }

    public ClassAccessFlagsList getOpensFlags() {
        return opensFlags;
    }

    public String[] getOpensToIndex() {
        return opensToIndex;
    }

    public String getPackage_name() {
        return package_name;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((opensFlags == null) ? 0 : opensFlags.hashCode());
        result = prime * result + Arrays.hashCode(opensToIndex);
        result = prime * result + ((package_name == null) ? 0 : package_name.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "ModuleOpensGen [package_name=" + package_name + ", opensToIndex=" + Arrays.toString(opensToIndex) + ", opensFlags=" + opensFlags + "]";
    }
}
