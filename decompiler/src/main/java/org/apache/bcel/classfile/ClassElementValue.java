
package org.apache.bcel.classfile;

import java.io.DataOutputStream;
import java.io.IOException;

import org.apache.bcel.Const;

public class ClassElementValue extends ElementValue {
    // For primitive types and string type, this points to the value entry in
    // the cpool
    // For 'class' this points to the class entry in the cpool
    private final int idx;

    public ClassElementValue(final int type, final int idx, final ConstantPool cpool) {
        super(type, cpool);
        this.idx = idx;
    }

    public int getIndex() {
        return idx;
    }

    public String getClassString() {
        final ConstantUtf8 c = (ConstantUtf8) super.getConstantPool().getConstant(idx, Const.CONSTANT_Utf8);
        return c.getBytes();
    }

    @Override
    public String stringifyValue() {
        final ConstantUtf8 cu8 = (ConstantUtf8) super.getConstantPool().getConstant(idx, Const.CONSTANT_Utf8);
        return cu8.getBytes();
    }

    @Override
    public void dump(final DataOutputStream dos) throws IOException {
        dos.writeByte(super.getType()); // u1 kind of value
        dos.writeShort(idx);
    }
}
