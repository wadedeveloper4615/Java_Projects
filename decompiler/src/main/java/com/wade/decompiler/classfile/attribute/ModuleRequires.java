package com.wade.decompiler.classfile.attribute;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.DataInput;
import java.io.IOException;

@Setter
@Getter
@ToString(callSuper = true, includeFieldNames = true)
@EqualsAndHashCode
public class ModuleRequires {
    private int requiresIndex;
    private int requiresFlags;
    private int requiresVersionIndex;

    public ModuleRequires(DataInput file) throws IOException {
        this.requiresIndex = file.readUnsignedShort();
        this.requiresFlags = file.readUnsignedShort();
        this.requiresVersionIndex = file.readUnsignedShort();
    }
}
