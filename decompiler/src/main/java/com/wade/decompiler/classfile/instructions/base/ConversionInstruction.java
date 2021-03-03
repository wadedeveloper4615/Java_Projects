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
public abstract class ConversionInstruction extends Instruction implements TypedInstruction, StackProducer, StackConsumer {
    public ConversionInstruction(InstructionOpCodes opcode, ConstantPool constantPool) {
        super(opcode, 1, constantPool);
    }

    @Override
    public Type getType() {
        InstructionOpCodes _opcode = super.getOpcode();
        switch (_opcode) {
            case D2I:
            case F2I:
            case L2I:
                return Type.INT;
            case D2F:
            case I2F:
            case L2F:
                return Type.FLOAT;
            case D2L:
            case F2L:
            case I2L:
                return Type.LONG;
            case F2D:
            case I2D:
            case L2D:
                return Type.DOUBLE;
            case I2B:
                return Type.BYTE;
            case I2C:
                return Type.CHAR;
            case I2S:
                return Type.SHORT;
            default: // Never reached
                throw new ClassGenException("Unknown type " + _opcode);
        }
    }
}
