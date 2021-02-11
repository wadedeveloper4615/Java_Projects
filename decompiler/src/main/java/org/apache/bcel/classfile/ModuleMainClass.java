
package org.apache.bcel.classfile;

import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

import org.apache.bcel.Const;

public final class ModuleMainClass extends Attribute {

    private int mainClassIndex;

    public ModuleMainClass(final ModuleMainClass c) {
        this(c.getNameIndex(), c.getLength(), c.getHostClassIndex(), c.getConstantPool());
    }

    public ModuleMainClass(final int name_index, final int length, final int mainClassIndex, final ConstantPool constantPool) {
        super(Const.ATTR_NEST_MEMBERS, name_index, length, constantPool);
        this.mainClassIndex = mainClassIndex;
    }

    ModuleMainClass(final int nameIndex, final int length, final DataInput input, final ConstantPool constantPool) throws IOException {
        this(nameIndex, length, 0, constantPool);
        mainClassIndex = input.readUnsignedShort();
    }

    @Override
    public void accept(final Visitor v) {
        v.visitModuleMainClass(this);
    }

    @Override
    public void dump(final DataOutputStream file) throws IOException {
        super.dump(file);
        file.writeShort(mainClassIndex);
    }

    public int getHostClassIndex() {
        return mainClassIndex;
    }

    public void setHostClassIndex(final int mainClassIndex) {
        this.mainClassIndex = mainClassIndex;
    }

    @Override
    public String toString() {
        final StringBuilder buf = new StringBuilder();
        buf.append("ModuleMainClass: ");
        final String class_name = super.getConstantPool().getConstantString(mainClassIndex, Const.CONSTANT_Class);
        buf.append(Utility.compactClassName(class_name, false));
        return buf.toString();
    }

    @Override
    public Attribute copy(final ConstantPool _constant_pool) {
        final ModuleMainClass c = (ModuleMainClass) clone();
        c.setConstantPool(_constant_pool);
        return c;
    }
}
