
package org.apache.bcel.classfile;

import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

import org.apache.bcel.Const;
import org.apache.bcel.enums.ClassFileConstants;

public final class ModulePackages extends Attribute {

    private int[] packageIndexTable;

    ModulePackages(final int name_index, final int length, final DataInput input, final ConstantPool constant_pool) throws IOException {
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

    @Override
    public void accept(final Visitor v) {
        v.visitModulePackages(this);
    }

    @Override
    public Attribute copy(final ConstantPool _constant_pool) {
        final ModulePackages c = (ModulePackages) clone();
        if (packageIndexTable != null) {
            c.packageIndexTable = new int[packageIndexTable.length];
            System.arraycopy(packageIndexTable, 0, c.packageIndexTable, 0, packageIndexTable.length);
        }
        c.setConstantPool(_constant_pool);
        return c;
    }

    @Override
    public void dump(final DataOutputStream file) throws IOException {
        super.dump(file);
        file.writeShort(packageIndexTable.length);
        for (final int index : packageIndexTable) {
            file.writeShort(index);
        }
    }

    public int getNumberOfPackages() {
        return packageIndexTable == null ? 0 : packageIndexTable.length;
    }

    public int[] getPackageIndexTable() {
        return packageIndexTable;
    }

    public String[] getPackageNames() {
        final String[] names = new String[packageIndexTable.length];
        for (int i = 0; i < packageIndexTable.length; i++) {
            names[i] = super.getConstantPool().getConstantString(packageIndexTable[i], ClassFileConstants.CONSTANT_Package).replace('/', '.');
        }
        return names;
    }

    public void setPackageIndexTable(final int[] packageIndexTable) {
        this.packageIndexTable = packageIndexTable != null ? packageIndexTable : new int[0];
    }

    @Override
    public String toString() {
        final StringBuilder buf = new StringBuilder();
        buf.append("ModulePackages(");
        buf.append(packageIndexTable.length);
        buf.append("):\n");
        for (final int index : packageIndexTable) {
            final String package_name = super.getConstantPool().getConstantString(index, ClassFileConstants.CONSTANT_Package);
            buf.append("  ").append(Utility.compactClassName(package_name, false)).append("\n");
        }
        return buf.substring(0, buf.length() - 1); // remove the last newline
    }
}
