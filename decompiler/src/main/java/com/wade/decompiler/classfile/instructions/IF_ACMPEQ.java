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
public class IF_ACMPEQ extends Instruction {
    private IF_ACMPNE negate;
    private short index;

    public IF_ACMPEQ(ConstantPool cp) {
        super(InstructionOpCodes.IF_ACMPEQ, 3, cp);
        negate = new IF_ACMPNE(cp);
    }

    @Override
    protected void initFromFile(final ByteSequence bytes, final boolean wide) throws IOException {
        index = bytes.readShort();
    }
}
