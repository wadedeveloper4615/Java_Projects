package com.wade.decompiler.generic;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.StoreInstruction;

public class ASTORE extends StoreInstruction {
    public ASTORE(ConstantPool cp) {
        super(InstructionOpCodes.ASTORE, InstructionOpCodes.ASTORE_0, cp);
    }

    public ASTORE(int n, ConstantPool cp) {
        super(InstructionOpCodes.ASTORE, InstructionOpCodes.ASTORE_0, n, cp);
    }
}
