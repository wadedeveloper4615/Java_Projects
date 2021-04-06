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
public class ConstantFloat extends Constant {
    private final float bytes;

    public ConstantFloat(DataInput file) throws IOException {
        this(file.readFloat());
    }

    public ConstantFloat(float bytes) {
        super(ClassFileConstants.CONSTANT_Float);
        this.bytes = bytes;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        ConstantFloat other = (ConstantFloat) obj;
        return Float.floatToIntBits(bytes) == Float.floatToIntBits(other.bytes);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + Objects.hash(bytes);
        return result;
    }
}
