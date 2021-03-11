package com.wade.decompiler.classfile.constant;

import java.io.DataInput;
import java.io.IOException;
import java.util.Objects;

import com.wade.decompiler.enums.ClassFileConstants;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = false, includeFieldNames = true)
public class ConstantMethodType extends Constant {
    private final int descriptorIndex;

    public ConstantMethodType(DataInput file) throws IOException {
        this(file.readUnsignedShort());
    }

    public ConstantMethodType(int descriptor_index) {
        super(ClassFileConstants.CONSTANT_MethodType);
        this.descriptorIndex = descriptor_index;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        ConstantMethodType other = (ConstantMethodType) obj;
        return descriptorIndex == other.descriptorIndex;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + Objects.hash(descriptorIndex);
        return result;
    }
}
