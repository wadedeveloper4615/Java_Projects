package com.wade.decompiler.generic.base;

import java.io.DataOutputStream;
import java.io.IOException;

import com.wade.decompiler.Const;
import com.wade.decompiler.classfile.constant.Constant;
import com.wade.decompiler.classfile.constant.ConstantClass;
import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.ClassFileConstants;
import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.type.Type;
import com.wade.decompiler.util.ByteSequence;

public abstract class CPInstruction extends Instruction implements TypedInstruction, IndexedInstruction {
    protected int index;

    public CPInstruction() {
    }

    public CPInstruction(InstructionOpCodes opcode, int index) {
        super(opcode, 3);
        setIndex(index);
    }

    @Override
    public void dump(DataOutputStream out) throws IOException {
        out.writeByte(super.getOpcode().getOpcode());
        out.writeShort(index);
    }

    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public Type getType(ConstantPool cp) {
        String name = cp.getConstantString(index, ClassFileConstants.CONSTANT_Class);
        if (!name.startsWith("[")) {
            name = "L" + name + ";";
        }
        return Type.getType(name);
    }

    @Override
    public void initFromFile(ByteSequence bytes, boolean wide) throws IOException {
        setIndex(bytes.readUnsignedShort());
        super.setLength(3);
    }

    @Override
    public void setIndex(int index) { // TODO could be package-protected?
        if (index < 0) {
            throw new ClassGenException("Negative index value: " + index);
        }
        this.index = index;
    }

    @Override
    public String toString(boolean verbose) {
        return super.toString(verbose) + " " + index;
    }

    @Override
    public String toString(ConstantPool cp) {
        Constant c = cp.getConstant(index);
        String str = cp.constantToString(c);
        if (c instanceof ConstantClass) {
            str = str.replace('.', '/');
        }
        return Const.getOpcodeName(super.getOpcode()) + " " + str;
    }
}
