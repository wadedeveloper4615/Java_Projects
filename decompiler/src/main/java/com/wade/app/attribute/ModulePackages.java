package com.wade.app.attribute;

import java.io.DataInputStream;
import java.io.IOException;

import com.wade.app.Const;
import com.wade.app.constantpool.ConstantPool;
import com.wade.app.enums.ClassFileConstants;
import com.wade.app.exception.ClassFormatException;

public final class ModulePackages extends Attribute {
    private int[] packageIndexTable;

    public ModulePackages(final int name_index, final int length, final DataInputStream input, final ConstantPool constant_pool) throws IOException {
        this(name_index, length, (int[]) null, constant_pool);
        final int number_of_packages = input.readUnsignedShort();
        packageIndexTable = new int[number_of_packages];
        for (int i = 0; i < number_of_packages; i++) {
            packageIndexTable[i] = input.readUnsignedShort();
        }
    }

    public ModulePackages(final int nameIndex, final int length, final int[] packageIndexTable, final ConstantPool constantPool) {
        super(Const.ATTR_MODULE_PACKAGES, nameIndex, length, constantPool);
        this.packageIndexTable = packageIndexTable != null ? packageIndexTable : new int[0];
    }

    public ModulePackages(final ModulePackages c) {
        this(c.getNameIndex(), c.getLength(), c.getPackageIndexTable(), c.getConstantPool());
    }

    public int getNumberOfPackages() {
        return packageIndexTable == null ? 0 : packageIndexTable.length;
    }

    public int[] getPackageIndexTable() {
        return packageIndexTable;
    }

    public String[] getPackageNames() throws ClassFormatException {
        final String[] names = new String[packageIndexTable.length];
        for (int i = 0; i < packageIndexTable.length; i++) {
            names[i] = super.getConstantPool().getConstantString(packageIndexTable[i], ClassFileConstants.CONSTANT_Package).replace('/', '.');
        }
        return names;
    }

}
