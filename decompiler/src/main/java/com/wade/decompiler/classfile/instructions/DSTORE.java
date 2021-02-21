package com.wade.decompiler.classfile.instructions;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.instructions.base.StoreInstruction;
import com.wade.decompiler.enums.InstructionOpCodes;

public class DSTORE extends StoreInstruction {
    public DSTORE(ConstantPool cp) {
        super(InstructionOpCodes.DSTORE, InstructionOpCodes.DSTORE_0, cp);
    }

    public DSTORE(int n, ConstantPool cp) {
        super(InstructionOpCodes.DSTORE, InstructionOpCodes.DSTORE_0, n, cp);
    }
}
