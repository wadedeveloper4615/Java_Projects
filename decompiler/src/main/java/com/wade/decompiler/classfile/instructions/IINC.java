package com.wade.decompiler.classfile.instructions;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.instructions.base.Instruction;
import com.wade.decompiler.classfile.instructions.type.Type;
import com.wade.decompiler.constants.Const;
import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.util.ByteSequence;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.IOException;

@Setter
@Getter
@ToString(callSuper = true, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
public class IINC extends Instruction {
    private boolean wide;
    private int increment;
    private int index;
    private Type type;

    public IINC(ConstantPool cp) {
        super(InstructionOpCodes.IINC, 3, cp);
        type = Type.INTEGER;
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
        wide = index > Const.MAX_BYTE;
        if (increment > 0) {
            wide = wide || (increment > Byte.MAX_VALUE);
        } else {
            wide = wide || (increment < Byte.MIN_VALUE);
        }
        if (wide) {
            super.setLength(6);
        } else {
            super.setLength(3);
        }
    }

}
