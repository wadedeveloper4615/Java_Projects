package com.wade.decompiler.generic;

import java.io.DataOutputStream;
import java.io.IOException;

import com.wade.decompiler.ExceptionConst;
import com.wade.decompiler.classfile.constant.ConstantInvokeDynamic;
import com.wade.decompiler.classfile.constant.ConstantNameAndType;
import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.ClassFileConstants;
import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.type.ObjectType;
import com.wade.decompiler.generic.type.ReferenceType;
import com.wade.decompiler.util.ByteSequence;

public class INVOKEDYNAMIC extends InvokeInstruction {
    public INVOKEDYNAMIC() {
    }

    public INVOKEDYNAMIC(int index) {
        super(InstructionOpCodes.INVOKEDYNAMIC, index);
    }

    @Override
    public void dump(DataOutputStream out) throws IOException {
        out.writeByte(super.getOpcode().getOpcode());
        out.writeShort(super.getIndex());
        out.writeByte(0);
        out.writeByte(0);
    }

    @Override
    public String getClassName(ConstantPool cp) {
        ConstantInvokeDynamic cid = (ConstantInvokeDynamic) cp.getConstant(super.getIndex(), ClassFileConstants.CONSTANT_InvokeDynamic);
        return ((ConstantNameAndType) cp.getConstant(cid.getNameAndTypeIndex())).getName(cp);
    }

    @Override
    public Class<?>[] getExceptions() {
        return ExceptionConst.createExceptions(ExceptionConst.EXCS.EXCS_INTERFACE_METHOD_RESOLUTION, ExceptionConst.UNSATISFIED_LINK_ERROR, ExceptionConst.ABSTRACT_METHOD_ERROR, ExceptionConst.ILLEGAL_ACCESS_ERROR, ExceptionConst.INCOMPATIBLE_CLASS_CHANGE_ERROR);
    }

    @Override
    public ReferenceType getReferenceType(ConstantPool cpg) {
        return new ObjectType(Object.class.getName());
    }

    @Override
    public void initFromFile(ByteSequence bytes, boolean wide) throws IOException {
        super.initFromFile(bytes, wide);
        super.setLength(5);
        bytes.readByte(); // Skip 0 byte
        bytes.readByte(); // Skip 0 byte
    }

    @Override
    public String toString(ConstantPool cp) {
        return super.toString(cp);
    }
}
