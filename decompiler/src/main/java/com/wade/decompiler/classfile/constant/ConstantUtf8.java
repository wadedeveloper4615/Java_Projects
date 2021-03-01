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
public class ConstantUtf8 extends Constant {
    private final String value;

    public ConstantUtf8(DataInput dataInput) throws IOException {
        super(ClassFileConstants.CONSTANT_Utf8);
        this.value = dataInput.readUTF();
    }

    public ConstantUtf8(final String value) {
        super(ClassFileConstants.CONSTANT_Utf8);
        if (value == null) {
            throw new IllegalArgumentException("Value must not be null.");
        }
        this.value = value;
    }

    public String getBytes() {
        return value;
    }
}
