package com.wade.decompiler.generate.instructions;

import com.wade.decompiler.classfile.constant.Constant;
import com.wade.decompiler.classfile.constant.ConstantCP;
import com.wade.decompiler.classfile.constant.ConstantClass;
import com.wade.decompiler.classfile.constant.ConstantFieldRef;
import com.wade.decompiler.classfile.constant.ConstantInvokeDynamic;
import com.wade.decompiler.classfile.constant.ConstantLong;
import com.wade.decompiler.classfile.constant.ConstantMethodref;
import com.wade.decompiler.classfile.constant.ConstantNameAndType;
import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.constant.ConstantUtf8;
import com.wade.decompiler.classfile.instructions.INVOKEDYNAMIC;
import com.wade.decompiler.classfile.instructions.INVOKEINTERFACE;
import com.wade.decompiler.classfile.instructions.INVOKESPECIAL;
import com.wade.decompiler.classfile.instructions.INVOKESTATIC;
import com.wade.decompiler.classfile.instructions.INVOKEVIRTUAL;
import com.wade.decompiler.classfile.instructions.type.Type;
import com.wade.decompiler.constants.ExceptionConst;
import com.wade.decompiler.decompiler.ExpressionStack;
import com.wade.decompiler.enums.ClassFileConstants;
import com.wade.decompiler.enums.InstructionOpCodes;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = true, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
public class InvokeGen extends InstructionGen {
    private InstructionOpCodes opcode;
    @ToString.Exclude
    private ConstantPool constantPool;
    private Integer index;
    private Integer nargs;
    private Type type;
    private String superName;
    private String methodName;
    private String signature;
    private Object constantValue;
    private String constantString;
    private Class<?>[] exceptions;

    public InvokeGen(int offset, INVOKEDYNAMIC instr) {
        super(offset, instr.getLength());
        opcode = instr.getOpcode();
        constantPool = instr.getConstantPool();
        ConstantCP c = (ConstantCP) constantPool.getConstant(instr.getIndex());
        extractConstantPoolInfo(c);
        exceptions = ExceptionConst.createExceptions(ExceptionConst.EXCS.EXCS_INTERFACE_METHOD_RESOLUTION, ExceptionConst.UNSATISFIED_LINK_ERROR, ExceptionConst.ABSTRACT_METHOD_ERROR, ExceptionConst.ILLEGAL_ACCESS_ERROR, ExceptionConst.INCOMPATIBLE_CLASS_CHANGE_ERROR);
        index = instr.getIndex();
        nargs = null;
        type = instr.getType();
    }

    public InvokeGen(int offset, INVOKEINTERFACE instr) {
        super(offset, instr.getLength());
        opcode = instr.getOpcode();
        constantPool = instr.getConstantPool();
        ConstantCP c = (ConstantCP) constantPool.getConstant(instr.getIndex());
        extractConstantPoolInfo(c);
        exceptions = null;
        index = instr.getIndex();
        nargs = instr.getNargs();
        type = Type.NULL;
    }

    public InvokeGen(int offset, INVOKESPECIAL instr) {
        super(offset, instr.getLength());
        opcode = instr.getOpcode();
        constantPool = instr.getConstantPool();
        ConstantCP c = (ConstantCP) constantPool.getConstant(instr.getIndex());
        extractConstantPoolInfo(c);
        exceptions = null;
        index = instr.getIndex();
        nargs = null;
        type = Type.NULL;
    }

    public InvokeGen(int offset, INVOKESTATIC instr) {
        super(offset, instr.getLength());
        opcode = instr.getOpcode();
        constantPool = instr.getConstantPool();
        ConstantCP c = (ConstantCP) constantPool.getConstant(instr.getIndex());
        extractConstantPoolInfo(c);
        exceptions = null;
        index = instr.getIndex();
        nargs = null;
        type = Type.NULL;
    }

    public InvokeGen(int offset, INVOKEVIRTUAL instr) {
        super(offset, instr.getLength());
        opcode = instr.getOpcode();
        constantPool = instr.getConstantPool();
        ConstantCP c = (ConstantCP) constantPool.getConstant(instr.getIndex());
        extractConstantPoolInfo(c);
        exceptions = null;
        index = instr.getIndex();
        nargs = null;
        type = Type.NULL;
    }

    @Override
    public String decompile(ExpressionStack stack) {
        return null;
    }

    private void extractConstantPoolInfo(Constant c) {
        if (c instanceof ConstantMethodref) {
            int classIndex = ((ConstantMethodref) c).getClassIndex();
            int nameAndTypeIndex = ((ConstantMethodref) c).getNameAndTypeIndex();

            ConstantClass cc = (ConstantClass) constantPool.getConstant(classIndex, ClassFileConstants.CONSTANT_Class);
            ConstantNameAndType cnt = (ConstantNameAndType) constantPool.getConstant(nameAndTypeIndex, ClassFileConstants.CONSTANT_NameAndType);

            superName = ((ConstantUtf8) constantPool.getConstant(cc.getNameIndex(), ClassFileConstants.CONSTANT_Utf8)).getBytes();
            methodName = ((ConstantUtf8) constantPool.getConstant(cnt.getNameIndex(), ClassFileConstants.CONSTANT_Utf8)).getBytes();
            signature = ((ConstantUtf8) constantPool.getConstant(cnt.getSignatureIndex(), ClassFileConstants.CONSTANT_Utf8)).getBytes();
        } else if (c instanceof ConstantFieldRef) {
            int classIndex = ((ConstantFieldRef) c).getClassIndex();
            int nameAndTypeIndex = ((ConstantFieldRef) c).getNameAndTypeIndex();

            ConstantClass cc = (ConstantClass) constantPool.getConstant(classIndex, ClassFileConstants.CONSTANT_Class);
            ConstantNameAndType cnt = (ConstantNameAndType) constantPool.getConstant(nameAndTypeIndex, ClassFileConstants.CONSTANT_NameAndType);

            superName = ((ConstantUtf8) constantPool.getConstant(cc.getNameIndex(), ClassFileConstants.CONSTANT_Utf8)).getBytes();
            methodName = ((ConstantUtf8) constantPool.getConstant(cnt.getNameIndex(), ClassFileConstants.CONSTANT_Utf8)).getBytes();
            signature = ((ConstantUtf8) constantPool.getConstant(cnt.getSignatureIndex(), ClassFileConstants.CONSTANT_Utf8)).getBytes();
        } else if (c instanceof ConstantInvokeDynamic) {
            // int classIndex = ((ConstantInvokeDynamic) c).getClassIndex();
            int nameAndTypeIndex = ((ConstantInvokeDynamic) c).getNameAndTypeIndex();

            // ConstantClass cc = (ConstantClass) constantPool.getConstant(classIndex,
            // ClassFileConstants.CONSTANT_Class);
            ConstantNameAndType cnt = (ConstantNameAndType) constantPool.getConstant(nameAndTypeIndex, ClassFileConstants.CONSTANT_NameAndType);

            // superName = ((ConstantUtf8) constantPool.getConstant(cc.getNameIndex(),
            // ClassFileConstants.CONSTANT_Utf8)).getBytes();
            methodName = ((ConstantUtf8) constantPool.getConstant(cnt.getNameIndex(), ClassFileConstants.CONSTANT_Utf8)).getBytes();
            signature = ((ConstantUtf8) constantPool.getConstant(cnt.getSignatureIndex(), ClassFileConstants.CONSTANT_Utf8)).getBytes();
        } else if (c instanceof ConstantClass) {
            ConstantClass constantClass = (ConstantClass) c;
            constantValue = ((ConstantUtf8) constantPool.getConstant(constantClass.getNameIndex(), ClassFileConstants.CONSTANT_Utf8)).getBytes();
        } else if (c instanceof ConstantUtf8) {
            constantString = ((ConstantUtf8) c).getBytes();
        } else if (c instanceof ConstantNameAndType) {
            methodName = constantPool.constantToString(((ConstantNameAndType) c).getNameIndex(), ClassFileConstants.CONSTANT_Utf8);
            signature = constantPool.constantToString(((ConstantNameAndType) c).getSignatureIndex(), ClassFileConstants.CONSTANT_Utf8);
        } else if (c instanceof ConstantLong) {
            constantValue = Long.valueOf(((ConstantLong) c).getBytes());
        } else {
            System.out.println(c.getClass().getName());
        }
    }
}
