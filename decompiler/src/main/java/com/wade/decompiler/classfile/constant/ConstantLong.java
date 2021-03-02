package com.wade.decompiler.classfile.constant;

import java.io.DataInput;
import java.io.IOException;

import com.wade.decompiler.enums.ClassFileConstants;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = true, includeFieldNames = true)
@EqualsAndHashCode(callSuper = true)
public class ConstantLong extends Constant implements ConstantObject {
    private final long bytes;

    public ConstantLong(DataInput file) throws IOException {
        this(file.readLong());
    }

    public ConstantLong(long bytes) {
        super(ClassFileConstants.CONSTANT_Long);
        this.bytes = bytes;
    }

    @Override
    public Object getConstantValue(ConstantPool cp) {
        return Long.valueOf(bytes);
    }
}
