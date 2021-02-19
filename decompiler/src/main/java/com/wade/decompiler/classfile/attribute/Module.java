package com.wade.decompiler.classfile.attribute;

import java.io.DataInput;
import java.io.IOException;

import com.wade.decompiler.classfile.ModuleExports;
import com.wade.decompiler.classfile.ModuleOpens;
import com.wade.decompiler.classfile.ModuleProvides;
import com.wade.decompiler.classfile.ModuleRequires;
import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.ClassFileAttributes;
import com.wade.decompiler.enums.ClassFileConstants;
import com.wade.decompiler.util.Utility;

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

    public ModuleExports[] getExportsTable() {
        return exportsTable;
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

    @Override
    public String toString() {
        ConstantPool cp = super.getConstantPool();
        StringBuilder buf = new StringBuilder();
        buf.append("Module:\n");
        buf.append("  name:    ").append(cp.getConstantString(moduleNameIndex, ClassFileConstants.CONSTANT_Module).replace('/', '.')).append("\n");
        buf.append("  flags:   ").append(String.format("%04x", moduleFlags)).append("\n");
        String version = moduleVersionIndex == 0 ? "0" : cp.getConstantString(moduleVersionIndex, ClassFileConstants.CONSTANT_Utf8);
        buf.append("  version: ").append(version).append("\n");
        buf.append("  requires(").append(requiresTable.length).append("):\n");
        for (ModuleRequires module : requiresTable) {
            buf.append("    ").append(module.toString(cp)).append("\n");
        }
        buf.append("  exports(").append(exportsTable.length).append("):\n");
        for (ModuleExports module : exportsTable) {
            buf.append("    ").append(module.toString(cp)).append("\n");
        }
        buf.append("  opens(").append(opensTable.length).append("):\n");
        for (ModuleOpens module : opensTable) {
            buf.append("    ").append(module.toString(cp)).append("\n");
        }
        buf.append("  uses(").append(usesIndex.length).append("):\n");
        for (int index : usesIndex) {
            String class_name = cp.getConstantString(index, ClassFileConstants.CONSTANT_Class);
            buf.append("    ").append(Utility.compactClassName(class_name, false)).append("\n");
        }
        buf.append("  provides(").append(providesTable.length).append("):\n");
        for (ModuleProvides module : providesTable) {
            buf.append("    ").append(module.toString(cp)).append("\n");
        }
        return buf.substring(0, buf.length() - 1); // remove the last newline
    }
}
