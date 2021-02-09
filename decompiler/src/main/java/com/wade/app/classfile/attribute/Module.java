package com.wade.app.classfile.attribute;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Arrays;

import com.wade.app.constantpool.ConstantPool;
import com.wade.app.enums.ClassFileAttributes;

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

    public Module(int name_index, int length, DataInputStream input, ConstantPool constant_pool) throws IOException {
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
    public String toString() {
        return "Module [moduleNameIndex=" + moduleNameIndex + ", moduleFlags=" + moduleFlags + ", moduleVersionIndex=" + moduleVersionIndex + ", requiresTable=" + Arrays.toString(requiresTable) + ", exportsTable=" + Arrays.toString(exportsTable) + ", opensTable=" + Arrays.toString(opensTable) + ", usesCount=" + usesCount + ", usesIndex=" + Arrays.toString(usesIndex) + ", providesTable=" + Arrays.toString(providesTable) + "]";
    }
}
