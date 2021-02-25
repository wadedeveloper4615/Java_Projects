package com.wade.decompiler.classfile.attribute;

import java.io.DataInput;
import java.io.IOException;
import java.util.Arrays;

public class ModuleExports {
    private int exportsIndex;
    private int exportsFlags;
    private int exportsToCount;
    private int[] exportsToIndex;

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
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ModuleExports other = (ModuleExports) obj;
        if (exportsFlags != other.exportsFlags)
            return false;
        if (exportsIndex != other.exportsIndex)
            return false;
        if (exportsToCount != other.exportsToCount)
            return false;
        if (!Arrays.equals(exportsToIndex, other.exportsToIndex))
            return false;
        return true;
    }

    public int getExportsFlags() {
        return exportsFlags;
    }

    public int getExportsIndex() {
        return exportsIndex;
    }

    public int getExportsToCount() {
        return exportsToCount;
    }

    public int[] getExportsToIndex() {
        return exportsToIndex;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + exportsFlags;
        result = prime * result + exportsIndex;
        result = prime * result + exportsToCount;
        result = prime * result + Arrays.hashCode(exportsToIndex);
        return result;
    }

    @Override
    public String toString() {
        return "ModuleExports [exportsIndex=" + exportsIndex + ", exportsFlags=" + exportsFlags + ", exportsToCount=" + exportsToCount + ", exportsToIndex=" + Arrays.toString(exportsToIndex) + "]";
    }
}
