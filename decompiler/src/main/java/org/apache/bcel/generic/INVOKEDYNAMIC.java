package org.apache.bcel.generic;

import java.io.DataOutputStream;
import java.io.IOException;

import org.apache.bcel.ExceptionConst;
import org.apache.bcel.classfile.constant.ConstantInvokeDynamic;
import org.apache.bcel.classfile.constant.ConstantNameAndType;
import org.apache.bcel.classfile.constant.ConstantPool;
import org.apache.bcel.enums.ClassFileConstants;
import org.apache.bcel.enums.InstructionOpCodes;
import org.apache.bcel.generic.base.InvokeInstruction;
import org.apache.bcel.generic.base.ReferenceType;
import org.apache.bcel.generic.base.Visitor;
import org.apache.bcel.generic.gen.ConstantPoolGen;
import org.apache.bcel.util.ByteSequence;

public class INVOKEDYNAMIC extends InvokeInstruction {
    public INVOKEDYNAMIC() {
    }

    public INVOKEDYNAMIC(final int index) {
        super(InstructionOpCodes.INVOKEDYNAMIC, index);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitExceptionThrower(this);
        v.visitTypedInstruction(this);
        v.visitStackConsumer(this);
        v.visitStackProducer(this);
        v.visitLoadClass(this);
        v.visitCPInstruction(this);
        v.visitFieldOrMethod(this);
        v.visitInvokeInstruction(this);
        v.visitINVOKEDYNAMIC(this);
    }

    @Override
    public void dump(final DataOutputStream out) throws IOException {
        out.writeByte(super.getOpcode().getOpcode());
        out.writeShort(super.getIndex());
        out.writeByte(0);
        out.writeByte(0);
    }

    @Override
    public String getClassName(final ConstantPoolGen cpg) {
        final ConstantPool cp = cpg.getConstantPool();
        final ConstantInvokeDynamic cid = (ConstantInvokeDynamic) cp.getConstant(super.getIndex(), ClassFileConstants.CONSTANT_InvokeDynamic);
        return ((ConstantNameAndType) cp.getConstant(cid.getNameAndTypeIndex())).getName(cp);
    }

    @Override
    public Class<?>[] getExceptions() {
        return ExceptionConst.createExceptions(ExceptionConst.EXCS.EXCS_INTERFACE_METHOD_RESOLUTION, ExceptionConst.UNSATISFIED_LINK_ERROR, ExceptionConst.ABSTRACT_METHOD_ERROR, ExceptionConst.ILLEGAL_ACCESS_ERROR, ExceptionConst.INCOMPATIBLE_CLASS_CHANGE_ERROR);
    }

    @Override
    public ReferenceType getReferenceType(final ConstantPoolGen cpg) {
        return new ObjectType(Object.class.getName());
    }

    @Override
    protected void initFromFile(final ByteSequence bytes, final boolean wide) throws IOException {
        super.initFromFile(bytes, wide);
        super.setLength(5);
        bytes.readByte();
        bytes.readByte();
    }

    @Override
    public String toString(final ConstantPool cp) {
        return super.toString(cp);
    }
}
