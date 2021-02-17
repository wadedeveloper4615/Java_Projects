package com.wade.decompiler.generic.base;

import java.io.DataOutputStream;
import java.io.IOException;

import com.wade.decompiler.Const;
import com.wade.decompiler.classfile.Constant;
import com.wade.decompiler.classfile.ConstantClass;
import com.wade.decompiler.classfile.ConstantPool;
import com.wade.decompiler.enums.ClassFileConstants;
import com.wade.decompiler.generic.gen.ClassGenException;
import com.wade.decompiler.generic.gen.ConstantPoolGen;
import com.wade.decompiler.util.ByteSequence;

public abstract class CPInstruction extends Instruction implements TypedInstruction, IndexedInstruction {
    protected int index; // index to constant pool

    public CPInstruction() {
    }

    protected CPInstruction(final short opcode, final int index) {
        super(opcode, (short) 3);
        setIndex(index);
    }

    @Override
    public void dump(final DataOutputStream out) throws IOException {
        out.writeByte(super.getOpcode());
        out.writeShort(index);
    }

    @Override
    public final int getIndex() {
        return index;
    }

    @Override
    public Type getType(final ConstantPoolGen cpg) {
        final ConstantPool cp = cpg.getConstantPool();
        String name = cp.getConstantString(index, ClassFileConstants.CONSTANT_Class);
        if (!name.startsWith("[")) {
            name = "L" + name + ";";
        }
        return Type.getType(name);
    }

    @Override
    public void initFromFile(final ByteSequence bytes, final boolean wide) throws IOException {
        setIndex(bytes.readUnsignedShort());
        super.setLength(3);
    }

    @Override
    public void setIndex(final int index) { // TODO could be package-protected?
        if (index < 0) {
            throw new ClassGenException("Negative index value: " + index);
        }
        this.index = index;
    }

    @Override
    public String toString(final boolean verbose) {
        return super.toString(verbose) + " " + index;
    }

    @Override
    public String toString(final ConstantPool cp) {
        final Constant c = cp.getConstant(index);
        String str = cp.constantToString(c);
        if (c instanceof ConstantClass) {
            str = str.replace('.', '/');
        }
        return Const.getOpcodeName(super.getOpcode()) + " " + str;
    }
}
