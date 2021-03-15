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
