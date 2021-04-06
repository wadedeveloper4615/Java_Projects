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
public class ModulePackages extends Attribute {
    private List<Integer> packageIndexTable;

    public ModulePackages(int nameIndex, int length, DataInput input, ConstantPool constantPool) throws IOException {
        this(nameIndex, length, (List<Integer>) null, constantPool);
        int number_of_packages = input.readUnsignedShort();
        packageIndexTable = new ArrayList<>();
        for (int i = 0; i < number_of_packages; i++) {
            packageIndexTable.add(input.readUnsignedShort());
        }
    }

    public ModulePackages(int nameIndex, int length, List<Integer> packageIndexTable, ConstantPool constantPool) {
        super(ClassFileAttributes.ATTR_MODULE_PACKAGES, nameIndex, length, constantPool);
        this.packageIndexTable = packageIndexTable;
    }
}
