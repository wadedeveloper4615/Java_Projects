package com.wade.decompiler.classfile.instructions.base;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.exceptions.ClassGenException;
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
public abstract class ArrayInstruction extends Instruction {
    public ArrayInstruction(InstructionOpCodes opcode, ConstantPool constantPool) {
        super(opcode, 1, constantPool);
    }

    public Type getType() {
        InstructionOpCodes _opcode = super.getOpcode();
        switch (_opcode) {
            case IALOAD:
            case IASTORE:
                return Type.INTEGER;
            case CALOAD:
            case CASTORE:
                return Type.CHAR;
            case BALOAD:
            case BASTORE:
                return Type.BYTE;
            case SALOAD:
            case SASTORE:
                return Type.SHORT;
            case LALOAD:
            case LASTORE:
                return Type.LONG;
            case DALOAD:
            case DASTORE:
                return Type.DOUBLE;
            case FALOAD:
            case FASTORE:
                return Type.FLOAT;
            case AALOAD:
            case AASTORE:
                return Type.OBJECT;
            default:
                throw new ClassGenException("Unknown case in switch" + _opcode);
        }
    }
}
