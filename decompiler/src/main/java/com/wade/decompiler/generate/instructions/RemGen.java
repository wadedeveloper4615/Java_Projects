package com.wade.decompiler.generate.instructions;

import com.wade.decompiler.classfile.instructions.DREM;
import com.wade.decompiler.classfile.instructions.FREM;
import com.wade.decompiler.classfile.instructions.IREM;
import com.wade.decompiler.classfile.instructions.LREM;
import com.wade.decompiler.classfile.instructions.type.Type;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = true, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
public class RemGen extends InstructionGen {
    private Type type;

    public RemGen(int offset, DREM instr) {
        super(offset, instr.getLength());
        type = Type.DOUBLE;
    }

    public RemGen(int offset, FREM instr) {
        super(offset, instr.getLength());
        type = Type.FLOAT;
    }

    public RemGen(int offset, IREM instr) {
        super(offset, instr.getLength());
        type = Type.INTEGER;
    }

    public RemGen(int offset, LREM instr) {
        super(offset, instr.getLength());
        type = Type.LONG;
    }
}
