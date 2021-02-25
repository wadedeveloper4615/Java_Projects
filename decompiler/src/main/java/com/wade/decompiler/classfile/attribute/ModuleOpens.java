package com.wade.decompiler.classfile.attribute;

import java.io.DataInput;
import java.io.IOException;
import java.util.Arrays;

public class ModuleOpens {
    private int opensIndex;
    private int opensFlags;
    private int opensToCount;
    private int[] opensToIndex;

    public ModuleOpens(DataInput file) throws IOException {
        opensIndex = file.readUnsignedShort();
        opensFlags = file.readUnsignedShort();
        opensToCount = file.readUnsignedShort();
        opensToIndex = new int[opensToCount];
        for (int i = 0; i < opensToCount; i++) {
            opensToIndex[i] = file.readUnsignedShort();
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
        ModuleOpens other = (ModuleOpens) obj;
        if (opensFlags != other.opensFlags)
            return false;
        if (opensIndex != other.opensIndex)
            return false;
        if (opensToCount != other.opensToCount)
            return false;
        if (!Arrays.equals(opensToIndex, other.opensToIndex))
            return false;
        return true;
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
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + opensFlags;
        result = prime * result + opensIndex;
        result = prime * result + opensToCount;
        result = prime * result + Arrays.hashCode(opensToIndex);
        return result;
    }
}
