package com.wade.decompiler.generate.attribute;

import java.util.Arrays;

import com.wade.decompiler.classfile.ModuleExports;
import com.wade.decompiler.classfile.ModuleOpens;
import com.wade.decompiler.classfile.ModuleProvides;
import com.wade.decompiler.classfile.ModuleRequires;
import com.wade.decompiler.classfile.attribute.Module;
import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.ClassAccessFlagsList;
import com.wade.decompiler.enums.ClassFileConstants;

public class ModuleGen extends AttributeGen {
    private String moduleName;
    private String moduleVersion;
    private ClassAccessFlagsList moduleFlags;
    private ModuleRequiresGen[] requiresTable;
    private ModuleExportsGen[] exportsTable;
    private ModuleOpensGen[] opensTable;
    private String[] usesIndex;
    private ModuleProvidesGen[] providesTable;

    public ModuleGen(Module attribute, ConstantPool constantPool) {
        super(attribute, constantPool);
        this.moduleName = constantPool.getConstantString(attribute.getModuleNameIndex(), ClassFileConstants.CONSTANT_Module);
        this.moduleVersion = constantPool.getConstantString(attribute.getModuleVersionIndex(), ClassFileConstants.CONSTANT_Utf8);
        this.moduleFlags = new ClassAccessFlagsList(attribute.getModuleFlags());

        ModuleRequires[] requiresTable = attribute.getRequiresTable();
        int requires_count = requiresTable.length;
        this.requiresTable = new ModuleRequiresGen[requires_count];
        for (int i = 0; i < requires_count; i++) {
            this.requiresTable[i] = new ModuleRequiresGen(requiresTable[i], constantPool);
        }

        ModuleExports[] exportsTable = attribute.getExportsTable();
        int exports_count = exportsTable.length;
        this.exportsTable = new ModuleExportsGen[exports_count];
        for (int i = 0; i < exports_count; i++) {
            this.exportsTable[i] = new ModuleExportsGen(exportsTable[i], constantPool);
        }

        ModuleOpens[] opensTable = attribute.getOpensTable();
        int opens_count = opensTable.length;
        this.opensTable = new ModuleOpensGen[opens_count];
        for (int i = 0; i < opens_count; i++) {
            this.opensTable[i] = new ModuleOpensGen(opensTable[i], constantPool);
        }

        int[] usesIndex = attribute.getUsesIndex();
        int usesCount = usesIndex.length;
        this.usesIndex = new String[usesCount];
        for (int i = 0; i < usesCount; i++) {
            this.usesIndex[i] = constantPool.getConstantString(usesIndex[i], ClassFileConstants.CONSTANT_Module);
        }

        ModuleProvides[] providesTable = attribute.getProvidesTable();
        int provides_count = opensTable.length;
        this.providesTable = new ModuleProvidesGen[provides_count];
        for (int i = 0; i < provides_count; i++) {
            this.providesTable[i] = new ModuleProvidesGen(providesTable[i], constantPool);
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
        ModuleGen other = (ModuleGen) obj;
        if (!Arrays.equals(exportsTable, other.exportsTable))
            return false;
        if (moduleFlags == null) {
            if (other.moduleFlags != null)
                return false;
        } else if (!moduleFlags.equals(other.moduleFlags))
            return false;
        if (moduleName == null) {
            if (other.moduleName != null)
                return false;
        } else if (!moduleName.equals(other.moduleName))
            return false;
        if (moduleVersion == null) {
            if (other.moduleVersion != null)
                return false;
        } else if (!moduleVersion.equals(other.moduleVersion))
            return false;
        if (!Arrays.equals(opensTable, other.opensTable))
            return false;
        if (!Arrays.equals(providesTable, other.providesTable))
            return false;
        if (!Arrays.equals(requiresTable, other.requiresTable))
            return false;
        if (!Arrays.equals(usesIndex, other.usesIndex))
            return false;
        return true;
    }

    public ModuleExportsGen[] getExportsTable() {
        return exportsTable;
    }

    public ClassAccessFlagsList getModuleFlags() {
        return moduleFlags;
    }

    public String getModuleName() {
        return moduleName;
    }

    public String getModuleVersion() {
        return moduleVersion;
    }

    public ModuleOpensGen[] getOpensTable() {
        return opensTable;
    }

    public ModuleProvidesGen[] getProvidesTable() {
        return providesTable;
    }

    public ModuleRequiresGen[] getRequiresTable() {
        return requiresTable;
    }

    public String[] getUsesIndex() {
        return usesIndex;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.hashCode(exportsTable);
        result = prime * result + ((moduleFlags == null) ? 0 : moduleFlags.hashCode());
        result = prime * result + ((moduleName == null) ? 0 : moduleName.hashCode());
        result = prime * result + ((moduleVersion == null) ? 0 : moduleVersion.hashCode());
        result = prime * result + Arrays.hashCode(opensTable);
        result = prime * result + Arrays.hashCode(providesTable);
        result = prime * result + Arrays.hashCode(requiresTable);
        result = prime * result + Arrays.hashCode(usesIndex);
        return result;
    }

    @Override
    public String toString() {
        return "ModuleGen [moduleName=" + moduleName + ", moduleVersion=" + moduleVersion + ", moduleFlags=" + moduleFlags + ", requiresTable=" + Arrays.toString(requiresTable) + ", exportsTable=" + Arrays.toString(exportsTable) + ", opensTable=" + Arrays.toString(opensTable) + ", usesIndex=" + Arrays.toString(usesIndex) + ", providesTable=" + Arrays.toString(providesTable) + "]";
    }
}
