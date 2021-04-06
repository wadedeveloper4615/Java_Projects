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
public class ModuleOpens {
    private int opensIndex;
    private int opensFlags;
    private int opensToCount;
    private List<Integer> opensToIndex;

    public ModuleOpens(DataInput file) throws IOException {
        opensIndex = file.readUnsignedShort();
        opensFlags = file.readUnsignedShort();
        opensToCount = file.readUnsignedShort();
        opensToIndex = new ArrayList<>();
        for (int i = 0; i < opensToCount; i++) {
            opensToIndex.add(file.readUnsignedShort());
        }
    }
}
