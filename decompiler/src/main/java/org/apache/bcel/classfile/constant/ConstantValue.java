package org.apache.bcel.classfile.constant;

import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

import org.apache.bcel.Const;
import org.apache.bcel.classfile.Utility;
import org.apache.bcel.classfile.Visitor;
import org.apache.bcel.classfile.attribute.Attribute;
import org.apache.bcel.enums.ClassFileConstants;

public final class ConstantValue extends Attribute {
    private int constantValueIndex;

    public ConstantValue(final ConstantValue c) {
        this(c.getNameIndex(), c.getLength(), c.getConstantValueIndex(), c.getConstantPool());
    }

    public ConstantValue(final int name_index, final int length, final DataInput input, final ConstantPool constant_pool) throws IOException {
        this(name_index, length, input.readUnsignedShort(), constant_pool);
    }

    public ConstantValue(final int name_index, final int length, final int constantValueIndex, final ConstantPool constant_pool) {
        super(Const.ATTR_CONSTANT_VALUE, name_index, length, constant_pool);
        this.constantValueIndex = constantValueIndex;
    }

    @Override
    public void accept(final Visitor v) {
        v.visitConstantValue(this);
    }

    @Override
    public Attribute copy(final ConstantPool _constant_pool) {
        final ConstantValue c = (ConstantValue) clone();
        c.setConstantPool(_constant_pool);
        return c;
    }

    @Override
    public void dump(final DataOutputStream file) throws IOException {
        super.dump(file);
        file.writeShort(constantValueIndex);
    }

    public int getConstantValueIndex() {
        return constantValueIndex;
    }

    public void setConstantValueIndex(final int constantValueIndex) {
        this.constantValueIndex = constantValueIndex;
    }

    @Override
    public String toString() {
        Constant c = super.getConstantPool().getConstant(constantValueIndex);
        int i;
        String buf = switch (c.getTag()) {
            case CONSTANT_Long -> String.valueOf(((ConstantLong) c).getBytes());
            case CONSTANT_Float -> String.valueOf(((ConstantFloat) c).getBytes());
            case CONSTANT_Double -> String.valueOf(((ConstantDouble) c).getBytes());
            case CONSTANT_Integer -> String.valueOf(((ConstantInteger) c).getBytes());
            case CONSTANT_String -> {
                i = ((ConstantString) c).getStringIndex();
                c = super.getConstantPool().getConstant(i, ClassFileConstants.CONSTANT_Utf8);
                yield "\"" + Utility.convertString(((ConstantUtf8) c).getBytes()) + "\"";
            }
            default -> throw new IllegalStateException("Type of ConstValue invalid: " + c);
        };
        return buf;
    }
}
