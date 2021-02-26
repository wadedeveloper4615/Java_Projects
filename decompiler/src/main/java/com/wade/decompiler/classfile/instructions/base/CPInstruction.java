package com.wade.decompiler.classfile.instructions.base;

import java.io.IOException;

import com.wade.decompiler.classfile.constant.Constant;
import com.wade.decompiler.classfile.constant.ConstantClass;
import com.wade.decompiler.classfile.constant.ConstantFieldRef;
import com.wade.decompiler.classfile.constant.ConstantLong;
import com.wade.decompiler.classfile.constant.ConstantMethodref;
import com.wade.decompiler.classfile.constant.ConstantNameAndType;
import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.constant.ConstantUtf8;
import com.wade.decompiler.classfile.instructions.type.Type;
import com.wade.decompiler.enums.ClassFileConstants;
import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.util.ByteSequence;

public abstract class CPInstruction extends Instruction implements TypedInstruction, IndexedInstruction {
    private int index;
    private String superName;
    protected String methodName;
    protected String signature;
    private Object constantValue;
    private String constantString;

    public CPInstruction() {
    }

    public CPInstruction(InstructionOpCodes opcode, ConstantPool constantPool, int index) {
        super(opcode, 3, constantPool);
        setIndex(index);
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

    public Object getConstantValue() {
        return constantValue;
    }

    @Override
    public int getIndex() {
        return index;
    }

    public String getMethodName() {
        return methodName;
    }

    public String getSignature() {
        return signature;
    }

    public String getSuperName() {
        return superName;
    }

    @Override
    public Type getType() {
        String name = this.constantPool.getConstantString(index, ClassFileConstants.CONSTANT_Class);
        if (!name.startsWith("[")) {
            name = "L" + name + ";";
        }
        return Type.getType(name);
    }

    @Override
    public void initFromFile(ByteSequence bytes, boolean wide) throws IOException {
        setIndex(bytes.readUnsignedShort());
        if (index > 0) {
            Constant c = constantPool.getConstant(index);
            extractConstantPoolInfo(c);
        }
        super.setLength(3);
    }

    @Override
    public void setIndex(int index) {
//        if (index < 0) {
//            throw new ClassGenException("Negative index value: " + index);
//        }
        this.index = index;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public void setSuperName(String superName) {
        this.superName = superName;
    }

    @Override
    public String toString() {
        return super.toString() + "[index=" + index + ", superName=" + superName + ", methodName=" + methodName + ", signature=" + signature + ", constantValue=" + constantValue + ", constantString=" + constantString + "]";
    }
}
