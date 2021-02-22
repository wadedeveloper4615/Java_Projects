package com.wade.decompiler.classfile.attribute;

import java.io.DataInput;
import java.io.IOException;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.constants.Const;
import com.wade.decompiler.enums.ClassFileConstants;

public class StackMapType {
    private byte type;
    private int index = -1;
    private ConstantPool constantPool;
    private String name;

    public StackMapType(byte type, int index, ConstantPool constant_pool) {
        if ((type < Const.ITEM_Bogus) || (type > Const.ITEM_NewObject)) {
            throw new IllegalArgumentException("Illegal type for StackMapType: " + type);
        }
        this.type = type;
        this.index = index;
        this.constantPool = constant_pool;
        if (index >= 1) {
            this.name = constantPool.constantToString(index, ClassFileConstants.CONSTANT_Class);
        } else {
            this.name = "Unknown";
        }
    }

    public StackMapType(DataInput file, ConstantPool constant_pool) throws IOException {
        this(file.readByte(), -1, constant_pool);
        if (hasIndex()) {
            this.index = file.readShort();
        }
        this.constantPool = constant_pool;
    }

    public ConstantPool getConstantPool() {
        return constantPool;
    }

    public int getIndex() {
        return index;
    }

    public String getName() {
        return name;
    }

    public byte getType() {
        return type;
    }

    public boolean hasIndex() {
        return type == Const.ITEM_Object || type == Const.ITEM_NewObject;
    }

    public void setConstantPool(ConstantPool constantPool) {
        this.constantPool = constantPool;
    }

    @Override
    public String toString() {
        return "StackMapType [type=" + type + ", index=" + index + ", constantPool=" + constantPool + ", name=" + name + "]";
    }
}
