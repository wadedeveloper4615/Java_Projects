package com.wade.decompiler.classfile.attribute;

import java.io.DataInput;
import java.io.IOException;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.ClassFileAttributes;
import com.wade.decompiler.enums.ClassFileConstants;
import com.wade.decompiler.util.Utility;

public class ModulePackages extends Attribute {
    private int[] packageIndexTable;

    public ModulePackages(int name_index, int length, DataInput input, ConstantPool constant_pool) throws IOException {
        this(name_index, length, (int[]) null, constant_pool);
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

    public ModulePackages(ModulePackages c) {
        this(c.getNameIndex(), c.getLength(), c.getPackageIndexTable(), c.getConstantPool());
    }

    public int getNumberOfPackages() {
        return packageIndexTable == null ? 0 : packageIndexTable.length;
    }

    public int[] getPackageIndexTable() {
        return packageIndexTable;
    }

    public String[] getPackageNames() {
        String[] names = new String[packageIndexTable.length];
        for (int i = 0; i < packageIndexTable.length; i++) {
            names[i] = super.getConstantPool().getConstantString(packageIndexTable[i], ClassFileConstants.CONSTANT_Package).replace('/', '.');
        }
        return names;
    }

    public void setPackageIndexTable(int[] packageIndexTable) {
        this.packageIndexTable = packageIndexTable != null ? packageIndexTable : new int[0];
    }

    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder();
        buf.append("ModulePackages(");
        buf.append(packageIndexTable.length);
        buf.append("):\n");
        for (int index : packageIndexTable) {
            String package_name = super.getConstantPool().getConstantString(index, ClassFileConstants.CONSTANT_Package);
            buf.append("  ").append(Utility.compactClassName(package_name, false)).append("\n");
        }
        return buf.substring(0, buf.length() - 1); // remove the last newline
    }
}
