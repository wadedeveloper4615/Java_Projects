package org.apache.bcel.generic.gen;

import java.io.DataOutputStream;
import java.io.IOException;

import org.apache.bcel.classfile.constant.ConstantUtf8;
import org.apache.bcel.classfile.element.ClassElementValue;
import org.apache.bcel.classfile.element.ElementValue;
import org.apache.bcel.generic.ObjectType;

public class ClassElementValueGen extends ElementValueGen {
    // For primitive types and string type, this points to the value entry in
    // the cpool
    // For 'class' this points to the class entry in the cpool
    private int idx;

    protected ClassElementValueGen(final int typeIdx, final ConstantPoolGen cpool) {
        super(ElementValueGen.CLASS, cpool);
        this.idx = typeIdx;
    }

    public ClassElementValueGen(final ObjectType t, final ConstantPoolGen cpool) {
        super(ElementValueGen.CLASS, cpool);
        // this.idx = cpool.addClass(t);
        idx = cpool.addUtf8(t.getSignature());
    }

    @Override
    public ElementValue getElementValue() {
        return new ClassElementValue(super.getElementValueType(), idx, getConstantPool().getConstantPool());
    }

    public ClassElementValueGen(final ClassElementValue value, final ConstantPoolGen cpool, final boolean copyPoolEntries) {
        super(CLASS, cpool);
        if (copyPoolEntries) {
            // idx = cpool.addClass(value.getClassString());
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
        // ConstantClass c = (ConstantClass)getConstantPool().getConstant(idx);
        // ConstantUtf8 utf8 =
        // (ConstantUtf8)getConstantPool().getConstant(c.getNameIndex());
        // return utf8.getBytes();
    }

    @Override
    public String stringifyValue() {
        return getClassString();
    }

    @Override
    public void dump(final DataOutputStream dos) throws IOException {
        dos.writeByte(super.getElementValueType()); // u1 kind of value
        dos.writeShort(idx);
    }
}
