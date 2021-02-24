package com.wade.decompiler.classfile.instructions;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.instructions.base.LoadInstruction;
import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generate.attribute.LocalVariableTableGen;

public class ALOAD extends LoadInstruction {
    public ALOAD(int n, LocalVariableTableGen localVariableTable, ConstantPool cp) {
        super(InstructionOpCodes.ALOAD, InstructionOpCodes.ALOAD_0, n, localVariableTable, cp);
    }

    public ALOAD(LocalVariableTableGen localVariableTable, ConstantPool cp) {
        super(InstructionOpCodes.ALOAD, InstructionOpCodes.ALOAD_0, cp);
    }
}
