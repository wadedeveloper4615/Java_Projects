package com.wade.decompiler.classfile;

import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

import com.wade.decompiler.enums.ClassFileConstants;

public class ModuleProvides implements Cloneable, Node {
    private int providesIndex; // points to CONSTANT_Class_info
    private int providesWithCount;
    private int[] providesWithIndex; // points to CONSTANT_Class_info

    ModuleProvides(DataInput file) throws IOException {
        providesIndex = file.readUnsignedShort();
        providesWithCount = file.readUnsignedShort();
        providesWithIndex = new int[providesWithCount];
        for (int i = 0; i < providesWithCount; i++) {
            providesWithIndex[i] = file.readUnsignedShort();
        }
    }

    @Override
    public void accept(Visitor v) {
        v.visitModuleProvides(this);
    }
    // TODO add more getters and setters?

    public ModuleProvides copy() {
        try {
            return (ModuleProvides) clone();
        } catch (CloneNotSupportedException e) {
            // TODO should this throw?
        }
        return null;
    }

    public void dump(DataOutputStream file) throws IOException {
        file.writeShort(providesIndex);
        file.writeShort(providesWithCount);
        for (int entry : providesWithIndex) {
            file.writeShort(entry);
        }
    }

    @Override
    public String toString() {
        return "provides(" + providesIndex + ", " + providesWithCount + ", ...)";
    }

    public String toString(ConstantPool constant_pool) {
        StringBuilder buf = new StringBuilder();
        String interface_name = constant_pool.constantToString(providesIndex, ClassFileConstants.CONSTANT_Class);
        buf.append(Utility.compactClassName(interface_name, false));
        buf.append(", with(").append(providesWithCount).append("):\n");
        for (int index : providesWithIndex) {
            String class_name = constant_pool.getConstantString(index, ClassFileConstants.CONSTANT_Class);
            buf.append("      ").append(Utility.compactClassName(class_name, false)).append("\n");
        }
        return buf.substring(0, buf.length() - 1); // remove the last newline
    }
}
