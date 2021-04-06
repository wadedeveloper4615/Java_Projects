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
public class ModuleProvides {
    private int providesIndex;
    private int providesWithCount;
    private List<Integer> providesWithIndex;

    public ModuleProvides(DataInput file) throws IOException {
        providesIndex = file.readUnsignedShort();
        providesWithCount = file.readUnsignedShort();
        providesWithIndex = new ArrayList<>();
        for (int i = 0; i < providesWithCount; i++) {
            providesWithIndex.add(file.readUnsignedShort());
        }
    }
}
