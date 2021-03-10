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
public class LOOKUPSWITCH extends Instruction {
    protected int[] match;
    protected int[] offset;
    protected int padding;
    protected int defaultOffset;

    public LOOKUPSWITCH(int[] match, ConstantPool cp) {
        super(InstructionOpCodes.LOOKUPSWITCH, 3, cp);
        this.match = match;
    }

    @Override
    protected void initFromFile(final ByteSequence bytes, final boolean wide) throws IOException {
        padding = (4 - (bytes.getIndex() % 4)) % 4; // Compute number of pad bytes
        for (int i = 0; i < padding; i++) {
            bytes.readByte();
        }
        setDefaultOffset(bytes.readInt() + 1);

        int matchlength = bytes.readInt();
        int fixedlength = (short) (9 + matchlength * 8);
        int length = fixedlength + 1;// (short) (matchlength + getPadding());
        setLength(length);
        setMatch(new int[matchlength]);
        setOffset(new int[matchlength]);
        for (int i = 0; i < matchlength; i++) {
            match[i] = bytes.readInt();
            offset[i] = bytes.readInt();
        }
    }
}
