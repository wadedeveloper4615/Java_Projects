package com.wade.decompiler.classfile.attribute;

import java.io.DataInput;
import java.io.IOException;

public class ModuleRequires {
    private int requiresIndex;
    private int requiresFlags;
    private int requiresVersionIndex;

    public ModuleRequires(DataInput file) throws IOException {
        this.requiresIndex = file.readUnsignedShort();
        this.requiresFlags = file.readUnsignedShort();
        this.requiresVersionIndex = file.readUnsignedShort();
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

    public int getRequiresFlags() {
        return requiresFlags;
    }

    public int getRequiresIndex() {
        return requiresIndex;
    }

    public int getRequiresVersionIndex() {
        return requiresVersionIndex;
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

    @Override
    public String toString() {
        return "ModuleRequires [requiresIndex=" + requiresIndex + ", requiresFlags=" + requiresFlags + ", requiresVersionIndex=" + requiresVersionIndex + "]";
    }
}
