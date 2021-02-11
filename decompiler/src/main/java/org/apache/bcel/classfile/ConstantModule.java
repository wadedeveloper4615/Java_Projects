
package org.apache.bcel.classfile;

import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

import org.apache.bcel.Const;

public final class ConstantModule extends Constant implements ConstantObject {

    private int nameIndex;

    public ConstantModule(final ConstantModule c) {
        this(c.getNameIndex());
    }

    ConstantModule(final DataInput file) throws IOException {
        this(file.readUnsignedShort());
    }

    public ConstantModule(final int nameIndex) {
        super(Const.CONSTANT_Module);
        this.nameIndex = nameIndex;
    }

    @Override
    public void accept(final Visitor v) {
        v.visitConstantModule(this);
    }

    @Override
    public void dump(final DataOutputStream file) throws IOException {
        file.writeByte(super.getTag());
        file.writeShort(nameIndex);
    }

    public int getNameIndex() {
        return nameIndex;
    }

    public void setNameIndex(final int nameIndex) {
        this.nameIndex = nameIndex;
    }

    @Override
    public Object getConstantValue(final ConstantPool cp) {
        final Constant c = cp.getConstant(nameIndex, Const.CONSTANT_Utf8);
        return ((ConstantUtf8) c).getBytes();
    }

    public String getBytes(final ConstantPool cp) {
        return (String) getConstantValue(cp);
    }

    @Override
    public String toString() {
        return super.toString() + "(nameIndex = " + nameIndex + ")";
    }
}
