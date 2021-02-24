package com.wade.decompiler.classfile.instructions;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.instructions.base.LoadInstruction;
import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generate.attribute.LocalVariableTableGen;

public class DLOAD extends LoadInstruction {
    public DLOAD(ConstantPool cp) {
        super(InstructionOpCodes.DLOAD, InstructionOpCodes.DLOAD_0, cp);
    }

    public DLOAD(int n, LocalVariableTableGen localVariableTable, ConstantPool cp) {
        super(InstructionOpCodes.DLOAD, InstructionOpCodes.DLOAD_0, n, localVariableTable, cp);
    }
}
