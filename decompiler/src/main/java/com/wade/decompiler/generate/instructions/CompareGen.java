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
@ToString(callSuper = true, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
public class CompareGen extends InstructionGen {
    private InstructionOpCodes opcode;
    private Type type;
    private Instruction negate;

    public CompareGen(int offset, DCMPG instr) {
        super(offset, instr.getLength());
        opcode = instr.getOpcode();
        type = Type.DOUBLE;
        negate = instr.getNegate();
    }

    public CompareGen(int offset, DCMPL instr) {
        super(offset, instr.getLength());
        opcode = instr.getOpcode();
        type = Type.DOUBLE;
        negate = instr.getNegate();
    }

    public CompareGen(int offset, FCMPG instr) {
        super(offset, instr.getLength());
        opcode = instr.getOpcode();
        type = Type.DOUBLE;
        negate = instr.getNegate();
    }

    public CompareGen(int offset, FCMPL instr) {
        super(offset, instr.getLength());
        opcode = instr.getOpcode();
        type = Type.DOUBLE;
        negate = instr.getNegate();
    }

    public CompareGen(int offset, IF_ACMPEQ instr) {
        super(offset, instr.getLength());
        opcode = instr.getOpcode();
        type = Type.DOUBLE;
        negate = instr.getNegate();
    }

    public CompareGen(int offset, IF_ACMPNE instr) {
        super(offset, instr.getLength());
        opcode = instr.getOpcode();
        type = Type.DOUBLE;
        negate = instr.negate();
    }

    public CompareGen(int offset, IF_ICMPEQ instr) {
        super(offset, instr.getLength());
        opcode = instr.getOpcode();
        type = Type.DOUBLE;
        negate = instr.negate();
    }

    public CompareGen(int offset, IF_ICMPGE instr) {
        super(offset, instr.getLength());
        opcode = instr.getOpcode();
        type = Type.DOUBLE;
        negate = instr.negate();
    }

    public CompareGen(int offset, IF_ICMPGT instr) {
        super(offset, instr.getLength());
        opcode = instr.getOpcode();
        type = Type.DOUBLE;
        negate = instr.negate();
    }

    public CompareGen(int offset, IF_ICMPLE instr) {
        super(offset, instr.getLength());
        opcode = instr.getOpcode();
        type = Type.DOUBLE;
        negate = instr.negate();
    }

    public CompareGen(int offset, IF_ICMPLT instr) {
        super(offset, instr.getLength());
        opcode = instr.getOpcode();
        type = Type.DOUBLE;
        negate = instr.negate();
    }

    public CompareGen(int offset, IF_ICMPNE instr) {
        super(offset, instr.getLength());
        opcode = instr.getOpcode();
        type = Type.DOUBLE;
        negate = instr.negate();
    }

    public CompareGen(int offset, IFEQ instr) {
        super(offset, instr.getLength());
        opcode = instr.getOpcode();
        type = Type.DOUBLE;
        negate = instr.negate();
    }

    public CompareGen(int offset, IFGE instr) {
        super(offset, instr.getLength());
        opcode = instr.getOpcode();
        type = Type.DOUBLE;
        negate = instr.negate();
    }

    public CompareGen(int offset, IFGT instr) {
        super(offset, instr.getLength());
        opcode = instr.getOpcode();
        type = Type.DOUBLE;
        negate = instr.negate();
    }

    public CompareGen(int offset, IFLE instr) {
        super(offset, instr.getLength());
        opcode = instr.getOpcode();
        type = Type.DOUBLE;
        negate = instr.negate();
    }

    public CompareGen(int offset, IFLT instr) {
        super(offset, instr.getLength());
        opcode = instr.getOpcode();
        type = Type.DOUBLE;
        negate = instr.negate();
    }

    public CompareGen(int offset, IFNE instr) {
        super(offset, instr.getLength());
        opcode = instr.getOpcode();
        type = Type.DOUBLE;
        negate = instr.negate();
    }

    public CompareGen(int offset, IFNONNULL instr) {
        super(offset, instr.getLength());
        opcode = instr.getOpcode();
        type = Type.DOUBLE;
        negate = instr.negate();
    }

    public CompareGen(int offset, IFNULL instr) {
        super(offset, instr.getLength());
        opcode = instr.getOpcode();
        type = Type.DOUBLE;
        negate = instr.negate();
    }

    public CompareGen(int offset, LCMP instr) {
        super(offset, instr.getLength());
        opcode = instr.getOpcode();
        type = Type.LONG;
        negate = instr;
    }
}
