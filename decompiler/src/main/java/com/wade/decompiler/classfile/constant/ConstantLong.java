package com.wade.decompiler.classfile.constant;

import com.wade.decompiler.enums.ClassFileConstants;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.DataInput;
import java.io.IOException;
import java.util.Objects;

@Setter
@Getter
@ToString(callSuper = false, includeFieldNames = true)
public class ConstantLong extends Constant {
    private final long bytes;

    public ConstantLong(DataInput file) throws IOException {
        this(file.readLong());
    }

    public ConstantLong(long bytes) {
        super(ClassFileConstants.CONSTANT_Long);
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
        ConstantLong other = (ConstantLong) obj;
        return bytes == other.bytes;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + Objects.hash(bytes);
        return result;
    }
}
