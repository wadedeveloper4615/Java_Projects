package com.wade.app.classfile.attribute;

import java.io.DataInput;
import java.io.IOException;

import com.wade.app.Const;
import com.wade.app.constantpool.ConstantPool;

public class StackMapType {
    private byte type;
    private int index = -1;
    private ConstantPool constantPool;

    public StackMapType(byte type, int index, ConstantPool constant_pool) {
        if ((type < Const.ITEM_Bogus) || (type > Const.ITEM_NewObject)) {
            throw new IllegalArgumentException("Illegal type for StackMapType: " + type);
        }
        this.type = type;
        this.index = index;
        this.constantPool = constant_pool;
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

    public byte getType() {
        return type;
    }

    public boolean hasIndex() {
        return type == Const.ITEM_Object || type == Const.ITEM_NewObject;
    }
}
