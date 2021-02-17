package com.wade.decompiler.classfile;

import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.gen.Visitor;
import com.wade.decompiler.enums.ClassFileConstants;
import com.wade.decompiler.util.Utility;

public class ModuleRequires implements Cloneable, Node {
    private int requiresIndex; // points to CONSTANT_Module_info
    private int requiresFlags;
    private int requiresVersionIndex; // either 0 or points to CONSTANT_Utf8_info

    public ModuleRequires(DataInput file) throws IOException {
        requiresIndex = file.readUnsignedShort();
        requiresFlags = file.readUnsignedShort();
        requiresVersionIndex = file.readUnsignedShort();
    }

    @Override
    public void accept(Visitor v) {
        v.visitModuleRequires(this);
    }
    // TODO add more getters and setters?

    public ModuleRequires copy() {
        try {
            return (ModuleRequires) clone();
        } catch (CloneNotSupportedException e) {
            // TODO should this throw?
        }
        return null;
    }

    public void dump(DataOutputStream file) throws IOException {
        file.writeShort(requiresIndex);
        file.writeShort(requiresFlags);
        file.writeShort(requiresVersionIndex);
    }

    @Override
    public String toString() {
        return "requires(" + requiresIndex + ", " + String.format("%04x", requiresFlags) + ", " + requiresVersionIndex + ")";
    }

    public String toString(ConstantPool constant_pool) {
        StringBuilder buf = new StringBuilder();
        String module_name = constant_pool.constantToString(requiresIndex, ClassFileConstants.CONSTANT_Module);
        buf.append(Utility.compactClassName(module_name, false));
        buf.append(", ").append(String.format("%04x", requiresFlags));
        String version = requiresVersionIndex == 0 ? "0" : constant_pool.getConstantString(requiresVersionIndex, ClassFileConstants.CONSTANT_Utf8);
        buf.append(", ").append(version);
        return buf.toString();
    }
}
