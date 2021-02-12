package org.apache.bcel.generic.gen;

import java.io.DataOutputStream;
import java.io.IOException;

import org.apache.bcel.classfile.constant.ConstantUtf8;
import org.apache.bcel.classfile.element.ClassElementValue;
import org.apache.bcel.classfile.element.ElementValue;
import org.apache.bcel.generic.ObjectType;

public class ClassElementValueGen extends ElementValueGen {
    private int idx;

    protected ClassElementValueGen(final int typeIdx, final ConstantPoolGen cpool) {
        super(ElementValueGen.CLASS, cpool);
        this.idx = typeIdx;
    }

    public ClassElementValueGen(final ObjectType t, final ConstantPoolGen cpool) {
        super(ElementValueGen.CLASS, cpool);
        idx = cpool.addUtf8(t.getSignature());
    }

    @Override
    public ElementValue getElementValue() {
        return new ClassElementValue(super.getElementValueType(), idx, getConstantPool().getConstantPool());
    }

    public ClassElementValueGen(final ClassElementValue value, final ConstantPoolGen cpool, final boolean copyPoolEntries) {
        super(CLASS, cpool);
        if (copyPoolEntries) {
            idx = cpool.addUtf8(value.getClassString());
        } else {
            idx = value.getIndex();
        }
    }

    public int getIndex() {
        return idx;
    }

    public String getClassString() {
        final ConstantUtf8 cu8 = (ConstantUtf8) getConstantPool().getConstant(idx);
        return cu8.getBytes();
    }

    @Override
    public String stringifyValue() {
        return getClassString();
    }

    @Override
    public void dump(final DataOutputStream dos) throws IOException {
        dos.writeByte(super.getElementValueType());
        dos.writeShort(idx);
    }
}
