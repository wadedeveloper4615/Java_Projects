package com.wade.decompiler.generate.instructions;

import com.wade.decompiler.classfile.constant.Constant;
import com.wade.decompiler.classfile.constant.ConstantClass;
import com.wade.decompiler.classfile.constant.ConstantConstantPool;
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
import com.wade.decompiler.enums.ClassFileConstants;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = false, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
public class InvokeGen extends InstructionGen {
    @ToString.Exclude
    private ConstantPool constantPool;
    private String superName;
    private String methodName;
    private String signature;
    private Object constantValue;
    private String constantString;
    private Class<?>[] exceptions;
    private Integer index;
    private Integer nargs;
    private Type type;

    public InvokeGen(INVOKEDYNAMIC instr) {
        constantPool = instr.getConstantPool();
        ConstantConstantPool c = (ConstantConstantPool) constantPool.getConstant(instr.getIndex());
        extractConstantPoolInfo(c);
        exceptions = ExceptionConst.createExceptions(ExceptionConst.EXCS.EXCS_INTERFACE_METHOD_RESOLUTION, ExceptionConst.UNSATISFIED_LINK_ERROR, ExceptionConst.ABSTRACT_METHOD_ERROR, ExceptionConst.ILLEGAL_ACCESS_ERROR, ExceptionConst.INCOMPATIBLE_CLASS_CHANGE_ERROR);
        index = instr.getIndex();
        nargs = null;
        type = instr.getType();
    }

    public InvokeGen(INVOKEINTERFACE instr) {
        constantPool = instr.getConstantPool();
        ConstantConstantPool c = (ConstantConstantPool) constantPool.getConstant(instr.getIndex());
        extractConstantPoolInfo(c);
        index = instr.getIndex();
        nargs = instr.getNargs();
        type = Type.NULL;
    }

    public InvokeGen(INVOKESPECIAL instr) {
        constantPool = instr.getConstantPool();
        ConstantConstantPool c = (ConstantConstantPool) constantPool.getConstant(instr.getIndex());
        extractConstantPoolInfo(c);
        index = instr.getIndex();
        nargs = null;
        type = Type.NULL;
    }

    public InvokeGen(INVOKESTATIC instr) {
        constantPool = instr.getConstantPool();
        ConstantConstantPool c = (ConstantConstantPool) constantPool.getConstant(instr.getIndex());
        extractConstantPoolInfo(c);
        index = instr.getIndex();
        nargs = null;
        type = Type.NULL;
    }

    public InvokeGen(INVOKEVIRTUAL instr) {
        constantPool = instr.getConstantPool();
        ConstantConstantPool c = (ConstantConstantPool) constantPool.getConstant(instr.getIndex());
        extractConstantPoolInfo(c);
        index = instr.getIndex();
        nargs = null;
        type = Type.NULL;
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
            int classIndex = ((ConstantInvokeDynamic) c).getClassIndex();
            int nameAndTypeIndex = ((ConstantInvokeDynamic) c).getNameAndTypeIndex();

            ConstantClass cc = (ConstantClass) constantPool.getConstant(classIndex, ClassFileConstants.CONSTANT_Class);
            ConstantNameAndType cnt = (ConstantNameAndType) constantPool.getConstant(nameAndTypeIndex, ClassFileConstants.CONSTANT_NameAndType);

            superName = ((ConstantUtf8) constantPool.getConstant(cc.getNameIndex(), ClassFileConstants.CONSTANT_Utf8)).getBytes();
            methodName = ((ConstantUtf8) constantPool.getConstant(cnt.getNameIndex(), ClassFileConstants.CONSTANT_Utf8)).getBytes();
            signature = ((ConstantUtf8) constantPool.getConstant(cnt.getSignatureIndex(), ClassFileConstants.CONSTANT_Utf8)).getBytes();
        } else if (c instanceof ConstantClass) {
            constantValue = ((ConstantClass) c).getConstantValue(constantPool);
        } else if (c instanceof ConstantUtf8) {
            constantString = ((ConstantUtf8) c).getBytes();
        } else if (c instanceof ConstantNameAndType) {
            methodName = ((ConstantNameAndType) c).getName(constantPool);
            signature = ((ConstantNameAndType) c).getSignature(constantPool);
        } else if (c instanceof ConstantLong) {
            constantValue = ((ConstantLong) c).getConstantValue(constantPool);
        } else {
            System.out.println(c.getClass().getName());
        }
    }

    public Class<?>[] getExceptions() {
        return ExceptionConst.createExceptions(ExceptionConst.EXCS.EXCS_FIELD_AND_METHOD_RESOLUTION, ExceptionConst.NULL_POINTER_EXCEPTION, ExceptionConst.INCOMPATIBLE_CLASS_CHANGE_ERROR, ExceptionConst.ABSTRACT_METHOD_ERROR, ExceptionConst.UNSATISFIED_LINK_ERROR);
    }
}
