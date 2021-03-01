package com.wade.decompiler.classfile.attribute;

import java.io.DataInput;
import java.io.IOException;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.ClassFileAttributes;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
public class ModulePackages extends Attribute {
    private int[] packageIndexTable;

    public ModulePackages(int nameIndex, int length, DataInput input, ConstantPool constantPool) throws IOException {
        this(nameIndex, length, (int[]) null, constantPool);
        int number_of_packages = input.readUnsignedShort();
        packageIndexTable = new int[number_of_packages];
        for (int i = 0; i < number_of_packages; i++) {
            packageIndexTable[i] = input.readUnsignedShort();
        }
    }

    public ModulePackages(int nameIndex, int length, int[] packageIndexTable, ConstantPool constantPool) {
        super(ClassFileAttributes.ATTR_MODULE_PACKAGES, nameIndex, length, constantPool);
        this.packageIndexTable = packageIndexTable != null ? packageIndexTable : new int[0];
    }
}
