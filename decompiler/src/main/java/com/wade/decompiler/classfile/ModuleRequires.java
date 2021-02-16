package com.wade.decompiler.classfile;

import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

import com.wade.decompiler.Const;

public final class ModuleRequires implements Cloneable, Node {
    private final int requiresIndex; // points to CONSTANT_Module_info
    private final int requiresFlags;
    private final int requiresVersionIndex; // either 0 or points to CONSTANT_Utf8_info

    ModuleRequires(final DataInput file) throws IOException {
        requiresIndex = file.readUnsignedShort();
        requiresFlags = file.readUnsignedShort();
        requiresVersionIndex = file.readUnsignedShort();
    }

    @Override
    public void accept(final Visitor v) {
        v.visitModuleRequires(this);
    }
    // TODO add more getters and setters?

    public ModuleRequires copy() {
        try {
            return (ModuleRequires) clone();
        } catch (final CloneNotSupportedException e) {
            // TODO should this throw?
        }
        return null;
    }

    public void dump(final DataOutputStream file) throws IOException {
        file.writeShort(requiresIndex);
        file.writeShort(requiresFlags);
        file.writeShort(requiresVersionIndex);
    }

    @Override
    public String toString() {
        return "requires(" + requiresIndex + ", " + String.format("%04x", requiresFlags) + ", " + requiresVersionIndex + ")";
    }

    public String toString(final ConstantPool constant_pool) {
        final StringBuilder buf = new StringBuilder();
        final String module_name = constant_pool.constantToString(requiresIndex, Const.CONSTANT_Module);
        buf.append(Utility.compactClassName(module_name, false));
        buf.append(", ").append(String.format("%04x", requiresFlags));
        final String version = requiresVersionIndex == 0 ? "0" : constant_pool.getConstantString(requiresVersionIndex, Const.CONSTANT_Utf8);
        buf.append(", ").append(version);
        return buf.toString();
    }
}