package com.wade.app.classfile.attribute;

import java.io.DataInputStream;
import java.io.IOException;

public  class ModuleExports {
    private  int exportsIndex;
    private  int exportsFlags;
    private  int exportsToCount;
    private  int[] exportsToIndex;

    ModuleExports(DataInputStream file) throws IOException {
        exportsIndex = file.readUnsignedShort();
        exportsFlags = file.readUnsignedShort();
        exportsToCount = file.readUnsignedShort();
        exportsToIndex = new int[exportsToCount];
        for (int i = 0; i < exportsToCount; i++) {
            exportsToIndex[i] = file.readUnsignedShort();
        }
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
}
