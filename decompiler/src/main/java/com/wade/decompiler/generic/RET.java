package com.wade.decompiler.generic;

import java.io.IOException;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.constants.Const;
import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.ClassGenException;
import com.wade.decompiler.generic.base.IndexedInstruction;
import com.wade.decompiler.generic.base.Instruction;
import com.wade.decompiler.generic.base.TypedInstruction;
import com.wade.decompiler.generic.type.ReturnaddressType;
import com.wade.decompiler.generic.type.Type;
import com.wade.decompiler.util.ByteSequence;

public class RET extends Instruction implements IndexedInstruction, TypedInstruction {
    private boolean wide;
    private int index;

    public RET() {
    }

    public RET(int index, ConstantPool cp) {
        super(InstructionOpCodes.RET, 2, cp);
        setIndex(index);
    }

    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public Type getType(ConstantPool cp) {
        return ReturnaddressType.NO_TARGET;
    }

    @Override
    public void initFromFile(ByteSequence bytes, boolean wide) throws IOException {
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
    public void setIndex(int n) {
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
    public String toString() {
        return super.toString() + " " + index;
    }
}
