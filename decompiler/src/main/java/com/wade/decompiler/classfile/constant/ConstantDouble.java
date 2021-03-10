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
@ToString(callSuper = false, includeFieldNames = true)
@EqualsAndHashCode(callSuper = true)
public class ConstantDouble extends Constant implements ConstantObject {
    private final double bytes;

    public ConstantDouble(DataInput file) throws IOException {
        this(file.readDouble());
    }

    public ConstantDouble(double bytes) {
        super(ClassFileConstants.CONSTANT_Double);
        this.bytes = bytes;
    }

    @Override
    public Object getConstantValue(ConstantPool cp) {
        return Double.valueOf(bytes);
    }
}
