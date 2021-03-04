package com.wade.decompiler.classfile.instructions;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.instructions.base.Instruction;
import com.wade.decompiler.classfile.instructions.base.inter.StackConsumer;
import com.wade.decompiler.classfile.instructions.base.inter.StackProducer;
import com.wade.decompiler.classfile.instructions.base.inter.TypedInstruction;
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
public class LCMP extends Instruction implements TypedInstruction, StackProducer, StackConsumer {
    public LCMP(ConstantPool cp) {
        super(InstructionOpCodes.LCMP, 1, cp);
    }

    @Override
    public Type getType() {
        return Type.LONG;
    }

    @Override
    public int produceStack() {
        return opcode.getProduceStack();
    }
}
