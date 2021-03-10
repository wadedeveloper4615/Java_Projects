package com.wade.decompiler.generate.instructions;

import com.wade.decompiler.classfile.instructions.IOR;
import com.wade.decompiler.classfile.instructions.LOR;
import com.wade.decompiler.classfile.instructions.type.Type;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = false, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
public class OrGen extends InstructionGen {
    private Type type;

    public OrGen(int offset, IOR instr) {
        super(offset, instr.getLength());
        type = Type.INT;
    }

    public OrGen(int offset, LOR instr) {
        super(offset, instr.getLength());
        type = Type.LONG;
    }
}
