package com.wade.decompiler.classfile.instructions.base;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
public class PUSH implements CompoundInstruction, VariableLengthInstruction, InstructionConstants {
    @Override
    public InstructionList getInstructionList() {
        return null;
    }
//    private Instruction instruction;
//
//    public PUSH(ConstantPool cp, ObjectType value) {
//        if (value == null) {
//            instruction = new ACONST_NULL();
//        } else {
//            instruction = new LDC(cp.addClass(value));
//        }
//    }
//
//    public PUSH(ConstantPool cp, String value) {
//        if (value == null) {
//            instruction = new ACONST_NULL();
//        } else {
//            instruction = new LDC(cp.addString(value));
//        }
//    }
//
//    public PUSH(ConstantPoolGen cp, boolean value) {
//        instruction = Instruction.getInstructions(InstructionOpCodes.read(InstructionConst.ICONST_0.getOpcode().getOpcode() + (value ? 1 : 0)));
//    }
//
//    public PUSH(ConstantPoolGen cp, Boolean value) {
//        this(cp, value.booleanValue());
//    }
//
//    public PUSH(ConstantPoolGen cp, Character value) {
//        this(cp, value.charValue());
//    }
//
//    public PUSH(ConstantPoolGen cp, double value) {
//        if (value == 0.0) {
//            instruction = InstructionConst.DCONST_0;
//        } else if (value == 1.0) {
//            instruction = InstructionConst.DCONST_1;
//        } else {
//            instruction = new LDC2_W(cp.addDouble(value));
//        }
//    }
//
//    public PUSH(ConstantPoolGen cp, float value) {
//        if (value == 0.0) {
//            instruction = InstructionConst.FCONST_0;
//        } else if (value == 1.0) {
//            instruction = InstructionConst.FCONST_1;
//        } else if (value == 2.0) {
//            instruction = InstructionConst.FCONST_2;
//        } else {
//            instruction = new LDC(cp.addFloat(value));
//        }
//    }
//
//    public PUSH(ConstantPoolGen cp, int value) {
//        if ((value >= -1) && (value <= 5)) {
//            instruction = Instruction.getInstructions(InstructionOpCodes.read(InstructionConst.ICONST_0.getOpcode().getOpcode() + value));
//            // instruction =
//            // InstructionConst.getInstruction(InstructionOpCodes.ICONST_0.getOpcode() +
//            // value);
//        } else if (Instruction.isValidByte(value)) {
//            instruction = new BIPUSH((byte) value);
//        } else if (Instruction.isValidShort(value)) {
//            instruction = new SIPUSH((short) value);
//        } else {
//            instruction = new LDC(cp.addInteger(value));
//        }
//    }
//
//    public PUSH(ConstantPoolGen cp, long value) {
//        if (value == 0) {
//            instruction = InstructionConst.LCONST_0;
//        } else if (value == 1) {
//            instruction = InstructionConst.LCONST_1;
//        } else {
//            instruction = new LDC2_W(cp.addLong(value));
//        }
//    }
//
//    public PUSH(ConstantPoolGen cp, Number value) {
//        if ((value instanceof Integer) || (value instanceof Short) || (value instanceof Byte)) {
//            instruction = new PUSH(cp, value.intValue()).instruction;
//        } else if (value instanceof Double) {
//            instruction = new PUSH(cp, value.doubleValue()).instruction;
//        } else if (value instanceof Float) {
//            instruction = new PUSH(cp, value.floatValue()).instruction;
//        } else if (value instanceof Long) {
//            instruction = new PUSH(cp, value.longValue()).instruction;
//        } else {
//            throw new ClassGenException("What's this: " + value);
//        }
//    }
//
//    public Instruction getInstruction() {
//        return instruction;
//    }
//
//    @Override
//    public InstructionList getInstructionList() {
//        return new InstructionList(instruction);
//    }
//
//    @Override
//    public String toString() {
//        return instruction + " (PUSH)";
//    }
}
