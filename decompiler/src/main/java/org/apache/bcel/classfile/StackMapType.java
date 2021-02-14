package org.apache.bcel.classfile;

import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

import org.apache.bcel.Const;
import org.apache.bcel.classfile.constant.ConstantPool;
import org.apache.bcel.enums.ClassFileConstants;
import org.apache.bcel.enums.ItemNamesEnum;

public final class StackMapType implements Cloneable {
    private ItemNamesEnum type;
    private int index = -1;
    private ConstantPool constantPool;

    StackMapType(final DataInput file, final ConstantPool constant_pool) throws IOException {
        this(ItemNamesEnum.read(file.readByte()), -1, constant_pool);
        if (hasIndex()) {
            this.index = file.readShort();
        }
        this.constantPool = constant_pool;
    }

    public StackMapType(ItemNamesEnum type, final int index, final ConstantPool constant_pool) {
        if ((type.getTag() < ItemNamesEnum.ITEM_Bogus.getTag()) || (type.getTag() > ItemNamesEnum.ITEM_NewObject.getTag())) {
            throw new IllegalArgumentException("Illegal type for StackMapType: " + type);
        }
        this.type = type;
        this.index = index;
        this.constantPool = constant_pool;
    }

    public StackMapType copy() {
        try {
            return (StackMapType) clone();
        } catch (final CloneNotSupportedException e) {
        }
        return null;
    }

    public void dump(final DataOutputStream file) throws IOException {
        file.writeByte(type.getTag());
        if (hasIndex()) {
            file.writeShort(getIndex());
        }
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

    private String printIndex() {
        if (type == ItemNamesEnum.ITEM_Object) {
            if (index < 0) {
                return ", class=<unknown>";
            }
            return ", class=" + constantPool.constantToString(index, ClassFileConstants.CONSTANT_Class);
        } else if (type == ItemNamesEnum.ITEM_NewObject) {
            return ", offset=" + index;
        } else {
            return "";
        }
    }

    public void setConstantPool(final ConstantPool constantPool) {
        this.constantPool = constantPool;
    }

    public void setIndex(final int t) {
        index = t;
    }

    public void setType(final byte t) {
        if ((t < ItemNamesEnum.ITEM_Bogus.getTag()) || (t > ItemNamesEnum.ITEM_NewObject.getTag())) {
            throw new IllegalArgumentException("Illegal type for StackMapType: " + t);
        }
        type = ItemNamesEnum.read(t);
    }

    @Override
    public String toString() {
        return "(type=" + Const.getItemName(type.getTag()) + printIndex() + ")";
    }
}
