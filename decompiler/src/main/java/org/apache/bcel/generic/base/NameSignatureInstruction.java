package org.apache.bcel.generic.base;

import org.apache.bcel.classfile.constant.ConstantCP;
import org.apache.bcel.classfile.constant.ConstantNameAndType;
import org.apache.bcel.classfile.constant.ConstantPool;
import org.apache.bcel.classfile.constant.ConstantUtf8;
import org.apache.bcel.enums.InstructionOpCodes;
import org.apache.bcel.generic.gen.ConstantPoolGen;

public abstract class NameSignatureInstruction extends CPInstruction {
    public NameSignatureInstruction() {
        super();
    }

    public NameSignatureInstruction(InstructionOpCodes opcode, final int index) {
        super(opcode, index);
    }

    public String getName(final ConstantPoolGen cpg) {
        final ConstantPool cp = cpg.getConstantPool();
        final ConstantNameAndType cnat = getNameAndType(cpg);
        return ((ConstantUtf8) cp.getConstant(cnat.getNameIndex())).getBytes();
    }

    public ConstantNameAndType getNameAndType(final ConstantPoolGen cpg) {
        final ConstantPool cp = cpg.getConstantPool();
        final ConstantCP cmr = (ConstantCP) cp.getConstant(super.getIndex());
        return (ConstantNameAndType) cp.getConstant(cmr.getNameAndTypeIndex());
    }

    public String getSignature(final ConstantPoolGen cpg) {
        final ConstantPool cp = cpg.getConstantPool();
        final ConstantNameAndType cnat = getNameAndType(cpg);
        return ((ConstantUtf8) cp.getConstant(cnat.getSignatureIndex())).getBytes();
    }
}
