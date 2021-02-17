package com.wade.decompiler.generic;

import java.io.DataOutputStream;
import java.io.IOException;

import com.wade.decompiler.Const;
import com.wade.decompiler.generic.base.IndexedInstruction;
import com.wade.decompiler.generic.base.Instruction;
import com.wade.decompiler.generic.base.ReturnaddressType;
import com.wade.decompiler.generic.base.Type;
import com.wade.decompiler.generic.base.TypedInstruction;
import com.wade.decompiler.generic.gen.ClassGenException;
import com.wade.decompiler.generic.gen.ConstantPoolGen;
import com.wade.decompiler.generic.gen.Visitor;
import com.wade.decompiler.util.ByteSequence;

public class RET extends Instruction implements IndexedInstruction, TypedInstruction {
    private boolean wide;
    private int index; // index to local variable containg the return address

    public RET() {
    }

    public RET(final int index) {
        super(Const.RET, (short) 2);
        setIndex(index); // May set wide as side effect
    }

    @Override
    public void accept(final Visitor v) {
        v.visitRET(this);
    }

    @Override
    public void dump(final DataOutputStream out) throws IOException {
        if (wide) {
            out.writeByte(Const.WIDE);
        }
        out.writeByte(super.getOpcode());
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
    public void initFromFile(final ByteSequence bytes, final boolean wide) throws IOException {
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
        wide = index > Const.MAX_BYTE;
        if (wide) {
            super.setLength(4); // Including the wide byte
        } else {
            super.setLength(2);
        }
    }

    @Override
    public String toString(final boolean verbose) {
        return super.toString(verbose) + " " + index;
    }
}
