package com.wade.app.classfile.attribute;

import java.io.DataInputStream;
import java.io.IOException;

import com.wade.app.classfile.Attribute;
import com.wade.app.constantpool.ConstantPool;
import com.wade.app.enums.ClassFileAttributes;

public class PMGClass extends Attribute {
    private int pmgClassIndex;
    private int pmgIndex;

    public PMGClass(int name_index, int length, DataInputStream input, ConstantPool constant_pool) throws IOException {
        this(name_index, length, input.readUnsignedShort(), input.readUnsignedShort(), constant_pool);
    }

    public PMGClass(int name_index, int length, int pmgIndex, int pmgClassIndex, ConstantPool constantPool) {
        super(ClassFileAttributes.ATTR_PMG, name_index, length, constantPool);
        this.pmgIndex = pmgIndex;
        this.pmgClassIndex = pmgClassIndex;
    }

    public int getPmgClassIndex() {
        return pmgClassIndex;
    }

    public int getPmgIndex() {
        return pmgIndex;
    }

}
