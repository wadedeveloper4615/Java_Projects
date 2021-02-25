package com.wade.decompiler.classfile.attribute;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Arrays;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.ClassFileAttributes;

public class ModulePackages extends Attribute {
    private int[] packageIndexTable;

    public ModulePackages(int name_index, int length, DataInputStream input, ConstantPool constantPool) throws IOException {
        this(name_index, length, (int[]) null, constantPool);
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        ModulePackages other = (ModulePackages) obj;
        if (!Arrays.equals(packageIndexTable, other.packageIndexTable))
            return false;
        return true;
    }

    public int[] getPackageIndexTable() {
        return packageIndexTable;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + Arrays.hashCode(packageIndexTable);
        return result;
    }

    @Override
    public String toString() {
        return "ModulePackages [packageIndexTable=" + Arrays.toString(packageIndexTable) + "]";
    }
}
