
package org.apache.bcel.classfile;

import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

import org.apache.bcel.Const;

public final class ModuleOpens implements Cloneable, Node {

    private final int opensIndex; // points to CONSTANT_Package_info
    private final int opensFlags;
    private final int opensToCount;
    private final int[] opensToIndex; // points to CONSTANT_Module_info

    ModuleOpens(final DataInput file) throws IOException {
        opensIndex = file.readUnsignedShort();
        opensFlags = file.readUnsignedShort();
        opensToCount = file.readUnsignedShort();
        opensToIndex = new int[opensToCount];
        for (int i = 0; i < opensToCount; i++) {
            opensToIndex[i] = file.readUnsignedShort();
        }
    }

    @Override
    public void accept(final Visitor v) {
        v.visitModuleOpens(this);
    }

    // TODO add more getters and setters?

    public void dump(final DataOutputStream file) throws IOException {
        file.writeShort(opensIndex);
        file.writeShort(opensFlags);
        file.writeShort(opensToCount);
        for (final int entry : opensToIndex) {
            file.writeShort(entry);
        }
    }

    @Override
    public String toString() {
        return "opens(" + opensIndex + ", " + opensFlags + ", " + opensToCount + ", ...)";
    }

    public String toString(final ConstantPool constant_pool) {
        final StringBuilder buf = new StringBuilder();
        final String package_name = constant_pool.constantToString(opensIndex, Const.CONSTANT_Package);
        buf.append(Utility.compactClassName(package_name, false));
        buf.append(", ").append(String.format("%04x", opensFlags));
        buf.append(", to(").append(opensToCount).append("):\n");
        for (final int index : opensToIndex) {
            final String module_name = constant_pool.getConstantString(index, Const.CONSTANT_Module);
            buf.append("      ").append(Utility.compactClassName(module_name, false)).append("\n");
        }
        return buf.substring(0, buf.length() - 1); // remove the last newline
    }

    public ModuleOpens copy() {
        try {
            return (ModuleOpens) clone();
        } catch (final CloneNotSupportedException e) {
            // TODO should this throw?
        }
        return null;
    }
}
