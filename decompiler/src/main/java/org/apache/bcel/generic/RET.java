package org.apache.bcel.generic;

import java.io.DataOutputStream;
import java.io.IOException;

import org.apache.bcel.enums.InstructionOpCodes;
import org.apache.bcel.generic.base.ClassGenException;
import org.apache.bcel.generic.base.IndexedInstruction;
import org.apache.bcel.generic.base.Instruction;
import org.apache.bcel.generic.base.ReturnaddressType;
import org.apache.bcel.generic.base.TypedInstruction;
import org.apache.bcel.generic.base.Visitor;
import org.apache.bcel.generic.gen.ConstantPoolGen;
import org.apache.bcel.util.ByteSequence;

public class RET extends Instruction implements IndexedInstruction, TypedInstruction {
    private boolean wide;
    private int index; 

    public RET() {
    }

    public RET(final int index) {
        super(InstructionOpCodes.RET, (short) 2);
        setIndex(index); 
    }

    @Override
    public void accept(final Visitor v) {
        v.visitRET(this);
    }

    @Override
    public void dump(final DataOutputStream out) throws IOException {
        if (wide) {
            out.writeByte(InstructionOpCodes.WIDE.getOpcode());
        }
        out.writeByte(super.getOpcode().getOpcode());
        if (wide) {
            out.writeShort(index);
        } else {
            out.writeByte(index);
        }
    }

    @Override
    public final int getIndex() {
        return index;
    }

    @Override
    public Type getType(final ConstantPoolGen cp) {
        return ReturnaddressType.NO_TARGET;
    }

    @Override
    protected void initFromFile(final ByteSequence bytes, final boolean wide) throws IOException {
        this.wide = wide;
        if (wide) {
            index = bytes.readUnsignedShort();
            super.setLength(4);
        } else {
            index = bytes.readUnsignedByte();
            super.setLength(2);
        }
    }

    @Override
    public final void setIndex(final int n) {
        if (n < 0) {
            throw new ClassGenException("Negative index value: " + n);
        }
        index = n;
        setWide();
    }

    private void setWide() {
        wide = index > org.apache.bcel.Const.MAX_BYTE;
        if (wide) {
            super.setLength(4); 
        } else {
            super.setLength(2);
        }
    }

    @Override
    public String toString(final boolean verbose) {
        return super.toString(verbose) + " " + index;
    }
}
