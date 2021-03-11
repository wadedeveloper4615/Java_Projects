package com.wade.decompiler.classfile.constant;

import java.io.DataInput;
import java.io.IOException;

import com.wade.decompiler.enums.ClassFileConstants;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = true, includeFieldNames = true)
public class ConstantInvokeDynamic extends ConstantCP {
    public ConstantInvokeDynamic(DataInput file) throws IOException {
        this(file.readShort(), file.readShort());
    }

    public ConstantInvokeDynamic(int bootstrap_method_attr_index, int name_and_type_index) {
        super(ClassFileConstants.CONSTANT_InvokeDynamic, bootstrap_method_attr_index, name_and_type_index);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
