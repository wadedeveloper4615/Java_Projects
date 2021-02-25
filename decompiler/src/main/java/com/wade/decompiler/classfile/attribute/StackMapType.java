package com.wade.decompiler.classfile.attribute;

import java.io.DataInputStream;
import java.io.IOException;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.ClassFileConstants;
import com.wade.decompiler.enums.ItemNamesEnum;

public class StackMapType {
    private byte type;
    private int index = -1;
    private ConstantPool constantPool;
    private String name;

    public StackMapType(byte type, int index, ConstantPool constant_pool) {
        if ((type < ItemNamesEnum.ITEM_Bogus.getTag()) || (type > ItemNamesEnum.ITEM_NewObject.getTag())) {
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

    public StackMapType(DataInputStream file, ConstantPool constant_pool) throws IOException {
        this(file.readByte(), -1, constant_pool);
        if (hasIndex()) {
            this.index = file.readShort();
        }
        this.constantPool = constant_pool;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        StackMapType other = (StackMapType) obj;
        if (constantPool == null) {
            if (other.constantPool != null)
                return false;
        } else if (!constantPool.equals(other.constantPool))
            return false;
        if (index != other.index)
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (type != other.type)
            return false;
        return true;
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((constantPool == null) ? 0 : constantPool.hashCode());
        result = prime * result + index;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + type;
        return result;
    }

    public boolean hasIndex() {
        return type == ItemNamesEnum.ITEM_Object.getTag() || type == ItemNamesEnum.ITEM_NewObject.getTag();
    }

    public void setConstantPool(ConstantPool constantPool) {
        this.constantPool = constantPool;
    }

    @Override
    public String toString() {
        return "StackMapType [type=" + type + ", index=" + index + ", constantPool=" + constantPool + ", name=" + name + "]";
    }
}
