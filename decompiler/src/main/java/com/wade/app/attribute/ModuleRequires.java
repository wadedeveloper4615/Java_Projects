package com.wade.app.attribute;

import java.io.DataInputStream;
import java.io.IOException;

import com.wade.app.constantpool.Node;

public final class ModuleRequires implements Node {
    private final int requiresIndex;
    private final int requiresFlags;
    private final int requiresVersionIndex;

    public ModuleRequires(final DataInputStream file) throws IOException {
        requiresIndex = file.readUnsignedShort();
        requiresFlags = file.readUnsignedShort();
        requiresVersionIndex = file.readUnsignedShort();
    }

    public int getRequiresFlags() {
        return requiresFlags;
    }

    public int getRequiresIndex() {
        return requiresIndex;
    }

    public int getRequiresVersionIndex() {
        return requiresVersionIndex;
    }
}
