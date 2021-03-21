package com.wade.decompiler.generate.instructions;

import com.wade.decompiler.classfile.instructions.DADD;
import com.wade.decompiler.classfile.instructions.DDIV;
import com.wade.decompiler.classfile.instructions.DMUL;
import com.wade.decompiler.classfile.instructions.DNEG;
import com.wade.decompiler.classfile.instructions.DREM;
import com.wade.decompiler.classfile.instructions.DSUB;
import com.wade.decompiler.classfile.instructions.FADD;
import com.wade.decompiler.classfile.instructions.FDIV;
import com.wade.decompiler.classfile.instructions.FMUL;
import com.wade.decompiler.classfile.instructions.FNEG;
import com.wade.decompiler.classfile.instructions.FREM;
import com.wade.decompiler.classfile.instructions.FSUB;
import com.wade.decompiler.classfile.instructions.IADD;
import com.wade.decompiler.classfile.instructions.IAND;
import com.wade.decompiler.classfile.instructions.IDIV;
import com.wade.decompiler.classfile.instructions.IMUL;
import com.wade.decompiler.classfile.instructions.INEG;
import com.wade.decompiler.classfile.instructions.IOR;
import com.wade.decompiler.classfile.instructions.IREM;
import com.wade.decompiler.classfile.instructions.ISHL;
import com.wade.decompiler.classfile.instructions.ISHR;
import com.wade.decompiler.classfile.instructions.ISUB;
import com.wade.decompiler.classfile.instructions.IUSHR;
import com.wade.decompiler.classfile.instructions.IXOR;
import com.wade.decompiler.classfile.instructions.LADD;
import com.wade.decompiler.classfile.instructions.LAND;
import com.wade.decompiler.classfile.instructions.LDIV;
import com.wade.decompiler.classfile.instructions.LMUL;
import com.wade.decompiler.classfile.instructions.LNEG;
import com.wade.decompiler.classfile.instructions.LOR;
import com.wade.decompiler.classfile.instructions.LREM;
import com.wade.decompiler.classfile.instructions.LSHL;
import com.wade.decompiler.classfile.instructions.LSHR;
import com.wade.decompiler.classfile.instructions.LSUB;
import com.wade.decompiler.classfile.instructions.LUSHR;
import com.wade.decompiler.classfile.instructions.LXOR;
import com.wade.decompiler.classfile.instructions.type.Type;
import com.wade.decompiler.decompiler.ExpressionStack;
import com.wade.decompiler.enums.InstructionOpCodes;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = true, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
public class ArithmeticGen extends InstructionGen {
    private InstructionOpCodes opcode;
    private Type type;

    public ArithmeticGen(int offset, DADD instr) {
        super(offset, instr.getLength());
        opcode = instr.getOpcode();
        type = Type.DOUBLE;
    }

    public ArithmeticGen(int offset, DDIV instr) {
        super(offset, instr.getLength());
        opcode = instr.getOpcode();
        type = Type.DOUBLE;
    }

    public ArithmeticGen(int offset, DMUL instr) {
        super(offset, instr.getLength());
        opcode = instr.getOpcode();
        type = Type.DOUBLE;
    }

    public ArithmeticGen(int offset, DNEG instr) {
        super(offset, instr.getLength());
        opcode = instr.getOpcode();
        type = Type.DOUBLE;
    }

    public ArithmeticGen(int offset, DREM instr) {
        super(offset, instr.getLength());
        opcode = instr.getOpcode();
        type = Type.DOUBLE;
    }

    public ArithmeticGen(int offset, DSUB instr) {
        super(offset, instr.getLength());
        opcode = instr.getOpcode();
        type = Type.DOUBLE;
    }

    public ArithmeticGen(int offset, FADD instr) {
        super(offset, instr.getLength());
        opcode = instr.getOpcode();
        type = Type.FLOAT;
    }

    public ArithmeticGen(int offset, FDIV instr) {
        super(offset, instr.getLength());
        opcode = instr.getOpcode();
        type = Type.FLOAT;
    }

    public ArithmeticGen(int offset, FMUL instr) {
        super(offset, instr.getLength());
        type = Type.FLOAT;
    }

    public ArithmeticGen(int offset, FNEG instr) {
        super(offset, instr.getLength());
        opcode = instr.getOpcode();
        type = Type.FLOAT;
    }

    public ArithmeticGen(int offset, FREM instr) {
        super(offset, instr.getLength());
        opcode = instr.getOpcode();
        type = Type.FLOAT;
    }

    public ArithmeticGen(int offset, FSUB instr) {
        super(offset, instr.getLength());
        opcode = instr.getOpcode();
        type = Type.FLOAT;
    }

    public ArithmeticGen(int offset, IADD instr) {
        super(offset, instr.getLength());
        opcode = instr.getOpcode();
        type = Type.INTEGER;
    }

    public ArithmeticGen(int offset, IAND instr) {
        super(offset, instr.getLength());
        opcode = instr.getOpcode();
        type = Type.INTEGER;
    }

    public ArithmeticGen(int offset, IDIV instr) {
        super(offset, instr.getLength());
        opcode = instr.getOpcode();
        type = Type.INTEGER;
    }

    public ArithmeticGen(int offset, IMUL instr) {
        super(offset, instr.getLength());
        opcode = instr.getOpcode();
        type = Type.INTEGER;
    }

    public ArithmeticGen(int offset, INEG instr) {
        super(offset, instr.getLength());
        opcode = instr.getOpcode();
        type = Type.INTEGER;
    }

    public ArithmeticGen(int offset, IOR instr) {
        super(offset, instr.getLength());
        opcode = instr.getOpcode();
        type = Type.INTEGER;
    }

    public ArithmeticGen(int offset, IREM instr) {
        super(offset, instr.getLength());
        opcode = instr.getOpcode();
        type = Type.INTEGER;
    }

    public ArithmeticGen(int offset, ISHL instr) {
        super(offset, instr.getLength());
        opcode = instr.getOpcode();
        type = Type.INTEGER;
    }

    public ArithmeticGen(int offset, ISHR instr) {
        super(offset, instr.getLength());
        opcode = instr.getOpcode();
        type = Type.INTEGER;
    }

    public ArithmeticGen(int offset, ISUB instr) {
        super(offset, instr.getLength());
        opcode = instr.getOpcode();
        type = Type.INTEGER;
    }

    public ArithmeticGen(int offset, IUSHR instr) {
        super(offset, instr.getLength());
        opcode = instr.getOpcode();
        type = Type.INTEGER;
    }

    public ArithmeticGen(int offset, IXOR instr) {
        super(offset, instr.getLength());
        opcode = instr.getOpcode();
        type = Type.INTEGER;
    }

    public ArithmeticGen(int offset, LADD instr) {
        super(offset, instr.getLength());
        opcode = instr.getOpcode();
        type = Type.LONG;
    }

    public ArithmeticGen(int offset, LAND instr) {
        super(offset, instr.getLength());
        opcode = instr.getOpcode();
        type = Type.LONG;
    }

    public ArithmeticGen(int offset, LDIV instr) {
        super(offset, instr.getLength());
        opcode = instr.getOpcode();
        type = Type.LONG;
    }

    public ArithmeticGen(int offset, LMUL instr) {
        super(offset, instr.getLength());
        opcode = instr.getOpcode();
        type = Type.LONG;
    }

    public ArithmeticGen(int offset, LNEG instr) {
        super(offset, instr.getLength());
        opcode = instr.getOpcode();
        type = Type.LONG;
    }

    public ArithmeticGen(int offset, LOR instr) {
        super(offset, instr.getLength());
        opcode = instr.getOpcode();
        type = Type.LONG;
    }

    public ArithmeticGen(int offset, LREM instr) {
        super(offset, instr.getLength());
        opcode = instr.getOpcode();
        type = Type.LONG;
    }

    public ArithmeticGen(int offset, LSHL instr) {
        super(offset, instr.getLength());
        opcode = instr.getOpcode();
        type = Type.LONG;
    }

    public ArithmeticGen(int offset, LSHR instr) {
        super(offset, instr.getLength());
        opcode = instr.getOpcode();
        type = Type.LONG;
    }

    public ArithmeticGen(int offset, LSUB instr) {
        super(offset, instr.getLength());
        opcode = instr.getOpcode();
        type = Type.LONG;
    }

    public ArithmeticGen(int offset, LUSHR instr) {
        super(offset, instr.getLength());
        opcode = instr.getOpcode();
        type = Type.LONG;
    }

    public ArithmeticGen(int offset, LXOR instr) {
        super(offset, instr.getLength());
        opcode = instr.getOpcode();
        type = Type.LONG;
    }

    @Override
    public String decompile(ExpressionStack stack) {
        return null;
    }
}
