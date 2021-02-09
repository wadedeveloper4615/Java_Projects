package com.wade.app.classfile.attribute;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Arrays;

public class ModuleProvides {
    private int providesIndex;
    private int providesWithCount;
    private int[] providesWithIndex;

    public ModuleProvides(DataInputStream file) throws IOException {
        providesIndex = file.readUnsignedShort();
        providesWithCount = file.readUnsignedShort();
        providesWithIndex = new int[providesWithCount];
        for (int i = 0; i < providesWithCount; i++) {
            providesWithIndex[i] = file.readUnsignedShort();
        }
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
    public String toString() {
        return "ModuleProvides [providesIndex=" + providesIndex + ", providesWithCount=" + providesWithCount + ", providesWithIndex=" + Arrays.toString(providesWithIndex) + "]";
    }
}
