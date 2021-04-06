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
    protected int[] offsets;
    protected int padding = 0;
    protected int defaultOffset;

    public TABLESWITCH(int[] match, ConstantPool cp) {
        super(InstructionOpCodes.TABLESWITCH, 3, cp);
        this.match = match;
    }

    @Override
    protected void initFromFile(final ByteSequence bytes, final boolean wide) throws IOException {
        padding = (4 - (bytes.getIndex() % 4)) % 4;
        for (int i = 0; i < padding; i++) {
            bytes.readByte();
        }
        setDefaultOffset(bytes.readInt());

        int low = bytes.readInt();
        int high = bytes.readInt();
        int matchlength = high - low + 1;
        int fixedlength = (13 + matchlength * 4);
        setLength(fixedlength + getPadding());
        setMatch(new int[matchlength]);
        setOffsets(new int[matchlength]);
        for (int i = 0; i < matchlength; i++) {
            match[i] = low + i;
            offsets[i] = bytes.readInt();
        }
    }
}
