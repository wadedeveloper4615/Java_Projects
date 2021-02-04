package com.wade.app.attribute;

import java.io.DataInputStream;
import java.io.IOException;

import com.wade.app.constantpool.Node;

public final class ModuleOpens implements Node {
    private final int opensIndex;
    private final int opensFlags;
    private final int opensToCount;
    private final int[] opensToIndex;

    public ModuleOpens(final DataInputStream file) throws IOException {
        opensIndex = file.readUnsignedShort();
        opensFlags = file.readUnsignedShort();
        opensToCount = file.readUnsignedShort();
        opensToIndex = new int[opensToCount];
        for (int i = 0; i < opensToCount; i++) {
            opensToIndex[i] = file.readUnsignedShort();
        }
    }

    public int getOpensFlags() {
        return opensFlags;
    }

    public int getOpensIndex() {
        return opensIndex;
    }

    public int getOpensToCount() {
        return opensToCount;
    }

    public int[] getOpensToIndex() {
        return opensToIndex;
    }
}
