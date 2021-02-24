package com.wade.decompiler.classfile.instructions;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.instructions.base.LoadInstruction;
import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generate.attribute.LocalVariableTableGen;

public class LLOAD extends LoadInstruction {
    public LLOAD(ConstantPool cp) {
        super(InstructionOpCodes.LLOAD, InstructionOpCodes.LLOAD_0, cp);
    }

    public LLOAD(int n, LocalVariableTableGen localVariableTable, ConstantPool cp) {
        super(InstructionOpCodes.LLOAD, InstructionOpCodes.LLOAD_0, n, localVariableTable, cp);
    }
}
