package com.wade.decompiler.generic;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.StoreInstruction;

public class DSTORE extends StoreInstruction {
    public DSTORE(ConstantPool cp) {
        super(InstructionOpCodes.DSTORE, InstructionOpCodes.DSTORE_0, cp);
    }

    public DSTORE(int n, ConstantPool cp) {
        super(InstructionOpCodes.DSTORE, InstructionOpCodes.DSTORE_0, n, cp);
    }
}
