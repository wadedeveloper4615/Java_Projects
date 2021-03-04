package com.wade.decompiler.classfile.instructions;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.instructions.base.Instruction;
import com.wade.decompiler.classfile.instructions.base.StackConsumer;
import com.wade.decompiler.classfile.instructions.base.StackProducer;
import com.wade.decompiler.classfile.instructions.base.TypedInstruction;
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
public class FCMPG extends Instruction implements TypedInstruction, StackProducer, StackConsumer {
    public FCMPG(ConstantPool cp) {
        super(InstructionOpCodes.FCMPG, 1, cp);
    }

    @Override
    public Type getType() {
        return Type.FLOAT;
    }

    @Override
    public int produceStack() {
        return opcode.getProduceStack();
    }
}
