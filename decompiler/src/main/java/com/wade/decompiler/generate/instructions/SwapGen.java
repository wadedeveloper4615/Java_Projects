package com.wade.decompiler.generate.instructions;

import com.wade.decompiler.classfile.instructions.SWAP;
import com.wade.decompiler.classfile.instructions.type.Type;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = true, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
@SuppressWarnings("unused")
public class SwapGen extends InstructionGen {
    private Type type;

    public SwapGen(SWAP instr) {
        type = Type.UNKNOWN;
    }
}
