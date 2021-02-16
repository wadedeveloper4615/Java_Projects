package com.wade.decompiler.classfile;

import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

import com.wade.decompiler.Const;
import com.wade.decompiler.enums.ClassFileAttributes;

public class ConstantValue extends Attribute {
    private int constantValueIndex;

    public ConstantValue(ConstantValue c) {
        this(c.getNameIndex(), c.getLength(), c.getConstantValueIndex(), c.getConstantPool());
    }

    public ConstantValue(int name_index, int length, DataInput input, ConstantPool constant_pool) throws IOException {
        this(name_index, length, input.readUnsignedShort(), constant_pool);
    }

    public ConstantValue(int name_index, int length, int constantValueIndex, ConstantPool constant_pool) {
        super(ClassFileAttributes.ATTR_CONSTANT_VALUE, name_index, length, constant_pool);
        this.constantValueIndex = constantValueIndex;
    }

    @Override
    public void accept(Visitor v) {
        v.visitConstantValue(this);
    }

    @Override
    public Attribute copy(ConstantPool _constant_pool) {
        ConstantValue c = (ConstantValue) clone();
        c.setConstantPool(_constant_pool);
        return c;
    }

    @Override
    public void dump(DataOutputStream file) throws IOException {
        super.dump(file);
        file.writeShort(constantValueIndex);
    }

    public int getConstantValueIndex() {
        return constantValueIndex;
    }

    public void setConstantValueIndex(int constantValueIndex) {
        this.constantValueIndex = constantValueIndex;
    }

    @Override
    public String toString() {
        Constant c = super.getConstantPool().getConstant(constantValueIndex);
        String buf = switch (c.getTag()) {
            case Const.CONSTANT_Long -> String.valueOf(((ConstantLong) c).getBytes());
            case Const.CONSTANT_Float -> String.valueOf(((ConstantFloat) c).getBytes());
            case Const.CONSTANT_Double -> String.valueOf(((ConstantDouble) c).getBytes());
            case Const.CONSTANT_Integer -> String.valueOf(((ConstantInteger) c).getBytes());
            case Const.CONSTANT_String -> {
                int i = ((ConstantString) c).getStringIndex();
                c = super.getConstantPool().getConstant(i, Const.CONSTANT_Utf8);
                yield "\"" + Utility.convertString(((ConstantUtf8) c).getBytes()) + "\"";
            }
            default -> throw new IllegalStateException("Type of ConstValue invalid: " + c);
        };
        return buf;
    }
}
