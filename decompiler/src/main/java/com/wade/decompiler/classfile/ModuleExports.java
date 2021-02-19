package com.wade.decompiler.classfile;

import java.io.DataInput;
import java.io.IOException;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.ClassFileConstants;
import com.wade.decompiler.util.Utility;

public class ModuleExports {
    private int exportsIndex; // points to CONSTANT_Package_info
    private int exportsFlags;
    private int exportsToCount;
    private int[] exportsToIndex; // points to CONSTANT_Module_info

    public ModuleExports(DataInput file) throws IOException {
        exportsIndex = file.readUnsignedShort();
        exportsFlags = file.readUnsignedShort();
        exportsToCount = file.readUnsignedShort();
        exportsToIndex = new int[exportsToCount];
        for (int i = 0; i < exportsToCount; i++) {
            exportsToIndex[i] = file.readUnsignedShort();
        }
    }

    @Override
    public String toString() {
        return "exports(" + exportsIndex + ", " + exportsFlags + ", " + exportsToCount + ", ...)";
    }

    public String toString(ConstantPool constant_pool) {
        StringBuilder buf = new StringBuilder();
        String package_name = constant_pool.constantToString(exportsIndex, ClassFileConstants.CONSTANT_Package);
        buf.append(Utility.compactClassName(package_name, false));
        buf.append(", ").append(String.format("%04x", exportsFlags));
        buf.append(", to(").append(exportsToCount).append("):\n");
        for (int index : exportsToIndex) {
            String module_name = constant_pool.getConstantString(index, ClassFileConstants.CONSTANT_Module);
            buf.append("      ").append(Utility.compactClassName(module_name, false)).append("\n");
        }
        return buf.substring(0, buf.length() - 1); // remove the last newline
    }
}
