package com.wade.app.attribute;

import java.io.DataInputStream;
import java.io.IOException;

import com.wade.app.constantpool.Node;

public final class ModuleProvides implements Node {
    private final int providesIndex;
    private final int providesWithCount;
    private final int[] providesWithIndex;

    public ModuleProvides(final DataInputStream file) throws IOException {
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
}
