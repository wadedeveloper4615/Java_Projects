package com.wade.decompiler.classfile.instructions;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.instructions.base.StackInstruction;
import com.wade.decompiler.classfile.instructions.base.inter.PushInstruction;
import com.wade.decompiler.enums.InstructionOpCodes;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = true, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
public class DUP2 extends StackInstruction implements PushInstruction {
    public DUP2(ConstantPool cp) {
        super(InstructionOpCodes.DUP2, cp);
    }

    @Override
    public int produceStack() {
        return opcode.getProduceStack();
    }
}
