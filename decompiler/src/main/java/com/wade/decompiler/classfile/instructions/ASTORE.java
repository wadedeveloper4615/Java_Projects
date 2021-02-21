package com.wade.decompiler.classfile.instructions;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.instructions.base.StoreInstruction;
import com.wade.decompiler.enums.InstructionOpCodes;

public class ASTORE extends StoreInstruction {
    public ASTORE(ConstantPool cp) {
        super(InstructionOpCodes.ASTORE, InstructionOpCodes.ASTORE_0, cp);
    }

    public ASTORE(int n, ConstantPool cp) {
        super(InstructionOpCodes.ASTORE, InstructionOpCodes.ASTORE_0, n, cp);
    }
}
