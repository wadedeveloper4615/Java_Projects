package com.wade.decompiler.generic;

import com.wade.decompiler.Const;
import com.wade.decompiler.generic.base.CompoundInstruction;
import com.wade.decompiler.generic.base.Instruction;
import com.wade.decompiler.generic.base.InstructionConst;
import com.wade.decompiler.generic.base.InstructionConstants;
import com.wade.decompiler.generic.base.InstructionList;
import com.wade.decompiler.generic.base.ObjectType;
import com.wade.decompiler.generic.base.VariableLengthInstruction;
import com.wade.decompiler.generic.gen.ClassGenException;
import com.wade.decompiler.generic.gen.ConstantPoolGen;

public final class PUSH implements CompoundInstruction, VariableLengthInstruction, InstructionConstants {
    private Instruction instruction;

    public PUSH(final ConstantPoolGen cp, final int value) {
        if ((value >= -1) && (value <= 5)) {
            instruction = InstructionConst.getInstruction(Const.ICONST_0 + value);
        } else if (Instruction.isValidByte(value)) {
            instruction = new BIPUSH((byte) value);
        } else if (Instruction.isValidShort(value)) {
            instruction = new SIPUSH((short) value);
        } else {
            instruction = new LDC(cp.addInteger(value));
        }
    }

    public PUSH(final ConstantPoolGen cp, final boolean value) {
        instruction = InstructionConst.getInstruction(Const.ICONST_0 + (value ? 1 : 0));
    }

    public PUSH(final ConstantPoolGen cp, final float value) {
        if (value == 0.0) {
            instruction = InstructionConst.FCONST_0;
        } else if (value == 1.0) {
            instruction = InstructionConst.FCONST_1;
        } else if (value == 2.0) {
            instruction = InstructionConst.FCONST_2;
        } else {
            instruction = new LDC(cp.addFloat(value));
        }
    }

    public PUSH(final ConstantPoolGen cp, final long value) {
        if (value == 0) {
            instruction = InstructionConst.LCONST_0;
        } else if (value == 1) {
            instruction = InstructionConst.LCONST_1;
        } else {
            instruction = new LDC2_W(cp.addLong(value));
        }
    }

    public PUSH(final ConstantPoolGen cp, final double value) {
        if (value == 0.0) {
            instruction = InstructionConst.DCONST_0;
        } else if (value == 1.0) {
            instruction = InstructionConst.DCONST_1;
        } else {
            instruction = new LDC2_W(cp.addDouble(value));
        }
    }

    public PUSH(final ConstantPoolGen cp, final String value) {
        if (value == null) {
            instruction = InstructionConst.ACONST_NULL;
        } else {
            instruction = new LDC(cp.addString(value));
        }
    }

    public PUSH(final ConstantPoolGen cp, final ObjectType value) {
        if (value == null) {
            instruction = InstructionConst.ACONST_NULL;
        } else {
            instruction = new LDC(cp.addClass(value));
        }
    }

    public PUSH(final ConstantPoolGen cp, final Number value) {
        if ((value instanceof Integer) || (value instanceof Short) || (value instanceof Byte)) {
            instruction = new PUSH(cp, value.intValue()).instruction;
        } else if (value instanceof Double) {
            instruction = new PUSH(cp, value.doubleValue()).instruction;
        } else if (value instanceof Float) {
            instruction = new PUSH(cp, value.floatValue()).instruction;
        } else if (value instanceof Long) {
            instruction = new PUSH(cp, value.longValue()).instruction;
        } else {
            throw new ClassGenException("What's this: " + value);
        }
    }

    public PUSH(final ConstantPoolGen cp, final Character value) {
        this(cp, value.charValue());
    }

    public PUSH(final ConstantPoolGen cp, final Boolean value) {
        this(cp, value.booleanValue());
    }

    @Override
    public InstructionList getInstructionList() {
        return new InstructionList(instruction);
    }

    public Instruction getInstruction() {
        return instruction;
    }

    @Override
    public String toString() {
        return instruction + " (PUSH)";
    }
}
