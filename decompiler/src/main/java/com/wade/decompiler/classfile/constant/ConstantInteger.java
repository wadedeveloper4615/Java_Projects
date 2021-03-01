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
@ToString
@EqualsAndHashCode(callSuper = false)
public class ConstantInteger extends Constant implements ConstantObject {
    private final int bytes;

    public ConstantInteger(DataInput file) throws IOException {
        this(file.readInt());
    }

    public ConstantInteger(int bytes) {
        super(ClassFileConstants.CONSTANT_Integer);
        this.bytes = bytes;
    }

    @Override
    public Object getConstantValue(ConstantPool cp) {
        return Integer.valueOf(bytes);
    }
}
