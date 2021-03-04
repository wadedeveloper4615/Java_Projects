package com.wade.decompiler.classfile.instructions.base;

import com.wade.decompiler.classfile.constant.Constant;
import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.instructions.type.Type;
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
public abstract class CPInstruction extends Instruction {
    private int index;
    @ToString.Exclude
    private String superName;
    @ToString.Exclude
    protected String methodName;
    @ToString.Exclude
    protected String signature;
    @ToString.Exclude
    private Object constantValue;
    @ToString.Exclude
    private String constantString;

    public CPInstruction(InstructionOpCodes opcode, ConstantPool constantPool, int index) {
        super(opcode, 3, constantPool);
        setIndex(index);
    }

    @SuppressWarnings("unused")
    public void extractConstantPoolInfo(Constant c) {
//        if (c instanceof ConstantMethodref) {
//            int classIndex = ((ConstantMethodref) c).getClassIndex();
//            int nameAndTypeIndex = ((ConstantMethodref) c).getNameAndTypeIndex();
//
//            ConstantClass cc = (ConstantClass) constantPool.getConstant(classIndex, ClassFileConstants.CONSTANT_Class);
//            ConstantNameAndType cnt = (ConstantNameAndType) constantPool.getConstant(nameAndTypeIndex, ClassFileConstants.CONSTANT_NameAndType);
//
//            superName = ((ConstantUtf8) constantPool.getConstant(cc.getNameIndex(), ClassFileConstants.CONSTANT_Utf8)).getBytes();
//            methodName = ((ConstantUtf8) constantPool.getConstant(cnt.getNameIndex(), ClassFileConstants.CONSTANT_Utf8)).getBytes();
//            signature = ((ConstantUtf8) constantPool.getConstant(cnt.getSignatureIndex(), ClassFileConstants.CONSTANT_Utf8)).getBytes();
//        } else if (c instanceof ConstantFieldRef) {
//            int classIndex = ((ConstantFieldRef) c).getClassIndex();
//            int nameAndTypeIndex = ((ConstantFieldRef) c).getNameAndTypeIndex();
//
//            ConstantClass cc = (ConstantClass) constantPool.getConstant(classIndex, ClassFileConstants.CONSTANT_Class);
//            ConstantNameAndType cnt = (ConstantNameAndType) constantPool.getConstant(nameAndTypeIndex, ClassFileConstants.CONSTANT_NameAndType);
//
//            superName = ((ConstantUtf8) constantPool.getConstant(cc.getNameIndex(), ClassFileConstants.CONSTANT_Utf8)).getBytes();
//            methodName = ((ConstantUtf8) constantPool.getConstant(cnt.getNameIndex(), ClassFileConstants.CONSTANT_Utf8)).getBytes();
//            signature = ((ConstantUtf8) constantPool.getConstant(cnt.getSignatureIndex(), ClassFileConstants.CONSTANT_Utf8)).getBytes();
//        } else if (c instanceof ConstantClass) {
//            constantValue = ((ConstantClass) c).getConstantValue(constantPool);
//        } else if (c instanceof ConstantUtf8) {
//            constantString = ((ConstantUtf8) c).getBytes();
//        } else if (c instanceof ConstantNameAndType) {
//            methodName = ((ConstantNameAndType) c).getName(constantPool);
//            signature = ((ConstantNameAndType) c).getSignature(constantPool);
//        } else if (c instanceof ConstantLong) {
//            constantValue = ((ConstantLong) c).getConstantValue(constantPool);
//        } else {
//            System.out.println(c.getClass().getName());
//        }
    }

    public int getIndex() {
        return index;
    }

    public Type getType() {
        String name = constantPool.getConstantString(index, ClassFileConstants.CONSTANT_Class);
        if (!name.startsWith("[")) {
            name = "L" + name + ";";
        }
        return Type.getType(name);
    }
}
