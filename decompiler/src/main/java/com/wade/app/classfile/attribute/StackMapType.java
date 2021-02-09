package com.wade.app.classfile.attribute;

import java.io.DataInput;
import java.io.IOException;

import com.wade.app.constantpool.ConstantPool;
import com.wade.app.enums.ItemNamesEnum;

public class StackMapType {
    private ItemNamesEnum type;
    private int index = -1;
    private ConstantPool constantPool;

    public StackMapType(byte type, int index, ConstantPool constant_pool) {
        if ((type < ItemNamesEnum.ITEM_Bogus.getTag()) || (type > ItemNamesEnum.ITEM_NewObject.getTag())) {
            throw new IllegalArgumentException("Illegal type for StackMapType: " + type);
        }
        this.type = ItemNamesEnum.read(type);
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

    public ItemNamesEnum getType() {
        return type;
    }

    public boolean hasIndex() {
        return type == ItemNamesEnum.ITEM_Object || type == ItemNamesEnum.ITEM_NewObject;
    }

    @Override
    public String toString() {
        return "StackMapType [type=" + type + ", index=" + index + "]";
    }
}
