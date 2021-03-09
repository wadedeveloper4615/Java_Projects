package com.wade.decompiler.classfile.instructions;

import java.io.IOException;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.instructions.base.Instruction;
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
public class INVOKEINTERFACE extends Instruction {
    private int index;
    private int nargs;

    public INVOKEINTERFACE(ConstantPool cp) {
        super(InstructionOpCodes.INVOKEINTERFACE, 5, cp);
        super.setLength(5);
    }

    @Override
    public int consumeStack() {
        return nargs;
    }

    public int getCount() {
        return nargs;
    }

    @Override
    public void initFromFile(ByteSequence bytes, boolean wide) throws IOException {
        super.setLength(5);
        setIndex(bytes.readUnsignedShort());
        setNargs(bytes.readUnsignedByte());
        bytes.readByte();
    }
}
