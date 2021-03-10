package com.wade.decompiler.generate.attribute;

import com.wade.decompiler.classfile.attribute.ModuleRequires;
import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.ClassAccessFlagsList;
import com.wade.decompiler.enums.ClassFileConstants;

public class ModuleRequiresGen {
    private String requiresVersion;
    private ClassAccessFlagsList requiresFlags;
    private String moduleName;

    public ModuleRequiresGen(ModuleRequires attribute, ConstantPool constantPool) {
        this.moduleName = constantPool.constantToString(attribute.getRequiresIndex(), ClassFileConstants.CONSTANT_Module);
        this.requiresFlags = new ClassAccessFlagsList(attribute.getRequiresFlags());
        this.requiresVersion = constantPool.constantToString(attribute.getRequiresVersionIndex(), ClassFileConstants.CONSTANT_Utf8);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ModuleRequiresGen other = (ModuleRequiresGen) obj;
        if (moduleName == null) {
            if (other.moduleName != null)
                return false;
        } else if (!moduleName.equals(other.moduleName))
            return false;
        if (requiresFlags == null) {
            if (other.requiresFlags != null)
                return false;
        } else if (!requiresFlags.equals(other.requiresFlags))
            return false;
        if (requiresVersion == null) {
            if (other.requiresVersion != null)
                return false;
        } else if (!requiresVersion.equals(other.requiresVersion))
            return false;
        return true;
    }

    public String getModuleName() {
        return moduleName;
    }

    public ClassAccessFlagsList getRequiresFlags() {
        return requiresFlags;
    }

    public String getRequiresVersion() {
        return requiresVersion;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((moduleName == null) ? 0 : moduleName.hashCode());
        result = prime * result + ((requiresFlags == null) ? 0 : requiresFlags.hashCode());
        result = prime * result + ((requiresVersion == null) ? 0 : requiresVersion.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "ModuleRequiresGen [requiresVersion=" + requiresVersion + ", requiresFlags=" + requiresFlags + ", moduleName=" + moduleName + "]";
    }
}
