package com.wade.decompiler.classfile.attribute;

import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.constant.ConstantUtf8;
import com.wade.decompiler.enums.ClassAccessFlagsList;
import com.wade.decompiler.enums.ClassFileConstants;
import com.wade.decompiler.util.Utility;

public class InnerClass {
    private int innerClassIndex;
    private int outerClassIndex;
    private int innerNameIndex;
    private ClassAccessFlagsList innerAccessFlags;

    InnerClass(DataInput file) throws IOException {
        this(file.readUnsignedShort(), file.readUnsignedShort(), file.readUnsignedShort(), file.readUnsignedShort());
    }

    public InnerClass(InnerClass c) {
        this(c.getInnerClassIndex(), c.getOuterClassIndex(), c.getInnerNameIndex(), c.getInnerAccessFlags());
    }

    public InnerClass(int innerClassIndex, int outerClassIndex, int innerNameIndex, ClassAccessFlagsList innerAccessFlags) {
        this.innerClassIndex = innerClassIndex;
        this.outerClassIndex = outerClassIndex;
        this.innerNameIndex = innerNameIndex;
        this.innerAccessFlags = innerAccessFlags;
    }

    public InnerClass(int innerClassIndex, int outerClassIndex, int innerNameIndex, int innerAccessFlags) {
        this.innerClassIndex = innerClassIndex;
        this.outerClassIndex = outerClassIndex;
        this.innerNameIndex = innerNameIndex;
        this.innerAccessFlags = new ClassAccessFlagsList(innerAccessFlags);
    }

    public void dump(DataOutputStream file) throws IOException {
        file.writeShort(innerClassIndex);
        file.writeShort(outerClassIndex);
        file.writeShort(innerNameIndex);
        file.writeShort(innerAccessFlags.getFlags());
    }

    public ClassAccessFlagsList getInnerAccessFlags() {
        return innerAccessFlags;
    }

    public int getInnerClassIndex() {
        return innerClassIndex;
    }

    public int getInnerNameIndex() {
        return innerNameIndex;
    }

    public int getOuterClassIndex() {
        return outerClassIndex;
    }

    public void setInnerAccessFlags(ClassAccessFlagsList innerAccessFlags) {
        this.innerAccessFlags = innerAccessFlags;
    }

    public void setInnerClassIndex(int innerClassIndex) {
        this.innerClassIndex = innerClassIndex;
    }

    public void setInnerNameIndex(int innerNameIndex) {
        this.innerNameIndex = innerNameIndex;
    }

    public void setOuterClassIndex(int outerClassIndex) {
        this.outerClassIndex = outerClassIndex;
    }

    @Override
    public String toString() {
        return "InnerClass(" + innerClassIndex + ", " + outerClassIndex + ", " + innerNameIndex + ", " + innerAccessFlags + ")";
    }

    public String toString(ConstantPool constantPool) {
        String outer_class_name;
        String inner_name;
        String inner_class_name = constantPool.getConstantString(innerClassIndex, ClassFileConstants.CONSTANT_Class);
        inner_class_name = Utility.compactClassName(inner_class_name, false);
        if (outerClassIndex != 0) {
            outer_class_name = constantPool.getConstantString(outerClassIndex, ClassFileConstants.CONSTANT_Class);
            outer_class_name = " of class " + Utility.compactClassName(outer_class_name, false);
        } else {
            outer_class_name = "";
        }
        if (innerNameIndex != 0) {
            inner_name = ((ConstantUtf8) constantPool.getConstant(innerNameIndex, ClassFileConstants.CONSTANT_Utf8)).getBytes();
        } else {
            inner_name = "(anonymous)";
        }
        String access = Utility.accessToString(innerAccessFlags, true);
        access = access.isEmpty() ? "" : (access + " ");
        return "  " + access + inner_name + "=class " + inner_class_name + outer_class_name;
    }
}
