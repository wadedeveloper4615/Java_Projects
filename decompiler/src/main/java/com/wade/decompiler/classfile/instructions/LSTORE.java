package com.wade.decompiler.classfile.instructions;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.instructions.base.StoreInstruction;
import com.wade.decompiler.decompiler.ExpressionStack;
import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generate.attribute.LocalVariableTableGen;

public class LSTORE extends StoreInstruction {
    public LSTORE(ConstantPool cp) {
        super(InstructionOpCodes.LSTORE, InstructionOpCodes.LSTORE_0, cp);
    }

    public LSTORE(int n, LocalVariableTableGen localVariableTable, ConstantPool cp) {
        super(InstructionOpCodes.LSTORE, InstructionOpCodes.LSTORE_0, n, localVariableTable, cp);
    }

    @Override
    public String decompile(ExpressionStack stack) {
        // TODO Auto-generated method stub
        return null;
    }
}
