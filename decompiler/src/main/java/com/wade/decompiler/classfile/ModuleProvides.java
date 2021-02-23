package com.wade.decompiler.classfile;

import java.io.DataInput;
import java.io.IOException;
import java.util.Arrays;

public class ModuleProvides {
    private int providesIndex;
    private int providesWithCount;
    private int[] providesWithIndex;

    public ModuleProvides(DataInput file) throws IOException {
        providesIndex = file.readUnsignedShort();
        providesWithCount = file.readUnsignedShort();
        providesWithIndex = new int[providesWithCount];
        for (int i = 0; i < providesWithCount; i++) {
            providesWithIndex[i] = file.readUnsignedShort();
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
        ModuleProvides other = (ModuleProvides) obj;
        if (providesIndex != other.providesIndex)
            return false;
        if (providesWithCount != other.providesWithCount)
            return false;
        if (!Arrays.equals(providesWithIndex, other.providesWithIndex))
            return false;
        return true;
    }

    public int getProvidesIndex() {
        return providesIndex;
    }

    public int getProvidesWithCount() {
        return providesWithCount;
    }

    public int[] getProvidesWithIndex() {
        return providesWithIndex;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + providesIndex;
        result = prime * result + providesWithCount;
        result = prime * result + Arrays.hashCode(providesWithIndex);
        return result;
    }

    @Override
    public String toString() {
        return "ModuleProvides [providesIndex=" + providesIndex + ", providesWithCount=" + providesWithCount + ", providesWithIndex=" + Arrays.toString(providesWithIndex) + "]";
    }
}
