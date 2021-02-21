package com.wade.decompiler.classfile;

import java.io.DataInput;
import java.io.IOException;

public class ModuleRequires {
    private int requiresIndex;
    private int requiresFlags;
    private int requiresVersionIndex;

    public ModuleRequires(DataInput file) throws IOException {
        requiresIndex = file.readUnsignedShort();
        requiresFlags = file.readUnsignedShort();
        requiresVersionIndex = file.readUnsignedShort();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ModuleRequires other = (ModuleRequires) obj;
        if (requiresFlags != other.requiresFlags)
            return false;
        if (requiresIndex != other.requiresIndex)
            return false;
        if (requiresVersionIndex != other.requiresVersionIndex)
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + requiresFlags;
        result = prime * result + requiresIndex;
        result = prime * result + requiresVersionIndex;
        return result;
    }
}
