package com.wade.decompiler.classfile.instructions.base;

import com.wade.decompiler.classfile.instructions.base.inter.CompoundInstruction;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = true, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
public class SWITCH implements CompoundInstruction {
    @Override
    public InstructionList getInstructionList() {
        return null;
    }
}
