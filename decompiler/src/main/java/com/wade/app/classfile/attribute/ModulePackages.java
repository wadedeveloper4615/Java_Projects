package com.wade.app.classfile.attribute;

import java.io.DataInput;
import java.io.IOException;

import com.wade.app.constantpool.ConstantPool;
import com.wade.app.enums.ClassFileAttributes;

public  class ModulePackages extends Attribute {
    private int[] packageIndexTable;

    public ModulePackages( int name_index,  int length,  DataInput input,  ConstantPool constant_pool) throws IOException {
        this(name_index, length, (int[]) null, constant_pool);
         int number_of_packages = input.readUnsignedShort();
        packageIndexTable = new int[number_of_packages];
        for (int i = 0; i < number_of_packages; i++) {
            packageIndexTable[i] = input.readUnsignedShort();
        }
    }

    public ModulePackages( int nameIndex,  int length,  int[] packageIndexTable,  ConstantPool constantPool) {
        super(ClassFileAttributes.ATTR_MODULE_PACKAGES, nameIndex, length, constantPool);
        this.packageIndexTable = packageIndexTable != null ? packageIndexTable : new int[0];
    }

    public int[] getPackageIndexTable() {
        return packageIndexTable;
    }
}
