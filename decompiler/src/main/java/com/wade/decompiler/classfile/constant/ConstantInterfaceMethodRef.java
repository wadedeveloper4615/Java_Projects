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
public class ConstantInterfaceMethodRef extends ConstantCP {
    public ConstantInterfaceMethodRef(DataInput input) throws IOException {
        super(ClassFileConstants.CONSTANT_InterfaceMethodref, input);
    }

    public ConstantInterfaceMethodRef(int class_index, int name_and_type_index) {
        super(ClassFileConstants.CONSTANT_InterfaceMethodref, class_index, name_and_type_index);
    }
}
