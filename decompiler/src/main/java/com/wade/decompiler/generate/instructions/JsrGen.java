package com.wade.decompiler.generate.instructions;

import com.wade.decompiler.classfile.instructions.JSR;
import com.wade.decompiler.classfile.instructions.JSR_W;
import com.wade.decompiler.classfile.instructions.type.Type;
import com.wade.decompiler.enums.InstructionOpCodes;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = true, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
public class JsrGen extends InstructionGen {
    private InstructionOpCodes opcode;
    private int index;
    private Type type;

    public JsrGen(JSR instr) {
        opcode = instr.getOpcode();
        index = -1;
        type = null;
    }

    public JsrGen(JSR_W instr) {
        opcode = instr.getOpcode();
        index = instr.getIndex();
        type = null;
    }
}
