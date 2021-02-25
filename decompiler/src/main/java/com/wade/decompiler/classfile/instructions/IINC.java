package com.wade.decompiler.classfile.instructions;

import java.io.IOException;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.instructions.base.ClassGenException;
import com.wade.decompiler.classfile.instructions.base.LocalVariableInstruction;
import com.wade.decompiler.classfile.instructions.type.Type;
import com.wade.decompiler.constants.Const;
import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.util.ByteSequence;

public class IINC extends LocalVariableInstruction {
    private boolean wide;
    private int c;

    public IINC() {
    }

    public IINC(int n, int c, ConstantPool cp) {
        super();
        super.setConstantPool(cp);
        super.setOpcode(InstructionOpCodes.IINC);
        super.setLength((short) 3);
        setIndex(n);
        setIncrement(c);
    }

    public int getIncrement() {
        return c;
    }

    @Override
    public Type getType(ConstantPool cp) {
        return Type.INT;
    }

    @Override
    public void initFromFile(ByteSequence bytes, boolean wide) throws IOException {
        this.wide = wide;
        if (wide) {
            super.setLength(6);
            super.setIndexOnly(bytes.readUnsignedShort());
            c = bytes.readShort();
        } else {
            super.setLength(3);
            super.setIndexOnly(bytes.readUnsignedByte());
            c = bytes.readByte();
        }
    }

    public void setIncrement(int c) {
        this.c = c;
        setWide();
    }

    @Override
    public void setIndex(int n) {
        if (n < 0) {
            throw new ClassGenException("Negative index value: " + n);
        }
        super.setIndexOnly(n);
        setWide();
    }

    private void setWide() {
        wide = super.getIndex() > Const.MAX_BYTE;
        if (c > 0) {
            wide = wide || (c > Byte.MAX_VALUE);
        } else {
            wide = wide || (c < Byte.MIN_VALUE);
        }
        if (wide) {
            super.setLength(6); // wide byte included
        } else {
            super.setLength(3);
        }
    }

    @Override
    public String toString() {
        return super.toString() + " " + c;
    }
}