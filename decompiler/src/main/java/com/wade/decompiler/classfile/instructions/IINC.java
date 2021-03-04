package com.wade.decompiler.classfile.instructions;

import java.io.IOException;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.exceptions.ClassGenException;
import com.wade.decompiler.classfile.instructions.base.LocalVariableInstruction;
import com.wade.decompiler.classfile.instructions.type.Type;
import com.wade.decompiler.constants.Const;
import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.util.ByteSequence;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = true, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
public class IINC extends LocalVariableInstruction {
    private boolean wide;
    private int increment;

    public IINC(int n, int c, ConstantPool cp) {
        super(InstructionOpCodes.IINC, InstructionOpCodes.IINC, cp);
        super.setConstantPool(cp);
        super.setLength((short) 3);
        setIndex(n);
        setIncrement(c);
    }

    @Override
    public Type getType() {
        return Type.INT;
    }

    @Override
    public void initFromFile(ByteSequence bytes, boolean wide) throws IOException {
        this.wide = wide;
        if (wide) {
            super.setLength(6);
            index = bytes.readUnsignedByte();
            increment = bytes.readShort();
        } else {
            super.setLength(3);
            index = bytes.readUnsignedByte();
            increment = bytes.readByte();
        }
    }

    public void setIncrement(int c) {
        this.increment = c;
        setWide();
    }

    @Override
    public void setIndex(int n) {
        if (n < 0) {
            throw new ClassGenException("Negative index value: " + n);
        }
        super.index = n;
        setWide();
    }

    private void setWide() {
        wide = super.getIndex() > Const.MAX_BYTE;
        if (increment > 0) {
            wide = wide || (increment > Byte.MAX_VALUE);
        } else {
            wide = wide || (increment < Byte.MIN_VALUE);
        }
        if (wide) {
            super.setLength(6); // wide byte included
        } else {
            super.setLength(3);
        }
    }
}
