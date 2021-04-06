package com.wade.decompiler.classfile.attribute;

import java.io.DataInput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.ClassFileAttributes;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = true, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
public class Module extends Attribute {
    private int moduleNameIndex;
    private int moduleFlags;
    private int moduleVersionIndex;
    private List<ModuleRequires> requiresTable;
    private List<ModuleExports> exportsTable;
    private List<ModuleOpens> opensTable;
    private int usesCount;
    private List<Integer> usesIndex;
    private List<ModuleProvides> providesTable;

    public Module(int nameIndex, int length, DataInput input, ConstantPool constantPool) throws IOException {
        super(ClassFileAttributes.ATTR_MODULE, nameIndex, length, constantPool);
        moduleNameIndex = input.readUnsignedShort();
        moduleFlags = input.readUnsignedShort();
        moduleVersionIndex = input.readUnsignedShort();

        int requires_count = input.readUnsignedShort();
        requiresTable = new ArrayList<>();
        for (int i = 0; i < requires_count; i++) {
            requiresTable.add(new ModuleRequires(input));
        }

        int exports_count = input.readUnsignedShort();
        exportsTable = new ArrayList<>();
        for (int i = 0; i < exports_count; i++) {
            exportsTable.add(new ModuleExports(input));
        }

        int opens_count = input.readUnsignedShort();
        opensTable = new ArrayList<>();
        for (int i = 0; i < opens_count; i++) {
            opensTable.add(new ModuleOpens(input));
        }

        usesCount = input.readUnsignedShort();
        usesIndex = new ArrayList<>();
        for (int i = 0; i < usesCount; i++) {
            usesIndex.add(input.readUnsignedShort());
        }

        int provides_count = input.readUnsignedShort();
        providesTable = new ArrayList<>();
        for (int i = 0; i < provides_count; i++) {
            providesTable.add(new ModuleProvides(input));
        }
    }
}
