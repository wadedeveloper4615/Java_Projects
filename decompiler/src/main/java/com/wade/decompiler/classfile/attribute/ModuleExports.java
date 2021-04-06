package com.wade.decompiler.classfile.attribute;

import java.io.DataInput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = true, includeFieldNames = true)
@EqualsAndHashCode
public class ModuleExports {
    private int exportsIndex;
    private int exportsFlags;
    private int exportsToCount;
    private List<Integer> exportsToIndex;

    public ModuleExports(DataInput file) throws IOException {
        exportsIndex = file.readUnsignedShort();
        exportsFlags = file.readUnsignedShort();
        exportsToCount = file.readUnsignedShort();
        exportsToIndex = new ArrayList<>();
        for (int i = 0; i < exportsToCount; i++) {
            exportsToIndex.add(file.readUnsignedShort());
        }
    }
}
