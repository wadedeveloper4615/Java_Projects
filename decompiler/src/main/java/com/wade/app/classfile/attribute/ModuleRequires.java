package com.wade.app.classfile.attribute;

import java.io.DataInputStream;
import java.io.IOException;

public class ModuleRequires {
    private  int requiresIndex;
    private  int requiresFlags;
    private  int requiresVersionIndex;

    public ModuleRequires(DataInputStream file) throws IOException {
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
