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
public class ConstantDouble extends Constant {
    private final double bytes;

    public ConstantDouble(DataInput file) throws IOException {
        this(file.readDouble());
    }

    public ConstantDouble(double bytes) {
        super(ClassFileConstants.CONSTANT_Double);
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
        ConstantDouble other = (ConstantDouble) obj;
        return Double.doubleToLongBits(bytes) == Double.doubleToLongBits(other.bytes);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + Objects.hash(bytes);
        return result;
    }
}
