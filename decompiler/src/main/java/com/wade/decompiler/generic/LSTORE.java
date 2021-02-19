package com.wade.decompiler.generic;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.StoreInstruction;

public class LSTORE extends StoreInstruction {
    public LSTORE(ConstantPool cp) {
        super(InstructionOpCodes.LSTORE, InstructionOpCodes.LSTORE_0, cp);
    }

    public LSTORE(int n, ConstantPool cp) {
        super(InstructionOpCodes.LSTORE, InstructionOpCodes.LSTORE_0, n, cp);
    }
}
