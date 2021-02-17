package com.wade.decompiler.classfile;

import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

import com.wade.decompiler.enums.ClassFileConstants;

public class ModuleOpens implements Cloneable, Node {
    private int opensIndex; // points to CONSTANT_Package_info
    private int opensFlags;
    private int opensToCount;
    private int[] opensToIndex; // points to CONSTANT_Module_info

    ModuleOpens(DataInput file) throws IOException {
        opensIndex = file.readUnsignedShort();
        opensFlags = file.readUnsignedShort();
        opensToCount = file.readUnsignedShort();
        opensToIndex = new int[opensToCount];
        for (int i = 0; i < opensToCount; i++) {
            opensToIndex[i] = file.readUnsignedShort();
        }
    }

    @Override
    public void accept(Visitor v) {
        v.visitModuleOpens(this);
    }
    // TODO add more getters and setters?

    public ModuleOpens copy() {
        try {
            return (ModuleOpens) clone();
        } catch (CloneNotSupportedException e) {
            // TODO should this throw?
        }
        return null;
    }

    public void dump(DataOutputStream file) throws IOException {
        file.writeShort(opensIndex);
        file.writeShort(opensFlags);
        file.writeShort(opensToCount);
        for (int entry : opensToIndex) {
            file.writeShort(entry);
        }
    }

    @Override
    public String toString() {
        return "opens(" + opensIndex + ", " + opensFlags + ", " + opensToCount + ", ...)";
    }

    public String toString(ConstantPool constant_pool) {
        StringBuilder buf = new StringBuilder();
        String package_name = constant_pool.constantToString(opensIndex, ClassFileConstants.CONSTANT_Package);
        buf.append(Utility.compactClassName(package_name, false));
        buf.append(", ").append(String.format("%04x", opensFlags));
        buf.append(", to(").append(opensToCount).append("):\n");
        for (int index : opensToIndex) {
            String module_name = constant_pool.getConstantString(index, ClassFileConstants.CONSTANT_Module);
            buf.append("      ").append(Utility.compactClassName(module_name, false)).append("\n");
        }
        return buf.substring(0, buf.length() - 1); // remove the last newline
    }
}
