package com.wade.decompiler.generic.base;

import com.wade.decompiler.classfile.ConstantCP;
import com.wade.decompiler.classfile.ConstantNameAndType;
import com.wade.decompiler.classfile.ConstantPool;
import com.wade.decompiler.classfile.ConstantUtf8;
import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.gen.ConstantPoolGen;

public abstract class NameSignatureInstruction extends CPInstruction {
    public NameSignatureInstruction() {
        super();
    }

    public NameSignatureInstruction(InstructionOpCodes opcode, int index) {
        super(opcode, index);
    }

    public String getName(ConstantPoolGen cpg) {
        ConstantPool cp = cpg.getConstantPool();
        ConstantNameAndType cnat = getNameAndType(cpg);
        return ((ConstantUtf8) cp.getConstant(cnat.getNameIndex())).getBytes();
    }

    public ConstantNameAndType getNameAndType(ConstantPoolGen cpg) {
        ConstantPool cp = cpg.getConstantPool();
        ConstantCP cmr = (ConstantCP) cp.getConstant(super.getIndex());
        return (ConstantNameAndType) cp.getConstant(cmr.getNameAndTypeIndex());
    }

    public String getSignature(ConstantPoolGen cpg) {
        ConstantPool cp = cpg.getConstantPool();
        ConstantNameAndType cnat = getNameAndType(cpg);
        return ((ConstantUtf8) cp.getConstant(cnat.getSignatureIndex())).getBytes();
    }
}
