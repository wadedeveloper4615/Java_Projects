package com.wade.decompiler.generic;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.StoreInstruction;

public class ISTORE extends StoreInstruction {
    public ISTORE(ConstantPool cp) {
        super(InstructionOpCodes.ISTORE, InstructionOpCodes.ISTORE_0, cp);
    }

    public ISTORE(int n, ConstantPool cp) {
        super(InstructionOpCodes.ISTORE, InstructionOpCodes.ISTORE_0, n, cp);
    }
}
