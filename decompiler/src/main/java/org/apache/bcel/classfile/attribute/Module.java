package org.apache.bcel.classfile.attribute;

import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

import org.apache.bcel.classfile.ModuleExports;
import org.apache.bcel.classfile.ModuleOpens;
import org.apache.bcel.classfile.ModuleProvides;
import org.apache.bcel.classfile.ModuleRequires;
import org.apache.bcel.classfile.Utility;
import org.apache.bcel.classfile.Visitor;
import org.apache.bcel.classfile.constant.ConstantPool;
import org.apache.bcel.enums.ClassFileAttributes;
import org.apache.bcel.enums.ClassFileConstants;

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

    public Module(final int name_index, final int length, final DataInput input, final ConstantPool constant_pool) throws IOException {
        super(ClassFileAttributes.ATTR_MODULE, name_index, length, constant_pool);
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

    @Override
    public void accept(final Visitor v) {
        v.visitModule(this);
    }

    @Override
    public Attribute copy(final ConstantPool _constant_pool) {
        final Module c = (Module) clone();
        c.requiresTable = new ModuleRequires[requiresTable.length];
        for (int i = 0; i < requiresTable.length; i++) {
            c.requiresTable[i] = requiresTable[i].copy();
        }
        c.exportsTable = new ModuleExports[exportsTable.length];
        for (int i = 0; i < exportsTable.length; i++) {
            c.exportsTable[i] = exportsTable[i].copy();
        }
        c.opensTable = new ModuleOpens[opensTable.length];
        for (int i = 0; i < opensTable.length; i++) {
            c.opensTable[i] = opensTable[i].copy();
        }
        c.providesTable = new ModuleProvides[providesTable.length];
        for (int i = 0; i < providesTable.length; i++) {
            c.providesTable[i] = providesTable[i].copy();
        }
        c.setConstantPool(_constant_pool);
        return c;
    }

    @Override
    public void dump(final DataOutputStream file) throws IOException {
        super.dump(file);
        file.writeShort(moduleNameIndex);
        file.writeShort(moduleFlags);
        file.writeShort(moduleVersionIndex);
        file.writeShort(requiresTable.length);
        for (final ModuleRequires entry : requiresTable) {
            entry.dump(file);
        }
        file.writeShort(exportsTable.length);
        for (final ModuleExports entry : exportsTable) {
            entry.dump(file);
        }
        file.writeShort(opensTable.length);
        for (final ModuleOpens entry : opensTable) {
            entry.dump(file);
        }
        file.writeShort(usesIndex.length);
        for (final int entry : usesIndex) {
            file.writeShort(entry);
        }
        file.writeShort(providesTable.length);
        for (final ModuleProvides entry : providesTable) {
            entry.dump(file);
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
        final ConstantPool cp = super.getConstantPool();
        final StringBuilder buf = new StringBuilder();
        buf.append("Module:\n");
        buf.append("  name:    ").append(cp.getConstantString(moduleNameIndex, ClassFileConstants.CONSTANT_Module).replace('/', '.')).append("\n");
        buf.append("  flags:   ").append(String.format("%04x", moduleFlags)).append("\n");
        final String version = moduleVersionIndex == 0 ? "0" : cp.getConstantString(moduleVersionIndex, ClassFileConstants.CONSTANT_Utf8);
        buf.append("  version: ").append(version).append("\n");
        buf.append("  requires(").append(requiresTable.length).append("):\n");
        for (final ModuleRequires module : requiresTable) {
            buf.append("    ").append(module.toString(cp)).append("\n");
        }
        buf.append("  exports(").append(exportsTable.length).append("):\n");
        for (final ModuleExports module : exportsTable) {
            buf.append("    ").append(module.toString(cp)).append("\n");
        }
        buf.append("  opens(").append(opensTable.length).append("):\n");
        for (final ModuleOpens module : opensTable) {
            buf.append("    ").append(module.toString(cp)).append("\n");
        }
        buf.append("  uses(").append(usesIndex.length).append("):\n");
        for (final int index : usesIndex) {
            final String class_name = cp.getConstantString(index, ClassFileConstants.CONSTANT_Class);
            buf.append("    ").append(Utility.compactClassName(class_name, false)).append("\n");
        }
        buf.append("  provides(").append(providesTable.length).append("):\n");
        for (final ModuleProvides module : providesTable) {
            buf.append("    ").append(module.toString(cp)).append("\n");
        }
        return buf.substring(0, buf.length() - 1);
    }
}
