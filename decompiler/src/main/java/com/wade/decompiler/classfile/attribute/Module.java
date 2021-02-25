package com.wade.decompiler.classfile.attribute;

import java.io.DataInput;
import java.io.IOException;
import java.util.Arrays;

import com.wade.decompiler.classfile.ModuleExports;
import com.wade.decompiler.classfile.ModuleOpens;
import com.wade.decompiler.classfile.ModuleProvides;
import com.wade.decompiler.classfile.ModuleRequires;
import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.ClassFileAttributes;

public class Module extends Attribute {
    private int moduleNameIndex;
    private int moduleFlags;
    private int moduleVersionIndex;
    private ModuleRequires[] requiresTable;
    private ModuleExports[] exportsTable;
    private ModuleOpens[] opensTable;
    private int usesCount;
    private int[] usesIndex;
    private ModuleProvides[] providesTable;

    public Module(int name_index, int length, DataInput input, ConstantPool constant_pool) throws IOException {
        super(ClassFileAttributes.ATTR_MODULE, name_index, length, constant_pool);
        moduleNameIndex = input.readUnsignedShort();
        moduleFlags = input.readUnsignedShort();
        moduleVersionIndex = input.readUnsignedShort();
        int requires_count = input.readUnsignedShort();
        requiresTable = new ModuleRequires[requires_count];
        for (int i = 0; i < requires_count; i++) {
            requiresTable[i] = new ModuleRequires(input);
        }
        int exports_count = input.readUnsignedShort();
        exportsTable = new ModuleExports[exports_count];
        for (int i = 0; i < exports_count; i++) {
            exportsTable[i] = new ModuleExports(input);
        }
        int opens_count = input.readUnsignedShort();
        opensTable = new ModuleOpens[opens_count];
        for (int i = 0; i < opens_count; i++) {
            opensTable[i] = new ModuleOpens(input);
        }
        usesCount = input.readUnsignedShort();
        usesIndex = new int[usesCount];
        for (int i = 0; i < usesCount; i++) {
            usesIndex[i] = input.readUnsignedShort();
        }
        int provides_count = input.readUnsignedShort();
        providesTable = new ModuleProvides[provides_count];
        for (int i = 0; i < provides_count; i++) {
            providesTable[i] = new ModuleProvides(input);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        Module other = (Module) obj;
        if (!Arrays.equals(exportsTable, other.exportsTable))
            return false;
        if (moduleFlags != other.moduleFlags)
            return false;
        if (moduleNameIndex != other.moduleNameIndex)
            return false;
        if (moduleVersionIndex != other.moduleVersionIndex)
            return false;
        if (!Arrays.equals(opensTable, other.opensTable))
            return false;
        if (!Arrays.equals(providesTable, other.providesTable))
            return false;
        if (!Arrays.equals(requiresTable, other.requiresTable))
            return false;
        if (usesCount != other.usesCount)
            return false;
        if (!Arrays.equals(usesIndex, other.usesIndex))
            return false;
        return true;
    }

    public ModuleExports[] getExportsTable() {
        return exportsTable;
    }

    public int getModuleFlags() {
        return moduleFlags;
    }

    public int getModuleNameIndex() {
        return moduleNameIndex;
    }

    public int getModuleVersionIndex() {
        return moduleVersionIndex;
    }

    public ModuleOpens[] getOpensTable() {
        return opensTable;
    }

    public ModuleProvides[] getProvidesTable() {
        return providesTable;
    }

    public ModuleRequires[] getRequiresTable() {
        return requiresTable;
    }

    public int getUsesCount() {
        return usesCount;
    }

    public int[] getUsesIndex() {
        return usesIndex;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + Arrays.hashCode(exportsTable);
        result = prime * result + moduleFlags;
        result = prime * result + moduleNameIndex;
        result = prime * result + moduleVersionIndex;
        result = prime * result + Arrays.hashCode(opensTable);
        result = prime * result + Arrays.hashCode(providesTable);
        result = prime * result + Arrays.hashCode(requiresTable);
        result = prime * result + usesCount;
        result = prime * result + Arrays.hashCode(usesIndex);
        return result;
    }
}
