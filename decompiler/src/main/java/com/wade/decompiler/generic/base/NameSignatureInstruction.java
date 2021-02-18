package com.wade.decompiler.generic.base;

import com.wade.decompiler.classfile.constant.ConstantCP;
import com.wade.decompiler.classfile.constant.ConstantNameAndType;
import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.constant.ConstantUtf8;
import com.wade.decompiler.enums.InstructionOpCodes;

public abstract class NameSignatureInstruction extends CPInstruction {
    public NameSignatureInstruction() {
        super();
    }

    public NameSignatureInstruction(InstructionOpCodes opcode, int index) {
        super(opcode, index);
    }

    public String getName(ConstantPool cp) {
        ConstantNameAndType cnat = getNameAndType(cp);
        return ((ConstantUtf8) cp.getConstant(cnat.getNameIndex())).getBytes();
    }

    public ConstantNameAndType getNameAndType(ConstantPool cp) {
        ConstantCP cmr = (ConstantCP) cp.getConstant(super.getIndex());
        return (ConstantNameAndType) cp.getConstant(cmr.getNameAndTypeIndex());
    }

    public String getSignature(ConstantPool cp) {
        ConstantNameAndType cnat = getNameAndType(cp);
        return ((ConstantUtf8) cp.getConstant(cnat.getSignatureIndex())).getBytes();
    }
}
