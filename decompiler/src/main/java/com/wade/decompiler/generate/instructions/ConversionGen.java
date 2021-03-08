package com.wade.decompiler.generate.instructions;

import com.wade.decompiler.classfile.instructions.I2B;
import com.wade.decompiler.classfile.instructions.I2C;
import com.wade.decompiler.classfile.instructions.I2S;
import com.wade.decompiler.classfile.instructions.type.Type;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = false, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
public class ConversionGen extends InstructionGen {
    private Type toType;
    private Type fromType;

    public ConversionGen(I2B instr) {
        fromType = Type.INT;
        toType = instr.getType();
    }

    public ConversionGen(I2C instr) {
        fromType = Type.INT;
        toType = instr.getType();
    }

    public ConversionGen(I2S instr) {
        fromType = Type.INT;
        toType = instr.getType();
    }
}
