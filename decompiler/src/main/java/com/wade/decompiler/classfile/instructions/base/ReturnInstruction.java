package com.wade.decompiler.classfile.instructions.base;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.InstructionOpCodes;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = true, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
public abstract class ReturnInstruction extends Instruction {
    public ReturnInstruction(InstructionOpCodes opcode, ConstantPool cp) {
        super(opcode, 1, cp);
    }
//
//    public Class<?>[] getExceptions() {
//        return new Class[] { ExceptionConst.ILLEGAL_MONITOR_STATE };
//    }
//
//    public Type getType() {
//        InstructionOpCodes _opcode = super.getOpcode();
//        switch (_opcode) {
//            case IRETURN:
//                return Type.INT;
//            case LRETURN:
//                return Type.LONG;
//            case FRETURN:
//                return Type.FLOAT;
//            case DRETURN:
//                return Type.DOUBLE;
//            case ARETURN:
//                return Type.OBJECT;
//            case RETURN:
//                return Type.VOID;
//            default:
//                throw new ClassGenException("Unknown type " + _opcode);
//        }
//    }
}
