package com.wade.decompiler.generate.attribute;

import java.util.Arrays;

import com.wade.decompiler.classfile.attribute.ModuleExports;
import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.ClassAccessFlagsList;
import com.wade.decompiler.enums.ClassFileConstants;

public class ModuleExportsGen {
    private String[] exports;
    private String package_name;
    private ClassAccessFlagsList exportFlags;

    public ModuleExportsGen(ModuleExports attribute, ConstantPool constantPool) {
        this.package_name = constantPool.constantToString(attribute.getExportsIndex(), ClassFileConstants.CONSTANT_Package);
        this.exportFlags = new ClassAccessFlagsList(attribute.getExportsFlags());
        int[] exports = attribute.getExportsToIndex();
        int requires_count = exports.length;
        this.exports = new String[requires_count];
        for (int i = 0; i < requires_count; i++) {
            this.exports[i] = constantPool.getConstantString(exports[i], ClassFileConstants.CONSTANT_Module);
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
        ModuleExportsGen other = (ModuleExportsGen) obj;
        if (exportFlags == null) {
            if (other.exportFlags != null)
                return false;
        } else if (!exportFlags.equals(other.exportFlags))
            return false;
        if (!Arrays.equals(exports, other.exports))
            return false;
        if (package_name == null) {
            if (other.package_name != null)
                return false;
        } else if (!package_name.equals(other.package_name))
            return false;
        return true;
    }

    public ClassAccessFlagsList getExportFlags() {
        return exportFlags;
    }

    public String[] getExports() {
        return exports;
    }

    public String getPackage_name() {
        return package_name;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((exportFlags == null) ? 0 : exportFlags.hashCode());
        result = prime * result + Arrays.hashCode(exports);
        result = prime * result + ((package_name == null) ? 0 : package_name.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "ModuleExportsGen [exports=" + Arrays.toString(exports) + ", package_name=" + package_name + ", exportFlags=" + exportFlags + "]";
    }
}
