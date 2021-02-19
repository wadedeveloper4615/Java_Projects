package com.wade.decompiler.classfile.attribute;

import java.io.DataInput;
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
    private String innerName;
    private String outerClassName;
    private String innerClassName;

    public InnerClass(DataInput file, ConstantPool constantPool) throws IOException {
        this(file.readUnsignedShort(), file.readUnsignedShort(), file.readUnsignedShort(), file.readUnsignedShort(), constantPool);
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

    public InnerClass(int innerClassIndex, int outerClassIndex, int innerNameIndex, int innerAccessFlags, ConstantPool constantPool) {
        this.innerClassIndex = innerClassIndex;
        this.outerClassIndex = outerClassIndex;
        this.innerNameIndex = innerNameIndex;
        this.innerAccessFlags = new ClassAccessFlagsList(innerAccessFlags);
        this.innerClassName = constantPool.getConstantString(innerClassIndex, ClassFileConstants.CONSTANT_Class);
        this.outerClassName = constantPool.getConstantString(outerClassIndex, ClassFileConstants.CONSTANT_Class);
        this.innerName = ((ConstantUtf8) constantPool.getConstant(innerNameIndex, ClassFileConstants.CONSTANT_Utf8)).getBytes();
    }

    public ClassAccessFlagsList getInnerAccessFlags() {
        return innerAccessFlags;
    }

    public int getInnerClassIndex() {
        return innerClassIndex;
    }

    public String getInnerClassName() {
        return innerClassName;
    }

    public String getInnerName() {
        return innerName;
    }

    public int getInnerNameIndex() {
        return innerNameIndex;
    }

    public int getOuterClassIndex() {
        return outerClassIndex;
    }

    public String getOuterClassName() {
        return outerClassName;
    }

    @Override
    public String toString() {
        return "InnerClass(" + innerClassIndex + ", " + outerClassIndex + ", " + innerNameIndex + ", " + innerAccessFlags + ")";
    }

    public String toString(ConstantPool constantPool) {
        String outer_class_name;
        String inner_name;
        innerClassName = Utility.compactClassName(innerClassName, false);
        if (outerClassIndex != 0) {
            outer_class_name = " of class " + Utility.compactClassName(outerClassName, false);
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
        return "  " + access + inner_name + "=class " + innerClassName + outer_class_name;
    }
}
