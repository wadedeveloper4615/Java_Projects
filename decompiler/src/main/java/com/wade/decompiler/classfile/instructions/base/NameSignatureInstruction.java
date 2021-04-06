package com.wade.decompiler.classfile.instructions.base;

import com.wade.decompiler.classfile.constant.ConstantCP;
import com.wade.decompiler.classfile.constant.ConstantNameAndType;
import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.constant.ConstantUtf8;
import com.wade.decompiler.enums.InstructionOpCodes;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = true, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
public abstract class NameSignatureInstruction extends CPInstruction {
    public NameSignatureInstruction(InstructionOpCodes opcode, ConstantPool constantPool, int index) {
        super(opcode, constantPool, index);
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
