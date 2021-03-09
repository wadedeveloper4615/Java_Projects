package com.wade.decompiler.generate.instructions;

import com.wade.decompiler.classfile.instructions.LOOKUPSWITCH;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = false, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
@SuppressWarnings("unused")
public class LookupSwitchGen extends InstructionGen {
    public LookupSwitchGen(LOOKUPSWITCH instr) {
    }
}
