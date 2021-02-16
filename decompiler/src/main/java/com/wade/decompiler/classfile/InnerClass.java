package com.wade.decompiler.classfile;

import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

import com.wade.decompiler.enums.ClassAccessFlagsList;
import com.wade.decompiler.enums.ClassFileConstants;

public final class InnerClass implements Cloneable, Node {
    private int innerClassIndex;
    private int outerClassIndex;
    private int innerNameIndex;
    private ClassAccessFlagsList innerAccessFlags;

    InnerClass(final DataInput file) throws IOException {
        this(file.readUnsignedShort(), file.readUnsignedShort(), file.readUnsignedShort(), file.readUnsignedShort());
    }

    public InnerClass(final InnerClass c) {
        this(c.getInnerClassIndex(), c.getOuterClassIndex(), c.getInnerNameIndex(), c.getInnerAccessFlags());
    }

    public InnerClass(final int innerClassIndex, final int outerClassIndex, final int innerNameIndex, ClassAccessFlagsList innerAccessFlags) {
        this.innerClassIndex = innerClassIndex;
        this.outerClassIndex = outerClassIndex;
        this.innerNameIndex = innerNameIndex;
        this.innerAccessFlags = innerAccessFlags;
    }

    public InnerClass(final int innerClassIndex, final int outerClassIndex, final int innerNameIndex, final int innerAccessFlags) {
        this.innerClassIndex = innerClassIndex;
        this.outerClassIndex = outerClassIndex;
        this.innerNameIndex = innerNameIndex;
        this.innerAccessFlags = new ClassAccessFlagsList(innerAccessFlags);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitInnerClass(this);
    }

    public InnerClass copy() {
        try {
            return (InnerClass) clone();
        } catch (final CloneNotSupportedException e) {
            // TODO should this throw?
        }
        return null;
    }

    public void dump(final DataOutputStream file) throws IOException {
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

    public void setInnerClassIndex(final int innerClassIndex) {
        this.innerClassIndex = innerClassIndex;
    }

    public void setInnerNameIndex(final int innerNameIndex) {
        this.innerNameIndex = innerNameIndex;
    }

    public void setOuterClassIndex(final int outerClassIndex) {
        this.outerClassIndex = outerClassIndex;
    }

    @Override
    public String toString() {
        return "InnerClass(" + innerClassIndex + ", " + outerClassIndex + ", " + innerNameIndex + ", " + innerAccessFlags + ")";
    }

    public String toString(final ConstantPool constantPool) {
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
