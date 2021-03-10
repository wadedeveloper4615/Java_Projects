package com.wade.decompiler.generate.instructions;

import com.wade.decompiler.classfile.instructions.DSUB;
import com.wade.decompiler.classfile.instructions.FSUB;
import com.wade.decompiler.classfile.instructions.ISUB;
import com.wade.decompiler.classfile.instructions.LSUB;
import com.wade.decompiler.classfile.instructions.type.Type;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = true, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
public class SubGen extends InstructionGen {
    private Type type;

    public SubGen(int offset, DSUB instr) {
        super(offset, instr.getLength());
        type = Type.DOUBLE;
    }

    public SubGen(int offset, FSUB instr) {
        super(offset, instr.getLength());
        type = Type.FLOAT;
    }

    public SubGen(int offset, ISUB instr) {
        super(offset, instr.getLength());
        type = Type.INTEGER;
    }

    public SubGen(int offset, LSUB instr) {
        super(offset, instr.getLength());
        type = Type.LONG;
    }
}
