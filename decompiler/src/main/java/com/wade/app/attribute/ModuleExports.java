package com.wade.app.attribute;

import java.io.DataInputStream;
import java.io.IOException;

import com.wade.app.constantpool.Node;

public final class ModuleExports implements Node {
    private final int exportsIndex;
    private final int exportsFlags;
    private final int exportsToCount;
    private final int[] exportsToIndex;

    public ModuleExports(final DataInputStream file) throws IOException {
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
