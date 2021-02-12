package org.apache.bcel.classfile;

import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

import org.apache.bcel.classfile.constant.ConstantPool;
import org.apache.bcel.enums.ClassFileConstants;

public final class ModuleExports implements Cloneable, Node {
    private final int exportsIndex; // points to CONSTANT_Package_info
    private final int exportsFlags;
    private final int exportsToCount;
    private final int[] exportsToIndex; // points to CONSTANT_Module_info

    public ModuleExports(final DataInput file) throws IOException {
        exportsIndex = file.readUnsignedShort();
        exportsFlags = file.readUnsignedShort();
        exportsToCount = file.readUnsignedShort();
        exportsToIndex = new int[exportsToCount];
        for (int i = 0; i < exportsToCount; i++) {
            exportsToIndex[i] = file.readUnsignedShort();
        }
    }

    @Override
    public void accept(final Visitor v) {
        v.visitModuleExports(this);
    }
    // TODO add more getters and setters?

    public ModuleExports copy() {
        try {
            return (ModuleExports) clone();
        } catch (final CloneNotSupportedException e) {
            // TODO should this throw?
        }
        return null;
    }

    public void dump(final DataOutputStream file) throws IOException {
        file.writeShort(exportsIndex);
        file.writeShort(exportsFlags);
        file.writeShort(exportsToCount);
        for (final int entry : exportsToIndex) {
            file.writeShort(entry);
        }
    }

    @Override
    public String toString() {
        return "exports(" + exportsIndex + ", " + exportsFlags + ", " + exportsToCount + ", ...)";
    }

    public String toString(final ConstantPool constant_pool) {
        final StringBuilder buf = new StringBuilder();
        final String package_name = constant_pool.constantToString(exportsIndex, ClassFileConstants.CONSTANT_Package);
        buf.append(Utility.compactClassName(package_name, false));
        buf.append(", ").append(String.format("%04x", exportsFlags));
        buf.append(", to(").append(exportsToCount).append("):\n");
        for (final int index : exportsToIndex) {
            final String module_name = constant_pool.getConstantString(index, ClassFileConstants.CONSTANT_Module);
            buf.append("      ").append(Utility.compactClassName(module_name, false)).append("\n");
        }
        return buf.substring(0, buf.length() - 1); // remove the last newline
    }
}
