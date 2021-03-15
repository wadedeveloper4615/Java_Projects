package com.wade.decompiler.generate.instructions;

import com.wade.decompiler.classfile.constant.Constant;
import com.wade.decompiler.classfile.constant.ConstantCP;
import com.wade.decompiler.classfile.constant.ConstantClass;
import com.wade.decompiler.classfile.constant.ConstantFieldRef;
import com.wade.decompiler.classfile.constant.ConstantLong;
import com.wade.decompiler.classfile.constant.ConstantMethodref;
import com.wade.decompiler.classfile.constant.ConstantNameAndType;
import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.constant.ConstantUtf8;
import com.wade.decompiler.classfile.instructions.PUTFIELD;
import com.wade.decompiler.classfile.instructions.PUTSTATIC;
import com.wade.decompiler.constants.ExceptionConst;
import com.wade.decompiler.decompiler.Expression;
import com.wade.decompiler.decompiler.ExpressionStack;
import com.wade.decompiler.enums.ClassFileConstants;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = true, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
public class PutFieldGen extends InstructionGen {
    @ToString.Exclude
    private ConstantPool constantPool;
    private String superName;
    private String methodName;
    private String signature;
    private Object constantValue;
    private String constantString;

    public PutFieldGen(int offset, PUTFIELD instr) {
        super(offset, instr.getLength());
        constantPool = instr.getConstantPool();
        ConstantCP c = (ConstantCP) constantPool.getConstant(instr.getIndex());
        extractConstantPoolInfo(c);
    }

    public PutFieldGen(int offset, PUTSTATIC instr) {
        super(offset, instr.getLength());
        constantPool = instr.getConstantPool();
        ConstantCP c = (ConstantCP) constantPool.getConstant(instr.getIndex());
        extractConstantPoolInfo(c);
    }

    @Override
    public String decompile(ExpressionStack stack) {
        Expression item1 = stack.pop();
        Expression item2 = stack.pop();
        String name = this.methodName;
        String result = item2.getValue() + "." + item1.getValue() + " = " + name;
        return result;
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

    public Class<?>[] getExceptions() {
        return ExceptionConst.createExceptions(ExceptionConst.EXCS.EXCS_FIELD_AND_METHOD_RESOLUTION, ExceptionConst.NULL_POINTER_EXCEPTION, ExceptionConst.INCOMPATIBLE_CLASS_CHANGE_ERROR);
    }
}
