package com.wade.decompiler.generate.instructions;

import com.wade.decompiler.classfile.instructions.LOOKUPSWITCH;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = true, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
public class LookupSwitchGen extends InstructionGen {
    private int padding;
    private int defaultOffset;
    private int[] offsets;
    private int[] match;

    public LookupSwitchGen(int offset, LOOKUPSWITCH instr) {
        super(offset, instr.getLength());
        match = instr.getMatch();
        offsets = instr.getOffset();
        padding = instr.getPadding();
        defaultOffset = instr.getDefaultOffset();
    }
}
