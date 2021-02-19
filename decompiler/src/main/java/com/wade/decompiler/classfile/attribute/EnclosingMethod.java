package com.wade.decompiler.classfile.attribute;

import java.io.DataInput;
import java.io.IOException;

import com.wade.decompiler.classfile.constant.ConstantClass;
import com.wade.decompiler.classfile.constant.ConstantNameAndType;
import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.ClassFileAttributes;
import com.wade.decompiler.enums.ClassFileConstants;

public class EnclosingMethod extends Attribute {
    private int classIndex;
    private int methodIndex;
    private ConstantClass className;
    private ConstantNameAndType methodName;

    public EnclosingMethod(int nameIndex, int len, DataInput input, ConstantPool cpool) throws IOException {
        this(nameIndex, len, input.readUnsignedShort(), input.readUnsignedShort(), cpool);
    }

    private EnclosingMethod(int nameIndex, int len, int classIdx, int methodIdx, ConstantPool cpool) {
        super(ClassFileAttributes.ATTR_ENCLOSING_METHOD, nameIndex, len, cpool);
        this.classIndex = classIdx;
        this.methodIndex = methodIdx;
        this.className = (ConstantClass) super.getConstantPool().getConstant(classIndex, ClassFileConstants.CONSTANT_Class);
        this.methodName = (ConstantNameAndType) super.getConstantPool().getConstant(methodIndex, ClassFileConstants.CONSTANT_NameAndType);
    }

    public int getClassIndex() {
        return classIndex;
    }

    public ConstantClass getClassName() {
        return className;
    }

    public int getMethodIndex() {
        return methodIndex;
    }

    public ConstantNameAndType getMethodName() {
        return methodName;
    }
}
