package com.wade.app.classfile.attribute;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Arrays;

public class ModuleOpens {
    private int opensIndex;
    private int opensFlags;
    private int opensToCount;
    private int[] opensToIndex;

    public ModuleOpens(DataInputStream file) throws IOException {
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

    @Override
    public String toString() {
        return "ModuleOpens [opensIndex=" + opensIndex + ", opensFlags=" + opensFlags + ", opensToCount=" + opensToCount + ", opensToIndex=" + Arrays.toString(opensToIndex) + "]";
    }
}
