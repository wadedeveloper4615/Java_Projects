package com.wade.app.attribute;

import java.io.DataInputStream;
import java.io.IOException;

import com.wade.app.Const;
import com.wade.app.constantpool.ConstantPool;

public final class StackMapType implements Cloneable {
    private byte type;
    private int index = -1;
    private ConstantPool constantPool;

    public StackMapType(final byte type, final int index, final ConstantPool constant_pool) {
        if ((type < Const.ITEM_Bogus) || (type > Const.ITEM_NewObject)) {
            throw new IllegalArgumentException("Illegal type for StackMapType: " + type);
        }
        this.type = type;
        this.index = index;
        this.constantPool = constant_pool;
    }

    public StackMapType(final DataInputStream file, final ConstantPool constant_pool) throws IOException {
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

    public void setConstantPool(final ConstantPool constantPool) {
        this.constantPool = constantPool;
    }

    public void setIndex(final int t) {
        index = t;
    }

    public void setType(final byte t) {
        if ((t < Const.ITEM_Bogus) || (t > Const.ITEM_NewObject)) {
            throw new IllegalArgumentException("Illegal type for StackMapType: " + t);
        }
        type = t;
    }
}
