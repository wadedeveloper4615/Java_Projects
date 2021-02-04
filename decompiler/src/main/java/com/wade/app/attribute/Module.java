package com.wade.app.attribute;

import java.io.DataInputStream;
import java.io.IOException;

import com.wade.app.Const;
import com.wade.app.constantpool.ConstantPool;

public final class Module extends Attribute {
    private final int moduleNameIndex;
    private final int moduleFlags;
    private final int moduleVersionIndex;
    private ModuleRequires[] requiresTable;
    private ModuleExports[] exportsTable;
    private ModuleOpens[] opensTable;
    private final int usesCount;
    private final int[] usesIndex;
    private ModuleProvides[] providesTable;

    public Module(final int name_index, final int length, final DataInputStream input, final ConstantPool constant_pool) throws IOException {
        super(Const.ATTR_MODULE, name_index, length, constant_pool);

        moduleNameIndex = input.readUnsignedShort();
        moduleFlags = input.readUnsignedShort();
        moduleVersionIndex = input.readUnsignedShort();

        final int requires_count = input.readUnsignedShort();
        requiresTable = new ModuleRequires[requires_count];
        for (int i = 0; i < requires_count; i++) {
            requiresTable[i] = new ModuleRequires(input);
        }

        final int exports_count = input.readUnsignedShort();
        exportsTable = new ModuleExports[exports_count];
        for (int i = 0; i < exports_count; i++) {
            exportsTable[i] = new ModuleExports(input);
        }

        final int opens_count = input.readUnsignedShort();
        opensTable = new ModuleOpens[opens_count];
        for (int i = 0; i < opens_count; i++) {
            opensTable[i] = new ModuleOpens(input);
        }

        usesCount = input.readUnsignedShort();
        usesIndex = new int[usesCount];
        for (int i = 0; i < usesCount; i++) {
            usesIndex[i] = input.readUnsignedShort();
        }

        final int provides_count = input.readUnsignedShort();
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
}
