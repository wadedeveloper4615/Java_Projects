package com.wade.decompiler.classfile.attribute;

import java.io.DataInput;
import java.io.IOException;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.constant.ConstantUtf8;
import com.wade.decompiler.enums.ClassFileAttributes;
import com.wade.decompiler.enums.ClassFileConstants;

public class PMGClass extends Attribute {
    private int pmgClassIndex;
    private int pmgIndex;
    private String pmgClass;
    private String pmg;

    public PMGClass(int name_index, int length, DataInput input, ConstantPool constant_pool) throws IOException {
        this(name_index, length, input.readUnsignedShort(), input.readUnsignedShort(), constant_pool);
    }

    public PMGClass(int name_index, int length, int pmgIndex, int pmgClassIndex, ConstantPool constantPool) {
        super(ClassFileAttributes.ATTR_PMG, name_index, length, constantPool);
        this.pmgIndex = pmgIndex;
        this.pmgClassIndex = pmgClassIndex;
        this.pmgClass = ((ConstantUtf8) super.getConstantPool().getConstant(pmgClassIndex, ClassFileConstants.CONSTANT_Utf8)).getBytes();
        this.pmg = ((ConstantUtf8) super.getConstantPool().getConstant(pmgIndex, ClassFileConstants.CONSTANT_Utf8)).getBytes();
    }

    public String getPmg() {
        return pmg;
    }

    public String getPmgClass() {
        return pmgClass;
    }

    public int getPmgClassIndex() {
        return pmgClassIndex;
    }

    public int getPmgIndex() {
        return pmgIndex;
    }

    @Override
    public String toString() {
        return "PMGClass [pmgClassIndex=" + pmgClassIndex + ", pmgIndex=" + pmgIndex + ", pmgClass=" + pmgClass + ", pmg=" + pmg + "]";
    }
}
