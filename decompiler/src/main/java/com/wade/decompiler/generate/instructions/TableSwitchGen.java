package com.wade.decompiler.generate.instructions;

import com.wade.decompiler.classfile.instructions.TABLESWITCH;
import com.wade.decompiler.decompiler.ExpressionStack;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = true, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
public class TableSwitchGen extends InstructionGen {
    private int padding;
    private int defaultOffset;
    private int[] offsets;
    private int[] match;

    public TableSwitchGen(int offset, TABLESWITCH instr) {
        super(offset, instr.getLength());
        match = instr.getMatch();
        offsets = instr.getOffsets();
        padding = instr.getPadding();
        defaultOffset = instr.getDefaultOffset();
    }

    @Override
    public String decompile(ExpressionStack stack) {
        return null;
    }
}
