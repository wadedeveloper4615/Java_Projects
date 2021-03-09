package com.wade.decompiler.generate.instructions;

import com.wade.decompiler.classfile.instructions.DCMPG;
import com.wade.decompiler.classfile.instructions.DCMPL;
import com.wade.decompiler.classfile.instructions.FCMPG;
import com.wade.decompiler.classfile.instructions.FCMPL;
import com.wade.decompiler.classfile.instructions.IFEQ;
import com.wade.decompiler.classfile.instructions.IFGE;
import com.wade.decompiler.classfile.instructions.IFGT;
import com.wade.decompiler.classfile.instructions.IFLE;
import com.wade.decompiler.classfile.instructions.IFLT;
import com.wade.decompiler.classfile.instructions.IFNE;
import com.wade.decompiler.classfile.instructions.IFNONNULL;
import com.wade.decompiler.classfile.instructions.IFNULL;
import com.wade.decompiler.classfile.instructions.IF_ACMPEQ;
import com.wade.decompiler.classfile.instructions.IF_ACMPNE;
import com.wade.decompiler.classfile.instructions.IF_ICMPEQ;
import com.wade.decompiler.classfile.instructions.IF_ICMPGE;
import com.wade.decompiler.classfile.instructions.IF_ICMPGT;
import com.wade.decompiler.classfile.instructions.IF_ICMPLE;
import com.wade.decompiler.classfile.instructions.IF_ICMPLT;
import com.wade.decompiler.classfile.instructions.IF_ICMPNE;
import com.wade.decompiler.classfile.instructions.LCMP;
import com.wade.decompiler.classfile.instructions.base.Instruction;
import com.wade.decompiler.classfile.instructions.type.Type;
import com.wade.decompiler.enums.InstructionOpCodes;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = false, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
public class CompareGen extends InstructionGen {
    private InstructionOpCodes opcode;
    private Type type;
    private Instruction negate;

    public CompareGen(DCMPG instr) {
        opcode = instr.getOpcode();
        type = Type.DOUBLE;
        negate = instr.getNegate();
    }

    public CompareGen(DCMPL instr) {
        opcode = instr.getOpcode();
        type = Type.DOUBLE;
        negate = instr.getNegate();
    }

    public CompareGen(FCMPG instr) {
        opcode = instr.getOpcode();
        type = Type.DOUBLE;
        negate = instr.getNegate();
    }

    public CompareGen(FCMPL instr) {
        opcode = instr.getOpcode();
        type = Type.DOUBLE;
        negate = instr.getNegate();
    }

    public CompareGen(IF_ACMPEQ instr) {
        opcode = instr.getOpcode();
        type = Type.DOUBLE;
        negate = instr.getNegate();
    }

    public CompareGen(IF_ACMPNE instr) {
        opcode = instr.getOpcode();
        type = Type.DOUBLE;
        negate = instr.negate();
    }

    public CompareGen(IF_ICMPEQ instr) {
        opcode = instr.getOpcode();
        type = Type.DOUBLE;
        negate = instr.negate();
    }

    public CompareGen(IF_ICMPGE instr) {
        opcode = instr.getOpcode();
        type = Type.DOUBLE;
        negate = instr.negate();
    }

    public CompareGen(IF_ICMPGT instr) {
        opcode = instr.getOpcode();
        type = Type.DOUBLE;
        negate = instr.negate();
    }

    public CompareGen(IF_ICMPLE instr) {
        opcode = instr.getOpcode();
        type = Type.DOUBLE;
        negate = instr.negate();
    }

    public CompareGen(IF_ICMPLT instr) {
        opcode = instr.getOpcode();
        type = Type.DOUBLE;
        negate = instr.negate();
    }

    public CompareGen(IF_ICMPNE instr) {
        opcode = instr.getOpcode();
        type = Type.DOUBLE;
        negate = instr.negate();
    }

    public CompareGen(IFEQ instr) {
        opcode = instr.getOpcode();
        type = Type.DOUBLE;
        negate = instr.negate();
    }

    public CompareGen(IFGE instr) {
        opcode = instr.getOpcode();
        type = Type.DOUBLE;
        negate = instr.negate();
    }

    public CompareGen(IFGT instr) {
        opcode = instr.getOpcode();
        type = Type.DOUBLE;
        negate = instr.negate();
    }

    public CompareGen(IFLE instr) {
        opcode = instr.getOpcode();
        type = Type.DOUBLE;
        negate = instr.negate();
    }

    public CompareGen(IFLT instr) {
        opcode = instr.getOpcode();
        type = Type.DOUBLE;
        negate = instr.negate();
    }

    public CompareGen(IFNE instr) {
        opcode = instr.getOpcode();
        type = Type.DOUBLE;
        negate = instr.negate();
    }

    public CompareGen(IFNONNULL instr) {
        opcode = instr.getOpcode();
        type = Type.DOUBLE;
        negate = instr.negate();
    }

    public CompareGen(IFNULL instr) {
        opcode = instr.getOpcode();
        type = Type.DOUBLE;
        negate = instr.negate();
    }

    public CompareGen(LCMP instr) {
        opcode = instr.getOpcode();
        type = Type.LONG;
        negate = instr;
    }
}
