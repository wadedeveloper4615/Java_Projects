package com.wade.decompiler.classfile.instructions;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.instructions.base.ArrayInstruction;
import com.wade.decompiler.classfile.instructions.base.StackConsumer;
import com.wade.decompiler.decompiler.ExpressionStack;
import com.wade.decompiler.enums.InstructionOpCodes;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
public class FASTORE extends ArrayInstruction implements StackConsumer {
    public FASTORE(ConstantPool cp) {
        super(InstructionOpCodes.FASTORE, cp);
    }

    @Override
    public String decompile(ExpressionStack stack) {
        // TODO Auto-generated method stub
        return null;
    }
}
