package com.wade.decompiler.classfile.instructions;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.instructions.base.StackConsumer;
import com.wade.decompiler.classfile.instructions.base.StackInstruction;
import com.wade.decompiler.classfile.instructions.base.StackProducer;
import com.wade.decompiler.enums.InstructionOpCodes;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = true, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
public class SWAP extends StackInstruction implements StackConsumer, StackProducer {
    public SWAP(ConstantPool cp) {
        super(InstructionOpCodes.SWAP, cp);
    }

    @Override
    public int produceStack() {
        return opcode.getProduceStack();
    }
}
