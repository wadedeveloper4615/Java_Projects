package com.wade.decompiler.generate.instructions;

import com.wade.decompiler.classfile.instructions.*;
import com.wade.decompiler.classfile.instructions.type.Type;
import com.wade.decompiler.decompiler.ExpressionStack;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = true, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
public class ConversionGen extends InstructionGen {
    private Type toType;
    private Type fromType;

    public ConversionGen(int offset, D2F instr) {
        super(offset, instr.getLength());
        fromType = Type.DOUBLE;
        toType = instr.getType();
    }

    public ConversionGen(int offset, D2I instr) {
        super(offset, instr.getLength());
        fromType = Type.DOUBLE;
        toType = instr.getType();
    }

    public ConversionGen(int offset, D2L instr) {
        super(offset, instr.getLength());
        fromType = Type.DOUBLE;
        toType = instr.getType();
    }

    public ConversionGen(int offset, F2D instr) {
        super(offset, instr.getLength());
        fromType = Type.INTEGER;
        toType = instr.getType();
    }

    public ConversionGen(int offset, F2I instr) {
        super(offset, instr.getLength());
        fromType = Type.INTEGER;
        toType = instr.getType();
    }

    public ConversionGen(int offset, F2L instr) {
        super(offset, instr.getLength());
        fromType = Type.INTEGER;
        toType = instr.getType();
    }

    public ConversionGen(int offset, I2B instr) {
        super(offset, instr.getLength());
        fromType = Type.INTEGER;
        toType = instr.getType();
    }

    public ConversionGen(int offset, I2C instr) {
        super(offset, instr.getLength());
        fromType = Type.INTEGER;
        toType = instr.getType();
    }

    public ConversionGen(int offset, I2D instr) {
        super(offset, instr.getLength());
        fromType = Type.INTEGER;
        toType = instr.getType();
    }

    public ConversionGen(int offset, I2F instr) {
        super(offset, instr.getLength());
        fromType = Type.INTEGER;
        toType = instr.getType();
    }

    public ConversionGen(int offset, I2L instr) {
        super(offset, instr.getLength());
        fromType = Type.INTEGER;
        toType = instr.getType();
    }

    public ConversionGen(int offset, I2S instr) {
        super(offset, instr.getLength());
        fromType = Type.INTEGER;
        toType = instr.getType();
    }

    public ConversionGen(int offset, L2D instr) {
        super(offset, instr.getLength());
        fromType = Type.LONG;
        toType = instr.getType();
    }

    public ConversionGen(int offset, L2F instr) {
        super(offset, instr.getLength());
        fromType = Type.LONG;
        toType = instr.getType();
    }

    public ConversionGen(int offset, L2I instr) {
        super(offset, instr.getLength());
        fromType = Type.LONG;
        toType = instr.getType();
    }

    @Override
    public String decompile(ExpressionStack stack) {
        return null;
    }
}
