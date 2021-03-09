package com.wade.decompiler.generate.instructions;

import com.wade.decompiler.classfile.instructions.D2F;
import com.wade.decompiler.classfile.instructions.D2I;
import com.wade.decompiler.classfile.instructions.D2L;
import com.wade.decompiler.classfile.instructions.F2D;
import com.wade.decompiler.classfile.instructions.F2I;
import com.wade.decompiler.classfile.instructions.F2L;
import com.wade.decompiler.classfile.instructions.I2B;
import com.wade.decompiler.classfile.instructions.I2C;
import com.wade.decompiler.classfile.instructions.I2D;
import com.wade.decompiler.classfile.instructions.I2F;
import com.wade.decompiler.classfile.instructions.I2L;
import com.wade.decompiler.classfile.instructions.I2S;
import com.wade.decompiler.classfile.instructions.L2D;
import com.wade.decompiler.classfile.instructions.L2F;
import com.wade.decompiler.classfile.instructions.L2I;
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

    public ConversionGen(D2F instr) {
        fromType = Type.DOUBLE;
        toType = instr.getType();
    }

    public ConversionGen(D2I instr) {
        fromType = Type.DOUBLE;
        toType = instr.getType();
    }

    public ConversionGen(D2L instr) {
        fromType = Type.DOUBLE;
        toType = instr.getType();
    }

    public ConversionGen(F2D instr) {
        fromType = Type.INT;
        toType = instr.getType();
    }

    public ConversionGen(F2I instr) {
        fromType = Type.INT;
        toType = instr.getType();
    }

    public ConversionGen(F2L instr) {
        fromType = Type.INT;
        toType = instr.getType();
    }

    public ConversionGen(I2B instr) {
        fromType = Type.INT;
        toType = instr.getType();
    }

    public ConversionGen(I2C instr) {
        fromType = Type.INT;
        toType = instr.getType();
    }

    public ConversionGen(I2D instr) {
        fromType = Type.INT;
        toType = instr.getType();
    }

    public ConversionGen(I2F instr) {
        fromType = Type.INT;
        toType = instr.getType();
    }

    public ConversionGen(I2L instr) {
        fromType = Type.INT;
        toType = instr.getType();
    }

    public ConversionGen(I2S instr) {
        fromType = Type.INT;
        toType = instr.getType();
    }

    public ConversionGen(L2D instr) {
        fromType = Type.LONG;
        toType = instr.getType();
    }

    public ConversionGen(L2F instr) {
        fromType = Type.LONG;
        toType = instr.getType();
    }

    public ConversionGen(L2I instr) {
        fromType = Type.LONG;
        toType = instr.getType();
    }
}
