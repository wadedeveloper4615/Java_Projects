package com.wade.decompiler.classfile.attribute;

import java.io.DataInput;
import java.io.IOException;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.ClassFileConstants;
import com.wade.decompiler.enums.ItemNamesEnum;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
public class StackMapType {
    private byte type;
    private int index = -1;
    private ConstantPool constantPool;
    private String name;

    public StackMapType(byte type, int index, ConstantPool constantPool) {
        if ((type < ItemNamesEnum.ITEM_Bogus.getTag()) || (type > ItemNamesEnum.ITEM_NewObject.getTag())) {
            throw new IllegalArgumentException("Illegal type for StackMapType: " + type);
        }
        this.type = type;
        this.index = index;
        this.constantPool = constantPool;
        if (index >= 1) {
            this.name = constantPool.constantToString(index, ClassFileConstants.CONSTANT_Class);
        } else {
            this.name = "Unknown";
        }
    }

    public StackMapType(DataInput file, ConstantPool constantPool) throws IOException {
        this(file.readByte(), -1, constantPool);
        if (hasIndex()) {
            this.index = file.readShort();
        }
        this.constantPool = constantPool;
    }

    public boolean hasIndex() {
        return type == ItemNamesEnum.ITEM_Object.getTag() || type == ItemNamesEnum.ITEM_NewObject.getTag();
    }
}
