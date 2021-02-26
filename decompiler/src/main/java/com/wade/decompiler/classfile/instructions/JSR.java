package com.wade.decompiler.classfile.instructions;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.instructions.base.JsrInstruction;
import com.wade.decompiler.classfile.instructions.base.VariableLengthInstruction;
import com.wade.decompiler.decompiler.ExpressionStack;
import com.wade.decompiler.enums.InstructionOpCodes;

public class JSR extends JsrInstruction implements VariableLengthInstruction {
    public JSR(ConstantPool cp) {
        super(InstructionOpCodes.JSR, cp);
    }

    @Override
    public String decompile(ExpressionStack stack) {
        // TODO Auto-generated method stub
        return null;
    }
}
