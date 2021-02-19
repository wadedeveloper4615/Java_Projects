package com.wade.decompiler.generic;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.StoreInstruction;

public class FSTORE extends StoreInstruction {
    public FSTORE(ConstantPool cp) {
        super(InstructionOpCodes.FSTORE, InstructionOpCodes.FSTORE_0, cp);
    }

    public FSTORE(int n, ConstantPool cp) {
        super(InstructionOpCodes.FSTORE, InstructionOpCodes.FSTORE_0, n, cp);
    }
}
