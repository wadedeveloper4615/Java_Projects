package org.apache.bcel.classfile.attribute;

import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

import org.apache.bcel.classfile.Visitor;
import org.apache.bcel.classfile.constant.ConstantClass;
import org.apache.bcel.classfile.constant.ConstantNameAndType;
import org.apache.bcel.classfile.constant.ConstantPool;
import org.apache.bcel.enums.ClassFileAttributes;
import org.apache.bcel.enums.ClassFileConstants;

public class EnclosingMethod extends Attribute {
    private int classIndex;
    private int methodIndex;

    public EnclosingMethod(final int nameIndex, final int len, final DataInput input, final ConstantPool cpool) throws IOException {
        this(nameIndex, len, input.readUnsignedShort(), input.readUnsignedShort(), cpool);
    }

    private EnclosingMethod(final int nameIndex, final int len, final int classIdx, final int methodIdx, final ConstantPool cpool) {
        super(ClassFileAttributes.ATTR_ENCLOSING_METHOD, nameIndex, len, cpool);
        classIndex = classIdx;
        methodIndex = methodIdx;
    }

    @Override
    public void accept(final Visitor v) {
        v.visitEnclosingMethod(this);
    }

    @Override
    public Attribute copy(final ConstantPool constant_pool) {
        return (Attribute) clone();
    }

    @Override
    public final void dump(final DataOutputStream file) throws IOException {
        super.dump(file);
        file.writeShort(classIndex);
        file.writeShort(methodIndex);
    }

    public final ConstantClass getEnclosingClass() {
        final ConstantClass c = (ConstantClass) super.getConstantPool().getConstant(classIndex, ClassFileConstants.CONSTANT_Class);
        return c;
    }

    public final int getEnclosingClassIndex() {
        return classIndex;
    }

    public final ConstantNameAndType getEnclosingMethod() {
        if (methodIndex == 0) {
            return null;
        }
        final ConstantNameAndType nat = (ConstantNameAndType) super.getConstantPool().getConstant(methodIndex, ClassFileConstants.CONSTANT_NameAndType);
        return nat;
    }

    public final int getEnclosingMethodIndex() {
        return methodIndex;
    }

    public final void setEnclosingClassIndex(final int idx) {
        classIndex = idx;
    }

    public final void setEnclosingMethodIndex(final int idx) {
        methodIndex = idx;
    }
}
