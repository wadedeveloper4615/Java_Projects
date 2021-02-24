package com.wade.decompiler.classfile.instructions;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.instructions.base.StoreInstruction;
import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generate.attribute.LocalVariableTableGen;

public class DSTORE extends StoreInstruction {
    public DSTORE(ConstantPool cp) {
        super(InstructionOpCodes.DSTORE, InstructionOpCodes.DSTORE_0, cp);
    }

    public DSTORE(int n, LocalVariableTableGen localVariableTable, ConstantPool cp) {
        super(InstructionOpCodes.DSTORE, InstructionOpCodes.DSTORE_0, n, localVariableTable, cp);
    }
}
