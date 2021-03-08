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
public class TABLESWITCH extends Instruction {
    protected int[] match;
    protected int[] indices;
    protected int fixed_length;
    protected int match_length;
    protected int padding = 0;

    public TABLESWITCH(int[] match, ConstantPool cp) {
        super(InstructionOpCodes.TABLESWITCH, 3, cp);
        this.match = match;
    }

    @Override
    protected void initFromFile(final ByteSequence bytes, final boolean wide) throws IOException {
    }
}
