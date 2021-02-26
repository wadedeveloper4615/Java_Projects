package com.wade.decompiler.classfile.instructions;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.instructions.base.StoreInstruction;
import com.wade.decompiler.decompiler.ExpressionStack;
import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generate.attribute.LocalVariableTableGen;

public class ISTORE extends StoreInstruction {
    public ISTORE(ConstantPool cp) {
        super(InstructionOpCodes.ISTORE, InstructionOpCodes.ISTORE_0, cp);
    }

    public ISTORE(int n, LocalVariableTableGen localVariableTable, ConstantPool cp) {
        super(InstructionOpCodes.ISTORE, InstructionOpCodes.ISTORE_0, n, localVariableTable, cp);
    }

    @Override
    public String decompile(ExpressionStack stack) {
        // TODO Auto-generated method stub
        return null;
    }
}
