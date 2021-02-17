package com.wade.decompiler.generic.gen;

import java.io.DataOutputStream;
import java.io.IOException;

import com.wade.decompiler.classfile.ClassElementValue;
import com.wade.decompiler.classfile.ConstantUtf8;
import com.wade.decompiler.classfile.ElementValue;
import com.wade.decompiler.generic.type.ObjectType;

public class ClassElementValueGen extends ElementValueGen {
    // For primitive types and string type, this points to the value entry in
    // the cpool
    // For 'class' this points to the class entry in the cpool
    private int idx;

    public ClassElementValueGen(ClassElementValue value, ConstantPoolGen cpool, boolean copyPoolEntries) {
        super(CLASS, cpool);
        if (copyPoolEntries) {
            // idx = cpool.addClass(value.getClassString());
            idx = cpool.addUtf8(value.getClassString());
        } else {
            idx = value.getIndex();
        }
    }

    public ClassElementValueGen(int typeIdx, ConstantPoolGen cpool) {
        super(ElementValueGen.CLASS, cpool);
        this.idx = typeIdx;
    }

    public ClassElementValueGen(ObjectType t, ConstantPoolGen cpool) {
        super(ElementValueGen.CLASS, cpool);
        // this.idx = cpool.addClass(t);
        idx = cpool.addUtf8(t.getSignature());
    }

    @Override
    public void dump(DataOutputStream dos) throws IOException {
        dos.writeByte(super.getElementValueType()); // u1 kind of value
        dos.writeShort(idx);
    }

    public String getClassString() {
        ConstantUtf8 cu8 = (ConstantUtf8) getConstantPool().getConstant(idx);
        return cu8.getBytes();
        // ConstantClass c = (ConstantClass)getConstantPool().getConstant(idx);
        // ConstantUtf8 utf8 =
        // (ConstantUtf8)getConstantPool().getConstant(c.getNameIndex());
        // return utf8.getBytes();
    }

    @Override
    public ElementValue getElementValue() {
        return new ClassElementValue(super.getElementValueType(), idx, getConstantPool().getConstantPool());
    }

    public int getIndex() {
        return idx;
    }

    @Override
    public String stringifyValue() {
        return getClassString();
    }
}
